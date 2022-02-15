package com.kona.kms.cao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.security.pkcs11.wrapper.PKCS11Constants;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;

import safenet.jcprov.CK_ATTRIBUTE;
import safenet.jcprov.CK_C_INITIALIZE_ARGS;
import safenet.jcprov.CK_SESSION_HANDLE;
import safenet.jcprov.CK_SESSION_INFO;
import safenet.jcprov.Cryptoki;
import safenet.jcprov.constants.CK_RV;
import safenet.jcprov.constants.CK_USER_TYPE;
public class PTokenMgmt {

	private static final Logger logger = LoggerFactory.getLogger(PTokenMgmt.class);
	
	static PTokenMgmt m_instance;
	public static PTokenMgmt getInstance() {
		logger.debug("PTokenMgmt::getInstance: invoked....");
		if (m_instance == null) {
			m_instance = new PTokenMgmt();
		}
		return m_instance;
	}
	
	
	public String initPToken(int logicalSlotIx, String logicalSlotLabel, String soPin, String usrPin) throws KmsException{
		
		CK_RV	rv;
		CK_C_INITIALIZE_ARGS	cArgs;
		CK_SESSION_HANDLE	hSession;
		CK_SESSION_INFO		InfoSession;
		CK_USER_TYPE		userType; 	
		CK_ATTRIBUTE[] ckAttribute;
		
		byte[] soPinVal = soPin.getBytes();
		byte[] usrPinVal = usrPin.getBytes();
		int physicalSlotIx = logicalSlotIx;
		byte[] physicalTokenLabel = new byte[32];
	    // The PKCS#11 standard states that token labels must be 32 bytes padded with spaces.
		logicalSlotLabel = logicalSlotLabel + "                                ";
		System.arraycopy(logicalSlotLabel.getBytes(), 0, physicalTokenLabel, 0, 32);

	    /*
	     * Start cryptoki.
	     */
    	cArgs = new CK_C_INITIALIZE_ARGS(0);
    	rv = Cryptoki.C_Initialize(cArgs);
    	if (rv.intValue() != 0){
            logger.warn("Could not initialise token on slot [{}] - {}", physicalSlotIx, rv);
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
    	}
    	
        /*
         * Initialise the token.
         */
    	//rv = Cryptoki.C_InitToken(3, "changeit".getBytes(), 8, "slot-3".getBytes());
    	rv = Cryptoki.C_InitToken(physicalSlotIx, soPinVal, soPinVal.length, physicalTokenLabel);
  	
        /*
         * We now want to intialize the user pin. To do this we will use the 
         * C_InitPIN() function which can only be called in the "R/W SO Functions"
         * state. So, open a session and log in the SO.
         */
    	InfoSession = new CK_SESSION_INFO();
    	InfoSession.flags = PKCS11Constants.CKF_RW_SESSION;
    	hSession = new CK_SESSION_HANDLE();
    	
    	rv = Cryptoki.C_OpenSession(physicalSlotIx, InfoSession.flags, null, null, hSession);
    	if (rv.intValue() != 0) {
            logger.warn("Could not open a session on Slot [{}] - {}", physicalSlotIx, rv);
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
    	}

    	userType = new CK_USER_TYPE();
    	userType.setValue(PKCS11Constants.CKU_SO);
        //rv = C_Login(hSession, CKU_SO, pSoPin, strlen((char*)pSoPin));
        rv = Cryptoki.C_Login(hSession, userType, soPinVal, soPinVal.length);
    	if (rv.intValue() != 0) {
            logger.warn("Could not login SO on Slot [{}] - {}", physicalSlotIx, rv);
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
    	}

        /*
         * No errors, so initialse the user pin.
         */
        rv = Cryptoki.C_InitPIN(hSession, usrPinVal, usrPinVal.length);
    	if (rv.intValue() != 0) {
            logger.warn("Could not initialize user pin on Slot [{}] - {}", physicalSlotIx, rv);
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
    	}

        /*
         * Leave the put the application in the same state as before the function
         * was called. This means closing any open sessions.
         */
        rv = Cryptoki.C_CloseSession(hSession);
    	if (rv.intValue() != 0) {
            logger.warn("Could not close session on Slot [{}] - {}", physicalSlotIx, rv);
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
    	}

        return logicalSlotLabel;
    }

}

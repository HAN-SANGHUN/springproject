package com.kona.kms.crypto.bo;

import java.util.List;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import sun.security.pkcs11.wrapper.PKCS11Constants;


import com.kona.kms.crypto.CryptoManager;
import com.kona.kms.crypto.MechMgmt;
import com.kona.kms.crypto.TokenSession;
import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.token.KeyMap;
import com.kona.kms.crypto.mech.MechGeneric;
import com.kona.kms.crypto.mech.MechPkcs11;
import com.kona.kms.token.KeyManager;
import com.kona.kms.token.model.KeyInfo;


@Service
public class CryptoBOImpl implements CryptoBO {
	
	private static final Logger logger = LoggerFactory.getLogger(CryptoBOImpl.class);

	String aToken = "AdminToken";
	
	//1. encrypt - ret encrypted data
	public CryptoResponse encrypt(CryptoRequest request) throws KmsException, Exception {

		try{
			CryptoResponse response 			= new CryptoResponse(request);
			CryptoResponse cryptoResponse 	= new CryptoResponse();

			byte workCode = 0x00;

//			매커니즘을 선택한다.
			MechPkcs11 mech = MechMgmt.getInstance().getMechanism(request.mechanism);

			logger.debug("KeyManager.getInstance().getTokenIndexforKeyLabel : [{}]", request.keyLabel);

			request.slotIx = KeyManager.getInstance().getTokenIndexforKeyLabel(request.keyLabel);
			
			if(request.slotIx < 0)
				request.slotIx = CryptoManager.getInstance().getAdminToken();

			CryptoManager 	provider 		= CryptoManager.getInstance();;
			TokenSession 		tokenSession 	= provider.getTokenSession(request.slotIx);

			logger.debug("KeyManager.getInstance().request.slotIx : [{}]", request.slotIx);
			workCode = workCodeCheck(request);
			
			if (workCode == 0x0F){
				response.retStatus = -1;
				return response;
			}

			if (workCode == 0x01){		//KEY LABEL이 없는 경우
				logger.debug("KeyManager.workCode == 0x01 [{}]", request.keyLabel);
				String strImportMachanism = getImportKeyMechanism(request.mechanism);
				cryptoResponse = mech.importKey(request.slotIx , request.keyAttr, tokenSession, strImportMachanism);	
			}
			
			CryptoResponse encryptResponse  = mech.SEncrypt(request.slotIx, request.mechanism, request.mechanismParameters, request.keyLabel, request.data, tokenSession, cryptoResponse.hKey);
			response.dataEncrypted = encryptResponse.dataEncrypted;
			
			//add by shifei 2014-04-16
			if (workCode == 0x01){		//KEY LABEL이 없는 경우
				logger.debug("KeyManager.workCode == 0x01 [{}]", request.keyLabel);
				mech.destroySessionKey(cryptoResponse.hKey, tokenSession);	
			}
			
			tokenSession.CloseSession();

			if (response.dataEncrypted != null) {
				response.retStatus = 0;
			}
			else {
				response.retStatus = -1;
			}
			return response;
		}catch (KmsException e) {
			e.printStackTrace();
//			throw new KmsException(e.getMsgcode());
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}
	

	//2. decrypt - ret decrypted data
	public CryptoResponse decrypt(CryptoRequest request) throws KmsException, Exception {
		
		try{
			CryptoResponse response = new CryptoResponse(request);
			CryptoResponse cryptoResponse = new CryptoResponse();

			byte workCode = 0x00;

			MechPkcs11 mech = MechMgmt.getInstance().getMechanism(request.mechanism);
			//request.slotIx = CryptoManager.getInstance().getSlotIx(request.keyLabel);
			request.slotIx = KeyManager.getInstance().getTokenIndexforKeyLabel(request.keyLabel);
			
			if(request.slotIx < 0)
				request.slotIx = CryptoManager.getInstance().getAdminToken();
			
			CryptoManager provider = CryptoManager.getInstance();
			TokenSession tokenSession = provider.getTokenSession(request.slotIx);

			logger.debug("KeyManager.getInstance().request.slotIx : [{}]", request.slotIx);
			workCode = workCodeCheck(request);
			
			if (workCode == 0x0F){
				response.retStatus = -1;
				return response;
			}

			if (workCode == 0x01){		//KEY LABEL이 없는 경우
				logger.debug("KeyManager.workCode == 0x01 [{}]", request.keyLabel);
				String strImportMachanism = getImportKeyMechanism(request.mechanism);
				cryptoResponse = mech.importKey(request.slotIx , request.keyAttr, tokenSession, strImportMachanism);	
			}

			CryptoResponse decryptResponse = mech.SDecrypt(request.slotIx, request.mechanism, request.mechanismParameters, request.keyLabel, request.data,tokenSession,cryptoResponse.hKey);
			response.dataDecrypted = decryptResponse.dataDecrypted;
			
			//add by shifei 2014-04-16
			if (workCode == 0x01){		//KEY LABEL이 없는 경우
				logger.debug("KeyManager.workCode == 0x01 [{}]", request.keyLabel);
				mech.destroySessionKey(cryptoResponse.hKey, tokenSession);	
			}
			
			tokenSession.CloseSession();

			if (response.dataDecrypted != null) 
			{
				response.retStatus = 0;
			}
			else 
			{
				response.retStatus = -1;
			}
			
			return response;
			
		}catch (KmsException e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}
	//2. decrypt - ret decrypted data

	//3. digest - ret digest data
	public CryptoResponse digest(CryptoRequest request) throws KmsException, Exception {
		
		try{
			CryptoResponse response = new CryptoResponse(request);
			CryptoResponse cryptoResponse = new CryptoResponse();

			MechPkcs11 mech = MechMgmt.getInstance().getMechanism(request.mechanism);
			
			//---------- modified by shifei 20140403 ----------------------
			//long slotIx;
					
			if (request.tokenLabel != null)
			{
				request.slotIx = KeyManager.getInstance().getTokenIndexforTokenLabel(request.tokenLabel);	
			} else{
				request.slotIx = CryptoManager.getInstance().getAdminToken();
				if(request.slotIx < 0)
					request.slotIx = CryptoManager.getInstance().getAdminToken();
			}
			
			
			//-----------------------------------------------------------

			CryptoManager provider = CryptoManager.getInstance();
			TokenSession tokenSession = provider.getTokenSession(request.slotIx);
			
			cryptoResponse = mech.digest(request.slotIx, request.mechanism, request.mechanismParameters, request.data,tokenSession);
//			cryptoResponse = mech.digest(slotIx, request.mechanism, request.mechanismParameters, request.data,tokenSession);
			response.dataDigest = cryptoResponse.dataDigest;
			tokenSession.CloseSession();
			
			if (response.dataDigest != null) {
				response.retStatus = 0;
			}
			else {
				response.retStatus = -1;
			}
			return response;
		}catch (KmsException e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

	//4. sign - ret signature for the data
	public CryptoResponse sign(CryptoRequest request) throws KmsException, Exception {
		
		try{
			CryptoResponse response = new CryptoResponse(request);
			CryptoResponse cryptoResponse = new CryptoResponse();

			MechPkcs11 mech = MechMgmt.getInstance().getMechanism(request.mechanism);
			request.slotIx = KeyManager.getInstance().getTokenIndexforKeyLabel(request.keyLabel);
			
			if(request.slotIx < 0)
				request.slotIx = CryptoManager.getInstance().getAdminToken();
			
			CryptoManager provider = CryptoManager.getInstance();;
			TokenSession tokenSession = provider.getTokenSession(request.slotIx);

			cryptoResponse.signature = mech.sign(request.slotIx, request.mechanism, request.mechanismParameters, request.keyLabel, request.data,tokenSession);
			response.signature = cryptoResponse.signature;
			tokenSession.CloseSession();

			if (response.signature != null) 
			{
				response.retStatus = 0;
			}
			else 
			{
				response.retStatus = -1;
			}
			return response;
		}catch (KmsException e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
		
	}

	//5. verify - verification success:true, fail:false
	public CryptoResponse verify(CryptoRequest request) throws KmsException, Exception {
		
		try{
			CryptoResponse response = new CryptoResponse(request);
			MechPkcs11 mech = MechMgmt.getInstance().getMechanism(request.mechanism);
			//request.slotIx = CryptoManager.getInstance().getSlotIx(request.keyLabel);
			request.slotIx = KeyManager.getInstance().getTokenIndexforKeyLabel(request.keyLabel);
			
			if(request.slotIx < 0)
				request.slotIx = CryptoManager.getInstance().getAdminToken();
			
			CryptoManager provider = CryptoManager.getInstance();;
			TokenSession tokenSession = provider.getTokenSession(request.slotIx);

			response.verified = mech.verify(request.slotIx, request.mechanism, request.mechanismParameters, request.keyLabel, request.data, request.signature,tokenSession);
			tokenSession.CloseSession();

			response.retStatus = 0;
			return response;
		}catch (KmsException e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

	//6. secret key generation - ret generated-key label
	public CryptoResponse generateKey(CryptoRequest request) throws KmsException, Exception {
		logger.debug("CryptoBOImpl::generateKey=================================== ");

		try{
			CryptoResponse 	response 			= new CryptoResponse(request);
			CryptoResponse 	cryptoResponse 	= new CryptoResponse();

			MechPkcs11 mech = MechMgmt.getInstance().getMechanism(request.mechanism);
			
			List<Long> listPTokenLoc = null;

			if(request.tokenLabel == null || request.tokenLabel.length() == 0)
				listPTokenLoc = CryptoManager.getInstance().getAdminTokenList();
			else
				listPTokenLoc = KeyManager.getInstance().findTokenIndexforTokenLabel(request.tokenLabel);
			

			CryptoManager provider = CryptoManager.getInstance();

			if(listPTokenLoc != null) {
				for(int idx = 0; idx < listPTokenLoc.size(); idx++) 
				{
					Long ptokenLoc = listPTokenLoc.get(idx);
					TokenSession tokenSession = provider.getTokenSession(ptokenLoc);
					cryptoResponse = mech.generateKey(ptokenLoc, request.keyAttr, tokenSession,request.mechanism);
					tokenSession.CloseSession();
				}
			}	
			else {
				response.retStatus = -1;
			}
			
			response.generatedKeyLabel = cryptoResponse.generatedKeyLabel;
					
			if (response.generatedKeyLabel != null) {
				response.retStatus = 0;
				// add to KeyMap
				KeyInfo kinfo = new KeyInfo(response.generatedKeyLabel, "SECRET", "DES", 0, request.tokenLabel);
				KeyMap.getInstance().add(kinfo);
			}
			else {
				response.retStatus = -1;
			}
			return response;
		}catch (KmsException e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

	public CryptoResponse importSecretKey(CryptoRequest request) throws KmsException, Exception {
		logger.debug("CryptoBOImpl::importSecretKey=================================== ");

		try{
			CryptoResponse response = new CryptoResponse(request);
			CryptoResponse cryptoResponse = new CryptoResponse();

			MechPkcs11 mech = MechMgmt.getInstance().getMechanism(request.mechanism);

			List<Long> listPTokenLoc = null;

			if(request.tokenLabel == null || request.tokenLabel.length() == 0)
				listPTokenLoc = CryptoManager.getInstance().getAdminTokenList();
			else
				listPTokenLoc = KeyManager.getInstance().findTokenIndexforTokenLabel(request.tokenLabel);
		
			CryptoManager provider = CryptoManager.getInstance();;

			if(listPTokenLoc != null) {
				for(int idx = 0; idx < listPTokenLoc.size(); idx++) {
					Long ptokenLoc = listPTokenLoc.get(idx);
					logger.debug("CryptoBOImpl::importSecretKey: ptokenLoc : [{}]", ptokenLoc);
					TokenSession tokenSession = provider.getTokenSession(ptokenLoc);
					cryptoResponse= mech.importKey(ptokenLoc, request.keyAttr,tokenSession,request.mechanism);
					tokenSession.CloseSession();
				}
			}	
			else {
				response.retStatus = -1;
			}

			response.keyLabel = cryptoResponse.keyLabel;
			
			if (response.keyLabel != null) {
				response.retStatus = 0;
				// add to KeyMap
				KeyInfo kinfo = new KeyInfo(response.keyLabel, "SECRET", "DES", 0, request.tokenLabel);
				KeyMap.getInstance().add(kinfo);
			}
			else {
				response.retStatus = -1;
			}
			
			return response;
		}catch (KmsException e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}

	}	

	//7. public/private key pair generation - ret generated-key label pair
	public CryptoResponse generateKeyPair(CryptoRequest request) throws KmsException, Exception {
		
		try{
			CryptoResponse response = new CryptoResponse(request);
			CryptoResponse cryptoResponse = new CryptoResponse();

			MechPkcs11 mech = MechMgmt.getInstance().getMechanism(request.mechanism);
			List<Long> listPTokenLoc = null;
			
			if(request.tokenLabel == null || request.tokenLabel.length() == 0)
				listPTokenLoc = CryptoManager.getInstance().getAdminTokenList();
			else
				listPTokenLoc = KeyManager.getInstance().findTokenIndexforTokenLabel(request.tokenLabel);

			CryptoManager provider = CryptoManager.getInstance();
			
			if(listPTokenLoc != null) {
				for(int idx = 0; idx < listPTokenLoc.size(); idx++) {
					Long ptokenLoc = listPTokenLoc.get(idx);
					logger.debug("CryptoBOImpl::generateKeyPair: ptokenLoc : [{}]", ptokenLoc);
//					TokenSession tokenSession = provider.getTokenSession(request.slotIx);
					TokenSession tokenSession = provider.getTokenSession(ptokenLoc);
					cryptoResponse = mech.generateKeyPair(ptokenLoc, request.PubKeyAttr, request.PriKeyAttr,tokenSession);
					tokenSession.CloseSession();
				}
			}	
			
			response.keyLabelPair = cryptoResponse.keyLabelPair;
			
			if (response.keyLabelPair != null) {
				response.retStatus = 0;
				// add to KeyMap
				KeyInfo kinfoPub = new KeyInfo(response.keyLabelPair[0], "PUBLIC", "RSA", 0, request.tokenLabel);
				KeyMap.getInstance().add(kinfoPub);
				KeyInfo kinfoPri = new KeyInfo(response.keyLabelPair[1], "PRIVATE", "RSA", 0, request.tokenLabel);
				KeyMap.getInstance().add(kinfoPri);
			}
			else {
				response.retStatus = -1;
			}
			return response;
		}catch (KmsException e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}
	
	public CryptoResponse importPublicKey(CryptoRequest request) throws KmsException, Exception {
		
		try{
			CryptoResponse response = new CryptoResponse(request);
			CryptoResponse cryptoResponse = new CryptoResponse();

			MechPkcs11 mech = MechMgmt.getInstance().getMechanism(request.mechanism);
			
			List<Long> listPTokenLoc = null;
			if(request.tokenLabel == null || request.tokenLabel.length() == 0)
				listPTokenLoc = CryptoManager.getInstance().getAdminTokenList();
			else
				listPTokenLoc = KeyManager.getInstance().findTokenIndexforTokenLabel(request.tokenLabel);

			CryptoManager provider = CryptoManager.getInstance();;
			
			if(listPTokenLoc != null) {
				for(int idx = 0; idx < listPTokenLoc.size(); idx++) {
					Long ptokenLoc = listPTokenLoc.get(idx);
					logger.debug("CryptoBOImpl::importPublicKey: ptokenLoc : [{}]", ptokenLoc);
					TokenSession tokenSession = provider.getTokenSession(ptokenLoc);
					cryptoResponse = mech.importPubKey(ptokenLoc, request.keyAttr,tokenSession);
					tokenSession.CloseSession();
				}
			}
			
			response.keyLabel = cryptoResponse.keyLabel;
			
			if (response.keyLabel != null) {
				response.retStatus = 0;
				// add to KeyMap
				KeyInfo kinfoPub = new KeyInfo(response.keyLabel, "PUBLIC", "RSA", 0, request.tokenLabel);
				KeyMap.getInstance().add(kinfoPub);
			}
			else {
				response.retStatus = -1;
			}
			return response;
			
		}catch (KmsException e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}
	
	public CryptoResponse importPrivateKey(CryptoRequest request) throws KmsException, Exception {
		
		try{
			CryptoResponse response = new CryptoResponse(request);
			CryptoResponse cryptoResponse = new CryptoResponse();

			MechPkcs11 mech = MechMgmt.getInstance().getMechanism(request.mechanism);
			// TODO: how to control crypto api slots

			List<Long> listPTokenLoc = null;
			if(request.tokenLabel == null || request.tokenLabel.length() == 0)
				listPTokenLoc = CryptoManager.getInstance().getAdminTokenList();
			else
				listPTokenLoc = KeyManager.getInstance().findTokenIndexforTokenLabel(request.tokenLabel);

			CryptoManager provider = CryptoManager.getInstance();;

			if(listPTokenLoc != null) {
				for(int idx = 0; idx < listPTokenLoc.size(); idx++) {
					Long ptokenLoc = listPTokenLoc.get(idx);
					logger.debug("CryptoBOImpl::importPrivateKey: ptokenLoc : [{}]", ptokenLoc);
					TokenSession tokenSession = provider.getTokenSession(ptokenLoc);
					cryptoResponse = mech.importPriKey(ptokenLoc, request.keyAttr,tokenSession);
					tokenSession.CloseSession();
				}
			}	
			response.keyLabel = cryptoResponse.keyLabel;
			
			if (response.keyLabel != null) {
				response.retStatus = 0;
				// add to KeyMap
				KeyInfo kinfoPri = new KeyInfo(response.keyLabel, "PRIVATE", "RSA", 0, request.tokenLabel);
				KeyMap.getInstance().add(kinfoPri);
			}
			else {
				response.retStatus = -1;
			}
			return response;
		}catch (KmsException e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

	//8. wrap a private or secret key
	public CryptoResponse wrapKey(CryptoRequest request) throws KmsException, Exception {
		
		try{
			CryptoResponse response = new CryptoResponse(request);
			CryptoResponse cryptoResponse = new CryptoResponse();

			MechPkcs11 mech = MechMgmt.getInstance().getMechanism(request.mechanism);
			//request.slotIx = CryptoManager.getInstance().getSlotIx(request.keyLabel);
			request.slotIx = KeyManager.getInstance().getTokenIndexforKeyLabel(request.keyLabel);
			
			//add by shifei 2014-06-23
			if(request.slotIx < 0)
				request.slotIx = CryptoManager.getInstance().getAdminToken();

			CryptoManager provider = CryptoManager.getInstance();;
			TokenSession tokenSession = provider.getTokenSession(request.slotIx);
				
			cryptoResponse = mech.wrapKey(request.slotIx, request.mechanism, request.mechanismParameters, request.wrappingKeyLabel, request.keyLabel,tokenSession);
			
			tokenSession.CloseSession();
			
			response.keyWrapped = cryptoResponse.keyWrapped;
			
			if (response.keyWrapped != null) {
				response.retStatus = 0;
			}
			else {
				response.retStatus = -1;
			}
			return response;
		}catch (KmsException e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

	//9. unwrap a wrapped key
	public CryptoResponse unwrapKey(CryptoRequest request) throws KmsException, Exception {
		
		try{
			CryptoResponse response = new CryptoResponse(request);
			CryptoResponse cryptoResponse = new CryptoResponse();

			MechPkcs11 mech = MechMgmt.getInstance().getMechanism(request.mechanism);

			request.slotIx = KeyManager.getInstance().getTokenIndexforKeyLabel(request.keyLabel);
			
			//add by shifei 20140704
			if(request.slotIx < 0)
				request.slotIx = CryptoManager.getInstance().getAdminToken();

			
			CryptoManager provider = CryptoManager.getInstance();;
			TokenSession tokenSession = provider.getTokenSession(request.slotIx);

			cryptoResponse = mech.unwrapKey(request.slotIx, request.mechanism, request.mechanismParameters, request.keyLabel, request.data, request.keyAttr,tokenSession);

			response.generatedKeyLabel = cryptoResponse.generatedKeyLabel;
			
			if (response.generatedKeyLabel != null) {
				response.retStatus = 0;
				// add to KeyMap
				// TODO: need to check mechanism and algorithm
//				KeyInfo kinfo = new KeyInfo(response.generatedKeyLabel, "SECRET", "DES", 0, request.tokenLabel);
				
				String mechanism = "SECRET";
				String algorithm = "DES";
				
				if(request.keyAttr.cka_keyClass == PKCS11Constants.CKO_SECRET_KEY){
					mechanism = "SECRET";
					algorithm = "DES";	
				}else if(request.keyAttr.cka_keyClass == PKCS11Constants.CKO_PRIVATE_KEY){
					mechanism = "PRIVATE";
					algorithm = "RSA";
				}else{
//					response.retStatus = -1;
				}
				
				KeyInfo kinfo = new KeyInfo(response.generatedKeyLabel, mechanism, algorithm, 0, request.tokenLabel);
				KeyMap.getInstance().add(kinfo);
			}
			else {
				response.retStatus = -1;
			}
			return response;
		}catch (KmsException e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

	//10. derive a key
	public CryptoResponse deriveKey(CryptoRequest request) throws KmsException, Exception {
		
		try{
			CryptoResponse response = new CryptoResponse(request);
			CryptoResponse cryptoResponse = new CryptoResponse();

			MechPkcs11 mech = MechMgmt.getInstance().getMechanism(request.mechanism);
			//request.slotIx = CryptoManager.getInstance().getSlotIx(request.keyLabel);
			request.slotIx = KeyManager.getInstance().getTokenIndexforKeyLabel(request.keyLabel);

			CryptoManager provider = CryptoManager.getInstance();;
			TokenSession tokenSession = provider.getTokenSession(request.slotIx);

			cryptoResponse = mech.deriveKey(request.slotIx, request.mechanism, request.mechanismParameters, request.keyLabel, request.keyAttr,tokenSession);
			response.generatedKeyLabel = cryptoResponse.generatedKeyLabel;

			if (response.generatedKeyLabel != null) {
				response.retStatus = 0;
				// add to KeyMap
				// TODO: need to check mechanism and algorithm
//				KeyInfo kinfo = new KeyInfo(response.generatedKeyLabel, "SECRET", "DES", 0, request.tokenLabel);
				
				String mechanism = "SECRET";
				String algorithm = "DES";
				
				if(request.keyAttr.cka_keyClass == PKCS11Constants.CKO_SECRET_KEY){
					mechanism = "SECRET";
					algorithm = "DES";	
				}else if(request.keyAttr.cka_keyClass == PKCS11Constants.CKO_PRIVATE_KEY){
					mechanism = "PRIVATE";
					algorithm = "RSA";
				}else if(request.keyAttr.cka_keyClass == PKCS11Constants.CKO_PUBLIC_KEY){
					mechanism = "PUBLIC";
					algorithm = "RSA";
				}
				else{
//					response.retStatus = -1;
				}
				
				KeyInfo kinfo = new KeyInfo(response.generatedKeyLabel, mechanism, algorithm, 0, request.tokenLabel);
				KeyMap.getInstance().add(kinfo);
			}
			else {
				response.retStatus = -1;
			}
			return response;
		}catch (KmsException e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
		
	}
	
	public CryptoResponse getAttributeValue(CryptoRequest request) throws KmsException, Exception {
		
		try{
			CryptoResponse response = new CryptoResponse(request);
			CryptoResponse cryptoResponse = new CryptoResponse();

			//request.slotIx = CryptoManager.getInstance().getSlotIx(request.keyLabel);
			request.slotIx = KeyManager.getInstance().getTokenIndexforKeyLabel(request.keyLabel);

			//modify by shifei 20140822
//			CryptoManager provider = CryptoManager.getInstance();
//			TokenSession tokenSession = provider.getTokenSession(request.slotIx);

			if(request.slotIx < 0)
				request.slotIx = CryptoManager.getInstance().getAdminToken();
			
			//add by shifei 20140822
			CryptoManager provider = CryptoManager.getInstance();
			TokenSession tokenSession = provider.getTokenSession(request.slotIx);
			
			cryptoResponse = (MechGeneric.getInstance()).getAttributeValue(request.slotIx, request.keyLabel,tokenSession);
			response.attrs = cryptoResponse.attrs;

			tokenSession.CloseSession();
			
			if (response.attrs != null) {
				response.retStatus = 0;
			}
			else {
				response.retStatus = -1;
			}
			return response;
		}catch (KmsException e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

	public CryptoResponse destroyKey(CryptoRequest request) throws KmsException, Exception {
		logger.debug("CryptoBOImpl::destroyKey: start...");
		
		try{
			CryptoResponse response = new CryptoResponse(request);
			CryptoResponse cryptoResponse = new CryptoResponse();
			
			List<Long> listPTokenLoc = KeyManager.getInstance().findTokenIndexforKeyLabel(request.keyLabel);	

			CryptoManager provider = CryptoManager.getInstance();
			
			if(listPTokenLoc == null){
				logger.debug("CryptoBOImpl::destroyKey: listPTokenLoc is null");
				listPTokenLoc = CryptoManager.getInstance().getAdminTokenList();
			}
			
			try{
				for(int idx = 0; idx < listPTokenLoc.size(); idx++) {
					Long ptokenLoc = listPTokenLoc.get(idx);	
					logger.debug("CryptoBOImpl::destroyKey: ptokenLoc : [{}]", ptokenLoc);
					TokenSession tokenSession = provider.getTokenSession(ptokenLoc);
					MechGeneric.getInstance().destroyKey(ptokenLoc, request.keyLabel,tokenSession);
					tokenSession.CloseSession();
				}
			} catch (KmsException e) {
				e.printStackTrace();
//				throw new KmsException(e.getMessage());
				throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
			}
			
			KeyMap.getInstance().delete(request.keyLabel);
			
			response.retStatus = 0;
			return response;
		}catch (KmsException e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}
	
	public CryptoResponse generateRandom(CryptoRequest request) throws KmsException, Exception {
		
		try{
			CryptoResponse response = new CryptoResponse(request);
			CryptoResponse cryptoResponse = new CryptoResponse();

			//request.slotIx = CryptoManager.getInstance().getLiveTokenSlotList()[0];
			//request.slotIx = KeyManager.getInstance().getTokenIndexforTokenLabel(request.tokenLabel);
			
			if(request.tokenLabel == null || request.tokenLabel.length() == 0)
				request.slotIx = CryptoManager.getInstance().getAdminToken();
			else
				request.slotIx = KeyManager.getInstance().getTokenIndexforTokenLabel(request.tokenLabel);

			CryptoManager provider = CryptoManager.getInstance();;
			TokenSession tokenSession = provider.getTokenSession(request.slotIx);
			
			cryptoResponse= (MechGeneric.getInstance()).generateRandom(request.slotIx, request.size,tokenSession);
			response.randomData = cryptoResponse.randomData;

			tokenSession.CloseSession();
			if (response.randomData != null) {
				response.retStatus = 0;
			}
			else {
				response.retStatus = -1;
			}
			
			return response;
		}catch (KmsException e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

	public byte  workCodeCheck(CryptoRequest request){

//		logger.debug("request.keyLabel.isEmpty() : {} , request.keyAttr.cka_value.length = {}", request.keyLabel.isEmpty(),request.keyAttr.cka_value.length );
		if (request.keyLabel.isEmpty() == true){			//key label이 없는경우
			logger.debug("Case  : 1");
			return 0x01;
		}else if(request.keyLabel.isEmpty() == false ){  	//key label이 있는경우 
			logger.debug("Case  : 2");
			return 0x02;
		}else {
			return 0x0F;
		}
	}

//키를 임시키로 encrypt,decrypt할 경우 createobject할떄의 키 매커니즘을 모를 경우 사용하는 함수	
	public String getImportKeyMechanism(String strMechanism){

		String strImportMechanism = "";

		logger.debug("getImportKeyMechanism strMechanism : [{}]", strMechanism);
		
		if(strMechanism.equalsIgnoreCase("DES_ECB") || strMechanism.equalsIgnoreCase("DES_CBC") || strMechanism.equalsIgnoreCase("DES_MAC"))
		{ 
			strImportMechanism = "DES_KEY_GEN"; // des3
		}
		else if(strMechanism.equalsIgnoreCase("DES2_ECB") || strMechanism.equalsIgnoreCase("DES2_CBC") || strMechanism.equalsIgnoreCase("DES2_MAC"))
		{ 
			strImportMechanism = "DES2_KEY_GEN"; // des2
		}
		else if(strMechanism.equalsIgnoreCase("DES3_ECB") || strMechanism.equalsIgnoreCase("DES3_CBC") || strMechanism.equalsIgnoreCase("DES3_MAC"))
		{ 
			strImportMechanism = "DES3_KEY_GEN"; // des3
		}
		else if(strMechanism.equalsIgnoreCase("SEED_ECB") || strMechanism.equalsIgnoreCase("SEED_CBC") || strMechanism.equalsIgnoreCase("SEED_MAC"))
		{ 
			strImportMechanism = "SEED_KEY_GEN"; // des3
		}
		else if(strMechanism.equalsIgnoreCase("AES_ECB") || strMechanism.equalsIgnoreCase("AES_CBC") || strMechanism.equalsIgnoreCase("AES_MAC"))
		{ 
			strImportMechanism = "AES_KEY_GEN"; // des3
		}
		
//RSA키는 임시키를 생성하는 것이 없다. 		
/*		else if(strMechanism.equalsIgnoreCase("RSA_PKCS") || strMechanism.equalsIgnoreCase("RSA_X_509"))
		{
			strImportMechanism = "RSA_PKCS_KEY"; // RSA KEY
		}
*/		else{
			strImportMechanism = "NOT_MECHANISM";
		}

		logger.debug("getImportKeyMechanism mechanism value : [{}]", strImportMechanism);

		return strImportMechanism;
	}	
	
//안쓰는 함수로 파악됨
	public CryptoResponse encrypt_v2(CryptoRequest request) throws KmsException {
		CryptoResponse response = new CryptoResponse(request);
		CryptoResponse cryptoResponse = new CryptoResponse();

		MechPkcs11 mech = MechMgmt.getInstance().getMechanism(request.mechanism);
		//request.slotIx = KeyManager.getInstance().getTokenIndexforTokenLabel(request.tokenLabel);
		
		if(request.tokenLabel == null || request.tokenLabel.length() == 0)
			request.slotIx = CryptoManager.getInstance().getAdminToken();
		else
			request.slotIx = KeyManager.getInstance().getTokenIndexforTokenLabel(request.tokenLabel);

		CryptoManager provider = CryptoManager.getInstance();;
		TokenSession tokenSession = provider.getTokenSession(request.slotIx);

		cryptoResponse = mech.encrypt(request.slotIx, request.mechanism, request.mechanismParameters, request.keyLabel, request.data,tokenSession);
		response.dataEncrypted = cryptoResponse.dataEncrypted;

		if (response.dataEncrypted != null) {
			response.retStatus = 0;
		}
		else {
			response.retStatus = -1;
		}
		return response;
	}
	
	
}

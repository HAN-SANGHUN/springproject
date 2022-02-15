package com.kona.kms.token;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.security.pkcs11.wrapper.CK_SLOT_INFO;
import sun.security.pkcs11.wrapper.CK_TOKEN_INFO;
import sun.security.pkcs11.wrapper.PKCS11;
import sun.security.pkcs11.wrapper.PKCS11Constants;
import sun.security.pkcs11.wrapper.PKCS11Exception;

import com.kona.kms.crypto.CryptoManager;
import com.kona.kms.crypto.PToken;
import com.kona.kms.crypto.TokenSession;

@SuppressWarnings("restriction")

public class PHsm {

	private static final Logger logger = LoggerFactory.getLogger(PHsm.class);

	PHsm(String label, String address, int startIx, int slotCount) {
		this.label = label;
		this.address = address;
		this.slotIx = startIx;
		this.slotCount = slotCount;
		
		Object[] prs = new Object[5];
		prs[0] = this;
		prs[1] = label;
		prs[2] = address;
		prs[3] = startIx;
		prs[4] = slotCount;
		logger.info("PHsm::PHsm: created @{}, label [{}], address [{}], startIx [{}], slotCount [{}]", prs);
		
		tokens = new ConcurrentHashMap<Integer, PToken>();
	}
	
	String label;
	String address;
	int slotIx;
	int slotCount;

	public void setName(String label) {
		this.label = label;
	}
	
	public void setAddress(String addr) {
		this.address = addr;
	}
	
	public void setSlotIx(int ix) {
		this.slotIx = ix;
	}
	
	public void setSlotCount(int count) {
		this.slotCount = count;
	}
	
	
	void init() {
		logger.debug("PHsm::init: invoked....");
		p11 = getPkcs11Path(label, address);
		getSlotInfo();
	}
	
	PKCS11 getPkcs11Path(String hsmLabel, String address) {
		return CryptoManager.getInstance().getPkcs11Intf();
	}
	
	String getTokenLabel(int slotOfs) {
		PToken pt = getToken(slotOfs);
		if (pt == null) return null;
		return pt.tokenLabel;
	}
	
	/********************************************************************************/
	
	PKCS11 p11 = null;

	ConcurrentHashMap<Integer, PToken> tokens;
	
	void putToken(PToken ptoken) {
		tokens.put(Integer.valueOf((int)ptoken.slotIx), ptoken);
	}
	public PToken getToken(long slotIx) {
		return tokens.get(Integer.valueOf((int)slotIx));
	}
	
	public TokenSession getTokenSession(long slotIx) {
		PToken pt = tokens.get(Integer.valueOf((int)slotIx));
		if (pt != null) {
			return pt.getSession();
		}
		return null;
	}
	
	public TokenSession getTokenSession() {
		if (tokens.size() <= 0)
			return null;
		PToken pt = (PToken) tokens.values().toArray()[0];
		if (pt != null) {
			return pt.getSession();
		}
		return null;
	}
	
	public long[] getLiveTokenSlotList() {
		Object[] list = tokens.keySet().toArray();
		if (list.length == 0)
			return null;
		long[] tlist = new long[list.length];
		for (int i=0; i<tlist.length; i++)
			tlist[i] = ((Integer)list[i]).longValue();
		return tlist;
	}

	void getSlotInfo() {
		
		long[] slotList = null;
		PToken ptoken = null;
		try {
			slotList = p11.C_GetSlotList(true);
			int l = slotList.length;
			for (int i=0; i<l; i++) {
				CK_SLOT_INFO slotInfo = p11.C_GetSlotInfo(slotList[i]);
				//logger.error("slot[{}] - {}", slotList[i], slotInfo.toString());
				
				if ((slotInfo.flags & PKCS11Constants.CKF_TOKEN_PRESENT) == 0) {
					logger.warn("slot[{}] - token not present !!!", slotList[i]);
					continue;
				}
				
				CK_TOKEN_INFO tokenInfo = p11.C_GetTokenInfo(slotList[i]);
				//logger.debug("		slot[{}] - {}", slotList[i], tokenInfo.toString());
				
				if (String.valueOf(tokenInfo.label).indexOf("AdminToken") >= 0) {
					logger.warn("slot[{}] - Admin token found!, label [{}] !!!", slotList[i], String.valueOf(tokenInfo.label));
					continue;
				}
				
				if ((tokenInfo.flags & PKCS11Constants.CKF_USER_PIN_INITIALIZED) != 0) {
					logger.info("slot[{}] - TOKEN USER PIN INITIALIZED !!!", slotList[i]);
					ptoken = new PToken(p11, label, slotList[i], String.valueOf(tokenInfo.label).trim());
					putToken(ptoken);
				}
				else {
					logger.warn("slot[{}] - TOKEN USER PIN NOT INITIALIZED !!!", slotList[i]);
				}
			}
		} catch (PKCS11Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

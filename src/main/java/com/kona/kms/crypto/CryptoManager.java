package com.kona.kms.crypto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.security.pkcs11.wrapper.*;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.TokenSession;

@SuppressWarnings("restriction")

public class CryptoManager {
	private static final Logger logger = LoggerFactory.getLogger(CryptoManager.class);
	
	static CryptoManager m_instance;
	PKCS11 	p11 = null;
	String 	library = null;
	String 	hsmId = null;
	
	ArrayList<String> slotPin;
	ConcurrentHashMap<Integer, PToken> tokens;
	
	//add 20150901
	int adminTokenAccessNo = 0;
	int livePhysicalHsmCnt = 0;

	public static CryptoManager getInstance() {

		if (m_instance == null) {
			m_instance = new CryptoManager();
		}
		return m_instance;
	}
	
	private CryptoManager() {
		logger.debug("CryptoManager::CryptoManager: invoked....");
		tokens = new ConcurrentHashMap<Integer, PToken>();
		slotPin = new ArrayList<String>();
	}
	
	
	public void setLibrary(String path) {
		logger.debug("CryptoManager::setLibrary: invoked...." + path);
		library = path;
		hsmId = "KMS_HSM";
		setPkcs11Intf();
	}
	
	public PKCS11 getPkcs11Intf() {
		return p11;
	}
	
	void setPkcs11Intf() {
		try {
			p11 = PKCS11.getInstance(library, "C_GetFunctionList", null, false);
			
			//TODO:
			ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
			long initialDelay1 = 1;
			long period1 = 1;
			service.scheduleAtFixedRate(new ScheduledExecutorBeatHsm(p11), initialDelay1, period1, TimeUnit.HOURS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO:
	class ScheduledExecutorBeatHsm implements Runnable{
		PKCS11 pkcs11 = null;
		
		public ScheduledExecutorBeatHsm(PKCS11 pk11){
			pkcs11 = pk11;
		}
		
		public void run(){
			try {
				long[] slotList = pkcs11.C_GetSlotList(true);
//				System.out.println("slotList size: " + slotList.length);
				logger.debug("CryptoManager: ScheduledExecutorBeatHsm - slotList size [{}]", slotList.length);
			} catch (PKCS11Exception e) {
				logger.debug("CryptoManager: ScheduledExecutorBeatHsm [{}]", e);
			}	
		}
	}
	
	/*
	public void setSlotConf(ArrayList<String> list) {
		logger.debug("CryptoManager::setSlotConf: invoked....");
		slotConf = list;
	}
	*/

	// bean init method
	public void init() {
		logger.debug("===> CryptoManager::init: invoked....");
		getSlotInfo();
		
		//add 20150901
		setLivePhysicalHsmCnt();
	}

	public void reinit() {
		logger.debug("===> CryptoManager::init: invoked....");
		regetSlotInfo();
		
		//add 20150901
		setLivePhysicalHsmCnt();
	}
	
	public void setSlotPin(String pin){
		slotPin.add(pin);
	}
	
	void putToken(PToken pt) {
		tokens.put(Integer.valueOf((int)pt.slotIx), pt);
	}
	
	public Object[] getTokens() {
		return tokens.values().toArray();
	}
	
	public long findSlotByTokenLabel(String tokenLabel) {
		logger.debug("findSlotByTokenLabel: tokenLabel [{}]", tokenLabel);		
		Object[] vlist = tokens.values().toArray();
		
		if (vlist.length == 0)
			return -1;
		
		if (tokenLabel == null) {
			return ((PToken)vlist[0]).slotIx;	// default 
		}
		
		for (int i=0; i<vlist.length; i++) {
			PToken pt = (PToken) vlist[i];
			if (pt.tokenLabel.equals(tokenLabel) == true) {
				logger.debug("findSlotByTokenLabel: {} Found at slotIx [{}]", tokenLabel, pt.slotIx);		
				return pt.slotIx;
			}
		}
		
		logger.debug("findSlotByTokenLabel: {} Not Found !!!", tokenLabel);		
		return -1;
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
	
	// temporarily 
	public int getSlotIx(String keyLabel) throws KmsException {
		long[] slots = getLiveTokenSlotList();
		if (slots == null) {
			throw new KmsException(KMSCode.KR_TOKEN_NOT_FOUND);
		}
		long found;
		if ((found=findSlotByKeyLabel(keyLabel))<0) {
			found = slots[0];
		}
		return (int) found;
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
	
	public long findSlotByKeyLabel(String keyLabel) throws KmsException {
		long ret = -1;
		Object[] list = tokens.keySet().toArray();
		if (list.length == 0) return ret;
		for (int i=0; i<list.length; i++) {
			PToken pt = tokens.get(list[i]);
			if (pt.findKeyByLabel(keyLabel) >= 0) {
				return pt.slotIx;
			}
		}
		return ret;
	}

	public void printLiveTokenInfo() {
		System.out.format("============== printLiveTokenInfo ==============\n");

		Object[] list = tokens.keySet().toArray();

		if (list.length == 0) 
			return;

		for (int i=0; i<list.length; i++) {
			PToken pt = tokens.get(list[i]);
			System.out.format("\t [%d] token slot [%d] - label (%s:%s)\n",
								i, pt.slotIx, pt.tokenLabel, slotPin.get((int) pt.slotIx)); 
		}
		System.out.format("-------------- printLiveTokenInfo --------------\n");
	}

	void getSlotInfo() {
		long[] slotList = null;
		PToken ptoken = null;
		int slotCount = 0;
		
		try {

			slotList = p11.C_GetSlotList(true);
			slotCount = slotList.length;

			logger.debug("CryptoManager :: slotInfo:slot count [{}]", slotCount);

			for (int i=0; i<slotCount; i++) {
				CK_SLOT_INFO slotInfo = p11.C_GetSlotInfo(slotList[i]);

				logger.debug("CryptoManager  :: slotInfo:slot[{}] - {}", slotList[i], slotInfo.toString());
				
				if ((slotInfo.flags & PKCS11Constants.CKF_TOKEN_PRESENT) == 0) {
					logger.debug("CryptoManager  :: slot[{}] - token NOT present", slotList[i]);
					continue;
				}
				
				CK_TOKEN_INFO tokenInfo = p11.C_GetTokenInfo(slotList[i]);

				logger.debug("tokenInfo:slot[{}] - {}", slotList[i], tokenInfo.toString());
				
				if (String.valueOf(tokenInfo.label).indexOf("AdminToken") >= 0) {
					logger.info("-----slot[{}] - Admin token found. label [{}] !!!", slotList[i], String.valueOf(tokenInfo.label));
					
					// JERRY add 1028
					ptoken = new PToken(p11, hsmId, slotList[i], String.valueOf(tokenInfo.label).trim());
					if(slotPin.isEmpty()==false){
						ptoken.setPin(slotPin.get((int)slotList[i]));
						putToken(ptoken);
					}
					
					continue;
				}
				
				if ((tokenInfo.flags & PKCS11Constants.CKF_USER_PIN_INITIALIZED) != 0) {
					logger.info("-----slot[{}] - TOKEN USER PIN INITIALIZED", slotList[i]);
					ptoken = new PToken(p11, hsmId, slotList[i], String.valueOf(tokenInfo.label).trim());
					if(slotPin.isEmpty()==false){
						ptoken.setPin(slotPin.get((int)slotList[i]));
						putToken(ptoken);
					}
					else
						logger.debug("slotConf is EMPTY i:slotList [{}:{}]", i, slotList[i]);
				}
				else {
					logger.info("-----slot[{}] - TOKEN USER PIN NOT INITIALIZED", slotList[i]);
				}
			}
			
			printLiveTokenInfo();

		} catch (PKCS11Exception e) {
			e.printStackTrace();
		}
		logger.debug("*********** slotPin : [{}]", slotPin.size());
		logger.debug("CryptoManager::getSlotInfo: end.....");
	}
	
	
	void regetSlotInfo() {
		long[] slotList = null;
		PToken ptoken = null;
		int slotCount = 0;
		
		try {

			slotList = p11.C_GetSlotList(true);
			slotCount = slotList.length;

			logger.debug("CryptoManager :: slotInfo:slot count [{}]", slotCount);

			for (int i=0; i<slotCount; i++) {
				CK_SLOT_INFO slotInfo = p11.C_GetSlotInfo(slotList[i]);

				logger.debug("CryptoManager  :: slotInfo:slot[{}] - {}", slotList[i], slotInfo.toString());
				
				if ((slotInfo.flags & PKCS11Constants.CKF_TOKEN_PRESENT) == 0) {
					logger.debug("CryptoManager  :: slot[{}] - token NOT present", slotList[i]);
					continue;
				}
				
				CK_TOKEN_INFO tokenInfo = p11.C_GetTokenInfo(slotList[i]);

				logger.debug("tokenInfo:slot[{}] - {}", slotList[i], tokenInfo.toString());
				
				if (String.valueOf(tokenInfo.label).indexOf("AdminToken") >= 0) {
					logger.info("-----slot[{}] - Admin token found. label [{}] !!!", slotList[i], String.valueOf(tokenInfo.label));
					
					// JERRY add 1028
					ptoken = new PToken(p11, hsmId, slotList[i], String.valueOf(tokenInfo.label).trim());
					if(slotPin.isEmpty()==false){
						ptoken.setPin(slotPin.get((int)(slotList[i]+slotCount)));
						putToken(ptoken);
					}
					
					continue;
				}
				
				if ((tokenInfo.flags & PKCS11Constants.CKF_USER_PIN_INITIALIZED) != 0) {
					logger.info("-----slot[{}] - TOKEN USER PIN INITIALIZED", slotList[i]);
					ptoken = new PToken(p11, hsmId, slotList[i], String.valueOf(tokenInfo.label).trim());
					if(slotPin.isEmpty()==false){
						ptoken.setPin(slotPin.get((int)(slotList[i]+slotCount)));
						putToken(ptoken);
					}
					else
						logger.debug("slotConf is EMPTY i:slotList [{}:{}]", i, slotList[i]);
				}
				else {
					logger.info("-----slot[{}] - TOKEN USER PIN NOT INITIALIZED", slotList[i]);
				}
			}
			
			printLiveTokenInfo();

		} catch (PKCS11Exception e) {
			e.printStackTrace();
		}
		logger.debug("*********** slotPin : [{}]", slotPin.size());
		logger.debug("CryptoManager::getSlotInfo: end.....");
	}


	public List<Long> getAdminTokenList() {
		ArrayList<Long> listPTokenLoc = new ArrayList<Long>();
		listPTokenLoc.add((long)slotPin.size()-1);
		return listPTokenLoc;
	}	
	
	public long getAdminToken() {
		long slotIx = (long)slotPin.size()-1;
		
		
		//add 20150901
		List<Long> listAdminToken = new ArrayList<Long>();
				
		Object[] list = tokens.keySet().toArray();

		if (list.length == 0) 
			return slotIx;

		for (int i=0; i<list.length; i++) {
			PToken pt = tokens.get(list[i]);
			
			if (pt.tokenLabel.indexOf("AdminToken") >= 0){
				listAdminToken.add(pt.slotIx);
			}
		}
		
		livePhysicalHsmCnt = listAdminToken.size();
		
		adminTokenAccessNo = (adminTokenAccessNo + 1) % livePhysicalHsmCnt;
		slotIx = listAdminToken.get(adminTokenAccessNo);
		
		for(int i=0; i<listAdminToken.size(); i++){
			System.out.println("AdminToken slot: " + listAdminToken.get(i));	
		}
		System.out.println("livePhysicalHsmCnt: " + livePhysicalHsmCnt);
		System.out.println("adminTokenAccessNo: " + adminTokenAccessNo);		
		System.out.println("slotIx: " + slotIx);
		
		
		return slotIx;
	}

	//add 20150901
	public int getLivePhysicalHsmCnt() {
		return livePhysicalHsmCnt;
	}

	//add 20150901
	public void setLivePhysicalHsmCnt() {
//		this.livePhysicalHsmCnt = livePhysicalHsmCnt;
		
		List<Long> listAdminToken = new ArrayList<Long>();
		
		Object[] list = tokens.keySet().toArray();

		for (int i=0; i<list.length; i++) {
			PToken pt = tokens.get(list[i]);
			
			if (pt.tokenLabel.indexOf("AdminToken") >= 0){
				listAdminToken.add(pt.slotIx);
			}
		}
		
		this.livePhysicalHsmCnt = listAdminToken.size();

		System.out.println("setLivePhysicalHsmCnt::livePhysicalHsmCnt: " + livePhysicalHsmCnt);
	}	
	
	
	
}

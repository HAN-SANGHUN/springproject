package com.kona.kms.token;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.CryptoManager;
import com.kona.kms.token.dao.TokenMapDao;
import com.kona.kms.token.model.KeyInfo;
import com.kona.kms.token.model.LHSMModel;
import com.kona.kms.token.model.LTokenModel;
import com.kona.kms.token.model.PHSMModel;
import com.kona.kms.token.model.PTokenModel;
import com.kona.kms.web.cert.bo.CertMgmtBO;

public class KeyManager {
	private static final Logger logger = LoggerFactory.getLogger(KeyManager.class);
	
	@Autowired
	private TokenMapDao tokenMapDao;

	private boolean isInit = false;
	public List<LHSMModel> lhsmConf = null;
	
	static KeyManager m_instance;
	static String soPin;
	static String adminPin;
	
	//add 20150902
	static int accessNo = 0;
	

	public static KeyManager getInstance() {
		logger.debug("KeyManager::getInstance: invoked....");
		if (m_instance == null) {
			m_instance = new KeyManager();
		}
		return m_instance;
	}
	
	private KeyManager() {
		logger.debug("KeyManager::KeyManager: invoked....");
	}

	public void setSoPin(String pin) {
		soPin = pin;
		logger.debug("KeyManager::setSOPin: soPin [{}]", soPin);
	}
	
	public static String getSoPin() {
		return soPin;
	}
	
	public void setAdminPin(String pin) {
		adminPin = pin;
		logger.debug("KeyManager::setAdminPin: adminPin [{}]", adminPin);
	}
	
	public static String getAdminPin() {
		return adminPin;
	}
			
	public static int getSlotIx(String keyLabel) throws KmsException {
		long[] slots = CryptoManager.getInstance().getLiveTokenSlotList();
		if (slots == null) {
			throw new KmsException(KMSCode.KR_TOKEN_NOT_FOUND);
		}
		return (int) slots[0];
	}
	
	// bean init method
	public void init() {

		lhsmConf = tokenMapDao.getTokenMapInfo();

		if(lhsmConf != null ) {
			logger.debug("===> KeyManager::init: invoked..., cluster count {}",lhsmConf.size() );

			int lhsmCnt = lhsmConf.size();
			int totalLogicalSlotCnt = 0;
			int totalPHsmCnt = 0;
			
			for (int i=0; i<lhsmCnt; i++) {
				LHSMModel logicalHsm = (LHSMModel) lhsmConf.get(i);

				logger.debug("KeyManager::init: Logical HSM [{}:{}] found", i, logicalHsm.getLhsmLabel());
				logger.debug("KeyManager::init: Logical token count [{}:{}]", i, logicalHsm.getLtokenCnt());
				
				List<PHSMModel> 	phmsConf = logicalHsm.getPhsmModel();
				List<LTokenModel> lTokenConf = logicalHsm.getLtokenModel();
				
				if(phmsConf != null) {

					int phsmCnt = phmsConf.size();
					int lTokenCnt = lTokenConf.size();

					logger.debug("KeyManager::init: Logical HSM [{}]'s hsm count - {}", i, phmsConf.size());
					
					if (phsmCnt == 0) continue;
					
					/****/
					//logicalHsm.init(totalLogicalSlotCnt);
					for (int j=0; j<phsmCnt; j++) {
						
						PHSMModel physicalHsm = (PHSMModel) phmsConf.get(j);
						
						for (int k=0; k<lTokenCnt;k++) {
							LTokenModel logicalToken = (LTokenModel) lTokenConf.get(k);
							logger.debug("KeyManager::init: test k:pin [{}:{}]", k, logicalToken.getTokenPin());
							(CryptoManager.getInstance()).setSlotPin(logicalToken.getTokenPin());
							logger.debug("KeyManager::init: test ----------");
						}
						
						(CryptoManager.getInstance()).setSlotPin(adminPin);
						/*
						TokenEntity tokenEntity = new TokenEntity();
						tokenEntity.init(physicalHsm.getHsmLabel(), physicalHsm.getIpAddress());
						physicalHsm.setPkcs11(tokenEntity.getPKCS11());
						*/
					}
					/****/
					totalPHsmCnt += phsmCnt;
					totalLogicalSlotCnt += logicalHsm.getLslotEndNo() - logicalHsm.getLtokenStartNo() + 1;
				}
				
				logger.debug("KeyManager::init: total Phisical HSM Count [{}], total token count [{}]", totalPHsmCnt, totalLogicalSlotCnt);
			} 
			
			if(totalPHsmCnt > 0) {
				TokenMap.getInstance().load();
				isInit = true;
			}
		} else {
			logger.debug("KeyManager::init: invoked..., TokenConfig List is null !!");
		}
	}
	
	
	public void reinit() {

		lhsmConf = tokenMapDao.getTokenMapInfo();

		if(lhsmConf != null ) {
			logger.debug("===> KeyManager::init: invoked..., cluster count {}",lhsmConf.size() );

			int lhsmCnt = lhsmConf.size();
			int totalLogicalSlotCnt = 0;
			int totalPHsmCnt = 0;
			
			for (int i=0; i<lhsmCnt; i++) {
				LHSMModel logicalHsm = (LHSMModel) lhsmConf.get(i);

				logger.debug("KeyManager::init: Logical HSM [{}:{}] found", i, logicalHsm.getLhsmLabel());
				logger.debug("KeyManager::init: Logical token count [{}:{}]", i, logicalHsm.getLtokenCnt());
				
				List<PHSMModel> 	phmsConf = logicalHsm.getPhsmModel();
				List<LTokenModel> lTokenConf = logicalHsm.getLtokenModel();
				
				if(phmsConf != null) {

					int phsmCnt = phmsConf.size();
					int lTokenCnt = lTokenConf.size();

					logger.debug("KeyManager::init: Logical HSM [{}]'s hsm count - {}", i, phmsConf.size());
					
					if (phsmCnt == 0) continue;
					
					/****/
					//logicalHsm.init(totalLogicalSlotCnt);
					for (int j=0; j<phsmCnt; j++) {
						
						PHSMModel physicalHsm = (PHSMModel) phmsConf.get(j);
						
						for (int k=0; k<lTokenCnt;k++) {
							LTokenModel logicalToken = (LTokenModel) lTokenConf.get(k);
							logger.debug("KeyManager::init: test k:pin [{}:{}]", k, logicalToken.getTokenPin());
							(CryptoManager.getInstance()).setSlotPin(logicalToken.getTokenPin());
							logger.debug("KeyManager::init: test ----------");
						}
						
						(CryptoManager.getInstance()).setSlotPin(adminPin);
						/*
						TokenEntity tokenEntity = new TokenEntity();
						tokenEntity.init(physicalHsm.getHsmLabel(), physicalHsm.getIpAddress());
						physicalHsm.setPkcs11(tokenEntity.getPKCS11());
						*/
					}
					/****/
					totalPHsmCnt += phsmCnt;
					totalLogicalSlotCnt += logicalHsm.getLslotEndNo() - logicalHsm.getLtokenStartNo() + 1;
				}
				
				logger.debug("KeyManager::init: total Phisical HSM Count [{}], total token count [{}]", totalPHsmCnt, totalLogicalSlotCnt);
			} 
			
			if(totalPHsmCnt > 0) {
				TokenMap.getInstance().load();
				isInit = true;
			}
		} else {
			logger.debug("KeyManager::init: invoked..., TokenConfig List is null !!");
		}
	}
	
	
	
	public boolean isInit(){
		return this.isInit;
	}

	public long getTokenIndexforKeyLabel(String keyLabel) {

		KeyInfo keyInfo = KeyMap.getInstance().find(keyLabel);

		if(keyInfo != null) {
			String tokenLabel = keyInfo.getTokenLabel();
			return getTokenIndexforTokenLabel(tokenLabel);
		}

		return -1;
	}
	
	public long getTokenIndexforTokenLabel(String tokenLabel) {

		if(this.lhsmConf != null)
		{
			LHSMModel lhsmModel = this.lhsmConf.get(0);

			int ptokenCnt = lhsmModel.getPhsmCnt();
			
			//add 20150901
			ptokenCnt = CryptoManager.getInstance().getLivePhysicalHsmCnt();
			System.out.println("getTokenIndexforTokenLabel:: ptokenCnt: " + ptokenCnt);
			
			
			LTokenModel ltokenModel = TokenMap.getInstance().find(tokenLabel);
			if(ltokenModel != null) 
			{
//				int accessNo = ltokenModel.getAccessNo();
//				ltokenModel.setAccessNo((accessNo + 1) % ptokenCnt);
				
				List<PTokenModel> listPTokenModel = ltokenModel.getPtokenModel();
				
				if(listPTokenModel != null) 
				{
					for(int ptokenIdx = 0; ptokenIdx < listPTokenModel.size();ptokenIdx++) 
					{
						if(accessNo == ptokenIdx) 
						{
							
							//add 20150902
							accessNo = (accessNo + 1) % ptokenCnt;
							
							PTokenModel ptokenModel = listPTokenModel.get(ptokenIdx);
							return ptokenModel.getSlotNo();
						}
					}
				}
			}

			logger.debug("KeyManager::getTokenIndexforTokenLabel ==> ptokenCnt : [{}], ", ptokenCnt );

		}
		return -1;
	}
	
	public List<Long> findTokenIndexforKeyLabel(String keyLabel) {
		KeyInfo keyInfo = KeyMap.getInstance().find(keyLabel);

		if(keyInfo != null && keyInfo.getTokenLabel() != null) {
			String tokenLabel = keyInfo.getTokenLabel();
			return findTokenIndexforTokenLabel(tokenLabel);
		}
		return null;
	}
	
	public List<Long> findTokenIndexforTokenLabel(String tokenLabel) {
		LTokenModel ltokenModel = TokenMap.getInstance().find(tokenLabel);

		logger.debug("findTokenIndexforTokenLabel({})",tokenLabel);
		
		if (ltokenModel == null){
			logger.debug("ltokenModel is not found!");
			return null;
		}
		
		if(ltokenModel.getTokenLabel().equals(tokenLabel)) {
			ArrayList<Long> listPTokenLoc = new ArrayList<Long>();
			List<PTokenModel> listPTokenModel = ltokenModel.getPtokenModel();

			logger.debug("Step1");

			if(listPTokenModel != null) {
				logger.debug("Step2 : [{}]",listPTokenModel.size());
				
				for(int ptokenIdx = 0; ptokenIdx < listPTokenModel.size();ptokenIdx++) {
					PTokenModel ptokenModel = listPTokenModel.get(ptokenIdx);
					//listPTokenLoc.add(ptokenModel.getSlotIndex());
					logger.debug("Step3 : [{}]",ptokenModel.getSlotNo());
					listPTokenLoc.add(ptokenModel.getSlotNo());
				}
				return listPTokenLoc;
			}
		}
		return null;
	}

	public static synchronized int getAccessNo() {
		return accessNo;
	}

	public static synchronized void setAccessNo(int accessNo) {
		KeyManager.accessNo = accessNo;
	}

	
}

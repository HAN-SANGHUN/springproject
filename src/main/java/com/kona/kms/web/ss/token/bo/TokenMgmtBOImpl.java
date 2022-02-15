package com.kona.kms.web.ss.token.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.cao.SafenetUtil;
import com.kona.kms.crypto.CryptoManager;
import com.kona.kms.token.KeyManager;
import com.kona.kms.token.KeyMap;
import com.kona.kms.token.TokenMap;
import com.kona.kms.token.model.KeyInfo;
import com.kona.kms.token.model.LTokenModel;
import com.kona.kms.web.ss.token.dao.TokenMgmtDao;
import com.kona.kms.web.ss.token.model.SlotModel;
import com.kona.kms.web.ss.token.model.TokenModel;
import com.kona.kms.web.utils.GridPageData;

@Service
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class TokenMgmtBOImpl implements TokenMgmtBO {

	private static final Logger logger = LoggerFactory.getLogger(TokenMgmtBOImpl.class);
	
	@Autowired
	private TokenMgmtDao tokenMgmtDao;

	@Override
	public TokenModel getToken(long id) {
		return tokenMgmtDao.getToken(id);
	}
	
	@Override
	public GridPageData<TokenModel> getTokenList(TokenModel condition) {
		List<TokenModel> values = tokenMgmtDao.getTokenList(condition);
		
		int totalRecords = 0;
		if(!values.isEmpty()){
			totalRecords = tokenMgmtDao.getTotalRecord(condition);
		}
		
		return new GridPageData<TokenModel>(condition.getRowSize(), condition.getPage(), totalRecords, values);
	}

	@Override
	public List<TokenModel> getTokenListAll(TokenModel condition) {
		return tokenMgmtDao.getTokenListAll(condition);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void updateToken(TokenModel model) {
			
		tokenMgmtDao.updateToken(model);
		
		if(model.isTokenPinChanged()){
			List<Long> listPTokenLoc = KeyManager.getInstance().findTokenIndexforTokenLabel(model.getTokenLabel());
			if(listPTokenLoc != null) {
				for(int idx = 0; idx < listPTokenLoc.size(); idx++) {
					Long ptokenLoc = listPTokenLoc.get(idx);
					SafenetUtil.getInstance().setUserPin(ptokenLoc, model.getOldTokenPin(), model.getTokenPin());
					logger.debug("TokenMgmtBOImpl:updateToken: ptokenLoc [{}]", ptokenLoc);
				}
			}
		}		
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void initToken(TokenModel model) {
		updateMemTokenInfo(model);
		logger.debug("initToken: TokenNo: after updateMemTokenInfo");
		
		tokenMgmtDao.initToken(model);	
		logger.debug("initToken: TokenNo: after tokenMgmtDao.initToken");
		
		SlotModel slotModel = new SlotModel();
		slotModel.setTokenNo(model.getTokenNo());
		slotModel.setSlotStatusCode("Active");
		logger.debug("initToken: TokenNo:SlotStatusCode [{}:{}]", slotModel.getTokenNo(), slotModel.getSlotStatusCode());
		tokenMgmtDao.activateTokenStatus(slotModel);
		
		List<Long> listPTokenLoc = KeyManager.getInstance().findTokenIndexforTokenLabel(model.getTokenLabel());
		if(listPTokenLoc != null) {
			for(int idx = 0; idx < listPTokenLoc.size(); idx++) {
				Long ptokenLoc = listPTokenLoc.get(idx);
				SafenetUtil.getInstance().initToken(ptokenLoc, KeyManager.getSoPin(), model.getTokenPin(), model.getTokenLabel());
			}
		}
		
		//add by shifei 20140612
		KeyManager.getInstance().reinit();
//		KeyMap.getInstance().load();
		CryptoManager.getInstance().reinit();

	}	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public synchronized void insertToken(TokenModel model) throws KmsException {		
		
		List<SlotModel> slots = tokenMgmtDao.getEmptySlots();
		
		if(slots == null || slots.isEmpty()){
			throw new KmsException(KMSCode.TOKEN_IS_FULL);
		}
		
		tokenMgmtDao.insertToken(model);	
		
		for(SlotModel slot : slots){
			slot.setTokenNo(model.getTokenNo());
			slot.setSlotStatusCode("Active");
			
			
			tokenMgmtDao.activateSlot(slot);
		}		
		
		// TODO initialize physical hsm
	}
	
	@Override
	public GridPageData<SlotModel> getSlotList(SlotModel condition) {
		List<SlotModel> values = tokenMgmtDao.getSlotList(condition);
		
		int totalRecords = 0;
		if(!values.isEmpty()){
			totalRecords = tokenMgmtDao.getSlotTotalRecord(condition);
			
		}
		
		return new GridPageData<SlotModel>(condition.getRowSize(), condition.getPage(), totalRecords, values);
	}
	
	// memory상의 ltoken , hashmap 정보 추가
	public void updateMemTokenInfo(TokenModel model) {
		if(model != null) {
			List<LTokenModel> listLTokenModel = KeyManager.getInstance().lhsmConf.get(0).getLtokenModel();
			if(listLTokenModel != null) {
				for(int ltokenIdx=0; ltokenIdx < listLTokenModel.size(); ltokenIdx++) {
					LTokenModel ltokenModel = listLTokenModel.get(ltokenIdx);
					if(ltokenModel.getCompanyID() == null) {
						ltokenModel.setTokenLabel(model.getCompanyID());
						ltokenModel.setCompanyID(model.getCompanyID());

						//add by shifei 20140612
						ltokenModel.setTokenPin(model.getTokenPin());
						
						model.setTokenNo(ltokenModel.getTokenNo());
						
						TokenMap.getInstance().add(ltokenModel);
						
						break;
					}
				}
			}
		}			
	}
}

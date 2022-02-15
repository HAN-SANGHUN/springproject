package com.kona.kms.web.ss.token.dao;

import java.util.List;

import com.kona.kms.web.ss.token.model.SlotModel;
import com.kona.kms.web.ss.token.model.TokenModel;

public interface TokenMgmtDao {

	TokenModel getToken(long id);
	
	List<TokenModel> getTokenListAll(TokenModel condition);

	List<TokenModel> getTokenList(TokenModel condition);
	
	int getTotalRecord(TokenModel condition);
	
	int updateToken(TokenModel model);
	
	int initToken(TokenModel model);
	
	int activateTokenStatus(SlotModel model);
	
	int insertToken(TokenModel model);
	
	String getTokenNo(String companyID);
	
	void activateSlot(SlotModel model);
	
	List<SlotModel> getEmptySlots();

	List<SlotModel> getSlotList(SlotModel condition);

	int getSlotTotalRecord(SlotModel condition);
}

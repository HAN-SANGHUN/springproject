package com.kona.kms.web.ss.token.bo;

import java.util.List;

import com.kona.kms.KmsException;
import com.kona.kms.web.ss.token.model.SlotModel;
import com.kona.kms.web.ss.token.model.TokenModel;
import com.kona.kms.web.utils.GridPageData;

public interface TokenMgmtBO {
	
	TokenModel getToken(long tokenNo);

	GridPageData<TokenModel> getTokenList(TokenModel condition);
	
	List<TokenModel> getTokenListAll(TokenModel condition);
	
	void updateToken(TokenModel model);
	
	void initToken(TokenModel model);
	
	void insertToken(TokenModel model) throws KmsException;	

	GridPageData<SlotModel> getSlotList(SlotModel condition);

}

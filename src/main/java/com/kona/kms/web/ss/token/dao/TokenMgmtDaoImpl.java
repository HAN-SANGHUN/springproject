package com.kona.kms.web.ss.token.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kona.kms.web.ss.token.model.SlotModel;
import com.kona.kms.web.ss.token.model.TokenModel;

@Repository
public class TokenMgmtDaoImpl implements TokenMgmtDao{

	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public TokenModel getToken(long id) {
		TokenModel model = sqlSessionTemplate.selectOne("token.getToken", id);
		List<SlotModel> slots = sqlSessionTemplate.selectList("token.getSlotInfos", model.getTokenNo());
		
		model.setSlotInfos(slots);
		return model;
	}

	@Override
	public List<TokenModel> getTokenListAll(TokenModel condition) {
		return sqlSessionTemplate.selectList("token.getTokenListAll", condition);
	}

	@Override
	public List<TokenModel> getTokenList(TokenModel condition) {
		return sqlSessionTemplate.selectList("token.getTokenList", condition);
	}

	@Override
	public int getTotalRecord(TokenModel condition) {
		return sqlSessionTemplate.selectOne("token.getTotalRecord", condition);
	}

	@Override
	public int updateToken(TokenModel model) {
		return sqlSessionTemplate.update("token.updateToken", model);
	}
	
	@Override
	public int initToken(TokenModel model) {
		return sqlSessionTemplate.update("token.initToken", model);
	}

	@Override
	public int activateTokenStatus(SlotModel model) {
		return sqlSessionTemplate.update("token.activatePToken", model);
	}
	
	@Override
	public int insertToken(TokenModel model) {
		return sqlSessionTemplate.insert("token.insertToken", model);
	}
	
	@Override
	public String getTokenNo(String companyID) {
		return sqlSessionTemplate.selectOne("token.getTokenNo", companyID);
	}
	
	@Override
	public void activateSlot(SlotModel model) {
		sqlSessionTemplate.update("token.activateSlot", model);
	}
	
	@Override
	public List<SlotModel> getEmptySlots(){
		return sqlSessionTemplate.selectList("token.getEmptySlots");
	}

	@Override
	public List<SlotModel> getSlotList(SlotModel condition) {
		return sqlSessionTemplate.selectList("token.getSlotList", condition);
	}

	@Override
	public int getSlotTotalRecord(SlotModel condition) {
		return sqlSessionTemplate.selectOne("token.getSlotTotalRecord", condition);

	}
}

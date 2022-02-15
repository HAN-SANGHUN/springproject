package com.kona.kms.web.ss.hsm.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kona.kms.token.LHsm;
import com.kona.kms.token.model.LHSMModel;
import com.kona.kms.token.model.LTokenModel;
import com.kona.kms.token.model.PTokenModel;
import com.kona.kms.web.ss.hsm.model.HsmMetaModel;

@Repository
public class HsmMetaDaoImpl implements HsmMetaDao{

	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public HsmMetaModel getPhysicalHsm(int hsmNo) {
		return sqlSessionTemplate.selectOne("hsm.getPhysicalHsm", hsmNo);
	}
	
	@Override
	public HsmMetaModel getPhysicalHsmPrimary() {
		return sqlSessionTemplate.selectOne("hsm.getPhysicalHsmPrimary");
	}

	@Override
	public List<HsmMetaModel> getPhysicalHsmList(HsmMetaModel condition) {
		return sqlSessionTemplate.selectList("hsm.getPhysicalHsmList", condition);
	}

	@Override
	public int getTotalRecord(HsmMetaModel condition) {
		return sqlSessionTemplate.selectOne("hsm.getTotalRecord", condition);
	}


	@Override
	public List<HsmMetaModel> getPhysicalHsmListAll(HsmMetaModel condition) {
		return sqlSessionTemplate.selectList("hsm.getPhysicalHsmListAll", condition);
	}

	@Override
	public int updatePhysicalHsm(HsmMetaModel model) {
		return sqlSessionTemplate.update("hsm.updatePhysicalHsm", model);
	}

	@Override
	public int insertPhysicalHsm(HsmMetaModel model) {
		return sqlSessionTemplate.insert("hsm.insertPhysicalHsm", model);
	}
	
	@Override
	public int deletePhysicalHsm(HsmMetaModel model) {
		return sqlSessionTemplate.update("hsm.deletePhysicalHsm", model);
	}

	@Override
	public int deletePhysicalHsmPermanantly(int hsmNo) {
		return sqlSessionTemplate.update("hsm.deletePhysicalHsmPermanantly", hsmNo);
	}
	
	@Override	
	public int insertLogicalHsm(HsmMetaModel model) {
		return sqlSessionTemplate.insert("hsm.insertLogicalHsm", model);
	}
	
	@Override	
	public int updateLogicalHsm(HsmMetaModel model) {
		return sqlSessionTemplate.update("hsm.updateLogicalHsm", model);
	}
	
	@Override
	public int insertLogicalToken(LTokenModel model) {
		return sqlSessionTemplate.insert("token.insertLogicalToken", model);
	}
	
	@Override
	public int insertPhysicalToken(PTokenModel model) {
		return sqlSessionTemplate.insert("token.insertPhysicalToken", model);
	}
	
	@Override
	public List<HashMap> getTokenNos() {
		return sqlSessionTemplate.selectList("token.getTokenNos");
	}
}

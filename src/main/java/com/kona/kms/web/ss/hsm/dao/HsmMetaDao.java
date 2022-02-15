package com.kona.kms.web.ss.hsm.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.kona.kms.token.LHsm;
import com.kona.kms.token.model.LHSMModel;
import com.kona.kms.token.model.LTokenModel;
import com.kona.kms.token.model.PTokenModel;
import com.kona.kms.web.ss.hsm.model.HsmMetaModel;

public interface HsmMetaDao {
	
	//////////////////////////////////////////////////////////////////////
	// Physical HSM Info
	//////////////////////////////////////////////////////////////////////
	
	HsmMetaModel getPhysicalHsm(int hsmNo);	
	
	HsmMetaModel getPhysicalHsmPrimary();	

	List<HsmMetaModel> getPhysicalHsmList(HsmMetaModel condition);
	
	int getTotalRecord(HsmMetaModel condition);	

	List<HsmMetaModel> getPhysicalHsmListAll(HsmMetaModel condition);
	
	int updatePhysicalHsm(HsmMetaModel model);
	
	int insertPhysicalHsm(HsmMetaModel model);
	
	int deletePhysicalHsm(HsmMetaModel model);
	
	int deletePhysicalHsmPermanantly(int hsmNo);
	
	
	//////////////////////////////////////////////////////////////////////
	// Logical HSM Info
	//////////////////////////////////////////////////////////////////////
	
	int insertLogicalHsm(HsmMetaModel model);
	int updateLogicalHsm(HsmMetaModel model);
	
	int insertPhysicalToken(PTokenModel model);

	int insertLogicalToken(LTokenModel model);

	public List<HashMap> getTokenNos();
}

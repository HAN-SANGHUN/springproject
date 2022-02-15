package com.kona.kms.web.ss.hsm.bo;

import java.util.List;

import com.kona.kms.KmsException;
import com.kona.kms.token.model.LHSMModel;
import com.kona.kms.web.ss.hsm.model.HsmMetaModel;
import com.kona.kms.web.utils.GridPageData;

public interface HsmMetaBO {

	HsmMetaModel getPhysicalHsm(int hsmNo);
	
	HsmMetaModel getPhysicalHsmPrimary();
	
	GridPageData<HsmMetaModel> getPhysicalHsmList(HsmMetaModel condition);
	
	void insertPhysicalHsm(HsmMetaModel model) throws KmsException;
	List<HsmMetaModel> getPhysicalHsmListAll(HsmMetaModel condition);
	
	void deletePhysicalHsm(String[] hsmno, String updater);
	
	void updatePhysicalHsm(HsmMetaModel model);
	
	void insertLogicalHsm(HsmMetaModel model);

	void activateHsm() throws KmsException;
	
	void addHsm(HsmMetaModel model) throws KmsException;
}

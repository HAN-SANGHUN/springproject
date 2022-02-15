package com.kona.kms.web.ss.company.bo;

import java.util.List;

import com.kona.kms.KmsException;
import com.kona.kms.web.ss.company.model.CompanyModel;
import com.kona.kms.web.utils.GridPageData;

public interface CompanyBO {
	
	GridPageData<CompanyModel> getCompanyList(CompanyModel condition);
	
	void insertCompany(CompanyModel model) throws KmsException;
	
	void deleteCompany(String[] companyId, String updater);
	
	void updateCompany(CompanyModel model);
	
	CompanyModel selectCompany(String companyId);
	
	List<CompanyModel> getCompanyListAll(CompanyModel condition);
	
}

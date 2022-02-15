package com.kona.kms.web.ss.company.dao;

import java.util.List;

import com.kona.kms.web.ss.company.model.CompanyModel;

public interface CompanyDao {
	
	List<CompanyModel> getCompanyList(CompanyModel condition);
	int getTotalRecord(CompanyModel condition);	
	
	int insertCompany(CompanyModel model);
	int deleteCompany(CompanyModel model);
	int updateCompany(CompanyModel model);
	CompanyModel selectCompany(String companyID);
	List<CompanyModel> getCompanyListAll(CompanyModel condition);
}

package com.kona.kms.web.ss.company.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kona.kms.web.ss.company.model.CompanyModel;


@Repository
public class CompanyDaoImpl implements CompanyDao{

	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<CompanyModel> getCompanyList(CompanyModel condition) {
		return sqlSessionTemplate.selectList("company.getCompanyList", condition);
	}
	
	@Override
	public int getTotalRecord(CompanyModel condition) {
		return sqlSessionTemplate.selectOne("company.getTotalRecord", condition);
	}
	
	@Override
	public int insertCompany(CompanyModel model) {
		return sqlSessionTemplate.insert("company.insertCompany", model);
	}
	
	@Override
	public int deleteCompany(CompanyModel model) {
		return sqlSessionTemplate.insert("company.deleteCompany", model);
	}
	
	@Override
	public int updateCompany(CompanyModel model) {
		return sqlSessionTemplate.insert("company.updateCompany", model);
	}
	
	@Override
	public List<CompanyModel> getCompanyListAll(CompanyModel condition) {
		return sqlSessionTemplate.selectList("company.getCompanyListAll", condition);
	}
	
	@Override
	public CompanyModel selectCompany(String companyID) {
		return sqlSessionTemplate.selectOne("company.selectCompany", companyID);
	}
}

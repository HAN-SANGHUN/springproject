package com.kona.kms.web.ss.company.bo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.web.ss.company.dao.CompanyDao;
import com.kona.kms.web.ss.company.model.CompanyModel;
import com.kona.kms.web.ss.user.bo.KmsUserBO;
import com.kona.kms.web.utils.GridPageData;

@Service
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class CompanyBOImpl implements CompanyBO{

	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private KmsUserBO kmsUserBO;
	
	@Override
	public GridPageData<CompanyModel> getCompanyList(CompanyModel condition) {
		List<CompanyModel> values = companyDao.getCompanyList(condition);
		
		int totalRecords = 0;
		if(!values.isEmpty()){
			totalRecords = companyDao.getTotalRecord(condition);
		}
		
		return new GridPageData<CompanyModel>(condition.getRowSize(), condition.getPage(), totalRecords, values);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void insertCompany(CompanyModel model) throws KmsException {
		
		CompanyModel old = companyDao.selectCompany(model.getCompanyId());
		
		//shifei 20150511
		if(old != null){
//			throw new KmsException(KMSCode.COMPANY_ALREADY_EXISTS);
			companyDao.updateCompany(model);
		}else{
			companyDao.insertCompany(model);
		}
		
		
	}	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void deleteCompany(String[] companyIds, String updater) {
		
		Date now = new Date();
		
		for(String companyId : companyIds){
			CompanyModel company = companyDao.selectCompany(companyId);
			company.setCompanyStatusCode("Inactive");
			company.setUpdateDate(now);
			company.setUpdateUserId(updater);
			
			companyDao.updateCompany(company);
			
			kmsUserBO.inactiveUsers(company.getCompanyId(), updater, now);
			
		}		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void updateCompany(CompanyModel model) {
		companyDao.updateCompany(model);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public CompanyModel selectCompany(String companyID) {
		return companyDao.selectCompany(companyID);
	}
	
	@Override
	public List<CompanyModel> getCompanyListAll(CompanyModel condition) {
		return companyDao.getCompanyListAll(condition);
	}
		
}

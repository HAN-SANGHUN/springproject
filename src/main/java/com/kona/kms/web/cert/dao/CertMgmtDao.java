package com.kona.kms.web.cert.dao;

import java.util.List;

import com.kona.kms.web.cert.model.CertificateModel;


public interface CertMgmtDao {

	CertificateModel getCertificate(String id);		

	CertificateModel getOldVersionCertificate(String binNumber, int ipkIndex);

	List<CertificateModel> getCertificateListAll(CertificateModel condition);
	
	List<CertificateModel> getCertificateList(CertificateModel condition);
	
	int getTotalRecord(CertificateModel condition);	
	
	int approvalCertificate(CertificateModel model);
	
	int registerCertificate(CertificateModel model);
	
	int updateCertificateAsDisposal(CertificateModel model);

}

package com.kona.kms.web.cert.dao;

import java.util.Date;
import java.util.List;

import com.kona.kms.web.cert.model.CertJobModel;
import com.kona.kms.web.cert.model.CertificateModel;

public interface CertReqDao {

	CertificateModel getCertificate(String id);	

	List<CertificateModel> getCertificateListAll(CertificateModel condition);
	
	List<CertificateModel> getCertificateList(CertificateModel condition);
	
	int getTotalRecord(CertificateModel condition);	
	
	int updateCertificate(CertificateModel model);
	
	int insertCertificate(CertificateModel model);
	
	int deleteCertificate(String id);
	
	int setCertificateAsDelete(String id);

	int checkBINAndIndexUnique(String bin, int ipkIndex, Date expireDate);
	
	CertJobModel getCertificateToTransfer(String certificateUID);
}

package com.kona.kms.web.cert.dao;

import java.util.List;

import com.kona.kms.web.cert.model.CertJobModel;

public interface CertJobDao {

	List<CertJobModel> getCertificatesToTransfer();

	int deleteCertJob(CertJobModel job);

	void logTransferException(CertJobModel job);
	
	int insertCertJob(CertJobModel job);
	
	int updateReqTransferFlag(String certUID);
	
	int updateResTransferFlag(String certUID);

	CertJobModel getCertificateToTransfer(String certificateUID);
}

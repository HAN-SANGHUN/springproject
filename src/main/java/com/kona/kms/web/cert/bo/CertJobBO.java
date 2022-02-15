package com.kona.kms.web.cert.bo;

import java.util.List;

import com.kona.kms.KmsException;
import com.kona.kms.web.cert.model.CertJobModel;
import com.kona.kms.web.cert.model.CertificateModel;

public interface CertJobBO {

	List<CertJobModel> getCertificatesToTransfer();
	
	void deleteCertJob(CertJobModel job, CertificateModel model);
	
	void logTransferException(CertJobModel job);
	
	CertJobModel getCertificateToTransfer(String certificateUID);

	void transferCertificate(CertJobModel job) throws KmsException;
}

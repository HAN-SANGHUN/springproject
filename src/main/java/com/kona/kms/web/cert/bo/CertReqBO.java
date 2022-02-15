package com.kona.kms.web.cert.bo;

import java.util.Date;
import java.util.List;

import com.kona.kms.KmsException;
import com.kona.kms.web.cert.model.CertJobModel;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.utils.GridPageData;

public interface CertReqBO {
	
	CertificateModel getCertificate(String id);

	GridPageData<CertificateModel> getCertificateList(
			CertificateModel condition);
	
	List<CertificateModel> getCertificateListAll(CertificateModel condition);
	
	void editCertificate(CertificateModel model) throws KmsException, Exception;
	
	void updateCertificate(CertificateModel model) throws KmsException;
	
	void requestCertificate(CertificateModel model) throws KmsException, Exception;
	
	void insertCertificate(CertificateModel model) throws KmsException;
	
	void deleteCertificate(String[] ids);
	
	void processCertificateRequest(CertificateModel model) throws KmsException, Exception;
	
	void checkBinAndIndexUnique(String bin, int ipkIndex, Date expireDate) throws KmsException;
	
	void clearBinAndIndex(String bin, int ipkIndex, Date expireDate);
	
	void sendCert(String certificateUID, String updater) throws KmsException;

	void deleteCertJob(CertJobModel job, CertificateModel model);
	

}

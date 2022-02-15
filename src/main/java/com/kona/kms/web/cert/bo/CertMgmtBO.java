package com.kona.kms.web.cert.bo;

import java.util.List;

import com.kona.kms.KmsException;
import com.kona.kms.web.cert.model.CertiRegisterDTO;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.utils.GridPageData;

public interface CertMgmtBO {

	CertificateModel getCertificate(String id);

	GridPageData<CertificateModel> getCertificateList(
			CertificateModel condition);
	
	List<CertificateModel> getCertificateListAll(CertificateModel condition);
	
	void approvalCertificate(CertificateModel model);
	
	void registerCertificate(CertiRegisterDTO dto, String updater) throws KmsException;
}

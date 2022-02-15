package com.kona.kms.web.cert.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kona.kms.web.cert.model.CertJobModel;

@Repository
public class CertJobDaoImpl implements CertJobDao{

	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<CertJobModel> getCertificatesToTransfer() {
		return sqlSessionTemplate.selectList("certMgmt.getCertificatesToTransfer");
	}
	
	@Override
	public CertJobModel getCertificateToTransfer(String certificateUID) {
		return sqlSessionTemplate.selectOne("certMgmt.getCertificateToTransfer", certificateUID);
	}

	@Override
	public int deleteCertJob(CertJobModel job) {
		return sqlSessionTemplate.delete("certMgmt.deleteCertJob", job.getCertificateUID());	
	}

	@Override
	public void logTransferException(CertJobModel job) {
		sqlSessionTemplate.update("certMgmt.logTransferException", job);			
	}

	@Override
	public int insertCertJob(CertJobModel job) {
		return sqlSessionTemplate.insert("certMgmt.insertCertJob", job);
	}

	@Override
	public int updateReqTransferFlag(String certUID) {
		return sqlSessionTemplate.update("certMgmt.updateReqTransferFlag", certUID);
	}

	@Override
	public int updateResTransferFlag(String certUID) {
		return sqlSessionTemplate.update("certMgmt.updateResTransferFlag", certUID);
	}

}

package com.kona.kms.web.cert.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kona.kms.web.cert.model.CertJobModel;
import com.kona.kms.web.cert.model.CertificateModel;

@Repository
public class CertReqDaoImpl implements CertReqDao{

	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public CertificateModel getCertificate(String id) {		
		return  sqlSessionTemplate.selectOne("certReq.getCertificate", id);
	}

	@Override
	public List<CertificateModel> getCertificateListAll(
			CertificateModel condition) {
		return sqlSessionTemplate.selectList("certReq.getCertificateListAll", condition);
	}

	@Override
	public List<CertificateModel> getCertificateList(CertificateModel condition) {
		return sqlSessionTemplate.selectList("certReq.getCertificateList", condition);
	}

	@Override
	public int getTotalRecord(CertificateModel condition) {
		return sqlSessionTemplate.selectOne("certReq.getTotalRecord", condition);
	}

	@Override
	public int updateCertificate(CertificateModel model) {
		return sqlSessionTemplate.update("certReq.updateCertificate", model);
	}

	@Override
	public int insertCertificate(CertificateModel model) {
		return sqlSessionTemplate.update("certReq.insertCertificate", model);
	}

	@Override
	public int deleteCertificate(String id) {
		return sqlSessionTemplate.delete("certReq.deleteCertificate", id);
	}
	
	@Override
	public int setCertificateAsDelete(String id) {
		return sqlSessionTemplate.update("certReq.setCertificateAsDelete", id);
	}

	@Override
	public int checkBINAndIndexUnique(String bin, int ipkIndex, Date expireDate) {
		ConcurrentHashMap<String, Object> values = new ConcurrentHashMap<String, Object>();
		values.put("bin", bin);
		values.put("ipkIndex", ipkIndex);
		values.put("expireDate", expireDate);
		
		return sqlSessionTemplate.selectOne("certReq.checkBINAndIndexUnique", values);
	}
	
	@Override
	public CertJobModel getCertificateToTransfer(String certificateUID) {
		return sqlSessionTemplate.selectOne("certReq.getCertificateToTransfer", certificateUID);
	}
	
	
}

package com.kona.kms.web.cert.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kona.kms.web.cert.model.CertificateModel;

@Repository
public class CertMgmtDaoImpl implements CertMgmtDao{

	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public CertificateModel getCertificate(String id) {		
		return  sqlSessionTemplate.selectOne("certMgmt.getCertificate", id);
	}

	@Override
	public CertificateModel getOldVersionCertificate(String binNumber,
			int ipkIndex) {
		ConcurrentHashMap<Object, Object> values = new ConcurrentHashMap<Object, Object>();
		
		values.put("bin", binNumber);
		values.put("ipkIndex", ipkIndex);
		
		return  sqlSessionTemplate.selectOne("certMgmt.getOldVersionCertificate", values);
	}

	@Override
	public List<CertificateModel> getCertificateListAll(
			CertificateModel condition) {
		return sqlSessionTemplate.selectList("certMgmt.getCertificateListAll", condition);
	}

	@Override
	public List<CertificateModel> getCertificateList(CertificateModel condition) {
		return sqlSessionTemplate.selectList("certMgmt.getCertificateList", condition);
	}

	@Override
	public int getTotalRecord(CertificateModel condition) {
		return sqlSessionTemplate.selectOne("certMgmt.getTotalRecord", condition);
	}

	@Override
	public int approvalCertificate(CertificateModel model) {
		return sqlSessionTemplate.update("certMgmt.approvalCertificate", model);
	}

	@Override
	public int registerCertificate(CertificateModel model) {
		return sqlSessionTemplate.update("certMgmt.registerCertificate", model);
	}

	@Override
	public int updateCertificateAsDisposal(CertificateModel model) {
		return sqlSessionTemplate.update("certMgmt.updateCertificateAsDisposal", model);
	}
}

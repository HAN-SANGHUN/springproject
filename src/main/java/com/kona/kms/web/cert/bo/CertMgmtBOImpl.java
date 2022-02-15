package com.kona.kms.web.cert.bo;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.utils.Util;
import com.kona.kms.web.cert.dao.CertJobDao;
import com.kona.kms.web.cert.dao.CertMgmtDao;
import com.kona.kms.web.cert.model.CertiRegisterDTO;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.cert.utils.CertiHelperFactory;
import com.kona.kms.web.cert.utils.ICertiParser;
import com.kona.kms.web.profile.ConvertUtil;
import com.kona.kms.web.utils.GridPageData;

@Service
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class CertMgmtBOImpl implements CertMgmtBO{

	private static final Logger logger = LoggerFactory.getLogger(CertMgmtBOImpl.class);
	
	@Autowired
	private CertMgmtDao certMgmtDao;
	
	@Autowired
	private CertJobDao certJobDao;
	
	@Override
	public CertificateModel getCertificate(String id) {
		return certMgmtDao.getCertificate(id);
	}

	@Override
	public GridPageData<CertificateModel> getCertificateList(
			CertificateModel condition) {
		List<CertificateModel> values = certMgmtDao.getCertificateList(condition);
		
		int totalRecords = 0;
		if(!values.isEmpty()){
			totalRecords = certMgmtDao.getTotalRecord(condition);
		}
		
		return new GridPageData<CertificateModel>(condition.getRowSize(), condition.getPage(), totalRecords, values);
	}

	@Override
	public List<CertificateModel> getCertificateListAll(
			CertificateModel condition) {
		return certMgmtDao.getCertificateListAll(condition);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void approvalCertificate(CertificateModel model) {
		certMgmtDao.approvalCertificate(model);		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void registerCertificate(CertiRegisterDTO dto, String updater) throws KmsException {
				
		CertificateModel model = certMgmtDao.getCertificate(dto.getCertificateUID());	
		
		CertificateModel oldVersion = certMgmtDao.getOldVersionCertificate(model.getBinNumber(), model.getIpkIndex());
		
		model.setUpdateUserID(updater);
		model.setUpdateDate(new Date());
		model.setBinStatusCode("USE");		
		try {
			model.setCertiResBinaryFile(dto.getCertiFile().getBytes());
			model.setCertiResFileName(dto.getCertiFileName());
		} catch (IOException e) {
			logger.error("CertMgmtBOImpl: registerCertificate: set binary file: " +e.getMessage());
			
			throw new KmsException(KMSCode.CERTI_REGISTER_FAIL);
		}
		
		ICertiParser parser = CertiHelperFactory.getInstance().getCertiParser(model);
		
		try{
			parser.parse(Util.byteArray2hexString(model.getCertiResBinaryFile()), dto.getCertiFileName());
		}catch(KmsException e){
			logger.error("CertMgmtBOImpl: registerCertificate: parse file: KmsException: " +e.getMessage());
			
			throw new KmsException(e.getMsgcode(), e.getMessage());
		}
		catch(Exception e){
			logger.error("CertMgmtBOImpl: registerCertificate: parse file: Exception: " +e.getMessage());
			
			throw new KmsException(KMSCode.CERTI_REGISTER_FAIL);
		}
		
		logger.debug("CertMgmtBOImpl: registerCertificate: Parser Result: " + parser);
		
		if(oldVersion != null){
			oldVersion.setBinStatusCode("DISPOSAL");
			oldVersion.setUpdateUserID(model.getUpdateUserID());
			oldVersion.setUpdateDate(model.getUpdateDate());
			
			certMgmtDao.updateCertificateAsDisposal(model);
		}
		
		certMgmtDao.registerCertificate(model);
		
		certJobDao.insertCertJob(ConvertUtil.createCertJob(model, "UPDATE"));
		
	}
}

package com.kona.kms.web.cert.bo;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.web.cert.dao.CertJobDao;
import com.kona.kms.web.cert.dao.CertMgmtDao;
import com.kona.kms.web.cert.dao.CertReqDao;
import com.kona.kms.web.cert.model.CertJobModel;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.profile.TransferUtil;
import com.konai.keymanagement.KeyManagementPort;
import com.konai.kmsexchangemessage.RegisteredBINCertification;
import com.konai.kmsexchangemessage.RequestedBINCertification;
import com.konai.kmsexchangemessage.ResponseType;

@Service
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class CertJobBOImpl implements CertJobBO{

	private static final Logger logger = LoggerFactory.getLogger(CertJobBOImpl.class);
	
	@Autowired
	private CertJobDao certJobDao;
	
	@Autowired
	private CertMgmtDao certMgmtDao;
	
	@Autowired
	private CertReqDao certReqDao;
	
	@Autowired
	private KeyManagementPort kiClient;
	
	@Override
	public List<CertJobModel> getCertificatesToTransfer() {		
		return certJobDao.getCertificatesToTransfer();
	}
	
	@Override
	public CertJobModel getCertificateToTransfer(String certificateUID) {		
		return certJobDao.getCertificateToTransfer(certificateUID);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void deleteCertJob(CertJobModel job, CertificateModel model) {
		
		certJobDao.deleteCertJob(job);		
		
		if("USE".equals(model.getBinStatusCode())){
			certJobDao.updateResTransferFlag(job.getCertificateUID());
		}else if("DRAFT".equals(model.getBinStatusCode())){
			certJobDao.updateReqTransferFlag(job.getCertificateUID());
		}else if("DELETE".equals(model.getBinStatusCode())){
			certReqDao.deleteCertificate(model.getCertificateUID());
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void logTransferException(CertJobModel job) {
		certJobDao.logTransferException(job);		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void transferCertificate(CertJobModel job) throws KmsException {
		CertificateModel model = certReqDao.getCertificate(job.getCertificateUID());
		
		ResponseType response = null;		
		
		if(model.getBinStatusCode().equals("USE")){
			RegisteredBINCertification value = null;
			
			try {
				value = TransferUtil.createRegisteredCertInfo(model, job.getWorkcode());
			} catch (DatatypeConfigurationException e) {
				logger.error("CertJobBOImpl: transferCertificate: converting: " + e.getMessage());
				
				throw new KmsException(KMSCode.TSM_TRANSFER_FAIL);
			}catch(Exception e){
				logger.error("CertJobBOImpl: transferCertificate: converting: " + e.getMessage());
				
				throw new KmsException(KMSCode.TSM_TRANSFER_FAIL);
			}
			
			response = kiClient.sendRegisteredBINCert(value);
			
		}else{
			
			RequestedBINCertification value = null;
			try {
				value = TransferUtil.createRequestedCertInfo(model, job.getWorkcode());
			} catch (DatatypeConfigurationException e) {
				logger.error("CertJobBOImpl: transferCertificate: converting: " + e.getMessage());
				
				throw new KmsException(KMSCode.TSM_TRANSFER_FAIL);
			}catch(Exception e){
				logger.error("CertJobBOImpl: transferCertificate: converting: " + e.getMessage());
				
				throw new KmsException(KMSCode.TSM_TRANSFER_FAIL);
			}
			
			response = kiClient.sendRequestedBINCert(value);
		}
				
		if(response == ResponseType.FAIL){			
			throw new KmsException(KMSCode.TSM_TRANSFER_FAIL);
		}		
		
		this.deleteCertJob(job, model);	
	}

}

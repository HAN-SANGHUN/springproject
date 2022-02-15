package com.kona.kms.web.cert.quartz;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kona.kms.web.cert.bo.CertJobBO;
import com.kona.kms.web.cert.model.CertJobModel;

public class CertJob {

	private static final Logger logger = LoggerFactory.getLogger(CertJob.class);
	
	@Autowired
	private CertJobBO certJobBO;
	
	public CertJob(){
		
	}
	
	public void sendCertificateToTsm(){
		
		List<CertJobModel> jobs = certJobBO.getCertificatesToTransfer();
		
		if(jobs == null || jobs.isEmpty()) return;
		
		logger.info("CertJob: Jobs Found:[Count]: " + jobs.size());
		
		for(CertJobModel job : jobs){
			
			if(job.getTryCount() >= 3){
				logger.info("CertJob: Try count exeed 3:[Job]: " + job);
				continue;
			}
			try{
				logger.debug("CertJob: starting job: " + job);
				
				certJobBO.transferCertificate(job);
				
				logger.debug("CertJob: finished job: " + job);
			}catch(Exception e){
				logger.error("CertJob: " + e.getMessage());
				
				job.setTryCount(job.getTryCount()+1);
				job.setDescription(e.getMessage());
				job.setUpdateUserID("KMS_SYS");
				job.setUpdateDate(new Date());
				
				try{
					certJobBO.logTransferException(job);
				}catch(Exception e0){
					// ignore
				}
			}
		}
	}
}

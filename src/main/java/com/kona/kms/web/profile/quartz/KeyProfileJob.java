package com.kona.kms.web.profile.quartz;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kona.kms.web.profile.bo.KeyProfileBO;
import com.kona.kms.web.profile.model.KeyProfileJobModel;

public class KeyProfileJob{

	private static final Logger logger = LoggerFactory.getLogger(KeyProfileJob.class);
	
	@Autowired
	private KeyProfileBO keyProfileBO;
	
	public KeyProfileJob(){
		
	}
	
	public void sendKeyProfileToTsm(){	
		
		List<KeyProfileJobModel> jobs = keyProfileBO.getKeyProfilesToTransfer();
		
		if(jobs == null || jobs.isEmpty()) return;
		
		logger.info("KeyProfileJob: Jobs Found:[Count]: " + jobs.size());
		
		for(KeyProfileJobModel job : jobs){
			
			if(job.getTryCount() >= 3){
				
				logger.info("KeyProfileJob: Job try count exceed 3: [Job]:  " + job);
				
				continue;
			}
			
			try{
				if(job.getKeyTypeCode().equals("SECRET")){
					keyProfileBO.transferSecretKeyProfile(job);
				}else{
					keyProfileBO.transferPairKeyProfile(job);
				}				
				
				logger.info("KeyProfileJob finished:" + job.getKeyProfileID() + ":" + job.getKeyProfileVersion());
			}catch(Exception e){
				
				logger.error("KeyProfileJob: " + e.getMessage());
								
				job.setTryCount(job.getTryCount()+1);
				job.setDescription(e.getMessage());
				job.setUpdateUserID("KMS_SYS");
				job.setUpdateDate(new Date());
				
				try{
					keyProfileBO.logTransferException(job);
				}catch(Exception e0){
					//ignored
				}				
			}
		}
	}
}

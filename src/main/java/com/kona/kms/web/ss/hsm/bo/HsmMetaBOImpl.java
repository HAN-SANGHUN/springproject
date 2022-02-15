package com.kona.kms.web.ss.hsm.bo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.CryptoManager;
import com.kona.kms.token.KeyManager;
import com.kona.kms.token.KeyMap;
import com.kona.kms.token.LHsm;
import com.kona.kms.token.TokenMap;
import com.kona.kms.token.model.LHSMModel;
import com.kona.kms.token.model.LTokenModel;
import com.kona.kms.token.model.PTokenModel;
import com.kona.kms.web.ss.hsm.dao.HsmMetaDao;
import com.kona.kms.web.ss.hsm.model.HsmMetaModel;
import com.kona.kms.web.utils.GridPageData;

@Service
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class HsmMetaBOImpl implements HsmMetaBO{

	private static final Logger logger = LoggerFactory.getLogger(HsmMetaBOImpl.class);
	
	@Autowired
	private HsmMetaDao hsmMetaDao;

	@Override
	public HsmMetaModel getPhysicalHsm(int hsmNo) {
		return hsmMetaDao.getPhysicalHsm(hsmNo);
	}
	
	@Override
	public HsmMetaModel getPhysicalHsmPrimary() {
		return hsmMetaDao.getPhysicalHsmPrimary();
	}

	@Override
	public GridPageData<HsmMetaModel> getPhysicalHsmList(HsmMetaModel condition) {
		List<HsmMetaModel> values = hsmMetaDao.getPhysicalHsmList(condition);
		
		int totalRecords = 0;
		if(!values.isEmpty()){
			totalRecords = hsmMetaDao.getTotalRecord(condition);
		}
		
		return new GridPageData<HsmMetaModel>(condition.getRowSize(), condition.getPage(), totalRecords, values);
	}
		
	@Override
	public List<HsmMetaModel> getPhysicalHsmListAll(HsmMetaModel condition) {
		return hsmMetaDao.getPhysicalHsmListAll(condition);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void updatePhysicalHsm(HsmMetaModel model) {		
		hsmMetaDao.updatePhysicalHsm(model);		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void insertPhysicalHsm(HsmMetaModel model) throws KmsException {	
		
		HsmMetaModel master = hsmMetaDao.getPhysicalHsmPrimary();
		
		if(master == null){
			model.setPrimaryCode("Primary");
		}else{
			model.setPrimaryCode("Secondary");
		}
		
		hsmMetaDao.insertPhysicalHsm(model);
		if(KeyManager.getInstance().isInit()) {
			addHsm(model);
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void deletePhysicalHsm(String[] hsmNos, String updater) {

		Date now = new Date();
		
		for(String hsmNo : hsmNos){
			
			HsmMetaModel hsm = hsmMetaDao.getPhysicalHsm(Integer.valueOf(hsmNo));
			
			if(hsm.getStatusCode().equals("Active")){
				hsm.setStatusCode("Inactive");
				hsm.setUpdateDate(now);
				hsm.setUpdateUserId(updater);
				
				hsmMetaDao.deletePhysicalHsm(hsm);
			}else{
				hsmMetaDao.deletePhysicalHsmPermanantly(hsm.getHsmNo());
			}				
		}
	}

	@Override
	public void insertLogicalHsm(HsmMetaModel model) {
		hsmMetaDao.insertLogicalHsm(model);		
	}

	@Override
	public void activateHsm() throws KmsException {
		logger.debug("HsmMetaBOImpl::activateHsm: invoked....");
		Calendar cal = Calendar.getInstance();
		
		HsmMetaModel condition = new HsmMetaModel();
		condition.setSortname("hsmNo");
		condition.setSortorder("asc");
		
		List<HsmMetaModel> pmodels = hsmMetaDao.getPhysicalHsmListAll(condition);
				if(pmodels == null || pmodels.isEmpty()){
			throw new KmsException(KMSCode.PHSYICAL_HSM_EMPTY);
		}
		
		// insert LHSM
		// get lhsm info from phsm primary
		HsmMetaModel lmodel = new HsmMetaModel();
		lmodel.setLHSMInitValue(pmodels.get(0));
		lmodel.setPHsmCount(pmodels.size());
		lmodel.setRegistrationUserId("system");
		lmodel.setRegistrationDate(cal.getTime());
		lmodel.setUpdateUserId("system");
		lmodel.setUpdateDate(cal.getTime());
		hsmMetaDao.insertLogicalHsm(lmodel);
		logger.debug("HsmMetaBOImpl::activateHsm: inserted LogicalHSM lhsm_no [{}]", lmodel.getLHsmNo());
		

		int i = 0;
		int j = 0;
		long[] lTokenNo = new long[(int)lmodel.getSlotCount()];
		long pSlotNo = 0;
		// insert to ltoken - lhsm.ltoken_cnt 만큼
		LTokenModel ltoken = new LTokenModel();
		
		ltoken.setRegistrationUserID("system");
		ltoken.setRegistrationDate(cal.getTime());
		ltoken.setUpdateUserID("system");
		ltoken.setUpdateDate(cal.getTime());
		
		logger.debug("HsmMetaBOImpl::activateHsm: LHSM.LSlotCount [{}]", lmodel.getSlotCount());
				
		for(i=0; i <= lmodel.getSlotEndNum(); i++){
			// set ltoken info
			ltoken.setLhsmNo(lmodel.getLHsmNo());
			// TODO: get init token-pin from config?
			ltoken.setTokenPin("----");
			
			// insert to ltoken
			hsmMetaDao.insertLogicalToken(ltoken);
			lTokenNo[i] = ltoken.getTokenNo();
		}
		logger.debug("HsmMetaBOImpl::activateHsm: ltoken [{}] rows inserted", i);
		
		//insert to ptoken - phsm*ltoken_cnt 만큼
		PTokenModel ptoken = new PTokenModel();
		ptoken.setRegistrationUserID("system");
		ptoken.setRegistrationDate(cal.getTime());
		ptoken.setUpdateUserID("system");
		ptoken.setUpdateDate(cal.getTime());
		
		for(i=0; i < lmodel.getPHsmCount(); i++){
			logger.debug("HsmMetaBOImpl::activateHsm: [{}] HSM ------------------------", i);
			ptoken.setHsmNo(pmodels.get(i).getHsmNo());
			pSlotNo = pmodels.get(i).getSlotStartNum();
			
			pmodels.get(i).setLHsmNo(lmodel.getLHsmNo());
			
			hsmMetaDao.updatePhysicalHsm(pmodels.get(i));
				
			for(j=0; j < (int)lmodel.getSlotCount(); j++){
				ptoken.setSlotNo(pSlotNo++);
				ptoken.setTokenNo(lTokenNo[j]);
				ptoken.setSlotStatusCode("Inactive");	// Uninitialised
				
				// insert to ptoken
				hsmMetaDao.insertPhysicalToken(ptoken);
				logger.debug("HsmMetaBOImpl::activateHsm: PToken.SlotIndex [{}] inserted", ptoken.getSlotIndex());
				logger.debug("HsmMetaBOImpl::activateHsm: SlotNo/TokenNo [{}/{}]", ptoken.getSlotNo(), ptoken.getTokenNo());
			}
		}
		logger.debug("HsmMetaBOImpl::activateHsm: ptoken inserted successfully");
		
		for(i=0; i < lmodel.getPHsmCount(); i++){
			pmodels.get(i).setStatusCode("Active");
			hsmMetaDao.updatePhysicalHsm(pmodels.get(i));
		}
		logger.debug("HsmMetaBOImpl::activateHsm: end.....");
		
		//add by shifei 20140612
		KeyManager.getInstance().reinit();
//		KeyMap.getInstance().load();
//		CryptoManager.getInstance().init();
//		TokenMap.getInstance().load();

	}
	
	// written by kcy(2013.10.21)
	// phsm 추가인 경우 (update lhsm ,add ptoken) 
	@Override
	public void addHsm(HsmMetaModel model) throws KmsException {
		logger.debug("HsmMetaBOImpl::addHsm: invoked....");
		Calendar cal = Calendar.getInstance();
		
		// update LHSM
		// get lhsm info from phsm primary
		HsmMetaModel lmodel = new HsmMetaModel();
		lmodel.setLHsmNo(model.getLHsmNo());
		lmodel.setSlotCount(model.getSlotCount());
		lmodel.setUpdateUserId("system");
		lmodel.setUpdateDate(cal.getTime());
		hsmMetaDao.updateLogicalHsm(lmodel);
		logger.debug("HsmMetaBOImpl::addHsm: updated LogicalHSM lhsm_no [{}]", lmodel.getLHsmNo());

		int j = 0;
		long[] lTokenNo = new long[(int)lmodel.getSlotCount()];
		long pSlotNo = 0;
		
		//insert to ptoken - ltoken_cnt 만큼
		PTokenModel ptoken = new PTokenModel();
		ptoken.setRegistrationUserID("system");
		ptoken.setRegistrationDate(cal.getTime());
		ptoken.setUpdateUserID("system");
		ptoken.setUpdateDate(cal.getTime());
		
		pSlotNo = model.getSlotStartNum();
		for(j=0; j < (int)model.getSlotCount(); j++){
			ptoken.setSlotNo(pSlotNo++);
//			ptoken.setTokenNo(lTokenNo[j]);
			HashMap<String, BigDecimal> hash = hsmMetaDao.getTokenNos().get(j);
			
			if(hash.get("TOKENNO")!=null){
				lTokenNo[j] = hash.get("TOKENNO").longValue();
			}else{
				lTokenNo[j] = hash.get("TokenNo").longValue();
			}
			
//			lTokenNo[j] = hash.get("TokenNo").longValue();
			ptoken.setTokenNo(lTokenNo[j]);
			ptoken.setSlotStatusCode("Active");	// Initialised
				
			// insert to ptoken
			hsmMetaDao.insertPhysicalToken(ptoken);
			logger.debug("HsmMetaBOImpl::addHsm: PToken.SlotIndex [{}] inserted", ptoken.getSlotIndex());
			logger.debug("HsmMetaBOImpl::addHsm: SlotNo/TokenNo [{}/{}]", ptoken.getSlotNo(), ptoken.getTokenNo());
		}
		logger.debug("HsmMetaBOImpl::addHsm: ptoken inserted successfully");
		
		model.setStatusCode("Active");
		hsmMetaDao.updatePhysicalHsm(model);

		logger.debug("HsmMetaBOImpl::addHsm: end.....");
	}
}

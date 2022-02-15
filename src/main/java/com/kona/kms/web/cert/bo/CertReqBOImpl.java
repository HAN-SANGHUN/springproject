package com.kona.kms.web.cert.bo;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;

import org.globalplatform.namespaces.systems_profiles._1_1.KeyProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.cao.KMSCrypto;
import com.kona.kms.utils.Util;
import com.kona.kms.web.cert.BrandType;
import com.kona.kms.web.cert.dao.CertJobDao;
import com.kona.kms.web.cert.dao.CertMgmtDao;
import com.kona.kms.web.cert.dao.CertReqDao;
import com.kona.kms.web.cert.model.CertJobModel;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.cert.utils.CertiHelperFactory;
import com.kona.kms.web.cert.utils.ICertiReqFormatter;
import com.kona.kms.web.profile.ConvertUtil;
import com.kona.kms.web.profile.TransferUtil;
import com.kona.kms.web.profile.bo.KeyProfileBO;
import com.kona.kms.web.profile.model.KeyProfileModel;
import com.kona.kms.web.utils.FileUtil;
import com.kona.kms.web.utils.GridPageData;
import com.konai.keymanagement.KeyManagementPort;
import com.konai.kmsexchangemessage.KeyInfoRequestType;
import com.konai.kmsexchangemessage.RegisteredBINCertification;
import com.konai.kmsexchangemessage.RequestedBINCertification;
import com.konai.kmsexchangemessage.ResponseType;

@Service
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class CertReqBOImpl implements CertReqBO {

	private static final Logger logger = LoggerFactory.getLogger(CertReqBOImpl.class);
	
	@Autowired
	private CertReqDao certReqDao;
	
	@Autowired
	private CertJobDao certJobDao;
	
	@Autowired
	private KeyProfileBO keyProfileBO;
	
	@Autowired
	private KeyManagementPort kiClient;
	
	@Autowired
	private CertMgmtDao certMgmtDao;
	
	@Autowired
	private CertJobBO certJobBO;
	
	private Set<BinAndIndex> bins = new HashSet<BinAndIndex>();
	
	private String savePath;
	
	@Value("${certi_req_info_save_path}")
	public void setCertiReqInfoSavePath(String savePath){
		this.savePath = StringUtils.trimWhitespace(savePath);
	}

	@Override
	public CertificateModel getCertificate(String id) {
		return certReqDao.getCertificate(id);
	}

	@Override
	public GridPageData<CertificateModel> getCertificateList(
			CertificateModel condition) {
		List<CertificateModel> values = certReqDao.getCertificateList(condition);
		
		int totalRecords = 0;
		if(!values.isEmpty()){
			totalRecords = certReqDao.getTotalRecord(condition);
		}
		
		return new GridPageData<CertificateModel>(condition.getRowSize(), condition.getPage(), totalRecords, values);
	}

	@Override
	public List<CertificateModel> getCertificateListAll(
			CertificateModel condition) {
		return certReqDao.getCertificateListAll(condition);
	}

	@Override	
	//add by shifei 2014-04-17
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void editCertificate(CertificateModel model) throws KmsException, Exception {
		
		CertificateModel old = certReqDao.getCertificate(model.getCertificateUID());
		
		if(!old.getBinNumber().equals(model.getBinNumber()) || old.getIpkIndex() != model.getIpkIndex()){
			this.checkBinAndIndexUnique(model.getBinNumber(), model.getIpkIndex(), model.getExpireDate());
		}
		
		this.processCertificateRequest(model);
		
		this.updateCertificate(model);	
		
		certJobDao.insertCertJob(ConvertUtil.createCertJob(model, "UPDATE"));
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void updateCertificate(CertificateModel model) throws KmsException {
				
		certReqDao.updateCertificate(model);		
	}

	@Override
	public void requestCertificate(CertificateModel model) throws KmsException, Exception {
		
		model.setCertificateUID(UUID.randomUUID().toString());
		
		this.checkBinAndIndexUnique(model.getBinNumber(), model.getIpkIndex(), model.getExpireDate());
		
		this.processCertificateRequest(model);
		
		certReqDao.insertCertificate(model);
		
		certJobDao.insertCertJob(ConvertUtil.createCertJob(model, "INSERT"));
		
		this.clearBinAndIndex(model.getBinNumber(), model.getIpkIndex(), model.getExpireDate());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void insertCertificate(CertificateModel model) throws KmsException {
		
		certReqDao.insertCertificate(model);	
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void deleteCertificate(String[] ids) {
		for(String id : ids){
			CertificateModel model = certReqDao.getCertificate(id);
			
			certReqDao.setCertificateAsDelete(id);				
			
			certJobDao.insertCertJob(ConvertUtil.createCertJob(model, "DELETE"));
		}		
	}

	@Override
	public void processCertificateRequest(CertificateModel model)
			throws KmsException, Exception {
		
		ICertiReqFormatter formatter = CertiHelperFactory.getInstance().getCertiFormatter(model);
		
		if(formatter == null){
			throw new KmsException(KMSCode.CERTI_BRAND_TYPE_UNKNOWN);
		}
		
		/*
		 * 1. Get Public / Private Key Profile.
		 */		
		KeyProfileModel pubProfile = null;
		KeyProfileModel priProfile = null;
		
		List<KeyProfileModel> profiles = keyProfileBO.getPairKeyProfiles(model.getKeySubject());
		
		for(KeyProfileModel profile : profiles){
			if(profile.getKeyTypeCode().equals("PUBLIC")){
				pubProfile = profile;
			}else{
				priProfile = profile;
			}
		}
		
		KMSCrypto crytoService = KMSCrypto.getInstance();
		
		/*
		 * 2. Get Modulus / Exponent Value Of Public Key.
		 */
		String[] attrs = crytoService.getAttributeCertValue(pubProfile.getTokenLabel(), pubProfile.getKeyLabel());
		
		String modulus = null;
		String exponent = null;
		String ipkSize = null;
		String ipkExpSize = null;
		String leftmost = null;
		
		for(String attr : attrs){
			StringUtils.trimWhitespace(attr);
			String[] values = attr.split("=");
			
			if("modulus".toUpperCase().contains(values[0].trim())){
				modulus = values[1].trim();			
			}else if("public_exponent".toUpperCase().equals(values[0].trim())){
				exponent = values[1].trim();
			}if("modulus_len".toUpperCase().contains(values[0].trim())){
				ipkSize = values[1].trim();			
			}else if("exponent_len".toUpperCase().equals(values[0].trim())){
				ipkExpSize = values[1].trim();
			}else if("leftmost".toUpperCase().equals(values[0].trim())){
				leftmost = values[1].trim();
			}else{
				// ignore
			}
		}
		
		/*
		 * 3. Set Extention / Self Sign Data
		 */
		formatter.format(modulus, exponent, ipkSize, ipkExpSize, leftmost);
		
		/*
		 * 4. Digest with Self Sign Data
		 */
		String hexDigestStr = crytoService.digest(pubProfile.getTokenLabel(), "SHA_1", null, formatter.getSelfSignData());
		
		logger.debug("Hex Digest Str: Crypto Data: " + hexDigestStr + ":" + formatter.getCrytoData(hexDigestStr));
		
		logger.debug("Formatter: " + formatter);
		
		/*
		 * 5. Encrypt with Crypto Data
		 */
		String hexCryptoResult = crytoService.encrypt("RSA_X_509", null, priProfile.getKeyLabel(), formatter.getCrytoData(hexDigestStr));
		
		/*
		 * 6. Save as File.
		 */
		String certiReqFileName = null;
		String hashValueFileName = null;
		try{
			// 6.1. Save Certi Info
			StringBuffer sb = new StringBuffer();
			sb.append(this.savePath).append("/").append(model.getCompanyID()).append("/").append(model.getBrandTypeInt()).append("/").append(formatter.getCertiInfoFileName());
			certiReqFileName = sb.toString();
			
			FileUtil.saveAsFile(certiReqFileName, Util.hexString2byteArray(formatter.getCertiInfo(hexCryptoResult)));
			
			// 6.2. Save Certi Hash Value
			sb = new StringBuffer();
			sb.append(this.savePath).append("/").append(model.getCompanyID()).append("/").append("/").append(model.getBrandTypeInt()).append("/").append(formatter.getCertiHashValueFileName());
			hashValueFileName = sb.toString();
			
			if("BYTE_ARRAY".equals(formatter.getHashValueFileType())){
				FileUtil.saveAsFile(hashValueFileName, Util.hexString2byteArray(formatter.getCertiHashValue()));			
			}else{
				FileUtil.saveAsFile(hashValueFileName, formatter.getCertiHashValue());			
			}
			
		}catch(Exception e){
			logger.error("CertReqBOImpl: processCertificateRequest: saveFile: " + e.getMessage()); // ignore
		}
				
		/*
		 * 7. Populate Certificate Model
		 */
		model.setBinStatusCode("DRAFT");
		model.setBrandTypeCode(BrandType.codeFor(model.getBrandTypeInt()).getName());
		model.setKeyProfileName(pubProfile.getKeyProfileName());
		model.setCertiReqFileName(formatter.getCertiInfoFileName());
		model.setCertiReqBinaryFile(Util.hexString2byteArray(formatter.getCertiInfo(hexCryptoResult)));
		model.setExponentValue(exponent);
		model.setHashValueFileName(formatter.getCertiHashValueFileName());
		model.setHashValue(formatter.getCertiHashValue());
		model.setIpkSize(Integer.valueOf(ipkSize)/2); // modulus length
	}

	@Override
	public void checkBinAndIndexUnique(String bin, int ipkIndex, Date expireDate)
			throws KmsException {

		BinAndIndex bin0 = new BinAndIndex(bin, ipkIndex, expireDate);
		
		synchronized(bins){
			if(bins.contains(bin0)){
				throw new KmsException(KMSCode.CERT_BIN_NOT_UNIQUE);
			}
			
			int count = certReqDao.checkBINAndIndexUnique(bin, ipkIndex, expireDate);
			
			if(count > 0){
				throw new KmsException(KMSCode.CERT_BIN_NOT_UNIQUE);
			}
			
			bins.add(bin0);
		}		
	}

	@Override
	public void clearBinAndIndex(String bin, int ipkIndex, Date expireDate) {
		BinAndIndex bin0 = new BinAndIndex(bin, ipkIndex, expireDate);
		
		synchronized(bins){
			this.bins.remove(bin0);
		}		
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void sendCert(String certificateUID, String updater) throws KmsException{
////		CertJobModel job = certJobBO.getCertificateToTransfer(certificateUID);
//		CertJobModel job = certReqDao.getCertificateToTransfer(certificateUID);
//				
//		if(job == null) return;
//		try{
//			logger.debug("CertJob: starting job: " + job);
////			certJobBO.transferCertificate(job);
//			
//			CertificateModel model = certReqDao.getCertificate(job.getCertificateUID());
//			
//			ResponseType response = null;		
//			
//			if(model.getBinStatusCode().equals("USE")){
//				RegisteredBINCertification value = null;
//				
//				try {
//					value = TransferUtil.createRegisteredCertInfo(model, job.getWorkcode());
//				} catch (DatatypeConfigurationException e) {
//					logger.error("CertJobBOImpl: transferCertificate: converting: " + e.getMessage());
//					
//					throw new KmsException(KMSCode.TSM_TRANSFER_FAIL);
//				}catch(Exception e){
//					logger.error("CertJobBOImpl: transferCertificate: converting: " + e.getMessage());
//					
//					throw new KmsException(KMSCode.TSM_TRANSFER_FAIL);
//				}
//				
//				response = kiClient.sendRegisteredBINCert(value);
//				
//			}else{
//				
//				RequestedBINCertification value = null;
//				try {
//					value = TransferUtil.createRequestedCertInfo(model, job.getWorkcode());
//				} catch (DatatypeConfigurationException e) {
//					logger.error("CertJobBOImpl: transferCertificate: converting: " + e.getMessage());
//					
//					throw new KmsException(KMSCode.TSM_TRANSFER_FAIL);
//				}catch(Exception e){
//					logger.error("CertJobBOImpl: transferCertificate: converting: " + e.getMessage());
//					
//					throw new KmsException(KMSCode.TSM_TRANSFER_FAIL);
//				}
//				
//				response = kiClient.sendRequestedBINCert(value);
//			}
//					
//			if(response == ResponseType.FAIL){			
//				throw new KmsException(KMSCode.TSM_TRANSFER_FAIL);
//			}		
//			
//			this.deleteCertJob(job, model);	
//			
//			logger.debug("CertJob: finished job: " + job);
//		}catch(Exception e){
//			logger.error("CertJob: " + e.getMessage());
//		}
		
		CertJobModel job = certJobBO.getCertificateToTransfer(certificateUID);
		
		if(job == null) return;
		
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
	
	
}

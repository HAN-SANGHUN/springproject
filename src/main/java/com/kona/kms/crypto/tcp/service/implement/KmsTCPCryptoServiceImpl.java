package com.kona.kms.crypto.tcp.service.implement;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;

//import javax.jws.WebService;

import org.globalplatform.namespaces.systems_profiles._1_1.KeyProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import com.kona.kms.token.KeyMap;
import com.kona.kms.token.LogicalToken;
import com.kona.kms.token.model.KeyInfo;
import com.kona.kms.utils.Util;
import com.kona.kms.web.ss.company.bo.CompanyBO;
import com.kona.kms.crypto.bo.CryptoBO;
import com.kona.kms.crypto.bo.CryptoBOImpl;
import com.kona.kms.crypto.bo.CryptoRequest;
import com.kona.kms.crypto.bo.CryptoResponse;
import com.kona.kms.crypto.CryptoManager;
import com.kona.kms.crypto.Key;
import com.kona.kms.crypto.KeyAttrMgmt;
import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.PToken;
import com.kona.kms.framework.GpMsgBinder;
import com.kona.kms.framework.UUIDGenerator;
import com.kona.kms.KmsException;


public class KmsTCPCryptoServiceImpl implements KmsTCPCryptoService {
	
	private static final Logger logger = LoggerFactory.getLogger(KmsTCPCryptoServiceImpl.class);

//	@Autowired
	CryptoBO crtBo;
	
	@Autowired
	CompanyBO companyBO;
	
	public KmsTCPCryptoServiceImpl(){
		crtBo = new CryptoBOImpl();
	}

	//add 20150902
	private void ReloadMap()
	{
		// Clear and Reload KeyMap
		logger.debug("KmsTCPCryptoServiceImpl:: ReloadMap: ...........");
		
		KeyMap.getInstance().clear();
		KeyMap.getInstance().load();
		
	}
	
	public String C_GenerateKey(String tokenLabel, String mechanism, String[] keyAttrs) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_GenerateKey: ...........");
		
		CryptoRequest request = new CryptoRequest();
		request.tokenLabel = tokenLabel;
		request.keyAttr = KeyAttrMgmt.getInstance().parseUsageParams(keyAttrs);
		request.mechanism = mechanism;
		
		try{
			CryptoResponse response = crtBo.generateKey(request);
			return response.generatedKeyLabel;
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

	public String[] C_GenerateKeyPair(String tokenLabel, String mechanism, String[] pubKeyAttrs, String[] priKeyAttrs) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_GenerateKeyPair: ..........");
		
		CryptoRequest request = new CryptoRequest();
		request.tokenLabel = tokenLabel;
		request.PubKeyAttr = KeyAttrMgmt.getInstance().parseUsageParams(pubKeyAttrs);
		request.PriKeyAttr = KeyAttrMgmt.getInstance().parseUsageParams(priKeyAttrs);
		request.mechanism = mechanism;
			
		try{
			CryptoResponse response = crtBo.generateKeyPair(request);
			return response.keyLabelPair;
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}
	
	public String C_ImportSecretKey(String tokenLabel, String mechanism, String[] keyAttrs) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_ImportSecretKey: ...........\n");
		
		CryptoRequest request = new CryptoRequest();

		request.tokenLabel = tokenLabel;
		request.keyAttr = KeyAttrMgmt.getInstance().parseUsageParams(keyAttrs);
		request.mechanism = mechanism;
				
		try{
			CryptoResponse response = crtBo.importSecretKey(request);
			return response.keyLabel;
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

	public String C_ImportPublicKey(String tokenLabel, String mechanism, String[] keyAttrs) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_ImportPublicKey: ...........\n");
		
		CryptoRequest request = new CryptoRequest();
		request.tokenLabel = tokenLabel;
		request.keyAttr = KeyAttrMgmt.getInstance().parseUsageParams(keyAttrs);
		request.mechanism = mechanism;
				
		try{
			CryptoResponse response = crtBo.importPublicKey(request);
			return response.keyLabel;	
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}
	
	public String C_ImportPrivateKey(String tokenLabel, String mechanism, String[] keyAttrs) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_ImportPrivateKey: ...........\n");
		
		CryptoRequest request = new CryptoRequest();
		request.tokenLabel = tokenLabel;
		request.keyAttr = KeyAttrMgmt.getInstance().parseUsageParams(keyAttrs);
		request.mechanism = mechanism;
				
		try{
			CryptoResponse response = crtBo.importPrivateKey(request);
			return response.keyLabel;	
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

//shhan 임시키 처러 테스트 함수.. 진행중인 함수
//	public byte[] C_Encrypt(String mechanism, String[] mechanismParameters, String keyLabel, byte[] data, String[] keyAttrs) throws KmsException{
	public byte[] C_Encrypt(String mechanism, String[] mechanismParameters, String keyLabel, byte[] data, KeyAttributes keyAttrs) throws KmsException, Exception{
		logger.debug("KmsCryptoServiceImpl::C_Encrypt: ...........\n");
		
		CryptoRequest request = new CryptoRequest();

		request.keyLabel 		= keyLabel;
		request.mechanism 	= mechanism;
		request.mechanismParameters = mechanismParameters;

		if (request.keyLabel.isEmpty() == true || request.keyLabel == null){
//			request.keyAttr = KeyAttrMgmt.getInstance().parseUsageParams(keyAttrs);
			request.keyAttr = keyAttrs;
//			PrintKeyAttribute(keyAttrs);
		}

		request.data = data;

		
		//add 20150902
		KeyInfo found = KeyMap.getInstance().find(keyLabel);
		if(found == null && keyAttrs == null)
		{
			logger.debug("KmsTCPCryptoServiceImpl::C_Encrypt ReloadMap when : keyLabel = [{}] & keyAttrs is null", keyLabel);
			ReloadMap();
		}
		
		
		try{
			CryptoResponse response = crtBo.encrypt(request);
			logger.debug("response.dataEncrypted : [{}]", Util.byteArray2hexString(response.dataEncrypted));
			return response.dataEncrypted;
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

//	public byte[] C_Decrypt(String mechanism, String[] mechanismParameters, String keyLabel, byte[] data,String[] keyAttrs) throws KmsException {
	public byte[] C_Decrypt(String mechanism, String[] mechanismParameters, String keyLabel, byte[] data,KeyAttributes keyAttrs) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_Decrypt: ...........\n");

		CryptoRequest request = new CryptoRequest();

		request.keyLabel = keyLabel;
		request.mechanism = mechanism;
		request.mechanismParameters = mechanismParameters;
		request.data = data;

		if (request.keyLabel.isEmpty() == true || request.keyLabel == null){
//			request.keyAttr = KeyAttrMgmt.getInstance().parseUsageParams(keyAttrs);
			request.keyAttr = keyAttrs;
//			PrintKeyAttribute(keyAttrs);
		}

		KeyInfo found = KeyMap.getInstance().find(keyLabel);
		if(found == null && keyAttrs == null)
		{
			logger.debug("KmsTCPCryptoServiceImpl::C_Encrypt ReloadMap when : keyLabel = [{}] & keyAttrs is null", keyLabel);
			ReloadMap();
		}
		
		try{
			CryptoResponse response = crtBo.decrypt(request);
			logger.debug("response.dataDecrypted : [{}]", Util.byteArray2hexString(response.dataDecrypted));
			return response.dataDecrypted;
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

	public byte[] C_Digest(String mechanism, String[] mechanismParameters, byte[] data) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_Digest: ...........\n");
		
		CryptoRequest request = new CryptoRequest();
		request.mechanism = mechanism;
		request.mechanismParameters = mechanismParameters;
		request.data = data;
			
		try{
			CryptoResponse response = crtBo.digest(request);
			return response.dataDigest;	
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

	public byte[] C_Sign(String mechanism, String[] mechanismParameters, String keyLabel, byte[] data) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_Sign: ...........\n");
		
		CryptoRequest request = new CryptoRequest();
		//request.slotIx;
		request.keyLabel = keyLabel;
		request.mechanism = mechanism;
		request.mechanismParameters = mechanismParameters;
		request.data = data;
				
		KeyInfo found = KeyMap.getInstance().find(keyLabel);
		if(found == null)
		{
			logger.debug("KmsTCPCryptoServiceImpl::C_Encrypt ReloadMap when : keyLabel = [{}] & keyAttrs is null", keyLabel);
			ReloadMap();
		}
		
		try{
			CryptoResponse response = crtBo.sign(request);
			return response.signature;
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

	public boolean C_Verify(String mechanism, String[] mechanismParameters, String keyLabel, byte[] data, byte[] signature) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_Verify: ...........\n");
		
		CryptoRequest request = new CryptoRequest();
		//request.slotIx;
		request.keyLabel = keyLabel;
		request.mechanism = mechanism;
		request.mechanismParameters = mechanismParameters;
		request.data = data;
		request.signature = signature;
		
		KeyInfo found = KeyMap.getInstance().find(keyLabel);
		if(found == null)
		{
			logger.debug("KmsTCPCryptoServiceImpl::C_Encrypt ReloadMap when : keyLabel = [{}] & keyAttrs is null", keyLabel);
			ReloadMap();
		}
				
		try{
			CryptoResponse response = crtBo.verify(request);
			return response.verified;
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

	public byte[] C_WrapKey(String mechanism, String[] mechanismParameters, String wrappingKeyLabel, String keyLabel) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_WrapKey: ...........\n");
		
		CryptoRequest request = new CryptoRequest();
		//request.slotIx;
		request.keyLabel = keyLabel;
		request.wrappingKeyLabel = wrappingKeyLabel;
		request.mechanism = mechanism;
		request.mechanismParameters = mechanismParameters;
		
		KeyInfo found = KeyMap.getInstance().find(keyLabel);
		if(found == null)
		{
			logger.debug("KmsTCPCryptoServiceImpl::C_Encrypt ReloadMap when : keyLabel = [{}] & keyAttrs is null", keyLabel);
			ReloadMap();
		}

		try{
			CryptoResponse response = crtBo.wrapKey(request);
			return response.keyWrapped;
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}

	//UnwrapKey는 pkcs#11의 구조와 다르다.. 추후에 구현한다.
	public String C_UnwrapKey(String mechanism, String[] mechanismParameters, String unWrappingKeyLabel, byte[] wrappedKey, String[] keyAttrs) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_UnwrapKey: ...........\n");
		
		CryptoRequest request = new CryptoRequest();
		//request.slotIx;
		request.keyAttr = KeyAttrMgmt.getInstance().parseUsageParams(keyAttrs);
		request.keyLabel = unWrappingKeyLabel;
		request.mechanism = mechanism;
		request.mechanismParameters = mechanismParameters;
		request.data = wrappedKey;
		
		KeyInfo found = KeyMap.getInstance().find(unWrappingKeyLabel);
		if(found == null)
		{
			logger.debug("KmsTCPCryptoServiceImpl::C_Encrypt ReloadMap when : keyLabel = [{}] & keyAttrs is null", unWrappingKeyLabel);
			ReloadMap();
		}
		
		try{
			CryptoResponse response = crtBo.unwrapKey(request);
			return response.generatedKeyLabel;
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

	//C_DeriveKey는 pkcs#11의 구조와 다르다.. 추후에 구현한다.
	public String C_DeriveKey(String mechanism, String[] mechanismParameters, String baseKeyLabel, String[] keyAttrs) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_DeriveKey: ...........\n");
		
		CryptoRequest request = new CryptoRequest();
		//request.slotIx;
		request.keyAttr = KeyAttrMgmt.getInstance().parseUsageParams(keyAttrs);
		request.keyLabel = baseKeyLabel;
		request.mechanism = mechanism;
		request.mechanismParameters = mechanismParameters;
				
		KeyInfo found = KeyMap.getInstance().find(baseKeyLabel);
		if(found == null)
		{
			logger.debug("KmsTCPCryptoServiceImpl::C_Encrypt ReloadMap when : keyLabel = [{}] & keyAttrs is null", baseKeyLabel);
			ReloadMap();
		}
		
		try{
			CryptoResponse response = crtBo.deriveKey(request);	
			return response.generatedKeyLabel;
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}
	
	public String[] C_GetAttributeValue(String keyLabel) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_GetAttributeValue: ...........\n");
		
		CryptoRequest request = new CryptoRequest();
		request.keyAttr = null;
		request.keyLabel = keyLabel;
		
		KeyInfo found = KeyMap.getInstance().find(keyLabel);
		if(found == null)
		{
			logger.debug("KmsTCPCryptoServiceImpl::C_Encrypt ReloadMap when : keyLabel = [{}] & keyAttrs is null", keyLabel);
			ReloadMap();
		}
				
		try{
			CryptoResponse response = crtBo.getAttributeValue(request);
			return response.attrs;	
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	
	}

	public void C_DestroyKey(String keyLabel) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_DestroyKey: ...........\n");
		
		CryptoRequest request = new CryptoRequest();
		request.keyLabel = keyLabel;
		
		KeyInfo found = KeyMap.getInstance().find(keyLabel);
		if(found == null)
		{
			logger.debug("KmsTCPCryptoServiceImpl::C_Encrypt ReloadMap when : keyLabel = [{}] & keyAttrs is null", keyLabel);
			ReloadMap();
		}
		
		try{
			CryptoResponse response = crtBo.destroyKey(request);	
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

	//
	public LogicalToken[] getTokens() {
		Object[] ptlist = CryptoManager.getInstance().getTokens();
		if (ptlist == null) return null;
		LogicalToken[] ltokens = new LogicalToken[ptlist.length];
		for (int i=0; i<ptlist.length; i++) {
			PToken pt = (PToken) ptlist[i];
			ltokens[i] = new LogicalToken((int) pt.slotIx,								/*logicalSlotIx*/
														pt.tokenLabel,								/*lTokenLabel*/
														0,												/*logicalHsmNo*/
														(int) pt.slotIx,								/*slotOfs within Local HSM*/
														"L-NDEF",									/*lHsmLabel*/
														"P-NDEF",									/*p1HsmLabel*/
														pt.tokenLabel);							/*p1HsmTokenLabel*/
		}
		
		return ltokens;
	}	
	
	// ret 0: OK, -1: error
		public int dataXfer(String type, byte[] data) {
			if (type.equalsIgnoreCase("KeyProfile") == true) {
				if (data == null) return -1;
				
				InputStream inMsg  = new ByteArrayInputStream(data);
				GpMsgBinder msgHandler = GpMsgBinder.getInstance();
				KeyProfile keyProfile = msgHandler.unmarshal(inMsg, "KeyProfile", KeyProfile.class);
				
				logger.debug("KmsCryptoServiceImpl::dataXfer: KeyProfile desc : {}", keyProfile.getDescription());
				logger.debug("KmsCryptoServiceImpl::dataXfer: KeyProfile type : {}/{}", keyProfile.getKeyInfo().getType(), keyProfile.getKeyInfo().getSubType());
				
				//byte[] xmlBytes = msgHandler.marshal(keyProfile);
				return 0;
			}
			else if (type.equalsIgnoreCase("company") == true) {
				return 0;
			}
			return -1;
		}

///////////////////////////////////////////////////////////////////////////////////////////////////
	
	public KeyAttributes GenerateKey(String tokenLabel, String mechanism,KeyAttributes keyAttrs) throws KmsException, Exception {
		// TODO Auto-generated method stub
		logger.debug("KmsCryptoServiceImpl::C_GenerateKey(v2): ...........\n");
		
		if (keyAttrs.cka_label == null || keyAttrs.cka_label.toString() == "" ||keyAttrs.cka_label.length ==0 || keyAttrs.cka_label.toString().trim() == "") {
			String tmpLabel = "Key"+UUIDGenerator.getNewId();
			keyAttrs.cka_label = tmpLabel.getBytes();
		}
		
		CryptoRequest request = new CryptoRequest();
		request.tokenLabel = tokenLabel;
		request.keyAttr = KeyAttrMgmt.getInstance().validateKeyAttribubute(keyAttrs);
		request.mechanism = mechanism;
				
		try{
			CryptoResponse response = crtBo.generateKey(request);
//			return keyAttrs;
			return response.keyAttr;
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}

	public KeyAttributes[] GenerateKeyPair(String tokenLabel, String mechanism,KeyAttributes pubKeyAttrs, KeyAttributes priKeyAttrs) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_GenerateKeyPair(v2): ...........\n");
		
		if (pubKeyAttrs.cka_label == null || pubKeyAttrs.cka_label.toString() == "" ||pubKeyAttrs.cka_label.length ==0 || pubKeyAttrs.cka_label.toString().trim() == "") {
			String tmpLabel = "Key"+UUIDGenerator.getNewId();
			pubKeyAttrs.cka_label = tmpLabel.getBytes();
		}
		if (priKeyAttrs.cka_label == null || priKeyAttrs.cka_label.toString() == "" ||priKeyAttrs.cka_label.length ==0 || priKeyAttrs.cka_label.toString().trim() == "") {
			String tmpLabel = "Key"+UUIDGenerator.getNewId();
			priKeyAttrs.cka_label = tmpLabel.getBytes();
		}
		
		CryptoRequest request = new CryptoRequest();
		request.tokenLabel = tokenLabel;
		request.PubKeyAttr = KeyAttrMgmt.getInstance().validateKeyAttribubute(pubKeyAttrs);
		request.PriKeyAttr = KeyAttrMgmt.getInstance().validateKeyAttribubute(priKeyAttrs);
		request.mechanism = mechanism;
				
		try{
			CryptoResponse response = crtBo.generateKeyPair(request);
			KeyAttributes[] ka = new KeyAttributes[2];
			ka[0] = pubKeyAttrs;
			ka[1] = priKeyAttrs;
			return ka;
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}
	
	public KeyAttributes ImportSecretKey(String tokenLabel, String mechanism,KeyAttributes keyAttrs) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_ImportSecretKey(v2): ...........\n");
		
		if (keyAttrs.cka_label == null || keyAttrs.cka_label.toString() == "" ||keyAttrs.cka_label.length ==0 || keyAttrs.cka_label.toString().trim() == "") {
			String tmpLabel = "Key"+UUIDGenerator.getNewId();
			keyAttrs.cka_label = tmpLabel.getBytes();
		}
		CryptoRequest request = new CryptoRequest();
		request.tokenLabel = tokenLabel;
		request.keyAttr = KeyAttrMgmt.getInstance().validateKeyAttribubute(keyAttrs);
		request.mechanism = mechanism;

		try{
			CryptoResponse response = crtBo.importSecretKey(request);
			return response.keyAttr;
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}

//		return keyAttrs;
	}

	public KeyAttributes ImportPublicKey(String tokenLabel, String mechanism,KeyAttributes keyAttrs) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_ImportPublicKey(v2): ...........\n");
		
		if (keyAttrs.cka_label == null || keyAttrs.cka_label.toString() == "" ||keyAttrs.cka_label.length ==0 || keyAttrs.cka_label.toString().trim() == "") {
			String tmpLabel = "Key"+UUIDGenerator.getNewId();
			keyAttrs.cka_label = tmpLabel.getBytes();
		}
		
		CryptoRequest request = new CryptoRequest();
		request.tokenLabel = tokenLabel;
		request.keyAttr = KeyAttrMgmt.getInstance().validateKeyAttribubute(keyAttrs);
		request.mechanism = mechanism;
		
		try{
			CryptoResponse response = crtBo.importPublicKey(request);
//			return keyAttrs;
			//modify by shifei 2014-06-24
			return response.keyAttr;
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}

	public KeyAttributes ImportPrivateKey(String tokenLabel, String mechanism, KeyAttributes keyAttrs) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_ImportPrivateKey(v2): ...........\n");
		
		if (keyAttrs.cka_label == null || keyAttrs.cka_label.toString() == "" ||keyAttrs.cka_label.length ==0 || keyAttrs.cka_label.toString().trim() == "") {
			String tmpLabel = "Key"+UUIDGenerator.getNewId();
			keyAttrs.cka_label = tmpLabel.getBytes();
		}
		
		CryptoRequest request = new CryptoRequest();
		request.tokenLabel = tokenLabel;
		request.keyAttr = KeyAttrMgmt.getInstance().validateKeyAttribubute(keyAttrs);
		request.mechanism = mechanism;
				
		try{
			CryptoResponse response = crtBo.importPrivateKey(request);
//			return keyAttrs;
			//modify by shifei 2014-06-24
			return response.keyAttr;
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}

	//-- 이미 구현핬음.
	public KeyAttributes UnwrapKey(String mechanism, String[] mechanismParameters, String unWrappingKeyLabel,byte[] wrappedKey, KeyAttributes keyAttrs) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_UnwrapKey(v2): ...........\n");
		
		if (keyAttrs.cka_label == null || keyAttrs.cka_label.toString() == "" ||keyAttrs.cka_label.length ==0 || keyAttrs.cka_label.toString().trim() == "") {
			String tmpLabel = "Key"+UUIDGenerator.getNewId();
			keyAttrs.cka_label = tmpLabel.getBytes();
		}
		
		CryptoRequest request = new CryptoRequest();
		request.keyAttr = KeyAttrMgmt.getInstance().validateKeyAttribubute(keyAttrs);
		request.keyLabel = unWrappingKeyLabel;
		request.mechanism = mechanism;
		request.mechanismParameters = mechanismParameters;
		request.data = wrappedKey;
				
		CryptoResponse response = new CryptoResponse();
		
		KeyInfo found = KeyMap.getInstance().find(unWrappingKeyLabel);
		if(found == null)
		{
			logger.debug("KmsTCPCryptoServiceImpl::C_Encrypt ReloadMap when : keyLabel = [{}] & keyAttrs is null", unWrappingKeyLabel);
			ReloadMap();
		}
		
		try {
			response = crtBo.unwrapKey(request);
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
//		return keyAttrs;
		return response.keyAttr;
	}

	//-- 이미 구현핬음.
	public KeyAttributes DeriveKey(String mechanism,String[] mechanismParameters, String baseKeyLabel,KeyAttributes keyAttrs) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_DeriveKey(v2): ...........\n");
		
		if (keyAttrs.cka_label == null || keyAttrs.cka_label.toString() == "" ||keyAttrs.cka_label.length ==0 || keyAttrs.cka_label.toString().trim() == "") {
			String tmpLabel = "Key"+UUIDGenerator.getNewId();
			keyAttrs.cka_label = tmpLabel.getBytes();
		}
		
		CryptoRequest request = new CryptoRequest();
		request.keyAttr = KeyAttrMgmt.getInstance().validateKeyAttribubute(keyAttrs);
		request.keyLabel = baseKeyLabel;
		request.mechanism = mechanism;
		request.mechanismParameters = mechanismParameters;
			
		CryptoResponse response = new CryptoResponse(); 
		
		KeyInfo found = KeyMap.getInstance().find(baseKeyLabel);
		if(found == null)
		{
			logger.debug("KmsTCPCryptoServiceImpl::C_Encrypt ReloadMap when : keyLabel = [{}] & keyAttrs is null", baseKeyLabel);
			ReloadMap();
		}
		
		try{
			response = crtBo.deriveKey(request);	
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
		return response.keyAttr;
//		return keyAttrs;
	}

	public KeyAttributes GetKeyAttribute(String keyLabel) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_GetKeyAttribute: ...........\n");
		
		CryptoRequest request = new CryptoRequest();
		request.keyAttr = null;
		request.keyLabel = keyLabel;
		
		KeyInfo found = KeyMap.getInstance().find(keyLabel);
		if(found == null)
		{
			logger.debug("KmsTCPCryptoServiceImpl::C_Encrypt ReloadMap when : keyLabel = [{}] & keyAttrs is null", keyLabel);
			ReloadMap();
		}
			
		try{
			CryptoResponse response = crtBo.getAttributeValue(request);
			KeyAttributes keyAttrs = KeyAttrMgmt.getInstance().parseUsageParams(response.attrs);
			return keyAttrs;
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}
	
	public byte[] generateRandom(int size) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::generateRandom: start..... size[{}]", size);
		
		CryptoRequest request = new CryptoRequest();
		request.size = size;
		
		try{
			CryptoResponse response = crtBo.generateRandom(request);
			return response.randomData;	
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}

	}


///////////////////////////////////////////////////////////////////////////////////////////////////

	public Key C_GenerateKey3(String tokenLabel, String mechanism, Key key) throws KmsException, Exception {
		// TODO Auto-generated method stub
		logger.debug("KmsCryptoServiceImpl::C_ImportSecretKey(v3): ...........\n");
		
		KeyAttributes keyAttrs = KeyAttrMgmt.getInstance().convKey2KeyAttributes(key);
		
		if (keyAttrs.cka_label == null) {
			String tmpLabel = "Key"+UUIDGenerator.getNewId();
			keyAttrs.cka_label = tmpLabel.getBytes();
		}
		
		CryptoRequest request = new CryptoRequest();
		request.tokenLabel = tokenLabel;
		request.keyAttr = KeyAttrMgmt.getInstance().validateKeyAttribubute(keyAttrs);
		request.mechanism = mechanism;

		try{
			CryptoResponse response = crtBo.importSecretKey(request);	
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
		key.setKmsLabel(new String(keyAttrs.cka_label));
		return key;
	}

	public Key[] C_GenerateKeyPair3(String tokenLabel, String mechanism,Key pubKey, Key priKey) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_GenerateKeyPair(v3): ...........\n");
		
		KeyAttributes pubKeyAttrs = KeyAttrMgmt.getInstance().convKey2KeyAttributes(pubKey);
		KeyAttributes priKeyAttrs = KeyAttrMgmt.getInstance().convKey2KeyAttributes(priKey);

		if (pubKeyAttrs.cka_label == null) {
			String tmpLabel = "Key"+UUIDGenerator.getNewId();
			pubKeyAttrs.cka_label = tmpLabel.getBytes();
		}
		if (priKeyAttrs.cka_label == null) {
			String tmpLabel = "Key"+UUIDGenerator.getNewId();
			priKeyAttrs.cka_label = tmpLabel.getBytes();
		}
		
		CryptoRequest request = new CryptoRequest();
		request.tokenLabel = tokenLabel;
		request.PubKeyAttr = KeyAttrMgmt.getInstance().validateKeyAttribubute(pubKeyAttrs);
		request.PriKeyAttr = KeyAttrMgmt.getInstance().validateKeyAttribubute(priKeyAttrs);
		request.mechanism = mechanism;
				
		try{
			CryptoResponse response = crtBo.generateKeyPair(request);	
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	
		pubKey.setKmsLabel(new String(pubKeyAttrs.cka_label));
		priKey.setKmsLabel(new String(priKeyAttrs.cka_label));
		Key[] ka = new Key[2];
		ka[0] = pubKey;
		ka[1] = priKey;
		return ka;
	}

	public Key C_ImportSecretKey3(String tokenLabel, String mechanism,Key key) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_ImportSecretKey(v3): ...........\n");
		
		KeyAttributes keyAttrs = KeyAttrMgmt.getInstance().convKey2KeyAttributes(key);

		if (keyAttrs.cka_label == null) {
			String tmpLabel = "Key"+UUIDGenerator.getNewId();
			keyAttrs.cka_label = tmpLabel.getBytes();
		}
		
		CryptoRequest request = new CryptoRequest();
		request.tokenLabel = tokenLabel;
		request.keyAttr = KeyAttrMgmt.getInstance().validateKeyAttribubute(keyAttrs);
		request.mechanism = mechanism;
		
		try{
			CryptoResponse response = crtBo.importSecretKey(request);	
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
		
		key.setKmsLabel(new String(keyAttrs.cka_label));
		return key;
	}

	public Key C_ImportPublicKey3(String tokenLabel, String mechanism,Key key) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_ImportPublicKey(v3): ...........\n");
		
		KeyAttributes keyAttrs = KeyAttrMgmt.getInstance().convKey2KeyAttributes(key);

		if (keyAttrs.cka_label == null) {
			String tmpLabel = "Key"+UUIDGenerator.getNewId();
			keyAttrs.cka_label = tmpLabel.getBytes();
		}
		
		CryptoRequest request = new CryptoRequest();
		request.tokenLabel = tokenLabel;
		request.keyAttr = KeyAttrMgmt.getInstance().validateKeyAttribubute(keyAttrs);
		request.mechanism = mechanism;
				
		try{
			CryptoResponse response = crtBo.importPublicKey(request);	
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
		key.setKmsLabel(new String(keyAttrs.cka_label));
		return key;
	}

	public Key C_ImportPrivateKey3(String tokenLabel, String mechanism,Key key) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_ImportPrivateKey(v3): ...........\n");
		
		KeyAttributes keyAttrs = KeyAttrMgmt.getInstance().convKey2KeyAttributes(key);

		if (keyAttrs.cka_label == null) {
			String tmpLabel = "Key"+UUIDGenerator.getNewId();
			keyAttrs.cka_label = tmpLabel.getBytes();
		}
		
		CryptoRequest request = new CryptoRequest();
		request.tokenLabel = tokenLabel;
		request.keyAttr = KeyAttrMgmt.getInstance().validateKeyAttribubute(keyAttrs);
		request.mechanism = mechanism;
				
		try{
			CryptoResponse response = crtBo.importPrivateKey(request);	
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
		
		key.setKmsLabel(new String(keyAttrs.cka_label));
		return key;
	}

	public Key C_UnwrapKey3(String mechanism, String[] mechanismParameters,String unWrappingKeyLabel, byte[] wrappedKey, Key key)throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_UnwrapKey(v2): ...........\n");
		
		KeyAttributes keyAttrs = KeyAttrMgmt.getInstance().convKey2KeyAttributes(key);

		if (keyAttrs.cka_label == null) {
			String tmpLabel = "Key"+UUIDGenerator.getNewId();
			keyAttrs.cka_label = tmpLabel.getBytes();
		}
		
		CryptoRequest request = new CryptoRequest();
		request.keyAttr = KeyAttrMgmt.getInstance().validateKeyAttribubute(keyAttrs);
		request.keyLabel = unWrappingKeyLabel;
		request.mechanism = mechanism;
		request.mechanismParameters = mechanismParameters;
		request.data = wrappedKey;
				
		CryptoResponse response = crtBo.unwrapKey(request);
		
		key.setKmsLabel(new String(keyAttrs.cka_label));
		return key;
	}

	public Key C_DeriveKey3(String mechanism, String[] mechanismParameters,String baseKeyLabel, Key key) throws KmsException, Exception {
		logger.debug("KmsCryptoServiceImpl::C_DeriveKey(v2): ...........\n");
		
		KeyAttributes keyAttrs = KeyAttrMgmt.getInstance().convKey2KeyAttributes(key);

		if (keyAttrs.cka_label == null) {
			String tmpLabel = "Key"+UUIDGenerator.getNewId();
			keyAttrs.cka_label = tmpLabel.getBytes();
		}
		
		CryptoRequest request = new CryptoRequest();
		request.keyAttr = KeyAttrMgmt.getInstance().validateKeyAttribubute(keyAttrs);
		request.keyLabel = baseKeyLabel;
		request.mechanism = mechanism;
		request.mechanismParameters = mechanismParameters;
				
		try{
			CryptoResponse response = crtBo.deriveKey(request);	
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
		key.setKmsLabel(new String(keyAttrs.cka_label));
		return key;
	}

	public Key C_GetKeyInfo3(String keyLabel) throws KmsException, Exception {
		// TODO Auto-generated method stub
		logger.debug("KmsCryptoServiceImpl::C_GetKeyAttribute: ...........\n");
		
		KeyAttributes keyAttrs = new KeyAttributes();
		try{
			keyAttrs = GetKeyAttribute(keyLabel);	
		}catch(KmsException e) {
			e.printStackTrace();
			throw new KmsException(e.getMsgcode());
		}catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
		Key key = null;
		key = KeyAttrMgmt.getInstance().convKeyAttributes2Key(keyAttrs);
		return key;
	}
	
	public byte[] C_Encrypt_v2(String mechanism, String[] mechanismParameters, String keyLabel, byte[] data, String tokenLabel) throws KmsException {	
		logger.debug("KmsCryptoServiceImpl::C_Encrypt_v2: ...........\n");
		
		CryptoRequest request = new CryptoRequest();
		//request.slotIx;
		request.tokenLabel = tokenLabel;
		request.keyLabel = keyLabel;
		request.mechanism = mechanism;
		request.mechanismParameters = mechanismParameters;
		request.data = data;
				
		CryptoResponse response = crtBo.encrypt_v2(request);
		return response.dataEncrypted;
	}

	public void PrintKeyAttribute(String[] keyAttrs){
		for (int i=0; 	i<keyAttrs.length; 	i++) {
			logger.debug("KmsCryptoServiceImpl::KeyAttribute: [{}]...........\n", keyAttrs[i]);
		}
	}
	
}
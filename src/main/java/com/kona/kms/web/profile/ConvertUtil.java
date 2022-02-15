package com.kona.kms.web.profile;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.globalplatform.namespaces.systems_profiles._1_1.Attribute;
import org.globalplatform.namespaces.systems_profiles._1_1.Component;
import org.globalplatform.namespaces.systems_profiles._1_1.Encoding;
import org.globalplatform.namespaces.systems_profiles._1_1.KeyCategory;
import org.globalplatform.namespaces.systems_profiles._1_1.KeyInfo;
import org.globalplatform.namespaces.systems_profiles._1_1.KeyPart;
import org.globalplatform.namespaces.systems_profiles._1_1.KeyProfile;
import org.globalplatform.namespaces.systems_profiles._1_1.Keypartalgorithm;
import org.globalplatform.namespaces.systems_profiles._1_1.Keysubtype;
import org.globalplatform.namespaces.systems_profiles._1_1.Mode;
import org.globalplatform.namespaces.systems_profiles._1_1.Revision;
import org.globalplatform.namespaces.systems_profiles._1_1.Revisions;
import org.globalplatform.namespaces.systems_profiles._1_1.TransportKey;
import org.globalplatform.namespaces.systems_profiles._1_1.Usage;
import org.globalplatform.namespaces.systems_profiles._1_1.Value;
import org.springframework.util.StringUtils;

import sun.security.pkcs11.wrapper.PKCS11Constants;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.cao.KMSCrypto;
import com.kona.kms.utils.Util;
import com.kona.kms.web.cert.model.CertJobModel;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.profile.model.KeyPartModel;
import com.kona.kms.web.profile.model.KeyProfileJobModel;
import com.kona.kms.web.profile.model.KeyProfileModel;
import com.kona.kms.web.profile.model.KeyProfileModel1;
import com.kona.kms.web.profile.model.KeyProfileRevisionModel;
import com.kona.kms.web.profile.model.KeyValueAttrVO;
import com.kona.kms.web.profile.model.KeyValueDTO;
import com.kona.kms.web.profile.model.KeyValueModel;
import com.kona.kms.web.profile.model.RSAKeyModel;
import com.kona.kms.web.profile.model.SecretKeyModel;
import com.kona.kms.web.profile.model.TransportKeyModel;
import com.kona.kms.web.profile.model.KeyValueModel.ValueComponent;

public class ConvertUtil {
	
	public static KeyProfileModel createPublicKeyProfileModel(KeyProfileModel pri, KeyProfileModel1 pub){
		
		KeyProfileModel kpm = new KeyProfileModel();
		
	    String uuid = UUID.randomUUID().toString();
		
		kpm.setKeyTypeCode("PUBLIC");
		pri.setKeyTypeCode("PRIVATE");
		kpm.setKeySubject(uuid);
		pri.setKeySubject(uuid);	
		
		kpm.setKeyProfileID(pub.getKeyProfileID1());
		kpm.setKeyProfileVersion(pub.getKeyProfileVersion1());
		kpm.setKeyProfileName(pub.getKeyProfileName1());
		kpm.setDescription(pub.getDescription1());
		
		kpm.setKeyLabel(pub.getKeyLabel1());
		kpm.setKeySize(pub.getKeySize1());
		kpm.setKeyAlgorithm(pub.getKeyAlgorithm1());
		
		kpm.setKeyUsageIndicatorValue(pub.getKeyUsageIndicatorValue1());
		kpm.setImportable(pub.isImportable1());
		kpm.setExportable(pub.isExportable1());
		kpm.setSensitive(pub.isSensitive1());
		kpm.setEncrypt(pub.isEncrypt1());
		kpm.setDecrypt(pub.isDecrypt1());
		kpm.setEncryptDecrypt(pub.isEncryptDecrypt1());
		kpm.setWrap(pub.isWrap1());
		kpm.setUnwrap(pub.isUnwrap1());
		kpm.setWrapUnwrap(pub.isWrapUnwrap1());
		kpm.setSign(pub.isSign1());
		kpm.setVerify(pub.isVerify1());
		kpm.setDerive(pub.isDerive1());
		
		/*
		 * Private / Public key common part
		 */	
		kpm.setKeySubtypeCode(pri.getKeySubtypeCode());
		kpm.setKeyVersion(pri.getKeyVersion());
		kpm.setKeyIdentifier(pri.getKeyIdentifier());
		kpm.setKeyDefinition(pri.getKeyDefinition());
		kpm.setKeyRoleCode(pri.getKeyRoleCode());
		kpm.setKeyIndex(pri.getKeyIndex());		
		
		
		kpm.setKeyOwner(pri.getKeyOwner());
		kpm.setCompanyID(pri.getCompanyID());
		kpm.setEffectiveStartDate(pri.getEffectiveStartDate());
		kpm.setEffectiveEndDate(pri.getEffectiveEndDate());
		kpm.setExpireDate(pri.getExpireDate());
		kpm.setRevocationDate(pri.getRevocationDate());
		kpm.setTestMode(pri.getTestMode());
		kpm.setActiveStatusCode(pri.getActiveStatusCode());
		kpm.setSendStatusCode(pri.getSendStatusCode());	
		
		
		kpm.setRegistrationUserID(pri.getRegistrationUserID());
		kpm.setRegistrationDate(pri.getRegistrationDate());
		kpm.setUpdateUserID(pri.getUpdateUserID());
		kpm.setUpdateDate(pri.getUpdateDate());		
		
		//add by shifei 2014-07-14
		kpm.setTokenLabel(pri.getTokenLabel());
		
		return kpm;
	}
	
	
	
	//add by shifei 20140603
	
	public static RSAKeyModel autoGenRSA(int size, int exp) throws Exception{
		
//				if(keySubtypeCode.equals(null)){
//					keySubtypeCode = "RSA";
//				}
		
		RSAUtils utils = new RSAUtils();
		
		utils.setKeyLength(size);
//				utils.setCertaintyOfPrime(100);
		utils.setRSAMode(2);
		
		int exp0x = 0x3;
		
		if(exp == 3){
			exp0x = 03;
		}else if(exp == 17){
			exp0x = 0x17;
		}else if (exp == 65537){
			exp0x = 0x65537;
		}
		
		utils.setExponent(exp0x);
		utils.initRSAKeyPair();
		
		RSAKeyParameters mypubkey = utils.getPublicKey();
		BigInteger mypubkey_modulus = mypubkey.getModulus();
		BigInteger mypubkey_exponent = mypubkey.getExponent();
		
		RSAPrivateCrtKeyParameters myprivkey = utils.getPrivateKey();
		BigInteger myprivkey_modulus = myprivkey.getModulus();
		
		BigInteger myprivkey_prime_p = myprivkey.getP();
		BigInteger myprivkey_prime_q = myprivkey.getQ();
		BigInteger myprivkey_dp = myprivkey.getDP();
		BigInteger myprivkey_dq = myprivkey.getDQ();
		BigInteger myprivkey_qp = myprivkey.getQInv();
		BigInteger myprivkey_exponent = myprivkey.getExponent();
		
		String modulus = mypubkey_modulus.toString(16).toUpperCase();
		String public_exponent = mypubkey_exponent.toString(16).toUpperCase();
		if(public_exponent.length()%2 != 0){
			public_exponent = "0" + public_exponent;
		}
		String prime_p = myprivkey_prime_p.toString(16).toUpperCase();
		String prime_q = myprivkey_prime_q.toString(16).toUpperCase();
		String prime_exponent_p = myprivkey_dp.toString(16).toUpperCase();
		String prime_exponent_q = myprivkey_dq.toString(16).toUpperCase();
		String crt_coefficient = myprivkey_qp.toString(16).toUpperCase();
		String private_exponent = myprivkey_exponent.toString(16).toUpperCase();
		
		RSAKeyModel rsaKeyModel = new RSAKeyModel();
		
		rsaKeyModel.setModulus(modulus);
		rsaKeyModel.setPublic_exponent(public_exponent);
		rsaKeyModel.setPrivate_exponent(private_exponent);
		rsaKeyModel.setPrime_p(prime_p);
		rsaKeyModel.setPrime_q(prime_q);
		rsaKeyModel.setPrime_exponent_p(prime_exponent_p);
		rsaKeyModel.setPrime_exponent_q(prime_exponent_q);
		rsaKeyModel.setCrt_coefficient(crt_coefficient);
		
		return rsaKeyModel;
		
	}
	
	
	//add by shifei 20140602
	//auto generate secret key
	public static SecretKeyModel autoGenSecret(int size, String keySubtype, String org_key) throws Exception{
		
		SecretKeyModel secretKeyModel = new SecretKeyModel();
	
		int length = size/4;
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		while(sb.length()<length){
			sb.append(Integer.toHexString(random.nextInt()));
		}
		String random1  = sb.toString().substring(0,length);
		
		byte[] random1_array = Util.hexString2byteArray(random1);
		byte[] org_key_array = Util.hexString2byteArray(org_key);
		byte[] xor1_array = Util.xor(org_key_array, random1_array);
		
		Random randomt = new Random();
		StringBuffer sbt = new StringBuffer();
		while(sbt.length()<length){
			sbt.append(Integer.toHexString(randomt.nextInt()));
		}
		String random2  = sbt.toString().substring(0,length);
		
		byte[] random2_array = Util.hexString2byteArray(random2);
		byte[] xor2_array = Util.xor(xor1_array, random2_array);
		byte[] random3_array = Util.xor(random1_array, random2_array);
//				byte[] key_array = Util.xor(random3_array, xor2_array);
		
		String temp = Util.byteArray2hexStringNormal(random1_array);
		String component1 = Util.byteArray2hexString(random1_array);
		String component2 = Util.byteArray2hexString(random2_array);
		String component3 = Util.byteArray2hexString(xor2_array);
		
		secretKeyModel.setComponent1(component1);
		secretKeyModel.setComponent2(component2);
		secretKeyModel.setComponent3(component3);
		secretKeyModel.setOrg_key(org_key);

		return secretKeyModel;
	}
	

	public static String oddCheckDES(String org_key) throws Exception{
//				OddCheckDESModel oddCheckDESModel = new OddCheckDESModel();
		
		byte[] org_key_array = Util.hexString2byteArray(org_key);
		
		String outString = "";  
		
		for(int i =0; i<org_key_array.length; i++){
			String temp = Util.byteToHex(org_key_array[i]);
			
			int tempint1 = Integer.parseInt(temp.substring(0, 1), 16);
			String bin1 = Integer.toBinaryString(tempint1);
			
			int tempint2 = Integer.parseInt(temp.substring(1, 2), 16);
			String bin2 = Integer.toBinaryString(tempint2);
			
			String bin = bin1 + bin2;
			
			int count = 0;
			for(int j=0;j<bin.length();j++){
				String tempstring = bin.substring(j, j+1);
				if (tempstring.equalsIgnoreCase("1")||tempstring == "1"){
					count ++;
				}
			}
			
			if (count%2 == 0){
//				tempint2 = tempint2 -1;
				String last = bin2.substring(bin2.length()-1);
				if(last.equals("1")){
					tempint2 = tempint2 - 1;
				}else if(last.equals("0")){
					tempint2 = tempint2 + 1;
				}
			}
			
			String tempint2string = Integer.toHexString(tempint2);
			String tempint1String = Integer.toHexString(tempint1);
			String tempstring = (tempint1String + tempint2string).toUpperCase();
			
			outString = outString + tempstring;
		}
		
//				oddCheckDESModel.setOrg_key(outString);
		
		return outString;
	}
	
	
	public static KeyProfile createKeyProfile(KeyProfileModel model) throws DatatypeConfigurationException{
		
		System.out.println("### Start converting key profile ###");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

		KeyProfile gpMsg = new KeyProfile();
		
		gpMsg.setUniqueID(StringUtil.StringToHexBinary(model.getKeyProfileID()));
    	gpMsg.setProfileVersion(model.getKeyProfileVersion());
    	
 //   	gpMsg.setErrataVersion(BigInteger.valueOf(model.getKeyProfileErrataVersion()));
    	gpMsg.setErrataVersion(BigInteger.valueOf(0));
		
    	gpMsg.setDescription(model.getDescription());	
		
		//componentname?
		
		/** Set Key Info*/
		KeyInfo keyInfo = new KeyInfo();
		
		if(model.getKeyVersion() != null){
			keyInfo.setVersion(StringUtil.StringToHexBinary(model.getKeyVersion()));
		}
		
		keyInfo.setType(KeyCategory.fromValue(model.getKeyTypeCode()));
		keyInfo.setSubType(Keysubtype.fromValue(model.getKeySubtypeCode()));
		keyInfo.setSize(BigInteger.valueOf(model.getKeySize()));		
		
		if(model.getKeyOwner() != null){
			keyInfo.setOwner(StringUtil.StringToHexBinary(model.getKeyOwner()));
		}							
		
		if(model.getEffectiveStartDate() != null){
			keyInfo.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(formatter2.format(model.getEffectiveStartDate())));
		}		
		
		if(model.getEffectiveEndDate() != null){
			keyInfo.setEndDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(formatter2.format(model.getEffectiveEndDate())));
		}		
		
		if(model.getRevocationDate() != null){
			keyInfo.setRecovationDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(formatter2.format(model.getRevocationDate())));
		}		
		
		//keyInfo.setKCVAlgorithm(Kcvalgorithmtype.fromValue(model.getKeyAlgorithm()));
		keyInfo.setMode(Mode.fromValue(model.getTestMode().toUpperCase()));
		keyInfo.setOwner(StringUtil.StringToHexBinary(model.getCompanyID()));
		
		System.out.println(" 1. Key Info Set");		
		
		// kcvalgorithmtype은 eightzeros 숫자?
		
		/** Set Usage */
		Usage usage = new Usage();
		
		KeyValueAttrVO vo = model.getKeyValueAttribute();
		
		usage.setEncrypt					(vo.isEncrypt);
    	usage.setDecrypt					(vo.isDecrypt);
    	usage.setDecryptEncrypt		(vo.isEncryptDecrypt);
    	usage.setSign						(vo.isSign);
    	usage.setVerify					(vo.isVerify);
    	usage.setWrap					(vo.isWrap);
    	usage.setUnwrap					(vo.isUnwrap);
    	usage.setUnwrapWrap			(vo.isWrapUnwrap);
    	usage.setDerive					(vo.isDerive);
    	
    	/** Set Attribute */
    	Attribute attr = new Attribute();
    	attr.setExportable(vo.isExportable);
    	attr.setImportable(vo.isImportable);
    	attr.setSensitive(vo.isSensitive);
		
    	System.out.println(" 2. Usage Set");
    	
    	/** Set Revision */
/*    	if(model.getRevisionHistory() != null && !model.getRevisionHistory().isEmpty()){
    		Revisions revs = new Revisions();
    		
    		revs.getArrayElement().add("Revision");
    		revs.getArrayIndex().add("#");
    		    		
    		
    		for(KeyProfileRevisionModel hist : model.getRevisionHistory()){
    			Revision rev = new Revision(); 
    			rev.setProfileID(StringUtil.StringToHexBinary(model.getKeyProfileID()));
    			rev.setVersion(hist.getKeyProfileVersion());
    			rev.setBy(hist.getUpdateUserID());    			    			    
    			  
    			String date = formatter.format(hist.getUpdateDate());     
    		
    			rev.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(date.substring(0, 10)));
    			rev.setTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(date.substring(11)));
    			
    			revs.getRevision().add(rev);
    		}
    		
    		gpMsg.setRevisions(revs);
    	}    	
*/
// shhan append 2014.06.03    	
// key profile에 Revisions를 추가한다.
    	Revisions revs = new Revisions();
		
		revs.getArrayElement().add("Revision");
		revs.getArrayIndex().add("#");
		    		
		
		Revision rev = new Revision(); 
		rev.setProfileID(StringUtil.StringToHexBinary(model.getKeyProfileID()));
		rev.setVersion(model.getKeyProfileVersion());
		rev.setBy(model.getKeyOwner());
		rev.setDigest("".getBytes());
		  
		String date = formatter.format(model.getUpdateDate());     
	
		rev.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(date.substring(0, 10)));
		rev.setTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(date.substring(11)));
		
		revs.getRevision().add(rev);
		
		gpMsg.setRevisions(revs);

    	System.out.println(" 3. Revision Set");
    	
    	if(model.getKeyPart() != null){
			KeyPartModel partInfo = model.getKeyPart();
			
			KeyPart keyPart = new KeyPart();
			
			keyPart.setNumberOfParts(BigInteger.valueOf(partInfo.getNumberOfParts()));
			keyPart.setPartNumber(BigInteger.valueOf(partInfo.getPartNumber()));
			
			if(partInfo.getAlgorithmCode() != null){
				keyPart.setAlgorithm(Keypartalgorithm.fromValue(partInfo.getAlgorithmCode()));
			}
			
			keyInfo.setKeyPart(keyPart);
		}
    	
    	System.out.println(" 4. Key Part Set");
		
		if(model.getTransportKey() != null){
			TransportKeyModel tranModel = model.getTransportKey();
			
			System.out.println(">>>>>>>>>> " + tranModel);
    		TransportKey tranKey = new TransportKey();
    		
    		tranKey.setID(StringUtil.StringToHexBinary(tranModel.getKeyID()));
    		tranKey.setName(tranModel.getKeyName());
    		tranKey.setOwner(StringUtil.StringToHexBinary(tranModel.getOwner()));
//    		tranKey.setVersion(StringUtil.StringToHexBinary(tranModel.getKeyVersion()));
//    		tranKey.setVersion((tranModel.getKeyVersion().getBytes()));
    		
    		//modify by shifei 2014-06-19
//    		tranKey.setVersion(tranModel.getKeyVersion());
    		
//    		if(tranModel.getAlgorithm() != null)
//    			tranKey.setAlgorithm(Tkalgorithm.fromValue(tranModel.getAlgorithm()));
    		
    		if(tranModel.getAlgorithmParameters() != null)
    			tranKey.setAlgorithmParameters(StringUtil.StringToHexBinary(tranModel.getAlgorithmParameters()));
    		
    		keyInfo.setTransportKey(tranKey);
		}
		
		System.out.println(" 5. Transport Key Set");
		
		/** Set Value */
    	if(model.getKeyValue() != null){
    		KeyValueModel valueModel = model.getKeyValue();
    		
    		Value value = new Value();
    		
    		if(valueModel.getFormatCode() != null){
    			//modify by shifei 20140919
//    			value.setFormat(valueModel.getFormatCode());
        		if(model.getKeySubtypeCode().equalsIgnoreCase("RSA")){
        			value.setFormat("MOD_EXP");
        		}else if (model.getKeySubtypeCode().equalsIgnoreCase("RSACRT")){
        			value.setFormat("CRT");
        		}else if (model.getKeySubtypeCode().equalsIgnoreCase("EC")){
        			value.setFormat("EC");
        		}else {
        			value.setFormat(valueModel.getFormatCode());
        		}
    		}
    		    		
    		for(ValueComponent vcom : valueModel.getComponents()){
    			Component comp = new Component();
    			
    			if(vcom.getName() != null){
    				comp.setName(vcom.getName());
    			}    			
    			
    			if(vcom.getValue() != null){
    				comp.setValue(vcom.getValue());
    			}
    			
    			if(vcom.getEncoding() != null){
    				comp.setEncoding(Encoding.fromValue(vcom.getEncoding()));
    			}
    			
    			if(vcom.getKcv() != null){
    				comp.setKCV(StringUtil.StringToHexBinary(vcom.getKcv()));
    			}
    			
    			value.getComponent().add(comp);
    		}
    		
    		gpMsg.setValue(value);
    	}else if (model.getKeyValue() == null){
    		Value value = new Value();
    		//modify by shifei 20140919
//    		value.setFormat(model.getKeySubtypeCode());
    		if(model.getKeySubtypeCode().equalsIgnoreCase("RSA")){
    			value.setFormat("MOD_EXP");
    		}else if (model.getKeySubtypeCode().equalsIgnoreCase("RSACRT")){
    			value.setFormat("CRT");
    		}else if (model.getKeySubtypeCode().equalsIgnoreCase("EC")){
    			value.setFormat("EC");
    		}else {
    			value.setFormat(model.getKeySubtypeCode());
    		}
    		
    		value.getArrayElement().add("Component");
    		value.getArrayIndex().add("#");
    		gpMsg.setValue(value);
    	}
    	
    	System.out.println(" 6. Key Value Set");
    	
    	/** */
    	
		gpMsg.setKeyInfo(keyInfo);
		gpMsg.setAttribute(attr);
		gpMsg.setUsage(usage);
		
		System.out.println("### End converting key profile ###");
		
		return gpMsg;
	}

	

	public static KeyProfileModel createKeyProfileModel(KeyProfile gpMsg, String updater) throws ParseException {
		
		System.out.println("### Start converting key profile model ###");
		
		Date now = new Date();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		KeyProfileModel model = new KeyProfileModel();
		model.setRegistrationDate(now);
		model.setRegistrationUserID(updater);
		model.setUpdateDate(now);
		model.setUpdateUserID(updater);
		model.setSendStatusCode("Y");
		model.setActiveStatusCode("Active");
		//model.setKeyDefinition("UNKNOWN");
		
		model.setKeyProfileID(StringUtil.HexToString(gpMsg.getUniqueID()));
//shhan update 2014.06.03		
//		model.setKeyProfileVersion(gpMsg.getProfileVersion() == null ? "01.00.00" : gpMsg.getProfileVersion());
		model.setKeyProfileVersion(gpMsg.getProfileVersion() == null ? "1.0.0" : gpMsg.getProfileVersion());

		model.setKeyProfileErrataVersion(gpMsg.getErrataVersion() == null ? 0 : gpMsg.getErrataVersion().intValue());
		model.setDescription(gpMsg.getDescription() == null ? null : gpMsg.getDescription());
		
		System.out.println(" 1. Default Set");
		
		KeyInfo keyInfo = gpMsg.getKeyInfo();
		
		model.setKeyTypeCode(keyInfo.getType().value());
		model.setKeySubtypeCode(keyInfo.getSubType().value());
		model.setKeySize(keyInfo.getSize() == null ? 0 : keyInfo.getSize().longValue());
		model.setKeyOwner(keyInfo.getOwner() == null ? null : StringUtil.HexToString(keyInfo.getOwner()));
		model.setKeyVersion(keyInfo.getVersion() == null ? null : StringUtil.HexToString(keyInfo.getVersion()));
		
		model.setEffectiveStartDate(keyInfo.getStartDate() == null ? null : formatter.parse(keyInfo.getStartDate().toString() + " 00:00:00"));
		model.setEffectiveEndDate(keyInfo.getEndDate() == null ? null : formatter.parse(keyInfo.getEndDate().toString() + " 00:00:00"));
		model.setRevocationDate(keyInfo.getRecovationDate() == null ? null : formatter.parse(keyInfo.getRecovationDate().toString() + " 00:00:00"));

		model.setTestMode(keyInfo.getMode() == null ? "TEST" : keyInfo.getMode().value());
		
		System.out.println(" 2. Key Info Set");
		
		Usage usg = gpMsg.getUsage();
		
		if(usg != null){
			model.setEncrypt(usg.isEncrypt() == null ? false : usg.isEncrypt());
			model.setDecrypt(usg.isDecrypt() == null ? false : usg.isDecrypt());
			model.setEncryptDecrypt(usg.isDecryptEncrypt() == null ? false : usg.isDecryptEncrypt());
			model.setSign(usg.isSign() == null ? false : usg.isSign());
			model.setVerify(usg.isVerify() == null ? false : usg.isVerify());
			model.setWrap(usg.isWrap() == null ? false : usg.isWrap());
			model.setUnwrap(usg.isUnwrap() == null ? false : usg.isUnwrap());
			model.setWrapUnwrap(usg.isUnwrapWrap() == null ? false : usg.isUnwrapWrap());
			model.setDerive(usg.isDerive() == null ? false : usg.isDerive());
		}
		
		System.out.println(" 3. Usage Set");
		
		Attribute attr = gpMsg.getAttribute();
		
		if(attr != null){
			model.setExportable(attr.isExportable() == null ? false : attr.isExportable());
			model.setImportable(attr.isImportable() == null ? false : attr.isImportable());
			model.setSensitive(attr.isSensitive() == null ? false : attr.isSensitive());
		}		
		
		System.out.println(" 4. Attribute Set");

		List<KeyProfileRevisionModel> hists = new ArrayList<KeyProfileRevisionModel>();
		
		Revisions revs = gpMsg.getRevisions();
		
		if(revs != null){
			for(Revision rev : revs.getRevision()){
				KeyProfileRevisionModel hist = new KeyProfileRevisionModel();
				
				hist.setKeyProfileID(rev.getProfileID() == null ? model.getKeyProfileID() : StringUtil.HexToString(rev.getProfileID()));
				hist.setKeyProfileVersion(rev.getVersion() == null ? model.getKeyProfileVersion() : model.getKeyProfileVersion());
				hist.setDescription(rev.getDescription());
				hist.setDigest(rev.getDigest() == null ? null : StringUtil.HexToString(rev.getDigest()));
				hist.setRegistrationDate(now);
				hist.setRegistrationUserID(updater);
				hist.setUpdateDate(rev.getDate() == null ? now : formatter.parse(rev.getDate().toString()+" "+rev.getTime().toString()));
				hist.setUpdateUserID(rev.getBy() == null ? updater : rev.getBy());
				
				hists.add(hist);
			}
		}
		
		if(!hists.isEmpty()){
			model.setRevisionHistory(hists);
		}
		
		System.out.println(" 5. Revision Set");
		
		KeyPart keyPart = keyInfo.getKeyPart();
		
		if(keyPart != null){
			KeyPartModel partModel = new KeyPartModel();
			partModel.setKeyProfileID(model.getKeyProfileID());
			partModel.setKeyProfileVersion(model.getKeyProfileVersion());
			partModel.setNumberOfParts(keyPart.getNumberOfParts() == null ? 0 : keyPart.getNumberOfParts().intValue());
			partModel.setPartNumber(keyPart.getPartNumber() == null ? 0 : keyPart.getPartNumber().intValue());
			partModel.setAlgorithmCode(keyPart.getAlgorithm() == null ?  null : keyPart.getAlgorithm().value());
			
			model.setKeyPart(partModel);
		}	
		
		System.out.println(" 6. KeyPart Set");
		
		TransportKey transKey = keyInfo.getTransportKey();
		
		if(transKey != null){
			TransportKeyModel transModel = new TransportKeyModel();
			
			transModel.setKeyID(transKey.getID() == null ? null : StringUtil.HexToString(transKey.getID()));			
//			transModel.setKeyVersion(transKey.getVersion() == null ? null : StringUtil.HexToString(transKey.getVersion()));
			
			//modify by shifei 2014-06-19
//			transModel.setKeyVersion(transKey.getVersion() == null ? null : transKey.getVersion());
			
//			String tempString = new String(transKey.getVersion());
//			transModel.setKeyVersion(transKey.getVersion() == null ? null : tempString);
			
			transModel.setKeyName(transKey.getName() == null ? null : transKey.getName());
			transModel.setOwner(transKey.getOwner() == null ? null : StringUtil.HexToString(transKey.getOwner()));
			transModel.setAlgorithm(transKey.getAlgorithm() == null ? null : transKey.getAlgorithm().value());
			transModel.setAlgorithmParameters(transKey.getAlgorithmParameters() == null ? null : StringUtil.HexToString(transKey.getAlgorithmParameters()));
			
			model.setTransportKey(transModel);
		}
		
		System.out.println(" 7. TransportKey Set");
		
		Value keyValue = gpMsg.getValue();
		
		if(keyValue != null){
			KeyValueModel valueModel = new KeyValueModel();
			
			valueModel.setFormatCode(keyValue.getFormat() == null ? null : keyValue.getFormat());
			
			List<Component> components = keyValue.getComponent();
			List<ValueComponent> values = new ArrayList<ValueComponent>();
			
			for(Component comp : components){
				KeyValueModel.ValueComponent compModel = valueModel.new ValueComponent();
				
				compModel.setName(comp.getName() == null ? null : comp.getName());
				compModel.setEncoding(comp.getEncoding() == null ? null : comp.getEncoding().value());
				compModel.setOrder(comp.getOrder() == null ? 0 : comp.getOrder().intValue());
				compModel.setKcv(comp.getKCV() == null ? null : StringUtil.HexToString(comp.getKCV()));
				compModel.setValue(comp.getValue());
				
				values.add(compModel);
			}
			
			valueModel.setComponents(values);
			
			model.setKeyValue(valueModel);
			model.setKeyValueFlag("Y");
		}
		
		System.out.println(" 8. Key Value Set");
		
		System.out.println("### End converting key profile model ###");
		
		return model;
	}	
	
	public static String[] createKeyAttrs(KeyProfileModel model) throws ParseException {
		List<String> attrs = getKeyAttrs(model);
				
		return attrs.toArray(new String[attrs.size()]);
	}
	
	public static String[] createPriKeyAttrs(KeyProfileModel model) throws ParseException {
		List<String> attrs = getPriKeyAttrs(model);
				
		return attrs.toArray(new String[attrs.size()]);
	}
	
	public static String[] createKeyAttrs(KeyProfileModel model, KeyValueDTO dto) throws ParseException {
		List<String> attrs = getKeyAttrs(model);		
		
		/*
		 * Private/Public Key Common Attributes
		 */
		attrs.add("SUBJECT=" + model.getKeySubject());
		attrs.add("MODULUS=" + dto.getMod());
		attrs.add("PUBLIC_EXPONENT=" + dto.getExp());
		attrs.add("START_DATE="+model.getEffectiveStartDateStr().replaceAll("-", ""));
		attrs.add("END_DATE="+model.getEffectiveEndDateStr().replaceAll("-", ""));
		
		/*
		 * Private Key Specific Attributes
		 */
		if(model.getKeyTypeCode().equals("PRIVATE")){
			attrs.add("PRIME_1=" + dto.getCrtp());
			attrs.add("PRIME_2=" + dto.getCrtq());
			attrs.add("EXPONENT_1=" + dto.getCrtdp1());
			attrs.add("EXPONENT_2=" + dto.getCrtdq1());
			attrs.add("COEFFICIENT=" + dto.getCrtpq1());
			attrs.add("PRIVATE_EXPONENT=" + dto.getModExp());
		}
		
		return attrs.toArray(new String[attrs.size()]);
	}
	
	private static List<String> getKeyAttrs(KeyProfileModel model) throws ParseException{
		List<String> attrs = new ArrayList<String>();

		attrs.add("TOKEN=true");
		attrs.add("LABEL="+model.getKeyLabel());
		attrs.add("VALUE_LEN="+model.getKeySize()/8);
		attrs.add("START_DATE="+model.getEffectiveStartDateStr().replaceAll("-", ""));
		attrs.add("END_DATE="+model.getEffectiveEndDateStr().replaceAll("-", ""));
		
		//add by shifei 2014-07-14
		attrs.add("CLASS="+model.getKeyTypeCode());	
//		attrs.add("KEY_TYPE="+model.getKeySubtypeCode());	
		
		String tempKeyCode;
		
		tempKeyCode = model.getKeySubtypeCode();
		
		if(model.getKeySubtypeCode().equalsIgnoreCase("DES") && model.getKeySize() == 192){
			tempKeyCode = "DES3";
		}else if(model.getKeySubtypeCode().equalsIgnoreCase("DES") && model.getKeySize() == 128){
			tempKeyCode = "DES2";
		}
		
//				attrs.add("KEY_TYPE="+model.getKeySubtypeCode());	
		attrs.add("KEY_TYPE="+tempKeyCode);
		
		if(model.getKeyValue() != null && !model.getKeyValue().getComponents().isEmpty()){
			if(model.getKeyTypeCode().equals("SECRET")){
				ValueComponent comp = model.getKeyValue().getComponents().get(0);
				
				if(comp.getValue() != null && !comp.getValue().equals(""))
				   attrs.add("VALUE=" +  comp.getValue());
				
			}else{
				for(ValueComponent comp : model.getKeyValue().getComponents()){
					String name = comp.getName();
					if("EXPONENT".equals(name)){
						attrs.add("PUBLIC_EXPONENT=" +  comp.getValue());
					}else if("MODULUS".equals(name)){
						attrs.add("MODULUS=" +  comp.getValue());
					}else if("CRT_P".equals(name)){
						attrs.add("PRIME_1=" +  comp.getValue());
					}else if("CRT_Q".equals(name)){
						attrs.add("PRIME_2=" +  comp.getValue());
					}else if("CRT_DP1".equals(name)){
						attrs.add("EXPONENT_1=" +  comp.getValue());
					}else if("CRT_DQ1".equals(name)){
						attrs.add("EXPONENT_2=" +  comp.getValue());
					}else if("CRT_PQ1".equals(name)){
						attrs.add("COEFFICIENT=" +  comp.getValue());
					}else if("CRT_EXP".equals(name)){
						attrs.add("PRIVATE_EXPONENT=" +  comp.getValue());
					}
				}
			}
		}
		
		KeyValueAttrVO vo = model.getKeyValueAttribute();
		
		attrs.add("EXPORTABLE=" + (vo.isExportable?"true":"false"));
		attrs.add("IMPORT=" + (vo.isImportable?"true":"false"));
		attrs.add("SENSITIVE=" + (vo.isSensitive?"true":"false"));
		attrs.add("ENCRYPT=" + (vo.isEncrypt?"true":"false"));
		attrs.add("DECRYPT=" + (vo.isDecrypt?"true":"false"));
		attrs.add("WRAP=" + (vo.isWrap?"true":"false"));
		attrs.add("UNWRAP=" + (vo.isUnwrap?"true":"false"));
		attrs.add("SIGN=" + (vo.isSign?"true":"false"));
		attrs.add("VERIFY=" + (vo.isVerify?"true":"false"));
		attrs.add("DERIVE=" + (vo.isDerive?"true":"false"));
		
		return attrs;
	}
	
	
	private static List<String> getPriKeyAttrs(KeyProfileModel model) throws ParseException{
		List<String> attrs = new ArrayList<String>();

		attrs.add("TOKEN=true");
		attrs.add("LABEL="+model.getKeyLabel());
		attrs.add("VALUE_LEN="+model.getKeySize()/8);
		attrs.add("START_DATE="+model.getEffectiveStartDateStr().replaceAll("-", ""));
		attrs.add("END_DATE="+model.getEffectiveEndDateStr().replaceAll("-", ""));
		
		//add by shifei 2014-07-14
		attrs.add("CLASS="+model.getKeyTypeCode());	
		
		String tempKeyCode;
		
		tempKeyCode = model.getKeySubtypeCode();
		
		if(model.getKeySubtypeCode().equalsIgnoreCase("DES") && model.getKeySize() == 192){
			tempKeyCode = "DES3";
		}else if(model.getKeySubtypeCode().equalsIgnoreCase("DES") && model.getKeySize() == 128){
			tempKeyCode = "DES2";
		}
		
//		attrs.add("KEY_TYPE="+model.getKeySubtypeCode());	
		attrs.add("KEY_TYPE="+tempKeyCode);
		
		if(model.getKeyValue() != null && !model.getKeyValue().getComponents().isEmpty()){
			if(model.getKeyTypeCode().equals("SECRET")){
				ValueComponent comp = model.getKeyValue().getComponents().get(0);
				
				if(comp.getValue() != null && !comp.getValue().equals(""))
				   attrs.add("VALUE=" +  comp.getValue());
			}else{
				for(ValueComponent comp : model.getKeyValue().getComponents()){
					String name = comp.getName();
					
					attrs.add("PUBLIC_EXPONENT=" +  "03");
				
					if("MODULUS".equals(name)){
						attrs.add("MODULUS=" +  comp.getValue());
					}else if("CRT_P".equals(name)){
						attrs.add("PRIME_1=" +  comp.getValue());
					}else if("CRT_Q".equals(name)){
						attrs.add("PRIME_2=" +  comp.getValue());
					}else if("CRT_DP1".equals(name)){
						attrs.add("EXPONENT_1=" +  comp.getValue());
					}else if("CRT_DQ1".equals(name)){
						attrs.add("EXPONENT_2=" +  comp.getValue());
					}else if("CRT_PQ1".equals(name)){
						attrs.add("COEFFICIENT=" +  comp.getValue());
					}else if("EXPONENT".equals(name)){
						attrs.add("PRIVATE_EXPONENT=" +  comp.getValue());
					}
				}
			}
		}
		
		KeyValueAttrVO vo = model.getKeyValueAttribute();
		
		attrs.add("EXPORTABLE=" + (vo.isExportable?"true":"false"));
		attrs.add("IMPORT=" + (vo.isImportable?"true":"false"));
		attrs.add("SENSITIVE=" + (vo.isSensitive?"true":"false"));
		attrs.add("ENCRYPT=" + (vo.isEncrypt?"true":"false"));
		attrs.add("DECRYPT=" + (vo.isDecrypt?"true":"false"));
		attrs.add("WRAP=" + (vo.isWrap?"true":"false"));
		attrs.add("UNWRAP=" + (vo.isUnwrap?"true":"false"));
		attrs.add("SIGN=" + (vo.isSign?"true":"false"));
		attrs.add("VERIFY=" + (vo.isVerify?"true":"false"));
		attrs.add("DERIVE=" + (vo.isDerive?"true":"false"));
		
		return attrs;
	}

	

	public static boolean compare(KeyProfileModel newModel,
			KeyProfileModel oldModel) {
		
		newModel.setKeySubject(oldModel.getKeySubject());
		newModel.setRegistrationDate(oldModel.getRegistrationDate());
		newModel.setRegistrationUserID(oldModel.getRegistrationUserID());
		
		/**
		 * owner/companyID/testmode/description/keyProfileName만 변경시 Version 변경 없음.
		 */
		boolean isVersionUp = newModel.getKeyProfileVersion().equals(oldModel.getKeyProfileVersion()) ? false : true;
		
		/*
		if(!newModel.getKeySubtypeCode().equals(oldModel.getKeySubtypeCode())) return true; 
		
		if(!newModel.getKeyVersion().equals(oldModel.getKeyVersion())) return true;
		
		if(!newModel.getKeyIdentifier().equals(oldModel.getKeyIdentifier())) return true;
		
		if(!newModel.getKeyRoleCode().equals(oldModel.getKeyRoleCode())) return true;
		
		if(newModel.getKeyIndex() != oldModel.getKeyIndex()) return true;
		
		if(!newModel.getKeyLabel().equals(oldModel.getKeyLabel())) return true;
		
		if(newModel.getKeySize() != oldModel.getKeySize()) return true;
		
		if(!newModel.getKeyAlgorithm().equals(oldModel.getKeyAlgorithm())) return true;
		
		if(!newModel.getKeyUsageIndicatorValue().equals(oldModel.getKeyUsageIndicatorValue())) return true;
		
		if(!newModel.getActiveStatusCode().equals(oldModel.getActiveStatusCode())) return true;
		
		if(!newModel.getEffectiveStartDate().equals(oldModel.getEffectiveStartDate())) return true;
		
		if(!newModel.getEffectiveEndDate().equals(oldModel.getEffectiveEndDate())) return true;
		
		if(!newModel.getRevocationDate().equals(oldModel.getRevocationDate())) return true;
		*/
		
		return isVersionUp;
	}
	
	
	public static void setNewKeyProfileVersion(KeyProfileModel model){
		
		KeyProfileRevisionModel rev = new KeyProfileRevisionModel();
		
		rev.setKeyProfileID(model.getKeyProfileID());
		rev.setKeyProfileVersion(model.getOldKeyProfileVersion());
		rev.setDescription(model.getRevisionDescription());
		rev.setDigest(null);
		rev.setRegistrationDate(model.getUpdateDate());
		rev.setRegistrationUserID(model.getUpdateUserID());
		rev.setUpdateDate(model.getUpdateDate());
		rev.setUpdateUserID(model.getUpdateUserID());
		
		List<KeyProfileRevisionModel> revs = new ArrayList<KeyProfileRevisionModel>();
		revs.add(rev);
		
		model.setRevisionHistory(revs);
		
		/*
		char vers[] = model.getKeyProfileVersion().toCharArray();
		
		char newVers[] = new char[5];
		
		int errataVer;
				
		if(vers[4] == '0'){
			errataVer = 1;
			newVers[0] = vers[0];
			newVers[1] = '.';
			newVers[2] = vers[2];
			newVers[3] = '.';
			newVers[4] = '1';
		}else{
			errataVer = 0;
			if(vers[2] == '0'){
				newVers[0] = vers[0];
				newVers[1] = '.';
				newVers[2] = '1';
				newVers[3] = '.';
				newVers[4] = '0';
			}else{
				newVers[0] = String.valueOf(Integer.valueOf(String.valueOf(vers[0])) + 1).charAt(0);
				newVers[1] = '.';
				newVers[2] = '0';
				newVers[3] = '.';
				newVers[4] = '0';
			}
		}
		
		model.setKeyProfileVersion(String.valueOf(newVers));
		model.setKeyProfileErrataVersion(errataVer);	
		*/	
	}
	
	public static void setKeyProfileSavePath(String savePath, KeyProfileModel model){
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		
		model.setKeyProfileXMLPath(savePath+"/"+model.getCompanyID()+"/"+model.getKeyProfileID()+"_"+model.getKeyProfileVersion()+"_"+model.getKeyLabel()+".xml");		
	}
	
	public static void createSensitiveKeyValueModel(KeyProfileModel expModel, KeyProfileModel tranModel, String wrappedValue){
		TransportKeyModel tranKey = new TransportKeyModel();
		
		tranKey.setKeyID(tranModel.getKeyProfileID());
		tranKey.setKeyName(tranModel.getKeyLabel());
		tranKey.setKeyVersion(tranModel.getKeyProfileVersion());
		
		if(tranModel.getKeyOwner() == null){
			tranKey.setOwner(tranModel.getCompanyID());
		}else{
			tranKey.setOwner(tranModel.getKeyOwner());
		}
		
		tranKey.setAlgorithm(tranModel.getKeyAlgorithm());
		tranKey.setAlgorithmParameters(null);
		
		expModel.setTransportKey(tranKey);		
		
		KeyValueModel valueModel = new KeyValueModel();		
		
		valueModel.setFormatCode("DES");
		
		List<ValueComponent> values = new ArrayList<ValueComponent>();
		
		KeyValueModel.ValueComponent compModel = valueModel.new ValueComponent();
		
		compModel.setEncoding("HEX");
		compModel.setValue(wrappedValue);
		
		values.add(compModel);
		
		valueModel.setComponents(values);
		
		expModel.setKeyValue(valueModel);
	}
	
	public static void createSensitivePriKeyValueModel(KeyProfileModel expModel, KeyProfileModel tranModel, String wrappedValue){
		TransportKeyModel tranKey = new TransportKeyModel();
		
		tranKey.setKeyID(tranModel.getKeyProfileID());
		tranKey.setKeyName(tranModel.getKeyLabel());
		tranKey.setKeyVersion(tranModel.getKeyProfileVersion());
		
		if(tranModel.getKeyOwner() == null){
			tranKey.setOwner(tranModel.getCompanyID());
		}else{
			tranKey.setOwner(tranModel.getKeyOwner());
		}
		
		tranKey.setAlgorithm(tranModel.getKeyAlgorithm());
		tranKey.setAlgorithmParameters(null);
		
		expModel.setTransportKey(tranKey);		
		
		KeyValueModel valueModel = new KeyValueModel();		
		valueModel.setFormatCode("MOD_EXP");
		List<ValueComponent> values = new ArrayList<ValueComponent>();
		KeyValueModel.ValueComponent compModel = valueModel.new ValueComponent();
		compModel.setEncoding("HEX");
		compModel.setName("MODULUS");
		String modulus = null;
		try{
			String[] attrs0 = KMSCrypto.getInstance().getKeyAttributesModulus(expModel.getTokenLabel(), expModel.getKeyLabel());
//			ConvertUtil.setPairKeyValueModulus(expModel, attrs0);	
			for(String attr : attrs0){
				StringUtils.trimWhitespace(attr);
				String[] values1 = attr.split("=");
				
				if("modulus".toUpperCase().contains(values1[0].trim())){
					modulus = values1[1].trim();			
				}
			}
		}catch(KmsException e){
//			throw new KmsException(KMSCode.KEY_VALUE_CREATE_FAIL);
			System.out.println("getKeyAttributes fails");
		}
		compModel.setValue(modulus);
		values.add(compModel);
		
//		String tempStr = wrappedValue;
//		
//		int k = wrappedValue.lastIndexOf("0200");
//		while (k != -1){
//			tempStr = tempStr.substring(0, tempStr.length()-4);
//			k = tempStr.lastIndexOf("0200");
//		}
//		
//		String encryptedPriExp = ""; 
//		encryptedPriExp = tempStr.substring(tempStr.length()-modulus.length(), modulus.length());
		
		
		compModel = valueModel.new ValueComponent();		
		compModel.setEncoding("HEX");
		compModel.setName("EXPONENT");
		compModel.setValue(wrappedValue);
		values.add(compModel);
		
		
		valueModel.setComponents(values);
		
		expModel.setKeyValue(valueModel);
	}
	
	public static void createSensitivePubKeyValueModel(KeyProfileModel expModel){	
		
		KeyValueModel valueModel = new KeyValueModel();		
		valueModel.setFormatCode("MOD_EXP");
		List<ValueComponent> values = new ArrayList<ValueComponent>();
		KeyValueModel.ValueComponent compModel = valueModel.new ValueComponent();
		compModel.setEncoding("HEX");
		compModel.setName("MODULUS");
		String modulus = null;
		String publicExponent = null;
		try{
			String[] attrs0 = KMSCrypto.getInstance().getKeyAttributesModulus(expModel.getTokenLabel(), expModel.getKeyLabel());
//			ConvertUtil.setPairKeyValueModulus(expModel, attrs0);	
			for(String attr : attrs0){
				StringUtils.trimWhitespace(attr);
				String[] values1 = attr.split("=");
				
				if("modulus".toUpperCase().contains(values1[0].trim())){
					modulus = values1[1].trim();			
				}else if("public_exponent".toUpperCase().equals(values1[0].trim())){
					publicExponent = values1[1].trim();
				}
			}
		}catch(KmsException e){
//			throw new KmsException(KMSCode.KEY_VALUE_CREATE_FAIL);
			System.out.println("getKeyAttributes fails");
		}
		compModel.setValue(modulus);
		values.add(compModel);
		
		compModel = valueModel.new ValueComponent();
		compModel.setEncoding("HEX");
		compModel.setName("EXPONENT");
		compModel.setValue(publicExponent);	
		values.add(compModel);
		
		
		valueModel.setComponents(values);
		
		expModel.setKeyValue(valueModel);
	}

	public static void createKeyValueModel(KeyProfileModel expModel,
			KeyProfileModel tranModel, String[] attrs) {
		
		if(tranModel != null){
			TransportKeyModel tranKey = new TransportKeyModel();
			
			tranKey.setKeyID(tranModel.getKeyProfileID());
			tranKey.setKeyName(tranModel.getKeyLabel());
			tranKey.setKeyVersion(tranModel.getKeyProfileVersion());
			
			if(tranModel.getKeyOwner() == null){
				tranKey.setOwner(tranModel.getCompanyID());
			}else{
				tranKey.setOwner(tranModel.getKeyOwner());
			}
			
			tranKey.setAlgorithm(tranModel.getKeyAlgorithm());
			tranKey.setAlgorithmParameters(null);
			
			expModel.setTransportKey(tranKey);
		}
		
		
		if(expModel.getKeyTypeCode().equals("SECRET")){
			setSecretKeyValue(expModel, attrs);
		}else{
			//modify by shifei 2014-07-14
			setPairKeyValue(expModel, attrs);
//			setSecretKeyValue(expModel, attrs);
		}
				
	}
	
	public static void setSecretKeyValue(KeyProfileModel model, String[] attrs) {
		
		String keyValue = null;
		for(String attr : attrs){
			StringUtils.trimWhitespace(attr);
			String[] values = attr.split("=");
			if("VALUE".equals(values[0].trim())){
				keyValue = values[1].trim();
				break;
			}
		}
		
		KeyValueModel valueModel = new KeyValueModel();
		
		if(model.getKeySubtypeCode().equals("DES")){
			valueModel.setFormatCode("DES");
		}
		
		List<ValueComponent> values = new ArrayList<ValueComponent>();
		
		KeyValueModel.ValueComponent compModel = valueModel.new ValueComponent();
		
		compModel.setEncoding("HEX");
		compModel.setValue(keyValue);
		
		values.add(compModel);
		
		valueModel.setComponents(values);
		
		model.setKeyValue(valueModel);		
	}
	
	public static void  setPairKeyValue(KeyProfileModel model, String[] attrs) {
		
		String modulus = null;
		String modulusBits = null;
		String publicExponent = null;
		String privateExponent = null;
		String prime1 = null;
		String prime2 = null;
		String exponent1 = null;
		String exponent2 = null;
		String coefficient = null;
		
		for(String attr : attrs){
			StringUtils.trimWhitespace(attr);
			String[] values = attr.split("=");
			
			if("modulus".toUpperCase().contains(values[0].trim())){
				modulus = values[1].trim();			
			}else if("modulus_bits".toUpperCase().equals(values[0].trim())){
				modulusBits = values[1].trim();
			}else if("public_exponent".toUpperCase().equals(values[0].trim())){
				publicExponent = values[1].trim();
			}else if("private_exponent".toUpperCase().equals(values[0].trim())){
				privateExponent = values[1].trim();
			}else if("prime_1".toUpperCase().equals(values[0].trim())){
				prime1 = values[1].trim();
			}else if("prime_2".toUpperCase().equals(values[0].trim())){
				prime2 = values[1].trim();
			}else if("exponent_1".toUpperCase().equals(values[0].trim())){
				exponent1 = values[1].trim();
			}else if("exponent_2".toUpperCase().equals(values[0].trim())){
				exponent2 = values[1].trim();
			}else if("coefficient".toUpperCase().equals(values[0])){
				coefficient = values[1].trim();
			}
		}
		
		if(model.getKeyTypeCode().equals("PUBLIC")){
			KeyValueModel valueModel = new KeyValueModel();
						
			valueModel.setFormatCode("MOD_EXP");			
			
			List<ValueComponent> values = new ArrayList<ValueComponent>();
			
			// 2. Set Public Modulus
			KeyValueModel.ValueComponent compModel = valueModel.new ValueComponent();
			compModel.setName("MODULUS");
			compModel.setEncoding("HEX");
			compModel.setValue(modulus);

			//add by shifei 2014-07-14
			values.add(compModel);
			
			// 1. Set Public Exponent
			compModel = valueModel.new ValueComponent();
			compModel.setName("EXPONENT");
			compModel.setEncoding("HEX");
			compModel.setValue(publicExponent);
			
			values.add(compModel);
			
			valueModel.setComponents(values);
			
			model.setKeyValue(valueModel);
			
		}else{
			KeyValueModel valueModel = new KeyValueModel();
			
			valueModel.setFormatCode("CRT");			
			
			List<ValueComponent> values = new ArrayList<ValueComponent>();
			
			// 1. Set Private CRT_P
			KeyValueModel.ValueComponent compModel = valueModel.new ValueComponent();
			compModel.setName("CRT_P");
			compModel.setEncoding("HEX");
			compModel.setValue(prime1);
			
			values.add(compModel);
			
			// 2. Set Private CRT_Q
			compModel = valueModel.new ValueComponent();
			compModel.setName("CRT_Q");
			compModel.setEncoding("HEX");
			compModel.setValue(prime2);
			
			values.add(compModel);
			
			// 3. Set Private CRT_DP1
			compModel = valueModel.new ValueComponent();
			compModel.setName("CRT_DP1");
			compModel.setEncoding("HEX");
			compModel.setValue(exponent1);
			
			// 4. Set Private CRT_DQ1
			compModel = valueModel.new ValueComponent();
			compModel.setName("CRT_DQ1");
			compModel.setEncoding("HEX");
			compModel.setValue(exponent2);
			
			// 5. Set Private CRT_PQ
			compModel = valueModel.new ValueComponent();
			compModel.setName("CRT_PQ");
			compModel.setEncoding("HEX");
			compModel.setValue(coefficient);
			
			// 6. Set Private CRT_PQ
			compModel = valueModel.new ValueComponent();
			compModel.setName("CRT_EXP");
			compModel.setEncoding("HEX");
			compModel.setValue(privateExponent);
						
			values.add(compModel);
			
			valueModel.setComponents(values);
			
			model.setKeyValue(valueModel);
		}
	}
	
	public static void  setPairKeyValueModulus(KeyProfileModel model, String[] attrs) {
		
		String modulus = null;
		
		for(String attr : attrs){
			StringUtils.trimWhitespace(attr);
			String[] values = attr.split("=");
			
			if("modulus".toUpperCase().contains(values[0].trim())){
				modulus = values[1].trim();			
			}
		}
		
			KeyValueModel valueModel = new KeyValueModel();
						
			valueModel.setFormatCode("MOD_EXP");			
			
			List<ValueComponent> values = new ArrayList<ValueComponent>();
	
			KeyValueModel.ValueComponent compModel = valueModel.new ValueComponent();
			
			// 2. Set Public Modulus
			compModel = valueModel.new ValueComponent();
			compModel.setName("MODULUS");
			compModel.setEncoding("HEX");
			compModel.setValue(modulus);
			
			valueModel.setComponents(values);
			
			model.setKeyValue(valueModel);

	}
	
	public static KeyProfileJobModel createKeyProfileJob(KeyProfileModel model) {
		KeyProfileJobModel job = new KeyProfileJobModel();
		
		job.setKeyProfileID(model.getKeyProfileID());
		job.setKeyProfileVersion(model.getKeyProfileVersion());
		job.setKeyTypeCode(model.getKeyTypeCode());
		job.setKeySubject(model.getKeySubject());
		job.setRegistrationUserID(model.getRegistrationUserID());
		job.setRegistrationDate(model.getRegistrationDate());
		job.setUpdateUserID(model.getUpdateUserID());
		job.setUpdateDate(model.getUpdateDate());
		
		return job;
	}
	
	public static CertJobModel createCertJob(CertificateModel model, String workcode){
		CertJobModel job = new CertJobModel();
		
		job.setCertificateUID(model.getCertificateUID());
		job.setCertificateName(model.getCertificateName());
		job.setWorkcode(workcode);
		
		job.setRegistrationUserID(model.getUpdateUserID());
		job.setRegistrationDate(model.getUpdateDate());
		job.setUpdateUserID(model.getUpdateUserID());
		job.setUpdateDate(model.getUpdateDate());
		
		return job;
	}
	
}

package com.kona.kms.crypto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.security.pkcs11.wrapper.PKCS11Constants;

import com.kona.kms.KMSCode;
import com.kona.kms.crypto.Key;
import com.kona.kms.KmsException;
import com.kona.kms.utils.Util;


public class KeyAttrMgmt {

	private static final Logger logger = LoggerFactory.getLogger(KeyAttrMgmt.class);

	static KeyAttrMgmt m_instance;

	public static KeyAttrMgmt getInstance() {
		logger.debug("KeyAttrMgmt::getInstance: invoked....");
		if (m_instance == null) {
			m_instance = new KeyAttrMgmt();
		}
		return m_instance;
	}

	private KeyAttrMgmt() {
		logger.debug("KeyAttrMgmt::KeyAttrMgmt: invoked....");
	}

	ConcurrentHashMap<String, String> usageDefs;
	public void setUsageDefs(ConcurrentHashMap<String,String> defs) {
// 	ApplicationContext-Services.xml 의 id	
//	<bean id="keyAttrMgmt" class="com.kona.kms.crypto.KeyAttrMgmt" factory-method="getInstance">
//  map으로 정의 되어 있음
		usageDefs = defs;
	}
	
//	public KeyAttributes parseUsageParams(String[] params) throws KmsException {
//		String[] keyval;
//		String key, val, type;
//		KeyAttributes attrs = new KeyAttributes();
//		
//		for (int i=0; i<params.length; i++) {
//			
//			keyval = params[i].split("=",2);
//			
//			key=keyval[0].trim();
//
////type은 데이터의 type을 정의한다. 
//			type=usageDefs.get(key);
//			
//			if (keyval.length == 2 && type != null) {
//				val=keyval[1].trim();
//
//				if (type.equalsIgnoreCase("boolean") == true) 
//				{
//					if (val.equalsIgnoreCase("true") == true || val.equalsIgnoreCase("false") == true) 
//					{
//						if (key.equalsIgnoreCase("PRIVATE")) {
//							attrs.cka_private = Boolean.valueOf(val);
//						}else if (key.equalsIgnoreCase("TOKEN")) {
//							attrs.cka_token = Boolean.valueOf(val);
//						}else if (key.equalsIgnoreCase("SENSITIVE")) {
//							attrs.cka_sensitive = Boolean.valueOf(val);
//						}
//						
//						// add by shifei 2014-04-14
//						 else if (key.equalsIgnoreCase("EXPORTABLE")) {
//							attrs.cka_exportable = Boolean.valueOf(val);
//						} else if (key.equalsIgnoreCase("IMPORT")) {
//							attrs.cka_importable = Boolean.valueOf(val);
//						}
//						
//						else if (key.equalsIgnoreCase("EXTRACTABLE")) {
//							attrs.cka_extractable = Boolean.valueOf(val);
//						}else if (key.equalsIgnoreCase("ENCRYPT")) {
//							attrs.cka_encrypt = Boolean.valueOf(val);
//						}else if (key.equalsIgnoreCase("DECRYPT")) {
//							attrs.cka_decrypt = Boolean.valueOf(val);
//						}else if (key.equalsIgnoreCase("WRAP")) {
//							attrs.cka_wrap = Boolean.valueOf(val);
//						}else if (key.equalsIgnoreCase("UNWRAP")) {
//							attrs.cka_unwrap = Boolean.valueOf(val);
//						}else if (key.equalsIgnoreCase("SIGN")) {
//							attrs.cka_sign = Boolean.valueOf(val);
//						}else if (key.equalsIgnoreCase("VERIFY")) {
//							attrs.cka_verify = Boolean.valueOf(val);
//						}else if (key.equalsIgnoreCase("SIGNRECOVER")) {
//							attrs.cka_signRecover = Boolean.valueOf(val);
//						}else if (key.equalsIgnoreCase("VERIFYRECOVER")) {
//							attrs.cka_verifyRecover = Boolean.valueOf(val);
//						}else if (key.equalsIgnoreCase("DERIVE")) {
//							attrs.cka_derive = Boolean.valueOf(val);
//						}else {
//							throw new KmsException(KMSCode.KR_ATTRIBUTE_INVALID);
//						}
//					}
//				}else if (type.equalsIgnoreCase("string") == true)
//				{
//					if (key.equalsIgnoreCase("LABEL")) {
//						attrs.cka_label = val.getBytes();
//					}else if (key.equalsIgnoreCase("SUBJECT")) {
//						attrs.cka_subject = val.getBytes();
//					}else if (key.equalsIgnoreCase("START_DATE")) {
//						attrs.cka_startDate = val.getBytes();
//					}else if (key.equalsIgnoreCase("END_DATE")) {
//						attrs.cka_endDate = val.getBytes();
//					}else {
//						throw new KmsException(KMSCode.KR_ATTRIBUTE_INVALID);
//					}
//				}else if (type.equalsIgnoreCase("hexstring") == true) {
//					if (key.equalsIgnoreCase("SEED")) {
//						attrs.cka_seed = Util.hexStringToByteArray(val);
//					}else if (key.equalsIgnoreCase("VALUE")) {
//						attrs.cka_value = Util.hexStringToByteArray(val);
//					}else if (key.equalsIgnoreCase("MODULUS")) {
//						attrs.cka_modulus = Util.hexStringToByteArray(val);
//					}else if (key.equalsIgnoreCase("PUBLIC_EXPONENT")) {
//						attrs.cka_public_exponent = Util.hexStringToByteArray(val);
//					}else if (key.equalsIgnoreCase("PRIVATE_EXPONENT")) {
//						attrs.cka_private_exponent = Util.hexStringToByteArray(val);
//					}else if (key.equalsIgnoreCase("PRIME_1")) {
//						attrs.cka_prime_1 = Util.hexStringToByteArray(val);
//					}else if (key.equalsIgnoreCase("PRIME_2")) {
//						attrs.cka_prime_2 = Util.hexStringToByteArray(val);
//					}else if (key.equalsIgnoreCase("EXPONENT_1")) {
//						attrs.cka_exponent_1 = Util.hexStringToByteArray(val);
//					}else if (key.equalsIgnoreCase("EXPONENT_2")) {
//						attrs.cka_exponent_2 = Util.hexStringToByteArray(val);
//					}else if (key.equalsIgnoreCase("COEFFICIENT")) {
//						attrs.cka_coefficient = Util.hexStringToByteArray(val);
//					}else {
//						throw new KmsException(KMSCode.KR_ATTRIBUTE_INVALID);
//					}
//				}else if (type.equalsIgnoreCase("long") == true || type.equalsIgnoreCase("int") == true) {
//					if (key.equalsIgnoreCase("MODULUS_BITS")) {
//						attrs.cka_modulus_bits = Long.valueOf(val);
//					}else if (key.equalsIgnoreCase("VALUE_LEN")) {
//						attrs.cka_value_len = Long.valueOf(val);
//					}else {
//						throw new KmsException(KMSCode.KR_ATTRIBUTE_INVALID);
//					}
//				}else {
//					throw new KmsException(KMSCode.KR_ATTRIBUTE_INVALID);
//				}
//			}
//			
//			//----------- modified by shifei 20140328---------	
//			else if (keyval.length == 2 && (type = usageDefs.get(key = keyval[0].trim())) == null){
//				val = keyval[1].trim();
//				
//				if (key.equalsIgnoreCase("CLASS")){
//					
//					System.out.println("val: " + val);
//					
//					if (val.equalsIgnoreCase("CKO_PUBLIC_KEY") ){
//						val = String.valueOf(PKCS11Constants.CKO_PUBLIC_KEY);
//					} else if (val.equalsIgnoreCase("CKO_PRIVATE_KEY")){
//						val = String.valueOf(PKCS11Constants.CKO_PRIVATE_KEY);
//					} else{
//						val = String.valueOf(PKCS11Constants.CKO_SECRET_KEY);
//					}
//					attrs.cka_keyClass = Long.valueOf(val);
//				} else if (key.equalsIgnoreCase("KEY_TYPE")){
//					if (val.equalsIgnoreCase("CKK_RSA") ){
//						val = String.valueOf(PKCS11Constants.CKK_RSA);
//					} else if (val.equalsIgnoreCase("CKK_DES") ){
//						val = String.valueOf(PKCS11Constants.CKK_DES);
//					} else{
//						val = String.valueOf(PKCS11Constants.CKK_DES3);
//					}
//					attrs.cka_keyType = Long.valueOf(val);
//				} else{
//					throw new KmsException(KMSCode.KR_ATTRIBUTE_INVALID);
//				}
//			}
//			//---------------------------------------------
//			
//			
//			
//			else {
//				logger.debug("KeyAttrMgmt::parseUsageParams: EXCEPTION KR_ATTRIBUTE_INVALID [{}]", params[i].toString());
//				//throw new KmsException(KMSCode.KR_ATTRIBUTE_INVALID);
//				continue;
//			}
//		}
//		return attrs;
//	}
	
	public KeyAttributes parseUsageParams(String[] params) throws KmsException {
		String[] keyval;
		String key, val, type;
		KeyAttributes attrs = new KeyAttributes();

		for (int i = 0; i < params.length; i++) {
			keyval = params[i].split("=", 2);

			if (keyval.length == 2 && (type = usageDefs.get(key = keyval[0].trim())) != null) {
				val = keyval[1].trim();

				if (type.equalsIgnoreCase("boolean") == true) {
					if (val.equalsIgnoreCase("true") == true || val.equalsIgnoreCase("false") == true) {
						if (key.equalsIgnoreCase("PRIVATE")) {
							attrs.cka_private = Boolean.valueOf(val);
						} else if (key.equalsIgnoreCase("TOKEN")) {
							attrs.cka_token = Boolean.valueOf(val);
						} else if (key.equalsIgnoreCase("SENSITIVE")) {
							attrs.cka_sensitive = Boolean.valueOf(val);
						} 
						
						// add by shifei 2014-04-14
					 else if (key.equalsIgnoreCase("EXPORTABLE")) {
						attrs.cka_exportable = Boolean.valueOf(val);
					} else if (key.equalsIgnoreCase("IMPORT")) {
						attrs.cka_importable = Boolean.valueOf(val);
					}
					
						
						else if (key.equalsIgnoreCase("EXTRACTABLE")) {
							attrs.cka_extractable = Boolean.valueOf(val);
						} else if (key.equalsIgnoreCase("ENCRYPT")) {
							attrs.cka_encrypt = Boolean.valueOf(val);
						} else if (key.equalsIgnoreCase("DECRYPT")) {
							attrs.cka_decrypt = Boolean.valueOf(val);
						} else if (key.equalsIgnoreCase("WRAP")) {
							attrs.cka_wrap = Boolean.valueOf(val);
						} else if (key.equalsIgnoreCase("UNWRAP")) {
							attrs.cka_unwrap = Boolean.valueOf(val);
						} else if (key.equalsIgnoreCase("SIGN")) {
							attrs.cka_sign = Boolean.valueOf(val);
						} else if (key.equalsIgnoreCase("VERIFY")) {
							attrs.cka_verify = Boolean.valueOf(val);
						} else if (key.equalsIgnoreCase("SIGNRECOVER")) {
							attrs.cka_signRecover = Boolean.valueOf(val);
						} else if (key.equalsIgnoreCase("VERIFYRECOVER")) {
							attrs.cka_verifyRecover = Boolean.valueOf(val);
						} else if (key.equalsIgnoreCase("DERIVE")) {
							attrs.cka_derive = Boolean.valueOf(val);
						} else {
							throw new KmsException(KMSCode.KR_ATTRIBUTE_INVALID);
						}
					}
				} else if (type.equalsIgnoreCase("string") == true) {
					if (key.equalsIgnoreCase("LABEL")) {
						attrs.cka_label = val.getBytes();
					} else if (key.equalsIgnoreCase("SUBJECT")) {
						attrs.cka_subject = val.getBytes();
					} else if (key.equalsIgnoreCase("START_DATE")) {
						attrs.cka_startDate = val.getBytes();
					} else if (key.equalsIgnoreCase("END_DATE")) {
						attrs.cka_endDate = val.getBytes();
					} else {
						throw new KmsException(KMSCode.KR_ATTRIBUTE_INVALID);
					}
				} else if (type.equalsIgnoreCase("hexstring") == true) {
					if (key.equalsIgnoreCase("SEED")) {
						attrs.cka_seed = Util.hexStringToByteArray(val);
					} else if (key.equalsIgnoreCase("VALUE")) {
						attrs.cka_value = Util.hexStringToByteArray(val);
					} else if (key.equalsIgnoreCase("MODULUS")) {
						attrs.cka_modulus = Util.hexStringToByteArray(val);
					} else if (key.equalsIgnoreCase("PUBLIC_EXPONENT")) {
						attrs.cka_public_exponent = Util.hexStringToByteArray(val);
					} else if (key.equalsIgnoreCase("PRIVATE_EXPONENT")) {
						attrs.cka_private_exponent = Util.hexStringToByteArray(val);
					} else if (key.equalsIgnoreCase("PRIME_1")) {
						attrs.cka_prime_1 = Util.hexStringToByteArray(val);
					} else if (key.equalsIgnoreCase("PRIME_2")) {
						attrs.cka_prime_2 = Util.hexStringToByteArray(val);
					} else if (key.equalsIgnoreCase("EXPONENT_1")) {
						attrs.cka_exponent_1 = Util.hexStringToByteArray(val);
					} else if (key.equalsIgnoreCase("EXPONENT_2")) {
						attrs.cka_exponent_2 = Util.hexStringToByteArray(val);
					} else if (key.equalsIgnoreCase("COEFFICIENT")) {
						attrs.cka_coefficient = Util.hexStringToByteArray(val);
					} else {
						throw new KmsException(KMSCode.KR_ATTRIBUTE_INVALID);
					}
				} else if (type.equalsIgnoreCase("long") == true || type.equalsIgnoreCase("int") == true) {
					if (key.equalsIgnoreCase("MODULUS_BITS")) {
						attrs.cka_modulus_bits = Long.valueOf(val);
					}
					// ----------- modified by shifei 20140328---------
					else if (key.equalsIgnoreCase("CLASS")) {
//						System.out.println("KeyAttrMgmt:: in CLASS set");
						attrs.cka_keyClass = Long.valueOf(val);
					} else if (key.equalsIgnoreCase("KEY_TYPE")) {
//						System.out.println("KeyAttrMgmt:: in KEY_TYPE set");
						attrs.cka_keyType = Long.valueOf(val);
					}

					// ---------------------------------------------

					else if (key.equalsIgnoreCase("VALUE_LEN")) {
						attrs.cka_value_len = Long.valueOf(val);
					} else {
						throw new KmsException(KMSCode.KR_ATTRIBUTE_INVALID);
					}
				} 
				
				else if (key.equalsIgnoreCase("VALUE_LEN")) {
					attrs.cka_value_len = Long.valueOf(val);
				}
				
				else {
					throw new KmsException(KMSCode.KR_ATTRIBUTE_INVALID);
				}
			}

			// ----------- modified by shifei 20140328---------
			else if (keyval.length == 2 && (type = usageDefs.get(key = keyval[0].trim())) == null) {
				val = keyval[1].trim();

				if (key.equalsIgnoreCase("CLASS")) {

//					System.out.println("val: " + val);

					if (val.equalsIgnoreCase("CKO_PUBLIC_KEY") || val.equalsIgnoreCase("PUBLIC")) {
						val = String.valueOf(PKCS11Constants.CKO_PUBLIC_KEY);
					} else if (val.equalsIgnoreCase("CKO_PRIVATE_KEY") || val.equalsIgnoreCase("PRIVATE")) {
						val = String.valueOf(PKCS11Constants.CKO_PRIVATE_KEY);
					} else {
						val = String.valueOf(PKCS11Constants.CKO_SECRET_KEY);
					}
					attrs.cka_keyClass = Long.valueOf(val);
				} else if (key.equalsIgnoreCase("KEY_TYPE")) {
					if (val.equalsIgnoreCase("CKK_RSA") || val.equalsIgnoreCase("RSA")) {
						val = String.valueOf(PKCS11Constants.CKK_RSA);
					} else if (val.equalsIgnoreCase("CKK_DES") || val.equalsIgnoreCase("DES")) {
						val = String.valueOf(PKCS11Constants.CKK_DES);
					} else if (val.equalsIgnoreCase("CKK_DES2") || val.equalsIgnoreCase("DES2")) {
						val = String.valueOf(PKCS11Constants.CKK_DES2);
					}else {
						val = String.valueOf(PKCS11Constants.CKK_DES3);
					}
					attrs.cka_keyType = Long.valueOf(val);
				} 
				else if (key.equalsIgnoreCase("VALUE_LEN")) {
					attrs.cka_value_len = Long.valueOf(val);
				}
				
				else {
					throw new KmsException(KMSCode.KR_ATTRIBUTE_INVALID);
				}
			}
			// ---------------------------------------------

			else {
				logger.debug("KeyAttrMgmt::parseUsageParams: EXCEPTION KR_ATTRIBUTE_INVALID [{}]", params[i].toString());
				// throw new KmsException(KMSCode.KR_ATTRIBUTE_INVALID);
				continue;
			}
		}
		return attrs;
	}

	public KeyAttributes validateKeyAttribubute(KeyAttributes attrs) {
		/* validation */
		return attrs;
	}

	/* Key -> KeyAttributes */
	public KeyAttributes convKey2KeyAttributes(Key key) {
		KeyAttributes keyAttr = new KeyAttributes();
		
		/* attribute */
		int attribute = key.getAttribute();
		keyAttr.cka_exportable =((attribute & key.EXPORTABLE) != 0) ? true : false;
		keyAttr.cka_importable =((attribute & key.IMPORTABLE) != 0) ? true : false;
		keyAttr.cka_sensitive =((attribute & key.SENSITIVE) != 0) ? true : false;

		/* usage */
		int usage = key.getUsage();
		keyAttr.cka_encrypt =((usage & key.ENCRYPT) != 0) ? true : false;
		keyAttr.cka_decrypt =((usage & key.DECRYPT) != 0) ? true : false;
		keyAttr.cka_decryptEncrypt =((usage & key.DECRYPT_ENCRYPT) != 0) ? true : false;
		keyAttr.cka_unwrapWrap =((usage & key.UNWRAP_WRAP) != 0) ? true : false;
		keyAttr.cka_derive =((usage & key.DERIVE) != 0) ? true : false;
		keyAttr.cka_sign =((usage & key.SIGN) != 0) ? true : false;
		keyAttr.cka_unwrap =((usage & key.UNWRAP) != 0) ? true : false;
		keyAttr.cka_unwrapWrap =((usage & key.UNWRAP_WRAP) != 0) ? true : false;
		keyAttr.cka_verify =((usage & key.VERIFY) != 0) ? true : false;
		keyAttr.cka_wrap =((usage & key.WRAP) != 0) ? true : false;

		/* wrapKey */
		
		keyAttr.cka_startDate = key.getStart();
		keyAttr.cka_endDate = key.getEnd();
		keyAttr.cka_label = key.getKmsLabel().getBytes();
		
		/* owner */

		keyAttr.cka_id = key.getProfileid();

		/* version */
		
		/* kcv <- GetAttributeValue() */
		
		/* key type */
		int keyType = key.getType();
		if (keyType == key.PUBLIC)
			keyAttr.cka_keyClass = PKCS11Constants.CKO_PUBLIC_KEY;
		else if (keyType == key.PRIVATE)
			keyAttr.cka_keyClass = PKCS11Constants.CKO_PRIVATE_KEY;
		else
			keyAttr.cka_keyClass = PKCS11Constants.CKO_SECRET_KEY;
		
		/* size */
		int size = key.getSize();
		/* subType */
		int subType = key.getSubType();
		if (subType == key.DES) {
			if (size == 64)
				keyAttr.cka_keyType = PKCS11Constants.CKK_DES;
			else if (size == 128)
				keyAttr.cka_keyType = PKCS11Constants.CKK_DES2;
			else	/* 192 */
				keyAttr.cka_keyType = PKCS11Constants.CKK_DES3;
			
			keyAttr.cka_value = key.getComponent(key.VALUE);	
		}
		else if (subType == key.RSA) {
			keyAttr.cka_keyType = PKCS11Constants.CKK_RSA;
			keyAttr.cka_modulus = key.getComponent(key.MODULUS);
			if (keyType == key.PUBLIC)
				keyAttr.cka_public_exponent = key.getComponent(key.EXPONENT);
			else
				keyAttr.cka_private_exponent = key.getComponent(key.EXPONENT);
		}
		else if (subType == key.RSACRT) {
			keyAttr.cka_keyType = PKCS11Constants.CKK_RSA;
			keyAttr.cka_exponent_1 = key.getComponent(key.CRT_DP1);
			keyAttr.cka_exponent_2 = key.getComponent(key.CRT_DQ1);
			keyAttr.cka_prime_1 =  key.getComponent(key.CRT_P);
			keyAttr.cka_coefficient =  key.getComponent(key.CRT_PQ);
			keyAttr.cka_prime_2 =  key.getComponent(key.CRT_Q);
		}
		else { /* EC */
			keyAttr.cka_keyType = PKCS11Constants.CKK_EC;
		}
		
		return keyAttr;
	}
	
	/* KeyAttributes -> Key  */
	public Key convKeyAttributes2Key(KeyAttributes keyAttr) {
		Key key = new Key();

		//ATTRIBUTES
		int attribute = (keyAttr.cka_exportable ? key.EXPORTABLE : 0x00000000) | 
						(keyAttr.cka_importable ? key.IMPORTABLE : 0x00000000) | 
						(keyAttr.cka_sensitive ? key.SENSITIVE : 0x00000000) ;
		key.setAttribute(attribute);
		
		//USAGE
		int usage = (keyAttr.cka_encrypt ? key.ENCRYPT : 0x00000000) | 
					(keyAttr.cka_decrypt ? key.DECRYPT : 0x00000000) | 
					(keyAttr.cka_decryptEncrypt ? key.DECRYPT_ENCRYPT : 0x00000000) | 			
					(keyAttr.cka_derive ? key.DERIVE : 0x00000000) | 
					(keyAttr.cka_sign ? key.SIGN : 0x00000000) | 
					(keyAttr.cka_unwrap ? key.UNWRAP : 0x00000000) | 
					(keyAttr.cka_unwrapWrap ? key.UNWRAP_WRAP : 0x00000000) | 
					(keyAttr.cka_verify ? key.VERIFY : 0x00000000) | 
					(keyAttr.cka_wrap ? key.WRAP : 0x00000000);
		key.setUsage(usage);
		
		// Size?
		int size = 0;
		
		// wrapkey?
		
		key.setStart(keyAttr.cka_startDate);
		key.setEnd(keyAttr.cka_endDate);
		key.setKmsLabel(Util.byteArray2hexString(keyAttr.cka_label));
		
		// owner ?
		
		key.setProfileid(keyAttr.cka_id);
		
		// version ?
		
		// kcv ?
		
		//KEY TYPE
		if(keyAttr.cka_keyClass == PKCS11Constants.CKO_PUBLIC_KEY)
			key.setType(key.PUBLIC);
		else if(keyAttr.cka_keyClass == PKCS11Constants.CKO_PRIVATE_KEY)
			key.setType(key.PRIVATE);
		else
			key.setType(key.SECRET);
		
		//KEY SUB TYPE
		if(keyAttr.cka_keyType == PKCS11Constants.CKK_DES){
			key.setSubType(key.DES);
			key.setSize(64);
		}
		else if(keyAttr.cka_keyType == PKCS11Constants.CKK_DES2){
			key.setSubType(key.DES);
			key.setSize(128);
		}
		else if(keyAttr.cka_keyType == PKCS11Constants.CKK_DES3){
			key.setSubType(key.DES);
			key.setSize(192);
		}
		else if(keyAttr.cka_keyType == PKCS11Constants.CKK_RSA){
			// RSA 인지 RSACRT 인지 어떻게 알지?
			if(keyAttr.cka_prime_1 == null){
				key.setSubType(key.RSA);
				key.setComponent(key.MODULUS, keyAttr.cka_modulus);
				if(keyAttr.cka_keyClass == PKCS11Constants.CKO_PUBLIC_KEY)
					key.setComponent(key.EXPONENT, keyAttr.cka_public_exponent);
				else 
					key.setComponent(key.EXPONENT, keyAttr.cka_private_exponent);
			} 
			else {
				key.setSubType(key.RSACRT);
				key.setComponent(key.CRT_DP1, keyAttr.cka_exponent_1);
				key.setComponent(key.CRT_DQ1, keyAttr.cka_exponent_2);
				key.setComponent(key.CRT_P, keyAttr.cka_prime_1);
				key.setComponent(key.CRT_PQ, keyAttr.cka_coefficient);
				key.setComponent(key.CRT_Q, keyAttr.cka_prime_2);
			}

			// key size ?
		}
		else{
			key.setSubType(key.EC);
			
			// key size ?
		}
			
		return key;
	}

}

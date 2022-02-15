package com.kona.kms.cao;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kona.kms.utils.Util;

public class BcCryptoUtil {
	private static final Logger logger = LoggerFactory.getLogger(BcCryptoUtil.class);
	
	static BcCryptoUtil m_instance;

	public static BcCryptoUtil getInstance() {
		logger.debug("BcCryptoUtil::getInstance: invoked....");
		if (m_instance == null) {
			m_instance = new BcCryptoUtil();
		}
		return m_instance;
	}

	private BcCryptoUtil() {
		logger.debug("BcCryptoUtil::BcCryptoUtil: invoked....");
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}
	
	public String cptKeyCheckValue(String mech, int keySize, String keyValue) {
		int inputLen = 8;
		if (mech != null && keySize > 32 && (keySize % 32)==0 && (keySize/8)==(keyValue.length()/2)) {
			if (mech.equalsIgnoreCase("DES")==true) {
				if (keySize > 64)
					mech = "DESede";
				else
					mech = "DES";
				inputLen = 8;
			}
			else if (mech.equalsIgnoreCase("AES")==true) {
				mech = "AES";
				inputLen = keySize / 8;
			}
			else {
				mech = "SEED";
				inputLen = keySize / 8;
			}
		}
		else {
			return null;
		}
				
		String kcvHexText = null;
		final byte[] input = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
										0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
										0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
										0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		//byte[] keyBytes = new byte[] { 0x01, 0x23, 0x45, 0x67, (byte)0x89, 0x01, 0x23, 0x45 };
		// byte[] ivBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x00, 0x00, 0x00, 0x01 };
		byte[] keyBytes = Util.hexStringToByteArray(keyValue);
		// odd parity check !
		
		//Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			
		SecretKeySpec key = new SecretKeySpec(keyBytes, mech);
		IvParameterSpec ivSpec = null; //new IvParameterSpec(ivBytes);
		Cipher cipher;
		try {
			//cipher = Cipher.getInstance("DES/CTR/NoPadding", "BC");
			cipher = Cipher.getInstance(mech+"/ECB/NoPadding", "BC");
			
//			System.out.println("input : " + Util.byteArray2hexString(input));
			
			// encryption pass
			cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
			byte[] cipherText = new byte[cipher.getOutputSize(inputLen)];
			int ctLength = cipher.update(input, 0, inputLen, cipherText, 0);
			ctLength += cipher.doFinal(cipherText, ctLength);
//			System.out.println("cipher: " + Util.byteArray2hexString(cipherText) + " bytes: " + ctLength);
			
			kcvHexText = Util.byteArray2hexString(cipherText);
		
			// decryption pass
			//cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
			//byte[] plainText = new byte[cipher.getOutputSize(ctLength)];
			//int ptLength = cipher.update(cipherText, 0, ctLength, plainText, 0);
			//ptLength += cipher.doFinal(plainText, ptLength);
			//System.out.println("plain : " + Util.byteArray2hexString(plainText) + " bytes: " + ptLength);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ShortBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return kcvHexText;
	}
}

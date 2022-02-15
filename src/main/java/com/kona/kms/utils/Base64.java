package com.kona.kms.utils;

import java.io.UnsupportedEncodingException;

/**
 * Implements a simple Base64 encoder and decoder
 * 
 * @author Andreas Schwier (info@cardcontact.de)
 */
public class Base64 {

//	  const static unsigned char Base64Str[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
//
//	  #define bintobase64(x)  (Base64Str[x])
//
	static int base64todec(char c) {
		int v;

		if ((c >= 'A') && (c <= 'Z'))
			v = c - 'A';
		else if ((c >= 'a') && (c <= 'z'))
			v = c - 'a' + 26;
		else if ((c >= '0') && (c <= '9'))
			v = c - '0' + 52;
		else if (c == '/')
			v = 63;
		else if (c == '+')
			v = 62;
		else if (c == '=')
			v = 64;
		else
			v = -1;

		return v;
	}

    
        
	static char dectobase64(int v) {
		char c;

		if (v == 64)
			c = '=';
		else if (v == 63)
			c = '/';
		else if (v == 62)
			c = '+';
		else if ((v >= 52) && (v <= 61))
			c = (char)(v - 52 + '0');
		else if ((v >= 26) && (v <= 51))
			c = (char)(v - 26 + 'a');
		else
			c = (char)(v + 'A');

		return c;
        
	}
    
    

	/**
	 * Convert Base64 encoded string into byte array
	 * 
	 * @param str Base64 String that may contain special characters
	 * @return Byte arrays with decoded data
	 */
	public static byte[] decode(String str) throws UnsupportedEncodingException {

		int v;
		int c = 18;
		int akku = 0;
		int pad = 0;
		UtilByteBuffer bb = new UtilByteBuffer(str.length() / 2);
        
		for (int i = 0; i < str.length(); i++) {
			v = base64todec(str.charAt(i));
            
			if (v >= 0) {
				if (v < 64)
					akku |= v << c;
				else
					pad++;
				c -= 6;
			} else if (c != 18)
				throw new UnsupportedEncodingException();

			if (c < 0) {
				bb.append((byte)((akku >> 16) & 0xFF));
				if (pad != 2) {
					bb.append((byte)((akku >>  8) & 0xFF));
					if (pad != 1)
						bb.append((byte)( akku        & 0xFF));
				}
				c = 18;
				akku = 0;
			}
		}
        
		return bb.getBytes();
	}

    
    
	/**
	 * Convert byte array to Base64 encoded string
	 * 
	 * @param bin Byte array
	 * @return String with encoded data
	 */
	public static String encode(byte[] bin, boolean linebreak) {

		int i = 0;
		int l = bin.length;
		int a0,a1,a2,a3;
        
		StringBuffer sb = new StringBuffer(bin.length);

		while(l > 0) {
			a0 = (bin[i] >> 2) & 0x3F;
			a1 = (bin[i] << 4) & 0x30;
			a2 = 0x40;
			a3 = 0x40;
			l--;
			i++;
            
			if (l > 0) {
				a1 |= (bin[i] >> 4) & 0x0F;
				a2  = (bin[i] << 2) & 0x3C;
				l--;
				i++;
                
				if (l > 0) {
					a2 |= (bin[i] >> 6) & 0x03;
					a3  =  bin[i]       & 0x3F;
					l--;
					i++;
				}
			}
			sb.append(dectobase64(a0));
			sb.append(dectobase64(a1));
			sb.append(dectobase64(a2));
			sb.append(dectobase64(a3));
			if (linebreak && ((i % 48) == 0))
				sb.append('\n');
		}
        
		return sb.toString();
	}
}

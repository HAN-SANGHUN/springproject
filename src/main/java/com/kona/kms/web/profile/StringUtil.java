package com.kona.kms.web.profile;

import java.io.ByteArrayOutputStream;
import java.util.regex.Pattern;
import javax.xml.bind.DatatypeConverter;

import java.util.regex.*;

public class StringUtil {    
    
    public static String toHexString(byte buf[]){
        StringBuffer sb = new StringBuffer();
        if ( buf==null ) return "";

        for (int i = 0; i < buf.length; i++) {
            sb.append(Integer.toHexString(0x0100 + (buf[i] & 0x00FF)).substring(1));
        }
        return sb.toString();
    }
    
    public static String HexToString(byte[] ba) {
    	  StringBuffer sb = new StringBuffer();
    	  int len = ba.length;
    	  for (int i=0; i<len; i++) {
    	   int upper, lower;
    	   upper = (int) (ba[i] >> 4) & 0xF;
    	   lower = (int) (ba[i] & 0xF);
    	   
    	   char uc, lc;
    	   if (upper > 9)
    	    uc = (char) ('A' + upper - 10);
    	   else
    	    uc = (char) ('0' + upper);
    	   if (lower > 9)
    	    lc = (char) ('A' + lower - 10);
    	   else
    	    lc = (char) ('0' + lower);
    	   
    	   sb.append(uc);
    	   sb.append(lc);
    	  }
    	  return sb.toString();
    }

    public static byte[] StringToHexBinary(String str) {
        //return DatatypeConverter.parseHexBinary(str);
    	int len = str.length();
    	//modify by shifei 2014-06-19
//        byte[] data = new byte[len / 2];
//        for (int i = 0; i < len; i += 2) {
//            data[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4)
//                                 + Character.digit(str.charAt(i+1), 16));
//        }
    	byte[] data = null;
    	if(len%2 == 0){
    		data = new byte[len / 2];
            for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4)
                                     + Character.digit(str.charAt(i+1), 16));
            }
    	}else{
    		data = new byte[len];
    		for(int i = 0; i < len-1; i ++){
    			data[i] = (byte) ((Character.digit(str.charAt(i), 16)) );
    		}
    	}
        return data;
    }
    
    public static int Hex2bin(String hexaStr){
        return Integer.parseInt(hexaStr) * 4;
    }

    public static int Hex2Dec(String hexaStr){
        return Integer.parseInt(hexaStr, 16) * 2;
    }

    static boolean onlyWhitespaces(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i)))
                return false;
        }

        return true;
    }

    public static String byteArray2hexString(byte[] array) {
        return byteArray2hexString(array, 0, array.length);
    }

    public static String byteArray2hexString(byte[] array, int offset) {
        return byteArray2hexString(array, offset, array.length - offset);
    }

    public static String byteArray2hexString(byte[] array, int offset, int length) {
        StringBuffer hexString = new StringBuffer();
        for (int i = offset; i < offset+length; i++) {
            String tmp = Integer.toHexString(array[i] & 0xff).toUpperCase();
            if (tmp.length() == 1)
                hexString.append("0" + tmp);
            else
                hexString.append(tmp);
        }

        return hexString.toString();
    }

    public static String byteToHexn(byte b) {
        String n = Integer.toHexString(b & 0xFF).toUpperCase();
        return (n.length() == 1 ? "0" + n : n);
    }

    public static String intToHex(int i) {
        String n = Integer.toHexString(i).toUpperCase();
        return (n.length() % 2 == 1 ? "0" + n : n);
    }

    public static int hexToByte(String hex) {
        return (Integer.parseInt(hex, 16) & 0xFF);
    }

    public static int hexToInt(String hex) {
        return Integer.parseInt(hex, 16);
    }

    public static String byteArray2hexStringNormal(byte[] array) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            String tmp = Integer.toHexString(array[i] & 0xff);
            if (tmp.length() == 1)
                hexString.append("0" + tmp);
            else
                hexString.append(tmp);
        }

        return hexString.toString();
    }

    public static byte[] hexCommand2byteArray(String hexString) {
        if ( hexString == null || hexString.length() < 8 ) {
            return null;
        } else {
            return hexString2byteArray(hexString);
        }
    }

    public static byte[] hexString2byteArray(String hexString) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        Pattern p = Pattern.compile("[^\\s\\da-fA-F]");
        Matcher m = p.matcher(hexString);
        if (m.find())
            return null;

        hexString = hexString.replaceAll("\\s+", "");
        if (hexString.length() % 2 != 0)
            return null;

        for (int i = 0; i < hexString.length(); i += 2) {
            byteStream.write(Integer.parseInt(hexString.substring(i, i + 2), 16));
        }

        return byteStream.toByteArray();
    }

    public static boolean arraycompare(byte[] array1, int offset1, byte[] array2, int offset2, int length) {
        for (int i = 0; i < length; i++) {
            if (array1[offset1 + i] != array2[offset2 + i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Assume the length of two input byte arrays is same.
     * @param a
     * @param b
     * @return
     */
    public static byte[] xor(byte[] a, byte[] b) {
        byte[] result = new byte[a.length];
        for (int i=0; i<a.length; i++) {
            result[i] = (byte)(a[i] ^ b[i]);
        }
        return result;
    }
}
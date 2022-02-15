package com.kona.kms.utils;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Util {
	
	public static int Hex2bin(String hexaStr){
		return Integer.parseInt(hexaStr) * 4;
	}
	
	public static int Hex2Dec(String hexaStr){
        return Integer.parseInt(hexaStr, 16) * 2;
    }
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
    static boolean onlyWhitespaces(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i)))
                return false;
        }
        return true;
    }

    public static String byteArray2hexString(byte[] array) {
    	String s = byteArray2hexString(array, 0, array.length); 
        return s;
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
    
    public static String byteToHex(byte b) {
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
    
    public static String getStackTraceMsg(Exception e) {
    	StackTraceElement[] se = e.getStackTrace();
		String errMsg = e.toString();
		for(int i=0; i<se.length; i++) {
            int lineNum  = se[i].getLineNumber();
            String lineNumber = "";
            if(lineNum==-1)
                lineNumber = "Compiled Code";
            else if(lineNum==-2)
                lineNumber = "Native Code";
            else
                lineNumber = ""+lineNum;

            String stackTraceLine = "\n    at "+ se[i].getClassName() +"."+ se[i].getMethodName() +"("+ se[i].getFileName() +":"+ lineNumber +")";
            errMsg += stackTraceLine;
		    //System.err.println("getClassName() : "+ se[i].getClassName());
		    //System.err.println("getMethodName() : "+ se[i].getMethodName());
		    //System.err.println("getFileName() : "+ se[i].getFileName());
		    //System.err.println("getLineNumber() : "+ se[i].getLineNumber());
		}
        return errMsg;
    }
    
}

/* $Id: Util.java,v 1.1 2011/12/06 00:06:57 shcscms Exp $ */
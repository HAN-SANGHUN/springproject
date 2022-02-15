package com.kona.kms.utils;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.List;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import java.util.regex.*;

public class StringUtil {
    public static StringBuffer globalStr=new StringBuffer();

    public static String lpad(String str, int len, String addStr) {
        String result = str;
        int templen   = len - result.length();
        for (int i = 0; i < templen; i++){
            result = addStr + result;
        }

        return result;
    }

    public static String rpad(String str, int len, String addStr) {
       String result = str;
       int templen   = len - result.length();
       for (int i = 0; i < templen; i++){
            result = result + addStr ;
       }

       return result;
    }

    public static String getCurrentDate(){
        Calendar cal = new GregorianCalendar();

        return String.valueOf(cal.get(Calendar.YEAR)) + lpad(String.valueOf(cal.get(Calendar.MONTH)+1),2,"0") + lpad(String.valueOf(cal.get(Calendar.DATE )),2,"0");
    }

    public static boolean isEmpty(String str) {
        return isNull(str) || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNull(String str) {
        return str == null;
    }

    public static boolean isNotNull(String str) {
        return !isNull(str);
    }

    public static String checkNull(String str) {
        if (str == null) {
            return "";
        } else {
            return str;
        }
    }

    static public String NVL(String str) {
        if (str == null || str.equals("null") || str.length() <= 0)
            return "";
        else
            return str;
    }
    
    static public String NVL(Object str) {
        if (str == null || str == "null" || "null".equals(str) )
            return "";
        else
            return str.toString();
    }

    static public String NVL(String str, String replace_str) {
        if (str == null || str.equals("null") || str.length() <= 0)
            return replace_str;
        else
            return str;
    }

    public static String formatDate(String str, String mask){
        if ( str != null && !str.trim().equals("") ) {
            int strLen = str.length();

            if ( strLen >= 8 ) {
                return str.substring(0, 4) + mask + str.substring(4, 6) + mask + str.substring(6, 8);
            } else if ( strLen >= 6 ) {
                return str.substring(0, 4) + mask + str.substring(4, 6);
            } else if ( strLen >= 4 ) {
                return str.substring(0, 4);
            } else {
                return str.trim();
            }
        } else {
            return "";
        }
    }

    public static String formatTime(String str, String mask){
        if ( str != null && !str.trim().equals("") ) {
            int strLen = str.length();
            if ( strLen >= 6 ) {
                return str.substring(0, 2) + mask + str.substring(2, 4) + mask + str.substring(4, 6);
            } else if ( strLen >= 2 ) {
                return str.substring(0, 2) + mask + str.substring(2, 4);
            } else if ( strLen >= 2 ) {
                return str.substring(0, 2);
            } else {
                return str.trim();
            }
        } else {
            return "";
        }
    }

    public static String[] getStringArray(String str, String strToken) {
        if (str.indexOf(strToken) != -1) {
            StringTokenizer st = new StringTokenizer(str, strToken);
            String[] stringArray = new String[st.countTokens()];

            for ( int i = 0; st.hasMoreTokens(); i++ ) {
                stringArray[i] = st.nextToken();
            }
            return stringArray;
       }

       return new String[] { str };
   }
    
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
        //byte[] helloBytes = str.getBytes();
        //String helloHex   = DatatypeConverter.printHexBinary(helloBytes);

        byte[] decodedHex = DatatypeConverter.parseHexBinary(str);
        //String decStr =new String(decodedHex);

        return decodedHex;
    }

    public static void getTreeDetail(HashMap<String,List<String>> hmapList,String subName){
        // String retStr="";

        List<String> hashList = (List<String>)hmapList.get(subName );

        for(int i=0;i < hashList.size();i++){
            String lastStr =(String) hashList.get(i);

            if(i==0)globalStr.append("\n<ul>");
            String[] arrystr2 = getStringArray(lastStr,"/");
            globalStr.append( "\n <li><a href=\"javascript:fnOnSelect('"+lastStr+"');\">"+arrystr2[arrystr2.length-1]+"</a></li>");
            if(getTreeExist(hmapList,lastStr)> 0 )getTreeDetail(hmapList,lastStr);
            if(i==(hashList.size()-1))globalStr.append("\n</ul>");

        }
    }

    public static int getTreeExist(HashMap<String,List<String>> hmapList,String subName){
        List<String> hashList = (List<String>)hmapList.get(subName );
        return hashList.size();
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

    public static String getParam(  HttpServletRequest request, String paramname ) {
        String rv = request.getParameter( paramname );

        return isEmpty( rv ) ? "" : rv;
    }

    public static String getParam(  HttpServletRequest request, String paramname, String defaultvalue ) {
        String rv = request.getParameter( paramname );

        return isEmpty( rv ) ? defaultvalue : rv;
    }
    
    

	
	public static String getMoneyStr(String str){
		long v = getLong(str,0);
		DecimalFormat df = new DecimalFormat("#,###"); 
		return df.format(v);
	}
	
	/**
	 * get Float String <BR>
	 * @return String
	 */
	public static String getFloatString(String str){
		if(str == null) return null;
		StringBuffer sb = new StringBuffer();
		char [] ca = str.trim().toCharArray();
		boolean dot = false;
		for(int i=0;i<ca.length;i++){
			if( i == 0 && ca[i] == '-' ){
				sb.append(ca[i]);
				continue;
			}
			if(  ca[i] >= '0' && ca[i] <= '9'  ){
				sb.append(ca[i]);
				continue;
			}
			if( dot == false && ca[i] == '.' ){
				sb.append(ca[i]);
				dot = true;
				continue;
			}
		}
		return sb.toString();
	}
	
	/**
	 * get long
	 * @param str
	 * @return long
	 */
	public static long getLong(String str){
		if(str == null) return 0;
		String num = StringUtil.getFloatString(str);
		return Long.parseLong(num);
	}
	
	/**
	 * get long
	 * @param str
	 * @param defaultValue
	 * @return long
	 */
	public static long getLong(String str,long defaultValue){
		if(str == null || str.trim().length() == 0) return defaultValue;
		return getLong(str);
	}
	

	
	/**
	 * get NumString <BR>
	 * @return String
	 * @see getFloatString(String)
	 */
	public static String getNumString(String str){
		if(str == null) return null;
		StringBuffer sb = new StringBuffer();
		char [] ca = str.toCharArray();
		for(int i=0;i<ca.length;i++){
			if(  ca[i] >= '0' && ca[i] <= '9'  ){
				sb.append(ca[i]);
				continue;
			}
		}
		return sb.toString();
	}
	
	//URLEncoder.encode
	public static String URLEncode(String str) throws Exception{
		if(str == null ){
			str = "";
		}
		str = URLEncoder.encode(str, "UTF-8")
			.replaceAll("\\+", "%20")
			.replaceAll("\\%21", "!")
			.replaceAll("\\%28", "(")
			.replaceAll("\\%29", ")")
			.replaceAll("\\%7E", "~")
			.replaceAll("\\%3D", "=")
			.replaceAll("\\%2F", "/")
			.replaceAll("\\%3A", ":")
			.replaceAll("\\%3B", ";")
			.replaceAll("\\%2C", ",")
			.replaceAll("\\%26%2391;", "&#91;")
			.replaceAll("\\%26%2393;", "&#93;");
		return str;
	}
	
	/**
	 * @param hexString 16진수로 구성된 문자열
	 * @return 16진수로 구성된 문자열을 Byte 배열로 반환
	 */
	public static byte[] toByteArray(String hexString){
		byte[] byteArrays = null;
		
		byteArrays = new byte[hexString.length() / 2];
		
		for(int i = 0;i<hexString.length()/2;i++){
			byteArrays[i] = (byte) Integer.parseInt(hexString.substring(i*2, i*2+2),16);
		}
		
		return byteArrays;
	}
	
	public static String arr_join(String []str) {
		String result = "";
        if(str.length == 0){
        	result = "";
        }else if(str.length == 1){
        	result = str[0];
        }else{
        	for(int i=0; i<str.length ; i++){
        		result += (str[i] + ", ");
        	}
        }
        return result;
    }
	
	public static String sha1(String s) {
	    try {
	        MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.update(s.getBytes());
	        byte messageDigest[] = digest.digest();

	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < messageDigest.length; i++)
	            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
	        return hexString.toString();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "";
	}
	
}
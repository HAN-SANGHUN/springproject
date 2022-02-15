package com.kona.kms.utils.tlv;

import com.kona.kms.utils.UtilByteBuffer;

/**
 * Utility class to handle all sorts of hexadecimal string conversions
 * 
 * @author Andreas Schwier (info@cardcontact.de)
 */
public class HexString {
	final static char hexchar[] = { '0', '1', '2', '3', '4', '5', '6', '7',
									'8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

	/**
	 * Convert integer value to hexadecimal byte presentation
	 * 
	 * @param val
	 * 		Value
	 * @return
	 * 		2 digit hexadecimal string
	 */
	public static String hexifyByte(int val) {
		return "" + hexchar[(val >>> 4) & 0x0F] + hexchar[val & 0x0F];
	}



	/**
	 * Convert byte value to hexadecimal byte presentation
	 * 
	 * @param val
	 * 		Value
	 * @return
	 * 		2 digit hexadecimal string
	 */
	public static String hexifyByte(byte val) {
		return hexifyByte((int)val & 0xFF);
	}


	
	/**
	 * Convert int value to hexadecimal short presentation
	 * 
	 * @param val
	 * 		Value
	 * @return
	 * 		4 digit hexadecimal string
	 */
	public static String hexifyShort(int val) {
		return hexifyByte((val >>> 8) & 0xFF) + hexifyByte(val & 0xFF);
	}


	
	/**
	 * Convert int value to hexadecimal int presentation
	 * 
	 * @param val
	 * 		Value
	 * @return
	 * 		8 digit hexadecimal string
	 */
	public static String hexifyInt(int val) {
		return hexifyShort((val >> 16) & 0xFFFF) + hexifyShort(val & 0xFFFF);
	}
	
	
	
	/**
	 * Convert byte array to hexadecimal string
	 * 
	 * @param buffer Buffer with bytes
	 * @param delimiter Delimiter to be inserted between bytes. Use 0 for none.
	 * @param length Number of byte to convert 
	 * @return String with hexadecimal data
	 */
	public static String hexifyByteArray(byte[] buffer, char delimiter, int length) {

	    // Allocate buffer for 2 or 3 times the size in bytes, depending on whether a delimiter
	    // was given or not
	    StringBuffer sb = new StringBuffer((length << 1) + (delimiter == 0 ? 0 : length));
	    
	    for (int i = 0; i < length; i++) {
			sb.append(hexchar[(buffer[i] >>> 4) & 0x0F]);
			sb.append(hexchar[buffer[i] & 0x0F]);
			if ((delimiter != 0) && (i < length - 1)) {
			    sb.append(delimiter);
			}
	    }
	    return sb.toString();
	}
	
	
	
	/**
	 * Convert byte array to hexadecimal string
	 * 
	 * @param buffer Buffer with bytes
	 * @param delimiter Delimiter to be inserted between bytes. Use 0 for none.
	 * @return String with hexadecimal data
	 */
	public static String hexifyByteArray(byte[] buffer, char delimiter) {
	    return hexifyByteArray(buffer, delimiter, buffer.length);
	}
	
	
	
	/**
	 * Convert byte array to hexadecimal string
	 * 
	 * @param buffer Buffer with bytes
	 * @return String with hexadecimal data
	 */
	public static String hexifyByteArray(byte[] buffer) {
		//System.out.println("buffer.length : " + buffer.length);
	    return hexifyByteArray(buffer, (char)0, buffer.length);
	}
	
	
	
	/**
	 * Dump buffer in hexadecimal format with offset and character codes
	 * 
	 * @param data
	 * 			Byte buffer
	 * @param offset
	 * 			Offset into byte buffer
	 * @param length
	 * 			Length of data to be dumped
	 * @param widths
	 * 			Number of bytes per line
	 * @param indent
	 * 			Number of blanks to indent each line
	 * @return
	 * 			String containing the dump
	 */
	public static String dump(byte[] data, int offset, int length, int widths, int indent) {
		StringBuffer buffer = new StringBuffer(80);
		int i, ofs, len;
		char ch;

		if ((data == null) || (widths == 0) || (length < 0) || (indent < 0))
			throw new IllegalArgumentException();
				
		while(length > 0) {
			for (i = 0; i < indent; i++)
				buffer.append(' ');
				
			buffer.append(hexifyShort(offset));
			buffer.append("  ");
			
			ofs = offset;
			len = widths < length ? widths : length;
			
			for (i = 0; i < len; i++, ofs++) {
				buffer.append(hexchar[(data[ofs] >>> 4) & 0x0F]);
				buffer.append(hexchar[data[ofs] & 0x0F]);
				buffer.append(' ');
			}
			
			for (; i < widths; i++) {
				buffer.append("   ");
			}
			
			buffer.append(' ');
			ofs = offset;
			
			for (i = 0; i < len; i++, ofs++) {
				ch = (char)(data[ofs] & 0xFF);
				if ((ch < 32) || ((ch >= 127) && (ch <= 0xA0)))
					ch = '.';
				buffer.append(ch);
			}
			
			buffer.append('\n');
			
			offset += len;
			length -= len;
		}
		return buffer.toString();
	}



	/**
	 * Dump buffer in hexadecimal format with offset and character codes
	 * 
	 * @param data
	 * 			Byte buffer
	 * @param offset
	 * 			Offset into byte buffer
	 * @param length
	 * 			Length of data to be dumped
	 * @param widths
	 * 			Number of bytes per line
	 * @return
	 * 			String containing the dump
	 */
	public static String dump(byte[] data, int offset, int length, int widths) {
		return dump(data, offset, length, widths, 0);
	}
	


	/**
	 * Dump buffer in hexadecimal format with offset and character codes.
	 * Output 16 bytes per line
	 * 
	 * @param data
	 * 			Byte buffer
	 * @param offset
	 * 			Offset into byte buffer
	 * @param length
	 * 			Length of data to be dumped
	 * @return
	 * 			String containing the dump
	 */
	public static String dump(byte[] data, int offset, int length) {
		return dump(data, offset, length, 16, 0);
	}



	/**
	 * Dump buffer in hexadecimal format with offset and character codes
	 * 
	 * @param data
	 * 			Byte buffer
	 * @return
	 * 			String containing the dump
	 */
	public static String dump(byte[] data) {
		return dump(data, 0, data.length, 16, 0);
	}
	
	

	/**
	 * Parse string of hexadecimal characters into byte array
	 * 
	 * @param str String to parse
	 * @return byte array containing the string
	 */
	public static byte[] parseHexString(String str) {

	    UtilByteBuffer b = new UtilByteBuffer(str.length() / 2);
	    int i = 0;
	    int size = str.length();
	    
	    if (str.startsWith("0x")) {
	        i += 2;
	        size -= 2;
	    }

	    while (size >= 2) {
	        if (!Character.isLetterOrDigit(str.charAt(i))) {
	        	i++;
	        	size--;
	        }
	        
	        String toParse = str.substring(i, i + (size > 2 ? 2 : size));
	        b.append((byte)Integer.parseInt(toParse, 16));
	        i += 2;
	        size -= 2;
	    }
	    return b.getBytes();
	}
}

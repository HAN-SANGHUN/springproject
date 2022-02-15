package com.kona.kms.utils.tlv;

/**
 * Class representing a tag that is part of a TLV structure
 * 
 * @author Andreas Schwier (info@cardcontact.de)
 */
public class Tag {
	int tagNumber;
	byte tagClass;
	boolean constructed;

	public final static byte CONSTRUCTED = (byte)0x20;
	public final static byte CLASS		 = (byte)0xC0;
	public final static byte TAG	     = (byte)0x1F;
	public final static byte UNIVERSAL   = (byte)0x00;
	public final static byte APPLICATION = (byte)0x40;
	public final static byte CONTEXT     = (byte)0x80;
	public final static byte PRIVATE     = (byte)0xC0;

	public final static int END_OF_CONTENTS = 0;
	public final static int BOOLEAN = 1;
	public final static int INTEGER = 2;
	public final static int BIT_STRING = 3;
	public final static int OCTET_STRING = 4;
	public final static int NULL = 5;
	public final static int OBJECT_IDENTIFIER = 6;
	public final static int OBJECT_DESCRIPTOR = 7;
	public final static int EXTERNAL_TYPE = 8;
	public final static int REAL = 9;
	public final static int ENUMERATED = 10;
	public final static int EMBEDDED_PDV = 11;
	public final static int UTF8String = 12;
	public final static int RELATIVE_OID = 13;
	public final static int SEQUENCE = 16;
	public final static int SET = 17;
	public final static int NumericString = 18;
	public final static int PrintableString = 19;
	public final static int T61String = 20;
	public final static int IA5String = 22;
	public final static int UTCTime = 23;
	public final static int GeneralizedTime = 24;
	public final static int GeneralString = 27;
	public final static int UniversalString = 28;
	public final static int BMPString = 30;

	final static String[] ClassText = { 
		"UNIVERSAL", 
		"APPLICATION", 
		"CONTEXT", 
		"PRIVATE" };
	final static String[] UniversalText = { 
		"END-OF-CONTENTS",
		"BOOLEAN",
		"INTEGER",
		"BIT-STRING",
		"OCTET-STRING",
		"NULL",
		"OBJECT-IDENTIFIER",
		"OBJECT-DESCRIPTOR",
		"EXTERNAL-TYPE/INSTANCE-OF",
		"REAL",
		"ENUMERATED",
		"EMBEDDED-PDV",
		"UTF8-STRING",
		"RFU",
		"RFU",
		"RFU",
		"SEQUENCE",
		"SET",
		"NUMERIC-STRING",
		"PRINTABLE-STRING",
		"TELETEXT-STRING",
		"VIDEOTEXT-STRING",
		"IA5-STRING",
		"UTC",
		"GENERALIZED-TIME",
		"GRAPHIC-STRING",
		"VISIBLE-STRING",
		"GENERAL-STRING",
		"UNIVERSAL-STRING",
		"RFU",
		"BMP-STRING" };


	/**
	 * Create tag from given components
	 * 
	 * @param newTag
	 * 		Tag number. Range 0 to 30 are single byte tags, Range 31 to 127
	 *      are double byte tags, Range 128 to 16383 are tripple byte tags 
	 * @param newClass
	 * 		Class of tag. Must be one of Tag.UNIVERSAL, Tag.APPLICATION,
	 *      Tag.CONTEXT oder Tag.PRIVATE
	 * @param newConstructed
	 * 		Boolean flag indicating if TLV object is contructed or primitive
	 */		
	public Tag(int newTag, byte newClass, boolean newConstructed) {
		tagNumber = newTag;
		tagClass = newClass;
		constructed = newConstructed;
	}


	/**
	 * Create tag from binary presentation in ParseBuffer
	 * 
	 * @param pb
	 * 		ParseBuffer
	 */
	public Tag(ParseBuffer pb) throws TLVEncodingException {
		byte temp;
		int i;
		
		temp = pb.get();

		tagClass = (byte)(temp & CLASS);

		constructed = ((temp & CONSTRUCTED) == CONSTRUCTED);

		tagNumber = temp & TAG;

		if (tagNumber == TAG) {
			tagNumber = 0;      // reset tag number

			i = 4;
			do	{
				temp = pb.get();
				tagNumber <<= 7;
				tagNumber |= (temp & 0x7F);
				i--;
			} while (((temp & 0x80) == 0x80) && (i > 0));
		}
	}



	/**
	 * Create tag from binary presentation
	 * 
	 * @param value
	 * 		Byte array containing binary tag
	 * @param offset
	 * 		Offset in byte array to look at
	 */
	public Tag(byte[] value, int offset) {
		byte temp;
		int i;
		
		temp = value[offset];

		tagClass = (byte)(temp & CLASS);

		constructed = ((temp & CONSTRUCTED) == CONSTRUCTED);

		tagNumber = temp & TAG;

		if (tagNumber == TAG) {
			tagNumber = 0;      // reset tag number
			offset++;

			i = 4;
			do	{
				temp = value[offset];
				tagNumber <<= 7;
				tagNumber |= (temp & 0x7F);
				offset++;
				i--;
			} while (((temp & 0x80) == 0x80) && (i > 0));
		}
	}


	/**
	 * Create tag from binary presentation
	 * 
	 * @param value
	 * 		Byte array containing binary tag
	 */
	public Tag(byte[] value) {
		this(value, 0);
	}
	

	/**
	 * Create tag from integer representation
	 * 
	 * @param newTag
	 * 		Integer tag value (e.g. 0x5F32)
	 */
	public Tag(int newTag) {
		int size, temp;
		
		if ((newTag >= 0x01000000) || (newTag < 0))
			size = 24;
		else if (newTag >= 0x010000)
			size = 16;
		else if (newTag >= 0x0100)
			size = 8;
		else
			size = 0;

		temp = newTag >> size;
		tagClass = (byte)(temp & CLASS);

		constructed = ((temp & CONSTRUCTED) == CONSTRUCTED);

		tagNumber = temp & TAG;
		
		if (tagNumber == TAG) {	// Multibyte tag found
			tagNumber = 0;

			do	{
				size -= 8;
				temp = (newTag >> size) & 0xFF;
				tagNumber <<= 7;
				tagNumber |= (temp & 0x7F);
			} while (size > 0);
		}
	}



	/**
	 * Test of Tags are equal
	 * 
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Tag))
			return false;

		Tag t = (Tag)object;
		
		if ((tagNumber != t.tagNumber) ||
			(tagClass != t.tagClass) ||
			(constructed != t.constructed))
			return false;
		return true;
	}
	


	/**
	 * Test if Tag has constructed flag set
	 * 
	 * @return
	 * 		true if constructed
	 */
	public boolean isConstructed() {
		return constructed;
	}
	


	/**
	 * Tag number getter
	 * 
	 * @return
	 * 		tag number
	 */
	public int getNumber() {
		return tagNumber;
	}


	
	/**
	 * Tag class getter
	 * 
	 * @return
	 * 		class number
	 */
	public byte getClazz() {
		return tagClass;
	}


	
	/**
	 * Fill byte array at given position with binary representation
	 * of tag and return new offset.
	 * 
	 * @param buffer
	 * 		Byte arrays to receive binary tag
	 * @param offset
	 * 		Offset in byte array that receives the bytes
	 * @return
	 * 		New offset after storing the tag
	 */
	public int toByteArray(byte[] buffer, int offset) {
		int i = 0;

		buffer[offset] = tagClass;

		if (constructed)
			buffer[offset] |= CONSTRUCTED;

		/* serialize a one byte tag */
		if (tagNumber < 0x1F) {
			buffer[offset] |= (byte)tagNumber;
			offset++;
		}
		/* serialize multi tag */
		else  {
			buffer[offset] |= (byte) 0x1F;
			offset++;

			for (i = (getSize() - 2) * 7; i > 0; i -= 7) {
				buffer[offset] = (byte) ( 0x80 | (( tagNumber >> i) & 0x7f));
				offset++;
			}

			buffer[offset] = (byte) (tagNumber & 0x7f);
			offset++;
		}

		return offset;
	}



	/**
	 * Get byte array containing binary representation of tag
	 * 
	 * @return
	 * 		Binary data for tag
	 */
	public byte[] getBytes() {
		byte[] buffer = new byte[getSize()];
		
		toByteArray(buffer, 0);

		return buffer;
	}



	/**
	 * Get size of tag in number of bytes
	 * 
	 * @return
	 * 		Number of bytes
	 */
	public int getSize()  {
		if      (tagNumber < 0x1F)       return 1;
		else if (tagNumber < 0x80)       return 2;
		else if (tagNumber < 0x4000)     return 3;
		else if (tagNumber < 0x200000)   return 4;
		return 5;
	}
	
	

	/**
	 * Dump tag to string
	 * 
	 */	
	public String toString() {
		if (tagClass == UNIVERSAL) {
			if (tagNumber < 0x1F) {
				return(UniversalText[tagNumber] + (constructed && (tagNumber != SEQUENCE) && (tagNumber != SET) ? "*" : ""));
			} else {
				return("(" + tagNumber + ")");
			}
		} else {
			return("[ " + ClassText[(tagClass >> 6) & 3] + " " + tagNumber + (constructed ? " ] IMPLICIT SEQUENCE" : " ]"));
		}
	}
}

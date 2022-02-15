package com.kona.kms.utils.tlv;

/**
 * Object used to parse binary buffers
 * 
 * @author Andreas Schwier (info@cardcontact.de)
 */
public class ParseBuffer {
	byte[] buffer = null;
	int cursor;
	int mark;
	int limit;

	/**
	 * Create ParseBuffer using bytes at given range in byte array
	 * 
	 * @param newBuffer
	 * 		Binary array
	 * @param newCursor
	 * 		Offset in buffer to start at
	 * @param newLength
	 * 		Length of region in buffer to parse
	 */
	public ParseBuffer(byte[] newBuffer, int newCursor, int newLength) {
		buffer = newBuffer;
		cursor = newCursor;
		mark = newCursor;
		limit = newCursor + newLength;
	}

	
	
	/**
	 * Create ParseBuffer to parse entire byte array
	 * 
	 * @param newBuffer
	 * 		Binary array
	 */
	public ParseBuffer(byte[] newBuffer) {
		this(newBuffer, 0, newBuffer.length);	
	}

	

	/**
	 * Mark current parser position
	 *
	 */
	public void mark() {
		mark = cursor;
	}

	
	
	/**
	 * Reset current parser position to previously set mark
	 *
	 */
	public void reset() {
		cursor = mark;
	}



	/**
	 * Return number of remaining bytes in parse buffer
	 * 
	 * @return
	 * 		Number of bytes
	 */
	public int remaining() {
		return limit - cursor;
	}
	
	
	
	/**
	 * Set new length of parse region starting at current offset
	 * 
	 * Throws IllegalArgumentException if the length exceed the
	 * underlying byte array
	 * 
	 * @param newLength
	 * 		New length starting at current offset
	 */
	public void setLength(int newLength) {
		if (cursor + newLength > buffer.length)
			throw new IllegalArgumentException("Can't set new length if it exceeds buffer");
		limit = cursor + newLength;
	}
	
	
	
	/**
	 * Return true if byte are available to parse
	 * @return
	 * 		true if more bytes are available
	 */
	public boolean hasRemaining() {
		return cursor < limit - 1;
	}
	

	
	/**
	 * Get next byte from parse buffer. Advance current position
	 * by one.
	 * 
	 * @return
	 * 		Byte at current parse position
	 * 
	 * @throws TLVEncodingException
	 * 		End of region reached
	 */
	public byte get() throws TLVEncodingException {
		if (cursor >= limit)
			throw new TLVEncodingException("End of buffer");

		return buffer[cursor++];
	}
	


	/**
	 * Bulk get from parse buffer
	 * 
	 * Get specified number of bytes from buffer
	 * 
	 * @param dst
	 * 		Receiving buffer
	 * @param offset
	 * 		Offset in receiving buffer
	 * @param length
	 * 		Number of bytes to get
	 * @return
	 * 		This ParseBuffer object
	 * 
	 * @throws TLVEncodingException
	 * 		Given length exceeds parse region
	 */	
	public ParseBuffer get(byte[] dst, int offset, int length) throws TLVEncodingException {
		if (length > limit - cursor)
			throw new TLVEncodingException("Invalid length field");

		System.arraycopy(buffer, cursor, dst, offset, length);
		cursor += length;
		
		return this;
	}

	

	/**
	 * Get DGI coded length
	 * 
	 * Values between 0 and 254 are encoded in one byte
	 * Values between 255 and 65535 are encoded in three bytes with the
	 * first byte set to 'FF'
	 * 
	 * @return
	 * 		Length
	 * @throws TLVEncodingException
	 * 		End of region reached during decoding
	 */	
	public int getDGILength() throws TLVEncodingException {
		int length;
		int i = 1;
		
		if (buffer[cursor] == (byte)0xFF) {
			i = 2;
			cursor++;
		}

		if (cursor + i > limit)
			throw new TLVEncodingException("Invalid DGI length field");
		
		length = 0;
		for (; i > 0; i--) {
			length = (length << 8) | (buffer[cursor++] & 0xFF);
		}
		
		return length;
	}

	

	/**
	 * Get DER coded length
	 * 
	 * Values between 0 and 127 are encoded in one byte
	 * Values between 128 and 255 are encoded in two bytes
	 * Values between 256 and 65535 are encoded in three bytes
	 * Values between 65536 and 2^24 - 1 are encoded in four bytes
	 * Values between 2^24 and 2^32 - 1 are encoded in five byte
	 * 
	 * For value >= 128 the first byte encoded the number of trailing
	 * bytes plus '80'
	 *  
	 * @return
	 * 		Length
	 * @throws TLVEncodingException
	 * 		End of region reached during decoding
	 */	
	public int getDERLength() throws TLVEncodingException {
		int length;
		int i = 1;
		
		if ((buffer[cursor] & 0x80) == 0x80) {
			i = buffer[cursor] & 0x07;
			cursor++;
		}

		if (cursor + i > limit)
			throw new TLVEncodingException("Invalid DER length field");
		
		length = 0;
		for (; i > 0; i--) {
			length = (length << 8) | (buffer[cursor++] & 0xFF);
		}
		
		return length;
	}
}

package com.kona.kms.utils.tlv;

/**
 * Class to implement ASN.1 SEQUENCE object
 *  
 * @author Andreas Schwier (info@cardcontact.de)
 */
public class Sequence extends ConstructedTLV {

	/**
	 * Create constructed TLV with Tag set to ASN.1 SEQUENCE
	 *
	 */	
	public Sequence() {
		super(new Tag(Tag.SEQUENCE, Tag.UNIVERSAL, true));
	}

	

	/**
	 * Create object from parse buffer
	 * 
	 * This should not be called directly. Use TLV.factory() methods instead
	 * 
	 * @param pb
	 */
	public Sequence(ParseBuffer pb) throws TLVEncodingException {
		super(pb);

		if ((tag.getNumber() != Tag.SEQUENCE) || !(tag.isConstructed())) {
			throw new TLVEncodingException("Invalid tag for SEQUENCE");
		}
	}
	
	
	
	/**
	 * Copy constructor
	 * 
	 * Initialise with existing ConstructedTLV object. Does not perform
	 * a deep copy. The tag and list of contained objects is reassigned.
	 * 
	 * Caution: If applied to a TLV object embedded in a complex structure
	 * remember to update the reference to this object in the parent node.
	 * 
	 * @param tlv
	 * 		ConstructedTLV
	 * 
	 * @throws UnsupportedOperationException
	 * 		
	 */
	public Sequence(TLVObj tlv) throws UnsupportedOperationException {
		super(tlv);
	}
}

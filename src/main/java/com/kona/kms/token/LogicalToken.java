package com.kona.kms.token;


public class LogicalToken {

	public int logicalSlotIx;
	public String tokenLabel;
	public int logicalHsmNo;
	public int localSlotOfs;
	public String lHsmLabel;
	public String p1HsmLabel;
	public String p1HsmTokenLabel;
	
	public LogicalToken() { }
	
	public LogicalToken(int lSlotIx,
						String lTokenLabel,
						int lHsmNo,
						int slotOfs,
						String lHsmLabel,
						String p1HsmLabel,
						String p1HsmTokenLabel) {
		
		this.logicalSlotIx = lSlotIx;
		this.tokenLabel = lTokenLabel;
		this.logicalHsmNo = lHsmNo;
		this.localSlotOfs = slotOfs;
		this.lHsmLabel = lHsmLabel;
		this.p1HsmLabel = p1HsmLabel;
		this.p1HsmTokenLabel = p1HsmTokenLabel;
				
		System.out.format("LogicalToken::LogicalToken: token[%d:%s] - lhsmNo/ix [%d:%d], label [%s:%s:%s]\n", lSlotIx, lTokenLabel, lHsmNo, slotOfs, lHsmLabel, p1HsmLabel, p1HsmTokenLabel);
	}

	public String getTokenLabel() {
		return p1HsmTokenLabel;
	}
}

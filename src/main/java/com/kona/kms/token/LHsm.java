package com.kona.kms.token;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LHsm {

	private static final Logger logger = LoggerFactory.getLogger(LHsm.class);

	public ArrayList<PHsm> hsmConf;
	public void setHsmConf(List<PHsm> hsmConf) {
		this.hsmConf = (ArrayList<PHsm>) hsmConf;
	}

	LHsm(String label, int seq, int slotCount) {
		this.label = label;
		this.seq = seq;
		this.slotCount = slotCount;
		
		Object[] prs = new Object[4];
		prs[0] = this;
		prs[1] = label;
		prs[2] = seq;
		prs[3] = slotCount;
		logger.info("LHsm::LHsm: logical hsm created @{}, label [{}], seq [{}], slotCount [{}]", prs);
	}
	
	public String label;
	public int seq;
	public int slotCount;
	public int startIx, endIx;
	
	public String description;
	public String statusCode;
	private String registrationUserId;
	private Date registrationDate;
	private String updateUserId;
	private Date updateDate;	
	

	public void setLabel(String label) {
		this.label = label;
	}
	
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public void setSlotCount(int count) {
		this.slotCount = count;
	}
		
	void init(int startLogicalSlotIx) {
		logger.debug("LHsm::init: invoked..., cluster hsm count [{}], start Logical Slot Ix [{}]", hsmConf.size(), startLogicalSlotIx);
		int phsmCnt = hsmConf.size();
		for (int i=0; i<phsmCnt; i++) {
			PHsm phsm = (PHsm) hsmConf.get(i);
			logger.debug("KeyManager::init: Physical HSM [{}:{}] found", i, phsm.label);
		}
		startIx = startLogicalSlotIx;
		endIx = startLogicalSlotIx + slotCount;
	}
}

/**
 * 
 */
package com.kona.kms.token.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.kona.kms.web.ss.hsm.model.HsmMetaModel;

public class LHSMModel implements Serializable {

	private static final long serialVersionUID = -8101344054015373045L;

	private int 			lhsmNo;
	private String 		lhsmLabel;
	private int 			phsmCnt;
	private long 		ltokenCnt;
	private long 		ltokenStartNo;
	private long 		ltokenEndNo;
	private String 		lhsmStatsCd;
	private String 		lhsmStatsDescr;
	private String 		descr;
	private String 		registrationUserId;
	private Date 		registrationDate;
	private String 		updateUserId;
	private Date 		updateDate;

	private List<PHSMModel> 		phsmModel;
	private List<LTokenModel> 		ltokenModel;

	public int getLhsmNo() {
		return lhsmNo;
	}

	public void setLhsmNo(int lhsmNo) {
		this.lhsmNo = lhsmNo;
	}

	public String getLhsmLabel() {
		return lhsmLabel;
	}

	public void setLhsmLabel(String lhsmLabel) {
		this.lhsmLabel = lhsmLabel;
	}

	/**
	 * @return the phmsCnt
	 */
	public int getPhsmCnt() {
		return phsmCnt;
	}

	/**
	 * @param phmsCnt the phmsCnt to set
	 */
	public void setPhsmCnt(int phsmCnt) {
		this.phsmCnt = phsmCnt;
	}
	
	public long getLtokenStartNo() {
		return ltokenStartNo;
	}

	public void setLtokenStartNo(long ltokenStartNo) {
		this.ltokenStartNo = ltokenStartNo;
	}

	/**
	 * @return the ltokenCnt
	 */
	public long getLtokenCnt() {
		return ltokenCnt;
	}

	/**
	 * @param ltokenCnt the ltokenCnt to set
	 */
	public void setLtokenCnt(long ltokenCnt) {
		this.ltokenCnt = ltokenCnt;
	}

	public long getLslotEndNo() {
		return ltokenEndNo;
	}

	public void setLslotEndNo(long ltokenEndNo) {
		this.ltokenEndNo = ltokenEndNo;
	}

	public String getLhsmStatsCd() {
		return lhsmStatsCd;
	}

	public void setLhsmStatsCd(String lhsmStatsCd) {
		this.lhsmStatsCd = lhsmStatsCd;
	}

	public String getLhsmStatsDescr() {
		return lhsmStatsDescr;
	}

	public void setLhsmStatsDescr(String lhsmStatsDescr) {
		this.lhsmStatsDescr = lhsmStatsDescr;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getRegistrationUserId() {
		return registrationUserId;
	}

	public void setRegistrationUserId(String registrationUserId) {
		this.registrationUserId = registrationUserId;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the List<PHSMModel>
	 */
	public List<PHSMModel> getPhsmModel() {
		return phsmModel;
	}

	/**
	 * @param phsmModel the phsmModel to set
	 */
	public void setPhsmModel(List<PHSMModel> phsmModel) {
		this.phsmModel = phsmModel;
	}
	
	/**
	 * @return the ltokenModel
	 */
	public List<LTokenModel> getLtokenModel() {
		return ltokenModel;
	}

	/**
	 * @param ltokenModel the ltokenModel to set
	 */
	public void setLtokenModel(List<LTokenModel> ltokenModel) {
		this.ltokenModel = ltokenModel;
	}
	
	@Override
	public String toString() {
		return "LHSMModel [lhsmNo=" + lhsmNo + ", lhsmLabel=" + lhsmLabel
				+ ", lslotStartNo=" + ltokenStartNo + ", lslotEndNo="
				+ ltokenEndNo + ", lhsmStatsCd=" + lhsmStatsCd
				+ ", lhsmStatsDescr=" + lhsmStatsDescr + ", descr=" + descr
				+ ", registrationUserId=" + registrationUserId
				+ ", registrationDate=" + registrationDate + ", updateUserId="
				+ updateUserId + ", updateDate=" + updateDate + "]";
	}	
}

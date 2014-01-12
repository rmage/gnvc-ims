package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class FishTs extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int id;
	protected int wdsId;
	protected int vesselId;
	protected String tsNo;
	protected Date tsDate;
	protected String issuedBy;
	protected String notedBy;
	protected String approvedBy;
	protected String receivedBy;
	protected FishWds withdrawalSlip;
	protected FishVessel vessel;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the wdsId
	 */
	public int getWdsId() {
		return wdsId;
	}

	/**
	 * @param wdsId the wdsId to set
	 */
	public void setWdsId(int wdsId) {
		this.wdsId = wdsId;
	}

	/**
	 * @return the vesselId
	 */
	public int getVesselId() {
		return vesselId;
	}

	/**
	 * @param vesselId the vesselId to set
	 */
	public void setVesselId(int vesselId) {
		this.vesselId = vesselId;
	}

	/**
	 * @return the tsNo
	 */
	public String getTsNo() {
		return tsNo;
	}

	/**
	 * @param tsNo the tsNo to set
	 */
	public void setTsNo(String tsNo) {
		this.tsNo = tsNo;
	}

	/**
	 * @return the tsDate
	 */
	public Date getTsDate() {
		return tsDate;
	}

	/**
	 * @param tsDate the tsDate to set
	 */
	public void setTsDate(Date tsDate) {
		this.tsDate = tsDate;
	}

	/**
	 * @return the issuedBy
	 */
	public String getIssuedBy() {
		return issuedBy;
	}

	/**
	 * @param issuedBy the issuedBy to set
	 */
	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	/**
	 * @return the notedBy
	 */
	public String getNotedBy() {
		return notedBy;
	}

	/**
	 * @param notedBy the notedBy to set
	 */
	public void setNotedBy(String notedBy) {
		this.notedBy = notedBy;
	}

	/**
	 * @return the approvedBy
	 */
	public String getApprovedBy() {
		return approvedBy;
	}

	/**
	 * @param approvedBy the approvedBy to set
	 */
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	/**
	 * @return the receivedBy
	 */
	public String getReceivedBy() {
		return receivedBy;
	}

	/**
	 * @param receivedBy the receivedBy to set
	 */
	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}

	/**
	 * @return the withdrawalSlip
	 */
	public FishWds getWithdrawalSlip() {
		return withdrawalSlip;
	}

	/**
	 * @param withdrawalSlip the withdrawalSlip to set
	 */
	public void setWithdrawalSlip(FishWds withdrawalSlip) {
		this.withdrawalSlip = withdrawalSlip;
	}

	/**
	 * @return the vessel
	 */
	public FishVessel getVessel() {
		return vessel;
	}

	/**
	 * @param vessel the vessel to set
	 */
	public void setVessel(FishVessel vessel) {
		this.vessel = vessel;
	}
}

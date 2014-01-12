package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class FishBadStock extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int id;
	protected int vesselId;
	protected String bsNo;
	protected Date bsDate;
	protected String issuedBy;
	protected String notedBy;
	protected String approvedBy;
	protected String receivedBy;
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
	public String getBsNo() {
		return bsNo;
	}

	/**
	 * @param tsNo the tsNo to set
	 */
	public void setBsNo(String bsNo) {
		this.bsNo = bsNo;
	}

	/**
	 * @return the tsDate
	 */
	public Date getBsDate() {
		return bsDate;
	}

	/**
	 * @param bsDate the tsDate to set
	 */
	public void setBsDate(Date bsDate) {
		this.bsDate = bsDate;
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

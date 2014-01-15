package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class FishWds extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int id;
	protected String wdsNo;
	protected Date wdsDate;
	protected int vesselId;
	protected String requestedBy;
	protected String approvedBy;
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
	 * @return the wdsNo
	 */
	public String getWdsNo() {
		return wdsNo;
	}

	/**
	 * @param wdsNo the wdsNo to set
	 */
	public void setWdsNo(String wdsNo) {
		this.wdsNo = wdsNo;
	}

	/**
	 * @return the wdsDate
	 */
	public Date getWdsDate() {
		return wdsDate;
	}

	/**
	 * @param wdsDate the wdsDate to set
	 */
	public void setWdsDate(Date wdsDate) {
		this.wdsDate = wdsDate;
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
	 * @return the requestedBy
	 */
	public String getRequestedBy() {
		return requestedBy;
	}

	/**
	 * @param requestedBy the requestedBy to set
	 */
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
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

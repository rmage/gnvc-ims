package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class FishRr extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int id;
	protected String rrNo;
	protected String wsNo;
	protected Date rrDate;
	protected int vesselId;
	protected String receivedBy;
	protected String approvedBy;
	protected String evaluatedBy;
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
	 * @return the rrNo
	 */
	public String getRrNo() {
		return rrNo;
	}

	/**
	 * @param rrNo the rrNo to set
	 */
	public void setRrNo(String rrNo) {
		this.rrNo = rrNo;
	}
	
	/**
	 * @return the wsNo
	 */
	public String getWsNo() {
		return wsNo;
	}

	/**
	 * @param wsNo the wsNo to set
	 */
	public void setWsNo(String wsNo) {
		this.wsNo = wsNo;
	}

	/**
	 * @return the rrDate
	 */
	public Date getRrDate() {
		return rrDate;
	}

	/**
	 * @param rrDate the rrDate to set
	 */
	public void setRrDate(Date rrDate) {
		this.rrDate = rrDate;
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
	 * @return the evaluatedBy
	 */
	public String getEvaluatedBy() {
		return evaluatedBy;
	}

	/**
	 * @param evaluatedBy the evaluatedBy to set
	 */
	public void setEvaluatedBy(String evaluatedBy) {
		this.evaluatedBy = evaluatedBy;
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

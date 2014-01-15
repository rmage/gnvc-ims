package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class FishSpoilage extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int id;
	protected String catcherNo;
	protected Date dateShift;
	protected String timeShift;
	protected int fishId;
	protected int vesselId;
	protected Double rawWeight;
	protected Double cookedWeight;
	protected Double totalProcessed;
	protected String reason;
	protected Fish fish;
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
	 * @return the catcherNo
	 */
	public String getCatcherNo() {
		return catcherNo;
	}

	/**
	 * @param catcherNo the catcherNo to set
	 */
	public void setCatcherNo(String catcherNo) {
		this.catcherNo = catcherNo;
	}

	/**
	 * @return the dateShift
	 */
	public Date getDateShift() {
		return dateShift;
	}

	/**
	 * @param dateShift the dateShift to set
	 */
	public void setDateShift(Date dateShift) {
		this.dateShift = dateShift;
	}

	/**
	 * @return the timeShift
	 */
	public String getTimeShift() {
		return timeShift;
	}

	/**
	 * @param timeShift the timeShift to set
	 */
	public void setTimeShift(String timeShift) {
		this.timeShift = timeShift;
	}

	/**
	 * @return the fishId
	 */
	public int getFishId() {
		return fishId;
	}

	/**
	 * @param fishId the fishId to set
	 */
	public void setFishId(int fishId) {
		this.fishId = fishId;
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
	 * @return the rawWeight
	 */
	public Double getRawWeight() {
		return rawWeight;
	}

	/**
	 * @param rawWeight the rawWeight to set
	 */
	public void setRawWeight(Double rawWeight) {
		this.rawWeight = rawWeight;
	}

	/**
	 * @return the cookedWeight
	 */
	public Double getCookedWeight() {
		return cookedWeight;
	}

	/**
	 * @param cookedWeight the cookedWeight to set
	 */
	public void setCookedWeight(Double cookedWeight) {
		this.cookedWeight = cookedWeight;
	}

	/**
	 * @return the totalProcessed
	 */
	public Double getTotalProcessed() {
		return totalProcessed;
	}

	/**
	 * @param totalProcessed the totalProcessed to set
	 */
	public void setTotalProcessed(Double totalProcessed) {
		this.totalProcessed = totalProcessed;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the fish
	 */
	public Fish getFish() {
		return fish;
	}

	/**
	 * @param fish the fish to set
	 */
	public void setFish(Fish fish) {
		this.fish = fish;
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

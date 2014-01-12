package com.app.wms.engine.db.dto;

import java.io.Serializable;

public class FishRrDetail extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int id;
	protected int rrId;
	protected int wsId;
	protected int fishId;
	protected Double goodWeight;
	protected Double spoilageWeight;
	protected Double totalWeight;
	protected int storageId;
	protected FishRr receivingReport;
	protected FishWs weightSlip;
	protected Fish fish;
	
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
	 * @return the rrId
	 */
	public int getRrId() {
		return rrId;
	}

	/**
	 * @param rrId the rrId to set
	 */
	public void setRrId(int rrId) {
		this.rrId = rrId;
	}

	/**
	 * @return the wsId
	 */
	public int getWsId() {
		return wsId;
	}

	/**
	 * @param wsId the wsId to set
	 */
	public void setWsId(int wsId) {
		this.wsId = wsId;
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
	 * @return the goodWeight
	 */
	public Double getGoodWeight() {
		return goodWeight;
	}

	/**
	 * @param goodWeight the goodWeight to set
	 */
	public void setGoodWeight(Double goodWeight) {
		this.goodWeight = goodWeight;
	}

	/**
	 * @return the spoilageWeight
	 */
	public Double getSpoilageWeight() {
		return spoilageWeight;
	}

	/**
	 * @param spoilageWeight the spoilageWeight to set
	 */
	public void setSpoilageWeight(Double spoilageWeight) {
		this.spoilageWeight = spoilageWeight;
	}

	/**
	 * @return the totalWeight
	 */
	public Double getTotalWeight() {
		return totalWeight;
	}

	/**
	 * @param totalWeight the totalWeight to set
	 */
	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	/**
	 * @return the storageId
	 */
	public int getStorageId() {
		return storageId;
	}

	/**
	 * @param storageId the storageId to set
	 */
	public void setStorageId(int storageId) {
		this.storageId = storageId;
	}

	/**
	 * @return the receivingReport
	 */
	public FishRr getReceivingReport() {
		return receivingReport;
	}

	/**
	 * @param receivingReport the receivingReport to set
	 */
	public void setReceivingReport(FishRr receivingReport) {
		this.receivingReport = receivingReport;
	}

	/**
	 * @return the weightSlip
	 */
	public FishWs getWeightSlip() {
		return weightSlip;
	}

	/**
	 * @param weightSlip the weightSlip to set
	 */
	public void setWeightSlip(FishWs weightSlip) {
		this.weightSlip = weightSlip;
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

	
}

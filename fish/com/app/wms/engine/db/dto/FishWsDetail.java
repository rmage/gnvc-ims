package com.app.wms.engine.db.dto;

import java.io.Serializable;

public class FishWsDetail extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int id;
	protected int wsId;
	protected int fishId;
	protected Double totalWeight;
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

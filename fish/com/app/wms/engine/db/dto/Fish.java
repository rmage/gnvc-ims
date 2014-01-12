package com.app.wms.engine.db.dto;

import java.io.Serializable;

public class Fish extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int id;
	protected int fishTypeId;
	protected int fishWeightTypeId;
	protected String code;
	protected FishType fishType;
	protected FishWeightType fishWeightType;
	
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
	 * @return the fishTypeId
	 */
	public int getFishTypeId() {
		return fishTypeId;
	}
	
	/**
	 * @param fishTypeId the fishTypeId to set
	 */
	public void setFishTypeId(int fishTypeId) {
		this.fishTypeId = fishTypeId;
	}
	
	/**
	 * @return the fishWeightTypeId
	 */
	public int getFishWeightTypeId() {
		return fishWeightTypeId;
	}

	/**
	 * @param fishWeightTypeId the fishWeightTypeId to set
	 */
	public void setFishWeightTypeId(int fishWeightTypeId) {
		this.fishWeightTypeId = fishWeightTypeId;
	}
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @return the fishType
	 */
	public FishType getFishType() {
		return fishType;
	}
	
	/**
	 * @param fishType the fishType to set
	 */
	public void setFishType(FishType fishType) {
		this.fishType = fishType;
	}
	
	/**
	 * @return the fishWeightType
	 */
	public FishWeightType getFishWeightType() {
		return fishWeightType;
	}
	
	/**
	 * @param fishWeightType the fishWeightType to set
	 */
	public void setFishWeightType(FishWeightType fishWeightType) {
		this.fishWeightType = fishWeightType;
	}
}

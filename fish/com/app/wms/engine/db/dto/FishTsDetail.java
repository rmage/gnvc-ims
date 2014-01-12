package com.app.wms.engine.db.dto;

import java.io.Serializable;

public class FishTsDetail extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int id;
	protected int tsId;
	protected int fishId;
	protected int storageId;
	protected String description;
	protected Double quantity;
	protected String uomCode;
	protected FishTs transferSlip;
	protected Fish fish;
	protected FishStorage storage;
	
	/**
	 * @return the storage
	 */
	public FishStorage getStorage() {
		return storage;
	}

	/**
	 * @param storage the storage to set
	 */
	public void setStorage(FishStorage storage) {
		this.storage = storage;
	}

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
	 * @return the tsId
	 */
	public int getTsId() {
		return tsId;
	}

	/**
	 * @param tsId the tsId to set
	 */
	public void setTsId(int tsId) {
		this.tsId = tsId;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the quantity
	 */
	public Double getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the uomCode
	 */
	public String getUomCode() {
		return uomCode;
	}

	/**
	 * @param uomCode the uomCode to set
	 */
	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	/**
	 * @return the transferSlip
	 */
	public FishTs getTransferSlip() {
		return transferSlip;
	}

	/**
	 * @param transferSlip the transferSlip to set
	 */
	public void setTransferSlip(FishTs transferSlip) {
		this.transferSlip = transferSlip;
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

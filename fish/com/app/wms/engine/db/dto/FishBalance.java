package com.app.wms.engine.db.dto;

import java.io.Serializable;

public class FishBalance extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int id;
	protected int vesselId;
	protected int storageId;
	protected int fishId;
	protected Double balance;
	protected FishVessel vessel;
	protected FishStorage storage;
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
	 * @return the balance
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
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

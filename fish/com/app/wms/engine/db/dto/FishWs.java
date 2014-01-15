package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class FishWs extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int id;
	protected int wsTypeId;
	protected int vesselId;
	protected int storageId;
	protected String wsNo;
	protected Date dateShift;
	protected String timeShift;
	protected String regu;
	protected FishWSType wsType;
	protected FishVessel vessel;
	protected FishStorage storage;
	
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
	 * @return the wsTypeId
	 */
	public int getWsTypeId() {
		return wsTypeId;
	}

	/**
	 * @param wsTypeId the wsTypeId to set
	 */
	public void setWsTypeId(int wsTypeId) {
		this.wsTypeId = wsTypeId;
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
	 * @return the regu
	 */
	public String getRegu() {
		return regu;
	}

	/**
	 * @param regu the regu to set
	 */
	public void setRegu(String regu) {
		this.regu = regu;
	}

	/**
	 * @return the wsType
	 */
	public FishWSType getWsType() {
		return wsType;
	}

	/**
	 * @param wsType the wsType to set
	 */
	public void setWsType(FishWSType wsType) {
		this.wsType = wsType;
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
}

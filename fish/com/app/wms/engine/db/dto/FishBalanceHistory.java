package com.app.wms.engine.db.dto;

import java.io.Serializable;

public class FishBalanceHistory extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int id;
	protected String docNo;
	protected String batchNo;
	protected String fishType;
	protected String storage;
	protected Double qtyIn;
	protected Double qtyOut;
	protected Double balance;
	
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
	 * @return the docNo
	 */
	public String getDocNo() {
		return docNo;
	}

	/**
	 * @param docNo the docNo to set
	 */
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	/**
	 * @return the batchNo
	 */
	public String getBatchNo() {
		return batchNo;
	}

	/**
	 * @param batchNo the batchNo to set
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * @return the fishType
	 */
	public String getFishType() {
		return fishType;
	}

	/**
	 * @param fishType the fishType to set
	 */
	public void setFishType(String fishType) {
		this.fishType = fishType;
	}

	/**
	 * @return the storage
	 */
	public String getStorage() {
		return storage;
	}

	/**
	 * @param storage the storage to set
	 */
	public void setStorage(String storage) {
		this.storage = storage;
	}

	/**
	 * @return the qtyIn
	 */
	public Double getQtyIn() {
		return qtyIn;
	}

	/**
	 * @param qtyIn the qtyIn to set
	 */
	public void setQtyIn(Double qtyIn) {
		this.qtyIn = qtyIn;
	}

	/**
	 * @return the qtyOut
	 */
	public Double getQtyOut() {
		return qtyOut;
	}

	/**
	 * @param qtyOut the qtyOut to set
	 */
	public void setQtyOut(Double qtyOut) {
		this.qtyOut = qtyOut;
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
}

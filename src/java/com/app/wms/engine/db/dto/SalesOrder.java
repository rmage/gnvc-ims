package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.sql.Timestamp;


public class SalesOrder implements Serializable {
	
	protected int id;
	protected String so_number;
	protected Timestamp so_date;
	protected Timestamp startDate;
	protected Timestamp endDate;
	protected String createdBy;
	protected Timestamp createdDate;
	protected String updatedBy;
	protected Timestamp updatedDate;
	protected Timestamp delivery_date;
	protected String delivery_name;
	protected String delivery_address;
	protected String corpId;
	protected String whCode;
	protected String remarks;
	
	public SalesOrder(){
		
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getWhCode() {
		return whCode;
	}

	public void setWhCode(String whCode) {
		this.whCode = whCode;
	}

	public Timestamp getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(Timestamp delivery_date) {
		this.delivery_date = delivery_date;
	}

	public String getDelivery_name() {
		return delivery_name;
	}

	public void setDelivery_name(String delivery_name) {
		this.delivery_name = delivery_name;
	}

	public String getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Timestamp getSo_date() {
		return so_date;
	}

	public void setSo_date(Timestamp so_date) {
		this.so_date = so_date;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getSo_number() {
		return so_number;
	}

	public void setSo_number(String so_number) {
		this.so_number = so_number;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * Returns <code>true</code> if the argument is an SalesOrder instance and
	 * all identifiers for this entity equal the identifiers of the argument
	 * entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof SalesOrder)) {
			return false;
		}
		final SalesOrder that = (SalesOrder) object;
		if (this.so_number == null || that.getSo_number() == null || !this.so_number.equals(that.getSo_number())) {
			return false;
		}
		return true;
	}

	/**
	 * Returns a hash code based on this entity's identifiers.
	 */
	public int hashCode() {
		int hashCode = 0;
		hashCode = 29 * hashCode + (so_number == null ? 0 : so_number.hashCode());

		return hashCode;
	}
	
	
	/**
	 * Method 'createPk'
	 * 
	 * @return SalesOrderPk
	 */
	public SalesOrderPk createPk()
	{
		return new SalesOrderPk(so_number);
	}
	

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString(){
		final StringBuffer sb = new StringBuffer();
		sb.append( "SalesOrder");
		sb.append( "{");
		sb.append( "id=").append(id);
		sb.append( ",so_number=").append(so_number);
		sb.append( ",so_date=").append(so_date);
		sb.append( ",createdBy=").append(createdBy);
		sb.append( ",createdDate=").append(createdDate);
		sb.append( ",updatedBy=").append(updatedBy);
		sb.append( ",updatedDate=").append(updatedDate);
		sb.append( ",delivery_date=").append(delivery_date);
		sb.append( ",delivery_name=").append(delivery_name);
		sb.append( ",delivery_address=").append(delivery_address);
		sb.append( ",corpId=").append(corpId);
		sb.append( ",whCode=").append(whCode);
		sb.append( ",remarks=").append(remarks);
		sb.append( "}");
		return sb.toString();
	}
}

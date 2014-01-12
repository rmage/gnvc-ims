package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Picking implements Serializable 
{
	
	protected int id;
	protected String pickingNo;
	protected Date pickingDate;
	protected String soNumber;
	protected Date soDate;
	protected String statusApp;
	protected Date appDate;
	protected String createdBy;
	protected Date createdDate;
	protected String updatedBy;
	protected Date updatedDate;
	protected String tallyman;
	protected PickingDetail pickingDetail;
	protected String corpId;
	protected String whCode;
	protected SalesOrder salesOrder;

	/**
	 * Method 'Picking'
	 * 
	 */
	public Picking()
	{
	}


	public SalesOrder getSalesOrder() {
		return salesOrder;
	}


	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
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


	public String getTallyman() {
		return tallyman;
	}


	public void setTallyman(String tallyman) {
		this.tallyman = tallyman;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPickingNo() {
		return pickingNo;
	}


	public void setPickingNo(String pickingNo) {
		this.pickingNo = pickingNo;
	}


	public PickingDetail getPickingDetail() {
		return pickingDetail;
	}


	public void setPickingDetail(PickingDetail pickingDetail) {
		this.pickingDetail = pickingDetail;
	}


	public String getStatusApp() {
		return statusApp;
	}


	public void setStatusApp(String statusApp) {
		this.statusApp = statusApp;
	}


	public Date getAppDate() {
		return appDate;
	}


	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}


	public Date getSoDate() {
		return soDate;
	}


	public void setSoDate(Date soDate) {
		this.soDate = soDate;
	}


	public String getSoNumber() {
		return soNumber;
	}



	public void setSoNumber(String soNumber) {
		this.soNumber = soNumber;
	}



	public Date getPickingDate() {
		return pickingDate;
	}



	public void setPickingDate(Date pickingDate) {
		this.pickingDate = pickingDate;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public Date getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	public String getUpdatedBy() {
		return updatedBy;
	}



	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}



	public Date getUpdatedDate() {
		return updatedDate;
	}



	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	/**
	 * Method 'createPk'
	 * 
	 * @return PickingPk
	 */
	public PickingPk createPk()
	{
		return new PickingPk(pickingNo);
	}
	
	
	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append( "com.app.wms.engine.db.dto.Picking: " );
		sb.append( "id=" + id );
		sb.append( ", pickingNo=" + pickingNo );
		sb.append( ", pickingDate=" + pickingDate );
		sb.append( ", soNumber=" + soNumber );
		sb.append( ", soDate=" + soDate );
		sb.append( ", statusApp=" + statusApp );
		sb.append( ", appDate=" + appDate );
		sb.append( ", createdBy=" + createdBy );
		sb.append( ", createdDate=" + createdDate );
		sb.append( ", updatedBy=" + updatedBy );
		sb.append( ", updatedDate=" + updatedDate );
		sb.append( ", tallyman=" + tallyman );
		sb.append( ", corpId=" + corpId );
		sb.append( ", whCode=" + whCode );
		sb.append( ", pickingDetail=" + pickingDetail );
		sb.append( ", salesOrder=" + salesOrder );
	
		return sb.toString();
	}



}

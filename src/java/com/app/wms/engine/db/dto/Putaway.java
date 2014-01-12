package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Putaway implements Serializable 
{

	protected String putawayId;
	protected Date putawayDate;
	protected String grNumber;
	protected Date grDate;
	protected String statusApp;
	protected Date appDate;
	protected String createdBy;
	protected Date createdDate;
	protected String updatedBy;
	protected Date updatedDate;
	protected PutawayDetail putawayDetail;
	
	public Putaway(){
		
	}

	public String getPutawayId() {
		return putawayId;
	}

	public void setPutawayId(String putawayId) {
		this.putawayId = putawayId;
	}

	public Date getPutawayDate() {
		return putawayDate;
	}

	public void setPutawayDate(Date putawayDate) {
		this.putawayDate = putawayDate;
	}

	public String getGrNumber() {
		return grNumber;
	}

	public void setGrNumber(String grNumber) {
		this.grNumber = grNumber;
	}

	public Date getGrDate() {
		return grDate;
	}

	public void setGrDate(Date grDate) {
		this.grDate = grDate;
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

	public PutawayDetail getPutawayDetail() {
		return putawayDetail;
	}

	public void setPutawayDetail(PutawayDetail putawayDetail) {
		this.putawayDetail = putawayDetail;
	}
	
	/**
	 * Method 'createPk'
	 * 
	 * @return PutawayPk
	 */
	public PutawayPk createPk()
	{
		return new PutawayPk(putawayId);
	}
	
	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append( "com.app.wms.engine.db.dto.Putaway: " );
		sb.append( "putawayId=" + putawayId );
		sb.append( ", putawayDate=" + putawayDate );
		sb.append( ", grNumber=" + grNumber );
		sb.append( ", grDate=" + grDate );
		sb.append( ", statusApp=" + statusApp );
		sb.append( ", appDate=" + appDate );
		sb.append( ", createdBy=" + createdBy );
		sb.append( ", createdDate=" + createdDate );
		sb.append( ", updatedBy=" + updatedBy );
		sb.append( ", updatedDate=" + updatedDate );
		sb.append( ", putawayDetail=" + putawayDetail );
	
		return sb.toString();
	}

	
}

package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Delivery implements Serializable
{
	/** 
	 * This attribute maps to the column delivery_no in the deliveryorder table.
	 */
	protected int id;
	
	protected String deliveryNo;

	/** 
	 * This attribute maps to the column delivery_date in the deliveryorder table.
	 */
	protected Date deliveryDate;

	/** 
	 * This attribute maps to the column delivery_name in the deliveryorder table.
	 */
	protected String deliveryName;

	/** 
	 * This attribute maps to the column delivery_address in the deliveryorder table.
	 */
	protected String deliveryAddress;

	/** 
	 * This attribute maps to the column created_by in the deliveryorder table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the deliveryorder table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the deliveryorder table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the deliveryorder table.
	 */
	protected Date updatedDate;
	
	protected String transporterType;
	
	protected String corpId;
	
	protected String whCode;

	/**
	 * Method 'Deliveryorder'
	 * 
	 */
	public Delivery()
	{
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTransporterType() {
		return transporterType;
	}

	public void setTransporterType(String transporterType) {
		this.transporterType = transporterType;
	}

	/**
	 * Method 'getDeliveryNo'
	 * 
	 * @return String
	 */
	public String getDeliveryNo()
	{
		return deliveryNo;
	}

	/**
	 * Method 'setDeliveryNo'
	 * 
	 * @param deliveryNo
	 */
	public void setDeliveryNo(String deliveryNo)
	{
		this.deliveryNo = deliveryNo;
	}

	/**
	 * Method 'getDeliveryDate'
	 * 
	 * @return Date
	 */
	public Date getDeliveryDate()
	{
		return deliveryDate;
	}

	/**
	 * Method 'setDeliveryDate'
	 * 
	 * @param deliveryDate
	 */
	public void setDeliveryDate(Date deliveryDate)
	{
		this.deliveryDate = deliveryDate;
	}

	/**
	 * Method 'getDeliveryName'
	 * 
	 * @return String
	 */
	public String getDeliveryName()
	{
		return deliveryName;
	}

	/**
	 * Method 'setDeliveryName'
	 * 
	 * @param deliveryName
	 */
	public void setDeliveryName(String deliveryName)
	{
		this.deliveryName = deliveryName;
	}

	/**
	 * Method 'getDeliveryAddress'
	 * 
	 * @return String
	 */
	public String getDeliveryAddress()
	{
		return deliveryAddress;
	}

	/**
	 * Method 'setDeliveryAddress'
	 * 
	 * @param deliveryAddress
	 */
	public void setDeliveryAddress(String deliveryAddress)
	{
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * Method 'getCreatedBy'
	 * 
	 * @return String
	 */
	public String getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * Method 'setCreatedBy'
	 * 
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	/**
	 * Method 'getCreatedDate'
	 * 
	 * @return Date
	 */
	public Date getCreatedDate()
	{
		return createdDate;
	}

	/**
	 * Method 'setCreatedDate'
	 * 
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	/**
	 * Method 'getUpdatedBy'
	 * 
	 * @return String
	 */
	public String getUpdatedBy()
	{
		return updatedBy;
	}

	/**
	 * Method 'setUpdatedBy'
	 * 
	 * @param updatedBy
	 */
	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	/**
	 * Method 'getUpdatedDate'
	 * 
	 * @return Date
	 */
	public Date getUpdatedDate()
	{
		return updatedDate;
	}

	/**
	 * Method 'setUpdatedDate'
	 * 
	 * @param updatedDate
	 */
	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof Delivery)) {
			return false;
		}
		
		final Delivery _cast = (Delivery) _other;
		if (deliveryNo == null ? _cast.deliveryNo != deliveryNo : !deliveryNo.equals( _cast.deliveryNo )) {
			return false;
		}
		
		if (deliveryDate == null ? _cast.deliveryDate != deliveryDate : !deliveryDate.equals( _cast.deliveryDate )) {
			return false;
		}
		
		if (deliveryName == null ? _cast.deliveryName != deliveryName : !deliveryName.equals( _cast.deliveryName )) {
			return false;
		}
		
		if (deliveryAddress == null ? _cast.deliveryAddress != deliveryAddress : !deliveryAddress.equals( _cast.deliveryAddress )) {
			return false;
		}
		
		if (createdBy == null ? _cast.createdBy != createdBy : !createdBy.equals( _cast.createdBy )) {
			return false;
		}
		
		if (createdDate == null ? _cast.createdDate != createdDate : !createdDate.equals( _cast.createdDate )) {
			return false;
		}
		
		if (updatedBy == null ? _cast.updatedBy != updatedBy : !updatedBy.equals( _cast.updatedBy )) {
			return false;
		}
		
		if (updatedDate == null ? _cast.updatedDate != updatedDate : !updatedDate.equals( _cast.updatedDate )) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		if (deliveryNo != null) {
			_hashCode = 29 * _hashCode + deliveryNo.hashCode();
		}
		
		if (deliveryDate != null) {
			_hashCode = 29 * _hashCode + deliveryDate.hashCode();
		}
		
		if (deliveryName != null) {
			_hashCode = 29 * _hashCode + deliveryName.hashCode();
		}
		
		if (deliveryAddress != null) {
			_hashCode = 29 * _hashCode + deliveryAddress.hashCode();
		}
		
		if (createdBy != null) {
			_hashCode = 29 * _hashCode + createdBy.hashCode();
		}
		
		if (createdDate != null) {
			_hashCode = 29 * _hashCode + createdDate.hashCode();
		}
		
		if (updatedBy != null) {
			_hashCode = 29 * _hashCode + updatedBy.hashCode();
		}
		
		if (updatedDate != null) {
			_hashCode = 29 * _hashCode + updatedDate.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return DeliveryorderPk
	 */
	public DeliveryPk createPk()
	{
		return new DeliveryPk(deliveryNo);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Deliveryorder: " );
		ret.append( "id=" + id );
		ret.append( "deliveryNo=" + deliveryNo );
		ret.append( ", deliveryDate=" + deliveryDate );
		ret.append( ", deliveryName=" + deliveryName );
		ret.append( ", deliveryAddress=" + deliveryAddress );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		ret.append( ", transporterType=" + transporterType );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		return ret.toString();
	}

}

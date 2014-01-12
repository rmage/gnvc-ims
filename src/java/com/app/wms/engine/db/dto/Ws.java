package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Ws implements Serializable
{
	/** 
	 * This attribute maps to the column id in the ws table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column ws_code in the ws table.
	 */
	protected String wsCode;

	/** 
	 * This attribute maps to the column ws_date in the ws table.
	 */
	protected String wsDate;

	/** 
	 * This attribute maps to the column supplier_name in the ws table.
	 */
	protected String supplierName;

	/** 
	 * This attribute maps to the column fish_species in the ws table.
	 */
	protected String fishSpecies;

	/** 
	 * This attribute maps to the column fish_size in the ws table.
	 */
	protected String fishSize;

	/** 
	 * This attribute maps to the column fish_weight in the ws table.
	 */
	protected String fishWeight;

	/** 
	 * This attribute maps to the column quantity in the ws table.
	 */
	protected int quantity;

	/** 
	 * This attribute represents whether the primitive attribute quantity is null.
	 */
	protected boolean quantityNull = true;

	/** 
	 * This attribute maps to the column remarks in the ws table.
	 */
	protected String remarks;

	/** 
	 * This attribute maps to the column issued_by in the ws table.
	 */
	protected String issuedBy;

	/** 
	 * This attribute maps to the column checked_by in the ws table.
	 */
	protected String checkedBy;

	/** 
	 * This attribute maps to the column received_by in the ws table.
	 */
	protected String receivedBy;

	/** 
	 * This attribute maps to the column is_active in the ws table.
	 */
	protected String isActive;

	/** 
	 * This attribute maps to the column is_delete in the ws table.
	 */
	protected String isDelete;

	/** 
	 * This attribute maps to the column created_by in the ws table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the ws table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the ws table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the ws table.
	 */
	protected Date updatedDate;

	/**
	 * Method 'Ws'
	 * 
	 */
	public Ws()
	{
	}

	/**
	 * Method 'getId'
	 * 
	 * @return int
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Method 'setId'
	 * 
	 * @param id
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Method 'getWsCode'
	 * 
	 * @return String
	 */
	public String getWsCode()
	{
		return wsCode;
	}

	/**
	 * Method 'setWsCode'
	 * 
	 * @param wsCode
	 */
	public void setWsCode(String wsCode)
	{
		this.wsCode = wsCode;
	}

	/**
	 * Method 'getWsDate'
	 * 
	 * @return String
	 */
	public String getWsDate()
	{
		return wsDate;
	}

	/**
	 * Method 'setWsDate'
	 * 
	 * @param wsDate
	 */
	public void setWsDate(String wsDate)
	{
		this.wsDate = wsDate;
	}

	/**
	 * Method 'getSupplierName'
	 * 
	 * @return String
	 */
	public String getSupplierName()
	{
		return supplierName;
	}

	/**
	 * Method 'setSupplierName'
	 * 
	 * @param supplierName
	 */
	public void setSupplierName(String supplierName)
	{
		this.supplierName = supplierName;
	}

	/**
	 * Method 'getFishSpecies'
	 * 
	 * @return String
	 */
	public String getFishSpecies()
	{
		return fishSpecies;
	}

	/**
	 * Method 'setFishSpecies'
	 * 
	 * @param fishSpecies
	 */
	public void setFishSpecies(String fishSpecies)
	{
		this.fishSpecies = fishSpecies;
	}

	/**
	 * Method 'getFishSize'
	 * 
	 * @return String
	 */
	public String getFishSize()
	{
		return fishSize;
	}

	/**
	 * Method 'setFishSize'
	 * 
	 * @param fishSize
	 */
	public void setFishSize(String fishSize)
	{
		this.fishSize = fishSize;
	}

	/**
	 * Method 'getFishWeight'
	 * 
	 * @return String
	 */
	public String getFishWeight()
	{
		return fishWeight;
	}

	/**
	 * Method 'setFishWeight'
	 * 
	 * @param fishWeight
	 */
	public void setFishWeight(String fishWeight)
	{
		this.fishWeight = fishWeight;
	}

	/**
	 * Method 'getQuantity'
	 * 
	 * @return int
	 */
	public int getQuantity()
	{
		return quantity;
	}

	/**
	 * Method 'setQuantity'
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
		this.quantityNull = false;
	}

	/**
	 * Method 'setQuantityNull'
	 * 
	 * @param value
	 */
	public void setQuantityNull(boolean value)
	{
		this.quantityNull = value;
	}

	/**
	 * Method 'isQuantityNull'
	 * 
	 * @return boolean
	 */
	public boolean isQuantityNull()
	{
		return quantityNull;
	}

	/**
	 * Method 'getRemarks'
	 * 
	 * @return String
	 */
	public String getRemarks()
	{
		return remarks;
	}

	/**
	 * Method 'setRemarks'
	 * 
	 * @param remarks
	 */
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	/**
	 * Method 'getIssuedBy'
	 * 
	 * @return String
	 */
	public String getIssuedBy()
	{
		return issuedBy;
	}

	/**
	 * Method 'setIssuedBy'
	 * 
	 * @param issuedBy
	 */
	public void setIssuedBy(String issuedBy)
	{
		this.issuedBy = issuedBy;
	}

	/**
	 * Method 'getCheckedBy'
	 * 
	 * @return String
	 */
	public String getCheckedBy()
	{
		return checkedBy;
	}

	/**
	 * Method 'setCheckedBy'
	 * 
	 * @param checkedBy
	 */
	public void setCheckedBy(String checkedBy)
	{
		this.checkedBy = checkedBy;
	}

	/**
	 * Method 'getReceivedBy'
	 * 
	 * @return String
	 */
	public String getReceivedBy()
	{
		return receivedBy;
	}

	/**
	 * Method 'setReceivedBy'
	 * 
	 * @param receivedBy
	 */
	public void setReceivedBy(String receivedBy)
	{
		this.receivedBy = receivedBy;
	}

	/**
	 * Method 'getIsActive'
	 * 
	 * @return String
	 */
	public String getIsActive()
	{
		return isActive;
	}

	/**
	 * Method 'setIsActive'
	 * 
	 * @param isActive
	 */
	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	/**
	 * Method 'getIsDelete'
	 * 
	 * @return String
	 */
	public String getIsDelete()
	{
		return isDelete;
	}

	/**
	 * Method 'setIsDelete'
	 * 
	 * @param isDelete
	 */
	public void setIsDelete(String isDelete)
	{
		this.isDelete = isDelete;
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
		
		if (!(_other instanceof Ws)) {
			return false;
		}
		
		final Ws _cast = (Ws) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (wsCode == null ? _cast.wsCode != wsCode : !wsCode.equals( _cast.wsCode )) {
			return false;
		}
		
		if (wsDate == null ? _cast.wsDate != wsDate : !wsDate.equals( _cast.wsDate )) {
			return false;
		}
		
		if (supplierName == null ? _cast.supplierName != supplierName : !supplierName.equals( _cast.supplierName )) {
			return false;
		}
		
		if (fishSpecies == null ? _cast.fishSpecies != fishSpecies : !fishSpecies.equals( _cast.fishSpecies )) {
			return false;
		}
		
		if (fishSize == null ? _cast.fishSize != fishSize : !fishSize.equals( _cast.fishSize )) {
			return false;
		}
		
		if (fishWeight == null ? _cast.fishWeight != fishWeight : !fishWeight.equals( _cast.fishWeight )) {
			return false;
		}
		
		if (quantity != _cast.quantity) {
			return false;
		}
		
		if (quantityNull != _cast.quantityNull) {
			return false;
		}
		
		if (remarks == null ? _cast.remarks != remarks : !remarks.equals( _cast.remarks )) {
			return false;
		}
		
		if (issuedBy == null ? _cast.issuedBy != issuedBy : !issuedBy.equals( _cast.issuedBy )) {
			return false;
		}
		
		if (checkedBy == null ? _cast.checkedBy != checkedBy : !checkedBy.equals( _cast.checkedBy )) {
			return false;
		}
		
		if (receivedBy == null ? _cast.receivedBy != receivedBy : !receivedBy.equals( _cast.receivedBy )) {
			return false;
		}
		
		if (isActive == null ? _cast.isActive != isActive : !isActive.equals( _cast.isActive )) {
			return false;
		}
		
		if (isDelete == null ? _cast.isDelete != isDelete : !isDelete.equals( _cast.isDelete )) {
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
		_hashCode = 29 * _hashCode + id;
		if (wsCode != null) {
			_hashCode = 29 * _hashCode + wsCode.hashCode();
		}
		
		if (wsDate != null) {
			_hashCode = 29 * _hashCode + wsDate.hashCode();
		}
		
		if (supplierName != null) {
			_hashCode = 29 * _hashCode + supplierName.hashCode();
		}
		
		if (fishSpecies != null) {
			_hashCode = 29 * _hashCode + fishSpecies.hashCode();
		}
		
		if (fishSize != null) {
			_hashCode = 29 * _hashCode + fishSize.hashCode();
		}
		
		if (fishWeight != null) {
			_hashCode = 29 * _hashCode + fishWeight.hashCode();
		}
		
		_hashCode = 29 * _hashCode + quantity;
		_hashCode = 29 * _hashCode + (quantityNull ? 1 : 0);
		if (remarks != null) {
			_hashCode = 29 * _hashCode + remarks.hashCode();
		}
		
		if (issuedBy != null) {
			_hashCode = 29 * _hashCode + issuedBy.hashCode();
		}
		
		if (checkedBy != null) {
			_hashCode = 29 * _hashCode + checkedBy.hashCode();
		}
		
		if (receivedBy != null) {
			_hashCode = 29 * _hashCode + receivedBy.hashCode();
		}
		
		if (isActive != null) {
			_hashCode = 29 * _hashCode + isActive.hashCode();
		}
		
		if (isDelete != null) {
			_hashCode = 29 * _hashCode + isDelete.hashCode();
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
	 * @return WsPk
	 */
	public WsPk createPk()
	{
		return new WsPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Ws: " );
		ret.append( "id=" + id );
		ret.append( ", wsCode=" + wsCode );
		ret.append( ", wsDate=" + wsDate );
		ret.append( ", supplierName=" + supplierName );
		ret.append( ", fishSpecies=" + fishSpecies );
		ret.append( ", fishSize=" + fishSize );
		ret.append( ", fishWeight=" + fishWeight );
		ret.append( ", quantity=" + quantity );
		ret.append( ", remarks=" + remarks );
		ret.append( ", issuedBy=" + issuedBy );
		ret.append( ", checkedBy=" + checkedBy );
		ret.append( ", receivedBy=" + receivedBy );
		ret.append( ", isActive=" + isActive );
		ret.append( ", isDelete=" + isDelete );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}

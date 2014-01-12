package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Dr implements Serializable
{
	/** 
	 * This attribute maps to the column id in the dr table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column drnumber in the dr table.
	 */
	protected String drnumber;

	/** 
	 * This attribute maps to the column ornumber in the dr table.
	 */
	protected String ornumber;

	/** 
	 * This attribute maps to the column dmnumber in the dr table.
	 */
	protected String dmnumber;

	/** 
	 * This attribute maps to the column drdate in the dr table.
	 */
	protected Date drdate;

	/** 
	 * This attribute maps to the column createdby in the dr table.
	 */
	protected String createdby;

	/** 
	 * This attribute maps to the column lotid in the dr table.
	 */
	protected String lotid;

	/** 
	 * This attribute maps to the column wh_code in the dr table.
	 */
	protected String whCode;

	/** 
	 * This attribute maps to the column wsNo in the dr table.
	 */
	protected String wsNo;

	/** 
	 * This attribute maps to the column unitCost in the dr table.
	 */
	protected int unitCost;

	/** 
	 * This attribute represents whether the primitive attribute unitCost is null.
	 */
	protected boolean unitCostNull = true;

	/** 
	 * This attribute maps to the column amount in the dr table.
	 */
	protected int amount;

	/** 
	 * This attribute represents whether the primitive attribute amount is null.
	 */
	protected boolean amountNull = true;

	/** 
	 * This attribute maps to the column from in the dr table.
	 */
	protected String from;

	/** 
	 * This attribute maps to the column issuedBy in the dr table.
	 */
	protected String issuedBy;

	/** 
	 * This attribute maps to the column deliveredBy in the dr table.
	 */
	protected String deliveredBy;

	/** 
	 * This attribute maps to the column accEntry in the dr table.
	 */
	protected String accEntry;

	/** 
	 * This attribute maps to the column approvedBy in the dr table.
	 */
	protected String approvedBy;

	/** 
	 * This attribute maps to the column remarks in the dr table.
	 */
	protected String remarks;

	/** 
	 * This attribute maps to the column receivedBy in the dr table.
	 */
	protected String receivedBy;

	/** 
	 * This attribute maps to the column department_name in the dr table.
	 */
	protected String departmentName;

	/** 
	 * This attribute maps to the column supplierName in the dr table.
	 */
	protected String supplierName;

	/**
	 * Method 'Dr'
	 * 
	 */
	public Dr()
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
	 * Method 'getDrnumber'
	 * 
	 * @return String
	 */
	public String getDrnumber()
	{
		return drnumber;
	}

	/**
	 * Method 'setDrnumber'
	 * 
	 * @param drnumber
	 */
	public void setDrnumber(String drnumber)
	{
		this.drnumber = drnumber;
	}

	/**
	 * Method 'getOrnumber'
	 * 
	 * @return String
	 */
	public String getOrnumber()
	{
		return ornumber;
	}

	/**
	 * Method 'setOrnumber'
	 * 
	 * @param ornumber
	 */
	public void setOrnumber(String ornumber)
	{
		this.ornumber = ornumber;
	}

	/**
	 * Method 'getDmnumber'
	 * 
	 * @return String
	 */
	public String getDmnumber()
	{
		return dmnumber;
	}

	/**
	 * Method 'setDmnumber'
	 * 
	 * @param dmnumber
	 */
	public void setDmnumber(String dmnumber)
	{
		this.dmnumber = dmnumber;
	}

	/**
	 * Method 'getDrdate'
	 * 
	 * @return Date
	 */
	public Date getDrdate()
	{
		return drdate;
	}

	/**
	 * Method 'setDrdate'
	 * 
	 * @param drdate
	 */
	public void setDrdate(Date drdate)
	{
		this.drdate = drdate;
	}

	/**
	 * Method 'getCreatedby'
	 * 
	 * @return String
	 */
	public String getCreatedby()
	{
		return createdby;
	}

	/**
	 * Method 'setCreatedby'
	 * 
	 * @param createdby
	 */
	public void setCreatedby(String createdby)
	{
		this.createdby = createdby;
	}

	/**
	 * Method 'getLotid'
	 * 
	 * @return String
	 */
	public String getLotid()
	{
		return lotid;
	}

	/**
	 * Method 'setLotid'
	 * 
	 * @param lotid
	 */
	public void setLotid(String lotid)
	{
		this.lotid = lotid;
	}

	/**
	 * Method 'getWhCode'
	 * 
	 * @return String
	 */
	public String getWhCode()
	{
		return whCode;
	}

	/**
	 * Method 'setWhCode'
	 * 
	 * @param whCode
	 */
	public void setWhCode(String whCode)
	{
		this.whCode = whCode;
	}

	/**
	 * Method 'getWsNo'
	 * 
	 * @return String
	 */
	public String getWsNo()
	{
		return wsNo;
	}

	/**
	 * Method 'setWsNo'
	 * 
	 * @param wsNo
	 */
	public void setWsNo(String wsNo)
	{
		this.wsNo = wsNo;
	}

	/**
	 * Method 'getUnitCost'
	 * 
	 * @return int
	 */
	public int getUnitCost()
	{
		return unitCost;
	}

	/**
	 * Method 'setUnitCost'
	 * 
	 * @param unitCost
	 */
	public void setUnitCost(int unitCost)
	{
		this.unitCost = unitCost;
		this.unitCostNull = false;
	}

	/**
	 * Method 'setUnitCostNull'
	 * 
	 * @param value
	 */
	public void setUnitCostNull(boolean value)
	{
		this.unitCostNull = value;
	}

	/**
	 * Method 'isUnitCostNull'
	 * 
	 * @return boolean
	 */
	public boolean isUnitCostNull()
	{
		return unitCostNull;
	}

	/**
	 * Method 'getAmount'
	 * 
	 * @return int
	 */
	public int getAmount()
	{
		return amount;
	}

	/**
	 * Method 'setAmount'
	 * 
	 * @param amount
	 */
	public void setAmount(int amount)
	{
		this.amount = amount;
		this.amountNull = false;
	}

	/**
	 * Method 'setAmountNull'
	 * 
	 * @param value
	 */
	public void setAmountNull(boolean value)
	{
		this.amountNull = value;
	}

	/**
	 * Method 'isAmountNull'
	 * 
	 * @return boolean
	 */
	public boolean isAmountNull()
	{
		return amountNull;
	}

	/**
	 * Method 'getFrom'
	 * 
	 * @return String
	 */
	public String getFrom()
	{
		return from;
	}

	/**
	 * Method 'setFrom'
	 * 
	 * @param from
	 */
	public void setFrom(String from)
	{
		this.from = from;
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
	 * Method 'getDeliveredBy'
	 * 
	 * @return String
	 */
	public String getDeliveredBy()
	{
		return deliveredBy;
	}

	/**
	 * Method 'setDeliveredBy'
	 * 
	 * @param deliveredBy
	 */
	public void setDeliveredBy(String deliveredBy)
	{
		this.deliveredBy = deliveredBy;
	}

	/**
	 * Method 'getAccEntry'
	 * 
	 * @return String
	 */
	public String getAccEntry()
	{
		return accEntry;
	}

	/**
	 * Method 'setAccEntry'
	 * 
	 * @param accEntry
	 */
	public void setAccEntry(String accEntry)
	{
		this.accEntry = accEntry;
	}

	/**
	 * Method 'getApprovedBy'
	 * 
	 * @return String
	 */
	public String getApprovedBy()
	{
		return approvedBy;
	}

	/**
	 * Method 'setApprovedBy'
	 * 
	 * @param approvedBy
	 */
	public void setApprovedBy(String approvedBy)
	{
		this.approvedBy = approvedBy;
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
	 * Method 'getDepartmentName'
	 * 
	 * @return String
	 */
	public String getDepartmentName()
	{
		return departmentName;
	}

	/**
	 * Method 'setDepartmentName'
	 * 
	 * @param departmentName
	 */
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
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
		
		if (!(_other instanceof Dr)) {
			return false;
		}
		
		final Dr _cast = (Dr) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (drnumber == null ? _cast.drnumber != drnumber : !drnumber.equals( _cast.drnumber )) {
			return false;
		}
		
		if (ornumber == null ? _cast.ornumber != ornumber : !ornumber.equals( _cast.ornumber )) {
			return false;
		}
		
		if (dmnumber == null ? _cast.dmnumber != dmnumber : !dmnumber.equals( _cast.dmnumber )) {
			return false;
		}
		
		if (drdate == null ? _cast.drdate != drdate : !drdate.equals( _cast.drdate )) {
			return false;
		}
		
		if (createdby == null ? _cast.createdby != createdby : !createdby.equals( _cast.createdby )) {
			return false;
		}
		
		if (lotid == null ? _cast.lotid != lotid : !lotid.equals( _cast.lotid )) {
			return false;
		}
		
		if (whCode == null ? _cast.whCode != whCode : !whCode.equals( _cast.whCode )) {
			return false;
		}
		
		if (wsNo == null ? _cast.wsNo != wsNo : !wsNo.equals( _cast.wsNo )) {
			return false;
		}
		
		if (unitCost != _cast.unitCost) {
			return false;
		}
		
		if (unitCostNull != _cast.unitCostNull) {
			return false;
		}
		
		if (amount != _cast.amount) {
			return false;
		}
		
		if (amountNull != _cast.amountNull) {
			return false;
		}
		
		if (from == null ? _cast.from != from : !from.equals( _cast.from )) {
			return false;
		}
		
		if (issuedBy == null ? _cast.issuedBy != issuedBy : !issuedBy.equals( _cast.issuedBy )) {
			return false;
		}
		
		if (deliveredBy == null ? _cast.deliveredBy != deliveredBy : !deliveredBy.equals( _cast.deliveredBy )) {
			return false;
		}
		
		if (accEntry == null ? _cast.accEntry != accEntry : !accEntry.equals( _cast.accEntry )) {
			return false;
		}
		
		if (approvedBy == null ? _cast.approvedBy != approvedBy : !approvedBy.equals( _cast.approvedBy )) {
			return false;
		}
		
		if (remarks == null ? _cast.remarks != remarks : !remarks.equals( _cast.remarks )) {
			return false;
		}
		
		if (receivedBy == null ? _cast.receivedBy != receivedBy : !receivedBy.equals( _cast.receivedBy )) {
			return false;
		}
		
		if (departmentName == null ? _cast.departmentName != departmentName : !departmentName.equals( _cast.departmentName )) {
			return false;
		}
		
		if (supplierName == null ? _cast.supplierName != supplierName : !supplierName.equals( _cast.supplierName )) {
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
		if (drnumber != null) {
			_hashCode = 29 * _hashCode + drnumber.hashCode();
		}
		
		if (ornumber != null) {
			_hashCode = 29 * _hashCode + ornumber.hashCode();
		}
		
		if (dmnumber != null) {
			_hashCode = 29 * _hashCode + dmnumber.hashCode();
		}
		
		if (drdate != null) {
			_hashCode = 29 * _hashCode + drdate.hashCode();
		}
		
		if (createdby != null) {
			_hashCode = 29 * _hashCode + createdby.hashCode();
		}
		
		if (lotid != null) {
			_hashCode = 29 * _hashCode + lotid.hashCode();
		}
		
		if (whCode != null) {
			_hashCode = 29 * _hashCode + whCode.hashCode();
		}
		
		if (wsNo != null) {
			_hashCode = 29 * _hashCode + wsNo.hashCode();
		}
		
		_hashCode = 29 * _hashCode + unitCost;
		_hashCode = 29 * _hashCode + (unitCostNull ? 1 : 0);
		_hashCode = 29 * _hashCode + amount;
		_hashCode = 29 * _hashCode + (amountNull ? 1 : 0);
		if (from != null) {
			_hashCode = 29 * _hashCode + from.hashCode();
		}
		
		if (issuedBy != null) {
			_hashCode = 29 * _hashCode + issuedBy.hashCode();
		}
		
		if (deliveredBy != null) {
			_hashCode = 29 * _hashCode + deliveredBy.hashCode();
		}
		
		if (accEntry != null) {
			_hashCode = 29 * _hashCode + accEntry.hashCode();
		}
		
		if (approvedBy != null) {
			_hashCode = 29 * _hashCode + approvedBy.hashCode();
		}
		
		if (remarks != null) {
			_hashCode = 29 * _hashCode + remarks.hashCode();
		}
		
		if (receivedBy != null) {
			_hashCode = 29 * _hashCode + receivedBy.hashCode();
		}
		
		if (departmentName != null) {
			_hashCode = 29 * _hashCode + departmentName.hashCode();
		}
		
		if (supplierName != null) {
			_hashCode = 29 * _hashCode + supplierName.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return DrPk
	 */
	public DrPk createPk()
	{
		return new DrPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Dr: " );
		ret.append( "id=" + id );
		ret.append( ", drnumber=" + drnumber );
		ret.append( ", ornumber=" + ornumber );
		ret.append( ", dmnumber=" + dmnumber );
		ret.append( ", drdate=" + drdate );
		ret.append( ", createdby=" + createdby );
		ret.append( ", lotid=" + lotid );
		ret.append( ", whCode=" + whCode );
		ret.append( ", wsNo=" + wsNo );
		ret.append( ", unitCost=" + unitCost );
		ret.append( ", amount=" + amount );
		ret.append( ", from=" + from );
		ret.append( ", issuedBy=" + issuedBy );
		ret.append( ", deliveredBy=" + deliveredBy );
		ret.append( ", accEntry=" + accEntry );
		ret.append( ", approvedBy=" + approvedBy );
		ret.append( ", remarks=" + remarks );
		ret.append( ", receivedBy=" + receivedBy );
		ret.append( ", departmentName=" + departmentName );
		ret.append( ", supplierName=" + supplierName );
		return ret.toString();
	}

}

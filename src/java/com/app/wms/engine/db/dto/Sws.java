package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Sws implements Serializable
{
	/** 
	 * This attribute maps to the column id in the sws table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column swsnumber in the sws table.
	 */
	protected String swsnumber;

	/** 
	 * This attribute maps to the column swsdate in the sws table.
	 */
	protected Date swsdate;

	/** 
	 * This attribute maps to the column swsreferensi in the sws table.
	 */
	protected String swsreferensi;

	/** 
	 * This attribute maps to the column remarks in the sws table.
	 */
	protected String remarks;

	/** 
	 * This attribute maps to the column createdby in the sws table.
	 */
	protected String createdby;

	/** 
	 * This attribute maps to the column requestedBy in the sws table.
	 */
	protected String requestedBy;

	/** 
	 * This attribute maps to the column approvedBy in the sws table.
	 */
	protected String approvedBy;

	/** 
	 * This attribute maps to the column wh_code in the sws table.
	 */
	protected String whCode;

	/** 
	 * This attribute maps to the column department_name in the sws table.
	 */
	protected String departmentName;
	
	protected String isApproved;
	
	protected Date approvedDate;

	/**
	 * Method 'Sws'
	 * 
	 */
	public Sws()
	{
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
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
	 * Method 'getSwsnumber'
	 * 
	 * @return String
	 */
	public String getSwsnumber()
	{
		return swsnumber;
	}

	/**
	 * Method 'setSwsnumber'
	 * 
	 * @param swsnumber
	 */
	public void setSwsnumber(String swsnumber)
	{
		this.swsnumber = swsnumber;
	}

	/**
	 * Method 'getSwsdate'
	 * 
	 * @return Date
	 */
	public Date getSwsdate()
	{
		return swsdate;
	}

	/**
	 * Method 'setSwsdate'
	 * 
	 * @param swsdate
	 */
	public void setSwsdate(Date swsdate)
	{
		this.swsdate = swsdate;
	}

	/**
	 * Method 'getSwsreferensi'
	 * 
	 * @return String
	 */
	public String getSwsreferensi()
	{
		return swsreferensi;
	}

	/**
	 * Method 'setSwsreferensi'
	 * 
	 * @param swsreferensi
	 */
	public void setSwsreferensi(String swsreferensi)
	{
		this.swsreferensi = swsreferensi;
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
	 * Method 'getRequestedBy'
	 * 
	 * @return String
	 */
	public String getRequestedBy()
	{
		return requestedBy;
	}

	/**
	 * Method 'setRequestedBy'
	 * 
	 * @param requestedBy
	 */
	public void setRequestedBy(String requestedBy)
	{
		this.requestedBy = requestedBy;
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
		
		if (!(_other instanceof Sws)) {
			return false;
		}
		
		final Sws _cast = (Sws) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (swsnumber == null ? _cast.swsnumber != swsnumber : !swsnumber.equals( _cast.swsnumber )) {
			return false;
		}
		
		if (swsdate == null ? _cast.swsdate != swsdate : !swsdate.equals( _cast.swsdate )) {
			return false;
		}
		
		if (swsreferensi == null ? _cast.swsreferensi != swsreferensi : !swsreferensi.equals( _cast.swsreferensi )) {
			return false;
		}
		
		if (remarks == null ? _cast.remarks != remarks : !remarks.equals( _cast.remarks )) {
			return false;
		}
		
		if (createdby == null ? _cast.createdby != createdby : !createdby.equals( _cast.createdby )) {
			return false;
		}
		
		if (requestedBy == null ? _cast.requestedBy != requestedBy : !requestedBy.equals( _cast.requestedBy )) {
			return false;
		}
		
		if (approvedBy == null ? _cast.approvedBy != approvedBy : !approvedBy.equals( _cast.approvedBy )) {
			return false;
		}
		
		if (whCode == null ? _cast.whCode != whCode : !whCode.equals( _cast.whCode )) {
			return false;
		}
		
		if (departmentName == null ? _cast.departmentName != departmentName : !departmentName.equals( _cast.departmentName )) {
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
		if (swsnumber != null) {
			_hashCode = 29 * _hashCode + swsnumber.hashCode();
		}
		
		if (swsdate != null) {
			_hashCode = 29 * _hashCode + swsdate.hashCode();
		}
		
		if (swsreferensi != null) {
			_hashCode = 29 * _hashCode + swsreferensi.hashCode();
		}
		
		if (remarks != null) {
			_hashCode = 29 * _hashCode + remarks.hashCode();
		}
		
		if (createdby != null) {
			_hashCode = 29 * _hashCode + createdby.hashCode();
		}
		
		if (requestedBy != null) {
			_hashCode = 29 * _hashCode + requestedBy.hashCode();
		}
		
		if (approvedBy != null) {
			_hashCode = 29 * _hashCode + approvedBy.hashCode();
		}
		
		if (whCode != null) {
			_hashCode = 29 * _hashCode + whCode.hashCode();
		}
		
		if (departmentName != null) {
			_hashCode = 29 * _hashCode + departmentName.hashCode();
		}
		
		if (isApproved != null) {
			_hashCode = 29 * _hashCode + isApproved.hashCode();
		}
		
		if (approvedDate != null) {
			_hashCode = 29 * _hashCode + approvedDate.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return SwsPk
	 */
	public SwsPk createPk()
	{
		return new SwsPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Sws: " );
		ret.append( "id=" + id );
		ret.append( ", swsnumber=" + swsnumber );
		ret.append( ", swsdate=" + swsdate );
		ret.append( ", swsreferensi=" + swsreferensi );
		ret.append( ", remarks=" + remarks );
		ret.append( ", createdby=" + createdby );
		ret.append( ", requestedBy=" + requestedBy );
		ret.append( ", approvedBy=" + approvedBy );
		ret.append( ", whCode=" + whCode );
		ret.append( ", departmentName=" + departmentName );
		ret.append( ", isApproved=" + isApproved );
		ret.append( ", approvedDate=" + approvedDate );
		return ret.toString();
	}

}

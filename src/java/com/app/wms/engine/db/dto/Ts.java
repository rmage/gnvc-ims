package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Ts implements Serializable
{
	/** 
	 * This attribute maps to the column id in the ts table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column tsnumber in the ts table.
	 */
	protected String tsnumber;

	/** 
	 * This attribute maps to the column tsdate in the ts table.
	 */
	protected Date tsdate;

	/** 
	 * This attribute maps to the column swsnumber in the ts table.
	 */
	protected String swsnumber;

	/** 
	 * This attribute maps to the column department_code in the ts table.
	 */
	protected String departmentCode;

	/** 
	 * This attribute maps to the column createdby in the ts table.
	 */
	protected String createdby;

	/** 
	 * This attribute maps to the column issuedby in the ts table.
	 */
	protected String issuedby;

	/** 
	 * This attribute maps to the column notedby in the ts table.
	 */
	protected String notedby;

	/** 
	 * This attribute maps to the column receivedby in the ts table.
	 */
	protected String receivedby;

	/** 
	 * This attribute maps to the column approvedby in the ts table.
	 */
	protected String approvedby;

	/** 
	 * This attribute maps to the column remarks in the ts table.
	 */
	protected String remarks;

	/**
	 * Method 'Ts'
	 * 
	 */
	public Ts()
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
	 * Method 'getTsnumber'
	 * 
	 * @return String
	 */
	public String getTsnumber()
	{
		return tsnumber;
	}

	/**
	 * Method 'setTsnumber'
	 * 
	 * @param tsnumber
	 */
	public void setTsnumber(String tsnumber)
	{
		this.tsnumber = tsnumber;
	}

	/**
	 * Method 'getTsdate'
	 * 
	 * @return Date
	 */
	public Date getTsdate()
	{
		return tsdate;
	}

	/**
	 * Method 'setTsdate'
	 * 
	 * @param tsdate
	 */
	public void setTsdate(Date tsdate)
	{
		this.tsdate = tsdate;
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
	 * Method 'getDepartmentCode'
	 * 
	 * @return String
	 */
	public String getDepartmentCode()
	{
		return departmentCode;
	}

	/**
	 * Method 'setDepartmentCode'
	 * 
	 * @param departmentCode
	 */
	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
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
	 * Method 'getIssuedby'
	 * 
	 * @return String
	 */
	public String getIssuedby()
	{
		return issuedby;
	}

	/**
	 * Method 'setIssuedby'
	 * 
	 * @param issuedby
	 */
	public void setIssuedby(String issuedby)
	{
		this.issuedby = issuedby;
	}

	/**
	 * Method 'getNotedby'
	 * 
	 * @return String
	 */
	public String getNotedby()
	{
		return notedby;
	}

	/**
	 * Method 'setNotedby'
	 * 
	 * @param notedby
	 */
	public void setNotedby(String notedby)
	{
		this.notedby = notedby;
	}

	/**
	 * Method 'getReceivedby'
	 * 
	 * @return String
	 */
	public String getReceivedby()
	{
		return receivedby;
	}

	/**
	 * Method 'setReceivedby'
	 * 
	 * @param receivedby
	 */
	public void setReceivedby(String receivedby)
	{
		this.receivedby = receivedby;
	}

	/**
	 * Method 'getApprovedby'
	 * 
	 * @return String
	 */
	public String getApprovedby()
	{
		return approvedby;
	}

	/**
	 * Method 'setApprovedby'
	 * 
	 * @param approvedby
	 */
	public void setApprovedby(String approvedby)
	{
		this.approvedby = approvedby;
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
		
		if (!(_other instanceof Ts)) {
			return false;
		}
		
		final Ts _cast = (Ts) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (tsnumber == null ? _cast.tsnumber != tsnumber : !tsnumber.equals( _cast.tsnumber )) {
			return false;
		}
		
		if (tsdate == null ? _cast.tsdate != tsdate : !tsdate.equals( _cast.tsdate )) {
			return false;
		}
		
		if (swsnumber == null ? _cast.swsnumber != swsnumber : !swsnumber.equals( _cast.swsnumber )) {
			return false;
		}
		
		if (departmentCode == null ? _cast.departmentCode != departmentCode : !departmentCode.equals( _cast.departmentCode )) {
			return false;
		}
		
		if (createdby == null ? _cast.createdby != createdby : !createdby.equals( _cast.createdby )) {
			return false;
		}
		
		if (issuedby == null ? _cast.issuedby != issuedby : !issuedby.equals( _cast.issuedby )) {
			return false;
		}
		
		if (notedby == null ? _cast.notedby != notedby : !notedby.equals( _cast.notedby )) {
			return false;
		}
		
		if (receivedby == null ? _cast.receivedby != receivedby : !receivedby.equals( _cast.receivedby )) {
			return false;
		}
		
		if (approvedby == null ? _cast.approvedby != approvedby : !approvedby.equals( _cast.approvedby )) {
			return false;
		}
		
		if (remarks == null ? _cast.remarks != remarks : !remarks.equals( _cast.remarks )) {
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
		if (tsnumber != null) {
			_hashCode = 29 * _hashCode + tsnumber.hashCode();
		}
		
		if (tsdate != null) {
			_hashCode = 29 * _hashCode + tsdate.hashCode();
		}
		
		if (swsnumber != null) {
			_hashCode = 29 * _hashCode + swsnumber.hashCode();
		}
		
		if (departmentCode != null) {
			_hashCode = 29 * _hashCode + departmentCode.hashCode();
		}
		
		if (createdby != null) {
			_hashCode = 29 * _hashCode + createdby.hashCode();
		}
		
		if (issuedby != null) {
			_hashCode = 29 * _hashCode + issuedby.hashCode();
		}
		
		if (notedby != null) {
			_hashCode = 29 * _hashCode + notedby.hashCode();
		}
		
		if (receivedby != null) {
			_hashCode = 29 * _hashCode + receivedby.hashCode();
		}
		
		if (approvedby != null) {
			_hashCode = 29 * _hashCode + approvedby.hashCode();
		}
		
		if (remarks != null) {
			_hashCode = 29 * _hashCode + remarks.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return TsPk
	 */
	public TsPk createPk()
	{
		return new TsPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Ts: " );
		ret.append( "id=" + id );
		ret.append( ", tsnumber=" + tsnumber );
		ret.append( ", tsdate=" + tsdate );
		ret.append( ", swsnumber=" + swsnumber );
		ret.append( ", departmentCode=" + departmentCode );
		ret.append( ", createdby=" + createdby );
		ret.append( ", issuedby=" + issuedby );
		ret.append( ", notedby=" + notedby );
		ret.append( ", receivedby=" + receivedby );
		ret.append( ", approvedby=" + approvedby );
		ret.append( ", remarks=" + remarks );
		return ret.toString();
	}

}

package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Canvassing implements Serializable
{
	/** 
	 * This attribute maps to the column id in the canvassing table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column supplier_code in the canvassing table.
	 */
	//protected String supplierCode;

	/** 
	 * This attribute maps to the column prsnumber in the canvassing table.
	 */
	protected String prsnumber;

	/** 
	 * This attribute maps to the column canvassername in the canvassing table.
	 */
	protected String canvassername;

	/** 
	 * This attribute maps to the column canvassingdate in the canvassing table.
	 */
	protected Date canvassingdate;

	/** 
	 * This attribute maps to the column remarks in the canvassing table.
	 */
	protected String remarks;

	/** 
	 * This attribute maps to the column createdby in the canvassing table.
	 */
	protected String createdby;

	/**
	 * Method 'Canvassing'
	 * 
	 */
	public Canvassing()
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
	 * Method 'getSupplierCode'
	 * 
	 * @return String
	 */
//	public String getSupplierCode()
//	{
//		return supplierCode;
//	}

	/**
	 * Method 'setSupplierCode'
	 * 
	 * @param supplierCode
	 */
//	public void setSupplierCode(String supplierCode)
//	{
//		this.supplierCode = supplierCode;
//	}

	/**
	 * Method 'getPrsnumber'
	 * 
	 * @return String
	 */
	public String getPrsnumber()
	{
		return prsnumber;
	}

	/**
	 * Method 'setPrsnumber'
	 * 
	 * @param prsnumber
	 */
	public void setPrsnumber(String prsnumber)
	{
		this.prsnumber = prsnumber;
	}

	/**
	 * Method 'getCanvassername'
	 * 
	 * @return String
	 */
	public String getCanvassername()
	{
		return canvassername;
	}

	/**
	 * Method 'setCanvassername'
	 * 
	 * @param canvassername
	 */
	public void setCanvassername(String canvassername)
	{
		this.canvassername = canvassername;
	}

	/**
	 * Method 'getCanvassingdate'
	 * 
	 * @return Date
	 */
	public Date getCanvassingdate()
	{
		return canvassingdate;
	}

	/**
	 * Method 'setCanvassingdate'
	 * 
	 * @param canvassingdate
	 */
	public void setCanvassingdate(Date canvassingdate)
	{
		this.canvassingdate = canvassingdate;
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
		
		if (!(_other instanceof Canvassing)) {
			return false;
		}
		
		final Canvassing _cast = (Canvassing) _other;
		if (id != _cast.id) {
			return false;
		}
		
//		if (supplierCode == null ? _cast.supplierCode != supplierCode : !supplierCode.equals( _cast.supplierCode )) {
//			return false;
//		}
		
		if (prsnumber == null ? _cast.prsnumber != prsnumber : !prsnumber.equals( _cast.prsnumber )) {
			return false;
		}
		
		if (canvassername == null ? _cast.canvassername != canvassername : !canvassername.equals( _cast.canvassername )) {
			return false;
		}
		
		if (canvassingdate == null ? _cast.canvassingdate != canvassingdate : !canvassingdate.equals( _cast.canvassingdate )) {
			return false;
		}
		
		if (remarks == null ? _cast.remarks != remarks : !remarks.equals( _cast.remarks )) {
			return false;
		}
		
		if (createdby == null ? _cast.createdby != createdby : !createdby.equals( _cast.createdby )) {
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
//		if (supplierCode != null) {
//			_hashCode = 29 * _hashCode + supplierCode.hashCode();
//		}
		
		if (prsnumber != null) {
			_hashCode = 29 * _hashCode + prsnumber.hashCode();
		}
		
		if (canvassername != null) {
			_hashCode = 29 * _hashCode + canvassername.hashCode();
		}
		
		if (canvassingdate != null) {
			_hashCode = 29 * _hashCode + canvassingdate.hashCode();
		}
		
		if (remarks != null) {
			_hashCode = 29 * _hashCode + remarks.hashCode();
		}
		
		if (createdby != null) {
			_hashCode = 29 * _hashCode + createdby.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return CanvassingPk
	 */
	public CanvassingPk createPk()
	{
		return new CanvassingPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Canvassing: " );
		ret.append( "id=" + id );
//		ret.append( ", supplierCode=" + supplierCode );
		ret.append( ", prsnumber=" + prsnumber );
		ret.append( ", canvassername=" + canvassername );
		ret.append( ", canvassingdate=" + canvassingdate );
		ret.append( ", remarks=" + remarks );
		ret.append( ", createdby=" + createdby );
		return ret.toString();
	}

}

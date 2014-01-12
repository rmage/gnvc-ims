package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class DrDetail implements Serializable
{
	/** 
	 * This attribute maps to the column id in the dr_detail table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column drnumber in the dr_detail table.
	 */
	protected String drnumber;

	/** 
	 * This attribute maps to the column productcode in the dr_detail table.
	 */
	protected String productcode;

	/** 
	 * This attribute maps to the column qtyreal in the dr_detail table.
	 */
	protected BigDecimal qtyreal;

	/** 
	 * This attribute represents whether the primitive attribute qtyreal is null.
	 */
	protected boolean qtyrealNull = true;

	/** 
	 * This attribute maps to the column status in the dr_detail table.
	 */
	protected String status;

	/** 
	 * This attribute maps to the column expirydate in the dr_detail table.
	 */
	protected Date expirydate;

	/** 
	 * This attribute maps to the column remarks in the dr_detail table.
	 */
	protected String remarks;

	/** 
	 * This attribute maps to the column qtygood in the dr_detail table.
	 */
	protected float qtygood;

	/** 
	 * This attribute represents whether the primitive attribute qtygood is null.
	 */
	protected boolean qtygoodNull = true;

	/** 
	 * This attribute maps to the column qtydmg in the dr_detail table.
	 */
	protected float qtydmg;

	/** 
	 * This attribute represents whether the primitive attribute qtydmg is null.
	 */
	protected boolean qtydmgNull = true;

	/** 
	 * This attribute maps to the column producttype in the dr_detail table.
	 */
	protected String producttype;

	/**
	 * Method 'DrDetail'
	 * 
	 */
	public DrDetail()
	{
	}

	public BigDecimal getQtyreal() {
		return qtyreal;
	}

	public void setQtyreal(BigDecimal qtyreal) {
		this.qtyreal = qtyreal;
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
	 * Method 'getProductcode'
	 * 
	 * @return String
	 */
	public String getProductcode()
	{
		return productcode;
	}

	/**
	 * Method 'setProductcode'
	 * 
	 * @param productcode
	 */
	public void setProductcode(String productcode)
	{
		this.productcode = productcode;
	}
	
	/**
	 * Method 'setQtyrealNull'
	 * 
	 * @param value
	 */
	public void setQtyrealNull(boolean value)
	{
		this.qtyrealNull = value;
	}

	/**
	 * Method 'isQtyrealNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtyrealNull()
	{
		return qtyrealNull;
	}

	/**
	 * Method 'getStatus'
	 * 
	 * @return String
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * Method 'setStatus'
	 * 
	 * @param status
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

	/**
	 * Method 'getExpirydate'
	 * 
	 * @return Date
	 */
	public Date getExpirydate()
	{
		return expirydate;
	}

	/**
	 * Method 'setExpirydate'
	 * 
	 * @param expirydate
	 */
	public void setExpirydate(Date expirydate)
	{
		this.expirydate = expirydate;
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
	 * Method 'getQtygood'
	 * 
	 * @return float
	 */
	public float getQtygood()
	{
		return qtygood;
	}

	/**
	 * Method 'setQtygood'
	 * 
	 * @param qtygood
	 */
	public void setQtygood(float qtygood)
	{
		this.qtygood = qtygood;
		this.qtygoodNull = false;
	}

	/**
	 * Method 'setQtygoodNull'
	 * 
	 * @param value
	 */
	public void setQtygoodNull(boolean value)
	{
		this.qtygoodNull = value;
	}

	/**
	 * Method 'isQtygoodNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtygoodNull()
	{
		return qtygoodNull;
	}

	/**
	 * Method 'getQtydmg'
	 * 
	 * @return float
	 */
	public float getQtydmg()
	{
		return qtydmg;
	}

	/**
	 * Method 'setQtydmg'
	 * 
	 * @param qtydmg
	 */
	public void setQtydmg(float qtydmg)
	{
		this.qtydmg = qtydmg;
		this.qtydmgNull = false;
	}

	/**
	 * Method 'setQtydmgNull'
	 * 
	 * @param value
	 */
	public void setQtydmgNull(boolean value)
	{
		this.qtydmgNull = value;
	}

	/**
	 * Method 'isQtydmgNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtydmgNull()
	{
		return qtydmgNull;
	}

	/**
	 * Method 'getProducttype'
	 * 
	 * @return String
	 */
	public String getProducttype()
	{
		return producttype;
	}

	/**
	 * Method 'setProducttype'
	 * 
	 * @param producttype
	 */
	public void setProducttype(String producttype)
	{
		this.producttype = producttype;
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
		
		if (!(_other instanceof DrDetail)) {
			return false;
		}
		
		final DrDetail _cast = (DrDetail) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (drnumber == null ? _cast.drnumber != drnumber : !drnumber.equals( _cast.drnumber )) {
			return false;
		}
		
		if (productcode == null ? _cast.productcode != productcode : !productcode.equals( _cast.productcode )) {
			return false;
		}
		
		if (qtyrealNull != _cast.qtyrealNull) {
			return false;
		}
		
		if (status == null ? _cast.status != status : !status.equals( _cast.status )) {
			return false;
		}
		
		if (expirydate == null ? _cast.expirydate != expirydate : !expirydate.equals( _cast.expirydate )) {
			return false;
		}
		
		if (remarks == null ? _cast.remarks != remarks : !remarks.equals( _cast.remarks )) {
			return false;
		}
		
		if (qtygood != _cast.qtygood) {
			return false;
		}
		
		if (qtygoodNull != _cast.qtygoodNull) {
			return false;
		}
		
		if (qtydmg != _cast.qtydmg) {
			return false;
		}
		
		if (qtydmgNull != _cast.qtydmgNull) {
			return false;
		}
		
		if (producttype == null ? _cast.producttype != producttype : !producttype.equals( _cast.producttype )) {
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
		
		if (productcode != null) {
			_hashCode = 29 * _hashCode + productcode.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (qtyrealNull ? 1 : 0);
		if (status != null) {
			_hashCode = 29 * _hashCode + status.hashCode();
		}
		
		if (expirydate != null) {
			_hashCode = 29 * _hashCode + expirydate.hashCode();
		}
		
		if (remarks != null) {
			_hashCode = 29 * _hashCode + remarks.hashCode();
		}
		
		_hashCode = 29 * _hashCode + Float.floatToIntBits(qtygood);
		_hashCode = 29 * _hashCode + (qtygoodNull ? 1 : 0);
		_hashCode = 29 * _hashCode + Float.floatToIntBits(qtydmg);
		_hashCode = 29 * _hashCode + (qtydmgNull ? 1 : 0);
		if (producttype != null) {
			_hashCode = 29 * _hashCode + producttype.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return DrDetailPk
	 */
	public DrDetailPk createPk()
	{
		return new DrDetailPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.DrDetail: " );
		ret.append( "id=" + id );
		ret.append( ", drnumber=" + drnumber );
		ret.append( ", productcode=" + productcode );
		ret.append( ", qtyreal=" + qtyreal );
		ret.append( ", status=" + status );
		ret.append( ", expirydate=" + expirydate );
		ret.append( ", remarks=" + remarks );
		ret.append( ", qtygood=" + qtygood );
		ret.append( ", qtydmg=" + qtydmg );
		ret.append( ", producttype=" + producttype );
		return ret.toString();
	}

}

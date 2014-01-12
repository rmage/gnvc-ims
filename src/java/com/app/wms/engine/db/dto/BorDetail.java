package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;

public class BorDetail implements Serializable
{
	/** 
	 * This attribute maps to the column id in the bor_detail table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column bornumber in the bor_detail table.
	 */
	protected String bornumber;

	/** 
	 * This attribute maps to the column productcode in the bor_detail table.
	 */
	protected String productcode;

	/** 
	 * This attribute maps to the column qty in the bor_detail table.
	 */
	protected float qty;

	/** 
	 * This attribute represents whether the primitive attribute qty is null.
	 */
	protected boolean qtyNull = true;

	/** 
	 * This attribute maps to the column producttype in the bor_detail table.
	 */
	protected String producttype;

	/**
	 * Method 'BorDetail'
	 * 
	 */
	public BorDetail()
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
	 * Method 'getBornumber'
	 * 
	 * @return String
	 */
	public String getBornumber()
	{
		return bornumber;
	}

	/**
	 * Method 'setBornumber'
	 * 
	 * @param bornumber
	 */
	public void setBornumber(String bornumber)
	{
		this.bornumber = bornumber;
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
	 * Method 'getQty'
	 * 
	 * @return float
	 */
	public float getQty()
	{
		return qty;
	}

	/**
	 * Method 'setQty'
	 * 
	 * @param qty
	 */
	public void setQty(float qty)
	{
		this.qty = qty;
		this.qtyNull = false;
	}

	/**
	 * Method 'setQtyNull'
	 * 
	 * @param value
	 */
	public void setQtyNull(boolean value)
	{
		this.qtyNull = value;
	}

	/**
	 * Method 'isQtyNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtyNull()
	{
		return qtyNull;
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
		
		if (!(_other instanceof BorDetail)) {
			return false;
		}
		
		final BorDetail _cast = (BorDetail) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (bornumber == null ? _cast.bornumber != bornumber : !bornumber.equals( _cast.bornumber )) {
			return false;
		}
		
		if (productcode == null ? _cast.productcode != productcode : !productcode.equals( _cast.productcode )) {
			return false;
		}
		
		if (qty != _cast.qty) {
			return false;
		}
		
		if (qtyNull != _cast.qtyNull) {
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
		if (bornumber != null) {
			_hashCode = 29 * _hashCode + bornumber.hashCode();
		}
		
		if (productcode != null) {
			_hashCode = 29 * _hashCode + productcode.hashCode();
		}
		
		_hashCode = 29 * _hashCode + Float.floatToIntBits(qty);
		_hashCode = 29 * _hashCode + (qtyNull ? 1 : 0);
		if (producttype != null) {
			_hashCode = 29 * _hashCode + producttype.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return BorDetailPk
	 */
	public BorDetailPk createPk()
	{
		return new BorDetailPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.BorDetail: " );
		ret.append( "id=" + id );
		ret.append( ", bornumber=" + bornumber );
		ret.append( ", productcode=" + productcode );
		ret.append( ", qty=" + qty );
		ret.append( ", producttype=" + producttype );
		return ret.toString();
	}

}

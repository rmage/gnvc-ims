package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class TsDetail implements Serializable
{
	/** 
	 * This attribute maps to the column id in the ts_detail table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column tsnumber in the ts_detail table.
	 */
	protected String tsnumber;

	/** 
	 * This attribute maps to the column productcode in the ts_detail table.
	 */
	protected String productcode;

	/** 
	 * This attribute maps to the column product_name in the ts_detail table.
	 */
	protected String productName;

	/** 
	 * This attribute maps to the column product_category in the ts_detail table.
	 */
	protected String productCategory;

	/** 
	 * This attribute maps to the column uom_name in the ts_detail table.
	 */
	protected String uomName;

	/** 
	 * This attribute maps to the column wh_code in the ts_detail table.
	 */
	protected String whCode;

	/** 
	 * This attribute maps to the column balance in the ts_detail table.
	 */
	protected BigDecimal balance;

	/** 
	 * This attribute represents whether the primitive attribute balance is null.
	 */
	protected boolean balanceNull = true;

	/** 
	 * This attribute maps to the column qtyrequest in the ts_detail table.
	 */
	protected BigDecimal qtyrequest;

	/** 
	 * This attribute represents whether the primitive attribute qtyrequest is null.
	 */
	protected boolean qtyrequestNull = true;

	/**
	 * Method 'TsDetail'
	 * 
	 */
	public TsDetail()
	{
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getQtyrequest() {
		return qtyrequest;
	}

	public void setQtyrequest(BigDecimal qtyrequest) {
		this.qtyrequest = qtyrequest;
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
	 * Method 'getProductName'
	 * 
	 * @return String
	 */
	public String getProductName()
	{
		return productName;
	}

	/**
	 * Method 'setProductName'
	 * 
	 * @param productName
	 */
	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	/**
	 * Method 'getProductCategory'
	 * 
	 * @return String
	 */
	public String getProductCategory()
	{
		return productCategory;
	}

	/**
	 * Method 'setProductCategory'
	 * 
	 * @param productCategory
	 */
	public void setProductCategory(String productCategory)
	{
		this.productCategory = productCategory;
	}

	/**
	 * Method 'getUomName'
	 * 
	 * @return String
	 */
	public String getUomName()
	{
		return uomName;
	}

	/**
	 * Method 'setUomName'
	 * 
	 * @param uomName
	 */
	public void setUomName(String uomName)
	{
		this.uomName = uomName;
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
	 * Method 'setBalanceNull'
	 * 
	 * @param value
	 */
	public void setBalanceNull(boolean value)
	{
		this.balanceNull = value;
	}

	/**
	 * Method 'isBalanceNull'
	 * 
	 * @return boolean
	 */
	public boolean isBalanceNull()
	{
		return balanceNull;
	}

	/**
	 * Method 'setQtyrequestNull'
	 * 
	 * @param value
	 */
	public void setQtyrequestNull(boolean value)
	{
		this.qtyrequestNull = value;
	}

	/**
	 * Method 'isQtyrequestNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtyrequestNull()
	{
		return qtyrequestNull;
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
		
		if (!(_other instanceof TsDetail)) {
			return false;
		}
		
		final TsDetail _cast = (TsDetail) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (tsnumber == null ? _cast.tsnumber != tsnumber : !tsnumber.equals( _cast.tsnumber )) {
			return false;
		}
		
		if (productcode == null ? _cast.productcode != productcode : !productcode.equals( _cast.productcode )) {
			return false;
		}
		
		if (productName == null ? _cast.productName != productName : !productName.equals( _cast.productName )) {
			return false;
		}
		
		if (productCategory == null ? _cast.productCategory != productCategory : !productCategory.equals( _cast.productCategory )) {
			return false;
		}
		
		if (uomName == null ? _cast.uomName != uomName : !uomName.equals( _cast.uomName )) {
			return false;
		}
		
		if (whCode == null ? _cast.whCode != whCode : !whCode.equals( _cast.whCode )) {
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
		
		if (productcode != null) {
			_hashCode = 29 * _hashCode + productcode.hashCode();
		}
		
		if (productName != null) {
			_hashCode = 29 * _hashCode + productName.hashCode();
		}
		
		if (productCategory != null) {
			_hashCode = 29 * _hashCode + productCategory.hashCode();
		}
		
		if (uomName != null) {
			_hashCode = 29 * _hashCode + uomName.hashCode();
		}
		
		if (whCode != null) {
			_hashCode = 29 * _hashCode + whCode.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return TsDetailPk
	 */
	public TsDetailPk createPk()
	{
		return new TsDetailPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.TsDetail: " );
		ret.append( "id=" + id );
		ret.append( ", tsnumber=" + tsnumber );
		ret.append( ", productcode=" + productcode );
		ret.append( ", productName=" + productName );
		ret.append( ", productCategory=" + productCategory );
		ret.append( ", uomName=" + uomName );
		ret.append( ", whCode=" + whCode );
		ret.append( ", balance=" + balance );
		ret.append( ", qtyrequest=" + qtyrequest );
		return ret.toString();
	}

}

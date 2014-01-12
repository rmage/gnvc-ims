package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class SwsDetail implements Serializable
{
	/** 
	 * This attribute maps to the column id in the sws_detail table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column swsnumber in the sws_detail table.
	 */
	protected String swsnumber;

	/** 
	 * This attribute maps to the column productcode in the sws_detail table.
	 */
	protected String productcode;

	/** 
	 * This attribute maps to the column qty in the sws_detail table.
	 */
	//protected float qty;
	protected BigDecimal qty;

	/** 
	 * This attribute represents whether the primitive attribute qty is null.
	 */
	protected boolean qtyNull = true;

	/** 
	 * This attribute maps to the column producttype in the sws_detail table.
	 */
	protected String producttype;
	
	protected Product product;
	
	protected StockInventory stockInventory;

	/**
	 * Method 'SwsDetail'
	 * 
	 */
	public SwsDetail()
	{
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public StockInventory getStockInventory() {
		return stockInventory;
	}

	public void setStockInventory(StockInventory stockInventory) {
		this.stockInventory = stockInventory;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
//	public float getQty()
//	{
//		return qty;
//	}

	/**
	 * Method 'setQty'
	 * 
	 * @param qty
	 */
//	public void setQty(float qty)
//	{
//		this.qty = qty;
//		this.qtyNull = false;
//	}

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
		
		if (!(_other instanceof SwsDetail)) {
			return false;
		}
		
		final SwsDetail _cast = (SwsDetail) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (swsnumber == null ? _cast.swsnumber != swsnumber : !swsnumber.equals( _cast.swsnumber )) {
			return false;
		}
		
		if (productcode == null ? _cast.productcode != productcode : !productcode.equals( _cast.productcode )) {
			return false;
		}
		
//		if (qty != _cast.qty) {
//			return false;
//		}
		
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
		if (swsnumber != null) {
			_hashCode = 29 * _hashCode + swsnumber.hashCode();
		}
		
		if (productcode != null) {
			_hashCode = 29 * _hashCode + productcode.hashCode();
		}
		
//		_hashCode = 29 * _hashCode + Float.floatToIntBits(qty);
		_hashCode = 29 * _hashCode + (qtyNull ? 1 : 0);
		if (producttype != null) {
			_hashCode = 29 * _hashCode + producttype.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return SwsDetailPk
	 */
	public SwsDetailPk createPk()
	{
		return new SwsDetailPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.SwsDetail: " );
		ret.append( "id=" + id );
		ret.append( ", swsnumber=" + swsnumber );
		ret.append( ", productcode=" + productcode );
		ret.append( ", qty=" + qty );
		ret.append( ", producttype=" + producttype );
		return ret.toString();
	}

}

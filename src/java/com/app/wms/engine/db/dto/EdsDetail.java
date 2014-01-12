package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;

public class EdsDetail implements Serializable
{
	/** 
	 * This attribute maps to the column id in the eds_detail table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column edsnumber in the eds_detail table.
	 */
	protected String edsnumber;

	/** 
	 * This attribute maps to the column packStyle in the eds_detail table.
	 */
	protected String packStyle;

	/** 
	 * This attribute maps to the column canSize in the eds_detail table.
	 */
	protected String canSize;

	/** 
	 * This attribute maps to the column productcode in the eds_detail table.
	 */
	protected String productcode;

	/** 
	 * This attribute maps to the column qtyCases in the eds_detail table.
	 */
	protected float qtyCases;

	/** 
	 * This attribute represents whether the primitive attribute qtyCases is null.
	 */
	protected boolean qtyCasesNull = true;

	/** 
	 * This attribute maps to the column remarks in the eds_detail table.
	 */
	protected String remarks;

	/** 
	 * This attribute maps to the column unitPrice in the eds_detail table.
	 */
	protected int unitPrice;

	/** 
	 * This attribute represents whether the primitive attribute unitPrice is null.
	 */
	protected boolean unitPriceNull = true;

	/** 
	 * This attribute maps to the column amount in the eds_detail table.
	 */
	protected int amount;

	/** 
	 * This attribute represents whether the primitive attribute amount is null.
	 */
	protected boolean amountNull = true;

	/** 
	 * This attribute maps to the column producttype in the eds_detail table.
	 */
	protected String producttype;

	/**
	 * Method 'EdsDetail'
	 * 
	 */
	public EdsDetail()
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
	 * Method 'getEdsnumber'
	 * 
	 * @return String
	 */
	public String getEdsnumber()
	{
		return edsnumber;
	}

	/**
	 * Method 'setEdsnumber'
	 * 
	 * @param edsnumber
	 */
	public void setEdsnumber(String edsnumber)
	{
		this.edsnumber = edsnumber;
	}

	/**
	 * Method 'getPackStyle'
	 * 
	 * @return String
	 */
	public String getPackStyle()
	{
		return packStyle;
	}

	/**
	 * Method 'setPackStyle'
	 * 
	 * @param packStyle
	 */
	public void setPackStyle(String packStyle)
	{
		this.packStyle = packStyle;
	}

	/**
	 * Method 'getCanSize'
	 * 
	 * @return String
	 */
	public String getCanSize()
	{
		return canSize;
	}

	/**
	 * Method 'setCanSize'
	 * 
	 * @param canSize
	 */
	public void setCanSize(String canSize)
	{
		this.canSize = canSize;
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
	 * Method 'getQtyCases'
	 * 
	 * @return float
	 */
	public float getQtyCases()
	{
		return qtyCases;
	}

	/**
	 * Method 'setQtyCases'
	 * 
	 * @param qtyCases
	 */
	public void setQtyCases(float qtyCases)
	{
		this.qtyCases = qtyCases;
		this.qtyCasesNull = false;
	}

	/**
	 * Method 'setQtyCasesNull'
	 * 
	 * @param value
	 */
	public void setQtyCasesNull(boolean value)
	{
		this.qtyCasesNull = value;
	}

	/**
	 * Method 'isQtyCasesNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtyCasesNull()
	{
		return qtyCasesNull;
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
	 * Method 'getUnitPrice'
	 * 
	 * @return int
	 */
	public int getUnitPrice()
	{
		return unitPrice;
	}

	/**
	 * Method 'setUnitPrice'
	 * 
	 * @param unitPrice
	 */
	public void setUnitPrice(int unitPrice)
	{
		this.unitPrice = unitPrice;
		this.unitPriceNull = false;
	}

	/**
	 * Method 'setUnitPriceNull'
	 * 
	 * @param value
	 */
	public void setUnitPriceNull(boolean value)
	{
		this.unitPriceNull = value;
	}

	/**
	 * Method 'isUnitPriceNull'
	 * 
	 * @return boolean
	 */
	public boolean isUnitPriceNull()
	{
		return unitPriceNull;
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
		
		if (!(_other instanceof EdsDetail)) {
			return false;
		}
		
		final EdsDetail _cast = (EdsDetail) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (edsnumber == null ? _cast.edsnumber != edsnumber : !edsnumber.equals( _cast.edsnumber )) {
			return false;
		}
		
		if (packStyle == null ? _cast.packStyle != packStyle : !packStyle.equals( _cast.packStyle )) {
			return false;
		}
		
		if (canSize == null ? _cast.canSize != canSize : !canSize.equals( _cast.canSize )) {
			return false;
		}
		
		if (productcode == null ? _cast.productcode != productcode : !productcode.equals( _cast.productcode )) {
			return false;
		}
		
		if (qtyCases != _cast.qtyCases) {
			return false;
		}
		
		if (qtyCasesNull != _cast.qtyCasesNull) {
			return false;
		}
		
		if (remarks == null ? _cast.remarks != remarks : !remarks.equals( _cast.remarks )) {
			return false;
		}
		
		if (unitPrice != _cast.unitPrice) {
			return false;
		}
		
		if (unitPriceNull != _cast.unitPriceNull) {
			return false;
		}
		
		if (amount != _cast.amount) {
			return false;
		}
		
		if (amountNull != _cast.amountNull) {
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
		if (edsnumber != null) {
			_hashCode = 29 * _hashCode + edsnumber.hashCode();
		}
		
		if (packStyle != null) {
			_hashCode = 29 * _hashCode + packStyle.hashCode();
		}
		
		if (canSize != null) {
			_hashCode = 29 * _hashCode + canSize.hashCode();
		}
		
		if (productcode != null) {
			_hashCode = 29 * _hashCode + productcode.hashCode();
		}
		
		_hashCode = 29 * _hashCode + Float.floatToIntBits(qtyCases);
		_hashCode = 29 * _hashCode + (qtyCasesNull ? 1 : 0);
		if (remarks != null) {
			_hashCode = 29 * _hashCode + remarks.hashCode();
		}
		
		_hashCode = 29 * _hashCode + unitPrice;
		_hashCode = 29 * _hashCode + (unitPriceNull ? 1 : 0);
		_hashCode = 29 * _hashCode + amount;
		_hashCode = 29 * _hashCode + (amountNull ? 1 : 0);
		if (producttype != null) {
			_hashCode = 29 * _hashCode + producttype.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return EdsDetailPk
	 */
	public EdsDetailPk createPk()
	{
		return new EdsDetailPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.EdsDetail: " );
		ret.append( "id=" + id );
		ret.append( ", edsnumber=" + edsnumber );
		ret.append( ", packStyle=" + packStyle );
		ret.append( ", canSize=" + canSize );
		ret.append( ", productcode=" + productcode );
		ret.append( ", qtyCases=" + qtyCases );
		ret.append( ", remarks=" + remarks );
		ret.append( ", unitPrice=" + unitPrice );
		ret.append( ", amount=" + amount );
		ret.append( ", producttype=" + producttype );
		return ret.toString();
	}

}

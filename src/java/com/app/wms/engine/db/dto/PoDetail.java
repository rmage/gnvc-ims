package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class PoDetail implements Serializable
{
	/** 
	 * This attribute maps to the column id in the po_detail table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column ponumber in the po_detail table.
	 */
	protected String ponumber;

	/** 
	 * This attribute maps to the column productcode in the po_detail table.
	 */
	protected String productcode;

	/** 
	 * This attribute maps to the column qty in the po_detail table.
	 */
	protected BigDecimal qty;

	/** 
	 * This attribute represents whether the primitive attribute qty is null.
	 */
	protected boolean qtyNull = true;

	/** 
	 * This attribute maps to the column producttype in the po_detail table.
	 */
	protected String producttype;

	/** 
	 * This attribute maps to the column unitprice in the po_detail table.
	 */
	protected BigDecimal unitprice;

	/** 
	 * This attribute represents whether the primitive attribute unitprice is null.
	 */
	protected boolean unitpriceNull = true;

	/** 
	 * This attribute maps to the column amount in the po_detail table.
	 */
	protected BigDecimal amount;

	/** 
	 * This attribute represents whether the primitive attribute amount is null.
	 */
	protected boolean amountNull = true;

	/** 
	 * This attribute maps to the column ppn in the po_detail table.
	 */
	protected BigDecimal ppn;

	/** 
	 * This attribute represents whether the primitive attribute ppn is null.
	 */
	protected boolean ppnNull = true;

	/** 
	 * This attribute maps to the column poremarks in the po_detail table.
	 */
	protected String poremarks;

	/** 
	 * This attribute maps to the column currencyCode in the po_detail table.
	 */
	protected String currencyCode;

	/** 
	 * This attribute maps to the column warranty in the po_detail table.
	 */
	protected Date warranty;

	/** 
	 * This attribute maps to the column termpayment in the po_detail table.
	 */
	protected String termpayment;

	/** 
	 * This attribute maps to the column termdelivery in the po_detail table.
	 */
	protected String termdelivery;

	/** 
	 * This attribute maps to the column discount in the po_detail table.
	 */
	protected BigDecimal discount;

	/** 
	 * This attribute represents whether the primitive attribute discount is null.
	 */
	protected boolean discountNull = true;

	/** 
	 * This attribute maps to the column pph in the po_detail table.
	 */
	protected BigDecimal pph;

	/** 
	 * This attribute represents whether the primitive attribute pph is null.
	 */
	protected boolean pphNull = true;

	/** 
	 * This attribute maps to the column total in the po_detail table.
	 */
	protected BigDecimal total;

	/** 
	 * This attribute represents whether the primitive attribute total is null.
	 */
	protected boolean totalNull = true;
	
	protected Po po;

	/**
	 * Method 'PoDetail'
	 * 
	 */
	public PoDetail()
	{
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPpn() {
		return ppn;
	}

	public void setPpn(BigDecimal ppn) {
		this.ppn = ppn;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getPph() {
		return pph;
	}

	public void setPph(BigDecimal pph) {
		this.pph = pph;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Po getPo() {
		return po;
	}

	public void setPo(Po po) {
		this.po = po;
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
	 * Method 'getPonumber'
	 * 
	 * @return String
	 */
	public String getPonumber()
	{
		return ponumber;
	}

	/**
	 * Method 'setPonumber'
	 * 
	 * @param ponumber
	 */
	public void setPonumber(String ponumber)
	{
		this.ponumber = ponumber;
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
	 * Method 'setUnitpriceNull'
	 * 
	 * @param value
	 */
	public void setUnitpriceNull(boolean value)
	{
		this.unitpriceNull = value;
	}

	/**
	 * Method 'isUnitpriceNull'
	 * 
	 * @return boolean
	 */
	public boolean isUnitpriceNull()
	{
		return unitpriceNull;
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
	 * Method 'setPpnNull'
	 * 
	 * @param value
	 */
	public void setPpnNull(boolean value)
	{
		this.ppnNull = value;
	}

	/**
	 * Method 'isPpnNull'
	 * 
	 * @return boolean
	 */
	public boolean isPpnNull()
	{
		return ppnNull;
	}

	/**
	 * Method 'getPoremarks'
	 * 
	 * @return String
	 */
	public String getPoremarks()
	{
		return poremarks;
	}

	/**
	 * Method 'setPoremarks'
	 * 
	 * @param poremarks
	 */
	public void setPoremarks(String poremarks)
	{
		this.poremarks = poremarks;
	}

	/**
	 * Method 'getCurrencyCode'
	 * 
	 * @return String
	 */
	public String getCurrencyCode()
	{
		return currencyCode;
	}

	/**
	 * Method 'setCurrencyCode'
	 * 
	 * @param currencyCode
	 */
	public void setCurrencyCode(String currencyCode)
	{
		this.currencyCode = currencyCode;
	}

	/**
	 * Method 'getWarranty'
	 * 
	 * @return Date
	 */
	public Date getWarranty()
	{
		return warranty;
	}

	/**
	 * Method 'setWarranty'
	 * 
	 * @param warranty
	 */
	public void setWarranty(Date warranty)
	{
		this.warranty = warranty;
	}

	/**
	 * Method 'getTermpayment'
	 * 
	 * @return String
	 */
	public String getTermpayment()
	{
		return termpayment;
	}

	/**
	 * Method 'setTermpayment'
	 * 
	 * @param termpayment
	 */
	public void setTermpayment(String termpayment)
	{
		this.termpayment = termpayment;
	}

	/**
	 * Method 'getTermdelivery'
	 * 
	 * @return String
	 */
	public String getTermdelivery()
	{
		return termdelivery;
	}

	/**
	 * Method 'setTermdelivery'
	 * 
	 * @param termdelivery
	 */
	public void setTermdelivery(String termdelivery)
	{
		this.termdelivery = termdelivery;
	}

	/**
	 * Method 'setDiscountNull'
	 * 
	 * @param value
	 */
	public void setDiscountNull(boolean value)
	{
		this.discountNull = value;
	}

	/**
	 * Method 'isDiscountNull'
	 * 
	 * @return boolean
	 */
	public boolean isDiscountNull()
	{
		return discountNull;
	}

	/**
	 * Method 'setPphNull'
	 * 
	 * @param value
	 */
	public void setPphNull(boolean value)
	{
		this.pphNull = value;
	}

	/**
	 * Method 'isPphNull'
	 * 
	 * @return boolean
	 */
	public boolean isPphNull()
	{
		return pphNull;
	}

	/**
	 * Method 'setTotalNull'
	 * 
	 * @param value
	 */
	public void setTotalNull(boolean value)
	{
		this.totalNull = value;
	}

	/**
	 * Method 'isTotalNull'
	 * 
	 * @return boolean
	 */
	public boolean isTotalNull()
	{
		return totalNull;
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
		
		if (!(_other instanceof PoDetail)) {
			return false;
		}
		
		final PoDetail _cast = (PoDetail) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (ponumber == null ? _cast.ponumber != ponumber : !ponumber.equals( _cast.ponumber )) {
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
		
		if (unitprice != _cast.unitprice) {
			return false;
		}
		
		if (unitpriceNull != _cast.unitpriceNull) {
			return false;
		}
		
		if (amount != _cast.amount) {
			return false;
		}
		
		if (amountNull != _cast.amountNull) {
			return false;
		}
		
		if (ppn != _cast.ppn) {
			return false;
		}
		
		if (ppnNull != _cast.ppnNull) {
			return false;
		}
		
		if (poremarks == null ? _cast.poremarks != poremarks : !poremarks.equals( _cast.poremarks )) {
			return false;
		}
		
		if (currencyCode == null ? _cast.currencyCode != currencyCode : !currencyCode.equals( _cast.currencyCode )) {
			return false;
		}
		
		if (warranty == null ? _cast.warranty != warranty : !warranty.equals( _cast.warranty )) {
			return false;
		}
		
		if (termpayment == null ? _cast.termpayment != termpayment : !termpayment.equals( _cast.termpayment )) {
			return false;
		}
		
		if (termdelivery == null ? _cast.termdelivery != termdelivery : !termdelivery.equals( _cast.termdelivery )) {
			return false;
		}
		
		if (discount != _cast.discount) {
			return false;
		}
		
		if (discountNull != _cast.discountNull) {
			return false;
		}
		
		if (pph != _cast.pph) {
			return false;
		}
		
		if (pphNull != _cast.pphNull) {
			return false;
		}
		
		if (total != _cast.total) {
			return false;
		}
		
		if (totalNull != _cast.totalNull) {
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
		if (ponumber != null) {
			_hashCode = 29 * _hashCode + ponumber.hashCode();
		}
		
		if (productcode != null) {
			_hashCode = 29 * _hashCode + productcode.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (qtyNull ? 1 : 0);
		if (producttype != null) {
			_hashCode = 29 * _hashCode + producttype.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (unitpriceNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (amountNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (ppnNull ? 1 : 0);
		if (poremarks != null) {
			_hashCode = 29 * _hashCode + poremarks.hashCode();
		}
		
		if (currencyCode != null) {
			_hashCode = 29 * _hashCode + currencyCode.hashCode();
		}
		
		if (warranty != null) {
			_hashCode = 29 * _hashCode + warranty.hashCode();
		}
		
		if (termpayment != null) {
			_hashCode = 29 * _hashCode + termpayment.hashCode();
		}
		
		if (termdelivery != null) {
			_hashCode = 29 * _hashCode + termdelivery.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (discountNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (pphNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (totalNull ? 1 : 0);
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return PoDetailPk
	 */
	public PoDetailPk createPk()
	{
		return new PoDetailPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.PoDetail: " );
		ret.append( "id=" + id );
		ret.append( ", ponumber=" + ponumber );
		ret.append( ", productcode=" + productcode );
		ret.append( ", qty=" + qty );
		ret.append( ", producttype=" + producttype );
		ret.append( ", unitprice=" + unitprice );
		ret.append( ", amount=" + amount );
		ret.append( ", ppn=" + ppn );
		ret.append( ", poremarks=" + poremarks );
		ret.append( ", currencyCode=" + currencyCode );
		ret.append( ", warranty=" + warranty );
		ret.append( ", termpayment=" + termpayment );
		ret.append( ", termdelivery=" + termdelivery );
		ret.append( ", discount=" + discount );
		ret.append( ", pph=" + pph );
		ret.append( ", total=" + total );
		ret.append( ", po=" + po );
		return ret.toString();
	}

}

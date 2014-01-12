package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class CanvassingDetail implements Serializable
{
	/** 
	 * This attribute maps to the column id in the canvassing_detail table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column prsnumber in the canvassing_detail table.
	 */
	protected String prsnumber;

	/** 
	 * This attribute maps to the column supplier_code in the canvassing_detail table.
	 */
	protected String supplierCode;

	/** 
	 * This attribute maps to the column productcode in the canvassing_detail table.
	 */
	protected String productcode;

	/** 
	 * This attribute maps to the column productname in the canvassing_detail table.
	 */
	protected String productname;

	/** 
	 * This attribute maps to the column priceunit in the canvassing_detail table.
	 */
	//protected float priceunit;
	protected BigDecimal priceunit;

	/** 
	 * This attribute represents whether the primitive attribute priceunit is null.
	 */
	protected boolean priceunitNull = true;

	/** 
	 * This attribute maps to the column warranty in the canvassing_detail table.
	 */
	protected Date warranty;

	/** 
	 * This attribute maps to the column termpayment in the canvassing_detail table.
	 */
	protected String termpayment;

	/** 
	 * This attribute maps to the column termdelivery in the canvassing_detail table.
	 */
	protected String termdelivery;

	/** 
	 * This attribute maps to the column discount in the canvassing_detail table.
	 */
	//protected float discount;
	protected BigDecimal discount;

	/** 
	 * This attribute represents whether the primitive attribute discount is null.
	 */
	protected boolean discountNull = true;

	/** 
	 * This attribute maps to the column pph in the canvassing_detail table.
	 */
	//protected float pph;
	protected BigDecimal pph;

	/** 
	 * This attribute represents whether the primitive attribute pph is null.
	 */
	protected boolean pphNull = true;

	/** 
	 * This attribute maps to the column ppn in the canvassing_detail table.
	 */
	//protected float ppn;
	protected BigDecimal ppn;
	
	/** 
	 * This attribute represents whether the primitive attribute ppn is null.
	 */
	protected boolean ppnNull = true;

	/** 
	 * This attribute maps to the column is_selected in the canvassing_detail table.
	 */
	protected String isSelected;
	
	protected Date canvassingdate;
	
	protected PrsDetail prsDetail;

	/**
	 * Method 'CanvassingDetail'
	 * 
	 */
	public CanvassingDetail()
	{
	}

	public Date getCanvassingdate() {
		return canvassingdate;
	}

	public void setCanvassingdate(Date canvassingdate) {
		this.canvassingdate = canvassingdate;
	}

	public PrsDetail getPrsDetail() {
		return prsDetail;
	}

	public void setPrsDetail(PrsDetail prsDetail) {
		this.prsDetail = prsDetail;
	}

	public BigDecimal getPriceunit() {
		return priceunit;
	}

	public void setPriceunit(BigDecimal priceunit) {
		this.priceunit = priceunit;
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

	public BigDecimal getPpn() {
		return ppn;
	}

	public void setPpn(BigDecimal ppn) {
		this.ppn = ppn;
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
	 * Method 'getSupplierCode'
	 * 
	 * @return String
	 */
	public String getSupplierCode()
	{
		return supplierCode;
	}

	/**
	 * Method 'setSupplierCode'
	 * 
	 * @param supplierCode
	 */
	public void setSupplierCode(String supplierCode)
	{
		this.supplierCode = supplierCode;
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
	 * Method 'getProductname'
	 * 
	 * @return String
	 */
	public String getProductname()
	{
		return productname;
	}

	/**
	 * Method 'setProductname'
	 * 
	 * @param productname
	 */
	public void setProductname(String productname)
	{
		this.productname = productname;
	}

	/**
	 * Method 'getPriceunit'
	 * 
	 * @return float
	 */
//	public float getPriceunit()
//	{
//		return priceunit;
//	}

	/**
	 * Method 'setPriceunit'
	 * 
	 * @param priceunit
	 */
//	public void setPriceunit(float priceunit)
//	{
//		this.priceunit = priceunit;
//		this.priceunitNull = false;
//	}

	/**
	 * Method 'setPriceunitNull'
	 * 
	 * @param value
	 */
	public void setPriceunitNull(boolean value)
	{
		this.priceunitNull = value;
	}

	/**
	 * Method 'isPriceunitNull'
	 * 
	 * @return boolean
	 */
	public boolean isPriceunitNull()
	{
		return priceunitNull;
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
	 * Method 'getDiscount'
	 * 
	 * @return float
	 */
//	public float getDiscount()
//	{
//		return discount;
//	}

	/**
	 * Method 'setDiscount'
	 * 
	 * @param discount
	 */
//	public void setDiscount(float discount)
//	{
//		this.discount = discount;
//		this.discountNull = false;
//	}

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
	 * Method 'getPph'
	 * 
	 * @return float
	 */
//	public float getPph()
//	{
//		return pph;
//	}

	/**
	 * Method 'setPph'
	 * 
	 * @param pph
	 */
//	public void setPph(float pph)
//	{
//		this.pph = pph;
//		this.pphNull = false;
//	}

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
	 * Method 'getPpn'
	 * 
	 * @return float
	 */
//	public float getPpn()
//	{
//		return ppn;
//	}

	/**
	 * Method 'setPpn'
	 * 
	 * @param ppn
	 */
//	public void setPpn(float ppn)
//	{
//		this.ppn = ppn;
//		this.ppnNull = false;
//	}

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
	 * Method 'getIsSelected'
	 * 
	 * @return String
	 */
	public String getIsSelected()
	{
		return isSelected;
	}

	/**
	 * Method 'setIsSelected'
	 * 
	 * @param isSelected
	 */
	public void setIsSelected(String isSelected)
	{
		this.isSelected = isSelected;
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
		
		if (!(_other instanceof CanvassingDetail)) {
			return false;
		}
		
		final CanvassingDetail _cast = (CanvassingDetail) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (prsnumber == null ? _cast.prsnumber != prsnumber : !prsnumber.equals( _cast.prsnumber )) {
			return false;
		}
		
		if (supplierCode == null ? _cast.supplierCode != supplierCode : !supplierCode.equals( _cast.supplierCode )) {
			return false;
		}
		
		if (productcode == null ? _cast.productcode != productcode : !productcode.equals( _cast.productcode )) {
			return false;
		}
		
		if (productname == null ? _cast.productname != productname : !productname.equals( _cast.productname )) {
			return false;
		}
		
//		if (priceunit != _cast.priceunit) {
//			return false;
//		}
		
		if (priceunitNull != _cast.priceunitNull) {
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
		
//		if (discount != _cast.discount) {
//			return false;
//		}
		
		if (discountNull != _cast.discountNull) {
			return false;
		}
		
//		if (pph != _cast.pph) {
//			return false;
//		}
		
		if (pphNull != _cast.pphNull) {
			return false;
		}
		
//		if (ppn != _cast.ppn) {
//			return false;
//		}
		
		if (ppnNull != _cast.ppnNull) {
			return false;
		}
		
		if (isSelected == null ? _cast.isSelected != isSelected : !isSelected.equals( _cast.isSelected )) {
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
		if (prsnumber != null) {
			_hashCode = 29 * _hashCode + prsnumber.hashCode();
		}
		
		if (supplierCode != null) {
			_hashCode = 29 * _hashCode + supplierCode.hashCode();
		}
		
		if (productcode != null) {
			_hashCode = 29 * _hashCode + productcode.hashCode();
		}
		
		if (productname != null) {
			_hashCode = 29 * _hashCode + productname.hashCode();
		}
		
//		_hashCode = 29 * _hashCode + Float.floatToIntBits(priceunit);
		_hashCode = 29 * _hashCode + (priceunitNull ? 1 : 0);
		if (warranty != null) {
			_hashCode = 29 * _hashCode + warranty.hashCode();
		}
		
		if (termpayment != null) {
			_hashCode = 29 * _hashCode + termpayment.hashCode();
		}
		
		if (termdelivery != null) {
			_hashCode = 29 * _hashCode + termdelivery.hashCode();
		}
		
//		_hashCode = 29 * _hashCode + Float.floatToIntBits(discount);
		_hashCode = 29 * _hashCode + (discountNull ? 1 : 0);
//		_hashCode = 29 * _hashCode + Float.floatToIntBits(pph);
		_hashCode = 29 * _hashCode + (pphNull ? 1 : 0);
//		_hashCode = 29 * _hashCode + Float.floatToIntBits(ppn);
		_hashCode = 29 * _hashCode + (ppnNull ? 1 : 0);
		if (isSelected != null) {
			_hashCode = 29 * _hashCode + isSelected.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return CanvassingDetailPk
	 */
	public CanvassingDetailPk createPk()
	{
		return new CanvassingDetailPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.CanvassingDetail: " );
		ret.append( "id=" + id );
		ret.append( ", prsnumber=" + prsnumber );
		ret.append( ", supplierCode=" + supplierCode );
		ret.append( ", productcode=" + productcode );
		ret.append( ", productname=" + productname );
		ret.append( ", priceunit=" + priceunit );
		ret.append( ", warranty=" + warranty );
		ret.append( ", termpayment=" + termpayment );
		ret.append( ", termdelivery=" + termdelivery );
		ret.append( ", discount=" + discount );
		ret.append( ", pph=" + pph );
		ret.append( ", ppn=" + ppn );
		ret.append( ", isSelected=" + isSelected );
		return ret.toString();
	}

}

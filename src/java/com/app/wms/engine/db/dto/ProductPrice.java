package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class ProductPrice implements Serializable
{
	/** 
	 * This attribute maps to the column id in the product_price table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column product_code in the product_price table.
	 */
	protected String productCode;

	/** 
	 * This attribute maps to the column catalog_code in the product_price table.
	 */
	protected String catalogCode;

	/** 
	 * This attribute maps to the column base_price in the product_price table.
	 */
	protected float basePrice;

	/** 
	 * This attribute represents whether the primitive attribute basePrice is null.
	 */
	protected boolean basePriceNull = true;

	/** 
	 * This attribute maps to the column discount_percent in the product_price table.
	 */
	protected float discountPercent;

	/** 
	 * This attribute represents whether the primitive attribute discountPercent is null.
	 */
	protected boolean discountPercentNull = true;

	/** 
	 * This attribute maps to the column discount_price in the product_price table.
	 */
	protected float discountPrice;

	/** 
	 * This attribute represents whether the primitive attribute discountPrice is null.
	 */
	protected boolean discountPriceNull = true;

	/** 
	 * This attribute maps to the column value_price in the product_price table.
	 */
	protected float valuePrice;

	/** 
	 * This attribute represents whether the primitive attribute valuePrice is null.
	 */
	protected boolean valuePriceNull = true;

	/** 
	 * This attribute maps to the column base_vat in the product_price table.
	 */
	protected float baseVat;

	/** 
	 * This attribute represents whether the primitive attribute baseVat is null.
	 */
	protected boolean baseVatNull = true;

	/** 
	 * This attribute maps to the column percent_vat in the product_price table.
	 */
	protected float percentVat;

	/** 
	 * This attribute represents whether the primitive attribute percentVat is null.
	 */
	protected boolean percentVatNull = true;

	/** 
	 * This attribute maps to the column value_vat in the product_price table.
	 */
	protected float valueVat;

	/** 
	 * This attribute represents whether the primitive attribute valueVat is null.
	 */
	protected boolean valueVatNull = true;

	/** 
	 * This attribute maps to the column customer_price in the product_price table.
	 */
	protected float customerPrice;

	/** 
	 * This attribute represents whether the primitive attribute customerPrice is null.
	 */
	protected boolean customerPriceNull = true;

	/** 
	 * This attribute maps to the column vendor_price in the product_price table.
	 */
	protected float vendorPrice;

	/** 
	 * This attribute represents whether the primitive attribute vendorPrice is null.
	 */
	protected boolean vendorPriceNull = true;

	/** 
	 * This attribute maps to the column is_active in the product_price table.
	 */
	protected String isActive;

	/** 
	 * This attribute maps to the column is_delete in the product_price table.
	 */
	protected String isDelete;

	/** 
	 * This attribute maps to the column created_by in the product_price table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the product_price table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the product_price table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the product_price table.
	 */
	protected Date updatedDate;

	/**
	 * Method 'ProductPrice'
	 * 
	 */
	public ProductPrice()
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
	 * Method 'getProductCode'
	 * 
	 * @return String
	 */
	public String getProductCode()
	{
		return productCode;
	}

	/**
	 * Method 'setProductCode'
	 * 
	 * @param productCode
	 */
	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}

	/**
	 * Method 'getCatalogCode'
	 * 
	 * @return String
	 */
	public String getCatalogCode()
	{
		return catalogCode;
	}

	/**
	 * Method 'setCatalogCode'
	 * 
	 * @param catalogCode
	 */
	public void setCatalogCode(String catalogCode)
	{
		this.catalogCode = catalogCode;
	}

	/**
	 * Method 'getBasePrice'
	 * 
	 * @return float
	 */
	public float getBasePrice()
	{
		return basePrice;
	}

	/**
	 * Method 'setBasePrice'
	 * 
	 * @param basePrice
	 */
	public void setBasePrice(float basePrice)
	{
		this.basePrice = basePrice;
		this.basePriceNull = false;
	}

	/**
	 * Method 'setBasePriceNull'
	 * 
	 * @param value
	 */
	public void setBasePriceNull(boolean value)
	{
		this.basePriceNull = value;
	}

	/**
	 * Method 'isBasePriceNull'
	 * 
	 * @return boolean
	 */
	public boolean isBasePriceNull()
	{
		return basePriceNull;
	}

	/**
	 * Method 'getDiscountPercent'
	 * 
	 * @return float
	 */
	public float getDiscountPercent()
	{
		return discountPercent;
	}

	/**
	 * Method 'setDiscountPercent'
	 * 
	 * @param discountPercent
	 */
	public void setDiscountPercent(float discountPercent)
	{
		this.discountPercent = discountPercent;
		this.discountPercentNull = false;
	}

	/**
	 * Method 'setDiscountPercentNull'
	 * 
	 * @param value
	 */
	public void setDiscountPercentNull(boolean value)
	{
		this.discountPercentNull = value;
	}

	/**
	 * Method 'isDiscountPercentNull'
	 * 
	 * @return boolean
	 */
	public boolean isDiscountPercentNull()
	{
		return discountPercentNull;
	}

	/**
	 * Method 'getDiscountPrice'
	 * 
	 * @return float
	 */
	public float getDiscountPrice()
	{
		return discountPrice;
	}

	/**
	 * Method 'setDiscountPrice'
	 * 
	 * @param discountPrice
	 */
	public void setDiscountPrice(float discountPrice)
	{
		this.discountPrice = discountPrice;
		this.discountPriceNull = false;
	}

	/**
	 * Method 'setDiscountPriceNull'
	 * 
	 * @param value
	 */
	public void setDiscountPriceNull(boolean value)
	{
		this.discountPriceNull = value;
	}

	/**
	 * Method 'isDiscountPriceNull'
	 * 
	 * @return boolean
	 */
	public boolean isDiscountPriceNull()
	{
		return discountPriceNull;
	}

	/**
	 * Method 'getValuePrice'
	 * 
	 * @return float
	 */
	public float getValuePrice()
	{
		return valuePrice;
	}

	/**
	 * Method 'setValuePrice'
	 * 
	 * @param valuePrice
	 */
	public void setValuePrice(float valuePrice)
	{
		this.valuePrice = valuePrice;
		this.valuePriceNull = false;
	}

	/**
	 * Method 'setValuePriceNull'
	 * 
	 * @param value
	 */
	public void setValuePriceNull(boolean value)
	{
		this.valuePriceNull = value;
	}

	/**
	 * Method 'isValuePriceNull'
	 * 
	 * @return boolean
	 */
	public boolean isValuePriceNull()
	{
		return valuePriceNull;
	}

	/**
	 * Method 'getBaseVat'
	 * 
	 * @return float
	 */
	public float getBaseVat()
	{
		return baseVat;
	}

	/**
	 * Method 'setBaseVat'
	 * 
	 * @param baseVat
	 */
	public void setBaseVat(float baseVat)
	{
		this.baseVat = baseVat;
		this.baseVatNull = false;
	}

	/**
	 * Method 'setBaseVatNull'
	 * 
	 * @param value
	 */
	public void setBaseVatNull(boolean value)
	{
		this.baseVatNull = value;
	}

	/**
	 * Method 'isBaseVatNull'
	 * 
	 * @return boolean
	 */
	public boolean isBaseVatNull()
	{
		return baseVatNull;
	}

	/**
	 * Method 'getPercentVat'
	 * 
	 * @return float
	 */
	public float getPercentVat()
	{
		return percentVat;
	}

	/**
	 * Method 'setPercentVat'
	 * 
	 * @param percentVat
	 */
	public void setPercentVat(float percentVat)
	{
		this.percentVat = percentVat;
		this.percentVatNull = false;
	}

	/**
	 * Method 'setPercentVatNull'
	 * 
	 * @param value
	 */
	public void setPercentVatNull(boolean value)
	{
		this.percentVatNull = value;
	}

	/**
	 * Method 'isPercentVatNull'
	 * 
	 * @return boolean
	 */
	public boolean isPercentVatNull()
	{
		return percentVatNull;
	}

	/**
	 * Method 'getValueVat'
	 * 
	 * @return float
	 */
	public float getValueVat()
	{
		return valueVat;
	}

	/**
	 * Method 'setValueVat'
	 * 
	 * @param valueVat
	 */
	public void setValueVat(float valueVat)
	{
		this.valueVat = valueVat;
		this.valueVatNull = false;
	}

	/**
	 * Method 'setValueVatNull'
	 * 
	 * @param value
	 */
	public void setValueVatNull(boolean value)
	{
		this.valueVatNull = value;
	}

	/**
	 * Method 'isValueVatNull'
	 * 
	 * @return boolean
	 */
	public boolean isValueVatNull()
	{
		return valueVatNull;
	}

	/**
	 * Method 'getCustomerPrice'
	 * 
	 * @return float
	 */
	public float getCustomerPrice()
	{
		return customerPrice;
	}

	/**
	 * Method 'setCustomerPrice'
	 * 
	 * @param customerPrice
	 */
	public void setCustomerPrice(float customerPrice)
	{
		this.customerPrice = customerPrice;
		this.customerPriceNull = false;
	}

	/**
	 * Method 'setCustomerPriceNull'
	 * 
	 * @param value
	 */
	public void setCustomerPriceNull(boolean value)
	{
		this.customerPriceNull = value;
	}

	/**
	 * Method 'isCustomerPriceNull'
	 * 
	 * @return boolean
	 */
	public boolean isCustomerPriceNull()
	{
		return customerPriceNull;
	}

	/**
	 * Method 'getVendorPrice'
	 * 
	 * @return float
	 */
	public float getVendorPrice()
	{
		return vendorPrice;
	}

	/**
	 * Method 'setVendorPrice'
	 * 
	 * @param vendorPrice
	 */
	public void setVendorPrice(float vendorPrice)
	{
		this.vendorPrice = vendorPrice;
		this.vendorPriceNull = false;
	}

	/**
	 * Method 'setVendorPriceNull'
	 * 
	 * @param value
	 */
	public void setVendorPriceNull(boolean value)
	{
		this.vendorPriceNull = value;
	}

	/**
	 * Method 'isVendorPriceNull'
	 * 
	 * @return boolean
	 */
	public boolean isVendorPriceNull()
	{
		return vendorPriceNull;
	}

	/**
	 * Method 'getIsActive'
	 * 
	 * @return String
	 */
	public String getIsActive()
	{
		return isActive;
	}

	/**
	 * Method 'setIsActive'
	 * 
	 * @param isActive
	 */
	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	/**
	 * Method 'getIsDelete'
	 * 
	 * @return String
	 */
	public String getIsDelete()
	{
		return isDelete;
	}

	/**
	 * Method 'setIsDelete'
	 * 
	 * @param isDelete
	 */
	public void setIsDelete(String isDelete)
	{
		this.isDelete = isDelete;
	}

	/**
	 * Method 'getCreatedBy'
	 * 
	 * @return String
	 */
	public String getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * Method 'setCreatedBy'
	 * 
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	/**
	 * Method 'getCreatedDate'
	 * 
	 * @return Date
	 */
	public Date getCreatedDate()
	{
		return createdDate;
	}

	/**
	 * Method 'setCreatedDate'
	 * 
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	/**
	 * Method 'getUpdatedBy'
	 * 
	 * @return String
	 */
	public String getUpdatedBy()
	{
		return updatedBy;
	}

	/**
	 * Method 'setUpdatedBy'
	 * 
	 * @param updatedBy
	 */
	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	/**
	 * Method 'getUpdatedDate'
	 * 
	 * @return Date
	 */
	public Date getUpdatedDate()
	{
		return updatedDate;
	}

	/**
	 * Method 'setUpdatedDate'
	 * 
	 * @param updatedDate
	 */
	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
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
		
		if (!(_other instanceof ProductPrice)) {
			return false;
		}
		
		final ProductPrice _cast = (ProductPrice) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (productCode == null ? _cast.productCode != productCode : !productCode.equals( _cast.productCode )) {
			return false;
		}
		
		if (catalogCode == null ? _cast.catalogCode != catalogCode : !catalogCode.equals( _cast.catalogCode )) {
			return false;
		}
		
		if (basePrice != _cast.basePrice) {
			return false;
		}
		
		if (basePriceNull != _cast.basePriceNull) {
			return false;
		}
		
		if (discountPercent != _cast.discountPercent) {
			return false;
		}
		
		if (discountPercentNull != _cast.discountPercentNull) {
			return false;
		}
		
		if (discountPrice != _cast.discountPrice) {
			return false;
		}
		
		if (discountPriceNull != _cast.discountPriceNull) {
			return false;
		}
		
		if (valuePrice != _cast.valuePrice) {
			return false;
		}
		
		if (valuePriceNull != _cast.valuePriceNull) {
			return false;
		}
		
		if (baseVat != _cast.baseVat) {
			return false;
		}
		
		if (baseVatNull != _cast.baseVatNull) {
			return false;
		}
		
		if (percentVat != _cast.percentVat) {
			return false;
		}
		
		if (percentVatNull != _cast.percentVatNull) {
			return false;
		}
		
		if (valueVat != _cast.valueVat) {
			return false;
		}
		
		if (valueVatNull != _cast.valueVatNull) {
			return false;
		}
		
		if (customerPrice != _cast.customerPrice) {
			return false;
		}
		
		if (customerPriceNull != _cast.customerPriceNull) {
			return false;
		}
		
		if (vendorPrice != _cast.vendorPrice) {
			return false;
		}
		
		if (vendorPriceNull != _cast.vendorPriceNull) {
			return false;
		}
		
		if (isActive == null ? _cast.isActive != isActive : !isActive.equals( _cast.isActive )) {
			return false;
		}
		
		if (isDelete == null ? _cast.isDelete != isDelete : !isDelete.equals( _cast.isDelete )) {
			return false;
		}
		
		if (createdBy == null ? _cast.createdBy != createdBy : !createdBy.equals( _cast.createdBy )) {
			return false;
		}
		
		if (createdDate == null ? _cast.createdDate != createdDate : !createdDate.equals( _cast.createdDate )) {
			return false;
		}
		
		if (updatedBy == null ? _cast.updatedBy != updatedBy : !updatedBy.equals( _cast.updatedBy )) {
			return false;
		}
		
		if (updatedDate == null ? _cast.updatedDate != updatedDate : !updatedDate.equals( _cast.updatedDate )) {
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
		if (productCode != null) {
			_hashCode = 29 * _hashCode + productCode.hashCode();
		}
		
		if (catalogCode != null) {
			_hashCode = 29 * _hashCode + catalogCode.hashCode();
		}
		
		_hashCode = 29 * _hashCode + Float.floatToIntBits(basePrice);
		_hashCode = 29 * _hashCode + (basePriceNull ? 1 : 0);
		_hashCode = 29 * _hashCode + Float.floatToIntBits(discountPercent);
		_hashCode = 29 * _hashCode + (discountPercentNull ? 1 : 0);
		_hashCode = 29 * _hashCode + Float.floatToIntBits(discountPrice);
		_hashCode = 29 * _hashCode + (discountPriceNull ? 1 : 0);
		_hashCode = 29 * _hashCode + Float.floatToIntBits(valuePrice);
		_hashCode = 29 * _hashCode + (valuePriceNull ? 1 : 0);
		_hashCode = 29 * _hashCode + Float.floatToIntBits(baseVat);
		_hashCode = 29 * _hashCode + (baseVatNull ? 1 : 0);
		_hashCode = 29 * _hashCode + Float.floatToIntBits(percentVat);
		_hashCode = 29 * _hashCode + (percentVatNull ? 1 : 0);
		_hashCode = 29 * _hashCode + Float.floatToIntBits(valueVat);
		_hashCode = 29 * _hashCode + (valueVatNull ? 1 : 0);
		_hashCode = 29 * _hashCode + Float.floatToIntBits(customerPrice);
		_hashCode = 29 * _hashCode + (customerPriceNull ? 1 : 0);
		_hashCode = 29 * _hashCode + Float.floatToIntBits(vendorPrice);
		_hashCode = 29 * _hashCode + (vendorPriceNull ? 1 : 0);
		if (isActive != null) {
			_hashCode = 29 * _hashCode + isActive.hashCode();
		}
		
		if (isDelete != null) {
			_hashCode = 29 * _hashCode + isDelete.hashCode();
		}
		
		if (createdBy != null) {
			_hashCode = 29 * _hashCode + createdBy.hashCode();
		}
		
		if (createdDate != null) {
			_hashCode = 29 * _hashCode + createdDate.hashCode();
		}
		
		if (updatedBy != null) {
			_hashCode = 29 * _hashCode + updatedBy.hashCode();
		}
		
		if (updatedDate != null) {
			_hashCode = 29 * _hashCode + updatedDate.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return ProductPricePk
	 */
	public ProductPricePk createPk()
	{
		return new ProductPricePk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.ProductPrice: " );
		ret.append( "id=" + id );
		ret.append( ", productCode=" + productCode );
		ret.append( ", catalogCode=" + catalogCode );
		ret.append( ", basePrice=" + basePrice );
		ret.append( ", discountPercent=" + discountPercent );
		ret.append( ", discountPrice=" + discountPrice );
		ret.append( ", valuePrice=" + valuePrice );
		ret.append( ", baseVat=" + baseVat );
		ret.append( ", percentVat=" + percentVat );
		ret.append( ", valueVat=" + valueVat );
		ret.append( ", customerPrice=" + customerPrice );
		ret.append( ", vendorPrice=" + vendorPrice );
		ret.append( ", isActive=" + isActive );
		ret.append( ", isDelete=" + isDelete );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}

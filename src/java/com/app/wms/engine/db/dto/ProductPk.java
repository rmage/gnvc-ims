package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the product table.
 */
public class ProductPk implements Serializable
{
	//protected int id;
	protected String productId;

	/** 
	 * This attribute represents whether the primitive attribute id is null.
	 */
	protected boolean idNull;

	/**
	 * Method 'ProductPk'
	 * 
	 */
	public ProductPk()
	{
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * Method 'ProductPk'
	 * 
	 * @param id
	 */
	public ProductPk(final String productId)
	{
		this.productId = productId;
	}

	/** 
	 * Sets the value of idNull
	 */
	public void setIdNull(boolean idNull)
	{
		this.idNull = idNull;
	}

	/** 
	 * Gets the value of idNull
	 */
	public boolean isIdNull()
	{
		return idNull;
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
		
		if (!(_other instanceof ProductPk)) {
			return false;
		}
		
		final ProductPk _cast = (ProductPk) _other;
		if (productId != _cast.productId) {
			return false;
		}
		
		if (idNull != _cast.idNull) {
			return false;
		}
		
		return true;
	}


	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.ProductPk: " );
		ret.append( "productId=" + productId );
		return ret.toString();
	}

}

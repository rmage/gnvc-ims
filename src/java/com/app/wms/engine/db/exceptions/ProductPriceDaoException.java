package com.app.wms.engine.db.exceptions;

public class ProductPriceDaoException extends DaoException
{
	/**
	 * Method 'ProductPriceDaoException'
	 * 
	 * @param message
	 */
	public ProductPriceDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'ProductPriceDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public ProductPriceDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

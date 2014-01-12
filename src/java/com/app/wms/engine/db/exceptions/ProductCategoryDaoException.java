package com.app.wms.engine.db.exceptions;

public class ProductCategoryDaoException extends DaoException
{
	/**
	 * Method 'ProductCategoryDaoException'
	 * 
	 * @param message
	 */
	public ProductCategoryDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'ProductCategoryDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public ProductCategoryDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

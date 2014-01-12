package com.app.wms.engine.db.exceptions;

public class ProductDaoException extends DaoException{
	
	/**
	 * Method 'ProductDaoException'
	 * 
	 * @param message
	 */
	public ProductDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'ProductDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public ProductDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}


}

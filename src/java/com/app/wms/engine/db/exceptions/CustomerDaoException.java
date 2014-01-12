package com.app.wms.engine.db.exceptions;

public class CustomerDaoException extends DaoException
{
	/**
	 * Method 'CustomerDaoException'
	 * 
	 * @param message
	 */
	public CustomerDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'CustomerDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public CustomerDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

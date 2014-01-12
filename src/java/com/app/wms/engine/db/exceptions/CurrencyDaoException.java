package com.app.wms.engine.db.exceptions;

public class CurrencyDaoException extends DaoException
{
	/**
	 * Method 'CurrencyDaoException'
	 * 
	 * @param message
	 */
	public CurrencyDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'CurrencyDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public CurrencyDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

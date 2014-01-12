package com.app.wms.engine.db.exceptions;

public class PrsDaoException extends DaoException
{
	/**
	 * Method 'PrsDaoException'
	 * 
	 * @param message
	 */
	public PrsDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'PrsDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public PrsDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

package com.app.wms.engine.db.exceptions;

public class PocDaoException extends DaoException
{
	/**
	 * Method 'PocDaoException'
	 * 
	 * @param message
	 */
	public PocDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'PocDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public PocDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

package com.app.wms.engine.db.exceptions;

public class DrDaoException extends DaoException
{
	/**
	 * Method 'DrDaoException'
	 * 
	 * @param message
	 */
	public DrDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'DrDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public DrDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

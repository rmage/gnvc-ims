package com.app.wms.engine.db.exceptions;

public class WhDaoException extends DaoException
{
	/**
	 * Method 'WhDaoException'
	 * 
	 * @param message
	 */
	public WhDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'WhDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public WhDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

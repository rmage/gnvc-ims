package com.app.wms.engine.db.exceptions;

public class UserLogDaoException extends DaoException
{
	/**
	 * Method 'UserLogDaoException'
	 * 
	 * @param message
	 */
	public UserLogDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'UserLogDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public UserLogDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

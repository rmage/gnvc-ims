package com.app.wms.engine.db.exceptions;

public class UserRoleDaoException extends DaoException
{
	/**
	 * Method 'UserRoleDaoException'
	 * 
	 * @param message
	 */
	public UserRoleDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'UserRoleDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public UserRoleDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

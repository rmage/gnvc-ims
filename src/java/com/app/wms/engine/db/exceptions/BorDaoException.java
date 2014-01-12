package com.app.wms.engine.db.exceptions;

public class BorDaoException extends DaoException
{
	/**
	 * Method 'BorDaoException'
	 * 
	 * @param message
	 */
	public BorDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'BorDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public BorDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

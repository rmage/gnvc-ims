package com.app.wms.engine.db.exceptions;

public class BundleDaoException extends DaoException
{
	/**
	 * Method 'BundleDaoException'
	 * 
	 * @param message
	 */
	public BundleDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'BundleDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public BundleDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

package com.app.wms.engine.db.exceptions;

public class WsDaoException extends DaoException
{
	/**
	 * Method 'WsDaoException'
	 * 
	 * @param message
	 */
	public WsDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'WsDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public WsDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

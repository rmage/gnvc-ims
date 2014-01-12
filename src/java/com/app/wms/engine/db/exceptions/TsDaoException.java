package com.app.wms.engine.db.exceptions;

public class TsDaoException extends DaoException
{
	/**
	 * Method 'TsDaoException'
	 * 
	 * @param message
	 */
	public TsDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'TsDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public TsDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

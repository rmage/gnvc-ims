package com.app.wms.engine.db.exceptions;

public class PoDaoException extends DaoException
{
	/**
	 * Method 'PoDaoException'
	 * 
	 * @param message
	 */
	public PoDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'PoDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public PoDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

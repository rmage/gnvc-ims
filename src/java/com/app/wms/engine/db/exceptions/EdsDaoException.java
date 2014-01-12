package com.app.wms.engine.db.exceptions;

public class EdsDaoException extends DaoException
{
	/**
	 * Method 'EdsDaoException'
	 * 
	 * @param message
	 */
	public EdsDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'EdsDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public EdsDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

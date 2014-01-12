package com.app.wms.engine.db.exceptions;

public class PtsDaoException extends DaoException
{
	/**
	 * Method 'PtsDaoException'
	 * 
	 * @param message
	 */
	public PtsDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'PtsDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public PtsDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

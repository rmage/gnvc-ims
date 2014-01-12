package com.app.wms.engine.db.exceptions;

public class TallymanDaoException extends DaoException
{
	/**
	 * Method 'TallymanDaoException'
	 * 
	 * @param message
	 */
	public TallymanDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'TallymanDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public TallymanDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

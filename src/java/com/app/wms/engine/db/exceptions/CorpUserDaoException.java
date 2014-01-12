package com.app.wms.engine.db.exceptions;

public class CorpUserDaoException extends DaoException
{
	/**
	 * Method 'CorpUserDaoException'
	 * 
	 * @param message
	 */
	public CorpUserDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'CorpUserDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public CorpUserDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

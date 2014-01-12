package com.app.wms.engine.db.exceptions;

public class AccountingDaoException extends DaoException
{
	/**
	 * Method 'AccountingDaoException'
	 * 
	 * @param message
	 */
	public AccountingDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'AccountingDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public AccountingDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

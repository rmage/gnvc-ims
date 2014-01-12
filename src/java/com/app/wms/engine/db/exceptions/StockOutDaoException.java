package com.app.wms.engine.db.exceptions;

public class StockOutDaoException extends DaoException
{
	/**
	 * Method 'StockOutDaoException'
	 * 
	 * @param message
	 */
	public StockOutDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'StockOutDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public StockOutDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

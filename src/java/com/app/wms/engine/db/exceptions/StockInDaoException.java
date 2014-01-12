package com.app.wms.engine.db.exceptions;

public class StockInDaoException extends DaoException
{
	/**
	 * Method 'StockInDaoException'
	 * 
	 * @param message
	 */
	public StockInDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'StockInDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public StockInDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

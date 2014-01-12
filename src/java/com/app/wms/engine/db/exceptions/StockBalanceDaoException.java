package com.app.wms.engine.db.exceptions;

public class StockBalanceDaoException extends DaoException
{
	/**
	 * Method 'StockBalanceDaoException'
	 * 
	 * @param message
	 */
	public StockBalanceDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'StockBalanceDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public StockBalanceDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

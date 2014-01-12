package com.app.wms.engine.db.exceptions;

public class StockDaoException extends DaoException {
	
	/**
	 * Method 'StockDaoException'
	 * 
	 * @param message
	 */
	public StockDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'StockDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public StockDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}


}

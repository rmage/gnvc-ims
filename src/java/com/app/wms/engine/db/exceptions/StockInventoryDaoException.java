package com.app.wms.engine.db.exceptions;

public class StockInventoryDaoException extends DaoException
{
	/**
	 * Method 'StockInventoryDaoException'
	 * 
	 * @param message
	 */
	public StockInventoryDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'StockInventoryDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public StockInventoryDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

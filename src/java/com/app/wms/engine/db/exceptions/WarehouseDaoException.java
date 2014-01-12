package com.app.wms.engine.db.exceptions;

public class WarehouseDaoException extends DaoException
{
	/**
	 * Method 'WarehouseDaoException'
	 * 
	 * @param message
	 */
	public WarehouseDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'WarehouseDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public WarehouseDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

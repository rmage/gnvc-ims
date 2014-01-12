package com.app.wms.engine.db.exceptions;

public class WarehouseLocationDaoException extends DaoException {
	/**
	 * Method 'WarehouseLocationDaoException'
	 * 
	 * @param message
	 */
	public WarehouseLocationDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'WarehouseLocationDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public WarehouseLocationDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

package com.app.wms.engine.db.exceptions;

public class SupplierDaoException extends DaoException
{
	/**
	 * Method 'SupplierDaoException'
	 * 
	 * @param message
	 */
	public SupplierDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'SupplierDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public SupplierDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

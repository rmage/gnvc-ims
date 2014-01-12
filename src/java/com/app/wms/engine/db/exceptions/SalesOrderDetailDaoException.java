package com.app.wms.engine.db.exceptions;

public class SalesOrderDetailDaoException extends DaoException

{

	/**
	 * Method 'SalesOrderDetailDaoException'
	 * 
	 * @param message
	 */
	public SalesOrderDetailDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'SalesOrderDetailDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public SalesOrderDetailDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

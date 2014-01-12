package com.app.wms.engine.db.exceptions;

public class DepartmentDaoException extends DaoException
{
	/**
	 * Method 'DepartmentDaoException'
	 * 
	 * @param message
	 */
	public DepartmentDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'DepartmentDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public DepartmentDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

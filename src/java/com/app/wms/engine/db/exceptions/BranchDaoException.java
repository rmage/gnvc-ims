package com.app.wms.engine.db.exceptions;

public class BranchDaoException extends DaoException
{
	/**
	 * Method 'BranchDaoException'
	 * 
	 * @param message
	 */
	public BranchDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'BranchDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public BranchDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

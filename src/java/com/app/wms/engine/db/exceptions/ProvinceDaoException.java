package com.app.wms.engine.db.exceptions;

public class ProvinceDaoException extends DaoException {
	
	/**
	 * Method 'ProvinceDaoException'
	 * 
	 * @param message
	 */
	public ProvinceDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'CityDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public ProvinceDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

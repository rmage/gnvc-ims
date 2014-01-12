package com.app.wms.engine.db.exceptions;

public class RegionDaoException extends DaoException
{
	/**
	 * Method 'CityDaoException'
	 * 
	 * @param message
	 */
	public RegionDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'CityDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public RegionDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

package com.app.wms.engine.db.exceptions;

public class PriceCatalogDaoException extends DaoException
{
	/**
	 * Method 'PriceCatalogDaoException'
	 * 
	 * @param message
	 */
	public PriceCatalogDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'PriceCatalogDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public PriceCatalogDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}

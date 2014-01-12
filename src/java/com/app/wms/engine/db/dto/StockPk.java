package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * This class represents the primary key of the STOCK table.
 */
public class StockPk implements Serializable
{
	protected String stockCode;

	/**
	 * Sets the value of stockCode
	 */
	public void setStockCode(String stockCode)
	{
		this.stockCode = stockCode;
	}

	/**
	 * Gets the value of stockCode
	 */
	public String getStockCode()
	{
		return stockCode;
	}

	/**
	 * Method 'StockPk'
	 *
	 */
	public StockPk()
	{
	}

	/**
	 * Method 'StockPk'
	 *
	 */
	public StockPk(final String stockCode)
	{
		this.stockCode = stockCode;
	}

	/**
	 * Method 'equals'
	 *
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}

		if (_other == this) {
			return true;
		}

		if (!(_other instanceof StockPk)) {
			return false;
		}

		final StockPk _cast = (StockPk) _other;
		if (stockCode == null ? _cast.stockCode != stockCode : !stockCode.equals( _cast.stockCode )) {
			return false;
		}

		return true;
	}

	/**
	 * Method 'hashCode'
	 *
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		if (stockCode != null) {
			_hashCode = 29 * _hashCode + stockCode.hashCode();
		}

		return _hashCode;
	}

	/**
	 * Method 'toString'
	 *
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.StockPk: " );
		ret.append( "stockCode=" + stockCode );
		return ret.toString();
	}

}

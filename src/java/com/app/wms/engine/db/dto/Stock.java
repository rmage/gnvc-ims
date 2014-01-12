package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;
import java.util.Date;

public class Stock implements Serializable
{
	/**
	 * This attribute maps to the column STOCK_CODE in the STOCK table.
	 */
	protected String stockCode;

	/**
	 * This attribute maps to the column BG_CODE in the STOCK table.
	 */
	protected String bgCode;

	/**
	 * This attribute maps to the column START_DATE in the CASH table.
	 */
	protected Date startDate;

	/**
	 * This attribute maps to the column END_DATE in the CASH table.
	 */
	protected Date endDate;

	/**
	 * This attribute maps to the column IS_CURRENT in the CASH table.
	 */
	protected String isCurrent;

	/**
	 * This attribute maps to the column CREATED_BY in the STOCK table.
	 */
	protected BigDecimal createdBy;

	/**
	 * This attribute maps to the column CREATED_DATE in the STOCK table.
	 */
	protected Date createdDate;

	/**
	 * This attribute maps to the column UPDATED_BY in the STOCK table.
	 */
	protected BigDecimal updatedBy;

	/**
	 * This attribute maps to the column UPDATED_DATE in the STOCK table.
	 */
	protected Date updatedDate;

	/**
	 * Method 'Stock'
	 *
	 */
	public Stock()
	{
	}

	/**
	 * Method 'getStockCode'
	 *
	 * @return String
	 */
	public String getStockCode()
	{
		return stockCode;
	}

	/**
	 * Method 'setStockCode'
	 *
	 * @param stockCode
	 */
	public void setStockCode(String stockCode)
	{
		this.stockCode = stockCode;
	}

        /**
	 * Method 'getBgCode'
	 *
	 * @return String
	 */
	public String getBgCode()
	{
		return bgCode;
	}

	/**
	 * Method 'setBgCode'
	 *
	 * @param bgCode
	 */
	public void setBgCode(String bgCode)
	{
		this.bgCode = bgCode;
	}

	/**
	 * Method 'getStartDate'
	 *
	 * @return Date
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * Method 'setStartDate'
	 *
	 * @param startDate
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * Method 'getEndDate'
	 *
	 * @return Date
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Method 'setEndDate'
	 *
	 * @param endDate
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * Method 'getIsCurrent'
	 *
	 * @return String
	 */
	public String getIsCurrent()
	{
		return isCurrent;
	}

	/**
	 * Method 'setIsCurrent'
	 *
	 * @param isCurrent
	 */
	public void setIsCurrent(String isCurrent)
	{
		this.isCurrent = isCurrent;
	}

	/**
	 * Method 'getCreatedBy'
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * Method 'setCreatedBy'
	 *
	 * @param createdBy
	 */
	public void setCreatedBy(BigDecimal createdBy)
	{
		this.createdBy = createdBy;
	}

	/**
	 * Method 'getCreatedDate'
	 *
	 * @return Date
	 */
	public Date getCreatedDate()
	{
		return createdDate;
	}

	/**
	 * Method 'setCreatedDate'
	 *
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	/**
	 * Method 'getUpdatedBy'
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getUpdatedBy()
	{
		return updatedBy;
	}

	/**
	 * Method 'setUpdatedBy'
	 *
	 * @param updatedBy
	 */
	public void setUpdatedBy(BigDecimal updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	/**
	 * Method 'getUpdatedDate'
	 *
	 * @return Date
	 */
	public Date getUpdatedDate()
	{
		return updatedDate;
	}

	/**
	 * Method 'setUpdatedDate'
	 *
	 * @param updatedDate
	 */
	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
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

		if (!(_other instanceof Stock)) {
			return false;
		}

		final Stock _cast = (Stock) _other;
		if (stockCode == null ? _cast.stockCode != stockCode : !stockCode.equals( _cast.stockCode )) {
			return false;
		}

                if (bgCode == null ? _cast.bgCode != bgCode : !bgCode.equals( _cast.bgCode )) {
			return false;
		}

		if (startDate == null ? _cast.startDate != startDate : !startDate.equals( _cast.startDate )) {
			return false;
		}

		if (endDate == null ? _cast.endDate != endDate : !endDate.equals( _cast.endDate )) {
			return false;
		}

		if (isCurrent == null ? _cast.isCurrent != isCurrent : !isCurrent.equals( _cast.isCurrent )) {
			return false;
		}

		if (createdBy == null ? _cast.createdBy != createdBy : !createdBy.equals( _cast.createdBy )) {
			return false;
		}

		if (createdDate == null ? _cast.createdDate != createdDate : !createdDate.equals( _cast.createdDate )) {
			return false;
		}

		if (updatedBy == null ? _cast.updatedBy != updatedBy : !updatedBy.equals( _cast.updatedBy )) {
			return false;
		}

		if (updatedDate == null ? _cast.updatedDate != updatedDate : !updatedDate.equals( _cast.updatedDate )) {
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

                if (bgCode != null) {
			_hashCode = 29 * _hashCode + bgCode.hashCode();
		}

		if (startDate != null) {
			_hashCode = 29 * _hashCode + startDate.hashCode();
		}

		if (endDate != null) {
			_hashCode = 29 * _hashCode + endDate.hashCode();
		}

		if (isCurrent != null) {
			_hashCode = 29 * _hashCode + isCurrent.hashCode();
		}

		if (createdBy != null) {
			_hashCode = 29 * _hashCode + createdBy.hashCode();
		}

		if (createdDate != null) {
			_hashCode = 29 * _hashCode + createdDate.hashCode();
		}

		if (updatedBy != null) {
			_hashCode = 29 * _hashCode + updatedBy.hashCode();
		}

		if (updatedDate != null) {
			_hashCode = 29 * _hashCode + updatedDate.hashCode();
		}

		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 *
	 * @return StockPk
	 */
	public StockPk createPk()
	{
		return new StockPk(stockCode);
	}

	/**
	 * Method 'toString'
	 *
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Stock: " );
		ret.append( "stockCode=" + stockCode );
		ret.append( ", bgCode=" + bgCode );
		ret.append( ", startDate=" + startDate );
		ret.append( ", endDate=" + endDate );
		ret.append( ", isCurrent=" + isCurrent );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}

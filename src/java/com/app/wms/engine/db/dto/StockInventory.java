package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class StockInventory implements Serializable
{
	/** 
	 * This attribute maps to the column id in the stock_inventory table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column product_code in the stock_inventory table.
	 */
	protected String productCode;

	/** 
	 * This attribute maps to the column wh_code in the stock_inventory table.
	 */
	protected String whCode;

	/** 
	 * This attribute maps to the column balance in the stock_inventory table.
	 */
	//protected float balance;
	protected BigDecimal balance;

	/** 
	 * This attribute represents whether the primitive attribute balance is null.
	 */
	protected boolean balanceNull = true;

	/** 
	 * This attribute maps to the column start_balance in the stock_inventory table.
	 */
	//protected float startBalance;
	protected BigDecimal startBalance;

	/** 
	 * This attribute represents whether the primitive attribute startBalance is null.
	 */
	protected boolean startBalanceNull = true;

	/** 
	 * This attribute maps to the column is_active in the stock_inventory table.
	 */
	protected String isActive;

	/** 
	 * This attribute maps to the column is_delete in the stock_inventory table.
	 */
	protected String isDelete;

	/** 
	 * This attribute maps to the column created_by in the stock_inventory table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the stock_inventory table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the stock_inventory table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the stock_inventory table.
	 */
	protected Date updatedDate;
	
	protected String productId;

	/**
	 * Method 'StockInventory'
	 * 
	 */
	public StockInventory()
	{
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
	}

	/**
	 * Method 'getId'
	 * 
	 * @return int
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Method 'setId'
	 * 
	 * @param id
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Method 'getProductCode'
	 * 
	 * @return String
	 */
	public String getProductCode()
	{
		return productCode;
	}

	/**
	 * Method 'setProductCode'
	 * 
	 * @param productCode
	 */
	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}

	/**
	 * Method 'getWhCode'
	 * 
	 * @return String
	 */
	public String getWhCode()
	{
		return whCode;
	}

	/**
	 * Method 'setWhCode'
	 * 
	 * @param whCode
	 */
	public void setWhCode(String whCode)
	{
		this.whCode = whCode;
	}

	/**
	 * Method 'getBalance'
	 * 
	 * @return float
	 */
//	public float getBalance()
//	{
//		return balance;
//	}

	/**
	 * Method 'setBalance'
	 * 
	 * @param balance
	 */
//	public void setBalance(float balance)
//	{
//		this.balance = balance;
//		this.balanceNull = false;
//	}

	/**
	 * Method 'setBalanceNull'
	 * 
	 * @param value
	 */
	public void setBalanceNull(boolean value)
	{
		this.balanceNull = value;
	}

	/**
	 * Method 'isBalanceNull'
	 * 
	 * @return boolean
	 */
	public boolean isBalanceNull()
	{
		return balanceNull;
	}

	/**
	 * Method 'getStartBalance'
	 * 
	 * @return float
	 */
//	public float getStartBalance()
//	{
//		return startBalance;
//	}

	/**
	 * Method 'setStartBalance'
	 * 
	 * @param startBalance
	 */
//	public void setStartBalance(float startBalance)
//	{
//		this.startBalance = startBalance;
//		this.startBalanceNull = false;
//	}

	/**
	 * Method 'setStartBalanceNull'
	 * 
	 * @param value
	 */
	public void setStartBalanceNull(boolean value)
	{
		this.startBalanceNull = value;
	}

	/**
	 * Method 'isStartBalanceNull'
	 * 
	 * @return boolean
	 */
	public boolean isStartBalanceNull()
	{
		return startBalanceNull;
	}

	/**
	 * Method 'getIsActive'
	 * 
	 * @return String
	 */
	public String getIsActive()
	{
		return isActive;
	}

	/**
	 * Method 'setIsActive'
	 * 
	 * @param isActive
	 */
	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	/**
	 * Method 'getIsDelete'
	 * 
	 * @return String
	 */
	public String getIsDelete()
	{
		return isDelete;
	}

	/**
	 * Method 'setIsDelete'
	 * 
	 * @param isDelete
	 */
	public void setIsDelete(String isDelete)
	{
		this.isDelete = isDelete;
	}

	/**
	 * Method 'getCreatedBy'
	 * 
	 * @return String
	 */
	public String getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * Method 'setCreatedBy'
	 * 
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy)
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
	 * @return String
	 */
	public String getUpdatedBy()
	{
		return updatedBy;
	}

	/**
	 * Method 'setUpdatedBy'
	 * 
	 * @param updatedBy
	 */
	public void setUpdatedBy(String updatedBy)
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
		
		if (!(_other instanceof StockInventory)) {
			return false;
		}
		
		final StockInventory _cast = (StockInventory) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (productCode == null ? _cast.productCode != productCode : !productCode.equals( _cast.productCode )) {
			return false;
		}
		
		if (whCode == null ? _cast.whCode != whCode : !whCode.equals( _cast.whCode )) {
			return false;
		}
		
//		if (balance != _cast.balance) {
//			return false;
//		}
		
		if (balanceNull != _cast.balanceNull) {
			return false;
		}
		
//		if (startBalance != _cast.startBalance) {
//			return false;
//		}
		
		if (startBalanceNull != _cast.startBalanceNull) {
			return false;
		}
		
		if (isActive == null ? _cast.isActive != isActive : !isActive.equals( _cast.isActive )) {
			return false;
		}
		
		if (isDelete == null ? _cast.isDelete != isDelete : !isDelete.equals( _cast.isDelete )) {
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
		_hashCode = 29 * _hashCode + id;
		if (productCode != null) {
			_hashCode = 29 * _hashCode + productCode.hashCode();
		}
		
		if (whCode != null) {
			_hashCode = 29 * _hashCode + whCode.hashCode();
		}
		
//		_hashCode = 29 * _hashCode + Float.floatToIntBits(balance);
//		_hashCode = 29 * _hashCode + (balanceNull ? 1 : 0);
//		_hashCode = 29 * _hashCode + Float.floatToIntBits(startBalance);
//		_hashCode = 29 * _hashCode + (startBalanceNull ? 1 : 0);
		if (isActive != null) {
			_hashCode = 29 * _hashCode + isActive.hashCode();
		}
		
		if (isDelete != null) {
			_hashCode = 29 * _hashCode + isDelete.hashCode();
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
	 * @return StockInventoryPk
	 */
	public StockInventoryPk createPk()
	{
		return new StockInventoryPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.StockInventory: " );
		ret.append( "id=" + id );
		ret.append( ", productCode=" + productCode );
		ret.append( ", whCode=" + whCode );
		ret.append( ", balance=" + balance );
		ret.append( ", startBalance=" + startBalance );
		ret.append( ", isActive=" + isActive );
		ret.append( ", isDelete=" + isDelete );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		ret.append( ", productId=" + productId );
		return ret.toString();
	}

}

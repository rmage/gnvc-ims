package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class StockBalance implements Serializable
{
	/** 
	 * This attribute maps to the column id in the stock_balance table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column document_no in the stock_balance table.
	 */
	protected String documentNo;

	/** 
	 * This attribute maps to the column document_date in the stock_balance table.
	 */
	protected Date documentDate;

	/** 
	 * This attribute maps to the column product_code in the stock_balance table.
	 */
	protected String productCode;

	/** 
	 * This attribute maps to the column product_name in the stock_balance table.
	 */
	protected String productName;

	/** 
	 * This attribute maps to the column qty_in in the stock_balance table.
	 */
	//protected float qtyIn;
	protected BigDecimal qtyIn;

	/** 
	 * This attribute represents whether the primitive attribute qtyIn is null.
	 */
	protected boolean qtyInNull = true;

	/** 
	 * This attribute maps to the column qty_out in the stock_balance table.
	 */
	//protected float qtyOut;
	protected BigDecimal qtyOut;

	/** 
	 * This attribute represents whether the primitive attribute qtyOut is null.
	 */
	protected boolean qtyOutNull = true;

	/** 
	 * This attribute maps to the column balance in the stock_balance table.
	 */
	//protected float balance;
	protected BigDecimal balance;

	/** 
	 * This attribute represents whether the primitive attribute balance is null.
	 */
	protected boolean balanceNull = true;

	/** 
	 * This attribute maps to the column is_active in the stock_balance table.
	 */
	protected String isActive;

	/** 
	 * This attribute maps to the column is_delete in the stock_balance table.
	 */
	protected String isDelete;

	/** 
	 * This attribute maps to the column created_by in the stock_balance table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the stock_balance table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the stock_balance table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the stock_balance table.
	 */
	protected Date updatedDate;

	/**
	 * Method 'StockBalance'
	 * 
	 */
	public StockBalance()
	{
	}

	public BigDecimal getQtyIn() {
		return qtyIn;
	}

	public void setQtyIn(BigDecimal qtyIn) {
		this.qtyIn = qtyIn;
	}

	public BigDecimal getQtyOut() {
		return qtyOut;
	}

	public void setQtyOut(BigDecimal qtyOut) {
		this.qtyOut = qtyOut;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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
	 * Method 'getDocumentNo'
	 * 
	 * @return String
	 */
	public String getDocumentNo()
	{
		return documentNo;
	}

	/**
	 * Method 'setDocumentNo'
	 * 
	 * @param documentNo
	 */
	public void setDocumentNo(String documentNo)
	{
		this.documentNo = documentNo;
	}

	/**
	 * Method 'getDocumentDate'
	 * 
	 * @return Date
	 */
	public Date getDocumentDate()
	{
		return documentDate;
	}

	/**
	 * Method 'setDocumentDate'
	 * 
	 * @param documentDate
	 */
	public void setDocumentDate(Date documentDate)
	{
		this.documentDate = documentDate;
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
	 * Method 'getProductName'
	 * 
	 * @return String
	 */
	public String getProductName()
	{
		return productName;
	}

	/**
	 * Method 'setProductName'
	 * 
	 * @param productName
	 */
	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	/**
	 * Method 'getQtyIn'
	 * 
	 * @return float
	 */
//	public float getQtyIn()
//	{
//		return qtyIn;
//	}

	/**
	 * Method 'setQtyIn'
	 * 
	 * @param qtyIn
	 */
//	public void setQtyIn(float qtyIn)
//	{
//		this.qtyIn = qtyIn;
//		this.qtyInNull = false;
//	}

	/**
	 * Method 'setQtyInNull'
	 * 
	 * @param value
	 */
	public void setQtyInNull(boolean value)
	{
		this.qtyInNull = value;
	}

	/**
	 * Method 'isQtyInNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtyInNull()
	{
		return qtyInNull;
	}

	/**
	 * Method 'getQtyOut'
	 * 
	 * @return float
	 */
//	public float getQtyOut()
//	{
//		return qtyOut;
//	}

	/**
	 * Method 'setQtyOut'
	 * 
	 * @param qtyOut
	 */
//	public void setQtyOut(float qtyOut)
//	{
//		this.qtyOut = qtyOut;
//		this.qtyOutNull = false;
//	}

	/**
	 * Method 'setQtyOutNull'
	 * 
	 * @param value
	 */
	public void setQtyOutNull(boolean value)
	{
		this.qtyOutNull = value;
	}

	/**
	 * Method 'isQtyOutNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtyOutNull()
	{
		return qtyOutNull;
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
		
		if (!(_other instanceof StockBalance)) {
			return false;
		}
		
		final StockBalance _cast = (StockBalance) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (documentNo == null ? _cast.documentNo != documentNo : !documentNo.equals( _cast.documentNo )) {
			return false;
		}
		
		if (documentDate == null ? _cast.documentDate != documentDate : !documentDate.equals( _cast.documentDate )) {
			return false;
		}
		
		if (productCode == null ? _cast.productCode != productCode : !productCode.equals( _cast.productCode )) {
			return false;
		}
		
		if (productName == null ? _cast.productName != productName : !productName.equals( _cast.productName )) {
			return false;
		}
		
//		if (qtyIn != _cast.qtyIn) {
//			return false;
//		}
//		
		if (qtyInNull != _cast.qtyInNull) {
			return false;
		}
		
//		if (qtyOut != _cast.qtyOut) {
//			return false;
//		}
//		
		if (qtyOutNull != _cast.qtyOutNull) {
			return false;
		}
		
//		if (balance != _cast.balance) {
//			return false;
//		}
//		
		if (balanceNull != _cast.balanceNull) {
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
		if (documentNo != null) {
			_hashCode = 29 * _hashCode + documentNo.hashCode();
		}
		
		if (documentDate != null) {
			_hashCode = 29 * _hashCode + documentDate.hashCode();
		}
		
		if (productCode != null) {
			_hashCode = 29 * _hashCode + productCode.hashCode();
		}
		
		if (productName != null) {
			_hashCode = 29 * _hashCode + productName.hashCode();
		}
		
//		_hashCode = 29 * _hashCode + Float.floatToIntBits(qtyIn);
//		_hashCode = 29 * _hashCode + (qtyInNull ? 1 : 0);
//		_hashCode = 29 * _hashCode + Float.floatToIntBits(qtyOut);
//		_hashCode = 29 * _hashCode + (qtyOutNull ? 1 : 0);
//		_hashCode = 29 * _hashCode + Float.floatToIntBits(balance);
		_hashCode = 29 * _hashCode + (balanceNull ? 1 : 0);
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
	 * @return StockBalancePk
	 */
	public StockBalancePk createPk()
	{
		return new StockBalancePk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.StockBalance: " );
		ret.append( "id=" + id );
		ret.append( ", documentNo=" + documentNo );
		ret.append( ", documentDate=" + documentDate );
		ret.append( ", productCode=" + productCode );
		ret.append( ", productName=" + productName );
		ret.append( ", qtyIn=" + qtyIn );
		ret.append( ", qtyOut=" + qtyOut );
		ret.append( ", balance=" + balance );
		ret.append( ", isActive=" + isActive );
		ret.append( ", isDelete=" + isDelete );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}

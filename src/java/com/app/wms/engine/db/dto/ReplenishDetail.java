package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class ReplenishDetail implements Serializable
{
	/** 
	 * This attribute maps to the column replenish_no in the replenish_detail table.
	 */
	protected String replenishNo;

	/** 
	 * This attribute maps to the column from_location in the replenish_detail table.
	 */
	protected String fromLocation;

	/** 
	 * This attribute maps to the column to_location in the replenish_detail table.
	 */
	protected String toLocation;

	/** 
	 * This attribute maps to the column product_id in the replenish_detail table.
	 */
	protected String productId;

	/** 
	 * This attribute maps to the column product_code in the replenish_detail table.
	 */
	protected String productCode;

	/** 
	 * This attribute maps to the column product_name in the replenish_detail table.
	 */
	protected String productName;

	/** 
	 * This attribute maps to the column received_date in the replenish_detail table.
	 */
	protected Date receivedDate;

	/** 
	 * This attribute maps to the column expired_date in the replenish_detail table.
	 */
	protected Date expiredDate;

	/** 
	 * This attribute maps to the column unit_code in the replenish_detail table.
	 */
	protected String unitCode;

	/** 
	 * This attribute maps to the column qty_replenish in the replenish_detail table.
	 */
	protected int qtyReplenish;

	/** 
	 * This attribute represents whether the primitive attribute qtyReplenish is null.
	 */
	protected boolean qtyReplenishNull = true;

	/** 
	 * This attribute maps to the column balance in the replenish_detail table.
	 */
	protected int balance;

	/** 
	 * This attribute represents whether the primitive attribute balance is null.
	 */
	protected boolean balanceNull = true;

	/** 
	 * This attribute maps to the column confirmed_by in the replenish_detail table.
	 */
	protected String confirmedBy;

	/** 
	 * This attribute maps to the column remarks in the replenish_detail table.
	 */
	protected String remarks;

	/** 
	 * This attribute maps to the column user_id in the replenish_detail table.
	 */
	protected String userId;

	/** 
	 * This attribute maps to the column corp_id in the replenish_detail table.
	 */
	protected String corpId;

	/** 
	 * This attribute maps to the column wh_code in the replenish_detail table.
	 */
	protected String whCode;

	/**
	 * Method 'ReplenishDetail'
	 * 
	 */
	public ReplenishDetail()
	{
	}

	/**
	 * Method 'getReplenishNo'
	 * 
	 * @return String
	 */
	public String getReplenishNo()
	{
		return replenishNo;
	}

	/**
	 * Method 'setReplenishNo'
	 * 
	 * @param replenishNo
	 */
	public void setReplenishNo(String replenishNo)
	{
		this.replenishNo = replenishNo;
	}

	/**
	 * Method 'getFromLocation'
	 * 
	 * @return String
	 */
	public String getFromLocation()
	{
		return fromLocation;
	}

	/**
	 * Method 'setFromLocation'
	 * 
	 * @param fromLocation
	 */
	public void setFromLocation(String fromLocation)
	{
		this.fromLocation = fromLocation;
	}

	/**
	 * Method 'getToLocation'
	 * 
	 * @return String
	 */
	public String getToLocation()
	{
		return toLocation;
	}

	/**
	 * Method 'setToLocation'
	 * 
	 * @param toLocation
	 */
	public void setToLocation(String toLocation)
	{
		this.toLocation = toLocation;
	}

	/**
	 * Method 'getProductId'
	 * 
	 * @return String
	 */
	public String getProductId()
	{
		return productId;
	}

	/**
	 * Method 'setProductId'
	 * 
	 * @param productId
	 */
	public void setProductId(String productId)
	{
		this.productId = productId;
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
	 * Method 'getReceivedDate'
	 * 
	 * @return Date
	 */
	public Date getReceivedDate()
	{
		return receivedDate;
	}

	/**
	 * Method 'setReceivedDate'
	 * 
	 * @param receivedDate
	 */
	public void setReceivedDate(Date receivedDate)
	{
		this.receivedDate = receivedDate;
	}

	/**
	 * Method 'getExpiredDate'
	 * 
	 * @return Date
	 */
	public Date getExpiredDate()
	{
		return expiredDate;
	}

	/**
	 * Method 'setExpiredDate'
	 * 
	 * @param expiredDate
	 */
	public void setExpiredDate(Date expiredDate)
	{
		this.expiredDate = expiredDate;
	}

	/**
	 * Method 'getUnitCode'
	 * 
	 * @return String
	 */
	public String getUnitCode()
	{
		return unitCode;
	}

	/**
	 * Method 'setUnitCode'
	 * 
	 * @param unitCode
	 */
	public void setUnitCode(String unitCode)
	{
		this.unitCode = unitCode;
	}

	/**
	 * Method 'getQtyReplenish'
	 * 
	 * @return int
	 */
	public int getQtyReplenish()
	{
		return qtyReplenish;
	}

	/**
	 * Method 'setQtyReplenish'
	 * 
	 * @param qtyReplenish
	 */
	public void setQtyReplenish(int qtyReplenish)
	{
		this.qtyReplenish = qtyReplenish;
		this.qtyReplenishNull = false;
	}

	/**
	 * Method 'setQtyReplenishNull'
	 * 
	 * @param value
	 */
	public void setQtyReplenishNull(boolean value)
	{
		this.qtyReplenishNull = value;
	}

	/**
	 * Method 'isQtyReplenishNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtyReplenishNull()
	{
		return qtyReplenishNull;
	}

	/**
	 * Method 'getBalance'
	 * 
	 * @return int
	 */
	public int getBalance()
	{
		return balance;
	}

	/**
	 * Method 'setBalance'
	 * 
	 * @param balance
	 */
	public void setBalance(int balance)
	{
		this.balance = balance;
		this.balanceNull = false;
	}

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
	 * Method 'getConfirmedBy'
	 * 
	 * @return String
	 */
	public String getConfirmedBy()
	{
		return confirmedBy;
	}

	/**
	 * Method 'setConfirmedBy'
	 * 
	 * @param confirmedBy
	 */
	public void setConfirmedBy(String confirmedBy)
	{
		this.confirmedBy = confirmedBy;
	}

	/**
	 * Method 'getRemarks'
	 * 
	 * @return String
	 */
	public String getRemarks()
	{
		return remarks;
	}

	/**
	 * Method 'setRemarks'
	 * 
	 * @param remarks
	 */
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	/**
	 * Method 'getUserId'
	 * 
	 * @return String
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * Method 'setUserId'
	 * 
	 * @param userId
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * Method 'getCorpId'
	 * 
	 * @return String
	 */
	public String getCorpId()
	{
		return corpId;
	}

	/**
	 * Method 'setCorpId'
	 * 
	 * @param corpId
	 */
	public void setCorpId(String corpId)
	{
		this.corpId = corpId;
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
		
		if (!(_other instanceof ReplenishDetail)) {
			return false;
		}
		
		final ReplenishDetail _cast = (ReplenishDetail) _other;
		if (replenishNo == null ? _cast.replenishNo != replenishNo : !replenishNo.equals( _cast.replenishNo )) {
			return false;
		}
		
		if (fromLocation == null ? _cast.fromLocation != fromLocation : !fromLocation.equals( _cast.fromLocation )) {
			return false;
		}
		
		if (toLocation == null ? _cast.toLocation != toLocation : !toLocation.equals( _cast.toLocation )) {
			return false;
		}
		
		if (productId == null ? _cast.productId != productId : !productId.equals( _cast.productId )) {
			return false;
		}
		
		if (productCode == null ? _cast.productCode != productCode : !productCode.equals( _cast.productCode )) {
			return false;
		}
		
		if (productName == null ? _cast.productName != productName : !productName.equals( _cast.productName )) {
			return false;
		}
		
		if (receivedDate == null ? _cast.receivedDate != receivedDate : !receivedDate.equals( _cast.receivedDate )) {
			return false;
		}
		
		if (expiredDate == null ? _cast.expiredDate != expiredDate : !expiredDate.equals( _cast.expiredDate )) {
			return false;
		}
		
		if (unitCode == null ? _cast.unitCode != unitCode : !unitCode.equals( _cast.unitCode )) {
			return false;
		}
		
		if (qtyReplenish != _cast.qtyReplenish) {
			return false;
		}
		
		if (qtyReplenishNull != _cast.qtyReplenishNull) {
			return false;
		}
		
		if (balance != _cast.balance) {
			return false;
		}
		
		if (balanceNull != _cast.balanceNull) {
			return false;
		}
		
		if (confirmedBy == null ? _cast.confirmedBy != confirmedBy : !confirmedBy.equals( _cast.confirmedBy )) {
			return false;
		}
		
		if (remarks == null ? _cast.remarks != remarks : !remarks.equals( _cast.remarks )) {
			return false;
		}
		
		if (userId == null ? _cast.userId != userId : !userId.equals( _cast.userId )) {
			return false;
		}
		
		if (corpId == null ? _cast.corpId != corpId : !corpId.equals( _cast.corpId )) {
			return false;
		}
		
		if (whCode == null ? _cast.whCode != whCode : !whCode.equals( _cast.whCode )) {
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
		if (replenishNo != null) {
			_hashCode = 29 * _hashCode + replenishNo.hashCode();
		}
		
		if (fromLocation != null) {
			_hashCode = 29 * _hashCode + fromLocation.hashCode();
		}
		
		if (toLocation != null) {
			_hashCode = 29 * _hashCode + toLocation.hashCode();
		}
		
		if (productId != null) {
			_hashCode = 29 * _hashCode + productId.hashCode();
		}
		
		if (productCode != null) {
			_hashCode = 29 * _hashCode + productCode.hashCode();
		}
		
		if (productName != null) {
			_hashCode = 29 * _hashCode + productName.hashCode();
		}
		
		if (receivedDate != null) {
			_hashCode = 29 * _hashCode + receivedDate.hashCode();
		}
		
		if (expiredDate != null) {
			_hashCode = 29 * _hashCode + expiredDate.hashCode();
		}
		
		if (unitCode != null) {
			_hashCode = 29 * _hashCode + unitCode.hashCode();
		}
		
		_hashCode = 29 * _hashCode + qtyReplenish;
		_hashCode = 29 * _hashCode + (qtyReplenishNull ? 1 : 0);
		_hashCode = 29 * _hashCode + balance;
		_hashCode = 29 * _hashCode + (balanceNull ? 1 : 0);
		if (confirmedBy != null) {
			_hashCode = 29 * _hashCode + confirmedBy.hashCode();
		}
		
		if (remarks != null) {
			_hashCode = 29 * _hashCode + remarks.hashCode();
		}
		
		if (userId != null) {
			_hashCode = 29 * _hashCode + userId.hashCode();
		}
		
		if (corpId != null) {
			_hashCode = 29 * _hashCode + corpId.hashCode();
		}
		
		if (whCode != null) {
			_hashCode = 29 * _hashCode + whCode.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.ReplenishDetail: " );
		ret.append( "replenishNo=" + replenishNo );
		ret.append( ", fromLocation=" + fromLocation );
		ret.append( ", toLocation=" + toLocation );
		ret.append( ", productId=" + productId );
		ret.append( ", productCode=" + productCode );
		ret.append( ", productName=" + productName );
		ret.append( ", receivedDate=" + receivedDate );
		ret.append( ", expiredDate=" + expiredDate );
		ret.append( ", unitCode=" + unitCode );
		ret.append( ", qtyReplenish=" + qtyReplenish );
		ret.append( ", balance=" + balance );
		ret.append( ", confirmedBy=" + confirmedBy );
		ret.append( ", remarks=" + remarks );
		ret.append( ", userId=" + userId );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		return ret.toString();
	}

}

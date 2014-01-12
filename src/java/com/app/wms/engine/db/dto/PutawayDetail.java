package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

public class PutawayDetail implements Serializable
{
	/** 
	 * This attribute maps to the column putaway_id in the putaway_detail table.
	 */
	protected String putawayId;

	/** 
	 * This attribute maps to the column product_id in the putaway_detail table.
	 */
	protected String productId;

	/** 
	 * This attribute maps to the column received_date in the putaway_detail table.
	 */
	protected Timestamp receivedDate;

	/** 
	 * This attribute maps to the column expired_date in the putaway_detail table.
	 */
	protected Timestamp expiredDate;

	/** 
	 * This attribute maps to the column unit_code in the putaway_detail table.
	 */
	protected String unitCode;

	/** 
	 * This attribute maps to the column qty_order in the putaway_detail table.
	 */
	protected int qtyOrder;

	/** 
	 * This attribute represents whether the primitive attribute qtyOrder is null.
	 */
	protected boolean qtyOrderNull = true;

	/** 
	 * This attribute maps to the column qty_put in the putaway_detail table.
	 */
	protected int qtyPut;

	/** 
	 * This attribute represents whether the primitive attribute qtyPut is null.
	 */
	protected boolean qtyPutNull = true;

	/** 
	 * This attribute maps to the column balance in the putaway_detail table.
	 */
	protected int balance;

	/** 
	 * This attribute represents whether the primitive attribute balance is null.
	 */
	protected boolean balanceNull = true;

	/** 
	 * This attribute maps to the column user_id in the putaway_detail table.
	 */
	protected String userId;

	/** 
	 * This attribute maps to the column corp_id in the putaway_detail table.
	 */
	protected String corpId;

	/** 
	 * This attribute maps to the column location_code in the putaway_detail table.
	 */
	protected String locationCode;

	/** 
	 * This attribute maps to the column wh_code in the putaway_detail table.
	 */
	protected String whCode;

	/** 
	 * This attribute maps to the column id in the putaway_detail table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column lotid in the putaway_detail table.
	 */
	protected String lotid;

	/** 
	 * This attribute maps to the column product_code in the putaway_detail table.
	 */
	protected String productCode;
	
	protected int qtyPick;
	
	protected WhLocation whLocation;
	
	protected Product product;
	
	protected int qtyLot;

	/**
	 * Method 'PutawayDetail'
	 * 
	 */
	public PutawayDetail()
	{
	}

	public int getQtyLot() {
		return qtyLot;
	}

	public void setQtyLot(int qtyLot) {
		this.qtyLot = qtyLot;
	}

	public int getQtyPick() {
		return qtyPick;
	}

	public void setQtyPick(int qtyPick) {
		this.qtyPick = qtyPick;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public WhLocation getWhLocation() {
		return whLocation;
	}

	public void setWhLocation(WhLocation whLocation) {
		this.whLocation = whLocation;
	}

	public Timestamp getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Timestamp receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Timestamp getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Timestamp expiredDate) {
		this.expiredDate = expiredDate;
	}

	/**
	 * Method 'getPutawayId'
	 * 
	 * @return String
	 */
	public String getPutawayId()
	{
		return putawayId;
	}

	/**
	 * Method 'setPutawayId'
	 * 
	 * @param putawayId
	 */
	public void setPutawayId(String putawayId)
	{
		this.putawayId = putawayId;
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
	 * Method 'getQtyOrder'
	 * 
	 * @return int
	 */
	public int getQtyOrder()
	{
		return qtyOrder;
	}

	/**
	 * Method 'setQtyOrder'
	 * 
	 * @param qtyOrder
	 */
	public void setQtyOrder(int qtyOrder)
	{
		this.qtyOrder = qtyOrder;
		this.qtyOrderNull = false;
	}

	/**
	 * Method 'setQtyOrderNull'
	 * 
	 * @param value
	 */
	public void setQtyOrderNull(boolean value)
	{
		this.qtyOrderNull = value;
	}

	/**
	 * Method 'isQtyOrderNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtyOrderNull()
	{
		return qtyOrderNull;
	}

	/**
	 * Method 'getQtyPut'
	 * 
	 * @return int
	 */
	public int getQtyPut()
	{
		return qtyPut;
	}

	/**
	 * Method 'setQtyPut'
	 * 
	 * @param qtyPut
	 */
	public void setQtyPut(int qtyPut)
	{
		this.qtyPut = qtyPut;
		this.qtyPutNull = false;
	}

	/**
	 * Method 'setQtyPutNull'
	 * 
	 * @param value
	 */
	public void setQtyPutNull(boolean value)
	{
		this.qtyPutNull = value;
	}

	/**
	 * Method 'isQtyPutNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtyPutNull()
	{
		return qtyPutNull;
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
	 * Method 'getLocationCode'
	 * 
	 * @return String
	 */
	public String getLocationCode()
	{
		return locationCode;
	}

	/**
	 * Method 'setLocationCode'
	 * 
	 * @param locationCode
	 */
	public void setLocationCode(String locationCode)
	{
		this.locationCode = locationCode;
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
	 * Method 'getLotid'
	 * 
	 * @return String
	 */
	public String getLotid()
	{
		return lotid;
	}

	/**
	 * Method 'setLotid'
	 * 
	 * @param lotid
	 */
	public void setLotid(String lotid)
	{
		this.lotid = lotid;
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
		
		if (!(_other instanceof PutawayDetail)) {
			return false;
		}
		
		final PutawayDetail _cast = (PutawayDetail) _other;
		if (putawayId == null ? _cast.putawayId != putawayId : !putawayId.equals( _cast.putawayId )) {
			return false;
		}
		
		if (productId == null ? _cast.productId != productId : !productId.equals( _cast.productId )) {
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
		
		if (qtyOrder != _cast.qtyOrder) {
			return false;
		}
		
		if (qtyOrderNull != _cast.qtyOrderNull) {
			return false;
		}
		
		if (qtyPut != _cast.qtyPut) {
			return false;
		}
		
		if (qtyPutNull != _cast.qtyPutNull) {
			return false;
		}
		
		if (balance != _cast.balance) {
			return false;
		}
		
		if (balanceNull != _cast.balanceNull) {
			return false;
		}
		
		if (userId == null ? _cast.userId != userId : !userId.equals( _cast.userId )) {
			return false;
		}
		
		if (corpId == null ? _cast.corpId != corpId : !corpId.equals( _cast.corpId )) {
			return false;
		}
		
		if (locationCode == null ? _cast.locationCode != locationCode : !locationCode.equals( _cast.locationCode )) {
			return false;
		}
		
		if (whCode == null ? _cast.whCode != whCode : !whCode.equals( _cast.whCode )) {
			return false;
		}
		
		if (id != _cast.id) {
			return false;
		}
		
		if (lotid == null ? _cast.lotid != lotid : !lotid.equals( _cast.lotid )) {
			return false;
		}
		
		if (productCode == null ? _cast.productCode != productCode : !productCode.equals( _cast.productCode )) {
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
		if (putawayId != null) {
			_hashCode = 29 * _hashCode + putawayId.hashCode();
		}
		
		if (productId != null) {
			_hashCode = 29 * _hashCode + productId.hashCode();
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
		
		_hashCode = 29 * _hashCode + qtyOrder;
		_hashCode = 29 * _hashCode + (qtyOrderNull ? 1 : 0);
		_hashCode = 29 * _hashCode + qtyPut;
		_hashCode = 29 * _hashCode + (qtyPutNull ? 1 : 0);
		_hashCode = 29 * _hashCode + balance;
		_hashCode = 29 * _hashCode + (balanceNull ? 1 : 0);
		if (userId != null) {
			_hashCode = 29 * _hashCode + userId.hashCode();
		}
		
		if (corpId != null) {
			_hashCode = 29 * _hashCode + corpId.hashCode();
		}
		
		if (locationCode != null) {
			_hashCode = 29 * _hashCode + locationCode.hashCode();
		}
		
		if (whCode != null) {
			_hashCode = 29 * _hashCode + whCode.hashCode();
		}
		
		_hashCode = 29 * _hashCode + id;
		if (lotid != null) {
			_hashCode = 29 * _hashCode + lotid.hashCode();
		}
		
		if (productCode != null) {
			_hashCode = 29 * _hashCode + productCode.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.PutawayDetail: " );
		ret.append( "putawayId=" + putawayId );
		ret.append( ", productId=" + productId );
		ret.append( ", receivedDate=" + receivedDate );
		ret.append( ", expiredDate=" + expiredDate );
		ret.append( ", unitCode=" + unitCode );
		ret.append( ", qtyOrder=" + qtyOrder );
		ret.append( ", qtyPut=" + qtyPut );
		ret.append( ", balance=" + balance );
		ret.append( ", userId=" + userId );
		ret.append( ", corpId=" + corpId );
		ret.append( ", locationCode=" + locationCode );
		ret.append( ", whCode=" + whCode );
		ret.append( ", id=" + id );
		ret.append( ", lotid=" + lotid );
		ret.append( ", productCode=" + productCode );
		ret.append( ", whLocation=" + whLocation );
		ret.append( ", product=" + product );
		ret.append( ", qtyPick=" + qtyPick );
		ret.append( ", qtyLot=" + qtyLot );
		return ret.toString();
	}

}

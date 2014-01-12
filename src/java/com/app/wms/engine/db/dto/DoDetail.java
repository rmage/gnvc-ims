package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class DoDetail implements Serializable
{
	/** 
	 * This attribute maps to the column delivery_no in the do_detail table.
	 */
	protected String deliveryNo;

	/** 
	 * This attribute maps to the column so_number in the do_detail table.
	 */
	protected String soNumber;

	/** 
	 * This attribute maps to the column picking_id in the do_detail table.
	 */
	protected String pickingId;

	/** 
	 * This attribute maps to the column product_id in the do_detail table.
	 */
	protected String productId;

	/** 
	 * This attribute maps to the column product_code in the do_detail table.
	 */
	protected String productCode;

	/** 
	 * This attribute maps to the column product_name in the do_detail table.
	 */
	protected String productName;

	/** 
	 * This attribute maps to the column qty_delivery in the do_detail table.
	 */
	//protected float qtyDelivery;
	protected BigDecimal qtyDelivery;

	/** 
	 * This attribute represents whether the primitive attribute qtyDelivery is null.
	 */
	protected boolean qtyDeliveryNull = true;

	/** 
	 * This attribute maps to the column unit_code in the do_detail table.
	 */
	protected String unitCode;

	/** 
	 * This attribute maps to the column transporter_type in the do_detail table.
	 */
	protected String transporterType;

	/** 
	 * This attribute maps to the column transporter_name in the do_detail table.
	 */
	protected String transporterName;

	/** 
	 * This attribute maps to the column user_id in the do_detail table.
	 */
	protected String userId;

	/** 
	 * This attribute maps to the column corp_id in the do_detail table.
	 */
	protected String corpId;

	/** 
	 * This attribute maps to the column wh_code in the do_detail table.
	 */
	protected String whCode;

	/**
	 * Method 'DoDetail'
	 * 
	 */
	public DoDetail()
	{
	}

	public BigDecimal getQtyDelivery() {
		return qtyDelivery;
	}

	public void setQtyDelivery(BigDecimal qtyDelivery) {
		this.qtyDelivery = qtyDelivery;
	}

	/**
	 * Method 'getDeliveryNo'
	 * 
	 * @return String
	 */
	public String getDeliveryNo()
	{
		return deliveryNo;
	}

	/**
	 * Method 'setDeliveryNo'
	 * 
	 * @param deliveryNo
	 */
	public void setDeliveryNo(String deliveryNo)
	{
		this.deliveryNo = deliveryNo;
	}

	/**
	 * Method 'getSoNumber'
	 * 
	 * @return String
	 */
	public String getSoNumber()
	{
		return soNumber;
	}

	/**
	 * Method 'setSoNumber'
	 * 
	 * @param soNumber
	 */
	public void setSoNumber(String soNumber)
	{
		this.soNumber = soNumber;
	}

	/**
	 * Method 'getPickingId'
	 * 
	 * @return String
	 */
	public String getPickingId()
	{
		return pickingId;
	}

	/**
	 * Method 'setPickingId'
	 * 
	 * @param pickingId
	 */
	public void setPickingId(String pickingId)
	{
		this.pickingId = pickingId;
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
	 * Method 'getQtyDelivery'
	 * 
	 * @return float
	 */
//	public float getQtyDelivery()
//	{
//		return qtyDelivery;
//	}

	/**
	 * Method 'setQtyDelivery'
	 * 
	 * @param qtyDelivery
	 */
//	public void setQtyDelivery(float qtyDelivery)
//	{
//		this.qtyDelivery = qtyDelivery;
//		this.qtyDeliveryNull = false;
//	}

	/**
	 * Method 'setQtyDeliveryNull'
	 * 
	 * @param value
	 */
	public void setQtyDeliveryNull(boolean value)
	{
		this.qtyDeliveryNull = value;
	}

	/**
	 * Method 'isQtyDeliveryNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtyDeliveryNull()
	{
		return qtyDeliveryNull;
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
	 * Method 'getTransporterType'
	 * 
	 * @return String
	 */
	public String getTransporterType()
	{
		return transporterType;
	}

	/**
	 * Method 'setTransporterType'
	 * 
	 * @param transporterType
	 */
	public void setTransporterType(String transporterType)
	{
		this.transporterType = transporterType;
	}

	/**
	 * Method 'getTransporterName'
	 * 
	 * @return String
	 */
	public String getTransporterName()
	{
		return transporterName;
	}

	/**
	 * Method 'setTransporterName'
	 * 
	 * @param transporterName
	 */
	public void setTransporterName(String transporterName)
	{
		this.transporterName = transporterName;
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
		
		if (!(_other instanceof DoDetail)) {
			return false;
		}
		
		final DoDetail _cast = (DoDetail) _other;
		if (deliveryNo == null ? _cast.deliveryNo != deliveryNo : !deliveryNo.equals( _cast.deliveryNo )) {
			return false;
		}
		
		if (soNumber == null ? _cast.soNumber != soNumber : !soNumber.equals( _cast.soNumber )) {
			return false;
		}
		
		if (pickingId == null ? _cast.pickingId != pickingId : !pickingId.equals( _cast.pickingId )) {
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
		
//		if (qtyDelivery != _cast.qtyDelivery) {
//			return false;
//		}
		
		if (qtyDeliveryNull != _cast.qtyDeliveryNull) {
			return false;
		}
		
		if (unitCode == null ? _cast.unitCode != unitCode : !unitCode.equals( _cast.unitCode )) {
			return false;
		}
		
		if (transporterType == null ? _cast.transporterType != transporterType : !transporterType.equals( _cast.transporterType )) {
			return false;
		}
		
		if (transporterName == null ? _cast.transporterName != transporterName : !transporterName.equals( _cast.transporterName )) {
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
		if (deliveryNo != null) {
			_hashCode = 29 * _hashCode + deliveryNo.hashCode();
		}
		
		if (soNumber != null) {
			_hashCode = 29 * _hashCode + soNumber.hashCode();
		}
		
		if (pickingId != null) {
			_hashCode = 29 * _hashCode + pickingId.hashCode();
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
		
//		_hashCode = 29 * _hashCode + Float.floatToIntBits(qtyDelivery);
		_hashCode = 29 * _hashCode + (qtyDeliveryNull ? 1 : 0);
		if (unitCode != null) {
			_hashCode = 29 * _hashCode + unitCode.hashCode();
		}
		
		if (transporterType != null) {
			_hashCode = 29 * _hashCode + transporterType.hashCode();
		}
		
		if (transporterName != null) {
			_hashCode = 29 * _hashCode + transporterName.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.DoDetail: " );
		ret.append( "deliveryNo=" + deliveryNo );
		ret.append( ", soNumber=" + soNumber );
		ret.append( ", pickingId=" + pickingId );
		ret.append( ", productId=" + productId );
		ret.append( ", productCode=" + productCode );
		ret.append( ", productName=" + productName );
		ret.append( ", qtyDelivery=" + qtyDelivery );
		ret.append( ", unitCode=" + unitCode );
		ret.append( ", transporterType=" + transporterType );
		ret.append( ", transporterName=" + transporterName );
		ret.append( ", userId=" + userId );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		return ret.toString();
	}

}

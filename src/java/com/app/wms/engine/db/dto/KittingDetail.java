package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;

public class KittingDetail implements Serializable
{
	/** 
	 * This attribute maps to the column kitting_no in the kitting_detail table.
	 */
	protected String kittingNo;

	/** 
	 * This attribute maps to the column product_id in the kitting_detail table.
	 */
	protected String productId;

	/** 
	 * This attribute maps to the column product_code in the kitting_detail table.
	 */
	protected String productCode;

	/** 
	 * This attribute maps to the column product_name in the kitting_detail table.
	 */
	protected String productName;

	/** 
	 * This attribute maps to the column unit_code in the kitting_detail table.
	 */
	protected String unitCode;

	/** 
	 * This attribute maps to the column qty_kitting in the kitting_detail table.
	 */
	protected int qtyKitting;

	/** 
	 * This attribute represents whether the primitive attribute qtyKitting is null.
	 */
	protected boolean qtyKittingNull = true;

	/** 
	 * This attribute maps to the column user_id in the kitting_detail table.
	 */
	protected String userId;

	/** 
	 * This attribute maps to the column corp_id in the kitting_detail table.
	 */
	protected String corpId;

	/** 
	 * This attribute maps to the column wh_code in the kitting_detail table.
	 */
	protected String whCode;

	/**
	 * Method 'KittingDetail'
	 * 
	 */
	
	protected String so_number;
	protected int qtyPick;
	
	
	public KittingDetail()
	{
	}
	
	public int getQtyPick() {
		return qtyPick;
	}

	public void setQtyPick(int qtyPick) {
		this.qtyPick = qtyPick;
	}

	public String getSo_number() {
		return so_number;
	}

	public void setSo_number(String so_number) {
		this.so_number = so_number;
	}

	/**
	 * Method 'getKittingNo'
	 * 
	 * @return String
	 */
	public String getKittingNo()
	{
		return kittingNo;
	}

	/**
	 * Method 'setKittingNo'
	 * 
	 * @param kittingNo
	 */
	public void setKittingNo(String kittingNo)
	{
		this.kittingNo = kittingNo;
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
	 * Method 'getQtyKitting'
	 * 
	 * @return int
	 */
	public int getQtyKitting()
	{
		return qtyKitting;
	}

	/**
	 * Method 'setQtyKitting'
	 * 
	 * @param qtyKitting
	 */
	public void setQtyKitting(int qtyKitting)
	{
		this.qtyKitting = qtyKitting;
		this.qtyKittingNull = false;
	}

	/**
	 * Method 'setQtyKittingNull'
	 * 
	 * @param value
	 */
	public void setQtyKittingNull(boolean value)
	{
		this.qtyKittingNull = value;
	}

	/**
	 * Method 'isQtyKittingNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtyKittingNull()
	{
		return qtyKittingNull;
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
		
		if (!(_other instanceof KittingDetail)) {
			return false;
		}
		
		final KittingDetail _cast = (KittingDetail) _other;
		if (kittingNo == null ? _cast.kittingNo != kittingNo : !kittingNo.equals( _cast.kittingNo )) {
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
		
		if (unitCode == null ? _cast.unitCode != unitCode : !unitCode.equals( _cast.unitCode )) {
			return false;
		}
		
		if (qtyKitting != _cast.qtyKitting) {
			return false;
		}
		
		if (qtyKittingNull != _cast.qtyKittingNull) {
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
		if (kittingNo != null) {
			_hashCode = 29 * _hashCode + kittingNo.hashCode();
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
		
		if (unitCode != null) {
			_hashCode = 29 * _hashCode + unitCode.hashCode();
		}
		
		_hashCode = 29 * _hashCode + qtyKitting;
		_hashCode = 29 * _hashCode + (qtyKittingNull ? 1 : 0);
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
	 * Method 'createPk'
	 * 
	 * @return KittingDetailPk
	 */
	public KittingDetailPk createPk()
	{
		return new KittingDetailPk(kittingNo);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.dto.KittingDetail: " );
		ret.append( "kittingNo=" + kittingNo );
		ret.append( ", salesOrderNo=" + so_number );
		ret.append( ", productId=" + productId );
		ret.append( ", productCode=" + productCode );
		ret.append( ", productName=" + productName );
		ret.append( ", unitCode=" + unitCode );
		ret.append( ", qtyPick=" + qtyPick );
		ret.append( ", qtyKitting=" + qtyKitting );
		ret.append( ", userId=" + userId );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		return ret.toString();
	}

}

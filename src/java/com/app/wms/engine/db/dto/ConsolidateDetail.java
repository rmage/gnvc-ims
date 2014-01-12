package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;

public class ConsolidateDetail implements Serializable
{
	/** 
	 * This attribute maps to the column consolidate_no in the consolidate_detail table.
	 */
	protected String consolidateNo;

	/** 
	 * This attribute maps to the column packing_no in the consolidate_detail table.
	 */
	protected String packingNo;

	/** 
	 * This attribute maps to the column product_id in the consolidate_detail table.
	 */
	protected String productId;

	/** 
	 * This attribute maps to the column product_code in the consolidate_detail table.
	 */
	protected String productCode;

	/** 
	 * This attribute maps to the column product_name in the consolidate_detail table.
	 */
	protected String productName;

	/** 
	 * This attribute maps to the column unit_code in the consolidate_detail table.
	 */
	protected String unitCode;

	/** 
	 * This attribute maps to the column qty_consolidate in the consolidate_detail table.
	 */
	protected int qtyConsolidate;

	/** 
	 * This attribute represents whether the primitive attribute qtyConsolidate is null.
	 */
	protected boolean qtyConsolidateNull = true;

	/** 
	 * This attribute maps to the column user_id in the consolidate_detail table.
	 */
	protected String userId;

	/** 
	 * This attribute maps to the column corp_id in the consolidate_detail table.
	 */
	protected String corpId;

	/** 
	 * This attribute maps to the column wh_code in the consolidate_detail table.
	 */
	protected String whCode;

	/**
	 * Method 'ConsolidateDetail'
	 * 
	 */
	public ConsolidateDetail()
	{
	}

	/**
	 * Method 'getConsolidateNo'
	 * 
	 * @return String
	 */
	public String getConsolidateNo()
	{
		return consolidateNo;
	}

	/**
	 * Method 'setConsolidateNo'
	 * 
	 * @param consolidateNo
	 */
	public void setConsolidateNo(String consolidateNo)
	{
		this.consolidateNo = consolidateNo;
	}

	/**
	 * Method 'getPackingNo'
	 * 
	 * @return String
	 */
	public String getPackingNo()
	{
		return packingNo;
	}

	/**
	 * Method 'setPackingNo'
	 * 
	 * @param packingNo
	 */
	public void setPackingNo(String packingNo)
	{
		this.packingNo = packingNo;
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
	 * Method 'getQtyConsolidate'
	 * 
	 * @return int
	 */
	public int getQtyConsolidate()
	{
		return qtyConsolidate;
	}

	/**
	 * Method 'setQtyConsolidate'
	 * 
	 * @param qtyConsolidate
	 */
	public void setQtyConsolidate(int qtyConsolidate)
	{
		this.qtyConsolidate = qtyConsolidate;
		this.qtyConsolidateNull = false;
	}

	/**
	 * Method 'setQtyConsolidateNull'
	 * 
	 * @param value
	 */
	public void setQtyConsolidateNull(boolean value)
	{
		this.qtyConsolidateNull = value;
	}

	/**
	 * Method 'isQtyConsolidateNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtyConsolidateNull()
	{
		return qtyConsolidateNull;
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
		
		if (!(_other instanceof ConsolidateDetail)) {
			return false;
		}
		
		final ConsolidateDetail _cast = (ConsolidateDetail) _other;
		if (consolidateNo == null ? _cast.consolidateNo != consolidateNo : !consolidateNo.equals( _cast.consolidateNo )) {
			return false;
		}
		
		if (packingNo == null ? _cast.packingNo != packingNo : !packingNo.equals( _cast.packingNo )) {
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
		
		if (qtyConsolidate != _cast.qtyConsolidate) {
			return false;
		}
		
		if (qtyConsolidateNull != _cast.qtyConsolidateNull) {
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
		if (consolidateNo != null) {
			_hashCode = 29 * _hashCode + consolidateNo.hashCode();
		}
		
		if (packingNo != null) {
			_hashCode = 29 * _hashCode + packingNo.hashCode();
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
		
		_hashCode = 29 * _hashCode + qtyConsolidate;
		_hashCode = 29 * _hashCode + (qtyConsolidateNull ? 1 : 0);
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
		ret.append( "com.app.wms.engine.dto.ConsolidateDetail: " );
		ret.append( "consolidateNo=" + consolidateNo );
		ret.append( ", packingNo=" + packingNo );
		ret.append( ", productId=" + productId );
		ret.append( ", productCode=" + productCode );
		ret.append( ", productName=" + productName );
		ret.append( ", unitCode=" + unitCode );
		ret.append( ", qtyConsolidate=" + qtyConsolidate );
		ret.append( ", userId=" + userId );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		return ret.toString();
	}

}

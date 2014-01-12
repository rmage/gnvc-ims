package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;

public class PackingDetail implements Serializable
{
	/** 
	 * This attribute maps to the column packing_no in the packing_detail table.
	 */
	protected String packingNo;

	/** 
	 * This attribute maps to the column so_number in the packing_detail table.
	 */
	protected String soNumber;

	/** 
	 * This attribute maps to the column picking_id in the packing_detail table.
	 */
	protected String pickingId;

	/** 
	 * This attribute maps to the column kitting_no in the packing_detail table.
	 */
	protected String kittingNo;

	/** 
	 * This attribute maps to the column product_id in the packing_detail table.
	 */
	protected String productId;

	/** 
	 * This attribute maps to the column product_code in the packing_detail table.
	 */
	protected String productCode;

	/** 
	 * This attribute maps to the column product_name in the packing_detail table.
	 */
	protected String productName;

	/** 
	 * This attribute maps to the column unitcode in the packing_detail table.
	 */
	protected String unitcode;

	/** 
	 * This attribute maps to the column quantity in the packing_detail table.
	 */
	protected int quantity;

	/** 
	 * This attribute represents whether the primitive attribute quantity is null.
	 */
	protected boolean quantityNull = true;

	/** 
	 * This attribute maps to the column user_id in the packing_detail table.
	 */
	protected String userId;

	/** 
	 * This attribute maps to the column corp_id in the packing_detail table.
	 */
	protected String corpId;

	/** 
	 * This attribute maps to the column wh_code in the packing_detail table.
	 */
	protected String whCode;

	/**
	 * Method 'PackingDetail'
	 * 
	 */
	public PackingDetail()
	{
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
	 * Method 'getUnitcode'
	 * 
	 * @return String
	 */
	public String getUnitcode()
	{
		return unitcode;
	}

	/**
	 * Method 'setUnitcode'
	 * 
	 * @param unitcode
	 */
	public void setUnitcode(String unitcode)
	{
		this.unitcode = unitcode;
	}

	/**
	 * Method 'getQuantity'
	 * 
	 * @return int
	 */
	public int getQuantity()
	{
		return quantity;
	}

	/**
	 * Method 'setQuantity'
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
		this.quantityNull = false;
	}

	/**
	 * Method 'setQuantityNull'
	 * 
	 * @param value
	 */
	public void setQuantityNull(boolean value)
	{
		this.quantityNull = value;
	}

	/**
	 * Method 'isQuantityNull'
	 * 
	 * @return boolean
	 */
	public boolean isQuantityNull()
	{
		return quantityNull;
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
		
		if (!(_other instanceof PackingDetail)) {
			return false;
		}
		
		final PackingDetail _cast = (PackingDetail) _other;
		if (packingNo == null ? _cast.packingNo != packingNo : !packingNo.equals( _cast.packingNo )) {
			return false;
		}
		
		if (soNumber == null ? _cast.soNumber != soNumber : !soNumber.equals( _cast.soNumber )) {
			return false;
		}
		
		if (pickingId == null ? _cast.pickingId != pickingId : !pickingId.equals( _cast.pickingId )) {
			return false;
		}
		
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
		
		if (unitcode == null ? _cast.unitcode != unitcode : !unitcode.equals( _cast.unitcode )) {
			return false;
		}
		
		if (quantity != _cast.quantity) {
			return false;
		}
		
		if (quantityNull != _cast.quantityNull) {
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
		if (packingNo != null) {
			_hashCode = 29 * _hashCode + packingNo.hashCode();
		}
		
		if (soNumber != null) {
			_hashCode = 29 * _hashCode + soNumber.hashCode();
		}
		
		if (pickingId != null) {
			_hashCode = 29 * _hashCode + pickingId.hashCode();
		}
		
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
		
		if (unitcode != null) {
			_hashCode = 29 * _hashCode + unitcode.hashCode();
		}
		
		_hashCode = 29 * _hashCode + quantity;
		_hashCode = 29 * _hashCode + (quantityNull ? 1 : 0);
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
	 * @return PackingDetailPk
	 */
	public PackingDetailPk createPk()
	{
		return new PackingDetailPk(packingNo);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.PackingDetail: " );
		ret.append( "packingNo=" + packingNo );
		ret.append( ", soNumber=" + soNumber );
		ret.append( ", pickingId=" + pickingId );
		ret.append( ", kittingNo=" + kittingNo );
		ret.append( ", productId=" + productId );
		ret.append( ", productCode=" + productCode );
		ret.append( ", productName=" + productName );
		ret.append( ", unitcode=" + unitcode );
		ret.append( ", quantity=" + quantity );
		ret.append( ", userId=" + userId );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		return ret.toString();
	}

}

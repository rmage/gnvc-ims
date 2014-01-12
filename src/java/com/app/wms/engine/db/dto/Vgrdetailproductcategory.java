package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Vgrdetailproductcategory implements Serializable
{
	/** 
	 * This attribute maps to the column id in the vgrdetailproductcategory table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column grnumber in the vgrdetailproductcategory table.
	 */
	protected String grnumber;

	/** 
	 * This attribute maps to the column productcode in the vgrdetailproductcategory table.
	 */
	protected String productcode;

	/** 
	 * This attribute maps to the column qtyreal in the vgrdetailproductcategory table.
	 */
	protected int qtyreal;

	/** 
	 * This attribute represents whether the primitive attribute qtyreal is null.
	 */
	protected boolean qtyrealNull = true;

	/** 
	 * This attribute maps to the column status in the vgrdetailproductcategory table.
	 */
	protected String status;

	/** 
	 * This attribute maps to the column expirydate in the vgrdetailproductcategory table.
	 */
	protected Date expirydate;

	/** 
	 * This attribute maps to the column remark in the vgrdetailproductcategory table.
	 */
	protected String remark;

	/** 
	 * This attribute maps to the column lotid in the vgrdetailproductcategory table.
	 */
	protected int lotid;

	/** 
	 * This attribute represents whether the primitive attribute lotid is null.
	 */
	protected boolean lotidNull = true;

	/** 
	 * This attribute maps to the column product_name in the vgrdetailproductcategory table.
	 */
	protected String productName;

	/** 
	 * This attribute maps to the column category_name in the vgrdetailproductcategory table.
	 */
	protected String categoryName;

	/** 
	 * This attribute maps to the column qtygood in the vgrdetailproductcategory table.
	 */
	protected int qtygood;

	/** 
	 * This attribute represents whether the primitive attribute qtygood is null.
	 */
	protected boolean qtygoodNull = true;

	/** 
	 * This attribute maps to the column qtydmg in the vgrdetailproductcategory table.
	 */
	protected int qtydmg;

	/** 
	 * This attribute represents whether the primitive attribute qtydmg is null.
	 */
	protected boolean qtydmgNull = true;

	/** 
	 * This attribute maps to the column producttype in the vgrdetailproductcategory table.
	 */
	protected String producttype;

	/** 
	 * This attribute maps to the column wh_code in the vgrdetailproductcategory table.
	 */
	protected String whCode;

	/** 
	 * This attribute maps to the column corp_id in the vgrdetailproductcategory table.
	 */
	protected String corpId;

	/**
	 * Method 'Vgrdetailproductcategory'
	 * 
	 */
	public Vgrdetailproductcategory()
	{
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
	 * Method 'getGrnumber'
	 * 
	 * @return String
	 */
	public String getGrnumber()
	{
		return grnumber;
	}

	/**
	 * Method 'setGrnumber'
	 * 
	 * @param grnumber
	 */
	public void setGrnumber(String grnumber)
	{
		this.grnumber = grnumber;
	}

	/**
	 * Method 'getProductcode'
	 * 
	 * @return String
	 */
	public String getProductcode()
	{
		return productcode;
	}

	/**
	 * Method 'setProductcode'
	 * 
	 * @param productcode
	 */
	public void setProductcode(String productcode)
	{
		this.productcode = productcode;
	}

	/**
	 * Method 'getQtyreal'
	 * 
	 * @return int
	 */
	public int getQtyreal()
	{
		return qtyreal;
	}

	/**
	 * Method 'setQtyreal'
	 * 
	 * @param qtyreal
	 */
	public void setQtyreal(int qtyreal)
	{
		this.qtyreal = qtyreal;
		this.qtyrealNull = false;
	}

	/**
	 * Method 'setQtyrealNull'
	 * 
	 * @param value
	 */
	public void setQtyrealNull(boolean value)
	{
		this.qtyrealNull = value;
	}

	/**
	 * Method 'isQtyrealNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtyrealNull()
	{
		return qtyrealNull;
	}

	/**
	 * Method 'getStatus'
	 * 
	 * @return String
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * Method 'setStatus'
	 * 
	 * @param status
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

	/**
	 * Method 'getExpirydate'
	 * 
	 * @return Date
	 */
	public Date getExpirydate()
	{
		return expirydate;
	}

	/**
	 * Method 'setExpirydate'
	 * 
	 * @param expirydate
	 */
	public void setExpirydate(Date expirydate)
	{
		this.expirydate = expirydate;
	}

	/**
	 * Method 'getRemark'
	 * 
	 * @return String
	 */
	public String getRemark()
	{
		return remark;
	}

	/**
	 * Method 'setRemark'
	 * 
	 * @param remark
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	/**
	 * Method 'getLotid'
	 * 
	 * @return int
	 */
	public int getLotid()
	{
		return lotid;
	}

	/**
	 * Method 'setLotid'
	 * 
	 * @param lotid
	 */
	public void setLotid(int lotid)
	{
		this.lotid = lotid;
		this.lotidNull = false;
	}

	/**
	 * Method 'setLotidNull'
	 * 
	 * @param value
	 */
	public void setLotidNull(boolean value)
	{
		this.lotidNull = value;
	}

	/**
	 * Method 'isLotidNull'
	 * 
	 * @return boolean
	 */
	public boolean isLotidNull()
	{
		return lotidNull;
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
	 * Method 'getCategoryName'
	 * 
	 * @return String
	 */
	public String getCategoryName()
	{
		return categoryName;
	}

	/**
	 * Method 'setCategoryName'
	 * 
	 * @param categoryName
	 */
	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}

	/**
	 * Method 'getQtygood'
	 * 
	 * @return int
	 */
	public int getQtygood()
	{
		return qtygood;
	}

	/**
	 * Method 'setQtygood'
	 * 
	 * @param qtygood
	 */
	public void setQtygood(int qtygood)
	{
		this.qtygood = qtygood;
		this.qtygoodNull = false;
	}

	/**
	 * Method 'setQtygoodNull'
	 * 
	 * @param value
	 */
	public void setQtygoodNull(boolean value)
	{
		this.qtygoodNull = value;
	}

	/**
	 * Method 'isQtygoodNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtygoodNull()
	{
		return qtygoodNull;
	}

	/**
	 * Method 'getQtydmg'
	 * 
	 * @return int
	 */
	public int getQtydmg()
	{
		return qtydmg;
	}

	/**
	 * Method 'setQtydmg'
	 * 
	 * @param qtydmg
	 */
	public void setQtydmg(int qtydmg)
	{
		this.qtydmg = qtydmg;
		this.qtydmgNull = false;
	}

	/**
	 * Method 'setQtydmgNull'
	 * 
	 * @param value
	 */
	public void setQtydmgNull(boolean value)
	{
		this.qtydmgNull = value;
	}

	/**
	 * Method 'isQtydmgNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtydmgNull()
	{
		return qtydmgNull;
	}

	/**
	 * Method 'getProducttype'
	 * 
	 * @return String
	 */
	public String getProducttype()
	{
		return producttype;
	}

	/**
	 * Method 'setProducttype'
	 * 
	 * @param producttype
	 */
	public void setProducttype(String producttype)
	{
		this.producttype = producttype;
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
		
		if (!(_other instanceof Vgrdetailproductcategory)) {
			return false;
		}
		
		final Vgrdetailproductcategory _cast = (Vgrdetailproductcategory) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (grnumber == null ? _cast.grnumber != grnumber : !grnumber.equals( _cast.grnumber )) {
			return false;
		}
		
		if (productcode == null ? _cast.productcode != productcode : !productcode.equals( _cast.productcode )) {
			return false;
		}
		
		if (qtyreal != _cast.qtyreal) {
			return false;
		}
		
		if (qtyrealNull != _cast.qtyrealNull) {
			return false;
		}
		
		if (status == null ? _cast.status != status : !status.equals( _cast.status )) {
			return false;
		}
		
		if (expirydate == null ? _cast.expirydate != expirydate : !expirydate.equals( _cast.expirydate )) {
			return false;
		}
		
		if (remark == null ? _cast.remark != remark : !remark.equals( _cast.remark )) {
			return false;
		}
		
		if (lotid != _cast.lotid) {
			return false;
		}
		
		if (lotidNull != _cast.lotidNull) {
			return false;
		}
		
		if (productName == null ? _cast.productName != productName : !productName.equals( _cast.productName )) {
			return false;
		}
		
		if (categoryName == null ? _cast.categoryName != categoryName : !categoryName.equals( _cast.categoryName )) {
			return false;
		}
		
		if (qtygood != _cast.qtygood) {
			return false;
		}
		
		if (qtygoodNull != _cast.qtygoodNull) {
			return false;
		}
		
		if (qtydmg != _cast.qtydmg) {
			return false;
		}
		
		if (qtydmgNull != _cast.qtydmgNull) {
			return false;
		}
		
		if (producttype == null ? _cast.producttype != producttype : !producttype.equals( _cast.producttype )) {
			return false;
		}
		
		if (whCode == null ? _cast.whCode != whCode : !whCode.equals( _cast.whCode )) {
			return false;
		}
		
		if (corpId == null ? _cast.corpId != corpId : !corpId.equals( _cast.corpId )) {
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
		if (grnumber != null) {
			_hashCode = 29 * _hashCode + grnumber.hashCode();
		}
		
		if (productcode != null) {
			_hashCode = 29 * _hashCode + productcode.hashCode();
		}
		
		_hashCode = 29 * _hashCode + qtyreal;
		_hashCode = 29 * _hashCode + (qtyrealNull ? 1 : 0);
		if (status != null) {
			_hashCode = 29 * _hashCode + status.hashCode();
		}
		
		if (expirydate != null) {
			_hashCode = 29 * _hashCode + expirydate.hashCode();
		}
		
		if (remark != null) {
			_hashCode = 29 * _hashCode + remark.hashCode();
		}
		
		_hashCode = 29 * _hashCode + lotid;
		_hashCode = 29 * _hashCode + (lotidNull ? 1 : 0);
		if (productName != null) {
			_hashCode = 29 * _hashCode + productName.hashCode();
		}
		
		if (categoryName != null) {
			_hashCode = 29 * _hashCode + categoryName.hashCode();
		}
		
		_hashCode = 29 * _hashCode + qtygood;
		_hashCode = 29 * _hashCode + (qtygoodNull ? 1 : 0);
		_hashCode = 29 * _hashCode + qtydmg;
		_hashCode = 29 * _hashCode + (qtydmgNull ? 1 : 0);
		if (producttype != null) {
			_hashCode = 29 * _hashCode + producttype.hashCode();
		}
		
		if (whCode != null) {
			_hashCode = 29 * _hashCode + whCode.hashCode();
		}
		
		if (corpId != null) {
			_hashCode = 29 * _hashCode + corpId.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.Vgrdetailproductcategory: " );
		ret.append( "id=" + id );
		ret.append( ", grnumber=" + grnumber );
		ret.append( ", productcode=" + productcode );
		ret.append( ", qtyreal=" + qtyreal );
		ret.append( ", status=" + status );
		ret.append( ", expirydate=" + expirydate );
		ret.append( ", remark=" + remark );
		ret.append( ", lotid=" + lotid );
		ret.append( ", productName=" + productName );
		ret.append( ", categoryName=" + categoryName );
		ret.append( ", qtygood=" + qtygood );
		ret.append( ", qtydmg=" + qtydmg );
		ret.append( ", producttype=" + producttype );
		ret.append( ", whCode=" + whCode );
		ret.append( ", corpId=" + corpId );
		return ret.toString();
	}

}

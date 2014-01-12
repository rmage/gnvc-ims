package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Bor implements Serializable
{
	/** 
	 * This attribute maps to the column id in the bor table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column bornumber in the bor table.
	 */
	protected String bornumber;

	/** 
	 * This attribute maps to the column bordate in the bor table.
	 */
	protected Date bordate;

	/** 
	 * This attribute maps to the column borreferensi in the bor table.
	 */
	protected String borreferensi;

	/** 
	 * This attribute maps to the column createdby in the bor table.
	 */
	protected String createdby;

	/** 
	 * This attribute maps to the column buyer_name in the bor table.
	 */
	protected String buyerName;

	/**
	 * Method 'Bor'
	 * 
	 */
	public Bor()
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
	 * Method 'getBornumber'
	 * 
	 * @return String
	 */
	public String getBornumber()
	{
		return bornumber;
	}

	/**
	 * Method 'setBornumber'
	 * 
	 * @param bornumber
	 */
	public void setBornumber(String bornumber)
	{
		this.bornumber = bornumber;
	}

	/**
	 * Method 'getBordate'
	 * 
	 * @return Date
	 */
	public Date getBordate()
	{
		return bordate;
	}

	/**
	 * Method 'setBordate'
	 * 
	 * @param bordate
	 */
	public void setBordate(Date bordate)
	{
		this.bordate = bordate;
	}

	/**
	 * Method 'getBorreferensi'
	 * 
	 * @return String
	 */
	public String getBorreferensi()
	{
		return borreferensi;
	}

	/**
	 * Method 'setBorreferensi'
	 * 
	 * @param borreferensi
	 */
	public void setBorreferensi(String borreferensi)
	{
		this.borreferensi = borreferensi;
	}

	/**
	 * Method 'getCreatedby'
	 * 
	 * @return String
	 */
	public String getCreatedby()
	{
		return createdby;
	}

	/**
	 * Method 'setCreatedby'
	 * 
	 * @param createdby
	 */
	public void setCreatedby(String createdby)
	{
		this.createdby = createdby;
	}

	/**
	 * Method 'getBuyerName'
	 * 
	 * @return String
	 */
	public String getBuyerName()
	{
		return buyerName;
	}

	/**
	 * Method 'setBuyerName'
	 * 
	 * @param buyerName
	 */
	public void setBuyerName(String buyerName)
	{
		this.buyerName = buyerName;
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
		
		if (!(_other instanceof Bor)) {
			return false;
		}
		
		final Bor _cast = (Bor) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (bornumber == null ? _cast.bornumber != bornumber : !bornumber.equals( _cast.bornumber )) {
			return false;
		}
		
		if (bordate == null ? _cast.bordate != bordate : !bordate.equals( _cast.bordate )) {
			return false;
		}
		
		if (borreferensi == null ? _cast.borreferensi != borreferensi : !borreferensi.equals( _cast.borreferensi )) {
			return false;
		}
		
		if (createdby == null ? _cast.createdby != createdby : !createdby.equals( _cast.createdby )) {
			return false;
		}
		
		if (buyerName == null ? _cast.buyerName != buyerName : !buyerName.equals( _cast.buyerName )) {
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
		if (bornumber != null) {
			_hashCode = 29 * _hashCode + bornumber.hashCode();
		}
		
		if (bordate != null) {
			_hashCode = 29 * _hashCode + bordate.hashCode();
		}
		
		if (borreferensi != null) {
			_hashCode = 29 * _hashCode + borreferensi.hashCode();
		}
		
		if (createdby != null) {
			_hashCode = 29 * _hashCode + createdby.hashCode();
		}
		
		if (buyerName != null) {
			_hashCode = 29 * _hashCode + buyerName.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return BorPk
	 */
	public BorPk createPk()
	{
		return new BorPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Bor: " );
		ret.append( "id=" + id );
		ret.append( ", bornumber=" + bornumber );
		ret.append( ", bordate=" + bordate );
		ret.append( ", borreferensi=" + borreferensi );
		ret.append( ", createdby=" + createdby );
		ret.append( ", buyerName=" + buyerName );
		return ret.toString();
	}

}

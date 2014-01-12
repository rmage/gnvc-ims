package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Goodreceive implements Serializable
{
	/** 
	 * This attribute maps to the column id in the goodreceive table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column grnumber in the goodreceive table.
	 */
	protected String grnumber;

	/** 
	 * This attribute maps to the column ponumber in the goodreceive table.
	 */
	protected String ponumber;

	/** 
	 * This attribute maps to the column receiveddate in the goodreceive table.
	 */
	protected Date receiveddate;

	/** 
	 * This attribute maps to the column createdby in the goodreceive table.
	 */
	protected String createdby;

	/** 
	 * This attribute maps to the column corpid in the goodreceive table.
	 */
	protected String corpid;

	/** 
	 * This attribute maps to the column lotid in the goodreceive table.
	 */
	protected String lotid;

	/** 
	 * This attribute maps to the column wh_code in the goodreceive table.
	 */
	protected String whCode;

	/**
	 * Method 'Goodreceive'
	 * 
	 */
	public Goodreceive()
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
	 * Method 'getPonumber'
	 * 
	 * @return String
	 */
	public String getPonumber()
	{
		return ponumber;
	}

	/**
	 * Method 'setPonumber'
	 * 
	 * @param ponumber
	 */
	public void setPonumber(String ponumber)
	{
		this.ponumber = ponumber;
	}

	/**
	 * Method 'getReceiveddate'
	 * 
	 * @return Date
	 */
	public Date getReceiveddate()
	{
		return receiveddate;
	}

	/**
	 * Method 'setReceiveddate'
	 * 
	 * @param receiveddate
	 */
	public void setReceiveddate(Date receiveddate)
	{
		this.receiveddate = receiveddate;
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
	 * Method 'getCorpid'
	 * 
	 * @return String
	 */
	public String getCorpid()
	{
		return corpid;
	}

	/**
	 * Method 'setCorpid'
	 * 
	 * @param corpid
	 */
	public void setCorpid(String corpid)
	{
		this.corpid = corpid;
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
		
		if (!(_other instanceof Goodreceive)) {
			return false;
		}
		
		final Goodreceive _cast = (Goodreceive) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (grnumber == null ? _cast.grnumber != grnumber : !grnumber.equals( _cast.grnumber )) {
			return false;
		}
		
		if (ponumber == null ? _cast.ponumber != ponumber : !ponumber.equals( _cast.ponumber )) {
			return false;
		}
		
		if (receiveddate == null ? _cast.receiveddate != receiveddate : !receiveddate.equals( _cast.receiveddate )) {
			return false;
		}
		
		if (createdby == null ? _cast.createdby != createdby : !createdby.equals( _cast.createdby )) {
			return false;
		}
		
		if (corpid == null ? _cast.corpid != corpid : !corpid.equals( _cast.corpid )) {
			return false;
		}
		
		if (lotid == null ? _cast.lotid != lotid : !lotid.equals( _cast.lotid )) {
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
		_hashCode = 29 * _hashCode + id;
		if (grnumber != null) {
			_hashCode = 29 * _hashCode + grnumber.hashCode();
		}
		
		if (ponumber != null) {
			_hashCode = 29 * _hashCode + ponumber.hashCode();
		}
		
		if (receiveddate != null) {
			_hashCode = 29 * _hashCode + receiveddate.hashCode();
		}
		
		if (createdby != null) {
			_hashCode = 29 * _hashCode + createdby.hashCode();
		}
		
		if (corpid != null) {
			_hashCode = 29 * _hashCode + corpid.hashCode();
		}
		
		if (lotid != null) {
			_hashCode = 29 * _hashCode + lotid.hashCode();
		}
		
		if (whCode != null) {
			_hashCode = 29 * _hashCode + whCode.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return GoodreceivePk
	 */
	public GoodreceivePk createPk()
	{
		return new GoodreceivePk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Goodreceive: " );
		ret.append( "id=" + id );
		ret.append( ", grnumber=" + grnumber );
		ret.append( ", ponumber=" + ponumber );
		ret.append( ", receiveddate=" + receiveddate );
		ret.append( ", createdby=" + createdby );
		ret.append( ", corpid=" + corpid );
		ret.append( ", lotid=" + lotid );
		ret.append( ", whCode=" + whCode );
		return ret.toString();
	}

}

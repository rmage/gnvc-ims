package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;

public class WhLocatingArea implements Serializable
{
	/** 
	 * This attribute maps to the column id in the wh_locating_area table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column locating_id in the wh_locating_area table.
	 */
	protected String locatingId;

	/** 
	 * This attribute maps to the column locating_area in the wh_locating_area table.
	 */
	protected String locatingArea;

	/** 
	 * This attribute maps to the column locating_condition in the wh_locating_area table.
	 */
	protected String locatingCondition;
	
	protected String userId;
	
	protected String corpId;
	
	protected String whCode;

	/**
	 * Method 'WhLocatingArea'
	 * 
	 */
	public WhLocatingArea()
	{
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getWhCode() {
		return whCode;
	}

	public void setWhCode(String whCode) {
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
	 * Method 'getLocatingId'
	 * 
	 * @return String
	 */
	public String getLocatingId()
	{
		return locatingId;
	}

	/**
	 * Method 'setLocatingId'
	 * 
	 * @param locatingId
	 */
	public void setLocatingId(String locatingId)
	{
		this.locatingId = locatingId;
	}

	/**
	 * Method 'getLocatingArea'
	 * 
	 * @return String
	 */
	public String getLocatingArea()
	{
		return locatingArea;
	}

	/**
	 * Method 'setLocatingArea'
	 * 
	 * @param locatingArea
	 */
	public void setLocatingArea(String locatingArea)
	{
		this.locatingArea = locatingArea;
	}

	/**
	 * Method 'getLocatingCondition'
	 * 
	 * @return String
	 */
	public String getLocatingCondition()
	{
		return locatingCondition;
	}

	/**
	 * Method 'setLocatingCondition'
	 * 
	 * @param locatingCondition
	 */
	public void setLocatingCondition(String locatingCondition)
	{
		this.locatingCondition = locatingCondition;
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
		
		if (!(_other instanceof WhLocatingArea)) {
			return false;
		}
		
		final WhLocatingArea _cast = (WhLocatingArea) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (locatingId == null ? _cast.locatingId != locatingId : !locatingId.equals( _cast.locatingId )) {
			return false;
		}
		
		if (locatingArea == null ? _cast.locatingArea != locatingArea : !locatingArea.equals( _cast.locatingArea )) {
			return false;
		}
		
		if (locatingCondition == null ? _cast.locatingCondition != locatingCondition : !locatingCondition.equals( _cast.locatingCondition )) {
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
		if (locatingId != null) {
			_hashCode = 29 * _hashCode + locatingId.hashCode();
		}
		
		if (locatingArea != null) {
			_hashCode = 29 * _hashCode + locatingArea.hashCode();
		}
		
		if (locatingCondition != null) {
			_hashCode = 29 * _hashCode + locatingCondition.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return WhLocatingAreaPk
	 */
	public WhLocatingAreaPk createPk()
	{
		return new WhLocatingAreaPk(locatingId);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.WhLocatingArea: " );
		ret.append( "id=" + id );
		ret.append( ", locatingId=" + locatingId );
		ret.append( ", locatingArea=" + locatingArea );
		ret.append( ", locatingCondition=" + locatingCondition );
		ret.append( ", userId=" + userId );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		return ret.toString();
	}

}

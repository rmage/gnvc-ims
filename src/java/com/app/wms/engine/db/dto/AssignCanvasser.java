package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class AssignCanvasser implements Serializable
{
	/** 
	 * This attribute maps to the column id in the canvasserassignment table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column prsnumber in the canvasserassignment table.
	 */
	protected String prsnumber;

	/** 
	 * This attribute maps to the column canvassername in the canvasserassignment table.
	 */
	protected String canvassername;

	/** 
	 * This attribute maps to the column created_by in the canvasserassignment table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the canvasserassignment table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the canvasserassignment table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the canvasserassignment table.
	 */
	protected Date updatedDate;

	/**
	 * Method 'Canvasserassignment'
	 * 
	 */
	public AssignCanvasser()
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
	 * Method 'getPrsnumber'
	 * 
	 * @return String
	 */
	public String getPrsnumber()
	{
		return prsnumber;
	}

	/**
	 * Method 'setPrsnumber'
	 * 
	 * @param prsnumber
	 */
	public void setPrsnumber(String prsnumber)
	{
		this.prsnumber = prsnumber;
	}

	/**
	 * Method 'getCanvassername'
	 * 
	 * @return String
	 */
	public String getCanvassername()
	{
		return canvassername;
	}

	/**
	 * Method 'setCanvassername'
	 * 
	 * @param canvassername
	 */
	public void setCanvassername(String canvassername)
	{
		this.canvassername = canvassername;
	}

	/**
	 * Method 'getCreatedBy'
	 * 
	 * @return String
	 */
	public String getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * Method 'setCreatedBy'
	 * 
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	/**
	 * Method 'getCreatedDate'
	 * 
	 * @return Date
	 */
	public Date getCreatedDate()
	{
		return createdDate;
	}

	/**
	 * Method 'setCreatedDate'
	 * 
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	/**
	 * Method 'getUpdatedBy'
	 * 
	 * @return String
	 */
	public String getUpdatedBy()
	{
		return updatedBy;
	}

	/**
	 * Method 'setUpdatedBy'
	 * 
	 * @param updatedBy
	 */
	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	/**
	 * Method 'getUpdatedDate'
	 * 
	 * @return Date
	 */
	public Date getUpdatedDate()
	{
		return updatedDate;
	}

	/**
	 * Method 'setUpdatedDate'
	 * 
	 * @param updatedDate
	 */
	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
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
		
		if (!(_other instanceof AssignCanvasser)) {
			return false;
		}
		
		final AssignCanvasser _cast = (AssignCanvasser) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (prsnumber == null ? _cast.prsnumber != prsnumber : !prsnumber.equals( _cast.prsnumber )) {
			return false;
		}
		
		if (canvassername == null ? _cast.canvassername != canvassername : !canvassername.equals( _cast.canvassername )) {
			return false;
		}
		
		if (createdBy == null ? _cast.createdBy != createdBy : !createdBy.equals( _cast.createdBy )) {
			return false;
		}
		
		if (createdDate == null ? _cast.createdDate != createdDate : !createdDate.equals( _cast.createdDate )) {
			return false;
		}
		
		if (updatedBy == null ? _cast.updatedBy != updatedBy : !updatedBy.equals( _cast.updatedBy )) {
			return false;
		}
		
		if (updatedDate == null ? _cast.updatedDate != updatedDate : !updatedDate.equals( _cast.updatedDate )) {
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
		if (prsnumber != null) {
			_hashCode = 29 * _hashCode + prsnumber.hashCode();
		}
		
		if (canvassername != null) {
			_hashCode = 29 * _hashCode + canvassername.hashCode();
		}
		
		if (createdBy != null) {
			_hashCode = 29 * _hashCode + createdBy.hashCode();
		}
		
		if (createdDate != null) {
			_hashCode = 29 * _hashCode + createdDate.hashCode();
		}
		
		if (updatedBy != null) {
			_hashCode = 29 * _hashCode + updatedBy.hashCode();
		}
		
		if (updatedDate != null) {
			_hashCode = 29 * _hashCode + updatedDate.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return AssignCanvasserPk
	 */
	public AssignCanvasserPk createPk()
	{
		return new AssignCanvasserPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Canvasserassignment: " );
		ret.append( "id=" + id );
		ret.append( ", prsnumber=" + prsnumber );
		ret.append( ", canvassername=" + canvassername );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}

package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Eds implements Serializable
{
	/** 
	 * This attribute maps to the column id in the eds table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column edsnumber in the eds table.
	 */
	protected String edsnumber;

	/** 
	 * This attribute maps to the column edsdate in the eds table.
	 */
	protected Date edsdate;

	/** 
	 * This attribute maps to the column cinumber in the eds table.
	 */
	protected String cinumber;

	/** 
	 * This attribute maps to the column buyerName in the eds table.
	 */
	protected String buyerName;

	/** 
	 * This attribute maps to the column brand in the eds table.
	 */
	protected String brand;

	/** 
	 * This attribute maps to the column referensi in the eds table.
	 */
	protected String referensi;

	/** 
	 * This attribute maps to the column destination in the eds table.
	 */
	protected String destination;

	/** 
	 * This attribute maps to the column van in the eds table.
	 */
	protected String van;

	/** 
	 * This attribute maps to the column sealShip in the eds table.
	 */
	protected String sealShip;

	/** 
	 * This attribute maps to the column vessel in the eds table.
	 */
	protected String vessel;

	/** 
	 * This attribute maps to the column platNo in the eds table.
	 */
	protected String platNo;

	/** 
	 * This attribute maps to the column time_in in the eds table.
	 */
	protected Date timeIn;

	/** 
	 * This attribute maps to the column time_out in the eds table.
	 */
	protected Date timeOut;

	/** 
	 * This attribute maps to the column driverName in the eds table.
	 */
	protected String driverName;

	/** 
	 * This attribute maps to the column issuedBy in the eds table.
	 */
	protected String issuedBy;

	/** 
	 * This attribute maps to the column checkedBy in the eds table.
	 */
	protected String checkedBy;

	/** 
	 * This attribute maps to the column approvedBy in the eds table.
	 */
	protected String approvedBy;

	/** 
	 * This attribute maps to the column remarks in the eds table.
	 */
	protected String remarks;

	/**
	 * Method 'Eds'
	 * 
	 */
	public Eds()
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
	 * Method 'getEdsnumber'
	 * 
	 * @return String
	 */
	public String getEdsnumber()
	{
		return edsnumber;
	}

	/**
	 * Method 'setEdsnumber'
	 * 
	 * @param edsnumber
	 */
	public void setEdsnumber(String edsnumber)
	{
		this.edsnumber = edsnumber;
	}

	/**
	 * Method 'getEdsdate'
	 * 
	 * @return Date
	 */
	public Date getEdsdate()
	{
		return edsdate;
	}

	/**
	 * Method 'setEdsdate'
	 * 
	 * @param edsdate
	 */
	public void setEdsdate(Date edsdate)
	{
		this.edsdate = edsdate;
	}

	/**
	 * Method 'getCinumber'
	 * 
	 * @return String
	 */
	public String getCinumber()
	{
		return cinumber;
	}

	/**
	 * Method 'setCinumber'
	 * 
	 * @param cinumber
	 */
	public void setCinumber(String cinumber)
	{
		this.cinumber = cinumber;
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
	 * Method 'getBrand'
	 * 
	 * @return String
	 */
	public String getBrand()
	{
		return brand;
	}

	/**
	 * Method 'setBrand'
	 * 
	 * @param brand
	 */
	public void setBrand(String brand)
	{
		this.brand = brand;
	}

	/**
	 * Method 'getReferensi'
	 * 
	 * @return String
	 */
	public String getReferensi()
	{
		return referensi;
	}

	/**
	 * Method 'setReferensi'
	 * 
	 * @param referensi
	 */
	public void setReferensi(String referensi)
	{
		this.referensi = referensi;
	}

	/**
	 * Method 'getDestination'
	 * 
	 * @return String
	 */
	public String getDestination()
	{
		return destination;
	}

	/**
	 * Method 'setDestination'
	 * 
	 * @param destination
	 */
	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	/**
	 * Method 'getVan'
	 * 
	 * @return String
	 */
	public String getVan()
	{
		return van;
	}

	/**
	 * Method 'setVan'
	 * 
	 * @param van
	 */
	public void setVan(String van)
	{
		this.van = van;
	}

	/**
	 * Method 'getSealShip'
	 * 
	 * @return String
	 */
	public String getSealShip()
	{
		return sealShip;
	}

	/**
	 * Method 'setSealShip'
	 * 
	 * @param sealShip
	 */
	public void setSealShip(String sealShip)
	{
		this.sealShip = sealShip;
	}

	/**
	 * Method 'getVessel'
	 * 
	 * @return String
	 */
	public String getVessel()
	{
		return vessel;
	}

	/**
	 * Method 'setVessel'
	 * 
	 * @param vessel
	 */
	public void setVessel(String vessel)
	{
		this.vessel = vessel;
	}

	/**
	 * Method 'getPlatNo'
	 * 
	 * @return String
	 */
	public String getPlatNo()
	{
		return platNo;
	}

	/**
	 * Method 'setPlatNo'
	 * 
	 * @param platNo
	 */
	public void setPlatNo(String platNo)
	{
		this.platNo = platNo;
	}

	/**
	 * Method 'getTimeIn'
	 * 
	 * @return Date
	 */
	public Date getTimeIn()
	{
		return timeIn;
	}

	/**
	 * Method 'setTimeIn'
	 * 
	 * @param timeIn
	 */
	public void setTimeIn(Date timeIn)
	{
		this.timeIn = timeIn;
	}

	/**
	 * Method 'getTimeOut'
	 * 
	 * @return Date
	 */
	public Date getTimeOut()
	{
		return timeOut;
	}

	/**
	 * Method 'setTimeOut'
	 * 
	 * @param timeOut
	 */
	public void setTimeOut(Date timeOut)
	{
		this.timeOut = timeOut;
	}

	/**
	 * Method 'getDriverName'
	 * 
	 * @return String
	 */
	public String getDriverName()
	{
		return driverName;
	}

	/**
	 * Method 'setDriverName'
	 * 
	 * @param driverName
	 */
	public void setDriverName(String driverName)
	{
		this.driverName = driverName;
	}

	/**
	 * Method 'getIssuedBy'
	 * 
	 * @return String
	 */
	public String getIssuedBy()
	{
		return issuedBy;
	}

	/**
	 * Method 'setIssuedBy'
	 * 
	 * @param issuedBy
	 */
	public void setIssuedBy(String issuedBy)
	{
		this.issuedBy = issuedBy;
	}

	/**
	 * Method 'getCheckedBy'
	 * 
	 * @return String
	 */
	public String getCheckedBy()
	{
		return checkedBy;
	}

	/**
	 * Method 'setCheckedBy'
	 * 
	 * @param checkedBy
	 */
	public void setCheckedBy(String checkedBy)
	{
		this.checkedBy = checkedBy;
	}

	/**
	 * Method 'getApprovedBy'
	 * 
	 * @return String
	 */
	public String getApprovedBy()
	{
		return approvedBy;
	}

	/**
	 * Method 'setApprovedBy'
	 * 
	 * @param approvedBy
	 */
	public void setApprovedBy(String approvedBy)
	{
		this.approvedBy = approvedBy;
	}

	/**
	 * Method 'getRemarks'
	 * 
	 * @return String
	 */
	public String getRemarks()
	{
		return remarks;
	}

	/**
	 * Method 'setRemarks'
	 * 
	 * @param remarks
	 */
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
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
		
		if (!(_other instanceof Eds)) {
			return false;
		}
		
		final Eds _cast = (Eds) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (edsnumber == null ? _cast.edsnumber != edsnumber : !edsnumber.equals( _cast.edsnumber )) {
			return false;
		}
		
		if (edsdate == null ? _cast.edsdate != edsdate : !edsdate.equals( _cast.edsdate )) {
			return false;
		}
		
		if (cinumber == null ? _cast.cinumber != cinumber : !cinumber.equals( _cast.cinumber )) {
			return false;
		}
		
		if (buyerName == null ? _cast.buyerName != buyerName : !buyerName.equals( _cast.buyerName )) {
			return false;
		}
		
		if (brand == null ? _cast.brand != brand : !brand.equals( _cast.brand )) {
			return false;
		}
		
		if (referensi == null ? _cast.referensi != referensi : !referensi.equals( _cast.referensi )) {
			return false;
		}
		
		if (destination == null ? _cast.destination != destination : !destination.equals( _cast.destination )) {
			return false;
		}
		
		if (van == null ? _cast.van != van : !van.equals( _cast.van )) {
			return false;
		}
		
		if (sealShip == null ? _cast.sealShip != sealShip : !sealShip.equals( _cast.sealShip )) {
			return false;
		}
		
		if (vessel == null ? _cast.vessel != vessel : !vessel.equals( _cast.vessel )) {
			return false;
		}
		
		if (platNo == null ? _cast.platNo != platNo : !platNo.equals( _cast.platNo )) {
			return false;
		}
		
		if (timeIn == null ? _cast.timeIn != timeIn : !timeIn.equals( _cast.timeIn )) {
			return false;
		}
		
		if (timeOut == null ? _cast.timeOut != timeOut : !timeOut.equals( _cast.timeOut )) {
			return false;
		}
		
		if (driverName == null ? _cast.driverName != driverName : !driverName.equals( _cast.driverName )) {
			return false;
		}
		
		if (issuedBy == null ? _cast.issuedBy != issuedBy : !issuedBy.equals( _cast.issuedBy )) {
			return false;
		}
		
		if (checkedBy == null ? _cast.checkedBy != checkedBy : !checkedBy.equals( _cast.checkedBy )) {
			return false;
		}
		
		if (approvedBy == null ? _cast.approvedBy != approvedBy : !approvedBy.equals( _cast.approvedBy )) {
			return false;
		}
		
		if (remarks == null ? _cast.remarks != remarks : !remarks.equals( _cast.remarks )) {
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
		if (edsnumber != null) {
			_hashCode = 29 * _hashCode + edsnumber.hashCode();
		}
		
		if (edsdate != null) {
			_hashCode = 29 * _hashCode + edsdate.hashCode();
		}
		
		if (cinumber != null) {
			_hashCode = 29 * _hashCode + cinumber.hashCode();
		}
		
		if (buyerName != null) {
			_hashCode = 29 * _hashCode + buyerName.hashCode();
		}
		
		if (brand != null) {
			_hashCode = 29 * _hashCode + brand.hashCode();
		}
		
		if (referensi != null) {
			_hashCode = 29 * _hashCode + referensi.hashCode();
		}
		
		if (destination != null) {
			_hashCode = 29 * _hashCode + destination.hashCode();
		}
		
		if (van != null) {
			_hashCode = 29 * _hashCode + van.hashCode();
		}
		
		if (sealShip != null) {
			_hashCode = 29 * _hashCode + sealShip.hashCode();
		}
		
		if (vessel != null) {
			_hashCode = 29 * _hashCode + vessel.hashCode();
		}
		
		if (platNo != null) {
			_hashCode = 29 * _hashCode + platNo.hashCode();
		}
		
		if (timeIn != null) {
			_hashCode = 29 * _hashCode + timeIn.hashCode();
		}
		
		if (timeOut != null) {
			_hashCode = 29 * _hashCode + timeOut.hashCode();
		}
		
		if (driverName != null) {
			_hashCode = 29 * _hashCode + driverName.hashCode();
		}
		
		if (issuedBy != null) {
			_hashCode = 29 * _hashCode + issuedBy.hashCode();
		}
		
		if (checkedBy != null) {
			_hashCode = 29 * _hashCode + checkedBy.hashCode();
		}
		
		if (approvedBy != null) {
			_hashCode = 29 * _hashCode + approvedBy.hashCode();
		}
		
		if (remarks != null) {
			_hashCode = 29 * _hashCode + remarks.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return EdsPk
	 */
	public EdsPk createPk()
	{
		return new EdsPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Eds: " );
		ret.append( "id=" + id );
		ret.append( ", edsnumber=" + edsnumber );
		ret.append( ", edsdate=" + edsdate );
		ret.append( ", cinumber=" + cinumber );
		ret.append( ", buyerName=" + buyerName );
		ret.append( ", brand=" + brand );
		ret.append( ", referensi=" + referensi );
		ret.append( ", destination=" + destination );
		ret.append( ", van=" + van );
		ret.append( ", sealShip=" + sealShip );
		ret.append( ", vessel=" + vessel );
		ret.append( ", platNo=" + platNo );
		ret.append( ", timeIn=" + timeIn );
		ret.append( ", timeOut=" + timeOut );
		ret.append( ", driverName=" + driverName );
		ret.append( ", issuedBy=" + issuedBy );
		ret.append( ", checkedBy=" + checkedBy );
		ret.append( ", approvedBy=" + approvedBy );
		ret.append( ", remarks=" + remarks );
		return ret.toString();
	}

}

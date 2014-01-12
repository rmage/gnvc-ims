package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class Supplier implements Serializable
{
	/** 
	 * This attribute maps to the column id in the supplier table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column supplier_code in the supplier table.
	 */
	protected String supplierCode;

	/** 
	 * This attribute maps to the column supplier_name in the supplier table.
	 */
	protected String supplierName;

	/** 
	 * This attribute maps to the column supplier_address in the supplier table.
	 */
	protected String supplierAddress;

	/** 
	 * This attribute maps to the column telephone in the supplier table.
	 */
	protected String telephone;

	/** 
	 * This attribute maps to the column fax in the supplier table.
	 */
	protected String fax;

	/** 
	 * This attribute maps to the column email in the supplier table.
	 */
	protected String email;

	/** 
	 * This attribute maps to the column contact_person in the supplier table.
	 */
	protected String contactPerson;

	/** 
	 * This attribute maps to the column is_active in the supplier table.
	 */
	protected String isActive;

	/** 
	 * This attribute maps to the column is_delete in the supplier table.
	 */
	protected String isDelete;

	/** 
	 * This attribute maps to the column created_by in the supplier table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the supplier table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the supplier table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the supplier table.
	 */
	protected Date updatedDate;

	/**
	 * Method 'Supplier'
	 * 
	 */
	public Supplier()
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
	 * Method 'getSupplierCode'
	 * 
	 * @return String
	 */
	public String getSupplierCode()
	{
		return supplierCode;
	}

	/**
	 * Method 'setSupplierCode'
	 * 
	 * @param supplierCode
	 */
	public void setSupplierCode(String supplierCode)
	{
		this.supplierCode = supplierCode;
	}

	/**
	 * Method 'getSupplierName'
	 * 
	 * @return String
	 */
	public String getSupplierName()
	{
		return supplierName;
	}

	/**
	 * Method 'setSupplierName'
	 * 
	 * @param supplierName
	 */
	public void setSupplierName(String supplierName)
	{
		this.supplierName = supplierName;
	}

	/**
	 * Method 'getSupplierAddress'
	 * 
	 * @return String
	 */
	public String getSupplierAddress()
	{
		return supplierAddress;
	}

	/**
	 * Method 'setSupplierAddress'
	 * 
	 * @param supplierAddress
	 */
	public void setSupplierAddress(String supplierAddress)
	{
		this.supplierAddress = supplierAddress;
	}

	/**
	 * Method 'getTelephone'
	 * 
	 * @return String
	 */
	public String getTelephone()
	{
		return telephone;
	}

	/**
	 * Method 'setTelephone'
	 * 
	 * @param telephone
	 */
	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	/**
	 * Method 'getFax'
	 * 
	 * @return String
	 */
	public String getFax()
	{
		return fax;
	}

	/**
	 * Method 'setFax'
	 * 
	 * @param fax
	 */
	public void setFax(String fax)
	{
		this.fax = fax;
	}

	/**
	 * Method 'getEmail'
	 * 
	 * @return String
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Method 'setEmail'
	 * 
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * Method 'getContactPerson'
	 * 
	 * @return String
	 */
	public String getContactPerson()
	{
		return contactPerson;
	}

	/**
	 * Method 'setContactPerson'
	 * 
	 * @param contactPerson
	 */
	public void setContactPerson(String contactPerson)
	{
		this.contactPerson = contactPerson;
	}

	/**
	 * Method 'getIsActive'
	 * 
	 * @return String
	 */
	public String getIsActive()
	{
		return isActive;
	}

	/**
	 * Method 'setIsActive'
	 * 
	 * @param isActive
	 */
	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	/**
	 * Method 'getIsDelete'
	 * 
	 * @return String
	 */
	public String getIsDelete()
	{
		return isDelete;
	}

	/**
	 * Method 'setIsDelete'
	 * 
	 * @param isDelete
	 */
	public void setIsDelete(String isDelete)
	{
		this.isDelete = isDelete;
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
		
		if (!(_other instanceof Supplier)) {
			return false;
		}
		
		final Supplier _cast = (Supplier) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (supplierCode == null ? _cast.supplierCode != supplierCode : !supplierCode.equals( _cast.supplierCode )) {
			return false;
		}
		
		if (supplierName == null ? _cast.supplierName != supplierName : !supplierName.equals( _cast.supplierName )) {
			return false;
		}
		
		if (supplierAddress == null ? _cast.supplierAddress != supplierAddress : !supplierAddress.equals( _cast.supplierAddress )) {
			return false;
		}
		
		if (telephone == null ? _cast.telephone != telephone : !telephone.equals( _cast.telephone )) {
			return false;
		}
		
		if (fax == null ? _cast.fax != fax : !fax.equals( _cast.fax )) {
			return false;
		}
		
		if (email == null ? _cast.email != email : !email.equals( _cast.email )) {
			return false;
		}
		
		if (contactPerson == null ? _cast.contactPerson != contactPerson : !contactPerson.equals( _cast.contactPerson )) {
			return false;
		}
		
		if (isActive == null ? _cast.isActive != isActive : !isActive.equals( _cast.isActive )) {
			return false;
		}
		
		if (isDelete == null ? _cast.isDelete != isDelete : !isDelete.equals( _cast.isDelete )) {
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
		if (supplierCode != null) {
			_hashCode = 29 * _hashCode + supplierCode.hashCode();
		}
		
		if (supplierName != null) {
			_hashCode = 29 * _hashCode + supplierName.hashCode();
		}
		
		if (supplierAddress != null) {
			_hashCode = 29 * _hashCode + supplierAddress.hashCode();
		}
		
		if (telephone != null) {
			_hashCode = 29 * _hashCode + telephone.hashCode();
		}
		
		if (fax != null) {
			_hashCode = 29 * _hashCode + fax.hashCode();
		}
		
		if (email != null) {
			_hashCode = 29 * _hashCode + email.hashCode();
		}
		
		if (contactPerson != null) {
			_hashCode = 29 * _hashCode + contactPerson.hashCode();
		}
		
		if (isActive != null) {
			_hashCode = 29 * _hashCode + isActive.hashCode();
		}
		
		if (isDelete != null) {
			_hashCode = 29 * _hashCode + isDelete.hashCode();
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
	 * @return SupplierPk
	 */
	public SupplierPk createPk()
	{
		return new SupplierPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Supplier: " );
		ret.append( "id=" + id );
		ret.append( ", supplierCode=" + supplierCode );
		ret.append( ", supplierName=" + supplierName );
		ret.append( ", supplierAddress=" + supplierAddress );
		ret.append( ", telephone=" + telephone );
		ret.append( ", fax=" + fax );
		ret.append( ", email=" + email );
		ret.append( ", contactPerson=" + contactPerson );
		ret.append( ", isActive=" + isActive );
		ret.append( ", isDelete=" + isDelete );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}

package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Customer implements Serializable
{
	protected BigDecimal customerId;
	protected String code;
	protected String salutation;
	protected String name;
	protected String nameTax;
	protected String address;
	protected String npwp;
	protected String pkpNumber;
	protected Date pkpDate;
	protected String phone;
	protected String email;
	protected String isActive;
	protected String isDefault;
	protected BigDecimal createdBy;
	protected Date createdDate;
	protected BigDecimal updatedBy;
	protected Date updatedDate;

	/**
	 * Method 'Customer'
	 * 
	 */
	public Customer()
	{
	}

	/**
	 * Method 'getCustomerId'
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getCustomerId()
	{
		return customerId;
	}

	/**
	 * Method 'setCustomerId'
	 * 
	 * @param customerId
	 */
	public void setCustomerId(BigDecimal customerId)
	{
		this.customerId = customerId;
	}

	/**
	 * Method 'getCode'
	 * 
	 * @return String
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Method 'setCode'
	 * 
	 * @param code
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * Method 'getSalutation'
	 * 
	 * @return String
	 */
	public String getSalutation()
	{
		return salutation;
	}

	/**
	 * Method 'setSalutation'
	 * 
	 * @param salutation
	 */
	public void setSalutation(String salutation)
	{
		this.salutation = salutation;
	}

	/**
	 * Method 'getName'
	 * 
	 * @return String
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Method 'setName'
	 * 
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Method 'getNameTax'
	 *
	 * @return String
	 */
	public String getNameTax()
	{
		return nameTax;
	}

	/**
	 * Method 'setNameTax'
	 *
	 * @param nameTax
	 */
	public void setNameTax(String nameTax)
	{
		this.nameTax = nameTax;
	}
        
	/**
	 * Method 'getAddress'
	 * 
	 * @return String
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * Method 'setAddress'
	 * 
	 * @param address
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * Method 'getNpwp'
	 * 
	 * @return String
	 */
	public String getNpwp()
	{
		return npwp;
	}

	/**
	 * Method 'setNpwp'
	 * 
	 * @param npwp
	 */
	public void setNpwp(String npwp)
	{
		this.npwp = npwp;
	}

	/**
	 * Method 'getPkpNumber'
	 * 
	 * @return String
	 */
	public String getPkpNumber()
	{
		return pkpNumber;
	}

	/**
	 * Method 'setPkpNumber'
	 * 
	 * @param pkpNumber
	 */
	public void setPkpNumber(String pkpNumber)
	{
		this.pkpNumber = pkpNumber;
	}

	/**
	 * Method 'getPkpDate'
	 * 
	 * @return Date
	 */
	public Date getPkpDate()
	{
		return pkpDate;
	}

	/**
	 * Method 'setPkpDate'
	 * 
	 * @param pkpDate
	 */
	public void setPkpDate(Date pkpDate)
	{
		this.pkpDate = pkpDate;
	}

	/**
	 * Method 'getPhone'
	 * 
	 * @return String
	 */
	public String getPhone()
	{
		return phone;
	}

	/**
	 * Method 'setPhone'
	 * 
	 * @param phone
	 */
	public void setPhone(String phone)
	{
		this.phone = phone;
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
	 * Method 'getIsDefault'
	 * 
	 * @return String
	 */
	public String getIsDefault()
	{
		return isDefault;
	}

	/**
	 * Method 'setIsDefault'
	 * 
	 * @param isDefault
	 */
	public void setIsDefault(String isDefault)
	{
		this.isDefault = isDefault;
	}

	/**
	 * Method 'getCreatedBy'
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * Method 'setCreatedBy'
	 * 
	 * @param createdBy
	 */
	public void setCreatedBy(BigDecimal createdBy)
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
	 * @return BigDecimal
	 */
	public BigDecimal getUpdatedBy()
	{
		return updatedBy;
	}

	/**
	 * Method 'setUpdatedBy'
	 * 
	 * @param updatedBy
	 */
	public void setUpdatedBy(BigDecimal updatedBy)
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
		
		if (!(_other instanceof Customer)) {
			return false;
		}
		
		final Customer _cast = (Customer) _other;
		if (customerId == null ? _cast.customerId != customerId : !customerId.equals( _cast.customerId )) {
			return false;
		}
		
		if (code == null ? _cast.code != code : !code.equals( _cast.code )) {
			return false;
		}
		
		if (salutation == null ? _cast.salutation != salutation : !salutation.equals( _cast.salutation )) {
			return false;
		}
		
		if (name == null ? _cast.name != name : !name.equals( _cast.name )) {
			return false;
		}
		
		if (nameTax == null ? _cast.nameTax != nameTax : !nameTax.equals( _cast.nameTax )) {
			return false;
		}

                if (address == null ? _cast.address != address : !address.equals( _cast.address )) {
			return false;
		}
		
		if (npwp == null ? _cast.npwp != npwp : !npwp.equals( _cast.npwp )) {
			return false;
		}
		
		if (pkpNumber == null ? _cast.pkpNumber != pkpNumber : !pkpNumber.equals( _cast.pkpNumber )) {
			return false;
		}
		
		if (pkpDate == null ? _cast.pkpDate != pkpDate : !pkpDate.equals( _cast.pkpDate )) {
			return false;
		}
		
		if (phone == null ? _cast.phone != phone : !phone.equals( _cast.phone )) {
			return false;
		}
		
		if (email == null ? _cast.email != email : !email.equals( _cast.email )) {
			return false;
		}
		
		if (isActive == null ? _cast.isActive != isActive : !isActive.equals( _cast.isActive )) {
			return false;
		}
		
		if (isDefault == null ? _cast.isDefault != isDefault : !isDefault.equals( _cast.isDefault )) {
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
		if (customerId != null) {
			_hashCode = 29 * _hashCode + customerId.hashCode();
		}
		
		if (code != null) {
			_hashCode = 29 * _hashCode + code.hashCode();
		}
		
		if (salutation != null) {
			_hashCode = 29 * _hashCode + salutation.hashCode();
		}
		
		if (name != null) {
			_hashCode = 29 * _hashCode + name.hashCode();
		}

		if (nameTax != null) {
			_hashCode = 29 * _hashCode + nameTax.hashCode();
		}

		if (address != null) {
			_hashCode = 29 * _hashCode + address.hashCode();
		}
		
		if (npwp != null) {
			_hashCode = 29 * _hashCode + npwp.hashCode();
		}
		
		if (pkpNumber != null) {
			_hashCode = 29 * _hashCode + pkpNumber.hashCode();
		}
		
		if (pkpDate != null) {
			_hashCode = 29 * _hashCode + pkpDate.hashCode();
		}
		
		if (phone != null) {
			_hashCode = 29 * _hashCode + phone.hashCode();
		}
		
		if (email != null) {
			_hashCode = 29 * _hashCode + email.hashCode();
		}
		
		if (isActive != null) {
			_hashCode = 29 * _hashCode + isActive.hashCode();
		}
		
		if (isDefault != null) {
			_hashCode = 29 * _hashCode + isDefault.hashCode();
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
	 * @return CustomerPk
	 */
	public CustomerPk createPk()
	{
		return new CustomerPk(customerId);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Customer: " );
		ret.append( "customerId=" + customerId );
		ret.append( ", code=" + code );
		ret.append( ", salutation=" + salutation );
		ret.append( ", name=" + name );
		ret.append( ", nameTax=" + nameTax );
		ret.append( ", address=" + address );
		ret.append( ", npwp=" + npwp );
		ret.append( ", pkpNumber=" + pkpNumber );
		ret.append( ", pkpDate=" + pkpDate );
		ret.append( ", phone=" + phone );
		ret.append( ", email=" + email );
		ret.append( ", isActive=" + isActive );
		ret.append( ", isDefault=" + isDefault );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}

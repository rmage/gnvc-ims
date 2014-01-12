package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.exceptions.SupplierDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class SupplierDaoExample
{
	/**
	 * Method 'main'
	 * 
	 * @param arg
	 * @throws Exception
	 */
	public static void main(String[] arg) throws Exception
	{
		// Uncomment one of the lines below to test the generated code
		
		// findAll();
		// findWhereIdEquals(0);
		// findWhereSupplierCodeEquals("");
		// findWhereSupplierNameEquals("");
		// findWhereSupplierAddressEquals("");
		// findWhereTelephoneEquals("");
		// findWhereFaxEquals("");
		// findWhereEmailEquals("");
		// findWhereContactPersonEquals("");
		// findWhereIsActiveEquals("");
		// findWhereIsDeleteEquals("");
		// findWhereCreatedByEquals("");
		// findWhereCreatedDateEquals(null);
		// findWhereUpdatedByEquals("");
		// findWhereUpdatedDateEquals(null);
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		SupplierDao dao = DaoFactory.createSupplierDao();
		List<Supplier> _result = dao.findAll();
		for (Supplier dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIdEquals'
	 * 
	 * @param id
	 * @throws Exception
	 */
	public static void findWhereIdEquals(int id) throws Exception
	{
		SupplierDao dao = DaoFactory.createSupplierDao();
		List<Supplier> _result = dao.findWhereIdEquals(id);
		for (Supplier dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereSupplierCodeEquals'
	 * 
	 * @param supplierCode
	 * @throws Exception
	 */
	public static void findWhereSupplierCodeEquals(String supplierCode) throws Exception
	{
		SupplierDao dao = DaoFactory.createSupplierDao();
		List<Supplier> _result = dao.findWhereSupplierCodeEquals(supplierCode);
		for (Supplier dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereSupplierNameEquals'
	 * 
	 * @param supplierName
	 * @throws Exception
	 */
	public static void findWhereSupplierNameEquals(String supplierName) throws Exception
	{
		SupplierDao dao = DaoFactory.createSupplierDao();
		List<Supplier> _result = dao.findWhereSupplierNameEquals(supplierName);
		for (Supplier dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereSupplierAddressEquals'
	 * 
	 * @param supplierAddress
	 * @throws Exception
	 */
	public static void findWhereSupplierAddressEquals(String supplierAddress) throws Exception
	{
		SupplierDao dao = DaoFactory.createSupplierDao();
		List<Supplier> _result = dao.findWhereSupplierAddressEquals(supplierAddress);
		for (Supplier dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereTelephoneEquals'
	 * 
	 * @param telephone
	 * @throws Exception
	 */
	public static void findWhereTelephoneEquals(String telephone) throws Exception
	{
		SupplierDao dao = DaoFactory.createSupplierDao();
		List<Supplier> _result = dao.findWhereTelephoneEquals(telephone);
		for (Supplier dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereFaxEquals'
	 * 
	 * @param fax
	 * @throws Exception
	 */
	public static void findWhereFaxEquals(String fax) throws Exception
	{
		SupplierDao dao = DaoFactory.createSupplierDao();
		List<Supplier> _result = dao.findWhereFaxEquals(fax);
		for (Supplier dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereEmailEquals'
	 * 
	 * @param email
	 * @throws Exception
	 */
	public static void findWhereEmailEquals(String email) throws Exception
	{
		SupplierDao dao = DaoFactory.createSupplierDao();
		List<Supplier> _result = dao.findWhereEmailEquals(email);
		for (Supplier dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereContactPersonEquals'
	 * 
	 * @param contactPerson
	 * @throws Exception
	 */
	public static void findWhereContactPersonEquals(String contactPerson) throws Exception
	{
		SupplierDao dao = DaoFactory.createSupplierDao();
		List<Supplier> _result = dao.findWhereContactPersonEquals(contactPerson);
		for (Supplier dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIsActiveEquals'
	 * 
	 * @param isActive
	 * @throws Exception
	 */
	public static void findWhereIsActiveEquals(String isActive) throws Exception
	{
		SupplierDao dao = DaoFactory.createSupplierDao();
		List<Supplier> _result = dao.findWhereIsActiveEquals(isActive);
		for (Supplier dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIsDeleteEquals'
	 * 
	 * @param isDelete
	 * @throws Exception
	 */
	public static void findWhereIsDeleteEquals(String isDelete) throws Exception
	{
		SupplierDao dao = DaoFactory.createSupplierDao();
		List<Supplier> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (Supplier dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCreatedByEquals'
	 * 
	 * @param createdBy
	 * @throws Exception
	 */
	public static void findWhereCreatedByEquals(String createdBy) throws Exception
	{
		SupplierDao dao = DaoFactory.createSupplierDao();
		List<Supplier> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Supplier dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCreatedDateEquals'
	 * 
	 * @param createdDate
	 * @throws Exception
	 */
	public static void findWhereCreatedDateEquals(Date createdDate) throws Exception
	{
		SupplierDao dao = DaoFactory.createSupplierDao();
		List<Supplier> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Supplier dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUpdatedByEquals'
	 * 
	 * @param updatedBy
	 * @throws Exception
	 */
	public static void findWhereUpdatedByEquals(String updatedBy) throws Exception
	{
		SupplierDao dao = DaoFactory.createSupplierDao();
		List<Supplier> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Supplier dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUpdatedDateEquals'
	 * 
	 * @param updatedDate
	 * @throws Exception
	 */
	public static void findWhereUpdatedDateEquals(Date updatedDate) throws Exception
	{
		SupplierDao dao = DaoFactory.createSupplierDao();
		List<Supplier> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Supplier dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Supplier dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getSupplierCode() );
		buf.append( ", " );
		buf.append( dto.getSupplierName() );
		buf.append( ", " );
		buf.append( dto.getSupplierAddress() );
		buf.append( ", " );
		buf.append( dto.getTelephone() );
		buf.append( ", " );
		buf.append( dto.getFax() );
		buf.append( ", " );
		buf.append( dto.getEmail() );
		buf.append( ", " );
		buf.append( dto.getContactPerson() );
		buf.append( ", " );
		buf.append( dto.getIsActive() );
		buf.append( ", " );
		buf.append( dto.getIsDelete() );
		buf.append( ", " );
		buf.append( dto.getCreatedBy() );
		buf.append( ", " );
		buf.append( dto.getCreatedDate() );
		buf.append( ", " );
		buf.append( dto.getUpdatedBy() );
		buf.append( ", " );
		buf.append( dto.getUpdatedDate() );
		System.out.println( buf.toString() );
	}

}

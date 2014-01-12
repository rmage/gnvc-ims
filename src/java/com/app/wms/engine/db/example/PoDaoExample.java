package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.PoDao;
import com.app.wms.engine.db.dto.Po;
import com.app.wms.engine.db.exceptions.PoDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class PoDaoExample
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
		// findWherePonumberEquals("");
		// findWhereDeliverydateEquals(null);
		// findWherePoreferensiEquals("");
		// findWhereCreatedbyEquals("");
		// findWhereCorpidEquals("");
		// findWhereWhCodeEquals("");
		// findWhereDepartmentNameEquals("");
		// findWhereSupplierNameEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		PoDao dao = DaoFactory.createPoDao();
		List<Po> _result = dao.findAll();
		for (Po dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIdEquals'
	 * 
	 * @param id
	 * @throws Exception
	 */
	public static void findWhereIdEquals(long id) throws Exception
	{
		PoDao dao = DaoFactory.createPoDao();
		List<Po> _result = dao.findWhereIdEquals(id);
		for (Po dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePonumberEquals'
	 * 
	 * @param ponumber
	 * @throws Exception
	 */
	public static void findWherePonumberEquals(String ponumber) throws Exception
	{
		PoDao dao = DaoFactory.createPoDao();
		List<Po> _result = dao.findWherePonumberEquals(ponumber);
		for (Po dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDeliverydateEquals'
	 * 
	 * @param deliverydate
	 * @throws Exception
	 */
	public static void findWhereDeliverydateEquals(Date deliverydate) throws Exception
	{
		PoDao dao = DaoFactory.createPoDao();
		List<Po> _result = dao.findWhereDeliverydateEquals(deliverydate);
		for (Po dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePoreferensiEquals'
	 * 
	 * @param poreferensi
	 * @throws Exception
	 */
	public static void findWherePoreferensiEquals(String poreferensi) throws Exception
	{
		PoDao dao = DaoFactory.createPoDao();
		List<Po> _result = dao.findWherePoreferensiEquals(poreferensi);
		for (Po dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCreatedbyEquals'
	 * 
	 * @param createdby
	 * @throws Exception
	 */
	public static void findWhereCreatedbyEquals(String createdby) throws Exception
	{
		PoDao dao = DaoFactory.createPoDao();
		List<Po> _result = dao.findWhereCreatedbyEquals(createdby);
		for (Po dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCorpidEquals'
	 * 
	 * @param corpid
	 * @throws Exception
	 */
	public static void findWhereCorpidEquals(String corpid) throws Exception
	{
		PoDao dao = DaoFactory.createPoDao();
		List<Po> _result = dao.findWhereCorpidEquals(corpid);
		for (Po dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereWhCodeEquals'
	 * 
	 * @param whCode
	 * @throws Exception
	 */
	public static void findWhereWhCodeEquals(String whCode) throws Exception
	{
		PoDao dao = DaoFactory.createPoDao();
		List<Po> _result = dao.findWhereWhCodeEquals(whCode);
		for (Po dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDepartmentNameEquals'
	 * 
	 * @param departmentName
	 * @throws Exception
	 */
	public static void findWhereDepartmentNameEquals(String departmentName) throws Exception
	{
		PoDao dao = DaoFactory.createPoDao();
		List<Po> _result = dao.findWhereDepartmentNameEquals(departmentName);
		for (Po dto : _result) {
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
		PoDao dao = DaoFactory.createPoDao();
		List<Po> _result = dao.findWhereSupplierNameEquals(supplierName);
		for (Po dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Po dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getPonumber() );
		buf.append( ", " );
		buf.append( dto.getDeliverydate() );
		buf.append( ", " );
		buf.append( dto.getPoreferensi() );
		buf.append( ", " );
		buf.append( dto.getCreatedby() );
		buf.append( ", " );
		buf.append( dto.getCorpid() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		buf.append( ", " );
		buf.append( dto.getDepartmentName() );
		buf.append( ", " );
		buf.append( dto.getSupplierName() );
		System.out.println( buf.toString() );
	}

}

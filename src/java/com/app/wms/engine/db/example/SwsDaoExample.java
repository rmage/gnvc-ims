package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.SwsDao;
import com.app.wms.engine.db.dto.Sws;
import com.app.wms.engine.db.exceptions.SwsDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class SwsDaoExample
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
		// findWhereSwsnumberEquals("");
		// findWhereSwsdateEquals(null);
		// findWhereSwsreferensiEquals("");
		// findWhereCreatedbyEquals("");
		// findWhereWhCodeEquals("");
		// findWhereDepartmentNameEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		SwsDao dao = DaoFactory.createSwsDao();
		List<Sws> _result = dao.findAll();
		for (Sws dto : _result) {
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
		SwsDao dao = DaoFactory.createSwsDao();
		List<Sws> _result = dao.findWhereIdEquals(id);
		for (Sws dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereSwsnumberEquals'
	 * 
	 * @param swsnumber
	 * @throws Exception
	 */
	public static void findWhereSwsnumberEquals(String swsnumber) throws Exception
	{
		SwsDao dao = DaoFactory.createSwsDao();
		List<Sws> _result = dao.findWhereSwsnumberEquals(swsnumber);
		for (Sws dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereSwsdateEquals'
	 * 
	 * @param swsdate
	 * @throws Exception
	 */
	public static void findWhereSwsdateEquals(Date swsdate) throws Exception
	{
		SwsDao dao = DaoFactory.createSwsDao();
		List<Sws> _result = dao.findWhereSwsdateEquals(swsdate);
		for (Sws dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereSwsreferensiEquals'
	 * 
	 * @param swsreferensi
	 * @throws Exception
	 */
	public static void findWhereSwsreferensiEquals(String swsreferensi) throws Exception
	{
		SwsDao dao = DaoFactory.createSwsDao();
		List<Sws> _result = dao.findWhereSwsreferensiEquals(swsreferensi);
		for (Sws dto : _result) {
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
		SwsDao dao = DaoFactory.createSwsDao();
		List<Sws> _result = dao.findWhereCreatedbyEquals(createdby);
		for (Sws dto : _result) {
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
		SwsDao dao = DaoFactory.createSwsDao();
		List<Sws> _result = dao.findWhereWhCodeEquals(whCode);
		for (Sws dto : _result) {
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
		SwsDao dao = DaoFactory.createSwsDao();
		List<Sws> _result = dao.findWhereDepartmentNameEquals(departmentName);
		for (Sws dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Sws dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getSwsnumber() );
		buf.append( ", " );
		buf.append( dto.getSwsdate() );
		buf.append( ", " );
		buf.append( dto.getSwsreferensi() );
		buf.append( ", " );
		buf.append( dto.getCreatedby() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		buf.append( ", " );
		buf.append( dto.getDepartmentName() );
		System.out.println( buf.toString() );
	}

}

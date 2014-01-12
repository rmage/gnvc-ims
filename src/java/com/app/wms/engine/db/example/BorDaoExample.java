package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.BorDao;
import com.app.wms.engine.db.dto.Bor;
import com.app.wms.engine.db.exceptions.BorDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class BorDaoExample
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
		// findWhereBornumberEquals("");
		// findWhereBordateEquals(null);
		// findWhereBorreferensiEquals("");
		// findWhereCreatedbyEquals("");
		// findWhereBuyerNameEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		BorDao dao = DaoFactory.createBorDao();
		List<Bor> _result = dao.findAll();
		for (Bor dto : _result) {
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
		BorDao dao = DaoFactory.createBorDao();
		List<Bor> _result = dao.findWhereIdEquals(id);
		for (Bor dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBornumberEquals'
	 * 
	 * @param bornumber
	 * @throws Exception
	 */
	public static void findWhereBornumberEquals(String bornumber) throws Exception
	{
		BorDao dao = DaoFactory.createBorDao();
		List<Bor> _result = dao.findWhereBornumberEquals(bornumber);
		for (Bor dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBordateEquals'
	 * 
	 * @param bordate
	 * @throws Exception
	 */
	public static void findWhereBordateEquals(Date bordate) throws Exception
	{
		BorDao dao = DaoFactory.createBorDao();
		List<Bor> _result = dao.findWhereBordateEquals(bordate);
		for (Bor dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBorreferensiEquals'
	 * 
	 * @param borreferensi
	 * @throws Exception
	 */
	public static void findWhereBorreferensiEquals(String borreferensi) throws Exception
	{
		BorDao dao = DaoFactory.createBorDao();
		List<Bor> _result = dao.findWhereBorreferensiEquals(borreferensi);
		for (Bor dto : _result) {
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
		BorDao dao = DaoFactory.createBorDao();
		List<Bor> _result = dao.findWhereCreatedbyEquals(createdby);
		for (Bor dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBuyerNameEquals'
	 * 
	 * @param buyerName
	 * @throws Exception
	 */
	public static void findWhereBuyerNameEquals(String buyerName) throws Exception
	{
		BorDao dao = DaoFactory.createBorDao();
		List<Bor> _result = dao.findWhereBuyerNameEquals(buyerName);
		for (Bor dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Bor dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getBornumber() );
		buf.append( ", " );
		buf.append( dto.getBordate() );
		buf.append( ", " );
		buf.append( dto.getBorreferensi() );
		buf.append( ", " );
		buf.append( dto.getCreatedby() );
		buf.append( ", " );
		buf.append( dto.getBuyerName() );
		System.out.println( buf.toString() );
	}

}

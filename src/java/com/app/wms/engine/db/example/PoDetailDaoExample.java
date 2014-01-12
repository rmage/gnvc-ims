package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.PoDetailDao;
import com.app.wms.engine.db.dto.PoDetail;
import com.app.wms.engine.db.exceptions.PoDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class PoDetailDaoExample
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
		// findWhereProductcodeEquals("");
		// findWhereQtyEquals(0);
		// findWhereProducttypeEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		PoDetailDao dao = DaoFactory.createPoDetailDao();
		List<PoDetail> _result = dao.findAll();
		for (PoDetail dto : _result) {
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
		PoDetailDao dao = DaoFactory.createPoDetailDao();
		List<PoDetail> _result = dao.findWhereIdEquals(id);
		for (PoDetail dto : _result) {
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
		PoDetailDao dao = DaoFactory.createPoDetailDao();
		List<PoDetail> _result = dao.findWherePonumberEquals(ponumber);
		for (PoDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductcodeEquals'
	 * 
	 * @param productcode
	 * @throws Exception
	 */
	public static void findWhereProductcodeEquals(String productcode) throws Exception
	{
		PoDetailDao dao = DaoFactory.createPoDetailDao();
		List<PoDetail> _result = dao.findWhereProductcodeEquals(productcode);
		for (PoDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQtyEquals'
	 * 
	 * @param qty
	 * @throws Exception
	 */
	public static void findWhereQtyEquals(int qty) throws Exception
	{
		PoDetailDao dao = DaoFactory.createPoDetailDao();
		List<PoDetail> _result = dao.findWhereQtyEquals(qty);
		for (PoDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProducttypeEquals'
	 * 
	 * @param producttype
	 * @throws Exception
	 */
	public static void findWhereProducttypeEquals(String producttype) throws Exception
	{
		PoDetailDao dao = DaoFactory.createPoDetailDao();
		List<PoDetail> _result = dao.findWhereProducttypeEquals(producttype);
		for (PoDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(PoDetail dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getPonumber() );
		buf.append( ", " );
		buf.append( dto.getProductcode() );
		buf.append( ", " );
		buf.append( dto.getQty() );
		buf.append( ", " );
		buf.append( dto.getProducttype() );
		System.out.println( buf.toString() );
	}

}

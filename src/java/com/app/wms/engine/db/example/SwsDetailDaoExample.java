package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.SwsDetailDao;
import com.app.wms.engine.db.dto.SwsDetail;
import com.app.wms.engine.db.exceptions.SwsDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class SwsDetailDaoExample
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
		SwsDetailDao dao = DaoFactory.createSwsDetailDao();
		List<SwsDetail> _result = dao.findAll();
		for (SwsDetail dto : _result) {
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
		SwsDetailDao dao = DaoFactory.createSwsDetailDao();
		List<SwsDetail> _result = dao.findWhereIdEquals(id);
		for (SwsDetail dto : _result) {
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
		SwsDetailDao dao = DaoFactory.createSwsDetailDao();
		List<SwsDetail> _result = dao.findWhereSwsnumberEquals(swsnumber);
		for (SwsDetail dto : _result) {
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
		SwsDetailDao dao = DaoFactory.createSwsDetailDao();
		List<SwsDetail> _result = dao.findWhereProductcodeEquals(productcode);
		for (SwsDetail dto : _result) {
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
		SwsDetailDao dao = DaoFactory.createSwsDetailDao();
		List<SwsDetail> _result = dao.findWhereQtyEquals(qty);
		for (SwsDetail dto : _result) {
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
		SwsDetailDao dao = DaoFactory.createSwsDetailDao();
		List<SwsDetail> _result = dao.findWhereProducttypeEquals(producttype);
		for (SwsDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(SwsDetail dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getSwsnumber() );
		buf.append( ", " );
		buf.append( dto.getProductcode() );
		buf.append( ", " );
		buf.append( dto.getQty() );
		buf.append( ", " );
		buf.append( dto.getProducttype() );
		System.out.println( buf.toString() );
	}

}

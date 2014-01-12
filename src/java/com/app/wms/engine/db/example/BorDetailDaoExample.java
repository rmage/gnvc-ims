package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.BorDetailDao;
import com.app.wms.engine.db.dto.BorDetail;
import com.app.wms.engine.db.exceptions.BorDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class BorDetailDaoExample
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
		BorDetailDao dao = DaoFactory.createBorDetailDao();
		List<BorDetail> _result = dao.findAll();
		for (BorDetail dto : _result) {
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
		BorDetailDao dao = DaoFactory.createBorDetailDao();
		List<BorDetail> _result = dao.findWhereIdEquals(id);
		for (BorDetail dto : _result) {
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
		BorDetailDao dao = DaoFactory.createBorDetailDao();
		List<BorDetail> _result = dao.findWhereBornumberEquals(bornumber);
		for (BorDetail dto : _result) {
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
		BorDetailDao dao = DaoFactory.createBorDetailDao();
		List<BorDetail> _result = dao.findWhereProductcodeEquals(productcode);
		for (BorDetail dto : _result) {
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
		BorDetailDao dao = DaoFactory.createBorDetailDao();
		List<BorDetail> _result = dao.findWhereQtyEquals(qty);
		for (BorDetail dto : _result) {
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
		BorDetailDao dao = DaoFactory.createBorDetailDao();
		List<BorDetail> _result = dao.findWhereProducttypeEquals(producttype);
		for (BorDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(BorDetail dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getBornumber() );
		buf.append( ", " );
		buf.append( dto.getProductcode() );
		buf.append( ", " );
		buf.append( dto.getQty() );
		buf.append( ", " );
		buf.append( dto.getProducttype() );
		System.out.println( buf.toString() );
	}

}

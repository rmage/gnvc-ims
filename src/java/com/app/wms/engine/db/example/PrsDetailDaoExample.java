package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.PrsDetailDao;
import com.app.wms.engine.db.dto.PrsDetail;
import com.app.wms.engine.db.exceptions.PrsDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class PrsDetailDaoExample
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
		// findWherePrsnumberEquals("");
		// findWhereProductcodeEquals("");
		// findWhereProductnameEquals("");
		// findWhereQtyEquals(0);
		// findWhereUomNameEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		PrsDetailDao dao = DaoFactory.createPrsDetailDao();
		List<PrsDetail> _result = dao.findAll();
		for (PrsDetail dto : _result) {
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
		PrsDetailDao dao = DaoFactory.createPrsDetailDao();
		List<PrsDetail> _result = dao.findWhereIdEquals(id);
		for (PrsDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePrsnumberEquals'
	 * 
	 * @param prsnumber
	 * @throws Exception
	 */
	public static void findWherePrsnumberEquals(String prsnumber) throws Exception
	{
		PrsDetailDao dao = DaoFactory.createPrsDetailDao();
		List<PrsDetail> _result = dao.findWherePrsnumberEquals(prsnumber);
		for (PrsDetail dto : _result) {
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
		PrsDetailDao dao = DaoFactory.createPrsDetailDao();
		List<PrsDetail> _result = dao.findWhereProductcodeEquals(productcode);
		for (PrsDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductnameEquals'
	 * 
	 * @param productname
	 * @throws Exception
	 */
	public static void findWhereProductnameEquals(String productname) throws Exception
	{
		PrsDetailDao dao = DaoFactory.createPrsDetailDao();
		List<PrsDetail> _result = dao.findWhereProductnameEquals(productname);
		for (PrsDetail dto : _result) {
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
		PrsDetailDao dao = DaoFactory.createPrsDetailDao();
		List<PrsDetail> _result = dao.findWhereQtyEquals(qty);
		for (PrsDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUomNameEquals'
	 * 
	 * @param uomName
	 * @throws Exception
	 */
	public static void findWhereUomNameEquals(String uomName) throws Exception
	{
		PrsDetailDao dao = DaoFactory.createPrsDetailDao();
		List<PrsDetail> _result = dao.findWhereUomNameEquals(uomName);
		for (PrsDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(PrsDetail dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getPrsnumber() );
		buf.append( ", " );
		buf.append( dto.getProductcode() );
		buf.append( ", " );
		buf.append( dto.getProductname() );
		buf.append( ", " );
		buf.append( dto.getQty() );
		buf.append( ", " );
		buf.append( dto.getUomName() );
		System.out.println( buf.toString() );
	}

}

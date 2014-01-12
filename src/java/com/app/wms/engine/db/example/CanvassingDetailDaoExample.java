package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.CanvassingDetailDao;
import com.app.wms.engine.db.dto.CanvassingDetail;
import com.app.wms.engine.db.exceptions.CanvassingDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class CanvassingDetailDaoExample
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
		// findWherePrsnumberEquals("");
		// findWhereProductcodeEquals("");
		// findWhereProductnameEquals("");
		// findWherePriceunitEquals(0);
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		CanvassingDetailDao dao = DaoFactory.createCanvassingDetailDao();
		List<CanvassingDetail> _result = dao.findAll();
		for (CanvassingDetail dto : _result) {
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
		CanvassingDetailDao dao = DaoFactory.createCanvassingDetailDao();
		List<CanvassingDetail> _result = dao.findWhereIdEquals(id);
		for (CanvassingDetail dto : _result) {
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
		CanvassingDetailDao dao = DaoFactory.createCanvassingDetailDao();
		List<CanvassingDetail> _result = dao.findWhereSupplierCodeEquals(supplierCode);
		for (CanvassingDetail dto : _result) {
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
		CanvassingDetailDao dao = DaoFactory.createCanvassingDetailDao();
		List<CanvassingDetail> _result = dao.findWherePrsnumberEquals(prsnumber);
		for (CanvassingDetail dto : _result) {
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
		CanvassingDetailDao dao = DaoFactory.createCanvassingDetailDao();
		List<CanvassingDetail> _result = dao.findWhereProductcodeEquals(productcode);
		for (CanvassingDetail dto : _result) {
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
		CanvassingDetailDao dao = DaoFactory.createCanvassingDetailDao();
		List<CanvassingDetail> _result = dao.findWhereProductnameEquals(productname);
		for (CanvassingDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePriceunitEquals'
	 * 
	 * @param priceunit
	 * @throws Exception
	 */
	public static void findWherePriceunitEquals(float priceunit) throws Exception
	{
		CanvassingDetailDao dao = DaoFactory.createCanvassingDetailDao();
		List<CanvassingDetail> _result = dao.findWherePriceunitEquals(priceunit);
		for (CanvassingDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(CanvassingDetail dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getSupplierCode() );
		buf.append( ", " );
		buf.append( dto.getPrsnumber() );
		buf.append( ", " );
		buf.append( dto.getProductcode() );
		buf.append( ", " );
		buf.append( dto.getProductname() );
		buf.append( ", " );
		buf.append( dto.getPriceunit() );
		System.out.println( buf.toString() );
	}

}

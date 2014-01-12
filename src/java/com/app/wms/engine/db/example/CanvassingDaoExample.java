package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.CanvassingDao;
import com.app.wms.engine.db.dto.Canvassing;
import com.app.wms.engine.db.exceptions.CanvassingDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class CanvassingDaoExample
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
		// findWhereCanvassernameEquals("");
		// findWhereCanvassingdateEquals(null);
		// findWhereRemarksEquals("");
		// findWhereCreatedbyEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		CanvassingDao dao = DaoFactory.createCanvassingDao();
		List<Canvassing> _result = dao.findAll();
		for (Canvassing dto : _result) {
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
		CanvassingDao dao = DaoFactory.createCanvassingDao();
		List<Canvassing> _result = dao.findWhereIdEquals(id);
		for (Canvassing dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereSupplierCodeEquals'
	 * 
	 * @param supplierCode
	 * @throws Exception
	 */
//	public static void findWhereSupplierCodeEquals(String supplierCode) throws Exception
//	{
//		CanvassingDao dao = DaoFactory.createCanvassingDao();
//		List<Canvassing> _result = dao.findWhereSupplierCodeEquals(supplierCode);
//		for (Canvassing dto : _result) {
//			display(dto);
//		}
//		
//	}

	/**
	 * Method 'findWherePrsnumberEquals'
	 * 
	 * @param prsnumber
	 * @throws Exception
	 */
	public static void findWherePrsnumberEquals(String prsnumber) throws Exception
	{
		CanvassingDao dao = DaoFactory.createCanvassingDao();
		List<Canvassing> _result = dao.findWherePrsnumberEquals(prsnumber);
		for (Canvassing dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCanvassernameEquals'
	 * 
	 * @param canvassername
	 * @throws Exception
	 */
	public static void findWhereCanvassernameEquals(String canvassername) throws Exception
	{
		CanvassingDao dao = DaoFactory.createCanvassingDao();
		List<Canvassing> _result = dao.findWhereCanvassernameEquals(canvassername);
		for (Canvassing dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCanvassingdateEquals'
	 * 
	 * @param canvassingdate
	 * @throws Exception
	 */
	public static void findWhereCanvassingdateEquals(Date canvassingdate) throws Exception
	{
		CanvassingDao dao = DaoFactory.createCanvassingDao();
		List<Canvassing> _result = dao.findWhereCanvassingdateEquals(canvassingdate);
		for (Canvassing dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereRemarksEquals'
	 * 
	 * @param remarks
	 * @throws Exception
	 */
	public static void findWhereRemarksEquals(String remarks) throws Exception
	{
		CanvassingDao dao = DaoFactory.createCanvassingDao();
		List<Canvassing> _result = dao.findWhereRemarksEquals(remarks);
		for (Canvassing dto : _result) {
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
		CanvassingDao dao = DaoFactory.createCanvassingDao();
		List<Canvassing> _result = dao.findWhereCreatedbyEquals(createdby);
		for (Canvassing dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Canvassing dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
//		buf.append( dto.getSupplierCode() );
//		buf.append( ", " );
		buf.append( dto.getPrsnumber() );
		buf.append( ", " );
		buf.append( dto.getCanvassername() );
		buf.append( ", " );
		buf.append( dto.getCanvassingdate() );
		buf.append( ", " );
		buf.append( dto.getRemarks() );
		buf.append( ", " );
		buf.append( dto.getCreatedby() );
		System.out.println( buf.toString() );
	}

}

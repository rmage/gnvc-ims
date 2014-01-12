package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.EdsDetailDao;
import com.app.wms.engine.db.dto.EdsDetail;
import com.app.wms.engine.db.exceptions.EdsDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class EdsDetailDaoExample
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
		// findWhereEdsnumberEquals("");
		// findWherePackStyleEquals("");
		// findWhereCanSizeEquals("");
		// findWhereProductcodeEquals("");
		// findWhereQtyCasesEquals(0);
		// findWhereRemarksEquals("");
		// findWhereUnitPriceEquals(0);
		// findWhereAmountEquals(0);
		// findWhereProducttypeEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		EdsDetailDao dao = DaoFactory.createEdsDetailDao();
		List<EdsDetail> _result = dao.findAll();
		for (EdsDetail dto : _result) {
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
		EdsDetailDao dao = DaoFactory.createEdsDetailDao();
		List<EdsDetail> _result = dao.findWhereIdEquals(id);
		for (EdsDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereEdsnumberEquals'
	 * 
	 * @param edsnumber
	 * @throws Exception
	 */
	public static void findWhereEdsnumberEquals(String edsnumber) throws Exception
	{
		EdsDetailDao dao = DaoFactory.createEdsDetailDao();
		List<EdsDetail> _result = dao.findWhereEdsnumberEquals(edsnumber);
		for (EdsDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePackStyleEquals'
	 * 
	 * @param packStyle
	 * @throws Exception
	 */
	public static void findWherePackStyleEquals(String packStyle) throws Exception
	{
		EdsDetailDao dao = DaoFactory.createEdsDetailDao();
		List<EdsDetail> _result = dao.findWherePackStyleEquals(packStyle);
		for (EdsDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCanSizeEquals'
	 * 
	 * @param canSize
	 * @throws Exception
	 */
	public static void findWhereCanSizeEquals(String canSize) throws Exception
	{
		EdsDetailDao dao = DaoFactory.createEdsDetailDao();
		List<EdsDetail> _result = dao.findWhereCanSizeEquals(canSize);
		for (EdsDetail dto : _result) {
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
		EdsDetailDao dao = DaoFactory.createEdsDetailDao();
		List<EdsDetail> _result = dao.findWhereProductcodeEquals(productcode);
		for (EdsDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQtyCasesEquals'
	 * 
	 * @param qtyCases
	 * @throws Exception
	 */
	public static void findWhereQtyCasesEquals(int qtyCases) throws Exception
	{
		EdsDetailDao dao = DaoFactory.createEdsDetailDao();
		List<EdsDetail> _result = dao.findWhereQtyCasesEquals(qtyCases);
		for (EdsDetail dto : _result) {
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
		EdsDetailDao dao = DaoFactory.createEdsDetailDao();
		List<EdsDetail> _result = dao.findWhereRemarksEquals(remarks);
		for (EdsDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUnitPriceEquals'
	 * 
	 * @param unitPrice
	 * @throws Exception
	 */
	public static void findWhereUnitPriceEquals(int unitPrice) throws Exception
	{
		EdsDetailDao dao = DaoFactory.createEdsDetailDao();
		List<EdsDetail> _result = dao.findWhereUnitPriceEquals(unitPrice);
		for (EdsDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereAmountEquals'
	 * 
	 * @param amount
	 * @throws Exception
	 */
	public static void findWhereAmountEquals(int amount) throws Exception
	{
		EdsDetailDao dao = DaoFactory.createEdsDetailDao();
		List<EdsDetail> _result = dao.findWhereAmountEquals(amount);
		for (EdsDetail dto : _result) {
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
		EdsDetailDao dao = DaoFactory.createEdsDetailDao();
		List<EdsDetail> _result = dao.findWhereProducttypeEquals(producttype);
		for (EdsDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(EdsDetail dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getEdsnumber() );
		buf.append( ", " );
		buf.append( dto.getPackStyle() );
		buf.append( ", " );
		buf.append( dto.getCanSize() );
		buf.append( ", " );
		buf.append( dto.getProductcode() );
		buf.append( ", " );
		buf.append( dto.getQtyCases() );
		buf.append( ", " );
		buf.append( dto.getRemarks() );
		buf.append( ", " );
		buf.append( dto.getUnitPrice() );
		buf.append( ", " );
		buf.append( dto.getAmount() );
		buf.append( ", " );
		buf.append( dto.getProducttype() );
		System.out.println( buf.toString() );
	}

}

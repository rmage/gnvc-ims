package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.DrDetailDao;
import com.app.wms.engine.db.dto.DrDetail;
import com.app.wms.engine.db.exceptions.DrDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class DrDetailDaoExample
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
		// findWhereDrnumberEquals("");
		// findWhereProductcodeEquals("");
		// findWhereQtyrealEquals(0);
		// findWhereStatusEquals("");
		// findWhereExpirydateEquals(null);
		// findWhereRemarksEquals("");
		// findWhereQtygoodEquals(0);
		// findWhereQtydmgEquals(0);
		// findWhereProducttypeEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		DrDetailDao dao = DaoFactory.createDrDetailDao();
		List<DrDetail> _result = dao.findAll();
		for (DrDetail dto : _result) {
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
		DrDetailDao dao = DaoFactory.createDrDetailDao();
		List<DrDetail> _result = dao.findWhereIdEquals(id);
		for (DrDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDrnumberEquals'
	 * 
	 * @param drnumber
	 * @throws Exception
	 */
	public static void findWhereDrnumberEquals(String drnumber) throws Exception
	{
		DrDetailDao dao = DaoFactory.createDrDetailDao();
		List<DrDetail> _result = dao.findWhereDrnumberEquals(drnumber);
		for (DrDetail dto : _result) {
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
		DrDetailDao dao = DaoFactory.createDrDetailDao();
		List<DrDetail> _result = dao.findWhereProductcodeEquals(productcode);
		for (DrDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQtyrealEquals'
	 * 
	 * @param qtyreal
	 * @throws Exception
	 */
	public static void findWhereQtyrealEquals(int qtyreal) throws Exception
	{
		DrDetailDao dao = DaoFactory.createDrDetailDao();
		List<DrDetail> _result = dao.findWhereQtyrealEquals(qtyreal);
		for (DrDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereStatusEquals'
	 * 
	 * @param status
	 * @throws Exception
	 */
	public static void findWhereStatusEquals(String status) throws Exception
	{
		DrDetailDao dao = DaoFactory.createDrDetailDao();
		List<DrDetail> _result = dao.findWhereStatusEquals(status);
		for (DrDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereExpirydateEquals'
	 * 
	 * @param expirydate
	 * @throws Exception
	 */
	public static void findWhereExpirydateEquals(Date expirydate) throws Exception
	{
		DrDetailDao dao = DaoFactory.createDrDetailDao();
		List<DrDetail> _result = dao.findWhereExpirydateEquals(expirydate);
		for (DrDetail dto : _result) {
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
		DrDetailDao dao = DaoFactory.createDrDetailDao();
		List<DrDetail> _result = dao.findWhereRemarksEquals(remarks);
		for (DrDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQtygoodEquals'
	 * 
	 * @param qtygood
	 * @throws Exception
	 */
	public static void findWhereQtygoodEquals(int qtygood) throws Exception
	{
		DrDetailDao dao = DaoFactory.createDrDetailDao();
		List<DrDetail> _result = dao.findWhereQtygoodEquals(qtygood);
		for (DrDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQtydmgEquals'
	 * 
	 * @param qtydmg
	 * @throws Exception
	 */
	public static void findWhereQtydmgEquals(int qtydmg) throws Exception
	{
		DrDetailDao dao = DaoFactory.createDrDetailDao();
		List<DrDetail> _result = dao.findWhereQtydmgEquals(qtydmg);
		for (DrDetail dto : _result) {
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
		DrDetailDao dao = DaoFactory.createDrDetailDao();
		List<DrDetail> _result = dao.findWhereProducttypeEquals(producttype);
		for (DrDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(DrDetail dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getDrnumber() );
		buf.append( ", " );
		buf.append( dto.getProductcode() );
		buf.append( ", " );
		buf.append( dto.getQtyreal() );
		buf.append( ", " );
		buf.append( dto.getStatus() );
		buf.append( ", " );
		buf.append( dto.getExpirydate() );
		buf.append( ", " );
		buf.append( dto.getRemarks() );
		buf.append( ", " );
		buf.append( dto.getQtygood() );
		buf.append( ", " );
		buf.append( dto.getQtydmg() );
		buf.append( ", " );
		buf.append( dto.getProducttype() );
		System.out.println( buf.toString() );
	}

}

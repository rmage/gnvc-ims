package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.GoodreceiveDetailDao;
import com.app.wms.engine.db.dto.GoodreceiveDetail;
import com.app.wms.engine.db.exceptions.GoodreceiveDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class GoodreceiveDetailDaoExample
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
		// findWhereGrnumberEquals("");
		// findWhereProductcodeEquals("");
		// findWhereQtyrealEquals(0);
		// findWhereStatusEquals("");
		// findWhereExpirydateEquals(null);
		// findWhereRemarkEquals("");
		// findWhereLotidEquals(0);
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
		GoodreceiveDetailDao dao = DaoFactory.createGoodreceiveDetailDao();
		List<GoodreceiveDetail> _result = dao.findAll();
		for (GoodreceiveDetail dto : _result) {
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
		GoodreceiveDetailDao dao = DaoFactory.createGoodreceiveDetailDao();
		List<GoodreceiveDetail> _result = dao.findWhereIdEquals(id);
		for (GoodreceiveDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereGrnumberEquals'
	 * 
	 * @param grnumber
	 * @throws Exception
	 */
	public static void findWhereGrnumberEquals(String grnumber) throws Exception
	{
		GoodreceiveDetailDao dao = DaoFactory.createGoodreceiveDetailDao();
		List<GoodreceiveDetail> _result = dao.findWhereGrnumberEquals(grnumber);
		for (GoodreceiveDetail dto : _result) {
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
		GoodreceiveDetailDao dao = DaoFactory.createGoodreceiveDetailDao();
		List<GoodreceiveDetail> _result = dao.findWhereProductcodeEquals(productcode);
		for (GoodreceiveDetail dto : _result) {
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
		GoodreceiveDetailDao dao = DaoFactory.createGoodreceiveDetailDao();
		List<GoodreceiveDetail> _result = dao.findWhereQtyrealEquals(qtyreal);
		for (GoodreceiveDetail dto : _result) {
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
		GoodreceiveDetailDao dao = DaoFactory.createGoodreceiveDetailDao();
		List<GoodreceiveDetail> _result = dao.findWhereStatusEquals(status);
		for (GoodreceiveDetail dto : _result) {
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
		GoodreceiveDetailDao dao = DaoFactory.createGoodreceiveDetailDao();
		List<GoodreceiveDetail> _result = dao.findWhereExpirydateEquals(expirydate);
		for (GoodreceiveDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereRemarkEquals'
	 * 
	 * @param remark
	 * @throws Exception
	 */
	public static void findWhereRemarkEquals(String remark) throws Exception
	{
		GoodreceiveDetailDao dao = DaoFactory.createGoodreceiveDetailDao();
		List<GoodreceiveDetail> _result = dao.findWhereRemarkEquals(remark);
		for (GoodreceiveDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereLotidEquals'
	 * 
	 * @param lotid
	 * @throws Exception
	 */
	public static void findWhereLotidEquals(int lotid) throws Exception
	{
		GoodreceiveDetailDao dao = DaoFactory.createGoodreceiveDetailDao();
		List<GoodreceiveDetail> _result = dao.findWhereLotidEquals(lotid);
		for (GoodreceiveDetail dto : _result) {
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
		GoodreceiveDetailDao dao = DaoFactory.createGoodreceiveDetailDao();
		List<GoodreceiveDetail> _result = dao.findWhereQtygoodEquals(qtygood);
		for (GoodreceiveDetail dto : _result) {
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
		GoodreceiveDetailDao dao = DaoFactory.createGoodreceiveDetailDao();
		List<GoodreceiveDetail> _result = dao.findWhereQtydmgEquals(qtydmg);
		for (GoodreceiveDetail dto : _result) {
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
		GoodreceiveDetailDao dao = DaoFactory.createGoodreceiveDetailDao();
		List<GoodreceiveDetail> _result = dao.findWhereProducttypeEquals(producttype);
		for (GoodreceiveDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(GoodreceiveDetail dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getGrnumber() );
		buf.append( ", " );
		buf.append( dto.getProductcode() );
		buf.append( ", " );
		buf.append( dto.getQtyreal() );
		buf.append( ", " );
		buf.append( dto.getStatus() );
		buf.append( ", " );
		buf.append( dto.getExpirydate() );
		buf.append( ", " );
		buf.append( dto.getRemark() );
		buf.append( ", " );
		buf.append( dto.getLotid() );
		buf.append( ", " );
		buf.append( dto.getQtygood() );
		buf.append( ", " );
		buf.append( dto.getQtydmg() );
		buf.append( ", " );
		buf.append( dto.getProducttype() );
		System.out.println( buf.toString() );
	}

}

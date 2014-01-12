package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.ConsolidateDetailDao;
import com.app.wms.engine.db.dto.ConsolidateDetail;
import com.app.wms.engine.db.exceptions.ConsolidateDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class ConsolidateDetailDaoExample
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
		// findWhereConsolidateNoEquals("");
		// findWherePackingNoEquals("");
		// findWhereProductIdEquals("");
		// findWhereProductCodeEquals("");
		// findWhereProductNameEquals("");
		// findWhereUnitCodeEquals("");
		// findWhereQtyConsolidateEquals(0);
		// findWhereUserIdEquals("");
		// findWhereCorpIdEquals("");
		// findWhereWhCodeEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		ConsolidateDetailDao dao = DaoFactory.createConsolidateDetailDao();
		List<ConsolidateDetail> _result = dao.findAll();
		for (ConsolidateDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereConsolidateNoEquals'
	 * 
	 * @param consolidateNo
	 * @throws Exception
	 */
	public static void findWhereConsolidateNoEquals(String consolidateNo) throws Exception
	{
		ConsolidateDetailDao dao = DaoFactory.createConsolidateDetailDao();
		List<ConsolidateDetail> _result = dao.findWhereConsolidateNoEquals(consolidateNo);
		for (ConsolidateDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePackingNoEquals'
	 * 
	 * @param packingNo
	 * @throws Exception
	 */
	public static void findWherePackingNoEquals(String packingNo) throws Exception
	{
		ConsolidateDetailDao dao = DaoFactory.createConsolidateDetailDao();
		List<ConsolidateDetail> _result = dao.findWherePackingNoEquals(packingNo);
		for (ConsolidateDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductIdEquals'
	 * 
	 * @param productId
	 * @throws Exception
	 */
	public static void findWhereProductIdEquals(String productId) throws Exception
	{
		ConsolidateDetailDao dao = DaoFactory.createConsolidateDetailDao();
		List<ConsolidateDetail> _result = dao.findWhereProductIdEquals(productId);
		for (ConsolidateDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductCodeEquals'
	 * 
	 * @param productCode
	 * @throws Exception
	 */
	public static void findWhereProductCodeEquals(String productCode) throws Exception
	{
		ConsolidateDetailDao dao = DaoFactory.createConsolidateDetailDao();
		List<ConsolidateDetail> _result = dao.findWhereProductCodeEquals(productCode);
		for (ConsolidateDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductNameEquals'
	 * 
	 * @param productName
	 * @throws Exception
	 */
	public static void findWhereProductNameEquals(String productName) throws Exception
	{
		ConsolidateDetailDao dao = DaoFactory.createConsolidateDetailDao();
		List<ConsolidateDetail> _result = dao.findWhereProductNameEquals(productName);
		for (ConsolidateDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUnitCodeEquals'
	 * 
	 * @param unitCode
	 * @throws Exception
	 */
	public static void findWhereUnitCodeEquals(String unitCode) throws Exception
	{
		ConsolidateDetailDao dao = DaoFactory.createConsolidateDetailDao();
		List<ConsolidateDetail> _result = dao.findWhereUnitCodeEquals(unitCode);
		for (ConsolidateDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQtyConsolidateEquals'
	 * 
	 * @param qtyConsolidate
	 * @throws Exception
	 */
	public static void findWhereQtyConsolidateEquals(int qtyConsolidate) throws Exception
	{
		ConsolidateDetailDao dao = DaoFactory.createConsolidateDetailDao();
		List<ConsolidateDetail> _result = dao.findWhereQtyConsolidateEquals(qtyConsolidate);
		for (ConsolidateDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUserIdEquals'
	 * 
	 * @param userId
	 * @throws Exception
	 */
	public static void findWhereUserIdEquals(String userId) throws Exception
	{
		ConsolidateDetailDao dao = DaoFactory.createConsolidateDetailDao();
		List<ConsolidateDetail> _result = dao.findWhereUserIdEquals(userId);
		for (ConsolidateDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCorpIdEquals'
	 * 
	 * @param corpId
	 * @throws Exception
	 */
	public static void findWhereCorpIdEquals(String corpId) throws Exception
	{
		ConsolidateDetailDao dao = DaoFactory.createConsolidateDetailDao();
		List<ConsolidateDetail> _result = dao.findWhereCorpIdEquals(corpId);
		for (ConsolidateDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereWhCodeEquals'
	 * 
	 * @param whCode
	 * @throws Exception
	 */
	public static void findWhereWhCodeEquals(String whCode) throws Exception
	{
		ConsolidateDetailDao dao = DaoFactory.createConsolidateDetailDao();
		List<ConsolidateDetail> _result = dao.findWhereWhCodeEquals(whCode);
		for (ConsolidateDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(ConsolidateDetail dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getConsolidateNo() );
		buf.append( ", " );
		buf.append( dto.getPackingNo() );
		buf.append( ", " );
		buf.append( dto.getProductId() );
		buf.append( ", " );
		buf.append( dto.getProductCode() );
		buf.append( ", " );
		buf.append( dto.getProductName() );
		buf.append( ", " );
		buf.append( dto.getUnitCode() );
		buf.append( ", " );
		buf.append( dto.getQtyConsolidate() );
		buf.append( ", " );
		buf.append( dto.getUserId() );
		buf.append( ", " );
		buf.append( dto.getCorpId() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		System.out.println( buf.toString() );
	}

}

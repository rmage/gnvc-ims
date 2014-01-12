package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.KittingDetailDao;
import com.app.wms.engine.db.dto.KittingDetail;
import com.app.wms.engine.db.exceptions.KittingDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class KittingDetailDaoExample
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
		// findWhereKittingNoEquals("");
		// findWhereProductIdEquals("");
		// findWhereProductCodeEquals("");
		// findWhereProductNameEquals("");
		// findWhereUnitCodeEquals("");
		// findWhereQtyKittingEquals(0);
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
		KittingDetailDao dao = DaoFactory.createKittingDetailDao();
		List<KittingDetail> _result = dao.findAll();
		for (KittingDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereKittingNoEquals'
	 * 
	 * @param kittingNo
	 * @throws Exception
	 */
	public static void findWhereKittingNoEquals(String kittingNo) throws Exception
	{
		KittingDetailDao dao = DaoFactory.createKittingDetailDao();
		List<KittingDetail> _result = dao.findWhereKittingNoEquals(kittingNo);
		for (KittingDetail dto : _result) {
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
		KittingDetailDao dao = DaoFactory.createKittingDetailDao();
		List<KittingDetail> _result = dao.findWhereProductIdEquals(productId);
		for (KittingDetail dto : _result) {
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
		KittingDetailDao dao = DaoFactory.createKittingDetailDao();
		List<KittingDetail> _result = dao.findWhereProductCodeEquals(productCode);
		for (KittingDetail dto : _result) {
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
		KittingDetailDao dao = DaoFactory.createKittingDetailDao();
		List<KittingDetail> _result = dao.findWhereProductNameEquals(productName);
		for (KittingDetail dto : _result) {
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
		KittingDetailDao dao = DaoFactory.createKittingDetailDao();
		List<KittingDetail> _result = dao.findWhereUnitCodeEquals(unitCode);
		for (KittingDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQtyKittingEquals'
	 * 
	 * @param qtyKitting
	 * @throws Exception
	 */
	public static void findWhereQtyKittingEquals(int qtyKitting) throws Exception
	{
		KittingDetailDao dao = DaoFactory.createKittingDetailDao();
		List<KittingDetail> _result = dao.findWhereQtyKittingEquals(qtyKitting);
		for (KittingDetail dto : _result) {
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
		KittingDetailDao dao = DaoFactory.createKittingDetailDao();
		List<KittingDetail> _result = dao.findWhereUserIdEquals(userId);
		for (KittingDetail dto : _result) {
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
		KittingDetailDao dao = DaoFactory.createKittingDetailDao();
		List<KittingDetail> _result = dao.findWhereCorpIdEquals(corpId);
		for (KittingDetail dto : _result) {
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
		KittingDetailDao dao = DaoFactory.createKittingDetailDao();
		List<KittingDetail> _result = dao.findWhereWhCodeEquals(whCode);
		for (KittingDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(KittingDetail dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getKittingNo() );
		buf.append( ", " );
		buf.append( dto.getProductId() );
		buf.append( ", " );
		buf.append( dto.getProductCode() );
		buf.append( ", " );
		buf.append( dto.getProductName() );
		buf.append( ", " );
		buf.append( dto.getUnitCode() );
		buf.append( ", " );
		buf.append( dto.getQtyKitting() );
		buf.append( ", " );
		buf.append( dto.getUserId() );
		buf.append( ", " );
		buf.append( dto.getCorpId() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		System.out.println( buf.toString() );
	}

}

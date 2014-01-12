package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.BundleDetailDao;
import com.app.wms.engine.db.dto.BundleDetail;
import com.app.wms.engine.db.exceptions.BundleDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class BundleDetailDaoExample
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
		// findWhereBundleCodeEquals("");
		// findWhereProductIdEquals("");
		// findWhereProductCodeEquals("");
		// findWhereProductNameEquals("");
		// findWhereUnitCodeEquals("");
		// findWhereQtyBundleEquals(0);
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
		BundleDetailDao dao = DaoFactory.createBundleDetailDao();
		List<BundleDetail> _result = dao.findAll();
		for (BundleDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBundleCodeEquals'
	 * 
	 * @param bundleCode
	 * @throws Exception
	 */
	public static void findWhereBundleCodeEquals(String bundleCode) throws Exception
	{
		BundleDetailDao dao = DaoFactory.createBundleDetailDao();
		List<BundleDetail> _result = dao.findWhereBundleCodeEquals(bundleCode);
		for (BundleDetail dto : _result) {
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
		BundleDetailDao dao = DaoFactory.createBundleDetailDao();
		List<BundleDetail> _result = dao.findWhereProductIdEquals(productId);
		for (BundleDetail dto : _result) {
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
		BundleDetailDao dao = DaoFactory.createBundleDetailDao();
		List<BundleDetail> _result = dao.findWhereProductCodeEquals(productCode);
		for (BundleDetail dto : _result) {
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
		BundleDetailDao dao = DaoFactory.createBundleDetailDao();
		List<BundleDetail> _result = dao.findWhereProductNameEquals(productName);
		for (BundleDetail dto : _result) {
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
		BundleDetailDao dao = DaoFactory.createBundleDetailDao();
		List<BundleDetail> _result = dao.findWhereUnitCodeEquals(unitCode);
		for (BundleDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQtyBundleEquals'
	 * 
	 * @param qtyBundle
	 * @throws Exception
	 */
	public static void findWhereQtyBundleEquals(int qtyBundle) throws Exception
	{
		BundleDetailDao dao = DaoFactory.createBundleDetailDao();
		List<BundleDetail> _result = dao.findWhereQtyBundleEquals(qtyBundle);
		for (BundleDetail dto : _result) {
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
		BundleDetailDao dao = DaoFactory.createBundleDetailDao();
		List<BundleDetail> _result = dao.findWhereUserIdEquals(userId);
		for (BundleDetail dto : _result) {
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
		BundleDetailDao dao = DaoFactory.createBundleDetailDao();
		List<BundleDetail> _result = dao.findWhereCorpIdEquals(corpId);
		for (BundleDetail dto : _result) {
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
		BundleDetailDao dao = DaoFactory.createBundleDetailDao();
		List<BundleDetail> _result = dao.findWhereWhCodeEquals(whCode);
		for (BundleDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(BundleDetail dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getBundleCode() );
		buf.append( ", " );
		buf.append( dto.getProductId() );
		buf.append( ", " );
		buf.append( dto.getProductCode() );
		buf.append( ", " );
		buf.append( dto.getProductName() );
		buf.append( ", " );
		buf.append( dto.getUnitCode() );
		buf.append( ", " );
		buf.append( dto.getQtyBundle() );
		buf.append( ", " );
		buf.append( dto.getUserId() );
		buf.append( ", " );
		buf.append( dto.getCorpId() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		System.out.println( buf.toString() );
	}

}

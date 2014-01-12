package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.WhLocationDetailDao;
import com.app.wms.engine.db.dto.WhLocationDetail;
import com.app.wms.engine.db.exceptions.WhLocationDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class WhLocationDetailDaoExample
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
		// findWhereLocationIdEquals("");
		// findWhereProductIdEquals("");
		// findWhereProductCodeEquals("");
		// findWhereProductNameEquals("");
		// findWhereUnitCodeEquals("");
		// findWhereProductCategoryEquals("");
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
		WhLocationDetailDao dao = DaoFactory.createWhLocationDetailDao();
		List<WhLocationDetail> _result = dao.findAll();
		for (WhLocationDetail dto : _result) {
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
		WhLocationDetailDao dao = DaoFactory.createWhLocationDetailDao();
		List<WhLocationDetail> _result = dao.findWhereIdEquals(id);
		for (WhLocationDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereLocationIdEquals'
	 * 
	 * @param locationId
	 * @throws Exception
	 */
	public static void findWhereLocationIdEquals(String locationId) throws Exception
	{
		WhLocationDetailDao dao = DaoFactory.createWhLocationDetailDao();
		List<WhLocationDetail> _result = dao.findWhereLocationIdEquals(locationId);
		for (WhLocationDetail dto : _result) {
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
		WhLocationDetailDao dao = DaoFactory.createWhLocationDetailDao();
		List<WhLocationDetail> _result = dao.findWhereProductIdEquals(productId);
		for (WhLocationDetail dto : _result) {
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
		WhLocationDetailDao dao = DaoFactory.createWhLocationDetailDao();
		List<WhLocationDetail> _result = dao.findWhereProductCodeEquals(productCode);
		for (WhLocationDetail dto : _result) {
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
		WhLocationDetailDao dao = DaoFactory.createWhLocationDetailDao();
		List<WhLocationDetail> _result = dao.findWhereProductNameEquals(productName);
		for (WhLocationDetail dto : _result) {
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
		WhLocationDetailDao dao = DaoFactory.createWhLocationDetailDao();
		List<WhLocationDetail> _result = dao.findWhereUnitCodeEquals(unitCode);
		for (WhLocationDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductCategoryEquals'
	 * 
	 * @param productCategory
	 * @throws Exception
	 */
	public static void findWhereProductCategoryEquals(String productCategory) throws Exception
	{
		WhLocationDetailDao dao = DaoFactory.createWhLocationDetailDao();
		List<WhLocationDetail> _result = dao.findWhereProductCategoryEquals(productCategory);
		for (WhLocationDetail dto : _result) {
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
		WhLocationDetailDao dao = DaoFactory.createWhLocationDetailDao();
		List<WhLocationDetail> _result = dao.findWhereUserIdEquals(userId);
		for (WhLocationDetail dto : _result) {
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
		WhLocationDetailDao dao = DaoFactory.createWhLocationDetailDao();
		List<WhLocationDetail> _result = dao.findWhereCorpIdEquals(corpId);
		for (WhLocationDetail dto : _result) {
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
		WhLocationDetailDao dao = DaoFactory.createWhLocationDetailDao();
		List<WhLocationDetail> _result = dao.findWhereWhCodeEquals(whCode);
		for (WhLocationDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(WhLocationDetail dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getLocationId() );
		buf.append( ", " );
		buf.append( dto.getProductId() );
		buf.append( ", " );
		buf.append( dto.getProductCode() );
		buf.append( ", " );
		buf.append( dto.getProductName() );
		buf.append( ", " );
		buf.append( dto.getUnitCode() );
		buf.append( ", " );
		buf.append( dto.getProductCategory() );
		buf.append( ", " );
		buf.append( dto.getUserId() );
		buf.append( ", " );
		buf.append( dto.getCorpId() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		System.out.println( buf.toString() );
	}

}

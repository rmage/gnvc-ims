package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.StockOutDao;
import com.app.wms.engine.db.dto.StockOut;
import com.app.wms.engine.db.exceptions.StockOutDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class StockOutDaoExample
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
		// findWhereDocumentNoEquals("");
		// findWhereDocumentDateEquals(null);
		// findWhereProductCodeEquals("");
		// findWhereProductNameEquals("");
		// findWhereQuantityEquals(0);
		// findWhereIsActiveEquals("");
		// findWhereIsDeleteEquals("");
		// findWhereCreatedByEquals("");
		// findWhereCreatedDateEquals(null);
		// findWhereUpdatedByEquals("");
		// findWhereUpdatedDateEquals(null);
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		StockOutDao dao = DaoFactory.createStockOutDao();
		List<StockOut> _result = dao.findAll();
		for (StockOut dto : _result) {
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
		StockOutDao dao = DaoFactory.createStockOutDao();
		List<StockOut> _result = dao.findWhereIdEquals(id);
		for (StockOut dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDocumentNoEquals'
	 * 
	 * @param documentNo
	 * @throws Exception
	 */
	public static void findWhereDocumentNoEquals(String documentNo) throws Exception
	{
		StockOutDao dao = DaoFactory.createStockOutDao();
		List<StockOut> _result = dao.findWhereDocumentNoEquals(documentNo);
		for (StockOut dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDocumentDateEquals'
	 * 
	 * @param documentDate
	 * @throws Exception
	 */
	public static void findWhereDocumentDateEquals(Date documentDate) throws Exception
	{
		StockOutDao dao = DaoFactory.createStockOutDao();
		List<StockOut> _result = dao.findWhereDocumentDateEquals(documentDate);
		for (StockOut dto : _result) {
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
		StockOutDao dao = DaoFactory.createStockOutDao();
		List<StockOut> _result = dao.findWhereProductCodeEquals(productCode);
		for (StockOut dto : _result) {
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
		StockOutDao dao = DaoFactory.createStockOutDao();
		List<StockOut> _result = dao.findWhereProductNameEquals(productName);
		for (StockOut dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQuantityEquals'
	 * 
	 * @param quantity
	 * @throws Exception
	 */
	public static void findWhereQuantityEquals(int quantity) throws Exception
	{
		StockOutDao dao = DaoFactory.createStockOutDao();
		List<StockOut> _result = dao.findWhereQuantityEquals(quantity);
		for (StockOut dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIsActiveEquals'
	 * 
	 * @param isActive
	 * @throws Exception
	 */
	public static void findWhereIsActiveEquals(String isActive) throws Exception
	{
		StockOutDao dao = DaoFactory.createStockOutDao();
		List<StockOut> _result = dao.findWhereIsActiveEquals(isActive);
		for (StockOut dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIsDeleteEquals'
	 * 
	 * @param isDelete
	 * @throws Exception
	 */
	public static void findWhereIsDeleteEquals(String isDelete) throws Exception
	{
		StockOutDao dao = DaoFactory.createStockOutDao();
		List<StockOut> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (StockOut dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCreatedByEquals'
	 * 
	 * @param createdBy
	 * @throws Exception
	 */
	public static void findWhereCreatedByEquals(String createdBy) throws Exception
	{
		StockOutDao dao = DaoFactory.createStockOutDao();
		List<StockOut> _result = dao.findWhereCreatedByEquals(createdBy);
		for (StockOut dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCreatedDateEquals'
	 * 
	 * @param createdDate
	 * @throws Exception
	 */
	public static void findWhereCreatedDateEquals(Date createdDate) throws Exception
	{
		StockOutDao dao = DaoFactory.createStockOutDao();
		List<StockOut> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (StockOut dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUpdatedByEquals'
	 * 
	 * @param updatedBy
	 * @throws Exception
	 */
	public static void findWhereUpdatedByEquals(String updatedBy) throws Exception
	{
		StockOutDao dao = DaoFactory.createStockOutDao();
		List<StockOut> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (StockOut dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUpdatedDateEquals'
	 * 
	 * @param updatedDate
	 * @throws Exception
	 */
	public static void findWhereUpdatedDateEquals(Date updatedDate) throws Exception
	{
		StockOutDao dao = DaoFactory.createStockOutDao();
		List<StockOut> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (StockOut dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(StockOut dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getDocumentNo() );
		buf.append( ", " );
		buf.append( dto.getDocumentDate() );
		buf.append( ", " );
		buf.append( dto.getProductCode() );
		buf.append( ", " );
		buf.append( dto.getProductName() );
		buf.append( ", " );
		buf.append( dto.getQuantity() );
		buf.append( ", " );
		buf.append( dto.getIsActive() );
		buf.append( ", " );
		buf.append( dto.getIsDelete() );
		buf.append( ", " );
		buf.append( dto.getCreatedBy() );
		buf.append( ", " );
		buf.append( dto.getCreatedDate() );
		buf.append( ", " );
		buf.append( dto.getUpdatedBy() );
		buf.append( ", " );
		buf.append( dto.getUpdatedDate() );
		System.out.println( buf.toString() );
	}

}

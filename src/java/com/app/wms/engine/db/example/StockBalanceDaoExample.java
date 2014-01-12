package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.StockBalanceDao;
import com.app.wms.engine.db.dto.StockBalance;
import com.app.wms.engine.db.exceptions.StockBalanceDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class StockBalanceDaoExample
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
		// findWhereQtyInEquals(0);
		// findWhereQtyOutEquals(0);
		// findWhereBalanceEquals(0);
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
		StockBalanceDao dao = DaoFactory.createStockBalanceDao();
		List<StockBalance> _result = dao.findAll();
		for (StockBalance dto : _result) {
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
		StockBalanceDao dao = DaoFactory.createStockBalanceDao();
		List<StockBalance> _result = dao.findWhereIdEquals(id);
		for (StockBalance dto : _result) {
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
		StockBalanceDao dao = DaoFactory.createStockBalanceDao();
		List<StockBalance> _result = dao.findWhereDocumentNoEquals(documentNo);
		for (StockBalance dto : _result) {
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
		StockBalanceDao dao = DaoFactory.createStockBalanceDao();
		List<StockBalance> _result = dao.findWhereDocumentDateEquals(documentDate);
		for (StockBalance dto : _result) {
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
		StockBalanceDao dao = DaoFactory.createStockBalanceDao();
		List<StockBalance> _result = dao.findWhereProductCodeEquals(productCode);
		for (StockBalance dto : _result) {
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
		StockBalanceDao dao = DaoFactory.createStockBalanceDao();
		List<StockBalance> _result = dao.findWhereProductNameEquals(productName);
		for (StockBalance dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQtyInEquals'
	 * 
	 * @param qtyIn
	 * @throws Exception
	 */
	public static void findWhereQtyInEquals(int qtyIn) throws Exception
	{
		StockBalanceDao dao = DaoFactory.createStockBalanceDao();
		List<StockBalance> _result = dao.findWhereQtyInEquals(qtyIn);
		for (StockBalance dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQtyOutEquals'
	 * 
	 * @param qtyOut
	 * @throws Exception
	 */
	public static void findWhereQtyOutEquals(int qtyOut) throws Exception
	{
		StockBalanceDao dao = DaoFactory.createStockBalanceDao();
		List<StockBalance> _result = dao.findWhereQtyOutEquals(qtyOut);
		for (StockBalance dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBalanceEquals'
	 * 
	 * @param balance
	 * @throws Exception
	 */
	public static void findWhereBalanceEquals(int balance) throws Exception
	{
		StockBalanceDao dao = DaoFactory.createStockBalanceDao();
		List<StockBalance> _result = dao.findWhereBalanceEquals(balance);
		for (StockBalance dto : _result) {
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
		StockBalanceDao dao = DaoFactory.createStockBalanceDao();
		List<StockBalance> _result = dao.findWhereIsActiveEquals(isActive);
		for (StockBalance dto : _result) {
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
		StockBalanceDao dao = DaoFactory.createStockBalanceDao();
		List<StockBalance> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (StockBalance dto : _result) {
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
		StockBalanceDao dao = DaoFactory.createStockBalanceDao();
		List<StockBalance> _result = dao.findWhereCreatedByEquals(createdBy);
		for (StockBalance dto : _result) {
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
		StockBalanceDao dao = DaoFactory.createStockBalanceDao();
		List<StockBalance> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (StockBalance dto : _result) {
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
		StockBalanceDao dao = DaoFactory.createStockBalanceDao();
		List<StockBalance> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (StockBalance dto : _result) {
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
		StockBalanceDao dao = DaoFactory.createStockBalanceDao();
		List<StockBalance> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (StockBalance dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(StockBalance dto)
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
		buf.append( dto.getQtyIn() );
		buf.append( ", " );
		buf.append( dto.getQtyOut() );
		buf.append( ", " );
		buf.append( dto.getBalance() );
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

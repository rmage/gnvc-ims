package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.StockOpnameDao;
import com.app.wms.engine.db.dto.StockOpname;
import com.app.wms.engine.db.exceptions.StockOpnameDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class StockOpnameDaoExample
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
		// findWhereProductCodeEquals("");
		// findWhereWhCodeEquals("");
		// findWhereQuantityEquals(0);
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
		StockOpnameDao dao = DaoFactory.createStockOpnameDao();
		List<StockOpname> _result = dao.findAll();
		for (StockOpname dto : _result) {
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
		StockOpnameDao dao = DaoFactory.createStockOpnameDao();
		List<StockOpname> _result = dao.findWhereIdEquals(id);
		for (StockOpname dto : _result) {
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
		StockOpnameDao dao = DaoFactory.createStockOpnameDao();
		List<StockOpname> _result = dao.findWhereProductCodeEquals(productCode);
		for (StockOpname dto : _result) {
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
		StockOpnameDao dao = DaoFactory.createStockOpnameDao();
		List<StockOpname> _result = dao.findWhereWhCodeEquals(whCode);
		for (StockOpname dto : _result) {
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
		StockOpnameDao dao = DaoFactory.createStockOpnameDao();
		List<StockOpname> _result = dao.findWhereQuantityEquals(quantity);
		for (StockOpname dto : _result) {
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
		StockOpnameDao dao = DaoFactory.createStockOpnameDao();
		List<StockOpname> _result = dao.findWhereBalanceEquals(balance);
		for (StockOpname dto : _result) {
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
		StockOpnameDao dao = DaoFactory.createStockOpnameDao();
		List<StockOpname> _result = dao.findWhereIsActiveEquals(isActive);
		for (StockOpname dto : _result) {
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
		StockOpnameDao dao = DaoFactory.createStockOpnameDao();
		List<StockOpname> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (StockOpname dto : _result) {
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
		StockOpnameDao dao = DaoFactory.createStockOpnameDao();
		List<StockOpname> _result = dao.findWhereCreatedByEquals(createdBy);
		for (StockOpname dto : _result) {
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
		StockOpnameDao dao = DaoFactory.createStockOpnameDao();
		List<StockOpname> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (StockOpname dto : _result) {
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
		StockOpnameDao dao = DaoFactory.createStockOpnameDao();
		List<StockOpname> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (StockOpname dto : _result) {
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
		StockOpnameDao dao = DaoFactory.createStockOpnameDao();
		List<StockOpname> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (StockOpname dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(StockOpname dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getProductCode() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		buf.append( ", " );
		buf.append( dto.getQuantity() );
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

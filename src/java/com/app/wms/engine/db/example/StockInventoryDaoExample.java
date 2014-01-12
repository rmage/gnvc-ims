package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.StockInventoryDao;
import com.app.wms.engine.db.dto.StockInventory;
import com.app.wms.engine.db.exceptions.StockInventoryDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class StockInventoryDaoExample
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
		// findWhereBalanceEquals(0);
		// findWhereStartBalanceEquals(0);
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
		StockInventoryDao dao = DaoFactory.createStockInventoryDao();
		List<StockInventory> _result = dao.findAll();
		for (StockInventory dto : _result) {
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
		StockInventoryDao dao = DaoFactory.createStockInventoryDao();
		List<StockInventory> _result = dao.findWhereIdEquals(id);
		for (StockInventory dto : _result) {
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
		StockInventoryDao dao = DaoFactory.createStockInventoryDao();
		List<StockInventory> _result = dao.findWhereProductCodeEquals(productCode);
		for (StockInventory dto : _result) {
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
		StockInventoryDao dao = DaoFactory.createStockInventoryDao();
		List<StockInventory> _result = dao.findWhereWhCodeEquals(whCode);
		for (StockInventory dto : _result) {
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
		StockInventoryDao dao = DaoFactory.createStockInventoryDao();
		List<StockInventory> _result = dao.findWhereBalanceEquals(balance);
		for (StockInventory dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereStartBalanceEquals'
	 * 
	 * @param startBalance
	 * @throws Exception
	 */
	public static void findWhereStartBalanceEquals(int startBalance) throws Exception
	{
		StockInventoryDao dao = DaoFactory.createStockInventoryDao();
		List<StockInventory> _result = dao.findWhereStartBalanceEquals(startBalance);
		for (StockInventory dto : _result) {
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
		StockInventoryDao dao = DaoFactory.createStockInventoryDao();
		List<StockInventory> _result = dao.findWhereIsActiveEquals(isActive);
		for (StockInventory dto : _result) {
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
		StockInventoryDao dao = DaoFactory.createStockInventoryDao();
		List<StockInventory> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (StockInventory dto : _result) {
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
		StockInventoryDao dao = DaoFactory.createStockInventoryDao();
		List<StockInventory> _result = dao.findWhereCreatedByEquals(createdBy);
		for (StockInventory dto : _result) {
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
		StockInventoryDao dao = DaoFactory.createStockInventoryDao();
		List<StockInventory> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (StockInventory dto : _result) {
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
		StockInventoryDao dao = DaoFactory.createStockInventoryDao();
		List<StockInventory> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (StockInventory dto : _result) {
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
		StockInventoryDao dao = DaoFactory.createStockInventoryDao();
		List<StockInventory> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (StockInventory dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(StockInventory dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getProductCode() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		buf.append( ", " );
		buf.append( dto.getBalance() );
		buf.append( ", " );
		buf.append( dto.getStartBalance() );
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

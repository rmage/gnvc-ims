package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.StockInventoryDao;
import com.app.wms.engine.db.dto.StockInventory;
import com.app.wms.engine.db.dto.StockInventoryPk;
import com.app.wms.engine.db.exceptions.StockInventoryDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface StockInventoryDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return StockInventoryPk
	 */
	public StockInventoryPk insert(StockInventory dto);

	/** 
	 * Updates a single row in the stock_inventory table.
	 */
	public void update(StockInventoryPk pk, StockInventory dto) throws StockInventoryDaoException;
	
	public void updateFromProduct(StockInventoryPk pk, StockInventory dto) throws StockInventoryDaoException;

	/** 
	 * Deletes a single row in the stock_inventory table.
	 */
	public void delete(StockInventoryPk pk) throws StockInventoryDaoException;

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'id = :id'.
	 */
	public StockInventory findByPrimaryKey(int id) throws StockInventoryDaoException;

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria ''.
	 */
	public List<StockInventory> findAll() throws StockInventoryDaoException;

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'id = :id'.
	 */
	public List<StockInventory> findWhereIdEquals(int id) throws StockInventoryDaoException;

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'product_code = :productCode'.
	 */
	public List<StockInventory> findWhereProductCodeEquals(String productCode) throws StockInventoryDaoException;

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'wh_code = :whCode'.
	 */
	public List<StockInventory> findWhereWhCodeEquals(String whCode) throws StockInventoryDaoException;

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'balance = :balance'.
	 */
	public List<StockInventory> findWhereBalanceEquals(int balance) throws StockInventoryDaoException;

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'start_balance = :startBalance'.
	 */
	public List<StockInventory> findWhereStartBalanceEquals(int startBalance) throws StockInventoryDaoException;

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'is_active = :isActive'.
	 */
	public List<StockInventory> findWhereIsActiveEquals(String isActive) throws StockInventoryDaoException;

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<StockInventory> findWhereIsDeleteEquals(String isDelete) throws StockInventoryDaoException;

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'created_by = :createdBy'.
	 */
	public List<StockInventory> findWhereCreatedByEquals(String createdBy) throws StockInventoryDaoException;

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'created_date = :createdDate'.
	 */
	public List<StockInventory> findWhereCreatedDateEquals(Date createdDate) throws StockInventoryDaoException;

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<StockInventory> findWhereUpdatedByEquals(String updatedBy) throws StockInventoryDaoException;

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<StockInventory> findWhereUpdatedDateEquals(Date updatedDate) throws StockInventoryDaoException;

	/** 
	 * Returns the rows from the stock_inventory table that matches the specified primary-key value.
	 */
	public StockInventory findByPrimaryKey(StockInventoryPk pk) throws StockInventoryDaoException;
	
	public List <StockInventory> balance(StockInventory dto) throws StockInventoryDaoException;
	
	public List <StockInventory> findProductandWarehouse(StockInventory dto) throws StockInventoryDaoException;

	public void updateBalance(StockInventory dto) throws StockInventoryDaoException;
	
	public void updateBalanceNoWarehouse(StockInventory dto) throws StockInventoryDaoException;
}

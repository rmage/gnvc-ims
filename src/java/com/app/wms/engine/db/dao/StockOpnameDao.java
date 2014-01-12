package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.StockOpnameDao;
import com.app.wms.engine.db.dto.StockOpname;
import com.app.wms.engine.db.dto.StockOpnamePk;
import com.app.wms.engine.db.exceptions.StockOpnameDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface StockOpnameDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return StockOpnamePk
	 */
	public StockOpnamePk insert(StockOpname dto);

	/** 
	 * Updates a single row in the stock_opname table.
	 */
	public void update(StockOpnamePk pk, StockOpname dto) throws StockOpnameDaoException;

	/** 
	 * Deletes a single row in the stock_opname table.
	 */
	public void delete(StockOpnamePk pk) throws StockOpnameDaoException;

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'id = :id'.
	 */
	public StockOpname findByPrimaryKey(int id) throws StockOpnameDaoException;

	/** 
	 * Returns all rows from the stock_opname table that match the criteria ''.
	 */
	public List<StockOpname> findAll() throws StockOpnameDaoException;

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'id = :id'.
	 */
	public List<StockOpname> findWhereIdEquals(int id) throws StockOpnameDaoException;

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'product_code = :productCode'.
	 */
	public List<StockOpname> findWhereProductCodeEquals(String productCode) throws StockOpnameDaoException;

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'wh_code = :whCode'.
	 */
	public List<StockOpname> findWhereWhCodeEquals(String whCode) throws StockOpnameDaoException;

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'quantity = :quantity'.
	 */
	public List<StockOpname> findWhereQuantityEquals(int quantity) throws StockOpnameDaoException;

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'balance = :balance'.
	 */
	public List<StockOpname> findWhereBalanceEquals(int balance) throws StockOpnameDaoException;

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'is_active = :isActive'.
	 */
	public List<StockOpname> findWhereIsActiveEquals(String isActive) throws StockOpnameDaoException;

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<StockOpname> findWhereIsDeleteEquals(String isDelete) throws StockOpnameDaoException;

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'created_by = :createdBy'.
	 */
	public List<StockOpname> findWhereCreatedByEquals(String createdBy) throws StockOpnameDaoException;

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'created_date = :createdDate'.
	 */
	public List<StockOpname> findWhereCreatedDateEquals(Date createdDate) throws StockOpnameDaoException;

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<StockOpname> findWhereUpdatedByEquals(String updatedBy) throws StockOpnameDaoException;

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<StockOpname> findWhereUpdatedDateEquals(Date updatedDate) throws StockOpnameDaoException;

	/** 
	 * Returns the rows from the stock_opname table that matches the specified primary-key value.
	 */
	public StockOpname findByPrimaryKey(StockOpnamePk pk) throws StockOpnameDaoException;

}

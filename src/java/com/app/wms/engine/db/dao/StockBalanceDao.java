package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.StockBalanceDao;
import com.app.wms.engine.db.dto.StockBalance;
import com.app.wms.engine.db.dto.StockBalancePk;
import com.app.wms.engine.db.exceptions.StockBalanceDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface StockBalanceDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return StockBalancePk
	 */
	public StockBalancePk insert(StockBalance dto);

	/** 
	 * Updates a single row in the stock_balance table.
	 */
	public void update(StockBalancePk pk, StockBalance dto) throws StockBalanceDaoException;

	/** 
	 * Deletes a single row in the stock_balance table.
	 */
	public void delete(StockBalancePk pk) throws StockBalanceDaoException;

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'id = :id'.
	 */
	public StockBalance findByPrimaryKey(int id) throws StockBalanceDaoException;

	/** 
	 * Returns all rows from the stock_balance table that match the criteria ''.
	 */
	public List<StockBalance> findAll() throws StockBalanceDaoException;

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'id = :id'.
	 */
	public List<StockBalance> findWhereIdEquals(int id) throws StockBalanceDaoException;

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'document_no = :documentNo'.
	 */
	public List<StockBalance> findWhereDocumentNoEquals(String documentNo) throws StockBalanceDaoException;

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'document_date = :documentDate'.
	 */
	public List<StockBalance> findWhereDocumentDateEquals(Date documentDate) throws StockBalanceDaoException;

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'product_code = :productCode'.
	 */
	public List<StockBalance> findWhereProductCodeEquals(String productCode) throws StockBalanceDaoException;

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'product_name = :productName'.
	 */
	public List<StockBalance> findWhereProductNameEquals(String productName) throws StockBalanceDaoException;

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'qty_in = :qtyIn'.
	 */
	public List<StockBalance> findWhereQtyInEquals(float qtyIn) throws StockBalanceDaoException;

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'qty_out = :qtyOut'.
	 */
	public List<StockBalance> findWhereQtyOutEquals(float qtyOut) throws StockBalanceDaoException;

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'balance = :balance'.
	 */
	public List<StockBalance> findWhereBalanceEquals(float balance) throws StockBalanceDaoException;

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'is_active = :isActive'.
	 */
	public List<StockBalance> findWhereIsActiveEquals(String isActive) throws StockBalanceDaoException;

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<StockBalance> findWhereIsDeleteEquals(String isDelete) throws StockBalanceDaoException;

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'created_by = :createdBy'.
	 */
	public List<StockBalance> findWhereCreatedByEquals(String createdBy) throws StockBalanceDaoException;

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'created_date = :createdDate'.
	 */
	public List<StockBalance> findWhereCreatedDateEquals(Date createdDate) throws StockBalanceDaoException;

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<StockBalance> findWhereUpdatedByEquals(String updatedBy) throws StockBalanceDaoException;

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<StockBalance> findWhereUpdatedDateEquals(Date updatedDate) throws StockBalanceDaoException;

	/** 
	 * Returns the rows from the stock_balance table that matches the specified primary-key value.
	 */
	public StockBalance findByPrimaryKey(StockBalancePk pk) throws StockBalanceDaoException;

}

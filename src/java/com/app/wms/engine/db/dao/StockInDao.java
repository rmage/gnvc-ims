package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.StockInDao;
import com.app.wms.engine.db.dto.StockIn;
import com.app.wms.engine.db.dto.StockInPk;
import com.app.wms.engine.db.exceptions.StockInDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface StockInDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return StockInPk
	 */
	public StockInPk insert(StockIn dto);

	/** 
	 * Updates a single row in the stock_in table.
	 */
	public void update(StockInPk pk, StockIn dto) throws StockInDaoException;

	/** 
	 * Deletes a single row in the stock_in table.
	 */
	public void delete(StockInPk pk) throws StockInDaoException;

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'id = :id'.
	 */
	public StockIn findByPrimaryKey(int id) throws StockInDaoException;

	/** 
	 * Returns all rows from the stock_in table that match the criteria ''.
	 */
	public List<StockIn> findAll() throws StockInDaoException;

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'id = :id'.
	 */
	public List<StockIn> findWhereIdEquals(int id) throws StockInDaoException;

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'document_no = :documentNo'.
	 */
	public List<StockIn> findWhereDocumentNoEquals(String documentNo) throws StockInDaoException;

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'document_date = :documentDate'.
	 */
	public List<StockIn> findWhereDocumentDateEquals(Date documentDate) throws StockInDaoException;

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'product_code = :productCode'.
	 */
	public List<StockIn> findWhereProductCodeEquals(String productCode) throws StockInDaoException;

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'product_name = :productName'.
	 */
	public List<StockIn> findWhereProductNameEquals(String productName) throws StockInDaoException;

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'quantity = :quantity'.
	 */
	public List<StockIn> findWhereQuantityEquals(int quantity) throws StockInDaoException;

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'is_active = :isActive'.
	 */
	public List<StockIn> findWhereIsActiveEquals(String isActive) throws StockInDaoException;

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<StockIn> findWhereIsDeleteEquals(String isDelete) throws StockInDaoException;

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'created_by = :createdBy'.
	 */
	public List<StockIn> findWhereCreatedByEquals(String createdBy) throws StockInDaoException;

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'created_date = :createdDate'.
	 */
	public List<StockIn> findWhereCreatedDateEquals(Date createdDate) throws StockInDaoException;

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<StockIn> findWhereUpdatedByEquals(String updatedBy) throws StockInDaoException;

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<StockIn> findWhereUpdatedDateEquals(Date updatedDate) throws StockInDaoException;

	/** 
	 * Returns the rows from the stock_in table that matches the specified primary-key value.
	 */
	public StockIn findByPrimaryKey(StockInPk pk) throws StockInDaoException;

}

package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.StockOutDao;
import com.app.wms.engine.db.dto.StockOut;
import com.app.wms.engine.db.dto.StockOutPk;
import com.app.wms.engine.db.exceptions.StockOutDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface StockOutDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return StockOutPk
	 */
	public StockOutPk insert(StockOut dto);

	/** 
	 * Updates a single row in the stock_out table.
	 */
	public void update(StockOutPk pk, StockOut dto) throws StockOutDaoException;

	/** 
	 * Deletes a single row in the stock_out table.
	 */
	public void delete(StockOutPk pk) throws StockOutDaoException;

	/** 
	 * Returns all rows from the stock_out table that match the criteria 'id = :id'.
	 */
	public StockOut findByPrimaryKey(int id) throws StockOutDaoException;

	/** 
	 * Returns all rows from the stock_out table that match the criteria ''.
	 */
	public List<StockOut> findAll() throws StockOutDaoException;

	/** 
	 * Returns all rows from the stock_out table that match the criteria 'id = :id'.
	 */
	public List<StockOut> findWhereIdEquals(int id) throws StockOutDaoException;

	/** 
	 * Returns all rows from the stock_out table that match the criteria 'document_no = :documentNo'.
	 */
	public List<StockOut> findWhereDocumentNoEquals(String documentNo) throws StockOutDaoException;

	/** 
	 * Returns all rows from the stock_out table that match the criteria 'document_date = :documentDate'.
	 */
	public List<StockOut> findWhereDocumentDateEquals(Date documentDate) throws StockOutDaoException;

	/** 
	 * Returns all rows from the stock_out table that match the criteria 'product_code = :productCode'.
	 */
	public List<StockOut> findWhereProductCodeEquals(String productCode) throws StockOutDaoException;

	/** 
	 * Returns all rows from the stock_out table that match the criteria 'product_name = :productName'.
	 */
	public List<StockOut> findWhereProductNameEquals(String productName) throws StockOutDaoException;

	/** 
	 * Returns all rows from the stock_out table that match the criteria 'quantity = :quantity'.
	 */
	public List<StockOut> findWhereQuantityEquals(int quantity) throws StockOutDaoException;

	/** 
	 * Returns all rows from the stock_out table that match the criteria 'is_active = :isActive'.
	 */
	public List<StockOut> findWhereIsActiveEquals(String isActive) throws StockOutDaoException;

	/** 
	 * Returns all rows from the stock_out table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<StockOut> findWhereIsDeleteEquals(String isDelete) throws StockOutDaoException;

	/** 
	 * Returns all rows from the stock_out table that match the criteria 'created_by = :createdBy'.
	 */
	public List<StockOut> findWhereCreatedByEquals(String createdBy) throws StockOutDaoException;

	/** 
	 * Returns all rows from the stock_out table that match the criteria 'created_date = :createdDate'.
	 */
	public List<StockOut> findWhereCreatedDateEquals(Date createdDate) throws StockOutDaoException;

	/** 
	 * Returns all rows from the stock_out table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<StockOut> findWhereUpdatedByEquals(String updatedBy) throws StockOutDaoException;

	/** 
	 * Returns all rows from the stock_out table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<StockOut> findWhereUpdatedDateEquals(Date updatedDate) throws StockOutDaoException;

	/** 
	 * Returns the rows from the stock_out table that matches the specified primary-key value.
	 */
	public StockOut findByPrimaryKey(StockOutPk pk) throws StockOutDaoException;

}

package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.AccountingDao;
import com.app.wms.engine.db.dto.Accounting;
import com.app.wms.engine.db.dto.AccountingPk;
import com.app.wms.engine.db.exceptions.AccountingDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface AccountingDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return AccountingPk
	 */
	public AccountingPk insert(Accounting dto);

	/** 
	 * Updates a single row in the accounting table.
	 */
	public void update(AccountingPk pk, Accounting dto) throws AccountingDaoException;

	/** 
	 * Deletes a single row in the accounting table.
	 */
	public void delete(AccountingPk pk) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'id = :id'.
	 */
	public Accounting findByPrimaryKey(int id) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria ''.
	 */
	public List<Accounting> findAll() throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'id = :id'.
	 */
	public List<Accounting> findWhereIdEquals(int id) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'accounting_no = :accountingNo'.
	 */
	public List<Accounting> findWhereAccountingNoEquals(String accountingNo) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'accounting_date = :accountingDate'.
	 */
	public List<Accounting> findWhereAccountingDateEquals(Date accountingDate) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'document_no = :documentNo'.
	 */
	public List<Accounting> findWhereDocumentNoEquals(String documentNo) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'document_date = :documentDate'.
	 */
	public List<Accounting> findWhereDocumentDateEquals(Date documentDate) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'product_code = :productCode'.
	 */
	public List<Accounting> findWhereProductCodeEquals(String productCode) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'product_name = :productName'.
	 */
	public List<Accounting> findWhereProductNameEquals(String productName) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'qty_in = :qtyIn'.
	 */
	public List<Accounting> findWhereQtyInEquals(float qtyIn) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'qty_out = :qtyOut'.
	 */
	public List<Accounting> findWhereQtyOutEquals(float qtyOut) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'balance = :balance'.
	 */
	public List<Accounting> findWhereBalanceEquals(float balance) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'wh_code = :whCode'.
	 */
	public List<Accounting> findWhereWhCodeEquals(String whCode) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'unitprice = :unitprice'.
	 */
	public List<Accounting> findWhereUnitpriceEquals(float unitprice) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'amount = :amount'.
	 */
	public List<Accounting> findWhereAmountEquals(float amount) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Accounting> findWhereCreatedByEquals(String createdBy) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Accounting> findWhereCreatedDateEquals(Date createdDate) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Accounting> findWhereUpdatedByEquals(String updatedBy) throws AccountingDaoException;

	/** 
	 * Returns all rows from the accounting table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Accounting> findWhereUpdatedDateEquals(Date updatedDate) throws AccountingDaoException;

	/** 
	 * Returns the rows from the accounting table that matches the specified primary-key value.
	 */
	public Accounting findByPrimaryKey(AccountingPk pk) throws AccountingDaoException;

	public List<Accounting> findProductDetail() throws AccountingDaoException;
}

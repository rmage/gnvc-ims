package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dto.Currency;
import com.app.wms.engine.db.dto.CurrencyPk;
import com.app.wms.engine.db.exceptions.CurrencyDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface CurrencyDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return CurrencyPk
	 */
	public CurrencyPk insert(Currency dto);

	/** 
	 * Updates a single row in the currency table.
	 */
	public void update(CurrencyPk pk, Currency dto) throws CurrencyDaoException;

	/** 
	 * Deletes a single row in the currency table.
	 */
	public void delete(CurrencyPk pk) throws CurrencyDaoException;

	/** 
	 * Returns all rows from the currency table that match the criteria 'id = :id'.
	 */
	public Currency findByPrimaryKey(int id) throws CurrencyDaoException;

	/** 
	 * Returns all rows from the currency table that match the criteria ''.
	 */
	public List<Currency> findAll() throws CurrencyDaoException;

	/** 
	 * Returns all rows from the currency table that match the criteria 'id = :id'.
	 */
	public List<Currency> findWhereIdEquals(int id) throws CurrencyDaoException;

	/** 
	 * Returns all rows from the currency table that match the criteria 'currency_code = :currencyCode'.
	 */
	public List<Currency> findWhereCurrencyCodeEquals(String currencyCode) throws CurrencyDaoException;

	/** 
	 * Returns all rows from the currency table that match the criteria 'currency_name = :currencyName'.
	 */
	public List<Currency> findWhereCurrencyNameEquals(String currencyName) throws CurrencyDaoException;

	/** 
	 * Returns all rows from the currency table that match the criteria 'currency_symbol = :currencySymbol'.
	 */
	public List<Currency> findWhereCurrencySymbolEquals(String currencySymbol) throws CurrencyDaoException;

	/** 
	 * Returns all rows from the currency table that match the criteria 'is_active = :isActive'.
	 */
	public List<Currency> findWhereIsActiveEquals(String isActive) throws CurrencyDaoException;

	/** 
	 * Returns all rows from the currency table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<Currency> findWhereIsDeleteEquals(String isDelete) throws CurrencyDaoException;

	/** 
	 * Returns all rows from the currency table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Currency> findWhereCreatedByEquals(String createdBy) throws CurrencyDaoException;

	/** 
	 * Returns all rows from the currency table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Currency> findWhereCreatedDateEquals(Date createdDate) throws CurrencyDaoException;

	/** 
	 * Returns all rows from the currency table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Currency> findWhereUpdatedByEquals(String updatedBy) throws CurrencyDaoException;

	/** 
	 * Returns all rows from the currency table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Currency> findWhereUpdatedDateEquals(Date updatedDate) throws CurrencyDaoException;

	/** 
	 * Returns the rows from the currency table that matches the specified primary-key value.
	 */
	public Currency findByPrimaryKey(CurrencyPk pk) throws CurrencyDaoException;

}

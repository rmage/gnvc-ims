package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.PriceCatalogDao;
import com.app.wms.engine.db.dto.PriceCatalog;
import com.app.wms.engine.db.dto.PriceCatalogPk;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.exceptions.PriceCatalogDaoException;
import com.app.wms.engine.db.exceptions.SupplierDaoException;

import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface PriceCatalogDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return PriceCatalogPk
	 */
	public PriceCatalogPk insert(PriceCatalog dto);

	/** 
	 * Updates a single row in the price_catalog table.
	 */
	public void update(PriceCatalogPk pk, PriceCatalog dto) throws PriceCatalogDaoException;

	/** 
	 * Deletes a single row in the price_catalog table.
	 */
	public void delete(PriceCatalogPk pk) throws PriceCatalogDaoException;

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'id = :id'.
	 */
	public PriceCatalog findByPrimaryKey(int id) throws PriceCatalogDaoException;

	/** 
	 * Returns all rows from the price_catalog table that match the criteria ''.
	 */
	public List<PriceCatalog> findAll() throws PriceCatalogDaoException;

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'id = :id'.
	 */
	public List<PriceCatalog> findWhereIdEquals(int id) throws PriceCatalogDaoException;

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'catalog_code = :catalogCode'.
	 */
	public List<PriceCatalog> findWhereCatalogCodeEquals(String catalogCode) throws PriceCatalogDaoException;

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'catalog_name = :catalogName'.
	 */
	public List<PriceCatalog> findWhereCatalogNameEquals(String catalogName) throws PriceCatalogDaoException;

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'is_active = :isActive'.
	 */
	public List<PriceCatalog> findWhereIsActiveEquals(String isActive) throws PriceCatalogDaoException;

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<PriceCatalog> findWhereIsDeleteEquals(String isDelete) throws PriceCatalogDaoException;

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'created_by = :createdBy'.
	 */
	public List<PriceCatalog> findWhereCreatedByEquals(String createdBy) throws PriceCatalogDaoException;

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'created_date = :createdDate'.
	 */
	public List<PriceCatalog> findWhereCreatedDateEquals(Date createdDate) throws PriceCatalogDaoException;

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<PriceCatalog> findWhereUpdatedByEquals(String updatedBy) throws PriceCatalogDaoException;

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<PriceCatalog> findWhereUpdatedDateEquals(Date updatedDate) throws PriceCatalogDaoException;

	/** 
	 * Returns the rows from the price_catalog table that matches the specified primary-key value.
	 */
	public PriceCatalog findByPrimaryKey(PriceCatalogPk pk) throws PriceCatalogDaoException;
	
	public List<PriceCatalog> findPriceCatalogPaging(PriceCatalog p,int page) throws PriceCatalogDaoException;

}

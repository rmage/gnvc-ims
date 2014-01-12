package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.web.engine.search.WarehouseLocationSearch;
import com.app.wms.engine.db.dto.WarehouseLocation;
import com.app.wms.engine.db.dto.WarehouseLocationPk;
import com.app.wms.engine.db.exceptions.WarehouseLocationDaoException;

public interface WarehouseLocationDao {
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return WarehouseLocationPk
	 */
	public WarehouseLocationPk insert(WarehouseLocation dto);

	/** 
	 * Updates a single row in the Location table.
	 */
	public void update(WarehouseLocationPk pk, WarehouseLocation dto) throws WarehouseLocationDaoException;

	/** 
	 * Deletes a single row in the Location table.
	 */
	public void delete(WarehouseLocationPk pk) throws WarehouseLocationDaoException;

	/** 
	 * Returns all rows from the Location table that match the criteria 'Location_CODE = :whCode'.
	 */
	public WarehouseLocation findByPrimaryKey(String whCode) throws WarehouseLocationDaoException;

	/** 
	 * Returns all rows from the Location table that match the criteria ''.
	 */
	public List<WarehouseLocation> findAll() throws WarehouseLocationDaoException;

	/** 
	 * Returns all rows from the Location table that match the criteria 'Location_CODE = :whCode'.
	 */
	public List<WarehouseLocation> findWarehouseLocationWhereLocationCodeEquals(String whCode) throws WarehouseLocationDaoException;

	/** 
	 * Returns all rows from the Location table that match the criteria 'NAME = :name'.
	 */
	public List<WarehouseLocation> findWarehouseLocationWhereLocationNameEquals(String name) throws WarehouseLocationDaoException;

	/** 
	 * Returns the rows from the Location table that matches the specified primary-key value.
	 */
	public WarehouseLocation findByPrimaryKey(WarehouseLocationPk pk) throws WarehouseLocationDaoException;

	public List<WarehouseLocation> findByCriteriaLimit(WarehouseLocationSearch criteria, int start, int end) throws WarehouseLocationDaoException;

	public List<WarehouseLocation> findWhereProductLocation(WarehouseLocation wl) throws WarehouseLocationDaoException;

	public List<WarehouseLocation> findWarehouseLocationWhereLocationAreaEquals(String area) throws WarehouseLocationDaoException;

}

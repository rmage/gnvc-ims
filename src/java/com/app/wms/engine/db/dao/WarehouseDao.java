package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.WarehouseDao;
import com.app.wms.engine.db.dto.Warehouse;
import com.app.wms.engine.db.dto.WarehousePk;
import com.app.wms.engine.db.exceptions.WarehouseDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface WarehouseDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return WarehousePk
	 */
	public WarehousePk insert(Warehouse dto);

	/** 
	 * Updates a single row in the warehouse table.
	 */
	public void update(WarehousePk pk, Warehouse dto) throws WarehouseDaoException;

	/** 
	 * Deletes a single row in the warehouse table.
	 */
	public void delete(WarehousePk pk) throws WarehouseDaoException;

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'id = :id'.
	 */
	public Warehouse findByPrimaryKey(int id) throws WarehouseDaoException;

	/** 
	 * Returns all rows from the warehouse table that match the criteria ''.
	 */
	public List<Warehouse> findAll() throws WarehouseDaoException;

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'id = :id'.
	 */
	public List<Warehouse> findWhereIdEquals(int id) throws WarehouseDaoException;

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'wh_code = :whCode'.
	 */
	public List<Warehouse> findWhereWhCodeEquals(String whCode) throws WarehouseDaoException;

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'wh_name = :whName'.
	 */
	public List<Warehouse> findWhereWhNameEquals(String whName) throws WarehouseDaoException;

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'category_name = :categoryName'.
	 */
	public List<Warehouse> findWhereCategoryNameEquals(String categoryName) throws WarehouseDaoException;

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'is_active = :isActive'.
	 */
	public List<Warehouse> findWhereIsActiveEquals(String isActive) throws WarehouseDaoException;

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<Warehouse> findWhereIsDeleteEquals(String isDelete) throws WarehouseDaoException;

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Warehouse> findWhereCreatedByEquals(String createdBy) throws WarehouseDaoException;

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Warehouse> findWhereCreatedDateEquals(Date createdDate) throws WarehouseDaoException;

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Warehouse> findWhereUpdatedByEquals(String updatedBy) throws WarehouseDaoException;

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Warehouse> findWhereUpdatedDateEquals(Date updatedDate) throws WarehouseDaoException;

	/** 
	 * Returns the rows from the warehouse table that matches the specified primary-key value.
	 */
	public Warehouse findByPrimaryKey(WarehousePk pk) throws WarehouseDaoException;

}

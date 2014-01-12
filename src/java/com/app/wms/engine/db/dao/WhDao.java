package com.app.wms.engine.db.dao;

import com.app.web.engine.search.WarehouseSearch;
import com.app.wms.engine.db.dao.WhDao;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.dto.WhPk;
import com.app.wms.engine.db.exceptions.WhDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface WhDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return WhPk
	 */
	public WhPk insert(Wh dto);

	/** 
	 * Updates a single row in the wh table.
	 */
	public void update(WhPk pk, Wh dto) throws WhDaoException;

	/** 
	 * Deletes a single row in the wh table.
	 */
	public void delete(WhPk pk) throws WhDaoException;

	/** 
	 * Returns all rows from the wh table that match the criteria 'id = :id'.
	 */
	public Wh findByPrimaryKey(int id) throws WhDaoException;

	/** 
	 * Returns all rows from the wh table that match the criteria ''.
	 */
	public List<Wh> findAll() throws WhDaoException;

	/** 
	 * Returns all rows from the wh table that match the criteria 'id = :id'.
	 */
	public List<Wh> findWhereIdEquals(int id) throws WhDaoException;

	/** 
	 * Returns all rows from the wh table that match the criteria 'wh_code = :whCode'.
	 */
	public List<Wh> findWhereWhCodeEquals(String whCode) throws WhDaoException;

	/** 
	 * Returns all rows from the wh table that match the criteria 'code = :code'.
	 */
	public List<Wh> findWhereCodeEquals(String code) throws WhDaoException;

	/** 
	 * Returns all rows from the wh table that match the criteria 'name = :name'.
	 */
	public List<Wh> findWhereNameEquals(String name) throws WhDaoException;

	/** 
	 * Returns all rows from the wh table that match the criteria 'region = :region'.
	 */
	public List<Wh> findWhereRegionEquals(String region) throws WhDaoException;

	/** 
	 * Returns all rows from the wh table that match the criteria 'is_active = :isActive'.
	 */
	public List<Wh> findWhereIsActiveEquals(String isActive) throws WhDaoException;

	/** 
	 * Returns all rows from the wh table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<Wh> findWhereIsDeleteEquals(String isDelete) throws WhDaoException;

	/** 
	 * Returns all rows from the wh table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Wh> findWhereCreatedByEquals(String createdBy) throws WhDaoException;

	/** 
	 * Returns all rows from the wh table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Wh> findWhereCreatedDateEquals(Date createdDate) throws WhDaoException;

	/** 
	 * Returns all rows from the wh table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Wh> findWhereUpdatedByEquals(String updatedBy) throws WhDaoException;

	/** 
	 * Returns all rows from the wh table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Wh> findWhereUpdatedDateEquals(Date updatedDate) throws WhDaoException;

	/** 
	 * Returns the rows from the wh table that matches the specified primary-key value.
	 */
	public Wh findByPrimaryKey(WhPk pk) throws WhDaoException;

	public Wh findByPrimaryKey(String whCode)throws WhDaoException;

	public List<Wh> findByCriteriaLimit(WarehouseSearch c, int start, int end) throws WhDaoException;

	public List<Wh> findWhPaging(WarehouseSearch c,int page) throws WhDaoException;
	
	public List<Wh> findWhereCorpId(String corpId) throws WhDaoException;
}

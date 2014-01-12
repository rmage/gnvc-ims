package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.WhLocatingAreaDao;
import com.app.wms.engine.db.dto.WhLocatingArea;
import com.app.wms.engine.db.dto.WhLocatingAreaPk;
import com.app.wms.engine.db.exceptions.WhLocatingAreaDaoException;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface WhLocatingAreaDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return WhLocatingAreaPk
	 */
	public WhLocatingAreaPk insert(WhLocatingArea dto);

	/** 
	 * Updates a single row in the wh_locating_area table.
	 */
	public void update(WhLocatingAreaPk pk, WhLocatingArea dto) throws WhLocatingAreaDaoException;

	/** 
	 * Deletes a single row in the wh_locating_area table.
	 */
	public void delete(WhLocatingAreaPk pk) throws WhLocatingAreaDaoException;

	/** 
	 * Returns all rows from the wh_locating_area table that match the criteria 'id = :id'.
	 */
	public WhLocatingArea findByPrimaryKey(int id) throws WhLocatingAreaDaoException;
	
	public WhLocatingArea findByPrimaryKey(String locatingId) throws WhLocatingAreaDaoException;

	/** 
	 * Returns all rows from the wh_locating_area table that match the criteria ''.
	 */
	public List<WhLocatingArea> findAll() throws WhLocatingAreaDaoException;

	/** 
	 * Returns all rows from the wh_locating_area table that match the criteria 'id = :id'.
	 */
	public List<WhLocatingArea> findWhereIdEquals(int id) throws WhLocatingAreaDaoException;

	/** 
	 * Returns all rows from the wh_locating_area table that match the criteria 'locating_id = :locatingId'.
	 */
	public List<WhLocatingArea> findWhereLocatingIdEquals(String locatingId) throws WhLocatingAreaDaoException;

	/** 
	 * Returns all rows from the wh_locating_area table that match the criteria 'locating_area = :locatingArea'.
	 */
	public List<WhLocatingArea> findWhereLocatingAreaEquals(String locatingArea) throws WhLocatingAreaDaoException;

	/** 
	 * Returns all rows from the wh_locating_area table that match the criteria 'locating_condition = :locatingCondition'.
	 */
	public List<WhLocatingArea> findWhereLocatingConditionEquals(String locatingCondition) throws WhLocatingAreaDaoException;

	/** 
	 * Returns the rows from the wh_locating_area table that matches the specified primary-key value.
	 */
	public WhLocatingArea findByPrimaryKey(WhLocatingAreaPk pk) throws WhLocatingAreaDaoException;

	public List<WhLocatingArea> findWhLocatingAreaPaging(WhLocatingArea w, Integer page) throws WhLocatingAreaDaoException;
}

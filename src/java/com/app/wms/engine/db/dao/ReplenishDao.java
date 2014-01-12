package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.web.engine.search.WarehouseSearch;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.dto.WhPk;
import com.app.wms.engine.db.exceptions.WhDaoException;

public interface ReplenishDao 
{

	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return WhPk
	 */
	public WhPk insert(Wh dto);

	/** 
	 * Updates a single row in the WH table.
	 */
	public void update(WhPk pk, Wh dto) throws WhDaoException;

	/** 
	 * Deletes a single row in the WH table.
	 */
	public void delete(WhPk pk) throws WhDaoException;

	/** 
	 * Returns all rows from the WH table that match the criteria 'WH_CODE = :whCode'.
	 */
	public Wh findByPrimaryKey(String whCode) throws WhDaoException;

	/** 
	 * Returns all rows from the WH table that match the criteria ''.
	 */
	public List<Wh> findAll() throws WhDaoException;

	/** 
	 * Returns all rows from the WH table that match the criteria 'WH_CODE = :whCode'.
	 */
	public List<Wh> findWhereWhCodeEquals(String whCode) throws WhDaoException;

	/** 
	 * Returns all rows from the WH table that match the criteria 'NAME = :name'.
	 */
	public List<Wh> findWhereNameEquals(String name) throws WhDaoException;

	/** 
	 * Returns the rows from the WH table that matches the specified primary-key value.
	 */
	public Wh findByPrimaryKey(WhPk pk) throws WhDaoException;

	public List<Wh> findByCriteriaLimit(WarehouseSearch criteria, int start, int end) throws WhDaoException;



}

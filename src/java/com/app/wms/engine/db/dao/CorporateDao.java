package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.web.engine.search.CorporateSearch;
import com.app.wms.engine.db.dto.Corporate;
import com.app.wms.engine.db.dto.CorporatePk;
import com.app.wms.engine.db.exceptions.CorporateDaoException;

public interface CorporateDao {
	

	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return CorporatePk
	 */
	public CorporatePk insert(Corporate dto);

	/** 
	 * Updates a single row in the Corporate table.
	 */
	public void update(CorporatePk pk, Corporate dto) throws CorporateDaoException;

	/** 
	 * Deletes a single row in the Corporate table.
	 */
	public void delete(CorporatePk pk) throws CorporateDaoException;

	/** 
	 * Returns all rows from the Corporate table that match the criteria 'corp_id = :id'.
	 */
	public Corporate findByPrimaryKey(String Id) throws CorporateDaoException;

	/** 
	 * Returns all rows from the Corporate table that match the criteria ''.
	 */
	public List<Corporate> findAll() throws CorporateDaoException;
	
	public List<Corporate> findCorporate(String id) throws CorporateDaoException;

	/** 
	 * Returns all rows from the Corporate table that match the criteria 'corp_id = :id'.
	 */
	public List<Corporate> findWhereCorporateIdEquals(String id) throws CorporateDaoException;

	/** 
	 * Returns all rows from the Corporate table that match the criteria 'corp_name = :name'.
	 */
	public List<Corporate> findWhereCorporateNameEquals(String name) throws CorporateDaoException;

	/** 
	 * Returns the rows from the Corporate table that matches the specified primary-key value.
	 */
	public Corporate findByPrimaryKey(CorporatePk pk) throws CorporateDaoException;

	public List<Corporate> findByCriteriaLimit(CorporateSearch criteria, int start, int end) throws CorporateDaoException;

	public List<Corporate> findCorpWh(Corporate corporate) throws CorporateDaoException;

}

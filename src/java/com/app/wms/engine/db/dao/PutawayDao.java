package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.web.engine.search.WarehouseSearch;
import com.app.wms.engine.db.dto.Putaway;
import com.app.wms.engine.db.dto.PutawayPk;
import com.app.wms.engine.db.exceptions.PutawayDaoException;

public interface PutawayDao 
{

	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return PutawayPk
	 */
	public PutawayPk insert(Putaway dto);

	/** 
	 * Updates a single row in the Putaway table.
	 */
	public void update(PutawayPk pk, Putaway dto) throws PutawayDaoException;

	/** 
	 * Deletes a single row in the Putaway table.
	 */
	public void delete(PutawayPk pk) throws PutawayDaoException;

	/** 
	 * Returns all rows from the Putaway table that match the criteria 'Putaway_CODE = :PutawayCode'.
	 */
	public Putaway findByPrimaryKey(String PutawayCode) throws PutawayDaoException;

	/** 
	 * Returns all rows from the Putaway table that match the criteria ''.
	 */
	public List<Putaway> findAll() throws PutawayDaoException;

	/** 
	 * Returns all rows from the Putaway table that match the criteria 'Putaway_CODE = :PutawayCode'.
	 */
	public List<Putaway> findPutawayerePutawayCodeEquals(String PutawayCode) throws PutawayDaoException;

	/** 
	 * Returns all rows from the Putaway table that match the criteria 'NAME = :name'.
	 */
	public List<Putaway> findPutawayereNameEquals(String name) throws PutawayDaoException;

	/** 
	 * Returns the rows from the Putaway table that matches the specified primary-key value.
	 */
	public Putaway findByPrimaryKey(PutawayPk pk) throws PutawayDaoException;

	public List<Putaway> findByCriteriaLimit(WarehouseSearch criteria, int start, int end) throws PutawayDaoException;


}

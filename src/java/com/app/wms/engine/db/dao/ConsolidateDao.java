package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.ConsolidateDao;
import com.app.wms.engine.db.dto.Consolidate;
import com.app.wms.engine.db.dto.ConsolidatePk;
import com.app.wms.engine.db.dto.SalesOrder;
import com.app.wms.engine.db.exceptions.ConsolidateDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDaoException;

import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface ConsolidateDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return ConsolidatePk
	 */
	public ConsolidatePk insert(Consolidate dto);

	/** 
	 * Updates a single row in the consolidate table.
	 */
	public void update(ConsolidatePk pk, Consolidate dto) throws ConsolidateDaoException;

	/** 
	 * Deletes a single row in the consolidate table.
	 */
	public void delete(ConsolidatePk pk) throws ConsolidateDaoException;

	/** 
	 * Returns all rows from the consolidate table that match the criteria 'consolidate_no = :consolidateNo'.
	 */
	public Consolidate findByPrimaryKey(String consolidateNo) throws ConsolidateDaoException;

	/** 
	 * Returns all rows from the consolidate table that match the criteria ''.
	 */
	public List<Consolidate> findAll() throws ConsolidateDaoException;

	/** 
	 * Returns all rows from the consolidate table that match the criteria 'consolidate_no = :consolidateNo'.
	 */
	public List<Consolidate> findWhereConsolidateNoEquals(String consolidateNo) throws ConsolidateDaoException;

	/** 
	 * Returns all rows from the consolidate table that match the criteria 'consolidate_date = :consolidateDate'.
	 */
	public List<Consolidate> findWhereConsolidateDateEquals(Date consolidateDate) throws ConsolidateDaoException;

	/** 
	 * Returns all rows from the consolidate table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Consolidate> findWhereCreatedByEquals(String createdBy) throws ConsolidateDaoException;

	/** 
	 * Returns all rows from the consolidate table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Consolidate> findWhereCreatedDateEquals(Date createdDate) throws ConsolidateDaoException;

	/** 
	 * Returns all rows from the consolidate table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Consolidate> findWhereUpdatedByEquals(String updatedBy) throws ConsolidateDaoException;

	/** 
	 * Returns all rows from the consolidate table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Consolidate> findWhereUpdatedDateEquals(Date updatedDate) throws ConsolidateDaoException;

	/** 
	 * Returns the rows from the consolidate table that matches the specified primary-key value.
	 */
	public Consolidate findByPrimaryKey(ConsolidatePk pk) throws ConsolidateDaoException;

	public List<Consolidate> findConsolidatePaging(Consolidate c, int page) throws ConsolidateDaoException;
	

}

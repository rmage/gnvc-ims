package com.app.wms.engine.db.dao;

import com.app.web.engine.search.ReplenishSearch;
import com.app.wms.engine.db.dao.ReplenishmentDao;
import com.app.wms.engine.db.dto.Replenishment;
import com.app.wms.engine.db.dto.ReplenishmentPk;
import com.app.wms.engine.db.exceptions.ReplenishmentDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface ReplenishmentDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return ReplenishmentPk
	 */
	public ReplenishmentPk insert(Replenishment dto);

	/** 
	 * Updates a single row in the replenishment table.
	 */
	public void update(ReplenishmentPk pk, Replenishment dto) throws ReplenishmentDaoException;

	/** 
	 * Deletes a single row in the replenishment table.
	 */
	public void delete(ReplenishmentPk pk) throws ReplenishmentDaoException;

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'replenish_no = :replenishNo'.
	 */
	public Replenishment findByPrimaryKey(String replenishNo) throws ReplenishmentDaoException;

	/** 
	 * Returns all rows from the replenishment table that match the criteria ''.
	 */
	public List<Replenishment> findAll() throws ReplenishmentDaoException;

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'replenish_no = :replenishNo'.
	 */
	public List<Replenishment> findWhereReplenishNoEquals(String replenishNo) throws ReplenishmentDaoException;

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'replenish_date = :replenishDate'.
	 */
	public List<Replenishment> findWhereReplenishDateEquals(Date replenishDate) throws ReplenishmentDaoException;

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'status_app = :statusApp'.
	 */
	public List<Replenishment> findWhereStatusAppEquals(String statusApp) throws ReplenishmentDaoException;

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'app_date = :appDate'.
	 */
	public List<Replenishment> findWhereAppDateEquals(Date appDate) throws ReplenishmentDaoException;

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Replenishment> findWhereCreatedByEquals(String createdBy) throws ReplenishmentDaoException;

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Replenishment> findWhereCreatedDateEquals(Date createdDate) throws ReplenishmentDaoException;

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Replenishment> findWhereUpdatedByEquals(String updatedBy) throws ReplenishmentDaoException;

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Replenishment> findWhereUpdatedDateEquals(Date updatedDate) throws ReplenishmentDaoException;

	/** 
	 * Returns the rows from the replenishment table that matches the specified primary-key value.
	 */
	public Replenishment findByPrimaryKey(ReplenishmentPk pk) throws ReplenishmentDaoException;
        
        /* added by FYA : 23 May 2013 */
        public void statusDocument(String replenishNo, String status) throws ReplenishmentDaoException;
        
        /* added by FYA : 24 May 2013 */
        public List<Replenishment> findByCriteriaLimit(ReplenishSearch c, int start, int end) throws ReplenishmentDaoException;
}

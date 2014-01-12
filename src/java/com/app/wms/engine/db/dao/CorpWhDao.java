package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.CorpWhDao;
import com.app.wms.engine.db.dto.CorpWh;
import com.app.wms.engine.db.exceptions.CorpWhDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface CorpWhDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(CorpWh dto);

	/** 
	 * Returns all rows from the corp_wh table that match the criteria ''.
	 */
	public List<CorpWh> findAll() throws CorpWhDaoException;

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'corp_wh_id = :corpWhId'.
	 */
	public List<CorpWh> findWhereCorpWhIdEquals(String corpWhId) throws CorpWhDaoException;

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'corp_id = :corpId'.
	 */
	public List<CorpWh> findWhereCorpIdEquals(String corpId) throws CorpWhDaoException;

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'wh_code = :whCode'.
	 */
	public List<CorpWh> findWhereWhCodeEquals(String whCode) throws CorpWhDaoException;

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'is_active = :isActive'.
	 */
	public List<CorpWh> findWhereIsActiveEquals(String isActive) throws CorpWhDaoException;

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<CorpWh> findWhereIsDeleteEquals(String isDelete) throws CorpWhDaoException;

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'created_by = :createdBy'.
	 */
	public List<CorpWh> findWhereCreatedByEquals(String createdBy) throws CorpWhDaoException;

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'created_date = :createdDate'.
	 */
	public List<CorpWh> findWhereCreatedDateEquals(Date createdDate) throws CorpWhDaoException;

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<CorpWh> findWhereUpdatedByEquals(String updatedBy) throws CorpWhDaoException;

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<CorpWh> findWhereUpdatedDateEquals(Date updatedDate) throws CorpWhDaoException;

}

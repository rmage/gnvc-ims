package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.CorpUserDao;
import com.app.wms.engine.db.dto.CorpUser;
import com.app.wms.engine.db.dto.CorpUserPk;
import com.app.wms.engine.db.exceptions.CorpUserDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface CorpUserDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return CorpUserPk
	 */
	public CorpUserPk insert(CorpUser dto);

	/** 
	 * Updates a single row in the corp_user table.
	 */
	public void update(CorpUserPk pk, CorpUser dto) throws CorpUserDaoException;

	/** 
	 * Deletes a single row in the corp_user table.
	 */
	public void delete(CorpUserPk pk) throws CorpUserDaoException;

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'corpuser_id = :corpuserId'.
	 */
	public CorpUser findByPrimaryKey(String corpuserId) throws CorpUserDaoException;

	/** 
	 * Returns all rows from the corp_user table that match the criteria ''.
	 */
	public List<CorpUser> findAll() throws CorpUserDaoException;

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'corpuser_id = :corpuserId'.
	 */
	public List<CorpUser> findWhereCorpuserIdEquals(String corpuserId) throws CorpUserDaoException;

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'corp_id = :corpId'.
	 */
	public List<CorpUser> findWhereCorpIdEquals(String corpId) throws CorpUserDaoException;

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'user_id = :userId'.
	 */
	public List<CorpUser> findWhereUserIdEquals(String userId) throws CorpUserDaoException;

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'is_active = :isActive'.
	 */
	public List<CorpUser> findWhereIsActiveEquals(String isActive) throws CorpUserDaoException;

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<CorpUser> findWhereIsDeleteEquals(String isDelete) throws CorpUserDaoException;

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'created_by = :createdBy'.
	 */
	public List<CorpUser> findWhereCreatedByEquals(String createdBy) throws CorpUserDaoException;

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'created_date = :createdDate'.
	 */
	public List<CorpUser> findWhereCreatedDateEquals(Date createdDate) throws CorpUserDaoException;

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<CorpUser> findWhereUpdatedByEquals(String updatedBy) throws CorpUserDaoException;

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<CorpUser> findWhereUpdatedDateEquals(Date updatedDate) throws CorpUserDaoException;

	/** 
	 * Returns the rows from the corp_user table that matches the specified primary-key value.
	 */
	public CorpUser findByPrimaryKey(CorpUserPk pk) throws CorpUserDaoException;

}

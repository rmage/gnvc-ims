package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.CorpUserGroupDao;
import com.app.wms.engine.db.dto.CorpUserGroup;
import com.app.wms.engine.db.dto.CorpUserGroupPk;
import com.app.wms.engine.db.exceptions.CorpUserGroupDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface CorpUserGroupDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return CorpUserGroupPk
	 */
	public CorpUserGroupPk insert(CorpUserGroup dto);

	/** 
	 * Updates a single row in the corp_user_group table.
	 */
	public void update(CorpUserGroupPk pk, CorpUserGroup dto) throws CorpUserGroupDaoException;

	/** 
	 * Deletes a single row in the corp_user_group table.
	 */
	public void delete(CorpUserGroupPk pk) throws CorpUserGroupDaoException;

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'corp_usergroup_id = :corpUsergroupId'.
	 */
	public CorpUserGroup findByPrimaryKey(String corpUsergroupId) throws CorpUserGroupDaoException;

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria ''.
	 */
	public List<CorpUserGroup> findAll() throws CorpUserGroupDaoException;

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'corp_usergroup_id = :corpUsergroupId'.
	 */
	public List<CorpUserGroup> findWhereCorpUsergroupIdEquals(String corpUsergroupId) throws CorpUserGroupDaoException;

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'corp_id = :corpId'.
	 */
	public List<CorpUserGroup> findWhereCorpIdEquals(String corpId) throws CorpUserGroupDaoException;

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'wh_code = :whCode'.
	 */
	public List<CorpUserGroup> findWhereWhCodeEquals(String whCode) throws CorpUserGroupDaoException;

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'role_code = :roleCode'.
	 */
	public List<CorpUserGroup> findWhereRoleCodeEquals(String roleCode) throws CorpUserGroupDaoException;

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'is_active = :isActive'.
	 */
	public List<CorpUserGroup> findWhereIsActiveEquals(String isActive) throws CorpUserGroupDaoException;

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<CorpUserGroup> findWhereIsDeleteEquals(String isDelete) throws CorpUserGroupDaoException;

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'created_by = :createdBy'.
	 */
	public List<CorpUserGroup> findWhereCreatedByEquals(String createdBy) throws CorpUserGroupDaoException;

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'created_date = :createdDate'.
	 */
	public List<CorpUserGroup> findWhereCreatedDateEquals(Date createdDate) throws CorpUserGroupDaoException;

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<CorpUserGroup> findWhereUpdatedByEquals(String updatedBy) throws CorpUserGroupDaoException;

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<CorpUserGroup> findWhereUpdatedDateEquals(Date updatedDate) throws CorpUserGroupDaoException;

	/** 
	 * Returns the rows from the corp_user_group table that matches the specified primary-key value.
	 */
	public CorpUserGroup findByPrimaryKey(CorpUserGroupPk pk) throws CorpUserGroupDaoException;

}

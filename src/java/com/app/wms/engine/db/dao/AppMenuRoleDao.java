package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.AppMenuRoleDao;
import com.app.wms.engine.db.dto.AppMenuRole;
import com.app.wms.engine.db.dto.AppMenuRolePk;
import com.app.wms.engine.db.exceptions.AppMenuRoleDaoException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AppMenuRoleDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return AppMenuRolePk
	 */
	public AppMenuRolePk insert(AppMenuRole dto);

	/** 
	 * Updates a single row in the APP_MENU_ROLE table.
	 */
	public void update(AppMenuRolePk pk, AppMenuRole dto) throws AppMenuRoleDaoException;

	/** 
	 * Deletes a single row in the APP_MENU_ROLE table.
	 */
	public void delete(AppMenuRolePk pk) throws AppMenuRoleDaoException;

	/** 
	 * Returns all rows from the APP_MENU_ROLE table that match the criteria 'ROLE_CODE = :roleCode AND MENU_CODE = :menuCode'.
	 */
	public AppMenuRole findByPrimaryKey(String roleCode, String menuCode) throws AppMenuRoleDaoException;

	/** 
	 * Returns all rows from the APP_MENU_ROLE table that match the criteria ''.
	 */
	public List<AppMenuRole> findAll() throws AppMenuRoleDaoException;

	/** 
	 * Returns all rows from the APP_MENU_ROLE table that match the criteria 'MENU_CODE = :menuCode'.
	 */
	public List<AppMenuRole> findByAppMenu(String menuCode) throws AppMenuRoleDaoException;

	/** 
	 * Returns all rows from the APP_MENU_ROLE table that match the criteria 'ROLE_CODE = :roleCode'.
	 */
	public List<AppMenuRole> findByUserRole(String roleCode) throws AppMenuRoleDaoException;

	/** 
	 * Returns all rows from the APP_MENU_ROLE table that match the criteria 'ROLE_CODE = :roleCode'.
	 */
	public List<AppMenuRole> findWhereRoleCodeEquals(String roleCode) throws AppMenuRoleDaoException;

	/** 
	 * Returns all rows from the APP_MENU_ROLE table that match the criteria 'MENU_CODE = :menuCode'.
	 */
	public List<AppMenuRole> findWhereMenuCodeEquals(String menuCode) throws AppMenuRoleDaoException;

	/** 
	 * Returns all rows from the APP_MENU_ROLE table that match the criteria 'IS_VIEW = :isView'.
	 */
	public List<AppMenuRole> findWhereIsViewEquals(String isView) throws AppMenuRoleDaoException;

	/** 
	 * Returns all rows from the APP_MENU_ROLE table that match the criteria 'IS_CREATE = :isCreate'.
	 */
	public List<AppMenuRole> findWhereIsCreateEquals(String isCreate) throws AppMenuRoleDaoException;

	/** 
	 * Returns all rows from the APP_MENU_ROLE table that match the criteria 'IS_EDIT = :isEdit'.
	 */
	public List<AppMenuRole> findWhereIsEditEquals(String isEdit) throws AppMenuRoleDaoException;

	/** 
	 * Returns all rows from the APP_MENU_ROLE table that match the criteria 'IS_DELETE = :isDelete'.
	 */
	public List<AppMenuRole> findWhereIsDeleteEquals(String isDelete) throws AppMenuRoleDaoException;

	/** 
	 * Returns all rows from the APP_MENU_ROLE table that match the criteria 'CREATED_BY = :createdBy'.
	 */
	public List<AppMenuRole> findWhereCreatedByEquals(BigDecimal createdBy) throws AppMenuRoleDaoException;

	/** 
	 * Returns all rows from the APP_MENU_ROLE table that match the criteria 'CREATED_DATE = :createdDate'.
	 */
	public List<AppMenuRole> findWhereCreatedDateEquals(Date createdDate) throws AppMenuRoleDaoException;

	/** 
	 * Returns all rows from the APP_MENU_ROLE table that match the criteria 'UPDATED_BY = :updatedBy'.
	 */
	public List<AppMenuRole> findWhereUpdatedByEquals(BigDecimal updatedBy) throws AppMenuRoleDaoException;

	/** 
	 * Returns all rows from the APP_MENU_ROLE table that match the criteria 'UPDATED_DATE = :updatedDate'.
	 */
	public List<AppMenuRole> findWhereUpdatedDateEquals(Date updatedDate) throws AppMenuRoleDaoException;

	/** 
	 * Returns the rows from the APP_MENU_ROLE table that matches the specified primary-key value.
	 */
	public AppMenuRole findByPrimaryKey(AppMenuRolePk pk) throws AppMenuRoleDaoException;

}

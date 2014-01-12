package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.UserRoleDao;
import com.app.wms.engine.db.dto.UserRole;
import com.app.wms.engine.db.dto.UserRolePk;
import com.app.wms.engine.db.exceptions.UserRoleDaoException;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface UserRoleDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return UserRolePk
	 */
	public UserRolePk insert(UserRole dto);

	/** 
	 * Updates a single row in the user_role table.
	 */
	public void update(UserRolePk pk, UserRole dto) throws UserRoleDaoException;

	/** 
	 * Deletes a single row in the user_role table.
	 */
	public void delete(UserRolePk pk) throws UserRoleDaoException;

	/** 
	 * Returns all rows from the user_role table that match the criteria 'id = :id'.
	 */
	public UserRole findByPrimaryKey(int id) throws UserRoleDaoException;

	/** 
	 * Returns all rows from the user_role table that match the criteria ''.
	 */
	public List<UserRole> findAll() throws UserRoleDaoException;

	/** 
	 * Returns all rows from the user_role table that match the criteria 'id = :id'.
	 */
	public List<UserRole> findWhereIdEquals(int id) throws UserRoleDaoException;

	/** 
	 * Returns all rows from the user_role table that match the criteria 'role_code = :roleCode'.
	 */
	public List<UserRole> findWhereRoleCodeEquals(String roleCode) throws UserRoleDaoException;

	/** 
	 * Returns all rows from the user_role table that match the criteria 'role_name = :roleName'.
	 */
	public List<UserRole> findWhereRoleNameEquals(String roleName) throws UserRoleDaoException;

	/** 
	 * Returns all rows from the user_role table that match the criteria 'role_level = :roleLevel'.
	 */
	public List<UserRole> findWhereRoleLevelEquals(int roleLevel) throws UserRoleDaoException;

	/** 
	 * Returns the rows from the user_role table that matches the specified primary-key value.
	 */
	public UserRole findByPrimaryKey(UserRolePk pk) throws UserRoleDaoException;

}

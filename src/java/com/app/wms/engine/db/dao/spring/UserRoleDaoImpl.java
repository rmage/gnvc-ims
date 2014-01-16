package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.UserRoleDao;
import com.app.wms.engine.db.dto.UserRole;
import com.app.wms.engine.db.dto.UserRolePk;
import com.app.wms.engine.db.exceptions.UserRoleDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UserRoleDaoImpl extends AbstractDAO implements ParameterizedRowMapper<UserRole>, UserRoleDao
{
	protected SimpleJdbcTemplate jdbcTemplate;

	protected DataSource dataSource;

	/**
	 * Method 'setDataSource'
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return UserRolePk
	 */
	public UserRolePk insert(UserRole dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( role_code, role_name, role_level, department_code ) VALUES ( ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getRoleCode(),dto.getRoleName(),dto.getRoleLevel(),dto.getDepartmentCode()} );
		UserRolePk pk = new UserRolePk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the user_role table.
	 */
	public void update(UserRolePk pk, UserRole dto) throws UserRoleDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET role_code = ?, role_name = ?, role_level = ?, department_code = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getRoleCode(),dto.getRoleName(),dto.getRoleLevel(),dto.getDepartmentCode(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the user_role table.
	 */
	@Transactional
	public void delete(UserRolePk pk) throws UserRoleDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return UserRole
	 */
	public UserRole mapRow(ResultSet rs, int row) throws SQLException
	{
		UserRole dto = new UserRole();
		dto.setId( rs.getInt( 1 ) );
		dto.setRoleCode( rs.getString( 2 ) );
		dto.setRoleName( rs.getString( 3 ) );
		dto.setRoleLevel( rs.getInt( 4 ) );
		if (rs.wasNull()) {
			dto.setRoleLevelNull( true );
		}
		dto.setDepartmentCode(rs.getString(5));
		
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..user_role";
	}

	/** 
	 * Returns all rows from the user_role table that match the criteria 'id = :id'.
	 */
	@Transactional
	public UserRole findByPrimaryKey(int id) throws UserRoleDaoException
	{
		try {
			List<UserRole> list = jdbcTemplate.query("SELECT id, role_code, role_name, role_level, department_code FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new UserRoleDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the user_role table that match the criteria ''.
	 */
	@Transactional
	public List<UserRole> findAll() throws UserRoleDaoException
	{
		try {
			return jdbcTemplate.query("SELECT a.id, role_code, role_name, role_level, b.department_name FROM " + getTableName() 
                            + " a LEFT JOIN department b ON b.department_code = a.department_code where role_code not in ('SYSADMIN') ORDER BY a.id", this);
		}
		catch (Exception e) {
			throw new UserRoleDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the user_role table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<UserRole> findWhereIdEquals(int id) throws UserRoleDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, role_code, role_name, role_level, department_code FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new UserRoleDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the user_role table that match the criteria 'role_code = :roleCode'.
	 */
	@Transactional
	public List<UserRole> findWhereRoleCodeEquals(String roleCode) throws UserRoleDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, role_code, role_name, role_level, department_code FROM " + getTableName() + " WHERE role_code = ? ORDER BY role_code", this,roleCode);
		}
		catch (Exception e) {
			throw new UserRoleDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the user_role table that match the criteria 'role_name = :roleName'.
	 */
	@Transactional
	public List<UserRole> findWhereRoleNameEquals(String roleName) throws UserRoleDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, role_code, role_name, role_level, department_code FROM " + getTableName() + " WHERE role_name = ? ORDER BY role_name", this,roleName);
		}
		catch (Exception e) {
			throw new UserRoleDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the user_role table that match the criteria 'role_level = :roleLevel'.
	 */
	@Transactional
	public List<UserRole> findWhereRoleLevelEquals(int roleLevel) throws UserRoleDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, role_code, role_name, role_level, department_code FROM " + getTableName() + " WHERE role_level = ? ORDER BY role_level", this,roleLevel);
		}
		catch (Exception e) {
			throw new UserRoleDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the user_role table that matches the specified primary-key value.
	 */
	public UserRole findByPrimaryKey(UserRolePk pk) throws UserRoleDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

}

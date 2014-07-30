package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.CorpUserGroupDao;
import com.app.wms.engine.db.dto.CorpUserGroup;
import com.app.wms.engine.db.dto.CorpUserGroupPk;
import com.app.wms.engine.db.exceptions.CorpUserGroupDaoException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class CorpUserGroupDaoImpl extends AbstractDAO implements ParameterizedRowMapper<CorpUserGroup>, CorpUserGroupDao
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
	 * @return CorpUserGroupPk
	 */
	public CorpUserGroupPk insert(CorpUserGroup dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( corp_usergroup_id, corp_id, wh_code, role_code, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getCorpUsergroupId(),dto.getCorpId(),dto.getWhCode(),dto.getRoleCode(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the corp_user_group table.
	 */
	public void update(CorpUserGroupPk pk, CorpUserGroup dto) throws CorpUserGroupDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET corp_usergroup_id = ?, corp_id = ?, wh_code = ?, role_code = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE corp_usergroup_id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getCorpUsergroupId(),dto.getCorpId(),dto.getWhCode(),dto.getRoleCode(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getCorpUsergroupId() } );
	}

	/** 
	 * Deletes a single row in the corp_user_group table.
	 */
	@Transactional
	public void delete(CorpUserGroupPk pk) throws CorpUserGroupDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE corp_usergroup_id = ?",pk.getCorpUsergroupId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return CorpUserGroup
	 */
	public CorpUserGroup mapRow(ResultSet rs, int row) throws SQLException
	{
		CorpUserGroup dto = new CorpUserGroup();
		dto.setCorpUsergroupId( rs.getString( 1 ) );
		dto.setCorpId( rs.getString( 2 ) );
		dto.setWhCode( rs.getString( 3 ) );
		dto.setRoleCode( rs.getString( 4 ) );
		dto.setIsActive( rs.getString( 5 ) );
		dto.setIsDelete( rs.getString( 6 ) );
		dto.setCreatedBy( rs.getString( 7 ) );
		dto.setCreatedDate( rs.getTimestamp(8 ) );
		dto.setUpdatedBy( rs.getString( 9 ) );
		dto.setUpdatedDate( rs.getTimestamp(10 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "corp_user_group";
	}

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'corp_usergroup_id = :corpUsergroupId'.
	 */
	@Transactional
	public CorpUserGroup findByPrimaryKey(String corpUsergroupId) throws CorpUserGroupDaoException
	{
		try {
			List<CorpUserGroup> list = jdbcTemplate.query("SELECT corp_usergroup_id, corp_id, wh_code, role_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE corp_usergroup_id = ?", this,corpUsergroupId);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new CorpUserGroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria ''.
	 */
	@Transactional
	public List<CorpUserGroup> findAll() throws CorpUserGroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_usergroup_id, corp_id, wh_code, role_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY corp_usergroup_id", this);
		}
		catch (Exception e) {
			throw new CorpUserGroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'corp_usergroup_id = :corpUsergroupId'.
	 */
	@Transactional
	public List<CorpUserGroup> findWhereCorpUsergroupIdEquals(String corpUsergroupId) throws CorpUserGroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_usergroup_id, corp_id, wh_code, role_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE corp_usergroup_id = ? ORDER BY corp_usergroup_id", this,corpUsergroupId);
		}
		catch (Exception e) {
			throw new CorpUserGroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'corp_id = :corpId'.
	 */
	@Transactional
	public List<CorpUserGroup> findWhereCorpIdEquals(String corpId) throws CorpUserGroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_usergroup_id, corp_id, wh_code, role_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE corp_id = ? ORDER BY corp_id", this,corpId);
		}
		catch (Exception e) {
			throw new CorpUserGroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<CorpUserGroup> findWhereWhCodeEquals(String whCode) throws CorpUserGroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_usergroup_id, corp_id, wh_code, role_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new CorpUserGroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'role_code = :roleCode'.
	 */
	@Transactional
	public List<CorpUserGroup> findWhereRoleCodeEquals(String roleCode) throws CorpUserGroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_usergroup_id, corp_id, wh_code, role_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE role_code = ? ORDER BY role_code", this,roleCode);
		}
		catch (Exception e) {
			throw new CorpUserGroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<CorpUserGroup> findWhereIsActiveEquals(String isActive) throws CorpUserGroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_usergroup_id, corp_id, wh_code, role_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new CorpUserGroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<CorpUserGroup> findWhereIsDeleteEquals(String isDelete) throws CorpUserGroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_usergroup_id, corp_id, wh_code, role_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new CorpUserGroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<CorpUserGroup> findWhereCreatedByEquals(String createdBy) throws CorpUserGroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_usergroup_id, corp_id, wh_code, role_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new CorpUserGroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<CorpUserGroup> findWhereCreatedDateEquals(Date createdDate) throws CorpUserGroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_usergroup_id, corp_id, wh_code, role_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new CorpUserGroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<CorpUserGroup> findWhereUpdatedByEquals(String updatedBy) throws CorpUserGroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_usergroup_id, corp_id, wh_code, role_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new CorpUserGroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user_group table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<CorpUserGroup> findWhereUpdatedDateEquals(Date updatedDate) throws CorpUserGroupDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_usergroup_id, corp_id, wh_code, role_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new CorpUserGroupDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the corp_user_group table that matches the specified primary-key value.
	 */
	public CorpUserGroup findByPrimaryKey(CorpUserGroupPk pk) throws CorpUserGroupDaoException
	{
		return findByPrimaryKey( pk.getCorpUsergroupId() );
	}

}

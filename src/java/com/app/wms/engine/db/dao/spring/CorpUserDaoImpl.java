package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.CorpUserDao;
import com.app.wms.engine.db.dto.CorpUser;
import com.app.wms.engine.db.dto.CorpUserPk;
import com.app.wms.engine.db.exceptions.CorpUserDaoException;
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

public class CorpUserDaoImpl extends AbstractDAO implements ParameterizedRowMapper<CorpUser>, CorpUserDao
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
	 * @return CorpUserPk
	 */
	public CorpUserPk insert(CorpUser dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( corpuser_id, corp_id, user_id, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )");
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
		su.update( new Object[] { dto.getCorpuserId(),dto.getCorpId(),dto.getUserId(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the corp_user table.
	 */
	public void update(CorpUserPk pk, CorpUser dto) throws CorpUserDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET corpuser_id = ?, corp_id = ?, user_id = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE corpuser_id = ?");
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
		su.update( new Object[] { dto.getCorpuserId(),dto.getCorpId(),dto.getUserId(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getCorpuserId() } );
	}

	/** 
	 * Deletes a single row in the corp_user table.
	 */
	@Transactional
	public void delete(CorpUserPk pk) throws CorpUserDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE corpuser_id = ?",pk.getCorpuserId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return CorpUser
	 */
	public CorpUser mapRow(ResultSet rs, int row) throws SQLException
	{
		CorpUser dto = new CorpUser();
		dto.setCorpuserId( rs.getString( 1 ) );
		dto.setCorpId( rs.getString( 2 ) );
		dto.setUserId( rs.getString( 3 ) );
		dto.setIsActive( rs.getString( 4 ) );
		dto.setIsDelete( rs.getString( 5 ) );
		dto.setCreatedBy( rs.getString( 6 ) );
		dto.setCreatedDate( rs.getTimestamp(7 ) );
		dto.setUpdatedBy( rs.getString( 8 ) );
		dto.setUpdatedDate( rs.getTimestamp(9 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "corp_user";
	}

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'corpuser_id = :corpuserId'.
	 */
	@Transactional
	public CorpUser findByPrimaryKey(String corpuserId) throws CorpUserDaoException
	{
		try {
			List<CorpUser> list = jdbcTemplate.query("SELECT corpuser_id, corp_id, user_id, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE corpuser_id = ?", this,corpuserId);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new CorpUserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user table that match the criteria ''.
	 */
	@Transactional
	public List<CorpUser> findAll() throws CorpUserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corpuser_id, corp_id, user_id, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY corpuser_id", this);
		}
		catch (Exception e) {
			throw new CorpUserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'corpuser_id = :corpuserId'.
	 */
	@Transactional
	public List<CorpUser> findWhereCorpuserIdEquals(String corpuserId) throws CorpUserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corpuser_id, corp_id, user_id, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE corpuser_id = ? ORDER BY corpuser_id", this,corpuserId);
		}
		catch (Exception e) {
			throw new CorpUserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'corp_id = :corpId'.
	 */
	@Transactional
	public List<CorpUser> findWhereCorpIdEquals(String corpId) throws CorpUserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corpuser_id, corp_id, user_id, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE corp_id = ? ORDER BY corp_id", this,corpId);
		}
		catch (Exception e) {
			throw new CorpUserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'user_id = :userId'.
	 */
	@Transactional
	public List<CorpUser> findWhereUserIdEquals(String userId) throws CorpUserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corpuser_id, corp_id, user_id, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE user_id = ? ORDER BY user_id", this,userId);
		}
		catch (Exception e) {
			throw new CorpUserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<CorpUser> findWhereIsActiveEquals(String isActive) throws CorpUserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corpuser_id, corp_id, user_id, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new CorpUserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<CorpUser> findWhereIsDeleteEquals(String isDelete) throws CorpUserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corpuser_id, corp_id, user_id, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new CorpUserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<CorpUser> findWhereCreatedByEquals(String createdBy) throws CorpUserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corpuser_id, corp_id, user_id, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new CorpUserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<CorpUser> findWhereCreatedDateEquals(Date createdDate) throws CorpUserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corpuser_id, corp_id, user_id, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new CorpUserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<CorpUser> findWhereUpdatedByEquals(String updatedBy) throws CorpUserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corpuser_id, corp_id, user_id, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new CorpUserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_user table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<CorpUser> findWhereUpdatedDateEquals(Date updatedDate) throws CorpUserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corpuser_id, corp_id, user_id, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new CorpUserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the corp_user table that matches the specified primary-key value.
	 */
	public CorpUser findByPrimaryKey(CorpUserPk pk) throws CorpUserDaoException
	{
		return findByPrimaryKey( pk.getCorpuserId() );
	}

}

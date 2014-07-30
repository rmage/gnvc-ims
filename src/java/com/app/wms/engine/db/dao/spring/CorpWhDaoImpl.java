package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.CorpWhDao;
import com.app.wms.engine.db.dto.CorpWh;
import com.app.wms.engine.db.exceptions.CorpWhDaoException;
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

public class CorpWhDaoImpl extends AbstractDAO implements ParameterizedRowMapper<CorpWh>, CorpWhDao
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
	 */
	public void insert(CorpWh dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( corp_wh_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )");
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
		su.update( new Object[] { dto.getCorpWhId(),dto.getCorpId(),dto.getWhCode(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return CorpWh
	 */
	public CorpWh mapRow(ResultSet rs, int row) throws SQLException
	{
		CorpWh dto = new CorpWh();
		dto.setCorpWhId( rs.getString( 1 ) );
		dto.setCorpId( rs.getString( 2 ) );
		dto.setWhCode( rs.getString( 3 ) );
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
		return "corp_wh";
	}

	/** 
	 * Returns all rows from the corp_wh table that match the criteria ''.
	 */
	@Transactional
	public List<CorpWh> findAll() throws CorpWhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_wh_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new CorpWhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'corp_wh_id = :corpWhId'.
	 */
	@Transactional
	public List<CorpWh> findWhereCorpWhIdEquals(String corpWhId) throws CorpWhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_wh_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE corp_wh_id = ? ORDER BY corp_wh_id", this,corpWhId);
		}
		catch (Exception e) {
			throw new CorpWhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'corp_id = :corpId'.
	 */
	@Transactional
	public List<CorpWh> findWhereCorpIdEquals(String corpId) throws CorpWhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_wh_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE corp_id = ? ORDER BY corp_id", this,corpId);
		}
		catch (Exception e) {
			throw new CorpWhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<CorpWh> findWhereWhCodeEquals(String whCode) throws CorpWhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_wh_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new CorpWhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<CorpWh> findWhereIsActiveEquals(String isActive) throws CorpWhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_wh_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new CorpWhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<CorpWh> findWhereIsDeleteEquals(String isDelete) throws CorpWhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_wh_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new CorpWhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<CorpWh> findWhereCreatedByEquals(String createdBy) throws CorpWhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_wh_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new CorpWhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<CorpWh> findWhereCreatedDateEquals(Date createdDate) throws CorpWhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_wh_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new CorpWhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<CorpWh> findWhereUpdatedByEquals(String updatedBy) throws CorpWhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_wh_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new CorpWhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp_wh table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<CorpWh> findWhereUpdatedDateEquals(Date updatedDate) throws CorpWhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_wh_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new CorpWhDaoException("Query failed", e);
		}
		
	}

}

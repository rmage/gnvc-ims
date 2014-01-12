package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.TallymanDao;
import com.app.wms.engine.db.dto.Tallyman;
import com.app.wms.engine.db.dto.TallymanPk;
import com.app.wms.engine.db.exceptions.TallymanDaoException;
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

public class TallymanDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Tallyman>, TallymanDao
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
	 * @return TallymanPk
	 */
	public TallymanPk insert(Tallyman dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( tally_id, code, name, tallyname, jobfunction, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getTallyId(),dto.getCode(),dto.getName(),dto.getTallyname(),dto.getJobfunction(),dto.getCorpId(),dto.getWhCode(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		TallymanPk pk = new TallymanPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the tallyman table.
	 */
	public void update(TallymanPk pk, Tallyman dto) throws TallymanDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET tally_id = ?, code = ?, name = ?, tallyname = ?, jobfunction = ?, corp_id = ?, wh_code = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getTallyId(),dto.getCode(),dto.getName(),dto.getTallyname(),dto.getJobfunction(),dto.getCorpId(),dto.getWhCode(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the tallyman table.
	 */
	@Transactional
	public void delete(TallymanPk pk) throws TallymanDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Tallyman
	 */
	public Tallyman mapRow(ResultSet rs, int row) throws SQLException
	{
		Tallyman dto = new Tallyman();
		dto.setId( rs.getInt( 1 ) );
		dto.setTallyId( rs.getString( 2 ) );
		dto.setCode( rs.getString( 3 ) );
		dto.setName( rs.getString( 4 ) );
		dto.setTallyname( rs.getString( 5 ) );
		dto.setJobfunction( rs.getString( 6 ) );
		dto.setCorpId( rs.getString( 7 ) );
		dto.setWhCode( rs.getString( 8 ) );
		dto.setIsActive( rs.getString( 9 ) );
		dto.setIsDelete( rs.getString( 10 ) );
		dto.setCreatedBy( rs.getInt( 11 ) );
		if (rs.wasNull()) {
			dto.setCreatedByNull( true );
		}
		
		dto.setCreatedDate( rs.getTimestamp(12 ) );
		dto.setUpdatedBy( rs.getInt( 13 ) );
		if (rs.wasNull()) {
			dto.setUpdatedByNull( true );
		}
		
		dto.setUpdatedDate( rs.getTimestamp(14 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..tallyman";
	}

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Tallyman findByPrimaryKey(int id) throws TallymanDaoException
	{
		try {
			List<Tallyman> list = jdbcTemplate.query("SELECT id, tally_id, code, name, tallyname, jobfunction, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new TallymanDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the tallyman table that match the criteria ''.
	 */
	@Transactional
	public List<Tallyman> findAll() throws TallymanDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tally_id, code, name, tallyname, jobfunction, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new TallymanDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Tallyman> findWhereIdEquals(int id) throws TallymanDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tally_id, code, name, tallyname, jobfunction, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new TallymanDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'tally_id = :tallyId'.
	 */
	@Transactional
	public List<Tallyman> findWhereTallyIdEquals(String tallyId) throws TallymanDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tally_id, code, name, tallyname, jobfunction, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE tally_id = ? ORDER BY tally_id", this,tallyId);
		}
		catch (Exception e) {
			throw new TallymanDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'code = :code'.
	 */
	@Transactional
	public List<Tallyman> findWhereCodeEquals(String code) throws TallymanDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tally_id, code, name, tallyname, jobfunction, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE code = ? ORDER BY code", this,code);
		}
		catch (Exception e) {
			throw new TallymanDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'name = :name'.
	 */
	@Transactional
	public List<Tallyman> findWhereNameEquals(String name) throws TallymanDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tally_id, code, name, tallyname, jobfunction, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE name = ? ORDER BY name", this,name);
		}
		catch (Exception e) {
			throw new TallymanDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'tallyname = :tallyname'.
	 */
	@Transactional
	public List<Tallyman> findWhereTallynameEquals(String tallyname) throws TallymanDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tally_id, code, name, tallyname, jobfunction, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE tallyname = ? ORDER BY tallyname", this,tallyname);
		}
		catch (Exception e) {
			throw new TallymanDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'jobfunction = :jobfunction'.
	 */
	@Transactional
	public List<Tallyman> findWhereJobfunctionEquals(String jobfunction) throws TallymanDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tally_id, code, name, tallyname, jobfunction, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE jobfunction = ? ORDER BY jobfunction", this,jobfunction);
		}
		catch (Exception e) {
			throw new TallymanDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'corp_id = :corpId'.
	 */
	@Transactional
	public List<Tallyman> findWhereCorpIdEquals(String corpId) throws TallymanDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tally_id, code, name, tallyname, jobfunction, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE corp_id = ? ORDER BY corp_id", this,corpId);
		}
		catch (Exception e) {
			throw new TallymanDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<Tallyman> findWhereWhCodeEquals(String whCode) throws TallymanDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tally_id, code, name, tallyname, jobfunction, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new TallymanDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<Tallyman> findWhereIsActiveEquals(String isActive) throws TallymanDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tally_id, code, name, tallyname, jobfunction, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new TallymanDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<Tallyman> findWhereIsDeleteEquals(String isDelete) throws TallymanDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tally_id, code, name, tallyname, jobfunction, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new TallymanDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<Tallyman> findWhereCreatedByEquals(int createdBy) throws TallymanDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tally_id, code, name, tallyname, jobfunction, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new TallymanDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<Tallyman> findWhereCreatedDateEquals(Date createdDate) throws TallymanDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tally_id, code, name, tallyname, jobfunction, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new TallymanDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<Tallyman> findWhereUpdatedByEquals(int updatedBy) throws TallymanDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tally_id, code, name, tallyname, jobfunction, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new TallymanDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<Tallyman> findWhereUpdatedDateEquals(Date updatedDate) throws TallymanDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tally_id, code, name, tallyname, jobfunction, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new TallymanDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the tallyman table that matches the specified primary-key value.
	 */
	public Tallyman findByPrimaryKey(TallymanPk pk) throws TallymanDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

}

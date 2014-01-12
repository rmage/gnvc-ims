package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dto.Currency;
import com.app.wms.engine.db.dto.CurrencyPk;
import com.app.wms.engine.db.exceptions.CurrencyDaoException;
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

public class CurrencyDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Currency>, CurrencyDao
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
	 * @return CurrencyPk
	 */
	public CurrencyPk insert(Currency dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( currency_code, currency_name, currency_symbol, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )");
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
		su.update( new Object[] { dto.getCurrencyCode(),dto.getCurrencyName(),dto.getCurrencySymbol(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		CurrencyPk pk = new CurrencyPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the currency table.
	 */
	public void update(CurrencyPk pk, Currency dto) throws CurrencyDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET currency_code = ?, currency_name = ?, currency_symbol = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getCurrencyCode(),dto.getCurrencyName(),dto.getCurrencySymbol(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the currency table.
	 */
	@Transactional
	public void delete(CurrencyPk pk) throws CurrencyDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Currency
	 */
	public Currency mapRow(ResultSet rs, int row) throws SQLException
	{
		Currency dto = new Currency();
		dto.setId( rs.getInt( 1 ) );
		dto.setCurrencyCode( rs.getString( 2 ) );
		dto.setCurrencyName( rs.getString( 3 ) );
		dto.setCurrencySymbol( rs.getString( 4 ) );
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
		return "inventory..currency";
	}

	/** 
	 * Returns all rows from the currency table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Currency findByPrimaryKey(int id) throws CurrencyDaoException
	{
		try {
			List<Currency> list = jdbcTemplate.query("SELECT id, currency_code, currency_name, currency_symbol, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new CurrencyDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the currency table that match the criteria ''.
	 */
	@Transactional
	public List<Currency> findAll() throws CurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, currency_code, currency_name, currency_symbol, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new CurrencyDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the currency table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Currency> findWhereIdEquals(int id) throws CurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, currency_code, currency_name, currency_symbol, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new CurrencyDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the currency table that match the criteria 'currency_code = :currencyCode'.
	 */
	@Transactional
	public List<Currency> findWhereCurrencyCodeEquals(String currencyCode) throws CurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, currency_code, currency_name, currency_symbol, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE currency_code = ? ORDER BY currency_code", this,currencyCode);
		}
		catch (Exception e) {
			throw new CurrencyDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the currency table that match the criteria 'currency_name = :currencyName'.
	 */
	@Transactional
	public List<Currency> findWhereCurrencyNameEquals(String currencyName) throws CurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, currency_code, currency_name, currency_symbol, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE currency_name = ? ORDER BY currency_name", this,currencyName);
		}
		catch (Exception e) {
			throw new CurrencyDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the currency table that match the criteria 'currency_symbol = :currencySymbol'.
	 */
	@Transactional
	public List<Currency> findWhereCurrencySymbolEquals(String currencySymbol) throws CurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, currency_code, currency_name, currency_symbol, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE currency_symbol = ? ORDER BY currency_symbol", this,currencySymbol);
		}
		catch (Exception e) {
			throw new CurrencyDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the currency table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<Currency> findWhereIsActiveEquals(String isActive) throws CurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, currency_code, currency_name, currency_symbol, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new CurrencyDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the currency table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<Currency> findWhereIsDeleteEquals(String isDelete) throws CurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, currency_code, currency_name, currency_symbol, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new CurrencyDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the currency table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<Currency> findWhereCreatedByEquals(String createdBy) throws CurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, currency_code, currency_name, currency_symbol, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new CurrencyDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the currency table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<Currency> findWhereCreatedDateEquals(Date createdDate) throws CurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, currency_code, currency_name, currency_symbol, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new CurrencyDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the currency table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<Currency> findWhereUpdatedByEquals(String updatedBy) throws CurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, currency_code, currency_name, currency_symbol, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new CurrencyDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the currency table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<Currency> findWhereUpdatedDateEquals(Date updatedDate) throws CurrencyDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, currency_code, currency_name, currency_symbol, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new CurrencyDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the currency table that matches the specified primary-key value.
	 */
	public Currency findByPrimaryKey(CurrencyPk pk) throws CurrencyDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

}

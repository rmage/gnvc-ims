package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.StockOpnameDao;
import com.app.wms.engine.db.dto.StockOpname;
import com.app.wms.engine.db.dto.StockOpnamePk;
import com.app.wms.engine.db.exceptions.StockOpnameDaoException;
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

public class StockOpnameDaoImpl extends AbstractDAO implements ParameterizedRowMapper<StockOpname>, StockOpnameDao
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
	 * @return StockOpnamePk
	 */
	public StockOpnamePk insert(StockOpname dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( product_code, wh_code, quantity, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getProductCode(),dto.getWhCode(),dto.getQuantity(),dto.getBalance(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		StockOpnamePk pk = new StockOpnamePk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the stock_opname table.
	 */
	public void update(StockOpnamePk pk, StockOpname dto) throws StockOpnameDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET product_code = ?, wh_code = ?, quantity = ?, balance = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getProductCode(),dto.getWhCode(),dto.getQuantity(),dto.getBalance(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the stock_opname table.
	 */
	@Transactional
	public void delete(StockOpnamePk pk) throws StockOpnameDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return StockOpname
	 */
	public StockOpname mapRow(ResultSet rs, int row) throws SQLException
	{
		StockOpname dto = new StockOpname();
		dto.setId( rs.getInt( 1 ) );
		dto.setProductCode( rs.getString( 2 ) );
		dto.setWhCode( rs.getString( 3 ) );
		dto.setQuantity( rs.getInt( 4 ) );
		if (rs.wasNull()) {
			dto.setQuantityNull( true );
		}
		
		dto.setBalance( rs.getInt( 5 ) );
		if (rs.wasNull()) {
			dto.setBalanceNull( true );
		}
		
		dto.setIsActive( rs.getString( 6 ) );
		dto.setIsDelete( rs.getString( 7 ) );
		dto.setCreatedBy( rs.getString( 8 ) );
		dto.setCreatedDate( rs.getTimestamp(9 ) );
		dto.setUpdatedBy( rs.getString( 10 ) );
		dto.setUpdatedDate( rs.getTimestamp(11 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..stock_opname";
	}

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'id = :id'.
	 */
	@Transactional
	public StockOpname findByPrimaryKey(int id) throws StockOpnameDaoException
	{
		try {
			List<StockOpname> list = jdbcTemplate.query("SELECT id, product_code, wh_code, quantity, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new StockOpnameDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_opname table that match the criteria ''.
	 */
	@Transactional
	public List<StockOpname> findAll() throws StockOpnameDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, quantity, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new StockOpnameDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<StockOpname> findWhereIdEquals(int id) throws StockOpnameDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, quantity, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new StockOpnameDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'product_code = :productCode'.
	 */
	@Transactional
	public List<StockOpname> findWhereProductCodeEquals(String productCode) throws StockOpnameDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, quantity, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE product_code = ? ORDER BY product_code", this,productCode);
		}
		catch (Exception e) {
			throw new StockOpnameDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<StockOpname> findWhereWhCodeEquals(String whCode) throws StockOpnameDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, quantity, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new StockOpnameDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'quantity = :quantity'.
	 */
	@Transactional
	public List<StockOpname> findWhereQuantityEquals(int quantity) throws StockOpnameDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, quantity, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE quantity = ? ORDER BY quantity", this,quantity);
		}
		catch (Exception e) {
			throw new StockOpnameDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'balance = :balance'.
	 */
	@Transactional
	public List<StockOpname> findWhereBalanceEquals(int balance) throws StockOpnameDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, quantity, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE balance = ? ORDER BY balance", this,balance);
		}
		catch (Exception e) {
			throw new StockOpnameDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<StockOpname> findWhereIsActiveEquals(String isActive) throws StockOpnameDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, quantity, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new StockOpnameDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<StockOpname> findWhereIsDeleteEquals(String isDelete) throws StockOpnameDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, quantity, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new StockOpnameDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<StockOpname> findWhereCreatedByEquals(String createdBy) throws StockOpnameDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, quantity, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new StockOpnameDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<StockOpname> findWhereCreatedDateEquals(Date createdDate) throws StockOpnameDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, quantity, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new StockOpnameDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<StockOpname> findWhereUpdatedByEquals(String updatedBy) throws StockOpnameDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, quantity, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new StockOpnameDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_opname table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<StockOpname> findWhereUpdatedDateEquals(Date updatedDate) throws StockOpnameDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, quantity, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new StockOpnameDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the stock_opname table that matches the specified primary-key value.
	 */
	public StockOpname findByPrimaryKey(StockOpnamePk pk) throws StockOpnameDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

}

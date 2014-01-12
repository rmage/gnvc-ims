package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.StockBalanceDao;
import com.app.wms.engine.db.dto.StockBalance;
import com.app.wms.engine.db.dto.StockBalancePk;
import com.app.wms.engine.db.exceptions.StockBalanceDaoException;
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

public class StockBalanceDaoImpl extends AbstractDAO implements ParameterizedRowMapper<StockBalance>, StockBalanceDao
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
	 * @return StockBalancePk
	 */
	public StockBalancePk insert(StockBalance dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( document_no, document_date, product_code, product_name, qty_in, qty_out, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getDocumentNo(),dto.getDocumentDate(),dto.getProductCode(),dto.getProductName(),dto.getQtyIn(),dto.getQtyOut(),dto.getBalance(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		StockBalancePk pk = new StockBalancePk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the stock_balance table.
	 */
	public void update(StockBalancePk pk, StockBalance dto) throws StockBalanceDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET document_no = ?, document_date = ?, product_code = ?, product_name = ?, qty_in = ?, qty_out = ?, balance = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getDocumentNo(),dto.getDocumentDate(),dto.getProductCode(),dto.getProductName(),dto.getQtyIn(),dto.getQtyOut(),dto.getBalance(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the stock_balance table.
	 */
	@Transactional
	public void delete(StockBalancePk pk) throws StockBalanceDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return StockBalance
	 */
	public StockBalance mapRow(ResultSet rs, int row) throws SQLException
	{
		StockBalance dto = new StockBalance();
		dto.setId( rs.getInt( 1 ) );
		dto.setDocumentNo( rs.getString( 2 ) );
		dto.setDocumentDate( rs.getTimestamp(3 ) );
		dto.setProductCode( rs.getString( 4 ) );
		dto.setProductName( rs.getString( 5 ) );
		dto.setQtyIn( rs.getBigDecimal( 6 ) );
		if (rs.wasNull()) {
			dto.setQtyInNull( true );
		}
		
		dto.setQtyOut( rs.getBigDecimal( 7 ) );
		if (rs.wasNull()) {
			dto.setQtyOutNull( true );
		}
		
		dto.setBalance( rs.getBigDecimal( 8 ) );
		if (rs.wasNull()) {
			dto.setBalanceNull( true );
		}
		
		dto.setIsActive( rs.getString( 9 ) );
		dto.setIsDelete( rs.getString( 10 ) );
		dto.setCreatedBy( rs.getString( 11 ) );
		dto.setCreatedDate( rs.getTimestamp(12 ) );
		dto.setUpdatedBy( rs.getString( 13 ) );
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
		return "inventory..stock_balance";
	}

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'id = :id'.
	 */
	@Transactional
	public StockBalance findByPrimaryKey(int id) throws StockBalanceDaoException
	{
		try {
			List<StockBalance> list = jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new StockBalanceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_balance table that match the criteria ''.
	 */
	@Transactional
	public List<StockBalance> findAll() throws StockBalanceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new StockBalanceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<StockBalance> findWhereIdEquals(int id) throws StockBalanceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new StockBalanceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'document_no = :documentNo'.
	 */
	@Transactional
	public List<StockBalance> findWhereDocumentNoEquals(String documentNo) throws StockBalanceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE document_no = ? ORDER BY document_no", this,documentNo);
		}
		catch (Exception e) {
			throw new StockBalanceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'document_date = :documentDate'.
	 */
	@Transactional
	public List<StockBalance> findWhereDocumentDateEquals(Date documentDate) throws StockBalanceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE document_date = ? ORDER BY document_date", this,documentDate);
		}
		catch (Exception e) {
			throw new StockBalanceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'product_code = :productCode'.
	 */
	@Transactional
	public List<StockBalance> findWhereProductCodeEquals(String productCode) throws StockBalanceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE product_code = ? ORDER BY product_code", this,productCode);
		}
		catch (Exception e) {
			throw new StockBalanceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'product_name = :productName'.
	 */
	@Transactional
	public List<StockBalance> findWhereProductNameEquals(String productName) throws StockBalanceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE product_name = ? ORDER BY product_name", this,productName);
		}
		catch (Exception e) {
			throw new StockBalanceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'qty_in = :qtyIn'.
	 */
	@Transactional
	public List<StockBalance> findWhereQtyInEquals(float qtyIn) throws StockBalanceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE qty_in = ? ORDER BY qty_in", this,qtyIn);
		}
		catch (Exception e) {
			throw new StockBalanceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'qty_out = :qtyOut'.
	 */
	@Transactional
	public List<StockBalance> findWhereQtyOutEquals(float qtyOut) throws StockBalanceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE qty_out = ? ORDER BY qty_out", this,qtyOut);
		}
		catch (Exception e) {
			throw new StockBalanceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'balance = :balance'.
	 */
	@Transactional
	public List<StockBalance> findWhereBalanceEquals(float balance) throws StockBalanceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE balance = ? ORDER BY balance", this,balance);
		}
		catch (Exception e) {
			throw new StockBalanceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<StockBalance> findWhereIsActiveEquals(String isActive) throws StockBalanceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new StockBalanceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<StockBalance> findWhereIsDeleteEquals(String isDelete) throws StockBalanceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new StockBalanceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<StockBalance> findWhereCreatedByEquals(String createdBy) throws StockBalanceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new StockBalanceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<StockBalance> findWhereCreatedDateEquals(Date createdDate) throws StockBalanceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new StockBalanceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<StockBalance> findWhereUpdatedByEquals(String updatedBy) throws StockBalanceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new StockBalanceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_balance table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<StockBalance> findWhereUpdatedDateEquals(Date updatedDate) throws StockBalanceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new StockBalanceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the stock_balance table that matches the specified primary-key value.
	 */
	public StockBalance findByPrimaryKey(StockBalancePk pk) throws StockBalanceDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

}

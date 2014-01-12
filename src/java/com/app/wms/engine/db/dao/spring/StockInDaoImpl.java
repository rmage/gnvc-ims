package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.StockInDao;
import com.app.wms.engine.db.dto.StockIn;
import com.app.wms.engine.db.dto.StockInPk;
import com.app.wms.engine.db.exceptions.StockInDaoException;
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

public class StockInDaoImpl extends AbstractDAO implements ParameterizedRowMapper<StockIn>, StockInDao
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
	 * @return StockInPk
	 */
	public StockInPk insert(StockIn dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( document_no, document_date, product_code, product_name, quantity, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getDocumentNo(),dto.getDocumentDate(),dto.getProductCode(),dto.getProductName(),dto.getQuantity(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		StockInPk pk = new StockInPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the stock_in table.
	 */
	public void update(StockInPk pk, StockIn dto) throws StockInDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET document_no = ?, document_date = ?, product_code = ?, product_name = ?, quantity = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getDocumentNo(),dto.getDocumentDate(),dto.getProductCode(),dto.getProductName(),dto.getQuantity(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the stock_in table.
	 */
	@Transactional
	public void delete(StockInPk pk) throws StockInDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return StockIn
	 */
	public StockIn mapRow(ResultSet rs, int row) throws SQLException
	{
		StockIn dto = new StockIn();
		dto.setId( rs.getInt( 1 ) );
		dto.setDocumentNo( rs.getString( 2 ) );
		dto.setDocumentDate( rs.getTimestamp(3 ) );
		dto.setProductCode( rs.getString( 4 ) );
		dto.setProductName( rs.getString( 5 ) );
		dto.setQuantity( rs.getInt( 6 ) );
		if (rs.wasNull()) {
			dto.setQuantityNull( true );
		}
		
		dto.setIsActive( rs.getString( 7 ) );
		dto.setIsDelete( rs.getString( 8 ) );
		dto.setCreatedBy( rs.getString( 9 ) );
		dto.setCreatedDate( rs.getTimestamp(10 ) );
		dto.setUpdatedBy( rs.getString( 11 ) );
		dto.setUpdatedDate( rs.getTimestamp(12 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..stock_in";
	}

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'id = :id'.
	 */
	@Transactional
	public StockIn findByPrimaryKey(int id) throws StockInDaoException
	{
		try {
			List<StockIn> list = jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, quantity, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new StockInDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_in table that match the criteria ''.
	 */
	@Transactional
	public List<StockIn> findAll() throws StockInDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, quantity, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new StockInDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<StockIn> findWhereIdEquals(int id) throws StockInDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, quantity, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new StockInDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'document_no = :documentNo'.
	 */
	@Transactional
	public List<StockIn> findWhereDocumentNoEquals(String documentNo) throws StockInDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, quantity, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE document_no = ? ORDER BY document_no", this,documentNo);
		}
		catch (Exception e) {
			throw new StockInDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'document_date = :documentDate'.
	 */
	@Transactional
	public List<StockIn> findWhereDocumentDateEquals(Date documentDate) throws StockInDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, quantity, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE document_date = ? ORDER BY document_date", this,documentDate);
		}
		catch (Exception e) {
			throw new StockInDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'product_code = :productCode'.
	 */
	@Transactional
	public List<StockIn> findWhereProductCodeEquals(String productCode) throws StockInDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, quantity, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE product_code = ? ORDER BY product_code", this,productCode);
		}
		catch (Exception e) {
			throw new StockInDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'product_name = :productName'.
	 */
	@Transactional
	public List<StockIn> findWhereProductNameEquals(String productName) throws StockInDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, quantity, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE product_name = ? ORDER BY product_name", this,productName);
		}
		catch (Exception e) {
			throw new StockInDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'quantity = :quantity'.
	 */
	@Transactional
	public List<StockIn> findWhereQuantityEquals(int quantity) throws StockInDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, quantity, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE quantity = ? ORDER BY quantity", this,quantity);
		}
		catch (Exception e) {
			throw new StockInDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<StockIn> findWhereIsActiveEquals(String isActive) throws StockInDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, quantity, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new StockInDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<StockIn> findWhereIsDeleteEquals(String isDelete) throws StockInDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, quantity, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new StockInDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<StockIn> findWhereCreatedByEquals(String createdBy) throws StockInDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, quantity, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new StockInDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<StockIn> findWhereCreatedDateEquals(Date createdDate) throws StockInDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, quantity, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new StockInDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<StockIn> findWhereUpdatedByEquals(String updatedBy) throws StockInDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, quantity, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new StockInDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_in table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<StockIn> findWhereUpdatedDateEquals(Date updatedDate) throws StockInDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, document_no, document_date, product_code, product_name, quantity, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new StockInDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the stock_in table that matches the specified primary-key value.
	 */
	public StockIn findByPrimaryKey(StockInPk pk) throws StockInDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

}

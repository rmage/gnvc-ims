package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.WarehouseDao;
import com.app.wms.engine.db.dto.Warehouse;
import com.app.wms.engine.db.dto.WarehousePk;
import com.app.wms.engine.db.exceptions.WarehouseDaoException;
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

public class WarehouseDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Warehouse>, WarehouseDao
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
	 * @return WarehousePk
	 */
	public WarehousePk insert(Warehouse dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( wh_code, wh_name, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )");
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
		su.update( new Object[] { dto.getWhCode(),dto.getWhName(),dto.getCategoryName(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		WarehousePk pk = new WarehousePk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the warehouse table.
	 */
	public void update(WarehousePk pk, Warehouse dto) throws WarehouseDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET wh_code = ?, wh_name = ?, category_name = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
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
		su.update( new Object[] { dto.getWhCode(),dto.getWhName(),dto.getCategoryName(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the warehouse table.
	 */
	@Transactional
	public void delete(WarehousePk pk) throws WarehouseDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Warehouse
	 */
	public Warehouse mapRow(ResultSet rs, int row) throws SQLException
	{
		Warehouse dto = new Warehouse();
		dto.setId( rs.getInt( 1 ) );
		dto.setWhCode( rs.getString( 2 ) );
		dto.setWhName( rs.getString( 3 ) );
		dto.setCategoryName( rs.getString( 4 ) );
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
		return "warehouse";
	}

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Warehouse findByPrimaryKey(int id) throws WarehouseDaoException
	{
		try {
			List<Warehouse> list = jdbcTemplate.query("SELECT id, wh_code, wh_name, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new WarehouseDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the warehouse table that match the criteria ''.
	 */
	@Transactional
	public List<Warehouse> findAll() throws WarehouseDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, wh_name, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new WarehouseDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Warehouse> findWhereIdEquals(int id) throws WarehouseDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, wh_name, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new WarehouseDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<Warehouse> findWhereWhCodeEquals(String whCode) throws WarehouseDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, wh_name, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new WarehouseDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'wh_name = :whName'.
	 */
	@Transactional
	public List<Warehouse> findWhereWhNameEquals(String whName) throws WarehouseDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, wh_name, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE wh_name = ? ORDER BY wh_name", this,whName);
		}
		catch (Exception e) {
			throw new WarehouseDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'category_name = :categoryName'.
	 */
	@Transactional
	public List<Warehouse> findWhereCategoryNameEquals(String categoryName) throws WarehouseDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, wh_name, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE category_name = ? ORDER BY category_name", this,categoryName);
		}
		catch (Exception e) {
			throw new WarehouseDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<Warehouse> findWhereIsActiveEquals(String isActive) throws WarehouseDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, wh_name, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new WarehouseDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<Warehouse> findWhereIsDeleteEquals(String isDelete) throws WarehouseDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, wh_name, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new WarehouseDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<Warehouse> findWhereCreatedByEquals(String createdBy) throws WarehouseDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, wh_name, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new WarehouseDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<Warehouse> findWhereCreatedDateEquals(Date createdDate) throws WarehouseDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, wh_name, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new WarehouseDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<Warehouse> findWhereUpdatedByEquals(String updatedBy) throws WarehouseDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, wh_name, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new WarehouseDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the warehouse table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<Warehouse> findWhereUpdatedDateEquals(Date updatedDate) throws WarehouseDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, wh_name, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new WarehouseDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the warehouse table that matches the specified primary-key value.
	 */
	public Warehouse findByPrimaryKey(WarehousePk pk) throws WarehouseDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

}

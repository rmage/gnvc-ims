package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.PriceCatalogDao;
import com.app.wms.engine.db.dto.PriceCatalog;
import com.app.wms.engine.db.dto.PriceCatalogPk;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.map.PriceCatalogListMap;
import com.app.wms.engine.db.dto.map.SupplierListMap;
import com.app.wms.engine.db.exceptions.PriceCatalogDaoException;
import com.app.wms.engine.db.exceptions.SupplierDaoException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class PriceCatalogDaoImpl extends AbstractDAO implements ParameterizedRowMapper<PriceCatalog>, PriceCatalogDao
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
	 * @return PriceCatalogPk
	 */
	public PriceCatalogPk insert(PriceCatalog dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( catalog_code, catalog_name, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getCatalogCode(),dto.getCatalogName(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		PriceCatalogPk pk = new PriceCatalogPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the price_catalog table.
	 */
	public void update(PriceCatalogPk pk, PriceCatalog dto) throws PriceCatalogDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET catalog_code = ?, catalog_name = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
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
		su.update( new Object[] { dto.getCatalogCode(),dto.getCatalogName(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the price_catalog table.
	 */
	@Transactional
	public void delete(PriceCatalogPk pk) throws PriceCatalogDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return PriceCatalog
	 */
	public PriceCatalog mapRow(ResultSet rs, int row) throws SQLException
	{
		PriceCatalog dto = new PriceCatalog();
		dto.setId( rs.getInt( 1 ) );
		dto.setCatalogCode( rs.getString( 2 ) );
		dto.setCatalogName( rs.getString( 3 ) );
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
		return "price_catalog";
	}

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'id = :id'.
	 */
	@Transactional
	public PriceCatalog findByPrimaryKey(int id) throws PriceCatalogDaoException
	{
		try {
			List<PriceCatalog> list = jdbcTemplate.query("SELECT id, catalog_code, catalog_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new PriceCatalogDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the price_catalog table that match the criteria ''.
	 */
	@Transactional
	public List<PriceCatalog> findAll() throws PriceCatalogDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, catalog_code, catalog_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new PriceCatalogDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<PriceCatalog> findWhereIdEquals(int id) throws PriceCatalogDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, catalog_code, catalog_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new PriceCatalogDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'catalog_code = :catalogCode'.
	 */
	@Transactional
	public List<PriceCatalog> findWhereCatalogCodeEquals(String catalogCode) throws PriceCatalogDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, catalog_code, catalog_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE catalog_code = ? ORDER BY catalog_code", this,catalogCode);
		}
		catch (Exception e) {
			throw new PriceCatalogDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'catalog_name = :catalogName'.
	 */
	@Transactional
	public List<PriceCatalog> findWhereCatalogNameEquals(String catalogName) throws PriceCatalogDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, catalog_code, catalog_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE catalog_name = ? ORDER BY catalog_name", this,catalogName);
		}
		catch (Exception e) {
			throw new PriceCatalogDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<PriceCatalog> findWhereIsActiveEquals(String isActive) throws PriceCatalogDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, catalog_code, catalog_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new PriceCatalogDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<PriceCatalog> findWhereIsDeleteEquals(String isDelete) throws PriceCatalogDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, catalog_code, catalog_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new PriceCatalogDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<PriceCatalog> findWhereCreatedByEquals(String createdBy) throws PriceCatalogDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, catalog_code, catalog_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new PriceCatalogDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<PriceCatalog> findWhereCreatedDateEquals(Date createdDate) throws PriceCatalogDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, catalog_code, catalog_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new PriceCatalogDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<PriceCatalog> findWhereUpdatedByEquals(String updatedBy) throws PriceCatalogDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, catalog_code, catalog_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new PriceCatalogDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the price_catalog table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<PriceCatalog> findWhereUpdatedDateEquals(Date updatedDate) throws PriceCatalogDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, catalog_code, catalog_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new PriceCatalogDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the price_catalog table that matches the specified primary-key value.
	 */
	public PriceCatalog findByPrimaryKey(PriceCatalogPk pk) throws PriceCatalogDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

	public List<PriceCatalog> findPriceCatalogPaging(PriceCatalog p, int page) throws PriceCatalogDaoException {
		try {
        	String catalogCode = p.getCatalogCode();
        	String catalogName = p.getCatalogName();
        	
        	int i = page;
        	Map map = new HashMap();
        	map.put("i", i);
        
        	StringBuffer sb = new StringBuffer();
        	
        	if(p == null){
        		p = new PriceCatalog();
        	}
        	
        	if(catalogCode == null || catalogName == null){
        		catalogCode = "%";
        		catalogName = "%";
        		
        		sb.append("declare @Page int, @PageSize int "
        				+"set @Page = '"+i+"'; "
        				+"set @PageSize = 10; "
        				+"with PagedResult "
        				+"as (select ROW_NUMBER() over (order by id asc) as id, catalog_code, catalog_name, is_active from price_catalog" +
        						" where catalog_code like '%"+catalogCode+"%' and catalog_name like '%"+catalogName+"%' ) "
        				    +"select * from PagedResult where id between "
        				+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
        				     +"else @Page end and @PageSize * @Page ");
        		
        	}else{
        		
        		sb.append("declare @Page int, @PageSize int "
        				+"set @Page = '"+i+"'; "
        				+"set @PageSize = 10; "
        				+"with PagedResult "
        				+"as (select ROW_NUMBER() over (order by id asc) as id, catalog_code, catalog_name, is_active from supplier" +
        						" where catalog_code like '%"+catalogCode+"%' and catalog_name like '%"+catalogName+"%' ) "
        				    +"select * from PagedResult where id between "
        				+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
        				     +"else @Page end and @PageSize * @Page ");
        		
        	}
        	
        	
        	
        	return jdbcTemplate.query(sb.toString(),new PriceCatalogListMap(),map);	
        
        } catch (Exception e) {
            throw new PriceCatalogDaoException("Query failed", e);
        }

    }

	

}

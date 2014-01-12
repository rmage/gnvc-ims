package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.BundleDao;
import com.app.wms.engine.db.dto.Bundle;
import com.app.wms.engine.db.dto.BundlePk;
import com.app.wms.engine.db.dto.map.BundleListMap;
import com.app.wms.engine.db.dto.map.ProductListMap;
import com.app.wms.engine.db.exceptions.BundleDaoException;
import com.app.wms.engine.db.exceptions.ProductDaoException;

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

public class BundleDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Bundle>, BundleDao
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
	 * @return BundlePk
	 */
	public BundlePk insert(Bundle dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( bundle_code, bundle_name, created_by, created_date, updated_by, updated_date, user_id, wh_code, corp_id ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getBundleCode(),dto.getBundleName(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),dto.getUserId(),dto.getWhCode(),dto.getCorpId()} );
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the bundle table.
	 */
	public void update(BundlePk pk, Bundle dto) throws BundleDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET bundle_code = ?, bundle_name = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ?, user_id = ?, wh_code = ?, corp_id = ? WHERE bundle_code = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getBundleCode(),dto.getBundleName(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(), dto.getUserId(),dto.getWhCode(),dto.getCorpId(),pk.getBundleCode() } );
	}

	/** 
	 * Deletes a single row in the bundle table.
	 */
	@Transactional
	public void delete(BundlePk pk) throws BundleDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE bundle_code = ?",pk.getBundleCode());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Bundle
	 */
	public Bundle mapRow(ResultSet rs, int row) throws SQLException
	{
		Bundle dto = new Bundle();
		dto.setBundleCode( rs.getString( 1 ) );
		dto.setBundleName( rs.getString( 2 ) );
		dto.setCreatedBy( rs.getString( 3 ) );
		dto.setCreatedDate( rs.getTimestamp(4 ) );
		dto.setUpdatedBy( rs.getString( 5 ) );
		dto.setUpdatedDate( rs.getTimestamp(6 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..bundle";
	}

	/** 
	 * Returns all rows from the bundle table that match the criteria 'bundle_code = :bundleCode'.
	 */
	@Transactional
	public Bundle findByPrimaryKey(String bundleCode) throws BundleDaoException
	{
		try {
			List<Bundle> list = jdbcTemplate.query("SELECT bundle_code, bundle_name, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE bundle_code = ?", this,bundleCode);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new BundleDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bundle table that match the criteria ''.
	 */
	@Transactional
	public List<Bundle> findAll() throws BundleDaoException
	{
		try {
			return jdbcTemplate.query("SELECT bundle_code, bundle_name, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY bundle_code", this);
		}
		catch (Exception e) {
			throw new BundleDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bundle table that match the criteria 'bundle_code = :bundleCode'.
	 */
	@Transactional
	public List<Bundle> findWhereBundleCodeEquals(String bundleCode) throws BundleDaoException
	{
		try {
			return jdbcTemplate.query("SELECT bundle_code, bundle_name, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE bundle_code = ? ORDER BY bundle_code", this,bundleCode);
		}
		catch (Exception e) {
			throw new BundleDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bundle table that match the criteria 'bundle_name = :bundleName'.
	 */
	@Transactional
	public List<Bundle> findWhereBundleNameEquals(String bundleName) throws BundleDaoException
	{
		try {
			return jdbcTemplate.query("SELECT bundle_code, bundle_name, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE bundle_name = ? ORDER BY bundle_name", this,bundleName);
		}
		catch (Exception e) {
			throw new BundleDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bundle table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<Bundle> findWhereCreatedByEquals(String createdBy) throws BundleDaoException
	{
		try {
			return jdbcTemplate.query("SELECT bundle_code, bundle_name, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new BundleDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bundle table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<Bundle> findWhereCreatedDateEquals(Date createdDate) throws BundleDaoException
	{
		try {
			return jdbcTemplate.query("SELECT bundle_code, bundle_name, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new BundleDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bundle table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<Bundle> findWhereUpdatedByEquals(String updatedBy) throws BundleDaoException
	{
		try {
			return jdbcTemplate.query("SELECT bundle_code, bundle_name, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new BundleDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bundle table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<Bundle> findWhereUpdatedDateEquals(Date updatedDate) throws BundleDaoException
	{
		try {
			return jdbcTemplate.query("SELECT bundle_code, bundle_name, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new BundleDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the bundle table that matches the specified primary-key value.
	 */
	public Bundle findByPrimaryKey(BundlePk pk) throws BundleDaoException
	{
		return findByPrimaryKey( pk.getBundleCode() );
	}

	@Override
	public List<Bundle> findBundlingPaging(Bundle b, int page)throws BundleDaoException {
		try {
        	String corpId = b.getCorpId();
        	String whCode = b.getWhCode();
        	
        	String bundleCode = b.getBundleCode();
        	String bundleName = b.getBundleName();
        	
        	int i = page;
        	Map map = new HashMap();
        	map.put("corpId", corpId);
        	map.put("whCode", whCode);
        	map.put("i", i);
        
        	StringBuffer sb = new StringBuffer();
        	
        	if(b == null){
        		b = new Bundle();
        	}
        	
        	if( bundleCode == null || bundleName == null){
        		
        		bundleCode = "%";
        		bundleName = "%";
        		
        		sb.append("declare @Page int, @PageSize int "
        				+"set @Page = '"+i+"'; "
        				+"set @PageSize = 10; "
        				+"with PagedResult "
        				+"as (select ROW_NUMBER() over (order by bundle_code desc) as id,bundle_code, bundle_name from bundle " +
        						" where corp_id like '"+corpId+"' and wh_code like '"+whCode+"' and bundle_code like '%"+bundleCode+"%' and bundle_name like '%"+bundleName+"%' ) "
        				    +"select * from PagedResult where id between "
        				+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
        				     +"else @Page end and @PageSize * @Page ");
        		
        	}else {
        		
        		sb.append("declare @Page int, @PageSize int "
        				+"set @Page = '"+i+"'; "
        				+"set @PageSize = 10; "
        				+"with PagedResult "
        				+"as (select ROW_NUMBER() over (order by bundle_code desc) as id,bundle_code, bundle_name from bundle " +
        						" where corp_id like '"+corpId+"' and wh_code like '"+whCode+"' and bundle_code like '%"+bundleCode+"%' and bundle_name like '%"+bundleName+"%' ) "
        				    +"select * from PagedResult where id between "
        				+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
        				     +"else @Page end and @PageSize * @Page ");
        		
        	}
        	
        	
        	return jdbcTemplate.query(sb.toString(),new BundleListMap(),map);	
        
        } catch (Exception e) {
            throw new BundleDaoException("Query failed", e);
        }

    }

	@Override
	public List<Bundle> findBundling(Bundle b) throws BundleDaoException {
		try {
        	String corpId = b.getCorpId();
        	String whCode = b.getWhCode();
        	Map map = new HashMap();
        	map.put("corpId", corpId);
        	map.put("whCode", whCode);
        
        	StringBuffer sb = new StringBuffer();
        	
        	sb.append("select ROW_NUMBER() over (order by bundle_code desc) as id, bundle_code, bundle_name from bundle " +
						" where corp_id like '"+corpId+"' and wh_code like '"+whCode+"' ");
        	
        	return jdbcTemplate.query(sb.toString(),new BundleListMap(),map);	
        
        } catch (Exception e) {
            throw new BundleDaoException("Query failed", e);
        }
	}
	

}

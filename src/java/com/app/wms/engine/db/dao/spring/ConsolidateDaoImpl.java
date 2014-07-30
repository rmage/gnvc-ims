package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.ConsolidateDao;
import com.app.wms.engine.db.dto.Consolidate;
import com.app.wms.engine.db.dto.ConsolidatePk;
import com.app.wms.engine.db.dto.map.ConsolidateListMap;
import com.app.wms.engine.db.exceptions.ConsolidateDaoException;

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

public class ConsolidateDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Consolidate>, ConsolidateDao
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
	 * @return ConsolidatePk
	 */
	public ConsolidatePk insert(Consolidate dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( consolidate_no, consolidate_date, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getConsolidateNo(),dto.getConsolidateDate(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the consolidate table.
	 */
	public void update(ConsolidatePk pk, Consolidate dto) throws ConsolidateDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET consolidate_no = ?, consolidate_date = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE consolidate_no = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getConsolidateNo(),dto.getConsolidateDate(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getConsolidateNo() } );
	}

	/** 
	 * Deletes a single row in the consolidate table.
	 */
	@Transactional
	public void delete(ConsolidatePk pk) throws ConsolidateDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE consolidate_no = ?",pk.getConsolidateNo());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Consolidate
	 */
	public Consolidate mapRow(ResultSet rs, int row) throws SQLException
	{
		Consolidate dto = new Consolidate();
		dto.setId(rs.getInt(1));
		dto.setConsolidateNo( rs.getString( 2 ) );
		dto.setConsolidateDate( rs.getTimestamp(3 ) );
		dto.setCreatedBy( rs.getString( 4 ) );
		dto.setCreatedDate( rs.getTimestamp(5 ) );
		dto.setUpdatedBy( rs.getString( 6 ) );
		dto.setUpdatedDate( rs.getTimestamp(7 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "consolidate";
	}

	/** 
	 * Returns all rows from the consolidate table that match the criteria 'consolidate_no = :consolidateNo'.
	 */
	@Transactional
	public Consolidate findByPrimaryKey(String consolidateNo) throws ConsolidateDaoException
	{
		try {
			List<Consolidate> list = jdbcTemplate.query("SELECT ROW_NUMBER() over (order by consolidate_no asc) as id, consolidate_no, consolidate_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE consolidate_no = ?", this,consolidateNo);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new ConsolidateDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consolidate table that match the criteria ''.
	 */
	@Transactional
	public List<Consolidate> findAll() throws ConsolidateDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, consolidate_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY consolidate_no", this);
		}
		catch (Exception e) {
			throw new ConsolidateDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consolidate table that match the criteria 'consolidate_no = :consolidateNo'.
	 */
	@Transactional
	public List<Consolidate> findWhereConsolidateNoEquals(String consolidateNo) throws ConsolidateDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, consolidate_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE consolidate_no = ? ORDER BY consolidate_no", this,consolidateNo);
		}
		catch (Exception e) {
			throw new ConsolidateDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consolidate table that match the criteria 'consolidate_date = :consolidateDate'.
	 */
	@Transactional
	public List<Consolidate> findWhereConsolidateDateEquals(Date consolidateDate) throws ConsolidateDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, consolidate_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE consolidate_date = ? ORDER BY consolidate_date", this,consolidateDate);
		}
		catch (Exception e) {
			throw new ConsolidateDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consolidate table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<Consolidate> findWhereCreatedByEquals(String createdBy) throws ConsolidateDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, consolidate_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new ConsolidateDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consolidate table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<Consolidate> findWhereCreatedDateEquals(Date createdDate) throws ConsolidateDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, consolidate_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new ConsolidateDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consolidate table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<Consolidate> findWhereUpdatedByEquals(String updatedBy) throws ConsolidateDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, consolidate_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new ConsolidateDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consolidate table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<Consolidate> findWhereUpdatedDateEquals(Date updatedDate) throws ConsolidateDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, consolidate_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new ConsolidateDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the consolidate table that matches the specified primary-key value.
	 */
	public Consolidate findByPrimaryKey(ConsolidatePk pk) throws ConsolidateDaoException
	{
		return findByPrimaryKey( pk.getConsolidateNo() );
	}

	@Override
	public List<Consolidate> findConsolidatePaging(Consolidate c,int page) throws ConsolidateDaoException {
		try {
			String corpId = c.getCorpId();
        	String whCode = c.getWhCode();
        	int i = page;
        	Map map = new HashMap();
        	map.put("corpId", corpId);
        	map.put("whCode", whCode);
        	map.put("i", i);
        
        	StringBuffer sb = new StringBuffer();
        	
        	sb.append("declare @Page int, @PageSize int "
        			+"set @Page = '"+i+"'; "
        			+"set @PageSize = 10; " 
        			+"with PagedResult " 
        			+"as (select a.consolidate_no, a.consolidate_date, ROW_NUMBER() over (order by consolidate_date desc) as id from consolidate a "
        			+"inner join consolidate_detail b on a.consolidate_no = b.consolidate_no where b.corp_id like '"+corpId+"' and b.wh_code like '"+whCode+"' "
        			+"group by a.consolidate_no, a.consolidate_date) " 
        			+"select * from PagedResult where id between " 
        			+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 " 
        			+"else @Page end and @PageSize * @Page " 
        			+"order by consolidate_date desc ");
        	
        	return jdbcTemplate.query(sb.toString(),new ConsolidateListMap(),map);
        
        } catch (Exception e) {
            throw new ConsolidateDaoException("Query failed", e);
        }
	}

}

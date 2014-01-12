package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.WhLocatingAreaDao;
import com.app.wms.engine.db.dto.WhLocatingArea;
import com.app.wms.engine.db.dto.WhLocatingAreaPk;
import com.app.wms.engine.db.dto.map.ConsigneeListMap;
import com.app.wms.engine.db.dto.map.WhLocatingAreaListMap;
import com.app.wms.engine.db.exceptions.ConsigneeDaoException;
import com.app.wms.engine.db.exceptions.WhLocatingAreaDaoException;

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

public class WhLocatingAreaDaoImpl extends AbstractDAO implements ParameterizedRowMapper<WhLocatingArea>, WhLocatingAreaDao
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
	 * @return WhLocatingAreaPk
	 */
	public WhLocatingAreaPk insert(WhLocatingArea dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( locating_area, locating_condition, user_id, corp_id, wh_code ) VALUES ( ?, ?, ?, ?, ? )");
//		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getLocatingArea(),dto.getLocatingCondition(), dto.getUserId(), dto.getCorpId(), dto.getWhCode()} );
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the wh_locating_area table.
	 */
	public void update(WhLocatingAreaPk pk, WhLocatingArea dto) throws WhLocatingAreaDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET locating_area = ?, locating_condition = ?, user_id = ?, corp_id = ?, wh_code = ? WHERE locating_id = ?");
//		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getLocatingArea(),dto.getLocatingCondition(), dto.getUserId(), dto.getCorpId(), dto.getWhCode(), pk.getLocatingId() } );
	}

	/** 
	 * Deletes a single row in the wh_locating_area table.
	 */
	@Transactional
	public void delete(WhLocatingAreaPk pk) throws WhLocatingAreaDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getLocatingId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return WhLocatingArea
	 */
	public WhLocatingArea mapRow(ResultSet rs, int row) throws SQLException
	{
		WhLocatingArea dto = new WhLocatingArea();
//		dto.setId( rs.getInt( 1 ) );
		dto.setLocatingId( rs.getString( 1 ) );
		dto.setLocatingArea( rs.getString( 2 ) );
		dto.setLocatingCondition( rs.getString( 3 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..wh_locating_area";
	}

	/** 
	 * Returns all rows from the wh_locating_area table that match the criteria 'id = :id'.
	 */
	@Transactional
	public WhLocatingArea findByPrimaryKey(int id) throws WhLocatingAreaDaoException
	{
		try {
			List<WhLocatingArea> list = jdbcTemplate.query("SELECT locating_area, locating_condition FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new WhLocatingAreaDaoException("Query failed", e);
		}
		
	}
	
	/** 
	 * Returns all rows from the wh_locating_area table that match the criteria 'id = :id'.
	 */
	@Transactional
	public WhLocatingArea findByPrimaryKey(String locatingId) throws WhLocatingAreaDaoException
	{
		try {
			List<WhLocatingArea> list = jdbcTemplate.query("SELECT locating_id, locating_area, locating_condition FROM " + getTableName() + " WHERE locating_id = ?", this,locatingId);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new WhLocatingAreaDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_locating_area table that match the criteria ''.
	 */
	@Transactional
	public List<WhLocatingArea> findAll() throws WhLocatingAreaDaoException
	{
		try {
			return jdbcTemplate.query("SELECT locating_id, locating_area, locating_condition FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new WhLocatingAreaDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_locating_area table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<WhLocatingArea> findWhereIdEquals(int id) throws WhLocatingAreaDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, locating_id, locating_area, locating_condition FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new WhLocatingAreaDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_locating_area table that match the criteria 'locating_id = :locatingId'.
	 */
	@Transactional
	public List<WhLocatingArea> findWhereLocatingIdEquals(String locatingId) throws WhLocatingAreaDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, locating_id, locating_area, locating_condition FROM " + getTableName() + " WHERE locating_id = ? ORDER BY locating_id", this,locatingId);
		}
		catch (Exception e) {
			throw new WhLocatingAreaDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_locating_area table that match the criteria 'locating_area = :locatingArea'.
	 */
	@Transactional
	public List<WhLocatingArea> findWhereLocatingAreaEquals(String locatingArea) throws WhLocatingAreaDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, locating_id, locating_area, locating_condition FROM " + getTableName() + " WHERE locating_area = ? ORDER BY locating_area", this,locatingArea);
		}
		catch (Exception e) {
			throw new WhLocatingAreaDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_locating_area table that match the criteria 'locating_condition = :locatingCondition'.
	 */
	@Transactional
	public List<WhLocatingArea> findWhereLocatingConditionEquals(String locatingCondition) throws WhLocatingAreaDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, locating_id, locating_area, locating_condition FROM " + getTableName() + " WHERE locating_condition = ? ORDER BY locating_condition", this,locatingCondition);
		}
		catch (Exception e) {
			throw new WhLocatingAreaDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the wh_locating_area table that matches the specified primary-key value.
	 */
	public WhLocatingArea findByPrimaryKey(WhLocatingAreaPk pk) throws WhLocatingAreaDaoException
	{
		return findByPrimaryKey( pk.getLocatingId());
	}

	@Override
	public List<WhLocatingArea> findWhLocatingAreaPaging(WhLocatingArea w, Integer page) throws WhLocatingAreaDaoException {
		try {
        	String corpId = w.getCorpId();
        	String whCode = w.getWhCode();
        	
        	String locatingArea = w.getLocatingArea();
        	String locatingCondition = w.getLocatingCondition();
        	
        	int i = page;
        	Map map = new HashMap();
        	map.put("corpId", corpId);
        	map.put("whCode", whCode);
        	map.put("i", i);
        
        	StringBuffer sb = new StringBuffer();
        	
        	if(w == null){
        		w = new WhLocatingArea();
        	}

        	if(locatingArea == null || locatingCondition == null){
        		locatingArea = "%";
        		locatingCondition = "%";
        		
        		sb.append("declare @Page int, @PageSize int " 
            			+"set @Page = '"+i+"'; " 
            			+"set @PageSize = 10; " 
            			+"with PagedResult " 
            			+"as (select ROW_NUMBER() over (order by locating_id desc) as id, locating_id, locating_area, locating_condition from wh_locating_area "
            			+"where corp_id like '"+corpId+"' and wh_code like '"+whCode+"' and locating_area like '%"+locatingArea+"%' and locating_condition like '%"+locatingCondition+"%') " 
            			+"select * from PagedResult where id between " 
            			+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 " 
            			+"else @Page end and @PageSize * @Page ");
        		
        	}else{
        		
        		sb.append("declare @Page int, @PageSize int " 
            			+"set @Page = '"+i+"'; " 
            			+"set @PageSize = 10; " 
            			+"with PagedResult " 
            			+"as (select ROW_NUMBER() over (order by locating_id desc) as id, locating_id, locating_area, locating_condition from wh_locating_area "
            			+"where corp_id like '"+corpId+"' and wh_code like '"+whCode+"' and locating_area like '%"+locatingArea+"%' and locating_condition like '%"+locatingCondition+"%') " 
            			+"select * from PagedResult where id between " 
            			+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 " 
            			+"else @Page end and @PageSize * @Page ");
        		
        	}
        	
        	
        	
        	return jdbcTemplate.query(sb.toString(),new WhLocatingAreaListMap(),map);	
        
        } catch (Exception e) {
            throw new WhLocatingAreaDaoException("Query failed", e);
        }
	}

}

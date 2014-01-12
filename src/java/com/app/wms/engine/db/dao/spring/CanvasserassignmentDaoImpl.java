package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.CanvasserassignmentDao;
import com.app.wms.engine.db.dto.Canvasserassignment;
import com.app.wms.engine.db.dto.CanvasserassignmentPk;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.map.SupplierListMap;
import com.app.wms.engine.db.exceptions.CanvasserassignmentDaoException;
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

public class CanvasserassignmentDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Canvasserassignment>, CanvasserassignmentDao
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
	 * @return CanvasserassignmentPk
	 */
	public CanvasserassignmentPk insert(Canvasserassignment dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( prsnumber, canvassername, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getPrsnumber(),dto.getCanvassername(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		CanvasserassignmentPk pk = new CanvasserassignmentPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the canvasserassignment table.
	 */
	public void update(CanvasserassignmentPk pk, Canvasserassignment dto) throws CanvasserassignmentDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET prsnumber = ?, canvassername = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getPrsnumber(),dto.getCanvassername(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the canvasserassignment table.
	 */
	@Transactional
	public void delete(CanvasserassignmentPk pk) throws CanvasserassignmentDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Canvasserassignment
	 */
	public Canvasserassignment mapRow(ResultSet rs, int row) throws SQLException
	{
		Canvasserassignment dto = new Canvasserassignment();
		dto.setId( rs.getInt( 1 ) );
		dto.setPrsnumber( rs.getString( 2 ) );
		dto.setCanvassername( rs.getString( 3 ) );
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
		return "inventory..canvasserassignment";
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Canvasserassignment findByPrimaryKey(int id) throws CanvasserassignmentDaoException
	{
		try {
			List<Canvasserassignment> list = jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new CanvasserassignmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria ''.
	 */
	@Transactional
	public List<Canvasserassignment> findAll() throws CanvasserassignmentDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new CanvasserassignmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Canvasserassignment> findWhereIdEquals(int id) throws CanvasserassignmentDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new CanvasserassignmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'prsnumber = :prsnumber'.
	 */
	@Transactional
	public List<Canvasserassignment> findWherePrsnumberEquals(String prsnumber) throws CanvasserassignmentDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE prsnumber = ? ORDER BY prsnumber", this,prsnumber);
		}
		catch (Exception e) {
			throw new CanvasserassignmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'canvassername = :canvassername'.
	 */
	@Transactional
	public List<Canvasserassignment> findWhereCanvassernameEquals(String canvassername) throws CanvasserassignmentDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE canvassername = ? ORDER BY canvassername", this,canvassername);
		}
		catch (Exception e) {
			throw new CanvasserassignmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<Canvasserassignment> findWhereCreatedByEquals(String createdBy) throws CanvasserassignmentDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new CanvasserassignmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<Canvasserassignment> findWhereCreatedDateEquals(Date createdDate) throws CanvasserassignmentDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new CanvasserassignmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<Canvasserassignment> findWhereUpdatedByEquals(String updatedBy) throws CanvasserassignmentDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new CanvasserassignmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<Canvasserassignment> findWhereUpdatedDateEquals(Date updatedDate) throws CanvasserassignmentDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new CanvasserassignmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the canvasserassignment table that matches the specified primary-key value.
	 */
	public Canvasserassignment findByPrimaryKey(CanvasserassignmentPk pk) throws CanvasserassignmentDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}
	
	public List<Canvasserassignment> findCanvasserAssignPaging (Canvasserassignment c, int page) throws CanvasserassignmentDaoException {
		 try {
	        	String prsnumber = c.getPrsnumber();
	        	String canvassername = c.getCanvassername();
	        	
	        	int i = page;
	        	Map map = new HashMap();
	        	map.put("i", i);
	        
	        	StringBuffer sb = new StringBuffer();
	        	
	        	if(c == null){
	        		c = new Canvasserassignment();
	        	}
	        	
	        	if(prsnumber == null || canvassername == null){
	        		prsnumber = "%";
	        		canvassername = "%";
	        		
	        		sb.append("declare @Page int, @PageSize int "
	        				+"set @Page = '"+i+"'; "
	        				+"set @PageSize = 10; "
	        				+"with PagedResult "
	        				+"as (select ROW_NUMBER() over (order by id asc) as id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date from canvasserassignment" +
	        						" where prsnumber like '%"+prsnumber+"%' and canvassername like '%"+canvassername+"%' ) "
	        				    +"select * from PagedResult where id between "
	        				+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
	        				     +"else @Page end and @PageSize * @Page ");
	        		
	        	}else{
	        		
	        		sb.append("declare @Page int, @PageSize int "
	        				+"set @Page = '"+i+"'; "
	        				+"set @PageSize = 10; "
	        				+"with PagedResult "
	        				+"as (select ROW_NUMBER() over (order by id asc) as id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date from canvasserassignment" +
	        						" where prsnumber like '%"+prsnumber+"%' and canvassername like '%"+canvassername+"%' ) "
	        				    +"select * from PagedResult where id between "
	        				+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
	        				     +"else @Page end and @PageSize * @Page ");
	        		
	        	}
	        	
	        	
	        	
	        	return jdbcTemplate.query(sb.toString(),this);	
	        
	        } catch (Exception e) {
	            throw new CanvasserassignmentDaoException("Query failed", e);
	        }

		
	}

}

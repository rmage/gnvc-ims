package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.AssignCanvasserDao;
import com.app.wms.engine.db.dto.AssignCanvasser;
import com.app.wms.engine.db.dto.AssignCanvasserPk;
import com.app.wms.engine.db.exceptions.AssignCanvasserDaoException;
import java.math.BigDecimal;

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

public class AssignCanvasserDaoImpl extends AbstractDAO implements ParameterizedRowMapper<AssignCanvasser>, AssignCanvasserDao
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
	 * @return AssignCanvasserPk
	 */
	public AssignCanvasserPk insert(AssignCanvasser dto)
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
		AssignCanvasserPk pk = new AssignCanvasserPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the canvasserassignment table.
	 */
	public void update(AssignCanvasserPk pk, AssignCanvasser dto) throws AssignCanvasserDaoException
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
	public void delete(AssignCanvasserPk pk) throws AssignCanvasserDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return AssignCanvasser
	 */
	public AssignCanvasser mapRow(ResultSet rs, int row) throws SQLException
	{
		AssignCanvasser dto = new AssignCanvasser();
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
		return "assign_canv";
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'id = :id'.
	 */
	@Transactional
	public AssignCanvasser findByPrimaryKey(int id) throws AssignCanvasserDaoException
	{
		try {
			List<AssignCanvasser> list = jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new AssignCanvasserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria ''.
	 */
	@Transactional
	public List<AssignCanvasser> findAll() throws AssignCanvasserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new AssignCanvasserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<AssignCanvasser> findWhereIdEquals(int id) throws AssignCanvasserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new AssignCanvasserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'prsnumber = :prsnumber'.
	 */
	@Transactional
	public List<AssignCanvasser> findWherePrsnumberEquals(String prsnumber) throws AssignCanvasserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE prsnumber = ? ORDER BY prsnumber", this,prsnumber);
		}
		catch (Exception e) {
			throw new AssignCanvasserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'canvassername = :canvassername'.
	 */
	@Transactional
	public List<AssignCanvasser> findWhereCanvassernameEquals(String canvassername) throws AssignCanvasserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE canvassername = ? ORDER BY canvassername", this,canvassername);
		}
		catch (Exception e) {
			throw new AssignCanvasserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<AssignCanvasser> findWhereCreatedByEquals(String createdBy) throws AssignCanvasserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new AssignCanvasserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<AssignCanvasser> findWhereCreatedDateEquals(Date createdDate) throws AssignCanvasserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new AssignCanvasserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<AssignCanvasser> findWhereUpdatedByEquals(String updatedBy) throws AssignCanvasserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new AssignCanvasserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<AssignCanvasser> findWhereUpdatedDateEquals(Date updatedDate) throws AssignCanvasserDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new AssignCanvasserDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the canvasserassignment table that matches the specified primary-key value.
	 */
	public AssignCanvasser findByPrimaryKey(AssignCanvasserPk pk) throws AssignCanvasserDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}
	
    public List<AssignCanvasser> findCanvasserAssignPaging (AssignCanvasser c, int page) throws AssignCanvasserDaoException {
        try {
            String prsnumber = c.getPrsnumber();
            String canvassername = c.getCanvassername();

            int i = page;
            Map map = new HashMap();
            map.put("i", i);

            StringBuffer sb = new StringBuffer();

            if(c == null){
                c = new AssignCanvasser();
            }

            if(prsnumber == null || canvassername == null){
                prsnumber = "%";
                canvassername = "%";
            }

            sb.append("declare @Page int, @PageSize int "
                 +"set @Page = '"+i+"'; "
                 +"set @PageSize = 10; "
                 +"with PagedResult "
                 +"as (select ROW_NUMBER() over (order by ca.id desc) as id, ca.prsnumber, ca.canvassername, ca.created_by, ca.created_date, ca.updated_by, ca.updated_date from " + getTableName() + " ca " +
                     "left join \"user\" u on u.user_id = canvassername where ca.prsnumber like '%"+prsnumber+"%' ) "
                 +"select * from PagedResult where id between "
                 +"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
                 +"else @Page end and @PageSize * @Page");

            return jdbcTemplate.query(sb.toString(),this);	
       } catch (Exception e) {
           throw new AssignCanvasserDaoException("Query failed", e);
       }		
    }

    public int ajaxMaxPage(String where, BigDecimal show) {
        return jdbcTemplate.queryForInt("SELECT CEILING(COUNT(id)/?) maxpage FROM " + getTableName() + " " + (where.isEmpty()? "where prsnumber like '%%%'" : where + " AND prsnumber like '%%%'"), show);
    }

    public List<AssignCanvasser> ajaxSearch(String where, String order, int page, int show) {
        return jdbcTemplate.query("declare @Page int, @PageSize int set @Page = ?; set @PageSize=?; with PagedResult as (select ca.id, ca.prsnumber, ca.canvassername, ca.created_by, ca.created_date, ca.updated_by, ca.updated_date, ROW_NUMBER() over (order by ca.id desc) as idx from " + getTableName() + " ca left join \"user\" u on u.user_id = canvassername "+ (where.isEmpty()? "where ca.is_active = 'Y'" : where + " AND ca.is_active = 'Y'")+") select * from PagedResult where idx between case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 else @Page end and @PageSize * @Page", this, page, show);
    }

    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC PRC_CA_UPDATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }
    
    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC PRC_CA_CREATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }
    
    public void delete(int key, String updatedBy) {
        jdbcTemplate.update("EXEC PRC_CA_DELETE ?, ?", key, updatedBy);
    }
    
    public List<Map<String, Object>> getAssignedCanvasser(int id) {
        return jdbcTemplate.queryForList("SELECT acd.id, acd.prsnumber, p.product_code, p.product_name, prsd.qty, acd.user_id FROM assign_canv_dtl acd INNER JOIN prs_detail prsd ON prsd.prsnumber = acd.prsnumber AND prsd.productcode = acd.productcode AND prsd.is_active = 'Y' INNER JOIN assign_canv ac ON ac.prsnumber = acd.prsnumber LEFT JOIN product p ON p.product_code = acd.productcode WHERE ac.id = ? AND ac.is_active = 'Y'", id);
    }
    
}

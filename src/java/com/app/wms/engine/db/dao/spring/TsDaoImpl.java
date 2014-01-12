package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.TsDao;
import com.app.wms.engine.db.dto.Ts;
import com.app.wms.engine.db.dto.TsPk;
import com.app.wms.engine.db.dto.map.TsListMap;
import com.app.wms.engine.db.exceptions.TsDaoException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

public class TsDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Ts>, TsDao
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
	 * @return TsPk
	 */
	public TsPk insert(Ts dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( tsnumber, tsdate, swsnumber, department_code, createdby, issuedby, notedby, receivedby, approvedby, remarks ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getTsnumber(),dto.getTsdate(),dto.getSwsnumber(),dto.getDepartmentCode(),dto.getCreatedby(),dto.getIssuedby(),dto.getNotedby(),dto.getReceivedby(),dto.getApprovedby(),dto.getRemarks()} );
		TsPk pk = new TsPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the ts table.
	 */
	public void update(TsPk pk, Ts dto) throws TsDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET tsnumber = ?, tsdate = ?, swsnumber = ?, department_code = ?, createdby = ?, issuedby = ?, notedby = ?, receivedby = ?, approvedby = ?, remarks = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getTsnumber(),dto.getTsdate(),dto.getSwsnumber(),dto.getDepartmentCode(),dto.getCreatedby(),dto.getIssuedby(),dto.getNotedby(),dto.getReceivedby(),dto.getApprovedby(),dto.getRemarks(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the ts table.
	 */
	@Transactional
	public void delete(TsPk pk) throws TsDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Ts
	 */
	public Ts mapRow(ResultSet rs, int row) throws SQLException
	{
		Ts dto = new Ts();
		dto.setId( rs.getInt( 1 ) );
		dto.setTsnumber( rs.getString( 2 ) );
		dto.setTsdate( rs.getTimestamp(3 ) );
		dto.setSwsnumber( rs.getString( 4 ) );
		dto.setDepartmentCode( rs.getString( 5 ) );
		dto.setCreatedby( rs.getString( 6 ) );
		dto.setIssuedby( rs.getString( 7 ) );
		dto.setNotedby( rs.getString( 8 ) );
		dto.setReceivedby( rs.getString( 9 ) );
		dto.setApprovedby( rs.getString( 10 ) );
		dto.setRemarks( rs.getString( 11 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..ts";
	}

	/** 
	 * Returns all rows from the ts table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Ts findByPrimaryKey(int id) throws TsDaoException
	{
		try {
			List<Ts> list = jdbcTemplate.query("SELECT id, tsnumber, tsdate, swsnumber, department_code, createdby, issuedby, notedby, receivedby, approvedby, remarks FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new TsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts table that match the criteria ''.
	 */
	@Transactional
	public List<Ts> findAll() throws TsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, tsdate, swsnumber, department_code, createdby, issuedby, notedby, receivedby, approvedby, remarks FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new TsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Ts> findWhereIdEquals(int id) throws TsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, tsdate, swsnumber, department_code, createdby, issuedby, notedby, receivedby, approvedby, remarks FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new TsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts table that match the criteria 'tsnumber = :tsnumber'.
	 */
	@Transactional
	public List<Ts> findWhereTsnumberEquals(String tsnumber) throws TsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, tsdate, swsnumber, department_code, createdby, issuedby, notedby, receivedby, approvedby, remarks FROM " + getTableName() + " WHERE tsnumber = ? ORDER BY tsnumber", this,tsnumber);
		}
		catch (Exception e) {
			throw new TsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts table that match the criteria 'tsdate = :tsdate'.
	 */
	@Transactional
	public List<Ts> findWhereTsdateEquals(Date tsdate) throws TsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, tsdate, swsnumber, department_code, createdby, issuedby, notedby, receivedby, approvedby, remarks FROM " + getTableName() + " WHERE tsdate = ? ORDER BY tsdate", this,tsdate);
		}
		catch (Exception e) {
			throw new TsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts table that match the criteria 'swsnumber = :swsnumber'.
	 */
	@Transactional
	public List<Ts> findWhereSwsnumberEquals(String swsnumber) throws TsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, tsdate, swsnumber, department_code, createdby, issuedby, notedby, receivedby, approvedby, remarks FROM " + getTableName() + " WHERE swsnumber = ? ORDER BY swsnumber", this,swsnumber);
		}
		catch (Exception e) {
			throw new TsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts table that match the criteria 'department_code = :departmentCode'.
	 */
	@Transactional
	public List<Ts> findWhereDepartmentCodeEquals(String departmentCode) throws TsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, tsdate, swsnumber, department_code, createdby, issuedby, notedby, receivedby, approvedby, remarks FROM " + getTableName() + " WHERE department_code = ? ORDER BY department_code", this,departmentCode);
		}
		catch (Exception e) {
			throw new TsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts table that match the criteria 'createdby = :createdby'.
	 */
	@Transactional
	public List<Ts> findWhereCreatedbyEquals(String createdby) throws TsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, tsdate, swsnumber, department_code, createdby, issuedby, notedby, receivedby, approvedby, remarks FROM " + getTableName() + " WHERE createdby = ? ORDER BY createdby", this,createdby);
		}
		catch (Exception e) {
			throw new TsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts table that match the criteria 'issuedby = :issuedby'.
	 */
	@Transactional
	public List<Ts> findWhereIssuedbyEquals(String issuedby) throws TsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, tsdate, swsnumber, department_code, createdby, issuedby, notedby, receivedby, approvedby, remarks FROM " + getTableName() + " WHERE issuedby = ? ORDER BY issuedby", this,issuedby);
		}
		catch (Exception e) {
			throw new TsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts table that match the criteria 'notedby = :notedby'.
	 */
	@Transactional
	public List<Ts> findWhereNotedbyEquals(String notedby) throws TsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, tsdate, swsnumber, department_code, createdby, issuedby, notedby, receivedby, approvedby, remarks FROM " + getTableName() + " WHERE notedby = ? ORDER BY notedby", this,notedby);
		}
		catch (Exception e) {
			throw new TsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts table that match the criteria 'receivedby = :receivedby'.
	 */
	@Transactional
	public List<Ts> findWhereReceivedbyEquals(String receivedby) throws TsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, tsdate, swsnumber, department_code, createdby, issuedby, notedby, receivedby, approvedby, remarks FROM " + getTableName() + " WHERE receivedby = ? ORDER BY receivedby", this,receivedby);
		}
		catch (Exception e) {
			throw new TsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts table that match the criteria 'approvedby = :approvedby'.
	 */
	@Transactional
	public List<Ts> findWhereApprovedbyEquals(String approvedby) throws TsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, tsdate, swsnumber, department_code, createdby, issuedby, notedby, receivedby, approvedby, remarks FROM " + getTableName() + " WHERE approvedby = ? ORDER BY approvedby", this,approvedby);
		}
		catch (Exception e) {
			throw new TsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts table that match the criteria 'remarks = :remarks'.
	 */
	@Transactional
	public List<Ts> findWhereRemarksEquals(String remarks) throws TsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, tsdate, swsnumber, department_code, createdby, issuedby, notedby, receivedby, approvedby, remarks FROM " + getTableName() + " WHERE remarks = ? ORDER BY remarks", this,remarks);
		}
		catch (Exception e) {
			throw new TsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the ts table that matches the specified primary-key value.
	 */
	public Ts findByPrimaryKey(TsPk pk) throws TsDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}
	
	public List<Ts> findTsPaging(Ts t, Integer page) throws TsDaoException {
		try{
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        	String tsNo = t.getTsnumber();
        	String tsDate = df.format(t.getTsdate());
        	
        	int i = page;
        	Map map = new HashMap();
        	map.put("i", i);
        	
        	StringBuffer sb = new StringBuffer();
        	
    		sb.append("declare @Page int, @PageSize int "
    				+"set @Page = '"+i+"'; "
    				+"set @PageSize = 10; "
    				+"with PagedResult "
    				+"as (select ROW_NUMBER() over (order by id asc) as id, tsnumber, tsdate from ts" +
    						" where tsnumber like '%"+tsNo+"%' and tsdate='"+tsDate+"' ) "
    				    +"select * from PagedResult where id between "
    				+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
    				     +"else @Page end and @PageSize * @Page ");
   
        	return jdbcTemplate.query(sb.toString(),new TsListMap(),map);	
        
		}catch(Exception e){
			e.printStackTrace();
			throw new TsDaoException("Query failed", e);
		}
	}

}

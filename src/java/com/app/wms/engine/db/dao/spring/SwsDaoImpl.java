package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.SwsDao;
import com.app.wms.engine.db.dto.Prs;
import com.app.wms.engine.db.dto.Sws;
import com.app.wms.engine.db.dto.SwsPk;
import com.app.wms.engine.db.dto.map.SwsListMap;
import com.app.wms.engine.db.exceptions.SwsDaoException;
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

public class SwsDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Sws>, SwsDao
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
	 * @return SwsPk
	 */
	public SwsPk insert(Sws dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( swsnumber, swsdate, swsreferensi, remarks, createdby, requestedBy, approvedBy, wh_code, department_name, is_approved, approved_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
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
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getSwsnumber(),dto.getSwsdate(),dto.getSwsreferensi(),dto.getRemarks(),dto.getCreatedby(),dto.getRequestedBy(),dto.getApprovedBy(),dto.getWhCode(),dto.getDepartmentName(),dto.getIsApproved(),dto.getApprovedDate()} );
		SwsPk pk = new SwsPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the sws table.
	 */
	public void update(SwsPk pk, Sws dto) throws SwsDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET swsnumber = ?, swsdate = ?, swsreferensi = ?, remarks = ?, createdby = ?, requestedBy = ?, approvedBy = ?, wh_code = ?, department_name, is_approved, approved_date = ?, is_approved = ?, approved_date = ? WHERE id = ?");
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
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getSwsnumber(),dto.getSwsdate(),dto.getSwsreferensi(),dto.getRemarks(),dto.getCreatedby(),dto.getRequestedBy(),dto.getApprovedBy(),dto.getWhCode(),dto.getDepartmentName(), dto.getIsApproved(), dto.getApprovedDate(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the sws table.
	 */
	@Transactional
	public void delete(SwsPk pk) throws SwsDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Sws
	 */
	public Sws mapRow(ResultSet rs, int row) throws SQLException
	{
		Sws dto = new Sws();
		dto.setId( rs.getInt( 1 ) );
		dto.setSwsnumber( rs.getString( 2 ) );
		dto.setSwsdate( rs.getTimestamp(3 ) );
		dto.setSwsreferensi( rs.getString( 4 ) );
		dto.setRemarks( rs.getString( 5 ) );
		dto.setCreatedby( rs.getString( 6 ) );
		dto.setRequestedBy( rs.getString( 7 ) );
		dto.setApprovedBy( rs.getString( 8 ) );
		dto.setWhCode( rs.getString( 9 ) );
		dto.setDepartmentName( rs.getString( 10 ) );
		dto.setIsApproved(rs.getString(11));
		dto.setApprovedDate(rs.getTimestamp(12));
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..sws";
	}

	/** 
	 * Returns all rows from the sws table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Sws findByPrimaryKey(int id) throws SwsDaoException
	{
		try {
			List<Sws> list = jdbcTemplate.query("SELECT id, swsnumber, swsdate, swsreferensi, remarks, createdby, requestedBy, approvedBy, wh_code, department_name, is_approved, approved_date, is_approved, approved_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new SwsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the sws table that match the criteria ''.
	 */
	@Transactional
	public List<Sws> findAll() throws SwsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, swsnumber, swsdate, swsreferensi, remarks, createdby, requestedBy, approvedBy, wh_code, department_name, is_approved, approved_date, is_approved, approved_date FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new SwsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the sws table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Sws> findWhereIdEquals(int id) throws SwsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, swsnumber, swsdate, swsreferensi, remarks, createdby, requestedBy, approvedBy, wh_code, department_name, is_approved, approved_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new SwsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the sws table that match the criteria 'swsnumber = :swsnumber'.
	 */
	@Transactional
	public List<Sws> findWhereSwsnumberEquals(String swsnumber) throws SwsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, swsnumber, swsdate, swsreferensi, remarks, createdby, requestedBy, approvedBy, wh_code, department_name, is_approved, approved_date FROM " + getTableName() + " WHERE swsnumber = ? ORDER BY swsnumber", this,swsnumber);
		}
		catch (Exception e) {
			throw new SwsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the sws table that match the criteria 'swsdate = :swsdate'.
	 */
	@Transactional
	public List<Sws> findWhereSwsdateEquals(Date swsdate) throws SwsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, swsnumber, swsdate, swsreferensi, remarks, createdby, requestedBy, approvedBy, wh_code, department_name, is_approved, approved_date FROM " + getTableName() + " WHERE swsdate = ? ORDER BY swsdate", this,swsdate);
		}
		catch (Exception e) {
			throw new SwsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the sws table that match the criteria 'swsreferensi = :swsreferensi'.
	 */
	@Transactional
	public List<Sws> findWhereSwsreferensiEquals(String swsreferensi) throws SwsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, swsnumber, swsdate, swsreferensi, remarks, createdby, requestedBy, approvedBy, wh_code, department_name, is_approved, approved_date FROM " + getTableName() + " WHERE swsreferensi = ? ORDER BY swsreferensi", this,swsreferensi);
		}
		catch (Exception e) {
			throw new SwsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the sws table that match the criteria 'remarks = :remarks'.
	 */
	@Transactional
	public List<Sws> findWhereRemarksEquals(String remarks) throws SwsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, swsnumber, swsdate, swsreferensi, remarks, createdby, requestedBy, approvedBy, wh_code, department_name, is_approved, approved_date FROM " + getTableName() + " WHERE remarks = ? ORDER BY remarks", this,remarks);
		}
		catch (Exception e) {
			throw new SwsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the sws table that match the criteria 'createdby = :createdby'.
	 */
	@Transactional
	public List<Sws> findWhereCreatedbyEquals(String createdby) throws SwsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, swsnumber, swsdate, swsreferensi, remarks, createdby, requestedBy, approvedBy, wh_code, department_name, is_approved, approved_date FROM " + getTableName() + " WHERE createdby = ? ORDER BY createdby", this,createdby);
		}
		catch (Exception e) {
			throw new SwsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the sws table that match the criteria 'requestedBy = :requestedBy'.
	 */
	@Transactional
	public List<Sws> findWhereRequestedByEquals(String requestedBy) throws SwsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, swsnumber, swsdate, swsreferensi, remarks, createdby, requestedBy, approvedBy, wh_code, department_name, is_approved, approved_date FROM " + getTableName() + " WHERE requestedBy = ? ORDER BY requestedBy", this,requestedBy);
		}
		catch (Exception e) {
			throw new SwsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the sws table that match the criteria 'approvedBy = :approvedBy'.
	 */
	@Transactional
	public List<Sws> findWhereApprovedByEquals(String approvedBy) throws SwsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, swsnumber, swsdate, swsreferensi, remarks, createdby, requestedBy, approvedBy, wh_code, department_name, is_approved, approved_date FROM " + getTableName() + " WHERE approvedBy = ? ORDER BY approvedBy", this,approvedBy);
		}
		catch (Exception e) {
			throw new SwsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the sws table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<Sws> findWhereWhCodeEquals(String whCode) throws SwsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, swsnumber, swsdate, swsreferensi, remarks, createdby, requestedBy, approvedBy, wh_code, department_name, is_approved, approved_date FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new SwsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the sws table that match the criteria 'department_name = :departmentName'.
	 */
	@Transactional
	public List<Sws> findWhereDepartmentNameEquals(String departmentName) throws SwsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, swsnumber, swsdate, swsreferensi, remarks, createdby, requestedBy, approvedBy, wh_code, department_name, is_approved, approved_date FROM " + getTableName() + " WHERE department_name = ? ORDER BY department_name", this,departmentName);
		}
		catch (Exception e) {
			throw new SwsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the sws table that matches the specified primary-key value.
	 */
	public Sws findByPrimaryKey(SwsPk pk) throws SwsDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}
	
	public List<Sws> findSwsPaging(Sws s, int page) throws SwsDaoException {
		try{
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        	String swsNo = s.getSwsnumber();
        	String swsDate = df.format(s.getSwsdate());
        	
        	
        	int i = page;
        	Map map = new HashMap();
        	map.put("i", i);
        	
        	StringBuffer sb = new StringBuffer();
        	
    		sb.append("declare @Page int, @PageSize int "
    				+"set @Page = '"+i+"'; "
    				+"set @PageSize = 10; "
    				+"with PagedResult "
    				+"as (select ROW_NUMBER() over (order by id asc) as id, swsnumber, swsdate from sws" +
    						" where swsnumber like '%"+swsNo+"%' and swsdate='"+swsDate+"' ) "
    				    +"select * from PagedResult where id between "
    				+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
    				     +"else @Page end and @PageSize * @Page ");
   
        	return jdbcTemplate.query(sb.toString(),new SwsListMap(),map);	
        
		}catch(Exception e){
			e.printStackTrace();
			throw new SwsDaoException("Query failed", e);
		}
	}

		@Override
		public List<Sws> findWhereSwsNumberNotInTS() throws SwsDaoException {
			try {
				return jdbcTemplate.query("select id, swsnumber, swsdate, swsreferensi, remarks, createdby, requestedBy, approvedBy, wh_code, department_name, is_approved, approved_date, is_approved, approved_date from sws where swsnumber not in (select swsnumber from ts) order by swsdate desc", this);
			}
			catch (Exception e) {
				throw new SwsDaoException("Query failed", e);
			}
			
		}
		
		
		public void update(Sws dto) throws SwsDaoException
		{
			SqlUpdate su = new SqlUpdate( dataSource, "update sws set is_approved = ?, approved_by = ?, approved_date = ? where swsnumber = ?");
			su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
			su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
			su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
			su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
			su.compile();
			su.update( new Object[] { dto.getIsApproved(), dto.getApprovedBy(), dto.getApprovedDate(),dto.getSwsnumber()} );
		}

}

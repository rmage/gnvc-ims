package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.CanvassingDao;
import com.app.wms.engine.db.dto.Canvassing;
import com.app.wms.engine.db.dto.CanvassingPk;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.dto.map.LoginUserMap;
import com.app.wms.engine.db.exceptions.CanvassingDaoException;
import com.app.wms.engine.db.exceptions.UserDaoException;
import com.app.wms.web.util.EncryptionUtility;

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

public class CanvassingDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Canvassing>, CanvassingDao
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
	 * @return CanvassingPk
	 */
	public CanvassingPk insert(Canvassing dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( prsnumber, canvassername, canvassingdate, remarks, createdby ) VALUES ( ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getPrsnumber(),dto.getCanvassername(),dto.getCanvassingdate(),dto.getRemarks(),dto.getCreatedby()} );
		CanvassingPk pk = new CanvassingPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the canvassing table.
	 */
	public void update(CanvassingPk pk, Canvassing dto) throws CanvassingDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET prsnumber = ?, canvassername = ?, canvassingdate = ?, remarks = ?, createdby = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getPrsnumber(),dto.getCanvassername(),dto.getCanvassingdate(),dto.getRemarks(),dto.getCreatedby(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the canvassing table.
	 */
	@Transactional
	public void delete(CanvassingPk pk) throws CanvassingDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Canvassing
	 */
	public Canvassing mapRow(ResultSet rs, int row) throws SQLException
	{
		Canvassing dto = new Canvassing();
		dto.setId( rs.getInt( 1 ) );
		dto.setPrsnumber( rs.getString( 2 ) );
		dto.setCanvassername( rs.getString( 3 ) );
		dto.setCanvassingdate( rs.getTimestamp(4 ) );
		dto.setRemarks( rs.getString( 5 ) );
		dto.setCreatedby( rs.getString( 6 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..canvassing";
	}

	/** 
	 * Returns all rows from the canvassing table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Canvassing findByPrimaryKey(int id) throws CanvassingDaoException
	{
		try {
			List<Canvassing> list = jdbcTemplate.query("SELECT id, prsnumber, canvassername, canvassingdate, remarks, createdby FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new CanvassingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing table that match the criteria ''.
	 */
	@Transactional
	public List<Canvassing> findAll() throws CanvassingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, canvassingdate, remarks, createdby FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new CanvassingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Canvassing> findWhereIdEquals(int id) throws CanvassingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, canvassingdate, remarks, createdby FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new CanvassingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing table that match the criteria 'supplier_code = :supplierCode'.
	 */
	@Transactional
	public List<Canvassing> findWhereSupplierCodeEquals(String supplierCode) throws CanvassingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, canvassingdate, remarks, createdby FROM " + getTableName() + " WHERE supplier_code = ? ORDER BY supplier_code", this,supplierCode);
		}
		catch (Exception e) {
			throw new CanvassingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing table that match the criteria 'prsnumber = :prsnumber'.
	 */
	@Transactional
	public List<Canvassing> findWherePrsnumberEquals(String prsnumber) throws CanvassingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, canvassingdate, remarks, createdby FROM " + getTableName() + " WHERE prsnumber = ? ORDER BY prsnumber", this,prsnumber);
		}
		catch (Exception e) {
			throw new CanvassingDaoException("Query failed", e);
		}
		
	}
	
	
	/** 
	 * Returns all rows from the canvassing table that match the criteria 'canvassername = :canvassername'.
	 */
	@Transactional
	public List<Canvassing> findWhereCanvassernameEquals(String canvassername) throws CanvassingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, canvassingdate, remarks, createdby FROM " + getTableName() + " WHERE canvassername = ? ORDER BY canvassername", this,canvassername);
		}
		catch (Exception e) {
			throw new CanvassingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing table that match the criteria 'canvassingdate = :canvassingdate'.
	 */
	@Transactional
	public List<Canvassing> findWhereCanvassingdateEquals(Date canvassingdate) throws CanvassingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, canvassingdate, remarks, createdby FROM " + getTableName() + " WHERE canvassingdate = ? ORDER BY canvassingdate", this,canvassingdate);
		}
		catch (Exception e) {
			throw new CanvassingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing table that match the criteria 'remarks = :remarks'.
	 */
	@Transactional
	public List<Canvassing> findWhereRemarksEquals(String remarks) throws CanvassingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, canvassingdate, remarks, createdby FROM " + getTableName() + " WHERE remarks = ? ORDER BY remarks", this,remarks);
		}
		catch (Exception e) {
			throw new CanvassingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing table that match the criteria 'createdby = :createdby'.
	 */
	@Transactional
	public List<Canvassing> findWhereCreatedbyEquals(String createdby) throws CanvassingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, canvassername, canvassingdate, remarks, createdby FROM " + getTableName() + " WHERE createdby = ? ORDER BY createdby", this,createdby);
		}
		catch (Exception e) {
			throw new CanvassingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the canvassing table that matches the specified primary-key value.
	 */
	public Canvassing findByPrimaryKey(CanvassingPk pk) throws CanvassingDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}
	
	public Canvassing findWherePrsnumber(String prsnumber) throws CanvassingDaoException
	{
		Canvassing c = null;
		try {
			List <Canvassing> canvassingList = jdbcTemplate.query("SELECT id, prsnumber, canvassername, canvassingdate, remarks, createdby FROM " + getTableName() + " WHERE prsnumber = ? ORDER BY prsnumber", this,prsnumber);
		    c = canvassingList.get(0);
		}
		catch (Exception e) {
			throw new CanvassingDaoException("Query failed", e);
		}
		return c;
	}
	
	public void updateDto(Canvassing dto) throws CanvassingDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET prsnumber = ?, canvassername = ?, canvassingdate = ?, remarks = ?, createdby = ? WHERE prsnumber = ? AND canvassername = ? ");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getPrsnumber(),dto.getCanvassername(),dto.getCanvassingdate(),dto.getRemarks(),dto.getCreatedby(),dto.getPrsnumber(),dto.getCanvassername()} );
	}


}

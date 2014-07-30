package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.GoodreceiveDao;
import com.app.wms.engine.db.dto.Goodreceive;
import com.app.wms.engine.db.dto.GoodreceivePk;
import com.app.wms.engine.db.exceptions.GoodreceiveDaoException;
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

public class GoodreceiveDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Goodreceive>, GoodreceiveDao
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
	 * @return GoodreceivePk
	 */
	public GoodreceivePk insert(Goodreceive dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( grnumber, ponumber, receiveddate, createdby, corpid, lotid, wh_code ) VALUES ( ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getGrnumber(),dto.getPonumber(),dto.getReceiveddate(),dto.getCreatedby(),dto.getCorpid(),dto.getLotid(),dto.getWhCode()} );
		GoodreceivePk pk = new GoodreceivePk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the goodreceive table.
	 */
	public void update(GoodreceivePk pk, Goodreceive dto) throws GoodreceiveDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET grnumber = ?, ponumber = ?, receiveddate = ?, createdby = ?, corpid = ?, lotid = ?, wh_code = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getGrnumber(),dto.getPonumber(),dto.getReceiveddate(),dto.getCreatedby(),dto.getCorpid(),dto.getLotid(),dto.getWhCode(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the goodreceive table.
	 */
	@Transactional
	public void delete(GoodreceivePk pk) throws GoodreceiveDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Goodreceive
	 */
	public Goodreceive mapRow(ResultSet rs, int row) throws SQLException
	{
		Goodreceive dto = new Goodreceive();
		dto.setId( rs.getInt( 1 ) );
		dto.setGrnumber( rs.getString( 2 ) );
		dto.setPonumber( rs.getString( 3 ) );
		dto.setReceiveddate( rs.getTimestamp(4 ) );
		dto.setCreatedby( rs.getString( 5 ) );
		dto.setCorpid( rs.getString( 6 ) );
		dto.setLotid( rs.getString( 7 ) );
		dto.setWhCode( rs.getString( 8 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "goodreceive";
	}

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Goodreceive findByPrimaryKey(int id) throws GoodreceiveDaoException
	{
		try {
			List<Goodreceive> list = jdbcTemplate.query("SELECT id, grnumber, ponumber, receiveddate, createdby, corpid, lotid, wh_code FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new GoodreceiveDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive table that match the criteria ''.
	 */
	@Transactional
	public List<Goodreceive> findAll() throws GoodreceiveDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, ponumber, receiveddate, createdby, corpid, lotid, wh_code FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new GoodreceiveDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Goodreceive> findWhereIdEquals(int id) throws GoodreceiveDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, ponumber, receiveddate, createdby, corpid, lotid, wh_code FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new GoodreceiveDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'grnumber = :grnumber'.
	 */
	@Transactional
	public List<Goodreceive> findWhereGrnumberEquals(String grnumber) throws GoodreceiveDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, ponumber, receiveddate, createdby, corpid, lotid, wh_code FROM " + getTableName() + " WHERE grnumber = ? ORDER BY grnumber", this,grnumber);
		}
		catch (Exception e) {
			throw new GoodreceiveDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'ponumber = :ponumber'.
	 */
	@Transactional
	public List<Goodreceive> findWherePonumberEquals(String ponumber) throws GoodreceiveDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, ponumber, receiveddate, createdby, corpid, lotid, wh_code FROM " + getTableName() + " WHERE ponumber = ? ORDER BY ponumber", this,ponumber);
		}
		catch (Exception e) {
			throw new GoodreceiveDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'receiveddate = :receiveddate'.
	 */
	@Transactional
	public List<Goodreceive> findWhereReceiveddateEquals(Date receiveddate) throws GoodreceiveDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, ponumber, receiveddate, createdby, corpid, lotid, wh_code FROM " + getTableName() + " WHERE receiveddate = ? ORDER BY receiveddate", this,receiveddate);
		}
		catch (Exception e) {
			throw new GoodreceiveDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'createdby = :createdby'.
	 */
	@Transactional
	public List<Goodreceive> findWhereCreatedbyEquals(String createdby) throws GoodreceiveDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, ponumber, receiveddate, createdby, corpid, lotid, wh_code FROM " + getTableName() + " WHERE createdby = ? ORDER BY createdby", this,createdby);
		}
		catch (Exception e) {
			throw new GoodreceiveDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'corpid = :corpid'.
	 */
	@Transactional
	public List<Goodreceive> findWhereCorpidEquals(String corpid) throws GoodreceiveDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, ponumber, receiveddate, createdby, corpid, lotid, wh_code FROM " + getTableName() + " WHERE corpid = ? ORDER BY corpid", this,corpid);
		}
		catch (Exception e) {
			throw new GoodreceiveDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'lotid = :lotid'.
	 */
	@Transactional
	public List<Goodreceive> findWhereLotidEquals(String lotid) throws GoodreceiveDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, ponumber, receiveddate, createdby, corpid, lotid, wh_code FROM " + getTableName() + " WHERE lotid = ? ORDER BY lotid", this,lotid);
		}
		catch (Exception e) {
			throw new GoodreceiveDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<Goodreceive> findWhereWhCodeEquals(String whCode) throws GoodreceiveDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, ponumber, receiveddate, createdby, corpid, lotid, wh_code FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new GoodreceiveDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the goodreceive table that matches the specified primary-key value.
	 */
	public Goodreceive findByPrimaryKey(GoodreceivePk pk) throws GoodreceiveDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

}

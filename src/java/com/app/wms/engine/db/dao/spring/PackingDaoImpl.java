package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.PackingDao;
import com.app.wms.engine.db.dto.Packing;
import com.app.wms.engine.db.dto.PackingPk;
import com.app.wms.engine.db.dto.map.KittingListMap;
import com.app.wms.engine.db.dto.map.PackingListMap;
import com.app.wms.engine.db.exceptions.KittingDaoException;
import com.app.wms.engine.db.exceptions.PackingDaoException;
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

public class PackingDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Packing>, PackingDao
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
	 * @return PackingPk
	 */
	public PackingPk insert(Packing dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( packing_no, packing_date, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getPackingNo(),dto.getPackingDate(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the packing table.
	 */
	public void update(PackingPk pk, Packing dto) throws PackingDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET packing_no = ?, packing_date = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE packing_no = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getPackingNo(),dto.getPackingDate(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getPackingNo() } );
	}

	/** 
	 * Deletes a single row in the packing table.
	 */
	@Transactional
	public void delete(PackingPk pk) throws PackingDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE packing_no = ?",pk.getPackingNo());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Packing
	 */
	public Packing mapRow(ResultSet rs, int row) throws SQLException
	{
		Packing dto = new Packing();
		dto.setId( rs.getInt( 1 ) );
		dto.setPackingNo( rs.getString( 2 ) );
		dto.setPackingDate( rs.getTimestamp(3 ) );
		dto.setCreatedBy( rs.getString( 4) );
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
		return "packing";
	}

	/** 
	 * Returns all rows from the packing table that match the criteria 'packing_no = :packingNo'.
	 */
	@Transactional
	public Packing findByPrimaryKey(String packingNo) throws PackingDaoException
	{
		try {
			List<Packing> list = jdbcTemplate.query("SELECT ROW_NUMBER() over (order by packing_no asc) as id, packing_no, packing_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE packing_no = ?", this,packingNo);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new PackingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing table that match the criteria ''.
	 */
	@Transactional
	public List<Packing> findAll() throws PackingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, packing_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY packing_no", this);
		}
		catch (Exception e) {
			throw new PackingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing table that match the criteria 'packing_no = :packingNo'.
	 */
	@Transactional
	public List<Packing> findWherePackingNoEquals(String packingNo) throws PackingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, packing_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE packing_no = ? ORDER BY packing_no", this,packingNo);
		}
		catch (Exception e) {
			throw new PackingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing table that match the criteria 'packing_date = :packingDate'.
	 */
	@Transactional
	public List<Packing> findWherePackingDateEquals(Date packingDate) throws PackingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, packing_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE packing_date = ? ORDER BY packing_date", this,packingDate);
		}
		catch (Exception e) {
			throw new PackingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<Packing> findWhereCreatedByEquals(String createdBy) throws PackingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, packing_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new PackingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<Packing> findWhereCreatedDateEquals(Date createdDate) throws PackingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, packing_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new PackingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<Packing> findWhereUpdatedByEquals(String updatedBy) throws PackingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, packing_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new PackingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<Packing> findWhereUpdatedDateEquals(Date updatedDate) throws PackingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, packing_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new PackingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the packing table that matches the specified primary-key value.
	 */
	public Packing findByPrimaryKey(PackingPk pk) throws PackingDaoException
	{
		return findByPrimaryKey( pk.getPackingNo() );
	}

	@Transactional
	public List<Packing> findPacking(Packing packing) throws PackingDaoException {
		try {
			return jdbcTemplate.query("SELECT ROW_NUMBER() over (order by packing_date desc) as id, packing_no, packing_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY packing_no", this);
		}
		catch (Exception e) {
			throw new PackingDaoException("Query failed", e);
		}
	}

	@Override
	public List<Packing> findPackingPaging(Packing p, int page) throws PackingDaoException {
		try {
			String corpId = p.getCorpId();
        	String whCode = p.getWhCode();
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
        			+"as (select a.packing_no, a.packing_date, ROW_NUMBER() over (order by packing_date desc) as id from packing a "
        			+"inner join packing_detail b on a.packing_no = b.packing_no where b.corp_id like '"+corpId+"' and b.wh_code like '"+whCode+"' "
        			+"group by a.packing_no, a.packing_date) " 
        			+"select * from PagedResult where id between " 
        			+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 " 
        			+"else @Page end and @PageSize * @Page " 
        			+"order by packing_date desc ");
        	
        	return jdbcTemplate.query(sb.toString(),new PackingListMap(),map);
        
        
        } catch (Exception e) {
            throw new PackingDaoException("Query failed", e);
        }
	}

}

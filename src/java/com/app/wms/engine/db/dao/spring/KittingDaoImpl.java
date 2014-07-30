package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.KittingDao;
import com.app.wms.engine.db.dto.Kitting;
import com.app.wms.engine.db.dto.KittingDetail;
import com.app.wms.engine.db.dto.KittingPk;
import com.app.wms.engine.db.dto.map.KittingDetailMap;
import com.app.wms.engine.db.dto.map.KittingListMap;
import com.app.wms.engine.db.dto.map.ProductListMap;
import com.app.wms.engine.db.exceptions.KittingDaoException;
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

public class KittingDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Kitting>, KittingDao
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
	 * @return KittingPk
	 */
	public KittingPk insert(Kitting dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( kitting_no, kitting_date, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getKittingNo(),dto.getKittingDate(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the kitting table.
	 */
	public void update(KittingPk pk, Kitting dto) throws KittingDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET kitting_no = ?, kitting_date = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE kitting_no = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getKittingNo(),dto.getKittingDate(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getKittingNo() } );
	}

	/** 
	 * Deletes a single row in the kitting table.
	 */
	@Transactional
	public void delete(KittingPk pk) throws KittingDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE kitting_no = ?",pk.getKittingNo());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Kitting
	 */
	public Kitting mapRow(ResultSet rs, int row) throws SQLException
	{
		Kitting dto = new Kitting();
		dto.setId( rs.getInt( 1 ) );
		dto.setKittingNo( rs.getString( 2 ) );
		dto.setKittingDate( rs.getTimestamp(3 ) );
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
		return "kitting";
	}

	/** 
	 * Returns all rows from the kitting table that match the criteria 'kitting_no = :kittingNo'.
	 */
	@Transactional
	public Kitting findByPrimaryKey(String kittingNo) throws KittingDaoException
	{
		try {
			List<Kitting> list = jdbcTemplate.query("SELECT ROW_NUMBER() over (order by kitting_no asc) as id, kitting_no, kitting_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE kitting_no = ?", this,kittingNo);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new KittingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the kitting table that match the criteria ''.
	 */
	@Transactional
	public List<Kitting> findAll() throws KittingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT kitting_no, kitting_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY kitting_no", this);
		}
		catch (Exception e) {
			throw new KittingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the kitting table that match the criteria 'kitting_no = :kittingNo'.
	 */
	@Transactional
	public List<Kitting> findWhereKittingNoEquals(String kittingNo) throws KittingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT kitting_no, kitting_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE kitting_no = ? ORDER BY kitting_no", this,kittingNo);
		}
		catch (Exception e) {
			throw new KittingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the kitting table that match the criteria 'kitting_date = :kittingDate'.
	 */
	@Transactional
	public List<Kitting> findWhereKittingDateEquals(Date kittingDate) throws KittingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT kitting_no, kitting_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE kitting_date = ? ORDER BY kitting_date", this,kittingDate);
		}
		catch (Exception e) {
			throw new KittingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the kitting table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<Kitting> findWhereCreatedByEquals(String createdBy) throws KittingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT kitting_no, kitting_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new KittingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the kitting table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<Kitting> findWhereCreatedDateEquals(Date createdDate) throws KittingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT kitting_no, kitting_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new KittingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the kitting table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<Kitting> findWhereUpdatedByEquals(String updatedBy) throws KittingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT kitting_no, kitting_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new KittingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the kitting table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<Kitting> findWhereUpdatedDateEquals(Date updatedDate) throws KittingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT kitting_no, kitting_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new KittingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the kitting table that matches the specified primary-key value.
	 */
	public Kitting findByPrimaryKey(KittingPk pk) throws KittingDaoException
	{
		return findByPrimaryKey( pk.getKittingNo() );
	}

	@Transactional
	public List<KittingDetail> findProductLocation(KittingDetail kittingDetail) throws KittingDaoException {
		try{
			Map map = new HashMap();
			StringBuffer sb = new StringBuffer();
			
			if (kittingDetail == null){
				kittingDetail = new KittingDetail();
			}
			
			String soNumber = kittingDetail.getSo_number();
			sb.append("select " 
					 +"c.so_number," 
					 +"a.product_id," 
					 +"b.product_code," 
					 +"b.product_name," 
					 +"a.qty_pick, "
					 +"a.unit_code "
			         +"from picking c " 
			         +"inner join picking_detail a on c.picking_id = a.picking_id "
			         +"inner join product b on a.product_id = b.product_id "
			         +"where c.so_number like '"+soNumber+"' "
			         +"and c.status_app like '%' "
			         +"order by a.product_id asc");	
		
			return jdbcTemplate.query(sb.toString(),new KittingDetailMap(),map);
		}
		catch(Exception e){
			 throw new KittingDaoException("Query failed", e);
		}
	}

	@Override
	public List<Kitting> findKittingPaging(Kitting k, int page) throws KittingDaoException {
		try {
			String corpId = k.getCorpId();
        	String whCode = k.getWhCode();
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
        			+"as (select a.kitting_no, a.kitting_date, ROW_NUMBER() over (order by kitting_date desc) as id from kitting a "
        			+"inner join kitting_detail b on a.kitting_no = b.kitting_no where b.corp_id like '"+corpId+"' and b.wh_code like '"+whCode+"' "
        			+"group by a.kitting_no, a.kitting_date) " 
        			+"select * from PagedResult where id between " 
        			+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 " 
        			+"else @Page end and @PageSize * @Page " 
        			+"order by kitting_date desc ");
        	
        	return jdbcTemplate.query(sb.toString(),new KittingListMap(),map);
        
        } catch (Exception e) {
            throw new KittingDaoException("Query failed", e);
        }
	}
	


}

package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.BorDao;
import com.app.wms.engine.db.dto.Bor;
import com.app.wms.engine.db.dto.BorPk;
import com.app.wms.engine.db.dto.map.BorListMap;
import com.app.wms.engine.db.exceptions.BorDaoException;
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

public class BorDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Bor>, BorDao
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
	 * @return BorPk
	 */
	public BorPk insert(Bor dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( bornumber, bordate, borreferensi, createdby, buyer_name ) VALUES ( ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getBornumber(),dto.getBordate(),dto.getBorreferensi(),dto.getCreatedby(),dto.getBuyerName()} );
		BorPk pk = new BorPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the bor table.
	 */
	public void update(BorPk pk, Bor dto) throws BorDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET bornumber = ?, bordate = ?, borreferensi = ?, createdby = ?, buyer_name = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getBornumber(),dto.getBordate(),dto.getBorreferensi(),dto.getCreatedby(),dto.getBuyerName(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the bor table.
	 */
	@Transactional
	public void delete(BorPk pk) throws BorDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Bor
	 */
	public Bor mapRow(ResultSet rs, int row) throws SQLException
	{
		Bor dto = new Bor();
		dto.setId( rs.getInt( 1 ) );
		dto.setBornumber( rs.getString( 2 ) );
		dto.setBordate( rs.getTimestamp(3 ) );
		dto.setBorreferensi( rs.getString( 4 ) );
		dto.setCreatedby( rs.getString( 5 ) );
		dto.setBuyerName( rs.getString( 6 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..bor";
	}

	/** 
	 * Returns all rows from the bor table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Bor findByPrimaryKey(int id) throws BorDaoException
	{
		try {
			List<Bor> list = jdbcTemplate.query("SELECT id, bornumber, bordate, borreferensi, createdby, buyer_name FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new BorDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bor table that match the criteria ''.
	 */
	@Transactional
	public List<Bor> findAll() throws BorDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, bornumber, bordate, borreferensi, createdby, buyer_name FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new BorDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bor table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Bor> findWhereIdEquals(int id) throws BorDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, bornumber, bordate, borreferensi, createdby, buyer_name FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new BorDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bor table that match the criteria 'bornumber = :bornumber'.
	 */
	@Transactional
	public List<Bor> findWhereBornumberEquals(String bornumber) throws BorDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, bornumber, bordate, borreferensi, createdby, buyer_name FROM " + getTableName() + " WHERE bornumber = ? ORDER BY bornumber", this,bornumber);
		}
		catch (Exception e) {
			throw new BorDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bor table that match the criteria 'bordate = :bordate'.
	 */
	@Transactional
	public List<Bor> findWhereBordateEquals(Date bordate) throws BorDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, bornumber, bordate, borreferensi, createdby, buyer_name FROM " + getTableName() + " WHERE bordate = ? ORDER BY bordate", this,bordate);
		}
		catch (Exception e) {
			throw new BorDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bor table that match the criteria 'borreferensi = :borreferensi'.
	 */
	@Transactional
	public List<Bor> findWhereBorreferensiEquals(String borreferensi) throws BorDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, bornumber, bordate, borreferensi, createdby, buyer_name FROM " + getTableName() + " WHERE borreferensi = ? ORDER BY borreferensi", this,borreferensi);
		}
		catch (Exception e) {
			throw new BorDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bor table that match the criteria 'createdby = :createdby'.
	 */
	@Transactional
	public List<Bor> findWhereCreatedbyEquals(String createdby) throws BorDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, bornumber, bordate, borreferensi, createdby, buyer_name FROM " + getTableName() + " WHERE createdby = ? ORDER BY createdby", this,createdby);
		}
		catch (Exception e) {
			throw new BorDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bor table that match the criteria 'buyer_name = :buyerName'.
	 */
	@Transactional
	public List<Bor> findWhereBuyerNameEquals(String buyerName) throws BorDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, bornumber, bordate, borreferensi, createdby, buyer_name FROM " + getTableName() + " WHERE buyer_name = ? ORDER BY buyer_name", this,buyerName);
		}
		catch (Exception e) {
			throw new BorDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the bor table that matches the specified primary-key value.
	 */
	public Bor findByPrimaryKey(BorPk pk) throws BorDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

	@Override
	public List<Bor> findBorPaging(Bor b, int page) throws BorDaoException {
		try{
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        	String borNo = b.getBornumber();
        	String borDate = df.format(b.getBordate());
        	
        	int i = page;
        	Map map = new HashMap();
        	map.put("i", i);
        	
        	StringBuffer sb = new StringBuffer();
        	
    		sb.append("declare @Page int, @PageSize int "
    				+"set @Page = '"+i+"'; "
    				+"set @PageSize = 10; "
    				+"with PagedResult "
    				+"as (select ROW_NUMBER() over (order by id asc) as id, bornumber, bordate from bor" +
    						" where bornumber like '%"+borNo+"%' and bordate='"+borDate+"' ) "
    				    +"select * from PagedResult where id between "
    				+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
    				     +"else @Page end and @PageSize * @Page ");
   
        	return jdbcTemplate.query(sb.toString(),new BorListMap(),map);	
        
		}catch(Exception e){
			e.printStackTrace();
			throw new BorDaoException("Query failed", e);
		}
	}

}

package com.app.wms.engine.db.dao.spring;

import com.app.web.engine.search.WarehouseSearch;
import com.app.wms.engine.db.dao.WhDao;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.dto.WhPk;
import com.app.wms.engine.db.dto.map.CorporateListMap;
import com.app.wms.engine.db.dto.map.WarehouseListMap;
import com.app.wms.engine.db.exceptions.CorpDaoException;
import com.app.wms.engine.db.exceptions.UserDaoException;
import com.app.wms.engine.db.exceptions.WhDaoException;
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

public class WhDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Wh>, WhDao
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
	 * @return WhPk
	 */
	public WhPk insert(Wh dto)
	{
		
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( code, name, region, is_active, is_delete, created_by, created_date, updated_by, updated_date, corp_id ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getCode(),dto.getName(),dto.getRegion(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),dto.getCorpId()} );
		WhPk pk = new WhPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
		
	}

	/** 
	 * Updates a single row in the wh table.
	 */
	public void update(WhPk pk, Wh dto) throws WhDaoException
	{
		System.out.println("dto update ="+dto);
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET code = ?, name = ?, region = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ?, corp_id = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getCode(),dto.getName(),dto.getRegion(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(), dto.getCorpId(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the wh table.
	 */
	@Transactional
	public void delete(WhPk pk) throws WhDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Wh
	 */
	public Wh mapRow(ResultSet rs, int row) throws SQLException
	{
		Wh dto = new Wh();
		dto.setId( rs.getInt( 1 ) );
		dto.setWhCode( rs.getString( 2 ) );
		dto.setCode( rs.getString( 3 ) );
		dto.setName( rs.getString( 4 ) );
		dto.setRegion( rs.getString( 5 ) );
		dto.setIsActive( rs.getString( 6 ) );
		dto.setIsDelete( rs.getString( 7 ) );
		dto.setCreatedBy( rs.getString( 8 ) );
		dto.setCreatedDate( rs.getTimestamp(9 ) );
		dto.setUpdatedBy( rs.getString( 10 ) );
		dto.setUpdatedDate( rs.getTimestamp(11 ) );
		dto.setCorpId(rs.getString( 12 ));
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "wh";
	}

	/** 
	 * Returns all rows from the wh table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Wh findByPrimaryKey(int id) throws WhDaoException
	{
		try {
			List<Wh> list = jdbcTemplate.query("SELECT id, wh_code, code, name, region, is_active, is_delete, created_by, created_date, updated_by, updated_date, corp_id FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new WhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh table that match the criteria ''.
	 */
	@Transactional
	public List<Wh> findAll() throws WhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, code, name, region, is_active, is_delete, created_by, created_date, updated_by, updated_date, corp_id FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new WhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Wh> findWhereIdEquals(int id) throws WhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, code, name, region, is_active, is_delete, created_by, created_date, updated_by, updated_date, corp_id FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new WhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<Wh> findWhereWhCodeEquals(String whCode) throws WhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, code, name, region, is_active, is_delete, created_by, created_date, updated_by, updated_date, corp_id FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new WhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh table that match the criteria 'code = :code'.
	 */
	@Transactional
	public List<Wh> findWhereCodeEquals(String code) throws WhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, code, name, region, is_active, is_delete, created_by, created_date, updated_by, updated_date, corp_id FROM " + getTableName() + " WHERE code = ? ORDER BY code", this,code);
		}
		catch (Exception e) {
			throw new WhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh table that match the criteria 'name = :name'.
	 */
	@Transactional
	public List<Wh> findWhereNameEquals(String name) throws WhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, code, name, region, is_active, is_delete, created_by, created_date, updated_by, updated_date, corp_id FROM " + getTableName() + " WHERE name = ? ORDER BY name", this,name);
		}
		catch (Exception e) {
			throw new WhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh table that match the criteria 'region = :region'.
	 */
	@Transactional
	public List<Wh> findWhereRegionEquals(String region) throws WhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, code, name, region, is_active, is_delete, created_by, created_date, updated_by, updated_date, corp_id FROM " + getTableName() + " WHERE region = ? ORDER BY region", this,region);
		}
		catch (Exception e) {
			throw new WhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<Wh> findWhereIsActiveEquals(String isActive) throws WhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, code, name, region, is_active, is_delete, created_by, created_date, updated_by, updated_date, corp_id FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new WhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<Wh> findWhereIsDeleteEquals(String isDelete) throws WhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, code, name, region, is_active, is_delete, created_by, created_date, updated_by, updated_date, corp_id FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new WhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<Wh> findWhereCreatedByEquals(String createdBy) throws WhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, code, name, region, is_active, is_delete, created_by, created_date, updated_by, updated_date, corp_id FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new WhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<Wh> findWhereCreatedDateEquals(Date createdDate) throws WhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, code, name, region, is_active, is_delete, created_by, created_date, updated_by, updated_date, corp_id FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new WhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<Wh> findWhereUpdatedByEquals(String updatedBy) throws WhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, code, name, region, is_active, is_delete, created_by, created_date, updated_by, updated_date, corp_id FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new WhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<Wh> findWhereUpdatedDateEquals(Date updatedDate) throws WhDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, wh_code, code, name, region, is_active, is_delete, created_by, created_date, updated_by, updated_date, corp_id FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new WhDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the wh table that matches the specified primary-key value.
	 */
	public Wh findByPrimaryKey(WhPk pk) throws WhDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

	@Override
	public Wh findByPrimaryKey(String whCode) throws WhDaoException {
		 try {
	            List<Wh> list = jdbcTemplate.query("SELECT ID, WH_CODE, CODE, NAME, REGION, IS_ACTIVE, IS_DELETE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, corp_id FROM " + getTableName() + " WHERE WH_CODE = ?", this, whCode);
	            return list.size() == 0 ? null : list.get(0);
	        } catch (Exception e) {
	            throw new WhDaoException("Query failed", e);
	        }
	}

	@Transactional
	public List<Wh> findByCriteriaLimit(WarehouseSearch criteria, int start, int end) throws WhDaoException {

        if (criteria == null) {
        
            criteria = new WarehouseSearch();
        }
        try {
         
            criteria.setTableAlias(this.getTableName()); 
         
            HashMap ret = criteria.getCriteria();
            HashMap param = (HashMap) ret.get("parameter");
        
            param.put("pagestart", start);
            param.put("pageend", end);

            String search = (String) ret.get("search");

            String strQuery = "SELECT ID, WH_CODE, CODE, NAME, REGION, IS_ACTIVE, IS_DELETE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, corp_id FROM WH"; 
            
            return jdbcTemplate.query(strQuery, this, param); 
        } catch (Exception e) {
            throw new WhDaoException("Query failed", e);
        }
	}

	@Override
	public List<Wh> findWhPaging(WarehouseSearch c ,int page) throws WhDaoException {
		try {
			StringBuffer sb = new StringBuffer();
        	int i = page;
        	Map map = new HashMap();
        	map.put("i", i);
        	
        	if(c == null){
        		c = new WarehouseSearch();
        	}
        	
        	String whCode = c.getWhCode();
        	String name   = c.getName();
        	
        	if (whCode == null || name == null){
        		whCode = "%";
        		name   = "%";
        		
        		sb.append("declare @Page int, @PageSize int "  
            			+"set @Page = '"+i+"'; "  
            			+"set @PageSize = 10; "  
            			+"with PagedResult "  
            			+"as (select ROW_NUMBER() over (order by wh_code desc) as id, wh_code, code, name, region, corp_id, is_active from wh where wh_code like '%"+whCode+"%' and name like '%"+name+"%') " 
            			+"select * from PagedResult where id between "  
            			+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "  
            			+"else @Page end and @PageSize * @Page");
            	
        	}else
        	{
        		
        		sb.append("declare @Page int, @PageSize int "  
            			+"set @Page = '"+i+"'; "  
            			+"set @PageSize = 10; "  
            			+"with PagedResult "  
            			+"as (select ROW_NUMBER() over (order by wh_code desc) as id, wh_code, code, name, region, corp_id, is_active from wh where wh_code like '%"+whCode+"%' and name like '%"+name+"%') " 
            			+"select * from PagedResult where id between "  
            			+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "  
            			+"else @Page end and @PageSize * @Page");
        		
        	}
        	
        	
        	return jdbcTemplate.query(sb.toString(),new WarehouseListMap(),map);	
        
        } catch (Exception e) {
            throw new WhDaoException("Query failed", e);
        }
	}

	@Override
	public List<Wh> findWhereCorpId(String corpId) throws WhDaoException {
		try {
			return jdbcTemplate.query("SELECT id, wh_code, code, name, region, is_active, is_delete, created_by, created_date, updated_by, updated_date, corp_id FROM " + getTableName() + " WHERE corp_id = ? ", this,corpId);
		} catch (Exception e) {
            throw new WhDaoException("Query failed", e);
        }
	}


}

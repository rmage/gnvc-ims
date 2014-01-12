package com.app.wms.engine.db.dao.spring;

import com.app.web.engine.search.CorporateSearch;
import com.app.wms.engine.db.dao.CorpDao;
import com.app.wms.engine.db.dto.Corp;
import com.app.wms.engine.db.dto.CorpPk;
import com.app.wms.engine.db.dto.Corporate;
import com.app.wms.engine.db.dto.map.CorporateListMap;
import com.app.wms.engine.db.dto.map.ProductListMap;
import com.app.wms.engine.db.exceptions.CorpDaoException;
import com.app.wms.engine.db.exceptions.CorporateDaoException;
import com.app.wms.engine.db.exceptions.ProductDaoException;

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

public class CorpDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Corp>, CorpDao
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
	 * @return CorpPk
	 */
	public CorpPk insert(Corp dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
//		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getCorpId(), dto.getCorpName(),dto.getAddress1(),dto.getAddress2(),dto.getAddress3(),dto.getEmail(),dto.getCityCode(),dto.getZipcode(),dto.getPhone1(),dto.getPhone2(),dto.getFax(),dto.getProvinceCode(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the corp table.
	 */
	public void update(CorpPk pk, Corp dto) throws CorpDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET corp_name = ?, address1 = ?, address2 = ?, address3 = ?, email = ?, city_code = ?, zipcode = ?, phone1 = ?, phone2 = ?, fax = ?, province_code = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE corp_id = ?");
		//su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
//		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
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
		su.update( new Object[] { dto.getCorpName(),dto.getAddress1(),dto.getAddress2(),dto.getAddress3(),dto.getEmail(),dto.getCityCode(),dto.getZipcode(),dto.getPhone1(),dto.getPhone2(),dto.getFax(),dto.getProvinceCode(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getCorpId() } );
	}

	/** 
	 * Deletes a single row in the corp table.
	 */
	@Transactional
	public void delete(CorpPk pk) throws CorpDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE corp_id = ?",pk.getCorpId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Corp
	 */
	public Corp mapRow(ResultSet rs, int row) throws SQLException
	{
		Corp dto = new Corp();
		dto.setCorpId( rs.getString( 1 ) );
		dto.setCorpName( rs.getString( 2 ) );
//		dto.setWhCode( rs.getString( 3 ) );
		dto.setAddress1( rs.getString( 3 ) );
		dto.setAddress2( rs.getString( 4 ) );
		dto.setAddress3( rs.getString( 5 ) );
		dto.setEmail( rs.getString( 6 ) );
		dto.setCityCode( rs.getString( 7 ) );
		dto.setZipcode( rs.getString( 8 ) );
		dto.setPhone1( rs.getString( 9 ) );
		dto.setPhone2( rs.getString( 10 ) );
		dto.setFax( rs.getString( 11 ) );
		dto.setProvinceCode( rs.getString( 12 ) );
		dto.setIsActive( rs.getString( 13 ) );
		dto.setIsDelete( rs.getString( 14 ) );
		dto.setCreatedBy( rs.getString( 15 ) );
		dto.setCreatedDate( rs.getTimestamp(16 ) );
		dto.setUpdatedBy( rs.getString( 17 ) );
		dto.setUpdatedDate( rs.getTimestamp(18 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..corp";
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'corp_id = :corpId'.
	 */
	@Transactional
	public Corp findByPrimaryKey(String corpId) throws CorpDaoException
	{
		try {
			List<Corp> list = jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE corp_id = ?", this,corpId);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria ''.
	 */
	@Transactional
	public List<Corp> findAll() throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY corp_id", this);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'corp_id = :corpId'.
	 */
	@Transactional
	public List<Corp> findWhereCorpIdEquals(String corpId) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE corp_id = ? ORDER BY corp_id", this,corpId);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'corp_name = :corpName'.
	 */
	@Transactional
	public List<Corp> findWhereCorpNameEquals(String corpName) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE corp_name = ? ORDER BY corp_name", this,corpName);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'wh_code = :whCode'.
	 */
	
	/*
	public List<Corp> findWhereWhCodeEquals(String whCode) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}
	*/
	/** 
	 * Returns all rows from the corp table that match the criteria 'address1 = :address1'.
	 */
	@Transactional
	public List<Corp> findWhereAddress1Equals(String address1) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE address1 = ? ORDER BY address1", this,address1);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'address2 = :address2'.
	 */
	@Transactional
	public List<Corp> findWhereAddress2Equals(String address2) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE address2 = ? ORDER BY address2", this,address2);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'address3 = :address3'.
	 */
	@Transactional
	public List<Corp> findWhereAddress3Equals(String address3) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE address3 = ? ORDER BY address3", this,address3);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'email = :email'.
	 */
	@Transactional
	public List<Corp> findWhereEmailEquals(String email) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE email = ? ORDER BY email", this,email);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'city_code = :cityCode'.
	 */
	@Transactional
	public List<Corp> findWhereCityCodeEquals(String cityCode) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE city_code = ? ORDER BY city_code", this,cityCode);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'zipcode = :zipcode'.
	 */
	@Transactional
	public List<Corp> findWhereZipcodeEquals(String zipcode) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE zipcode = ? ORDER BY zipcode", this,zipcode);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'phone1 = :phone1'.
	 */
	@Transactional
	public List<Corp> findWherePhone1Equals(String phone1) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE phone1 = ? ORDER BY phone1", this,phone1);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'phone2 = :phone2'.
	 */
	@Transactional
	public List<Corp> findWherePhone2Equals(String phone2) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE phone2 = ? ORDER BY phone2", this,phone2);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'fax = :fax'.
	 */
	@Transactional
	public List<Corp> findWhereFaxEquals(String fax) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE fax = ? ORDER BY fax", this,fax);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'province_code = :provinceCode'.
	 */
	@Transactional
	public List<Corp> findWhereProvinceCodeEquals(String provinceCode) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE province_code = ? ORDER BY province_code", this,provinceCode);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<Corp> findWhereIsActiveEquals(String isActive) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<Corp> findWhereIsDeleteEquals(String isDelete) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<Corp> findWhereCreatedByEquals(String createdBy) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<Corp> findWhereCreatedDateEquals(Date createdDate) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<Corp> findWhereUpdatedByEquals(String updatedBy) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the corp table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<Corp> findWhereUpdatedDateEquals(Date updatedDate) throws CorpDaoException
	{
		try {
			return jdbcTemplate.query("SELECT corp_id, corp_name, address1, address2, address3, email, city_code, zipcode, phone1, phone2, fax, province_code, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new CorpDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the corp table that matches the specified primary-key value.
	 */
	public Corp findByPrimaryKey(CorpPk pk) throws CorpDaoException
	{
		return findByPrimaryKey( pk.getCorpId() );
	}

	@Transactional
	public List<Corp> findByCriteriaLimit(CorporateSearch criteria, int start, int end) throws CorpDaoException {

        if (criteria == null) {
        
            System.out.println("Search Corporate with no criteria");
            criteria = new CorporateSearch();
        }
        try {
         
            criteria.setTableAlias(this.getTableName()); 
         
            HashMap ret = criteria.getCriteria();
            HashMap param = (HashMap) ret.get("parameter");
        
            param.put("pagestart", start);
            param.put("pageend", end);

            String search = (String) ret.get("search");

            String strQuery = 
            "select corp_id, corp_name, address1, address2, address3, email, city_code, zipcode,"
           +"phone1,phone2,fax,province_code,is_active,is_delete,created_by,created_date,updated_by," 
           +"updated_date from corp";         

            return jdbcTemplate.query(strQuery, this, param); 
        } catch (Exception e) {
            throw new CorpDaoException("Query failed", e);
        }
	}

	@Override
	public List<Corp> findCorporatePaging(CorporateSearch c, int page) throws CorpDaoException {
		try {
			StringBuffer sb = new StringBuffer();
			int i = page;
        	Map map = new HashMap();
        	map.put("i", i);
        	
        	if(c == null){
        		c = new CorporateSearch();
        	}
        	
        	String corpId = c.getId();
    		String name   = c.getName();
        	
        	if(corpId == null || name == null){
   			 
        		corpId = "%";
       		 	name   = "%";
				
				sb.append("declare @Page int, @PageSize int " 
	        			+"set @Page = '"+i+"'; " 
	        			+"set @PageSize = 10; " 
	        			+"with PagedResult " 
	        			+"as (select ROW_NUMBER() over (order by created_date desc) as id, corp_id, corp_name, is_active from corp where corp_id like '%"+corpId+"%' and corp_name like '%"+name+"%') "
	        			+"select * from PagedResult where id between " 
	        			+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 " 
	        			+"else @Page end and @PageSize * @Page");
        	}else 
        	{
        		
        		sb.append("declare @Page int, @PageSize int " 
	        			+"set @Page = '"+i+"'; " 
	        			+"set @PageSize = 10; " 
	        			+"with PagedResult " 
	        			+"as (select ROW_NUMBER() over (order by created_date desc) as id, corp_id, corp_name, is_active from corp where corp_id like '%"+corpId+"%' and corp_name like '%"+name+"%') "
	        			+"select * from PagedResult where id between " 
	        			+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 " 
	        			+"else @Page end and @PageSize * @Page");
        		
        	}
			
        	return jdbcTemplate.query(sb.toString(),new CorporateListMap(),map);	
        
        } catch (Exception e) {
            throw new CorpDaoException("Query failed", e);
        }
	}

}

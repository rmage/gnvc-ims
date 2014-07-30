package com.app.wms.engine.db.dao.spring;
//
import com.app.wms.engine.db.dao.ConsigneeDao;
import com.app.wms.engine.db.dto.Consignee;
import com.app.wms.engine.db.dto.ConsigneePk;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.dto.map.ConsigneeListMap;
import com.app.wms.engine.db.dto.map.PutawayDetailProductMap;
import com.app.wms.engine.db.exceptions.ConsigneeDaoException;
import com.app.wms.engine.db.exceptions.PutawayDetailDaoException;

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

public class ConsigneeDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Consignee>, ConsigneeDao
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
	 * @return ConsigneePk
	 */
	public ConsigneePk insert(Consignee dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( consignee_name, consignee_pic, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		//su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
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
		su.update( new Object[] { dto.getConsigneeName(),dto.getConsigneePIC(),dto.getAddress1(),dto.getAddress2(),dto.getAddress3(),dto.getConsigneePhone(),dto.getUserId(),dto.getCorpId(),dto.getWhCode(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the consignee table.
	 */
	public void update(ConsigneePk pk, Consignee dto) throws ConsigneeDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET consignee_name = ?, consignee_pic = ?, address1 = ?, address2 = ?, address3 = ?, consignee_phone = ?, user_id = ?, corp_id = ?, wh_code = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE consignee_code = ?");
		//su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
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
		su.update( new Object[] { dto.getConsigneeName(),dto.getConsigneePIC(),dto.getAddress1(),dto.getAddress2(),dto.getAddress3(),dto.getConsigneePhone(),dto.getUserId(),dto.getCorpId(),dto.getWhCode(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getConsigneeCode() } );
	}

	/** 
	 * Deletes a single row in the consignee table.
	 */
	@Transactional
	public void delete(ConsigneePk pk) throws ConsigneeDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE consignee_code = ?",pk.getConsigneeCode());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Consignee
	 */
	public Consignee mapRow(ResultSet rs, int row) throws SQLException
	{
		Consignee dto = new Consignee();
		//dto.setId( rs.getInt( 1 ) );
		dto.setConsigneeCode( rs.getString( 1 ) );
		dto.setConsigneeName( rs.getString( 2 ) );
		dto.setAddress1( rs.getString( 3 ) );
		dto.setAddress2( rs.getString( 4 ) );
		dto.setAddress3( rs.getString( 5 ) );
		dto.setConsigneePhone( rs.getString( 6 ) );
		dto.setUserId( rs.getString( 7 ) );
		dto.setCorpId( rs.getString( 8 ) );
		dto.setWhCode( rs.getString( 9 ) );
		dto.setIsActive( rs.getString( 10 ) );
		dto.setIsDelete( rs.getString( 11 ) );
		dto.setCreatedBy( rs.getString( 12 ) );
		dto.setCreatedDate( rs.getTimestamp(13 ) );
		dto.setUpdatedBy( rs.getString( 14 ) );
		dto.setUpdatedDate( rs.getTimestamp(15 ) );
		dto.setConsigneePIC( rs.getString( 16 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "consignee";
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Consignee findByPrimaryKey(int id) throws ConsigneeDaoException
	{
		try {
			List<Consignee> list = jdbcTemplate.query("SELECT id, consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}
	
	/** 
	 * Returns all rows from the consignee table that match the criteria 'consigneeCode = :consigneeCode'.
	 */
	@Transactional
	public Consignee findByPrimaryKey(String consigneeCode) throws ConsigneeDaoException
	{
		try {
			List<Consignee> list = jdbcTemplate.query("SELECT consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE consignee_code = ?", this,consigneeCode);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria ''.
	 */
	@Transactional
	public List<Consignee> findAll() throws ConsigneeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Consignee> findWhereIdEquals(int id) throws ConsigneeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria 'consignee_code = :consigneeCode'.
	 */
	@Transactional
	public List<Consignee> findWhereConsigneeCodeEquals(String consigneeCode) throws ConsigneeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE consignee_code = ? ORDER BY consignee_code", this,consigneeCode);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria 'consignee_name = :consigneeName'.
	 */
	@Transactional
	public List<Consignee> findWhereConsigneeNameEquals(String consigneeName) throws ConsigneeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE consignee_name = ? ORDER BY consignee_name", this,consigneeName);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria 'address1 = :address1'.
	 */
	@Transactional
	public List<Consignee> findWhereAddress1Equals(String address1) throws ConsigneeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE address1 = ? ORDER BY address1", this,address1);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria 'address2 = :address2'.
	 */
	@Transactional
	public List<Consignee> findWhereAddress2Equals(String address2) throws ConsigneeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE address2 = ? ORDER BY address2", this,address2);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria 'address3 = :address3'.
	 */
	@Transactional
	public List<Consignee> findWhereAddress3Equals(String address3) throws ConsigneeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE address3 = ? ORDER BY address3", this,address3);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria 'consignee_phone = :consigneePhone'.
	 */
	@Transactional
	public List<Consignee> findWhereConsigneePhoneEquals(String consigneePhone) throws ConsigneeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE consignee_phone = ? ORDER BY consignee_phone", this,consigneePhone);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria 'user_id = :userId'.
	 */
	@Transactional
	public List<Consignee> findWhereUserIdEquals(String userId) throws ConsigneeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE user_id = ? ORDER BY user_id", this,userId);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria 'corp_id = :corpId'.
	 */
	@Transactional
	public List<Consignee> findWhereCorpIdEquals(String corpId) throws ConsigneeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE corp_id = ? ORDER BY corp_id", this,corpId);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<Consignee> findWhereWhCodeEquals(String whCode) throws ConsigneeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<Consignee> findWhereIsActiveEquals(String isActive) throws ConsigneeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<Consignee> findWhereIsDeleteEquals(String isDelete) throws ConsigneeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<Consignee> findWhereCreatedByEquals(String createdBy) throws ConsigneeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<Consignee> findWhereCreatedDateEquals(Date createdDate) throws ConsigneeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<Consignee> findWhereUpdatedByEquals(String updatedBy) throws ConsigneeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consignee table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<Consignee> findWhereUpdatedDateEquals(Date updatedDate) throws ConsigneeDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, consignee_code, consignee_name, address1, address2, address3, consignee_phone, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, consignee_pic FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new ConsigneeDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the consignee table that matches the specified primary-key value.
	 */
	public Consignee findByPrimaryKey(ConsigneePk pk) throws ConsigneeDaoException
	{
		return findByPrimaryKey( pk.getConsigneeCode());
	}

	@Override
	public List<Consignee> findConsigneePaging(Consignee c, Integer page) throws ConsigneeDaoException {
		try {
        	String corpId = c.getCorpId();
        	String whCode = c.getWhCode();
        	
        	String consigneeCode = c.getConsigneeCode();
        	String consigneeName = c.getConsigneeName();
        	
        	int i = page;
        	Map map = new HashMap();
        	map.put("corpId", corpId);
        	map.put("whCode", whCode);
        	map.put("i", i);
        
        	StringBuffer sb = new StringBuffer();
        	
        	if(c == null){
        		c = new Consignee();
        	}
        	
        	if(consigneeCode == null || consigneeName == null){
        		consigneeCode = "%";
        		consigneeName = "%";
        		
        		sb.append("declare @Page int, @PageSize int " 
            			+"set @Page = '"+i+"'; " 
            			+"set @PageSize = 10; " 
            			+"with PagedResult " 
            			+"as (select ROW_NUMBER() over (order by consignee_code desc) as id, consignee_code, consignee_name, consignee_pic, address1, address2, address3, consignee_phone, is_active from consignee "
            			+"where corp_id like '"+corpId+"' and wh_code like '"+whCode+"' and consignee_code like '%"+consigneeCode+"%' and consignee_name like '%"+consigneeName+"%' ) " 
            			+"select * from PagedResult where id between " 
            			+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 " 
            			+"else @Page end and @PageSize * @Page ");
            	
        	}else{
        		
        		sb.append("declare @Page int, @PageSize int " 
            			+"set @Page = '"+i+"'; " 
            			+"set @PageSize = 10; " 
            			+"with PagedResult " 
            			+"as (select ROW_NUMBER() over (order by consignee_code desc) as id, consignee_code, consignee_name, consignee_pic, address1, address2, address3, consignee_phone, is_active from consignee "
            			+"where corp_id like '"+corpId+"' and wh_code like '"+whCode+"' and consignee_code like '%"+consigneeCode+"%' and consignee_name like '%"+consigneeName+"%' ) " 
            			+"select * from PagedResult where id between " 
            			+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 " 
            			+"else @Page end and @PageSize * @Page ");
        		
        	}

        	
        	return jdbcTemplate.query(sb.toString(),new ConsigneeListMap(),map);	
        
        } catch (Exception e) {
            throw new ConsigneeDaoException("Query failed", e);
        }
	}

	@Override
	public List<Consignee> findWhereConsignee(Consignee c) throws ConsigneeDaoException {
		// TODO Auto-generated method stub
	try{
			
			if (c == null){
				c = new Consignee();
			}
			String corpId = c.getCorpId();
			String whCode = c.getWhCode();
			
			Map map = new HashMap();
			map.put("corpId", corpId);
			map.put("whCode", whCode);
			
			StringBuffer sb = new StringBuffer();
			
			sb.append("select " 
					 +"a.consignee_code, a.consignee_name, a.address1, a.address2, a.address3, a.consignee_phone, a.user_id, a.corp_id, a.wh_code, a.is_active, a.is_delete, a.created_by, a.created_date, a.updated_by, a.updated_date, a.consignee_pic " 
			         +"from consignee a " 
			         +"where a.wh_code like '"+whCode+"' "
			         +"and a.corp_id like '"+corpId+"' ");
			        
			return jdbcTemplate.query(sb.toString(),this,map);
		}
		catch(Exception e){
			 throw new ConsigneeDaoException("Query failed", e);
		}
	}

}

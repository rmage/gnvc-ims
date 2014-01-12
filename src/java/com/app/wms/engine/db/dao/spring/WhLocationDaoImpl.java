package com.app.wms.engine.db.dao.spring;

import com.app.web.engine.search.UserSearch;
import com.app.web.engine.search.WarehouseLocationSearch;
import com.app.wms.engine.db.dao.WhLocationDao;
import com.app.wms.engine.db.dto.WhLocation;
import com.app.wms.engine.db.dto.WhLocationPk;
import com.app.wms.engine.db.dto.map.LocationListMap;
import com.app.wms.engine.db.dto.map.ProductListMap;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.exceptions.UserDaoException;
import com.app.wms.engine.db.exceptions.WhLocationDaoException;
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

public class WhLocationDaoImpl extends AbstractDAO implements ParameterizedRowMapper<WhLocation>, WhLocationDao
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
	 * @return WhLocationPk
	 */
	public WhLocationPk insert(WhLocation dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		//su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
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
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getLocationCode(),dto.getLocationName(),dto.getLocationType(),dto.getMinProduct(),dto.getMaxProduct(),dto.getLocatingArea(),dto.getLocatingZone(),dto.getLocationBay(),dto.getLocationLevel(),dto.getLocationPosition(),dto.getAllocationZone(),dto.getWorkZone(),dto.getIsActive(),dto.getIsDelete(),dto.getUserId(),dto.getCorpId(),dto.getWhCode(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the wh_location table.
	 */
	public void update(WhLocationPk pk, WhLocation dto) throws WhLocationDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET location_code = ?, location_name = ?, location_type = ?, min_product = ?, max_product = ?, locating_area = ?, locating_zone = ?, location_bay = ?, location_level = ?, location_position = ?, allocation_zone = ?, work_zone = ?, is_active = ?, is_delete = ?, user_id = ?, corp_id = ?, wh_code = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE location_id = ?");
		//su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
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
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getLocationCode(),dto.getLocationName(),dto.getLocationType(),dto.getMinProduct(),dto.getMaxProduct(),dto.getLocatingArea(),dto.getLocatingZone(),dto.getLocationBay(),dto.getLocationLevel(),dto.getLocationPosition(),dto.getAllocationZone(),dto.getWorkZone(),dto.getIsActive(),dto.getIsDelete(),dto.getUserId(),dto.getCorpId(),dto.getWhCode(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),dto.getLocationId() } );
	}

	/** 
	 * Deletes a single row in the wh_location table.
	 */
	@Transactional
	public void delete(WhLocationPk pk) throws WhLocationDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return WhLocation
	 */
	public WhLocation mapRow(ResultSet rs, int row) throws SQLException
	{
		WhLocation dto = new WhLocation();

		dto.setLocationId( rs.getString( 1 ) );
		dto.setLocationCode( rs.getString( 2 ) );
		dto.setLocationName( rs.getString( 3 ) );
		dto.setLocationType( rs.getString( 4 ) );
		dto.setMinProduct( rs.getInt( 5 ) );
		if (rs.wasNull()) {
			dto.setMinProductNull( true );
		}
		
		dto.setMaxProduct( rs.getInt( 6 ) );
		if (rs.wasNull()) {
			dto.setMaxProductNull( true );
		}
		
		dto.setLocatingArea( rs.getString( 7 ) );
		dto.setLocatingZone( rs.getString( 8 ) );
		dto.setLocationBay( rs.getString( 9 ) );
		dto.setLocationLevel( rs.getString( 10 ) );
		dto.setLocationPosition( rs.getString( 11 ) );
		dto.setAllocationZone( rs.getString( 12 ) );
		dto.setWorkZone( rs.getString( 13 ) );
		dto.setIsActive( rs.getString( 14 ) );
		dto.setIsDelete( rs.getString( 15 ) );
		dto.setUserId( rs.getString( 16 ) );
		dto.setCorpId( rs.getString( 17 ) );
		dto.setWhCode( rs.getString( 18 ) );
		dto.setCreatedBy( rs.getString( 19 ) );
		dto.setCreatedDate( rs.getTimestamp(20 ) );
		dto.setUpdatedBy( rs.getString( 21 ) );
		dto.setUpdatedDate( rs.getTimestamp(22 ) );

		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..wh_location";
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'id = :id'.
	 */
	@Transactional
	public WhLocation findByPrimaryKey(String locationId) throws WhLocationDaoException
	{
		try {
			List<WhLocation> list = jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE location_id = ?", this,locationId);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria ''.
	 */
	@Transactional
	public List<WhLocation> findAll() throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY location_code", this);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<WhLocation> findWhereIdEquals(int id) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'location_id = :locationId'.
	 */
	@Transactional
	public List<WhLocation> findWhereLocationIdEquals(String locationId) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE location_id = ? ORDER BY location_id", this,locationId);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}
	
	/** 
	 * Returns all rows from the wh_location table that match the criteria 'location_id = :locationId'.
	 */
	@Transactional
	public List<WhLocation> findWhereLocationIdEquals(WhLocation location, String locationId) throws WhLocationDaoException
	{
		try {
        	String corpId = location.getCorpId();
        	String whCode = location.getWhCode();
        	
        	Map map = new HashMap();
        	map.put("locationId", locationId);
        	map.put("corpId", corpId);
        	map.put("whCode", whCode);
        	
        	StringBuffer sb = new StringBuffer();
        	
        	sb.append(" select a.location_id, a.location_code, a.location_name, a.location_type, a.min_product, a.max_product, a.locating_area, a.locating_zone, " 
        			+"a.location_bay, a.location_level, a.location_position, a.allocation_zone, a.work_zone, a.is_active, a.is_delete, a.user_id, a.corp_id, " 
        			+"a.wh_code, a.created_by, a.created_date, a.updated_by, a.updated_date, "    
        			+"b.product_code, b.product_name, b.unit_code from wh_location a " 
        			+"inner join wh_location_detail b on a.location_id = b.location_id where a.location_id like '"+locationId+"' " 
        			+"and a.corp_id like '"+corpId+"' and a.wh_code like '"+whCode+"' ");
        	
        	return jdbcTemplate.query(sb.toString(),null,map);	
        
        }
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'location_code = :locationCode'.
	 */
	@Transactional
	public List<WhLocation> findWhereLocationCodeEquals(String locationCode) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE location_code = ? ORDER BY location_code", this,locationCode);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'location_name = :locationName'.
	 */
	@Transactional
	public List<WhLocation> findWhereLocationNameEquals(String locationName) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE location_name = ? ORDER BY location_name", this,locationName);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'location_type = :locationType'.
	 */
	@Transactional
	public List<WhLocation> findWhereLocationTypeEquals(String locationType) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE location_type = ? ORDER BY location_type", this,locationType);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'min_product = :minProduct'.
	 */
	@Transactional
	public List<WhLocation> findWhereMinProductEquals(int minProduct) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE min_product = ? ORDER BY min_product", this,minProduct);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'max_product = :maxProduct'.
	 */
	@Transactional
	public List<WhLocation> findWhereMaxProductEquals(int maxProduct) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE max_product = ? ORDER BY max_product", this,maxProduct);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'locating_area = :locatingArea'.
	 */
	@Transactional
	public List<WhLocation> findWhereLocatingAreaEquals(String locatingArea) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE locating_area = ? ORDER BY locating_area", this,locatingArea);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'locating_zone = :locatingZone'.
	 */
	@Transactional
	public List<WhLocation> findWhereLocatingZoneEquals(String locatingZone) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE locating_zone = ? ORDER BY locating_zone", this,locatingZone);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'location_bay = :locationBay'.
	 */
	@Transactional
	public List<WhLocation> findWhereLocationBayEquals(String locationBay) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE location_bay = ? ORDER BY location_bay", this,locationBay);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'location_level = :locationLevel'.
	 */
	@Transactional
	public List<WhLocation> findWhereLocationLevelEquals(String locationLevel) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE location_level = ? ORDER BY location_level", this,locationLevel);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'location_position = :locationPosition'.
	 */
	@Transactional
	public List<WhLocation> findWhereLocationPositionEquals(String locationPosition) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE location_position = ? ORDER BY location_position", this,locationPosition);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'allocation_zone = :allocationZone'.
	 */
	@Transactional
	public List<WhLocation> findWhereAllocationZoneEquals(String allocationZone) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE allocation_zone = ? ORDER BY allocation_zone", this,allocationZone);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'work_zone = :workZone'.
	 */
	@Transactional
	public List<WhLocation> findWhereWorkZoneEquals(String workZone) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE work_zone = ? ORDER BY work_zone", this,workZone);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<WhLocation> findWhereIsActiveEquals(String isActive) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<WhLocation> findWhereIsDeleteEquals(String isDelete) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'user_id = :userId'.
	 */
	@Transactional
	public List<WhLocation> findWhereUserIdEquals(String userId) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE user_id = ? ORDER BY user_id", this,userId);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'corp_id = :corpId'.
	 */
	@Transactional
	public List<WhLocation> findWhereCorpIdEquals(String corpId) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE corp_id = ? ORDER BY corp_id", this,corpId);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<WhLocation> findWhereWhCodeEquals(String whCode) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<WhLocation> findWhereCreatedByEquals(String createdBy) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<WhLocation> findWhereCreatedDateEquals(Date createdDate) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<WhLocation> findWhereUpdatedByEquals(String updatedBy) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<WhLocation> findWhereUpdatedDateEquals(Date updatedDate) throws WhLocationDaoException
	{
		try {
			return jdbcTemplate.query("SELECT location_id, location_code, location_name, location_type, min_product, max_product, locating_area, locating_zone, location_bay, location_level, location_position, allocation_zone, work_zone, is_active, is_delete, user_id, corp_id, wh_code, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new WhLocationDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the wh_location table that matches the specified primary-key value.
	 */
	public WhLocation findByPrimaryKey(WhLocationPk pk) throws WhLocationDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

	@Override
	public List<WhLocation> findByCriteriaLimit(WarehouseLocationSearch c, int start, int end) throws WhLocationDaoException {
		if (c == null) {
		    
            System.out.println("Search User with no criteria");
            c = new WarehouseLocationSearch(); 
        }
        try {
        
            c.setTableAlias(this.getTableName()); 
         
            HashMap ret = c.getCriteria();
            HashMap param = (HashMap) ret.get("parameter");
            
            param.put("pagestart", start);
            param.put("pageend", end);
            
            String search = (String)ret.get("search");// add by rendra 06112012 4:32 PM
            
            String strQuery = "select location_id,location_code,location_name,location_type,min_product,max_product,"
				 +"locating_area,locating_zone,location_bay,location_level,location_position,allocation_zone,work_zone,"
				 +"is_active,is_delete,user_id,corp_id,wh_code,created_by,created_date,updated_by,updated_date FROM wh_location";         

            return jdbcTemplate.query(strQuery, this, param); 
        } catch (Exception e) {
            throw new WhLocationDaoException("Query failed", e);
        }
	}

	@Override
	public List<WhLocation> findByLocationPaging(WhLocation w, int page) throws WhLocationDaoException {
		try {
        	String corpId = w.getCorpId();
        	String whCode = w.getWhCode();
        	
        	String locationCode = w.getLocationCode();
        	String locationName = w.getLocationName();
        	
        	int i = page;
        	Map map = new HashMap();
        	map.put("corpId", corpId);
        	map.put("whCode", whCode);
        	map.put("i", i);
        
        	StringBuffer sb = new StringBuffer();
        	
        	if( w == null){
        		w = new WhLocation();
        	}
        	
        	if(locationCode == null || locationName == null){
        		locationCode = "%";
        		locationName = "%";
        		
        		sb.append(" declare @Page int, @PageSize int "
            			+"set @Page = '"+i+"'; " 
            			+"set @PageSize = 10; " 
            			+"with PagedResult " 
            			+"as (select ROW_NUMBER() over (order by a.location_code desc) as id, a.location_id, a.location_code, a.location_name, "  
            			+"b.product_code, b.product_name,b.unit_code, a.is_active from wh_location a "
            			+"inner join wh_location_detail b on a.location_id = b.location_id where a.corp_id like '"+corpId+"' and a.wh_code like '"+whCode+"' and a.location_code like '%"+locationCode+"%' and a.location_name like '%"+locationName+"%' ) " 
            			+"select * from PagedResult where id between " 
            			+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 " 
            			+"else @Page end and @PageSize * @Page ");
        		
        	}else{
        		
        		sb.append(" declare @Page int, @PageSize int "
            			+"set @Page = '"+i+"'; " 
            			+"set @PageSize = 10; " 
            			+"with PagedResult " 
            			+"as (select ROW_NUMBER() over (order by a.location_code desc) as id, a.location_id, a.location_code, a.location_name, "  
            			+"b.product_code, b.product_name,b.unit_code, a.is_active from wh_location a "
            			+"inner join wh_location_detail b on a.location_id = b.location_id where a.corp_id like '"+corpId+"' and a.wh_code like '"+whCode+"' and a.location_code like '%"+locationCode+"%' and a.location_name like '%"+locationName+"%' ) " 
            			+"select * from PagedResult where id between " 
            			+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 " 
            			+"else @Page end and @PageSize * @Page ");
        		
        	}
        	
        	
        	
        	
        	return jdbcTemplate.query(sb.toString(),new LocationListMap(),map);	
        
        } catch (Exception e) {
            throw new WhLocationDaoException("Query failed", e);
        }
	}

}

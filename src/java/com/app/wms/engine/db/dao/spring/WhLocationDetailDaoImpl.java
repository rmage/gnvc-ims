package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.WhLocationDetailDao;
import com.app.wms.engine.db.dto.WhLocationDetail;
import com.app.wms.engine.db.dto.WhLocationDetailPk;
import com.app.wms.engine.db.exceptions.WhLocationDetailDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class WhLocationDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<WhLocationDetail>, WhLocationDetailDao
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
	 * @return WhLocationDetailPk
	 */
	public WhLocationDetailPk insert(WhLocationDetail dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( location_id, product_id, product_code, product_name, unit_code, product_category, user_id, corp_id, wh_code ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getLocationId(),dto.getProductId(),dto.getProductCode(),dto.getProductName(),dto.getUnitCode(),dto.getProductCategory(),dto.getUserId(),dto.getCorpId(),dto.getWhCode()} );
		WhLocationDetailPk pk = new WhLocationDetailPk();
		//pk.setLocationId( jdbcTemplate.queryForObject("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the wh_location_detail table.
	 */
	public void update(WhLocationDetailPk pk, WhLocationDetail dto) throws WhLocationDetailDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET location_id = ?, product_id = ?, product_code = ?, product_name = ?, unit_code = ?, product_category = ?, user_id = ?, corp_id = ?, wh_code = ? WHERE location_id = ?");
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
		su.compile();
		su.update( new Object[] { dto.getLocationId(),dto.getProductId(),dto.getProductCode(),dto.getProductName(),dto.getUnitCode(),dto.getProductCategory(),dto.getUserId(),dto.getCorpId(),dto.getWhCode(),pk.getLocationId() } );
	}

	/** 
	 * Deletes a single row in the wh_location_detail table.
	 */
	@Transactional
	public void delete(WhLocationDetailPk pk) throws WhLocationDetailDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE location_id = ?",pk.getLocationId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return WhLocationDetail
	 */
	public WhLocationDetail mapRow(ResultSet rs, int row) throws SQLException
	{
		WhLocationDetail dto = new WhLocationDetail();
		dto.setId( rs.getInt( 1 ) );
		dto.setLocationId( rs.getString( 2 ) );
		dto.setProductId( rs.getString( 3 ) );
		dto.setProductCode( rs.getString( 4 ) );
		dto.setProductName( rs.getString( 5 ) );
		dto.setUnitCode( rs.getString( 6 ) );
		dto.setProductCategory( rs.getString( 7 ) );
		dto.setUserId( rs.getString( 8 ) );
		dto.setCorpId( rs.getString( 9 ) );
		dto.setWhCode( rs.getString( 10 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..wh_location_detail";
	}

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public WhLocationDetail findByPrimaryKey(String locationId) throws WhLocationDetailDaoException
	{
		try {
			List<WhLocationDetail> list = jdbcTemplate.query("SELECT id, location_id, product_id, product_code, product_name, unit_code, product_category, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE id = ?", this,locationId);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new WhLocationDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria ''.
	 */
	@Transactional
	public List<WhLocationDetail> findAll() throws WhLocationDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, location_id, product_id, product_code, product_name, unit_code, product_category, user_id, corp_id, wh_code FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new WhLocationDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<WhLocationDetail> findWhereIdEquals(int id) throws WhLocationDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, location_id, product_id, product_code, product_name, unit_code, product_category, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new WhLocationDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'location_id = :locationId'.
	 */
	@Transactional
	public List<WhLocationDetail> findWhereLocationIdEquals(String locationId) throws WhLocationDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, location_id, product_id, product_code, product_name, unit_code, product_category, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE location_id = ? ORDER BY location_id", this,locationId);
		}
		catch (Exception e) {
			throw new WhLocationDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'product_id = :productId'.
	 */
	@Transactional
	public List<WhLocationDetail> findWhereProductIdEquals(String productId) throws WhLocationDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, location_id, product_id, product_code, product_name, unit_code, product_category, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_id = ? ORDER BY product_id", this,productId);
		}
		catch (Exception e) {
			throw new WhLocationDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'product_code = :productCode'.
	 */
	@Transactional
	public List<WhLocationDetail> findWhereProductCodeEquals(String productCode) throws WhLocationDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, location_id, product_id, product_code, product_name, unit_code, product_category, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_code = ? ORDER BY product_code", this,productCode);
		}
		catch (Exception e) {
			throw new WhLocationDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'product_name = :productName'.
	 */
	@Transactional
	public List<WhLocationDetail> findWhereProductNameEquals(String productName) throws WhLocationDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, location_id, product_id, product_code, product_name, unit_code, product_category, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_name = ? ORDER BY product_name", this,productName);
		}
		catch (Exception e) {
			throw new WhLocationDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'unit_code = :unitCode'.
	 */
	@Transactional
	public List<WhLocationDetail> findWhereUnitCodeEquals(String unitCode) throws WhLocationDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, location_id, product_id, product_code, product_name, unit_code, product_category, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE unit_code = ? ORDER BY unit_code", this,unitCode);
		}
		catch (Exception e) {
			throw new WhLocationDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'product_category = :productCategory'.
	 */
	@Transactional
	public List<WhLocationDetail> findWhereProductCategoryEquals(String productCategory) throws WhLocationDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, location_id, product_id, product_code, product_name, unit_code, product_category, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_category = ? ORDER BY product_category", this,productCategory);
		}
		catch (Exception e) {
			throw new WhLocationDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'user_id = :userId'.
	 */
	@Transactional
	public List<WhLocationDetail> findWhereUserIdEquals(String userId) throws WhLocationDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, location_id, product_id, product_code, product_name, unit_code, product_category, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE user_id = ? ORDER BY user_id", this,userId);
		}
		catch (Exception e) {
			throw new WhLocationDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'corp_id = :corpId'.
	 */
	@Transactional
	public List<WhLocationDetail> findWhereCorpIdEquals(String corpId) throws WhLocationDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, location_id, product_id, product_code, product_name, unit_code, product_category, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE corp_id = ? ORDER BY corp_id", this,corpId);
		}
		catch (Exception e) {
			throw new WhLocationDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<WhLocationDetail> findWhereWhCodeEquals(String whCode) throws WhLocationDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, location_id, product_id, product_code, product_name, unit_code, product_category, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new WhLocationDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the wh_location_detail table that matches the specified primary-key value.
	 */
	public WhLocationDetail findByPrimaryKey(WhLocationDetailPk pk) throws WhLocationDetailDaoException
	{
		return findByPrimaryKey( pk.getLocationId() );
	}

	@Override
	public WhLocationDetail findByPrimaryKey(int id)throws WhLocationDetailDaoException {
		// TODO Auto-generated method stub
		return null;
	}

}

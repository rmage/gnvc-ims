package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.BundleDetailDao;
import com.app.wms.engine.db.dto.BundleDetail;
import com.app.wms.engine.db.dto.SalesOrderDetail;
import com.app.wms.engine.db.exceptions.BundleDetailDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class BundleDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<BundleDetail>, BundleDetailDao
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
	 */
	public void insert(BundleDetail dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( bundle_code, product_id, product_code, product_name, unit_code, qty_bundle, user_id, corp_id, wh_code ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getBundleCode(),dto.getProductId(),dto.getProductCode(),dto.getProductName(),dto.getUnitCode(),dto.getQtyBundle(),dto.getUserId(),dto.getCorpId(),dto.getWhCode()} );
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return BundleDetail
	 */
	public BundleDetail mapRow(ResultSet rs, int row) throws SQLException
	{
		BundleDetail dto = new BundleDetail();
		dto.setBundleCode( rs.getString( 1 ) );
		dto.setProductId( rs.getString( 2 ) );
		dto.setProductCode( rs.getString( 3 ) );
		dto.setProductName( rs.getString( 4 ) );
		dto.setUnitCode( rs.getString( 5 ) );
		dto.setQtyBundle( rs.getInt( 6 ) );
		if (rs.wasNull()) {
			dto.setQtyBundleNull( true );
		}
		
		dto.setUserId( rs.getString( 7 ) );
		dto.setCorpId( rs.getString( 8 ) );
		dto.setWhCode( rs.getString( 9 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "bundle_detail";
	}

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria ''.
	 */
	@Transactional
	public List<BundleDetail> findAll() throws BundleDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT bundle_code, product_id, product_code, product_name, unit_code, qty_bundle, user_id, corp_id, wh_code FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new BundleDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'bundle_code = :bundleCode'.
	 */
	@Transactional
	public List<BundleDetail> findWhereBundleCodeEquals(String bundleCode) throws BundleDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT bundle_code, product_id, product_code, product_name, unit_code, qty_bundle, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE bundle_code = ? ORDER BY bundle_code", this,bundleCode);
		}
		catch (Exception e) {
			throw new BundleDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'product_id = :productId'.
	 */
	@Transactional
	public List<BundleDetail> findWhereProductIdEquals(String productId) throws BundleDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT bundle_code, product_id, product_code, product_name, unit_code, qty_bundle, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_id = ? ORDER BY product_id", this,productId);
		}
		catch (Exception e) {
			throw new BundleDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'product_code = :productCode'.
	 */
	@Transactional
	public List<BundleDetail> findWhereProductCodeEquals(String productCode) throws BundleDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT bundle_code, product_id, product_code, product_name, unit_code, qty_bundle, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_code = ? ORDER BY product_code", this,productCode);
		}
		catch (Exception e) {
			throw new BundleDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'product_name = :productName'.
	 */
	@Transactional
	public List<BundleDetail> findWhereProductNameEquals(String productName) throws BundleDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT bundle_code, product_id, product_code, product_name, unit_code, qty_bundle, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_name = ? ORDER BY product_name", this,productName);
		}
		catch (Exception e) {
			throw new BundleDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'unit_code = :unitCode'.
	 */
	@Transactional
	public List<BundleDetail> findWhereUnitCodeEquals(String unitCode) throws BundleDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT bundle_code, product_id, product_code, product_name, unit_code, qty_bundle, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE unit_code = ? ORDER BY unit_code", this,unitCode);
		}
		catch (Exception e) {
			throw new BundleDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'qty_bundle = :qtyBundle'.
	 */
	@Transactional
	public List<BundleDetail> findWhereQtyBundleEquals(int qtyBundle) throws BundleDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT bundle_code, product_id, product_code, product_name, unit_code, qty_bundle, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE qty_bundle = ? ORDER BY qty_bundle", this,qtyBundle);
		}
		catch (Exception e) {
			throw new BundleDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'user_id = :userId'.
	 */
	@Transactional
	public List<BundleDetail> findWhereUserIdEquals(String userId) throws BundleDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT bundle_code, product_id, product_code, product_name, unit_code, qty_bundle, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE user_id = ? ORDER BY user_id", this,userId);
		}
		catch (Exception e) {
			throw new BundleDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'corp_id = :corpId'.
	 */
	@Transactional
	public List<BundleDetail> findWhereCorpIdEquals(String corpId) throws BundleDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT bundle_code, product_id, product_code, product_name, unit_code, qty_bundle, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE corp_id = ? ORDER BY corp_id", this,corpId);
		}
		catch (Exception e) {
			throw new BundleDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<BundleDetail> findWhereWhCodeEquals(String whCode) throws BundleDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT bundle_code, product_id, product_code, product_name, unit_code, qty_bundle, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new BundleDetailDaoException("Query failed", e);
		}
		
	}

	@Override
	public List<BundleDetail> findWhereBundleCode(BundleDetail bud)	throws BundleDetailDaoException {
		// TODO Auto-generated method stub
		try {
			return jdbcTemplate.query("SELECT bundle_code, product_id, product_code, product_name, unit_code, qty_bundle, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE bundle_code = ? ORDER BY bundle_code", this,bud.getBundleCode());
		}
		catch (Exception e) {
			throw new BundleDetailDaoException("Query failed", e);
		}
		
	}

	public BundleDetail findByPrimaryKey(String bundleCode) throws BundleDetailDaoException {
		// TODO Auto-generated method stub
		try {
			List<BundleDetail> list = jdbcTemplate.query("select bundle_code, product_id, product_code, product_name, unit_code, qty_bundle, user_id, corp_id, wh_code from " + getTableName() + " where bundle_code = ?", this, bundleCode);
            return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new BundleDetailDaoException("Query failed", e);
		}
	}
	
	public List <BundleDetail> findDetail (BundleDetail bud) throws BundleDetailDaoException {
		try {
			
			String bundleCode = bud.getBundleCode();
			String corpId 	  = bud.getCorpId();
			String whCode	  = bud.getWhCode();
			
			StringBuffer sb = new StringBuffer();
			sb.append("select bundle_code, product_id, product_code, product_name, unit_code, qty_bundle, user_id, corp_id, wh_code from " +getTableName()+" where corp_id like '"+corpId+"' and wh_code like '"+whCode+"' and bundle_code like '"+bundleCode+"' ");

			return jdbcTemplate.query(sb.toString(), this);
		}
		catch (Exception e) {
			throw new BundleDetailDaoException("Query failed", e);
		}
	}

}

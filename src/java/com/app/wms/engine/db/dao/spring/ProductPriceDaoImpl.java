package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.ProductPriceDao;
import com.app.wms.engine.db.dto.ProductPrice;
import com.app.wms.engine.db.dto.ProductPricePk;
import com.app.wms.engine.db.exceptions.ProductPriceDaoException;
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

public class ProductPriceDaoImpl extends AbstractDAO implements ParameterizedRowMapper<ProductPrice>, ProductPriceDao
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
	 * @return ProductPricePk
	 */
	public ProductPricePk insert(ProductPrice dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getProductCode(),dto.getCatalogCode(),dto.getBasePrice(),dto.getDiscountPercent(),dto.getDiscountPrice(),dto.getValuePrice(),dto.getBaseVat(),dto.getPercentVat(),dto.getValueVat(),dto.getCustomerPrice(),dto.getVendorPrice(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		ProductPricePk pk = new ProductPricePk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the product_price table.
	 */
	public void update(ProductPricePk pk, ProductPrice dto) throws ProductPriceDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET product_code = ?, catalog_code = ?, base_price = ?, discount_percent = ?, discount_price = ?, value_price = ?, base_vat = ?, percent_vat = ?, value_vat = ?, customer_price = ?, vendor_price = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getProductCode(),dto.getCatalogCode(),dto.getBasePrice(),dto.getDiscountPercent(),dto.getDiscountPrice(),dto.getValuePrice(),dto.getBaseVat(),dto.getPercentVat(),dto.getValueVat(),dto.getCustomerPrice(),dto.getVendorPrice(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the product_price table.
	 */
	@Transactional
	public void delete(ProductPricePk pk) throws ProductPriceDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return ProductPrice
	 */
	public ProductPrice mapRow(ResultSet rs, int row) throws SQLException
	{
		ProductPrice dto = new ProductPrice();
		dto.setId( rs.getInt( 1 ) );
		dto.setProductCode( rs.getString( 2 ) );
		dto.setCatalogCode( rs.getString( 3 ) );
		dto.setBasePrice( rs.getFloat( 4 ) );
		if (rs.wasNull()) {
			dto.setBasePriceNull( true );
		}
		
		dto.setDiscountPercent( rs.getFloat( 5 ) );
		if (rs.wasNull()) {
			dto.setDiscountPercentNull( true );
		}
		
		dto.setDiscountPrice( rs.getFloat( 6 ) );
		if (rs.wasNull()) {
			dto.setDiscountPriceNull( true );
		}
		
		dto.setValuePrice( rs.getFloat( 7 ) );
		if (rs.wasNull()) {
			dto.setValuePriceNull( true );
		}
		
		dto.setBaseVat( rs.getFloat( 8 ) );
		if (rs.wasNull()) {
			dto.setBaseVatNull( true );
		}
		
		dto.setPercentVat( rs.getFloat( 9 ) );
		if (rs.wasNull()) {
			dto.setPercentVatNull( true );
		}
		
		dto.setValueVat( rs.getFloat( 10 ) );
		if (rs.wasNull()) {
			dto.setValueVatNull( true );
		}
		
		dto.setCustomerPrice( rs.getFloat( 11 ) );
		if (rs.wasNull()) {
			dto.setCustomerPriceNull( true );
		}
		
		dto.setVendorPrice( rs.getFloat( 12 ) );
		if (rs.wasNull()) {
			dto.setVendorPriceNull( true );
		}
		
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
		return "inventory..product_price";
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'id = :id'.
	 */
	@Transactional
	public ProductPrice findByPrimaryKey(int id) throws ProductPriceDaoException
	{
		try {
			List<ProductPrice> list = jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria ''.
	 */
	@Transactional
	public List<ProductPrice> findAll() throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<ProductPrice> findWhereIdEquals(int id) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'product_code = :productCode'.
	 */
	@Transactional
	public List<ProductPrice> findWhereProductCodeEquals(String productCode) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE product_code = ? ORDER BY product_code", this,productCode);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'catalog_code = :catalogCode'.
	 */
	@Transactional
	public List<ProductPrice> findWhereCatalogCodeEquals(String catalogCode) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE catalog_code = ? ORDER BY catalog_code", this,catalogCode);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'base_price = :basePrice'.
	 */
	@Transactional
	public List<ProductPrice> findWhereBasePriceEquals(float basePrice) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE base_price = ? ORDER BY base_price", this,basePrice);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'discount_percent = :discountPercent'.
	 */
	@Transactional
	public List<ProductPrice> findWhereDiscountPercentEquals(float discountPercent) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE discount_percent = ? ORDER BY discount_percent", this,discountPercent);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'discount_price = :discountPrice'.
	 */
	@Transactional
	public List<ProductPrice> findWhereDiscountPriceEquals(float discountPrice) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE discount_price = ? ORDER BY discount_price", this,discountPrice);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'value_price = :valuePrice'.
	 */
	@Transactional
	public List<ProductPrice> findWhereValuePriceEquals(float valuePrice) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE value_price = ? ORDER BY value_price", this,valuePrice);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'base_vat = :baseVat'.
	 */
	@Transactional
	public List<ProductPrice> findWhereBaseVatEquals(float baseVat) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE base_vat = ? ORDER BY base_vat", this,baseVat);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'percent_vat = :percentVat'.
	 */
	@Transactional
	public List<ProductPrice> findWherePercentVatEquals(float percentVat) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE percent_vat = ? ORDER BY percent_vat", this,percentVat);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'value_vat = :valueVat'.
	 */
	@Transactional
	public List<ProductPrice> findWhereValueVatEquals(float valueVat) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE value_vat = ? ORDER BY value_vat", this,valueVat);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'customer_price = :customerPrice'.
	 */
	@Transactional
	public List<ProductPrice> findWhereCustomerPriceEquals(float customerPrice) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE customer_price = ? ORDER BY customer_price", this,customerPrice);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'vendor_price = :vendorPrice'.
	 */
	@Transactional
	public List<ProductPrice> findWhereVendorPriceEquals(float vendorPrice) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE vendor_price = ? ORDER BY vendor_price", this,vendorPrice);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<ProductPrice> findWhereIsActiveEquals(String isActive) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<ProductPrice> findWhereIsDeleteEquals(String isDelete) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<ProductPrice> findWhereCreatedByEquals(String createdBy) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<ProductPrice> findWhereCreatedDateEquals(Date createdDate) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<ProductPrice> findWhereUpdatedByEquals(String updatedBy) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_price table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<ProductPrice> findWhereUpdatedDateEquals(Date updatedDate) throws ProductPriceDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, catalog_code, base_price, discount_percent, discount_price, value_price, base_vat, percent_vat, value_vat, customer_price, vendor_price, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new ProductPriceDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the product_price table that matches the specified primary-key value.
	 */
	public ProductPrice findByPrimaryKey(ProductPricePk pk) throws ProductPriceDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

}

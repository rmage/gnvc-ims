package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.TsDetailDao;
import com.app.wms.engine.db.dto.TsDetail;
import com.app.wms.engine.db.dto.TsDetailPk;
import com.app.wms.engine.db.exceptions.TsDetailDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class TsDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<TsDetail>, TsDetailDao
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
	 * @return TsDetailPk
	 */
	public TsDetailPk insert(TsDetail dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( tsnumber, productcode, product_name, product_category, uom_name, wh_code, balance, qtyrequest ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.compile();
		su.update( new Object[] { dto.getTsnumber(),dto.getProductcode(),dto.getProductName(),dto.getProductCategory(),dto.getUomName(),dto.getWhCode(),dto.getBalance(),dto.getQtyrequest()} );
		TsDetailPk pk = new TsDetailPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the ts_detail table.
	 */
	public void update(TsDetailPk pk, TsDetail dto) throws TsDetailDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET tsnumber = ?, productcode = ?, product_name = ?, product_category = ?, uom_name = ?, wh_code = ?, balance = ?, qtyrequest = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getTsnumber(),dto.getProductcode(),dto.getProductName(),dto.getProductCategory(),dto.getUomName(),dto.getWhCode(),dto.getBalance(),dto.getQtyrequest(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the ts_detail table.
	 */
	@Transactional
	public void delete(TsDetailPk pk) throws TsDetailDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return TsDetail
	 */
	public TsDetail mapRow(ResultSet rs, int row) throws SQLException
	{
		TsDetail dto = new TsDetail();
		dto.setId( rs.getInt( 1 ) );
		dto.setTsnumber( rs.getString( 2 ) );
		dto.setProductcode( rs.getString( 3 ) );
		dto.setProductName( rs.getString( 4 ) );
		dto.setProductCategory( rs.getString( 5 ) );
		dto.setUomName( rs.getString( 6 ) );
		dto.setWhCode( rs.getString( 7 ) );
		dto.setBalance( rs.getBigDecimal( 8 ) );
		if (rs.wasNull()) {
			dto.setBalanceNull( true );
		}
		
		dto.setQtyrequest( rs.getBigDecimal( 9 ) );
		if (rs.wasNull()) {
			dto.setQtyrequestNull( true );
		}
		
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..ts_detail";
	}

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public TsDetail findByPrimaryKey(int id) throws TsDetailDaoException
	{
		try {
			List<TsDetail> list = jdbcTemplate.query("SELECT id, tsnumber, productcode, product_name, product_category, uom_name, wh_code, balance, qtyrequest FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new TsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts_detail table that match the criteria ''.
	 */
	@Transactional
	public List<TsDetail> findAll() throws TsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, productcode, product_name, product_category, uom_name, wh_code, balance, qtyrequest FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new TsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<TsDetail> findWhereIdEquals(int id) throws TsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, productcode, product_name, product_category, uom_name, wh_code, balance, qtyrequest FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new TsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'tsnumber = :tsnumber'.
	 */
	@Transactional
	public List<TsDetail> findWhereTsnumberEquals(String tsnumber) throws TsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, productcode, product_name, product_category, uom_name, wh_code, balance, qtyrequest FROM " + getTableName() + " WHERE tsnumber = ? ORDER BY tsnumber", this,tsnumber);
		}
		catch (Exception e) {
			throw new TsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'productcode = :productcode'.
	 */
	@Transactional
	public List<TsDetail> findWhereProductcodeEquals(String productcode) throws TsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, productcode, product_name, product_category, uom_name, wh_code, balance, qtyrequest FROM " + getTableName() + " WHERE productcode = ? ORDER BY productcode", this,productcode);
		}
		catch (Exception e) {
			throw new TsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'product_name = :productName'.
	 */
	@Transactional
	public List<TsDetail> findWhereProductNameEquals(String productName) throws TsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, productcode, product_name, product_category, uom_name, wh_code, balance, qtyrequest FROM " + getTableName() + " WHERE product_name = ? ORDER BY product_name", this,productName);
		}
		catch (Exception e) {
			throw new TsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'product_category = :productCategory'.
	 */
	@Transactional
	public List<TsDetail> findWhereProductCategoryEquals(String productCategory) throws TsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, productcode, product_name, product_category, uom_name, wh_code, balance, qtyrequest FROM " + getTableName() + " WHERE product_category = ? ORDER BY product_category", this,productCategory);
		}
		catch (Exception e) {
			throw new TsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'uom_name = :uomName'.
	 */
	@Transactional
	public List<TsDetail> findWhereUomNameEquals(String uomName) throws TsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, productcode, product_name, product_category, uom_name, wh_code, balance, qtyrequest FROM " + getTableName() + " WHERE uom_name = ? ORDER BY uom_name", this,uomName);
		}
		catch (Exception e) {
			throw new TsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<TsDetail> findWhereWhCodeEquals(String whCode) throws TsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, productcode, product_name, product_category, uom_name, wh_code, balance, qtyrequest FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new TsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'balance = :balance'.
	 */
	@Transactional
	public List<TsDetail> findWhereBalanceEquals(float balance) throws TsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, productcode, product_name, product_category, uom_name, wh_code, balance, qtyrequest FROM " + getTableName() + " WHERE balance = ? ORDER BY balance", this,balance);
		}
		catch (Exception e) {
			throw new TsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'qtyrequest = :qtyrequest'.
	 */
	@Transactional
	public List<TsDetail> findWhereQtyrequestEquals(float qtyrequest) throws TsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, tsnumber, productcode, product_name, product_category, uom_name, wh_code, balance, qtyrequest FROM " + getTableName() + " WHERE qtyrequest = ? ORDER BY qtyrequest", this,qtyrequest);
		}
		catch (Exception e) {
			throw new TsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the ts_detail table that matches the specified primary-key value.
	 */
	public TsDetail findByPrimaryKey(TsDetailPk pk) throws TsDetailDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

}

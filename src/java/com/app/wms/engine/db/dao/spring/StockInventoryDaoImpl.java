package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.StockInventoryDao;
import com.app.wms.engine.db.dto.StockInventory;
import com.app.wms.engine.db.dto.StockInventoryPk;
import com.app.wms.engine.db.dto.map.BalanceMap;
import com.app.wms.engine.db.exceptions.StockInventoryDaoException;
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

public class StockInventoryDaoImpl extends AbstractDAO implements ParameterizedRowMapper<StockInventory>, StockInventoryDao
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
	 * @return StockInventoryPk
	 */
	public StockInventoryPk insert(StockInventory dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( product_code, wh_code, balance, start_balance, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getProductCode(),dto.getWhCode(),dto.getBalance(),dto.getStartBalance(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		StockInventoryPk pk = new StockInventoryPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the stock_inventory table.
	 */
	public void update(StockInventoryPk pk, StockInventory dto) throws StockInventoryDaoException
	
	{
		System.out.println("update dto ="+dto);
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET product_code = ?, wh_code = ?, balance = ?, start_balance = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
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
		su.update( new Object[] { dto.getProductCode(),dto.getWhCode(),dto.getBalance(),dto.getStartBalance(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getId() } );
	}
	
	public void updateFromProduct(StockInventoryPk pk, StockInventory dto) throws StockInventoryDaoException
	
	{
		
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET product_code = ?, wh_code = ? WHERE product_id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getProductCode(),dto.getWhCode(),dto.getProductId() } );
	}

	/** 
	 * Deletes a single row in the stock_inventory table.
	 */
	@Transactional
	public void delete(StockInventoryPk pk) throws StockInventoryDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return StockInventory
	 */
	public StockInventory mapRow(ResultSet rs, int row) throws SQLException
	{
		StockInventory dto = new StockInventory();
		dto.setId( rs.getInt( 1 ) );
		dto.setProductCode( rs.getString( 2 ) );
		dto.setWhCode( rs.getString( 3 ) );
		dto.setBalance( rs.getBigDecimal( 4 ) );
		if (rs.wasNull()) {
			dto.setBalanceNull( true );
		}
		
		dto.setStartBalance( rs.getBigDecimal( 5 ) );
		if (rs.wasNull()) {
			dto.setStartBalanceNull( true );
		}
		
		dto.setIsActive( rs.getString( 6 ) );
		dto.setIsDelete( rs.getString( 7 ) );
		dto.setCreatedBy( rs.getString( 8 ) );
		dto.setCreatedDate( rs.getTimestamp(9 ) );
		dto.setUpdatedBy( rs.getString( 10 ) );
		dto.setUpdatedDate( rs.getTimestamp(11 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..stock_inventory";
	}

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'id = :id'.
	 */
	@Transactional
	public StockInventory findByPrimaryKey(int id) throws StockInventoryDaoException
	{
		try {
			List<StockInventory> list = jdbcTemplate.query("SELECT id, product_code, wh_code, balance, start_balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new StockInventoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria ''.
	 */
	@Transactional
	public List<StockInventory> findAll() throws StockInventoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, balance, start_balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new StockInventoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<StockInventory> findWhereIdEquals(int id) throws StockInventoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, balance, start_balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new StockInventoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'product_code = :productCode'.
	 */
	@Transactional
	public List<StockInventory> findWhereProductCodeEquals(String productCode) throws StockInventoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, balance, start_balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE product_code = ? ORDER BY product_code", this,productCode);
		}
		catch (Exception e) {
			throw new StockInventoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<StockInventory> findWhereWhCodeEquals(String whCode) throws StockInventoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, balance, start_balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new StockInventoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'balance = :balance'.
	 */
	@Transactional
	public List<StockInventory> findWhereBalanceEquals(int balance) throws StockInventoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, balance, start_balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE balance = ? ORDER BY balance", this,balance);
		}
		catch (Exception e) {
			throw new StockInventoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'start_balance = :startBalance'.
	 */
	@Transactional
	public List<StockInventory> findWhereStartBalanceEquals(int startBalance) throws StockInventoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, balance, start_balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE start_balance = ? ORDER BY start_balance", this,startBalance);
		}
		catch (Exception e) {
			throw new StockInventoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<StockInventory> findWhereIsActiveEquals(String isActive) throws StockInventoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, balance, start_balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new StockInventoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<StockInventory> findWhereIsDeleteEquals(String isDelete) throws StockInventoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, balance, start_balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new StockInventoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<StockInventory> findWhereCreatedByEquals(String createdBy) throws StockInventoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, balance, start_balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new StockInventoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<StockInventory> findWhereCreatedDateEquals(Date createdDate) throws StockInventoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, balance, start_balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new StockInventoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<StockInventory> findWhereUpdatedByEquals(String updatedBy) throws StockInventoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, balance, start_balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new StockInventoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the stock_inventory table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<StockInventory> findWhereUpdatedDateEquals(Date updatedDate) throws StockInventoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, balance, start_balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new StockInventoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the stock_inventory table that matches the specified primary-key value.
	 */
	public StockInventory findByPrimaryKey(StockInventoryPk pk) throws StockInventoryDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

	@Override
	public List <StockInventory> balance(StockInventory dto) throws StockInventoryDaoException {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		try {
			
			String productCode = dto.getProductCode();
			String warehouse = dto.getWhCode();
			map.put("productCode", dto.getProductCode());
			map.put("warehouse", dto.getWhCode());
			
			return jdbcTemplate.query("SELECT balance FROM stock_inventory where product_code = '"+productCode+"' and wh_code = '"+warehouse+"' ", new BalanceMap(),map);
		}
		catch (Exception e) {
			throw new StockInventoryDaoException("Query failed", e);
		}
		
	}

	@Override
	public List<StockInventory> findProductandWarehouse(StockInventory dto)	throws StockInventoryDaoException {
		try {
			return jdbcTemplate.query("SELECT id, product_code, wh_code, balance, start_balance, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE product_code = ? ORDER BY product_code", this,dto.getProductCode());
		}
		catch (Exception e) {
			throw new StockInventoryDaoException("Query failed", e);
		}
	}

	@Override
	public void updateBalance(StockInventory dto) throws StockInventoryDaoException {
		
		SqlUpdate su = new SqlUpdate 
					  ( dataSource,
						" update " + getTableName() + " set balance = ? " +	
						" where product_code = ? and wh_code = ? " );
		
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object [] { dto.getBalance(), dto.getProductCode(), dto.getWhCode()});
		
	}
	
	public void updateBalanceNoWarehouse(StockInventory dto) throws StockInventoryDaoException {
		SqlUpdate su = new SqlUpdate 
		 			  ( dataSource,
						" update " + getTableName() + " set balance = ?, wh_code = ? " +	
						" where product_code = ? " );
		
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object [] { dto.getBalance(), dto.getWhCode(), dto.getProductCode()});
		
	}

}

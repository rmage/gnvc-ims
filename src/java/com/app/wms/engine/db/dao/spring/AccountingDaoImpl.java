package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.AccountingDao;
import com.app.wms.engine.db.dto.Accounting;
import com.app.wms.engine.db.dto.AccountingPk;
import com.app.wms.engine.db.dto.map.AccountingListMap;
import com.app.wms.engine.db.exceptions.AccountingDaoException;
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

public class AccountingDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Accounting>, AccountingDao
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
	 * @return AccountingPk
	 */
	public AccountingPk insert(Accounting dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getAccountingNo(),dto.getAccountingDate(),dto.getDocumentNo(),dto.getDocumentDate(),dto.getProductCode(),dto.getProductName(),dto.getQtyIn(),dto.getQtyOut(),dto.getBalance(),dto.getWhCode(),dto.getUnitprice(),dto.getAmount(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		AccountingPk pk = new AccountingPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the accounting table.
	 */
	public void update(AccountingPk pk, Accounting dto) throws AccountingDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET accounting_no = ?, accounting_date = ?, document_no = ?, document_date = ?, product_code = ?, product_name = ?, qty_in = ?, qty_out = ?, balance = ?, wh_code = ?, unitprice = ?, amount = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getAccountingNo(),dto.getAccountingDate(),dto.getDocumentNo(),dto.getDocumentDate(),dto.getProductCode(),dto.getProductName(),dto.getQtyIn(),dto.getQtyOut(),dto.getBalance(),dto.getWhCode(),dto.getUnitprice(),dto.getAmount(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the accounting table.
	 */
	@Transactional
	public void delete(AccountingPk pk) throws AccountingDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Accounting
	 */
	public Accounting mapRow(ResultSet rs, int row) throws SQLException
	{
		Accounting dto = new Accounting();
		dto.setId( rs.getInt( 1 ) );
		dto.setAccountingNo( rs.getString( 2 ) );
		dto.setAccountingDate( rs.getTimestamp(3 ) );
		dto.setDocumentNo( rs.getString( 4 ) );
		dto.setDocumentDate( rs.getTimestamp(5 ) );
		dto.setProductCode( rs.getString( 6 ) );
		dto.setProductName( rs.getString( 7 ) );
		dto.setQtyIn( rs.getBigDecimal( 8 ) );
		if (rs.wasNull()) {
			dto.setQtyInNull( true );
		}
		
		dto.setQtyOut( rs.getBigDecimal( 9 ) );
		if (rs.wasNull()) {
			dto.setQtyOutNull( true );
		}
		
		dto.setBalance( rs.getBigDecimal( 10 ) );
		if (rs.wasNull()) {
			dto.setBalanceNull( true );
		}
		
		dto.setWhCode( rs.getString( 11 ) );
		dto.setUnitprice( rs.getBigDecimal( 12 ) );
		if (rs.wasNull()) {
			dto.setUnitpriceNull( true );
		}
		
		dto.setAmount( rs.getBigDecimal( 13 ) );
		if (rs.wasNull()) {
			dto.setAmountNull( true );
		}
		
		dto.setCreatedBy( rs.getString( 14 ) );
		dto.setCreatedDate( rs.getTimestamp(15 ) );
		dto.setUpdatedBy( rs.getString( 16 ) );
		dto.setUpdatedDate( rs.getTimestamp(17 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..accounting";
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Accounting findByPrimaryKey(int id) throws AccountingDaoException
	{
		try {
			List<Accounting> list = jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria ''.
	 */
	@Transactional
	public List<Accounting> findAll() throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Accounting> findWhereIdEquals(int id) throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'accounting_no = :accountingNo'.
	 */
	@Transactional
	public List<Accounting> findWhereAccountingNoEquals(String accountingNo) throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE accounting_no = ? ORDER BY accounting_no", this,accountingNo);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'accounting_date = :accountingDate'.
	 */
	@Transactional
	public List<Accounting> findWhereAccountingDateEquals(Date accountingDate) throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE accounting_date = ? ORDER BY accounting_date", this,accountingDate);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'document_no = :documentNo'.
	 */
	@Transactional
	public List<Accounting> findWhereDocumentNoEquals(String documentNo) throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE document_no = ? ORDER BY document_no", this,documentNo);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'document_date = :documentDate'.
	 */
	@Transactional
	public List<Accounting> findWhereDocumentDateEquals(Date documentDate) throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE document_date = ? ORDER BY document_date", this,documentDate);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'product_code = :productCode'.
	 */
	@Transactional
	public List<Accounting> findWhereProductCodeEquals(String productCode) throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE product_code = ? ORDER BY product_code", this,productCode);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'product_name = :productName'.
	 */
	@Transactional
	public List<Accounting> findWhereProductNameEquals(String productName) throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE product_name = ? ORDER BY product_name", this,productName);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'qty_in = :qtyIn'.
	 */
	@Transactional
	public List<Accounting> findWhereQtyInEquals(float qtyIn) throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE qty_in = ? ORDER BY qty_in", this,qtyIn);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'qty_out = :qtyOut'.
	 */
	@Transactional
	public List<Accounting> findWhereQtyOutEquals(float qtyOut) throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE qty_out = ? ORDER BY qty_out", this,qtyOut);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'balance = :balance'.
	 */
	@Transactional
	public List<Accounting> findWhereBalanceEquals(float balance) throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE balance = ? ORDER BY balance", this,balance);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<Accounting> findWhereWhCodeEquals(String whCode) throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'unitprice = :unitprice'.
	 */
	@Transactional
	public List<Accounting> findWhereUnitpriceEquals(float unitprice) throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE unitprice = ? ORDER BY unitprice", this,unitprice);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'amount = :amount'.
	 */
	@Transactional
	public List<Accounting> findWhereAmountEquals(float amount) throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE amount = ? ORDER BY amount", this,amount);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<Accounting> findWhereCreatedByEquals(String createdBy) throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<Accounting> findWhereCreatedDateEquals(Date createdDate) throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<Accounting> findWhereUpdatedByEquals(String updatedBy) throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the accounting table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<Accounting> findWhereUpdatedDateEquals(Date updatedDate) throws AccountingDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, accounting_no, accounting_date, document_no, document_date, product_code, product_name, qty_in, qty_out, balance, wh_code, unitprice, amount, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the accounting table that matches the specified primary-key value.
	 */
	public Accounting findByPrimaryKey(AccountingPk pk) throws AccountingDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}
	
	@Override
	public List<Accounting> findProductDetail() throws AccountingDaoException {
		try {
			
			Map map = new HashMap();
        	
        	StringBuffer sb = new StringBuffer();
        	
        	sb.append(" select sb.document_date, sb.document_no, sb.product_code, p.product_name, " +
        			  " si.wh_code as location, sb.qty_in, sb.qty_out, sb.balance " +
        			  " from stock_balance sb " +
        			  " inner join stock_inventory si " +
        			  " on sb.product_code = si.product_code " +
        			  " inner join product p " +
        			  " on sb.product_code = p.product_code " +
        			  " order by sb.document_date asc ");
			
        	return jdbcTemplate.query(sb.toString(),new AccountingListMap(),map);
		}
		catch (Exception e) {
			throw new AccountingDaoException("Query failed", e);
		}
	}

}

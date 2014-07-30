package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.ReplenishDetailDao;
import com.app.wms.engine.db.dto.ReplenishDetail;
import com.app.wms.engine.db.exceptions.ReplenishDetailDaoException;
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

public class ReplenishDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<ReplenishDetail>, ReplenishDetailDao
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
	public void insert(ReplenishDetail dto)	{
            String sql = "INSERT INTO replenish_detail " +
                "SELECT TOP(1) ?, ?, ?, P.product_id, P.product_code, P.product_name, ?, ?, PD.unit_code, ?, ?, ?, ?, ?, ?, ? " +
                "FROM putaway_detail PD LEFT JOIN product P ON P.product_id = PD.product_id " +
                "WHERE PD.location_code = ? ORDER BY PD.id DESC";
            jdbcTemplate.update(sql, dto.getReplenishNo(), dto.getFromLocation(), dto.getToLocation(), dto.getReceivedDate(), dto.getExpiredDate(),
                    dto.getQtyReplenish(), dto.getBalance(), dto.getConfirmedBy(), dto.getRemarks(), dto.getUserId(), dto.getCorpId(), dto.getWhCode(),
                    dto.getFromLocation());
//		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
//		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
//		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
//		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
//		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
//		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
//		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
//		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
//		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
//		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
//		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
//		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
//		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
//		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
//		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
//		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
//		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
//		su.compile();
//		su.update( new Object[] { dto.getReplenishNo(),dto.getFromLocation(),dto.getToLocation(),dto.getProductId(),dto.getProductCode(),dto.getProductName(),dto.getReceivedDate(),dto.getExpiredDate(),dto.getUnitCode(),dto.getQtyReplenish(),dto.getBalance(),dto.getConfirmedBy(),dto.getRemarks(),dto.getUserId(),dto.getCorpId(),dto.getWhCode()} );
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return ReplenishDetail
	 */
	public ReplenishDetail mapRow(ResultSet rs, int row) throws SQLException
	{
		ReplenishDetail dto = new ReplenishDetail();
		dto.setReplenishNo( rs.getString( 1 ) );
		dto.setFromLocation( rs.getString( 2 ) );
		dto.setToLocation( rs.getString( 3 ) );
		dto.setProductId( rs.getString( 4 ) );
		dto.setProductCode( rs.getString( 5 ) );
		dto.setProductName( rs.getString( 6 ) );
		dto.setReceivedDate( rs.getTimestamp(7 ) );
		dto.setExpiredDate( rs.getTimestamp(8 ) );
		dto.setUnitCode( rs.getString( 9 ) );
		dto.setQtyReplenish( rs.getInt( 10 ) );
		if (rs.wasNull()) {
			dto.setQtyReplenishNull( true );
		}
		
		dto.setBalance( rs.getInt( 11 ) );
		if (rs.wasNull()) {
			dto.setBalanceNull( true );
		}
		
		dto.setConfirmedBy( rs.getString( 12 ) );
		dto.setRemarks( rs.getString( 13 ) );
		dto.setUserId( rs.getString( 14 ) );
		dto.setCorpId( rs.getString( 15 ) );
		dto.setWhCode( rs.getString( 16 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "replenish_detail";
	}

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria ''.
	 */
	@Transactional
	public List<ReplenishDetail> findAll() throws ReplenishDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new ReplenishDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'replenish_no = :replenishNo'.
	 */
	@Transactional
	public List<ReplenishDetail> findWhereReplenishNoEquals(String replenishNo) throws ReplenishDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE replenish_no = ? ORDER BY replenish_no", this,replenishNo);
		}
		catch (Exception e) {
			throw new ReplenishDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'from_location = :fromLocation'.
	 */
	@Transactional
	public List<ReplenishDetail> findWhereFromLocationEquals(String fromLocation) throws ReplenishDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE from_location = ? ORDER BY from_location", this,fromLocation);
		}
		catch (Exception e) {
			throw new ReplenishDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'to_location = :toLocation'.
	 */
	@Transactional
	public List<ReplenishDetail> findWhereToLocationEquals(String toLocation) throws ReplenishDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE to_location = ? ORDER BY to_location", this,toLocation);
		}
		catch (Exception e) {
			throw new ReplenishDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'product_id = :productId'.
	 */
	@Transactional
	public List<ReplenishDetail> findWhereProductIdEquals(String productId) throws ReplenishDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_id = ? ORDER BY product_id", this,productId);
		}
		catch (Exception e) {
			throw new ReplenishDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'product_code = :productCode'.
	 */
	@Transactional
	public List<ReplenishDetail> findWhereProductCodeEquals(String productCode) throws ReplenishDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_code = ? ORDER BY product_code", this,productCode);
		}
		catch (Exception e) {
			throw new ReplenishDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'product_name = :productName'.
	 */
	@Transactional
	public List<ReplenishDetail> findWhereProductNameEquals(String productName) throws ReplenishDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_name = ? ORDER BY product_name", this,productName);
		}
		catch (Exception e) {
			throw new ReplenishDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'received_date = :receivedDate'.
	 */
	@Transactional
	public List<ReplenishDetail> findWhereReceivedDateEquals(Date receivedDate) throws ReplenishDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE received_date = ? ORDER BY received_date", this,receivedDate);
		}
		catch (Exception e) {
			throw new ReplenishDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'expired_date = :expiredDate'.
	 */
	@Transactional
	public List<ReplenishDetail> findWhereExpiredDateEquals(Date expiredDate) throws ReplenishDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE expired_date = ? ORDER BY expired_date", this,expiredDate);
		}
		catch (Exception e) {
			throw new ReplenishDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'unit_code = :unitCode'.
	 */
	@Transactional
	public List<ReplenishDetail> findWhereUnitCodeEquals(String unitCode) throws ReplenishDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE unit_code = ? ORDER BY unit_code", this,unitCode);
		}
		catch (Exception e) {
			throw new ReplenishDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'qty_replenish = :qtyReplenish'.
	 */
	@Transactional
	public List<ReplenishDetail> findWhereQtyReplenishEquals(int qtyReplenish) throws ReplenishDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE qty_replenish = ? ORDER BY qty_replenish", this,qtyReplenish);
		}
		catch (Exception e) {
			throw new ReplenishDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'balance = :balance'.
	 */
	@Transactional
	public List<ReplenishDetail> findWhereBalanceEquals(int balance) throws ReplenishDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE balance = ? ORDER BY balance", this,balance);
		}
		catch (Exception e) {
			throw new ReplenishDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'confirmed_by = :confirmedBy'.
	 */
	@Transactional
	public List<ReplenishDetail> findWhereConfirmedByEquals(String confirmedBy) throws ReplenishDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE confirmed_by = ? ORDER BY confirmed_by", this,confirmedBy);
		}
		catch (Exception e) {
			throw new ReplenishDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'remarks = :remarks'.
	 */
	@Transactional
	public List<ReplenishDetail> findWhereRemarksEquals(String remarks) throws ReplenishDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE remarks = ? ORDER BY remarks", this,remarks);
		}
		catch (Exception e) {
			throw new ReplenishDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'user_id = :userId'.
	 */
	@Transactional
	public List<ReplenishDetail> findWhereUserIdEquals(String userId) throws ReplenishDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE user_id = ? ORDER BY user_id", this,userId);
		}
		catch (Exception e) {
			throw new ReplenishDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'corp_id = :corpId'.
	 */
	@Transactional
	public List<ReplenishDetail> findWhereCorpIdEquals(String corpId) throws ReplenishDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE corp_id = ? ORDER BY corp_id", this,corpId);
		}
		catch (Exception e) {
			throw new ReplenishDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<ReplenishDetail> findWhereWhCodeEquals(String whCode) throws ReplenishDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, from_location, to_location, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_replenish, balance, confirmed_by, remarks, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new ReplenishDetailDaoException("Query failed", e);
		}
		
	}
        
        /* added by FYA : 23 May 2013 */
        public void confirmDocument(String replenishNo, String userId) throws ReplenishDetailDaoException {
            try {
                jdbcTemplate.update("UPDATE replenish_detail SET confirmed_by = ? WHERE replenish_no = ?", userId, replenishNo);
            }
            catch (Exception e) {
                throw new ReplenishDetailDaoException("Query failed : " + e.getMessage(), e);
            }
        }
}

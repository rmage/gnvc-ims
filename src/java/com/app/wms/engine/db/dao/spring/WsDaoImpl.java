package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.WsDao;
import com.app.wms.engine.db.dto.Pts;
import com.app.wms.engine.db.dto.Ws;
import com.app.wms.engine.db.dto.WsPk;
import com.app.wms.engine.db.exceptions.WsDaoException;
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

public class WsDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Ws>, WsDao
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
	 * @return WsPk
	 */
	public WsPk insert(Ws dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
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
		su.update( new Object[] { dto.getWsCode(),dto.getWsDate(),dto.getSupplierName(),dto.getFishSpecies(),dto.getFishSize(),dto.getFishWeight(),dto.getQuantity(),dto.getRemarks(),dto.getIssuedBy(),dto.getCheckedBy(),dto.getReceivedBy(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		WsPk pk = new WsPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the ws table.
	 */
	public void update(WsPk pk, Ws dto) throws WsDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET ws_code = ?, ws_date = ?, supplier_name = ?, fish_species = ?, fish_size = ?, fish_weight = ?, quantity = ?, remarks = ?, issued_by = ?, checked_by = ?, received_by = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
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
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getWsCode(),dto.getWsDate(),dto.getSupplierName(),dto.getFishSpecies(),dto.getFishSize(),dto.getFishWeight(),dto.getQuantity(),dto.getRemarks(),dto.getIssuedBy(),dto.getCheckedBy(),dto.getReceivedBy(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the ws table.
	 */
	@Transactional
	public void delete(WsPk pk) throws WsDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Ws
	 */
	public Ws mapRow(ResultSet rs, int row) throws SQLException
	{
		Ws dto = new Ws();
		dto.setId( rs.getInt( 1 ) );
		dto.setWsCode( rs.getString( 2 ) );
		dto.setWsDate( rs.getString( 3 ) );
		dto.setSupplierName( rs.getString( 4 ) );
		dto.setFishSpecies( rs.getString( 5 ) );
		dto.setFishSize( rs.getString( 6 ) );
		dto.setFishWeight( rs.getString( 7 ) );
		dto.setQuantity( rs.getInt( 8 ) );
		if (rs.wasNull()) {
			dto.setQuantityNull( true );
		}
		
		dto.setRemarks( rs.getString( 9 ) );
		dto.setIssuedBy( rs.getString( 10 ) );
		dto.setCheckedBy( rs.getString( 11 ) );
		dto.setReceivedBy( rs.getString( 12 ) );
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
		return "inventory..ws";
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Ws findByPrimaryKey(int id) throws WsDaoException
	{
		try {
			List<Ws> list = jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria ''.
	 */
	@Transactional
	public List<Ws> findAll() throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Ws> findWhereIdEquals(int id) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'ws_code = :wsCode'.
	 */
	@Transactional
	public List<Ws> findWhereWsCodeEquals(String wsCode) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE ws_code = ? ORDER BY ws_code", this,wsCode);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'ws_date = :wsDate'.
	 */
	@Transactional
	public List<Ws> findWhereWsDateEquals(String wsDate) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE ws_date = ? ORDER BY ws_date", this,wsDate);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'supplier_name = :supplierName'.
	 */
	@Transactional
	public List<Ws> findWhereSupplierNameEquals(String supplierName) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE supplier_name = ? ORDER BY supplier_name", this,supplierName);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'fish_species = :fishSpecies'.
	 */
	@Transactional
	public List<Ws> findWhereFishSpeciesEquals(String fishSpecies) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE fish_species = ? ORDER BY fish_species", this,fishSpecies);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'fish_size = :fishSize'.
	 */
	@Transactional
	public List<Ws> findWhereFishSizeEquals(String fishSize) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE fish_size = ? ORDER BY fish_size", this,fishSize);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'fish_weight = :fishWeight'.
	 */
	@Transactional
	public List<Ws> findWhereFishWeightEquals(String fishWeight) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE fish_weight = ? ORDER BY fish_weight", this,fishWeight);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'quantity = :quantity'.
	 */
	@Transactional
	public List<Ws> findWhereQuantityEquals(int quantity) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE quantity = ? ORDER BY quantity", this,quantity);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'remarks = :remarks'.
	 */
	@Transactional
	public List<Ws> findWhereRemarksEquals(String remarks) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE remarks = ? ORDER BY remarks", this,remarks);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'issued_by = :issuedBy'.
	 */
	@Transactional
	public List<Ws> findWhereIssuedByEquals(String issuedBy) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE issued_by = ? ORDER BY issued_by", this,issuedBy);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'checked_by = :checkedBy'.
	 */
	@Transactional
	public List<Ws> findWhereCheckedByEquals(String checkedBy) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE checked_by = ? ORDER BY checked_by", this,checkedBy);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'received_by = :receivedBy'.
	 */
	@Transactional
	public List<Ws> findWhereReceivedByEquals(String receivedBy) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE received_by = ? ORDER BY received_by", this,receivedBy);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<Ws> findWhereIsActiveEquals(String isActive) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<Ws> findWhereIsDeleteEquals(String isDelete) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<Ws> findWhereCreatedByEquals(String createdBy) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<Ws> findWhereCreatedDateEquals(Date createdDate) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<Ws> findWhereUpdatedByEquals(String updatedBy) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the ws table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<Ws> findWhereUpdatedDateEquals(Date updatedDate) throws WsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ws_code, ws_date, supplier_name, fish_species, fish_size, fish_weight, quantity, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new WsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the ws table that matches the specified primary-key value.
	 */
	public Ws findByPrimaryKey(WsPk pk) throws WsDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

	@Override
	public List<Ws> findWsPaging(Ws s, int page) throws WsDaoException {
		// TODO Auto-generated method stub
		return null;
	}

}

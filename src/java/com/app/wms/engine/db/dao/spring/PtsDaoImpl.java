package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.PtsDao;
import com.app.wms.engine.db.dto.Pts;
import com.app.wms.engine.db.dto.PtsPk;
import com.app.wms.engine.db.dto.map.PtslistMap;
import com.app.wms.engine.db.exceptions.PtsDaoException;

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

public class PtsDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Pts>, PtsDao
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
	 * @return PtsPk
	 */
	public PtsPk insert(Pts dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
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
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getPtsCode(),dto.getPtsDate(),dto.getPackStyleSize(),dto.getCanCode(),dto.getForBrand(),dto.getReff(),dto.getNsDs(),dto.getProductBatch(),dto.getBasket(),dto.getQuantity(),dto.getFlkPercent(),dto.getNw(),dto.getDw(),dto.getPw(),dto.getReleaseTo(),dto.getRemarks(),dto.getIssuedBy(),dto.getCheckedBy(),dto.getReceivedBy(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		PtsPk pk = new PtsPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the pts table.
	 */
	public void update(PtsPk pk, Pts dto) throws PtsDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET pts_code = ?, pts_date = ?, pack_style_size = ?, can_code = ?, for_brand = ?, reff = ?, ns_ds = ?, product_batch = ?, basket = ?, quantity = ?, flk_percent = ?, nw = ?, dw = ?, pw = ?, release_to = ?, remarks = ?, issued_by = ?, checked_by = ?, received_by = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
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
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getPtsCode(),dto.getPtsDate(),dto.getPackStyleSize(),dto.getCanCode(),dto.getForBrand(),dto.getReff(),dto.getNsDs(),dto.getProductBatch(),dto.getBasket(),dto.getQuantity(),dto.getFlkPercent(),dto.getNw(),dto.getDw(),dto.getPw(),dto.getReleaseTo(),dto.getRemarks(),dto.getIssuedBy(),dto.getCheckedBy(),dto.getReceivedBy(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the pts table.
	 */
	@Transactional
	public void delete(PtsPk pk) throws PtsDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Pts
	 */
	public Pts mapRow(ResultSet rs, int row) throws SQLException
	{
		Pts dto = new Pts();
		dto.setId( rs.getInt( 1 ) );
		dto.setPtsCode( rs.getString( 2 ) );
		dto.setPtsDate( rs.getString( 3 ) );
		dto.setPackStyleSize( rs.getString( 4 ) );
		dto.setCanCode( rs.getString( 5 ) );
		dto.setForBrand( rs.getString( 6 ) );
		dto.setReff( rs.getString( 7 ) );
		dto.setNsDs( rs.getString( 8 ) );
		dto.setProductBatch( rs.getString( 9 ) );
		dto.setBasket( rs.getString( 10 ) );
		dto.setQuantity( rs.getInt( 11 ) );
		if (rs.wasNull()) {
			dto.setQuantityNull( true );
		}
		
		dto.setFlkPercent( rs.getInt( 12 ) );
		if (rs.wasNull()) {
			dto.setFlkPercentNull( true );
		}
		
		dto.setNw( rs.getString( 13 ) );
		dto.setDw( rs.getString( 14 ) );
		dto.setPw( rs.getString( 15 ) );
		dto.setReleaseTo( rs.getString( 16 ) );
		dto.setRemarks( rs.getString( 17 ) );
		dto.setIssuedBy( rs.getString( 18 ) );
		dto.setCheckedBy( rs.getString( 19 ) );
		dto.setReceivedBy( rs.getString( 20 ) );
		dto.setIsActive( rs.getString( 21 ) );
		dto.setIsDelete( rs.getString( 22 ) );
		dto.setCreatedBy( rs.getString( 23 ) );
		dto.setCreatedDate( rs.getTimestamp(24 ) );
		dto.setUpdatedBy( rs.getString( 25 ) );
		dto.setUpdatedDate( rs.getTimestamp(26 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..pts";
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Pts findByPrimaryKey(int id) throws PtsDaoException
	{
		try {
			List<Pts> list = jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria ''.
	 */
	@Transactional
	public List<Pts> findAll() throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Pts> findWhereIdEquals(int id) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'pts_code = :ptsCode'.
	 */
	@Transactional
	public List<Pts> findWherePtsCodeEquals(String ptsCode) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE pts_code = ? ORDER BY pts_code", this,ptsCode);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'pts_date = :ptsDate'.
	 */
	@Transactional
	public List<Pts> findWherePtsDateEquals(String ptsDate) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE pts_date = ? ORDER BY pts_date", this,ptsDate);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'pack_style_size = :packStyleSize'.
	 */
	@Transactional
	public List<Pts> findWherePackStyleSizeEquals(String packStyleSize) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE pack_style_size = ? ORDER BY pack_style_size", this,packStyleSize);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'can_code = :canCode'.
	 */
	@Transactional
	public List<Pts> findWhereCanCodeEquals(String canCode) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE can_code = ? ORDER BY can_code", this,canCode);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'for_brand = :forBrand'.
	 */
	@Transactional
	public List<Pts> findWhereForBrandEquals(String forBrand) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE for_brand = ? ORDER BY for_brand", this,forBrand);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'reff = :reff'.
	 */
	@Transactional
	public List<Pts> findWhereReffEquals(String reff) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE reff = ? ORDER BY reff", this,reff);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'ns_ds = :nsDs'.
	 */
	@Transactional
	public List<Pts> findWhereNsDsEquals(String nsDs) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE ns_ds = ? ORDER BY ns_ds", this,nsDs);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'product_batch = :productBatch'.
	 */
	@Transactional
	public List<Pts> findWhereProductBatchEquals(String productBatch) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE product_batch = ? ORDER BY product_batch", this,productBatch);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'basket = :basket'.
	 */
	@Transactional
	public List<Pts> findWhereBasketEquals(String basket) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE basket = ? ORDER BY basket", this,basket);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'quantity = :quantity'.
	 */
	@Transactional
	public List<Pts> findWhereQuantityEquals(int quantity) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE quantity = ? ORDER BY quantity", this,quantity);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'flk_percent = :flkPercent'.
	 */
	@Transactional
	public List<Pts> findWhereFlkPercentEquals(int flkPercent) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE flk_percent = ? ORDER BY flk_percent", this,flkPercent);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'nw = :nw'.
	 */
	@Transactional
	public List<Pts> findWhereNwEquals(String nw) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE nw = ? ORDER BY nw", this,nw);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'dw = :dw'.
	 */
	@Transactional
	public List<Pts> findWhereDwEquals(String dw) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE dw = ? ORDER BY dw", this,dw);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'pw = :pw'.
	 */
	@Transactional
	public List<Pts> findWherePwEquals(String pw) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE pw = ? ORDER BY pw", this,pw);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'release_to = :releaseTo'.
	 */
	@Transactional
	public List<Pts> findWhereReleaseToEquals(String releaseTo) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE release_to = ? ORDER BY release_to", this,releaseTo);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'remarks = :remarks'.
	 */
	@Transactional
	public List<Pts> findWhereRemarksEquals(String remarks) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE remarks = ? ORDER BY remarks", this,remarks);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'issued_by = :issuedBy'.
	 */
	@Transactional
	public List<Pts> findWhereIssuedByEquals(String issuedBy) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE issued_by = ? ORDER BY issued_by", this,issuedBy);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'checked_by = :checkedBy'.
	 */
	@Transactional
	public List<Pts> findWhereCheckedByEquals(String checkedBy) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE checked_by = ? ORDER BY checked_by", this,checkedBy);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'received_by = :receivedBy'.
	 */
	@Transactional
	public List<Pts> findWhereReceivedByEquals(String receivedBy) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE received_by = ? ORDER BY received_by", this,receivedBy);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<Pts> findWhereIsActiveEquals(String isActive) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<Pts> findWhereIsDeleteEquals(String isDelete) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<Pts> findWhereCreatedByEquals(String createdBy) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<Pts> findWhereCreatedDateEquals(Date createdDate) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<Pts> findWhereUpdatedByEquals(String updatedBy) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the pts table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<Pts> findWhereUpdatedDateEquals(Date updatedDate) throws PtsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, pts_code, pts_date, pack_style_size, can_code, for_brand, reff, ns_ds, product_batch, basket, quantity, flk_percent, nw, dw, pw, release_to, remarks, issued_by, checked_by, received_by, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new PtsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the pts table that matches the specified primary-key value.
	 */
	public Pts findByPrimaryKey(PtsPk pk) throws PtsDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

	@Override
	public List<Pts> findPtsPaging(Pts p, int page) throws PtsDaoException {
		try {
        	String ptsCode = p.getPtsCode();
        	String ptsDate = p.getPtsDate();
        	
        	int i = page;
        	Map map = new HashMap();
        	map.put("i", i);
        
        	StringBuffer sb = new StringBuffer();
        	
        	if(p == null){
        		p = new Pts();
        	}
        	
        	if(ptsCode == null || ptsDate == null){
        		ptsCode = "%";
        		ptsDate = "%";
        		
        		sb.append("declare @Page int, @PageSize int "
        				+"set @Page = '"+i+"'; "
        				+"set @PageSize = 10; "
        				+"with PagedResult "
        				+"as (select ROW_NUMBER() over (order by id asc) as id, pts_code, pts_date, is_active from pts" +
        						" where pts_code like '%"+ptsCode+"%' and pts_date like '%"+ptsDate+"%' ) "
        				    +"select * from PagedResult where id between "
        				+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
        				     +"else @Page end and @PageSize * @Page ");
        		
        	}else{
        		
        		sb.append("declare @Page int, @PageSize int "
        				+"set @Page = '"+i+"'; "
        				+"set @PageSize = 10; "
        				+"with PagedResult "
        				+"as (select ROW_NUMBER() over (order by id asc) as id, pts_code, pts_date, is_active from pts" +
        						" where pts_code like '%"+ptsCode+"%' and pts_date like '%"+ptsDate+"%' ) "
        				    +"select * from PagedResult where id between "
        				+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
        				     +"else @Page end and @PageSize * @Page ");
        		
        	}
        	
        	
        	
        	return jdbcTemplate.query(sb.toString(),new PtslistMap(),map);	
        
        } catch (Exception e) {
            throw new PtsDaoException("Query failed", e);
        }
	}

}

package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.BeritaAcaraDao;
import com.app.wms.engine.db.dto.BeritaAcara;
import com.app.wms.engine.db.dto.BeritaAcaraPk;
import com.app.wms.engine.db.dto.map.BeritaAcaraListMap;
import com.app.wms.engine.db.exceptions.BeritaAcaraDaoException;

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

public class BeritaAcaraDaoImpl extends AbstractDAO implements ParameterizedRowMapper<BeritaAcara>, BeritaAcaraDao
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
	 * @return BeritaAcaraPk
	 */
	public BeritaAcaraPk insert(BeritaAcara dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
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
		su.update( new Object[] { dto.getBeritaNo(),dto.getBeritaDate(),dto.getPonumber(),dto.getPodate(),dto.getProductcode(),dto.getQty(),dto.getUomName(),dto.getTotal(),dto.getContractorName(),dto.getResultWork(),dto.getPertialCompletion(),dto.getRetention(),dto.getDescription(),dto.getProgress(),dto.getTotalProgress(),dto.getEndUser(),dto.getPlantEngineer(),dto.getOperationEngineer(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		BeritaAcaraPk pk = new BeritaAcaraPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the berita_acara table.
	 */
	public void update(BeritaAcaraPk pk, BeritaAcara dto) throws BeritaAcaraDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET berita_no = ?, berita_date = ?, ponumber = ?, podate = ?, productcode = ?, qty = ?, uom_name = ?, total = ?, contractor_name = ?, result_work = ?, pertial_completion = ?, retention = ?, description = ?, progress = ?, total_progress = ?, end_user = ?, plant_engineer = ?, operation_engineer = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
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
		su.update( new Object[] { dto.getBeritaNo(),dto.getBeritaDate(),dto.getPonumber(),dto.getPodate(),dto.getProductcode(),dto.getQty(),dto.getUomName(),dto.getTotal(),dto.getContractorName(),dto.getResultWork(),dto.getPertialCompletion(),dto.getRetention(),dto.getDescription(),dto.getProgress(),dto.getTotalProgress(),dto.getEndUser(),dto.getPlantEngineer(),dto.getOperationEngineer(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the berita_acara table.
	 */
	@Transactional
	public void delete(BeritaAcaraPk pk) throws BeritaAcaraDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return BeritaAcara
	 */
	public BeritaAcara mapRow(ResultSet rs, int row) throws SQLException
	{
		BeritaAcara dto = new BeritaAcara();
		dto.setId( rs.getInt( 1 ) );
		dto.setBeritaNo( rs.getString( 2 ) );
		dto.setBeritaDate( rs.getTimestamp(3 ) );
		dto.setPonumber( rs.getString( 4 ) );
		dto.setPodate( rs.getTimestamp(5 ) );
		dto.setProductcode( rs.getString( 6 ) );
		dto.setQty( rs.getBigDecimal( 7 ) );
		if (rs.wasNull()) {
			dto.setQtyNull( true );
		}
		
		dto.setUomName( rs.getString( 8 ) );
		dto.setTotal( rs.getBigDecimal( 9 ) );
		if (rs.wasNull()) {
			dto.setTotalNull( true );
		}
		
		dto.setContractorName( rs.getString( 10 ) );
		dto.setResultWork( rs.getBigDecimal( 11 ) );
		if (rs.wasNull()) {
			dto.setResultWorkNull( true );
		}
		
		dto.setPertialCompletion( rs.getBigDecimal( 12 ) );
		if (rs.wasNull()) {
			dto.setPertialCompletionNull( true );
		}
		
		dto.setRetention( rs.getBigDecimal( 13 ) );
		if (rs.wasNull()) {
			dto.setRetentionNull( true );
		}
		
		dto.setDescription( rs.getString( 14 ) );
		dto.setProgress( rs.getBigDecimal( 15 ) );
		if (rs.wasNull()) {
			dto.setProgressNull( true );
		}
		
		dto.setTotalProgress( rs.getBigDecimal( 16 ) );
		if (rs.wasNull()) {
			dto.setTotalProgressNull( true );
		}
		
		dto.setEndUser( rs.getString( 17 ) );
		dto.setPlantEngineer( rs.getString( 18 ) );
		dto.setOperationEngineer( rs.getString( 19 ) );
		dto.setIsActive( rs.getString( 20 ) );
		dto.setIsDelete( rs.getString( 21 ) );
		dto.setCreatedBy( rs.getString( 22 ) );
		dto.setCreatedDate( rs.getTimestamp(23 ) );
		dto.setUpdatedBy( rs.getString( 24 ) );
		dto.setUpdatedDate( rs.getTimestamp(25 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "berita_acara";
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'id = :id'.
	 */
	@Transactional
	public BeritaAcara findByPrimaryKey(int id) throws BeritaAcaraDaoException
	{
		try {
			List<BeritaAcara> list = jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria ''.
	 */
	@Transactional
	public List<BeritaAcara> findAll() throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereIdEquals(int id) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'berita_no = :beritaNo'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereBeritaNoEquals(String beritaNo) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE berita_no = ? ORDER BY berita_no", this,beritaNo);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'berita_date = :beritaDate'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereBeritaDateEquals(Date beritaDate) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE berita_date = ? ORDER BY berita_date", this,beritaDate);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'ponumber = :ponumber'.
	 */
	@Transactional
	public List<BeritaAcara> findWherePonumberEquals(String ponumber) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE ponumber = ? ORDER BY ponumber", this,ponumber);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'podate = :podate'.
	 */
	@Transactional
	public List<BeritaAcara> findWherePodateEquals(Date podate) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE podate = ? ORDER BY podate", this,podate);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'productcode = :productcode'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereProductcodeEquals(String productcode) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE productcode = ? ORDER BY productcode", this,productcode);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'qty = :qty'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereQtyEquals(float qty) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE qty = ? ORDER BY qty", this,qty);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'uom_name = :uomName'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereUomNameEquals(String uomName) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE uom_name = ? ORDER BY uom_name", this,uomName);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'total = :total'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereTotalEquals(float total) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE total = ? ORDER BY total", this,total);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'contractor_name = :contractorName'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereContractorNameEquals(String contractorName) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE contractor_name = ? ORDER BY contractor_name", this,contractorName);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'result_work = :resultWork'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereResultWorkEquals(float resultWork) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE result_work = ? ORDER BY result_work", this,resultWork);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'pertial_completion = :pertialCompletion'.
	 */
	@Transactional
	public List<BeritaAcara> findWherePertialCompletionEquals(float pertialCompletion) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE pertial_completion = ? ORDER BY pertial_completion", this,pertialCompletion);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'retention = :retention'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereRetentionEquals(float retention) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE retention = ? ORDER BY retention", this,retention);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'description = :description'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereDescriptionEquals(String description) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE description = ? ORDER BY description", this,description);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'progress = :progress'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereProgressEquals(float progress) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE progress = ? ORDER BY progress", this,progress);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'total_progress = :totalProgress'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereTotalProgressEquals(float totalProgress) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE total_progress = ? ORDER BY total_progress", this,totalProgress);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'end_user = :endUser'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereEndUserEquals(String endUser) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE end_user = ? ORDER BY end_user", this,endUser);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'plant_engineer = :plantEngineer'.
	 */
	@Transactional
	public List<BeritaAcara> findWherePlantEngineerEquals(String plantEngineer) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE plant_engineer = ? ORDER BY plant_engineer", this,plantEngineer);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'operation_engineer = :operationEngineer'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereOperationEngineerEquals(String operationEngineer) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE operation_engineer = ? ORDER BY operation_engineer", this,operationEngineer);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereIsActiveEquals(String isActive) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereIsDeleteEquals(String isDelete) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereCreatedByEquals(String createdBy) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereCreatedDateEquals(Date createdDate) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereUpdatedByEquals(String updatedBy) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<BeritaAcara> findWhereUpdatedDateEquals(Date updatedDate) throws BeritaAcaraDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, berita_no, berita_date, ponumber, podate, productcode, qty, uom_name, total, contractor_name, result_work, pertial_completion, retention, description, progress, total_progress, end_user, plant_engineer, operation_engineer, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new BeritaAcaraDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the berita_acara table that matches the specified primary-key value.
	 */
	public BeritaAcara findByPrimaryKey(BeritaAcaraPk pk) throws BeritaAcaraDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

	@Override
	public List<BeritaAcara> findBeritaAcaraPaging(BeritaAcara b, int page)	throws BeritaAcaraDaoException {
		try {
			
			String beritaNo = b.getBeritaNo();
			String ponumber = b.getPonumber();
			String productcode =  b.getProductcode();

			int i = page;
        	Map map = new HashMap();
        	map.put("i", i);
        
        	StringBuffer sb = new StringBuffer();
        	
        	if(b == null){
        		b = new BeritaAcara();
        	}
        	
        	if(beritaNo == null || ponumber == null){
        		beritaNo = "%";
        		ponumber = "%";
        		
        		sb.append("declare @Page int, @PageSize int "
        				+"set @Page = '"+i+"'; "
        				+"set @PageSize = 10; "
        				+"with PagedResult "
        				+"as (select ROW_NUMBER() over (order by id asc) as id, berita_no, berita_date, ponumber from berita_acara" +
        						" where berita_no like '%"+beritaNo+"%' and ponumber like '%"+ponumber+"%' ) "
        				    +"select * from PagedResult where id between "
        				+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
        				     +"else @Page end and @PageSize * @Page ");
        		
        	}else{
        		
        		sb.append("declare @Page int, @PageSize int "
        				+"set @Page = '"+i+"'; "
        				+"set @PageSize = 10; "
        				+"with PagedResult "
        				+"as (select ROW_NUMBER() over (order by id asc) as id, berita_no, berita_date, ponumber from berita_acara" +
        						" where berita_no like '%"+beritaNo+"%' and ponumber like '%"+ponumber+"%' ) "
        				    +"select * from PagedResult where id between "
        				+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
        				     +"else @Page end and @PageSize * @Page ");
        		
        	}
        	
        	return jdbcTemplate.query(sb.toString(),new BeritaAcaraListMap(),map);	
        
        } catch (Exception e) {
            throw new BeritaAcaraDaoException("Query failed", e);
        }

	}

}

package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.DrDetailDao;
import com.app.wms.engine.db.dto.DrDetail;
import com.app.wms.engine.db.dto.DrDetailPk;
import com.app.wms.engine.db.exceptions.DrDetailDaoException;
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

public class DrDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<DrDetail>, DrDetailDao
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
	 * @return DrDetailPk
	 */
	public DrDetailPk insert(DrDetail dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( drnumber, productcode, qtyreal, status, expirydate, remarks, qtygood, qtydmg, producttype ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getDrnumber(),dto.getProductcode(),dto.getQtyreal(),dto.getStatus(),dto.getExpirydate(),dto.getRemarks(),dto.getQtygood(),dto.getQtydmg(),dto.getProducttype()} );
		DrDetailPk pk = new DrDetailPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the dr_detail table.
	 */
	public void update(DrDetailPk pk, DrDetail dto) throws DrDetailDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET drnumber = ?, productcode = ?, qtyreal = ?, status = ?, expirydate = ?, remarks = ?, qtygood = ?, qtydmg = ?, producttype = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getDrnumber(),dto.getProductcode(),dto.getQtyreal(),dto.getStatus(),dto.getExpirydate(),dto.getRemarks(),dto.getQtygood(),dto.getQtydmg(),dto.getProducttype(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the dr_detail table.
	 */
	@Transactional
	public void delete(DrDetailPk pk) throws DrDetailDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return DrDetail
	 */
	public DrDetail mapRow(ResultSet rs, int row) throws SQLException
	{
		DrDetail dto = new DrDetail();
		dto.setId( rs.getInt( 1 ) );
		dto.setDrnumber( rs.getString( 2 ) );
		dto.setProductcode( rs.getString( 3 ) );
		dto.setQtyreal( rs.getBigDecimal( 4 ) );
		if (rs.wasNull()) {
			dto.setQtyrealNull( true );
		}
		
		dto.setStatus( rs.getString( 5 ) );
		dto.setExpirydate( rs.getTimestamp(6 ) );
		dto.setRemarks( rs.getString( 7 ) );
		dto.setQtygood( rs.getFloat( 8 ) );
		if (rs.wasNull()) {
			dto.setQtygoodNull( true );
		}
		
		dto.setQtydmg( rs.getFloat( 9 ) );
		if (rs.wasNull()) {
			dto.setQtydmgNull( true );
		}
		
		dto.setProducttype( rs.getString( 10 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..dr_detail";
	}

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public DrDetail findByPrimaryKey(int id) throws DrDetailDaoException
	{
		try {
			List<DrDetail> list = jdbcTemplate.query("SELECT id, drnumber, productcode, qtyreal, status, expirydate, remarks, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new DrDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr_detail table that match the criteria ''.
	 */
	@Transactional
	public List<DrDetail> findAll() throws DrDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, productcode, qtyreal, status, expirydate, remarks, qtygood, qtydmg, producttype FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new DrDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<DrDetail> findWhereIdEquals(int id) throws DrDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, productcode, qtyreal, status, expirydate, remarks, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new DrDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'drnumber = :drnumber'.
	 */
	@Transactional
	public List<DrDetail> findWhereDrnumberEquals(String drnumber) throws DrDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, productcode, qtyreal, status, expirydate, remarks, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE drnumber = ? ORDER BY drnumber", this,drnumber);
		}
		catch (Exception e) {
			throw new DrDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'productcode = :productcode'.
	 */
	@Transactional
	public List<DrDetail> findWhereProductcodeEquals(String productcode) throws DrDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, productcode, qtyreal, status, expirydate, remarks, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE productcode = ? ORDER BY productcode", this,productcode);
		}
		catch (Exception e) {
			throw new DrDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'qtyreal = :qtyreal'.
	 */
	@Transactional
	public List<DrDetail> findWhereQtyrealEquals(float qtyreal) throws DrDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, productcode, qtyreal, status, expirydate, remarks, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE qtyreal = ? ORDER BY qtyreal", this,qtyreal);
		}
		catch (Exception e) {
			throw new DrDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'status = :status'.
	 */
	@Transactional
	public List<DrDetail> findWhereStatusEquals(String status) throws DrDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, productcode, qtyreal, status, expirydate, remarks, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE status = ? ORDER BY status", this,status);
		}
		catch (Exception e) {
			throw new DrDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'expirydate = :expirydate'.
	 */
	@Transactional
	public List<DrDetail> findWhereExpirydateEquals(Date expirydate) throws DrDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, productcode, qtyreal, status, expirydate, remarks, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE expirydate = ? ORDER BY expirydate", this,expirydate);
		}
		catch (Exception e) {
			throw new DrDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'remarks = :remarks'.
	 */
	@Transactional
	public List<DrDetail> findWhereRemarksEquals(String remarks) throws DrDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, productcode, qtyreal, status, expirydate, remarks, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE remarks = ? ORDER BY remarks", this,remarks);
		}
		catch (Exception e) {
			throw new DrDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'qtygood = :qtygood'.
	 */
	@Transactional
	public List<DrDetail> findWhereQtygoodEquals(float qtygood) throws DrDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, productcode, qtyreal, status, expirydate, remarks, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE qtygood = ? ORDER BY qtygood", this,qtygood);
		}
		catch (Exception e) {
			throw new DrDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'qtydmg = :qtydmg'.
	 */
	@Transactional
	public List<DrDetail> findWhereQtydmgEquals(float qtydmg) throws DrDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, productcode, qtyreal, status, expirydate, remarks, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE qtydmg = ? ORDER BY qtydmg", this,qtydmg);
		}
		catch (Exception e) {
			throw new DrDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'producttype = :producttype'.
	 */
	@Transactional
	public List<DrDetail> findWhereProducttypeEquals(String producttype) throws DrDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, productcode, qtyreal, status, expirydate, remarks, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE producttype = ? ORDER BY producttype", this,producttype);
		}
		catch (Exception e) {
			throw new DrDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the dr_detail table that matches the specified primary-key value.
	 */
	public DrDetail findByPrimaryKey(DrDetailPk pk) throws DrDetailDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

}

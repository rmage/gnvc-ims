package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.GoodreceiveDetailDao;
import com.app.wms.engine.db.dto.GoodreceiveDetail;
import com.app.wms.engine.db.dto.GoodreceiveDetailPk;
import com.app.wms.engine.db.exceptions.GoodreceiveDetailDaoException;
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

public class GoodreceiveDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<GoodreceiveDetail>, GoodreceiveDetailDao
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
	 * @return GoodreceiveDetailPk
	 */
	public GoodreceiveDetailPk insert(GoodreceiveDetail dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( grnumber, productcode, qtyreal, status, expirydate, remark, lotid, qtygood, qtydmg, producttype ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getGrnumber(),dto.getProductcode(),dto.getQtyreal(),dto.getStatus(),dto.getExpirydate(),dto.getRemark(),dto.getLotid(),dto.getQtygood(),dto.getQtydmg(),dto.getProducttype()} );
		GoodreceiveDetailPk pk = new GoodreceiveDetailPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the goodreceive_detail table.
	 */
	public void update(GoodreceiveDetailPk pk, GoodreceiveDetail dto) throws GoodreceiveDetailDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET grnumber = ?, productcode = ?, qtyreal = ?, status = ?, expirydate = ?, remark = ?, lotid = ?, qtygood = ?, qtydmg = ?, producttype = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getGrnumber(),dto.getProductcode(),dto.getQtyreal(),dto.getStatus(),dto.getExpirydate(),dto.getRemark(),dto.getLotid(),dto.getQtygood(),dto.getQtydmg(),dto.getProducttype(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the goodreceive_detail table.
	 */
	@Transactional
	public void delete(GoodreceiveDetailPk pk) throws GoodreceiveDetailDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return GoodreceiveDetail
	 */
	public GoodreceiveDetail mapRow(ResultSet rs, int row) throws SQLException
	{
		GoodreceiveDetail dto = new GoodreceiveDetail();
		dto.setId( rs.getInt( 1 ) );
		dto.setGrnumber( rs.getString( 2 ) );
		dto.setProductcode( rs.getString( 3 ) );
		dto.setQtyreal( rs.getFloat( 4 ) );
		if (rs.wasNull()) {
			dto.setQtyrealNull( true );
		}
		
		dto.setStatus( rs.getString( 5 ) );
		dto.setExpirydate( rs.getTimestamp(6 ) );
		dto.setRemark( rs.getString( 7 ) );
		dto.setLotid( rs.getInt( 8 ) );
		if (rs.wasNull()) {
			dto.setLotidNull( true );
		}
		
		dto.setQtygood( rs.getFloat( 9 ) );
		if (rs.wasNull()) {
			dto.setQtygoodNull( true );
		}
		
		dto.setQtydmg( rs.getFloat( 10 ) );
		if (rs.wasNull()) {
			dto.setQtydmgNull( true );
		}
		
		dto.setProducttype( rs.getString( 11 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..goodreceive_detail";
	}

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public GoodreceiveDetail findByPrimaryKey(int id) throws GoodreceiveDetailDaoException
	{
		try {
			List<GoodreceiveDetail> list = jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new GoodreceiveDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria ''.
	 */
	@Transactional
	public List<GoodreceiveDetail> findAll() throws GoodreceiveDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, qtygood, qtydmg, producttype FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new GoodreceiveDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<GoodreceiveDetail> findWhereIdEquals(int id) throws GoodreceiveDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new GoodreceiveDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'grnumber = :grnumber'.
	 */
	@Transactional
	public List<GoodreceiveDetail> findWhereGrnumberEquals(String grnumber) throws GoodreceiveDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE grnumber = ? ORDER BY grnumber", this,grnumber);
		}
		catch (Exception e) {
			throw new GoodreceiveDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'productcode = :productcode'.
	 */
	@Transactional
	public List<GoodreceiveDetail> findWhereProductcodeEquals(String productcode) throws GoodreceiveDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE productcode = ? ORDER BY productcode", this,productcode);
		}
		catch (Exception e) {
			throw new GoodreceiveDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'qtyreal = :qtyreal'.
	 */
	@Transactional
	public List<GoodreceiveDetail> findWhereQtyrealEquals(float qtyreal) throws GoodreceiveDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE qtyreal = ? ORDER BY qtyreal", this,qtyreal);
		}
		catch (Exception e) {
			throw new GoodreceiveDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'status = :status'.
	 */
	@Transactional
	public List<GoodreceiveDetail> findWhereStatusEquals(String status) throws GoodreceiveDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE status = ? ORDER BY status", this,status);
		}
		catch (Exception e) {
			throw new GoodreceiveDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'expirydate = :expirydate'.
	 */
	@Transactional
	public List<GoodreceiveDetail> findWhereExpirydateEquals(Date expirydate) throws GoodreceiveDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE expirydate = ? ORDER BY expirydate", this,expirydate);
		}
		catch (Exception e) {
			throw new GoodreceiveDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'remark = :remark'.
	 */
	@Transactional
	public List<GoodreceiveDetail> findWhereRemarkEquals(String remark) throws GoodreceiveDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE remark = ? ORDER BY remark", this,remark);
		}
		catch (Exception e) {
			throw new GoodreceiveDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'lotid = :lotid'.
	 */
	@Transactional
	public List<GoodreceiveDetail> findWhereLotidEquals(int lotid) throws GoodreceiveDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE lotid = ? ORDER BY lotid", this,lotid);
		}
		catch (Exception e) {
			throw new GoodreceiveDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'qtygood = :qtygood'.
	 */
	@Transactional
	public List<GoodreceiveDetail> findWhereQtygoodEquals(float qtygood) throws GoodreceiveDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE qtygood = ? ORDER BY qtygood", this,qtygood);
		}
		catch (Exception e) {
			throw new GoodreceiveDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'qtydmg = :qtydmg'.
	 */
	@Transactional
	public List<GoodreceiveDetail> findWhereQtydmgEquals(float qtydmg) throws GoodreceiveDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE qtydmg = ? ORDER BY qtydmg", this,qtydmg);
		}
		catch (Exception e) {
			throw new GoodreceiveDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'producttype = :producttype'.
	 */
	@Transactional
	public List<GoodreceiveDetail> findWhereProducttypeEquals(String producttype) throws GoodreceiveDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, qtygood, qtydmg, producttype FROM " + getTableName() + " WHERE producttype = ? ORDER BY producttype", this,producttype);
		}
		catch (Exception e) {
			throw new GoodreceiveDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the goodreceive_detail table that matches the specified primary-key value.
	 */
	public GoodreceiveDetail findByPrimaryKey(GoodreceiveDetailPk pk) throws GoodreceiveDetailDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

}

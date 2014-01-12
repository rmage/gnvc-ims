package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.EdsDetailDao;
import com.app.wms.engine.db.dto.EdsDetail;
import com.app.wms.engine.db.dto.EdsDetailPk;
import com.app.wms.engine.db.exceptions.EdsDetailDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class EdsDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<EdsDetail>, EdsDetailDao
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
	 * @return EdsDetailPk
	 */
	public EdsDetailPk insert(EdsDetail dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( edsnumber, packStyle, canSize, productcode, qtyCases, remarks, unitPrice, amount, producttype ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getEdsnumber(),dto.getPackStyle(),dto.getCanSize(),dto.getProductcode(),dto.getQtyCases(),dto.getRemarks(),dto.getUnitPrice(),dto.getAmount(),dto.getProducttype()} );
		EdsDetailPk pk = new EdsDetailPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the eds_detail table.
	 */
	public void update(EdsDetailPk pk, EdsDetail dto) throws EdsDetailDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET edsnumber = ?, packStyle = ?, canSize = ?, productcode = ?, qtyCases = ?, remarks = ?, unitPrice = ?, amount = ?, producttype = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getEdsnumber(),dto.getPackStyle(),dto.getCanSize(),dto.getProductcode(),dto.getQtyCases(),dto.getRemarks(),dto.getUnitPrice(),dto.getAmount(),dto.getProducttype(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the eds_detail table.
	 */
	@Transactional
	public void delete(EdsDetailPk pk) throws EdsDetailDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return EdsDetail
	 */
	public EdsDetail mapRow(ResultSet rs, int row) throws SQLException
	{
		EdsDetail dto = new EdsDetail();
		dto.setId( rs.getInt( 1 ) );
		dto.setEdsnumber( rs.getString( 2 ) );
		dto.setPackStyle( rs.getString( 3 ) );
		dto.setCanSize( rs.getString( 4 ) );
		dto.setProductcode( rs.getString( 5 ) );
		dto.setQtyCases( rs.getFloat( 6 ) );
		if (rs.wasNull()) {
			dto.setQtyCasesNull( true );
		}
		
		dto.setRemarks( rs.getString( 7 ) );
		dto.setUnitPrice( rs.getInt( 8 ) );
		if (rs.wasNull()) {
			dto.setUnitPriceNull( true );
		}
		
		dto.setAmount( rs.getInt( 9 ) );
		if (rs.wasNull()) {
			dto.setAmountNull( true );
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
		return "inventory..eds_detail";
	}

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public EdsDetail findByPrimaryKey(int id) throws EdsDetailDaoException
	{
		try {
			List<EdsDetail> list = jdbcTemplate.query("SELECT id, edsnumber, packStyle, canSize, productcode, qtyCases, remarks, unitPrice, amount, producttype FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new EdsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds_detail table that match the criteria ''.
	 */
	@Transactional
	public List<EdsDetail> findAll() throws EdsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, packStyle, canSize, productcode, qtyCases, remarks, unitPrice, amount, producttype FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new EdsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<EdsDetail> findWhereIdEquals(int id) throws EdsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, packStyle, canSize, productcode, qtyCases, remarks, unitPrice, amount, producttype FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new EdsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'edsnumber = :edsnumber'.
	 */
	@Transactional
	public List<EdsDetail> findWhereEdsnumberEquals(String edsnumber) throws EdsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, packStyle, canSize, productcode, qtyCases, remarks, unitPrice, amount, producttype FROM " + getTableName() + " WHERE edsnumber = ? ORDER BY edsnumber", this,edsnumber);
		}
		catch (Exception e) {
			throw new EdsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'packStyle = :packStyle'.
	 */
	@Transactional
	public List<EdsDetail> findWherePackStyleEquals(String packStyle) throws EdsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, packStyle, canSize, productcode, qtyCases, remarks, unitPrice, amount, producttype FROM " + getTableName() + " WHERE packStyle = ? ORDER BY packStyle", this,packStyle);
		}
		catch (Exception e) {
			throw new EdsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'canSize = :canSize'.
	 */
	@Transactional
	public List<EdsDetail> findWhereCanSizeEquals(String canSize) throws EdsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, packStyle, canSize, productcode, qtyCases, remarks, unitPrice, amount, producttype FROM " + getTableName() + " WHERE canSize = ? ORDER BY canSize", this,canSize);
		}
		catch (Exception e) {
			throw new EdsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'productcode = :productcode'.
	 */
	@Transactional
	public List<EdsDetail> findWhereProductcodeEquals(String productcode) throws EdsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, packStyle, canSize, productcode, qtyCases, remarks, unitPrice, amount, producttype FROM " + getTableName() + " WHERE productcode = ? ORDER BY productcode", this,productcode);
		}
		catch (Exception e) {
			throw new EdsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'qtyCases = :qtyCases'.
	 */
	@Transactional
	public List<EdsDetail> findWhereQtyCasesEquals(float qtyCases) throws EdsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, packStyle, canSize, productcode, qtyCases, remarks, unitPrice, amount, producttype FROM " + getTableName() + " WHERE qtyCases = ? ORDER BY qtyCases", this,qtyCases);
		}
		catch (Exception e) {
			throw new EdsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'remarks = :remarks'.
	 */
	@Transactional
	public List<EdsDetail> findWhereRemarksEquals(String remarks) throws EdsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, packStyle, canSize, productcode, qtyCases, remarks, unitPrice, amount, producttype FROM " + getTableName() + " WHERE remarks = ? ORDER BY remarks", this,remarks);
		}
		catch (Exception e) {
			throw new EdsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'unitPrice = :unitPrice'.
	 */
	@Transactional
	public List<EdsDetail> findWhereUnitPriceEquals(int unitPrice) throws EdsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, packStyle, canSize, productcode, qtyCases, remarks, unitPrice, amount, producttype FROM " + getTableName() + " WHERE unitPrice = ? ORDER BY unitPrice", this,unitPrice);
		}
		catch (Exception e) {
			throw new EdsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'amount = :amount'.
	 */
	@Transactional
	public List<EdsDetail> findWhereAmountEquals(int amount) throws EdsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, packStyle, canSize, productcode, qtyCases, remarks, unitPrice, amount, producttype FROM " + getTableName() + " WHERE amount = ? ORDER BY amount", this,amount);
		}
		catch (Exception e) {
			throw new EdsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'producttype = :producttype'.
	 */
	@Transactional
	public List<EdsDetail> findWhereProducttypeEquals(String producttype) throws EdsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, packStyle, canSize, productcode, qtyCases, remarks, unitPrice, amount, producttype FROM " + getTableName() + " WHERE producttype = ? ORDER BY producttype", this,producttype);
		}
		catch (Exception e) {
			throw new EdsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the eds_detail table that matches the specified primary-key value.
	 */
	public EdsDetail findByPrimaryKey(EdsDetailPk pk) throws EdsDetailDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

}

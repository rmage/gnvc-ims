package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.BorDetailDao;
import com.app.wms.engine.db.dto.BorDetail;
import com.app.wms.engine.db.dto.BorDetailPk;
import com.app.wms.engine.db.exceptions.BorDetailDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class BorDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<BorDetail>, BorDetailDao
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
	 * @return BorDetailPk
	 */
	public BorDetailPk insert(BorDetail dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( bornumber, productcode, qty, producttype ) VALUES ( ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getBornumber(),dto.getProductcode(),dto.getQty(),dto.getProducttype()} );
		BorDetailPk pk = new BorDetailPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the bor_detail table.
	 */
	public void update(BorDetailPk pk, BorDetail dto) throws BorDetailDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET bornumber = ?, productcode = ?, qty = ?, producttype = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getBornumber(),dto.getProductcode(),dto.getQty(),dto.getProducttype(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the bor_detail table.
	 */
	@Transactional
	public void delete(BorDetailPk pk) throws BorDetailDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return BorDetail
	 */
	public BorDetail mapRow(ResultSet rs, int row) throws SQLException
	{
		BorDetail dto = new BorDetail();
		dto.setId( rs.getInt( 1 ) );
		dto.setBornumber( rs.getString( 2 ) );
		dto.setProductcode( rs.getString( 3 ) );
		dto.setQty( rs.getFloat( 4 ) );
		if (rs.wasNull()) {
			dto.setQtyNull( true );
		}
		
		dto.setProducttype( rs.getString( 5 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..bor_detail";
	}

	/** 
	 * Returns all rows from the bor_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public BorDetail findByPrimaryKey(int id) throws BorDetailDaoException
	{
		try {
			List<BorDetail> list = jdbcTemplate.query("SELECT id, bornumber, productcode, qty, producttype FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new BorDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bor_detail table that match the criteria ''.
	 */
	@Transactional
	public List<BorDetail> findAll() throws BorDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, bornumber, productcode, qty, producttype FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new BorDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bor_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<BorDetail> findWhereIdEquals(int id) throws BorDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, bornumber, productcode, qty, producttype FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new BorDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bor_detail table that match the criteria 'bornumber = :bornumber'.
	 */
	@Transactional
	public List<BorDetail> findWhereBornumberEquals(String bornumber) throws BorDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, bornumber, productcode, qty, producttype FROM " + getTableName() + " WHERE bornumber = ? ORDER BY bornumber", this,bornumber);
		}
		catch (Exception e) {
			throw new BorDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bor_detail table that match the criteria 'productcode = :productcode'.
	 */
	@Transactional
	public List<BorDetail> findWhereProductcodeEquals(String productcode) throws BorDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, bornumber, productcode, qty, producttype FROM " + getTableName() + " WHERE productcode = ? ORDER BY productcode", this,productcode);
		}
		catch (Exception e) {
			throw new BorDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bor_detail table that match the criteria 'qty = :qty'.
	 */
	@Transactional
	public List<BorDetail> findWhereQtyEquals(float qty) throws BorDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, bornumber, productcode, qty, producttype FROM " + getTableName() + " WHERE qty = ? ORDER BY qty", this,qty);
		}
		catch (Exception e) {
			throw new BorDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the bor_detail table that match the criteria 'producttype = :producttype'.
	 */
	@Transactional
	public List<BorDetail> findWhereProducttypeEquals(String producttype) throws BorDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, bornumber, productcode, qty, producttype FROM " + getTableName() + " WHERE producttype = ? ORDER BY producttype", this,producttype);
		}
		catch (Exception e) {
			throw new BorDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the bor_detail table that matches the specified primary-key value.
	 */
	public BorDetail findByPrimaryKey(BorDetailPk pk) throws BorDetailDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

}

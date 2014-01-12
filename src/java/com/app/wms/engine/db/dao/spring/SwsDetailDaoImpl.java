package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.SwsDetailDao;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.SwsDetail;
import com.app.wms.engine.db.dto.SwsDetailPk;
import com.app.wms.engine.db.dto.map.SwsListDetailJsonMap;
import com.app.wms.engine.db.exceptions.SwsDetailDaoException;

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

public class SwsDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<SwsDetail>, SwsDetailDao
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
	 * @return SwsDetailPk
	 */
	public SwsDetailPk insert(SwsDetail dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( swsnumber, productcode, qty, producttype, productname, uom_name ) VALUES ( ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getSwsnumber(),dto.getProductcode(),dto.getQty(),dto.getProducttype(),dto.getProduct().getProductName(),dto.getProduct().getUom()} );
		SwsDetailPk pk = new SwsDetailPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the sws_detail table.
	 */
	public void update(SwsDetailPk pk, SwsDetail dto) throws SwsDetailDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET swsnumber = ?, productcode = ?, qty = ?, producttype = ?, productname= ?, uom_name = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getSwsnumber(),dto.getProductcode(),dto.getQty(),dto.getProducttype(),dto.getProduct().getProductName(), dto.getProduct().getUom(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the sws_detail table.
	 */
	@Transactional
	public void delete(SwsDetailPk pk) throws SwsDetailDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return SwsDetail
	 */
	public SwsDetail mapRow(ResultSet rs, int row) throws SQLException
	{
		SwsDetail dto = new SwsDetail();
		Product p = new Product();
		
		dto.setId( rs.getInt( 1 ) );
		dto.setSwsnumber( rs.getString( 2 ) );
		dto.setProductcode( rs.getString( 3 ) );
		dto.setQty( rs.getBigDecimal( 4 ) );
		if (rs.wasNull()) {
			dto.setQtyNull( true );
		}
		
		dto.setProducttype( rs.getString( 5 ) );
		p.setProductName(rs.getString(6));
		p.setUom(rs.getString(7));
		dto.setProduct(p);
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..sws_detail";
	}

	/** 
	 * Returns all rows from the sws_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public SwsDetail findByPrimaryKey(int id) throws SwsDetailDaoException
	{
		try {
			List<SwsDetail> list = jdbcTemplate.query("SELECT id, swsnumber, productcode, qty, producttype, productname, uom_name FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new SwsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the sws_detail table that match the criteria ''.
	 */
	@Transactional
	public List<SwsDetail> findAll() throws SwsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, swsnumber, productcode, qty, producttype, productname, uom_name FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new SwsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the sws_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<SwsDetail> findWhereIdEquals(int id) throws SwsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, swsnumber, productcode, qty, producttype, productname, uom_name FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new SwsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the sws_detail table that match the criteria 'swsnumber = :swsnumber'.
	 */
	@Transactional
	public List<SwsDetail> findWhereSwsnumberEquals(String swsnumber) throws SwsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, swsnumber, productcode, qty, producttype, productname, uom_name FROM " + getTableName() + " WHERE swsnumber = ? ORDER BY swsnumber", this,swsnumber);
		}
		catch (Exception e) {
			throw new SwsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the sws_detail table that match the criteria 'productcode = :productcode'.
	 */
	@Transactional
	public List<SwsDetail> findWhereProductcodeEquals(String productcode) throws SwsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, swsnumber, productcode, qty, producttype, productname, uom_name FROM " + getTableName() + " WHERE productcode = ? ORDER BY productcode", this,productcode);
		}
		catch (Exception e) {
			throw new SwsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the sws_detail table that match the criteria 'qty = :qty'.
	 */
	@Transactional
	public List<SwsDetail> findWhereQtyEquals(int qty) throws SwsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, swsnumber, productcode, qty, producttype, productname, uom_name FROM " + getTableName() + " WHERE qty = ? ORDER BY qty", this,qty);
		}
		catch (Exception e) {
			throw new SwsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the sws_detail table that match the criteria 'producttype = :producttype'.
	 */
	@Transactional
	public List<SwsDetail> findWhereProducttypeEquals(String producttype) throws SwsDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, swsnumber, productcode, qty, producttype, productname, uom_name FROM " + getTableName() + " WHERE producttype = ? ORDER BY producttype", this,producttype);
		}
		catch (Exception e) {
			throw new SwsDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the sws_detail table that matches the specified primary-key value.
	 */
	public SwsDetail findByPrimaryKey(SwsDetailPk pk) throws SwsDetailDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}
	
	@Transactional
	public List<SwsDetail> findWhereSwsnumberDetail(String swsnumber) throws SwsDetailDaoException
	{
		try {
			
			
			Map map = new HashMap();
			map.put("swsnumber", swsnumber);
			
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT s.id, s.swsnumber, s.productcode, p.product_name, p.product_category, p.uom_name, s.qty, s.producttype, i.wh_code, i.balance " +
					" FROM sws_detail s" +
					" INNER JOIN product p" +
					" ON s.productcode = p.product_code" +
					" INNER JOIN stock_inventory i" +
					" ON s.productcode = i.product_code" +
					" WHERE swsnumber = '"+swsnumber+"' " +
					" ORDER BY swsnumber");
			
			return jdbcTemplate.query(sb.toString(),new SwsListDetailJsonMap(),map);
		}
		catch (Exception e) {
			throw new SwsDetailDaoException("Query failed", e);
		}
		
	}

}

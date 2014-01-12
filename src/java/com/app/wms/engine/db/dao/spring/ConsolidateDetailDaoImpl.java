package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.ConsolidateDetailDao;
import com.app.wms.engine.db.dto.ConsolidateDetail;
import com.app.wms.engine.db.dto.SalesOrderDetail;
import com.app.wms.engine.db.exceptions.ConsolidateDetailDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDetailDaoException;

import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class ConsolidateDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<ConsolidateDetail>, ConsolidateDetailDao
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
	public void insert(ConsolidateDetail dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( consolidate_no, packing_no, product_id, product_code, product_name, unit_code, qty_consolidate, user_id, corp_id, wh_code ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
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
		su.compile();
		su.update( new Object[] { dto.getConsolidateNo(),dto.getPackingNo(),dto.getProductId(),dto.getProductCode(),dto.getProductName(),dto.getUnitCode(),dto.getQtyConsolidate(),dto.getUserId(),dto.getCorpId(),dto.getWhCode()} );
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return ConsolidateDetail
	 */
	public ConsolidateDetail mapRow(ResultSet rs, int row) throws SQLException
	{
		ConsolidateDetail dto = new ConsolidateDetail();
		dto.setConsolidateNo( rs.getString( 1 ) );
		dto.setPackingNo( rs.getString( 2 ) );
		dto.setProductId( rs.getString( 3 ) );
		dto.setProductCode( rs.getString( 4 ) );
		dto.setProductName( rs.getString( 5 ) );
		dto.setUnitCode( rs.getString( 6 ) );
		dto.setQtyConsolidate( rs.getInt( 7 ) );
		if (rs.wasNull()) {
			dto.setQtyConsolidateNull( true );
		}
		
		dto.setUserId( rs.getString( 8 ) );
		dto.setCorpId( rs.getString( 9 ) );
		dto.setWhCode( rs.getString( 10 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..consolidate_detail";
	}

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria ''.
	 */
	@Transactional
	public List<ConsolidateDetail> findAll() throws ConsolidateDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, packing_no, product_id, product_code, product_name, unit_code, qty_consolidate, user_id, corp_id, wh_code FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new ConsolidateDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'consolidate_no = :consolidateNo'.
	 */
	@Transactional
	public List<ConsolidateDetail> findWhereConsolidateNoEquals(String consolidateNo) throws ConsolidateDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, packing_no, product_id, product_code, product_name, unit_code, qty_consolidate, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE consolidate_no = ? ORDER BY consolidate_no", this,consolidateNo);
		}
		catch (Exception e) {
			throw new ConsolidateDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'packing_no = :packingNo'.
	 */
	@Transactional
	public List<ConsolidateDetail> findWherePackingNoEquals(String packingNo) throws ConsolidateDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, packing_no, product_id, product_code, product_name, unit_code, qty_consolidate, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE packing_no = ? ORDER BY packing_no", this,packingNo);
		}
		catch (Exception e) {
			throw new ConsolidateDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'product_id = :productId'.
	 */
	@Transactional
	public List<ConsolidateDetail> findWhereProductIdEquals(String productId) throws ConsolidateDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, packing_no, product_id, product_code, product_name, unit_code, qty_consolidate, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_id = ? ORDER BY product_id", this,productId);
		}
		catch (Exception e) {
			throw new ConsolidateDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'product_code = :productCode'.
	 */
	@Transactional
	public List<ConsolidateDetail> findWhereProductCodeEquals(String productCode) throws ConsolidateDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, packing_no, product_id, product_code, product_name, unit_code, qty_consolidate, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_code = ? ORDER BY product_code", this,productCode);
		}
		catch (Exception e) {
			throw new ConsolidateDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'product_name = :productName'.
	 */
	@Transactional
	public List<ConsolidateDetail> findWhereProductNameEquals(String productName) throws ConsolidateDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, packing_no, product_id, product_code, product_name, unit_code, qty_consolidate, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_name = ? ORDER BY product_name", this,productName);
		}
		catch (Exception e) {
			throw new ConsolidateDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'unit_code = :unitCode'.
	 */
	@Transactional
	public List<ConsolidateDetail> findWhereUnitCodeEquals(String unitCode) throws ConsolidateDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, packing_no, product_id, product_code, product_name, unit_code, qty_consolidate, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE unit_code = ? ORDER BY unit_code", this,unitCode);
		}
		catch (Exception e) {
			throw new ConsolidateDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'qty_consolidate = :qtyConsolidate'.
	 */
	@Transactional
	public List<ConsolidateDetail> findWhereQtyConsolidateEquals(int qtyConsolidate) throws ConsolidateDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, packing_no, product_id, product_code, product_name, unit_code, qty_consolidate, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE qty_consolidate = ? ORDER BY qty_consolidate", this,qtyConsolidate);
		}
		catch (Exception e) {
			throw new ConsolidateDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'user_id = :userId'.
	 */
	@Transactional
	public List<ConsolidateDetail> findWhereUserIdEquals(String userId) throws ConsolidateDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, packing_no, product_id, product_code, product_name, unit_code, qty_consolidate, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE user_id = ? ORDER BY user_id", this,userId);
		}
		catch (Exception e) {
			throw new ConsolidateDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'corp_id = :corpId'.
	 */
	@Transactional
	public List<ConsolidateDetail> findWhereCorpIdEquals(String corpId) throws ConsolidateDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, packing_no, product_id, product_code, product_name, unit_code, qty_consolidate, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE corp_id = ? ORDER BY corp_id", this,corpId);
		}
		catch (Exception e) {
			throw new ConsolidateDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<ConsolidateDetail> findWhereWhCodeEquals(String whCode) throws ConsolidateDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT consolidate_no, packing_no, product_id, product_code, product_name, unit_code, qty_consolidate, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new ConsolidateDetailDaoException("Query failed", e);
		}
		
	}

	@Override
	public ConsolidateDetail findByPrimaryKey(String ConsolidateNumber)	throws ConsolidateDetailDaoException {
		try {
            List<ConsolidateDetail> list = jdbcTemplate.query("select consolidate_no, packing_no, product_id, product_code, product_name, unit_code, qty_consolidate, user_id, corp_id, wh_code from " + getTableName() + " where consolidate_no = ?", this, ConsolidateNumber);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new ConsolidateDetailDaoException("Query failed", e);
        }
	}

	@Override
	public List<ConsolidateDetail> findDetail(ConsolidateDetail cod) throws ConsolidateDetailDaoException {
		try {
       	 String consolidateNo = cod.getConsolidateNo();
       	 StringBuffer sb = new StringBuffer();
       	 
	    	 sb.append("select " 
		 		  +"consolidate_no, packing_no, product_id, product_code, product_name, unit_code, qty_consolidate, user_id, corp_id, "
		 		  +"wh_code from "
		 		  +"consolidate_detail "
		 		  +"where consolidate_no like '"+consolidateNo+"' ");

       	 return jdbcTemplate.query(sb.toString(), this);
       } catch (Exception e) {
           throw new ConsolidateDetailDaoException("Query failed", e);
       }
	}

}

package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.DoDetailDao;
import com.app.wms.engine.db.dto.DoDetail;
import com.app.wms.engine.db.exceptions.DoDetailDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DoDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<DoDetail>, DoDetailDao
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
	public void insert(DoDetail dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( delivery_no, so_number, picking_id, product_id, product_code, product_name, qty_delivery, unit_code, transporter_type, transporter_name, user_id, corp_id, wh_code ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getDeliveryNo(),dto.getSoNumber(),dto.getPickingId(),dto.getProductId(),dto.getProductCode(),dto.getProductName(),dto.getQtyDelivery(),dto.getUnitCode(),dto.getTransporterType(),dto.getTransporterName(),dto.getUserId(),dto.getCorpId(),dto.getWhCode()} );
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return DoDetail
	 */
	public DoDetail mapRow(ResultSet rs, int row) throws SQLException
	{
		DoDetail dto = new DoDetail();
		dto.setDeliveryNo( rs.getString( 1 ) );
		dto.setSoNumber( rs.getString( 2 ) );
		dto.setPickingId( rs.getString( 3 ) );
		dto.setProductId( rs.getString( 4 ) );
		dto.setProductCode( rs.getString( 5 ) );
		dto.setProductName( rs.getString( 6 ) );
		dto.setQtyDelivery( rs.getBigDecimal( 7 ) );
		if (rs.wasNull()) {
			dto.setQtyDeliveryNull( true );
		}
		
		dto.setUnitCode( rs.getString( 8 ) );
		dto.setTransporterType( rs.getString( 9 ) );
		dto.setTransporterName( rs.getString( 10 ) );
		dto.setUserId( rs.getString( 11 ) );
		dto.setCorpId( rs.getString( 12 ) );
		dto.setWhCode( rs.getString( 13 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "do_detail";
	}

	/** 
	 * Returns all rows from the do_detail table that match the criteria ''.
	 */
	@Transactional
	public List<DoDetail> findAll() throws DoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, so_number, picking_id, product_id, product_code, product_name, qty_delivery, unit_code, transporter_type, transporter_name, user_id, corp_id, wh_code FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new DoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'delivery_no = :deliveryNo'.
	 */
	@Transactional
	public List<DoDetail> findWhereDeliveryNoEquals(String deliveryNo) throws DoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, so_number, picking_id, product_id, product_code, product_name, qty_delivery, unit_code, transporter_type, transporter_name, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE delivery_no = ? ORDER BY delivery_no", this,deliveryNo);
		}
		catch (Exception e) {
			throw new DoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'so_number = :soNumber'.
	 */
	@Transactional
	public List<DoDetail> findWhereSoNumberEquals(String soNumber) throws DoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, so_number, picking_id, product_id, product_code, product_name, qty_delivery, unit_code, transporter_type, transporter_name, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE so_number = ? ORDER BY so_number", this,soNumber);
		}
		catch (Exception e) {
			throw new DoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'picking_id = :pickingId'.
	 */
	@Transactional
	public List<DoDetail> findWherePickingIdEquals(String pickingId) throws DoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, so_number, picking_id, product_id, product_code, product_name, qty_delivery, unit_code, transporter_type, transporter_name, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE picking_id = ? ORDER BY picking_id", this,pickingId);
		}
		catch (Exception e) {
			throw new DoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'product_id = :productId'.
	 */
	@Transactional
	public List<DoDetail> findWhereProductIdEquals(String productId) throws DoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, so_number, picking_id, product_id, product_code, product_name, qty_delivery, unit_code, transporter_type, transporter_name, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_id = ? ORDER BY product_id", this,productId);
		}
		catch (Exception e) {
			throw new DoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'product_code = :productCode'.
	 */
	@Transactional
	public List<DoDetail> findWhereProductCodeEquals(String productCode) throws DoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, so_number, picking_id, product_id, product_code, product_name, qty_delivery, unit_code, transporter_type, transporter_name, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_code = ? ORDER BY product_code", this,productCode);
		}
		catch (Exception e) {
			throw new DoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'product_name = :productName'.
	 */
	@Transactional
	public List<DoDetail> findWhereProductNameEquals(String productName) throws DoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, so_number, picking_id, product_id, product_code, product_name, qty_delivery, unit_code, transporter_type, transporter_name, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_name = ? ORDER BY product_name", this,productName);
		}
		catch (Exception e) {
			throw new DoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'qty_delivery = :qtyDelivery'.
	 */
	@Transactional
	public List<DoDetail> findWhereQtyDeliveryEquals(float qtyDelivery) throws DoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, so_number, picking_id, product_id, product_code, product_name, qty_delivery, unit_code, transporter_type, transporter_name, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE qty_delivery = ? ORDER BY qty_delivery", this,qtyDelivery);
		}
		catch (Exception e) {
			throw new DoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'unit_code = :unitCode'.
	 */
	@Transactional
	public List<DoDetail> findWhereUnitCodeEquals(String unitCode) throws DoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, so_number, picking_id, product_id, product_code, product_name, qty_delivery, unit_code, transporter_type, transporter_name, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE unit_code = ? ORDER BY unit_code", this,unitCode);
		}
		catch (Exception e) {
			throw new DoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'transporter_type = :transporterType'.
	 */
	@Transactional
	public List<DoDetail> findWhereTransporterTypeEquals(String transporterType) throws DoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, so_number, picking_id, product_id, product_code, product_name, qty_delivery, unit_code, transporter_type, transporter_name, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE transporter_type = ? ORDER BY transporter_type", this,transporterType);
		}
		catch (Exception e) {
			throw new DoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'transporter_name = :transporterName'.
	 */
	@Transactional
	public List<DoDetail> findWhereTransporterNameEquals(String transporterName) throws DoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, so_number, picking_id, product_id, product_code, product_name, qty_delivery, unit_code, transporter_type, transporter_name, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE transporter_name = ? ORDER BY transporter_name", this,transporterName);
		}
		catch (Exception e) {
			throw new DoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'user_id = :userId'.
	 */
	@Transactional
	public List<DoDetail> findWhereUserIdEquals(String userId) throws DoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, so_number, picking_id, product_id, product_code, product_name, qty_delivery, unit_code, transporter_type, transporter_name, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE user_id = ? ORDER BY user_id", this,userId);
		}
		catch (Exception e) {
			throw new DoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'corp_id = :corpId'.
	 */
	@Transactional
	public List<DoDetail> findWhereCorpIdEquals(String corpId) throws DoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, so_number, picking_id, product_id, product_code, product_name, qty_delivery, unit_code, transporter_type, transporter_name, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE corp_id = ? ORDER BY corp_id", this,corpId);
		}
		catch (Exception e) {
			throw new DoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<DoDetail> findWhereWhCodeEquals(String whCode) throws DoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, so_number, picking_id, product_id, product_code, product_name, qty_delivery, unit_code, transporter_type, transporter_name, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new DoDetailDaoException("Query failed", e);
		}
		
	}
	
	@Override
	public DoDetail findByPrimaryKey(String DeliveryOrderNumber) throws DoDetailDaoException {
		 try {
	            List<DoDetail> list = jdbcTemplate.query("SELECT delivery_no, so_number, picking_id, product_id, product_code, product_name, qty_delivery, unit_code, transporter_type, transporter_name, user_id, corp_id, wh_code from " + getTableName() + " where delivery_no = ?", this, DeliveryOrderNumber);
	            return list.size() == 0 ? null : list.get(0);
	        } catch (Exception e) {
	            throw new DoDetailDaoException("Query failed", e);
	        }
	}

	@Override
	public List<DoDetail> findDetail(DoDetail dod) throws DoDetailDaoException {
		try {
       	 String doNumber = dod.getDeliveryNo();
       	 StringBuffer sb = new StringBuffer();
       	 
	    	 sb.append("select " 
		 		  +"delivery_no, so_number, picking_id, product_id, product_code, product_name, qty_delivery, unit_code, transporter_type, transporter_name, user_id, corp_id, "
		 		  +"wh_code from "
		 		  +"do_detail "
		 		  +"where delivery_no like '"+doNumber+"' ");

       	 return jdbcTemplate.query(sb.toString(), this);
       } catch (Exception e) {
           throw new DoDetailDaoException("Query failed", e);
       }
	}

}

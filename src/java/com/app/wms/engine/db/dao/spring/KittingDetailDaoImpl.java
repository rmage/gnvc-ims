package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.KittingDetailDao;
import com.app.wms.engine.db.dto.Kitting;
import com.app.wms.engine.db.dto.KittingDetail;
import com.app.wms.engine.db.dto.KittingDetailPk;
import com.app.wms.engine.db.exceptions.KittingDetailDaoException;
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

public class KittingDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<KittingDetail>, KittingDetailDao
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
	 * @return KittingDetailPk
	 */
	public KittingDetailPk insert(KittingDetail dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( kitting_no, so_number, product_id, product_code, product_name, unit_code, qty_pick, qty_kitting, user_id, corp_id, wh_code ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
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
		su.compile();
		su.update( new Object[] { dto.getKittingNo(),dto.getSo_number(),dto.getProductId(),dto.getProductCode(),dto.getProductName(),dto.getUnitCode(), dto.getQtyPick(),dto.getQtyKitting(),dto.getUserId(),dto.getCorpId(),dto.getWhCode()} );
		
		return dto.createPk();
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return KittingDetail
	 */
	public KittingDetail mapRow(ResultSet rs, int row) throws SQLException
	{
		KittingDetail dto = new KittingDetail();
		dto.setKittingNo( rs.getString( 1 ) );
		dto.setSo_number( rs.getString( 2 ) );
		dto.setProductId( rs.getString( 3 ) );
		dto.setProductCode( rs.getString( 4 ) );
		dto.setProductName( rs.getString( 5 ) );
		dto.setUnitCode( rs.getString( 6 ) );
		dto.setQtyKitting( rs.getInt( 7 ) );
		if (rs.wasNull()) {
			dto.setQtyKittingNull( true );
		}
		
		dto.setUserId( rs.getString( 7 ) );
		dto.setCorpId( rs.getString( 8 ) );
		dto.setWhCode( rs.getString( 9 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "kitting_detail";
	}

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria ''.
	 */
	@Transactional
	public List<KittingDetail> findAll() throws KittingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT kitting_no, so_number, product_id, product_code, product_name, unit_code, qty_kitting, user_id, corp_id, wh_code FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new KittingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'kitting_no = :kittingNo'.
	 */
	@Transactional
	public List<KittingDetail> findWhereKittingNoEquals(String kittingNo) throws KittingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT kitting_no, so_number, product_id, product_code, product_name, unit_code, qty_kitting, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE kitting_no = ? ORDER BY kitting_no", this,kittingNo);
		}
		catch (Exception e) {
			throw new KittingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'product_id = :productId'.
	 */
	@Transactional
	public List<KittingDetail> findWhereProductIdEquals(String productId) throws KittingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT kitting_no, so_number, product_id, product_code, product_name, unit_code, qty_kitting, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_id = ? ORDER BY product_id", this,productId);
		}
		catch (Exception e) {
			throw new KittingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'product_code = :productCode'.
	 */
	@Transactional
	public List<KittingDetail> findWhereProductCodeEquals(String productCode) throws KittingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT kitting_no, so_number, product_id, product_code, product_name, unit_code, qty_kitting, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_code = ? ORDER BY product_code", this,productCode);
		}
		catch (Exception e) {
			throw new KittingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'product_name = :productName'.
	 */
	@Transactional
	public List<KittingDetail> findWhereProductNameEquals(String productName) throws KittingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT kitting_no, so_number, product_id, product_code, product_name, unit_code, qty_kitting, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_name = ? ORDER BY product_name", this,productName);
		}
		catch (Exception e) {
			throw new KittingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'unit_code = :unitCode'.
	 */
	@Transactional
	public List<KittingDetail> findWhereUnitCodeEquals(String unitCode) throws KittingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT kitting_no, so_number, product_id, product_code, product_name, unit_code, qty_kitting, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE unit_code = ? ORDER BY unit_code", this,unitCode);
		}
		catch (Exception e) {
			throw new KittingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'qty_kitting = :qtyKitting'.
	 */
	@Transactional
	public List<KittingDetail> findWhereQtyKittingEquals(int qtyKitting) throws KittingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT kitting_no, so_number, product_id, product_code, product_name, unit_code, qty_kitting, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE qty_kitting = ? ORDER BY qty_kitting", this,qtyKitting);
		}
		catch (Exception e) {
			throw new KittingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'user_id = :userId'.
	 */
	@Transactional
	public List<KittingDetail> findWhereUserIdEquals(String userId) throws KittingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT kitting_no, so_number, product_id, product_code, product_name, unit_code, qty_kitting, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE user_id = ? ORDER BY user_id", this,userId);
		}
		catch (Exception e) {
			throw new KittingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'corp_id = :corpId'.
	 */
	@Transactional
	public List<KittingDetail> findWhereCorpIdEquals(String corpId) throws KittingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT kitting_no, so_number, product_id, product_code, product_name, unit_code, qty_kitting, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE corp_id = ? ORDER BY corp_id", this,corpId);
		}
		catch (Exception e) {
			throw new KittingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<KittingDetail> findWhereWhCodeEquals(String whCode) throws KittingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT kitting_no, so_number, product_id, product_code, product_name, unit_code, qty_kitting, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new KittingDetailDaoException("Query failed", e);
		}
		
	}

	/**
     * Updates a single row in the KittingDetail table.
     */
    @Transactional
	public void update(KittingDetailPk pk, KittingDetail dto) throws KittingDetailDaoException {
    	jdbcTemplate.update("update " + getTableName() + " set kitting_no = ?, so_number = ?,product_id = ?, product_code = ?, product_name = ?, unit_code = ?, qty_kitting = ?, user_id = ?, corp_id = ?, wh_code = ? where kitting_no = ?", dto.getKittingNo(), dto.getSo_number(), dto.getProductId(), dto.getProductCode(), dto.getProductName(), dto.getUnitCode(), dto.getQtyKitting(), dto.getUserId(), dto.getCorpId(), dto.getWhCode(), pk.getKittingNo());
	}

    /**
     * Deletes a single row in the KittingDetail table.
     */
    @Transactional
	public void delete(KittingDetailPk pk) throws KittingDetailDaoException {
    	jdbcTemplate.update("delete from " + getTableName() + " where kitting_no = ?", pk.getKittingNo());
	}

    /**
     * Returns all rows from the KittingDetail table that match the criteria 'kittingNo = :kittingNo'.
     */
    @Transactional
	public KittingDetail findByPrimaryKey(String KittingNo) throws KittingDetailDaoException {
    	try {
            List<KittingDetail> list = jdbcTemplate.query("select kitting_no,so_number,product_id,product_code,product_name,unit_code,qty_kitting,user_id,corp_id,wh_code from " + getTableName() + " where kitting_no = ?", this, KittingNo);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new KittingDetailDaoException("Query failed", e);
        }
	}

    /**
     * Returns the rows from the KittingDetail table that matches the specified primary-key value.
     */
    @Transactional
	public KittingDetail findByPrimaryKey(KittingDetailPk pk) throws KittingDetailDaoException {
		return findByPrimaryKey(pk.getKittingNo());
	}

    @Transactional
	public List<KittingDetail> findDetail(KittingDetail kd)	throws KittingDetailDaoException {
		try {
       	 String kittingNo = kd.getKittingNo();
       	 StringBuffer sb = new StringBuffer();
       	 
	    	 sb.append("select " 
		 		  +"kitting_no,"
		 		  +"so_number,"
		 		  +"product_id,"
		 		  +"product_code,"
		 		  +"product_name,"
		 		  +"unit_code,"
		 		  +"qty_kitting,"
		 		  +"user_id,"
		 		  +"corp_id,"
		 		  +"wh_code from "
		 		  +getTableName() 
		 		  +" where kitting_no like '"+kittingNo+"' ");

       	 return jdbcTemplate.query(sb.toString(), this);
       } catch (Exception e) {
           throw new KittingDetailDaoException("Query failed", e);
       }
	}

    @Transactional
	public List<KittingDetail> findKittingDetailList(String salesOrderNo)	throws KittingDetailDaoException {
		try {
	       	 String so = salesOrderNo;
	       	 StringBuffer sb = new StringBuffer();
	       	 
		     sb.append("select " 
			 		  +"kitting_no,"
			 		  +"so_number,"
			 		  +"product_id,"
			 		  +"product_code,"
			 		  +"product_name,"
			 		  +"unit_code,"
			 		  +"qty_kitting,"
			 		  +"user_id,"
			 		  +"corp_id,"
			 		  +"wh_code from "
			 		  +getTableName() 
			 		  +" where so_number like '%"+so+"%' ");

	       	 return jdbcTemplate.query(sb.toString(), this);
	       } catch (Exception e) {
	           throw new KittingDetailDaoException("Query failed", e);
	       }
	}

}

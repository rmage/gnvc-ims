package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dto.map.VgrdetailproductcategoryListMap;
import com.app.wms.engine.db.dao.VgrdetailproductcategoryDao;
import com.app.wms.engine.db.dto.Vgrdetailproductcategory;
import com.app.wms.engine.db.exceptions.VgrdetailproductcategoryDaoException;
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

public class VgrdetailproductcategoryDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Vgrdetailproductcategory>, VgrdetailproductcategoryDao
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
	public void insert(Vgrdetailproductcategory dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, product_name, category_name, qtygood, qtydmg, producttype, wh_code, corp_id ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getId(),dto.getGrnumber(),dto.getProductcode(),dto.getQtyreal(),dto.getStatus(),dto.getExpirydate(),dto.getRemark(),dto.getLotid(),dto.getProductName(),dto.getCategoryName(),dto.getQtygood(),dto.getQtydmg(),dto.getProducttype(),dto.getWhCode(),dto.getCorpId()} );
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Vgrdetailproductcategory
	 */
	public Vgrdetailproductcategory mapRow(ResultSet rs, int row) throws SQLException
	{
		Vgrdetailproductcategory dto = new Vgrdetailproductcategory();
		dto.setId( rs.getInt( 1 ) );
		dto.setGrnumber( rs.getString( 2 ) );
		dto.setProductcode( rs.getString( 3 ) );
		dto.setQtyreal( rs.getInt( 4 ) );
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
		
		dto.setProductName( rs.getString( 9 ) );
		dto.setCategoryName( rs.getString( 10 ) );
		dto.setQtygood( rs.getInt( 11 ) );
		if (rs.wasNull()) {
			dto.setQtygoodNull( true );
		}
		
		dto.setQtydmg( rs.getInt( 12 ) );
		if (rs.wasNull()) {
			dto.setQtydmgNull( true );
		}
		
		dto.setProducttype( rs.getString( 13 ) );
		dto.setWhCode( rs.getString( 14 ) );
		dto.setCorpId( rs.getString( 15 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "vgrdetailproductcategory";
	}

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria ''.
	 */
	@Transactional
	public List<Vgrdetailproductcategory> findAll() throws VgrdetailproductcategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, product_name, category_name, qtygood, qtydmg, producttype, wh_code, corp_id FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new VgrdetailproductcategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Vgrdetailproductcategory> findWhereIdEquals(int id) throws VgrdetailproductcategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, product_name, category_name, qtygood, qtydmg, producttype, wh_code, corp_id FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new VgrdetailproductcategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'grnumber = :grnumber'.
	 */
	@Transactional
	public List<Vgrdetailproductcategory> findWhereGrnumberEquals(String grnumber) throws VgrdetailproductcategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, product_name, category_name, qtygood, qtydmg, producttype, wh_code, corp_id FROM " + getTableName() + " WHERE grnumber = ? ORDER BY grnumber", this,grnumber);
		}
		catch (Exception e) {
			throw new VgrdetailproductcategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'productcode = :productcode'.
	 */
	@Transactional
	public List<Vgrdetailproductcategory> findWhereProductcodeEquals(String productcode) throws VgrdetailproductcategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, product_name, category_name, qtygood, qtydmg, producttype, wh_code, corp_id FROM " + getTableName() + " WHERE productcode = ? ORDER BY productcode", this,productcode);
		}
		catch (Exception e) {
			throw new VgrdetailproductcategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'qtyreal = :qtyreal'.
	 */
	@Transactional
	public List<Vgrdetailproductcategory> findWhereQtyrealEquals(int qtyreal) throws VgrdetailproductcategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, product_name, category_name, qtygood, qtydmg, producttype, wh_code, corp_id FROM " + getTableName() + " WHERE qtyreal = ? ORDER BY qtyreal", this,qtyreal);
		}
		catch (Exception e) {
			throw new VgrdetailproductcategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'status = :status'.
	 */
	@Transactional
	public List<Vgrdetailproductcategory> findWhereStatusEquals(String status) throws VgrdetailproductcategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, product_name, category_name, qtygood, qtydmg, producttype, wh_code, corp_id FROM " + getTableName() + " WHERE status = ? ORDER BY status", this,status);
		}
		catch (Exception e) {
			throw new VgrdetailproductcategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'expirydate = :expirydate'.
	 */
	@Transactional
	public List<Vgrdetailproductcategory> findWhereExpirydateEquals(Date expirydate) throws VgrdetailproductcategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, product_name, category_name, qtygood, qtydmg, producttype, wh_code, corp_id FROM " + getTableName() + " WHERE expirydate = ? ORDER BY expirydate", this,expirydate);
		}
		catch (Exception e) {
			throw new VgrdetailproductcategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'remark = :remark'.
	 */
	@Transactional
	public List<Vgrdetailproductcategory> findWhereRemarkEquals(String remark) throws VgrdetailproductcategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, product_name, category_name, qtygood, qtydmg, producttype, wh_code, corp_id FROM " + getTableName() + " WHERE remark = ? ORDER BY remark", this,remark);
		}
		catch (Exception e) {
			throw new VgrdetailproductcategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'lotid = :lotid'.
	 */
	@Transactional
	public List<Vgrdetailproductcategory> findWhereLotidEquals(int lotid) throws VgrdetailproductcategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, product_name, category_name, qtygood, qtydmg, producttype, wh_code, corp_id FROM " + getTableName() + " WHERE lotid = ? ORDER BY lotid", this,lotid);
		}
		catch (Exception e) {
			throw new VgrdetailproductcategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'product_name = :productName'.
	 */
	@Transactional
	public List<Vgrdetailproductcategory> findWhereProductNameEquals(String productName) throws VgrdetailproductcategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, product_name, category_name, qtygood, qtydmg, producttype, wh_code, corp_id FROM " + getTableName() + " WHERE product_name = ? ORDER BY product_name", this,productName);
		}
		catch (Exception e) {
			throw new VgrdetailproductcategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'category_name = :categoryName'.
	 */
	@Transactional
	public List<Vgrdetailproductcategory> findWhereCategoryNameEquals(String categoryName) throws VgrdetailproductcategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, product_name, category_name, qtygood, qtydmg, producttype, wh_code, corp_id FROM " + getTableName() + " WHERE category_name = ? ORDER BY category_name", this,categoryName);
		}
		catch (Exception e) {
			throw new VgrdetailproductcategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'qtygood = :qtygood'.
	 */
	@Transactional
	public List<Vgrdetailproductcategory> findWhereQtygoodEquals(int qtygood) throws VgrdetailproductcategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, product_name, category_name, qtygood, qtydmg, producttype, wh_code, corp_id FROM " + getTableName() + " WHERE qtygood = ? ORDER BY qtygood", this,qtygood);
		}
		catch (Exception e) {
			throw new VgrdetailproductcategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'qtydmg = :qtydmg'.
	 */
	@Transactional
	public List<Vgrdetailproductcategory> findWhereQtydmgEquals(int qtydmg) throws VgrdetailproductcategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, product_name, category_name, qtygood, qtydmg, producttype, wh_code, corp_id FROM " + getTableName() + " WHERE qtydmg = ? ORDER BY qtydmg", this,qtydmg);
		}
		catch (Exception e) {
			throw new VgrdetailproductcategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'producttype = :producttype'.
	 */
	@Transactional
	public List<Vgrdetailproductcategory> findWhereProducttypeEquals(String producttype) throws VgrdetailproductcategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, product_name, category_name, qtygood, qtydmg, producttype, wh_code, corp_id FROM " + getTableName() + " WHERE producttype = ? ORDER BY producttype", this,producttype);
		}
		catch (Exception e) {
			throw new VgrdetailproductcategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<Vgrdetailproductcategory> findWhereWhCodeEquals(String whCode) throws VgrdetailproductcategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, product_name, category_name, qtygood, qtydmg, producttype, wh_code, corp_id FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new VgrdetailproductcategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'corp_id = :corpId'.
	 */
	@Transactional
	public List<Vgrdetailproductcategory> findWhereCorpIdEquals(String corpId) throws VgrdetailproductcategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, grnumber, productcode, qtyreal, status, expirydate, remark, lotid, product_name, category_name, qtygood, qtydmg, producttype, wh_code, corp_id FROM " + getTableName() + " WHERE corp_id = ? ORDER BY corp_id", this,corpId);
		}
		catch (Exception e) {
			throw new VgrdetailproductcategoryDaoException("Query failed", e);
		}
		
	}
	@Transactional
	public List<Vgrdetailproductcategory> getGRDetail(String grnumber) throws VgrdetailproductcategoryDaoException
	{
		
		Map  map = new HashMap();
		map.put("grnumber", grnumber);
		
		try {
			return jdbcTemplate.query("" +
					"select distinct( vgrdetailproductcategory.id ), "
					+ "vgrdetailproductcategory.grnumber, vgrdetailproductcategory.productcode, po_detail.qty AS qtyreal, "
	                + "vgrdetailproductcategory.status, vgrdetailproductcategory.expirydate, vgrdetailproductcategory.remark, "
	                + "vgrdetailproductcategory.lotid, vgrdetailproductcategory.product_name, vgrdetailproductcategory.category_name, "
	                + "vgrdetailproductcategory.qtygood, vgrdetailproductcategory.qtydmg, dbo.po_detail.producttype "
	                + "FROM dbo.vgrdetailproductcategory "
	                + "INNER JOIN dbo.goodreceive ON ( dbo.vgrdetailproductcategory.grnumber = dbo.goodreceive.grnumber ) "
	                + "INNER JOIN dbo.po ON ( dbo.goodreceive.ponumber = dbo.po.ponumber ) "
	                + "INNER JOIN dbo.po_detail ON ( dbo.po.ponumber = dbo.po_detail.ponumber ) "
	                + "INNER JOIN dbo.goodreceive_detail ON ( dbo.goodreceive_detail.grnumber = dbo.goodreceive.grnumber ) "
	                + "WHERE po_detail.productcode = vgrdetailproductcategory.productcode "
	                + "AND dbo.goodreceive_detail.producttype = dbo.vgrdetailproductcategory.producttype "
	                + "AND dbo.goodreceive_detail.producttype = dbo.po_detail.producttype "
	                + "AND dbo.goodreceive_detail.grnumber = dbo.goodreceive.grnumber "
	                + "AND (vgrdetailproductcategory.qtygood is not null or vgrdetailproductcategory.qtydmg is not null) "
	                + "and  vgrdetailproductcategory.grnumber = :grnumber ", new VgrdetailproductcategoryListMap() , map);
		
		}
		catch (Exception e) {
			throw new VgrdetailproductcategoryDaoException("Query failed", e);
		}
	}

}

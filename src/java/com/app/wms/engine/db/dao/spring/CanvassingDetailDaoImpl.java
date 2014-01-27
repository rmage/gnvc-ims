package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.CanvassingDetailDao;
import com.app.wms.engine.db.dto.CanvassingDetail;
import com.app.wms.engine.db.dto.CanvassingDetailPk;
import com.app.wms.engine.db.dto.map.CanvassingAllMap;
import com.app.wms.engine.db.dto.map.CanvassingListMap;
import com.app.wms.engine.db.exceptions.CanvassingDetailDaoException;
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

public class CanvassingDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<CanvassingDetail>, CanvassingDetailDao
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
	 * @return CanvassingDetailPk
	 */
	public CanvassingDetailPk insert(CanvassingDetail dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( prsnumber, supplier_code, productcode, productname, priceunit, warranty, termpayment, termdelivery, discount, pph, ppn, is_selected ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.compile();
		su.update( new Object[] { dto.getPrsnumber(),dto.getSupplierCode(),dto.getProductcode(),dto.getProductname(),dto.getPriceunit(),dto.getWarranty(),dto.getTermpayment(),dto.getTermdelivery(),dto.getDiscount(),dto.getPph(),dto.getPpn(),dto.getIsSelected()} );
		CanvassingDetailPk pk = new CanvassingDetailPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the canvassing_detail table.
	 */
	public void update(CanvassingDetailPk pk, CanvassingDetail dto) throws CanvassingDetailDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET prsnumber = ?, supplier_code = ?, productcode = ?, productname = ?, priceunit = ?, warranty = ?, termpayment = ?, termdelivery = ?, discount = ?, pph = ?, ppn = ?, is_selected = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getPrsnumber(),dto.getSupplierCode(),dto.getProductcode(),dto.getProductname(),dto.getPriceunit(),dto.getWarranty(),dto.getTermpayment(),dto.getTermdelivery(),dto.getDiscount(),dto.getPph(),dto.getPpn(),dto.getIsSelected(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the canvassing_detail table.
	 */
	@Transactional
	public void delete(CanvassingDetailPk pk) throws CanvassingDetailDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return CanvassingDetail
	 */
	public CanvassingDetail mapRow(ResultSet rs, int row) throws SQLException
	{
		CanvassingDetail dto = new CanvassingDetail();
		dto.setId( rs.getInt( 1 ) );
		dto.setPrsnumber( rs.getString( 2 ) );
		dto.setSupplierCode( rs.getString( 3 ) );
		dto.setProductcode( rs.getString( 4 ) );
		dto.setProductname( rs.getString( 5 ) );
		dto.setPriceunit( rs.getBigDecimal( 6 ) );
		if (rs.wasNull()) {
			dto.setPriceunitNull( true );
		}
		
		dto.setWarranty( rs.getTimestamp(7 ) );
		dto.setTermpayment( rs.getString( 8 ) );
		dto.setTermdelivery( rs.getString( 9 ) );
		dto.setDiscount( rs.getBigDecimal( 10 ) );
		if (rs.wasNull()) {
			dto.setDiscountNull( true );
		}
		
		dto.setPph( rs.getBigDecimal( 11 ) );
		if (rs.wasNull()) {
			dto.setPphNull( true );
		}
		
		dto.setPpn( rs.getBigDecimal( 12 ) );
		if (rs.wasNull()) {
			dto.setPpnNull( true );
		}
		
		dto.setIsSelected( rs.getString( 13 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..canvassing_detail";
	}

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public CanvassingDetail findByPrimaryKey(int id) throws CanvassingDetailDaoException
	{
		try {
			List<CanvassingDetail> list = jdbcTemplate.query("SELECT id, prsnumber, supplier_code, productcode, productname, priceunit, warranty, termpayment, termdelivery, discount, pph, ppn, is_selected FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new CanvassingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria ''.
	 */
	@Transactional
	public List<CanvassingDetail> findAll() throws CanvassingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, supplier_code, productcode, productname, priceunit, warranty, termpayment, termdelivery, discount, pph, ppn, is_selected FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new CanvassingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<CanvassingDetail> findWhereIdEquals(int id) throws CanvassingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, supplier_code, productcode, productname, priceunit, warranty, termpayment, termdelivery, discount, pph, ppn, is_selected FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new CanvassingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'prsnumber = :prsnumber'.
	 */
	@Transactional
	public List<CanvassingDetail> findWherePrsnumberEquals(String prsnumber) throws CanvassingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, supplier_code, productcode, productname, priceunit, warranty, termpayment, termdelivery, discount, pph, ppn, is_selected FROM " + getTableName() + " WHERE prsnumber = ? ORDER BY prsnumber", this,prsnumber);
		}
		catch (Exception e) {
			throw new CanvassingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'supplier_code = :supplierCode'.
	 */
	@Transactional
	public List<CanvassingDetail> findWhereSupplierCodeEquals(String supplierCode) throws CanvassingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, supplier_code, productcode, productname, priceunit, warranty, termpayment, termdelivery, discount, pph, ppn, is_selected FROM " + getTableName() + " WHERE supplier_code = ? ORDER BY supplier_code", this,supplierCode);
		}
		catch (Exception e) {
			throw new CanvassingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'productcode = :productcode'.
	 */
	@Transactional
	public List<CanvassingDetail> findWhereProductcodeEquals(String productcode) throws CanvassingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, supplier_code, productcode, productname, priceunit, warranty, termpayment, termdelivery, discount, pph, ppn, is_selected FROM " + getTableName() + " WHERE productcode = ? ORDER BY productcode", this,productcode);
		}
		catch (Exception e) {
			throw new CanvassingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'productname = :productname'.
	 */
	@Transactional
	public List<CanvassingDetail> findWhereProductnameEquals(String productname) throws CanvassingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, supplier_code, productcode, productname, priceunit, warranty, termpayment, termdelivery, discount, pph, ppn, is_selected FROM " + getTableName() + " WHERE productname = ? ORDER BY productname", this,productname);
		}
		catch (Exception e) {
			throw new CanvassingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'priceunit = :priceunit'.
	 */
	@Transactional
	public List<CanvassingDetail> findWherePriceunitEquals(float priceunit) throws CanvassingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, supplier_code, productcode, productname, priceunit, warranty, termpayment, termdelivery, discount, pph, ppn, is_selected FROM " + getTableName() + " WHERE priceunit = ? ORDER BY priceunit", this,priceunit);
		}
		catch (Exception e) {
			throw new CanvassingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'warranty = :warranty'.
	 */
	@Transactional
	public List<CanvassingDetail> findWhereWarrantyEquals(Date warranty) throws CanvassingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, supplier_code, productcode, productname, priceunit, warranty, termpayment, termdelivery, discount, pph, ppn, is_selected FROM " + getTableName() + " WHERE warranty = ? ORDER BY warranty", this,warranty);
		}
		catch (Exception e) {
			throw new CanvassingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'termpayment = :termpayment'.
	 */
	@Transactional
	public List<CanvassingDetail> findWhereTermpaymentEquals(String termpayment) throws CanvassingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, supplier_code, productcode, productname, priceunit, warranty, termpayment, termdelivery, discount, pph, ppn, is_selected FROM " + getTableName() + " WHERE termpayment = ? ORDER BY termpayment", this,termpayment);
		}
		catch (Exception e) {
			throw new CanvassingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'termdelivery = :termdelivery'.
	 */
	@Transactional
	public List<CanvassingDetail> findWhereTermdeliveryEquals(String termdelivery) throws CanvassingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, supplier_code, productcode, productname, priceunit, warranty, termpayment, termdelivery, discount, pph, ppn, is_selected FROM " + getTableName() + " WHERE termdelivery = ? ORDER BY termdelivery", this,termdelivery);
		}
		catch (Exception e) {
			throw new CanvassingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'discount = :discount'.
	 */
	@Transactional
	public List<CanvassingDetail> findWhereDiscountEquals(float discount) throws CanvassingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, supplier_code, productcode, productname, priceunit, warranty, termpayment, termdelivery, discount, pph, ppn, is_selected FROM " + getTableName() + " WHERE discount = ? ORDER BY discount", this,discount);
		}
		catch (Exception e) {
			throw new CanvassingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'pph = :pph'.
	 */
	@Transactional
	public List<CanvassingDetail> findWherePphEquals(float pph) throws CanvassingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, supplier_code, productcode, productname, priceunit, warranty, termpayment, termdelivery, discount, pph, ppn, is_selected FROM " + getTableName() + " WHERE pph = ? ORDER BY pph", this,pph);
		}
		catch (Exception e) {
			throw new CanvassingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'ppn = :ppn'.
	 */
	@Transactional
	public List<CanvassingDetail> findWherePpnEquals(float ppn) throws CanvassingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, supplier_code, productcode, productname, priceunit, warranty, termpayment, termdelivery, discount, pph, ppn, is_selected FROM " + getTableName() + " WHERE ppn = ? ORDER BY ppn", this,ppn);
		}
		catch (Exception e) {
			throw new CanvassingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'is_selected = :isSelected'.
	 */
	@Transactional
	public List<CanvassingDetail> findWhereIsSelectedEquals(String isSelected) throws CanvassingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, prsnumber, supplier_code, productcode, productname, priceunit, warranty, termpayment, termdelivery, discount, pph, ppn, is_selected FROM " + getTableName() + " WHERE is_selected = ? ORDER BY is_selected", this,isSelected);
		}
		catch (Exception e) {
			throw new CanvassingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the canvassing_detail table that matches the specified primary-key value.
	 */
	public CanvassingDetail findByPrimaryKey(CanvassingDetailPk pk) throws CanvassingDetailDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}
	
	public List<CanvassingDetail> findAllList() throws CanvassingDetailDaoException
	{
		try {
			Map map = new HashMap();
			StringBuffer sb = new StringBuffer();
			sb.append(" select distinct cd.id, cd.supplier_code, cd.prsnumber, cd.productcode, cd.productname, " +
					  " cd.priceunit, cd.is_selected, c.canvassingdate " +
					  " from canvassing_detail cd " +
					  " inner join canvassing c " +
					  " on cd.prsnumber = c.prsnumber ");
			
			return jdbcTemplate.query(sb.toString(), new CanvassingAllMap(), map);
		}
		catch (Exception e) {
			throw new CanvassingDetailDaoException("Query failed", e);
		}
		
	}

    public List<CanvassingDetail> findWhereCanvassingDetail(int id, String prsnumber, String productcode) throws CanvassingDetailDaoException {
        try {
            Map map = new HashMap();
            map.put("id", id);
            map.put("prsnumber", prsnumber);
            map.put("productcode", productcode);
        	
            StringBuffer sb = new StringBuffer();

            sb.append(" select cd.id, cd.supplier_code, cd.prsnumber, cd.productcode, cd.productname, pd.uom_name, pd.qty," +
                " cd.priceunit, cd.warranty, cd.termpayment, cd.termdelivery, cd.discount, cd.pph, cd.ppn, " +
                " cd.is_selected FROM canvassing_detail cd " +
                " inner join prs_detail pd on cd.prsnumber = pd.prsnumber " +
                " where " +
                " cd.id like '%"+id+"%' and cd.prsnumber like '%"+prsnumber+"%' and pd.prsnumber like '%"+prsnumber+"%' " +
                " and cd.productcode like '%"+productcode+"%' and pd.productcode like '%"+productcode+"%' ");
        	
            return jdbcTemplate.query(sb.toString(), new CanvassingListMap(), map);
        } catch (Exception e) {
            throw new CanvassingDetailDaoException ("Query failed", e);
        }
    }
	
	
	public CanvassingDetail findWherePrsnumber(String prsnumber) throws CanvassingDetailDaoException
	{
		CanvassingDetail c = null;
		try {
			List <CanvassingDetail> canvassingDetailList = jdbcTemplate.query("SELECT id, prsnumber, supplier_code, productcode, productname, priceunit, warranty, termpayment, termdelivery, discount, pph, ppn, is_selected FROM " + getTableName() + " WHERE prsnumber = ? ORDER BY prsnumber", this,prsnumber);
		    c = canvassingDetailList.get(0);
		}
		catch (Exception e) {
			throw new CanvassingDetailDaoException("Query failed", e);
		}
		return c;
	}
	
	public void updateDto(CanvassingDetail dto) throws CanvassingDetailDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + "  SET prsnumber = ?, supplier_code = ?, productcode = ?, productname = ?, priceunit = ?, warranty = ?, termpayment = ?, termdelivery = ?, discount = ?, pph = ?, ppn = ?, is_selected = ? WHERE supplier_code = ? AND prsnumber = ?  AND productcode = ? ");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getPrsnumber(),dto.getSupplierCode(),dto.getProductcode(),dto.getProductname(),dto.getPriceunit(),dto.getWarranty(),dto.getTermpayment(),dto.getTermdelivery(),dto.getDiscount(),dto.getPph(),dto.getPpn(),dto.getIsSelected(),dto.getSupplierCode(),dto.getPrsnumber(),dto.getProductcode()} );
	}

}

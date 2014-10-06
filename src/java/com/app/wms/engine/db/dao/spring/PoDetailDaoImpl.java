package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.PoDetailDao;
import com.app.wms.engine.db.dto.PoDetail;
import com.app.wms.engine.db.dto.PoDetailPk;
import com.app.wms.engine.db.dto.map.PoDetailListMap;
import com.app.wms.engine.db.exceptions.PoDetailDaoException;

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

public class PoDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<PoDetail>, PoDetailDao
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
	 * @return PoDetailPk
	 */
	public PoDetailPk insert(PoDetail dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.compile();
		su.update( new Object[] { dto.getPonumber(),dto.getProductcode(),dto.getQty(),dto.getProducttype(),dto.getUnitprice(),dto.getAmount(),dto.getPpn(),dto.getPoremarks(),dto.getCurrencyCode(),dto.getWarranty(),dto.getTermpayment(),dto.getTermdelivery(),dto.getDiscount(),dto.getPph(),dto.getTotal()} );
		PoDetailPk pk = new PoDetailPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the po_detail table.
	 */
	public void update(PoDetailPk pk, PoDetail dto) throws PoDetailDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET ponumber = ?, productcode = ?, qty = ?, producttype = ?, unitprice = ?, amount = ?, ppn = ?, poremarks = ?, currencyCode = ?, warranty = ?, termpayment = ?, termdelivery = ?, discount = ?, pph = ?, total = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.DECIMAL) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getPonumber(),dto.getProductcode(),dto.getQty(),dto.getProducttype(),dto.getUnitprice(),dto.getAmount(),dto.getPpn(),dto.getPoremarks(),dto.getCurrencyCode(),dto.getWarranty(),dto.getTermpayment(),dto.getTermdelivery(),dto.getDiscount(),dto.getPph(),dto.getTotal(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the po_detail table.
	 */
	@Transactional
	public void delete(PoDetailPk pk) throws PoDetailDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return PoDetail
	 */
	public PoDetail mapRow(ResultSet rs, int row) throws SQLException
	{
		PoDetail dto = new PoDetail();
		dto.setId( rs.getInt( 1 ) );
		dto.setPonumber( rs.getString( 2 ) );
		dto.setProductcode( rs.getString( 3 ) );
		dto.setQty( rs.getBigDecimal( 4 ) );
		if (rs.wasNull()) {
			dto.setQtyNull( true );
		}
		
		dto.setProducttype( rs.getString( 5 ) );
		dto.setUnitprice( rs.getBigDecimal( 6 ) );
		if (rs.wasNull()) {
			dto.setUnitpriceNull( true );
		}
		
		dto.setAmount( rs.getBigDecimal( 7 ) );
		if (rs.wasNull()) {
			dto.setAmountNull( true );
		}
		
		dto.setPpn( rs.getBigDecimal( 8 ) );
		if (rs.wasNull()) {
			dto.setPpnNull( true );
		}
		
		dto.setPoremarks( rs.getString( 9 ) );
		dto.setCurrencyCode( rs.getString( 10 ) );
		dto.setWarranty( rs.getTimestamp(11 ) );
		dto.setTermpayment( rs.getString( 12 ) );
		dto.setTermdelivery( rs.getString( 13 ) );
		dto.setDiscount( rs.getBigDecimal( 14 ) );
		if (rs.wasNull()) {
			dto.setDiscountNull( true );
		}
		
		dto.setPph( rs.getBigDecimal( 15 ) );
		if (rs.wasNull()) {
			dto.setPphNull( true );
		}
		
		dto.setTotal( rs.getBigDecimal( 16 ) );
		if (rs.wasNull()) {
			dto.setTotalNull( true );
		}
		
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "po_detail";
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public PoDetail findByPrimaryKey(int id) throws PoDetailDaoException
	{
		try {
			List<PoDetail> list = jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria ''.
	 */
	@Transactional
	public List<PoDetail> findAll() throws PoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<PoDetail> findWhereIdEquals(int id) throws PoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'ponumber = :ponumber'.
	 */
	@Transactional
	public List<PoDetail> findWherePonumberEquals(String ponumber) throws PoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " WHERE ponumber = ? ORDER BY ponumber", this,ponumber);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'productcode = :productcode'.
	 */
	@Transactional
	public List<PoDetail> findWhereProductcodeEquals(String productcode) throws PoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " WHERE productcode = ? ORDER BY productcode", this,productcode);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'qty = :qty'.
	 */
	@Transactional
	public List<PoDetail> findWhereQtyEquals(float qty) throws PoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " WHERE qty = ? ORDER BY qty", this,qty);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'producttype = :producttype'.
	 */
	@Transactional
	public List<PoDetail> findWhereProducttypeEquals(String producttype) throws PoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " WHERE producttype = ? ORDER BY producttype", this,producttype);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'unitprice = :unitprice'.
	 */
	@Transactional
	public List<PoDetail> findWhereUnitpriceEquals(float unitprice) throws PoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " WHERE unitprice = ? ORDER BY unitprice", this,unitprice);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'amount = :amount'.
	 */
	@Transactional
	public List<PoDetail> findWhereAmountEquals(float amount) throws PoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " WHERE amount = ? ORDER BY amount", this,amount);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'ppn = :ppn'.
	 */
	@Transactional
	public List<PoDetail> findWherePpnEquals(float ppn) throws PoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " WHERE ppn = ? ORDER BY ppn", this,ppn);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'poremarks = :poremarks'.
	 */
	@Transactional
	public List<PoDetail> findWherePoremarksEquals(String poremarks) throws PoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " WHERE poremarks = ? ORDER BY poremarks", this,poremarks);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'currencyCode = :currencyCode'.
	 */
	@Transactional
	public List<PoDetail> findWhereCurrencyCodeEquals(String currencyCode) throws PoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " WHERE currencyCode = ? ORDER BY currencyCode", this,currencyCode);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'warranty = :warranty'.
	 */
	@Transactional
	public List<PoDetail> findWhereWarrantyEquals(Date warranty) throws PoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " WHERE warranty = ? ORDER BY warranty", this,warranty);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'termpayment = :termpayment'.
	 */
	@Transactional
	public List<PoDetail> findWhereTermpaymentEquals(String termpayment) throws PoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " WHERE termpayment = ? ORDER BY termpayment", this,termpayment);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'termdelivery = :termdelivery'.
	 */
	@Transactional
	public List<PoDetail> findWhereTermdeliveryEquals(String termdelivery) throws PoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " WHERE termdelivery = ? ORDER BY termdelivery", this,termdelivery);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'discount = :discount'.
	 */
	@Transactional
	public List<PoDetail> findWhereDiscountEquals(float discount) throws PoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " WHERE discount = ? ORDER BY discount", this,discount);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'pph = :pph'.
	 */
	@Transactional
	public List<PoDetail> findWherePphEquals(float pph) throws PoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " WHERE pph = ? ORDER BY pph", this,pph);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'total = :total'.
	 */
	@Transactional
	public List<PoDetail> findWhereTotalEquals(float total) throws PoDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, productcode, qty, producttype, unitprice, amount, ppn, poremarks, currencyCode, warranty, termpayment, termdelivery, discount, pph, total FROM " + getTableName() + " WHERE total = ? ORDER BY total", this,total);
		}
		catch (Exception e) {
			throw new PoDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the po_detail table that matches the specified primary-key value.
	 */
	public PoDetail findByPrimaryKey(PoDetailPk pk) throws PoDetailDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

	@Override
	public List<PoDetail> findPoDetailPaging(PoDetail pd, Integer page) throws PoDetailDaoException {
		try {
        	String poNumber = pd.getPonumber();
        	
        	int i = page;
        	Map map = new HashMap();
        	map.put("i", i);
        
        	StringBuffer sb = new StringBuffer();
        	
        	if(pd == null){
        		pd = new PoDetail();
        	}
        	
        	if(poNumber == null){
        		poNumber = "%";
        		
        		sb.append("declare @Page int, @PageSize int "
        				+"set @Page = '"+i+"'; "
        				+"set @PageSize = 10; "
        				+"with PagedResult "
        				+"as (select ROW_NUMBER() over (order by p.id asc) as id, pd.ponumber, p.podate, p.supplier_name, " +
        						" sum(pd.total)as total, p.role_code, p.status " +
        						" from po_detail pd inner join po p on pd.ponumber = p.ponumber " +
        						" where pd.ponumber like '%"+poNumber+"%' group by p.id, pd.ponumber, p.podate, p.supplier_name, p.role_code, p.status ) "
        				    +"select * from PagedResult where id between "
        				+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
        				     +"else @Page end and @PageSize * @Page ");
        		
        	}else{
        		
        		sb.append("declare @Page int, @PageSize int "
        				+"set @Page = '"+i+"'; "
        				+"set @PageSize = 10; "
        				+"with PagedResult "
        				+"as (select ROW_NUMBER() over (order by p.id asc) as id, pd.ponumber, p.podate, p.supplier_name, " +
        						" sum(pd.total)as total, p.role_code, p.status " +
        						" from po_detail pd inner join po p on pd.ponumber = p.ponumber " +
        						" where pd.ponumber like '%"+poNumber+"%' group by p.id, pd.ponumber, p.podate, p.supplier_name, p.role_code, p.status ) "
        				    +"select * from PagedResult where id between "
        				+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
        				     +"else @Page end and @PageSize * @Page ");
        		
        	}
        	
        	return jdbcTemplate.query(sb.toString(),new PoDetailListMap(),map);	
        
        } catch (Exception e) {
            throw new PoDetailDaoException("Query failed", e);
        }
	}

    public void update(PoDetail dto) throws PoDetailDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

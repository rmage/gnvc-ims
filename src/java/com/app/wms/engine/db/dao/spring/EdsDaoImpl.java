package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.EdsDao;
import com.app.wms.engine.db.dto.Eds;
import com.app.wms.engine.db.dto.EdsPk;
import com.app.wms.engine.db.dto.map.EdsListMap;
//import com.app.wms.engine.db.dto.map.TsListMap;
import com.app.wms.engine.db.exceptions.EdsDaoException;
//import com.app.wms.engine.db.exceptions.TsDaoException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

public class EdsDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Eds>, EdsDao
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
	 * @return EdsPk
	 */
	public EdsPk insert(Eds dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getEdsnumber(),dto.getEdsdate(),dto.getCinumber(),dto.getBuyerName(),dto.getBrand(),dto.getReferensi(),dto.getDestination(),dto.getVan(),dto.getSealShip(),dto.getVessel(),dto.getPlatNo(),dto.getTimeIn(),dto.getTimeOut(),dto.getDriverName(),dto.getIssuedBy(),dto.getCheckedBy(),dto.getApprovedBy(),dto.getRemarks()} );
		EdsPk pk = new EdsPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the eds table.
	 */
	public void update(EdsPk pk, Eds dto) throws EdsDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET edsnumber = ?, edsdate = ?, cinumber = ?, buyerName = ?, brand = ?, referensi = ?, destination = ?, van = ?, sealShip = ?, vessel = ?, platNo = ?, time_in = ?, time_out = ?, driverName = ?, issuedBy = ?, checkedBy = ?, approvedBy = ?, remarks = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getEdsnumber(),dto.getEdsdate(),dto.getCinumber(),dto.getBuyerName(),dto.getBrand(),dto.getReferensi(),dto.getDestination(),dto.getVan(),dto.getSealShip(),dto.getVessel(),dto.getPlatNo(),dto.getTimeIn(),dto.getTimeOut(),dto.getDriverName(),dto.getIssuedBy(),dto.getCheckedBy(),dto.getApprovedBy(),dto.getRemarks(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the eds table.
	 */
	@Transactional
	public void delete(EdsPk pk) throws EdsDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Eds
	 */
	public Eds mapRow(ResultSet rs, int row) throws SQLException
	{
		Eds dto = new Eds();
		dto.setId( rs.getInt( 1 ) );
		dto.setEdsnumber( rs.getString( 2 ) );
		dto.setEdsdate( rs.getTimestamp(3 ) );
		dto.setCinumber( rs.getString( 4 ) );
		dto.setBuyerName( rs.getString( 5 ) );
		dto.setBrand( rs.getString( 6 ) );
		dto.setReferensi( rs.getString( 7 ) );
		dto.setDestination( rs.getString( 8 ) );
		dto.setVan( rs.getString( 9 ) );
		dto.setSealShip( rs.getString( 10 ) );
		dto.setVessel( rs.getString( 11 ) );
		dto.setPlatNo( rs.getString( 12 ) );
		dto.setTimeIn( rs.getTimestamp(13 ) );
		dto.setTimeOut( rs.getTimestamp(14 ) );
		dto.setDriverName( rs.getString( 15 ) );
		dto.setIssuedBy( rs.getString( 16 ) );
		dto.setCheckedBy( rs.getString( 17 ) );
		dto.setApprovedBy( rs.getString( 18 ) );
		dto.setRemarks( rs.getString( 19 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..eds";
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Eds findByPrimaryKey(int id) throws EdsDaoException
	{
		try {
			List<Eds> list = jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria ''.
	 */
	@Transactional
	public List<Eds> findAll() throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Eds> findWhereIdEquals(int id) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'edsnumber = :edsnumber'.
	 */
	@Transactional
	public List<Eds> findWhereEdsnumberEquals(String edsnumber) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE edsnumber = ? ORDER BY edsnumber", this,edsnumber);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'edsdate = :edsdate'.
	 */
	@Transactional
	public List<Eds> findWhereEdsdateEquals(Date edsdate) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE edsdate = ? ORDER BY edsdate", this,edsdate);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'cinumber = :cinumber'.
	 */
	@Transactional
	public List<Eds> findWhereCinumberEquals(String cinumber) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE cinumber = ? ORDER BY cinumber", this,cinumber);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'buyerName = :buyerName'.
	 */
	@Transactional
	public List<Eds> findWhereBuyerNameEquals(String buyerName) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE buyerName = ? ORDER BY buyerName", this,buyerName);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'brand = :brand'.
	 */
	@Transactional
	public List<Eds> findWhereBrandEquals(String brand) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE brand = ? ORDER BY brand", this,brand);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'referensi = :referensi'.
	 */
	@Transactional
	public List<Eds> findWhereReferensiEquals(String referensi) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE referensi = ? ORDER BY referensi", this,referensi);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'destination = :destination'.
	 */
	@Transactional
	public List<Eds> findWhereDestinationEquals(String destination) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE destination = ? ORDER BY destination", this,destination);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'van = :van'.
	 */
	@Transactional
	public List<Eds> findWhereVanEquals(String van) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE van = ? ORDER BY van", this,van);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'sealShip = :sealShip'.
	 */
	@Transactional
	public List<Eds> findWhereSealShipEquals(String sealShip) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE sealShip = ? ORDER BY sealShip", this,sealShip);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'vessel = :vessel'.
	 */
	@Transactional
	public List<Eds> findWhereVesselEquals(String vessel) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE vessel = ? ORDER BY vessel", this,vessel);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'platNo = :platNo'.
	 */
	@Transactional
	public List<Eds> findWherePlatNoEquals(String platNo) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE platNo = ? ORDER BY platNo", this,platNo);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'time_in = :timeIn'.
	 */
	@Transactional
	public List<Eds> findWhereTimeInEquals(Date timeIn) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE time_in = ? ORDER BY time_in", this,timeIn);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'time_out = :timeOut'.
	 */
	@Transactional
	public List<Eds> findWhereTimeOutEquals(Date timeOut) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE time_out = ? ORDER BY time_out", this,timeOut);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'driverName = :driverName'.
	 */
	@Transactional
	public List<Eds> findWhereDriverNameEquals(String driverName) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE driverName = ? ORDER BY driverName", this,driverName);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'issuedBy = :issuedBy'.
	 */
	@Transactional
	public List<Eds> findWhereIssuedByEquals(String issuedBy) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE issuedBy = ? ORDER BY issuedBy", this,issuedBy);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'checkedBy = :checkedBy'.
	 */
	@Transactional
	public List<Eds> findWhereCheckedByEquals(String checkedBy) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE checkedBy = ? ORDER BY checkedBy", this,checkedBy);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'approvedBy = :approvedBy'.
	 */
	@Transactional
	public List<Eds> findWhereApprovedByEquals(String approvedBy) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE approvedBy = ? ORDER BY approvedBy", this,approvedBy);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the eds table that match the criteria 'remarks = :remarks'.
	 */
	@Transactional
	public List<Eds> findWhereRemarksEquals(String remarks) throws EdsDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, edsnumber, edsdate, cinumber, buyerName, brand, referensi, destination, van, sealShip, vessel, platNo, time_in, time_out, driverName, issuedBy, checkedBy, approvedBy, remarks FROM " + getTableName() + " WHERE remarks = ? ORDER BY remarks", this,remarks);
		}
		catch (Exception e) {
			throw new EdsDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the eds table that matches the specified primary-key value.
	 */
	public Eds findByPrimaryKey(EdsPk pk) throws EdsDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

	@Override
	public List<Eds> findEdsPaging(Eds t, Integer page) throws EdsDaoException {

		try{
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        	String edsNo = t.getEdsnumber();
        	String edsDate = df.format(t.getEdsdate());
        	
        	int i = page;
        	Map map = new HashMap();
        	map.put("i", i);
        	
        	StringBuffer sb = new StringBuffer();
        	
    		sb.append("declare @Page int, @PageSize int "
    				+"set @Page = '"+i+"'; "
    				+"set @PageSize = 10; "
    				+"with PagedResult "
    				+"as (select ROW_NUMBER() over (order by id asc) as id, edsnumber, edsdate from eds" +
    						" where edsnumber like '%"+edsNo+"%' and edsdate='"+edsDate+"' ) "
    				    +"select * from PagedResult where id between "
    				+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
    				     +"else @Page end and @PageSize * @Page ");
   
        	return jdbcTemplate.query(sb.toString(),new EdsListMap(),map);	
        
		}catch(Exception e){
			e.printStackTrace();
			throw new EdsDaoException("Query failed", e);
		}
	
	}

}

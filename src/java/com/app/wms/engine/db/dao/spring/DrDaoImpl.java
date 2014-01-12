package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.DrDao;
import com.app.wms.engine.db.dto.Delivery;
import com.app.wms.engine.db.dto.Dr;
import com.app.wms.engine.db.dto.DrPk;
import com.app.wms.engine.db.dto.map.DeliveryOrderListMap;
import com.app.wms.engine.db.exceptions.DeliveryDaoException;
import com.app.wms.engine.db.exceptions.DrDaoException;

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

public class DrDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Dr>, DrDao
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
	 * @return DrPk
	 */
	public DrPk insert(Dr dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount, issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getDrnumber(),dto.getOrnumber(),dto.getDmnumber(),dto.getDrdate(),dto.getCreatedby(),dto.getLotid(),dto.getWhCode(),dto.getWsNo(),dto.getUnitCost(),dto.getAmount(),dto.getIssuedBy(),dto.getDeliveredBy(),dto.getAccEntry(),dto.getApprovedBy(),dto.getRemarks(),dto.getReceivedBy(),dto.getDepartmentName(),dto.getSupplierName()} );
		DrPk pk = new DrPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the dr table.
	 */
	public void update(DrPk pk, Dr dto) throws DrDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET drnumber = ?, ornumber = ?, dmnumber = ?, drdate = ?, createdby = ?, lotid = ?, wh_code = ?, wsNo = ?, unitCost = ?, amount = ?, issuedBy = ?, deliveredBy = ?, accEntry = ?, approvedBy = ?, remarks = ?, receivedBy = ?, department_name = ?, supplierName = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getDrnumber(),dto.getOrnumber(),dto.getDmnumber(),dto.getDrdate(),dto.getCreatedby(),dto.getLotid(),dto.getWhCode(),dto.getWsNo(),dto.getUnitCost(),dto.getAmount(),dto.getIssuedBy(),dto.getDeliveredBy(),dto.getAccEntry(),dto.getApprovedBy(),dto.getRemarks(),dto.getReceivedBy(),dto.getDepartmentName(),dto.getSupplierName(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the dr table.
	 */
	@Transactional
	public void delete(DrPk pk) throws DrDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Dr
	 */
	public Dr mapRow(ResultSet rs, int row) throws SQLException
	{
		Dr dto = new Dr();
		dto.setId( rs.getInt( 1 ) );
		dto.setDrnumber( rs.getString( 2 ) );
		dto.setOrnumber( rs.getString( 3 ) );
		dto.setDmnumber( rs.getString( 4 ) );
		dto.setDrdate( rs.getTimestamp(5 ) );
		dto.setCreatedby( rs.getString( 6 ) );
		dto.setLotid( rs.getString( 7 ) );
		dto.setWhCode( rs.getString( 8 ) );
		dto.setWsNo( rs.getString( 9 ) );
		dto.setUnitCost( rs.getInt( 10 ) );
		if (rs.wasNull()) {
			dto.setUnitCostNull( true );
		}
		
		dto.setAmount( rs.getInt( 11 ) );
		if (rs.wasNull()) {
			dto.setAmountNull( true );
		}
		
		//dto.setFrom( rs.getString( 12 ) );
		dto.setIssuedBy( rs.getString( 12 ) );
		dto.setDeliveredBy( rs.getString( 13 ) );
		dto.setAccEntry( rs.getString( 14 ) );
		dto.setApprovedBy( rs.getString( 15 ) );
		dto.setRemarks( rs.getString( 16 ) );
		dto.setReceivedBy( rs.getString( 17 ) );
		dto.setDepartmentName( rs.getString( 18 ) );
		dto.setSupplierName( rs.getString( 19 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..dr";
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Dr findByPrimaryKey(int id) throws DrDaoException
	{
		try {
			List<Dr> list = jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria ''.
	 */
	@Transactional
	public List<Dr> findAll() throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Dr> findWhereIdEquals(int id) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'drnumber = :drnumber'.
	 */
	@Transactional
	public List<Dr> findWhereDrnumberEquals(String drnumber) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE drnumber = ? ORDER BY drnumber", this,drnumber);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'ornumber = :ornumber'.
	 */
	@Transactional
	public List<Dr> findWhereOrnumberEquals(String ornumber) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE ornumber = ? ORDER BY ornumber", this,ornumber);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'dmnumber = :dmnumber'.
	 */
	@Transactional
	public List<Dr> findWhereDmnumberEquals(String dmnumber) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE dmnumber = ? ORDER BY dmnumber", this,dmnumber);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'drdate = :drdate'.
	 */
	@Transactional
	public List<Dr> findWhereDrdateEquals(Date drdate) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE drdate = ? ORDER BY drdate", this,drdate);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'createdby = :createdby'.
	 */
	@Transactional
	public List<Dr> findWhereCreatedbyEquals(String createdby) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE createdby = ? ORDER BY createdby", this,createdby);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'lotid = :lotid'.
	 */
	@Transactional
	public List<Dr> findWhereLotidEquals(String lotid) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE lotid = ? ORDER BY lotid", this,lotid);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<Dr> findWhereWhCodeEquals(String whCode) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'wsNo = :wsNo'.
	 */
	@Transactional
	public List<Dr> findWhereWsNoEquals(String wsNo) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE wsNo = ? ORDER BY wsNo", this,wsNo);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'unitCost = :unitCost'.
	 */
	@Transactional
	public List<Dr> findWhereUnitCostEquals(int unitCost) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE unitCost = ? ORDER BY unitCost", this,unitCost);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'amount = :amount'.
	 */
	@Transactional
	public List<Dr> findWhereAmountEquals(int amount) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE amount = ? ORDER BY amount", this,amount);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'from = :from'.
	 */
	@Transactional
	public List<Dr> findWhereFromEquals(String from) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE from = ? ORDER BY from", this,from);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'issuedBy = :issuedBy'.
	 */
	@Transactional
	public List<Dr> findWhereIssuedByEquals(String issuedBy) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE issuedBy = ? ORDER BY issuedBy", this,issuedBy);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'deliveredBy = :deliveredBy'.
	 */
	@Transactional
	public List<Dr> findWhereDeliveredByEquals(String deliveredBy) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE deliveredBy = ? ORDER BY deliveredBy", this,deliveredBy);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'accEntry = :accEntry'.
	 */
	@Transactional
	public List<Dr> findWhereAccEntryEquals(String accEntry) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE accEntry = ? ORDER BY accEntry", this,accEntry);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'approvedBy = :approvedBy'.
	 */
	@Transactional
	public List<Dr> findWhereApprovedByEquals(String approvedBy) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE approvedBy = ? ORDER BY approvedBy", this,approvedBy);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'remarks = :remarks'.
	 */
	@Transactional
	public List<Dr> findWhereRemarksEquals(String remarks) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE remarks = ? ORDER BY remarks", this,remarks);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'receivedBy = :receivedBy'.
	 */
	@Transactional
	public List<Dr> findWhereReceivedByEquals(String receivedBy) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE receivedBy = ? ORDER BY receivedBy", this,receivedBy);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'department_name = :departmentName'.
	 */
	@Transactional
	public List<Dr> findWhereDepartmentNameEquals(String departmentName) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE department_name = ? ORDER BY department_name", this,departmentName);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the dr table that match the criteria 'supplierName = :supplierName'.
	 */
	@Transactional
	public List<Dr> findWhereSupplierNameEquals(String supplierName) throws DrDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, drnumber, ornumber, dmnumber, drdate, createdby, lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, accEntry, approvedBy, remarks, receivedBy, department_name, supplierName FROM " + getTableName() + " WHERE supplierName = ? ORDER BY supplierName", this,supplierName);
		}
		catch (Exception e) {
			throw new DrDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the dr table that matches the specified primary-key value.
	 */
	public Dr findByPrimaryKey(DrPk pk) throws DrDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}
	
	public List<Dr> findDeliveryPaging(Dr d, int page) throws DrDaoException {
		try {
			
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        	String drNo = d.getDrnumber();
        	String drDate = df.format(d.getDrdate()); 
        	
        	int i = page;
        	Map map = new HashMap();
        	map.put("i", i);
        	
        	StringBuffer sb = new StringBuffer();
        	
        	sb.append("declare @Page int, @PageSize int "
        			+" set @Page = '"+i+"'; "
        			+" set @PageSize = 10; "
        			+" with PagedResult "
        			+" as (select ROW_NUMBER() over (order by id asc) as id, drnumber, "
        			+" ornumber, dmnumber, drdate, createdby, "
        			+" lotid, wh_code, wsNo, unitCost, amount,  issuedBy, deliveredBy, "
        			+" accEntry, approvedBy, remarks, receivedBy, department_name, supplierName "
        			+" from dr "
        			+" where drnumber like '%"+drNo+"%' and drdate='"+drDate+"' ) "
        			+" select * from PagedResult where id between "
        			+" case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
        			+" else @Page end and @PageSize * @Page; ");

        	return jdbcTemplate.query(sb.toString(),this,map);
        
        } catch (Exception e) {
            throw new DrDaoException("Query failed", e);
        }
	}


}

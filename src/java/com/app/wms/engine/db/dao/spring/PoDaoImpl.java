package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.PoDao;
import com.app.wms.engine.db.dto.Po;
import com.app.wms.engine.db.dto.PoPk;
import com.app.wms.engine.db.exceptions.PoDaoException;
import com.app.wms.web.util.Utility;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class PoDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Po>, PoDao
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
	 * @return PoPk
	 */
	public PoPk insert(Po dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date,status, status_date_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
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
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getPonumber(),dto.getPodate(),dto.getPrsnumber(),dto.getPrsdate(),dto.getDeliverydate(),dto.getPoreferensi(),dto.getCreatedby(),dto.getCorpid(),dto.getWhCode(),dto.getDepartmentName(),dto.getSupplierName(),dto.getCurrency(),dto.getPrsremarks(),dto.getRoleCode(),dto.getStatus(),dto.getStatusdate()} );
		PoPk pk = new PoPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the po table.
	 */
	public void update(PoPk pk, Po dto) throws PoDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET ponumber = ?, podate = ?, prsnumber = ?, prsdate = ?, deliverydate = ?, poreferensi = ?, createdby = ?, corpid = ?, wh_code = ?, department_name = ?, supplier_name = ?, currency = ?, prsremarks = ?, role_code = ?,status, status_date = ?,status, status_date_date = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
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
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.BIGINT) );
		su.compile();
		su.update( new Object[] { dto.getPonumber(),dto.getPodate(),dto.getPrsnumber(),dto.getPrsdate(),dto.getDeliverydate(),dto.getPoreferensi(),dto.getCreatedby(),dto.getCorpid(),dto.getWhCode(),dto.getDepartmentName(),dto.getSupplierName(),dto.getCurrency(),dto.getPrsremarks(),dto.getRoleCode(),dto.getStatus(),dto.getStatusdate(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the po table.
	 */
	@Transactional
	public void delete(PoPk pk) throws PoDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Po
	 */
	public Po mapRow(ResultSet rs, int row) throws SQLException
	{
		Po dto = new Po();
		dto.setId( rs.getLong( 1 ) );
		dto.setPonumber( rs.getString( 2 ) );
		dto.setPodate( rs.getTimestamp(3 ) );
		dto.setPrsnumber( rs.getString( 4 ) );
		dto.setPrsdate( rs.getTimestamp(5 ) );
		dto.setDeliverydate( rs.getTimestamp(6 ) );
		dto.setPoreferensi( rs.getString( 7 ) );
		dto.setCreatedby( rs.getString( 8 ) );
		dto.setCorpid( rs.getString( 9 ) );
		dto.setWhCode( rs.getString( 10 ) );
		dto.setDepartmentName( rs.getString( 11 ) );
		dto.setSupplierName( rs.getString( 12 ) );
		dto.setCurrency( rs.getString( 13 ) );
		dto.setPrsremarks( rs.getString( 14 ) );
		dto.setRoleCode( rs.getString( 15 ) );
		dto.setStatus( rs.getString( 16 ) );
		dto.setStatusdate(rs.getDate(17));
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "po";
	}

	/** 
	 * Returns all rows from the po table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Po findByPrimaryKey(long id) throws PoDaoException
	{
		try {
			List<Po> list = jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po table that match the criteria ''.
	 */
	@Transactional
	public List<Po> findAll() throws PoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date FROM " + getTableName() + " ORDER BY id", this);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Po> findWhereIdEquals(long id) throws PoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po table that match the criteria 'ponumber = :ponumber'.
	 */
	@Transactional
	public List<Po> findWherePonumberEquals(String ponumber) throws PoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date FROM " + getTableName() + " WHERE ponumber = ? ORDER BY ponumber", this,ponumber);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po table that match the criteria 'podate = :podate'.
	 */
	@Transactional
	public List<Po> findWherePodateEquals(Date podate) throws PoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date FROM " + getTableName() + " WHERE podate = ? ORDER BY podate", this,podate);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po table that match the criteria 'prsnumber = :prsnumber'.
	 */
	@Transactional
	public List<Po> findWherePrsnumberEquals(String prsnumber) throws PoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date FROM " + getTableName() + " WHERE prsnumber = ? ORDER BY prsnumber", this,prsnumber);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po table that match the criteria 'prsdate = :prsdate'.
	 */
	@Transactional
	public List<Po> findWherePrsdateEquals(Date prsdate) throws PoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date FROM " + getTableName() + " WHERE prsdate = ? ORDER BY prsdate", this,prsdate);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po table that match the criteria 'deliverydate = :deliverydate'.
	 */
	@Transactional
	public List<Po> findWhereDeliverydateEquals(Date deliverydate) throws PoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date FROM " + getTableName() + " WHERE deliverydate = ? ORDER BY deliverydate", this,deliverydate);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po table that match the criteria 'poreferensi = :poreferensi'.
	 */
	@Transactional
	public List<Po> findWherePoreferensiEquals(String poreferensi) throws PoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date FROM " + getTableName() + " WHERE poreferensi = ? ORDER BY poreferensi", this,poreferensi);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po table that match the criteria 'createdby = :createdby'.
	 */
	@Transactional
	public List<Po> findWhereCreatedbyEquals(String createdby) throws PoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date FROM " + getTableName() + " WHERE createdby = ? ORDER BY createdby", this,createdby);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po table that match the criteria 'corpid = :corpid'.
	 */
	@Transactional
	public List<Po> findWhereCorpidEquals(String corpid) throws PoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date FROM " + getTableName() + " WHERE corpid = ? ORDER BY corpid", this,corpid);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<Po> findWhereWhCodeEquals(String whCode) throws PoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po table that match the criteria 'department_name = :departmentName'.
	 */
	@Transactional
	public List<Po> findWhereDepartmentNameEquals(String departmentName) throws PoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date FROM " + getTableName() + " WHERE department_name = ? ORDER BY department_name", this,departmentName);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po table that match the criteria 'supplier_name = :supplierName'.
	 */
	@Transactional
	public List<Po> findWhereSupplierNameEquals(String supplierName) throws PoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date FROM " + getTableName() + " WHERE supplier_name = ? ORDER BY supplier_name", this,supplierName);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po table that match the criteria 'currency = :currency'.
	 */
	@Transactional
	public List<Po> findWhereCurrencyEquals(String currency) throws PoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date FROM " + getTableName() + " WHERE currency = ? ORDER BY currency", this,currency);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po table that match the criteria 'prsremarks = :prsremarks'.
	 */
	@Transactional
	public List<Po> findWherePrsremarksEquals(String prsremarks) throws PoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date FROM " + getTableName() + " WHERE prsremarks = ? ORDER BY prsremarks", this,prsremarks);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po table that match the criteria 'role_code = :roleCode'.
	 */
	@Transactional
	public List<Po> findWhereRoleCodeEquals(String roleCode) throws PoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code,status, status_date FROM " + getTableName() + " WHERE role_code = ? ORDER BY role_code", this,roleCode);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the po table that match the criteria 'status = :status'.
	 */
	@Transactional
	public List<Po> findWhereStatusEquals(String status) throws PoDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, ponumber, podate, prsnumber, prsdate, deliverydate, poreferensi, createdby, corpid, wh_code, department_name, supplier_name, currency, prsremarks, role_code, status, status_date FROM " + getTableName() + " WHERE status = ? ORDER BY status", this,status);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the po table that matches the specified primary-key value.
	 */
	public Po findByPrimaryKey(PoPk pk) throws PoDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}
	
	@Transactional
	public List<Po> findWherePoNumberNotInGR() throws PoDaoException
	{
		try {
			return jdbcTemplate.query("select * from po where ponumber not in (select ponumber from goodreceive) and status='Y' order by deliverydate desc", this);
		}
		catch (Exception e) {
			throw new PoDaoException("Query failed", e);
		}
		
	}
        
    /* FYA : 07 January 2014 */
    public List<Po> fyaGetByDepartment(String[][] criteria) throws PoDaoException {
        try {
            Object[] o = new Utility().fyaGenerateSQLCriteria(criteria);
            return jdbcTemplate.query("SELECT * FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY id desc) AS rownum FROM po) AS qPo" 
                + o[0], this, o[1]);
        } catch(DataAccessException e) {
            e.printStackTrace();
            throw new PoDaoException("Query failed", e);
        }
    }

}

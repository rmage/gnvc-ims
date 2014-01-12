package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.DeliveryDao;
import com.app.wms.engine.db.dto.Delivery;
import com.app.wms.engine.db.dto.DeliveryPk;
import com.app.wms.engine.db.dto.map.DeliveryOrderListMap;
import com.app.wms.engine.db.dto.map.ProductListMap;
import com.app.wms.engine.db.exceptions.DeliveryDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDaoException;

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

public class DeliveryDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Delivery>, DeliveryDao
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
	 * @return DeliveryPk
	 */
	public DeliveryPk insert(Delivery dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( delivery_no, delivery_date, delivery_name, delivery_address, created_by, created_date, updated_by, updated_date, transporter_type ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getDeliveryNo(),dto.getDeliveryDate(),dto.getDeliveryName(),dto.getDeliveryAddress(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),dto.getTransporterType()} );
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the deliveryorder table.
	 */
	public void update(DeliveryPk pk, Delivery dto) throws DeliveryDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET delivery_no = ?, delivery_date = ?, delivery_name = ?, delivery_address = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE delivery_no = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getDeliveryNo(),dto.getDeliveryDate(),dto.getDeliveryName(),dto.getDeliveryAddress(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getDeliveryNo() } );
	}

	/** 
	 * Deletes a single row in the deliveryorder table.
	 */
	@Transactional
	public void delete(DeliveryPk pk) throws DeliveryDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE delivery_no = ?",pk.getDeliveryNo());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Delivery
	 */
	public Delivery mapRow(ResultSet rs, int row) throws SQLException
	{
		Delivery dto = new Delivery();
		dto.setDeliveryNo( rs.getString( 2 ) );
		dto.setDeliveryDate( rs.getTimestamp(3 ) );
		dto.setDeliveryName( rs.getString( 4 ) );
		dto.setDeliveryAddress( rs.getString( 5 ) );
		dto.setCreatedBy( rs.getString( 6 ) );
		dto.setCreatedDate( rs.getTimestamp(7 ) );
		dto.setUpdatedBy( rs.getString( 8 ) );
		dto.setUpdatedDate( rs.getTimestamp(9 ) );
		dto.setTransporterType(rs.getString( 10 ));
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..deliveryorder";
	}

	/** 
	 * Returns all rows from the deliveryorder table that match the criteria 'delivery_no = :deliveryNo'.
	 */
	@Transactional
	public Delivery findByPrimaryKey(String deliveryNo) throws DeliveryDaoException
	{
		try {
			List<Delivery> list = jdbcTemplate.query("SELECT delivery_no, delivery_date, delivery_name, delivery_address, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE delivery_no = ?", this,deliveryNo);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new DeliveryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the deliveryorder table that match the criteria ''.
	 */
	@Transactional
	public List<Delivery> findAll() throws DeliveryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, delivery_date, delivery_name, delivery_address, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY delivery_no", this);
		}
		catch (Exception e) {
			throw new DeliveryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the deliveryorder table that match the criteria 'delivery_no = :deliveryNo'.
	 */
	@Transactional
	public List<Delivery> findWhereDeliveryNoEquals(String deliveryNo) throws DeliveryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, delivery_date, delivery_name, delivery_address, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE delivery_no = ? ORDER BY delivery_no", this,deliveryNo);
		}
		catch (Exception e) {
			throw new DeliveryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the deliveryorder table that match the criteria 'delivery_date = :deliveryDate'.
	 */
	@Transactional
	public List<Delivery> findWhereDeliveryDateEquals(Date deliveryDate) throws DeliveryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, delivery_date, delivery_name, delivery_address, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE delivery_date = ? ORDER BY delivery_date", this,deliveryDate);
		}
		catch (Exception e) {
			throw new DeliveryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the deliveryorder table that match the criteria 'delivery_name = :deliveryName'.
	 */
	@Transactional
	public List<Delivery> findWhereDeliveryNameEquals(String deliveryName) throws DeliveryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, delivery_date, delivery_name, delivery_address, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE delivery_name = ? ORDER BY delivery_name", this,deliveryName);
		}
		catch (Exception e) {
			throw new DeliveryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the deliveryorder table that match the criteria 'delivery_address = :deliveryAddress'.
	 */
	@Transactional
	public List<Delivery> findWhereDeliveryAddressEquals(String deliveryAddress) throws DeliveryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, delivery_date, delivery_name, delivery_address, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE delivery_address = ? ORDER BY delivery_address", this,deliveryAddress);
		}
		catch (Exception e) {
			throw new DeliveryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the deliveryorder table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<Delivery> findWhereCreatedByEquals(String createdBy) throws DeliveryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, delivery_date, delivery_name, delivery_address, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new DeliveryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the deliveryorder table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<Delivery> findWhereCreatedDateEquals(Date createdDate) throws DeliveryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, delivery_date, delivery_name, delivery_address, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new DeliveryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the deliveryorder table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<Delivery> findWhereUpdatedByEquals(String updatedBy) throws DeliveryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, delivery_date, delivery_name, delivery_address, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new DeliveryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the deliveryorder table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<Delivery> findWhereUpdatedDateEquals(Date updatedDate) throws DeliveryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT delivery_no, delivery_date, delivery_name, delivery_address, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new DeliveryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the deliveryorder table that matches the specified primary-key value.
	 */
	public Delivery findByPrimaryKey(DeliveryPk pk) throws DeliveryDaoException
	{
		return findByPrimaryKey( pk.getDeliveryNo() );
	}

	@Override
	public List<Delivery> findDeliveryPaging(Delivery d, int page) throws DeliveryDaoException {
		try {
			String corpId = d.getCorpId();
        	String whCode = d.getWhCode();
        	int i = page;
        	Map map = new HashMap();
        	map.put("corpId", corpId);
        	map.put("whCode", whCode);
        	map.put("i", i);
        	
        	StringBuffer sb = new StringBuffer();
        
        	sb.append("declare @Page int, @PageSize int "
        			+"set @Page = '"+i+"'; "
        			+"set @PageSize = 10; " 
        			+"with PagedResult " 
        			+"as (select a.delivery_no, a.delivery_date, a.transporter_type, ROW_NUMBER() over (order by delivery_date desc) as id from deliveryorder a "
        			+"inner join do_detail b on a.delivery_no = b.delivery_no where b.corp_id like '"+corpId+"' and b.wh_code like '"+whCode+"' "
        			+"group by a.delivery_no, a.delivery_date, a.transporter_type) " 
        			+"select * from PagedResult where id between " 
        			+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 " 
        			+"else @Page end and @PageSize * @Page " 
        			+"order by delivery_date desc "); 
        	
        	return jdbcTemplate.query(sb.toString(),new DeliveryOrderListMap(),map);
        
        } catch (Exception e) {
            throw new DeliveryDaoException("Query failed", e);
        }
	}

}

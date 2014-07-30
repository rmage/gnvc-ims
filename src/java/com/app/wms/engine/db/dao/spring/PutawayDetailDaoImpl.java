package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.PutawayDetailDao;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.dto.PutawayDetailPk;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.dto.map.PutawayDetailMap;
import com.app.wms.engine.db.dto.map.PutawayDetailMaxBalance;
import com.app.wms.engine.db.dto.map.PutawayDetailMaxIdMap;
import com.app.wms.engine.db.dto.map.PutawayDetailPickingMap;
import com.app.wms.engine.db.dto.map.PutawayDetailProductMap;
import com.app.wms.engine.db.exceptions.PickingDetailDaoException;
import com.app.wms.engine.db.exceptions.PutawayDetailDaoException;
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

public class PutawayDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<PutawayDetail>, PutawayDetailDao
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
	public void insert(PutawayDetail dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, lotid, product_code ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getPutawayId(),dto.getProductId(),dto.getReceivedDate(),dto.getExpiredDate(),dto.getUnitCode(),dto.getQtyOrder(),dto.getQtyPut(),dto.getBalance(),dto.getUserId(),dto.getCorpId(),dto.getLocationCode(),dto.getWhCode(),dto.getLotid(),dto.getProductCode()} );
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return PutawayDetail
	 */
	public PutawayDetail mapRow(ResultSet rs, int row) throws SQLException
	{
		PutawayDetail dto = new PutawayDetail();
		dto.setPutawayId( rs.getString( 1 ) );
		dto.setProductId( rs.getString( 2 ) );
		dto.setReceivedDate( rs.getTimestamp(3 ) );
		dto.setExpiredDate( rs.getTimestamp(4 ) );
		dto.setUnitCode( rs.getString( 5 ) );
		dto.setQtyOrder( rs.getInt( 6 ) );
		if (rs.wasNull()) {
			dto.setQtyOrderNull( true );
		}
		
		dto.setQtyPut( rs.getInt( 7 ) );
		if (rs.wasNull()) {
			dto.setQtyPutNull( true );
		}
		
		dto.setBalance( rs.getInt( 8 ) );
		if (rs.wasNull()) {
			dto.setBalanceNull( true );
		}
		
		dto.setUserId( rs.getString( 9 ) );
		dto.setCorpId( rs.getString( 10 ) );
		dto.setLocationCode( rs.getString( 11 ) );
		dto.setWhCode( rs.getString( 12 ) );
		dto.setId( rs.getInt( 13 ) );
		dto.setLotid( rs.getString( 14 ) );
		dto.setProductCode( rs.getString( 15 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "putaway_detail";
	}

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria ''.
	 */
	@Transactional
	public List<PutawayDetail> findAll() throws PutawayDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'putaway_id = :putawayId'.
	 */
	@Transactional
	public List<PutawayDetail> findWherePutawayIdEquals(String putawayId) throws PutawayDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code FROM " + getTableName() + " WHERE putaway_id = ? ORDER BY putaway_id", this,putawayId);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'product_id = :productId'.
	 */
	@Transactional
	public List<PutawayDetail> findWhereProductIdEquals(String productId) throws PutawayDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code FROM " + getTableName() + " WHERE product_id = ? ORDER BY product_id", this,productId);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'received_date = :receivedDate'.
	 */
	@Transactional
	public List<PutawayDetail> findWhereReceivedDateEquals(Date receivedDate) throws PutawayDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code FROM " + getTableName() + " WHERE received_date = ? ORDER BY received_date", this,receivedDate);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'expired_date = :expiredDate'.
	 */
	@Transactional
	public List<PutawayDetail> findWhereExpiredDateEquals(Date expiredDate) throws PutawayDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code FROM " + getTableName() + " WHERE expired_date = ? ORDER BY expired_date", this,expiredDate);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'unit_code = :unitCode'.
	 */
	@Transactional
	public List<PutawayDetail> findWhereUnitCodeEquals(String unitCode) throws PutawayDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code FROM " + getTableName() + " WHERE unit_code = ? ORDER BY unit_code", this,unitCode);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'qty_order = :qtyOrder'.
	 */
	@Transactional
	public List<PutawayDetail> findWhereQtyOrderEquals(int qtyOrder) throws PutawayDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code FROM " + getTableName() + " WHERE qty_order = ? ORDER BY qty_order", this,qtyOrder);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'qty_put = :qtyPut'.
	 */
	@Transactional
	public List<PutawayDetail> findWhereQtyPutEquals(int qtyPut) throws PutawayDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code FROM " + getTableName() + " WHERE qty_put = ? ORDER BY qty_put", this,qtyPut);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'balance = :balance'.
	 */
	@Transactional
	public List<PutawayDetail> findWhereBalanceEquals(int balance) throws PutawayDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code FROM " + getTableName() + " WHERE balance = ? ORDER BY balance", this,balance);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'user_id = :userId'.
	 */
	@Transactional
	public List<PutawayDetail> findWhereUserIdEquals(String userId) throws PutawayDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code FROM " + getTableName() + " WHERE user_id = ? ORDER BY user_id", this,userId);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'corp_id = :corpId'.
	 */
	@Transactional
	public List<PutawayDetail> findWhereCorpIdEquals(String corpId) throws PutawayDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code FROM " + getTableName() + " WHERE corp_id = ? ORDER BY corp_id", this,corpId);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'location_code = :locationCode'.
	 */
	@Transactional
	public List<PutawayDetail> findWhereLocationCodeEquals(String locationCode) throws PutawayDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code FROM " + getTableName() + " WHERE location_code = ? ORDER BY location_code", this,locationCode);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<PutawayDetail> findWhereWhCodeEquals(String whCode) throws PutawayDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<PutawayDetail> findWhereIdEquals(int id) throws PutawayDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'lotid = :lotid'.
	 */
	@Transactional
	public List<PutawayDetail> findWhereLotidEquals(String lotid) throws PutawayDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code FROM " + getTableName() + " WHERE lotid = ? ORDER BY lotid", this,lotid);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'product_code = :productCode'.
	 */
	@Transactional
	public List<PutawayDetail> findWhereProductCodeEquals(String productCode) throws PutawayDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code FROM " + getTableName() + " WHERE product_code = ? ORDER BY product_code", this,productCode);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
		
	}
	
	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'product_code = :productCode'.
	 */
	@Transactional
	public List<PutawayDetail> findWhereProductModel(PutawayDetail pud) throws PutawayDetailDaoException
	{
		try{
			if (pud == null){
				pud = new PutawayDetail();
			}
			
			String productId = pud.getProductId();
			String locationCode = pud.getLocationCode();
			//String lotId = pud.getLotid();
			String corpId = pud.getCorpId();
			String whCode = pud.getWhCode();	
			
			StringBuffer sb = new StringBuffer();
			
				sb.append("select " 
					 +"putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code " 
			         +"from putaway_detail " 
			         +"where location_code like '"+locationCode+"' and product_id like '"+productId+"' AND lotid like '%' AND corp_id like '"+corpId+"' AND wh_code like '"+whCode+"' ");
			        
			return jdbcTemplate.query(sb.toString(),this);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
		
	}

	@Override
	public List<PutawayDetail> findWhereProductCodeEquals(PutawayDetail pud) throws PutawayDetailDaoException {
		try{
			if (pud == null){
				pud = new PutawayDetail();
			}
			
			String productCode = pud.getProductCode();
			String corpId = pud.getCorpId();
			String whCode = pud.getWhCode();
			
			System.out.println("pud ="+pud);
			
			Map map = new HashMap();
			map.put("productCode", productCode);
			map.put("corpId", corpId);
			map.put("whCode", whCode);
			
			StringBuffer sb = new StringBuffer();
				/*
				sb.append("select " 
					 +"a.location_code, c.location_name, " 
					 +"a.received_date, a.expired_date, " 
					 +"a.balance, a.lotid " 
			         +"from putaway_detail a " 
			         +"inner join product d on a.product_code = d.product_code "
			         +"inner join putaway b on a.putaway_id = b.putaway_id "
			         +"inner join wh_location c on a.location_code = c.location_id "
			         +"where d.product_code like '"+productCode+"' ");
			         
			         
			         select top 1 max(a.balance) as balance,a.location_code, c.location_name, d.product_code, a.lotid, 
a.received_date, a.expired_date from putaway_detail a inner join product d on a.product_code = d.product_code 
inner join putaway b on a.putaway_id = b.putaway_id 
inner join wh_location c on a.location_code = c.location_code 
where d.product_code like 'PRO123' and b.status_app='SUCCESSFULL' 
--and a.balance <>0 and a.corp_id= 'CY-0001' and a.wh_code= 'WH-0001' 
and a.balance <>0 and a.corp_id= 'CY-0001' and a.wh_code= 'WH-0001' 
group by a.location_code, c.location_name, d.product_code, a.lotid, a.received_date, a.expired_date
order by balance desc



select distinct max(a.balance) as balance, max(a.lotid) as lotid, 
a.location_code, c.location_name, d.product_code, 
a.received_date, a.expired_date 
from putaway_detail a inner join product d on a.product_code = d.product_code 
inner join putaway b on a.putaway_id = b.putaway_id 
inner join wh_location c on a.location_code = c.location_code 
where d.product_code like 'TS00001' and b.status_app='SUCCESSFULL' 
and a.balance <>0 and a.corp_id= 'CY-0001' and a.wh_code= 'WH-0001' 
group by a.location_code, c.location_name, d.product_code, a.received_date, a.expired_date
order by location_code

				*/
			
				/*
				sb.append("select distinct max(a.balance) as balance, " 
						 +"a.location_code, c.location_name, d.product_code,  max(a.lotid) as lotid, a.received_date, a.expired_date " 
				         +"from putaway_detail a " 
				         +"inner join product d on a.product_code = d.product_code "
				         +"inner join putaway b on a.putaway_id = b.putaway_id "
				         +"inner join wh_location c on a.location_code = c.location_code "
				         +"where d.product_code like '"+productCode+"' " 
				         +"and b.status_app='SUCCESSFULL' and a.balance <>0 "
				         +"and a.corp_id= '"+corpId+"' and a.wh_code= '"+whCode+"' "
				         +"group by a.location_code, c.location_name, d.product_code, a.received_date, a.expired_date order by location_code");
				         
				         return jdbcTemplate.query(sb.toString(),new PutawayDetailMap(),map);
				         
				        select a.balance , a.location_code, b.location_name, b.product_code   
						from  vbalanceputawaydetail a  
						inner join vlocationproduct b on a.location_code = b.location_code 
						inner join vproductsku c on b.product_code = c.product_code 
						where a.wh_code like 'WH-0001' 
						and a.corp_id like 'CY-0001' and b.product_code like '1FG00001' order by a.location_code;
				*/
				
				sb.append("select b.id, a.balance , a.location_code, b.location_name, b.product_code " 
				         +"from vbalanceputawaydetail a " 
				         +"inner join vlocationproduct b on a.location_code = b.location_code "
				         +"inner join vproductsku c on b.product_code = c.product_code "
				         +"where b.product_code like '"+productCode+"' and a.balance <>0 " 
				         +"and a.corp_id= '"+corpId+"' and a.wh_code= '"+whCode+"' "
				         +"order by a.location_code ");
				
			return jdbcTemplate.query(sb.toString(),new PutawayDetailPickingMap(),map);
		}
		catch(Exception e){
			 throw new PutawayDetailDaoException("Query failed", e);
		}
	}
	
	@Override
	public List<PutawayDetail> findWhereProductCodeEquals2(PutawayDetail pud) throws PutawayDetailDaoException {
		try{
			if (pud == null){
				pud = new PutawayDetail();
			}
			String productCode = pud.getProductCode();
			String lotId = pud.getLotid();
			
			Map map = new HashMap();
			map.put("productCode", productCode);
			map.put("lotId", lotId);
			
			StringBuffer sb = new StringBuffer();
			
			sb.append("select " 
					 +"a.location_code, c.location_name, " 
					 +"a.received_date, a.expired_date, " 
					 +"a.qty_put as qty, a.lotid " 
			         +"from putaway_detail a " 
			         +"inner join product d on a.product_code = d.product_code "
			         +"inner join putaway b on a.putaway_id = b.putaway_id "
			         +"inner join wh_location c on a.location_code = c.location_id "
			         +"where d.product_code like '"+productCode+"' "
			         +"and b.status_app='SUCCESSFULL' "
			         +"and a.lotid like '"+lotId+"' ");
			        
			return jdbcTemplate.query(sb.toString(),new PutawayDetailMap(),map);
		}
		catch(Exception e){
			 throw new PutawayDetailDaoException("Query failed", e);
		}
	}
	
	public List<PutawayDetail> findWhereProductLocation(PutawayDetail pud) throws PutawayDetailDaoException {
		try{
			
			if (pud == null){
				pud = new PutawayDetail();
			}
			String corpId = pud.getCorpId();
			String whCode = pud.getWhCode();
			
			Map map = new HashMap();
			map.put("corpId", corpId);
			map.put("whCode", whCode);
			
			StringBuffer sb = new StringBuffer();
			/*
			 * select top 1 MAX(a.balance) as balance,
				 b.product_code,  
				 b.product_name,    
				 a.unit_code, 
				 a.location_code 
				 from  putaway_detail a  
				 inner join putaway c on a.putaway_id = c.putaway_id 
				 inner join product b on a.product_id = b.product_id 
				 where c.status_app='SUCCESSFULL' 
				 and a.balance <>0
				 and a.wh_code like '%' 
				 and a.corp_id like '%' 
				 group by a.balance,b.product_code, b.product_name, a.unit_code, a.location_code
				 order by a.balance;
				 
				last updated tgl 23 April 2013;
				
				select b.product_code, c.product_name, c.sku as unit_code, a.location_code, a.balance
				from vbalanceputawaydetail a
				inner join vlocationproduct b
				on a.location_code = b.location_code
				inner join vproductsku c
				on b.product_code = c.product_code
				where a.wh_code like 'WH-0001' 
				and a.corp_id like 'CY-0001' 
				order by b.product_code;
				 
			 */
			sb.append("select b.product_code, c.product_name, c.sku as unit_code, a.location_code, a.balance " 
			         +"from  vbalanceputawaydetail a " 
			         +"inner join vlocationproduct b on a.location_code = b.location_code "
			         +"inner join vproductsku c on b.product_code = c.product_code "
			         +"where a.wh_code like '"+whCode+"' "
			         +"and a.corp_id like '"+corpId+"' " 
			         +"and b.wh_code like '"+whCode+"' "
			         +"and b.corp_id like '"+corpId+"' " 
			         +"order by b.product_code");
			        
			return jdbcTemplate.query(sb.toString(),new PutawayDetailProductMap(),map);
		}
		catch(Exception e){
			 throw new PutawayDetailDaoException("Query failed", e);
		}
	}

	@Override
	public void update(PutawayDetail dto) throws PickingDetailDaoException {
		// TODO Auto-generated method stub 
		
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET qty_pick = ? where putaway_id = ? and product_id = ? and balance <> 0 and location_code = ? and corp_id = ? and wh_code = ?");
		//su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		//su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		//su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		//su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getQtyPick(),dto.getPutawayId(),dto.getProductId(), dto.getLocationCode(), dto.getCorpId(), dto.getWhCode()} );
		
	}

	@Override
	//untuk meng-update balance after picking
	public void updateBalance(PutawayDetail dto) throws PickingDetailDaoException {
		// TODO Auto-generated method stub
		//String locationCode = dto.getLocationCode();
		System.out.println(">>>>>>>>>>>>>>>dto :"+dto);
		
		SqlUpdate su = new SqlUpdate( dataSource, 
		"update " + getTableName() + " set balance = ? " +
		"where location_code = ? and product_id = ? and corp_id = ? and wh_code = ?  and putaway_id = ?");
	    
		//su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		//su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		//su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		//su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getBalance(),dto.getLocationCode(),dto.getProductId(),dto.getCorpId(), dto.getWhCode(), dto.getPutawayId()} );
		
	}

	@Override
	public List<PutawayDetail> findMaxBalance(PutawayDetail pud) throws PutawayDetailDaoException {
		// TODO Auto-generated method stub
		try{
			if (pud == null){
				pud = new PutawayDetail();
			}
			
			String productId = pud.getProductId();
			String corpId = pud.getCorpId();
			String whCode = pud.getWhCode();
			Map map = new HashMap();
			map.put("productId", productId);
			map.put("corpId", corpId);
			map.put("whCode", whCode);
			
			StringBuffer sb = new StringBuffer();
			
				//select top 1 max(balance) as balance from putaway_detail where product_id = 'PR000001' and corp_id ='CY-0001' and wh_code ='WH-0001'
			 
				sb.append("select max(id) as id, max(balance) as balance " 
			         +"from putaway_detail " 
			         +"where product_id like '"+productId+"' AND corp_id like '"+corpId+"' AND wh_code like '"+whCode+"' ");
			        
			return jdbcTemplate.query(sb.toString(),new PutawayDetailMaxBalance(),map);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
	}

	@Override
	public void updateQtyPut(PutawayDetail dto) throws PickingDetailDaoException {
		// TODO Auto-generated method stub
		
		SqlUpdate su = new SqlUpdate( dataSource, 
		"update " + getTableName() + " set qty_put = ? " +
		"where product_id = ? and lotid = ? and location_code = ? and corp_id = ? and wh_code = ? ");
	    
		//su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		//su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		//su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		//su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getQtyLot(),dto.getProductId(),dto.getLotid(),dto.getLocationCode(),dto.getCorpId(), dto.getWhCode()} );
		
	}

	@Override
	public void updateBalance1(PutawayDetail dto) throws PickingDetailDaoException {
		// TODO Auto-generated method stub
		
		SqlUpdate su = new SqlUpdate( dataSource, 
		"update " + getTableName() + " set balance = ? " +
		"where id = ? and product_id = ? and corp_id = ? and wh_code = ? ");
		//"where qty_put = qty_lot and qty_put<>0 and qty_lot <>0 and product_id = ? and corp_id = ? and wh_code = ? ");
	    
		//su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		//su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		//su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		//su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getBalance(),dto.getId(),dto.getProductId(),dto.getCorpId(), dto.getWhCode()} );
		
	}
	
	@Override
	public List<PutawayDetail> findBalanceLocation(PutawayDetail pud)throws PutawayDetailDaoException {
		// TODO Auto-generated method stub
		try{
			if (pud == null){
				pud = new PutawayDetail();
			}
			
			String productId = pud.getProductId();
			String corpId = pud.getCorpId();
			String whCode = pud.getWhCode();
			String locationCode = pud.getLocationCode();
			String lotId = pud.getLotid();
			Map map = new HashMap();
			map.put("productId", productId);
			map.put("locationCode", locationCode);
			//map.put("lotId", lotId);
			map.put("corpId", corpId);
			map.put("whCode", whCode);
			
			StringBuffer sb = new StringBuffer();
			
				//select balance from putaway_detail where location_code='R1A001' and lotid='1' and product_id = 'PR000001' and corp_id ='CY-0001' and wh_code ='WH-0001'
			 
				sb.append("select max(id) as id, max(balance) as balance " 
			         +"from putaway_detail " 
			         +"where location_code like '"+locationCode+"' and product_id like '"+productId+"' AND corp_id like '"+corpId+"' AND wh_code like '"+whCode+"' ");
			        
			return jdbcTemplate.query(sb.toString(),new PutawayDetailMaxBalance(),map);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
	}

	@Override
	public List<PutawayDetail> findMaxBalance1(PutawayDetail pud)throws PutawayDetailDaoException {
		// TODO Auto-generated method stub
		try{
			if (pud == null){
				pud = new PutawayDetail();
			}
			
			String productId = pud.getProductId();
			String corpId = pud.getCorpId();
			String whCode = pud.getWhCode();
			int maxId = pud.getId();
			Map map = new HashMap();
			map.put("productId", productId);
			map.put("corpId", corpId);
			map.put("whCode", whCode);
			
			StringBuffer sb = new StringBuffer();
			
				//select top 1 max(balance) as balance from putaway_detail where product_id = 'PR000001' and corp_id ='CY-0001' and wh_code ='WH-0001'
			 
				sb.append("select max(id) as id, max(balance) as balance " 
			         +"from putaway_detail " 
			         +"where id like '"+maxId+"' and product_id like '"+productId+"' AND corp_id like '"+corpId+"' AND wh_code like '"+whCode+"' ");
			        
			return jdbcTemplate.query(sb.toString(),new PutawayDetailMaxBalance(),map);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
	}

	@Override
	public List<PutawayDetail> findMaxId(PutawayDetail pud)	throws PutawayDetailDaoException {
		// TODO Auto-generated method stub
		try{
			if (pud == null){
				pud = new PutawayDetail();
			}
			
			String productId = pud.getProductId();
			String corpId = pud.getCorpId();
			String whCode = pud.getWhCode();
			Map map = new HashMap();
			map.put("productId", productId);
			map.put("corpId", corpId);
			map.put("whCode", whCode);
			
			StringBuffer sb = new StringBuffer();
			
				sb.append("select MAX(id) as id " 
			         +"from putaway_detail " 
			         +"where product_id like '"+productId+"' AND corp_id like '"+corpId+"' AND wh_code like '"+whCode+"' ");
			        
			return jdbcTemplate.query(sb.toString(),new PutawayDetailMaxIdMap(),map);
		}
		catch (Exception e) {
			throw new PutawayDetailDaoException("Query failed", e);
		}
	}
	
	  /* added by FYA : 14 May 2013 */
    public List<PutawayDetail> findForReplenishFrom(String whCode, String sidx, String sord) throws PutawayDetailDaoException {
        try {
            return jdbcTemplate.query("SELECT putaway_id, P.product_name as product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, PD.user_id, PD.corp_id, " +
                "PD.location_code, PD.wh_code, PD.id, WL.max_product AS lotid, PD.product_code " +
                "FROM " + getTableName() + " PD LEFT JOIN product P ON P.product_id = PD.product_id LEFT JOIN wh_location WL ON WL.location_code = PD.location_code " +
                "WHERE PD.id IN (select max(id) id from putaway_detail where wh_code = ? group by location_code) ORDER BY " + sidx + " " + sord, this, whCode);
        } catch(Exception e) {
            throw new PutawayDetailDaoException("Query failed", e);
        }
    }
    
    /* added by FYA : 22 May 2013 */
    public List<PutawayDetail> findForReplenishTo(String whCode, String not, String product, String sidx, String sord) throws PutawayDetailDaoException {
        try {
            return jdbcTemplate.query("SELECT putaway_id, P.product_name as product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, PD.user_id, PD.corp_id, " +
                "PD.location_code, PD.wh_code, PD.id, WL.max_product AS lotid, PD.product_code " +
                "FROM " + getTableName() + " PD LEFT JOIN product P ON P.product_id = PD.product_id LEFT JOIN wh_location WL ON WL.location_code = PD.location_code " +
                "WHERE PD.id IN (select max(id) id from putaway_detail where wh_code = ? group by location_code) and PD.location_code != ? and PD.product_code = ? " + 
                    " and PD.balance < WL.max_product ORDER BY " + sidx + " " + sord, this, whCode, not, product);
        } catch(Exception e) {
            throw new PutawayDetailDaoException("Query failed", e);
        }
    }
    
    /* added by FYA : 10 June 2013 */
    public List<PutawayDetail> findForReplenishToX(String whCode, String corpId, String product, String sidx, String sord) throws PutawayDetailDaoException {
        try {
            return jdbcTemplate.query("SELECT putaway_id, P.product_name as product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, PD.user_id, PD.corp_id, " +
                "PD.location_code, PD.wh_code, PD.id, WL.max_product AS lotid, PD.product_code " +
                "FROM " + getTableName() + " PD LEFT JOIN product P ON P.product_id = PD.product_id LEFT JOIN wh_location WL ON WL.location_code = PD.location_code " +
                "WHERE PD.id IN (select max(id) id from putaway_detail where wh_code != ? group by location_code) and PD.corp_id = ? and PD.product_code = ? " + 
                    " and PD.balance < WL.max_product ORDER BY " + sidx + " " + sord, this, whCode, corpId, product);
        } catch(Exception e) {
            throw new PutawayDetailDaoException("Query failed", e);
        }
    }
    
    /* added by FYA : 23 May 2013 */
    public PutawayDetail findExactLocationCode(String locationCode, String whCode, String corpId) throws PutawayDetailDaoException {
        try {
            return jdbcTemplate.query("SELECT TOP(1) putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code " +
                "FROM " + getTableName() + " WHERE location_code = ? AND wh_code = ? AND corp_id = ? ORDER BY id DESC", this, locationCode, whCode, corpId).get(0);
        } catch(Exception e) {
            throw new PutawayDetailDaoException("Query failed", e);
        }
    }
    
    /* added by FYA : 14 June 2013 */
    public PutawayDetail findExactLocationCodeC(String locationCode, String userId, String corpId) throws PutawayDetailDaoException {
        try {
            return jdbcTemplate.query("SELECT TOP(1) putaway_id, product_id, received_date, expired_date, unit_code, qty_order, qty_put, balance, user_id, corp_id, location_code, wh_code, id, lotid, product_code " +
                "FROM " + getTableName() + " WHERE location_code = ? AND wh_code = (SELECT wh_code FROM \"user\" WHERE user_id = ?) AND corp_id = ? ORDER BY id DESC", this, locationCode, userId, corpId).get(0);
        } catch(Exception e) {
            throw new PutawayDetailDaoException("Query failed", e);
        }
    }

}

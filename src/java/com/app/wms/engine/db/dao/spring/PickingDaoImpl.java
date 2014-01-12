package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.transaction.annotation.Transactional;
import com.app.web.engine.search.PickingSearch;
import com.app.wms.engine.db.dao.PickingDao;
import com.app.wms.engine.db.dto.Picking;
import com.app.wms.engine.db.dto.PickingDetail;
import com.app.wms.engine.db.dto.PickingPk;
import com.app.wms.engine.db.dto.SalesOrderDetail;
import com.app.wms.engine.db.dto.map.PickingDetailMap;
import com.app.wms.engine.db.dto.map.PickingDetailSuccessMap;
import com.app.wms.engine.db.dto.map.PickingListMap;
import com.app.wms.engine.db.dto.map.PickingProductDetailMap;
import com.app.wms.engine.db.dto.map.ProductListMap;
import com.app.wms.engine.db.dto.map.SalesOrderListMap;
import com.app.wms.engine.db.dto.map.SalesOrderPickingListMap;
import com.app.wms.engine.db.exceptions.PickingDaoException;
import com.app.wms.engine.db.exceptions.PickingDetailDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDaoException;

public class PickingDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Picking>, PickingDao 
{


    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;

    /**
     * Method 'setDataSource'
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    /**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return PickingPk
	 */
	public PickingPk insert(Picking dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( picking_id, picking_date, so_number, so_date, status_app, app_date, created_by, created_date, updated_by, updated_date, tallyman ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getPickingNo(),dto.getPickingDate(),dto.getSoNumber(),dto.getSoDate(),dto.getStatusApp(),dto.getAppDate(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),dto.getTallyman()} );
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the picking table.
	 */
	public void update(PickingPk pk, Picking dto) throws PickingDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET picking_id = ?, picking_date = ?, so_number = ?, so_date = ?, status_app = ?, app_date = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ?, tallyman = ? WHERE picking_id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getPickingNo(),dto.getPickingDate(),dto.getSoNumber(),dto.getSoDate(),dto.getStatusApp(),dto.getAppDate(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),dto.getTallyman(),pk.getId() } );
	}

    /**
     * Deletes a single row in the Picking table.
     */
    @Transactional
    public void delete(PickingPk pk) throws PickingDaoException {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE picking_id = ?", pk.getId());
    }

    /**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Picking
	 */
	public Picking mapRow(ResultSet rs, int row) throws SQLException
	{
		Picking dto = new Picking();
		
		dto.setId( rs.getInt( 1 ) );
		dto.setPickingNo( rs.getString( 2 ) );
		dto.setPickingDate( rs.getTimestamp(3 ) );
		dto.setSoNumber( rs.getString( 4 ) );
		dto.setSoDate( rs.getTimestamp(5 ) );
		dto.setStatusApp( rs.getString( 6 ) );
		dto.setAppDate( rs.getTimestamp(7 ) );
		dto.setCreatedBy( rs.getString( 8 ) );
		dto.setCreatedDate( rs.getTimestamp(9 ) );
		dto.setUpdatedBy( rs.getString( 10 ) );
		dto.setUpdatedDate( rs.getTimestamp(11 ) );
		dto.setTallyman(rs.getString(12));
		return dto;
	}

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "picking";
    }

    /** 
	 * Returns all rows from the picking table that match the criteria 'picking_id = :pickingId'.
	 */
	@Transactional
	public Picking findByPrimaryKey(String pickingId) throws PickingDaoException
	{
		try {
			List<Picking> list = jdbcTemplate.query("SELECT ROW_NUMBER() over (order by picking_id asc) as id, picking_id, picking_date, so_number, so_date, status_app, app_date, created_by, created_date, updated_by, updated_date, tallyman FROM " + getTableName() + " WHERE picking_id = ?", this,pickingId);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new PickingDaoException("Query failed", e);
		}
		
	}

    /**
     * Returns all rows from the Picking table that match the criteria ''.
     */
    @Transactional
    public List<Picking> findAll() throws PickingDaoException {
        try {
            return jdbcTemplate.query("select ROW_NUMBER() over (order by so_number asc) as id, "
            						  +"picking_id, picking_date, so_number, so_date, " 
            				          +"status_app, app_date, created_by, "
            						  +"created_date, updated_by, updated_date, tallyman from " + getTableName() + " order by picking_id", this);
        } catch (Exception e) {
            throw new PickingDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the Picking table that match the criteria 'Picking_id = :Pickingid'.
     */
    @Transactional
    public List<Picking> findWherePickingidEquals(String Pickingid) throws PickingDaoException {
        try {
            return jdbcTemplate.query("SELECT Picking_id, NAME FROM " + getTableName() + " WHERE Picking_id = ? ORDER BY Picking_id", this, Pickingid);
        } catch (Exception e) {
            throw new PickingDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the Picking table that match the criteria 'NAME = :name'.
     */
    @Transactional
    public List<Picking> findWhereNameEquals(String name) throws PickingDaoException {
        try {
            return jdbcTemplate.query("SELECT Picking_id, NAME FROM " + getTableName() + " PickingERE NAME = ? ORDER BY NAME", this, name);
        } catch (Exception e) {
            throw new PickingDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the Picking table that matches the specified primary-key value.
     */
    @Transactional
    public Picking findByPrimaryKey(PickingPk pk) throws PickingDaoException {
        return findByPrimaryKey(pk.getId());
    }

	@Transactional
	public List<Picking> findByCriteriaLimit(PickingSearch criteria, int start, int end) throws PickingDaoException {

        if (criteria == null) {
            criteria = new PickingSearch();
        }
        try {
         
            criteria.setTableAlias(this.getTableName()); 
         
            HashMap ret = criteria.getCriteria();
            HashMap param = (HashMap) ret.get("parameter");
        
            param.put("pagestart", start);
            param.put("pageend", end);

            String search = (String) ret.get("search");

            String strQuery = "SELECT Picking_id, NAME, IS_ACTIVE," 
            				 +"CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM Picking";         

            return jdbcTemplate.query(strQuery, this, param); 
        } catch (Exception e) {
            throw new PickingDaoException("Query failed", e);
        }
	}

	@Override
	public List<Picking> findWherePickingCodeEquals(String PickingCode)	throws PickingDaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PickingDetail> findProductLocation(PickingDetail pickingDetail) throws PickingDetailDaoException {
		
		try{
			Map map = new HashMap();
			StringBuffer sb = new StringBuffer();
			
			if (pickingDetail == null){
				pickingDetail = new PickingDetail();
			}
		
			sb.append("select " 
					 +"a.product_id," 
					 +"b.product_code," 
					 +"b.product_name," 
					 +"a.balance, "
					 +"a.unit_code, "
					 +"a.location_code "
			         +"from putaway c " 
			         +"inner join putaway_detail a on c.putaway_id = a.putaway_id "
			         +"inner join product b on a.product_id = b.product_id "
			         +"where c.status_app='SUCCESSFULL' "
			         +"and a.location_code like '%"+"%' "
			         +"and b.product_code like '%"+"%' " 
			         +"and b.product_name like '%"+"%' " 
			         +"order by a.product_id asc");	
					//filter belum berdasarkan product punya corp dan berada pada warehouse?
			return jdbcTemplate.query(sb.toString(),new PickingDetailMap(),map);
		}
		catch(Exception e){
			 throw new PickingDetailDaoException("Query failed", e);
		}
	}

	public List<PickingDetail> findSearchProductLocation(PickingDetail pickingDetail) throws PickingDetailDaoException {
		
		try{
			
			if (pickingDetail == null){
				pickingDetail = new PickingDetail();
			}
			
			String whLocation  = pickingDetail.getWhlocation().getLocationCode().toString();
			String productCode = pickingDetail.getProducts().getProductCode().toString();
			String productName = pickingDetail.getProducts().getProductName();

			Map map = new HashMap();
			map.put("whLocation", whLocation);
			map.put("productCode", productCode);
			map.put("productName", productName);
			
			StringBuffer sb = new StringBuffer();
			
			if(whLocation.equalsIgnoreCase("")){
				whLocation="%";
				sb.append("select " 
						 +"a.product_id," 
						 +"b.product_code," 
						 +"b.product_name," 
						 +"a.balance, "
						 +"a.unit_code, "
						 +"a.location_code "
				         +"from putaway c " 
				         +"inner join putaway_detail a on c.putaway_id = a.putaway_id "
				         +"inner join product b on a.product_id = b.product_id "
				         +"where location_code like '"+whLocation+"' "
				         +"and product_code like '%"+productCode+"%' " 
				         +"and product_name like '%"+productName+"%' " 
				         +"order by a.product_id asc");
			}
			else
			{
				sb.append("select " 
						 +"a.product_id," 
						 +"b.product_code," 
						 +"b.product_name," 
						 +"a.balance, "
						 +"a.unit_code, "
						 +"a.location_code "
				         +"from putaway c " 
				         +"inner join putaway_detail a on c.putaway_id = a.putaway_id "
				         +"inner join product b on a.product_id = b.product_id "
				         +"where location_code = '"+whLocation+"' "
				         +"and product_code like '%"+productCode+"%' " 
				         +"and product_name like '%"+productName+"%' " 
				         +"order by a.product_id asc");		
			}
			
			 return jdbcTemplate.query(sb.toString(),new PickingDetailMap(),map);			
		
		}
		catch(Exception e){
			 throw new PickingDetailDaoException("Query failed", e);
		}
	}

	public List<PickingDetail> findProductPickingQuantity(PickingDetail pickingDetail)	throws PickingDetailDaoException {
		try{
			
			if (pickingDetail == null){
				pickingDetail = new PickingDetail();
			}
			
			String whLocation  = "%";
			String productCode = "%";
			String productName = "%";
			
			SalesOrderDetail sod = new SalesOrderDetail();
			String salesNumber = pickingDetail.getSoDetail().getSo_number();
			/*
			String whLocation  = pickingDetail.getWhlocation().getLocationCode().toString();
			String productCode = pickingDetail.getProducts().getProductCode().toString();
			String productName = pickingDetail.getProducts().getProductName();
		    */
			Map map = new HashMap();
			map.put("whLocation", whLocation);
			map.put("productCode", productCode);
			map.put("productName", productName);
		
			
			StringBuffer sb = new StringBuffer();
			sb.append("select " 
					 +"d.so_number," 
					 +"e.so_date," 
					 +"a.product_id," 
					 +"a.received_date,"
					 +"a.expired_date," 
					 +"b.product_code," 
					 +"b.product_name," 
					 +"a.balance, "
					 +"d.qty_order," 
					 +"a.unit_code, "
					 +"a.location_code "
			         +"from putaway c " 
			         +"inner join putaway_detail a on c.putaway_id = a.putaway_id "
			         +"inner join product b on a.product_id = b.product_id "
			         +"inner join so_detail d on d.product_code = b.product_code "
			         +"inner join salesorder e on d.so_number = e.so_number "
			         +"where d.so_number like '"+salesNumber+"' "
			         +"and b.product_code like '"+productCode+"' " 
			         +"and b.product_name like '"+productName+"' " 
			         +"and a.location_code like '"+whLocation+"' "
			         +"order by a.product_id asc");
			
			 return jdbcTemplate.query(sb.toString(),new PickingProductDetailMap(),map);			
		}
		catch(Exception e){
			 throw new PickingDetailDaoException("Query failed", e);
		}
	}

	@Override
	public List<Picking> findPickingPaging(Picking pi, int page) throws PickingDaoException {
		try {
			String corpId = pi.getCorpId();
        	String whCode = pi.getWhCode();
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
        			+"as (select a.picking_id, a.picking_date, a.status_app, ROW_NUMBER() over (order by picking_date desc) as id from picking a "
        			+"inner join picking_detail b on a.picking_id = b.picking_id where b.corp_id like '"+corpId+"' and b.wh_code like '"+whCode+"' "
        			+"group by a.picking_id, a.picking_date, a.status_app) " 
        			+"select * from PagedResult where id between " 
        			+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 " 
        			+"else @Page end and @PageSize * @Page " 
        			+"order by picking_date desc "); 
        	
        	return jdbcTemplate.query(sb.toString(),new PickingListMap(),map);	
            
        	
        } catch (Exception e) {
            throw new PickingDaoException("Query failed", e);
        }

	}

	@Override
	public List<Picking> findSalesOrderPickingList(Picking p) throws PickingDaoException {
		try {
			String corpId = p.getCorpId();
			String whCode = p.getWhCode();
			Map map = new HashMap();
        	map.put("corpId", corpId);
        	map.put("whCode", whCode);
			
        	StringBuffer sb = new StringBuffer();
        	
        	sb.append("select distinct "
    				+"a.picking_id, a.picking_date, a.so_number, a.so_date, c.remarks from picking a "
    				+"inner join picking_detail b on a.picking_id = b.picking_id "
    				+"inner join salesorder c on a.so_number = c.so_number "
    				+"where status_app='SUCCESSFULL' and b.corp_id like '"+corpId+"' and b.wh_code like '"+whCode+"' "
    				+"order by a.so_date desc ");
       
        	return jdbcTemplate.query(sb.toString(),new SalesOrderPickingListMap(),map);	
            
		} catch (Exception e) {
            throw new PickingDaoException("Query failed", e);
        }
	}

	public List<Picking> findProductDelivery(Picking pic) throws PickingDaoException {
		try{
			
			if (pic == null){
	    		pic = new Picking();
			}
			
			String so_number = pic.getSoNumber();
			String corpId = pic.getCorpId();
			String whCode = pic.getWhCode();
			
			Map map = new HashMap();
			map.put("so_number", so_number);
			map.put("corpId", corpId);
			map.put("whCode", whCode);
			
			StringBuffer sb = new StringBuffer();
			
			sb.append( 
			" with deliver as ( "+
					" select "+ 
					" sum(a.qty_pick) as qty , b.product_id "+
					" from picking c "+   
					" inner join picking_detail a on c.picking_id = a.picking_id "+  
					" inner join product b on a.product_id = b.product_id "+  
					" where c.so_number like '"+so_number+"' "+ 
					" and a.qty_pick <>0 "+
					" and a.corp_id like '"+corpId+"' "+
					" and a.wh_code like '"+whCode+"' "+
					" and c.status_app ='SUCCESSFULL' "+
					" group by b.product_id "+ 
					" ) "+
					" select "+  
					" distinct(a.picking_id), "+ 
					" b.product_id, "+  
					" b.product_code, "+  
					" b.product_name, "+  
					" ( "+ 
					" d.qty "+
					" ) as qty, "+ 
					" a.unit_code, "+ 
					" a.location_code "+  
					" from picking c "+   
					" inner join picking_detail a on c.picking_id = a.picking_id "+  
					" inner join product b on a.product_id = b.product_id "+  
					" inner join deliver d on d.product_id = b.product_id "+
					" where c.so_number like '"+so_number+"' "+ 
					" and a.qty_pick <>0 "+
					" and a.corp_id like '"+corpId+"' "+
					" and a.wh_code like '"+whCode+"' "+
					" and c.status_app ='SUCCESSFULL' ");
			
			return jdbcTemplate.query(sb.toString(),new PickingDetailSuccessMap(),map);
			
		}catch (Exception e) {
            throw new PickingDaoException("Query failed", e);
        }
		
	}
	
	
}

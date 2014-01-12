package com.app.wms.engine.db.dao.spring;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;



import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.app.web.engine.search.SalesOrderSearch;
import com.app.web.engine.search.WarehouseSearch;
import com.app.wms.engine.db.dao.SalesOrderDao;
import com.app.wms.engine.db.dto.SalesOrder;
import com.app.wms.engine.db.dto.PickingDetail;
import com.app.wms.engine.db.dto.SalesOrderDetail;
import com.app.wms.engine.db.dto.SalesOrderPk;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.dto.map.PickingDetailMap;
import com.app.wms.engine.db.dto.map.PickingDetailSuccessMap;
import com.app.wms.engine.db.dto.map.ProductListMap;
import com.app.wms.engine.db.dto.map.SalesOrderDetailListMap;
import com.app.wms.engine.db.dto.map.SalesOrderDtlListMap;
import com.app.wms.engine.db.dto.map.SalesOrderListMap;
import com.app.wms.engine.db.dto.map.SalesOrderPickingListMap;
import com.app.wms.engine.db.exceptions.PickingDetailDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDetailDaoException;
import com.app.wms.engine.db.exceptions.WhDaoException;

public class SalesOrderDaoImpl extends AbstractDAO implements ParameterizedRowMapper<SalesOrder>, SalesOrderDao{

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
     * @return SalesOrderPk
     */
    @Transactional
    public SalesOrderPk insert(SalesOrder dto) {
        jdbcTemplate.update("insert into " + getTableName() + " ( so_number, so_date, created_by, created_date, updated_by, updated_date, delivery_date, delivery_name, delivery_address, remarks ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )", dto.getSo_number(), dto.getSo_date(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate(), dto.getDelivery_date(), dto.getDelivery_name(), dto.getDelivery_address(), dto.getRemarks());
        return dto.createPk();
    }

    /**
     * Updates a single row in the SalesOrder table.
     */
    @Transactional
    public void update(SalesOrderPk pk, SalesOrder dto) throws SalesOrderDaoException {
        jdbcTemplate.update("update " + getTableName() + " set so_number = ?, so_date = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ?, delivery_date = ?, delivery_name = ?, delivery_address = ?, remarks = ?  where so_number = ?", dto.getSo_number(), dto.getSo_date(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate(), dto.getDelivery_date(),dto.getDelivery_name(), dto.getDelivery_address(), dto.getRemarks(), pk.getSo_number());
    }

    /**
     * Deletes a single row in the SalesOrder table.
     */
    @Transactional
    public void delete(SalesOrderPk pk) throws SalesOrderDaoException {
        jdbcTemplate.update("delete from " + getTableName() + " where so_number = ?", pk.getSo_number());
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return SalesOrder
     */
    
    public SalesOrder mapRow(ResultSet rs, int row) throws SQLException {
        SalesOrder dto = new SalesOrder();
       
        dto.setId(rs.getInt(1));
        dto.setSo_number(rs.getString(2));
        dto.setSo_date(rs.getTimestamp(3));
        dto.setCreatedBy(rs.getString(4));
        dto.setCreatedDate(rs.getTimestamp(5));
        dto.setUpdatedBy(rs.getString(6));
        dto.setUpdatedDate(rs.getTimestamp(7));
        dto.setDelivery_date(rs.getTimestamp(8));
        dto.setDelivery_name(rs.getString(9));
        dto.setDelivery_address(rs.getString(10));
        
        return dto;
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "salesorder";
    }

    /**
     * Returns all rows from the SalesOrder table that match the criteria 'so_number = :so_number'.
     */
    @Transactional
    public SalesOrder findByPrimaryKey(String SalesOrderNumber) throws SalesOrderDaoException {
    	try {
            List<SalesOrder> list = jdbcTemplate.query("select ROW_NUMBER() over (order by so_number) as id, so_number, so_date, created_by, created_date, updated_by, updated_date, delivery_date, delivery_name, delivery_address  from " + getTableName() + " where so_number = ?", this, SalesOrderNumber);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new SalesOrderDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the SalesOrder table that match the criteria ''.
     */
    @Transactional
    public List<SalesOrder> findAll() throws SalesOrderDaoException {
        try {
            return jdbcTemplate.query("select "
            						  +" ROW_NUMBER() over (order by so_number asc) as id, so_number, so_date, created_by," 
            						  +"created_date, updated_by, updated_date, delivery_date, delivery_name, delivery_address from " + getTableName() + " order by so_date desc", this);
        } catch (Exception e) {
            throw new SalesOrderDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the SalesOrder table that matches the specified primary-key value.
     */
    @Transactional
    public SalesOrder findByPrimaryKey(SalesOrderPk pk) throws SalesOrderDaoException {
        return findByPrimaryKey(pk.getSo_number());
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
			         +"where location_code like '%"+"%' "
			         +"and product_code like '%"+"%' " 
			         +"and product_name like '%"+"%' " 
			         +"order by a.product_id asc");	
		
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

	public List<PickingDetail> findProductQuantity(PickingDetail pickingDetail)	throws PickingDetailDaoException {
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
			sb.append("select " 
					 +"a.product_id," 
					 +"b.product_code," 
					 +"b.product_name," 
					 +"a.balance, "
					 +"a.location_code "
			         +"from putaway c " 
			         +"inner join putaway_detail a on c.putaway_id = a.putaway_id "
			         +"inner join product b on a.product_id = b.product_id "
			         +"where location_code like '"+whLocation+"' "
			         +"and product_code like '"+productCode+"' " 
			         +"and product_name like '"+productName+"' " 
			         +"order by a.product_id asc");
			
			
			 return jdbcTemplate.query(sb.toString(),new PickingDetailMap(),map);			
		}
		catch(Exception e){
			 throw new PickingDetailDaoException("Query failed", e);
		}
	}

	
	@Transactional
	public List<SalesOrder> findByCriteriaLimit(SalesOrderSearch criteria, int start, int end) throws SalesOrderDaoException {

        if (criteria == null) {
        
            criteria = new SalesOrderSearch();
        }
        try {
         
            criteria.setTableAlias(this.getTableName()); 
         
            HashMap ret = criteria.getCriteria();
            HashMap param = (HashMap) ret.get("parameter");
        
            param.put("pagestart", start);
            param.put("pageend", end);

            String search = (String) ret.get("search");

            String strQuery = "select ROW_NUMBER() over (order by so_number) as id, so_number, so_date, " 
            				 +"created_by, created_date, updated_by, updated_date from salesorder";         

            return jdbcTemplate.query(strQuery, this, param); 
        } catch (Exception e) {
            throw new SalesOrderDaoException("Query failed", e);
        }
	}
	
	 /**
     * Returns all rows from the SalesOrder table that match the criteria ''.
     */
    @Transactional
    public List<SalesOrder> findSalesOrder() throws SalesOrderDaoException {
        try {
            return jdbcTemplate.query("select "
            						  +"ROW_NUMBER() over (order by so_date desc) as id, so_number, so_date, created_by," 
            						  +"created_date, updated_by, updated_date, delivery_date, delivery_name, delivery_address from " + getTableName() + " order by so_date desc", this);
        } catch (Exception e) {
            throw new SalesOrderDaoException("Query failed", e);
        }

    }
    
    /**
     * Returns all rows from the SalesOrder table that match the criteria ''.
     */
    @Transactional
    public List<SalesOrder> findSalesOrderPickList() throws SalesOrderDaoException {
        try {
        	return jdbcTemplate.query("declare @Page int, @PageSize int "
    				+"set @Page = 1; "
    				+"set @PageSize = 15; "
    				+"with PagedResult "
    				+"as (select ROW_NUMBER() over (order by so_date desc) as RowNum, * from salesorder) "
    				    +"select * from PagedResult where RowNum between "
    				+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
    				     +"else @Page end and @PageSize * @Page "
    				+"order by so_date desc ", this);
        } catch (Exception e) {
            throw new SalesOrderDaoException("Query failed", e);
        }

    }
    
    public List<SalesOrder> findSalesOrder(SalesOrder so) throws SalesOrderDaoException{
    try {
    	
    	if (so == null){
    		so = new SalesOrder();
		}
    	
		 Timestamp tsStart = so.getStartDate();
		 Timestamp tsEnd = so.getEndDate();
		 
		 String start = tsStart.toString();
		 String end   = tsEnd.toString();
		
		 /*
		 StringBuffer sb = new StringBuffer();
		 
		 sb.append("select " 
		 		  +"so_number,"
		 		  +"so_date,"
		 		  +"created_by,"
		 		  +"created_date,"
		 		  +"updated_by,"
		 		  +"updated_date from "
		 		  +getTableName() 
		 		  +" where so_date between('"+start+"')and('"+end+"')"
		 		  +" order by so_date desc");
		 */
		
		 return jdbcTemplate.query("declare @Page int, @PageSize int "
					+"set @Page = 1; "
					+"set @PageSize = 15; "
					+"with PagedResult "
					+"as (select ROW_NUMBER() over (order by so_date desc) as RowNum, * from salesorder) "
					    +"select * from PagedResult where so_date between('"+start+"')and('"+end+"') "
					+"order by so_date desc ", this);
		 
         } catch (Exception e) {
             throw new SalesOrderDaoException("Query failed", e);
         }
    }
    
    /**
     * Returns all rows from the SalesOrderPaging table that match the criteria ''.
     */
    @Transactional
    public List<SalesOrder> findSalesOrderPaging(SalesOrder so, int page) throws SalesOrderDaoException {
        try {
        	

        	String corpId = so.getCorpId();
        	String whCode = so.getWhCode();
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
        			+"as (select a.so_number, a.so_date, ROW_NUMBER() over (order by so_date desc) as id from salesorder a "
        			+"inner join so_detail b on a.so_number = b.so_number where b.corp_id like '"+corpId+"' and b.wh_code like '"+whCode+"' "
        			+"group by a.so_number, a.so_date ) " 
        			+"select * from PagedResult where id between " 
        			+"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 " 
        			+"else @Page end and @PageSize * @Page " 
        			+"order by so_date desc ");
        	
        	return jdbcTemplate.query(sb.toString(),new SalesOrderListMap(),map);	
        
        } catch (Exception e) {
            throw new SalesOrderDaoException("Query failed", e);
        }

    }
    
   
    @Transactional
    public List<SalesOrder> findSalesOrderDeliveryProduct(SalesOrder so) throws SalesOrderDaoException {
        try {
        	
        	String corpId = so.getCorpId();
        	String whCode = so.getWhCode();
        	
        	Map map = new HashMap();
        	map.put("corpId", corpId);
        	map.put("whCode", whCode);
        	
        	/*
        	StringBuffer sb = new StringBuffer();
        	sb.append("select ROW_NUMBER() over (order by a.so_number asc) as id,* from salesorder a " 
        			+"inner join so_detail b on a.so_number = b.so_number where a.so_number not in (select b.so_number  from picking b) " 
        			+"and b.corp_id like '"+corpId+"' and b.wh_code like '"+whCode+"' ");
        	*/
        	StringBuffer sb = new StringBuffer();
        	sb.append("select distinct (ROW_NUMBER() over (order by a.so_number asc)) as id," 
        			+"a.so_number, a.so_date, a.created_by, a.created_date, a.updated_by, a.updated_date, a.delivery_date, a.delivery_name, a.delivery_address, a.remarks from salesorder a "
        			+"inner join so_detail b on a.so_number = b.so_number where a.so_number not in (select b.so_number  from picking b) " 
        			+"and b.corp_id like '"+corpId+"' and b.wh_code like '"+whCode+"' group by a.so_number,  a.so_date, a.created_by, a.created_date, a.updated_by, a.updated_date, a.delivery_date, a.delivery_name, a.delivery_address, a.remarks");
        	
        	return jdbcTemplate.query(sb.toString(),new SalesOrderDtlListMap(),map);	
        
        } catch (Exception e) {
            throw new SalesOrderDaoException("Query failed", e);
        }

    }

	@Override
	public List<SalesOrder> findSO(SalesOrder so) throws SalesOrderDaoException {
		// TODO Auto-generated method stub
		try {
        	String soNumber = so.getSo_number();
        	Map map = new HashMap();
        	map.put("soNumber", soNumber);
        	
        	StringBuffer sb = new StringBuffer();
        	
        	sb.append("select ROW_NUMBER() over (order by a.so_number asc) as id,a.so_number, a.so_date, a.created_by, " 
        			+"a.created_date, a.updated_by, a.updated_date, a.delivery_date, a.delivery_name, " 
        			+"a.delivery_address, a.remarks from salesorder a where a.so_number like '"+soNumber+"' "); 
        	
        	return jdbcTemplate.query(sb.toString(),new SalesOrderDetailListMap(),map);	
        
        } catch (Exception e) {
            throw new SalesOrderDaoException("Query failed", e);
        }
	}
	
	@Transactional
    public List<SalesOrder> findSONumberOnDeliveryOrder(SalesOrder so) throws SalesOrderDaoException {
        try {

        	String corpId = so.getCorpId();
        	String whCode = so.getWhCode();
        	
        	Map map = new HashMap();
        	map.put("corpId", corpId);
        	map.put("whCode", whCode);
        	
        	
        	StringBuffer sb = new StringBuffer();
        	sb.append("select distinct (ROW_NUMBER() over (order by a.so_number asc)) as id,a.so_number, a.so_date, a.created_by," 
        			+"a.created_date, a.updated_by, a.updated_date, a.delivery_date, a.delivery_name," 
        			+"a.delivery_address, a.remarks from salesorder a " 
        			+"inner join so_detail b on a.so_number = b.so_number " 
        			+"inner join picking c on a.so_number = c.so_number " 
        			+"left join do_detail d on a.so_number = d.so_number " 
        			+"where c.status_app='SUCCESSFULL' " 
        			+"and a.so_number not in (select d.so_number from do_detail d) " 
        			+"and b.corp_id like '"+corpId+"' and b.wh_code like '"+whCode+"' group by a.so_number, a.so_date, a.created_by, a.created_date, a.updated_by, a.updated_date, a.delivery_date,a.delivery_name, a.delivery_address, a.remarks ");
        	
        	return jdbcTemplate.query(sb.toString(),new SalesOrderDetailListMap(),map);	
        
        } catch (Exception e) {
            throw new SalesOrderDaoException("Query failed", e);
        }

    }
	
}

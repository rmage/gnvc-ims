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

import com.app.wms.engine.db.dao.PickingDetailDao;
import com.app.wms.engine.db.dto.Corporate;
import com.app.wms.engine.db.dto.Picking;
import com.app.wms.engine.db.dto.PickingDetailPk;
import com.app.wms.engine.db.dto.PickingPk;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.WarehouseLocation;
import com.app.wms.engine.db.dto.Wh;
import com.app.web.engine.search.PickingDetailSearch;
import com.app.web.engine.search.PickingSearch;
import com.app.wms.engine.db.dto.PickingDetail;
import com.app.wms.engine.db.exceptions.KittingDetailDaoException;
import com.app.wms.engine.db.exceptions.PickingDaoException;
import com.app.wms.engine.db.exceptions.PickingDetailDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDetailDaoException;

public class PickingDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<PickingDetail>, PickingDetailDao
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
	 * @return PickingDetailPk
	 */
	public PickingDetailPk insert(PickingDetail dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( picking_id, product_id, product_code, product_name, received_date, expired_date, unit_code, qty_order, qty_pick, balance, user_id, corp_id, location_code, wh_code, lotid ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
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
		su.compile();
		su.update( new Object[] { dto.getPickingId(),dto.getProducts().getProductId(),dto.getProducts().getProductCode(),dto.getProducts().getProductName(),dto.getReceivedDate(),dto.getExpiredDate(),dto.getUnitCode(),dto.getQtyOrder(),dto.getQtyPick(),dto.getBalance(),dto.getUserId(),dto.getCorp().getId(),dto.getWhlocation().getLocationCode(),dto.getWh().getWhCode(),dto.getLotId()} );
		return dto.createPk();
	}

   
    /** 
	 * Updates a single row in the picking table.
	 */
	public void update(PickingDetail dto) throws PickingDetailDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET picking_id = ?, product_id = ?, product_code = ?, product_name = ?, received_date = ?, expired_date = ?, unit_code = ?, qty_order = ?, qty_pick = ?, balance = ?, user_id = ?, corp_id = ?, location_code = ?, wh_code = ?, lotid = ? WHERE picking_id = ? AND product_id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
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
		//su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getPickingId(),dto.getProducts().getProductId(),dto.getProducts().getProductCode(),dto.getProducts().getProductName(),dto.getReceivedDate(),dto.getExpiredDate(),dto.getUnitCode(),dto.getQtyOrder(),dto.getQtyPick(),dto.getBalance(),dto.getUserId(),dto.getCorp().getId(),dto.getWhlocation().getLocationCode(),dto.getWh().getWhCode(),dto.getLotId(),dto.getProducts().getProductId()} );
		
	}

    /**
     * Deletes a single row in the PickingDetail table.
     */
    @Transactional
    public void delete(PickingDetailPk pk) throws PickingDetailDaoException {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE Picking_id = ?", pk.getId());
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return PickingDetail
     */
    
    public PickingDetail mapRow(ResultSet rs, int row) throws SQLException {
        PickingDetail dto = new PickingDetail();
        Product pro = new Product();
        Corporate corp = new Corporate();
        WarehouseLocation loc = new WarehouseLocation();
        Wh wh = new Wh();
      
        dto.setPickingId(rs.getString(1));
        pro.setProductId(rs.getString(2));
        pro.setProductCode(rs.getString(3));
        pro.setProductName(rs.getString(4));
        dto.setReceivedDate(rs.getTimestamp(5));
        dto.setExpiredDate(rs.getTimestamp(6));
        dto.setUnitCode(rs.getString(7));
        dto.setQtyOrder(rs.getInt(8));
        dto.setQtyPick(rs.getInt(9));
        dto.setBalance(rs.getInt(10));
        dto.setUserId(rs.getString(11));
        corp.setId(rs.getString(12));
        loc.setLocationCode(rs.getString(13));
        wh.setWhCode(rs.getString(14));
        dto.setProducts(pro);
        dto.setCorp(corp);
        dto.setWhlocation(loc);
        dto.setWh(wh);
        dto.setLotId(rs.getString(15));
      
        return dto;
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "picking_detail";
    }

    /**
     * Returns all rows from the PickingDetail table that match the criteria 'picking_id = :picking_id'.
     */
    @Transactional
    public PickingDetail findByPrimaryKey(String Pickingid) throws PickingDetailDaoException {
        try {
            List<PickingDetail> list = jdbcTemplate.query("select picking_id, product_id, product_code, product_name, received_date," +
            		"expired_date, unit_code, qty_order, qty_pick, balance, user_id, corp_id, location_code, wh_code, lotid from " + getTableName() + " where picking_id = ?", this, Pickingid);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new PickingDetailDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the PickingDetail table that match the criteria ''.
     */
    @Transactional
    public List<PickingDetail> findAll() throws PickingDetailDaoException {
        try {
            return jdbcTemplate.query("select "
            						  +"picking_id, product_id, product_code, product_name, received_date," 
            						  +"expired_date, unit_code, qty_order, qty_pick, balance, user_id, corp_id, location_code, wh_code, lotid from " + getTableName() + " order by picking_id", this);
        } catch (Exception e) {
            throw new PickingDetailDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the PickingDetail table that match the criteria 'Picking_id = :Pickingid'.
     */
    @Transactional
    public List<PickingDetail> findWherePickingidEquals(String Pickingid) throws PickingDetailDaoException {
        try {
            return jdbcTemplate.query("SELECT Picking_id, NAME FROM " + getTableName() + " WHERE Picking_id = ? ORDER BY Picking_id", this, Pickingid);
        } catch (Exception e) {
            throw new PickingDetailDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the PickingDetail table that match the criteria 'NAME = :name'.
     */
    @Transactional
    public List<PickingDetail> findWhereNameEquals(String name) throws PickingDetailDaoException {
        try {
            return jdbcTemplate.query("SELECT Picking_id, NAME FROM " + getTableName() + " PickingERE NAME = ? ORDER BY NAME", this, name);
        } catch (Exception e) {
            throw new PickingDetailDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the PickingDetail table that matches the specified primary-key value.
     */
    @Transactional
    public PickingDetail findByPrimaryKey(PickingDetailPk pk) throws PickingDetailDaoException {
        return findByPrimaryKey(pk.getId());
    }

	@Transactional
	public List<PickingDetail> findByCriteriaLimit(PickingDetailSearch criteria, int start, int end) throws PickingDetailDaoException {

        if (criteria == null) {
            criteria = new PickingDetailSearch();
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
            throw new PickingDetailDaoException("Query failed", e);
        }
	}

	public List<PickingDetail> findWherePickingIdEquals(String PickingId) throws PickingDetailDaoException {
		try {
            return jdbcTemplate.query("select picking_id, product_id, product_code, product_name,"
            						  +"received_date, expired_date, unit_code, qty_order," 
            						  +"qty_pick, balance, user_id, corp_id, location_code, wh_code, lotid " +
            						  "from " + getTableName() + " " +
            						  "where picking_id = ? and qty_pick <> 0 " +
            						  "order by lotid asc ", this, PickingId);
        } catch (Exception e) {
            throw new PickingDetailDaoException("Query failed", e);
        }
	}

	@Transactional
	public List<PickingDetail> findPickingDetailList(String salesOrderNo) throws PickingDetailDaoException {
		try {
	       	 String so = salesOrderNo;
	       	 StringBuffer sb = new StringBuffer();
	      
		     sb.append("select " 
			 		  +"d.picking_id, d.product_id, d.product_code, d.product_name, d.received_date, d.expired_date, " 
			 		  +"d.unit_code, d.qty_order, d.qty_pick, d.balance, d.user_id, d.corp_id, d.location_code, d.wh_code, d.lotid "
			 		  +"from picking_detail d "
			 		  +"inner join picking a "
			 		  +"on a.picking_id = d.picking_id "
			 		  +"where so_number like '%"+so+"%' "
			 		  +"and a.status_app='SUCCESSFULL' "
			 		  +"and d.product_id not in(select k.product_id from kitting_detail k where so_number like '%"+so+"%') "
			 		  +"and d.product_code not in(select k.product_code from kitting_detail k where so_number like '%"+so+"%') "
			 		  +"and d.product_name not in(select k.product_name from kitting_detail k where so_number like '%"+so+"%') ");

	       	 return jdbcTemplate.query(sb.toString(), this);
	       } catch (Exception e) {
	           throw new PickingDetailDaoException("Query failed", e);
	       }
	}

	@Override
	public List<PickingDetail> findDetail(PickingDetail pid) throws PickingDetailDaoException {
		try {
       	 String pickingNo = pid.getPickingId();
       	 StringBuffer sb = new StringBuffer();

       	 sb.append("select " 
	    			 +"picking_id, product_id, product_code, product_name,"
	    			 +"received_date, expired_date, unit_code, qty_order,"
	    			 +"qty_pick, balance, user_id, corp_id, location_code, wh_code, lotid "
	    			 +"from picking_detail "
	    			 +"where picking_id like '"+pickingNo+"' and qty_pick <> 0 " 
	    			 +"group by "
					+"picking_id, product_id, product_code, product_name, "
					+"received_date, expired_date, unit_code, qty_order, "
					+"qty_pick, balance, user_id, corp_id, location_code, wh_code, lotid");

       	 
       	 return jdbcTemplate.query(sb.toString(), this);
       } catch (Exception e) {
           throw new PickingDetailDaoException("Query failed", e);
       }
	}

	
}

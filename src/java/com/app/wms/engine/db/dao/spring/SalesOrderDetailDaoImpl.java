package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import com.app.wms.engine.db.dao.SalesOrderDetailDao;
import com.app.wms.engine.db.dto.Bundle;
import com.app.wms.engine.db.dto.Corporate;
import com.app.wms.engine.db.dto.Picking;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.dto.SalesOrderDetail;
import com.app.wms.engine.db.dto.SalesOrderDetailPk;
import com.app.wms.engine.db.dto.UnitCode;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.dto.map.PickingDetailSuccessMap;
import com.app.wms.engine.db.dto.map.PutawayDetailBalanceMap;
import com.app.wms.engine.db.dto.map.PutawayDetailMap;
import com.app.wms.engine.db.exceptions.PickingDaoException;
import com.app.wms.engine.db.exceptions.PutawayDetailDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDetailDaoException;

public class SalesOrderDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<SalesOrderDetail>, SalesOrderDetailDao
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
     * @param dso
     * @return SalesOrderDetailPk
     */
    @Transactional
    public SalesOrderDetailPk insert(SalesOrderDetail dso) {
    	
        jdbcTemplate.update("insert into " + getTableName() + " (so_number, product_id, product_code, product_name, bundle_code, bundle_name, unit_code, qty_order, user_id, corp_id, wh_code) values (? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", dso.getSo_number(), dso.getProduct().getProductId(), dso.getProduct().getProductCode(), dso.getProduct().getProductName(), dso.getBundle().getBundleCode(), dso.getBundle().getBundleName(), dso.getUnitCode().getName(), dso.getQuantitySO(), dso.getUser().getUserId(), dso.getCorp().getId(), dso.getWh().getWhCode());
        return dso.createPk();
    }

    /**
     * Updates a single row in the SalesOrderDetail table.
     */
    @Transactional
    public void update(SalesOrderDetailPk pk, SalesOrderDetail dso) throws SalesOrderDetailDaoException {
        jdbcTemplate.update("update " + getTableName() + " set so_number = ?, product_id = ?, product_code = ?, product_name = ?, bundle_code = ?, bundle_name = ?, unit_code = ?, qty_order = ?, user_id = ?, corp_id = ?, wh_code = ? where so_number = ?", dso.getSo_number(), dso.getProduct().getProductId(), dso.getProduct().getProductCode(), dso.getProduct().getProductName(), dso.getBundle().getBundleCode(), dso.getBundle().getBundleName(), dso.getUnitCode().getName(), dso.getQuantitySO(), dso.getUser().getUserId(), dso.getCorp().getId(), dso.getWh().getWhCode(), pk.getSo_number());
    }
    
   

    /**
     * Deletes a single row in the SalesOrderDetail table.
     */
    @Transactional
    public void delete(SalesOrderDetailPk pk) throws SalesOrderDetailDaoException {
        jdbcTemplate.update("delete from " + getTableName() + " where so_number = ?", pk.getSo_number());
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return SalesOrderDetail
     */
    
    public SalesOrderDetail mapRow(ResultSet rs, int row) throws SQLException {
        SalesOrderDetail dso = new SalesOrderDetail();
        Product          pro = new Product();
        Bundle           bun = new Bundle(); 
        UnitCode         uni = new UnitCode();
        User             usr = new User();
        Corporate 		 crp = new Corporate();
        Wh				 wh  = new Wh();
      
        dso.setSo_number(rs.getString(1));
        pro.setProductId(rs.getString(2));
        pro.setProductCode(rs.getString(3));
        pro.setProductName(rs.getString(4));
        bun.setBundleCode(rs.getString(5));
        bun.setBundleName(rs.getString(6));
        dso.setSku(rs.getString(7));
        dso.setQuantitySO(rs.getInt(8));
        usr.setUserId(rs.getString(9));
        crp.setId(rs.getString(10));
        wh.setWhCode(rs.getString(11));
        dso.setProduct(pro);
        dso.setBundle(bun);
       // dso.setUnitCode(uni);
        dso.setUser(usr);
        dso.setCorp(crp);
        dso.setWh(wh);
        
        return dso;
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "so_detail";
    }

    /**
     * Returns all rows from the SalesOrderDetail table that match the criteria 'SalesOrderDetail_id = :SalesOrderid'.
     */
    @Transactional
    public SalesOrderDetail findByPrimaryKey(String SalesOrderNumber) throws SalesOrderDetailDaoException {
        try {
            List<SalesOrderDetail> list = jdbcTemplate.query("select so_number,product_id,product_code,product_name,bundle_code,bundle_name,unit_code,qty_order,user_id, corp_id, wh_code from " + getTableName() + " where so_number = ?", this, SalesOrderNumber);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new SalesOrderDetailDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the SalesOrder table that match the criteria ''.
     */
    @Transactional
    public List<SalesOrderDetail> findAll() throws SalesOrderDetailDaoException {
        try {
            return jdbcTemplate.query("select "
            						  +"so_number, product_id, product_name, bundle_code, bundle_name, " 
            				          +"unit_code, qty_order, user_id, "
            						  +"corp_id, wh_code from " + getTableName() + " order by so_number", this);
        } catch (Exception e) {
            throw new SalesOrderDetailDaoException("Query failed", e);
        }

    }
    
    
    /**
     * Returns all rows from the SalesOrder table that match the criteria ''.
     */
    @Transactional
    public List<SalesOrderDetail> findDetail(SalesOrderDetail sod) throws SalesOrderDetailDaoException {
        try {
        	
        	System.out.println("sod ="+sod);
        	 String soNumber = sod.getSo_number();
        	 String corpId = sod.getCorpId();
        	 String whCode = sod.getWhCode();
        	 
        	 StringBuffer sb = new StringBuffer();
        	 
	    	 sb.append("select " 
		 		  +"so_number,"
		 		  +"product_id,"
		 		  +"product_code,"
		 		  +"product_name,"
		 		  +"bundle_code,"
		 		  +"bundle_name,"
		 		  +"unit_code,"
		 		  +"qty_order,"
		 		  +"user_id,"
		 		  +"corp_id,"
		 		  +"wh_code from "
		 		  +"so_detail "
		 		  +"where corp_id like '"+corpId+"' "
		 		  +"and wh_code like '"+whCode+"' "
		 		  +"and so_number like '"+soNumber+"' order by bundle_code");

        	 return jdbcTemplate.query(sb.toString(), this);
        } catch (Exception e) {
            throw new SalesOrderDetailDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the SalesOrderDetail table that match the criteria 'SalesOrder_id = :SalesOrderid'.
     */
    @Transactional
    public List<SalesOrderDetail> findWhereSalesOrderDetailidEquals(String SalesOrderid) throws SalesOrderDetailDaoException {
        try {
            return jdbcTemplate.query("SELECT SalesOrder_id, NAME FROM " + getTableName() + " WHERE SalesOrder_id = ? ORDER BY SalesOrder_id", this, SalesOrderid);
        } catch (Exception e) {
            throw new SalesOrderDetailDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the SalesOrderDetail table that match the criteria 'NAME = :name'.
     */
    @Transactional
    public List<SalesOrderDetail> findWhereNameEquals(String name) throws SalesOrderDetailDaoException {
        try {
            return jdbcTemplate.query("SELECT SalesOrder_id, NAME FROM " + getTableName() + " SalesOrderERE NAME = ? ORDER BY NAME", this, name);
        } catch (Exception e) {
            throw new SalesOrderDetailDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the SalesOrderDetail table that matches the specified primary-key value.
     */
    @Transactional
    public SalesOrderDetail findByPrimaryKey(SalesOrderDetailPk pk) throws SalesOrderDetailDaoException {
        return findByPrimaryKey(pk.getSo_number());
    }

	@Override
	public List<PutawayDetail> findWhereProductCodeBalanceEquals(PutawayDetail pud) throws PutawayDetailDaoException {
		try{
			if (pud == null){
				pud = new PutawayDetail();
			}
			
			String productCode = pud.getProductCode();
			
			Map map = new HashMap();
			map.put("productCode", productCode);
			
			StringBuffer sb = new StringBuffer();
		
			sb.append("select " 
					 +"a.location_code, a.product_code, " 
					 +"c.product_name, MAX(a.balance) AS balance, " 
					 +"MAX(a.id) AS id " 
			         +"from putaway_detail a " 
			         +"inner join putaway b on a.putaway_id = b.putaway_id "
			         +"inner join product c on a.product_id = c.product_id "
			         +"where b.status_app='SUCCESSFULL' "
			         +"and c.product_code like '"+productCode+"' GROUP BY a.location_code,a.product_code,c.product_name"); 
			        
			return jdbcTemplate.query(sb.toString(),new PutawayDetailBalanceMap(),map);
		}
		catch(Exception e){
			 throw new PutawayDetailDaoException("Query failed", e);
		}
	}
    
}

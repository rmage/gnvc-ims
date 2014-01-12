package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.app.web.engine.search.WarehouseSearch;
import com.app.wms.engine.db.dao.PurchaseDao;
import com.app.wms.engine.db.dto.Purchase;
import com.app.wms.engine.db.dto.PurchasePk;
import com.app.wms.engine.db.exceptions.PurchaseDaoException;


public class PurchaseDaoImp extends AbstractDAO implements ParameterizedRowMapper<Purchase>, PurchaseDao 
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
     * @return PurchasePk
     */
    @Transactional
    public PurchasePk insert(Purchase dto) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " ( Purchase_CODE, NAME ) VALUES ( ?, ? )", dto.getName());
        return null;
    }

    /**
     * Updates a single row in the Purchase table.
     */
    @Transactional
    public void update(PurchasePk pk, Purchase dto) throws PurchaseDaoException {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET Purchase_CODE = ?, NAME = ? PurchaseERE Purchase_CODE = ?", dto.getName(), pk);
    }

    /**
     * Deletes a single row in the Purchase table.
     */
    @Transactional
    public void delete(PurchasePk pk) throws PurchaseDaoException {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " PurchaseERE Purchase_CODE = ?", pk);
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return Purchase
     */
    public Purchase mapRow(ResultSet rs, int row) throws SQLException {
        Purchase dto = new Purchase();
       // dto.setPurchaseCode(rs.getString(1));
        dto.setName(rs.getString(2));
        dto.setIsActive(rs.getString(3));

        return dto;
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "Purchase";
    }

    /**
     * Returns all rows from the Purchase table that match the criteria 'Purchase_CODE = :PurchaseCode'.
     */
    @Transactional
    public Purchase findByPrimaryKey(String PurchaseCode) throws PurchaseDaoException {
        try {
            List<Purchase> list = jdbcTemplate.query("SELECT Purchase_CODE, NAME FROM " + getTableName() + " PurchaseERE Purchase_CODE = ?", this, PurchaseCode);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new PurchaseDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the Purchase table that match the criteria ''.
     */
    @Transactional
    public List<Purchase> findAll() throws PurchaseDaoException {
        try {
            return jdbcTemplate.query("SELECT Purchase_CODE, NAME, IS_ACTIVE," 
            				          +"CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " ORDER BY Purchase_CODE", this);
        } catch (Exception e) {
            throw new PurchaseDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the Purchase table that match the criteria 'Purchase_CODE = :PurchaseCode'.
     */
    @Transactional
    public List<Purchase> findPurchaseerePurchaseCodeEquals(String PurchaseCode) throws PurchaseDaoException {
        try {
            return jdbcTemplate.query("SELECT Purchase_CODE, NAME FROM " + getTableName() + " PurchaseERE Purchase_CODE = ? ORDER BY Purchase_CODE", this, PurchaseCode);
        } catch (Exception e) {
            throw new PurchaseDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the Purchase table that match the criteria 'NAME = :name'.
     */
    @Transactional
    public List<Purchase> findPurchaseereNameEquals(String name) throws PurchaseDaoException {
        try {
            return jdbcTemplate.query("SELECT Purchase_CODE, NAME FROM " + getTableName() + " PurchaseERE NAME = ? ORDER BY NAME", this, name);
        } catch (Exception e) {
            throw new PurchaseDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the Purchase table that matches the specified primary-key value.
     */
    @Transactional
    public Purchase findByPrimaryKey(PurchasePk pk) throws PurchaseDaoException {
        return findByPrimaryKey(pk);
    }

	@Transactional
	public List<Purchase> findByCriteriaLimit(WarehouseSearch criteria, int start, int end) throws PurchaseDaoException {

        if (criteria == null) {
        
            criteria = new WarehouseSearch();
        }
        try {
         
            criteria.setTableAlias(this.getTableName()); 
         
            HashMap ret = criteria.getCriteria();
            HashMap param = (HashMap) ret.get("parameter");
        
            param.put("pagestart", start);
            param.put("pageend", end);

            String search = (String) ret.get("search");

            String strQuery = "SELECT Purchase_CODE, NAME, IS_ACTIVE," 
            				 +"CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM Purchase";         
//            		
//                    + "* FROM "
//                    + "( SELECT Purchase_CODE, NAME, IS_ACTIVE, "
//                    + "CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, ROWNUM AS ID  "
//                    + " FROM Purchase "
//                    + "PurchaseERE " + search + " ) PurchaseERE ID BETWEEN :pagestart and :pageend";
            
            System.out.println(">search="+search);
            System.out.println(">strQuery="+strQuery);
            return jdbcTemplate.query(strQuery, this, param); 
        } catch (Exception e) {
            throw new PurchaseDaoException("Query failed", e);
        }
	}

	
    


}

package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import com.app.web.engine.search.WarehouseLocationSearch;
import com.app.wms.engine.db.dao.WarehouseLocationDao;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.dto.WarehouseLocation;
import com.app.wms.engine.db.dto.WarehouseLocationPk;
import com.app.wms.engine.db.dto.map.LocationProductDetailMap;
import com.app.wms.engine.db.dto.map.PutawayDetailProductMap;
import com.app.wms.engine.db.exceptions.PutawayDetailDaoException;
import com.app.wms.engine.db.exceptions.WarehouseLocationDaoException;


public class WarehouseLocationDaoImpl extends AbstractDAO implements ParameterizedRowMapper<WarehouseLocation>, WarehouseLocationDao {

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
     * @return WarehouseLocationPk
     */
    @Transactional
    public WarehouseLocationPk insert(WarehouseLocation dto) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " ( WH_CODE, NAME ) VALUES ( ?, ? )", dto.getLocationCode(), dto.getLocationName());
        return dto.createPk();
    }

    /**
     * Updates a single row in the WH table.
     */
    @Transactional
    public void update(WarehouseLocationPk pk, WarehouseLocation dto) throws WarehouseLocationDaoException {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET WH_CODE = ?, NAME = ? WHERE WH_CODE = ?", dto.getLocationCode(), dto.getLocationName(), pk.getId());
    }

    /**
     * Deletes a single row in the WH table.
     */
    @Transactional
    public void delete(WarehouseLocationPk pk) throws WarehouseLocationDaoException {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE WH_CODE = ?", pk.getId());
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return WarehouseLocation
     */
    public WarehouseLocation mapRow(ResultSet rs, int row) throws SQLException {
        WarehouseLocation dto = new WarehouseLocation();
        dto.setLocationCode(rs.getString(3));
        dto.setLocationName(rs.getString(4));
        dto.setLocating_area(rs.getString(8));
        dto.setCreatedBy(rs.getString("created_by"));
        dto.setCreatedDate(rs.getDate("created_date"));
        dto.setIsActive(rs.getString(15));

        return dto;
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "wh_location";
    }

    /**
     * Returns all rows from the WH table that match the criteria 'WH_CODE = :whCode'.
     */
    @Transactional
    public WarehouseLocation findByPrimaryKey(String whCode) throws WarehouseLocationDaoException {
        try {
            List<WarehouseLocation> list = jdbcTemplate.query("SELECT WH_CODE, NAME FROM " + getTableName() + " WHERE WH_CODE = ?", this, whCode);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new WarehouseLocationDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the WH table that match the criteria ''.
     */
    @Transactional
    public List<WarehouseLocation> findAll() throws WarehouseLocationDaoException {
        try {
            return jdbcTemplate.query("select id,location_id,location_code,location_name,location_type,min_product,max_product,"
            						 +"locating_area,locating_zone,location_bay,location_level,location_position,allocation_zone,work_zone,"
            						 +"is_active,is_delete,user_id,corp_id,wh_code,created_by,created_date,updated_by,updated_date FROM " + getTableName() + " ORDER BY location_code", this);
        } catch (Exception e) {
            throw new WarehouseLocationDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the WH table that match the criteria 'WH_CODE = :whCode'.
     */
    @Transactional
    public List<WarehouseLocation> findWarehouseLocationWhereLocationCodeEquals(String whCode) throws WarehouseLocationDaoException {
        try {
            return jdbcTemplate.query("SELECT WH_CODE, NAME FROM " + getTableName() + " WHERE WH_CODE = ? ORDER BY WH_CODE", this, whCode);
        } catch (Exception e) {
            throw new WarehouseLocationDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the WH table that match the criteria 'NAME = :name'.
     */
    @Transactional
    public List<WarehouseLocation> findWarehouseLocationWhereLocationNameEquals(String name) throws WarehouseLocationDaoException {
        try {
            return jdbcTemplate.query("SELECT WH_CODE, NAME FROM " + getTableName() + " WHERE NAME = ? ORDER BY NAME", this, name);
        } catch (Exception e) {
            throw new WarehouseLocationDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the WH table that matches the specified primary-key value.
     */
    @Transactional
    public WarehouseLocation findByPrimaryKey(WarehouseLocationPk pk) throws WarehouseLocationDaoException {
        return findByPrimaryKey(pk.getId());
    }

	@Transactional
	public List<WarehouseLocation> findByCriteriaLimit(WarehouseLocationSearch criteria, int start, int end) throws WarehouseLocationDaoException {

        if (criteria == null) {
        
            criteria = new WarehouseLocationSearch();
        }
        try {
         
            criteria.setTableAlias(this.getTableName()); 
         
            HashMap ret = criteria.getCriteria();
            HashMap param = (HashMap) ret.get("parameter");
        
            param.put("pagestart", start);
            param.put("pageend", end);

            String search = (String) ret.get("search");

            String strQuery = "select id,location_id,location_code,location_name,location_type,min_product,max_product,"
            				 +"locating_area,locating_zone,location_bay,location_level,location_position,allocation_zone,work_zone,"
            				 +"is_active,is_delete,user_id,corp_id,wh_code,created_by,created_date,updated_by,updated_date FROM wh_location";         

            return jdbcTemplate.query(strQuery, this, param); 
        } catch (Exception e) {
            throw new WarehouseLocationDaoException("Query failed", e);
        }
	}

	@Override
	public List<WarehouseLocation> findWhereProductLocation(WarehouseLocation wl) throws WarehouseLocationDaoException {
		try{
			
			if (wl == null){
				wl = new WarehouseLocation();
			}
			String corpId = wl.getCorpId();
			String whCode = wl.getWhCode();
			
			Map map = new HashMap();
			map.put("corpId", corpId);
			map.put("whCode", whCode);
			
			StringBuffer sb = new StringBuffer();
			/*
			sb.append("select b.product_code, b.product_name, b.product_category, c.brand_name, c.product_description " 
			         +"from  wh_location_detail b " 
			         +"inner join wh_location a on a.location_id = b.location_id "
			         +"inner join product c on b.product_id = c.product_id "
			         +"and a.wh_code like '"+whCode+"' "
			         +"and a.corp_id like '"+corpId+"' ");
			*/
			sb.append("select c.product_code, c.product_name, c.product_category, c.brand_name, c.product_description " 
			         +"from product c " 
			         +"where c.wh_code like '"+whCode+"' "
			         +"and c.corp_id like '"+corpId+"' ");
			        
			return jdbcTemplate.query(sb.toString(),new LocationProductDetailMap(),map);
		}
		catch(Exception e){
			 throw new WarehouseLocationDaoException("Query failed", e);
		}
	}

	@Transactional
    public List<WarehouseLocation> findWarehouseLocationWhereLocationAreaEquals(String area) throws WarehouseLocationDaoException {
        try {
            return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE locating_area = ? ORDER BY location_name", this, area);
        } catch (Exception e) {
            throw new WarehouseLocationDaoException("Query failed", e);
        }

    }

}

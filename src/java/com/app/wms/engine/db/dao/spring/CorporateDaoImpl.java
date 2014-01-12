package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import com.app.wms.engine.db.dao.CorporateDao;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import com.app.web.engine.search.CorporateSearch;
import com.app.wms.engine.db.dto.Corporate;
import com.app.wms.engine.db.dto.CorporatePk;
import com.app.wms.engine.db.dto.Picking;
import com.app.wms.engine.db.dto.PickingDetail;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.dto.map.CorporateDetailMap;
import com.app.wms.engine.db.dto.map.PickingDetailMap;
import com.app.wms.engine.db.exceptions.CorporateDaoException;
import com.app.wms.engine.db.exceptions.PickingDaoException;
import com.app.wms.engine.db.exceptions.PickingDetailDaoException;

public class CorporateDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Corporate>, CorporateDao {


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
     * @return CorporatePk
     */
    @Transactional
    public CorporatePk insert(Corporate dto) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " ( ID, NAME ) VALUES ( ?, ? )", dto.getId(), dto.getName());
        return dto.createPk();
    }

    /**
     * Updates a single row in the Corporate table.
     */
    @Transactional
    public void update(CorporatePk pk, Corporate dto) throws CorporateDaoException {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET ID = ?, NAME = ? WHERE ID = ?", dto.getId(), dto.getName(), pk.getId());
    }

    /**
     * Deletes a single row in the Corporate table.
     */
    @Transactional
    public void delete(CorporatePk pk) throws CorporateDaoException {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE ID = ?", pk.getId());
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return Corporate
     */
    public Corporate mapRow(ResultSet rs, int row) throws SQLException {
    	Corporate dto = new Corporate();
    	Wh wh = new Wh();
    	wh.getWhCode();
    	
        dto.setId(rs.getString(1));
        dto.setName(rs.getString(2));
        wh.setWhCode(rs.getString(3));
        dto.setWh(wh);
        dto.setIsActive(rs.getString(14));

        return dto;
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "CORP";
    }

    /**
     * Returns all rows from the Corporate table that match the criteria 'ID = :id'.
     */
    @Transactional
    public Corporate findByPrimaryKey(String id) throws CorporateDaoException {
        try {
            List<Corporate> list = jdbcTemplate.query("SELECT ID, NAME FROM " + getTableName() + " WHERE ID = ?", this, id);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new CorporateDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the Corporate table that match the criteria ''.
     */
    @Transactional
    public List<Corporate> findAll() throws CorporateDaoException {
        try {
            return jdbcTemplate.query(
            		"SELECT CORP_ID, CORP_NAME, WH_CODE, ADDRESS1, ADDRESS2, ADDRESS3, EMAIL, " +
            		"CITY_CODE, ZIPCODE, PHONE1, PHONE2, FAX, PROVINCE_CODE, " 
            		+"IS_ACTIVE, IS_DELETE, " 
            		+"CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName(), this);
        } catch (Exception e) {
            throw new CorporateDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the Corporate table that match the criteria ''.
     */
    @Transactional
    public List<Corporate> findCorporate(String id) throws CorporateDaoException {
        try {
            return jdbcTemplate.query(
            		"SELECT CORP_ID, CORP_NAME, WH_CODE, ADDRESS1, ADDRESS2, ADDRESS3, EMAIL, " +
            		"CITY_CODE, ZIPCODE, PHONE1, PHONE2, FAX, PROVINCE_CODE, " 
            		+"IS_ACTIVE, IS_DELETE, " 
            		+"CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE CORP_ID = ?", this, id);
        } catch (Exception e) {
            throw new CorporateDaoException("Query failed", e);
        }

    }
    
    /**
     * Returns all rows from the Corporate table that match the criteria 'ID = :id'.
     */
    @Transactional
    public List<Corporate> findWhereCorporateIdEquals(String id) throws CorporateDaoException {
        try {
            return jdbcTemplate.query("SELECT ID, NAME FROM " + getTableName() + " WHERE ID = ? ORDER BY ID", this, id);
        } catch (Exception e) {
            throw new CorporateDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the Corporate table that match the criteria 'NAME = :name'.
     */
    @Transactional
    public List<Corporate> findWhereCorporateNameEquals(String name) throws CorporateDaoException {
        try {
            return jdbcTemplate.query("SELECT ID, NAME FROM " + getTableName() + " WHERE NAME = ? ORDER BY NAME", this, name);
        } catch (Exception e) {
            throw new CorporateDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the Corporate table that matches the specified primary-key value.
     */
    @Transactional
    public Corporate findByPrimaryKey(CorporatePk pk) throws CorporateDaoException {
        return findByPrimaryKey(pk.getId());
    }

	@Transactional
	public List<Corporate> findByCriteriaLimit(CorporateSearch criteria, int start, int end) throws CorporateDaoException {

        if (criteria == null) {
        
            System.out.println("Search Corporate with no criteria");
            criteria = new CorporateSearch();
        }
        try {
         
            criteria.setTableAlias(this.getTableName()); 
         
            HashMap ret = criteria.getCriteria();
            HashMap param = (HashMap) ret.get("parameter");
        
            param.put("pagestart", start);
            param.put("pageend", end);

            String search = (String) ret.get("search");

            String strQuery = 
            "select corp_id, corp_name, wh_code, address1, address2, address3, email, city_code, zipcode,"
           +"phone1,phone2,fax,province_code,is_active,is_delete,created_by,created_date,updated_by," 
           +"updated_date from corp";         

            return jdbcTemplate.query(strQuery, this, param); 
        } catch (Exception e) {
            throw new CorporateDaoException("Query failed", e);
        }
	}
	

	public List<Corporate> findCorpWh(Corporate corporate) throws CorporateDaoException {
		
		try{
			
			if (corporate == null){
				corporate = new Corporate();
			}
			
			String corpId = corporate.getId();
			
			Map map = new HashMap();
			map.put("corpId", corpId);
			StringBuffer sb = new StringBuffer();
			
			
			sb.append("select " 
					 +"a.corp_id," 
					 +"b.wh_code " 
					 +"from corp a " 
					 +"inner join wh b "
					 +"on a.corp_id = b.corp_id where a.corp_id like '"+corpId+"'");
			return jdbcTemplate.query(sb.toString(),new CorporateDetailMap(),map);
		}
		catch(Exception e){
			 throw new CorporateDaoException("Query failed", e);
		}
	}

}

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

import com.app.web.engine.search.WarehouseSearch;
import com.app.wms.engine.db.dao.PutawayDao;
import com.app.wms.engine.db.dto.Putaway;
import com.app.wms.engine.db.dto.PutawayPk;
import com.app.wms.engine.db.exceptions.PutawayDaoException;

public class PutawayDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Putaway>, PutawayDao 
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
     * @return PutawayPk
     */
    @Transactional
    public PutawayPk insert(Putaway dto) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " ( Putaway_CODE, NAME ) VALUES ( ?, ? )");
        return dto.createPk();
    }

    /**
     * Updates a single row in the Putaway table.
     */
    @Transactional
    public void update(PutawayPk pk, Putaway dto) throws PutawayDaoException {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET Putaway_CODE = ?, NAME = ? Putaway WHERE Putaway_CODE = ?", pk);
    }

    /**
     * Deletes a single row in the Putaway table.
     */
    @Transactional
    public void delete(PutawayPk pk) throws PutawayDaoException {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " Putaway WHERE Putaway_CODE = ?", pk);
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return Putaway
     */
    public Putaway mapRow(ResultSet rs, int row) throws SQLException {
        Putaway dto = new Putaway();
        //dto.setPutawayCode(rs.getString(1));
        //dto.setName(rs.getString(2));
        //dto.setIsActive(rs.getString(3));
        /* edited by FYA 12 February 2013 */
        dto.setPutawayId(rs.getString(1));
        dto.setPutawayDate(rs.getDate(2));
        dto.setGrNumber(rs.getString(3));
        dto.setGrDate(rs.getDate(4));
        dto.setStatusApp(rs.getString(5));
        dto.setAppDate(rs.getDate(6));
        dto.setCreatedBy(rs.getString(7));
        dto.setCreatedDate(rs.getDate(8));
        dto.setUpdatedBy(rs.getString(9));
        dto.setUpdatedDate(rs.getDate(10));
        
        return dto;
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "Putaway";
    }

    /**
     * Returns all rows from the Putaway table that match the criteria 'Putaway_CODE = :PutawayCode'.
     */
    @Transactional
    public Putaway findByPrimaryKey(String PutawayCode) throws PutawayDaoException {
        try {
            List<Putaway> list = jdbcTemplate.query("SELECT Putaway_CODE, NAME FROM " + getTableName() + " PutawayERE Putaway_CODE = ?", this, PutawayCode);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new PutawayDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the Putaway table that match the criteria ''.
     */
    @Transactional
    public List<Putaway> findAll() throws PutawayDaoException {
        try {
            return jdbcTemplate.query("SELECT Putaway_CODE, NAME, IS_ACTIVE," 
            				          +"CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " ORDER BY Putaway_CODE", this);
        } catch (Exception e) {
            throw new PutawayDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the Putaway table that match the criteria 'Putaway_CODE = :PutawayCode'.
     */
    @Transactional
    public List<Putaway> findPutawayerePutawayCodeEquals(String PutawayCode) throws PutawayDaoException {
        try {
            return jdbcTemplate.query("SELECT Putaway_CODE, NAME FROM " + getTableName() + " PutawayERE Putaway_CODE = ? ORDER BY Putaway_CODE", this, PutawayCode);
        } catch (Exception e) {
            throw new PutawayDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the Putaway table that match the criteria 'NAME = :name'.
     */
    @Transactional
    public List<Putaway> findPutawayereNameEquals(String name) throws PutawayDaoException {
        try {
            return jdbcTemplate.query("SELECT Putaway_CODE, NAME FROM " + getTableName() + " PutawayERE NAME = ? ORDER BY NAME", this, name);
        } catch (Exception e) {
            throw new PutawayDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the Putaway table that matches the specified primary-key value.
     */
    @Transactional
    public Putaway findByPrimaryKey(PutawayPk pk) throws PutawayDaoException {
        return findByPrimaryKey(pk);
    }

	@Transactional
	public List<Putaway> findByCriteriaLimit(WarehouseSearch criteria, int start, int end) throws PutawayDaoException {

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

            String strQuery = "SELECT Putaway_CODE, NAME, IS_ACTIVE," 
            				 +"CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM Putaway";         
//            		
//                    + "* FROM "
//                    + "( SELECT Putaway_CODE, NAME, IS_ACTIVE, "
//                    + "CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, ROWNUM AS ID  "
//                    + " FROM Putaway "
//                    + "PutawayERE " + search + " ) PutawayERE ID BETWEEN :pagestart and :pageend";
            
            System.out.println(">search="+search);
            System.out.println(">strQuery="+strQuery);
            return jdbcTemplate.query(strQuery, this, param); 
        } catch (Exception e) {
            throw new PutawayDaoException("Query failed", e);
        }
	}


}

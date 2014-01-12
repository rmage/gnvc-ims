package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.CityDao;
import com.app.wms.engine.db.dto.City;
import com.app.wms.engine.db.dto.CityPk;
import com.app.wms.engine.db.exceptions.CityDaoException;
import com.app.web.engine.search.CitySearch;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class CityDaoImpl extends AbstractDAO implements ParameterizedRowMapper<City>, CityDao {

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
     * @return CityPk
     */
    @Transactional
    public CityPk insert(City dto) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " ( CITY_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE ) VALUES ( ?, ?, ?, ?, ?, ?, ? )", dto.getCityCode(), dto.getName(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate(), dto.getIsActive());
        return dto.createPk();
    }

    /**
     * Updates a single row in the CITY table.
     */
    @Transactional
    public void update(CityPk pk, City dto) throws CityDaoException {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET CITY_CODE = ?, NAME = ?, CREATED_BY = ?, CREATED_DATE = ?, UPDATED_BY = ?, UPDATED_DATE = ?, IS_ACTIVE=? WHERE CITY_CODE = ?", dto.getCityCode(), dto.getName(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate(), dto.getIsActive(), pk.getCityCode());
    }

    /**
     * Deletes a single row in the CITY table.
     */
    @Transactional
    public void delete(CityPk pk) throws CityDaoException {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE CITY_CODE = ?", pk.getCityCode());
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return City
     */
    public City mapRow(ResultSet rs, int row) throws SQLException {
        City dto = new City();
        dto.setCityCode(rs.getString(1));
        dto.setName(rs.getString(2));
        dto.setCreatedBy(rs.getString(3));
        dto.setCreatedDate(rs.getTimestamp(4));
        dto.setUpdatedBy(rs.getString(5));
        dto.setUpdatedDate(rs.getTimestamp(6));
        dto.setIsActive(rs.getString(7));
        return dto;
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "CITY";
    }

    /**
     * Returns all rows from the CITY table that match the criteria 'CITY_CODE = :cityCode'.
     */
    @Transactional
    public City findByPrimaryKey(String cityCode) throws CityDaoException {
        try {
            List<City> list = jdbcTemplate.query("SELECT CITY_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE,IS_ACTIVE FROM " + getTableName() + " WHERE CITY_CODE = ?", this, cityCode);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new CityDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the CITY table that match the criteria ''.
     */
    @Transactional
    public List<City> findAll() throws CityDaoException {
        try {
            return jdbcTemplate.query("SELECT CITY_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " ORDER BY CITY_CODE", this);
        } catch (Exception e) {
            throw new CityDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the CITY table that match the criteria 'CITY_CODE = :cityCode'.
     */
    @Transactional
    public List<City> findWhereCityCodeEquals(String cityCode) throws CityDaoException {
        try {
            return jdbcTemplate.query("SELECT CITY_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " WHERE CITY_CODE = ? ORDER BY CITY_CODE", this, cityCode);
        } catch (Exception e) {
            throw new CityDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the CITY table that match the criteria 'NAME = :name'.
     */
    @Transactional
    public List<City> findWhereNameEquals(String name) throws CityDaoException {
        try {
            return jdbcTemplate.query("SELECT CITY_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " WHERE NAME = ? ORDER BY NAME", this, name);
        } catch (Exception e) {
            throw new CityDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the CITY table that match the criteria 'CREATED_BY = :createdBy'.
     */
    @Transactional
    public List<City> findWhereCreatedByEquals(BigDecimal createdBy) throws CityDaoException {
        try {
            return jdbcTemplate.query("SELECT CITY_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " WHERE CREATED_BY = ? ORDER BY CREATED_BY", this, createdBy);
        } catch (Exception e) {
            throw new CityDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the CITY table that match the criteria 'CREATED_DATE = :createdDate'.
     */
    @Transactional
    public List<City> findWhereCreatedDateEquals(Date createdDate) throws CityDaoException {
        try {
            return jdbcTemplate.query("SELECT CITY_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " WHERE CREATED_DATE = ? ORDER BY CREATED_DATE", this, createdDate);
        } catch (Exception e) {
            throw new CityDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the CITY table that match the criteria 'UPDATED_BY = :updatedBy'.
     */
    @Transactional
    public List<City> findWhereUpdatedByEquals(BigDecimal updatedBy) throws CityDaoException {
        try {
            return jdbcTemplate.query("SELECT CITY_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " WHERE UPDATED_BY = ? ORDER BY UPDATED_BY", this, updatedBy);
        } catch (Exception e) {
            throw new CityDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the CITY table that match the criteria 'UPDATED_DATE = :updatedDate'.
     */
    @Transactional
    public List<City> findWhereUpdatedDateEquals(Date updatedDate) throws CityDaoException {
        try {
            return jdbcTemplate.query("SELECT CITY_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " WHERE UPDATED_DATE = ? ORDER BY UPDATED_DATE", this, updatedDate);
        } catch (Exception e) {
            throw new CityDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the CITY table that matches the specified primary-key value.
     */
    @Transactional
    public City findByPrimaryKey(CityPk pk) throws CityDaoException {
        return findByPrimaryKey(pk.getCityCode());
    }

    @Transactional
    public List<City> findLimit(int page, int paging) throws CityDaoException {
        try {
            return jdbcTemplate.query("SELECT CITY_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName(), this);
        } catch (Exception e) {
            throw new CityDaoException("Query Failed", e);
        }
    }

    @Transactional
    public List<City> findByCriteriaLimit(CitySearch criteria, int start, int end) throws CityDaoException {
        // jaga kalau criteria nya null
        if (criteria == null) {
            // bikin dummy criteria
            System.out.println("Search City with no criteria");
            criteria = new CitySearch(); // tanpa searching apa apa, tapi biar konsisten
        }
        try {
            // set table name alias nya, mungkin beda letak beda alias
            criteria.setTableAlias(this.getTableName()); // ini sesuaikand di query, misal didalam sana pake alias table P misal nya, ya diganti P (bukan nama table lengkap nya)
            // get searc criteria sekalian parameter nya
            // remember, kita pake NAMED parameter, bukan position based yang pake ?

            HashMap ret = criteria.getCriteria();
            HashMap param = (HashMap) ret.get("parameter");
            // tambahkan parameter paging
            param.put("pagestart", start);
            param.put("pageend", end);

            String search = (String) ret.get("search");

            String strQuery = "SELECT "
                    + "* FROM "
                    + "( SELECT CITY_CODE, NAME, "
                    + "CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE, ROWNUM AS ID  "
                    + " FROM CITY "
                    + "WHERE " + search + " ) WHERE ID BETWEEN :pagestart and :pageend";
            System.out.println(strQuery);
            return jdbcTemplate.query(strQuery, this, param); //page, paging);
        } catch (Exception e) {
            throw new CityDaoException("Query failed", e);
        }
    }

    @Transactional
    public int countTotalRows() throws CityDaoException {
        return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM " + getTableName());
    }
}

package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.RegionDao;
import com.app.wms.engine.db.dto.Region;
import com.app.wms.engine.db.dto.RegionPk;
import com.app.wms.engine.db.exceptions.RegionDaoException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class RegionDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Region>, RegionDao {

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
     * @return RegionPk
     */
    @Transactional
    public RegionPk insert(Region dto) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " ( REGION_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE ) VALUES ( ?, ?, ?, ?, ?, ?, ? )", dto.getRegionCode(), dto.getName(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate(), dto.getIsActive());
        return dto.createPk();
    }

    /**
     * Updates a single row in the REGION table.
     */
    @Transactional
    public void update(RegionPk pk, Region dto) throws RegionDaoException {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET REGION_CODE = ?, NAME = ?, CREATED_BY = ?, CREATED_DATE = ?, UPDATED_BY = ?, UPDATED_DATE = ?, IS_ACTIVE=? WHERE REGION_CODE = ?", dto.getRegionCode(), dto.getName(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate(), dto.getIsActive(), pk.getRegionCode());
    }

    /**
     * Deletes a single row in the REGION table.
     */
    @Transactional
    public void delete(RegionPk pk) throws RegionDaoException {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE REGION_CODE = ?", pk.getRegionCode());
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return Region
     */
    public Region mapRow(ResultSet rs, int row) throws SQLException {
        Region dto = new Region();
        dto.setRegionCode(rs.getString(1));
        dto.setName(rs.getString(2));
        dto.setCreatedBy(rs.getBigDecimal(3));
        dto.setCreatedDate(rs.getTimestamp(4));
        dto.setUpdatedBy(rs.getBigDecimal(5));
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
        return "REGION";
    }

    /**
     * Returns all rows from the REGION table that match the criteria 'REGION_CODE = :regionCode'.
     */
    @Transactional
    public Region findByPrimaryKey(String regionCode) throws RegionDaoException {
        try {
            List<Region> list = jdbcTemplate.query("SELECT REGION_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE,IS_ACTIVE FROM " + getTableName() + " WHERE REGION_CODE = ?", this, regionCode);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new RegionDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the REGION table that match the criteria ''.
     */
    @Transactional
    public List<Region> findAll() throws RegionDaoException {
        try {
            return jdbcTemplate.query("SELECT REGION_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " ORDER BY REGION_CODE", this);
        } catch (Exception e) {
            throw new RegionDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the REGION table that match the criteria 'REGION_CODE = :regionCode'.
     */
    @Transactional
    public List<Region> findWhereRegionCodeEquals(String regionCode) throws RegionDaoException {
        try {
            return jdbcTemplate.query("SELECT REGION_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " WHERE REGION_CODE = ? ORDER BY REGION_CODE", this, regionCode);
        } catch (Exception e) {
            throw new RegionDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the REGION table that match the criteria 'NAME = :name'.
     */
    @Transactional
    public List<Region> findWhereNameEquals(String name) throws RegionDaoException {
        try {
            return jdbcTemplate.query("SELECT REGION_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " WHERE NAME = ? ORDER BY NAME", this, name);
        } catch (Exception e) {
            throw new RegionDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the REGION table that match the criteria 'CREATED_BY = :createdBy'.
     */
    @Transactional
    public List<Region> findWhereCreatedByEquals(BigDecimal createdBy) throws RegionDaoException {
        try {
            return jdbcTemplate.query("SELECT REGION_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " WHERE CREATED_BY = ? ORDER BY CREATED_BY", this, createdBy);
        } catch (Exception e) {
            throw new RegionDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the REGION table that match the criteria 'CREATED_DATE = :createdDate'.
     */
    @Transactional
    public List<Region> findWhereCreatedDateEquals(Date createdDate) throws RegionDaoException {
        try {
            return jdbcTemplate.query("SELECT REGION_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " WHERE CREATED_DATE = ? ORDER BY CREATED_DATE", this, createdDate);
        } catch (Exception e) {
            throw new RegionDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the REGION table that match the criteria 'UPDATED_BY = :updatedBy'.
     */
    @Transactional
    public List<Region> findWhereUpdatedByEquals(BigDecimal updatedBy) throws RegionDaoException {
        try {
            return jdbcTemplate.query("SELECT REGION_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " WHERE UPDATED_BY = ? ORDER BY UPDATED_BY", this, updatedBy);
        } catch (Exception e) {
            throw new RegionDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the REGION table that match the criteria 'UPDATED_DATE = :updatedDate'.
     */
    @Transactional
    public List<Region> findWhereUpdatedDateEquals(Date updatedDate) throws RegionDaoException {
        try {
            return jdbcTemplate.query("SELECT REGION_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " WHERE UPDATED_DATE = ? ORDER BY UPDATED_DATE", this, updatedDate);
        } catch (Exception e) {
            throw new RegionDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the REGION table that matches the specified primary-key value.
     */
    @Transactional
    public Region findByPrimaryKey(RegionPk pk) throws RegionDaoException {
        return findByPrimaryKey(pk.getRegionCode());
    }

    @Transactional
    public List<Region> findLimit(int page, int paging) throws RegionDaoException {
        try {
            return jdbcTemplate.query("SELECT REGION_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName(), this);
        } catch (Exception e) {
            throw new RegionDaoException("Query Failed", e);
        }
    }

    @Transactional
    public int countTotalRows() throws RegionDaoException {
        return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM " + getTableName());
    }


    @Transactional
    private String getBgType(String isWIC, String isDSA) {
        String ret = null;
        if (isWIC != null && isWIC.equalsIgnoreCase("Y") && (isDSA == null || isDSA.equalsIgnoreCase("N"))) {
            ret = "BG_WIC";
        }
        if (isDSA != null && isDSA.equalsIgnoreCase("Y") && (isWIC == null || isWIC.equalsIgnoreCase("N"))) {
            ret = "BG_DSA";
        }
        return ret;
    }

    public void assignBg(String regionCode, List<String> bgCode) throws RegionDaoException {

        String inStr = "(";
        for (String bg : bgCode) {
            if (!inStr.contentEquals("(")) {
                inStr += ",";
            }
            inStr += "'" + bg + "'";
        }
        inStr += ")";

// iki ngawur
        try {
            jdbcTemplate.update("UPDATE BG set REGION_CODE = ? WHERE BG_CODE IN " + inStr, regionCode);
        } catch (Exception e) {
            throw new RegionDaoException("Query Failed", e);
        }
    }

    @Transactional
    public void removeBg(List<String> bgCode) throws RegionDaoException {

        String inStr = "(";
        for (String bg : bgCode) {
            if (!inStr.contentEquals("(")) {
                inStr += ",";
            }
            inStr += "'" + bg + "'";
        }
        inStr += ")";

        try {
            jdbcTemplate.update("UPDATE BG set REGION_CODE = null WHERE BG_CODE IN " + inStr);
        } catch (Exception e) {
            throw new RegionDaoException("Query Failed", e);
        }
    }
}

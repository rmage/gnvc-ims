package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.PocDao;
import com.app.wms.engine.db.dto.Poc;
import com.app.wms.engine.db.dto.PocPk;
import com.app.wms.engine.db.exceptions.PocDaoException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class PocDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Poc>, PocDao {

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
     * @return PocPk
     */
    @Transactional
    public PocPk insert(Poc dto) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " ( POC_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE ) VALUES ( ?, ?, ?, sysdate, ?, sysdate,? )", dto.getPocCode(), dto.getName(), dto.getCreatedBy(), dto.getUpdatedBy(), dto.getIsActive());
        return dto.createPk();
    }

    /**
     * Updates a single row in the POC table.
     */
    @Transactional
    public void update(PocPk pk, Poc dto) throws PocDaoException {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET POC_CODE = ?, NAME = ?, CREATED_BY = ?, CREATED_DATE = sysdate, UPDATED_BY = ?, UPDATED_DATE = sysdate, IS_ACTIVE=? WHERE POC_CODE = ?", dto.getPocCode(), dto.getName(), dto.getCreatedBy(), dto.getUpdatedBy(), dto.getIsActive(), pk.getPocCode());
    }

    /**
     * Deletes a single row in the POC table.
     */
    @Transactional
    public void delete(PocPk pk) throws PocDaoException {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE POC_CODE = ?", pk.getPocCode());
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return Poc
     */
    public Poc mapRow(ResultSet rs, int row) throws SQLException {
        Poc dto = new Poc();
        dto.setPocCode(rs.getString(1));
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
        return "POC";
    }

    /**
     * Returns all rows from the POC table that match the criteria 'POC_CODE = :pocCode'.
     */
    @Transactional
    public Poc findByPrimaryKey(String pocCode) throws PocDaoException {
        try {
            List<Poc> list = jdbcTemplate.query("SELECT POC_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE,IS_ACTIVE FROM " + getTableName() + " WHERE POC_CODE = ?", this, pocCode);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new PocDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the POC table that match the criteria ''.
     */
    @Transactional
    public List<Poc> findAll() throws PocDaoException {
        try {
            return jdbcTemplate.query("SELECT POC_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE,IS_ACTIVE FROM " + getTableName() + " ORDER BY POC_CODE", this);
        } catch (Exception e) {
            throw new PocDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the POC table that match the criteria 'POC_CODE = :pocCode'.
     */
    @Transactional
    public List<Poc> findWherePocCodeEquals(String pocCode) throws PocDaoException {
        try {
            return jdbcTemplate.query("SELECT POC_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE,IS_ACTIVE FROM " + getTableName() + " WHERE POC_CODE = ? ORDER BY POC_CODE", this, pocCode);
        } catch (Exception e) {
            throw new PocDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the POC table that match the criteria 'NAME = :name'.
     */
    @Transactional
    public List<Poc> findWhereNameEquals(String name) throws PocDaoException {
        try {
            return jdbcTemplate.query("SELECT POC_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " WHERE NAME = ? ORDER BY NAME", this, name);
        } catch (Exception e) {
            throw new PocDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the POC table that match the criteria 'CREATED_BY = :createdBy'.
     */
    @Transactional
    public List<Poc> findWhereCreatedByEquals(BigDecimal createdBy) throws PocDaoException {
        try {
            return jdbcTemplate.query("SELECT POC_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE,IS_ACTIVE FROM " + getTableName() + " WHERE CREATED_BY = ? ORDER BY CREATED_BY", this, createdBy);
        } catch (Exception e) {
            throw new PocDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the POC table that match the criteria 'CREATED_DATE = :createdDate'.
     */
    @Transactional
    public List<Poc> findWhereCreatedDateEquals(Date createdDate) throws PocDaoException {
        try {
            return jdbcTemplate.query("SELECT POC_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " WHERE CREATED_DATE = ? ORDER BY CREATED_DATE", this, createdDate);
        } catch (Exception e) {
            throw new PocDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the POC table that match the criteria 'UPDATED_BY = :updatedBy'.
     */
    @Transactional
    public List<Poc> findWhereUpdatedByEquals(BigDecimal updatedBy) throws PocDaoException {
        try {
            return jdbcTemplate.query("SELECT POC_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE,IS_ACTIVE FROM " + getTableName() + " WHERE UPDATED_BY = ? ORDER BY UPDATED_BY", this, updatedBy);
        } catch (Exception e) {
            throw new PocDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the POC table that match the criteria 'UPDATED_DATE = :updatedDate'.
     */
    @Transactional
    public List<Poc> findWhereUpdatedDateEquals(Date updatedDate) throws PocDaoException {
        try {
            return jdbcTemplate.query("SELECT POC_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " WHERE UPDATED_DATE = ? ORDER BY UPDATED_DATE", this, updatedDate);
        } catch (Exception e) {
            throw new PocDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the POC table that matches the specified primary-key value.
     */
    @Transactional
    public Poc findByPrimaryKey(PocPk pk) throws PocDaoException {
        return findByPrimaryKey(pk.getPocCode());
    }

    @Transactional
    public List<Poc> findLimit(int page, int paging) throws PocDaoException {
        try {
            return jdbcTemplate.query("SELECT POC_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName(), this);
        } catch (Exception e) {
            throw new PocDaoException("Query failed", e);
        }
    }

    @Transactional
    public List<Poc> findUnassignedToBg(String bgCode) throws PocDaoException {
        try {
            return jdbcTemplate.query("SELECT POC_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " WHERE POC_CODE NOT IN (SELECT POC_CODE FROM BG_POC WHERE BG_CODE = ?)", this, bgCode);
        } catch (Exception e) {
            throw new PocDaoException("Query failed", e);
        }
    }

    @Transactional
    public List<Poc> findAssignedToBg(String bgCode) throws PocDaoException {
        try {
            return jdbcTemplate.query("SELECT POC_CODE, NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, IS_ACTIVE FROM " + getTableName() + " WHERE POC_CODE IN (SELECT POC_CODE FROM BG_POC WHERE BG_CODE = ?)", this, bgCode);
        } catch (Exception e) {
            throw new PocDaoException("Query failed", e);
        }
    }
}

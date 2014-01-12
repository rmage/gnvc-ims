package com.app.wms.engine.db.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.app.wms.engine.db.dao.ProvinceDao;
import com.app.wms.engine.db.dto.Province;
import com.app.wms.engine.db.exceptions.ProvinceDaoException;

public class ProvinceDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Province>, ProvinceDao {
	
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
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return City
     */
    public Province mapRow(ResultSet rs, int row) throws SQLException {
        Province dto = new Province();
        dto.setProvinceCode(rs.getString(1));
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
        return "PROVINCE";
    }

	/**
     * Returns all rows from the PROVINCE table that match the criteria ''.
     */
    @Transactional
    public List<Province> findAll() throws ProvinceDaoException {
        try {
            return jdbcTemplate.query("SELECT PROVINCE_CODE, NAME, IS_ACTIVE, IS_DELETE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE  FROM " + getTableName() + " ORDER BY PROVINCE_CODE", this);
        } catch (Exception e) {
            throw new ProvinceDaoException("Query failed", e);
        }

    }
    
    @Transactional
    public int countTotalRows() throws ProvinceDaoException {
        return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM " + getTableName());
    }

}

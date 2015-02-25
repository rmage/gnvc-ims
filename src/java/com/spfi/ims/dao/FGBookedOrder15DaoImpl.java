package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class FGBookedOrder15DaoImpl extends AbstractDAO implements FGBookedOrder15Dao {

    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC FG_BOR15_MAX_PAGE ?, ?", show, where);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public Map<String, Object> findByNumber(String borNumber) {
        try {
            return jdbcTemplate.queryForMap("EXEC FG_BOR15_BY_NUMBER ?", borNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<String, Object>();
        }
    }
    
    public List<Map<String, Object>> findDtlByNumber(String borNumber) {
        try {
            return jdbcTemplate.queryForList("EXEC FG_BOR15_DTL_BY_NUMBER ?", borNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    public List<Map<String, Object>> getBorItem(String borNumber) {
        try {
            return jdbcTemplate.queryForList("EXEC FG_BOR15_GET_ITEM ?", borNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order) {
        try {
            return jdbcTemplate.queryForList("EXEC FG_BOR15_LIST ?, ?, ?, ?", page, show, where, order);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    public void delete(String key, String createdBy) {
        jdbcTemplate.update("EXEC FG_BOR15_DELETE ?, ?", key, createdBy);
    }
    
    public void edit(String data, String createdBy) {
        jdbcTemplate.update("EXEC FG_BOR15_EDIT ?, ?", data, createdBy);
    }

    public void insert(String data, String createdBy) {
        jdbcTemplate.update("EXEC FG_BOR15_INSERT ?, ?", data, createdBy);
    }

}

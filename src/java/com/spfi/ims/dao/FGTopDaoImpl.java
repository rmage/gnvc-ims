package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class FGTopDaoImpl extends AbstractDAO implements FGTopDao {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC FG_TOP_MAX_PAGE ?, ?", show, where);
        } catch(Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public Map<String, Object> findById(int id) {
        try {
            return jdbcTemplate.queryForMap("EXEC FG_TOP_FIND_BY_ID ?", id);
        } catch(Exception e) {
            e.printStackTrace();
            return new HashMap<String, Object>();
        }
    }

    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order) {
        try {
            return jdbcTemplate.queryForList("EXEC FG_TOP_LIST ?, ?, ?, ?", page, show, where, order);
        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    public List<Map<String, Object>> findAllActive() {
        try {
            return jdbcTemplate.queryForList("EXEC FG_TOP_FIND_ACTIVE");
        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    public void insert(String data, String createdBy) {
        jdbcTemplate.update("EXEC FG_TOP_INSERT ?, ?", data, createdBy);
    }
    
    public void edit(int key, String data, String updatedBy) {
        jdbcTemplate.update("EXEC FG_TOP_EDIT ?, ?, ?", key, data, updatedBy);
    }
    
    public void delete(int key, String updatedBy) {
        jdbcTemplate.update("EXEC FG_TOP_DELETE ?, ?", key, updatedBy);
    }
    
}

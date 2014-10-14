package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class RenderingSalesDaoImpl extends AbstractDAO implements RenderingSalesDao {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC REND_SALES_MAX_PAGE ?, ?", show, where);
        } catch(Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    
    public Map<String, Object> ajaxViewStock(String date) {
        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList("EXEC REND_SALES_VIEW_STOCK ?", date);
            return rows.isEmpty() ? new HashMap<String, Object>() : rows.get(0);
        } catch(Exception e) {
            e.printStackTrace();
            return new HashMap<String, Object>();
        }
    }
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order) {
        try {
            return jdbcTemplate.queryForList("EXEC REND_SALES_LIST ?, ?, ?, ?", page, show, where, order);
        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    public void insert(String data, String createdBy) {
        jdbcTemplate.update("EXEC REND_SALES_INSERT ?, ?", data, createdBy);
    }
    
}

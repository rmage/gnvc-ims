package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class D3DaoImpl extends AbstractDAO implements D3Dao {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public List<Map<String, Object>> NFUnitPriceTrend (String itemCode, String currencyCode, String dateFrom, String dateTo) {
        try {
            return jdbcTemplate.queryForList("EXEC D3_NF_UNIT_PRICE_TREND ?, ?, ?, ?", itemCode, currencyCode, dateFrom, dateTo);
        } catch(DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
}

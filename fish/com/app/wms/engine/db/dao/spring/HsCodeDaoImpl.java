package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.HsCodeDao;
import com.app.wms.engine.db.dao.spring.AbstractDAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class HsCodeDaoImpl extends AbstractDAO implements HsCodeDao {
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "hs_code";
    }
    
    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC IC_HSCODE_MAX_PAGE ?, ?", show, where);
        } catch(Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order) {
        try {
            return jdbcTemplate.queryForList("EXEC IC_HSCODE_LIST ?, ?, ?, ?", page, show, where, order);
        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC IC_HSCODE_UPDATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC IC_HSCODE_CREATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void delete(String key, String updatedBy) {
        jdbcTemplate.update("EXEC IC_HSCODE_DELETE ?, ?", key, updatedBy);
    }

    public List<Map<String, Object>> getHsCode(String code) {
        return jdbcTemplate.queryForList("EXEC IC_HSCODE_GET_CONTENT_FOR_UPDATE ?", code);
    }

    public List<Map<String, Object>> findHsCodebyCode(String code) {
        try {
            return jdbcTemplate.queryForList("EXEC IC_HSCODE_GET_by_CODE ?", code);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
}

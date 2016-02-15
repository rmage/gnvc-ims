package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class FGReturnCargoDaoImpl extends AbstractDAO implements FGReturnCargoDao {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC FG_RETURN_CARGO_MAX_PAGE ?, ?", show, where);
        } catch(Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order) {
        try {
            return jdbcTemplate.queryForList("EXEC FG_RETURN_CARGO_LIST ?, ?, ?, ?", page, show, where, order);
        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    public List<Map<String, Object>> getPalletTransfer(String edsCode) {
        try {
            return jdbcTemplate.queryForList("EXEC FG_PALLET_TRANSFER_FIND_BY_EDSCODE ?", edsCode);
        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    public void insert(String data, String createdBy) {
        jdbcTemplate.update("EXEC FG_RETURN_CARGO_INSERT ?, ?", data, createdBy);
    }
    
    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC FG_RETURN_CARGO_UPDATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy, int isBackDate) {
        jdbcTemplate.update("EXEC FG_RETURN_CARGO_CREATE ?, ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy, isBackDate);
    }

    public void delete(String key, String updatedBy) {
        jdbcTemplate.update("EXEC FG_RETURN_CARGO_DELETE ?, ?", key, updatedBy);
    }

    public List<Map<String, Object>> getReturnCargo(String key) {
        return jdbcTemplate.queryForList("EXEC FG_RETURN_CARGO_GET_CONTENT_FOR_UPDATE ?", key);
    }
    
}

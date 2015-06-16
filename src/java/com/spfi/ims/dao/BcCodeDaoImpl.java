package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class BcCodeDaoImpl extends AbstractDAO implements BcCodeDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "bc_code";
    }
    
    public List<Map<String, Object>> findWhereTrxNo(String rrCOde, String module, int mode) {        
        try {
            return jdbcTemplate.queryForList("EXEC BCCODE_GET_BY_TRX_RR ?, ?, ?", rrCOde, module, mode);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC BCCODE_MAX_PAGE ?, ?", show, where);
        } catch(Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order) {
        try {
            return jdbcTemplate.queryForList("EXEC BCCODE_LIST ?, ?, ?, ?", page, show, where, order);
        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC BCCODE_UPDATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC BCCODE_CREATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void delete(String key, String updatedBy) {
        jdbcTemplate.update("EXEC BCCODE_DELETE ?, ?", key, updatedBy);
    }

    public List<Map<String, Object>> getBcCode(String idBc) {
        return jdbcTemplate.queryForList("EXEC BCCODE_GET_CONTENT_FOR_UPDATE ?", idBc);
    }
    
    public List<Map<String, Object>> findCodeBc() {
        try {
            return jdbcTemplate.queryForList("EXEC BCCODE_GET_CODE_BC");
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
}

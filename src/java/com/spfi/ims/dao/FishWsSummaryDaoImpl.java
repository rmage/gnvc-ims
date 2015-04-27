package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import com.spfi.ims.dto.FishWsSummary;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class FishWsSummaryDaoImpl extends AbstractDAO implements FishWsSummaryDao {

    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC F_WSS_MAX_PAGE ?, ?", show, where);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 1;
        }
    }
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order) {
        try {
            return jdbcTemplate.queryForList("EXEC F_WSS_LIST ?, ?, ?, ?", page, show, where, order);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    public List<Map<String, Object>> getBatchInfo(String batchNo) { return jdbcTemplate.queryForList("EXEC F_X_GET_BATCH_INFO ?", batchNo); }
    
    public void insert(FishWsSummary fws) {
        jdbcTemplate.update("EXEC F_WSS_CREATE ?, ?, ?, ?, ?",
                fws.getCode(), fws.getBatchNo(), fws.getDateFrom(), fws.getDateTo(), fws.getCreatedBy());
    }
    
    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC F_WSS_UPDATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void delete(int key, String updatedBy) {
        jdbcTemplate.update("EXEC F_WSS_DELETE ?, ?", key, updatedBy);
    }

    public List<Map<String, Object>> getWSSummary(int key) {
        return jdbcTemplate.queryForList("EXEC F_WSS_GET_CONTENT_FOR_UPDATE ?", key);
    }
    
}

package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import com.spfi.ims.dto.FishReclassification;
import com.spfi.ims.dto.FishReclassificationDetail;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class FishReclassificationDaoImpl extends AbstractDAO implements FishReclassificationDao {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public int insert(FishReclassification fr) {
        return jdbcTemplate.queryForInt("EXEC F_REC_INSERT ?, ?, ?",
                fr.getCode(), fr.getDate(), fr.getCreatedBy());
    }
    
    public void insertD(FishReclassificationDetail frd) {
        jdbcTemplate.update("EXEC F_RECD_INSERT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?",
                frd.getFrId(), frd.getFromVesselId(), frd.getFromFishId(), frd.getFromStorageId(), frd.getFromQty(),
                frd.getToVesselId(), frd.getToFishId(), frd.getToStorageId(), frd.getToQty(), frd.getRemarks(),
                frd.getFrType(), frd.getCreatedBy());
    }
    
    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC F_REC_MAX_PAGE ?, ?", show, where);
        } catch(DataAccessException e) {
            e.printStackTrace();
            return 1;
        }
    }
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order) {
        try {
            return jdbcTemplate.queryForList("EXEC F_REC_LIST ?, ?, ?, ?", page, show, where, order);
        } catch(DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    public List<Map<String, Object>> getBatchInfo(String batchNo) { return jdbcTemplate.queryForList("EXEC F_GET_BATCH_INFO ?", batchNo); }
    
    public List<Map<String, Object>> getFish() { return jdbcTemplate.queryForList("EXEC F_GET_FISH"); }
    
    public List<Map<String, Object>> getFishStorage() { return jdbcTemplate.queryForList("EXEC F_GET_COLD_STORAGE"); }
    
    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC F_REC_UPDATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC F_REC_CREATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void delete(int key, String updatedBy) {
        jdbcTemplate.update("EXEC F_REC_DELETE ?, ?", key, updatedBy);
    }

    public List<Map<String, Object>> getReclassification(int key) {
        return jdbcTemplate.queryForList("EXEC F_REC_GET_CONTENT_FOR_UPDATE ?", key);
    }
    
}

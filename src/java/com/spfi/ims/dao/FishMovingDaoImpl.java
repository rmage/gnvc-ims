package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import com.spfi.ims.dto.FishMoving;
import com.spfi.ims.dto.FishMovingDetail;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class FishMovingDaoImpl extends AbstractDAO implements FishMovingDao {

    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public int insert(FishMoving fm) {
        return jdbcTemplate.queryForInt("EXEC F_FMOV_INSERT ?, ?, ?, ?, ?, ?", 
                fm.getCode(), fm.getDate(), fm.getFromStorageId(), fm.getToStorageId(), fm.getRemarks(),
                fm.getCreatedBy());
    }

    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC F_FMOV_MAX_PAGE ?, ?", show, where);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order) {
        try {
            return jdbcTemplate.queryForList("EXEC F_FMOV_LIST ?, ?, ?, ?", page, show, where, order);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public List<Map<String, Object>> getBatchInCs(int storageId) {
        return jdbcTemplate.queryForList("EXEC COMMON_GET_BATCH_IN_CS ?", storageId);
    }

    public List<Map<String, Object>> getFishStorage() {
        return jdbcTemplate.queryForList("EXEC COMMON_GET_COLD_STORAGE");
    }

    public List<Map<String, Object>> getFishInCsBatch(int storageId, int vesselId) {
        return jdbcTemplate.queryForList("EXEC COMMON_GET_FISH_IN_CS_BATCH ?, ?", storageId, vesselId);
    }
    
    public void insertD(FishMovingDetail fmd) {
        jdbcTemplate.update("EXEC F_FMOVD_INSERT ?, ?, ?, ?, ?, ?",
                fmd.getFmId(), fmd.getVesselId(), fmd.getFishId(), fmd.getTotalWeight(), fmd.getUom(),
                fmd.getCreatedBy());
    }

}






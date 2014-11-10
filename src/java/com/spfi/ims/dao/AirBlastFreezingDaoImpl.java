package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import com.spfi.ims.dto.AirBlastFreezing;
import com.spfi.ims.dto.AirBlastFreezingDetail;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class AirBlastFreezingDaoImpl extends AbstractDAO implements AirBlastFreezingDao {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC F_ABF_MAX_PAGE ?, ?", show, where);
        } catch(Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    
    public int insertH(AirBlastFreezing abf) {
        return jdbcTemplate.queryForInt("EXEC F_ABF_INSERT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?", 
                abf.getAbfNo(), abf.getAbfDate(), abf.getWsId(), abf.getStorageId(), abf.getBatchNo(),
                abf.getRegu(), abf.getTimeShift(), abf.getTimeStart(), abf.getTimeFinish(), abf.getCreatedBy());
    }
    
    public void insertD(AirBlastFreezingDetail abfd, String abfNo, String batchNo, int storageId) {
        jdbcTemplate.update("EXEC F_ABFD_INSERT ?, ?, ?, ?, ?, ?, ?", 
                abfd.getAbfId(), abfd.getFishId(), abfd.getTotalWeight(), abfd.getCreatedby(), abfNo, 
                batchNo, storageId);
    }
    
    public List<Map<String, Object>> getFish(String batchNo, String wsNo) {
        return jdbcTemplate.queryForList("EXEC F_ABF_GET_FISH ?, ?", batchNo, wsNo);
    }
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order) {
        try {
            return jdbcTemplate.queryForList("EXEC F_ABF_LIST ?, ?, ?, ?", page, show, where, order);
        } catch(DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    public void insert(String data, String createdBy) {
        jdbcTemplate.update("EXEC F_ABF_INSERT2 ?, ?", data, createdBy);
    }
    
}

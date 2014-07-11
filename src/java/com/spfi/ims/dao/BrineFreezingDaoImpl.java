package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import com.spfi.ims.dto.BrineFreezing;
import com.spfi.ims.dto.BrineFreezingDetail;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class BrineFreezingDaoImpl extends AbstractDAO implements BrineFreezingDao {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC F_BF_MAX_PAGE ?, ?", show, where);
        } catch(DataAccessException e) {
            e.printStackTrace();
            return 1;
        }
    }
    
    public int insertH(BrineFreezing bf) {
        return jdbcTemplate.queryForInt("EXEC F_BF_INSERT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?", 
                bf.getBfNo(), bf.getBfDate(), bf.getWsId(), bf.getStorageId(), bf.getBatchNo(),
                bf.getRegu(), bf.getTimeShift(), bf.getTimeStart(), bf.getTimeFinish(), bf.getCreatedBy());
    }
    
    public void insertD(BrineFreezingDetail bfd, String bfNo, String batchNo, int storageId) {
        jdbcTemplate.update("EXEC F_BFD_INSERT ?, ?, ?, ?, ?, ?, ?", 
                bfd.getBfId(), bfd.getFishId(), bfd.getTotalWeight(), bfd.getCreatedby(), bfNo, 
                batchNo, storageId);
    }
    
    public List<Map<String, Object>> getFish(String batchNo, String wsNo) {
        return jdbcTemplate.queryForList("EXEC F_BF_GET_FISH ?, ?", batchNo, wsNo);
    }
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order) {
        try {
            return jdbcTemplate.queryForList("EXEC F_BF_LIST ?, ?, ?, ?", page, show, where, order);
        } catch(DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
}

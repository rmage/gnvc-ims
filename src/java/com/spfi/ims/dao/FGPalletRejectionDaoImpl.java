package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class FGPalletRejectionDaoImpl extends AbstractDAO implements FGPalletRejectionDao {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC FG_PALLET_REJECTION_MAX_PAGE ?, ?", show, where);
        } catch(Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order) {
        try {
            return jdbcTemplate.queryForList("EXEC FG_PALLET_REJECTION_LIST ?, ?, ?, ?", page, show, where, order);
        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    public List<Map<String, Object>> getPalletTransfer(String ptsCode) {
        try {
            return jdbcTemplate.queryForList("EXEC FG_PALLET_TRANSFER_DTL_FIND_BY_PTSCODE_FOR_REJECTION ?", ptsCode);
        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    public void insert(String data, String createdBy) {
        jdbcTemplate.update("EXEC FG_PALLET_REJECTION_INSERT ?, ?", data, createdBy);
    }
    
}

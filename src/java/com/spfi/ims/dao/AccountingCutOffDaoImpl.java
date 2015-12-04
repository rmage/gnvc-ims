package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class AccountingCutOffDaoImpl extends AbstractDAO implements AccountingCutOffDao {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public Map<String, Object> getHeaderData() {
        return jdbcTemplate.queryForMap("EXEC ACC_CO_GET_HEADER_DATA");
    }
    
    public List<Map<String, Object>> getDetailData(String dateFrom, String dateTo, String storage, BigDecimal currencyRate) {
        return jdbcTemplate.queryForList("EXEC RPT_ACC_F_STOCK_SUMMARY_PER_CS_VIEW ?, ?, ?, ?", dateFrom, dateTo, storage, currencyRate);
    }
    
    public void saveDetailData(String dateFrom, String dateTo, String storage, BigDecimal currencyRate, String createdBy) {
        jdbcTemplate.update("EXEC ACC_CO_SAVE_DETAIL_DATA ?, ?, ?, ?, ?", dateFrom, dateTo, storage, currencyRate, createdBy);
    }
    
}

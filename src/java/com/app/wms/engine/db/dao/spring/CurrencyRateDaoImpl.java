package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.CurrencyRateDao;
import com.app.wms.engine.db.dto.CurrencyRate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class CurrencyRateDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<CurrencyRate>, CurrencyRateDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "currency_rate";
    }
    
    public CurrencyRate mapRow(ResultSet rs, int i) throws SQLException {
        CurrencyRate cr = new CurrencyRate();
        cr.setRateId(rs.getInt("rate_id"));
        cr.setCurrencyCode(rs.getString("currency_code"));
        cr.setRateValue(rs.getBigDecimal("rate_value"));
        cr.setRateDate(rs.getDate("rate_date"));
        cr.setCreatedBy(rs.getString("created_by"));
        cr.setCreatedDate(rs.getDate("created_date"));
        
        return cr;
    }
    
    public int insert(CurrencyRate cr) {
        return jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?)",
            cr.getCurrencyCode(), cr.getRateValue(), cr.getRateDate(), cr.getCreatedBy(), cr.getCreatedDate());
    }
    
    public CurrencyRate findByCurrency(String currencyCode) {
        List<CurrencyRate> crs = jdbcTemplate.query("SELECT TOP(1) * FROM " + getTableName() + " WHERE currency_code = ? ORDER BY rate_date DESC", this, currencyCode);
        return crs.isEmpty() ? null : crs.get(0);
    }
    
    public CurrencyRate findByPurchase(int poCode) {
        List<CurrencyRate> crs = jdbcTemplate.query("SELECT cr.* FROM po INNER JOIN " + getTableName() + " cr ON cr.rate_id = po.rate_id WHERE po.po_code = ?", this, poCode);
        return crs.isEmpty() ? null : crs.get(0);
    }
    
    public List<CurrencyRate> findAll() {
        return jdbcTemplate.query("SELECT cr.rate_id, c.currency_code, cr.rate_value, cr.rate_date, cr.created_by, cr.created_date FROM currency c " +
            "LEFT JOIN (SELECT MAX(rate_id) rate_id, currency_code, MAX(rate_value) rate_value, MAX(rate_date) rate_date, MAX(created_by) created_by, MAX(created_date) created_date " +
            "	FROM " + getTableName() + " GROUP BY currency_code) cr ON cr.currency_code = c.currency_code WHERE c.is_active = 'Y'", this);
    }
    
}

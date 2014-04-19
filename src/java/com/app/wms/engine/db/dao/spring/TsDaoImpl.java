package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.TsDao;
import com.app.wms.engine.db.dto.Sws;
import com.app.wms.engine.db.dto.Ts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class TsDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<Ts>, TsDao {

    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "inventory..ts";
    }
    
    public Ts mapRow(ResultSet rs, int i) throws SQLException {
        Ts t = new Ts();
        t.setTsCode(rs.getInt("ts_code"));
        t.setTsDate(rs.getDate("ts_date"));
        t.setTsInfo(rs.getString("ts_info"));
        t.setTsTo(rs.getString("ts_to"));
        t.setTsModule(rs.getString("ts_module"));
        t.setTsType(rs.getString("ts_type"));
        t.setSwsCode(rs.getInt("sws_code"));
        t.setNotedBy(rs.getString("noted_by"));
        t.setNotedDate(rs.getDate("noted_date"));
        t.setApprovedBy(rs.getString("approved_by"));
        t.setApprovedDate(rs.getDate("approved_date"));
        t.setReceivedBy(rs.getString("received_by"));
        t.setReceivedDate(rs.getDate("received_date"));
        t.setCreatedBy(rs.getString("created_by"));
        t.setCreatedDate(rs.getDate("created_date"));
        t.setUpdatedBy(rs.getString("updated_by"));
        t.setUpdatedDate(rs.getDate("updated_date"));
        
        return t;
    }
    
    public void insert(Ts t) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
            t.getTsCode(), t.getTsDate(), t.getTsInfo(), t.getTsTo(), t.getTsModule(), t.getTsType(), t.getSwsCode(), null, null, null, null, null, null, t.getCreatedBy(),
            t.getCreatedDate(), null, null);
    }
    
    public void updateStockInventory(String productCode, int qty) {
        jdbcTemplate.update("UPDATE inventory..stock_inventory SET balance = balance - ? WHERE product_code = ?",
            qty, productCode);
    }
    
    public List<Sws> findWhereNotInTs() {
        return jdbcTemplate.query("SELECT * FROM sws WHERE sws_code NOT IN (SELECT sws_code FROM " + getTableName() + ") ORDER BY sws_date ASC", 
            new SwsDaoImpl());
    }
    
    public List<Ts> findAll(String module) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE ts_module = ? ORDER BY created_date DESC", this, module);
    }

}

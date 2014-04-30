package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.DrDao;
import com.app.wms.engine.db.dto.Dr;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class DrDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<Dr>, DrDao {

    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "inventory..dr";
    }
    
    public Dr mapRow(ResultSet rs, int i) throws SQLException {
        Dr d = new Dr();
        d.setDrCode(rs.getInt("dr_code"));
        d.setDrDate(rs.getDate("dr_date"));
        d.setDrFrom(rs.getString("dr_from"));
        d.setDrFromLoc(rs.getString("dr_fromloc"));
        d.setDrToLoc(rs.getString("dr_toloc"));
        d.setDrRemarks(rs.getString("dr_remarks"));
        d.setDrType(rs.getString("dr_type"));
        d.setSupplierCode(rs.getString("supplier_code"));
        d.setOrCode(rs.getString("or_code"));
        d.setDmCode(rs.getString("dm_code"));
        d.setDeliveredBy(rs.getString("delivered_by"));
        d.setDeliveredDate(rs.getDate("delivered_date"));
        d.setApprovedBy(rs.getString("approved_by"));
        d.setApprovedDate(rs.getDate("approved_date"));
        d.setReceivedBy(rs.getString("received_by"));
        d.setApprovedDate(rs.getDate("received_date"));
        d.setCreatedBy(rs.getString("created_by"));
        d.setCreatedDate(rs.getDate("created_date"));
        d.setUpdatedBy(rs.getString("updated_by"));
        d.setUpdatedDate(rs.getDate("updated_date"));
        
        return d;
    }
    
    public void insert(Dr d) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            d.getDrCode(), d.getDrDate(), d.getDrFrom(), d.getDrFromLoc(), d.getDrToLoc(), d.getDrRemarks(), d.getDrType(), d.getSupplierCode(),
            d.getOrCode(), d.getDmCode(), null, null, null, null, null, null, d.getCreatedBy(), d.getCreatedDate(), null, null);
    }
    
    public void updateStockInventory(String productCode, BigDecimal qty) {
        jdbcTemplate.update("UPDATE inventory..stock_inventory SET balance = balance - ? WHERE product_code = ?",
            qty, productCode);
    }
    
    public Dr findByCode(int code) {
        List<Dr> ds = jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE dr_code = ?", this, code);
        return ds.isEmpty() ? null : ds.get(0);
    }
    
    public List<Dr> findAll(String type) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE dr_type = ? ORDER BY created_date", this, type);
    }

}

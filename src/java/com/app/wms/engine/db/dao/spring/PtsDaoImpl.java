package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.PtsDao;
import com.app.wms.engine.db.dto.Pts;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class PtsDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<Pts>, PtsDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "inventory..pts";
    }
    
    public Pts mapRow(ResultSet rs, int i) throws SQLException {
        Pts t = new Pts();
        t.setPtsCode(rs.getInt("pts_code"));
        t.setPtsDate(rs.getDate("pts_date"));
        t.setPtsCanCode(rs.getString("pts_cancode"));
        t.setPtsCs(rs.getBigDecimal("pts_cs"));
        t.setPtsLocation(rs.getString("pts_location"));
        t.setProductCode(rs.getString("product_code"));
        t.setBorCode(rs.getString("bor_code"));
        t.setCoeFlk(rs.getBigDecimal("coe_flk"));
        t.setCoeNw(rs.getBigDecimal("coe_nw"));
        t.setCoeDw(rs.getBigDecimal("coe_dw"));
        t.setCoePw(rs.getBigDecimal("coe_pw"));
        t.setQadReleaseTo(rs.getString("qad_releaseto"));
        t.setQadRemarks(rs.getString("qad_remarks"));
        t.setCreatedBy(rs.getString("created_by")); 
        t.setCreatedDate(rs.getDate("created_date")); 
        t.setUpdatedBy(rs.getString("updated_by")); 
        t.setUpdatedDate(rs.getDate("updated_date")); 
        
        return t;
    }
    
    public void insert(Pts p) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
            p.getPtsCode(), p.getPtsDate(), p.getPtsCanCode(), p.getPtsCs(), p.getPtsLocation(), p.getProductCode(), p.getBorCode(),
            p.getCoeFlk(), p.getCoeNw(), p.getCoeDw(), p.getCoePw(), p.getQadReleaseTo(), p.getQadRemarks(), p.getCreatedBy(),
            p.getCreatedDate(), null, null);
    }
    
    public void updateStockInventory(String productCode, BigDecimal qty) {
        jdbcTemplate.update("UPDATE inventory..stock_inventory SET balance = balance + ? WHERE product_code = ?; " +
            "IF @@ROWCOUNT = 0 INSERT INTO inventory..stock_inventory(product_code, balance) VALUES(?, ?);", 
            qty, productCode, productCode, qty);
    }
    
    public List<Pts> findByUser(String userId) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_date DESC", this, userId);
    }
    
}

package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.ReceiveReportDao;
import com.app.wms.engine.db.dto.Purchase;
import com.app.wms.engine.db.dto.PurchaseDtl;
import com.app.wms.engine.db.dto.ReceiveReport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class ReceiveReportDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<ReceiveReport>, ReceiveReportDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "inventory..rr";
    }
    
    public ReceiveReport mapRow(ResultSet rs, int i) throws SQLException {
        ReceiveReport rr = new ReceiveReport();
        rr.setRrCode(rs.getInt("rr_code"));
        rr.setRrDate(rs.getDate("rr_date"));
        rr.setPoCode(rs.getInt("po_code"));
        rr.setRrFrom(rs.getString("rr_from"));
        rr.setEvaluatedBy(rs.getString("evaluated_by"));
        rr.setEvaluatedDate(rs.getDate("evaluated_date"));
        rr.setApprovedBy(rs.getString("approved_by"));
        rr.setApprovedDate(rs.getDate("approved_date"));
        rr.setCreatedBy(rs.getString("created_by"));
        rr.setCreatedDate(rs.getDate("created_date"));
        rr.setUpdatedBy(rs.getString("updated_by"));
        rr.setUpdatedDate(rs.getDate("updated_date"));
        
        return rr;
    }
    
    public void insert(ReceiveReport rr) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
            rr.getRrCode(), rr.getRrDate(), rr.getPoCode(), rr.getRrFrom(), null, null, null, null, rr.getCreatedBy(),
            rr.getCreatedDate(), null, null);
    }
    
    public void updateStockInventory(String productCode, int qty) {
        jdbcTemplate.update("UPDATE inventory..stock_inventory SET balance = balance + ? WHERE product_code = ?; " +
            "IF @@ROWCOUNT = 0 INSERT INTO inventory..stock_inventory(product_code, balance) VALUES(?, ?);", 
            qty, productCode, productCode, qty);
    }
    
    public List<Purchase> findByNotInRR() {
        return jdbcTemplate.query("SELECT * FROM po WHERE po_code IN ( " +
            "SELECT DISTINCT pod.po_code FROM po_detail pod " +
            "LEFT JOIN prs_detail prsd ON prsd.prsnumber = pod.prsnumber AND prsd.productcode = pod.product_code " +
            "LEFT JOIN ( " +
            "SELECT MAX(rr.rr_code) as rr_code, rr.po_code, rrd.product_code, sum(rrd.qty_g) as qty_g FROM " + getTableName() + 
                " INNER JOIN rr_detail rrd ON rrd.rr_code = rr.rr_code GROUP BY rr.po_code, rrd.product_code " +
            ") x ON x.po_code = pod.po_code AND x.product_code = pod.product_code " +
            "WHERE rr_code IS NULL OR x.qty_g < prsd.qty ) ORDER BY po_date ASC", new PurchaseDaoImpl());
    }
    
    public List<PurchaseDtl> findByPo(int poCode) {
        return jdbcTemplate.query("SELECT pod.* FROM po_detail pod " +
            "LEFT JOIN prs_detail prsd ON prsd.prsnumber = pod.prsnumber AND prsd.productcode = pod.product_code " +
            "LEFT JOIN ( " +
            "SELECT MAX(rr.rr_code) as rr_code, rr.po_code, rrd.product_code, sum(rrd.qty_g) as qty_g FROM " + getTableName() + 
                " INNER JOIN rr_detail rrd ON rrd.rr_code = rr.rr_code GROUP BY rr.po_code, rrd.product_code " +
            ") x ON x.po_code = pod.po_code AND x.product_code = pod.product_code " +
            "WHERE pod.po_code = ? AND (rr_code IS NULL OR x.qty_g < prsd.qty)", new PurchaseDtlDaoImpl(), poCode);
    }
    
    public List<ReceiveReport> findAll() {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " ORDER BY created_date DESC", this);
    }
    
}

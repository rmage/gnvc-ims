package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.ReceiveReportDtlDao;
import com.app.wms.engine.db.dto.ReceiveReportDtl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class ReceiveReportDtlDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<ReceiveReportDtl>, ReceiveReportDtlDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "rr_detail";
    }
    
    public ReceiveReportDtl mapRow(ResultSet rs, int i) throws SQLException {
        ReceiveReportDtl rrd = new ReceiveReportDtl();
        rrd.setId(rs.getInt("id"));
        rrd.setRrCode(rs.getInt("rr_code"));
        rrd.setProductCode(rs.getString("product_code"));
        rrd.setDepartmentCode(rs.getString("department_code"));
        rrd.setQtyGood(rs.getInt("qty_g"));
        rrd.setQtyBad(rs.getInt("qty_b"));
        rrd.setUom(rs.getString("uom"));
        rrd.setUnitCost(rs.getBigDecimal("unit_cost"));
        rrd.setAmount(rs.getBigDecimal("amount"));
        rrd.setCreatedBy(rs.getString("created_by"));
        rrd.setCreatedDate(rs.getDate("created_date"));
        rrd.setUpdatedBy(rs.getString("updated_by"));
        rrd.setUpdatedDate(rs.getDate("updated_date"));
        
        return rrd;
    }
    
    public void insert(ReceiveReportDtl rrd) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            rrd.getRrCode(), rrd.getProductCode(), rrd.getDepartmentCode(), rrd.getQtyGood(), rrd.getQtyBad(),
            rrd.getUom(), null, null, rrd.getCreatedBy(), rrd.getCreatedDate(), null, null);
    }
    
    public List<ReceiveReportDtl> findByPoProduct(int poCode, String productCode) {
        return jdbcTemplate.query("SELECT rrd.* FROM rr " +
            "INNER JOIN rr_detail rrd ON rrd.rr_code = rr.rr_code " +
            "WHERE po_code = ? AND rrd.product_code = ?", this, poCode, productCode);
    }
    
    public List<ReceiveReportDtl> findByRr(int rrCode) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE rr_code = ?", this, rrCode);
    }
    
    
}

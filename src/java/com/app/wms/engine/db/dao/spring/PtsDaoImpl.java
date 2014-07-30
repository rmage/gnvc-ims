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
        return "pts";
    }
    
    public Pts mapRow(ResultSet rs, int i) throws SQLException {
        Pts t = new Pts();
        t.setPtsCode(rs.getInt("pts_code"));
        t.setPtsDate(rs.getDate("pts_date"));
        t.setPtsCanCode(rs.getString("pts_cancode"));
        t.setPtsCs(rs.getBigDecimal("pts_cs"));
        t.setPtsLocation(rs.getString("pts_location"));
        t.setProductCode(rs.getString("product_code"));
        t.setBrandName(rs.getString("brand_name"));
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
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
            p.getPtsCode(), p.getPtsDate(), p.getPtsCanCode(), p.getPtsCs(), p.getPtsLocation(), p.getProductCode(), p.getBrandName(), p.getBorCode(),
            p.getCoeFlk(), p.getCoeNw(), p.getCoeDw(), p.getCoePw(), p.getQadReleaseTo(), p.getQadRemarks(), p.getCreatedBy(),
            p.getCreatedDate(), null, null);
    }
    
    public void updateStockInventory(String productCode, BigDecimal qty) {
        jdbcTemplate.update("UPDATE stock_inventory SET balance = balance + ? WHERE product_code = ?; " +
            "IF @@ROWCOUNT = 0 INSERT INTO stock_inventory(product_code, balance) VALUES(?, ?);", 
            qty, productCode, productCode, qty);
    }
    
    public Pts findByPts(int ptsCode) {
        List<Pts> ps = jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE pts_code = ?", this, ptsCode);
        return ps.isEmpty() ? null : ps.get(0);
    }
    
    public Pts findByPtsUnref(int ptsCode) {
        List<Pts> ps = jdbcTemplate.query("SELECT * FROM ( " +
            "	SELECT pts.pts_code, pts.pts_date, pts.pts_cancode, (pts.pts_cs - ISNULL(od.qty, 0)) pts_cs, pts.pts_location, pts.product_code, " +
            "		pts.brand_name, pts.bor_code, pts.coe_flk, pts.coe_nw, pts.coe_dw, pts.coe_pw, pts.qad_releaseto, pts.qad_remarks, " +
            "		pts.created_by, pts.created_date, pts.updated_by, pts.updated_date FROM pts  " +
            "		LEFT JOIN (SELECT pts_code, SUM(qty) qty FROM ofal_detail od WHERE pts_code = ? GROUP BY pts_code) od ON od.pts_code = pts.pts_code  " +
            "	WHERE pts.pts_code = ? " +
            ") q WHERE q.pts_cs != 0;", this, ptsCode, ptsCode);
        return ps.isEmpty() ? null : ps.get(0);
    }
    
    public List<Pts> findByBor(String borCode) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE bor_code = ?", this, borCode);
    }
    
    public List<Pts> findByBorNotInOfal(String borCode, String brandName) {
        return jdbcTemplate.query("SELECT * FROM ( " +
            "	SELECT pts.pts_code, pts.pts_date, pts.pts_cancode, (pts.pts_cs - ISNULL(od.qty, 0)) pts_cs, pts.pts_location, pts.product_code, " +
            "		pts.brand_name, pts.bor_code, pts.coe_flk, pts.coe_nw, pts.coe_dw, pts.coe_pw, pts.qad_releaseto, pts.qad_remarks, " +
            "		pts.created_by, pts.created_date, pts.updated_by, pts.updated_date FROM pts  " +
            "	LEFT JOIN (SELECT od.pts_code, SUM(od.qty) qty FROM ofal o INNER JOIN ofal_detail od ON od.ofal_id = o.ofal_id WHERE o.bor_code = ? GROUP BY od.pts_code) od ON od.pts_code = pts.pts_code  " +
            "	WHERE bor_code = ? AND brand_name = ? " +
            ") q WHERE q.pts_cs != 0", this, borCode, borCode, brandName);
    }
    
    public List<Pts> findByUser(String userId) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_date DESC", this, userId);
    }
    
}

package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.PurchaseDao;
import com.app.wms.engine.db.dto.AssignCanvassing;
import com.app.wms.engine.db.dto.Purchase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class PurchaseDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<Purchase>, PurchaseDao {

    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "inventory..po";
    }

    public Purchase mapRow(ResultSet rs, int i) throws SQLException {
        Purchase p = new Purchase();
        p.setPoCode(rs.getInt("po_code"));
        p.setPoDate(rs.getDate("po_date"));
        p.setSupplierCode(rs.getString("supplier_code"));
        p.setDiscount(rs.getInt("discount"));
        p.setPph(rs.getInt("pph"));
        p.setPpn(rs.getInt("ppn"));
        p.setCurrency(rs.getString("currency"));
        p.setRemarks(rs.getString("remarks"));
        p.setIsApproved(rs.getString("is_approved"));
        p.setApprovedBy(rs.getString("approved_by"));
        p.setApprovedDate(rs.getDate("approved_date"));
        p.setCreatedBy(rs.getString("created_by"));
        p.setCreatedDate(rs.getDate("created_date"));
        p.setUpdatedBy(rs.getString("updated_by"));
        p.setUpdatedDate(rs.getDate("updated_date"));
        
        return p;
    }
    
    public void insert(Purchase p) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            p.getPoCode(), p.getPoDate(), p.getSupplierCode(), p.getDiscount(), p.getPph(), p.getPpn(), p.getCurrency(), 
            p.getRemarks(), "N", null, null, p.getCreatedBy(), p.getCreatedDate(), null, null);
    }
    
    public void update(Purchase p) {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET po_date = ?, supplier_code = ?, discount = ?, pph = ?, ppn = ?, currency = ?, remarks = ?, is_approved = ?" +
            ",approved_by = ?, approved_date = ?, updated_by = ?, updated_date = ? WHERE po_code = ?", 
            p.getPoDate(), p.getSupplierCode(), p.getDiscount(), p.getPph(), p.getPpn(), p.getCurrency(), p.getRemarks(), p.getIsApproved(), p.getApprovedBy(), p.getApprovedDate(),
            p.getUpdatedBy(), p.getUpdatedDate(), p.getPoCode());
    }
    
    public Purchase findByPo(String poCode) {
        List<Purchase> ps = jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE po_code = ?", this, poCode);
        return ps.isEmpty() ? null : ps.get(0);
    }
    
    public AssignCanvassing findByPrsSupplierProduct(String prsCode, String supplierCode, String productCode) {
        List<AssignCanvassing> acs = jdbcTemplate.query("SELECT * FROM assign_canv_prc acp " +
            "LEFT JOIN po_detail pd ON pd.prsnumber = acp.prsnumber AND pd.product_code = acp.productcode " +
            "WHERE acp.prsnumber = ? AND acp.supplier_code = ? AND acp.productcode = ?", new AssignCanvassingDaoImpl(), prsCode, supplierCode, productCode);
        
        return acs.isEmpty() ? null : acs.get(0);
    }
    
    public List<Purchase> findAll() {
        return jdbcTemplate.query("SELECT po.* FROM " + getTableName() + " ORDER BY (CASE WHEN is_approved IS NULL THEN 0 ELSE 1 END), created_date DESC", this);
    }
    
    public List<AssignCanvassing> findBySupplier(String supplierCode) {
        return jdbcTemplate.query("SELECT * FROM assign_canv_prc acp " +
            "LEFT JOIN po_detail pd ON pd.prsnumber = acp.prsnumber AND pd.product_code = acp.productcode " +
            "WHERE acp.is_selected = 'Y' AND acp.supplier_code = ? AND pd.po_code IS NULL ", new AssignCanvassingDaoImpl(), supplierCode);
    }
    
}

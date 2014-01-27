package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.AssignCanvassingDao;
import com.app.wms.engine.db.dto.AssignCanvassing;
import com.app.wms.engine.db.dto.Supplier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class AssignCanvassingDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<AssignCanvassing>, AssignCanvassingDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "inventory..assign_canv_prc";
    }
    
    public AssignCanvassing mapRow(ResultSet rs, int row) throws SQLException {
        AssignCanvassing ac = new AssignCanvassing();
        ac.setId(rs.getInt("id"));
        ac.setPrsNumber(rs.getString("prsnumber"));
        ac.setProductCode(rs.getString("productcode"));
        ac.setSupplierCode(rs.getString("supplier_code"));
        ac.setUnitPrice(rs.getBigDecimal("unit_price"));
        ac.setTop(rs.getString("top"));
        ac.setTopDesc(rs.getString("top_desc"));
        ac.setTod(rs.getString("tod"));
        ac.setWp(rs.getDate("wp"));
        ac.setIsSelected(rs.getString("is_selected"));
        ac.setCreatedBy(rs.getString("created_by"));
        ac.setCreateDate(rs.getDate("created_date"));
        ac.setUpdatedBy(rs.getString("updated_by"));
        ac.setUpdatedDate(rs.getDate("updated_date"));
        return ac;
    }
    
    public int insert(AssignCanvassing ac) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
            ac.getPrsNumber(), ac.getProductCode(), ac.getSupplierCode(), ac.getUnitPrice(), ac.getTop(), ac.getTopDesc(), 
            ac.getTod(), ac.getWp(), ac.getIsSelected(), ac.getCreatedBy(), ac.getCreateDate(), ac.getUpdatedBy(), ac.getUpdatedDate());
        return jdbcTemplate.queryForInt("select @@IDENTITY");
    }
    
    public void update(AssignCanvassing ac) {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET prsnumber = ?, productcode = ?, supplier_code = ?, unit_price = ?, [top] = ?, top_desc = ?, tod = ?, wp = ?, is_selected = ?, updated_by = ?, updated_date = ? WHERE id = ?", 
            ac.getPrsNumber(), ac.getProductCode(), ac.getSupplierCode(), ac.getUnitPrice(), ac.getTop(), ac.getTopDesc(), ac.getTod(), ac.getWp(), ac.getIsSelected(), ac.getUpdatedBy(), ac.getUpdatedDate(), ac.getId());
    }
    
    public AssignCanvassing findForPriceSaving(String prsNumber, String itemCode, String supplierCode) {
        List<AssignCanvassing> acs = jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE prsnumber = ? AND productcode = ? AND supplier_code = ?", this, prsNumber, itemCode, supplierCode);
        return acs.isEmpty() ? null : acs.get(0);
    }
    
    public List<AssignCanvassing> findByUserId(String userId) {
        return jdbcTemplate.query("SELECT MAX(id) as id, prsnumber, productcode, MAX(supplier_code) as supplier_code, MAX(unit_price) as unit_price, MAX([top]) as [top], " +
            "MAX(top_desc) as top_desc, MAX(tod) as tod, MAX(wp) as wp, MAX(is_selected) as is_selected, MAX(created_by) as created_by, MAX(created_date) as created_date, MAX(updated_by) as updated_by, " +
            "MAX(updated_date) as updated_date " +
            "FROM " + getTableName() + " WHERE created_by = ? GROUP BY prsnumber, productcode ORDER BY id DESC", this, userId);
    }
    
    public List<AssignCanvassing> findByPrsNumber(String prsNumber) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE prsnumber = ?", this, prsNumber);
    }
    
    public List<AssignCanvassing> findForPriceAssign(String supplierCode) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE unit_price IS NULL AND supplier_code = ?", this, supplierCode);
    }
    
    public List<Supplier> findForCanvasingForm(String userId) {
        return jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date " +
            "FROM supplier WHERE supplier_code IN ( SELECT supplier_code FROM " + getTableName() + " WHERE unit_price IS NULL AND created_by = ? ) ORDER BY supplier_name", new SupplierDaoImpl(), userId);
    }
    
}

package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.AssignCanvassingDao;
import com.app.wms.engine.db.dto.AssignCanvassing;
import com.app.wms.engine.db.dto.Supplier;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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
        return "assign_canv_prc";
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
        return jdbcTemplate.query("SELECT MAX(id) as id, prsnumber, productcode, MAX(supplier_code) as supplier_code, MAX(unit_price) as unit_price, MAX([top]) as [top], "
                + "MAX(top_desc) as top_desc, MAX(tod) as tod, MAX(wp) as wp, MAX(is_selected) as is_selected, MAX(created_by) as created_by, MAX(created_date) as created_date, MAX(updated_by) as updated_by, "
                + "MAX(updated_date) as updated_date "
                + "FROM " + getTableName() + " WHERE created_by = ? GROUP BY prsnumber, productcode ORDER BY id DESC", this, userId);
    }

    public List<AssignCanvassing> findByUserIdPA(String userId) {
        return jdbcTemplate.query("SELECT MAX(id) as id, prsnumber, productcode, supplier_code, MAX(unit_price) as unit_price, MAX([top]) as [top], "
                + "MAX(top_desc) as top_desc, MAX(tod) as tod, MAX(wp) as wp, MAX(is_selected) as is_selected, MAX(created_by) as created_by, MAX(created_date) as created_date, MAX(updated_by) as updated_by, "
                + "MAX(updated_date) as updated_date "
                + "FROM " + getTableName() + " WHERE unit_price IS NOT NULL AND created_by = ? GROUP BY prsnumber, productcode, supplier_code ORDER BY updated_date DESC", this, userId);
    }

    public List<AssignCanvassing> findByPrsNumberItemCode(String prsNumber, String itemCode) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE prsnumber = ? AND productcode = ?", this, prsNumber, itemCode);
    }

    public List<AssignCanvassing> findByPrsNumberItemCodeSupplierCode(String prsNumber, String itemCode, String supplierCode) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE prsnumber = ? AND productcode = ? AND supplier_code = ?", this, prsNumber, itemCode, supplierCode);
    }

    public List<AssignCanvassing> findForPriceAssign(String supplierCode, String userId) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE unit_price IS NULL AND supplier_code = ? AND is_active = 'Y' AND created_by = (SELECT name FROM \"user\" WHERE user_id = ?)", this, supplierCode, userId);
    }

    public List<Supplier> findForCanvasingForm(String userId) {
        return jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date "
                + "FROM supplier WHERE supplier_code IN ( SELECT supplier_code FROM " + getTableName() + " WHERE unit_price IS NULL AND created_by = ? ) ORDER BY supplier_name", new SupplierDaoImpl(), userId);
    }

    //Modified 24 April 2014
    public int ajaxMaxPageSA(String where, BigDecimal show, String userId) {
        return jdbcTemplate.queryForInt("DECLARE @USERID varchar(50) = ? SELECT @USERID = name FROM \"user\" u where u.user_id = @USERID SELECT CEILING(COUNT(id)/?) maxpage FROM " + getTableName() + " " + (where.isEmpty() ? "WHERE created_by = @USERID AND is_active = 'Y'" : where + " AND created_by = @USERID AND is_active = 'Y'"), show, userId);
    }

    public List<AssignCanvassing> ajaxSearchSA(String where, String order, int page, int show, String userId) {
        return jdbcTemplate.query("EXEC PRC_SA_LIST ?, ?, ?, ?, ?", this, page, show, where, order, userId);
    }

    public int ajaxMaxPagePA(String where, BigDecimal show, String userId) {
        return jdbcTemplate.queryForInt("DECLARE @USERID varchar(50) = ? SELECT @USERID = name FROM \"user\" u where u.user_id = @USERID SELECT CEILING(COUNT(id)/?) maxpage FROM " + getTableName() + " " + (where.isEmpty() ? "WHERE unit_price IS NOT NULL AND created_by = @USERID AND is_active = 'Y'" : where + " AND unit_price IS NOT NULL AND created_by = @USERID AND is_active = 'Y'"), show, userId);
    }

    public List<AssignCanvassing> ajaxSearchPA(String where, String order, int page, int show, String userId) {
        return jdbcTemplate.query("EXEC PRC_PA_LIST ?, ?, ?, ?, ?", this, page, show, where, order, userId);
    }

    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC PRC_SA_UPDATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }
    
    public void ajaxNUpdatePA(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC PRC_PA_UPDATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC PRC_SA_CREATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void delete(int key, String updatedBy) {
        jdbcTemplate.update("EXEC PRC_SA_DELETE ?, ?", key, updatedBy);
    }

    public void deletePA(int key, String updatedBy) {
        jdbcTemplate.update("EXEC PRC_PA_DELETE ?, ?", key, updatedBy);
    }

    public Map<String, Object> getAssignedPrice(int id) {
        return jdbcTemplate.queryForMap("SELECT "
                + "	acp.id, acp.prsnumber, p.product_code, p.product_name, s.supplier_code, s.supplier_name, "
                + "	acp.unit_price, acp.[top], acp.top_desc, acp.tod, acp.wp, acp.is_selected "
                + "FROM assign_canv_prc acp "
                + "	INNER JOIN product p ON p.product_code = acp.productcode "
                + "	INNER JOIN supplier s ON s.supplier_code = acp.supplier_code "
                + "WHERE acp.id = ?", id);
    }

    public List<Map<String, Object>> getAssignedSupplier(int id) {
        return jdbcTemplate.queryForList("DECLARE "
                + "	@prsNumber		varchar(50), "
                + "	@productCode	varchar(50) "
                + "SELECT @prsNumber = prsnumber, @productCode = productcode FROM assign_canv_prc WHERE id = ? "
                + "SELECT acp.id, acp.prsnumber, p.product_code, p.product_name, prsd.qty, s.supplier_code, s.supplier_name FROM assign_canv_prc acp  "
                + "	LEFT JOIN supplier s ON s.supplier_code = acp.supplier_code  "
                + "	LEFT JOIN product p ON p.product_code = acp.productcode "
                + "   INNER JOIN prs_detail prsd ON prsd.prsnumber = acp.prsnumber AND prsd.productcode = acp.productcode "
                + "WHERE acp.prsnumber = @prsNumber AND acp.productcode = @productCode", id);
    }

}

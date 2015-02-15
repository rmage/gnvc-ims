package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.PurchaseDao;
import com.app.wms.engine.db.dto.AssignCanvassing;
import com.app.wms.engine.db.dto.Purchase;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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
        return "po";
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
        p.setIsCertified(rs.getString("is_certified"));
        p.setCertifiedBy(rs.getString("certified_by"));
        p.setCertifiedDate(rs.getDate("certified_date"));
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
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                p.getPoCode(), p.getRateId(), p.getPoDate(), p.getSupplierCode(), p.getDiscount(), p.getPph(), p.getPpn(), p.getCurrency(),
                p.getRemarks(), "N", null, null, "N", null, null, p.getCreatedBy(), p.getCreatedDate(), null, null);
    }

    public void update(Purchase p) {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET po_date = ?, supplier_code = ?, discount = ?, pph = ?, ppn = ?, currency = ?, remarks = ?, is_approved = ?"
                + ",approved_by = ?, approved_date = ?, updated_by = ?, updated_date = ?, is_certified = ?, certified_by = ?, certified_date = ? WHERE po_code = ?",
                p.getPoDate(), p.getSupplierCode(), p.getDiscount(), p.getPph(), p.getPpn(), p.getCurrency(), p.getRemarks(), p.getIsApproved(), p.getApprovedBy(), p.getApprovedDate(),
                p.getUpdatedBy(), p.getUpdatedDate(), p.getIsCertified(), p.getCertifiedBy(), p.getCertifiedDate(), p.getPoCode());
    }

    public Purchase findByPo(String poCode) {
        List<Purchase> ps = jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE po_code = ?", this, poCode);
        return ps.isEmpty() ? null : ps.get(0);
    }

    public AssignCanvassing findByPrsSupplierProduct(String prsCode, String supplierCode, String productCode) {
        List<AssignCanvassing> acs = jdbcTemplate.query("SELECT * FROM assign_canv_prc acp "
                + "LEFT JOIN po_detail pd ON pd.prsnumber = acp.prsnumber AND pd.product_code = acp.productcode "
                + "WHERE acp.prsnumber = ? AND acp.supplier_code = ? AND acp.productcode = ?", new AssignCanvassingDaoImpl(), prsCode, supplierCode, productCode);

        return acs.isEmpty() ? null : acs.get(0);
    }

    public List<Purchase> findAll() {
        return jdbcTemplate.query("SELECT po.* FROM " + getTableName() + " ORDER BY (CASE WHEN is_approved IS NULL THEN 0 ELSE 1 END), created_date DESC", this);
    }

    public List<AssignCanvassing> findBySupplier(String supplierCode, String userId) {
        return jdbcTemplate.query("DECLARE @name VARCHAR(50) "
                + "SELECT @name = name FROM \"user\" where user_id = ? "
                + "SELECT * "
                + "FROM assign_canv_prc acp "
                + "WHERE supplier_code = ? AND is_selected = 'Y' AND is_active = 'Y' AND created_by = @name AND NOT EXISTS ( "
                + "	SELECT 1 FROM po INNER JOIN po_detail pod ON pod.po_code = po.po_code AND pod.is_active = 'Y' "
                + "	WHERE po.is_active = 'Y' AND po.supplier_code = acp.supplier_code AND pod.prsnumber = acp.prsnumber AND pod.product_code = acp.productcode AND po.created_by = @name "
                + ")", new AssignCanvassingDaoImpl(), userId, supplierCode);
    }

    public int ajaxMaxPage(String where, BigDecimal show) {
        return jdbcTemplate.queryForInt("SELECT CEILING(COUNT(po_code)/?) maxpage FROM " + getTableName() + " " + (where.isEmpty() ? " WHERE is_active = 'Y' " : where + " AND is_active = 'Y' "), show);
    }

    public List<Purchase> ajaxSearch(String where, String order, int page, int show) {
        return jdbcTemplate.query("DECLARE @page INT, @show INT SELECT @page=?, @show=? "
                + "SELECT * FROM (SELECT * , ROW_NUMBER() OVER (" + (order.isEmpty() ? "ORDER BY created_date DESC" : order) + ") row "
                + "FROM " + getTableName() + " " + (where.isEmpty() ? " WHERE is_active = 'Y' " : where + " AND is_active = 'Y' ") + ") "
                + "list WHERE row BETWEEN (((@page - 1) * @show) + 1) AND (@page * @show)", this, page, show);
    }

    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC PRC_PO_UPDATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC PRC_PO_CREATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void delete(int key, String updatedBy) {
        jdbcTemplate.update("EXEC PRC_PO_DELETE ?, ?", key, updatedBy);
    }

    public List<Map<String, Object>> getPurchaseOrder(String poCode) {
        return jdbcTemplate.queryForList("SELECT po.po_code, po.po_date, s.supplier_code, s.supplier_name, po.discount, po.pph, po.ppn, po.currency, po.remarks, "
                + "	pod.id, prsd.prsnumber, prsd.productcode, prsd.productname, pod.department_code, prsd.qty, prsd.uom_name, acp.unit_price, pod.sub_total "
                + "FROM po "
                + "	INNER JOIN po_detail pod ON pod.po_code = po.po_code AND pod.is_active = 'Y' "
                + "	INNER JOIN prs_detail prsd ON prsd.prsnumber = pod.prsnumber AND prsd.productcode = pod.product_code AND prsd.is_active = 'Y' "
                + "	INNER JOIN assign_canv_prc acp ON acp.prsnumber = pod.prsnumber AND acp.productcode = pod.product_code AND acp.supplier_code = po.supplier_code AND acp.is_active = 'Y' "
                + "	INNER JOIN supplier s ON s.supplier_code = po.supplier_code "
                + "WHERE po.is_active = 'Y' AND po.po_code = ?", poCode);
    }

}

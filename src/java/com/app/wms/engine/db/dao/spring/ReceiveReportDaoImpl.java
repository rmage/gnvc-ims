package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.CategoryItemCurrencyTypeDao;
import com.app.wms.engine.db.dao.CurrencyRateDao;
import com.app.wms.engine.db.dao.PoDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.ReceiveReportDao;
import com.app.wms.engine.db.dto.CategoryItemCurrencyType;
import com.app.wms.engine.db.dto.CurrencyRate;
import com.app.wms.engine.db.dto.Po;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.Purchase;
import com.app.wms.engine.db.dto.PurchaseDtl;
import com.app.wms.engine.db.dto.ReceiveReport;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class ReceiveReportDaoImpl extends AbstractDAO
        implements ParameterizedRowMapper<ReceiveReport>, ReceiveReportDao {

    private SimpleJdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    private String productCode = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "rr";
    }

    public ReceiveReport mapRow(ResultSet rs, int i) throws SQLException {
        ReceiveReport rr = new ReceiveReport();
        rr.setRrCode(rs.getString("rr_code"));
        rr.setRrDate(rs.getDate("rr_date"));
        rr.setPoCode(rs.getInt("po_code"));
        rr.setRrFrom(rs.getString("rr_from"));
        rr.setUnitCost(rs.getDouble("unit_price"));
        rr.setEvaluatedBy(rs.getString("evaluated_by"));
        rr.setEvaluatedDate(rs.getDate("evaluated_date"));
        rr.setApprovedBy(rs.getString("approved_by"));
        rr.setApprovedDate(rs.getDate("approved_date"));
        rr.setCreatedBy(rs.getString("created_by"));
        rr.setCreatedDate(rs.getDate("created_date"));
        rr.setUpdatedBy(rs.getString("updated_by"));
        rr.setUpdatedDate(rs.getDate("updated_date"));
        rr.setQty(rs.getDouble("qty"));

        Po po = new Po();
        /*INSERT PO*/
        try {
            PoDao poDao = DaoFactory.createPoDao();
            po = poDao.findByPrimaryKey(rs.getLong("po_code"));
            rr.setPo(po);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        /*GET PRODUCT BY PRODUCTCODE*/
        Product product = new Product();
        if (productCode != null) {
            try {
                ProductDao productDao = DaoFactory.createProductDao();
                product = productDao.findByProductCode(productCode);
            } catch (ProductDaoException ex) {
                Logger.getLogger(ReceiveReportDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /*GET CURRENCY TYPE FROM CATEGORY*/
        CategoryItemCurrencyType cri = new CategoryItemCurrencyType();
        CategoryItemCurrencyTypeDao categoryItemCurrencyTypeDao = DaoFactory.createCategoryItemCurrencyTypeDao();
        cri = categoryItemCurrencyTypeDao.findCurrencyTypeByCategoryCode(product.getProductCategory());
        /*INSERT CURR RATE*/
        /*INSERT Currency Rate*/
        CurrencyRateDao currencyRateDao = DaoFactory.createCurrencyRateDao();
        CurrencyRate cr = currencyRateDao.findLatestByCurrencyCodeFromToDateAndType(po.getCurrency(), "IDR", rr.getRrDate(), cri.getCurrencyType());
        rr.setCurrencyRate(cr);

        return rr;
    }

    public void insert(ReceiveReport rr) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                rr.getRrCode(), rr.getRrDate(), rr.getPoCode(), rr.getRrFrom(), null, null, null, null, rr.getCreatedBy(),
                rr.getCreatedDate(), null, null);
    }

    public void updateStockInventory(String productCode, int qty) {
        jdbcTemplate.update("UPDATE stock_inventory SET balance = balance + ? WHERE product_code = ?; "
                + "IF @@ROWCOUNT = 0 INSERT INTO stock_inventory(product_code, balance) VALUES(?, ?);",
                qty, productCode, productCode, qty);
    }

    public List<Purchase> findByNotInRR() {
        return jdbcTemplate.query("SELECT * FROM po WHERE po_code IN ( "
                + "SELECT DISTINCT pod.po_code FROM po_detail pod "
                + "LEFT JOIN prs_detail prsd ON prsd.prsnumber = pod.prsnumber AND prsd.productcode = pod.product_code "
                + "LEFT JOIN ( "
                + "SELECT MAX(rr.rr_code) as rr_code, rr.po_code, rrd.product_code, sum(rrd.qty_g) as qty_g FROM " + getTableName()
                + " INNER JOIN rr_detail rrd ON rrd.rr_code = rr.rr_code GROUP BY rr.po_code, rrd.product_code "
                + ") x ON x.po_code = pod.po_code AND x.product_code = pod.product_code "
                + "WHERE rr_code IS NULL OR x.qty_g < prsd.qty ) ORDER BY po_date ASC", new PurchaseDaoImpl());
    }

    public List<PurchaseDtl> findByPo(int poCode) {
        return jdbcTemplate.query("SELECT pod.* FROM po_detail pod "
                + "LEFT JOIN prs_detail prsd ON prsd.prsnumber = pod.prsnumber AND prsd.productcode = pod.product_code "
                + "LEFT JOIN ( "
                + "SELECT MAX(rr.rr_code) as rr_code, rr.po_code, rrd.product_code, sum(rrd.qty_g) as qty_g FROM " + getTableName()
                + " INNER JOIN rr_detail rrd ON rrd.rr_code = rr.rr_code GROUP BY rr.po_code, rrd.product_code "
                + ") x ON x.po_code = pod.po_code AND x.product_code = pod.product_code "
                + "WHERE pod.po_code = ? AND (rr_code IS NULL OR x.qty_g < prsd.qty)", new PurchaseDtlDaoImpl(), poCode);
    }

    public List<ReceiveReport> findAll() {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " ORDER BY created_date DESC", this);
    }

    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC NF_RR_MAX_PAGE ?, ?", show, where);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order) {
        try {
            return jdbcTemplate.queryForList("EXEC NF_RR_LIST ?, ?, ?, ?", page, show, where, order);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public List<Map<String, Object>> getPo(String poCode) {
        try {
            return jdbcTemplate.queryForList("EXEC PRC_PO_FIND_BY_POCODE ?", poCode);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public List<Map<String, Object>> getPoDetail(String poCode) {
        try {
            return jdbcTemplate.queryForList("EXEC PRC_PO_DTL_FIND_BY_POCODE_FOR_RR ?", poCode);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public void insert(String data, String createdBy) {
        jdbcTemplate.update("EXEC NF_RR_INSERT ?, ?", data, createdBy);
    }

    public List<ReceiveReport> findByProductCode(String productCode, String asOf) {
        List<ReceiveReport> resultList = null;
        String sqlQuery = "select rr.rr_code,rr.rr_date, rr.po_code, rr.rr_from, "
                + "acprice.unit_price, rr.evaluated_by,rr.evaluated_date, "
                + " rr.approved_by, rr.approved_date , rr.created_by , "
                + "  rr.created_date,rr.updated_by, rr.updated_date,sum(rrd.qty_g) qty from "
                + "  dbo.rr rr left join dbo.rr_detail rrd on rr.rr_code = rrd.rr_code "
                + "  left join dbo.product prod on prod.product_code = rrd.product_code left join "
                + "   dbo.po po on rr.po_code = po.po_code left join dbo.assign_canv_prc acprice "
                + "    on rrd.prs_code = acprice.prsnumber "
                + "     where rrd.product_code = '" + productCode + "' and acprice.productcode = '" + productCode + "' "
                + "    and rr.rr_date BETWEEN DATEADD(MONTH, DATEDIFF(MONTH, -1, '" + asOf + "') - 1, 0) "
                + "     AND '" + asOf + "' group by rr.rr_code,rr.rr_date,rrd.qty_g,rr.po_code, "
                + "     acprice.unit_price, rr.rr_from, rr.evaluated_by,rr.evaluated_date, rr.approved_by, "
                + "     rr.approved_date , rr.created_by , rr.created_date,rr.updated_by, rr.updated_date order by rr.rr_date";

        resultList = jdbcTemplate.query(sqlQuery, this);
        return resultList;
    }

    public List<ReceiveReport> findByProductCodeAndBeforeThan(String productCode, String asOf) {
        List<ReceiveReport> resultList = null;
        /*String sqlQuery = "select rr.rr_code,rr.rr_date, rr.po_code, "
         + "rr.rr_from, acprice.unit_price, rr.evaluated_by,rr.evaluated_date, "
         + "rr.approved_by, rr.approved_date , rr.created_by , rr.created_date,"
         + "rr.updated_by, rr.updated_date,"
         + "sum(rrd.qty_g) qty from dbo.rr rr left join dbo.rr_detail rrd on "
         + "rr.rr_code = rrd.rr_code left join dbo.product prod on "
         + "prod.product_code = rrd.product_code left join dbo.po po on "
         + "rr.po_code = po.po_code left join dbo.assign_canv_prc acprice on "
         + "rrd.prs_code = acprice.prsnumber "
         + "where rrd.product_code = '" + productCode + "' and "
         + "rr.rr_date < DATEADD(MONTH, DATEDIFF(MONTH, -1, '" + asOf + "') - 1, 0) "
         + "group by rr.rr_code,rr.rr_date,rrd.qty_g,rr.po_code, acprice.unit_price, rr.rr_from, rr.evaluated_by,rr.evaluated_date, "
         + "rr.approved_by, rr.approved_date , rr.created_by , rr.created_date,"
         + "rr.updated_by, rr.updated_date order by rr.rr_date";*/
        this.productCode = productCode;
        String sqlQuery = "select rr.rr_code,rr.rr_date, rr.po_code, rr.rr_from, ACPR.unit_price, rr.evaluated_by,rr.evaluated_date, "
                + "rr.approved_by, rr.approved_date , rr.created_by , rr.created_date,rr.updated_by, "
                + "rr.updated_date, sum(rrd.qty_g) qty  from rr , (select * from rr_detail) RRD , (select prsnumber , product_code from po left join po_detail pod on "
                + "po.po_code = pod.po_code) PO , (select * from assign_canv_prc) ACPR "
                + "where rr.rr_code = RRD.rr_code and ACPR.prsnumber = RRD.prs_code AND RRD.product_code = '" + productCode + "' and PO.prsnumber = RRD.prs_code and PO.product_code = '" + productCode + "' AND ACPR.productcode = '" + productCode + "' AND "
                + "rr.rr_date < DATEADD(MONTH, DATEDIFF(MONTH, -1, '" + asOf + "') - 1, 0) "
                + "group by rr.rr_code,rr.rr_date,rrd.qty_g,rr.po_code, ACPR.unit_price, rr.rr_from, rr.evaluated_by,rr.evaluated_date, rr.approved_by, rr.approved_date , rr.created_by , rr.created_date,rr.updated_by, rr.updated_date ";

        resultList = jdbcTemplate.query(sqlQuery, this);
        return resultList;
    }

    public List<String> findProductCodeWithRR(String productCategory, Date asOf) {
        List<String> resultList = null;
        String sqlQuery = "select distinct rrd.product_code from dbo.rr rr left join dbo.rr_detail rrd on "
                + "rr.rr_code = rrd.rr_code left join dbo.product prod on "
                + "prod.product_code = rrd.product_code where prod.product_category = ? AND "
                + "(rr.rr_date BETWEEN DATEADD(MONTH, DATEDIFF(MONTH, -1, ?) - 1, 0) AND ?) ";

        resultList = (List<String>) jdbcTemplate.query(sqlQuery, new ParameterizedRowMapper<String>() {

            public String mapRow(ResultSet rs, int arg1) throws SQLException {
                return rs.getString(1);
            }
        }, productCategory, asOf, asOf);
        return resultList;
    }
    
    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC NF_RR_UPDATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC NF_RR_CREATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void delete(String key, String updatedBy) {
        jdbcTemplate.update("EXEC NF_RR_DELETE ?, ?", key, updatedBy);
    }

    public List<Map<String, Object>> getReceiving(String rrCode) {
        return jdbcTemplate.queryForList("EXEC NF_RR_GET_CONTENT_FOR_UPDATE ?", rrCode);
    }

}

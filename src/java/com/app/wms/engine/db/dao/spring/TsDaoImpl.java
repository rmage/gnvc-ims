package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.TsDao;
import com.app.wms.engine.db.dto.Sws;
import com.app.wms.engine.db.dto.Ts;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class TsDaoImpl extends AbstractDAO
        implements ParameterizedRowMapper<Ts>, TsDao {

    private SimpleJdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "ts";
    }

    public Ts mapRow(ResultSet rs, int i) throws SQLException {
        Ts t = new Ts();
        t.setTsCode(rs.getString("ts_code"));
        t.setTsDate(rs.getDate("ts_date"));
        t.setTsInfo(rs.getString("ts_info"));
        t.setTsTo(rs.getString("ts_to"));
        t.setTsModule(rs.getString("ts_module"));
        t.setTsType(rs.getString("ts_type"));
        t.setSwsCode(rs.getString("sws_code"));
        t.setNotedBy(rs.getString("noted_by"));
        t.setNotedDate(rs.getDate("noted_date"));
        t.setApprovedBy(rs.getString("approved_by"));
        t.setApprovedDate(rs.getDate("approved_date"));
        t.setReceivedBy(rs.getString("received_by"));
        t.setReceivedDate(rs.getDate("received_date"));
        t.setCreatedBy(rs.getString("created_by"));
        t.setCreatedDate(rs.getDate("created_date"));
        t.setUpdatedBy(rs.getString("updated_by"));
        t.setUpdatedDate(rs.getDate("updated_date"));
        t.setQty(rs.getDouble("qty"));

        return t;
    }

    public void insert(Ts t) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                t.getTsCode(), t.getTsDate(), t.getTsInfo(), t.getTsTo(), t.getTsModule(), t.getTsType(), t.getSwsCode(), null, null, null, null, null, null, t.getCreatedBy(),
                t.getCreatedDate(), null, null);
    }

    public void updateStockInventory(String productCode, BigDecimal qty) {
        jdbcTemplate.update("UPDATE stock_inventory SET balance = balance - ? WHERE product_code = ?",
                qty, productCode);
    }

    public List<Sws> findWhereNotInTs() {
        return jdbcTemplate.query("SELECT * FROM sws WHERE sws_code NOT IN (SELECT sws_code FROM " + getTableName() + ") ORDER BY sws_code ASC",
                new SwsDaoImpl());
    }

    public List<Ts> findAll(String module) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE ts_module = ? ORDER BY created_date DESC", this, module);
    }

    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC NF_TS_MAX_PAGE ?, ?", show, where);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order) {
        try {
            return jdbcTemplate.queryForList("EXEC NF_TS_LIST ?, ?, ?, ?", page, show, where, order);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public List<Map<String, Object>> findSwsDtlForTs(String swsCode) {
        try {
            return jdbcTemplate.queryForList("EXEC NF_SWS_BY_CODE_FOR_TS ?", swsCode);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public List<Ts> findByProductCode(String productCode, String asOf) {
        List<Ts> resultList = null;
        String sqlQuery = "select * from dbo.ts ts left join dbo.ts_detail tsd on "
                + "ts.ts_code = tsd.ts_code left join dbo.product prod on "
                + "prod.product_code = tsd.product_code where tsd.product_code = '" + productCode + "' and "
                + "ts.ts_date BETWEEN DATEADD(MONTH, DATEDIFF(MONTH, -1, '" + asOf + "') - 1, 0) AND '" + asOf + "' "
                + "order by ts.ts_date";

        resultList = jdbcTemplate.query(sqlQuery, this);
        return resultList;
    }

    public List<Ts> findByProductCodeAndBeforeThan(String productCode, String asOf) {
        List<Ts> resultList = null;
        String sqlQuery = "select * from dbo.ts ts left join dbo.ts_detail tsd on "
                + "ts.ts_code = tsd.ts_code left join dbo.product prod on "
                + "prod.product_code = tsd.product_code where tsd.product_code = '" + productCode + "' and "
                + "ts.ts_date < DATEADD(MONTH, DATEDIFF(MONTH, -1, '" + asOf + "') - 1, 0) "
                + "order by ts.ts_date";

        resultList = jdbcTemplate.query(sqlQuery, this);
        return resultList;
    }

    public List<String> findProductCodeWithRR(String productCategory, Date asOf) {
        List<String> resultList = null;
        String sqlQuery = "select distinct tsd.product_code from dbo.ts ts left join dbo.ts_detail tsd on "
                + "ts.ts_code = tsd.ts_code left join dbo.product prod on "
                + "prod.product_code = tsd.product_code where prod.product_category = ? AND "
                + "(ts.ts_date BETWEEN DATEADD(MONTH, DATEDIFF(MONTH, -1, ?) - 1, 0) AND ? )";

        resultList = (List<String>) jdbcTemplate.query(sqlQuery, new ParameterizedRowMapper<String>() {

            public String mapRow(ResultSet rs, int arg1) throws SQLException {
                return rs.getString(1);
            }
        }, productCategory, asOf, asOf);
        return resultList;
    }
    
    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC NF_TS_UPDATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }
    
    public void ajaxNUpdateO(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC NF_TSO_UPDATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC NF_TS_CREATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void ajaxNSaveO(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC NF_TSO_CREATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void delete(String key, String updatedBy) {
        jdbcTemplate.update("EXEC NF_TS_DELETE ?, ?", key, updatedBy);
    }

    public List<Map<String, Object>> getTransfer(String tsCode) {
        return jdbcTemplate.queryForList("EXEC NF_TS_GET_CONTENT_FOR_UPDATE ?", tsCode);
    }

}

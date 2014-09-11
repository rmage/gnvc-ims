package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.CurrencyRateDao;
import com.app.wms.engine.db.dto.CurrencyRate;
import com.app.wms.engine.db.dto.map.CurrencyRateListMap;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class CurrencyRateDaoImpl extends AbstractDAO
        implements ParameterizedRowMapper<CurrencyRate>, CurrencyRateDao {

    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private SimpleJdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "currency_rate";
    }

    public CurrencyRate mapRow(ResultSet rs, int i) throws SQLException {
        CurrencyRate cr = new CurrencyRate();
        cr.setRateId(rs.getInt("rate_id"));
        cr.setcurrencyCodeFrom(rs.getString("currency_code_from"));
        cr.setCurrencyCodeTo(rs.getString("currency_code_to"));
        cr.setRateValue(rs.getBigDecimal("rate_value"));
        cr.setRateDate(rs.getDate("rate_date"));
        cr.setCreatedBy(rs.getString("created_by"));
        cr.setCreatedDate(rs.getDate("created_date"));

        return cr;
    }

    public int insert(CurrencyRate cr) {
        return jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?)",
                cr.getCurrencyCodeFrom(), cr.getCurrencyCodeTo(), cr.getRateValue(), cr.getRateDate(), cr.getCreatedBy(), cr.getCreatedDate());
    }

    public CurrencyRate findByCurrency(String currencyCode) {
        List<CurrencyRate> crs = jdbcTemplate.query("SELECT TOP(1) * FROM " + getTableName() + " WHERE currency_code = ? ORDER BY rate_date DESC", this, currencyCode);
        return crs.isEmpty() ? null : crs.get(0);
    }

    public CurrencyRate findByPurchase(int poCode) {
        List<CurrencyRate> crs = jdbcTemplate.query("SELECT cr.* FROM po INNER JOIN " + getTableName() + " cr ON cr.rate_id = po.rate_id WHERE po.po_code = ?", this, poCode);
        return crs.isEmpty() ? null : crs.get(0);
    }

    public List<CurrencyRate> findAll() {
//        return jdbcTemplate.query("SELECT cr.rate_id, c.currency_code, cr.rate_value, cr.rate_date, cr.created_by, cr.created_date FROM currency c " +
//            "LEFT JOIN (SELECT MAX(rate_id) rate_id, currency_code, MAX(rate_value) rate_value, MAX(rate_date) rate_date, MAX(created_by) created_by, MAX(created_date) created_date " +
//            "	FROM " + getTableName() + " GROUP BY currency_code) cr ON cr.currency_code = c.currency_code WHERE c.is_active = 'Y'", this);
        return jdbcTemplate.query("SELECT * FROM currency_rate ORDER BY currency_code ASC" //            "LEFT JOIN (SELECT MAX(rate_id) rate_id, currency_code, rate_value, rate_date, created_by, created_date " +
                //            "	FROM " + getTableName() + " GROUP BY currency_code) cr ON cr.currency_code = c.currency_code WHERE c.is_active = 'Y'"
                , this);
    }

    public List<CurrencyRate> findByCurrencyCodeAndDate(String currencyCode, Date newerDate) {
        String queryString;
        if (currencyCode.equalsIgnoreCase("ALL")) {
            currencyCode = "%";
        }
        if (newerDate == null) {
            newerDate = new Date(100);
        }
        queryString = "SELECT * FROM " + getTableName() + " G WHERE G.currency_code_from like ? AND G.rate_date >= ? ORDER BY currency_code_from ASC";
        List<CurrencyRate> list = jdbcTemplate.query(queryString, this, currencyCode, newerDate);
        return list;
    }

    public CurrencyRate findLatestRate(String currencyCode) {
        CurrencyRate result = new CurrencyRate();
        String queryString;

        queryString = "SELECT TOP 1 * FROM (SELECT *,ROW_NUMBER() OVER(PARTITION BY "
                + "rate_date ORDER BY rate_id DESC)'RowNum' FROM "
                + getTableName()
                + ")sub WHERE RowNum = 1 and [currency_code_from] = ? ORDER BY rate_date DESC";
        result = (CurrencyRate) jdbcTemplate.query(queryString, this, currencyCode).get(0);
        return result;
    }

    public List<CurrencyRate> findByCurrencyCodeAndDate(String currencyCode, Date newerDate, int page, int pageSize) {
        /*CONVERT DATE TO STRING*/
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        StringBuffer sb = new StringBuffer();
        int i = page;
        Map map = new HashMap();
        map.put("i", i);

        if (currencyCode.equalsIgnoreCase("ALL")) {
            currencyCode = "%";
        }
        if (newerDate == null) {
            newerDate = new Date(100);
        }

        String newerDateString = df.format(newerDate);

        sb.append("declare @Page int, @PageSize int "
                + "set @Page = " + i + "; "
                + "set @PageSize = " + pageSize + "; "
                + "with PagedResult "
                + "as (select ROW_NUMBER() over (order by currency_code_from desc) as rate_id, currency_code_from, currency_code_to, rate_value, rate_date, created_by, created_date from dbo.[currency_rate] where currency_code_from like '" + currencyCode + "' and rate_date >= '" + newerDateString + "') "
                + "select * from PagedResult where rate_id between "
                + "case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
                + "else @Page end and @PageSize * @Page");

        return jdbcTemplate.query(sb.toString(), new CurrencyRateListMap(), map);
    }

    public int ajaxMaxPage(String where, BigDecimal show) {
        if (where == null || where.equalsIgnoreCase("")) {
            where = "WHERE 1 = 1 ";
        } else {
            where = where.replace("=", ">=");
        }
        return jdbcTemplate.queryForInt("SELECT CEILING(COUNT(rate_id)/?) maxpage FROM " + getTableName() + " " + where, show);
    }

    public List<CurrencyRate> ajaxSearch(String condition, String where, String order, int page, int show) {

        StringBuffer sb = new StringBuffer();
        int i = page;
        Map map = new HashMap();
        map.put("i", i);

        if (condition == null || condition.equalsIgnoreCase("")) {
            condition = "WHERE 1 = 1 ";
        } else {
            condition = condition.replace("=", ">=");
        }

        System.out.println("condition " + condition);

        sb.append("declare @Page int, @PageSize int "
                + "set @Page = " + i + "; "
                + "set @PageSize = " + show + "; "
                + "with PagedResult "
                + "as (select ROW_NUMBER() over (order by currency_code_from desc) as rate_id, currency_code_from, currency_code_to, rate_value, rate_date, created_by, created_date from dbo.[currency_rate] " + condition + ") "
                + "select * from PagedResult where rate_id between "
                + "case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
                + "else @Page end and @PageSize * @Page");

        System.out.println("QUERY " + sb.toString());

        return jdbcTemplate.query(sb.toString(), new CurrencyRateListMap());
    }

    public CurrencyRate findLatestCurrency(Date rateDate, String currencyCode) {
        CurrencyRate result = new CurrencyRate();
        String queryString;

        queryString = "SELECT TOP 1 * FROM " + getTableName()
                + " WHERE rate_date <= ? and currency_code_from = ? ORDER BY rate_date DESC";
        result = (CurrencyRate) jdbcTemplate.query(queryString, this, currencyCode).get(0);
        return result;
    }

    public CurrencyRate findLatestByCurrencyCodeAndDate(String currencyCode, Date date) {
        CurrencyRate result = new CurrencyRate();
        String queryString;
        String dateString = df.format(date);

        queryString = "SELECT TOP 1 * FROM " + getTableName()
                + " WHERE rate_date <= '" + dateString + "' and currency_code_from = ? ORDER BY rate_date DESC";
        result = (CurrencyRate) jdbcTemplate.query(queryString, this, currencyCode).get(0);
        return result;
    }

    public CurrencyRate findLatestByCurrencyCodeFromToAndDate(String currencyCodeFrom, String currencyCodeTo, Date date) {
        CurrencyRate result = new CurrencyRate();
        String queryString;
        String dateString = df.format(date);

        queryString = "SELECT TOP 1 * FROM " + getTableName()
                + " WHERE rate_date <= '" + dateString + "' and currency_code_from = ? and currency_code_to = ? ORDER BY rate_date DESC";
        result = (CurrencyRate) jdbcTemplate.query(queryString, this, currencyCodeFrom, currencyCodeTo).get(0);
        return result;
    }

}

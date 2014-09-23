/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dto.FGItem;
import com.app.wms.engine.db.dto.FGUnitCost;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGItemDao;
import com.spfi.ims.dao.FgUnitCostDao;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/**
 *
 * @author Faridzi
 */
public class FGUnitCostDaoImpl extends AbstractDAO implements ParameterizedRowMapper<FGUnitCost>, FgUnitCostDao {

    protected SimpleJdbcTemplate jdbcTemplate;

    protected DataSource dataSource;

    /**
     * Method 'setDataSource'
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "fg_unit_cost";
    }

    public FGUnitCost mapRow(ResultSet rs, int i) throws SQLException {
        FGUnitCost fgUc = new FGUnitCost();
        fgUc.setId(rs.getInt("id"));
        fgUc.setFgItemId(rs.getInt("fg_item_id"));
        fgUc.setCurrencyCode(rs.getString("currency_code"));
        fgUc.setAmountFixCost(rs.getBigDecimal("amount_fix_cost"));
        fgUc.setAmountVarCost(rs.getBigDecimal("amount_var_cost"));
        fgUc.setAmountTotal(rs.getBigDecimal("amount_total"));
        fgUc.setUnitCostDate(rs.getDate("unit_cost_date"));
        fgUc.setCreatedBy(rs.getString("created_by"));
        fgUc.setCreatedDate(rs.getDate("created_date"));
        fgUc.setUpdatedBy(rs.getString("updated_by"));
        fgUc.setUpdatedDate(rs.getDate("updated_date"));

        FGItemDao fgItemDao = DaoFactory.createFGItemDao();
        FGItem fgItem = fgItemDao.findById(fgUc.getFgItemId());

        fgUc.setFgItem(fgItem);
        return fgUc;
    }

    public int ajaxMaxPage(BigDecimal show, String where) {
        return jdbcTemplate.queryForInt("SELECT CEILING(COUNT(id)/?) maxpage FROM " + getTableName() + " " + (where.isEmpty() ? "WHERE 1=1 " : where + " "), show);
    }

    public List<FGUnitCost> ajaxSearch(String where, String order, int page, int show) {
        return jdbcTemplate.query("declare @Page int, @PageSize int set @Page = ?; "
                + "set @PageSize = ?; with PagedResult as (select ROW_NUMBER() "
                + "over (ORDER BY id) as id, fg_item_id, currency_code , "
                + "amount_fix_cost, amount_var_cost, amount_total, unit_cost_date , created_by , created_date , updated_by , updated_date, "
                + " ROW_NUMBER() OVER (ORDER BY id) "
                + "row from " + getTableName() + " " + (where.isEmpty() ? "WHERE 1=1 " : where + " ") + " ) "
                + " select * from PagedResult where id between   case when @Page > 1 then "
                + "  (@PageSize * @Page) - @PageSize + 1 else @Page end and @PageSize * @Page", this, page, show);
    }

    public int save(FGUnitCost fgUc) {
        return jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ? , ?) ",
                fgUc.getFgItemId(), fgUc.getCurrencyCode(), fgUc.getAmountFixCost(), fgUc.getAmountVarCost(), fgUc.getAmountTotal(), fgUc.getUnitCostDate(), fgUc.getCreatedBy(), fgUc.getCreatedDate(), fgUc.getCreatedBy(), fgUc.getCreatedDate());
    }

    public FGUnitCost findLatest(Date date, Integer fgItemId) {
        FGUnitCost result = new FGUnitCost();
        String queryString;

        queryString = "select top 1 * from " + getTableName() + " where unit_cost_date <= ? and fg_item_id = ? order by unit_cost_date desc";
        result = (FGUnitCost) jdbcTemplate.query(queryString, this, date, fgItemId).get(0);
        return result;
    }

}

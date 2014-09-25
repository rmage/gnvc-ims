/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dto.FGStockCardAccounting;
import com.app.wms.engine.db.dto.map.FGAccountingListMap;
import com.spfi.ims.dao.FGStockCardDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/**
 *
 * @author Faridzi
 */
public class FGStockCardDaoImpl extends AbstractDAO implements FGStockCardDao {

    private SimpleJdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "fg_sc";
    }

    public List<FGStockCardAccounting> findByDateAndPackId(Integer packId, Date date) {
        String queryString;
        queryString = "select F.*, CR.rate_value , CR.rate_date , CR.currency_code_from from ( "
                + "select a.item_id, a.sc_ending, a.sc_date, (select c.pack_id from fg_item c where a.item_id = c.item_id) as pack_id , "
                + "(select i.item_name from fg_item i where a.item_id = i.item_id) as item_name , "
                + "r.currency_code , r.amount_fix_cost, r.amount_var_cost , r.amount_total "
                + "from fg_sc a, "
                + "(select  item_id , max(sc_date) as sc_date from fg_sc group by item_id) b , "
                + "(select g.fg_item_id , g.currency_code , g.amount_fix_cost, g.amount_var_cost , g.amount_total , g.unit_cost_date from fg_unit_cost g , "
                + "(select fg_item_id , max(unit_cost_date) as unit_cost_date from fg_unit_cost group by fg_item_id) h "
                + "where g.fg_item_id = h.fg_item_id and g.unit_cost_date = h.unit_cost_date) r "
                + "where a.item_id = b.item_id and a.sc_date = b.sc_date and r.fg_item_id = a.item_id) F , "
                + "(select top 1 rate_value , rate_date , cr.currency_code_from, cr.currency_code_to from currency_rate cr , "
                + "(select currency_code_from, currency_code_to, max(rate_date) as r_date from currency_rate where (currency_code_from = 'USD' and currency_code_to='IDR' or currency_code_from = 'IDR' and currency_code_to='USD') and rate_date <= ? "
                + "group by currency_code_from, currency_code_to) cra "
                + "where cr.rate_date = cra.r_date and cra.currency_code_from = cr.currency_code_from and cra.currency_code_to = cr.currency_code_to) CR "
                + "where F.pack_id = ? and (F.sc_date BETWEEN DATEADD(MONTH, DATEDIFF(MONTH, -1, ?) - 1, 0) AND ?)";
        List<FGStockCardAccounting> list = new ArrayList<FGStockCardAccounting>();
        list = jdbcTemplate.query(queryString, new FGAccountingListMap(), date, packId, date, date);
        return list;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.FishTransactionDao;
import com.app.wms.engine.db.dto.FishTransaction;
import com.app.wms.engine.db.dto.map.FishTransactionListMap;
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
public class FishTransactionDaoImpl extends AbstractDAO implements ParameterizedRowMapper<FishTransaction>, FishTransactionDao {

    private SimpleJdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "unavailable";
    }

    public FishTransaction mapRow(ResultSet rs, int i) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<FishTransaction> findByTransactionIn(Integer fishSupplierId, Integer fishId, Date date) {
        String queryString;
        List<FishTransaction> result;
        queryString = "select frr.id, f.id as fish_id ,fuc.unit_cost,fuc.currency_code, frr.rr_no as doc_num , frr.rr_date as doc_date ,fv.supplier_id , sum(frrd.total_weight) as qty from dbo.fish_rr frr "
                + "inner join dbo.fish_rr_detail frrd on "
                + "frr.id = frrd.rr_id left join dbo.fish f on "
                + "frrd.fish_id = f.id left join dbo.fish_vessel fv on "
                + "frr.vessel_id = fv.id left join dbo.fish_unit_cost fuc on "
                + "fv.supplier_id = fuc.supplier_id "
                + "where f.id = ? AND "
                + "frr.rr_date < ? and "
                + "fv.supplier_id = ? and fuc.fish_id = ? "
                + "group by frr.id,frr.rr_no, frr.rr_date,fuc.currency_code, frr.vessel_id, fuc.unit_cost,fv.supplier_id,f.id order by frr.rr_date";

        result = jdbcTemplate.query(queryString, new FishTransactionListMap(), fishId, date, fishSupplierId, fishId);
        return result;
    }

    public List<FishTransaction> findByTransactionOut(Integer fishSupplierId, Integer fishId, Date date) {
        String queryString;
        List<FishTransaction> result;
        queryString = "select fwds.id, fwdsd.fish_id , fwds.wds_no as unit_cost , fwds.wds_no as currency_code , fwds.wds_no as doc_num , "
                + "fwds.wds_date as doc_date, fv.supplier_id , fwdsd.qty as qty from "
                + "fish_wds fwds left join fish_wds_detail fwdsd on "
                + "fwds.id = fwdsd.wds_id left join fish_vessel fv on "
                + "fwds.vessel_id = fv.id "
                + "where "
                + "fwdsd.fish_id = ? and fv.supplier_id = ? and "
                + "fwds.wds_date < ? "
                + "order by fwds.wds_date";

        result = jdbcTemplate.query(queryString, new FishTransactionListMap(), fishId, fishSupplierId, date);
        return result;
    }

    public List<FishTransaction> findByTransactionInThisMonth(Integer fishSupplierId, Integer fishId, Date date) {
        String queryString;
        List<FishTransaction> result;
        queryString = "select frr.id, f.id as fish_id ,fuc.unit_cost,fuc.currency_code, frr.rr_no as doc_num , frr.rr_date as doc_date ,fv.supplier_id , sum(frrd.total_weight) as qty from dbo.fish_rr frr "
                + "inner join dbo.fish_rr_detail frrd on "
                + "frr.id = frrd.rr_id left join dbo.fish f on "
                + "frrd.fish_id = f.id left join dbo.fish_vessel fv on "
                + "frr.vessel_id = fv.id left join dbo.fish_unit_cost fuc on "
                + "fv.supplier_id = fuc.supplier_id "
                + "where f.id = ? AND "
                + "frr.rr_date BETWEEN DATEADD(MONTH, DATEDIFF(MONTH, -1, ? ) - 1, 0) AND ? and "
                + "fv.supplier_id = ? and fuc.fish_id = ? "
                + "group by frr.id,frr.rr_no, frr.rr_date,fuc.currency_code, frr.vessel_id, fuc.unit_cost,fv.supplier_id,f.id order by frr.rr_date";

        result = jdbcTemplate.query(queryString, new FishTransactionListMap(), fishId, date, date, fishSupplierId, fishId);
        return result;
    }

    public List<FishTransaction> findByTransactionOutThisMonth(Integer fishSupplierId, Integer fishId, Date date) {
        String queryString;
        List<FishTransaction> result;
        queryString = "select fwds.id, fwdsd.fish_id , fwds.wds_no as unit_cost , fwds.wds_no as currency_code , fwds.wds_no as doc_num , "
                + "fwds.wds_date as doc_date, fv.supplier_id , fwdsd.qty as qty from "
                + "fish_wds fwds left join fish_wds_detail fwdsd on "
                + "fwds.id = fwdsd.wds_id left join fish_vessel fv on "
                + "fwds.vessel_id = fv.id "
                + "where "
                + "fwdsd.fish_id = ? and fv.supplier_id = ? and "
                + "fwds.wds_date BETWEEN DATEADD(MONTH, DATEDIFF(MONTH, -1, ? ) - 1, 0) AND ? "
                + "order by fwds.wds_date";

        result = jdbcTemplate.query(queryString, new FishTransactionListMap(), fishId, fishSupplierId, date, date);
        return result;
    }

}

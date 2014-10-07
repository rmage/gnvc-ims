/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.FishDao;
import com.app.wms.engine.db.dao.FishRRAccountingDetailDao;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishRRAccountingDetail;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/**
 *
 * @author Faridzi
 */
public class FishRRAccountingDetailDaoImpl extends AbstractDAO
        implements ParameterizedRowMapper<FishRRAccountingDetail>, FishRRAccountingDetailDao {

    private SimpleJdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "fish_rr_accounting_detail";
    }

    public FishRRAccountingDetail mapRow(ResultSet rs, int i) throws SQLException {
        FishRRAccountingDetail fishRRAccountingDetail = new FishRRAccountingDetail();
        fishRRAccountingDetail.setId(rs.getInt("id"));
        fishRRAccountingDetail.setRrId(rs.getInt("rr_id"));
        fishRRAccountingDetail.setFishId(rs.getInt("fish_id"));
        fishRRAccountingDetail.setSupplierId(rs.getInt("supplier_id"));
        fishRRAccountingDetail.setAmount(rs.getBigDecimal("amount"));
        fishRRAccountingDetail.setContractPrice(rs.getBigDecimal("contract_price"));
        fishRRAccountingDetail.setTotalWeight(rs.getDouble("total_weight"));
        fishRRAccountingDetail.setCurrencyCode(rs.getString("currency_code"));

        /*GET FISH BY ID*/
        FishDao fishDao = DaoFactory.createFishDao();
        Fish f = new Fish();
        try {
            f = fishDao.findByPrimaryKey(fishRRAccountingDetail.getFishId());
        } catch (DaoException ex) {
            Logger.getLogger(FishRRAccountingDetailDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        fishRRAccountingDetail.setFish(f);

        return fishRRAccountingDetail;
    }

    public void copyFromRRDetail() {
        String query = "insert into fish_rr_accounting_detail "
                + "(rr_id, fish_id , supplier_id , total_weight ,amount,  contract_price , currency_code ) "
                + " select rr_id , frrd.fish_id , fv.supplier_id , total_weight, fuc.unit_cost, fuc.unit_cost ,"
                + " fuc.currency_code from  fish_rr_detail frrd left join fish_rr frr on "
                + " frrd.rr_id = frr.id left join fish_vessel fv on fv.id = frr.vessel_id left join "
                + " (select contract_number,"
                + "       contract_end_date,"
                + "       fish_id,"
                + "       currency_code,"
                + "       unit_cost "
                + " from (select contract_number, "
                + "             contract_end_date, "
                + "             fish_id, "
                + "             currency_code, "
                + "             unit_cost, "
                + "             row_number() over(partition by fish_id order by contract_end_date desc) as rn "
                + "      from fish_unit_cost) as T "
                + " where rn = 1 ) fuc on fuc.fish_id = frrd.fish_id where frrd.rr_id not in "
                + "(select rr_id from fish_rr_accounting_detail) and frrd.total_weight > 0 ";
        jdbcTemplate.update(query);
    }

    public List<FishRRAccountingDetail> findByRRId(Integer rrId) {
        List<FishRRAccountingDetail> result;
        String query = "select * from fish_rr_accounting_detail where rr_id = ? ";

        result = jdbcTemplate.query(query, this, rrId);

        return result;
    }

    public int ajaxMaxPage(BigDecimal show, String where) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<FishRRAccountingDetail> ajaxSearch(String where, String order, int page, int show) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int update(FishRRAccountingDetail fishRRAccountingDetail) {
        return jdbcTemplate.update("UPDATE " + getTableName() + " SET AMOUNT = ? WHERE ID = ?", fishRRAccountingDetail.getAmount(), fishRRAccountingDetail.getId());
    }

}

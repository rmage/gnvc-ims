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
        queryString = "select fgi.item_id , fgi.item_code , fgi.item_name , sc_ending ,sc_date "
                + "from fg_sc fsc left join fg_item fgi on fgi.item_id = fsc.item_id "
                + "where fgi.pack_id = ? and fsc.sc_date BETWEEN DATEADD(MONTH, DATEDIFF(MONTH, -1, ? ) - 1, 0) AND ? ";
        List<FGStockCardAccounting> list = new ArrayList<FGStockCardAccounting>();
        list = jdbcTemplate.query(queryString, new FGAccountingListMap(), packId, date, date);
        return list;
    }

}

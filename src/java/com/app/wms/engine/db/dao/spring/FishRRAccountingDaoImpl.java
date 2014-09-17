/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.FishRRAccountingDao;
import com.app.wms.engine.db.dao.FishRrDao;
import com.app.wms.engine.db.dto.FishRRAccounting;
import com.app.wms.engine.db.dto.FishRr;
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
public class FishRRAccountingDaoImpl extends AbstractDAO
        implements ParameterizedRowMapper<FishRRAccounting>, FishRRAccountingDao {

    private SimpleJdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "fish_rr_accounting";
    }

    public FishRRAccounting mapRow(ResultSet rs, int i) throws SQLException {
        FishRRAccounting fishRRAccounting = new FishRRAccounting();
        fishRRAccounting.setId(rs.getInt("id"));
        fishRRAccounting.setRrId(rs.getInt("rr_id"));
        fishRRAccounting.setUpdatedBy(rs.getString("updated_by"));
        fishRRAccounting.setUpdatedDate(rs.getDate("updated_date"));

        /*GET RR INFO*/
        FishRrDao fishRrDao = DaoFactory.createFishRrDao();
        FishRr fRR = new FishRr();
        try {
            fRR = fishRrDao.findByPrimaryKey(fishRRAccounting.getRrId());
        } catch (DaoException ex) {
            Logger.getLogger(FishRRAccountingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        fishRRAccounting.setFishRr(fRR);

        return fishRRAccounting;
    }

    public void copyFromRR() {
        String query = "insert into " + getTableName() + " (rr_id) "
                + "select id from fish_rr "
                + "where id not in (select rr_id from " + getTableName() + ")";
        jdbcTemplate.update(query);
    }

    public int update(FishRRAccounting frrAcc) {
        return jdbcTemplate.update("UPDATE " + getTableName() + " SET updated_by = ? , updated_date = ? WHERE rr_id = ?", frrAcc.getUpdatedBy(), frrAcc.getUpdatedDate(), frrAcc.getRrId() );
    }

    public int ajaxMaxPage(BigDecimal show, String where) {
        return jdbcTemplate.queryForInt("SELECT CEILING(COUNT(id)/?) maxpage FROM " + getTableName() + " " + (where.isEmpty() ? "WHERE 1=1 " : where + " "), show);
    }

    public List<FishRRAccounting> ajaxSearch(String where, String order, int page, int show) {
        return jdbcTemplate.query("declare @Page int, @PageSize int set @Page = ?; set @PageSize = ?; "
                + "with PagedResult as (select ROW_NUMBER() over (ORDER BY id) as id, "
                + "rr_id, updated_by, updated_date, ROW_NUMBER() OVER (ORDER BY id) row from " + getTableName() + " " + (where.isEmpty() ? "WHERE 1=1 " : where + " ") + " ) "
                + " select * from PagedResult where id between   case when @Page > 1 then  "
                + "  (@PageSize * @Page) - @PageSize + 1 else @Page end and @PageSize * @Page", this, page, show);
    }

}

package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class FGOrderFillDaoImpl extends AbstractDAO implements FGOrderFillDao {

    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC FG_ORDER_FILL_MAX_PAGE ?, ?", show, where);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order) {
        try {
            return jdbcTemplate.queryForList("EXEC FG_ORDER_FILL_LIST ?, ?, ?, ?", page, show, where, order);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public List<Map<String, Object>> getBor(String borCode) {
        try {
            return jdbcTemplate.queryForList("EXEC FG_BOOKED_ORDER_DTL_FIND_BY_BOR_REFERENCE ?", borCode);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public List<Map<String, Object>> getPalletTransfer(String ptsCode, String itemCode) {
        try {
            return jdbcTemplate.queryForList("EXEC FG_PALLET_TRANSFER_DTL_FIND_BY_PTSCODE ?, ?", ptsCode, itemCode);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public void insert(String data, String createdBy) {
        jdbcTemplate.update("EXEC FG_ORDER_FILL_INSERT ?, ?", data, createdBy);
    }

    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC FG_ORDER_FILL_UPDATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC FG_ORDER_FILL_CREATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void delete(int key, String updatedBy) {
        jdbcTemplate.update("EXEC FG_ORDER_FILL_DELETE ?, ?", key, updatedBy);
    }

    public List<Map<String, Object>> getOrderFill(int key) {
        return jdbcTemplate.queryForList("EXEC FG_ORDER_FILL_GET_CONTENT_FOR_UPDATE ?", key);
    }

}

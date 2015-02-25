package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import com.app.wms.engine.db.dto.FGItem;
import com.app.wms.engine.db.dto.map.FGItemListMap;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class FGItemDaoImpl extends AbstractDAO implements FGItemDao {

    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "fg_item";
    }

    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC FG_ITEM_MAX_PAGE ?, ?", show, where);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public Map<String, Object> findById(int id) {
        try {
            return jdbcTemplate.queryForMap("EXEC FG_ITEM_FIND_BY_ID ?", id);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<String, Object>();
        }
    }

    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order) {
        try {
            return jdbcTemplate.queryForList("EXEC FG_ITEM_LIST ?, ?, ?, ?", page, show, where, order);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    public List<Map<String, Object>> findByItemCode(String itemCode) {
        try {
            return jdbcTemplate.queryForList("EXEC FG_ITEM_FIND_BY_CODE_LIST ?", "%" + itemCode + "%");
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    public List<Map<String, Object>> findByItemCodeOnTest(String itemCode) {
        try {
            return jdbcTemplate.queryForList("EXEC FG_ITEM_FIND_BY_CODE_ON_TEST_LIST ?", "%" + itemCode + "%");
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public List<Map<String, Object>> getPackStyle() {
        try {
            return jdbcTemplate.queryForList("EXEC FG_PACK_STYLE_LIST_ACTIVE");
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public void insert(String data, String createdBy) {
        jdbcTemplate.update("EXEC FG_ITEM_INSERT ?, ?", data, createdBy);
    }

    public void edit(int key, String data, String updatedBy) {
        jdbcTemplate.update("EXEC FG_ITEM_EDIT ?, ?, ?", key, data, updatedBy);
    }

    public void delete(int key, String updatedBy) {
        jdbcTemplate.update("EXEC FG_ITEM_DELETE ?, ?", key, updatedBy);
    }

    public List<FGItem> findAllActive() {
        String queryString;

        queryString = "SELECT * FROM " + getTableName() + " WHERE is_active = 'Y' ORDER BY item_code ASC";
        List<FGItem> list = jdbcTemplate.query(queryString, new FGItemListMap());

        return list;
    }

    public FGItem findById(Integer id) {
        FGItem result = new FGItem();
        String queryString;

        queryString = "SELECT * FROM " + getTableName() + " WHERE is_active = 'Y' AND item_id = " + id;
        result = jdbcTemplate.query(queryString, new FGItemListMap()).get(0);

        return result;
    }

}

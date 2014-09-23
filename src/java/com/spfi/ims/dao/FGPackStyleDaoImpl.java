package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import com.app.wms.engine.db.dto.FGPackStyle;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class FGPackStyleDaoImpl extends AbstractDAO implements ParameterizedRowMapper<FGPackStyle>, FGPackStyleDao {

    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "fg_pack_style";
    }

    public FGPackStyle mapRow(ResultSet rs, int i) throws SQLException {
        FGPackStyle fgPackStyle = new FGPackStyle();
        fgPackStyle.setId(rs.getInt("pack_id"));
        fgPackStyle.setPackStyle(rs.getString("pack_style"));
        fgPackStyle.setPackSize(rs.getString("pack_size"));
        fgPackStyle.setPackPerCs(rs.getDouble("pack_per_cs"));
        fgPackStyle.setCreatedBy(rs.getString("created_by"));
        fgPackStyle.setCreatedDate(rs.getDate("created_date"));
        fgPackStyle.setUpdatedBy(rs.getString("updated_by"));
        fgPackStyle.setUpdatedDate(rs.getDate("updated_date"));
        fgPackStyle.setIsActive(rs.getString("is_active"));

        return fgPackStyle;
    }

    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC FG_PACK_STYLE_MAX_PAGE ?, ?", show, where);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public Map<String, Object> findById(int id) {
        try {
            return jdbcTemplate.queryForMap("EXEC FG_PACK_STYLE_FIND_BY_ID ?", id);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<String, Object>();
        }
    }

    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order) {
        try {
            return jdbcTemplate.queryForList("EXEC FG_PACK_STYLE_LIST ?, ?, ?, ?", page, show, where, order);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public void insert(String data, String createdBy) {
        jdbcTemplate.update("EXEC FG_PACK_STYLE_INSERT ?, ?", data, createdBy);
    }

    public void edit(int key, String data, String updatedBy) {
        jdbcTemplate.update("EXEC FG_PACK_STYLE_EDIT ?, ?, ?", key, data, updatedBy);
    }

    public void delete(int key, String updatedBy) {
        jdbcTemplate.update("EXEC FG_PACK_STYLE_DELETE ?, ?", key, updatedBy);
    }

    public List<FGPackStyle> findAllActive() {
        List<FGPackStyle> result = new ArrayList<FGPackStyle>();
        String sqlQuery = "select * from " + getTableName() + " where is_active = 'Y'";
        result = jdbcTemplate.query(sqlQuery, this);

        return result;
    }

}

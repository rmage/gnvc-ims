package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.SwsDao;
import com.app.wms.engine.db.dto.Sws;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class SwsDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<Sws>, SwsDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "sws";
    }
    
    public Sws mapRow(ResultSet rs, int i) throws SQLException {
        Sws s = new Sws();
        s.setSwsCode(rs.getString("sws_code"));
        s.setSwsDate(rs.getDate("sws_date"));
        s.setSwsInfo(rs.getString("sws_info"));
        s.setDepartmentCode(rs.getString("department_code"));
        s.setApprovedBy(rs.getString("approved_by"));
        s.setApprovedDate(rs.getDate("approved_date"));
        s.setCreatedBy(rs.getString("created_by"));
        s.setCreatedDate(rs.getDate("created_date"));
        s.setUpdatedBy(rs.getString("updated_by"));
        s.setUpdatedDate(rs.getDate("updated_date"));
        
        return s;
    }
    
//    public void insert(Sws s) {
//        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?,?, ?, ?, ?, ?);"
//                + "UPDATE sq_nf_sws SET is_used = 'Y' WHERE document_number = ?;", 
//            s.getSwsCode(), s.getSwsDate(), s.getSwsInfo(), s.getDepartmentCode(), null, null, s.getCreatedBy(), s.getCreatedDate(), null, null,
//            s.getSwsCode());
//    }
    
    public List<Sws> findByLogin(String departmentCode) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE department_code = ? ORDER BY CREATED_DATE DESC", 
            this, departmentCode);
    }
    
    public int ajaxMaxPage(BigDecimal show, String where) {
        try {
            return jdbcTemplate.queryForInt("EXEC NF_SWS_MAX_PAGE ?, ?", show, where);
        } catch(Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    
    public String generateNumber(String userId, String departmentCode) {
        try {
            Map<String, Object> map = jdbcTemplate.queryForMap("EXEC NF_SQ_SWS ?, ?", userId, departmentCode);
            return map == null ? "" : map.get("sws_code").toString();
        } catch(Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order, String departmentCode) {
        try {
            return jdbcTemplate.queryForList("EXEC NF_SWS_LIST ?, ?, ?, ?, ?", page, show, where, order, departmentCode);
        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC NF_SWS_UPDATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy) {
        jdbcTemplate.update("EXEC NF_SWS_CREATE ?, ?, ?, ?", data, separatorColumn, separatorRow, createdBy);
    }

    public void delete(String key, String updatedBy) {
        jdbcTemplate.update("EXEC NF_SWS_DELETE ?, ?", key, updatedBy);
    }

    public List<Map<String, Object>> getStoresWithdrawal(String swsCode) {
        return jdbcTemplate.queryForList("EXEC NF_SWS_GET_CONTENT_FOR_UPDATE ?", swsCode);
    }
    
}

package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import com.spfi.ims.dto.BackDateProfile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class BackDateProfileDaoImpl extends AbstractDAO implements BackDateProfileDao {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public void create(BackDateProfile backDateProfile) {
        jdbcTemplate.update("INSERT INTO back_date_profile (menu_code, user_id, is_active, created_by, created_date) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)", 
                backDateProfile.getMenuCode(), backDateProfile.getUserId(), backDateProfile.getIsActive(), backDateProfile.getCreatedBy());
    }
    
    public void delete(int id, String updatedBy) {
        jdbcTemplate.update("UPDATE back_date_profile SET is_active = 'N', updated_by = ?, updated_date = CURRENT_TIMESTAMP WHERE id = ?", updatedBy, id);
    }
    
    public List<Map<String, Object>> findByAppMenu(String appMenuCode) {
        try {
            return jdbcTemplate.queryForList("SELECT bdp.id, u.username, u.name, ur.role_name, d.department_name " +
                "FROM back_date_profile bdp " +
                "	INNER JOIN app_menu am ON am.menu_code = bdp.menu_code " +
                "	INNER JOIN \"user\" u ON u.user_id = bdp.user_id " +
                "	INNER JOIN user_role ur ON ur.role_code = u.role_code " +
                "	INNER JOIN department d ON d.department_code = ur.department_code " +
                "WHERE bdp.menu_code = ? AND bdp.is_active = 'Y'", appMenuCode);
        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    public List<Map<String, Object>> findUserByName(String name, String appMenuCode) {
        try {
            return jdbcTemplate.queryForList("SELECT u.user_id, u.username, u.name, ur.role_name, d.department_name " +
                "FROM \"user\" u " +
                "	INNER JOIN user_role ur ON ur.role_code = u.role_code " +
                "	INNER JOIN department d ON d.department_code = ur.department_code " +
                "WHERE u.name LIKE ? AND NOT EXISTS (SELECT 1 FROM back_date_profile bdp WHERE bdp.menu_code = ? AND bdp.user_id = u.user_id AND bdp.is_active = 'Y')", "%" + name + "%", appMenuCode);
        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
    public int checkIsValid(String pathName, String userId) {
        try {
            return jdbcTemplate.queryForInt("SELECT 1 " +
                "FROM back_date_profile bdp " +
                "	INNER JOIN app_menu am ON am.menu_code = bdp.menu_code AND am.url = ? " +
                "WHERE bdp.user_id = ? AND bdp.is_active = 'Y'", pathName, userId);
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
}

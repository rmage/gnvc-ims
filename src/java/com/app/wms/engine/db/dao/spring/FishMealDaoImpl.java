package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.FishMealDao;
import com.app.wms.engine.db.dto.FishMeal;
import com.app.wms.web.controller.FishMealController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class FishMealDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<FishMeal>, FishMealDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "inventory..fishmeal";
    }
    
    public FishMeal mapRow(ResultSet rs, int i) throws SQLException {
        FishMeal fm = new FishMeal();
        fm.setFmId(rs.getInt("fm_id"));
        fm.setFmMonth(rs.getString("fm_month"));
        fm.setFmYear(rs.getString("fm_year"));
        fm.setNotedBy(rs.getString("noted_by"));
        fm.setNotedDate(rs.getDate("noted_date"));
        fm.setApprovedBy(rs.getString("approved_by"));
        fm.setApprovedDate(rs.getDate("approved_date"));
        fm.setCreatedBy(rs.getString("created_by"));
        fm.setCreatedDate(rs.getDate("created_date"));
        fm.setUpdatedBy(rs.getString("updated_by"));
        fm.setUpdatedDate(rs.getDate("updated_date"));
        
        return fm;
    }
    
    public int insert(FishMeal fm) {
        return jdbcTemplate.queryForInt("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?); SELECT @@IDENTITY;", 
            fm.getFmMonth(), fm.getFmYear(), null, null, null, null, fm.getCreatedBy(), fm.getCreatedDate(), null, null);
    }
    
    public boolean testFirstUse() {
        List<FishMeal> fms = jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE fm_year = ? AND fm_month = ?", this, 
            FishMealController.initYear, FishMealController.initMonth - 1);
        return fms.isEmpty();
    }
    
    public int insertFirstUse(int bags, int kilos, String userId) {
        return jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, GETDATE(), ?, ?); " +
            "INSERT INTO fishmeal_detail(fm_id, fm_date, fm_ei_bags, fm_ei_kilos, created_by, created_date) VALUES(" +
            "(SELECT @@IDENTITY), (SELECT CAST('20140228' AS DATE)), ?, ?, ?, GETDATE())", 
            "2", "2014", null, null, null, null, userId, null, null, bags, kilos, userId);
    }
    
}

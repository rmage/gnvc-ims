package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.FishMealDao;
import com.app.wms.engine.db.dto.FishMeal;
import com.app.wms.web.controller.FishMealController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
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
    
    public void insertOrUpdateAdjustFM(int bags, int kilos, String date, int b) {
        String productCode = "";
        
        switch(b) {
            case 1: {
                productCode = "FISH_MEAL";
            } break;
            case 2: {
                productCode = "FISH_OIL";
            } break;
        }
        
        jdbcTemplate.update("UPDATE stock_inventory_log SET log = ? WHERE product_code = '" + productCode + "' AND created_date = ? " + 
            "IF(@@ROWCOUNT = 0) INSERT INTO stock_inventory_log VALUES('" + productCode + "', ?, ?) " +
            "DECLARE @DATE VARCHAR(10), @BAGS INT, @KILOS INT " +
            "SET @DATE = ? SET @BAGS = ? SET @KILOS = ? " +
            "UPDATE fishmeal_detail SET fm_bi_bags = fm_bi_bags - @BAGS, fm_bi_kilos = fm_bi_kilos - @KILOS, fm_ei_bags = fm_ei_bags - @BAGS, fm_ei_kilos = fm_ei_kilos - @KILOS " +
            "WHERE YEAR(fm_date) >= YEAR(@DATE) AND MONTH(fm_date) > MONTH(@DATE)",
            bags + ":" + kilos, date, bags + ":" + kilos, date, date, bags, kilos);
    }
    
    public String findAdjusted(String date, int b) {
        String productCode = "";
        
        switch(b) {
            case 1: {
                productCode = "FISH_MEAL";
            } break;
            case 2: {
                productCode = "FISH_OIL";
            } break;
        }
        
        List<String> ls = jdbcTemplate.query("SELECT log FROM stock_inventory_log WHERE product_code = ? AND created_date = ?", new ParameterizedRowMapper<String>() {
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(1);
            }
        }, productCode, date);
        
        return ls.isEmpty() ? null : ls.get(0);
    }
    
}

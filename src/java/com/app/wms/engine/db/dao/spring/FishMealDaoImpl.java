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
        return "fishmeal";
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
        
        jdbcTemplate.update("DECLARE @_DATE VARCHAR(10), @_BAGS INT, @_KILOS INT, @_PRODUCT VARCHAR(30), @BAGS INT, @KILOS INT, @VAL VARCHAR(30) " +
            "SET @_DATE = ? " +
            "SET @_BAGS = ? " +
            "SET @_KILOS = ? " +
            "SET @_PRODUCT = ? " +
            " " +
            "SELECT TOP 1 @VAL = log FROM stock_inventory_log WHERE product_code = @_PRODUCT AND created_date = @_DATE " +
            "IF (@VAL IS NULL) BEGIN " +
            "	UPDATE fishmeal_detail SET fm_bi_bags = fm_bi_bags + @_BAGS, fm_bi_kilos = fm_bi_kilos + @_KILOS, fm_ei_bags = fm_ei_bags + @_BAGS, fm_ei_kilos = fm_ei_kilos + @_KILOS " +
            "		WHERE YEAR(fm_date) >= YEAR(@_DATE) AND MONTH(fm_date) > MONTH(@_DATE) " +
            "	INSERT INTO stock_inventory_log VALUES(@_PRODUCT, (CAST(@_BAGS AS VARCHAR) + ':' + CAST(@_KILOS AS VARCHAR)), @_DATE) " +
            "END ELSE BEGIN " +
            "	SELECT  " +
            "		@BAGS = CAST(SUBSTRING(@VAL, 1, CHARINDEX(':', @VAL) - 1) AS INT), " +
            "		@KILOS = CAST(SUBSTRING(@VAL, CHARINDEX(':', @VAL) + 1, LEN(@VAL)) AS INT) " +
            "	UPDATE fishmeal_detail SET fm_bi_bags = fm_bi_bags + (@_BAGS - @BAGS), fm_bi_kilos = fm_bi_kilos + (@_KILOS - @KILOS), fm_ei_bags = fm_ei_bags + (@_BAGS - @BAGS), fm_ei_kilos = fm_ei_kilos + (@_KILOS - @KILOS) " +
            "		WHERE YEAR(fm_date) >= YEAR(@_DATE) AND MONTH(fm_date) > MONTH(@_DATE) " +
            "	UPDATE stock_inventory_log SET log = (CAST(@_BAGS AS VARCHAR) + ':' + CAST(@_KILOS AS CHAR)) WHERE product_code = @_PRODUCT AND created_date = @_DATE " +
            "END", date, bags, kilos, productCode);
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

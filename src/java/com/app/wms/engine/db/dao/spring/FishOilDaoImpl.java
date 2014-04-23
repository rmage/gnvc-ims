package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.FishOilDao;
import com.app.wms.engine.db.dto.FishOil;
import com.app.wms.web.controller.FishOilController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class FishOilDaoImpl extends AbstractDAO implements ParameterizedRowMapper<FishOil>, FishOilDao {

    private SimpleJdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "inventory..fishoil";
    }

    public FishOil mapRow(ResultSet rs, int i) throws SQLException {
        FishOil fo = new FishOil();
        fo.setFoId(rs.getInt("fo_id"));
        fo.setFoMonth(rs.getString("fo_month"));
        fo.setFoYear(rs.getString("fo_year"));
        fo.setNotedBy(rs.getString("noted_by"));
        fo.setNotedDate(rs.getDate("noted_date"));
        fo.setApprovedBy(rs.getString("approved_by"));
        fo.setApprovedDate(rs.getDate("approved_date"));
        fo.setCreatedBy(rs.getString("created_by"));
        fo.setCreatedDate(rs.getDate("created_date"));
        fo.setUpdatedBy(rs.getString("updated_by"));
        fo.setUpdatedDate(rs.getDate("updated_date"));
        return fo;
    }

    public int insert(FishOil fo) {
        return jdbcTemplate.queryForInt("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?); SELECT @@IDENTITY;",
                fo.getFoMonth(), fo.getFoYear(), null, null, null, null, fo.getCreatedBy(), fo.getCreatedDate(), null, null);
    }

    public boolean testFirstUse() {
        List<FishOil> fos = jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE fo_year = ? AND fo_month = ?", this,
                FishOilController.initYear, FishOilController.initMonth - 1);
        return fos.isEmpty();
    }

    public String findAdjusted(String date, int b) {
        String productCode = "";
        switch (b) {
            case 1: {
                productCode = "FISH_MEAL";
            }
            break;
            case 2: {
                productCode = "FISH_OIL";
            }
            break;
        }
        List<String> ls = jdbcTemplate.query("SELECT log FROM stock_inventory_log WHERE product_code = ? AND created_date = ?", new ParameterizedRowMapper<String>() {
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(1);
            }
        }, productCode, date);
        return ls.isEmpty() ? null : ls.get(0);
    }

    public int insertFirstUse(int drums, int kilos, String userId) {
        return jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, GETDATE(), ?, ?); "
                + "INSERT INTO fishoil_detail(fo_id, fo_date, fo_ei_drums, fo_ei_kilos, created_by, created_date) VALUES("
                + "(SELECT @@IDENTITY), (SELECT CAST('20140331' AS DATE)), ?, ?, ?, GETDATE())",
                "3", "2014", null, null, null, null, userId, null, null, drums, kilos, userId);
    }

    public void insertOrUpdateAdjustFM(int drums, int kilos, String date, int b) {
        String productCode = "";

        switch (b) {
            case 1: {
                productCode = "FISH_MEAL";
            }
            break;
            case 2: {
                productCode = "FISH_OIL";
            }
            break;
        }

        jdbcTemplate.update("DECLARE @_DATE VARCHAR(10), @_DRUMS INT, @_KILOS INT, @_PRODUCT VARCHAR(30), @DRUMS INT, @KILOS INT, @VAL VARCHAR(30) "
                + "SET @_DATE = ? "
                + "SET @_DRUMS = ? "
                + "SET @_KILOS = ? "
                + "SET @_PRODUCT = ? "
                + " "
                + "SELECT TOP 1 @VAL = log FROM stock_inventory_log WHERE product_code = @_PRODUCT AND created_date = @_DATE "
                + "IF (@VAL IS NULL) BEGIN "
                + "	UPDATE fishoil_detail SET fo_bi_drums = fo_bi_drums + @_DRUMS, fo_bi_kilos = fo_bi_kilos + @_KILOS, fo_ei_drums = fo_ei_drums + @_DRUMS, fo_ei_kilos = fo_ei_kilos + @_KILOS "
                + "		WHERE YEAR(fo_date) >= YEAR(@_DATE) AND MONTH(fo_date) > MONTH(@_DATE) "
                + "	INSERT INTO stock_inventory_log VALUES(@_PRODUCT, (CAST(@_DRUMS AS VARCHAR) + ':' + CAST(@_KILOS AS VARCHAR)), @_DATE) "
                + "END ELSE BEGIN "
                + "	SELECT  "
                + "		@DRUMS = CAST(SUBSTRING(@VAL, 1, CHARINDEX(':', @VAL) - 1) AS INT), "
                + "		@KILOS = CAST(SUBSTRING(@VAL, CHARINDEX(':', @VAL) + 1, LEN(@VAL)) AS INT) "
                + "	UPDATE fishoil_detail SET fo_bi_drums = fo_bi_drums + (@_DRUMS - @DRUMS), fo_bi_kilos = fo_bi_kilos + (@_KILOS - @KILOS), fo_ei_drums = fo_ei_drums + (@_DRUMS - @DRUMS), fo_ei_kilos = fo_ei_kilos + (@_KILOS - @KILOS) "
                + "		WHERE YEAR(fo_date) >= YEAR(@_DATE) AND MONTH(fo_date) > MONTH(@_DATE) "
                + "	UPDATE stock_inventory_log SET log = (CAST(@_DRUMS AS VARCHAR) + ':' + CAST(@_KILOS AS CHAR)) WHERE product_code = @_PRODUCT AND created_date = @_DATE "
                + "END", date, drums, kilos, productCode);
    }
}

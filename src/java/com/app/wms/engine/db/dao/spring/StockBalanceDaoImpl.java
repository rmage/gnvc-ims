package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.StockBalanceDao;
import com.app.wms.engine.db.dto.StockBalance;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;


public class StockBalanceDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<StockBalance>, StockBalanceDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "inventory..stock_balance";
    }
    
    public StockBalance mapRow(ResultSet rs, int i) throws SQLException {
        StockBalance sb = new StockBalance();
        sb.setId(rs.getInt("id"));
        sb.setProductCode(rs.getString("product_code"));
        sb.setDate(rs.getDate("date"));
        sb.setBegin(rs.getBigDecimal("begin"));
        sb.setQty_in1(rs.getBigDecimal("qty_in1"));
        sb.setQty_in2(rs.getBigDecimal("qty_in2"));
        sb.setQty_in3(rs.getBigDecimal("qty_in3"));
        sb.setQty_out1(rs.getBigDecimal("qty_out1"));
        sb.setQty_out2(rs.getBigDecimal("qty_out2"));
        sb.setQty_out3(rs.getBigDecimal("qty_out3"));
        sb.setEnd(rs.getBigDecimal("end"));
        
        return sb;
    }
    
    public void insertOrUpdate(String productCode, Date date, BigDecimal begin, BigDecimal qty, int mode) {
        /*
         *  please see mode for multiply support stock card
         *  10   :   Receive Report
         *  20   :   Transfer Slip
         */
        String q = "";
        String i = "";
        switch(mode) {
            case 10: {
                q = "qty_in1 = qty_in1 + @QTY, [end] = [end] + @QTY";
                i = "@QTY, 0, 0, 0, 0, 0, " + begin.add(qty);
            } break;
            case 20: {
                q = "qty_out1 = qty_out1 + @QTY, [end] = [end] - @QTY";
                i = "0, 0, 0, @QTY, 0, 0, " + begin.subtract(qty);
            } break;
        }
        jdbcTemplate.update("DECLARE @PRODUCT VARCHAR(30), @DATE DATE, @QTY NUMERIC(18,2) \n" +
            "SET @PRODUCT = ? \n" +
            "SET @DATE = ? \n" +
            "SET @QTY = ? \n" +
            "UPDATE stock_balance SET " + q + " WHERE product_code = @PRODUCT AND date = @DATE \n" +
            "IF @@ROWCOUNT = 0 \n" +
            "   INSERT INTO stock_balance VALUES(@DATE, @PRODUCT, ?, " + i + ")", productCode, date, qty, begin);
    }
    
}

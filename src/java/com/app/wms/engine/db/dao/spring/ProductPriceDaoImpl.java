package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.ProductPriceDao;
import com.app.wms.engine.db.dto.ProductPrice;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;


public class ProductPriceDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<ProductPrice>, ProductPriceDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "product_price";
    }
    
    public ProductPrice mapRow(ResultSet rs, int i) throws SQLException {
        ProductPrice pp = new ProductPrice();
        pp.setId(rs.getInt("id"));
        pp.setProductCode(rs.getString("product_code"));
        pp.setCurrency(rs.getString("currency"));
        pp.setUnitPrice(rs.getBigDecimal("unit_price"));
        pp.setLastRr(rs.getInt("rr_code"));
        pp.setLastQty(rs.getBigDecimal("old_qty"));
        pp.setLastPrice(rs.getBigDecimal(("old_price")));
        pp.setCreatedBy(rs.getString("created_by"));
        pp.setCreatedDate(rs.getDate("created_date"));
        pp.setUpdatedBy(rs.getString("updated_by"));
        pp.setUpdatedDate(rs.getDate("updated_date"));
        
        return pp;
    }
    
    public void insert(ProductPrice pp) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?); " +
            "DECLARE @BALANCE NUMERIC(18, 2); SELECT @BALANCE = balance FROM stock_inventory WHERE product_code = ?; " +
            "INSERT INTO product_price_history VALUES(?, NULL, 'IDR', @BALANCE, 0, @BALANCE, 0, 'SYS', GETDATE());",
            pp.getProductCode(), pp.getCurrency(), pp.getUnitPrice(), pp.getCreatedBy(), pp.getCreatedDate(), null, null,
            pp.getProductCode(), pp.getProductCode());
    }
    
    public ProductPrice update(ProductPrice pp, int status, BigDecimal qty) {
        jdbcTemplate.update("DECLARE \n" +
            "	@STATUS INT, \n" +
            "	@RRCODE INT = -1, \n" +
            "	@PRODUCTCODE VARCHAR(30), \n" +
            "	@CURRENCY VARCHAR(3), \n" +
            "	@USERID VARCHAR(30), \n" +
            "	@OLDQTY NUMERIC(18, 2), \n" +
            "	@OLDPRICE NUMERIC(18, 2), \n" +
            "	@NAME VARCHAR(50); \n" +
            "SET @STATUS = ?;		\n" +
            "SET @PRODUCTCODE = ?;\n" +
            "SET @CURRENCY = ?;\n" +
            "SET @USERID = ?;\n" +
            "SET @OLDQTY = (SELECT balance FROM stock_inventory WHERE product_code = @PRODUCTCODE);\n" +
            "SET @OLDPRICE = (SELECT unit_price FROM product_price WHERE product_code = @PRODUCTCODE);\n" +
            "SET @NAME = (SELECT name FROM \"user\" WHERE user_id = @USERID);\n" +
            "\n" +
            "IF @STATUS != 0\n" +
            "	SET @RRCODE = (SELECT TOP(1) rr_code FROM rr_detail WHERE product_code = @PRODUCTCODE ORDER BY created_date DESC);\n" +
            "INSERT INTO product_price_history VALUES(@PRODUCTCODE, @RRCODE, @CURRENCY, @OLDQTY, @OLDPRICE, @OLDQTY + ?, ?, @NAME, GETDATE());\n" +
            "UPDATE product_price SET currency = @CURRENCY, unit_price = ?, updated_by = @USERID, updated_date = ? WHERE product_code = @PRODUCTCODE;", 
            status, pp.getProductCode(), pp.getCurrency(), pp.getUpdatedBy(), qty, pp.getUnitPrice(), pp.getUnitPrice(), pp.getUpdatedDate());
        
        return findByProduct(pp.getProductCode());
    }
    
    public ProductPrice findByProduct(String productCode) {
        List<ProductPrice> pps = jdbcTemplate.query("SELECT TOP(1) pp.*,  pph.rr_code, pph.old_qty, pph.old_price \n" +
            "FROM product_price pp \n" +
            "	LEFT JOIN product_price_history pph ON pph.product_code = pp.product_code AND pph.currency = pp.currency \n" +
            "WHERE pp.product_code = ? ORDER BY pph.updated_date DESC", this, productCode);
        
        if(pps.isEmpty()) {
            ProductPrice pp = new ProductPrice();
            pp.setProductCode(productCode);
            pp.setCurrency("IDR");
            pp.setUnitPrice(BigDecimal.ZERO);
            pp.setLastPrice(BigDecimal.ZERO);
            pp.setLastQty(BigDecimal.ZERO);
            pp.setCreatedBy("SYS");
            pp.setCreatedDate(new Date());
            insert(pp);
            return pp;
        } else {
            return pps.get(0);
        }
    }
    
}

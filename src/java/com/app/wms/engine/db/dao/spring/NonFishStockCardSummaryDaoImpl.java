package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.NonFishStockCardSummaryDao;
import com.app.wms.engine.db.dto.NonFishStockCardSummary;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class NonFishStockCardSummaryDaoImpl extends AbstractDAO
        implements ParameterizedRowMapper<NonFishStockCardSummary>, NonFishStockCardSummaryDao {

    private SimpleJdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "nf_stock_card_summary";
    }

    public NonFishStockCardSummary mapRow(ResultSet rs, int i) throws SQLException {
        NonFishStockCardSummary nfStockSummary = new NonFishStockCardSummary();
        nfStockSummary.setId(rs.getInt("id"));
        nfStockSummary.setProductCode(rs.getString("product_code"));
        nfStockSummary.setProductCategory(rs.getString("product_category"));
        nfStockSummary.setAsOFDate(rs.getDate("as_of_date"));
        nfStockSummary.setQuantity(rs.getDouble("qty"));
        nfStockSummary.setUnitCost(rs.getBigDecimal("unit_cost"));
        nfStockSummary.setAmountToDate(rs.getBigDecimal("amount_to_date"));
        nfStockSummary.setBeginningAmount(rs.getBigDecimal("beginning_amount"));
        nfStockSummary.setTransactionAmount(rs.getBigDecimal("transaction_amount"));

        /*INSERT PRODUCT*/
        com.app.wms.engine.db.dao.ProductDao productDao = DaoFactory.createProductDao();
        Product p = new Product();
        try {
            p = productDao.findByProductCode(rs.getString("product_code"));
        } catch (ProductDaoException ex) {
            Logger.getLogger(NonFishStockCardSummaryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        nfStockSummary.setProduct(p);
        return nfStockSummary;
    }

    public int insert(NonFishStockCardSummary nfSummary) {
        return jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                nfSummary.getProductCode(), nfSummary.getProductCategory(), nfSummary.getAsOFDate(), nfSummary.getQuantity(), nfSummary.getUnitCost(), nfSummary.getAmountToDate(), nfSummary.getBeginningAmount(), nfSummary.getTransactionAmount());
    }

    public List<NonFishStockCardSummary> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<NonFishStockCardSummary> findByItemCategory(String productCategory) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<NonFishStockCardSummary> findByItemCategoryandDate(String productCategory, String asOfDate) {
        String queryString;
        queryString = "  select * from dbo.nf_stock_card_summary where product_category = '" + productCategory + "'  and "
                + "  as_of_date BETWEEN DATEADD(MONTH, DATEDIFF(MONTH, -1, '" + asOfDate + "') - 1, 0) AND '" + asOfDate + "'";
        List<NonFishStockCardSummary> list = new ArrayList<NonFishStockCardSummary>();
        list = jdbcTemplate.query(queryString, this);
        return list;
    }

    public int update(NonFishStockCardSummary nfSummary) {
        return jdbcTemplate.update("UPDATE " + getTableName() + " SET as_of_date = ?, qty =  ?, unit_cost = ?, amount_to_date = ?, beginning_amount = ?, transaction_amount = ? where id = ? ",
                nfSummary.getAsOFDate(), nfSummary.getQuantity(), nfSummary.getUnitCost(), nfSummary.getAmountToDate(), nfSummary.getBeginningAmount(), nfSummary.getTransactionAmount(), nfSummary.getId());
    }

    public boolean isExist(String productCode, String asOfDate) {
        boolean result = false;
        String sqlQuery = " SELECT count(*) "
                + "  FROM dbo.nf_stock_card_summary  "
                + "  where product_code = '" + productCode + "' and "
                + "  as_of_date BETWEEN DATEADD(MONTH, DATEDIFF(MONTH, -1, '" + asOfDate + "') - 1, 0) AND '" + asOfDate + "'";
        int temp = (int) jdbcTemplate.queryForInt(sqlQuery);
        if (temp > 0) {
            result = true;
        }
        return result;
    }

    public NonFishStockCardSummary findByProductCodeAndDate(String productCode, String asOfDate) {
        NonFishStockCardSummary result = new NonFishStockCardSummary();
        String sqlQuery = " SELECT top 1 * "
                + "  FROM dbo.nf_stock_card_summary  "
                + "  where product_code = '" + productCode + "' and "
                + "  as_of_date BETWEEN DATEADD(MONTH, DATEDIFF(MONTH, -1, '" + asOfDate + "') - 1, 0) AND '" + asOfDate + "'";
        result = (NonFishStockCardSummary) jdbcTemplate.query(sqlQuery, this).get(0);

        return result;

    }

}

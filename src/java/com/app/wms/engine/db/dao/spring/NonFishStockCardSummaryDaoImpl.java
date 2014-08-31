package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.NonFishStockCardSummaryDao;
import com.app.wms.engine.db.dto.CurrencyRate;
import com.app.wms.engine.db.dto.NonFishStockCardSummary;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
        queryString = "select a.* from dbo.nf_stock_card_summary a inner join "
                + "( "
                + "select distinct product_code, min(id) as id from dbo.nf_stock_card_summary group by product_code "
                + ") as b "
                + "on a.product_code = b.product_code and a.id = b.id where a.product_category = '" + productCategory + "' and as_of_date <= '" + asOfDate + "' ";
        List<NonFishStockCardSummary> list = new ArrayList<NonFishStockCardSummary>();
        list = jdbcTemplate.query(queryString, this);
        return list;
    }

}

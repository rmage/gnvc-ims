package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.NonFishStockCardDao;
import com.app.wms.engine.db.dto.NonFishDocumentSummary;
import com.app.wms.engine.db.dto.NonFishStockCardAccounting;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.map.NonFishDocumentSummaryListMap;
import com.app.wms.engine.db.factory.DaoFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class NonFishStockCardDaoImpl extends AbstractDAO
        implements ParameterizedRowMapper<NonFishStockCardAccounting>, NonFishStockCardDao {

    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private SimpleJdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "nf_stock_card";
    }

    public NonFishStockCardAccounting mapRow(ResultSet rs, int i) throws SQLException {
        NonFishStockCardAccounting nfStockCard = new NonFishStockCardAccounting();
        nfStockCard.setId(rs.getInt("id"));
        nfStockCard.setProductId(rs.getString("product_id"));
        nfStockCard.setProductCategory(rs.getString("product_category"));
        nfStockCard.setStockCardDate(rs.getDate("stock_card_date"));
        nfStockCard.setCode(rs.getString("code"));
        nfStockCard.setNumber(rs.getString("number"));
        nfStockCard.setCurrencyCode(rs.getString("currency_code"));
        nfStockCard.setRateValue(rs.getBigDecimal("rate_value"));
        nfStockCard.setAmount(rs.getBigDecimal("amount"));
        nfStockCard.setAmountIDR(rs.getBigDecimal("amount_IDR"));
        nfStockCard.setQuantityIN(rs.getDouble("qty_in"));
        nfStockCard.setUnitCostIN(rs.getBigDecimal("unit_cost_in"));
        nfStockCard.setAmountIN(rs.getBigDecimal("amount_in"));
        nfStockCard.setQuantityOUT(rs.getDouble("qty_out"));
        nfStockCard.setUnitCostOUT(rs.getBigDecimal("unit_cost_out"));
        nfStockCard.setAmountOUT(rs.getBigDecimal("amount_out"));
        nfStockCard.setQuantityEND(rs.getDouble("qty_end"));
        nfStockCard.setUnitCostEND(rs.getBigDecimal("unit_cost_end"));
        nfStockCard.setAmountEND(rs.getBigDecimal("amount_end"));

        /*INSERT PRODUCT*/
        com.app.wms.engine.db.dao.ProductDao productDao = DaoFactory.createProductDao();
        Product p = new Product();

        p = productDao.findId(rs.getString("product_id"));

        nfStockCard.setProduct(p);
        return nfStockCard;
    }

    public int insert(NonFishStockCardAccounting nfs) {
        return jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?)",
                nfs.getProductId(), nfs.getProductCategory(), nfs.getStockCardDate(), nfs.getCode(), nfs.getNumber(), nfs.getCurrencyCode(), nfs.getRateValue(), nfs.getAmount(), nfs.getAmountIDR(),
                nfs.getQuantityIN(), nfs.getUnitCostIN(), nfs.getAmountIN(), nfs.getQuantityOUT(), nfs.getUnitCostOUT(), nfs.getAmountOUT(), nfs.getQuantityEND(), nfs.getUnitCostEND(), nfs.getAmountEND());
    }

    public int update(NonFishStockCardAccounting nfs) {
        return jdbcTemplate.update("UPDATE " + getTableName() + " SET product_id = ?, product_category =  ?, stock_card_date = ?, code = ?, number = ?, currency_code = ? , rate_value = ? , amount = ? , "
                + "amount_IDR = ?, qty_in = ?, unit_cost_in = ? , amount_in = ? ,qty_out = ? ,unit_cost_out = ? ,amount_out = ? ,qty_end = ? ,unit_cost_end = ? ,amount_end = ? where id = ? ",
                nfs.getProductId(), nfs.getProductCategory(), nfs.getStockCardDate(), nfs.getCode(), nfs.getNumber(), nfs.getCurrencyCode(), nfs.getRateValue(), nfs.getAmount(), nfs.getAmountIDR(),
                nfs.getQuantityIN(), nfs.getUnitCostIN(), nfs.getAmountIN(), nfs.getQuantityOUT(), nfs.getUnitCostOUT(), nfs.getAmountOUT(), nfs.getQuantityEND(), nfs.getUnitCostEND(), nfs.getAmountEND(), nfs.getId());
    }

    public List<NonFishStockCardAccounting> findByProductCategoryAndDateAndDoctype(String productCategory, String date, String docType) {
        String queryString;
        queryString = "select * from nf_stock_card where product_category = '" + productCategory + "' AND code = '" + docType + "' AND "
                + "stock_card_date BETWEEN DATEADD(MONTH, DATEDIFF(MONTH, -1, '" + date + "') - 1, 0) AND '" + date + "' ";
        List<NonFishStockCardAccounting> list = jdbcTemplate.query(queryString, this);
        return list;
    }

    public boolean isExist(NonFishStockCardAccounting nfs) {
        boolean result = false;
        String dateString = df.format(nfs.getStockCardDate());
        String sqlQuery = " select count(*) from dbo.nf_stock_card where "
                + "product_id = '" + nfs.getProductId() + "' and stock_card_date = ' " + dateString + "' and code = '" + nfs.getCode() + "' and number = '" + nfs.getNumber() + "'";
        int temp = (int) jdbcTemplate.queryForInt(sqlQuery);
        if (temp > 0) {
            result = true;
        }
        return result;
    }

    public List<NonFishStockCardAccounting> findSummaryRR(String productCategory, String date) {
        String queryString;
        queryString = "select product_id, sum(amount_in) total from nf_stock_card where "
                + "product_category = '" + productCategory + "' AND code = 'RR' AND "
                + "stock_card_date BETWEEN "
                + "DATEADD(MONTH, DATEDIFF(MONTH, -1, '" + date + "') - 1, 0) AND '" + date + "' "
                + "group by product_id";
        List<NonFishStockCardAccounting> list = jdbcTemplate.query(queryString, this);
        return list;
    }

    public List<NonFishStockCardAccounting> findSummaryTS(String productCategory, String date) {
        String queryString;
        queryString = "select product_id, sum(amount_out) total from nf_stock_card where "
                + "product_category = '" + productCategory + "' AND code = 'TS' AND "
                + "stock_card_date BETWEEN "
                + "DATEADD(MONTH, DATEDIFF(MONTH, -1, '" + date + "') - 1, 0) AND '" + date + "' "
                + "group by product_id";
        List<NonFishStockCardAccounting> list = jdbcTemplate.query(queryString, this);
        return list;
    }

    public List<NonFishStockCardAccounting> findSummaryDR(String productCategory, String date) {
        String queryString;
        queryString = "select product_id, sum(amount_out) total from nf_stock_card where "
                + "product_category = '" + productCategory + "' AND code = 'DR' AND "
                + "stock_card_date BETWEEN "
                + "DATEADD(MONTH, DATEDIFF(MONTH, -1, '" + date + "') - 1, 0) AND '" + date + "' "
                + "group by product_id";
        List<NonFishStockCardAccounting> list = jdbcTemplate.query(queryString, this);
        return list;
    }

    public List<NonFishDocumentSummary> findPerDocumentType(String productCategory, String date, String docType) {
        List<NonFishDocumentSummary> result;
        String queryString;
        if (docType.equalsIgnoreCase("RR")) {
            queryString = "select product_id , sum(amount_in) total from nf_stock_card where "
                    + "product_category = '" + productCategory + "' AND code = '" + docType + "' AND "
                    + "stock_card_date BETWEEN "
                    + "DATEADD(MONTH, DATEDIFF(MONTH, -1, '" + date + "') - 1, 0) AND '" + date + "' "
                    + "group by product_id";
        } else {
            queryString = "select product_id , sum(amount_out) total from nf_stock_card where "
                    + "product_category = '" + productCategory + "' AND code = '" + docType + "' AND "
                    + "stock_card_date BETWEEN "
                    + "DATEADD(MONTH, DATEDIFF(MONTH, -1, '" + date + "') - 1, 0) AND '" + date + "' "
                    + "group by product_id";
        }
        result = jdbcTemplate.query(queryString, new NonFishDocumentSummaryListMap());
        return result;
    }

    public Integer exist(NonFishStockCardAccounting nfs) {
        Integer result = 0;
        String dateString = df.format(nfs.getStockCardDate());
        String sqlQuery = " select id from dbo.nf_stock_card where "
                + "product_id = '" + nfs.getProductId() + "' and stock_card_date = ' " + dateString + "' and code = '" + nfs.getCode() + "' and number = '" + nfs.getNumber() + "'";
        Integer temp = (Integer) jdbcTemplate.queryForInt(sqlQuery);
        if (temp != null) {
            result = temp;
        }
        return result;
    }

}

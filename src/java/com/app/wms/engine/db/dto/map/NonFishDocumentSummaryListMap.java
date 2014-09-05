package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dto.NonFishDocumentSummary;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class NonFishDocumentSummaryListMap implements ParameterizedRowMapper<NonFishDocumentSummary> {

    private final ProductDao productDao = DaoFactory.createProductDao();

    @Override
    public NonFishDocumentSummary mapRow(ResultSet rs, int row) throws SQLException {
        // TODO Auto-generated method stub
        NonFishDocumentSummary nfd = new NonFishDocumentSummary();
        nfd.setProductId(rs.getString("product_id"));
        nfd.setAmountIDR(rs.getBigDecimal("total"));
        /*SET PRODUCT */
        Product p = null;
        try {
            p = productDao.findByPrimaryKey(nfd.getProductId());
        } catch (ProductDaoException ex) {
            Logger.getLogger(NonFishDocumentSummaryListMap.class.getName()).log(Level.SEVERE, null, ex);
        }
        nfd.setProduct(p);

        return nfd;
    }

}

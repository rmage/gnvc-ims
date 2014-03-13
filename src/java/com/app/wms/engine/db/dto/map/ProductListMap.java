package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Product;
import java.sql.ResultSetMetaData;

public class ProductListMap implements ParameterizedRowMapper <Product>{

    @Override
    public Product mapRow(ResultSet rs, int row) throws SQLException {
            // TODO Auto-generated method stub

            Product p = new Product();
            p.setId(rs.getInt(1));
            p.setProductId(rs.getString(2));
            p.setProductCode(rs.getString(3));
            p.setProductName(rs.getString(4));
            p.setIsActive(rs.getString(5));
            p.setProductCategory(rs.getString("product_category"));
            p.setUpdatedBy(hasColumn(rs, "updated_by") ? rs.getString("updated_by") : null);
            p.setUpdatedDate(hasColumn(rs, "updated_date") ? rs.getDate("updated_date") : null);

            return p;
    }
    
    public static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.equals(rsmd.getColumnName(x))) {
                return true;
            }
        }
        return false;
    }

}

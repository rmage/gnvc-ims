package com.app.wms.engine.db.dto.map;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;


public class BundleDtlItemMap implements ParameterizedRowMapper<BundleDtlItem>
{

    public BundleDtlItem mapRow(ResultSet rs, int i) throws SQLException
    {
        BundleDtlItem dto = new BundleDtlItem();
        dto.setAliasCode(rs.getString("ALIAS_CODE"));
        dto.setBundleCode(rs.getString("BUNDLE_CODE"));
        dto.setBundleName(rs.getString("BUNDLE_NAME"));
        dto.setPriceCatalogCode(rs.getString("PRICE_CATALOG_CODE"));
        dto.setProductCatalog(rs.getString("PRODUCT_CATALOG"));
        dto.setCreatedBy(rs.getBigDecimal("CREATED_BY"));
        dto.setCreatedDate(rs.getDate("CREATED_DATE"));
        dto.setCustomerPrice(rs.getBigDecimal("CUSTOMER_PRICE"));
        dto.setItemCode(rs.getString("ITEM_CODE"));
        dto.setQuantity(rs.getBigDecimal("QUANTITY"));
        dto.setUpdatedBy(rs.getBigDecimal("UPDATE_BY"));
        dto.setUpdatedDate(rs.getDate("UPDATE_DATE"));
        
        return dto;
    }

}

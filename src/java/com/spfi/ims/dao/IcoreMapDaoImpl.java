package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.ProductCategory;
import com.app.wms.engine.db.dto.map.ProductListMap;
import com.app.wms.engine.db.exceptions.ProductCategoryDaoException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class IcoreMapDaoImpl extends AbstractDAO implements IcoreMapDao {
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "icore_map";
    }

    public List<Map<String, Object>> findWhereProductCodeEquals(String productCategory, String source) {        
        try {
            return jdbcTemplate.queryForList("EXEC IC_ICOREMAP_GET_BY_PRODUCT_CODE ?, ?", productCategory, source);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public void insertNUpdate(String productCode, String hsCode, String sourcePro, String createdDate, String createdBy, String updatedDate, String updatedBy, String isActive) {
        jdbcTemplate.update("IC_ICOREMAP_MERGE_UPDATE_INSERT ?, ?, ?, ?, ?, ?, ?, ?", productCode, hsCode, sourcePro, createdDate, createdBy, updatedDate, updatedBy, isActive);
    }

    public List<Map<String, Object>> findProductCategory() {
        try {
            return jdbcTemplate.queryForList("EXEC IC_ICOREMAP_GET_PRODUCT_CATEGORY");
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
}

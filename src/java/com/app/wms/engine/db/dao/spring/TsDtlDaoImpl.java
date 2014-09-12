package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.TsDtlDao;
import com.app.wms.engine.db.dto.TsDtl;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class TsDtlDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<TsDtl>, TsDtlDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "ts_detail";
    }
    
    public TsDtl mapRow(ResultSet rs, int i) throws SQLException {
        TsDtl td = new TsDtl();
        td.setId(rs.getInt("id"));
        td.setTsCode(rs.getString("ts_code"));
        td.setProductCode(rs.getString("product_code"));
        td.setQty(rs.getBigDecimal("qty"));
        td.setCreatedBy(rs.getString("created_by"));
        td.setCreatedDate(rs.getDate("created_date"));
        td.setUpdatedBy(rs.getString("updated_by"));
        td.setUpdatedDate(rs.getDate("updated_date"));
        
        return td;
    }
    
    public void insert(TsDtl td) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?)", 
            td.getTsCode(), td.getProductCode(), td.getQty(), td.getCreatedBy(), td.getCreatedDate(), null, null);
    }
    
}

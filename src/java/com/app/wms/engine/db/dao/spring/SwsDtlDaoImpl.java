package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.SwsDtlDao;
import com.app.wms.engine.db.dto.ReceiveReport;
import com.app.wms.engine.db.dto.SwsDtl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class SwsDtlDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<SwsDtl>, SwsDtlDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "sws_detail";
    }
    
    public SwsDtl mapRow(ResultSet rs, int i) throws SQLException {
        SwsDtl sd = new SwsDtl();
        sd.setId(rs.getInt("id"));
        sd.setSwsCode(rs.getInt("sws_code"));
        sd.setProductCode(rs.getString("product_code"));
        sd.setQty(rs.getInt("qty"));
        sd.setSoh(rs.getInt("soh"));
        sd.setUom(rs.getString("uom"));
        sd.setCreatedBy(rs.getString("created_by"));
        sd.setCreatedDate(rs.getDate("created_date"));
        sd.setUpdatedBy(rs.getString("updated_by"));
        sd.setUpdatedDate(rs.getDate("updated_date"));
        
        return sd;
    }
    
    public void insert(SwsDtl sd) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)", 
            sd.getSwsCode(), sd.getProductCode(), sd.getQty(), sd.getSoh(), sd.getUom(), sd.getCreatedBy(),
            sd.getCreatedDate(), null, null);
    }
    
    public List<SwsDtl> findBySws(int swsCode) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE sws_code = ?", this, swsCode);
    }

}

package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.DrDtlDao;
import com.app.wms.engine.db.dto.DrDtl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class DrDtlDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<DrDtl>, DrDtlDao {

    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "dr_detail";
    }
    
    public DrDtl mapRow(ResultSet rs, int i) throws SQLException {
        DrDtl dd = new DrDtl();
        dd.setId(rs.getInt("id"));
        dd.setDrCode(rs.getInt("dr_code"));
        dd.setDrQty(rs.getBigDecimal("dr_qty"));
        dd.setDrUom(rs.getString("dr_uom"));
        dd.setDrUnitCost(rs.getBigDecimal("dr_unitcost"));
        dd.setProductCode(rs.getString("product_code"));
        dd.setCreatedBy(rs.getString("created_by"));
        dd.setCreatedDate(rs.getDate("created_date"));
        dd.setUpdatedBy(rs.getString("updated_by"));
        dd.setUpdatedDate(rs.getDate("updated_date"));
        
        return dd;
    }
    
    public void insert(DrDtl dd) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)",
            dd.getDrCode(), dd.getDrQty(), dd.getDrUom(), dd.getDrUnitCost(), dd.getProductCode(), dd.getCreatedBy(),
            dd.getCreatedDate(), null, null);
    }
    
    public List<DrDtl> findByDR(int drCode) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE dr_code = ?", this, drCode);
    }

}

package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.ReturnCargoDrDtlDao;
import com.app.wms.engine.db.dto.ReturnCargoDrDtl;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class ReturnCargoDrDtlDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<ReturnCargoDrDtl>, ReturnCargoDrDtlDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "inventory..rrc_dr_detail";
    }

    public ReturnCargoDrDtl mapRow(ResultSet rs, int i) throws SQLException {
        ReturnCargoDrDtl rcdd = new ReturnCargoDrDtl();
        rcdd.setId(rs.getInt("id"));
        rcdd.setRrCode(rs.getString("rr_code"));
        rcdd.setProductCode(rs.getString("product_code"));
        rcdd.setDepartmentCode(rs.getString("department_code"));
        rcdd.setRrQty(rs.getBigDecimal("rr_qty"));
        rcdd.setRrUom(rs.getString("rr_uom"));
        rcdd.setRrUnitCost(rs.getBigDecimal("rr_unitcost"));
        rcdd.setCreatedBy(rs.getString("created_by"));
        rcdd.setCreatedDate(rs.getDate("created_date"));
        rcdd.setUpdatedBy(rs.getString("updated_by"));
        rcdd.setUpdatedDate(rs.getDate("updated_date"));
        return rcdd;
    }
    
}

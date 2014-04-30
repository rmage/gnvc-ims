package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.ReturnCargoDrDao;
import com.app.wms.engine.db.dto.ReturnCargoDr;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class ReturnCargoDrDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<ReturnCargoDr>, ReturnCargoDrDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "inventory..rrc_dr";
    }

    public ReturnCargoDr mapRow(ResultSet rs, int i) throws SQLException {
        ReturnCargoDr rcd = new ReturnCargoDr();
        rcd.setRrCode(rs.getString("rr_code"));
        rcd.setRrDate(rs.getDate("rr_date"));
        rcd.setDrCode(rs.getString("dr_code"));
        rcd.setRrFrom(rs.getString("rr_from"));
        rcd.setReceivedBy(rs.getString("received_by"));
        rcd.setReceivedDate(rs.getDate("received_date"));
        rcd.setEvaluatedBy(rs.getString("evaluated_by"));
        rcd.setEvaluatedDate(rs.getDate("evaluated_date"));
        rcd.setApprovedBy(rs.getString("approved_by"));
        rcd.setApprovedDate(rs.getDate("approved_date"));
        rcd.setCreatedBy(rs.getString("created_by"));
        rcd.setCreatedDate(rs.getDate("created_date"));
        rcd.setUpdatedBy(rs.getString("updated_by"));
        rcd.setUpdatedDate(rs.getDate("updated_date"));
        return rcd;
    }
    
}

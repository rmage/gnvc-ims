package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.EdsDao;
import com.app.wms.engine.db.dto.Eds;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class EdsDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<Eds>, EdsDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "inventory..eds";
    }
    
    public Eds mapRow(ResultSet rs, int i) throws SQLException {
        Eds e = new Eds();
        
        e.setEdsCode(rs.getInt("eds_code"));
        e.setEdsDate(rs.getDate("eds_date"));
        e.setEdsRemarks(rs.getString("eds_remarks"));
        e.setBorCode(rs.getString("bor_code"));
        e.setEdsVan(rs.getString("eds_van"));
        e.setEdsSeal(rs.getString("eds_seal"));
        e.setEdsVessel(rs.getString("eds_vessel"));
        e.setEdsPlatNo(rs.getString("eds_platno"));
        e.setEdsTimeIn(rs.getString("eds_timein"));
        e.setEdsTimeOut(rs.getString("eds_timeout"));
        e.setEdsDriver(rs.getString("eds_driver"));
        e.setEdsCi(rs.getString("eds_ci"));
        e.setIssuedBy(rs.getString("issued_by"));
        e.setIssuedDate(rs.getDate("issued_date"));
        e.setCheckedBy(rs.getString("checked_by"));
        e.setCheckedDate(rs.getDate("checked_date"));
        e.setApprovedBy(rs.getString("approved_by"));
        e.setApprovedDate(rs.getDate("approved_date"));
        e.setCreatedBy(rs.getString("created_by"));
        e.setCreatedDate(rs.getDate("created_date"));
        e.setUpdatedBy(rs.getString("updated_by"));
        e.setUpdatedDate(rs.getDate("updated_date"));
        
        return e;
    }
    
    public void insert(Eds e) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            e.getEdsCode(), e.getEdsDate(), e.getEdsRemarks(), e.getBorCode(), e.getEdsVan(), e.getEdsSeal(), e.getEdsVessel(),
            e.getEdsPlatNo(), e.getEdsTimeIn(), e.getEdsTimeOut(), e.getEdsDriver(), e.getEdsCi(), null, null, null, null,
            null, null, e.getCreatedBy(), e.getCreatedDate(), null, null);
    }
    
    public List<Eds> findAll() {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " ORDER BY (CASE WHEN issued_by IS NULL THEN 0 ELSE 1 END), created_date DESC", this);
    }
    
}

package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.OfalDtlDao;
import com.app.wms.engine.db.dto.OfalDtl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class OfalDtlDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<OfalDtl>, OfalDtlDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "inventory..ofal_detail";
    }
    
    public OfalDtl mapRow(ResultSet rs, int i) throws SQLException {
        OfalDtl od = new OfalDtl();
        od.setId(rs.getInt("id"));
        od.setOfalId(rs.getInt("ofal_id"));
        od.setPtsCode(rs.getInt("pts_code"));
        od.setCreatedBy(rs.getString("created_by"));
        od.setCreatedDate(rs.getDate("created_date"));
        od.setUpdatedBy(rs.getString("updated_by"));
        od.setUpdatedDate(rs.getDate("updated_date"));
        
        return od;
    }
    
    public void insert(OfalDtl od) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?)", 
            od.getOfalId(), od.getPtsCode(), od.getCreatedBy(), od.getCreatedDate(), null, null);
    }
    
    public List<OfalDtl> findByOfal(int ofalId) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE ofal_id = ?", this, ofalId);
    }
    
}

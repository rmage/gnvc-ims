package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import com.spfi.ims.dto.SystemOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class SystemOptionDaoImpl extends AbstractDAO implements SystemOptionDao, ParameterizedRowMapper<SystemOption> {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    @Override
    public SystemOption mapRow(ResultSet rs, int i) throws SQLException {
        SystemOption so = new SystemOption();
        so.setId(rs.getInt("id"));
        so.setCode(rs.getString("code"));
        so.setName(rs.getString("name"));
        so.setValue(rs.getString("value"));
        so.setIsEditable(rs.getInt("is_editable"));
        so.setType(rs.getInt("type"));
        return so;
    }
    
    @Override
    public void postUpdateValue(int id, String value) {
        jdbcTemplate.update("UPDATE system_option SET value = ? WHERE id = ?", value, id);
    }
    
    @Override
    public SystemOption findByCode (String code) {
        List<SystemOption> soList = jdbcTemplate.query("SELECT * FROM system_option WHERE code = ?", this, code);
        return soList.isEmpty() ? null : soList.get(0);
    }
    
    @Override
    public List<SystemOption> getEditableOptions() {
        return jdbcTemplate.query("SELECT * FROM system_option WHERE is_editable = 1 ORDER BY name", this);
    }
    
}

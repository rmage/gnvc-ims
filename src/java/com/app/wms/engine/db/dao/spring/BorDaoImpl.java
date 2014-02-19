package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.BorDao;
import com.app.wms.engine.db.dto.Bor;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class BorDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<Bor>, BorDao {

    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "inventory..bor";
    }
    
    public Bor mapRow(ResultSet rs, int i) throws SQLException {
        Bor b = new Bor();
        
        b.setBorCode(rs.getString("bor_code"));
        b.setBorDate(rs.getDate("bor_date"));
        b.setPreparedBy(rs.getString("prepared_by"));
        b.setPreparedDate(rs.getDate("prepared_date"));
        b.setReviewedBy(rs.getString("reviewed_by"));
        b.setReviewedDate(rs.getDate("reviewed_date"));
        b.setCreatedBy(rs.getString("created_by"));
        b.setCreatedDate(rs.getDate("created_date"));
        b.setUpdatedBy(rs.getString("updated_by"));
        b.setUpdatedDate(rs.getDate("updated_date"));
        
        return b;
    }
    
    public void insert(Bor b) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            b.getBorCode(), b.getBorDate(), null, null, null, null, b.getCreatedBy(), b.getCreatedDate(), null, null);
    }
    
    public void approval(Bor b, String mode) {
        if(mode.equals("p"))
            jdbcTemplate.update("UPDATE " + getTableName() + " SET prepared_by = ?, prepared_date = ?, updated_by = ?, updated_date = ? WHERE bor_code = ?", 
                b.getPreparedBy(), b.getPreparedDate(), b.getUpdatedBy(), b.getUpdatedDate(), b.getBorCode());
        else if(mode.equals("r"))
            jdbcTemplate.update("UPDATE " + getTableName() + " SET reviewed_by = ?, reviewed_date = ?, updated_by = ?, updated_date = ? WHERE bor_code = ?", 
                b.getReviewedBy(), b.getReviewedDate(), b.getUpdatedBy(), b.getUpdatedDate(), b.getBorCode());
    }
    
    public List<Bor> findAll() {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " ORDER BY (CASE WHEN prepared_by IS NULL THEN 0 ELSE 1 END), " +
            "(CASE WHEN reviewed_by IS NULL THEN 0 ELSE 1 END), created_date DESC", this);
    }

}

package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.PtsDtlDao;
import com.app.wms.engine.db.dto.PtsDtl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class PtsDtlDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<PtsDtl>, PtsDtlDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "pts_detail";
    }
    
    public PtsDtl mapRow(ResultSet rs, int i) throws SQLException {
        PtsDtl td = new PtsDtl();
        td.setId(rs.getInt("id"));
        td.setPtsCode(rs.getInt("pts_code"));
        td.setPtsShift(rs.getString("pts_shift"));
        td.setPtsDate((rs.getDate("pts_date")));
        td.setPtsProdBatch(rs.getString("pts_prodbatch"));
        td.setPtsBasket(rs.getString("pts_basket"));
        td.setPtsQty(rs.getBigDecimal("pts_qty"));
        td.setCreatedBy(rs.getString("created_by"));
        td.setCreatedDate(rs.getDate("created_date"));
        td.setUpdatedBy(rs.getString("updated_by"));
        td.setUpdatedDate(rs.getDate("updated_date"));
        
        return td;
    }
    
    public void insert(PtsDtl pd) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
            pd.getPtsCode(), pd.getPtsShift(), pd.getPtsDate(), pd.getPtsProdBatch(), pd.getPtsBasket(), pd.getPtsQty(),
            pd.getCreatedBy(), pd.getCreatedDate(), null, null);
    }
    
    public List<PtsDtl> findByPts(int ptsCode) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE pts_code = ?", this, ptsCode);
    }
    
}

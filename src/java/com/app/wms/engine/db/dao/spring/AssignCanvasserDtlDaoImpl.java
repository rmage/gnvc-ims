package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.AssignCanvasserDtlDao;
import com.app.wms.engine.db.dto.AssignCanvasserDtl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class AssignCanvasserDtlDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<AssignCanvasserDtl>, AssignCanvasserDtlDao {
    
    private SimpleJdbcTemplate jdbcTemplate;

    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "inventory..assign_canv_dtl";
    }
    
    public AssignCanvasserDtl mapRow(ResultSet rs, int row) throws SQLException {
        AssignCanvasserDtl cd = new AssignCanvasserDtl();
        cd.setId(rs.getInt("id"));
        cd.setPrsNumber(rs.getString("prsnumber"));
        cd.setProductCode(rs.getString("productcode"));
        cd.setUserId(rs.getString("user_id"));
        return cd;
    }
    
    public int insert(AssignCanvasserDtl cd) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?)", cd.getPrsNumber(), cd.getProductCode(), cd.getUserId());
        return jdbcTemplate.queryForInt("select @@IDENTITY");
    }
    
    public List<AssignCanvasserDtl> findByPrsnumber(String prsNumber) {
        return jdbcTemplate.query("SELECT id, prsnumber, productcode, user_id FROM " + getTableName() + " WHERE prsnumber = ?", this, prsNumber);
    }
    
    public List<AssignCanvasserDtl> findByUserId(String userId) {
        return jdbcTemplate.query("SELECT acd.id, acd.prsnumber, acd.productcode, acd.user_id FROM " + getTableName() + " acd " +
            "LEFT JOIN assign_canv_prc acp ON acp.prsnumber = acd.prsnumber AND acp.productcode = acd.productcode " +
            "WHERE acp.id IS NULL AND acd.user_id = ?", this, userId);
    }
    
}

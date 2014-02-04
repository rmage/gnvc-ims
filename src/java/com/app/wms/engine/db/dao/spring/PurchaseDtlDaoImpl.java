package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.PurchaseDtlDao;
import com.app.wms.engine.db.dto.PurchaseDtl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class PurchaseDtlDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<PurchaseDtl>, PurchaseDtlDao {

    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "inventory..po_detail";
    }
    
    public PurchaseDtl mapRow(ResultSet rs, int i) throws SQLException {
        PurchaseDtl pd = new PurchaseDtl();
        pd.setId(rs.getInt("id"));
        pd.setPoCode(rs.getInt("po_code"));
        pd.setPrsNumber(rs.getString("prsnumber"));
        pd.setProductCode(rs.getString("product_code"));
        pd.setDepartmentCode(rs.getString("department_code"));
        pd.setSubTotal(rs.getBigDecimal("sub_total"));
        pd.setCreatedBy(rs.getString("created_by"));
        pd.setCreatedDate(rs.getDate("created_date"));
        pd.setUpdatedBy(rs.getString("updated_by"));
        pd.setUpdatedDate(rs.getDate("updated_date"));
        
        return pd;
    }

    public void insert(PurchaseDtl pd) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)", 
            pd.getPoCode(), pd.getPrsNumber(), pd.getProductCode(), pd.getDepartmentCode(), pd.getSubTotal(), 
            pd.getCreatedBy(), pd.getCreatedDate(), null, null);
    }
    
    public List<PurchaseDtl> findByPo(int poCode) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE po_code = ?", this, poCode);
    }
    
}

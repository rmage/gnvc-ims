package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.BorDtlDao;
import com.app.wms.engine.db.dto.BorDtl;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class BorDtlDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<BorDtl>, BorDtlDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "inventory..bor_detail";
    }
    
    public BorDtl mapRow(ResultSet rs, int i) throws SQLException {
        BorDtl bd = new BorDtl();
        
        return bd;
    }
    
    public void insert(BorDtl bd) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            bd.getBorCode(), bd.getBorPackStyle(), bd.getBorCanSize(), bd.getBorQty(), bd.getBorCs(), bd.getBorCnfPrice(), bd.getBorCommission(), bd.getBorBuyer(),
            bd.getBorBrand(), bd.getBorShipmentDate(), bd.getBorDestinationPort(), bd.getBorPoNumber(), bd.getBorODpw(), bd.getBorOFt(), bd.getBorOPt(), 
            bd.getBorOGf(), bd.getBorOCcm(), bd.getBorOOm(), bd.getBorOGmo(), bd.getBorOEc(), bd.getBorOPf(), bd.getBorOOwr(), bd.getBorONol(), bd.getBorOInfo(),
            bd.getCreatedBy(), bd.getCreatedDate(), bd.getUpdatedBy(), bd.getUpdatedDate());
    }
    
}

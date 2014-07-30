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
        return "bor_detail";
    }
    
    public BorDtl mapRow(ResultSet rs, int i) throws SQLException {
        BorDtl bd = new BorDtl();
        bd.setId(rs.getInt("id"));
        bd.setBorCode(rs.getString("bor_code"));
        bd.setBorPackStyle(rs.getString("bor_packstyle"));
        bd.setBorCanSize(rs.getString("bor_cansize"));
        bd.setBorQty(rs.getBigDecimal("bor_qty"));
        bd.setBorCs(rs.getBigDecimal("bor_cs"));
        bd.setBorCnfPrice(rs.getBigDecimal("bor_cnfprice"));
        bd.setBorCommission(rs.getString("bor_commission"));
        bd.setBorBuyer(rs.getString("bor_buyer"));
        bd.setBorBrand(rs.getString("bor_brand"));
        bd.setBorShipmentDate(rs.getString("bor_shipmentdate"));
        bd.setBorDestinationPort(rs.getString("bor_destinationport"));
        bd.setBorPoNumber(rs.getString("bor_ponumber"));
        bd.setBorODpw(rs.getBigDecimal("bor_o_dpw"));
        bd.setBorOFt(rs.getString("bor_o_ft"));
        bd.setBorOPt(rs.getString("bor_o_pt"));
        bd.setBorOGf(rs.getString("bor_o_gf"));
        bd.setBorOCcm(rs.getString("bor_o_ccm"));
        bd.setBorOOm(rs.getString("bor_o_om"));
        bd.setBorOGmo(rs.getString("bor_o_gmo"));
        bd.setBorOEc(rs.getString("bor_o_ec"));
        bd.setBorOPf(rs.getString("bor_o_pf"));
        bd.setBorOOwr(rs.getString("bor_o_owr"));
        bd.setBorONol(rs.getString("bor_o_nol"));
        bd.setBorOInfo(rs.getString("bor_o_info"));
        bd.setCreatedBy(rs.getString("created_by"));
        bd.setCreatedDate(rs.getDate("created_date"));
        bd.setUpdatedBy(rs.getString("updated_by"));
        bd.setUpdatedDate(rs.getDate("updated_date"));
        
        return bd;
    }
    
    public void insert(BorDtl bd) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            bd.getBorCode(), bd.getBorPackStyle(), bd.getBorCanSize(), bd.getBorQty(), bd.getBorCs(), bd.getBorCnfPrice(), bd.getBorCommission(), bd.getBorBuyer(),
            bd.getBorBrand(), bd.getBorShipmentDate(), bd.getBorDestinationPort(), bd.getBorPoNumber(), bd.getBorODpw(), bd.getBorOFt(), bd.getBorOPt(), 
            bd.getBorOGf(), bd.getBorOCcm(), bd.getBorOOm(), bd.getBorOGmo(), bd.getBorOEc(), bd.getBorOPf(), bd.getBorOOwr(), bd.getBorONol(), bd.getBorOInfo(),
            bd.getCreatedBy(), bd.getCreatedDate(), bd.getUpdatedBy(), bd.getUpdatedDate());
    }
    
    public List<BorDtl> findByBor(String borCode) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE bor_code = ?", this, borCode);
    }
    
}

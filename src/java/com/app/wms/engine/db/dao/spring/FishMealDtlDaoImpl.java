package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.FishMealDtlDao;
import com.app.wms.engine.db.dto.FishMealDtl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class FishMealDtlDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<FishMealDtl>, FishMealDtlDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "inventory..fishmeal_detail";
    }
    
    public FishMealDtl mapRow(ResultSet rs, int i) throws SQLException {
        FishMealDtl fmd = new FishMealDtl();
        fmd.setId(rs.getInt("id"));
        fmd.setFmId(rs.getInt("fm_id"));
        fmd.setFmDate(rs.getDate("fm_date"));
        fmd.setFmBiBags(rs.getInt("fm_bi_bags"));
        fmd.setFmBiKilos(rs.getInt("fm_bi_kilos"));
        fmd.setFmRS1Rm(rs.getInt("fm_r_s1_rm"));
        fmd.setFmRS1Bags(rs.getInt("fm_r_s1_bags"));
        fmd.setFmRS1Kilos(rs.getInt("fm_r_s1_kilos"));
        fmd.setFmRS2Rm(rs.getInt("fm_r_s2_rm"));
        fmd.setFmRS2Bags(rs.getInt("fm_r_s2_bags"));
        fmd.setFmRS2Kilos(rs.getInt("fm_r_s2_kilos"));
        fmd.setFmRS3Rm(rs.getInt("fm_r_s3_rm"));
        fmd.setFmRS3Bags(rs.getInt("fm_r_s3_bags"));
        fmd.setFmRS3Kilos(rs.getInt("fm_r_s3_kilos"));
        fmd.setFmTtdBags(rs.getInt("fm_ttd_bags"));
        fmd.setFmTtdKilos(rs.getInt("fm_ttd_kilos"));
        fmd.setFmIBags(rs.getInt("fm_i_bags"));
        fmd.setFmIKilos(rs.getInt("fm_i_kilos"));
        fmd.setFmIPrice(rs.getBigDecimal("fm_i_price"));
        fmd.setFmEiBags(rs.getInt("fm_ei_bags"));
        fmd.setFmEiKilos(rs.getInt("fm_ei_kilos"));
        fmd.setFmRMhrs(rs.getString("fm_r_mhrs"));
        fmd.setFmROthr(rs.getString("fm_r_othr"));
        fmd.setCreatedBy(rs.getString("created_by"));
        fmd.setCreatedDate(rs.getDate("created_date"));
        fmd.setUpdatedBy(rs.getString("updated_by"));
        fmd.setUpdatedDate(rs.getDate("updated_date"));
        
        return fmd;
    }
    
    public int insert(FishMealDtl fmd) {
        return jdbcTemplate.queryForInt("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); SELECT @@IDENTITY;",
            fmd.getFmId(), fmd.getFmDate(), fmd.getFmBiBags(), fmd.getFmBiKilos(), fmd.getFmRS1Rm(), fmd.getFmRS1Bags(), fmd.getFmRS1Kilos(), fmd.getFmRS2Rm(),
            fmd.getFmRS2Bags(), fmd.getFmRS2Kilos(), fmd.getFmRS3Rm(), fmd.getFmRS3Bags(), fmd.getFmRS3Kilos(), fmd.getFmTtdBags(), fmd.getFmTtdKilos(), fmd.getFmIBags(),
            fmd.getFmIKilos(), fmd.getFmIPrice(), fmd.getFmEiBags(), fmd.getFmEiKilos(), fmd.getFmRMhrs(), fmd.getFmROthr(), fmd.getCreatedBy(), fmd.getCreatedDate(), null, null);
    }
    
    public void update(FishMealDtl fmd) {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET fm_id = ?, fm_date = ?, fm_bi_bags = ?, fm_bi_kilos = ?, fm_r_s1_rm = ?, fm_r_s1_bags = ?, fm_r_s1_kilos = ?, fm_r_s2_rm = ?,"
            + "fm_r_s2_bags = ?, fm_r_s2_kilos = ?, fm_r_s3_rm = ?, fm_r_s3_bags = ?, fm_r_s3_kilos = ?, fm_ttd_bags = ?, fm_ttd_kilos = ?, fm_i_bags = ?, fm_i_kilos = ?, fm_i_price = ?, "
            + "fm_ei_bags = ?, fm_ei_kilos = ?, fm_r_mhrs = ?, fm_r_othr = ?, updated_by = ?, updated_date = ? WHERE id = ?", fmd.getFmId(), fmd.getFmDate(), fmd.getFmBiBags(), fmd.getFmBiKilos(), 
            fmd.getFmRS1Rm(), fmd.getFmRS1Bags(), fmd.getFmRS1Kilos(), fmd.getFmRS2Rm(), fmd.getFmRS2Bags(), fmd.getFmRS2Kilos(), fmd.getFmRS3Rm(), fmd.getFmRS3Bags(), fmd.getFmRS3Kilos(),
            fmd.getFmTtdBags(), fmd.getFmTtdKilos(), fmd.getFmIBags(), fmd.getFmIKilos(), fmd.getFmIPrice(), fmd.getFmEiBags(), fmd.getFmEiKilos(), fmd.getFmRMhrs(), fmd.getFmROthr(), 
            fmd.getUpdatedBy(), fmd.getUpdatedDate(), fmd.getId());
    }
    
    public FishMealDtl findLastDate(int year, int month) {
        List<FishMealDtl> fmds = jdbcTemplate.query("SELECT fd.* FROM " + getTableName() + " fd " +
            "INNER JOIN fishmeal f ON f.fm_id = fd.fm_id " +
            "WHERE f.fm_year = ? AND f.fm_month = ? ORDER BY fd.fm_date DESC", 
            this, year, month);
        
        return fmds.get(0);
    }
    
    public List<FishMealDtl> findCurrentMonth(int year, int month, int endDate) {
        return jdbcTemplate.query("SELECT fd.* FROM " + getTableName() + " fd INNER JOIN fishmeal f ON f.fm_id = fd.fm_id " +
            "WHERE f.fm_year = ? AND f.fm_month = ? AND DAY(fd.fm_date) BETWEEN 1 AND ? ORDER BY fd.fm_date ASC", this, year, month, endDate);
    }
    
}

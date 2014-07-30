package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.FishOilDtlDao;
import com.app.wms.engine.db.dto.FishOilDtl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class FishOilDtlDaoImpl extends AbstractDAO implements ParameterizedRowMapper<FishOilDtl>, FishOilDtlDao {

    private SimpleJdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "fishoil_detail";
    }

    public FishOilDtl mapRow(ResultSet rs, int i) throws SQLException {
        FishOilDtl fsd = new FishOilDtl();
        fsd.setId(rs.getInt("id"));
        fsd.setFoId(rs.getInt("fo_id"));
        fsd.setFoDate(rs.getDate("fo_date"));
        fsd.setFoBiDrums(rs.getInt("fo_bi_drums"));
        fsd.setFoBiKilos(rs.getInt("fo_bi_kilos"));
        fsd.setFoRS1Rm(rs.getInt("fo_r_s1_rm"));
        fsd.setFoRS1Drums(rs.getInt("fo_r_s1_drums"));
        fsd.setFoRS1Kilos(rs.getInt("fo_r_s1_kilos"));
        fsd.setFoRS2Rm(rs.getInt("fo_r_s2_rm"));
        fsd.setFoRS2Drums(rs.getInt("fo_r_s2_drums"));
        fsd.setFoRS2Kilos(rs.getInt("fo_r_s2_kilos"));
        fsd.setFoRS3Rm(rs.getInt("fo_r_s2_rm"));
        fsd.setFoRS3Drums(rs.getInt("fo_r_s2_drums"));
        fsd.setFoRS3Kilos(rs.getInt("fo_r_s2_kilos"));
        fsd.setFoTtdDrums(rs.getInt("fo_ttd_drums"));
        fsd.setFoTtdKilos(rs.getInt("fo_ttd_kilos"));
        fsd.setFoIDrums(rs.getInt("fo_i_drums"));
        fsd.setFoIKilos(rs.getInt("fo_i_kilos"));
        fsd.setFoIPrice(rs.getBigDecimal("fo_i_price"));
        fsd.setFoEiDrums(rs.getInt("fo_ei_drums"));
        fsd.setFoEiKilos(rs.getInt("fo_ei_kilos"));
        fsd.setFoRMhrs(rs.getString("fo_r_mhrs"));
        fsd.setFoROthr(rs.getString("fo_r_othr"));
        fsd.setCreatedBy(rs.getString("created_by"));
        fsd.setCreatedDate(rs.getDate("created_date"));
        fsd.setUpdatedBy(rs.getString("updated_by"));
        fsd.setUpdatedDate(rs.getDate("updated_date"));
        return fsd;
    }

    public List<FishOilDtl> findCurrentMonth(int year, int month, int endDate) {
        return jdbcTemplate.query("SELECT fd.* FROM " + getTableName() + " fd INNER JOIN fishoil f ON f.fo_id = fd.fo_id "
                + "WHERE f.fo_year = ? AND f.fo_month = ? AND DAY(fd.fo_date) BETWEEN 1 AND ? ORDER BY fd.fo_date ASC", this, year, month, endDate);
    }

    public FishOilDtl findLastDate(int year, int month) {
        List<FishOilDtl> fods = jdbcTemplate.query("SELECT fd.* FROM " + getTableName() + " fd "
                + "INNER JOIN fishoil f ON f.fo_id = fd.fo_id "
                + "WHERE f.fo_year = ? AND f.fo_month = ? ORDER BY fd.fo_date DESC",
                this, year, month);

        return fods.isEmpty() ? null : fods.get(0);
    }

    public int insert(FishOilDtl fod) {
        return jdbcTemplate.queryForInt("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); SELECT @@IDENTITY;",
                fod.getFoId(), fod.getFoDate(), fod.getFoBiDrums(), fod.getFoBiKilos(), fod.getFoRS1Rm(), fod.getFoRS1Drums(), fod.getFoRS1Kilos(), fod.getFoRS2Rm(),
                fod.getFoRS2Drums(), fod.getFoRS2Kilos(), fod.getFoRS3Rm(), fod.getFoRS3Drums(), fod.getFoRS3Kilos(), fod.getFoTtdDrums(), fod.getFoTtdKilos(), fod.getFoIDrums(),
                fod.getFoIKilos(), fod.getFoIPrice(), fod.getFoEiDrums(), fod.getFoEiKilos(), fod.getFoRMhrs(), fod.getFoROthr(), fod.getCreatedBy(), fod.getCreatedDate(), null, null);
    }

    public int update(FishOilDtl fod) {
        return jdbcTemplate.update("UPDATE " + getTableName() + " SET fo_id = ?, fo_date = ?, fo_bi_bags = ?, fo_bi_kilos = ?, fo_r_s1_rm = ?, fo_r_s1_bags = ?, fo_r_s1_kilos = ?, fo_r_s2_rm = ?,"
                + "fo_r_s2_bags = ?, fo_r_s2_kilos = ?, fo_r_s3_rm = ?, fo_r_s3_bags = ?, fo_r_s3_kilos = ?, fo_ttd_bags = ?, fo_ttd_kilos = ?, fo_i_bags = ?, fo_i_kilos = ?, fo_i_price = ?, "
                + "fo_ei_bags = ?, fo_ei_kilos = ?, fo_r_mhrs = ?, fo_r_othr = ?, updated_by = ?, updated_date = ? WHERE id = ?", fod.getFoId(), fod.getFoDate(), fod.getFoBiDrums(), fod.getFoBiKilos(),
                fod.getFoRS1Rm(), fod.getFoRS1Drums(), fod.getFoRS1Kilos(), fod.getFoRS2Rm(), fod.getFoRS2Drums(), fod.getFoRS2Kilos(), fod.getFoRS3Rm(), fod.getFoRS3Drums(), fod.getFoRS3Kilos(),
                fod.getFoTtdDrums(), fod.getFoTtdKilos(), fod.getFoIDrums(), fod.getFoIKilos(), fod.getFoIPrice(), fod.getFoEiDrums(), fod.getFoEiKilos(), fod.getFoRMhrs(), fod.getFoROthr(),
                fod.getUpdatedBy(), fod.getUpdatedDate(), fod.getId());
    }
}

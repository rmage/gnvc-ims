package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.OfalDao;
import com.app.wms.engine.db.dto.Ofal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class OfalDaoImpl extends AbstractDAO 
    implements ParameterizedRowMapper<Ofal>, OfalDao {
    
    private SimpleJdbcTemplate jdbcTemplate;
    
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public String getTableName() {
        return "ofal";
    }
    
    public Ofal mapRow(ResultSet rs, int i) throws SQLException {
        Ofal o = new Ofal();
        o.setOfalId(rs.getInt("ofal_id"));
        o.setOfalLabelNw(rs.getString("ofal_labelnw"));
        o.setOfalLabelDw(rs.getString("ofal_labeldw"));
        o.setBorCode(rs.getString("bor_code"));
        o.setBorId(rs.getInt("bor_id"));
        o.setOfalShipment(rs.getString("ofal_shipment"));
        o.setPreparedBy(rs.getString("prepared_by"));
        o.setPreparedDate(rs.getDate("prepared_date"));
        o.setReviewedBy(rs.getString("reviewed_by"));
        o.setReviewedDate(rs.getDate("reviewed_date"));
        o.setCheckedBy(rs.getString("checked_by"));
        o.setCheckedDate(rs.getDate("checked_date"));
        o.setApprovedBy(rs.getString("approved_by"));
        o.setApprovedDate(rs.getDate("approved_date"));
        o.setCreatedBy(rs.getString("created_by"));
        o.setCreatedDate(rs.getDate("created_date"));
        o.setUpdatedBy(rs.getString("updated_by"));
        o.setUpdatedDate(rs.getDate("updated_date"));
        
        return o;
    }
    
    public int insert(Ofal o) {
        return jdbcTemplate.queryForInt("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); SELECT @@IDENTITY;",
            o.getOfalLabelNw(), o.getOfalLabelDw(), o.getBorCode(), o.getBorId(), o.getOfalShipment(), null, null, null, null, null, null, null, null,
            o.getCreatedBy(), o.getCreatedDate(), null, null);
    }
    
    public List<Ofal> findAll() {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " ORDER BY created_date DESC", this);
    }
    
}

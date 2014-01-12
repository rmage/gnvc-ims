package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.GRCrossDockDtlDao;
import com.app.wms.engine.db.dto.GRCrossDockDtl;
import com.app.wms.engine.db.dto.map.GRCrossDockDtlMap;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class GRCrossDockDtlDaoImpl extends AbstractDAO implements GRCrossDockDtlDao {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public void insert(GRCrossDockDtl dto) {
        try {
            jdbcTemplate.update("INSERT INTO grcd_dtl VALUES(?, ?, ?, ?, ?, CURRENT_TIMESTAMP, NULL, NULL)", 
                dto.getGRCodeDtl(), dto.getGRCode(), dto.getPOCodeDtl(), dto.getGRStatus(), dto.getCreatedBy());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public void update(GRCrossDockDtl dto) {
        try {
            jdbcTemplate.update("UPDATE grcd_dtl SET gr_status = ?, updated_by = ?, updated_date = CURRENT_TIMESTAMP WHERE gr_code_dtl = ?", 
                dto.getGRStatus(), dto.getUpdatedBy(), dto.getGRCodeDtl());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public List<GRCrossDockDtl> findbyGRCode(String grCode) {
        try {
            return jdbcTemplate.query("SELECT * FROM grcd_dtl WHERE gr_code = ?", new GRCrossDockDtlMap(), grCode);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

}

package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.QuarantineDtlDao;
import com.app.wms.engine.db.dto.QuarantineDtl;
import com.app.wms.engine.db.dto.map.QuarantineDtlMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 * 11 February 2013
 */

public class QuarantineDtlDaoImpl extends AbstractDAO implements QuarantineDtlDao {

    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public void insert(QuarantineDtl dto) {
        try {
            jdbcTemplate.update("INSERT INTO quarantine_dtl VALUES(?, ?, ?, CURRENT_TIMESTAMP, NULL, NULL)", 
                dto.getQCode(), dto.getProductCode(), dto.getCreatedBy());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public List<QuarantineDtl> findByQCode(String qCode) {
        try {
            return jdbcTemplate.query("SELECT * from quarantine_dtl WHERE q_code = ?", new QuarantineDtlMap(), qCode);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public List<Map<String,Object>> findByPutawayCode(String putawayCode) {
        try {
            return jdbcTemplate.queryForList("SELECT pd.product_code, p.product_name, pd.qty_put, ISNULL(pod.po_qty, 0) AS qty_po FROM quarantine q "
                + "INNER JOIN putaway_detail pd ON pd.putaway_id = q.putaway_code "
                + "INNER JOIN wh_location wl ON wl.location_code = pd.location_code "
                + "LEFT JOIN pocd_dtl pod ON pod.po_code = q.po_code AND pod.product_code = pd.product_code "
                + "INNER JOIN product p ON p.product_code = pd.product_code "
                + "WHERE wl.locating_area = 'Quarantine' AND q.putaway_code = ?", putawayCode);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

}

package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.POCrossDockDtlDao;
import com.app.wms.engine.db.dto.POCrossDockDtl;
import com.app.wms.engine.db.dto.map.POCrossDockDtlMap;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class POCrossDockDtlDaoImpl extends AbstractDAO implements POCrossDockDtlDao{

    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public void insert(POCrossDockDtl dto) {
        try {
            jdbcTemplate.update("INSERT INTO pocd_dtl VALUES(?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, NULL, NULL)", 
                dto.getPOCodeDtl(), dto.getPOCode(), dto.getProductCode(), dto.getPOQty(), dto.getPOType(), dto.getCreatedBy());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void update(POCrossDockDtl dto) {
        try {
            jdbcTemplate.update("UPDATE pocd_dtl SET po_qty = ?, po_type = ?, updated_by = ?, updated_date = CURRENT_TIMESTAMP WHERE po_code_dtl = ?", 
                dto.getPOQty(), dto.getPOType(), dto.getUpdatedBy(), dto.getPOCodeDtl());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public List<POCrossDockDtl> findbyPOCode(int poCode) {
        try {
            return jdbcTemplate.query("SELECT * FROM pocd_dtl WHERE po_code = ?", new POCrossDockDtlMap(), poCode);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    /*
    
    public List<POCrossDockDtl> findbyPOCodeApproved(int poCode) {
        try {
            return jdbcTemplate.query("SELECT b.* FROM pocd a, pocd_dtl b WHERE a.po_code = b.po_code AND a.approved_by IS NOT NULL AND b.po_code = ?", 
                new POCrossDockDtlMap(), poCode);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }*/
    
    public List<POCrossDockDtl> findbyPOCodeCrossDock(String poCode) {
        try {
            return jdbcTemplate.query("SELECT pd.* FROM pocd_dtl pd INNER JOIN grcd g ON g.po_code = pd.po_code INNER JOIN pacd p ON p.gr_code = g.gr_code INNER JOIN crossdock c ON c.pa_code = p.pa_code WHERE pd.po_code = ?", 
                new POCrossDockDtlMap(), poCode);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public List<POCrossDockDtl> findbyGRCode(String grCode) {
        try {
            return jdbcTemplate.query("SELECT pd.* from pocd_dtl pd LEFT OUTER JOIN grcd g ON pd.po_code = g.po_code WHERE g.gr_code = ?", 
                new POCrossDockDtlMap(), grCode);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public List<POCrossDockDtl> findbySOCode(String soCode) {
        try {
            return jdbcTemplate.query("SELECT pd.* from pocd_dtl pd LEFT OUTER JOIN socd S ON pd.po_code = s.po_code WHERE s.so_code = ?", 
                new POCrossDockDtlMap(), soCode);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

}

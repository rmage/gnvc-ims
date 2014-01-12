package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.GRCrossDockDao;
import com.app.wms.engine.db.dto.GRCrossDock;
import com.app.wms.engine.db.dto.map.GRCrossDockMap;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class GRCrossDockDaoImpl extends AbstractDAO implements GRCrossDockDao {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public void insert(GRCrossDock dto) {
        try {
            jdbcTemplate.update("INSERT INTO grcd VALUES(?, ?, ?, ?, NULL, NULL, ?, CURRENT_TIMESTAMP, NULL, NULL)", 
                dto.getGRCode(), dto.getPOCode(), dto.getWHCode(), dto.getGRDate(), dto.getCreatedBy());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void update(GRCrossDock dto) {
        try {
            jdbcTemplate.update("UPDATE grcd SET gr_date = ?, updated_by = ?, updated_date = CURRENT_TIMESTAMP WHERE gr_code = ?", 
                dto.getGRDate(), dto.getUpdatedBy(), dto.getGRCode());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void approved(String paCode, String updatedBy) {
        try {
            jdbcTemplate.update("UPDATE grcd SET approved_by = ?, approved_date = CURRENT_TIMESTAMP WHERE gr_code = (SELECT gr_code FROM pacd WHERE pa_code = ?)", 
                updatedBy, paCode);
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public List<GRCrossDock> findAll(String whCode, int page) {
        try {
            int top = page * 10;
            int rownum = 1 + ((page - 1) * 10);
            return jdbcTemplate.query("SELECT * FROM (SELECT TOP (?) g.*, p.po_number, ROW_NUMBER() OVER (ORDER BY g.created_date desc) AS rownum "
                .concat("FROM grcd g INNER JOIN pocd p ON p.po_code = g.po_code AND g.wh_code = ? ORDER BY g.created_date desc) as grcd_list WHERE rownum BETWEEN ? AND ?"), new GRCrossDockMap(), top, whCode, rownum, top);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public List<GRCrossDock> findGRNotInPA(String whCode, String sidx, String sord) {
        try {
            return jdbcTemplate.query("SELECT g.*, p.po_number FROM grcd g INNER JOIN pocd p ON p.po_code = g.po_code  WHERE g.gr_code NOT IN (SELECT DISTINCT gr_code FROM pacd) AND g.wh_code = ? ORDER BY g." + sidx + " " + sord, new GRCrossDockMap(), whCode);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public List<GRCrossDock> findByGRDate(Date grDate) {
        try {
            List<GRCrossDock> list =  jdbcTemplate.query("SELECT g.*, p.po_number FROM grcd g INNER JOIN pocd p ON p.po_code = g.po_code AND g.gr_date = ?", new GRCrossDockMap(), grDate);
            return list;
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public GRCrossDock findByGRCode(String grCode) {
        try {
            List<GRCrossDock> list = jdbcTemplate.query("SELECT g.*, p.po_number FROM grcd g INNER JOIN pocd p ON p.po_code = g.po_code AND g.gr_code = ?", new GRCrossDockMap(), grCode);
            return list.isEmpty() ? null : list.get(0);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

}

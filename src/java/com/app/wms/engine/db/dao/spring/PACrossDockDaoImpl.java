package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.PACrossDockDao;
import com.app.wms.engine.db.dto.PACrossDock;
import com.app.wms.engine.db.dto.POCrossDockDtl;
import com.app.wms.engine.db.dto.map.PACrossDockMap;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class PACrossDockDaoImpl extends AbstractDAO implements PACrossDockDao {

    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public void insert(PACrossDock dto) {
        try {
            jdbcTemplate.update("INSERT INTO pacd VALUES(?, ?, ?, ?, ?, ?, NULL, NULL, ?, CURRENT_TIMESTAMP, NULL, NULL)", 
                dto.getPACode(), dto.getGRCode(), dto.getLocationCode(), dto.getTallymanCode(), dto.getWHCode(), dto.getPAInfo(), dto.getCreatedBy());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public void update(PACrossDock dto) {
        try {
            jdbcTemplate.update("UPDATE pacd SET location_code = ?, tallyman_code = ?, pa_info = ?, updated_by = ?, updated_date = CURRENT_TIMESTAMP WHERE pa_code = ?", 
                dto.getLocationCode(), dto.getTallymanCode(), dto.getPAInfo(), dto.getUpdatedBy(), dto.getPACode());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void updatePutaway(POCrossDockDtl dto, String whCode) {
        try {
            jdbcTemplate.update("UPDATE putaway_detail SET balance = balance - ? WHERE product_code = ? AND wh_code = ? AND location_code = ( "
                + "SELECT wl.location_code FROM wh_location wl INNER JOIN wh_location_detail wld ON wld.location_id = wl.location_id WHERE wl.locating_area = 'Quarantine' AND wld.product_code = ? "
                + ") AND putaway_id = (SELECT TOP 1 putaway_id FROM putaway_detail WHERE wh_code = ? ORDER BY putaway_id DESC)", 
                dto.getPOQty(), dto.getProductCode(), whCode, dto.getProductCode(), whCode );
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public void approved(String paCode, String approvedBy) {
        try {
            jdbcTemplate.update("UPDATE pacd SET approved_by = ?, approved_date = CURRENT_TIMESTAMP WHERE pa_code = ?", 
                approvedBy, paCode);
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public List<PACrossDock> findAll(String whCode, int page) {
        try {
            int top = page * 10;
            int rownum = 1 + ((page - 1) * 10);
            return jdbcTemplate.query("SELECT * FROM (SELECT TOP (?) p.*, whl.location_name, t.name, ROW_NUMBER() OVER (ORDER BY p.created_date desc) AS rownum "
                .concat("FROM pacd p LEFT JOIN wh_location whl ON whl.location_code = p.location_code LEFT JOIN tallyman t ON t.tally_id = p.tallyman_code WHERE p.wh_code = ? ORDER BY created_date desc) as pacd_list WHERE rownum BETWEEN ? AND ?"), new PACrossDockMap(), top, whCode, rownum, top);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public PACrossDock findByPCode(String pCode) {
        try {
            List<PACrossDock> list = jdbcTemplate.query("SELECT p.*, wl.location_name, '' as name FROM pacd p INNER JOIN wh_location wl ON wl.location_code = p.location_code WHERE p.gr_code = (SELECT g.gr_code FROM grcd g WHERE g.po_code = (SELECT po_code FROM socd s INNER JOIN pcd p ON p.so_code = s.so_code WHERE p.p_code = ?))", new PACrossDockMap(), pCode);
            return list.isEmpty() ? null : list.get(0);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public PACrossDock findByPACode(String paCode) {
        try {
            List<PACrossDock> list = jdbcTemplate.query("SELECT p.*, whl.location_name, t.name FROM pacd p LEFT JOIN wh_location whl ON whl.location_code = p.location_code LEFT JOIN tallyman t ON t.tally_id = p.tallyman_code WHERE p.pa_code = ?", new PACrossDockMap(), paCode);
            return list.isEmpty() ? null : list.get(0);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public List<PACrossDock> findByPADate(Date paDate) {
        try {
            List<PACrossDock> list = jdbcTemplate.query("SELECT p.*, whl.location_name, t.name FROM pacd p LEFT JOIN wh_location whl ON whl.location_code = p.location_code LEFT JOIN tallyman t ON t.tally_id = p.tallyman_code WHERE CONVERT(DATE, p.created_date, 121) = ?", new PACrossDockMap(), paDate);
            return list;
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

}

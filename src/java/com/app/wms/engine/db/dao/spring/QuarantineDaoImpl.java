package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.QuarantineDao;
import com.app.wms.engine.db.dto.Putaway;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.dto.Quarantine;
import com.app.wms.engine.db.dto.map.QuarantineMap;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 * 11 February 2013
 */

public class QuarantineDaoImpl extends AbstractDAO implements QuarantineDao {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public void insert(Quarantine dto) {
        try {
            jdbcTemplate.update("INSERT INTO quarantine VALUES(?, ?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP, NULL, NULL)", 
                dto.getQCode(), dto.getPOCode(), dto.getPutawayCode(), dto.getWhCode(), dto.getCreatedBy());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public List<Quarantine> findAll(String whCode, int page) {
        try {
            int top = page * 10;
            int rownum = 1 + ((page - 1) * 10);
            return jdbcTemplate.query("SELECT * FROM (SELECT TOP (?) q.*, p.po_number, ROW_NUMBER() OVER (ORDER BY q.created_date desc) AS rownum "
                .concat("FROM quarantine q INNER JOIN pocd p ON p.po_code = q.po_code WHERE q.wh_code = ? ORDER BY q.created_date desc) as q_list WHERE rownum BETWEEN ? AND ?"), new QuarantineMap(), top, whCode, rownum, top);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public Quarantine findByQCode(String qCode) {
        try {
            List<Quarantine> list = jdbcTemplate.query("SELECT q.*, p.po_number FROM quarantine q INNER JOIN pocd p ON p.po_code = q.po_code WHERE q_code = ?", new QuarantineMap(), qCode);
            return list.isEmpty() ? null : list.get(0);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public List<Quarantine> findByQDate(Date qDate) {
        try {
            return jdbcTemplate.query("SELECT q.*, p.po_number FROM quarantine q INNER JOIN pocd p ON p.po_code = q.po_code WHERE CONVERT(DATE, q_date, 121) = ?", new QuarantineMap(), qDate);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public Putaway findPutaway(String whCode) {
        try {
            List<Putaway> list = jdbcTemplate.query("SELECT * FROM putaway p WHERE p.putaway_id = (SELECT TOP(1) pd.putaway_id FROM putaway_detail pd INNER JOIN putaway p ON p.putaway_id = pd.putaway_id INNER JOIN wh_location wl ON wl.location_code = pd.location_code WHERE wl.locating_area like '%quarantine%' AND p.status_app = 'SUCCESSFULL' AND pd.wh_code = ? ORDER BY pd.lotid)", new PutawayDaoImpl(), whCode);
            return list.isEmpty() ? null : list.get(0);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public Putaway findPutaway(int poCode) {
        try {
            List<Putaway> list = jdbcTemplate.query("SELECT p.* FROM putaway p INNER JOIN putaway_detail pd ON pd.putaway_id = p.putaway_id WHERE gr_number IN (SELECT grnumber FROM goodreceive_detail GROUP BY grnumber) AND p.status_app = 'SUCCESSFULL' AND p.putaway_id = (SELECT putaway_code FROM quarantine WHERE po_code = ?)", new PutawayDaoImpl(), poCode);
            return list.isEmpty() ? null : list.get(0);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public List<PutawayDetail> findPutawayDtl(String putawayId) {
        try {
            return jdbcTemplate.query("SELECT * FROM putaway_detail pd INNER JOIN wh_location wl ON wl.location_code = pd.location_code WHERE wl.locating_area like '%quarantine%' AND pd.putaway_id = ?", new PutawayDetailDaoImpl(), putawayId);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
}

package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.PCrossDockDao;
import com.app.wms.engine.db.dto.PCrossDock;
import com.app.wms.engine.db.dto.map.PCrossDockMap;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class PCrossDockDaoImpl extends AbstractDAO implements PCrossDockDao {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public void insert(PCrossDock dto) {
        try {
            jdbcTemplate.update("INSERT INTO pcd VALUES(?, ?, ?, ?, ?, NULL, NULL, ?, CURRENT_TIMESTAMP, NULL, NULL)", 
                dto.getPCode(), dto.getSOCode(), dto.getTallymanCode(), dto.getWHCode(), dto.getPDate(), dto.getCreatedBy());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public void update(PCrossDock dto) {
        try {
            jdbcTemplate.update("UPDATE pcd SET tallyman_code = ?, p_date = ?, updated_by = ?, updated_date = CURRENT_TIMESTAMP WHERE p_code = ?", 
                dto.getTallymanCode(), dto.getPDate(), dto.getUpdatedBy(), dto.getPCode());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public void approved(String pCode, String approvedBy) {
        try {
            jdbcTemplate.update("UPDATE pcd SET approved_by = ?, approved_date = CURRENT_TIMESTAMP WHERE p_code = ?", 
                approvedBy, pCode);
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public List<PCrossDock> findAll(String whCode, int page) {
        try {
            int top = page * 10;
            int rownum = 1 + ((page - 1) * 10);
            return jdbcTemplate.query("SELECT * FROM (SELECT TOP (?) p.*, t.name, ROW_NUMBER() OVER (ORDER BY p.created_date desc) AS rownum "
                .concat("FROM pcd p INNER JOIN tallyman t ON t.tally_id = p.tallyman_code WHERE p.wh_code = ? ORDER BY p.created_date desc) as pcd_list WHERE rownum BETWEEN ? AND ?"), new PCrossDockMap(), top, whCode, rownum, top);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public PCrossDock findByPCode(String pCode) {
        try {
            List<PCrossDock> list = jdbcTemplate.query("SELECT p.*, t.name FROM pcd p INNER JOIN tallyman t ON t.tally_id = p.tallyman_code WHERE p.p_code = ?", new PCrossDockMap(), pCode);
            return list.isEmpty() ? null : list.get(0);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public List<PCrossDock> findByPDate(Date pDate) {
        try {
            return jdbcTemplate.query("SELECT p.*, t.tallyname FROM pcd p INNER JOIN tallyman t ON t.tally_id = p.tallyman_code WHERE p.p_date = ?", new PCrossDockMap(), pDate);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

}
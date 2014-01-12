package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.SOCrossDockDao;
import com.app.wms.engine.db.dto.SOCrossDock;
import com.app.wms.engine.db.dto.map.SOCrossDockMap;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class SOCrossDockDaoImpl extends AbstractDAO implements SOCrossDockDao {

    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    public void insert(SOCrossDock dto) {
        try {
            jdbcTemplate.update("INSERT INTO socd VALUES(?, ?, ?, ?, ?, NULL, NULL, ?, CURRENT_TIMESTAMP, NULL, NULL)", 
                dto.getSOCode(), dto.getPOCode(), dto.getConsigneeCode(), dto.getWHCode(), dto.getSODate(), dto.getCreatedBy());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public void update(SOCrossDock dto) {
        try {
            jdbcTemplate.update("UPDATE socd SET po_code = ?, consignee_code = ?, so_date = ?, updated_by = ?, updated_date = CURRENT_TIMESTAMP WHERE so_code = ?", 
                dto.getPOCode(), dto.getConsigneeCode(), dto.getSODate(), dto.getUpdatedBy(), dto.getSOCode());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public void approved(String pCode, String approvedBy) {
        try {
            jdbcTemplate.update("UPDATE socd SET approved_by = ?, approved_date = CURRENT_TIMESTAMP WHERE so_code = (SELECT so_code FROM pcd WHERE p_code = ?)", approvedBy, pCode);
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public List<SOCrossDock> findAll(String whCode, int page) {
        try {
            int top = page * 10;
            int rownum = 1 + ((page - 1) * 10);
            return jdbcTemplate.query("SELECT * FROM (SELECT TOP (?) s.*, p.po_number, c.consignee_name, ROW_NUMBER() OVER (ORDER BY s.created_date desc) AS rownum "
                .concat("FROM socd s INNER JOIN pocd p ON p.po_code = s.po_code INNER JOIN consignee c ON c.consignee_code = s.consignee_code WHERE s.wh_code = ? ORDER BY s.created_date desc) as socd_list WHERE rownum BETWEEN ? AND ?"), new SOCrossDockMap(), top, whCode, rownum, top);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public List<SOCrossDock> findSONotInP(String whCode, String sidx, String sord) {
        try {
            return jdbcTemplate.query("SELECT s.*, p.po_number, c.consignee_name FROM socd s INNER JOIN pocd p ON p.po_code = s.po_code INNER JOIN consignee c ON c.consignee_code = s.consignee_code  WHERE s.so_code NOT IN (SELECT DISTINCT so_code FROM pcd) AND s.wh_code = ? ORDER BY s." + sidx + " " + sord, new SOCrossDockMap(), whCode);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public List<SOCrossDock> findSONotInDO(String whCode, String sidx, String sord) {
        try {
            return jdbcTemplate.query("SELECT s.*, p.po_number, c.consignee_name FROM socd s INNER JOIN pocd p ON p.po_code = s.po_code INNER JOIN consignee c ON c.consignee_code = s.consignee_code  WHERE s.so_code NOT IN (SELECT DISTINCT so_code FROM docd) AND s.wh_code = ? ORDER BY s." + sidx + " " + sord, new SOCrossDockMap(), whCode);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public SOCrossDock findBySOCode(String soCode) {
        try {
            List<SOCrossDock> list = jdbcTemplate.query("SELECT s.*, p.po_number, c.consignee_name FROM socd s INNER JOIN pocd p ON p.po_code = s.po_code INNER JOIN consignee c ON c.consignee_code = s.consignee_code WHERE s.so_code = ?", new SOCrossDockMap(), soCode);
            return list.isEmpty() ? null : list.get(0);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public List<SOCrossDock> findBySODate(Date soDate) {
        try {
            return jdbcTemplate.query("SELECT s.*, p.po_number, c.consignee_name FROM socd s INNER JOIN pocd p ON p.po_code = s.po_code INNER JOIN consignee c ON c.consignee_code = s.consignee_code WHERE s.so_date = ?", new SOCrossDockMap(), soDate);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

}

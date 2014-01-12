package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.POCrossDockDao;
import com.app.wms.engine.db.dto.POCrossDock;
import com.app.wms.engine.db.dto.map.POCrossDockMap;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class POCrossDockDaoImpl extends AbstractDAO implements POCrossDockDao {
    
    protected SimpleJdbcInsert jdbcInsert;
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("pocd").usingGeneratedKeyColumns("po_code");
    }
    
    public Integer insert(POCrossDock dto) {
        try {
            Map params = new HashMap(5);
            params.put("po_number", dto.getPONumber());
            params.put("wh_code", dto.getWHCode());
            params.put("po_date", dto.getPODate());
            params.put("approved_by", dto.getApprovedBy());
            params.put("created_by", dto.getCreatedBy());
            params.put("created_date", new Date());
            
            return jdbcInsert.executeAndReturnKey(params).intValue();
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public void update(POCrossDock dto) {
        try {
            jdbcTemplate.update("UPDATE pocd SET po_date = ?, updated_by = ?, updated_date = CURRENT_TIMESTAMP WHERE po_code = ?", 
                dto.getPODate(), dto.getUpdatedBy(), dto.getPOCode());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public void approved(String paCode, String approvedBy, String type) {
        try {
            if(type.equals("normal")) {
                jdbcTemplate.update("UPDATE pocd SET approved_by = ?, approved_date = CURRENT_TIMESTAMP WHERE po_code = (SELECT po_code FROM grcd WHERE gr_code = (SELECT gr_code FROM pacd WHERE pa_code = ?))", 
                    approvedBy, paCode);
            } else if(type.equals("quarantine")) {
                jdbcTemplate.update("UPDATE pocd SET approved_date = CURRENT_TIMESTAMP WHERE po_code = (SELECT po_code FROM grcd WHERE gr_code = (SELECT gr_code FROM pacd WHERE pa_code = ?))", 
                    paCode);
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public List<POCrossDock> findAll(String whCode, int page) {
        try {
            int top = page * 10;
            int rownum = 1 + ((page - 1) * 10);
            return jdbcTemplate.query("SELECT * FROM (SELECT TOP (?) pocd.*, ROW_NUMBER() OVER (ORDER BY created_date desc) AS rownum "
                .concat("FROM pocd WHERE wh_code = ? ORDER BY created_date desc) as pocd_list WHERE rownum BETWEEN ? AND ?"), new POCrossDockMap(), top, whCode, rownum, top);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public List<POCrossDock> findPONotInGR(String whCode, String sidx, String sord) {
        try {
            return jdbcTemplate.query("SELECT * FROM pocd WHERE po_code NOT IN (SELECT DISTINCT po_code FROM grcd) AND wh_code = ? ORDER BY " + sidx + " " + sord, new POCrossDockMap(), whCode);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public List<POCrossDock> findPONotInSO(String whCode, String sidx, String sord) {
        try {
            return jdbcTemplate.query("SELECT * FROM pocd WHERE po_code NOT IN (SELECT DISTINCT po_code FROM socd) AND wh_code = ? ORDER BY " + sidx + " " + sord, new POCrossDockMap(), whCode);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public POCrossDock findByPOCode(int poCode) {
        try {
            List<POCrossDock> list = jdbcTemplate.query("SELECT * FROM pocd WHERE po_code = ?", new POCrossDockMap(), poCode);
            return list.isEmpty() ? null : list.get(0);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public POCrossDock findByPONumber(String poNumber) {
        try {
            List<POCrossDock> list = jdbcTemplate.query("SELECT * FROM pocd WHERE po_number = ?", new POCrossDockMap(), poNumber);
            return list.isEmpty() ? null : list.get(0);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public List<POCrossDock> findByPODate(Date poDate) {
        try {
            List<POCrossDock> list = jdbcTemplate.query("SELECT * FROM pocd WHERE po_date = ? ORDER BY po_code DESC", new POCrossDockMap(), poDate);
            return list;
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public POCrossDock findByPACode(String paCode) {
        try {
            List<POCrossDock> list = jdbcTemplate.query("SELECT * FROM pocd WHERE po_code = (SELECT po_code FROM grcd WHERE gr_code = (SELECT gr_code FROM pacd WHERE pa_code = ?))", new POCrossDockMap(), paCode);
            return list.isEmpty() ? null : list.get(0);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

}

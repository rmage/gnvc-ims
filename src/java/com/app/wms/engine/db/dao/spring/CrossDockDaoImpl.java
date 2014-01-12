package com.app.wms.engine.db.dao.spring;


import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import com.app.wms.engine.db.dao.CrossDockDao;
import com.app.wms.engine.db.dto.CrossDock;
import com.app.wms.engine.db.dto.map.CrossDockMap;
import java.util.Date;
import java.util.List;

public class CrossDockDaoImpl extends AbstractDAO implements CrossDockDao {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public void insert(CrossDock dto) {
        try {
            jdbcTemplate.update("INSERT INTO crossdock VALUES(?, ?, ?, ?, CURRENT_TIMESTAMP, NULL, ?, CURRENT_TIMESTAMP, NULL, NULL)", 
                dto.getCDCode(), dto.getPACode(), dto.getPCode(), dto.getWHCode(), dto.getCreatedBy());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public void update(CrossDock dto) {
        try {
            jdbcTemplate.update("UPDATE crossdock SET p_code = ?, cd_dateout = CURRENT_TIMESTAMP, updated_by = ?, updated_date = CURRENT_TIMESTAMP WHERE cd_code = (SELECT c.cd_code FROM pcd p LEFT OUTER JOIN socd s ON s.so_code = p.so_code LEFT OUTER JOIN grcd g ON g.po_code = s.po_code LEFT OUTER JOIN pacd pa ON pa.gr_code = g.gr_code LEFT OUTER JOIN crossdock c ON c.pa_code = pa.pa_code WHERE p.p_code = ?)", 
                dto.getPCode(), dto.getUpdatedBy(), dto.getPCode());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public List<CrossDock> findAll(String whCode, int page) {
        try {
            int top = page * 10;
            int rownum = 1 + ((page - 1) * 10);
            return jdbcTemplate.query("SELECT * FROM (SELECT TOP (?) crossdock.*, ROW_NUMBER() OVER (ORDER BY created_date desc) AS rownum "
                .concat("FROM crossdock WHERE wh_code = ? ORDER BY (CASE WHEN p_code IS NULL THEN 0 ELSE 1 END), created_date desc) as pacd_list WHERE rownum BETWEEN ? AND ?"), new CrossDockMap(), top, whCode, rownum, top);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public CrossDock findByCDCode(String cdCode) {
        try {
            List<CrossDock> list = jdbcTemplate.query("SELECT * FROM crossdock WHERE cd_code = ?", new CrossDockMap(), cdCode);
            return list.isEmpty() ? null : list.get(0);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public List<CrossDock> findByCDDate(Date cdDateIn) {
        try {
            return jdbcTemplate.query("SELECT * FROM crossdock WHERE CONVERT(DATE, cd_datein, 121) = ?", new CrossDockMap(), cdDateIn);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
}
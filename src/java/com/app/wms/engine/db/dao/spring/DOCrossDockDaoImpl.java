package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.DOCrossDockDao;
import com.app.wms.engine.db.dto.DOCrossDock;
import com.app.wms.engine.db.dto.map.DOCrossDockMap;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class DOCrossDockDaoImpl extends AbstractDAO implements DOCrossDockDao {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public void insert(DOCrossDock dto) {
        try {
            jdbcTemplate.update("INSERT INTO docd VALUES(?, ?, ?, ?, ?, NULL, NULL, ?, CURRENT_TIMESTAMP, NULL, NULL)", 
                dto.getDOCode(), dto.getSOCode(), dto.getWHCode(), dto.getDOName(), dto.getDODate(), dto.getCreatedBy());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public void update(DOCrossDock dto) {
        try {
            jdbcTemplate.update("UPDATE docd SET do_name = ?, do_date = ?, updated_by = ?, updated_date = CURRENT_TIMESTAMP WHERE do_code = ?", 
                dto.getDOName(), dto.getDODate(), dto.getUpdatedBy(), dto.getDOCode());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public List<DOCrossDock> findAll(String whCode, int page) {
        try {
            int top = page * 10;
            int rownum = 1 + ((page - 1) * 10);
            return jdbcTemplate.query("SELECT * FROM (SELECT TOP (?) d.*, ROW_NUMBER() OVER (ORDER BY d.created_date desc) AS rownum "
                .concat("FROM docd d WHERE d.wh_code = ? ORDER BY d.created_date desc) as socd_list WHERE rownum BETWEEN ? AND ?"), new DOCrossDockMap(), top, whCode, rownum, top);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public DOCrossDock findByDOCode(String doCode) {
        try {
            List<DOCrossDock> list = jdbcTemplate.query("SELECT * FROM docd WHERE do_code = ?", new DOCrossDockMap(), doCode);
            return list.isEmpty() ? null : list.get(0);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public List<DOCrossDock> findByDODate(Date doDate) {
        try {
            return jdbcTemplate.query("SELECT * FROM docd WHERE do_date = ?", new DOCrossDockMap(), doDate);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

}

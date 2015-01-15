package com.spfi.ims.dao;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import java.util.Date;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class DBLoggingDaoImpl extends AbstractDAO implements DBLoggingDao {
    
    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
    
    private static final String LOG_FG_ERROR = "b12d4a36_logging.[dbo].db_error";
    
    public void logError(Date logDate, String logModule, String logSubmodule, String logText, String loguser, int logStatus) {
        try {
            jdbcTemplate.update("INSERT INTO " + LOG_FG_ERROR + "(log_date, log_module, log_submodule, log_text, log_user, log_status) " +
                    "VALUES (?, ?, ?, ?, ?, ?)",
                    logDate, logModule, logSubmodule, logText, loguser, logStatus);
        } catch(DataAccessException e) {
            e.printStackTrace();
        }
    }
    
}

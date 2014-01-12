/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.NextSequenceDao;
import com.app.wms.engine.db.dto.Sequence;
import com.app.wms.engine.db.exceptions.DaoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author zyrex
 */
public class NextSequenceDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Sequence>, NextSequenceDao {

    protected SimpleJdbcTemplate jdbcTemplate;
    protected DataSource dataSource;

    /**
     * Method 'setDataSource'
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public Sequence mapRow(ResultSet rs, int i) throws SQLException {
        System.out.println("mapRow colIndex:" + i);
        Sequence dto = new Sequence();
        dto.setNextSequence(rs.getBigDecimal(0));
        return dto;
    }

    public Sequence getNextSequenceVal() throws DaoException {
        try {
            List<Sequence> list = jdbcTemplate.query("select SQ_UPLOAD.nextval from dual", this);
            return list.size() == 0 ? null : list.get(1);
            //return seq;
        } catch (Exception e) {
            throw new DaoException("Query failed", e);
        }
    }
}

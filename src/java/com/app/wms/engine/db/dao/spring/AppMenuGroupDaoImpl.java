package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.AppMenuGroupDao;
import com.app.wms.engine.db.dto.AppMenuGroup;
import com.app.wms.engine.db.dto.AppMenuGroupPk;
import com.app.wms.engine.db.exceptions.AppMenuGroupDaoException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class AppMenuGroupDaoImpl extends AbstractDAO implements ParameterizedRowMapper<AppMenuGroup>, AppMenuGroupDao {

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

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "app_menu_group";
    }

    public AppMenuGroup mapRow(ResultSet rs, int row) throws SQLException {
        AppMenuGroup dto = new AppMenuGroup();
        dto.setGroupCode(rs.getString(1));
        dto.setName(rs.getString(2));
        dto.setSortNo(rs.getString(7));

        return dto;
    }

    @Transactional
    public List<AppMenuGroup> findByAuthLogin(String userID) throws AppMenuGroupDaoException {
        try {
            return jdbcTemplate.query("SELECT DISTINCT G.GROUP_CODE,G.NAME "
                    + ", G.CREATED_DATE,G.CREATED_BY,G.UPDATED_DATE,G.UPDATED_BY,G.SORT_NO "
                    + "FROM APP_MENU D "
                    + "INNER JOIN APP_MENU_ROLE R on D.MENU_CODE=R.MENU_CODE "
                    + "INNER JOIN \"USER\" u on r.ROLE_CODE=u.ROLE_CODE "
                    + "INNER JOIN " + getTableName() + " G ON D.GROUP_CODE=G.GROUP_CODE   "
                    + "WHERE u.USER_ID= ? ORDER BY G.SORT_NO,G.NAME", this, userID);
        } catch (Exception e) {
            throw new AppMenuGroupDaoException("Query failed", e);
        }

    }

    @Transactional
    public AppMenuGroupPk insert(AppMenuGroup dto) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " ( GROUP_CODE, NAME, SORT_NO, IS_DELETED, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )", dto.getGroupCode(), dto.getName(), dto.getSortNo(), dto.getIsDelete() , dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate());
        return dto.createPk();
    }

    @Transactional
    public void update(AppMenuGroupPk pk, AppMenuGroup dto) throws AppMenuGroupDaoException {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET GROUP_CODE = ?,NAME = ?,SORT_NO = ?"
                + ",UPDATED_BY = ?, UPDATED_DATE = ? WHERE GROUP_CODE = ?", dto.getGroupCode(), dto.getName(), dto.getSortNo(), dto.getUpdatedBy(), dto.getUpdatedDate(), pk.getGroupCode());
    }

    @Transactional
    public void delete(AppMenuGroupPk pk) throws AppMenuGroupDaoException {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET IS_DELETED ='Y' WHERE GROUP_CODE = ?", pk.getGroupCode());
    }

    @Transactional
    public AppMenuGroup findByPrimaryKey(String groupCode) throws AppMenuGroupDaoException {
        try {
            List<AppMenuGroup> list = jdbcTemplate.query("SELECT G.GROUP_CODE,G.NAME "
                    + ", G.CREATED_DATE,G.CREATED_BY,G.UPDATED_DATE,G.UPDATED_BY,G.SORT_NO "
                    + " FROM " + getTableName() + " G WHERE G.GROUP_CODE = ?", this, groupCode);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new AppMenuGroupDaoException("Query failed", e);
        }
    }

    @Transactional
    public AppMenuGroup findByPrimaryKey(AppMenuGroupPk pk) throws AppMenuGroupDaoException {
        return findByPrimaryKey(pk.getGroupCode());
    }

    @Transactional
    public List<AppMenuGroup> findAll() throws AppMenuGroupDaoException {
        try {
            return jdbcTemplate.query("SELECT G.GROUP_CODE, G.NAME, G.IS_DELETED"
                    + ", G.CREATED_DATE, G.CREATED_BY, G.UPDATED_DATE, G.UPDATED_BY, G.SORT_NO "
                    + " FROM " + getTableName()
                    + " G WHERE IS_DELETED='N'" 
                    + " ORDER BY G.SORT_NO,G.GROUP_CODE", this);
        } catch (Exception e) {
            throw new AppMenuGroupDaoException("Query failed", e);
        }
    }
}

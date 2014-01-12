package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.AppMenuDao;
import com.app.wms.engine.db.dto.AppMenu;
import com.app.wms.engine.db.dto.AppMenuPk;
import com.app.wms.engine.db.exceptions.AppMenuDaoException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class AppMenuDaoImpl extends AbstractDAO implements ParameterizedRowMapper<AppMenu>, AppMenuDao {

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
     * Method 'insert'
     *
     * @param dto
     * @return AppMenuPk
     */
    @Transactional
    public AppMenuPk insert(AppMenu dto) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " ( MENU_CODE, NAME, SORT_NO, URL, GROUP_CODE, IS_DELETED, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )", dto.getMenuCode(), dto.getName(), dto.getSortNo(), dto.getUrl(), dto.getGroupCode(), dto.getIs_delete(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate());
        return dto.createPk();
    }

    /**
     * Updates a single row in the APP_MENU table.
     */
    @Transactional
    public void update(AppMenuPk pk, AppMenu dto) throws AppMenuDaoException {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET MENU_CODE = ?, NAME = ?, SORT_NO = ?, URL = ?, CREATED_BY = ?, CREATED_DATE = ?, UPDATED_BY = ?, UPDATED_DATE = ? WHERE MENU_CODE = ?", dto.getMenuCode(), dto.getName(), dto.getSortNo(), dto.getUrl(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate(), pk.getMenuCode());
    }

    /**
     * Deletes a single row in the APP_MENU table.
     */
    @Transactional
    public void delete(AppMenuPk pk) throws AppMenuDaoException {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET IS_DELETED ='Y' WHERE MENU_CODE = ?", pk.getMenuCode());
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return AppMenu
     */
    public AppMenu mapRow(ResultSet rs, int row) throws SQLException {
        AppMenu dto = new AppMenu();
        dto.setMenuCode(rs.getString(1));
        dto.setName(rs.getString(2));
        dto.setUrl(rs.getString(3));
        dto.setCreatedBy(rs.getString(4));
        dto.setCreatedDate(rs.getDate(5));
        dto.setUpdatedBy(rs.getString(6));
        dto.setUpdatedDate(rs.getDate(7));
        dto.setGroupCode(rs.getString(8));
        dto.setSortNo(rs.getString(9));
        return dto;
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "app_menu";
    }

    /**
     * Returns all rows from the APP_MENU table that match the criteria 'MENU_CODE = :menuCode'.
     */
    @Transactional
    public AppMenu findByPrimaryKey(String menuCode) throws AppMenuDaoException {
        try {
            List<AppMenu> list = jdbcTemplate.query("SELECT MENU_CODE, NAME, URL, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, GROUP_CODE, SORT_NO FROM " + getTableName() + " WHERE MENU_CODE = ? AND IS_DELETED='N'", this, menuCode);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new AppMenuDaoException("Query failed", e);
        }

    }

    @Transactional
    public List<AppMenu> findByGroupCode(String groupCode) throws AppMenuDaoException {
        try {
            List<AppMenu> list = jdbcTemplate.query("SELECT MENU_CODE, NAME, URL, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE,GROUP_CODE,SORT_NO FROM " + getTableName() + " WHERE GROUP_CODE = ? ORDER BY SORT_NO,MENU_CODE", this, groupCode);
            return list;
        } catch (Exception e) {
            throw new AppMenuDaoException("Query failed", e);
        }
    }

    /**
     * Returns all rows from the APP_MENU table that match the criteria ''.
     */
    @Transactional
    public List<AppMenu> findAll() throws AppMenuDaoException {
        try {
            return jdbcTemplate.query("SELECT D.MENU_CODE, D.NAME, D.URL, D.CREATED_BY"
                    + ",  D.CREATED_DATE,  D.UPDATED_BY,  D.UPDATED_DATE"
                    + ", D.GROUP_CODE, D.SORT_NO FROM " + getTableName()
                    + " D INNER JOIN APP_MENU_GROUP G ON D.GROUP_CODE=G.GROUP_CODE WHERE D.IS_DELETED='N' "
                    + " ORDER BY G.SORT_NO,D.SORT_NO,D.MENU_CODE", this);
        } catch (Exception e) {
            throw new AppMenuDaoException("Query failed", e);
        }

    }


    /**
     * Returns the rows from the APP_MENU table that matches the specified primary-key value.
     */
    @Transactional
    public AppMenu findByPrimaryKey(AppMenuPk pk) throws AppMenuDaoException {
        return findByPrimaryKey(pk.getMenuCode());
    }

    @Transactional
    public List<AppMenu> findByAuthLogin(String userID) throws AppMenuDaoException {
        try {
            //+ ",D.CREATED_BY,D.CREATED_BY,D.UPDATED_BY,D.UPDATED_DATE "
            return jdbcTemplate.query("SELECT D.MENU_CODE, D.NAME, D.URL,D.CREATED_BY, D.CREATED_DATE, D.UPDATED_BY, D.UPDATED_DATE "
                    + ",D.GROUP_CODE,D.SORT_NO FROM " + getTableName()
                    + " D INNER JOIN APP_MENU_ROLE R on D.MENU_CODE=R.MENU_CODE "
                    + " inner join \"USER\" u on r.ROLE_CODE=u.ROLE_CODE "
                    + "WHERE u.USER_ID= ? and R.is_delete='N' ORDER BY D.SORT_NO,D.NAME", this, userID);
        } catch (Exception e) {
            throw new AppMenuDaoException("Query failed", e);
        }

    }

    @Transactional
    public List<AppMenu> findByMenuGroup(String groupCode) throws AppMenuDaoException {
        try {
            return jdbcTemplate.query("SELECT D.MENU_CODE, D.NAME, D.URL, D.CREATED_BY"
                    + ",  D.CREATED_DATE,  D.UPDATED_BY,  D.UPDATED_DATE"
                    + ", D.GROUP_CODE, D.SORT_NO FROM " + getTableName()
                    + " D INNER JOIN APP_MENU_GROUP G ON D.GROUP_CODE=G.GROUP_CODE WHERE G.GROUP_CODE = ? AND D.IS_DELETED='N' "
                    + " ORDER BY G.SORT_NO,D.SORT_NO,D.MENU_CODE", this, groupCode);
        } catch (Exception e) {
            throw new AppMenuDaoException("Query failed", e);
        }
    }

    @Transactional
    public List<AppMenu> findWhereNotInAppMenuRole(String roleCode, String filter) throws AppMenuDaoException {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT APP_MENU.MENU_CODE, APP_MENU.NAME, APP_MENU.URL, APP_MENU.CREATED_BY "
                    + ", APP_MENU.CREATED_DATE,  APP_MENU.UPDATED_BY,  APP_MENU.UPDATED_DATE "
                    + ", APP_MENU.GROUP_CODE, APP_MENU.SORT_NO FROM " + getTableName() + " WHERE APP_MENU.IS_DELETED='N' ");
            
          //  		+ ", APP_MENU.GROUP_CODE, APP_MENU.SORT_NO FROM " + getTableName() + " ");
          //  strQuery.append("WHERE NOT EXISTS(SELECT MENU_CODE FROM APP_MENU_ROLE WHERE APP_MENU_ROLE.MENU_CODE = APP_MENU.MENU_CODE AND APP_MENU_ROLE.ROLE_CODE = ?)");
            
            if (filter != null && !filter.trim().equals("")) {
                sb.append(" AND ");
                sb.append(filter);
            }
         
            sb.append("ORDER BY MENU_CODE ");
     
            Map map = new HashMap();
            map.put(roleCode, roleCode);
          //  return jdbcTemplate.query(DBUtil.createPagingSql(strQuery.toString(), 1, 10), this, roleCode);
            return jdbcTemplate.query(sb.toString(),this,map);
        } catch (Exception e) {
            throw new AppMenuDaoException("Query failed", e);
        }
    }
}

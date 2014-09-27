package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.AppMenuRoleDao;
import com.app.wms.engine.db.dto.AppMenuRole;
import com.app.wms.engine.db.dto.AppMenuRolePk;
import com.app.wms.engine.db.exceptions.AppMenuRoleDaoException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class AppMenuRoleDaoImpl extends AbstractDAO implements ParameterizedRowMapper<AppMenuRole>, AppMenuRoleDao {

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
     * @return AppMenuRolePk
     */
    @Transactional
    public AppMenuRolePk insert(AppMenuRole dto) {
        jdbcTemplate.update("EXEC M_ROLE_ACCESS_UPSERT ?, ?, ?, ?, ?, ?, ?", dto.getRoleCode(), dto.getMenuCode(), dto.getIsView(), dto.getIsCreate(), dto.getIsEdit(), dto.getIsDelete(), dto.getCreatedBy());
        return dto.createPk();
    }

    /**
     * Updates a single row in the APP_MENU_ROLE table.
     */
    @Transactional
    public void update(AppMenuRolePk pk, AppMenuRole dto) throws AppMenuRoleDaoException {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET ROLE_CODE = ?, MENU_CODE = ?, IS_VIEW = ?, IS_CREATE = ?, IS_EDIT = ?, IS_DELETE = ?, CREATED_BY = ?, CREATED_DATE = ?, UPDATED_BY = ?, UPDATED_DATE = ? WHERE ROLE_CODE = ? AND MENU_CODE = ?", dto.getRoleCode(), dto.getMenuCode(), dto.getIsView(), dto.getIsCreate(), dto.getIsEdit(), dto.getIsDelete(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate(), pk.getRoleCode(), pk.getMenuCode());
    }

    /**
     * Deletes a single row in the APP_MENU_ROLE table.
     */
    @Transactional
    public void delete(AppMenuRolePk pk) throws AppMenuRoleDaoException {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET IS_DELETE='Y' WHERE ROLE_CODE = ? AND MENU_CODE = ?", pk.getRoleCode(), pk.getMenuCode());
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return AppMenuRole
     */
    public AppMenuRole mapRow(ResultSet rs, int row) throws SQLException {
        AppMenuRole dto = new AppMenuRole();
        dto.setRoleCode(rs.getString(1));
        dto.setMenuCode(rs.getString(2));
        dto.setIsView(rs.getString(3));
        dto.setIsCreate(rs.getString(4));
        dto.setIsEdit(rs.getString(5));
        dto.setIsDelete(rs.getString(6));
        dto.setCreatedBy(rs.getString(7));
        dto.setCreatedDate(rs.getTimestamp(8));
        dto.setUpdatedBy(rs.getString(9));
        dto.setUpdatedDate(rs.getTimestamp(10));
        return dto;
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "app_menu_role";
    }

    /**
     * Returns all rows from the APP_MENU_ROLE table that match the criteria 'ROLE_CODE = :roleCode AND MENU_CODE = :menuCode'.
     */
    @Transactional
    public AppMenuRole findByPrimaryKey(String roleCode, String menuCode) throws AppMenuRoleDaoException {
        try {
            List<AppMenuRole> list = jdbcTemplate.query("SELECT ROLE_CODE, MENU_CODE, IS_VIEW, IS_CREATE, IS_EDIT, IS_DELETE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE ROLE_CODE = ? AND MENU_CODE = ? AND IS_DELETE='N'", this, roleCode, menuCode);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new AppMenuRoleDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the APP_MENU_ROLE table that match the criteria ''.
     */
    @Transactional
    public List<AppMenuRole> findAll() throws AppMenuRoleDaoException {
        try {
            return jdbcTemplate.query("SELECT ROLE_CODE, MENU_CODE, IS_VIEW, IS_CREATE, IS_EDIT, IS_DELETE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE IS_DELETE='N' ORDER BY ROLE_CODE, MENU_CODE", this);
        } catch (Exception e) {
            throw new AppMenuRoleDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the APP_MENU_ROLE table that match the criteria 'MENU_CODE = :menuCode'.
     */
    @Transactional
    public List<AppMenuRole> findByAppMenu(String menuCode) throws AppMenuRoleDaoException {
        try {
            return jdbcTemplate.query("SELECT ROLE_CODE, MENU_CODE, IS_VIEW, IS_CREATE, IS_EDIT, IS_DELETE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE MENU_CODE = ? AND IS_DELETE='N'", this, menuCode);
        } catch (Exception e) {
            throw new AppMenuRoleDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the APP_MENU_ROLE table that match the criteria 'ROLE_CODE = :roleCode'.
     */
    @Transactional
    public List<AppMenuRole> findByUserRole(String roleCode) throws AppMenuRoleDaoException {
    
        try {
            return jdbcTemplate.query("select role_code,menu_code,is_view,is_create,is_edit,is_delete,created_by,created_date,updated_by,updated_date from " + getTableName() + " where role_code = ? and is_delete='N'", this, roleCode);
        } catch (Exception e) {
            throw new AppMenuRoleDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the APP_MENU_ROLE table that match the criteria 'ROLE_CODE = :roleCode'.
     */
    @Transactional
    public List<AppMenuRole> findWhereRoleCodeEquals(String roleCode) throws AppMenuRoleDaoException {
        try {
            return jdbcTemplate.query("SELECT ROLE_CODE, MENU_CODE, IS_VIEW, IS_CREATE, IS_EDIT, IS_DELETE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE ROLE_CODE = ? AND IS_DELETE='N' ORDER BY ROLE_CODE", this, roleCode);
        } catch (Exception e) {
            throw new AppMenuRoleDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the APP_MENU_ROLE table that match the criteria 'MENU_CODE = :menuCode'.
     */
    @Transactional
    public List<AppMenuRole> findWhereMenuCodeEquals(String menuCode) throws AppMenuRoleDaoException {
        try {
            return jdbcTemplate.query("SELECT ROLE_CODE, MENU_CODE, IS_VIEW, IS_CREATE, IS_EDIT, IS_DELETE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE MENU_CODE = ? AND IS_DELETE='N' ORDER BY MENU_CODE", this, menuCode);
        } catch (Exception e) {
            throw new AppMenuRoleDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the APP_MENU_ROLE table that match the criteria 'IS_VIEW = :isView'.
     */
    @Transactional
    public List<AppMenuRole> findWhereIsViewEquals(String isView) throws AppMenuRoleDaoException {
        try {
            return jdbcTemplate.query("SELECT ROLE_CODE, MENU_CODE, IS_VIEW, IS_CREATE, IS_EDIT, IS_DELETE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE IS_VIEW = ? AND IS_DELETE='N' ORDER BY IS_VIEW", this, isView);
        } catch (Exception e) {
            throw new AppMenuRoleDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the APP_MENU_ROLE table that match the criteria 'IS_CREATE = :isCreate'.
     */
    @Transactional
    public List<AppMenuRole> findWhereIsCreateEquals(String isCreate) throws AppMenuRoleDaoException {
        try {
            return jdbcTemplate.query("SELECT ROLE_CODE, MENU_CODE, IS_VIEW, IS_CREATE, IS_EDIT, IS_DELETE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE IS_CREATE = ? AND IS_DELETE='N' ORDER BY IS_CREATE", this, isCreate);
        } catch (Exception e) {
            throw new AppMenuRoleDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the APP_MENU_ROLE table that match the criteria 'IS_EDIT = :isEdit'.
     */
    @Transactional
    public List<AppMenuRole> findWhereIsEditEquals(String isEdit) throws AppMenuRoleDaoException {
        try {
            return jdbcTemplate.query("SELECT ROLE_CODE, MENU_CODE, IS_VIEW, IS_CREATE, IS_EDIT, IS_DELETE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE IS_EDIT = ? AND IS_DELETE='N' ORDER BY IS_EDIT", this, isEdit);
        } catch (Exception e) {
            throw new AppMenuRoleDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the APP_MENU_ROLE table that match the criteria 'IS_DELETE = :isDelete'.
     */
    @Transactional
    public List<AppMenuRole> findWhereIsDeleteEquals(String isDelete) throws AppMenuRoleDaoException {
        try {
            return jdbcTemplate.query("SELECT ROLE_CODE, MENU_CODE, IS_VIEW, IS_CREATE, IS_EDIT, IS_DELETE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE IS_DELETE = ? ORDER BY IS_DELETE", this, isDelete);
        } catch (Exception e) {
            throw new AppMenuRoleDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the APP_MENU_ROLE table that match the criteria 'CREATED_BY = :createdBy'.
     */
    @Transactional
    public List<AppMenuRole> findWhereCreatedByEquals(BigDecimal createdBy) throws AppMenuRoleDaoException {
        try {
            return jdbcTemplate.query("SELECT ROLE_CODE, MENU_CODE, IS_VIEW, IS_CREATE, IS_EDIT, IS_DELETE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE CREATED_BY = ? AND IS_DELETE='N' ORDER BY CREATED_BY", this, createdBy);
        } catch (Exception e) {
            throw new AppMenuRoleDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the APP_MENU_ROLE table that match the criteria 'CREATED_DATE = :createdDate'.
     */
    @Transactional
    public List<AppMenuRole> findWhereCreatedDateEquals(Date createdDate) throws AppMenuRoleDaoException {
        try {
            return jdbcTemplate.query("SELECT ROLE_CODE, MENU_CODE, IS_VIEW, IS_CREATE, IS_EDIT, IS_DELETE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE CREATED_DATE = ? AND IS_DELETE='N' ORDER BY CREATED_DATE", this, createdDate);
        } catch (Exception e) {
            throw new AppMenuRoleDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the APP_MENU_ROLE table that match the criteria 'UPDATED_BY = :updatedBy'.
     */
    @Transactional
    public List<AppMenuRole> findWhereUpdatedByEquals(BigDecimal updatedBy) throws AppMenuRoleDaoException {
        try {
            return jdbcTemplate.query("SELECT ROLE_CODE, MENU_CODE, IS_VIEW, IS_CREATE, IS_EDIT, IS_DELETE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE UPDATED_BY = ? AND IS_DELETE='N' ORDER BY UPDATED_BY", this, updatedBy);
        } catch (Exception e) {
            throw new AppMenuRoleDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the APP_MENU_ROLE table that match the criteria 'UPDATED_DATE = :updatedDate'.
     */
    @Transactional
    public List<AppMenuRole> findWhereUpdatedDateEquals(Date updatedDate) throws AppMenuRoleDaoException {
        try {
            return jdbcTemplate.query("SELECT ROLE_CODE, MENU_CODE, IS_VIEW, IS_CREATE, IS_EDIT, IS_DELETE, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE FROM " + getTableName() + " WHERE UPDATED_DATE = ? AND IS_DELETE='N' ORDER BY UPDATED_DATE", this, updatedDate);
        } catch (Exception e) {
            throw new AppMenuRoleDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the APP_MENU_ROLE table that matches the specified primary-key value.
     */
    @Transactional
    public AppMenuRole findByPrimaryKey(AppMenuRolePk pk) throws AppMenuRoleDaoException {
        return findByPrimaryKey(pk.getRoleCode(), pk.getMenuCode());
    }
}

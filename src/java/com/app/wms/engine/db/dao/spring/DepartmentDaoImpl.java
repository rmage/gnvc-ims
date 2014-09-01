package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.DepartmentDao;
import com.app.wms.engine.db.dto.Department;
import com.app.wms.engine.db.dto.DepartmentPk;
import com.app.wms.engine.db.dto.map.DepartmentListMap;
import com.app.wms.engine.db.exceptions.DepartmentDaoException;
import java.math.BigDecimal;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DepartmentDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Department>, DepartmentDao {

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
     * @return DepartmentPk
     */
    public DepartmentPk insert(Department dto) {
        SqlUpdate su = new SqlUpdate(dataSource, "INSERT INTO " + getTableName() + " ( department_code, department_name, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )");
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.compile();
        su.update(new Object[]{dto.getDepartmentCode(), dto.getDepartmentName(), dto.getIsActive(), dto.getIsDelete(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate()});
        DepartmentPk pk = new DepartmentPk();
        pk.setId(jdbcTemplate.queryForInt("select @@IDENTITY"));
        return pk;
    }

    /**
     * Updates a single row in the department table.
     */
    public void update(DepartmentPk pk, Department dto) throws DepartmentDaoException {
        SqlUpdate su = new SqlUpdate(dataSource, "UPDATE " + getTableName() + " SET department_code = ?, department_name = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.compile();
        su.update(new Object[]{dto.getDepartmentCode(), dto.getDepartmentName(), dto.getIsActive(), dto.getIsDelete(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate(), pk.getId()});
    }

    /**
     * Deletes a single row in the department table.
     */
    @Transactional
    public void delete(DepartmentPk pk) throws DepartmentDaoException {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?", pk.getId());
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return Department
     */
    public Department mapRow(ResultSet rs, int row) throws SQLException {
        Department dto = new Department();
        dto.setId(rs.getInt(1));
        dto.setDepartmentCode(rs.getString(2));
        dto.setDepartmentName(rs.getString(3));
        dto.setIsActive(rs.getString(4));
        dto.setIsDelete(rs.getString(5));
        dto.setCreatedBy(rs.getString(6));
        dto.setCreatedDate(rs.getTimestamp(7));
        dto.setUpdatedBy(rs.getString(8));
        dto.setUpdatedDate(rs.getTimestamp(9));
        return dto;
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "department";
    }

    /**
     * Returns all rows from the department table that match the criteria 'id =
     * :id'.
     */
    @Transactional
    public Department findByPrimaryKey(int id) throws DepartmentDaoException {
        try {
            List<Department> list = jdbcTemplate.query("SELECT id, department_code, department_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this, id);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new DepartmentDaoException("Query failed", e);
        }
    }

    /**
     * Returns all rows from the department table that match the criteria ''.
     */
    @Transactional
    public List<Department> findAll() throws DepartmentDaoException {
        try {
            return jdbcTemplate.query("SELECT id, department_code, department_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active='Y' ORDER BY department_code ", this);
        } catch (Exception e) {
            throw new DepartmentDaoException("Query failed", e);
        }
    }

    /**
     * Returns all rows from the department table that match the criteria 'id =
     * :id'.
     */
    @Transactional
    public List<Department> findWhereIdEquals(int id) throws DepartmentDaoException {
        try {
            return jdbcTemplate.query("SELECT id, department_code, department_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this, id);
        } catch (Exception e) {
            throw new DepartmentDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the department table that match the criteria
     * 'department_code = :departmentCode'.
     */
    @Transactional
    public List<Department> findWhereDepartmentCodeEquals(String departmentCode) throws DepartmentDaoException {
        try {
            return jdbcTemplate.query("SELECT id, department_code, department_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE department_code = ? and is_active = 'Y' ORDER BY department_code", this, departmentCode);
        } catch (Exception e) {
            throw new DepartmentDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the department table that match the criteria
     * 'department_name = :departmentName'.
     */
    @Transactional
    public List<Department> findWhereDepartmentNameEquals(String departmentName) throws DepartmentDaoException {
        try {
            return jdbcTemplate.query("SELECT id, department_code, department_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE department_name LIKE ? ORDER BY department_name", this, "%" + departmentName + "%");
        } catch (Exception e) {
            throw new DepartmentDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the department table that match the criteria
     * 'is_active = :isActive'.
     */
    @Transactional
    public List<Department> findWhereIsActiveEquals(String isActive) throws DepartmentDaoException {
        try {
            return jdbcTemplate.query("SELECT id, department_code, department_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this, isActive);
        } catch (Exception e) {
            throw new DepartmentDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the department table that match the criteria
     * 'is_delete = :isDelete'.
     */
    @Transactional
    public List<Department> findWhereIsDeleteEquals(String isDelete) throws DepartmentDaoException {
        try {
            return jdbcTemplate.query("SELECT id, department_code, department_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this, isDelete);
        } catch (Exception e) {
            throw new DepartmentDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the department table that match the criteria
     * 'created_by = :createdBy'.
     */
    @Transactional
    public List<Department> findWhereCreatedByEquals(String createdBy) throws DepartmentDaoException {
        try {
            return jdbcTemplate.query("SELECT id, department_code, department_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this, createdBy);
        } catch (Exception e) {
            throw new DepartmentDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the department table that match the criteria
     * 'created_date = :createdDate'.
     */
    @Transactional
    public List<Department> findWhereCreatedDateEquals(Date createdDate) throws DepartmentDaoException {
        try {
            return jdbcTemplate.query("SELECT id, department_code, department_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this, createdDate);
        } catch (Exception e) {
            throw new DepartmentDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the department table that match the criteria
     * 'updated_by = :updatedBy'.
     */
    @Transactional
    public List<Department> findWhereUpdatedByEquals(String updatedBy) throws DepartmentDaoException {
        try {
            return jdbcTemplate.query("SELECT id, department_code, department_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this, updatedBy);
        } catch (Exception e) {
            throw new DepartmentDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the department table that match the criteria
     * 'updated_date = :updatedDate'.
     */
    @Transactional
    public List<Department> findWhereUpdatedDateEquals(Date updatedDate) throws DepartmentDaoException {
        try {
            return jdbcTemplate.query("SELECT id, department_code, department_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this, updatedDate);
        } catch (Exception e) {
            throw new DepartmentDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the department table that matches the specified
     * primary-key value.
     */
    public Department findByPrimaryKey(DepartmentPk pk) throws DepartmentDaoException {
        return findByPrimaryKey(pk.getId());
    }

    @Transactional
    public List<Department> findDepartmentPaging(Department d, int page) throws DepartmentDaoException {
        try {
            String departmentCode = d.getDepartmentCode();
            String departmentName = d.getDepartmentName();

            int i = page;
            Map map = new HashMap();
            map.put("i", i);

            StringBuffer sb = new StringBuffer();

            if (d == null) {
                d = new Department();
            }

            if (departmentCode == null || departmentName == null) {
                departmentCode = "%";
                departmentName = "%";
            }

            sb.append("declare @Page int, @PageSize int "
                    + "set @Page = '" + i + "'; "
                    + "set @PageSize = 10; "
                    + "with PagedResult "
                    + "as (select id, department_code, department_name, is_active, ROW_NUMBER() over (order by id asc) as ids from department"
                    + " where department_code like '%" + departmentCode + "%' and department_name like '%" + departmentName + "%' and is_active = 'Y' ) "
                    + "select * from PagedResult where ids between "
                    + "case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
                    + "else @Page end and @PageSize * @Page order by department_code");

            return jdbcTemplate.query(sb.toString(), new DepartmentListMap(), map);

        } catch (Exception e) {
            throw new DepartmentDaoException("Query failed", e);
        }

    }

    //Modified 10 April 2014
    public int ajaxMaxPage(String where, BigDecimal show) {
        return jdbcTemplate.queryForInt("SELECT CEILING(COUNT(id)/?) maxpage FROM " + getTableName() + " " + (where.isEmpty() ? "WHERE is_active='Y'" : where + " AND is_active='Y'"), show);
    }

    public List<Department> ajaxSearch(String where, String order, int page, int show) {
        return jdbcTemplate.query("DECLARE @page INT, @show INT "
                + "SELECT @page=?, @show=? "
                + "SELECT * FROM ("
                + "SELECT id, department_code, department_name, is_active, ROW_NUMBER() OVER (" + (order.isEmpty() ? "ORDER BY id" : order) + ") row FROM " + getTableName() + " " + (where.isEmpty() ? "WHERE is_active='Y'" : where + " AND is_active='Y'")
                + ") list WHERE row BETWEEN (((@page - 1) * @show) + 1) AND (@page * @show)", new DepartmentListMap(), page, show);
    }
    
    public Department findId(int id){
        List<Department> departments = jdbcTemplate.query("SELECT id, department_code, department_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id=?", this, id);
        return departments.isEmpty() ? null : departments.get(0);
    }
}

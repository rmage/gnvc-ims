package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.ApprovalRangeDao;
import com.app.wms.engine.db.dto.ApprovalRange;
import com.app.wms.engine.db.dto.ApprovalRangePk;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.Uom;
import com.app.wms.engine.db.dto.map.AppRangeRoleCodeListMap;
import com.app.wms.engine.db.dto.map.SupplierListMap;
import com.app.wms.engine.db.exceptions.ApprovalRangeDaoException;
import com.app.wms.engine.db.exceptions.SupplierDaoException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class ApprovalRangeDaoImpl extends AbstractDAO implements ParameterizedRowMapper<ApprovalRange>, ApprovalRangeDao {

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
     * @return ApprovalRangePk
     */
    public ApprovalRangePk insert(ApprovalRange dto) {
        SqlUpdate su = new SqlUpdate(dataSource, "INSERT INTO " + getTableName() + " ( username, role_code, from_amount, to_amount, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
        su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.compile();
        su.update(new Object[]{dto.getUsername(), dto.getRoleCode(), dto.getFromAmount(), dto.getToAmount(), dto.getIsActive(), dto.getIsDelete(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate()});
        ApprovalRangePk pk = new ApprovalRangePk();
        pk.setId(jdbcTemplate.queryForInt("select @@IDENTITY"));
        return pk;
    }

    /**
     * Updates a single row in the approval_range table.
     */
    public void update(ApprovalRangePk pk, ApprovalRange dto) throws ApprovalRangeDaoException {
        SqlUpdate su = new SqlUpdate(dataSource, "UPDATE " + getTableName() + " SET username = ?, role_code = ?, from_amount = ?, to_amount = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
        su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.compile();
        su.update(new Object[]{dto.getUsername(), dto.getRoleCode(), dto.getFromAmount(), dto.getToAmount(), dto.getIsActive(), dto.getIsDelete(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate(), pk.getId()});
    }

    /**
     * Deletes a single row in the approval_range table.
     */
    @Transactional
    public void delete(ApprovalRangePk pk) throws ApprovalRangeDaoException {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?", pk.getId());
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return ApprovalRange
     */
    public ApprovalRange mapRow(ResultSet rs, int row) throws SQLException {
        ApprovalRange dto = new ApprovalRange();
        dto.setId(rs.getInt(1));
        dto.setUsername(rs.getString(2));
        dto.setRoleCode(rs.getString(3));
        dto.setFromAmount(rs.getBigDecimal(4));
        dto.setToAmount(rs.getBigDecimal(5));
        dto.setIsActive(rs.getString(6));
        dto.setIsDelete(rs.getString(7));
        dto.setCreatedBy(rs.getString(8));
        dto.setCreatedDate(rs.getTimestamp(9));
        dto.setUpdatedBy(rs.getString(10));
        dto.setUpdatedDate(rs.getTimestamp(11));
        return dto;
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "approval_range";
    }

    /**
     * Returns all rows from the approval_range table that match the criteria
     * 'id = :id'.
     */
    @Transactional
    public ApprovalRange findByPrimaryKey(int id) throws ApprovalRangeDaoException {
        try {
            List<ApprovalRange> list = jdbcTemplate.query("SELECT id, username, role_code, from_amount, to_amount, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this, id);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new ApprovalRangeDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the approval_range table that match the criteria
     * ''.
     */
    @Transactional
    public List<ApprovalRange> findAll() throws ApprovalRangeDaoException {
        try {
            return jdbcTemplate.query("SELECT id, username, role_code, from_amount, to_amount, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = 'Y' ORDER BY from_amount DESC", this);
        } catch (Exception e) {
            throw new ApprovalRangeDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the approval_range table that match the criteria
     * 'id = :id'.
     */
    @Transactional
    public List<ApprovalRange> findWhereIdEquals(int id) throws ApprovalRangeDaoException {
        try {
            return jdbcTemplate.query("SELECT id, username, role_code, from_amount, to_amount, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this, id);
        } catch (Exception e) {
            throw new ApprovalRangeDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the approval_range table that match the criteria
     * 'username = :username'.
     */
    @Transactional
    public List<ApprovalRange> findWhereUsernameEquals(String username) throws ApprovalRangeDaoException {
        try {
            return jdbcTemplate.query("SELECT id, username, role_code, from_amount, to_amount, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE username = ? ORDER BY username", this, username);
        } catch (Exception e) {
            throw new ApprovalRangeDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the approval_range table that match the criteria
     * 'role_code = :roleCode'.
     */
    @Transactional
    public List<ApprovalRange> findWhereRoleCodeEquals(String roleCode) throws ApprovalRangeDaoException {
        try {
            return jdbcTemplate.query("SELECT id, username, role_code, from_amount, to_amount, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE role_code = ? ORDER BY role_code", this, roleCode);
        } catch (Exception e) {
            throw new ApprovalRangeDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the approval_range table that match the criteria
     * 'val_amount = :valAmount'.
     */
    @Transactional
    public List<ApprovalRange> findWhereValAmountEquals(float valAmount) throws ApprovalRangeDaoException {
        try {
            return jdbcTemplate.query("SELECT id, username, role_code, from_amount, to_amount, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE val_amount = ? ORDER BY val_amount", this, valAmount);
        } catch (Exception e) {
            throw new ApprovalRangeDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the approval_range table that match the criteria
     * 'is_active = :isActive'.
     */
    @Transactional
    public List<ApprovalRange> findWhereIsActiveEquals(String isActive) throws ApprovalRangeDaoException {
        try {
            return jdbcTemplate.query("SELECT id, username, role_code, from_amount, to_amount, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this, isActive);
        } catch (Exception e) {
            throw new ApprovalRangeDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the approval_range table that match the criteria
     * 'is_delete = :isDelete'.
     */
    @Transactional
    public List<ApprovalRange> findWhereIsDeleteEquals(String isDelete) throws ApprovalRangeDaoException {
        try {
            return jdbcTemplate.query("SELECT id, username, role_code, from_amount, to_amount, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this, isDelete);
        } catch (Exception e) {
            throw new ApprovalRangeDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the approval_range table that match the criteria
     * 'created_by = :createdBy'.
     */
    @Transactional
    public List<ApprovalRange> findWhereCreatedByEquals(String createdBy) throws ApprovalRangeDaoException {
        try {
            return jdbcTemplate.query("SELECT id, username, role_code, from_amount, to_amount, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this, createdBy);
        } catch (Exception e) {
            throw new ApprovalRangeDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the approval_range table that match the criteria
     * 'created_date = :createdDate'.
     */
    @Transactional
    public List<ApprovalRange> findWhereCreatedDateEquals(Date createdDate) throws ApprovalRangeDaoException {
        try {
            return jdbcTemplate.query("SELECT id, username, role_code, from_amount, to_amount, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this, createdDate);
        } catch (Exception e) {
            throw new ApprovalRangeDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the approval_range table that match the criteria
     * 'updated_by = :updatedBy'.
     */
    @Transactional
    public List<ApprovalRange> findWhereUpdatedByEquals(String updatedBy) throws ApprovalRangeDaoException {
        try {
            return jdbcTemplate.query("SELECT id, username, role_code, from_amount, to_amount, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this, updatedBy);
        } catch (Exception e) {
            throw new ApprovalRangeDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the approval_range table that match the criteria
     * 'updated_date = :updatedDate'.
     */
    @Transactional
    public List<ApprovalRange> findWhereUpdatedDateEquals(Date updatedDate) throws ApprovalRangeDaoException {
        try {
            return jdbcTemplate.query("SELECT id, username, role_code, from_amount, to_amount, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this, updatedDate);
        } catch (Exception e) {
            throw new ApprovalRangeDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the approval_range table that matches the specified
     * primary-key value.
     */
    public ApprovalRange findByPrimaryKey(ApprovalRangePk pk) throws ApprovalRangeDaoException {
        return findByPrimaryKey(pk.getId());
    }

    @Override
    public List<ApprovalRange> findApprovalRangePaging(ApprovalRange a, int page) throws ApprovalRangeDaoException {
        try {
            String userName = a.getUsername();
            String roleCode = a.getRoleCode();

            int i = page;
            Map map = new HashMap();
            map.put("i", i);

            StringBuffer sb = new StringBuffer();

            if (a == null) {
                a = new ApprovalRange();
            }

            if (userName == null || roleCode == null) {
                userName = "%";
                roleCode = "%";
            }

            sb.append("declare @Page int, @PageSize int "
                    + "set @Page = '" + i + "'; "
                    + "set @PageSize = 10; "
                    + "with PagedResult "
                    + "as (select id, username, role_code, from_amount, to_amount, is_active, is_delete, created_by, created_date, updated_by, updated_date, ROW_NUMBER() over (order by id asc) as ids from approval_range ) "
                    + "select * from PagedResult where ids between "
                    + "case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
                    + "else @Page end and @PageSize * @Page ");

            return jdbcTemplate.query(sb.toString(), this, map);
        } catch (Exception e) {
            throw new ApprovalRangeDaoException("Query failed", e);
        }
    }

    public List<ApprovalRange> findApprovalRangeTotal(BigDecimal total) throws ApprovalRangeDaoException {

        try {

            Map map = new HashMap();
            map.put("total", total);

            StringBuffer sb = new StringBuffer();

            sb.append(" SELECT id, username, role_code, from_amount, to_amount, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName()
                    + " WHERE '" + total + "' BETWEEN from_amount AND to_amount ");

            return jdbcTemplate.query(sb.toString(), this, map);

        } catch (Exception e) {
            throw new ApprovalRangeDaoException("Query failed", e);
        }
    }

    @Override
    public List<ApprovalRange> findApprovalRangeRoleCode(String departmentCode, BigDecimal total, BigDecimal from, BigDecimal to) throws ApprovalRangeDaoException {

        try {

            Map map = new HashMap();
            map.put("departmentCode", departmentCode);
            map.put("total", total);
            map.put("from", from);
            map.put("to", to);

            StringBuffer sb = new StringBuffer();

            sb.append(" SELECT ar.username, ar.role_code, ar.from_amount, ar.to_amount, ur.department_code "
                    + " FROM approval_range ar inner join user_role ur on ar.role_code = ur.role_code "
                    + " WHERE ur.department_code like '%" + departmentCode + "%'  "
                    + " AND '" + total + "' BETWEEN from_amount AND to_amount "
                    + " AND ar.from_amount='" + from + "' AND ar.to_amount='" + to + "' ");

            return jdbcTemplate.query(sb.toString(), new AppRangeRoleCodeListMap(), map);

        } catch (Exception e) {
            throw new ApprovalRangeDaoException("Query failed", e);
        }
    }

    //Modified 14 April 2014
    public int ajaxMaxPage(String where, BigDecimal show) {
        return jdbcTemplate.queryForInt("SELECT CEILING(COUNT(id)/?) maxpage FROM " + getTableName() + " " + (where.isEmpty() ? "WHERE is_active='Y'" : where + " AND is_active='Y'"), show);
    }

    public List<ApprovalRange> ajaxSearch(String where, String order, int page, int show) {
        return jdbcTemplate.query("DECLARE @page INT, @show INT "
                + "SELECT @page=?, @show=? "
                + "SELECT * FROM ("
                + "SELECT id, username, role_code, from_amount, to_amount, is_active, is_delete, created_by, created_date, updated_by, updated_date, ROW_NUMBER() OVER (" + (order.isEmpty() ? "ORDER BY id" : order) + ") row FROM " + getTableName() + " " + (where.isEmpty() ? "WHERE is_active='Y'" : where + " AND is_active='Y'")
                + ") list WHERE row BETWEEN (((@page - 1) * @show) + 1) AND (@page * @show)", this, page, show);
    }
    
    public ApprovalRange findId(int id){
        List<ApprovalRange> ranges = jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE id=?", this, id);
        return ranges.isEmpty() ? null : ranges.get(0);
    }
}

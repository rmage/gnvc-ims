package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.SupplierPk;
import com.app.wms.engine.db.dto.map.SupplierListMap;
import com.app.wms.engine.db.exceptions.SupplierDaoException;
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

public class SupplierDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Supplier>, SupplierDao {

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
     * @return SupplierPk
     */
    public SupplierPk insert(Supplier dto) {
        SqlUpdate su = new SqlUpdate(dataSource, "INSERT INTO " + getTableName() + " ( supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.compile();
        su.update(new Object[]{dto.getSupplierCode(), dto.getSupplierName(), dto.getSupplierAddress(), dto.getTelephone(), dto.getFax(), dto.getEmail(), dto.getContactPerson(), dto.getIsActive(), dto.getIsDelete(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate()});
        SupplierPk pk = new SupplierPk();
        pk.setId(jdbcTemplate.queryForInt("select @@IDENTITY"));
        return pk;
    }

    /**
     * Updates a single row in the supplier table.
     */
    public void update(SupplierPk pk, Supplier dto) throws SupplierDaoException {
        SqlUpdate su = new SqlUpdate(dataSource, "UPDATE " + getTableName() + " SET supplier_code = ?, supplier_name = ?, supplier_address = ?, telephone = ?, fax = ?, email = ?, contact_person = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
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
        su.update(new Object[]{dto.getSupplierCode(), dto.getSupplierName(), dto.getSupplierAddress(), dto.getTelephone(), dto.getFax(), dto.getEmail(), dto.getContactPerson(), dto.getIsActive(), dto.getIsDelete(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate(), pk.getId()});
    }

    /**
     * Deletes a single row in the supplier table.
     */
    @Transactional
    public void delete(SupplierPk pk) throws SupplierDaoException {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?", pk.getId());
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return Supplier
     */
    public Supplier mapRow(ResultSet rs, int row) throws SQLException {
        Supplier dto = new Supplier();
        dto.setId(rs.getInt(1));
        dto.setSupplierCode(rs.getString(2));
        dto.setSupplierName(rs.getString(3));
        dto.setSupplierAddress(rs.getString(4));
        dto.setTelephone(rs.getString(5));
        dto.setFax(rs.getString(6));
        dto.setEmail(rs.getString(7));
        dto.setContactPerson(rs.getString(8));
        dto.setIsActive(rs.getString(9));
        dto.setIsDelete(rs.getString(10));
        dto.setCreatedBy(rs.getString(11));
        dto.setCreatedDate(rs.getTimestamp(12));
        dto.setUpdatedBy(rs.getString(13));
        dto.setUpdatedDate(rs.getTimestamp(14));
        return dto;
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "supplier";
    }

    /**
     * Returns all rows from the supplier table that match the criteria 'id =
     * :id'.
     */
    @Transactional
    public Supplier findByPrimaryKey(int id) throws SupplierDaoException {
        try {
            List<Supplier> list = jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this, id);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new SupplierDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the supplier table that match the criteria ''.
     */
    @Transactional
    public List<Supplier> findAll() throws SupplierDaoException {
        try {
            return jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY id", this);
        } catch (Exception e) {
            throw new SupplierDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the supplier table that match the criteria 'id =
     * :id'.
     */
    @Transactional
    public List<Supplier> findWhereIdEquals(int id) throws SupplierDaoException {
        try {
            return jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this, id);
        } catch (Exception e) {
            throw new SupplierDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the supplier table that match the criteria
     * 'supplier_code = :supplierCode'.
     */
    @Transactional
    public List<Supplier> findWhereSupplierCodeEquals(String supplierCode) throws SupplierDaoException {
        try {
            return jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE supplier_code = ? AND is_active = 'Y' ORDER BY supplier_code", this, supplierCode);
        } catch (Exception e) {
            throw new SupplierDaoException("Query failed", e);
        }
    }

    /**
     * Returns all rows from the supplier table that match the criteria
     * 'supplier_name = :supplierName'.
     */
    @Transactional
    public List<Supplier> findWhereSupplierNameEquals(String supplierName) throws SupplierDaoException {
        try {
            return jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE supplier_name LIKE ? ORDER BY supplier_name", this, "%" + supplierName + "%");
        } catch (Exception e) {
            throw new SupplierDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the supplier table that match the criteria
     * 'supplier_address = :supplierAddress'.
     */
    @Transactional
    public List<Supplier> findWhereSupplierAddressEquals(String supplierAddress) throws SupplierDaoException {
        try {
            return jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE supplier_address = ? ORDER BY supplier_address", this, supplierAddress);
        } catch (Exception e) {
            throw new SupplierDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the supplier table that match the criteria
     * 'telephone = :telephone'.
     */
    @Transactional
    public List<Supplier> findWhereTelephoneEquals(String telephone) throws SupplierDaoException {
        try {
            return jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE telephone = ? ORDER BY telephone", this, telephone);
        } catch (Exception e) {
            throw new SupplierDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the supplier table that match the criteria 'fax =
     * :fax'.
     */
    @Transactional
    public List<Supplier> findWhereFaxEquals(String fax) throws SupplierDaoException {
        try {
            return jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE fax = ? ORDER BY fax", this, fax);
        } catch (Exception e) {
            throw new SupplierDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the supplier table that match the criteria 'email =
     * :email'.
     */
    @Transactional
    public List<Supplier> findWhereEmailEquals(String email) throws SupplierDaoException {
        try {
            return jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE email = ? ORDER BY email", this, email);
        } catch (Exception e) {
            throw new SupplierDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the supplier table that match the criteria
     * 'contact_person = :contactPerson'.
     */
    @Transactional
    public List<Supplier> findWhereContactPersonEquals(String contactPerson) throws SupplierDaoException {
        try {
            return jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE contact_person = ? ORDER BY contact_person", this, contactPerson);
        } catch (Exception e) {
            throw new SupplierDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the supplier table that match the criteria
     * 'is_active = :isActive'.
     */
    @Transactional
    public List<Supplier> findWhereIsActiveEquals(String isActive) throws SupplierDaoException {
        try {
            return jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this, isActive);
        } catch (Exception e) {
            throw new SupplierDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the supplier table that match the criteria
     * 'is_delete = :isDelete'.
     */
    @Transactional
    public List<Supplier> findWhereIsDeleteEquals(String isDelete) throws SupplierDaoException {
        try {
            return jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this, isDelete);
        } catch (Exception e) {
            throw new SupplierDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the supplier table that match the criteria
     * 'created_by = :createdBy'.
     */
    @Transactional
    public List<Supplier> findWhereCreatedByEquals(String createdBy) throws SupplierDaoException {
        try {
            return jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this, createdBy);
        } catch (Exception e) {
            throw new SupplierDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the supplier table that match the criteria
     * 'created_date = :createdDate'.
     */
    @Transactional
    public List<Supplier> findWhereCreatedDateEquals(Date createdDate) throws SupplierDaoException {
        try {
            return jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this, createdDate);
        } catch (Exception e) {
            throw new SupplierDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the supplier table that match the criteria
     * 'updated_by = :updatedBy'.
     */
    @Transactional
    public List<Supplier> findWhereUpdatedByEquals(String updatedBy) throws SupplierDaoException {
        try {
            return jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this, updatedBy);
        } catch (Exception e) {
            throw new SupplierDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the supplier table that match the criteria
     * 'updated_date = :updatedDate'.
     */
    @Transactional
    public List<Supplier> findWhereUpdatedDateEquals(Date updatedDate) throws SupplierDaoException {
        try {
            return jdbcTemplate.query("SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this, updatedDate);
        } catch (Exception e) {
            throw new SupplierDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the supplier table that matches the specified
     * primary-key value.
     */
    public Supplier findByPrimaryKey(SupplierPk pk) throws SupplierDaoException {
        return findByPrimaryKey(pk.getId());
    }

    @Transactional
    public List<Supplier> findSupplierPaging(Supplier s, int page) throws SupplierDaoException {
        try {
            String supplierCode = s.getSupplierCode();
            String supplierName = s.getSupplierName();

            int i = page;
            Map map = new HashMap();
            map.put("i", i);

            StringBuffer sb = new StringBuffer();

            if (s == null) {
                s = new Supplier();
            }

            if (supplierCode == null || supplierName == null) {
                supplierCode = "%";
                supplierName = "%";
            }
            sb.append("declare @Page int, @PageSize int "
                    + "set @Page = '" + i + "'; "
                    + "set @PageSize = 10; "
                    + "with PagedResult "
                    + "as (select id, supplier_code, supplier_name, is_active, ROW_NUMBER() over (order by id asc) as ids from supplier"
                    + " where supplier_code like '%" + supplierCode + "%' and supplier_name like '%" + supplierName + "%' and is_active = 'Y' ) "
                    + "select * from PagedResult where ids between "
                    + "case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
                    + "else @Page end and @PageSize * @Page ");

            return jdbcTemplate.query(sb.toString(), new SupplierListMap(), map);

        } catch (Exception e) {
            throw new SupplierDaoException("Query failed", e);
        }

    }

    //Modified 9 April 2014
    public int ajaxMaxPage(String where, BigDecimal show) {
        return jdbcTemplate.queryForInt("SELECT CEILING(COUNT(id)/?) maxpage FROM " + getTableName() + " " + (where.isEmpty()? "WHERE is_active='Y'" : where + " AND is_active='Y'"), show);
    }

    public List<Supplier> ajaxSearch(String where, String order, int page, int show) {
        return jdbcTemplate.query("DECLARE @page INT, @show INT "
                + "SELECT @page=?, @show=? "
                + "SELECT * FROM ("
                + "SELECT id, supplier_code, supplier_name, supplier_address, telephone, fax, email, contact_person, is_active, is_delete, created_by, created_date, updated_by, updated_date , ROW_NUMBER() OVER (" + (order.isEmpty() ? "ORDER BY id" : order) + ") row FROM " + getTableName() + " " + (where.isEmpty()? "WHERE is_active='Y'" : where + " AND is_active='Y'")
                + ") list WHERE row BETWEEN (((@page - 1) * @show) + 1) AND (@page * @show)", this, page, show);
    }

    public Supplier findId(int id){
        List<Supplier> supplier = jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE id=?", this, id);
        return supplier.isEmpty() ? null : supplier.get(0);
    }
    
    public void edit(Supplier s){
        jdbcTemplate.update("UPDATE " + getTableName() + " SET supplier_name=?, supplier_address=?, telephone=?, fax=?, email=?, updated_by=?, updated_date=? WHERE id=?",
                s.getSupplierName(), s.getSupplierAddress(), s.getTelephone(), s.getFax(), s.getEmail(), s.getUpdatedBy(), s.getUpdatedDate(), s.getId());
    }
}

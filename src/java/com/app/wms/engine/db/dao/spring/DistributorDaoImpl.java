package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.DistributorDao;
import com.app.wms.engine.db.dto.Distributor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class DistributorDaoImpl extends AbstractDAO
        implements ParameterizedRowMapper<Distributor>, DistributorDao {

    SimpleJdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public Distributor mapRow(ResultSet rs, int i) throws SQLException {
        Distributor d = new Distributor();
        d.setId(rs.getInt("id"));
        d.setDistributorCode(rs.getString("distributor_code"));
        d.setDistributorName(rs.getString("distributor_name"));
        d.setDistributorAddress(rs.getString("distributor_address"));
        d.setTelephone(rs.getString("telephone"));
        d.setFax(rs.getString("fax"));
        d.setEmail(rs.getString("email"));
        d.setContactPerson(rs.getString("contact_person"));
        d.setIsActive(rs.getString("is_active"));
        d.setIsDelete(rs.getString("is_delete"));
        d.setCreatedBy(rs.getString("created_by"));
        d.setCreatedDate(rs.getDate("created_date"));
        d.setUpdatedBy(rs.getString("updated_by"));
        d.setUpdatedDate(rs.getDate("updated_date"));
        return d;
    }

    public void insert(Distributor d) {
        jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES (?,?,?,?,?,?,?,?,?,?,?,null,null)",
                d.getDistributorCode(), d.getDistributorName(), d.getDistributorAddress(), d.getTelephone(), d.getFax(), d.getEmail(), d.getContactPerson(), d.getIsActive(), d.getIsDelete(), d.getCreatedBy(), d.getCreatedDate());
    }

    public List<Distributor> findAll() {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE is_active='Y'  ORDER BY created_date DESC", this);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "inventory..distributor";
    }

    public void edit(Distributor d) {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET distributor_name=?, distributor_address=?, telephone=?, fax=?, email=?, updated_by=?, updated_date=? WHERE id=?",
                d.getDistributorName(), d.getDistributorAddress(), d.getTelephone(), d.getFax(), d.getEmail(), d.getUpdatedBy(), d.getUpdatedDate(), d.getId());
    }

    public void delete(Distributor d) {
        jdbcTemplate.update("UPDATE " + getTableName() + " SET is_delete='Y', is_active='N', updated_by=?, updated_date=? WHERE id=?", d.getUpdatedBy(), d.getUpdatedDate(), d.getId());
    }

    public List<Distributor> ajaxSearch(String where, String order, int page, int show) {
        return jdbcTemplate.query("DECLARE @page INT, @show INT "
                + "SELECT @page=?, @show=? "
                + "SELECT * FROM ("
                + "SELECT * , ROW_NUMBER() OVER (" + (order.isEmpty() ? "ORDER BY id" : order) + ") row FROM " + getTableName() + " " + (where.isEmpty()? "WHERE is_active='Y'" : where + " AND is_active='Y'")
                + ") list WHERE row BETWEEN (((@page - 1) * @show) + 1) AND (@page * @show)", this, page, show);
    }

    public int ajaxMaxPage(String where, BigDecimal show) {
        return jdbcTemplate.queryForInt("SELECT CEILING(COUNT(id)/?) maxpage FROM " + getTableName() + " " + (where.isEmpty()? "WHERE is_active='Y'" : where + " AND is_active='Y'"), show);
    }

    public Distributor findId(int id) {
        List<Distributor> distributors = jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE id=?", this, id);
        return distributors.isEmpty() ? null : distributors.get(0);
    }
    
    public Distributor findByCode(String code) {
        List<Distributor> ds = jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE distributor_code = ?", this, code);
        return ds.isEmpty() ? null : ds.get(0);
    }
    
    public List<Distributor> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " WHERE distributor_name LIKE ?", this, "%" + name + "%");
    }
    
}

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
        jdbcTemplate.update("INSERT INTO distributor VALUES (?,?,?,?,?,?,?,?,?,?,?,null,null)",
                d.getDistributorCode(), d.getDistributorName(), d.getDistributorAddress(), d.getTelephone(), d.getFax(), d.getEmail(), d.getContactPerson(), d.getIsActive(), d.getIsDelete(), d.getCreatedBy(), d.getCreatedDate());
    }

    public List<Distributor> findAll() {
        return jdbcTemplate.query("SELECT * FROM " + getTableName() + " ORDER BY created_date DESC", this);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "inventory..distributor";
    }

    public void edit(Distributor d) {
        jdbcTemplate.update("UPDATE distributor SET distributor_name=?, distributor_address=?, telephone=?, fax=?, email=? WHERE distributor_code=?",
                d.getDistributorName(), d.getDistributorAddress(), d.getTelephone(), d.getFax(), d.getEmail(), d.getDistributorCode());
    }

    public void delete(Distributor d) {
        jdbcTemplate.update("UPDATE distributor SET is_delete = 'Y' WHERE distributor_code=?", d.getDistributorCode());
    }

    public List<Distributor> ajaxSearch(String where, String order, int page, int show) {
        return jdbcTemplate.query("DECLARE @page INT, @show INT "
                + "SELECT @page=?, @show=? "
                + "SELECT * FROM ("
                + "SELECT * , ROW_NUMBER() OVER (" + (order.isEmpty() ? "ORDER BY id" : order) + ") row FROM distributor " + where
                + ") list WHERE row BETWEEN (((@page - 1) * @show) + 1) AND (@page * @show)", this, page, show);
    }

    public int ajaxMaxPage(String where, BigDecimal show) {
        return jdbcTemplate.queryForInt("SELECT CEILING(COUNT(id)/?) maxpage FROM distributor " + where, show);
    }

}

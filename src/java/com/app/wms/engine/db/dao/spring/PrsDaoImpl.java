package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.PrsDao;
import com.app.wms.engine.db.dto.PoDetail;
import com.app.wms.engine.db.dto.Prs;
import com.app.wms.engine.db.dto.PrsPk;
import com.app.wms.engine.db.dto.map.PrsListMap;
import com.app.wms.engine.db.exceptions.PoDaoException;
import com.app.wms.engine.db.exceptions.PoDetailDaoException;
import com.app.wms.engine.db.exceptions.PrsDaoException;
import java.math.BigDecimal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class PrsDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Prs>, PrsDao {

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
     * @return PrsPk
     */
    public PrsPk insert(Prs dto) {
        SqlUpdate su = new SqlUpdate(dataSource, "INSERT INTO " + getTableName() + " ( prsnumber, prsdate, requestdate, deliverydate, poreferensi, remarks, createdby, department_name, is_approved) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.compile();
        su.update(new Object[]{dto.getPrsnumber(), dto.getPrsdate(), dto.getRequestdate(), dto.getDeliverydate(), dto.getPoreferensi(), dto.getRemarks(), dto.getCreatedby(), dto.getDepartmentName(), dto.getIsApproved()});
        PrsPk pk = new PrsPk();
        pk.setId(jdbcTemplate.queryForInt("select @@IDENTITY"));
        return pk;
    }

    /**
     * Updates a single row in the prs table.
     */
    public void update(PrsPk pk, Prs dto) throws PrsDaoException {
        SqlUpdate su = new SqlUpdate(dataSource, "UPDATE " + getTableName() + " SET prsnumber = ?, prsdate = ?, requestdate = ?, deliverydate = ?, poreferensi = ?, remarks = ?, createdby = ?, department_name = ?, is_approved = ? WHERE id = ?");
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.compile();
        su.update(new Object[]{dto.getPrsnumber(), dto.getPrsdate(), dto.getRequestdate(), dto.getDeliverydate(), dto.getPoreferensi(), dto.getRemarks(), dto.getCreatedby(), dto.getDepartmentName(), dto.getIsApproved(), pk.getId()});
    }

    /**
     * Deletes a single row in the prs table.
     */
    @Transactional
    public void delete(PrsPk pk) throws PrsDaoException {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?", pk.getId());
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return Prs
     */
    public Prs mapRow(ResultSet rs, int row) throws SQLException {
        Prs dto = new Prs();
        dto.setId(rs.getInt(1));
        dto.setPrsnumber(rs.getString(2));
        dto.setPrsdate(rs.getTimestamp(3));
        dto.setRequestdate(rs.getTimestamp(4));
        dto.setDeliverydate(rs.getTimestamp(5));
        dto.setPoreferensi(rs.getString(6));
        dto.setRemarks(rs.getString(7));
        dto.setCreatedby(rs.getString(8));
        dto.setDepartmentName(rs.getString(9));
        dto.setIsApproved(rs.getString(10));
        return dto;
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "prs";
    }

    /**
     * Returns all rows from the prs table that match the criteria 'id = :id'.
     */
    @Transactional
    public Prs findByPrimaryKey(int id) throws PrsDaoException {
        try {
            List<Prs> list = jdbcTemplate.query("SELECT id, prsnumber, prsdate, requestdate, deliverydate, poreferensi, remarks, createdby, department_name, is_approved FROM " + getTableName() + " WHERE id = ?", this, id);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new PrsDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the prs table that match the criteria ''.
     */
    @Transactional
    public List<Prs> findAll() throws PrsDaoException {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, prsdate, requestdate, deliverydate, poreferensi, remarks, createdby, department_name, is_approved FROM " + getTableName() + " ORDER BY id", this);
        } catch (Exception e) {
            throw new PrsDaoException("Query failed", e);
        }
    }

    /**
     * Returns all rows from the prs table that match the criteria 'id = :id'.
     */
    @Transactional
    public List<Prs> findWhereIdEquals(int id) throws PrsDaoException {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, prsdate, requestdate, deliverydate, poreferensi, remarks, createdby, department_name, is_approved FROM " + getTableName() + " WHERE id = ? ORDER BY id", this, id);
        } catch (Exception e) {
            throw new PrsDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the prs table that match the criteria 'prsnumber =
     * :prsnumber'.
     */
    @Transactional
    public List<Prs> findWherePrsnumberEquals(String prsnumber) throws PrsDaoException {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, prsdate, requestdate, deliverydate, poreferensi, remarks, createdby, department_name, is_approved FROM " + getTableName() + " where prsnumber not in (select DISTINCT prsnumber from po_detail) AND prsnumber like '%" + prsnumber + "%' ORDER BY prsnumber desc", this);
        } catch (Exception e) {
            throw new PrsDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the prs table that match the criteria 'prsdate =
     * :prsdate'.
     */
    @Transactional
    public List<Prs> findWherePrsdateEquals(Date prsdate) throws PrsDaoException {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, prsdate, requestdate, deliverydate, poreferensi, remarks, createdby, department_name, is_approved FROM " + getTableName() + " WHERE prsdate = ? ORDER BY prsdate", this, prsdate);
        } catch (Exception e) {
            throw new PrsDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the prs table that match the criteria 'requestdate
     * = :requestdate'.
     */
    @Transactional
    public List<Prs> findWhereRequestdateEquals(Date requestdate) throws PrsDaoException {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, prsdate, requestdate, deliverydate, poreferensi, remarks, createdby, department_name, is_approved FROM " + getTableName() + " WHERE requestdate = ? ORDER BY requestdate", this, requestdate);
        } catch (Exception e) {
            throw new PrsDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the prs table that match the criteria 'deliverydate
     * = :deliverydate'.
     */
    @Transactional
    public List<Prs> findWhereDeliverydateEquals(Date deliverydate) throws PrsDaoException {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, prsdate, requestdate, deliverydate, poreferensi, remarks, createdby, department_name, is_approved FROM " + getTableName() + " WHERE deliverydate = ? ORDER BY deliverydate", this, deliverydate);
        } catch (Exception e) {
            throw new PrsDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the prs table that match the criteria 'poreferensi
     * = :poreferensi'.
     */
    @Transactional
    public List<Prs> findWherePoreferensiEquals(String poreferensi) throws PrsDaoException {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, prsdate, requestdate, deliverydate, poreferensi, remarks, createdby, department_name, is_approved FROM " + getTableName() + " WHERE poreferensi = ? ORDER BY poreferensi", this, poreferensi);
        } catch (Exception e) {
            throw new PrsDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the prs table that match the criteria 'remarks =
     * :remarks'.
     */
    @Transactional
    public List<Prs> findWhereRemarksEquals(String remarks) throws PrsDaoException {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, prsdate, requestdate, deliverydate, poreferensi, remarks, createdby, department_name, is_approved FROM " + getTableName() + " WHERE remarks = ? ORDER BY remarks", this, remarks);
        } catch (Exception e) {
            throw new PrsDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the prs table that match the criteria 'createdby =
     * :createdby'.
     */
    @Transactional
    public List<Prs> findWhereCreatedbyEquals(String createdby) throws PrsDaoException {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, prsdate, requestdate, deliverydate, poreferensi, remarks, createdby, department_name, is_approved FROM " + getTableName() + " WHERE createdby = ? ORDER BY createdby", this, createdby);
        } catch (Exception e) {
            throw new PrsDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the prs table that match the criteria
     * 'department_name = :departmentName'.
     */
    @Transactional
    public List<Prs> findWhereDepartmentNameEquals(String departmentName) throws PrsDaoException {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, prsdate, requestdate, deliverydate, poreferensi, remarks, createdby, department_name, is_approved FROM " + getTableName() + " WHERE department_name = ? ORDER BY department_name", this, departmentName);
        } catch (Exception e) {
            throw new PrsDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the prs table that matches the specified
     * primary-key value.
     */
    public Prs findByPrimaryKey(PrsPk pk) throws PrsDaoException {
        return findByPrimaryKey(pk.getId());
    }

    @Override
    public List<Prs> findPrsPaging(Prs p, int page) throws PrsDaoException {
        try {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String prsNo = p.getPrsnumber();
            String prsDate = df.format(p.getPrsdate());

            int i = page;
            Map map = new HashMap();
            map.put("i", i);

            StringBuffer sb = new StringBuffer();

            sb.append("declare @Page int, @PageSize int "
                    + "set @Page = '" + i + "'; "
                    + "set @PageSize = 10; "
                    + "with PagedResult "
                    + "as (select ROW_NUMBER() over (order by id asc) as id, prsnumber, "
                    + " prsdate, requestdate, deliverydate, poreferensi, remarks, createdby, department_name, is_approved "
                    + " from prs"
                    + " where prsnumber like '%" + prsNo + "%' and prsdate='" + prsDate + "' ) "
                    + "select * from PagedResult where id between "
                    + "case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
                    + "else @Page end and @PageSize * @Page ");

            return jdbcTemplate.query(sb.toString(), new PrsListMap(), map);

        } catch (Exception e) {
            e.printStackTrace();
            throw new PrsDaoException("Query failed", e);
        }
    }

    @Override
    public List<Prs> findWherePrsNumberCanvasserNotInPO() throws PrsDaoException {
        try {
            return jdbcTemplate.query("select * from prs where prsnumber not in (select prsnumber from po) and is_approved='Y' order by prsnumber desc", this);
        } catch (Exception e) {
            throw new PrsDaoException("Query failed", e);
        }
    }

    @Override
    public List<Prs> findWherePrsNumberNotInPO() throws PrsDaoException {
        try {

            Map map = new HashMap();

            StringBuffer sb = new StringBuffer();

            sb.append(" select distinct p.id, p.prsnumber, p.prsdate, p.requestdate, "
                    + " p.deliverydate, p.poreferensi, p.remarks, p.createdby, p.department_name, cd.supplier_code, cd.is_selected "
                    + " from prs p "
                    + " inner join canvassing_detail cd "
                    + " on p.prsnumber = cd.prsnumber "
                    + " and cd.supplier_code not in (select supplier_name from po) "
                    + //" and cd.productcode not in (select productcode from po_detail) " +
                    " and cd.is_selected='Y' ");

            return jdbcTemplate.query(sb.toString(), new PrsListMap(), map);
        } catch (Exception e) {
            throw new PrsDaoException("Query failed", e);
        }
    }

    public void update(Prs dto) throws PrsDaoException {
        SqlUpdate su = new SqlUpdate(dataSource, "update prs set is_approved = ?, approved_by = ?, approved_date = ? where prsnumber = ?");
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.compile();
        su.update(new Object[]{dto.getIsApproved(), dto.getApprovedBy(), dto.getApprovedDate(), dto.getPrsnumber()});
    }

    /* FYA : 07 January 2014 */
    public Prs findByPrsnumberEquals(String prsNumber) {
        List<Prs> ps = jdbcTemplate.query("SELECT id, prsnumber, prsdate, requestdate, deliverydate, poreferensi, remarks, createdby, department_name, is_approved FROM " + getTableName() + " WHERE prsnumber = ?", this, prsNumber);
        return ps.isEmpty() ? null : ps.get(0);
    }

    public List<Prs> findByDepartment(String deptId) {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, prsdate, requestdate, deliverydate, poreferensi, remarks, createdby, department_name, is_approved FROM " + getTableName() + " WHERE department_name = ? ORDER BY id desc", this, deptId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Prs> findAllNotInCanvas() {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, prsdate, requestdate, deliverydate, poreferensi, remarks, createdby, department_name, is_approved FROM " + getTableName()
                    + " WHERE prsnumber NOT IN(SELECT prsnumber FROM assign_canv) ORDER BY department_name, prsdate, id", this);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Prs> fyaFindByCanvaser(String userId) {
        try {
            return jdbcTemplate.query("select p.* from prs p "
                    + "left join assign_canv ca on ca.prsnumber = p.prsnumber where p.prsnumber not in (select prsnumber from po) "
                    + "and ca.canvassername = ? order by p.prsnumber desc", this, userId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Prs> fyaFindByCanvaser(String prsNo, String userId) {
        try {
            return jdbcTemplate.query("select p.* from prs p "
                    + "left join assign_canv ca on ca.prsnumber = p.prsnumber where p.prsnumber not in (select prsnumber from po) "
                    + "and ca.canvassername = ? and p.prsnumber like '%" + prsNo + "%' order by p.prsnumber desc", this, userId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int prsCount() {
        try {
            return jdbcTemplate.queryForInt("SELECT count(id) FROM prs");
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int ajaxMaxPage(String deptId, String where, BigDecimal show) {
        try {
            return jdbcTemplate.queryForInt("EXEC PRC_PURCHASE_REQUISITION_MAX_PAGE ?, ?, ?", show, where, deptId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public List<Map<String, Object>> ajaxSearch(String deptId, String where, String order, int page, int show) {
        try {
            return jdbcTemplate.queryForList("EXEC PRC_PURCHASE_REQUISITION_LIST ?, ?, ?, ?, ?", page, show, where, order, deptId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public List<Map<String, Object>> ajaxReadDetail(String prsNo) {
        try {
            return jdbcTemplate.queryForList("EXEC PRC_REQUISITION_GET_DETAIL ?", prsNo);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }

    public int prsLastNumber() {
        String lastPrs = jdbcTemplate.queryForObject("SELECT TOP(1) prsnumber FROM prs ORDER BY id DESC", String.class);
        return Integer.parseInt(lastPrs.substring(4, lastPrs.length()));
    }

}

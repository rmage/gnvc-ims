package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.PrsDetailDao;
import com.app.wms.engine.db.dto.PrsDetail;
import com.app.wms.engine.db.dto.PrsDetailPk;
import com.app.wms.engine.db.dto.map.DeliveryOrderListMap;
import com.app.wms.engine.db.dto.map.PRSCanvassingSelectedJsonMap;
import com.app.wms.engine.db.dto.map.PRSDetailListJsonMap;
import com.app.wms.engine.db.exceptions.PrsDetailDaoException;

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

public class PrsDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<PrsDetail>, PrsDetailDao {

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
     * @return PrsDetailPk
     */
    public PrsDetailPk insert(PrsDetail dto) {
        SqlUpdate su = new SqlUpdate(dataSource, "INSERT INTO " + getTableName() + " ( prsnumber, productcode, productname, qty, uom_name, prs_soh ) VALUES ( ?, ?, ?, ?, ?, ? )");
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.NUMERIC));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.NUMERIC));
        su.compile();
        su.update(new Object[]{dto.getPrsnumber(), dto.getProductcode(), dto.getProductname(), dto.getQty(), dto.getUomName(), dto.getPrsStockOnHand()});
        PrsDetailPk pk = new PrsDetailPk();
        pk.setId(jdbcTemplate.queryForInt("select @@IDENTITY"));
        return pk;
    }

    /**
     * Updates a single row in the prs_detail table.
     */
    public void update(PrsDetailPk pk, PrsDetail dto) throws PrsDetailDaoException {
        SqlUpdate su = new SqlUpdate(dataSource, "UPDATE " + getTableName() + " SET prsnumber = ?, productcode = ?, productname = ?, qty = ?, uom_name = ? WHERE id = ?");
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.DECIMAL));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.compile();
        su.update(new Object[]{dto.getPrsnumber(), dto.getProductcode(), dto.getProductname(), dto.getQty(), dto.getUomName(), pk.getId()});
    }

    /**
     * Deletes a single row in the prs_detail table.
     */
    @Transactional
    public void delete(PrsDetailPk pk) throws PrsDetailDaoException {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?", pk.getId());
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return PrsDetail
     */
    public PrsDetail mapRow(ResultSet rs, int row) throws SQLException {
        PrsDetail dto = new PrsDetail();
        dto.setId(rs.getInt(1));
        dto.setPrsnumber(rs.getString(2));
        dto.setProductcode(rs.getString(3));
        dto.setProductname(rs.getString(4));
        dto.setQty(rs.getBigDecimal(5));
        if (rs.wasNull()) {
            dto.setQtyNull(true);
        }

        dto.setUomName(rs.getString(6));
        return dto;
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "prs_detail";
    }

    /**
     * Returns all rows from the prs_detail table that match the criteria 'id =
     * :id'.
     */
    @Transactional
    public PrsDetail findByPrimaryKey(int id) throws PrsDetailDaoException {
        try {
            List<PrsDetail> list = jdbcTemplate.query("SELECT id, prsnumber, productcode, productname, qty, uom_name FROM " + getTableName() + " WHERE id = ?", this, id);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new PrsDetailDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the prs_detail table that match the criteria ''.
     */
    @Transactional
    public List<PrsDetail> findAll() throws PrsDetailDaoException {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, productcode, productname, qty, uom_name FROM " + getTableName() + " ORDER BY id", this);
        } catch (Exception e) {
            throw new PrsDetailDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the prs_detail table that match the criteria 'id =
     * :id'.
     */
    @Transactional
    public List<PrsDetail> findWhereIdEquals(int id) throws PrsDetailDaoException {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, productcode, productname, qty, uom_name FROM " + getTableName() + " WHERE id = ? ORDER BY id", this, id);
        } catch (Exception e) {
            throw new PrsDetailDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the prs_detail table that match the criteria
     * 'prsnumber = :prsnumber'.
     */
    @Transactional
    public List<PrsDetail> findWherePrsnumberEquals(String prsnumber) throws PrsDetailDaoException {
        try {
            return jdbcTemplate.query(" "
                    + " SELECT id, prsnumber, productcode, productname, "
                    + " qty, uom_name FROM " + getTableName()
                    + " WHERE prsnumber = ? ORDER BY prsnumber", this, prsnumber);
        } catch (Exception e) {
            throw new PrsDetailDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the prs_detail table that match the criteria
     * 'productcode = :productcode'.
     */
    @Transactional
    public List<PrsDetail> findWhereProductcodeEquals(String productcode) throws PrsDetailDaoException {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, productcode, productname, qty, uom_name FROM " + getTableName() + " WHERE productcode = ? ORDER BY productcode", this, productcode);
        } catch (Exception e) {
            throw new PrsDetailDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the prs_detail table that match the criteria
     * 'productname = :productname'.
     */
    @Transactional
    public List<PrsDetail> findWhereProductnameEquals(String productname) throws PrsDetailDaoException {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, productcode, productname, qty, uom_name FROM " + getTableName() + " WHERE productname = ? ORDER BY productname", this, productname);
        } catch (Exception e) {
            throw new PrsDetailDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the prs_detail table that match the criteria 'qty =
     * :qty'.
     */
    @Transactional
    public List<PrsDetail> findWhereQtyEquals(int qty) throws PrsDetailDaoException {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, productcode, productname, qty, uom_name FROM " + getTableName() + " WHERE qty = ? ORDER BY qty", this, qty);
        } catch (Exception e) {
            throw new PrsDetailDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the prs_detail table that match the criteria
     * 'uom_name = :uomName'.
     */
    @Transactional
    public List<PrsDetail> findWhereUomNameEquals(String uomName) throws PrsDetailDaoException {
        try {
            return jdbcTemplate.query("SELECT id, prsnumber, productcode, productname, qty, uom_name FROM " + getTableName() + " WHERE uom_name = ? ORDER BY uom_name", this, uomName);
        } catch (Exception e) {
            throw new PrsDetailDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the prs_detail table that matches the specified
     * primary-key value.
     */
    public PrsDetail findByPrimaryKey(PrsDetailPk pk) throws PrsDetailDaoException {
        return findByPrimaryKey(pk.getId());
    }

    /**
     * Returns all rows from the prs_detail table that match the criteria
     * 'prsnumber = :prsnumber'.
     */
    @Transactional
    public List<PrsDetail> findWherePrsnumberEqualsDetail(String prsnumber) throws PrsDetailDaoException {
        try {
            Map map = new HashMap();
            map.put("prsnumber", prsnumber);
            StringBuffer sb = new StringBuffer();

            sb.append("select p.prsnumber, "
                    + "p.prsdate, pd.productcode, pd.productname, pd.qty, "
                    + "pd.uom_name, inv.balance, p.department_name, p.requestdate, p.remarks "
                    + "from prs p inner join prs_detail pd on p.prsnumber = pd.prsnumber "
                    + "inner join stock_inventory inv on pd.productcode = inv.product_code "
                    + "where p.prsnumber= '" + prsnumber + "' ");

            return jdbcTemplate.query(sb.toString(), new PRSDetailListJsonMap(), map);

        } catch (Exception e) {
            throw new PrsDetailDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the prs_detail table that match the criteria
     * 'prsnumber = :prsnumber'.
     */
    @Transactional
    public List<PrsDetail> findWherePrsnumberCanvassingSelected(String prsnumber, String suppliercode) throws PrsDetailDaoException {
        try {
            Map map = new HashMap();
            map.put("prsnumber", prsnumber);
            StringBuffer sb = new StringBuffer();

            sb.append(" select p.id, p.prsnumber, pd.productcode, pd.productname, pd.qty, pd.uom_name , "
                    + " cd.priceunit, cd.warranty, cd.termpayment, cd.termdelivery, cd.discount, cd.pph, cd.ppn, cd.supplier_code, cd.is_selected, "
                    + " p.prsdate, p.requestdate, p.deliverydate, p.remarks, p.department_name, p.createdby, p.poreferensi "
                    + " from prs p inner join canvassing_detail cd on p.prsnumber = cd.prsnumber "
                    + " inner join prs_detail pd on p.prsnumber = pd.prsnumber where pd.productcode = cd.productcode "
                    + " and cd.priceunit > 0 and p.prsnumber= '" + prsnumber + "' and cd.supplier_code= '" + suppliercode + "' ");

            return jdbcTemplate.query(sb.toString(), new PRSCanvassingSelectedJsonMap(), map);

        } catch (Exception e) {
            throw new PrsDetailDaoException("Query failed", e);
        }

    }

    /* FYA : 07 January 2014 */
    public PrsDetail findByPrsProduct(String prsNumber, String productCode) {
        List<PrsDetail> pds = jdbcTemplate.query("SELECT id, prsnumber, productcode, productname, qty, uom_name FROM " + getTableName() + " WHERE prsnumber = ? AND productcode = ?", this, prsNumber, productCode);
        return pds.isEmpty() ? null : pds.get(0);
    }

}

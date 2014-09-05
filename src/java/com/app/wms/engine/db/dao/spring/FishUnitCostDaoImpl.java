package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.FishDao;
import com.app.wms.engine.db.dao.FishUnitCostDao;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishUnitCost;
import com.app.wms.engine.db.dto.map.FishUnitCostListMap;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class FishUnitCostDaoImpl extends AbstractDAO implements ParameterizedRowMapper<FishUnitCost>, FishUnitCostDao {

    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private SimpleJdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "fish_unit_cost";
    }

    public FishUnitCost mapRow(ResultSet rs, int i) throws SQLException {
        FishUnitCost fc = new FishUnitCost();
        fc.setId(rs.getInt("id"));
        fc.setContractNumber(rs.getString("contract_number"));
        fc.setContractBeginDate(rs.getDate("contract_begin_date"));
        fc.setContractEndDate(rs.getDate("contract_end_date"));
        fc.setSupplierCode(rs.getString("supplier_code"));
        fc.setFishId(rs.getInt("fish_id"));
        fc.setFishDescription(rs.getString("fish_description"));
        fc.setUnitCost(rs.getBigDecimal("unit_cost"));
        fc.setCreatedBy(rs.getString("created_by"));
        fc.setCreatedDate(rs.getDate("created_date"));
        fc.setUpdatedBy(rs.getString("updated_by"));
        fc.setUpdatedDate(rs.getDate("updated_date"));

        /*INSERT FISH*/
        FishDao fishDao = DaoFactory.createFishDao();
        Fish f = null;
        try {
            f = fishDao.findByPrimaryKey(fc.getFishId());
        } catch (DaoException ex) {
            Logger.getLogger(FishUnitCostDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        fc.setFish(f);

        return fc;
    }

    public int insert(FishUnitCost fc) {
        return jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?)",
                fc.getContractNumber(), fc.getContractBeginDate(), fc.getContractEndDate(), fc.getSupplierCode(), fc.getFishId(), fc.getFishDescription(), fc.getUnitCost(), fc.getCreatedBy(), fc.getCreatedDate(), fc.getCreatedBy(), fc.getCreatedDate(), "Y", "N");
    }

    public int update(FishUnitCost fc) {
        return jdbcTemplate.update("UPDATE " + getTableName() + " SET contract_number = ?,contract_begin_date = ?, contract_end_date = ?, supplier_code = ?, fish_id =  ?, unit_cost = ?, updated_by = ?, updated_date = ? where id = ? ",
                fc.getContractNumber(), fc.getContractBeginDate(), fc.getContractEndDate(), fc.getSupplierCode(), fc.getFishId(), fc.getUnitCost(), fc.getUpdatedBy(), fc.getUpdatedDate(), fc.getId());
    }

    public int delete(FishUnitCost fc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int ajaxMaxPage(String where, BigDecimal show) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<FishUnitCost> ajaxSearch(String where, String order, int page, int show) {
        String query = "declare @Page int, @PageSize int set @Page = ?; "
                + "set @PageSize = ?; "
                + "with PagedResult as (select ROW_NUMBER() over (ORDER BY supplier_code) as id, "
                + "supplier_code, contract_number , contract_begin_date, contract_end_date , "
                + " ROW_NUMBER() OVER (ORDER BY supplier_code) row from dbo.fish_unit_cost "
                + "where supplier_code like '%' group by supplier_code, contract_number , contract_begin_date, contract_end_date ) "
                + "select * from PagedResult where id between  "
                + " case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 else @Page end and @PageSize * @Page";
        return jdbcTemplate.query(query, new FishUnitCostListMap(), page, show);
    }

    public boolean isExist(FishUnitCost fc) {
        boolean result = false;
        String sqlQuery = "select count(*) from dbo.fish_unit_cost where contract_number like '" + fc.getContractNumber() + "' and supplier_code = "
                + "'" + fc.getSupplierCode() + "' and fish_id = " + fc.getFishId() + "";
        int temp = (int) jdbcTemplate.queryForInt(sqlQuery);
        if (temp > 0) {
            result = true;
        }
        return result;
    }

    public int findBySupplierCodeandFishCode(FishUnitCost fc) {
        Integer result = 0;
        String sqlQuery = "select id from dbo.fish_unit_cost where contract_number like '" + fc.getContractNumber() + "' and supplier_code = "
                + "'" + fc.getSupplierCode() + "' and fish_id = " + fc.getFishId() + "";
        result = (Integer) jdbcTemplate.queryForInt(sqlQuery);

        return result;
    }

    public boolean isContractNumberAlreadyUsed(String contractNumber) {
        boolean result = false;
        String sqlQuery = "select count(*) from dbo.fish_unit_cost where contract_number like  '" + contractNumber + "'";
        int temp = (int) jdbcTemplate.queryForInt(sqlQuery);
        if (temp > 0) {
            result = true;
        }
        return result;
    }

    public List<FishUnitCost> findByContractNumber(String contractNumber) {
        List<FishUnitCost> result = new ArrayList<FishUnitCost>();
        String sqlQuery = "select * from dbo.fish_unit_cost where contract_number like '" + contractNumber + "'";
        result = jdbcTemplate.query(sqlQuery, this);

        return result;
    }

    public int updateUnitCost(Integer fcId, BigDecimal unitCost, String updatedBy, Date updatedDate) {
        return jdbcTemplate.update("UPDATE " + getTableName() + " SET unit_cost = ?, updated_by = ?, updated_date = ? where id = ? ",
                unitCost, updatedBy, updatedDate, fcId);
    }

}

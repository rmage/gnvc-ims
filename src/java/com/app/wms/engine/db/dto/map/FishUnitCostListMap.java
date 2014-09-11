package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dao.FishSupplierDao;
import com.app.wms.engine.db.dao.spring.FishUnitCostDaoImpl;
import com.app.wms.engine.db.dto.FishSupplier;
import com.app.wms.engine.db.dto.FishUnitCost;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FishUnitCostListMap implements ParameterizedRowMapper<FishUnitCost> {

    @Override
    public FishUnitCost mapRow(ResultSet rs, int row) throws SQLException {
        // TODO Auto-generated method stub
        FishUnitCost fc = new FishUnitCost();
        fc.setId(rs.getInt("id"));
        fc.setSupplierId(rs.getInt("supplier_id"));
        fc.setContractNumber(rs.getString("contract_number"));
        fc.setContractBeginDate(rs.getDate("contract_begin_date"));
        fc.setContractEndDate(rs.getDate("contract_end_date"));

        /*INSERT FISH SUPPLIER*/
        FishSupplierDao fishSupplierDao = DaoFactory.createFishSupplierDao();
        FishSupplier fishSupplier = new FishSupplier();

        try {

            fishSupplier = fishSupplierDao.findByPrimaryKey(fc.getSupplierId());
            fc.setFishSupplier(fishSupplier);

        } catch (DaoException ex) {
            Logger.getLogger(FishUnitCostDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fc;
    }

}

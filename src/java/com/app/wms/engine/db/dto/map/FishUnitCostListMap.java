package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dto.FishUnitCost;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FishUnitCostListMap implements ParameterizedRowMapper<FishUnitCost> {

    @Override
    public FishUnitCost mapRow(ResultSet rs, int row) throws SQLException {
        // TODO Auto-generated method stub
        FishUnitCost fc = new FishUnitCost();
        fc.setId(rs.getInt("id"));
        fc.setSupplierCode(rs.getString("supplier_code"));
        fc.setContractNumber(rs.getString("contract_number"));
        fc.setContractBeginDate(rs.getDate("contract_begin_date"));
        fc.setContractEndDate(rs.getDate("contract_end_date"));

        return fc;
    }

}

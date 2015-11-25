package com.spfi.ims.dao.mapper;

import com.app.wms.engine.db.dto.ReceiveReport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class MapperNonFishReceiveReportAccounting implements ParameterizedRowMapper<ReceiveReport> {
    
    public ReceiveReport mapRow(ResultSet rs, int i) throws SQLException {
        ReceiveReport rr = new ReceiveReport();
        rr.setRrCode(rs.getString("rr_code"));
        rr.setRrDate(rs.getDate("rr_date"));
        rr.setPoCode(rs.getInt("po_code"));
        rr.setRrFrom(rs.getString("rr_from"));
        rr.setRrRemarks(rs.getString("rr_remarks"));
        rr.setApprovedBy(rs.getString("approved_by"));
        rr.setCreatedBy(rs.getString("created_by"));
        rr.setCreatedDate(rs.getDate("created_date"));
        
        return rr;
    }
    
    public static HashMap<String, Object> parseToHashMap(ReceiveReport rr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        HashMap<String, Object> hm = new HashMap<String, Object>();
        hm.put("rrCode", rr.getRrCode());
        hm.put("rrDate", sdf.format(rr.getRrDate()));
        hm.put("poCode", rr.getPoCode());
        hm.put("rrFrom", rr.getRrFrom());
        hm.put("rrRemarks", rr.getRrRemarks());
        hm.put("approvedBy", rr.getApprovedBy());
        hm.put("createdBy", rr.getCreatedBy());
        hm.put("createdDate", sdf.format(rr.getCreatedDate()));
        
        return hm;
    }
}

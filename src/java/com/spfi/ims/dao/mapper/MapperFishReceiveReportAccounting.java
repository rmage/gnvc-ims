package com.spfi.ims.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class MapperFishReceiveReportAccounting implements ParameterizedRowMapper<Map<String, Object>> {
    
    public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
        Map<String, Object> rr = new HashMap<String, Object>();
        rr.put("rr_code", rs.getString("rr_code"));
        rr.put("rr_date", rs.getDate("rr_date"));
        rr.put("rr_from", rs.getString("rr_from"));
        rr.put("ws_codes", rs.getString("ws_codes"));
        rr.put("batch_number", rs.getString("batch_number"));
        rr.put("approved_by", rs.getString("approved_by"));
        rr.put("created_by", rs.getString("created_by"));
        rr.put("created_date", rs.getDate("created_date"));
        
        return rr;
    }
    
    public static HashMap<String, Object> parseToHashMap(Map<String, Object> rr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        HashMap<String, Object> hm = new HashMap<String, Object>();
        hm.put("rrCode", rr.get("rr_code"));
        hm.put("rrDate", sdf.format(rr.get("rr_date")));
        hm.put("rrFrom", rr.get("rr_from"));
        hm.put("wsCodes", rr.get("ws_codes"));
        hm.put("batchNumber", rr.get("batch_number"));
        hm.put("approvedBy", rr.get("approved_by"));
        hm.put("createdBy", rr.get("created_by"));
        hm.put("createdDate", sdf.format(rr.get("created_date")));
        
        return hm;
    }
}

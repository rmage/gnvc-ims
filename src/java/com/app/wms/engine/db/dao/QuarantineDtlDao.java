package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.QuarantineDtl;
import java.util.List;
import java.util.Map;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 * 11 February 2013
 */

public interface QuarantineDtlDao {

    public void insert(QuarantineDtl dto);
    
    public List<QuarantineDtl> findByQCode(String qCode);
    
    public List<Map<String,Object>> findByPutawayCode(String putawayCode);
    
}

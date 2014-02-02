package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.AssignCanvasserDtl;
import java.util.List;

public interface AssignCanvasserDtlDao {
    
    public int insert(AssignCanvasserDtl cd);
    
    public List<AssignCanvasserDtl> findByPrsnumber(String prsNumber);
    
    public List<AssignCanvasserDtl> findByUserId(String userId);
    
}

package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.Putaway;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.dto.Quarantine;
import java.util.Date;
import java.util.List;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 * 11 February 2013
 */

public interface QuarantineDao {

    public void insert(Quarantine dto);
    
    public List<Quarantine> findAll(String whCode, int page);
    
    public Quarantine findByQCode(String qCode);
    
    public List<Quarantine> findByQDate(Date qDate);
    
    public Putaway findPutaway(String whCode);
    
    public Putaway findPutaway(int poCode);
    
    public List<PutawayDetail> findPutawayDtl(String putawayId);
    
}

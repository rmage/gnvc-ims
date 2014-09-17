/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.FishRRAccountingDetail;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Faridzi
 */
public interface FishRRAccountingDetailDao {

    public void copyFromRRDetail();

    public int ajaxMaxPage(BigDecimal show, String where);
    
    public int update(FishRRAccountingDetail fishRRAccountingDetail);
    
    public List<FishRRAccountingDetail> findByRRId(Integer rrId);

    public List<FishRRAccountingDetail> ajaxSearch(String where, String order, int page, int show);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spfi.ims.dao;

import com.app.wms.engine.db.dto.FGUnitCost;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Faridzi
 */
public interface FgUnitCostDao {

    public int ajaxMaxPage(BigDecimal show, String where);

    public List<FGUnitCost> ajaxSearch(String where, String order, int page, int show);
    
    public int save(FGUnitCost fgUc);

}

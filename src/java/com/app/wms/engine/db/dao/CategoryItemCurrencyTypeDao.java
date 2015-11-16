/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.CategoryItemCurrencyType;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Faridzi
 */
public interface CategoryItemCurrencyTypeDao {

    public int insert(CategoryItemCurrencyType ciCurrType);

    public int update(CategoryItemCurrencyType ciCurrType);
    
    public CategoryItemCurrencyType findCurrencyTypeByCategoryCode(String categoryCode);
    
    public CategoryItemCurrencyType getById(int id);

    public int ajaxMaxPage(String where, BigDecimal show);

    public List<CategoryItemCurrencyType> ajaxSearch(String condition, String where, String order, int page, int show);
    
    public void deleteByCategoryCode(String categoryCode);

}

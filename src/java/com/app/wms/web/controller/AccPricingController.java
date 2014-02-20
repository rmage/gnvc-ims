package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.ProductCategoryDao;
import com.app.wms.engine.db.dto.ProductCategory;
import com.app.wms.engine.db.exceptions.ProductCategoryDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AccPricingController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) 
        throws ProductCategoryDaoException {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        
        /* DAO | Define needed dao here */
        ProductCategoryDao productCategoryDao = DaoFactory.createProductCategoryDao();
        
        /* TRANSACTION | Something complex here */
        List<ProductCategory> pcs = productCategoryDao.findAll();
        m.put("pc", pcs);
        
        return new ModelAndView("accounting/ACPList", "model", m);
        
    }
    
}

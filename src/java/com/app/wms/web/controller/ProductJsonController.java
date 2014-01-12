/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.spring.ProductDao;
import com.app.wms.hbm.bean.Customer;
import com.app.wms.hbm.bean.Product;
import com.app.wms.hbm.bean.Vproductsku;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @genevacons
 */
@Controller
@RequestMapping( {"/productjson.htm"})
public class ProductJsonController extends BaseJsonController {

    @Autowired
    private ProductDao productDao;
    
     @RequestMapping(method = RequestMethod.GET, value = "/productjson.htm")
    public ModelAndView hello(HttpServletRequest request, HttpServletResponse response) {        

        // process httprequest
        process(request);
         
        List<Vproductsku> vproductskus = new ArrayList<Vproductsku>();
        
        com.app.wms.engine.db.dto.map.LoginUser lu = (com.app.wms.engine.db.dto.map.LoginUser) request.getSession().getAttribute("user");
        
        //vproductskus.addAll(productDao.getProductsWithSKU(lu.getCorpId(), lu.getWhCode()));
        vproductskus.addAll(productDao.getProductsWithSKU());
        System.out.println("@@@@ vproductskus ="+vproductskus);
                    
        if(sord.equalsIgnoreCase("asc"))
            Collections.reverse(vproductskus);
        
        Map model = new HashMap();
        model.put(PAGE, page);
        model.put(TOTAL, getTotalPages(vproductskus.size()));
        model.put(RECORDS, vproductskus.size());                       
        model.put(ROWS, pagination(vproductskus));

        return new ModelAndView("jsonView", model);
    }
    
}

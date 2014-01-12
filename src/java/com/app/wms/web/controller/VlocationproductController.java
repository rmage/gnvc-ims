/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.spring.VlocationproductDao;
import com.app.wms.hbm.bean.Vlocationproduct;
import java.util.ArrayList;
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
 * @gnv solution
 */
@Controller
@RequestMapping( {"/vlocationproduct.htm"})
public class VlocationproductController {
    
    @Autowired
    private VlocationproductDao vlocationproductDao;
    
    @RequestMapping(method = RequestMethod.GET, value =  "/vlocationproduct.htm")
    public ModelAndView jsonrequest(HttpServletRequest request, HttpServletResponse response) {

        String productId = request.getParameter("productId") + "";
        String karantina = request.getParameter("karantina") + "";
        String wh = request.getParameter("wh") + "";
        
        com.app.wms.engine.db.dto.map.LoginUser lu =
                    (com.app.wms.engine.db.dto.map.LoginUser) request.getSession().getAttribute("user"); 

        List<Vlocationproduct> vlocationproducts = new ArrayList<Vlocationproduct>();
        
        if(karantina == null) {
        	
            vlocationproducts.addAll(vlocationproductDao.getVlocationproducts(productId, null, wh, lu.getCorpId(), lu.getWhCode()));
        }        
        else if(karantina.equals("true")) {
            vlocationproducts.addAll(vlocationproductDao.getVlocationproducts(productId, true, wh, lu.getCorpId(), lu.getWhCode()));
        }
        else {
            vlocationproducts.addAll(vlocationproductDao.getVlocationproducts(productId, false, wh, lu.getCorpId(), lu.getWhCode()));
        }
        
        Map model = new HashMap();                            
        model.put("vlocationproducts", vlocationproducts);

        return new ModelAndView("jsonView", model);
    }
}

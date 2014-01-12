/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.spring.PODao;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @gnv solution
 */
@Controller
@RequestMapping( {"/PurchaseSPV.htm"})
public class PurchaseOrder2Controller {
    
    @Autowired
    private PODao poDao;
    
    private Logger logger = Logger.getLogger(PurchaseOrder2Controller.class);
    
     /**     
     * 
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(method = RequestMethod.GET, value="/PurchaseSPV.htm")
    public ModelAndView spvGet(HttpServletRequest request, HttpServletResponse response) {       
        
         com.app.wms.engine.db.dto.map.LoginUser lu =
                    (com.app.wms.engine.db.dto.map.LoginUser) request.getSession().getAttribute("user");
        
        HashMap m = new HashMap();
        //m.put("pos", poDao.getPos(lu.getCorpId(), lu.getWhCode()));
        m.put("pos", poDao.getPos());
        return new ModelAndView("2_receive/PurchaseOrderList", "model", m);
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.spring.StockTakeDao;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.hbm.bean.Stocktake;
import java.util.Date;
import java.util.HashMap;
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
@RequestMapping( {"/stocktake.htm","/StockTakeAdd.htm","/StockTakeSave.htm", "/StockTakeUpdate.htm"})
public class StockTakeController {
    
    @Autowired
    private StockTakeDao stockTakeDao;
    
    @RequestMapping(method = RequestMethod.GET, value="/stocktake.htm")
    public ModelAndView  index(HttpServletRequest request, HttpServletResponse response) {   
        HashMap maps = new HashMap();
        
        LoginUser loginUser = (LoginUser) request.getSession().getAttribute("user");
        
        maps.put("stockTakes", stockTakeDao.get(loginUser.getCorpId(), loginUser.getWhCode()));
        return new ModelAndView("9_stocktake/StockTakeList", "model", maps);
    }
    
    @RequestMapping(method = RequestMethod.GET, value="/StockTakeAdd.htm")
    public ModelAndView  add(HttpServletRequest request, HttpServletResponse response) {
        LoginUser loginUser = (LoginUser) request.getSession().getAttribute("user");
        
        HashMap maps = new HashMap();
        maps.put("stockTakes", stockTakeDao.getVO(loginUser.getCorpId(), loginUser.getWhCode()));
        
        return new ModelAndView("9_stocktake/StockTakeAdd", "model", maps);
    }
    
     @RequestMapping(method = RequestMethod.POST, value="/StockTakeSave.htm")
    public String  save(HttpServletRequest request, HttpServletResponse response) {  
         
         LoginUser loginUser = (LoginUser) request.getSession().getAttribute("user");
         
         String[] productCodes = request.getParameterValues("productCode");
         String[] qtys = request.getParameterValues("qty");
         String[] locations = request.getParameterValues("location");
         String stockname = request.getParameter("stockname")+"";
         
         for (int i = 0; i < productCodes.length; i++) {
             String productCode = productCodes[i];
             String qty = qtys[i];
             
             
              Stocktake stocktake = new Stocktake();
            stocktake.setActualqty(Integer.parseInt(qty));
            stocktake.setProductcode(productCode);
            stocktake.setCorpId(loginUser.getCorpId()); 
            stocktake.setWhCode(loginUser.getWhCode()); 
            stocktake.setStockname(stockname);
            stocktake.setLocationcode(locations[i]);
            stocktake.setStockdate(new Date());
            stocktake.setApproved(false);

            stockTakeDao.save(stocktake);
             
         }                
        return "redirect:/stocktake.htm";
    }
     
     @RequestMapping(method = RequestMethod.POST, value="/StockTakeUpdate.htm")
    public String  Update(HttpServletRequest request, HttpServletResponse response) {  
         
         LoginUser loginUser = (LoginUser) request.getSession().getAttribute("user");
         
        Stocktake stocktake = new Stocktake();
        stocktake.setActualqty(Integer.parseInt(request.getParameter("actualQty")+""));
        stocktake.setProductcode(request.getParameter("productCode"));
//        stocktake.setQty(Integer.parseInt(request.getParameter("qty")+""));
        stocktake.setCorpId(loginUser.getCorpId()); 
        stocktake.setWhCode(loginUser.getWhCode()); 
        stocktake.setStockname(request.getParameter("stockname")+"");
        stocktake.setStockdate(new Date());
        stocktake.setApproved(false);
        
        stockTakeDao.save(stocktake);
        return "redirect:/stocktake.htm";
    }
    
}

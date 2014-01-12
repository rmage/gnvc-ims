package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.CrossDockDao;
import com.app.wms.engine.db.dao.PACrossDockDao;
import com.app.wms.engine.db.dto.CrossDock;
import com.app.wms.engine.db.dto.PACrossDock;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.controller.common.BaseCrossDockController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

@Controller
@RequestMapping(value = "/CrossDock.htm")
public class CrossDockController extends BaseCrossDockController { 
    
    @RequestMapping(params = "action=list")
    public ModelAndView getIndex(HttpServletRequest request, HttpServletResponse response) {
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        
        CrossDockDao crossDockDao = DaoFactory.createCrossDockDao();
        List<CrossDock> list = crossDockDao.findAll(getUser(request, "wh"), page);
        
        HashMap model = new HashMap();
        model.put("list", list);
        model.put("page", page);
        
        return new ModelAndView("cross_dock/CrossDock", "model", model);
    }
    
    @RequestMapping(params = "action=view")
    public ModelAndView getDetail(@RequestParam("cdCode") String cdCode, HttpServletRequest request) {
        try {
            PACrossDockDao pACrossDockDao = DaoFactory.createPACrossDockDao();
            PACrossDock pacd = pACrossDockDao.findByPACode(cdCode);
            
            HashMap model = new HashMap();
            model.put("list", pacd);
            
            return new ModelAndView("cross_dock/CrossDockView", "model", model);
        } catch(Exception ex) {
            System.out.println(ex);
            return getIndex(request, null);
        }
    }
    
    @RequestMapping(params = "action=search")
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
        List<CrossDock> list = new ArrayList<CrossDock>();
        
        try {
            String criteria = request.getParameter("cdCode");
            CrossDockDao crossDockDao = DaoFactory.createCrossDockDao();
            
            if(criteria != null) {
                CrossDock dto = crossDockDao.findByCDCode(criteria);
                
                if(dto != null) {
                    list.add(dto);
                }
            } else {
                criteria = request.getParameter("cdDate");
                list = crossDockDao.findByCDDate(new SimpleDateFormat("dd/MM/yyyy").parse(criteria));
            }
            
        } catch(Exception ex) {
            System.out.println(ex);
        } finally {
            HashMap model = new HashMap();
            model.put("list", list);
            model.put("page", 1);
            
            return new ModelAndView("cross_dock/CrossDock", "model", model);
        }
    }
    
}
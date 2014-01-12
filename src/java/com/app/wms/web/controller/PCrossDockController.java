package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.dto.*;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.engine.util.ctrlIDGenerator;
import com.app.wms.web.controller.common.BaseCrossDockController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

@Controller
@RequestMapping(value = "/PCrossDock.htm")
public class PCrossDockController extends BaseCrossDockController {

    @RequestMapping(params = "action=list")
    public ModelAndView getIndex(HttpServletRequest request, HttpServletResponse response) {
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        
        PCrossDockDao pCrossDockDao = DaoFactory.createPCrossDockDao();
        List<PCrossDock> list = pCrossDockDao.findAll(getUser(request, "wh"), page);
        
        HashMap model = new HashMap();
        model.put("list", list);
        model.put("page", page);
        
        return new ModelAndView("cross_dock/PCrossDock", "model", model);
    }
    
    @RequestMapping(params = "action=create")
    public ModelAndView create(HttpServletRequest request) {
        return new ModelAndView("cross_dock/PCrossDockAdd");
    }
    
    @RequestMapping(params = "action=save", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            PCrossDockDao pCrossDockDao = DaoFactory.createPCrossDockDao();
            
            /* P Cross Dock */
            PCrossDock pcd = new PCrossDock();
            pcd.setPCode(new ctrlIDGenerator().getCDCode("PICR-", getUser(request, "wh")));
            pcd.setSOCode(request.getParameter("soCode"));
            pcd.setTallymanCode(request.getParameter("tallymanCode"));
            pcd.setWHCode(getUser(request, "wh"));
            pcd.setPDate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("pDate")));
            pcd.setCreatedBy(getUser(request, "code"));
            
            pCrossDockDao.insert(pcd);
            return getView(pcd.getPCode(), request);
        } catch(Exception ex) {
            System.out.println(ex);
            return getIndex(request, response);
        }
    }
    
    @RequestMapping(params = "action=update")
    public ModelAndView getDetail(@RequestParam("pCode") String pCode, HttpServletRequest request) {
        try {
            PCrossDockDao pCrossDockDao = DaoFactory.createPCrossDockDao();
            PCrossDock pcd = pCrossDockDao.findByPCode(pCode);
            
            PACrossDockDao pACrossDockDao = DaoFactory.createPACrossDockDao();
            PACrossDock listPA = pACrossDockDao.findByPCode(pCode);
            
            HashMap model = new HashMap();
            model.put("list", pcd);
            model.put("listPA", listPA);
            
            if(request.getAttribute("isView") == null) {
                return new ModelAndView("cross_dock/PCrossDockEdit", "model", model);
            } else {
                return new ModelAndView("cross_dock/PCrossDockView", "model", model);
            }
        } catch(Exception ex) {
            System.out.println(ex);
            return getIndex(request, null);
        }
    }
    
    @RequestMapping(params = "action=edit", method = RequestMethod.POST)
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
        try {
            PCrossDockDao pCrossDockDao = DaoFactory.createPCrossDockDao();
            
            /* PA Cross Dock */
            PCrossDock pcd = new PCrossDock();
            pcd.setPCode(request.getParameter("pCode"));
            pcd.setTallymanCode(request.getParameter("tallymanCode"));
            pcd.setPDate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("pDate")));
            pcd.setUpdatedBy(getUser(request, "code"));
            
            pCrossDockDao.update(pcd);
        } catch(Exception ex) {
            System.out.println(ex);
        }
        
        return getIndex(request, response);
    }
    
    @RequestMapping(params = "action=view")
    public ModelAndView getView(@RequestParam("pCode") String pCode, HttpServletRequest request) {
        request.setAttribute("isView", true);
        
        return getDetail(pCode, request);
    }
    
    @RequestMapping(params = "action=approved")
    public ModelAndView setApprove(@RequestParam("pCode") String pCode, HttpServletRequest request) {
        try {
            /* PA Cross Dock Approved */
            PCrossDockDao pCrossDockDao = DaoFactory.createPCrossDockDao();
            pCrossDockDao.approved(pCode, getUser(request, "code"));
            
            /* SO Cross Dock Approved */
            SOCrossDockDao sOCrossDockDao = DaoFactory.createSOCrossDockDao();
            sOCrossDockDao.approved(pCode, getUser(request, "code"));
            
            /* Create Cross Dock */
            CrossDockDao crossDockDao = DaoFactory.createCrossDockDao();
            CrossDock cd = new CrossDock();
            cd.setPCode(pCode);
            cd.setUpdatedBy(getUser(request, "code"));
            crossDockDao.update(cd);
        } catch(Exception ex){
            System.out.println(ex);
        }
        
        return getIndex(request, null);
    }
    
    /*
     *  Utilities Function
     */
    
    @RequestMapping(params = "action=search")
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
        List<PCrossDock> list = new ArrayList<PCrossDock>();
        
        try {
            String criteria = request.getParameter("pCode");
            PCrossDockDao pCrossDockDao = DaoFactory.createPCrossDockDao();
            
            if(criteria != null) {
                PCrossDock dto = pCrossDockDao.findByPCode(criteria);
                
                if(dto != null) {
                    list.add(dto);
                }
            } else {
                criteria = request.getParameter("pDate");
                list = pCrossDockDao.findByPDate(new SimpleDateFormat("dd/MM/yyyy").parse(criteria));
            }
            
        } catch(Exception ex) {
            System.out.println(ex);
        } finally {
            HashMap model = new HashMap();
            model.put("list", list);
            model.put("page", 1);
            
            return new ModelAndView("cross_dock/PCrossDock", "model", model);
        }
    }
    
}

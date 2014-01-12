package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.DOCrossDockDao;
import com.app.wms.engine.db.dao.SOCrossDockDao;
import com.app.wms.engine.db.dto.DOCrossDock;
import com.app.wms.engine.db.dto.SOCrossDock;
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
@RequestMapping(value = "/DOCrossDock.htm")
public class DOCrossDockController extends BaseCrossDockController {

    @RequestMapping(params = "action=list")
    public ModelAndView getIndex(HttpServletRequest request, HttpServletResponse response) {
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        
        DOCrossDockDao dOCrossDockDao = DaoFactory.createDOCrossDockDao();
        List<DOCrossDock> list = dOCrossDockDao.findAll(getUser(request, "wh"), page);
        
        HashMap model = new HashMap();
        model.put("list", list);
        model.put("page", page);
        
        return new ModelAndView("cross_dock/DOCrossDock", "model", model);
    }
    
    @RequestMapping(params = "action=create")
    public ModelAndView create() {
        return new ModelAndView("cross_dock/DOCrossDockAdd");
    }
    
    @RequestMapping(params = "action=save", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            DOCrossDockDao dOCrossDockDao = DaoFactory.createDOCrossDockDao();
            
            /* SO Cross Dock Master */
            DOCrossDock dto = new DOCrossDock();
            dto.setDOCode(new ctrlIDGenerator().getCDCode("DOCR-", getUser(request, "wh")));
            dto.setSOCode(request.getParameter("soCode"));
            dto.setWHCode(getUser(request, "wh"));
            dto.setDOName(request.getParameter("doName"));
            dto.setDODate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("doDate")));
            dto.setCreatedBy(getUser(request, "code"));
            dOCrossDockDao.insert(dto);
            return getView(dto.getDOCode(), request);
        } catch(Exception ex) {
        	ex.printStackTrace();
            System.out.println(ex);
            return getIndex(request, response);
        }
    }
    
    @RequestMapping(params = "action=update")
    public ModelAndView getDetail(@RequestParam("doCode") String doCode, HttpServletRequest request) {
        try {
            DOCrossDockDao dOCrossDockDao = DaoFactory.createDOCrossDockDao();
            DOCrossDock dto = dOCrossDockDao.findByDOCode(doCode);

            HashMap model = new HashMap();
            model.put("list", dto);
            
            if(request.getAttribute("isView") == null) {
                return new ModelAndView("cross_dock/DOCrossDockEdit", "model", model);
            } else {
                return new ModelAndView("cross_dock/DOCrossDockView", "model", model);
            }
        } catch(Exception ex) {
            System.out.println(ex);
            return getIndex(request, null);
        }
    }
    
    @RequestMapping(params = "action=edit", method = RequestMethod.POST)
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
        try {
            DOCrossDockDao dOCrossDockDao = DaoFactory.createDOCrossDockDao();
            DOCrossDock dto = new DOCrossDock();
            dto.setDOCode(request.getParameter("doCode"));
            dto.setDODate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("doDate")));
            dto.setDOName(request.getParameter("doName"));
            dto.setUpdatedBy(getUser(request, "code"));
            
            dOCrossDockDao.update(dto);
            return getView(dto.getDOCode(), request);
        } catch(Exception ex) {
            System.out.println(ex);
            return getIndex(request, null);
        }
    }
    
    @RequestMapping(params = "action=view")
    public ModelAndView getView(@RequestParam("doCode") String doCode, HttpServletRequest request) {
        request.setAttribute("isView", true);
        
        return getDetail(doCode, request);
    }
    
    /*
     *  Utilities Function
     */
    
    @RequestMapping(params = "action=getCC")
    public void getCCFromSO(@RequestParam("soCode") String soCode, HttpServletResponse response) {
        SOCrossDockDao sOCrossDockDao = DaoFactory.createSOCrossDockDao();
        SOCrossDock dto = sOCrossDockDao.findBySOCode(soCode);
        
        getConsignee(dto.getConsigneeCode(), response);
    }
    
    @RequestMapping(params = "action=search")
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
        List<DOCrossDock> list = new ArrayList<DOCrossDock>();
        
        try {
            String criteria = request.getParameter("doCode");
            DOCrossDockDao dOCrossDockDao = DaoFactory.createDOCrossDockDao();
            
            if(criteria != null) {
                DOCrossDock dto = dOCrossDockDao.findByDOCode(criteria);
                
                if(dto != null) {
                    list.add(dto);
                }
            } else {
                criteria = request.getParameter("doDate");
                list = dOCrossDockDao.findByDODate(new SimpleDateFormat("dd/MM/yyyy").parse(criteria));
            }
            
        } catch(Exception ex) {
            System.out.println(ex);
        } finally {
            HashMap model = new HashMap();
            model.put("list", list);
            model.put("page", 1);
            
            return new ModelAndView("cross_dock/DOCrossDock", "model", model);
        }
    }
    
}

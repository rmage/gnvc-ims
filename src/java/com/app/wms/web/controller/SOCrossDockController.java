package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.ConsigneeDao;
import com.app.wms.engine.db.dao.SOCrossDockDao;
import com.app.wms.engine.db.dao.POCrossDockDtlDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dto.Consignee;
import com.app.wms.engine.db.dto.SOCrossDock;
import com.app.wms.engine.db.dto.POCrossDockDtl;
import com.app.wms.engine.db.dto.Product;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

@Controller
@RequestMapping(value = "/SOCrossDock.htm")
public class SOCrossDockController extends BaseCrossDockController {
    
    @RequestMapping(params = "action=list")
    public ModelAndView getIndex(HttpServletRequest request, HttpServletResponse response) {
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        
        SOCrossDockDao sOCrossDockDao = DaoFactory.createSOCrossDockDao();
        List<SOCrossDock> list = sOCrossDockDao.findAll(getUser(request, "wh"), page);
        
        HashMap model = new HashMap();
        model.put("list", list);
        model.put("page", page);
        
        return new ModelAndView("cross_dock/SOCrossDock", "model", model);
    }
    
    @RequestMapping(params = "action=create")
    public ModelAndView create() {
        return new ModelAndView("cross_dock/SOCrossDockAdd");
    }
    
    @RequestMapping(params = "action=po")
    public void getPOCrossDock(@RequestParam("poCode") String poCode, HttpServletResponse response) {
        try {
            POCrossDockDtlDao pOCrossDockDtlDao = DaoFactory.createPOCrossDockDtlDao();
            List<POCrossDockDtl> list = pOCrossDockDtlDao.findbyPOCodeCrossDock(poCode);

            if(!list.isEmpty()) {
                ProductDao productDao = DaoFactory.createProductDao();
                for(int i = 0; i < list.size(); i++) {
                    POCrossDockDtl pocdd = list.get(i);
                    Product p = productDao.findWhereProductCodeEquals(pocdd.getProductCode()).get(0);
                    
                    String out = "<tr>";
                    out = out.concat("<td>" + pocdd.getProductCode() + "</td>");
                    out = out.concat("<td>" + p.getProductName() + "</td>");
                    out = out.concat("<td>" + pocdd.getPOQty() + "</td>");
                    out = out.concat("<td>" + pocdd.getPOType() + "</td>");
                    out = out.concat("</tr>");
                    
                    response.getWriter().println(out);
                }
            } else {
                response.getWriter().print("N");
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    @RequestMapping(params = "action=save", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            SOCrossDockDao sOCrossDockDao = DaoFactory.createSOCrossDockDao();
            
            /* SO Cross Dock Master */
            SOCrossDock socd = new SOCrossDock();
            socd.setSOCode(request.getParameter("soCode"));
            socd.setPOCode(request.getParameter("poCode"));
            socd.setConsigneeCode(request.getParameter("consigneeCode"));
            socd.setWHCode(getUser(request, "wh"));
            socd.setSODate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("soDate")));
            socd.setCreatedBy(getUser(request, "code"));
            sOCrossDockDao.insert(socd);
        } catch(Exception ex) {
            System.out.println(ex);
        }
        
        return getIndex(request, response);
    }
    
    @RequestMapping(params = "action=update")
    public ModelAndView getDetail(@RequestParam("soCode") String soCode, HttpServletRequest request) {
        try {
            SOCrossDockDao sOCrossDockDao = DaoFactory.createSOCrossDockDao();
            SOCrossDock socd = sOCrossDockDao.findBySOCode(soCode);

            HashMap model = new HashMap();
            model.put("list", socd);
            
            if(request.getAttribute("isView") == null) {
                return new ModelAndView("cross_dock/SOCrossDockEdit", "model", model);
            } else {
                return new ModelAndView("cross_dock/SOCrossDockView", "model", model);
            }
        } catch(Exception ex) {
            System.out.println(ex);
            return getIndex(request, null);
        }
    }
    
    @RequestMapping(params = "action=edit", method = RequestMethod.POST)
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
        try {
            SOCrossDockDao sOCrossDockDao = DaoFactory.createSOCrossDockDao();
            
            /* SO Cross Dock Master */
            SOCrossDock socd = new SOCrossDock();
            socd.setSOCode(request.getParameter("soCode"));
            socd.setPOCode(request.getParameter("poCode"));
            socd.setConsigneeCode(request.getParameter("consigneeCode"));
            socd.setSODate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("soDate")));
            socd.setUpdatedBy(getUser(request, "code"));
            sOCrossDockDao.update(socd);
        } catch(Exception ex) {
            System.out.println(ex);
        }
        
        return getIndex(request, response);
    }
    
    @RequestMapping(params = "action=approved")
    public ModelAndView setApprove(@RequestParam("soCode") String soCode, HttpServletRequest request) {
        try {
            SOCrossDockDao sOCrossDockDao = DaoFactory.createSOCrossDockDao();
            
            sOCrossDockDao.approved(soCode, getUser(request, "code"));
        } catch(Exception ex){
            System.out.println(ex);
        }
        
        return getIndex(request, null);
    }
    
    @RequestMapping(params = "action=view")
    public ModelAndView getView(@RequestParam("soCode") String soCode, HttpServletRequest request) {
        request.setAttribute("isView", true);
        
        return getDetail(soCode, request);
    }
    
    @RequestMapping(params = "action=search")
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
        List<SOCrossDock> list = new ArrayList<SOCrossDock>();
        
        try {
            String criteria = request.getParameter("soCode");
            SOCrossDockDao sOCrossDockDao = DaoFactory.createSOCrossDockDao();
            
            if(criteria != null) {
                SOCrossDock dto = sOCrossDockDao.findBySOCode(criteria);
                
                if(dto != null) {
                    list.add(dto);
                }
            } else {
                criteria = request.getParameter("soDate");
                list = sOCrossDockDao.findBySODate(new SimpleDateFormat("dd/MM/yyyy").parse(criteria));
            }
            
        } catch(Exception ex) {
            System.out.println(ex);
        } finally {
            HashMap model = new HashMap();
            model.put("list", list);
            model.put("page", 1);
            
            return new ModelAndView("cross_dock/SOCrossDock", "model", model);
        }
    }

}

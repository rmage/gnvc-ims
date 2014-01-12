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
@RequestMapping(value = "/PACrossDock.htm")
public class PACrossDockController extends BaseCrossDockController {
    
    @RequestMapping(params = "action=list")
    public ModelAndView getIndex(HttpServletRequest request, HttpServletResponse response) {
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        
        PACrossDockDao pACrossDockDao = DaoFactory.createPACrossDockDao();
        List<PACrossDock> list = pACrossDockDao.findAll(getUser(request, "wh"), page);
        
        HashMap model = new HashMap();
        model.put("list", list);
        model.put("page", page);
        
        return new ModelAndView("cross_dock/PACrossDock", "model", model);
    }
    
    @RequestMapping(params = "action=create")
    public ModelAndView create(HttpServletRequest request) {
        return new ModelAndView("cross_dock/PACrossDockAdd");
    }
    
    @RequestMapping(params = "action=save", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            PACrossDockDao pACrossDockDao = DaoFactory.createPACrossDockDao();
            
            /* PA Cross Dock */
            PACrossDock pacd = new PACrossDock();
            pacd.setPACode(new ctrlIDGenerator().getCDCode("PUCR-", getUser(request, "wh")));
            pacd.setGRCode(request.getParameter("grCode"));
            pacd.setLocationCode(request.getParameter("locationCode"));
            pacd.setTallymanCode(request.getParameter("tallymanCode"));
            pacd.setWHCode(getUser(request, "wh"));
            pacd.setPAInfo(request.getParameter("paInfo"));
            pacd.setCreatedBy(getUser(request, "code"));
            
            pACrossDockDao.insert(pacd);
            return getView(pacd.getPACode(), request);
        } catch(Exception ex) {
            System.out.println(ex);
            return getIndex(request, response);
        }
    }
    
    @RequestMapping(params = "action=update")
    public ModelAndView getDetail(@RequestParam("paCode") String paCode, HttpServletRequest request) {
        try {
            PACrossDockDao pACrossDockDao = DaoFactory.createPACrossDockDao();
            PACrossDock pacd = pACrossDockDao.findByPACode(paCode);

            /*WarehouseLocationDao warehouseLocationDao = DaoFactory.createWarehouseLocationDao();
            List<WarehouseLocation> listLocation = warehouseLocationDao.findWarehouseLocationWhereLocationAreaEquals("CrossDock");

            TallymanDao tallymanDao = DaoFactory.createTallymanDao();
            List<Tallyman> listTallyman = tallymanDao.findWhereWhCodeEquals(getUser(request, "wh"));*/
            
            HashMap model = new HashMap();
            model.put("list", pacd);
            //model.put("listLocation", listLocation);
            //model.put("listTallyman", listTallyman);
            
            if(request.getAttribute("isView") == null) {
                return new ModelAndView("cross_dock/PACrossDockEdit", "model", model);
            } else {
                return new ModelAndView("cross_dock/PACrossDockView", "model", model);
            }
        } catch(Exception ex) {
            System.out.println(ex);
            return getIndex(request, null);
        }
    }
    
    @RequestMapping(params = "action=edit", method = RequestMethod.POST)
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
        try {
            PACrossDockDao pACrossDockDao = DaoFactory.createPACrossDockDao();
            
            /* PA Cross Dock */
            PACrossDock pacd = new PACrossDock();
            pacd.setPACode(request.getParameter("paCode"));
            pacd.setLocationCode(request.getParameter("locationCode"));
            pacd.setTallymanCode(request.getParameter("tallymanCode"));
            pacd.setPAInfo(request.getParameter("paInfo"));
            pacd.setUpdatedBy(getUser(request, "code"));
            
            pACrossDockDao.update(pacd);
        } catch(Exception ex) {
            System.out.println(ex);
        }
        
        return getIndex(request, response);
    }
    
    @RequestMapping(params = "action=view")
    public ModelAndView getView(@RequestParam("paCode") String paCode, HttpServletRequest request) {
        request.setAttribute("isView", true);
        
        return getDetail(paCode, request);
    }
    
    @RequestMapping(params = "action=approved")
    public ModelAndView setApprove(@RequestParam("paCode") String paCode, HttpServletRequest request) {
        try {
            /* PA Cross Dock Approved */
            PACrossDockDao pACrossDockDao = DaoFactory.createPACrossDockDao();
            pACrossDockDao.approved(paCode, getUser(request, "code"));
            
            /* GR Cross Dock Approved */
            GRCrossDockDao gRCrossDockDao = DaoFactory.createGRCrossDockDao();
            gRCrossDockDao.approved(paCode, getUser(request, "code"));
            
            /* PO Cross Dock Approved */
            POCrossDockDao pOCrossDockDao = DaoFactory.createPOCrossDockDao();
            POCrossDockDtlDao pOCrossDockDtlDao = DaoFactory.createPOCrossDockDtlDao();
            
            POCrossDock pocd = pOCrossDockDao.findByPACode(paCode);
            if(pocd.getApprovedBy() == null) {
                pOCrossDockDao.approved(paCode, getUser(request, "code"), "normal");
            } else {
                List<POCrossDockDtl> list = pOCrossDockDtlDao.findbyPOCode(pocd.getPOCode());
                for(int i = 0; i < list.size(); i ++){
                    pACrossDockDao.updatePutaway(list.get(i), getUser(request, "wh"));
                }
                
                pOCrossDockDao.approved(paCode, getUser(request, "code"), "quarantine");
            }
            
            /* Create Cross Dock */
            CrossDockDao crossDockDao = DaoFactory.createCrossDockDao();
            CrossDock cd = new CrossDock();
            cd.setCDCode(new ctrlIDGenerator().getCDCode("CDCR-", getUser(request, "wh")));
            cd.setPACode(paCode);
            cd.setWHCode(getUser(request, "wh"));
            cd.setCreatedBy(getUser(request, "code"));
            crossDockDao.insert(cd);
        } catch(Exception ex){
            System.out.println(ex);
        }
        
        return getIndex(request, null);
    }

    /*
     *  Utilities Function
     */
    
    @RequestMapping(params = "action=gr")
    public void getPOCrossDock(@RequestParam("grCode") String grCode, HttpServletResponse response) {
        try {
            POCrossDockDtlDao pOCrossDockDtlDao = DaoFactory.createPOCrossDockDtlDao();
            List<POCrossDockDtl> list = pOCrossDockDtlDao.findbyGRCode(grCode);

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
    
    @RequestMapping(params = "action=search")
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
        List<PACrossDock> list = new ArrayList<PACrossDock>();
        
        try {
            String criteria = request.getParameter("paCode");
            PACrossDockDao pACrossDockDao = DaoFactory.createPACrossDockDao();
            
            if(criteria != null) {
                PACrossDock dto = pACrossDockDao.findByPACode(criteria);
                
                if(dto != null) {
                    list.add(dto);
                }
            } else {
                criteria = request.getParameter("paDate");
                list = pACrossDockDao.findByPADate(new SimpleDateFormat("dd/MM/yyyy").parse(criteria));
            }
            
        } catch(Exception ex) {
            System.out.println(ex);
        } finally {
            HashMap model = new HashMap();
            model.put("list", list);
            model.put("page", 1);
            
            return new ModelAndView("cross_dock/PACrossDock", "model", model);
        }
    }
    
}

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
@RequestMapping(value = "/GRCrossDock.htm")
public class GRCrossDockController extends BaseCrossDockController {

    @RequestMapping(params = "action=list")
    public ModelAndView getIndex(HttpServletRequest request, HttpServletResponse response) {
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        
        GRCrossDockDao pOCrossDockDao = DaoFactory.createGRCrossDockDao();
        List<GRCrossDock> list = pOCrossDockDao.findAll(getUser(request, "wh"), page);
        
        HashMap model = new HashMap();
        model.put("list", list);
        model.put("page", page);
        
        return new ModelAndView("cross_dock/GRCrossDock", "model", model);
    }
    
    @RequestMapping(params = "action=create")
    public ModelAndView create(HttpServletRequest request) {
        HashMap model = new HashMap();
        model.put("code", new ctrlIDGenerator().getCDCode("GRCR-", getUser(request, "wh")));
        
        return new ModelAndView("cross_dock/GRCrossDockAdd", "model", model);
    }
    
    @RequestMapping(params = "action=po")
    public void getPOCrossDockDtl(@RequestParam("poCode") String poCode, HttpServletResponse response) {
        try {
            POCrossDockDtlDao pOCrossDockDtlDao = DaoFactory.createPOCrossDockDtlDao();
            List<POCrossDockDtl> list = pOCrossDockDtlDao.findbyPOCode(Integer.parseInt(poCode));

            if(!list.isEmpty()) {
                ProductDao productDao = DaoFactory.createProductDao();
                for(int i = 0; i < list.size(); i++) {
                    POCrossDockDtl pocdd = list.get(i);
                    Product p = productDao.findWhereProductCodeEquals(pocdd.getProductCode()).get(0);
                    
                    String out = "<tr code=\"" + pocdd.getPOCodeDtl() + "\" status=\"N\">";
                    out = out.concat("<td>" + pocdd.getProductCode() + "</td>");
                    out = out.concat("<td>" + p.getProductName() + "</td>");
                    out = out.concat("<td>" + pocdd.getPOQty() + "</td>");
                    out = out.concat("<td>" + pocdd.getPOType() + "</td>");
                    out = out.concat("<td><input type=\"checkbox\" class=\"list-status\" /></td>");
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
    
    @RequestMapping(params = "action=view")
    public ModelAndView getView(@RequestParam("grCode") String grCode, HttpServletRequest request) {
        request.setAttribute("isView", true);
        
        return getDetail(grCode, request);
    }
    
    @RequestMapping(params = "action=save", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            GRCrossDockDao gRCrossDockDao = DaoFactory.createGRCrossDockDao();
            GRCrossDockDtlDao gRCrossDockDtlDao = DaoFactory.createGRCrossDockDtlDao();
            
            /* GR Cross Dock Master */
            GRCrossDock grcd = new GRCrossDock();
            grcd.setGRCode(new ctrlIDGenerator().getCDCode("GRCR-", getUser(request, "wh")));
            grcd.setPOCode(Integer.parseInt(request.getParameter("poCode")));
            grcd.setWHCode(getUser(request, "wh"));
            grcd.setGRDate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("grDate")));
            grcd.setCreatedBy(getUser(request, "code"));
            gRCrossDockDao.insert(grcd);
            
            /* GR Cross Dock Detail */
            String[] detail = request.getParameter("detail").split(":");
            for(int i = 0; i < detail.length; i++){
                String[] item = detail[i].split(",");
                
                GRCrossDockDtl grcdd = new GRCrossDockDtl();
                grcdd.setGRCodeDtl(grcd.getGRCode() + "." + String.valueOf(i + 1));
                grcdd.setGRCode(grcd.getGRCode());
                grcdd.setPOCodeDtl(item[0]);
                grcdd.setGRStatus(item[1]);
                grcdd.setCreatedBy(getUser(request, "code"));
                gRCrossDockDtlDao.insert(grcdd);
            }
            
            return getView(grcd.getGRCode(), request);
        } catch(Exception ex) {
            System.out.println(ex);
            return getIndex(request, response);
        }
    }
    
    @RequestMapping(params = "action=update")
    public ModelAndView getDetail(@RequestParam("grCode") String grCode, HttpServletRequest request) {
        try {
            GRCrossDockDao gRCrossDockDao = DaoFactory.createGRCrossDockDao();
            GRCrossDock grcd = gRCrossDockDao.findByGRCode(grCode);
            
            GRCrossDockDtlDao gRCrossDockDtlDao = DaoFactory.createGRCrossDockDtlDao();
            List<GRCrossDockDtl> listGR = gRCrossDockDtlDao.findbyGRCode(grCode);
            
            POCrossDockDtlDao pOCrossDockDtlDao = DaoFactory.createPOCrossDockDtlDao();
            List<POCrossDockDtl> listDetail = pOCrossDockDtlDao.findbyPOCode(grcd.getPOCode());
            
            ProductDao productDao = DaoFactory.createProductDao();
            List<Product> listProduct = new ArrayList<Product>();
            for(int i = 0; i < listDetail.size(); i++){
                Product dto = productDao.findWhereProductCodeEquals(listDetail.get(i).getProductCode()).get(0);
                listProduct.add(dto);
            }

            HashMap model = new HashMap();
            model.put("list", grcd);
            model.put("listGR", listGR);
            model.put("listDetail", listDetail);
            model.put("listProduct", listProduct);
            
            if(request.getAttribute("isView") == null) {
                return new ModelAndView("cross_dock/GRCrossDockEdit", "model", model);
            } else {
                return new ModelAndView("cross_dock/GRCrossDockView", "model", model);
            }
        } catch(Exception ex) {
            System.out.println(ex);
            return getIndex(request, null);
        }
    }
    
    @RequestMapping(params = "action=edit", method = RequestMethod.POST)
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
        try {
            GRCrossDockDao gRCrossDockDao = DaoFactory.createGRCrossDockDao();
            GRCrossDockDtlDao gRCrossDockDtlDao = DaoFactory.createGRCrossDockDtlDao();
            
            /* GR Cross Dock Master */
            GRCrossDock grcd = new GRCrossDock();
            grcd.setGRCode(request.getParameter("grCode"));
            grcd.setGRDate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("grDate")));
            grcd.setUpdatedBy(getUser(request, "code"));
            gRCrossDockDao.update(grcd);

            /* GR Cross Dock Detail */
            String[] detail = request.getParameter("detail").split(":");
            for(int i = 0; i < detail.length; i++){
                String[] item = detail[i].split(",");
                GRCrossDockDtl grcdd = new GRCrossDockDtl();
                grcdd.setGRCodeDtl(item[0]);
                grcdd.setGRStatus(item[1]);
                grcdd.setUpdatedBy(getUser(request, "code"));
                
                if(item[2].equals("U")){
                    gRCrossDockDtlDao.update(grcdd);
                } 
            }        
        } catch(Exception ex) {
            System.out.println(ex);
        }
        
        return getIndex(request, response);
    }
    
    @RequestMapping(value = "/GRCrossDock.htm", params = "action=search")
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
        List<GRCrossDock> list = new ArrayList<GRCrossDock>();
        
        try {
            String criteria = request.getParameter("grCode");
            GRCrossDockDao gRCrossDockDao = DaoFactory.createGRCrossDockDao();
            
            if(criteria != null) {
                GRCrossDock dto = gRCrossDockDao.findByGRCode(criteria);
                
                if(dto != null) {
                    list.add(dto);
                }
            } else {
                criteria = request.getParameter("grDate");
                list = gRCrossDockDao.findByGRDate(new SimpleDateFormat("dd/MM/yyyy").parse(criteria));
            }
            
        } catch(Exception ex) {
            System.out.println(ex);
        } finally {
            HashMap model = new HashMap();
            model.put("list", list);
            model.put("page", 1);
            
            return new ModelAndView("cross_dock/GRCrossDock", "model", model);
        }
    }
    
}

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
public class POCrossDockController extends BaseCrossDockController {
    
    @RequestMapping(value = "/POCrossDock.htm", params = "action=list")
    public ModelAndView getIndex(HttpServletRequest request, HttpServletResponse response) {
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        
        POCrossDockDao pOCrossDockDao = DaoFactory.createPOCrossDockDao();
        List<POCrossDock> list = pOCrossDockDao.findAll(getUser(request, "wh"), page);
        
        HashMap model = new HashMap();
        model.put("list", list);
        model.put("page", page);
        
        return new ModelAndView("cross_dock/POCrossDock", "model", model);
    }
    
    @RequestMapping(value = "/POCrossDock.htm", params = "action=detail")
    public ModelAndView getDetail(HttpServletRequest request, HttpServletResponse response) {
        String poCode = request.getParameter("poCode");
        
        POCrossDockDtlDao pOCrossDockDtlDao = DaoFactory.createPOCrossDockDtlDao();
        List<POCrossDockDtl> list = pOCrossDockDtlDao.findbyPOCode(Integer.parseInt(poCode));
        
        HashMap model = new HashMap();
        model.put("list", list);
        
        return new ModelAndView("cross_dock/util/POCrossDockDetail", "model", model);
    }
    
    @RequestMapping(value = "/POCrossDock.htm", params = "action=product")
    public void getProduct(@RequestParam("productCode") String productCode, HttpServletResponse response) {
        try {
            ProductDao productDao = DaoFactory.createProductDao();
            List<Product> list = productDao.findWhereProductCodeEquals(productCode);
            
            if(!list.isEmpty()) {
                Product dto = list.get(0);
                response.getWriter().println(dto.getProductCode() + "," + dto.getProductName());
            } else {
                response.getWriter().println("N");
            }
        } catch(Exception ex) {
            System.out.println(ex);
            try {
                response.getWriter().println("N");
            } catch (Exception ex2) {
                System.out.println(ex2);
            }
        }
    }
    
    @RequestMapping(value = "/POCrossDock.htm", params = "action=quarantine")
    public void getQuarantine(HttpServletRequest request, HttpServletResponse response) {
        try {
            QuarantineDao quarantineDao = DaoFactory.createQuarantineDao();
            Putaway p;
            if(request.getParameter("poCode") == null){
                p = quarantineDao.findPutaway(getUser(request, "wh"));
            } else {
                p = quarantineDao.findPutaway(Integer.parseInt(request.getParameter("poCode")));
            }
            
            List<PutawayDetail> list = quarantineDao.findPutawayDtl(p.getPutawayId());
            
            response.getWriter().println(p.getPutawayId() + "," + new SimpleDateFormat("dd/MM/yyyy").format(p.getPutawayDate()) + "," + p.getGrNumber());
            ProductDao productDao = DaoFactory.createProductDao();
            for(int i = 0; i < list.size(); i++) {
                Product dto = productDao.findByPrimaryKey(list.get(i).getProductId());
                
                String out = dto.getProductCode() + "," + dto.getProductName() + "," + list.get(i).getQtyPut();
                response.getWriter().println("|" + out);
            }
        } catch(Exception ex) {
            System.out.println(ex);
            try {
                response.getWriter().println("N");
            } catch (Exception ex2) {
                System.out.println(ex2);
            }
        }
    }
    
    @RequestMapping(value = "/POCrossDock.htm", params = "action=update")
    public ModelAndView getDetail(@RequestParam("poCode") String poCode, HttpServletRequest request){
        try {
            POCrossDockDao pOCrossDockDao = DaoFactory.createPOCrossDockDao();
            POCrossDock listMaster = pOCrossDockDao.findByPOCode(Integer.valueOf(poCode));
            
            if(listMaster != null) {
                POCrossDockDtlDao pOCrossDockDtlDao = DaoFactory.createPOCrossDockDtlDao();
                List<POCrossDockDtl> listDetail = pOCrossDockDtlDao.findbyPOCode(Integer.parseInt(poCode));
                
                ProductDao productDao = DaoFactory.createProductDao();
                List<Product> listProduct = new ArrayList<Product>();
                
                for(int i = 0; i < listDetail.size(); i++){
                    Product dto = productDao.findWhereProductCodeEquals(listDetail.get(i).getProductCode()).get(0);
                    listProduct.add(dto);
                }
            
                HashMap model = new HashMap();
                model.put("list", listMaster);
                model.put("listDetail", listDetail);
                model.put("listProduct", listProduct);

                if(request.getAttribute("isView") == null) {
                    return new ModelAndView("cross_dock/POCrossDockEdit", "model", model);
                } else {
                    return new ModelAndView("cross_dock/POCrossDockView", "model", model);
                }
            } else {
                return getIndex(request, null);
            }
        } catch(Exception ex) {
            System.out.println(ex);
            return getIndex(request, null);
        }
    }
    
    @RequestMapping(value = "/POCrossDock.htm", params = "action=view")
    public ModelAndView getView(@RequestParam("poCode") String poCode, HttpServletRequest request){
        request.setAttribute("isView", true);
        
        return getDetail(poCode, request);
    }
    
    @RequestMapping(value = "/POCrossDock.htm", params = "action=search")
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
        List<POCrossDock> list = new ArrayList<POCrossDock>();
        
        try {
            String criteria = request.getParameter("poNumber");
            POCrossDockDao pOCrossDockDao = DaoFactory.createPOCrossDockDao();
            
            if(criteria != null) {
                POCrossDock dto = pOCrossDockDao.findByPONumber(criteria);
                
                if(dto != null) {
                    list.add(dto);
                }
            } else {
                criteria = request.getParameter("poDate");
                list = pOCrossDockDao.findByPODate(new SimpleDateFormat("dd/MM/yyyy").parse(criteria));
            }
            
        } catch(Exception ex) {
            System.out.println(ex);
        } finally {
            HashMap model = new HashMap();
            model.put("list", list);
            model.put("page", 1);
            
            return new ModelAndView("cross_dock/POCrossDock", "model", model);
        }
    }
    
    @RequestMapping(value = "/POCrossDock.htm", params = "action=create")
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("cross_dock/POCrossDockAdd");
    }
    
    @RequestMapping(value = "/POCrossDock.htm", params = "action=save", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            POCrossDockDao pOCrossDockDao = DaoFactory.createPOCrossDockDao();
            POCrossDockDtlDao pOCrossDockDtlDao = DaoFactory.createPOCrossDockDtlDao();
            
            /* PO Cross Dock Master */
            POCrossDock pocd = new POCrossDock();
            pocd.setPONumber(request.getParameter("poNumber"));
            pocd.setPODate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("poDate")));
            pocd.setWHCode(getUser(request, "wh"));
            pocd.setCreatedBy(getUser(request, "code"));
            int poCode;
            
            /* Quarantine Master */
            Quarantine q = new Quarantine();
            if(request.getParameter("type").equals("Quarantine")){
                pocd.setApprovedBy("Quarantine");
                poCode = pOCrossDockDao.insert(pocd);
                
                QuarantineDao quarantineDao = DaoFactory.createQuarantineDao();
                
                q.setQCode(new ctrlIDGenerator().getCDCode("QRCR-", getUser(request, "wh")));
                q.setPOCode(poCode);
                q.setPutawayCode(request.getParameter("putawayCode"));
                q.setWhCode(getUser(request, "wh"));
                q.setCreatedBy(getUser(request, "code"));
                quarantineDao.insert(q);
            } else {
                poCode = pOCrossDockDao.insert(pocd);
            }

            /* PO Cross Dock Detail */
            String[] detail = request.getParameter("detail").split(":");
            for(int i = 0; i < detail.length; i++){
                String[] item = detail[i].split(",");
                
                POCrossDockDtl pocdd = new POCrossDockDtl();
                pocdd.setPOCodeDtl(pocd.getPONumber() + "." + String.valueOf(i + 1));
                pocdd.setPOCode(poCode);
                pocdd.setProductCode(item[0]);
                pocdd.setPOQty(Integer.valueOf(item[2]));
                pocdd.setPOType(item[3]);
                pocdd.setCreatedBy(getUser(request, "code"));
                pOCrossDockDtlDao.insert(pocdd);
                
                /* Quarantine Detail */
                if(request.getParameter("type").equals("Quarantine")){
                    QuarantineDtlDao quarantineDtlDao = DaoFactory.createQuarantineDtlDao();
                    
                    QuarantineDtl qd = new QuarantineDtl();
                    qd.setQCode(q.getQCode());
                    qd.setProductCode(item[0]);
                    qd.setCreatedBy(getUser(request, "code"));
                    quarantineDtlDao.insert(qd);
                }
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
        
        return getIndex(request, response);
    }
    
    @RequestMapping(value = "/POCrossDock.htm", params = "action=edit", method = RequestMethod.POST)
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
        try {
            POCrossDockDao pOCrossDockDao = DaoFactory.createPOCrossDockDao();
            POCrossDockDtlDao pOCrossDockDtlDao = DaoFactory.createPOCrossDockDtlDao();
            
            /* PO Cross Dock Master */
            POCrossDock pocd = new POCrossDock();
            pocd.setPOCode(Integer.parseInt(request.getParameter("poCode")));
            pocd.setPODate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("poDate")));
            pocd.setUpdatedBy(getUser(request, "code"));
            pOCrossDockDao.update(pocd);

            /* PO Cross Dock Detail */
            String[] detail = request.getParameter("detail").split(":");
            for(int i = 0; i < detail.length; i++){
                String[] item = detail[i].split(",");
                POCrossDockDtl pocdd = new POCrossDockDtl();
                pocdd.setPOCodeDtl(request.getParameter("poNumber") + "." + String.valueOf(i + 1));
                pocdd.setPOCode(pocd.getPOCode());
                pocdd.setProductCode(item[0]);
                pocdd.setPOQty(Integer.valueOf(item[2]));
                pocdd.setPOType(item[3]);
                pocdd.setCreatedBy(getUser(request, "code"));
                pocdd.setUpdatedBy(getUser(request, "code"));
                
                if(item[4].equals("N")){
                    pOCrossDockDtlDao.insert(pocdd);
                } else if(item[4].equals("U")){
                    pOCrossDockDtlDao.update(pocdd);
                } 
            }        
        } catch(Exception ex) {
            System.out.println(ex);
        }
        
        return getIndex(request, response);
    }
    
}

package com.app.wms.web.controller.common;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.dto.*;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class BaseCrossDockController {
    
    protected String getUser(HttpServletRequest request, String key) {
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        
        if (lu != null) {
            HashMap map = new HashMap();
            map.put("code", lu.getUserId());
            map.put("role", lu.getRoleCode());
            map.put("wh", lu.getWhCode());
            
            return map.get(key).toString();
        } else {
            return null;
        }
    }
    
    @RequestMapping(params = "action=json")
    public ModelAndView getJSON(@RequestParam String type, HttpServletRequest request, HttpServletResponse response) {
        try {
            String sidx = request.getParameter("sidx");
            String sord = request.getParameter("sord");

            HashMap model = new HashMap();
            if(type.equals("po")){
                String c = request.getParameter("c");
                POCrossDockDao pOCrossDockDao = DaoFactory.createPOCrossDockDao();
                List<POCrossDock> list = new ArrayList<POCrossDock>();
                
                if(c.equals("gr")){
                    list = pOCrossDockDao.findPONotInGR(getUser(request, "wh"), sidx, sord);
                } else if(c.equals("so")){
                    list = pOCrossDockDao.findPONotInSO(getUser(request, "wh"), sidx, sord);
                }
                
                model.put("records", list.size());
                model.put("rows", list);
            } else if(type.equals("product")){
                ProductDao productDao = DaoFactory.createProductDao();
                List<Product> list = productDao.findWhereWhCodeEquals(getUser(request, "wh"));
                for(int i = 0; i < list.size(); i++) {
                    list.get(i).setId(i);
                }
                model.put("records", list.size());
                model.put("rows", list);
            } else if(type.equals("gr")){
                GRCrossDockDao gRCrossDockDao = DaoFactory.createGRCrossDockDao();
                List<GRCrossDock> list = gRCrossDockDao.findGRNotInPA(getUser(request, "wh"), sidx, sord);
                model.put("records", list.size());
                model.put("rows", list);
            } else if(type.equals("tallyman")){
                TallymanDao tallymanDao = DaoFactory.createTallymanDao();
                List<Tallyman> list = tallymanDao.findWhereWhCodeEquals(getUser(request, "wh"));
                model.put("records", list.size());
                model.put("rows", list);
            } else if(type.equals("location")){
                WarehouseLocationDao warehouseLocationDao = DaoFactory.createWarehouseLocationDao();
                List<WarehouseLocation> list = warehouseLocationDao.findWarehouseLocationWhereLocationAreaEquals("Cross Dock");
                model.put("records", list.size());
                model.put("rows", list);
            } else if(type.equals("consignee")){
                ConsigneeDao consigneeDao = DaoFactory.createConsigneeDao();
                List<Consignee> list = consigneeDao.findWhereWhCodeEquals(getUser(request, "wh"));
                for(int i = 0; i < list.size(); i++) {
                    list.get(i).setId(i);
                }
                model.put("records", list.size());
                model.put("rows", list);
            } else if(type.equals("so")){
                String c = request.getParameter("c");
                SOCrossDockDao sOCrossDockDao = DaoFactory.createSOCrossDockDao();
                List<SOCrossDock> list = new ArrayList<SOCrossDock>();
                
                if(c.equals("p")){
                    list = sOCrossDockDao.findSONotInP(getUser(request, "wh"), sidx, sord);
                } else if(c.equals("do")){
                    list = sOCrossDockDao.findSONotInDO(getUser(request, "wh"), sidx, sord);
                }
                model.put("records", list.size());
                model.put("rows", list);
            }

            model.put("page", 1);
            model.put("total", 1);

            return new ModelAndView("jsonView", model);
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    @RequestMapping(params = "action=so")
    public void getSOCrossDock(@RequestParam("soCode") String soCode, HttpServletResponse response) {
        try {
            POCrossDockDtlDao pOCrossDockDtlDao = DaoFactory.createPOCrossDockDtlDao();
            List<POCrossDockDtl> list = pOCrossDockDtlDao.findbySOCode(soCode);

            if(!list.isEmpty()) {
                ProductDao productDao = DaoFactory.createProductDao();
                for(int i = 0; i < list.size(); i++) {
                    POCrossDockDtl pocdd = list.get(i);
                    Product p = productDao.findWhereProductCodeEquals(pocdd.getProductCode()).get(0);

                    String out = "<tr>";
                    out = out.concat("<td>" + (i + 1) + "</td>");
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
    
    @RequestMapping(params = "action=cc")
    public void getConsignee(@RequestParam("consigneeCode") String consigneeCode, HttpServletResponse response) {
        try {
            /* FIXME: [FYA] findByPrimaryKey(consigneeCode) not use id but in the future will use it | info: Rendra 01 02 2013 */
            ConsigneeDao consigneeDao = DaoFactory.createConsigneeDao();
            Consignee c = consigneeDao.findByPrimaryKey(consigneeCode);
            
            if(c != null) {
                String out = c.getConsigneeName() + "<br />"
                    + c.getConsigneePhone() + "<br />"
                    + c.getAddress1();

                response.getWriter().print(out);
            } else {
                response.getWriter().print("N");
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
}

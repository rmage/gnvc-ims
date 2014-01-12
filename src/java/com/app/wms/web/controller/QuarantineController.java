package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.QuarantineDao;
import com.app.wms.engine.db.dao.QuarantineDtlDao;
import com.app.wms.engine.db.dto.Quarantine;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.controller.common.BaseCrossDockController;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 * 16 February 2013
 */

@Controller
@RequestMapping(value = "/Quarantine.htm")
public class QuarantineController extends BaseCrossDockController {
    
    @RequestMapping(params = "action=list")
    public ModelAndView getIndex(HttpServletRequest request, HttpServletResponse response) {
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        
        QuarantineDao quarantineDao = DaoFactory.createQuarantineDao();
        List<Quarantine> list = quarantineDao.findAll(getUser(request, "wh"), page);
        
        HashMap model = new HashMap();
        model.put("list", list);
        model.put("page", page);
        
        return new ModelAndView("cross_dock/Quarantine", "model", model);
    }
    
    @RequestMapping(params = "action=view")
    public ModelAndView getDetail(@RequestParam("qCode") String qCode, HttpServletRequest request) {
        try {
            QuarantineDao quarantineDao = DaoFactory.createQuarantineDao();
            Quarantine q = quarantineDao.findByQCode(qCode);
            
            HashMap model = new HashMap();
            model.put("list", q);
            
            return new ModelAndView("cross_dock/QuarantineView", "model", model);
        } catch(Exception ex) {
            System.out.println(ex);
            return getIndex(request, null);
        }
    }
    
    @RequestMapping(params = "action=qdtl")
    public void getDetail(@RequestParam("putawayCode") String putawayCode, HttpServletResponse response) {
        try {
            QuarantineDtlDao quarantineDtlDao = DaoFactory.createQuarantineDtlDao();
            List<Map<String,Object>> list = quarantineDtlDao.findByPutawayCode(putawayCode);
            
            int i = 1;
            for(Map m : list) {
                String out = "<tr>"
                    + "<td>" + i + "</td>"
                    + "<td>" + m.get("product_code") + "</td>"
                    + "<td>" + m.get("product_name") + "</td>"
                    + "<td>" + m.get("qty_put") + "</td>"
                    + "<td>" + m.get("qty_po") + "</td>"
                    + "</tr>";
                
                i++;
                response.getWriter().println(out);
            }
        } catch(Exception ex) {
            System.out.println(ex);
            try {
                response.getWriter().print('N');
            } catch(IOException ex2) {
                System.out.println(ex2);
            }
        }
    }
    
    /*
     *  Utilities Function
     */
    
    @RequestMapping(params = "action=search")
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
        List<Quarantine> list = new ArrayList<Quarantine>();
        
        try {
            String criteria = request.getParameter("qCode");
            QuarantineDao quarantineDao = DaoFactory.createQuarantineDao();
            
            if(criteria != null) {
                Quarantine dto = quarantineDao.findByQCode(criteria);
                
                if(dto != null) {
                    list.add(dto);
                }
            } else {
                criteria = request.getParameter("qDate");
                list = quarantineDao.findByQDate(new SimpleDateFormat("dd/MM/yyyy").parse(criteria));
            }
            
        } catch(Exception ex) {
            System.out.println(ex);
        } finally {
            HashMap model = new HashMap();
            model.put("list", list);
            model.put("page", 1);
            
            return new ModelAndView("cross_dock/Quarantine", "model", model);
        }
    }
    
}

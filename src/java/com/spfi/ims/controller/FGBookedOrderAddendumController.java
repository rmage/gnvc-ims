package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGBookedOrderAddendumDao;
import com.spfi.ims.dao.FGBookedOrderDao;
import com.spfi.ims.dao.FGBrandDao;
import com.spfi.ims.dao.FGBuyerDao;
import com.spfi.ims.dao.FGDestinationDao;
import com.spfi.ims.dao.FGFreightDao;
import com.spfi.ims.dao.FGTopDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FGBookedOrderAddendumController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/BookedOrderAddendumList");
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        HashMap map = new HashMap();
        
        /* DAO | Define needed dao here */
        FGBuyerDao fgbDao = DaoFactory.createFGBuyerDao();
        FGFreightDao fgfDao = DaoFactory.createFGFreightDao();
        FGTopDao fgtDao = DaoFactory.createFGTopDao();
        
        /* TRANSACTION | Something complex here */
        map.put("buyers", fgbDao.findAllActive());
        map.put("freights", fgfDao.findAllActive());
        map.put("tops", fgtDao.findAllActive());
        
        return new ModelAndView("default/finish_goods/BookedOrderAddendumAdd", "ims", map);
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data")
                    .replaceAll(":numberSign:", "#")
                    .replaceAll(":percentageSign:", "%");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FGBookedOrderAddendumDao fgboDao = DaoFactory.createFGBookedOrderAddendumDao();
            
            /* TRANSACTION | Something complex here */
            fgboDao.insert(data, lu.getUserId());

            return new ModelAndView("redirect:FGBookedOrderAddendum.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGBookedOrderAddendum.htm?action=create");
        }
    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        FGBookedOrderAddendumDao fgboDao = DaoFactory.createFGBookedOrderAddendumDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fgboDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fgboDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("bor_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("bor_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("bor_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("borad_number")).append("\", ");
            sb.append("\"5\": \"").append(x.get("borad_date")).append("\", ");
            sb.append("\"6\": \"").append(x.get("created_by")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }
    
    public void getBor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        FGBookedOrderDao fgboDao = DaoFactory.createFGBookedOrderDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = fgboDao.findByCode(request.getParameter("code"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("bor_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("bor_date")).append("\", ");
            sb.append("\"3\": \"").append(x.get("bor_id")).append("\", ");
            sb.append("\"4\": \"").append(x.get("bor_specification")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }
    
    public void getBrand(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        FGBrandDao fgbDao = DaoFactory.createFGBrandDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = fgbDao.findByBuyer(Integer.parseInt(request.getParameter("buyer")));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("brand_id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("brand_name")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }
    
    public void getDestination(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        FGDestinationDao fgdDao = DaoFactory.createFGDestinationDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = fgdDao.findAllActive();
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("destination_id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("destination_name")).append("\",");
            sb.append("\"3\": \"").append(x.get("destination_description")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }
    
    public void getPackStyle(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        FGBookedOrderAddendumDao fgboDao = DaoFactory.createFGBookedOrderAddendumDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = fgboDao.getPackStyle(request.getParameter("term"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("pack_id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("pack_style")).append("\", ");
            sb.append("\"3\": \"").append(x.get("pack_size")).append("\", ");
            sb.append("\"4\": \"").append(x.get("pack_per_cs")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }
    
    public void getItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        FGBookedOrderAddendumDao fgboDao = DaoFactory.createFGBookedOrderAddendumDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = fgboDao.getItem(Integer.parseInt(request.getParameter("key")));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("item_id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("item_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("item_name")).append("\",");
            sb.append("\"4\": \"").append(x.get("pack_size")).append("\",");
            sb.append("\"5\": \"").append(x.get("item_nw")).append("\",");
            sb.append("\"6\": \"").append(x.get("item_dwpw")).append("\",");
            sb.append("\"7\": \"").append(x.get("item_oil")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }
    
}

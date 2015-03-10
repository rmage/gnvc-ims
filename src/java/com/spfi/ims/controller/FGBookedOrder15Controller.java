package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGBookedOrder15Dao;
import com.spfi.ims.dao.FGBrandDao;
import com.spfi.ims.dao.FGBuyerDao;
import com.spfi.ims.dao.FGDestinationDao;
import com.spfi.ims.dao.FGFreightDao;
import com.spfi.ims.dao.FGItemDao;
import com.spfi.ims.dao.FGTopDao;
import com.spfi.ims.helper.StringHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FGBookedOrder15Controller extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/BookedOrder15List");
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

        return new ModelAndView("default/finish_goods/BookedOrder15Add", "ims", map);
    }
    
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        HashMap map = new HashMap();
        
        /* DAO | Define needed dao here */
        FGBuyerDao fgbDao = DaoFactory.createFGBuyerDao();
        FGFreightDao fgfDao = DaoFactory.createFGFreightDao();
        FGTopDao fgtDao = DaoFactory.createFGTopDao();
        FGBookedOrder15Dao fgboDao = DaoFactory.createFGBookedOrder15Dao();
        
        /* TRANSACTION | Something complex here */
        map.put("buyers", fgbDao.findAllActive());
        map.put("freights", fgfDao.findAllActive());
        map.put("tops", fgtDao.findAllActive());
        map.put("dataHeader", fgboDao.findByNumber(request.getParameter("key")));
        map.put("dataDetail", fgboDao.findDtlByNumber(request.getParameter("key")));
        map.put("items", fgboDao.getBorItem(request.getParameter("key")));
        
        return new ModelAndView("default/finish_goods/BookedOrder15Edit", "ims", map);
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        /* DAO | Define needed dao here */
        FGBookedOrder15Dao fgboDao = DaoFactory.createFGBookedOrder15Dao();
        
        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fgboDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fgboDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("bor_number")).append("\", ");
            sb.append("\"2\": \"").append(x.get("bor_number")).append("\", ");
            sb.append("\"3\": \"").append(sdf.format(x.get("bor_date"))).append("\", ");
            sb.append("\"4\": \"").append(x.get("buyer_name")).append("\", ");
            sb.append("\"5\": \"").append(x.get("bor_contract")).append("\", ");
            sb.append("\"6\": \"").append(x.get("bor_maxcancode")).append("\", ");
            sb.append("\"7\": \"").append(x.get("freight_code") == null ? "" : x.get("freight_code")).append("\", ");
            sb.append("\"8\": \"").append(x.get("top_code") == null ? "" : x.get("top_code")).append("\", ");
            sb.append("\"9\": \"").append(x.get("bor_gspform")).append("\", ");
            sb.append("\"10\": \"").append(x.get("created_by")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data").replaceAll(":s:", "^")
                    .replaceAll(":se:", "~");
//                    request.getParameter("data")
//                    .replaceAll(":numberSign:", "#")
//                    .replaceAll(":percentageSign:", "%");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FGBookedOrder15Dao fgboDao = DaoFactory.createFGBookedOrder15Dao();
            
            /* TRANSACTION | Something complex here */
            fgboDao.insert(data, lu.getUserId());

            return new ModelAndView("redirect:FGBookedOrder15.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGBookedOrder15.htm?action=create");
        }
    }
    
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data").replaceAll(":s:", "^")
                    .replaceAll(":se:", "~");
//                    request.getParameter("data")
//                    .replaceAll(":numberSign:", "#")
//                    .replaceAll(":percentageSign:", "%");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FGBookedOrder15Dao fgboDao = DaoFactory.createFGBookedOrder15Dao();
            System.out.println(data);
            /* TRANSACTION | Something complex here */
            fgboDao.edit(data, lu.getUserId());

            return new ModelAndView("redirect:FGBookedOrder15.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGBookedOrder15.htm?action=update&key=" + request.getParameter("key"));
        }
    }
    
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FGBookedOrder15Dao fgboDao = DaoFactory.createFGBookedOrder15Dao();
            
            /* TRANSACTION | Something complex here */
            fgboDao.delete(request.getParameter("key"), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new ModelAndView("redirect:FGBookedOrder15.htm");
    }

    public void getBrand(HttpServletRequest request, HttpServletResponse response) {
        try {
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
        } catch (Exception e) {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            DaoFactory.createDBLoggingDao().logError(new Date(), "FINISH GOODS", "BOOKED ORDER > getBrand", StringHelper.stackTraceToString(e), lu.getUserId(), 0);
        }
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

    public ModelAndView getItem(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> json = new HashMap<String, Object>();
        
        try {
            json.put("rows", DaoFactory.createFGItemDao().findByItemCodeOnTest(request.getParameter("term")));
        } catch (Exception e) {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            DaoFactory.createDBLoggingDao().logError(new Date(), "FINISH GOODS", "BOOKED ORDER > getItem", StringHelper.stackTraceToString(e), lu.getUserId(), 0);
        }
        
        return new ModelAndView("jsonView", json);
    }

}

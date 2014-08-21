package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGPalletTransferDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FGPalletTransferController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/PalletTranferList");
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        HashMap<String, Object> model = new HashMap<String, Object>();
        
        /* DAO | Define needed dao here */
        FGPalletTransferDao fgptDao = DaoFactory.createFGPalletTransferDao();
        
        /* TRANSACTION | Something complex here */
        model.put("pss", fgptDao.getPackStyle());
        model.put("ls", fgptDao.getLocation());
        
        return new ModelAndView("default/finish_goods/PalletTranferAdd", "ims", model);
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FGPalletTransferDao fgptDao = DaoFactory.createFGPalletTransferDao();
            
            /* TRANSACTION | Something complex here */
            fgptDao.insert(data, lu.getUserId());

            return new ModelAndView("redirect:FGPalletTransfer.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGPalletTransfer.htm?action=create");
        }
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        FGPalletTransferDao fgptDao = DaoFactory.createFGPalletTransferDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fgptDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fgptDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) sb.append(",");
            sb.append("{\"1\": \"").append(x.get("pts_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("pts_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("pts_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("pts_for_brand")).append("\", ");
            sb.append("\"5\": \"").append(x.get("pack_style")).append("(").append(x.get("pack_size")).append(")").append("\", ");
            sb.append("\"6\": \"").append(x.get("pts_can_code")).append("\", ");
            sb.append("\"7\": \"").append(x.get("bor_code") == null ? "" : x.get("bor_code")).append("\", ");
            sb.append("\"8\": \"").append(x.get("loca_code")).append("\", ");
            sb.append("\"9\": \"").append(NumberFormat.getNumberInstance().format(x.get("pts_total_qty"))).append("\", ");
            sb.append("\"10\": \"").append(x.get("created_by")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}"); pw.print(sb.toString()); pw.flush(); pw.close();
    }
    
    public void getBor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        FGPalletTransferDao fgptDao = DaoFactory.createFGPalletTransferDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = fgptDao.getBor(request.getParameter("term"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("bor_id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("bor_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("bor_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("bor_buyer")).append("\"}");
            
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
        FGPalletTransferDao fgptDao = DaoFactory.createFGPalletTransferDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = fgptDao.getItem(Integer.parseInt(request.getParameter("key")));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("item_id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("item_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("item_name")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
        
    }
    
}

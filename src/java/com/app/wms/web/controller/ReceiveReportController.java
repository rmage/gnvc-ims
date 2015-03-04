package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.ReceiveReportDao;
import com.app.wms.engine.db.dao.ReceiveReportDtlDao;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.ReceiveReportDtl;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.helper.StringHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ReceiveReportController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("non_fish/RRList");
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
      return new ModelAndView("non_fish/RRAdd");
        
    }
    
    public void ajaxDocument(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ProductDaoException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        int rrCode = Integer.parseInt(request.getParameter("key"));

        /* DAO | Define needed dao here */
        ProductDao productDao = DaoFactory.createProductDao();
        ReceiveReportDtlDao receiveReportDtlDao = DaoFactory.createReceiveReportDtlDao();

        /* TRANSACTION | Something complex here */
        // get receiving report detail
        pw.print("[");
        List<ReceiveReportDtl> rrds = receiveReportDtlDao.findByRr(rrCode);
        for(ReceiveReportDtl x : rrds) {
            if(b)
                pw.print(",");
            
            Product p = productDao.findWhereProductCodeEquals(x.getProductCode()).get(0);
            pw.print("{\"itemCode\": \"" + p.getProductCode() + "\", ");
            pw.print("\"itemName\": \"" + p.getProductName()+ "\",");
            pw.print("\"department\": \"" + x.getDepartmentCode() + "\",");
            pw.print("\"qtyGood\": \"" + x.getQtyGood() + "\",");
            pw.print("\"qtyBad\": \"" + x.getQtyBad() + "\",");
            pw.print("\"uom\": \"" + x.getUom()+ "\"}");
            
            b = Boolean.TRUE;
        } pw.print("]");
        
    }
    
    public void getPO(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
        
        /* DATA | get initial value */
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        ReceiveReportDao rrDao = DaoFactory.createReceiveReportDao();
        
        /* TRANSACTION | Something complex here */
        List<Map<String, Object>> ms = rrDao.getPo(request.getParameter("key"));
        for (Map<String, Object> x : ms) {
            sb.append("{\"1\": \"").append(x.get("po_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("po_date")).append("\", ");
            sb.append("\"3\": \"").append(x.get("supplier_name")).append("\"}");
        }
        pw.print(sb.toString());
        pw.flush();
        pw.close();
        
    }
    
    public void getPODetail(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        ReceiveReportDao rrDao = DaoFactory.createReceiveReportDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = rrDao.getPoDetail(request.getParameter("key"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("prs_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("product_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("product_name")).append("\", ");
            sb.append("\"4\": \"").append(x.get("department_code")).append("\", ");
            sb.append("\"5\": \"").append(NumberFormat.getNumberInstance().format(x.get("qty"))).append("\", ");
            sb.append("\"6\": \"").append(x.get("uom_name")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
        
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        ReceiveReportDao rrDao = DaoFactory.createReceiveReportDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(rrDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = rrDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("rr_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("rr_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("rr_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("po_code")).append("\", ");
            sb.append("\"5\": \"").append(x.get("rr_from")).append("\", ");
            sb.append("\"6\": \"").append(x.get("created_by")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }
    
    // 2015 Update | by FYA
    public ModelAndView ajaxNSave(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createReceiveReportDao().ajaxNSave(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }

    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            DaoFactory.createReceiveReportDao().delete(Integer.parseInt(request.getParameter("key")), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:ReceiveReport.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        try {
            String key = request.getParameter("key");
            model.put("rr", DaoFactory.createReceiveReportDao().getReceiving(key));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("default/non_fish/ReceiveUpdate", "model", model);
    }

    public ModelAndView ajaxNUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createReceiveReportDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }
    
}

package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.BcCodeDao;
import com.spfi.ims.dao.IcoreMapDao;
import com.spfi.ims.helper.StringHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DateFormat;
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

public class BcCodeController extends MultiActionController{
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("bc_code/BcCodeList");
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response){

        /* DATA | get initial value */
        Map<String, Object> m = new HashMap<String, Object>();
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        
        /* DAO | Define needed dao here */
        BcCodeDao bcCodeDao = DaoFactory.createBcCodeDao();
        
        /* TRANSACTION | Something complex here */
        List<Map<String, Object>> pcs = bcCodeDao.findCodeBc();
        m.put("pc", pcs);

        return new ModelAndView("bc_code/BcCodeAdd", "model", m);
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        /* DAO | Define needed dao here */
        BcCodeDao bcCodeDao = DaoFactory.createBcCodeDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(bcCodeDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = bcCodeDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("trx_no")).append("\", ");
            sb.append("\"3\": \"").append(x.get("bc_no")).append("\", ");
            sb.append("\"4\": \"").append(x.get("reg_no")).append("\"}");
            
            System.out.println("append 1 = " + x.get("srjalan_no"));
            System.out.println("append 2 = " + x.get("trx_no"));
            System.out.println("append 3 = " + x.get("bc_no"));
            System.out.println("append 4 = " + x.get("reg_no"));
            System.out.println("append ID = " + x.get("id"));
            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }

    public ModelAndView ajaxNSave(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createBcCodeDao().ajaxNSave(data, separator[0], separator[1], lu.getUserId());
            
            System.out.println("data = " + data);
            System.out.println("separator 0 = " + separator[0]);
            System.out.println("separator 1 = " + separator[1]);            

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
            DaoFactory.createBcCodeDao().delete(request.getParameter("key"), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:BcCode.htm");
    }
    
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
            try {
                String key = request.getParameter("key");
                model.put("bc_code", DaoFactory.createBcCodeDao().getBcCode(key));
            } catch (Exception e) {
                e.printStackTrace();
            }

        return new ModelAndView("bc_code/BcCodeUpdate", "model", model);
    }

    public ModelAndView ajaxNUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createBcCodeDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }
    
    public ModelAndView getTrxNo(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
        
        /* DATA | get initial value */
        Map<String, Object> json = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String key = request.getParameter("key");
        String cat = request.getParameter("cat");
        int mode =  Integer.parseInt(request.getParameter("mode"));

        /* DAO | Define needed dao here */
        BcCodeDao bcCodeDao = DaoFactory.createBcCodeDao();

        /* TRANSACTION | Something complex here */
        List<Map<String, Object>> bc = bcCodeDao.findWhereTrxNo(key, cat, mode);
	
        for(Map<String, Object> x : bc) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tanggal", x.get("tanggal"));
            map.put("prodCode", x.get("prodCode"));
            map.put("prodName", x.get("prodName"));
            map.put("qty", x.get("qty"));
            map.put("hs_code", x.get("hs_code"));
            map.put("harga_per_satuan", x.get("harga_per_satuan"));            
            
            // added by : FYA
            map.put("tanggal_pemasukan", x.get("tanggal_pemasukan"));   
            map.put("supplier", x.get("supplier"));
            map.put("valuta", x.get("valuta"));
            list.add(map);
        }
        
        json.put("rows", list);
        
        return new ModelAndView("jsonView", json);

    }    
}

package com.spfi.ims.controller;

import com.app.wms.engine.db.dao.HsCodeDao;
import com.app.wms.engine.db.dao.ProductCategoryDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dto.ProductCategory;
import java.util.HashMap;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.ProductCategoryDaoException;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.IcoreMapDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class IcoreMapController extends MultiActionController {
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {        
        /* DATA | get initial value */
        Map<String, Object> m = new HashMap<String, Object>();
        
        /* DAO | Define needed dao here */
        IcoreMapDao icoreMapDao = DaoFactory.createIcoreMapDao();
        
        /* TRANSACTION | Something complex here */
        List<Map<String, Object>> pcs = icoreMapDao.findProductCategory();
        m.put("pc", pcs);
        
        return new ModelAndView("icore/IcoreMapList", "model", m);
    }
    
    public ModelAndView getProductList(HttpServletRequest request, HttpServletResponse response) 
        throws ProductDaoException, IOException {
        
        /* DATA | get initial value */
        Map<String, Object> json = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String categoryCode = request.getParameter("key");
        String sourceCategory = request.getParameter("soc");

        /* DAO | Define needed dao here */
        ProductDao productDao = DaoFactory.createProductDao();
        IcoreMapDao icoreMapDao = DaoFactory.createIcoreMapDao();

        /* TRANSACTION | Something complex here */
        List<Map<String, Object>> im =  icoreMapDao.findWhereProductCodeEquals(categoryCode, sourceCategory);        
        
        for(Map<String, Object> x : im) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", x.get("product_code"));
            map.put("name", x.get("product_name"));
            map.put("type", x.get("product_category"));
            map.put("uom", x.get("uom_name"));
            map.put("hscode", x.get("hs_code"));
            map.put("hsname", x.get("name"));
            list.add(map);
        }
        
        json.put("rows", list);
        
        return new ModelAndView("jsonView", json);
    }
    
    public void getHsCodeList (HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
        
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        String term = request.getParameter("term");
        
        HsCodeDao hsCodeDao = DaoFactory.createHsCodeDao();
        
        pw.print("[");
        List<Map<String, Object>> hsc;
        hsc = hsCodeDao.findHsCodebyCode(term);
        for (Map<String, Object> x : hsc) {
            if (b) {
                pw.print(",");
            }

            pw.print("{\"code\": \"" + x.get("code") + "\", ");
            pw.print("\"name\": \"" + x.get("name") + "\"}");

            b = Boolean.TRUE;

        }
        pw.print("]");
    }
    
    public ModelAndView insertNUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            String categoryCode = request.getParameter("key");
            
            IcoreMapDao icoreMapDao = DaoFactory.createIcoreMapDao();
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            //get current date time with Date()
            Date date = new Date();
            
            String productCode = request.getParameter("code");
            String hsCode = request.getParameter("hscode");
            String sourcePro = request.getParameter("scPro");
            String createdDate = dateFormat.format(date);
            String updatedDate = dateFormat.format(date);
            char c = 'Y';
            String isActive = Character.toString(c);
            
            DaoFactory.createIcoreMapDao().insertNUpdate(productCode, hsCode, sourcePro, createdDate, lu.getUserId(), updatedDate, lu.getUserId(), isActive);
            json.put("s", 1);
            json.put("hscode", hsCode);
            json.put("scPro", sourcePro);
            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }
}

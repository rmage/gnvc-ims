package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.DepartmentDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.StockInventoryDao;
import com.app.wms.engine.db.dao.SwsDao;
import com.app.wms.engine.db.dao.SwsDtlDao;
import com.app.wms.engine.db.dto.Department;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.StockInventory;
import com.app.wms.engine.db.dto.Sws;
import com.app.wms.engine.db.dto.SwsDtl;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.DepartmentDaoException;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.exceptions.StockInventoryDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class StoreWithdrawalSlipController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        
        /* DAO | Define needed dao here */
        SwsDao swsDao = DaoFactory.createSwsDao();
        
        /* TRANSACTION | Something complex here */
        List<Sws> ss = swsDao.findByLogin(lu.getDepartmentCode());
        m.put("s", ss);
        
        return new ModelAndView("non_fish/SWSList", "model", m);
        
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) 
        throws DepartmentDaoException {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        
        /* DAO | Define needed dao here */
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        
        /* TRANSACTION | Something complex here */
        Department d = departmentDao.findWhereDepartmentCodeEquals(lu.getDepartmentCode()).get(0);
        m.put("department", d.getDepartmentCode() + ":" + d.getDepartmentName());
        
        return new ModelAndView("non_fish/SWSAdd", "model", m);
        
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) 
        throws ParseException {
        
        /* DATA | get initial value */
        String[] master = request.getParameter("master").split(":", -1);
        String[] details = request.getParameterValues("detail");
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        
        /* DAO | Define needed dao here */
        SwsDao swsDao = DaoFactory.createSwsDao();
        SwsDtlDao swsDtlDao = DaoFactory.createSwsDtlDao();
        
        /* TRANSACTION | Something complex here */
        // insert master sws
        Sws s = new Sws();
        s.setSwsCode(Integer.parseInt(master[0]));
        s.setSwsDate(new SimpleDateFormat("dd/MM/yyyy").parse(master[1]));
        s.setSwsInfo(master[2]);
        s.setDepartmentCode(master[3]);
        s.setCreatedBy(lu.getUserId());
        s.setCreatedDate(new Date());
        swsDao.insert(s);
        
        //insert detail sws
        for(String x : details) {
            String[] detail = x.split(":");
            
            SwsDtl sd = new SwsDtl();
            sd.setSwsCode(s.getSwsCode());
            sd.setProductCode(detail[0]);
            sd.setSoh(Integer.parseInt(detail[1]));
            sd.setQty(Integer.parseInt(detail[2]));
            sd.setUom(detail[3]);
            sd.setCreatedBy(lu.getUserId());
            sd.setCreatedDate(new Date());
            swsDtlDao.insert(sd);
        }
        
        return new ModelAndView("redirect:Sws.htm");
        
    }
    
    public void ajaxDocument(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ProductDaoException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        int swsCode = Integer.parseInt(request.getParameter("key"));
        
        /* DAO | Define needed dao here */
        SwsDtlDao swsDtlDao = DaoFactory.createSwsDtlDao();
        ProductDao productDao = DaoFactory.createProductDao();
        
        /* TRANSACTION | Something complex here */
        pw.print("[");
        List<SwsDtl> sds = swsDtlDao.findBySws(swsCode);
        for(SwsDtl x : sds) {
            if(b)
                pw.print(",");
            
            Product p = productDao.findWhereProductCodeEquals(x.getProductCode()).get(0);
            pw.print("{\"itemCode\": \"" + p.getProductCode() + "\", ");
            pw.print("\"itemName\": \"" + p.getProductName() + "\",");
            pw.print("\"type\": \"" + p.getProductCategory() + "\",");
            pw.print("\"soh\": \"" + x.getSoh() + "\",");
            pw.print("\"qty\": \"" + x.getQty() + "\",");
            pw.print("\"uom\": \"" + x.getUom() + "\"}");
            
            b = Boolean.TRUE;
        } pw.print("]");
        
    }
    
    public void getProduct(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, StockInventoryDaoException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        String productName = request.getParameter("term");
        
        /* DAO | Define needed dao here */
        ProductDao productDao = DaoFactory.createProductDao();
        StockInventoryDao stockInventoryDao = DaoFactory.createStockInventoryDao();
        
        /* TRANSACTION | Something complex here */
        pw.print("[");
        List<Product> ps = productDao.findWhereProductNameEquals(productName, 5);
        for(Product x : ps) {
            if(b)
                pw.print(",");
            
            StockInventory si = stockInventoryDao.findWhereProductCodeEquals(x.getProductCode()).get(0);
            
            pw.print("{\"itemCode\": \"" + x.getProductCode() + "\", ");
            pw.print("\"itemName\": \"" + x.getProductName() + "\",");
            pw.print("\"type\": \"" + x.getProductCategory() + "\",");
            pw.print("\"soh\": \"" + si.getBalance() + "\",");
            pw.print("\"uom\": \"" + x.getUom() + "\"}");
            
            b = Boolean.TRUE;
            
        } pw.print("]");
        
    }
    
}

package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.StockBalanceDao;
import com.app.wms.engine.db.dao.StockInventoryDao;
import com.app.wms.engine.db.dao.SwsDtlDao;
import com.app.wms.engine.db.dao.TsDao;
import com.app.wms.engine.db.dao.TsDtlDao;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.StockInventory;
import com.app.wms.engine.db.dto.Sws;
import com.app.wms.engine.db.dto.SwsDtl;
import com.app.wms.engine.db.dto.Ts;
import com.app.wms.engine.db.dto.TsDtl;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.exceptions.StockInventoryDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class TransferSlipController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        
        /* DAO | Define needed dao here */
        TsDao tsDao = DaoFactory.createTsDao();
        
        /* TRANSACTION | Something complex here */
        List<Ts> ts = tsDao.findAll();
        m.put("t", ts);
        
        return new ModelAndView("non_fish/TSList", "model", m);
        
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        
        /* DAO | Define needed dao here */
        TsDao tsDao = DaoFactory.createTsDao();
        
        /* TRANSACTION | Something complex here */
        List<Sws> ss = tsDao.findWhereNotInTs();
        m.put("s", ss);
        
        return new ModelAndView("non_fish/TSAdd", "model", m);
        
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) 
        throws ParseException, StockInventoryDaoException {
        
        /* DATA | get initial value */
        Ts t = new Ts();
        String[] qtys = request.getParameterValues("qty");
        String[] items = request.getParameterValues("item");
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
                
        /* DAO | Define needed dao here */
        TsDao tsDao = DaoFactory.createTsDao();
        TsDtlDao tsDtlDao = DaoFactory.createTsDtlDao();
        StockBalanceDao stockBalanceDao = DaoFactory.createStockBalanceDao();
        StockInventoryDao stockInventoryDao = DaoFactory.createStockInventoryDao();
        
        /* TRANSACTION | Something complex here */
        // insert transfer slip
        t.setTsCode(Integer.parseInt(request.getParameter("tsCode")));
        t.setTsDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(request.getParameter("tsDate") + " " + 
            new SimpleDateFormat("HH:mm:ss").format(new Date())));
        t.setTsInfo(request.getParameter("tsInfo"));
        t.setSwsCode(Integer.parseInt(request.getParameter("swsCode")));
        t.setCreatedBy(lu.getUserId());
        t.setCreatedDate(new Date());
        tsDao.insert(t);
        
        int i = 0;
        TsDtl td = new TsDtl();
        td.setTsCode(t.getTsCode());
        td.setCreatedBy(lu.getUserId());
        td.setCreatedDate(new Date());
        for(String x : items) {
            td.setProductCode(x);
            td.setQty(Integer.parseInt(qtys[i]));
            tsDtlDao.insert(td);
            
            // stock balance history for stock card
            StockInventory si = stockInventoryDao.findWhereProductCodeEquals(td.getProductCode()).get(0);
            stockBalanceDao.insertOrUpdate(td.getProductCode(), new Date(), si.getBalance(), new BigDecimal(td.getQty()), 20);
            
            // substract stock inventory balance
            tsDao.updateStockInventory(td.getProductCode(), td.getQty()); i++;
        }
        
        return new ModelAndView("redirect:TransferSlip.htm");
        
    }
    
    public void getSwsDetail(HttpServletRequest request, HttpServletResponse response) 
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
            pw.print("\"qty\": \"" + x.getQty() + "\",");
            pw.print("\"uom\": \"" + x.getUom() + "\"}");
            
            b = Boolean.TRUE;
        } pw.print("]");
        
    }
    
}

package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.DrDao;
import com.app.wms.engine.db.dao.DrDtlDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.StockInventoryDao;
import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dto.Dr;
import com.app.wms.engine.db.dto.DrDtl;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.StockInventory;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.exceptions.StockInventoryDaoException;
import com.app.wms.engine.db.exceptions.SupplierDaoException;
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

public class DeliveryController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) 
        throws SupplierDaoException {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();

        /* DAO | Define needed dao here */
        DrDao drDao = DaoFactory.createDrDao();
        SupplierDao supplierDao = DaoFactory.createSupplierDao();

        /* TRANSACTION | Something complex here */
        m.put("type", request.getParameter("type"));
        
        List<Dr> ds = drDao.findAll(request.getParameter("type"));
        for(Dr x : ds) {
            Supplier s = supplierDao.findWhereSupplierCodeEquals(x.getSupplierCode()).get(0);
            x.setSupplierCode(s.getSupplierName());
        }
        m.put("d", ds);
        
        return new ModelAndView("finish_goods/DRList", "model", m);
        
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) 
        throws SupplierDaoException {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();

        /* DAO | Define needed dao here */
        SupplierDao supplierDao = DaoFactory.createSupplierDao();
        
        /* TRANSACTION | Something complex here */
        m.put("type", request.getParameter("type"));
        
        List<Supplier> ss = supplierDao.findWhereIsActiveEquals("Y");
        m.put("supplier", ss);
        
        return new ModelAndView("finish_goods/DRAdd", "model", m);
        
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) 
        throws ParseException, NumberFormatException {
        
        /* DATA | get initial value */
        String type = request.getParameter("type");
        String[] master = request.getParameter("master").split(":");
        String[] details = request.getParameterValues("detail");
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        /* DAO | Define needed dao here */
        DrDao drDao = DaoFactory.createDrDao();
        DrDtlDao drDtlDao = DaoFactory.createDrDtlDao();

        /* TRANSACTION | Something complex here */
        // insert master delivery receipt
        Dr d = new Dr();
        d.setDrCode(Integer.parseInt(master[0]));
        d.setDrDate(new SimpleDateFormat("dd/MM/yyyy").parse(master[1]));
        d.setDrFrom(master[2]);
        d.setDrFromLoc(master[3]);
        d.setDrToLoc(master[4]);
        d.setDrRemarks(master[5]);
        d.setDrType(type);
        d.setSupplierCode(master[6]);
        d.setOrCode(master[7]);
        d.setDmCode(master[8]);
        d.setCreatedBy(lu.getUserId());
        d.setCreatedDate(new Date());
        drDao.insert(d);
        
        // insert detail delivery receipt
        for(String x : details) {
            String[] detail = x.split(":");
            DrDtl dd = new DrDtl();
            dd.setDrCode(d.getDrCode());
            dd.setDrQty(new BigDecimal(detail[0]));
            dd.setDrUom(detail[1]);
            dd.setProductCode(detail[2]);
            dd.setCreatedBy(lu.getUserId());
            dd.setCreatedDate(new Date());
            drDtlDao.insert(dd);
            
            // update stock inventory
            drDao.updateStockInventory(dd.getProductCode(), dd.getDrQty());
        }
        
        return new ModelAndView("redirect:Delivery.htm?type=" + type);
        
    }
    
    public void ajaxDocument(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ProductDaoException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        int drCode = Integer.parseInt(request.getParameter("key"));

        /* DAO | Define needed dao here */
        DrDtlDao drDtlDao = DaoFactory.createDrDtlDao();
        ProductDao productDao = DaoFactory.createProductDao();

        /* TRANSACTION | Something complex here */
        pw.print("[");
        List<DrDtl> dds = drDtlDao.findByDR(drCode);
        for(DrDtl x : dds) {
            if(b)
                pw.print(",");
            
            Product p = productDao.findWhereProductCodeEquals(x.getProductCode()).get(0);
            pw.print("{\"itemCode\": \"" + p.getProductCode() + "\", ");
            pw.print("\"itemName\": \"" + p.getProductName() + "\",");
            pw.print("\"qty\": \"" + x.getDrQty() + "\",");
            pw.print("\"uom\": \"" + x.getDrUom() + "\"}");
            
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

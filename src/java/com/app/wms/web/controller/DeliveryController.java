package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.DeliveryDao;
import com.app.wms.engine.db.dao.DistributorDao;
import com.app.wms.engine.db.dao.DrDao;
import com.app.wms.engine.db.dao.DrDtlDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.StockBalanceDao;
import com.app.wms.engine.db.dao.StockInventoryDao;
import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.engine.db.dto.Distributor;
import com.app.wms.engine.db.dto.Dr;
import com.app.wms.engine.db.dto.DrDtl;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.StockInventory;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.exceptions.StockInventoryDaoException;
import com.app.wms.engine.db.exceptions.SupplierDaoException;
import com.app.wms.engine.db.exceptions.UserDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class DeliveryController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("finish_goods/DRList");
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) 
        throws SupplierDaoException {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();

        /* DAO | Define needed dao here */
        
        /* TRANSACTION | Something complex here */
        m.put("type", request.getParameter("type"));
        
        return new ModelAndView("finish_goods/DRAdd", "model", m);
        
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) 
        throws ParseException, NumberFormatException, StockInventoryDaoException, UserDaoException {
        
        /* DATA | get initial value */
        String type = request.getParameter("type");
        String[] master = request.getParameter("master").split(":", -1);
        String[] details = request.getParameterValues("detail");
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        /* DAO | Define needed dao here */
        DrDao drDao = DaoFactory.createDrDao();
        DrDtlDao drDtlDao = DaoFactory.createDrDtlDao();
        StockBalanceDao stockBalanceDao = DaoFactory.createStockBalanceDao();
        StockInventoryDao stockInventoryDao = DaoFactory.createStockInventoryDao();
        UserDao uDao = DaoFactory.createUserDao();

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
        d.setCreatedBy(uDao.findByPrimaryKey(lu.getUserId()).getName());
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
            dd.setCreatedBy(d.getCreatedBy());
            dd.setCreatedDate(d.getCreatedDate());
            drDtlDao.insert(dd);
            
            // stock balance history for stock card
            StockInventory si = stockInventoryDao.findWhereProductCodeEquals(dd.getProductCode()).get(0);
            stockBalanceDao.insertOrUpdate(dd.getProductCode(), new Date(), si.getBalance(), dd.getDrQty(), 23);
            
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
        String term = request.getParameter("term");
        
        /* DAO | Define needed dao here */
        ProductDao productDao = DaoFactory.createProductDao();
        StockInventoryDao stockInventoryDao = DaoFactory.createStockInventoryDao();
        
        /* TRANSACTION | Something complex here */
        pw.print("[");
        List<Product> ps;
        if(request.getParameter("mode").equals("name")) {
            ps = productDao.findWhereProductNameEquals(term, 20);
        } else {
            ps = productDao.findWhereProductCodeEquals(term, 20);
        }
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
    
    public void getSupplier (HttpServletRequest request, HttpServletResponse response) 
        throws IOException, SupplierDaoException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        String supplierName = request.getParameter("term");
        
        /* DAO | Define needed dao here */
        SupplierDao supplierDao = DaoFactory.createSupplierDao();
        
        /* TRANSACTION | Something complex here */
        pw.print("[");
        List<Supplier> ss = supplierDao.findWhereSupplierNameEquals(supplierName);
        for(Supplier x : ss) {
            if(b) pw.print(",");
            pw.print("{\"code\": \"" + x.getSupplierCode() + "\", ");
            pw.print("\"name\": \"" + x.getSupplierName() + "\",");
            pw.print("\"address\": \"" + x.getSupplierAddress() + "\"}");
            b = Boolean.TRUE;
        }
        pw.print("]");
        
    }
    
    public void getDistributor (HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        String distributorName = request.getParameter("term");
        
        /* DAO | Define needed dao here */
        DistributorDao distributorDao = DaoFactory.createDistributorDao();
        
        /* TRANSACTION | Something complex here */
        pw.print("[");
        List<Distributor> ss = distributorDao.findByName(distributorName);
        for(Distributor x : ss) {
            if(b) pw.print(",");
            pw.print("{\"code\": \"" + x.getDistributorCode() + "\", ");
            pw.print("\"name\": \"" + x.getDistributorName() + "\",");
            pw.print("\"address\": \"" + x.getDistributorAddress() + "\"}");
            b = Boolean.TRUE;
        }
        pw.print("]");
        
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        DeliveryDao dDao = DaoFactory.createDeliveryDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(dDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = dDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("dr_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("dr_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("dr_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("dr_from")).append("\", ");
            sb.append("\"5\": \"").append(x.get("dr_to")).append("\", ");
            sb.append("\"6\": \"").append(x.get("created_by")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }

}

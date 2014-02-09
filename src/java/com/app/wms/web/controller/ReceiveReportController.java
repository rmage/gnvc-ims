package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.PrsDetailDao;
import com.app.wms.engine.db.dao.ReceiveReportDao;
import com.app.wms.engine.db.dao.ReceiveReportDtlDao;
import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.PrsDetail;
import com.app.wms.engine.db.dto.Purchase;
import com.app.wms.engine.db.dto.PurchaseDtl;
import com.app.wms.engine.db.dto.ReceiveReport;
import com.app.wms.engine.db.dto.ReceiveReportDtl;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.exceptions.SupplierDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ReceiveReportController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();

        /* DAO | Define needed dao here */
        ReceiveReportDao receiveReportDao = DaoFactory.createReceiveReportDao();

        /* TRANSACTION | Something complex here */
        List<ReceiveReport> rrs = receiveReportDao.findAll();
        m.put("rr", rrs);
        
        return new ModelAndView("non_fish/RRList", "model", m);
        
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) 
        throws SupplierDaoException {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();

        /* DAO | Define needed dao here */
        SupplierDao supplierDao = DaoFactory.createSupplierDao();
        ReceiveReportDao receiveReportDao = DaoFactory.createReceiveReportDao();

        /* TRANSACTION | Something complex here */
        List<Purchase> ps = receiveReportDao.findByNotInRR();
        for(Purchase x : ps) {
            Supplier s = supplierDao.findWhereSupplierCodeEquals(x.getSupplierCode()).get(0);
            x.setSupplierCode(s.getSupplierName());
        } m.put("p", ps);
        
        return new ModelAndView("non_fish/RRAdd", "model", m);
        
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            /* DATA | get initial value */
            String[] master = request.getParameter("master").split(":");
            String[] details = request.getParameterValues("detail");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            /* DAO | Define needed dao here */
            ReceiveReportDao receiveReportDao = DaoFactory.createReceiveReportDao();
            ReceiveReportDtlDao receiveReportDtlDao = DaoFactory.createReceiveReportDtlDao();

            /* TRANSACTION | Something complex here */
            // insert master receiving report
            ReceiveReport rr = new ReceiveReport();
            rr.setRrCode(Integer.parseInt(master[0]));
            rr.setRrDate(new SimpleDateFormat("dd/MM/yyyy").parse(master[1]));
            rr.setPoCode(Integer.parseInt(master[2]));
            rr.setRrFrom(master[3]);
            rr.setCreatedBy(lu.getUserId());
            rr.setCreatedDate(new Date());
            receiveReportDao.insert(rr);
            
            // insert detail receiving report
            for(String x : details) {
                String[] detail = x.split(":");
                ReceiveReportDtl rrd = new ReceiveReportDtl();
                rrd.setRrCode(rr.getRrCode());
                rrd.setProductCode(detail[0]);
                rrd.setDepartmentCode(detail[1]);
                rrd.setQtyGood(Integer.parseInt(detail[2]));
                rrd.setQtyBad(Integer.parseInt(detail[3]));
                rrd.setUom(detail[4]);
                rrd.setCreatedBy(lu.getUserId());
                rrd.setCreatedDate(new Date());
                receiveReportDtlDao.insert(rrd);
                
                // insert or update stock_inventory
                receiveReportDao.updateStockInventory(rrd.getProductCode(), rrd.getQtyGood());
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return new ModelAndView("redirect:ReceiveReport.htm");
        
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
            pw.print("\"uom\": \"" + x.getDepartmentCode() + "\"}");
            
            b = Boolean.TRUE;
        } pw.print("]");
        
    }
    
    public void getPODetail(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ProductDaoException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter(); 
        int poCode = Integer.parseInt(request.getParameter("key"));

        /* DAO | Define needed dao here */
        ProductDao productDao = DaoFactory.createProductDao();
        PrsDetailDao prsDetailDao = DaoFactory.createPrsDetailDao();
        ReceiveReportDao receiveReportDao = DaoFactory.createReceiveReportDao();
        ReceiveReportDtlDao receiveReportDtlDao = DaoFactory.createReceiveReportDtlDao();

        /* TRANSACTION | Something complex here */
        pw.print("[");
        List<PurchaseDtl> pds = receiveReportDao.findByPo(poCode);
        for(PurchaseDtl x : pds) {
            if(b)
                pw.print(",");
            
            Product p = productDao.findWhereProductCodeEquals(x.getProductCode()).get(0);
            PrsDetail pd = prsDetailDao.findByPrsProduct(x.getPrsNumber(), x.getProductCode());
            List<ReceiveReportDtl> rrd = receiveReportDtlDao.findByPoProduct(poCode, x.getProductCode());
            
            for(ReceiveReportDtl xx : rrd) {
                pd.setQty(pd.getQty().subtract(new BigDecimal(xx.getQtyGood())));
            }
            
            x.setProductCode(p.getProductName() + ":" + p.getProductCode() + ":" + 
                pd.getQty() + ":" + pd.getUomName());
            
            pw.print("{\"product\": \"" + x.getProductCode()+ "\", ");
            pw.print("\"department\": \"" + x.getDepartmentCode()+ "\"}");
            
            b = Boolean.TRUE;
        } pw.print("]");
        
    }
    
}

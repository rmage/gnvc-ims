package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.ProductPriceDao;
import com.app.wms.engine.db.dao.PrsDetailDao;
import com.app.wms.engine.db.dao.PurchaseDao;
import com.app.wms.engine.db.dao.PurchaseDtlDao;
import com.app.wms.engine.db.dao.ReceiveReportDao;
import com.app.wms.engine.db.dao.ReceiveReportDtlDao;
import com.app.wms.engine.db.dao.StockBalanceDao;
import com.app.wms.engine.db.dao.StockInventoryDao;
import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.ProductPrice;
import com.app.wms.engine.db.dto.PrsDetail;
import com.app.wms.engine.db.dto.Purchase;
import com.app.wms.engine.db.dto.PurchaseDtl;
import com.app.wms.engine.db.dto.ReceiveReport;
import com.app.wms.engine.db.dto.ReceiveReportDtl;
import com.app.wms.engine.db.dto.StockInventory;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.exceptions.SupplierDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
            String[] master = request.getParameter("master").split(":", -1);
            String[] details = request.getParameterValues("detail");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            /* DAO | Define needed dao here */
            PurchaseDao purchaseDao = DaoFactory.createPurchaseDao();
            PrsDetailDao prsDetailDao = DaoFactory.createPrsDetailDao();
            PurchaseDtlDao purchaseDtlDao = DaoFactory.createPurchaseDtlDao();
            StockBalanceDao stockBalanceDao = DaoFactory.createStockBalanceDao();
            ProductPriceDao productPriceDao = DaoFactory.createProductPriceDao();
            ReceiveReportDao receiveReportDao = DaoFactory.createReceiveReportDao();
            StockInventoryDao stockInventoryDao = DaoFactory.createStockInventoryDao();
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
            
            // get sub total and quantity
            Purchase p = purchaseDao.findByPo(String.valueOf(rr.getPoCode()));
            List<PurchaseDtl> pds = purchaseDtlDao.findByPo(p.getPoCode());
            
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
                
                // XXX: FYA | ACCOUNTING - set average price
                PurchaseDtl pd = new PurchaseDtl();
                for(PurchaseDtl xx : pds) {
                    if(xx.getProductCode().equals(rrd.getProductCode())) {
                        pd = xx; break;
                    }
                }
                PrsDetail prd = prsDetailDao.findByPrsProduct(pd.getPrsNumber(), rrd.getProductCode());
                ProductPrice pp = productPriceDao.findByProduct(rrd.getProductCode());
                
                // FIXME: FYA | Only Support IDR not check PO Currency
                StockInventory si = stockInventoryDao.findWhereProductCodeEquals(rrd.getProductCode()).get(0);
                pp.setUnitPrice( (pp.getUnitPrice()
                    .multiply( si.getBalance() ).setScale(2, RoundingMode.HALF_EVEN)).add( (pd.getSubTotal()
                    .divide(prd.getQty(), 2, RoundingMode.HALF_EVEN))
                    .multiply(new BigDecimal(rrd.getQtyGood())).setScale(2, RoundingMode.HALF_EVEN) )
                    .divide( si.getBalance().add(new BigDecimal(rrd.getQtyGood())), 2, RoundingMode.HALF_EVEN ));
                pp.setUpdatedBy(lu.getUserId());
                pp.setUpdatedDate(new Date());
                productPriceDao.update(pp, 1, new BigDecimal(rrd.getQtyGood()));
                
                // stock balance history for stock card
                stockBalanceDao.insertOrUpdate(rrd.getProductCode(), new Date(), si.getBalance(), new BigDecimal(rrd.getQtyGood()), 10);
                
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
            pw.print("\"uom\": \"" + x.getUom()+ "\"}");
            
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

package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.DistributorDao;
import com.app.wms.engine.db.dao.DrDao;
import com.app.wms.engine.db.dao.DrDtlDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dto.Distributor;
import com.app.wms.engine.db.dto.Dr;
import com.app.wms.engine.db.dto.DrDtl;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.exceptions.SupplierDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ReturnCargoDrController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey (HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        
        /* DAO | Define needed dao here */
        
        /* TRANSACTION | Something complex here */
        
        return new ModelAndView("finish_goods/RRCDrList", "model", m);
        
    }
    
    public ModelAndView create (HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("finish_goods/RRCDrAdd");
    }
    
    public void getDrDetail (HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ProductDaoException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        int drCode = Integer.parseInt(request.getParameter("key"));
        
        /* DAO | Define needed dao here */
        DrDao drDao = DaoFactory.createDrDao();
        DrDtlDao drDtlDao = DaoFactory.createDrDtlDao();
        ProductDao productDao = DaoFactory.createProductDao();
        DistributorDao distributorDao = DaoFactory.createDistributorDao();
        
        /* TRANSACTION | Something complex here */
        Dr d = drDao.findByCode(drCode);
        if(d != null) {
            Distributor dis = distributorDao.findByCode(d.getSupplierCode());
            String out = "{\"status\":true, \"from\":\"" + dis.getDistributorName()+ "\", \"date\":\"" + new SimpleDateFormat("dd/MM/yyyy").format(d.getDrDate()) + "\", \"data\":[";
            List<DrDtl> ds = drDtlDao.findByDR(drCode);
            for(DrDtl x : ds) {
                if(b) out += ",";
                Product p = productDao.findWhereProductCodeEquals(x.getProductCode()).get(0);
                out = out + "{\"name\":\"" + p.getProductName() + "\", \"code\":\"" + p.getProductCode() + "\", \"qty\":" + x.getDrQty() + ", \"uom\":\"" + x.getDrUom() + "\"}";
                b = Boolean.TRUE;
            }
            pw.print(out + "]}");
        } else {
            pw.print("{\"status\":false, \"message\":\"DR Number missing?\"}");
        }
        
    }
    
}

package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.PrsDao;
import com.app.wms.engine.db.dao.PrsDetailDao;
import com.app.wms.engine.db.dao.PurchaseDao;
import com.app.wms.engine.db.dao.PurchaseDtlDao;
import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.engine.db.dto.AssignCanvassing;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.Prs;
import com.app.wms.engine.db.dto.PrsDetail;
import com.app.wms.engine.db.dto.Purchase;
import com.app.wms.engine.db.dto.PurchaseDtl;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.ApprovalRangeDaoException;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.exceptions.PrsDaoException;
import com.app.wms.engine.db.exceptions.SupplierDaoException;
import com.app.wms.engine.db.exceptions.UserDaoException;
import com.app.wms.engine.db.exceptions.UserRoleDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.util.FyaUtility;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class PurchaseController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) 
        throws SupplierDaoException, UserDaoException, ApprovalRangeDaoException, UserRoleDaoException {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        List<BigDecimal> bds = new ArrayList<BigDecimal>();
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        
        /* DAO | Define needed dao here */
        PurchaseDao purchaseDao = DaoFactory.createPurchaseDao();
        SupplierDao supplierDao = DaoFactory.createSupplierDao();
        PurchaseDtlDao purchaseDtlDao = DaoFactory.createPurchaseDtlDao();
        
        /* TRANSACTION | Something complex here */
        List<Purchase> ps = purchaseDao.findAll();
        for(Purchase x : ps) {
            BigDecimal amount = BigDecimal.ZERO;
            List<PurchaseDtl> pds = purchaseDtlDao.findByPo(x.getPoCode());
            for(PurchaseDtl xx : pds) {
                amount = amount.add(xx.getSubTotal());
            } bds.add(amount);
            
            Supplier s = supplierDao.findWhereSupplierCodeEquals(x.getSupplierCode()).get(0);
            x.setSupplierCode(s.getSupplierCode() + ":" + s.getSupplierName() + ":" + s.getTelephone());
            if(x.getIsApproved().equals("N")) {
                x.setIsApproved(FyaUtility.checkPOApprovalAuth(lu.getUserId(), amount));
                x.setApprovedBy(FyaUtility.checkPOApprovalWait(amount, "name"));
            }
        }
        m.put("p", ps);
        m.put("bd", bds);
        
        return new ModelAndView("non_fish/POList", "model", m);
        
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) 
        throws SupplierDaoException {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        
        /* DAO | Define needed dao here */
        SupplierDao supplierDao = DaoFactory.createSupplierDao();
        
        /* TRANSACTION | Something complex here */
        // get supplier list
        List<Supplier> ss = supplierDao.findWhereIsActiveEquals("Y");
        m.put("supplier", ss);
        
        return new ModelAndView("non_fish/POAdd", "model", m);
        
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            /* DATA | get initial value */
            String[] master = request.getParameter("master").split(":");
            String[] details = request.getParameterValues("detail");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            PurchaseDao purchaseDao = DaoFactory.createPurchaseDao();
            PurchaseDtlDao purchaseDtlDao = DaoFactory.createPurchaseDtlDao();
            
            /* TRANSACTION | Something complex here */
            // insert master purchase order
            Purchase p = new Purchase();
            p.setPoCode(Integer.parseInt(master[0]));
            p.setPoDate(new SimpleDateFormat("dd/MM/yyyy").parse(master[1]));
            p.setSupplierCode(master[2]);
            p.setDiscount(Integer.parseInt(master[3]));
            p.setPph(Integer.parseInt(master[4]));
            p.setPpn(Integer.parseInt(master[5]));
            p.setCreatedBy(lu.getUserId());
            p.setCreatedDate(new Date());
            purchaseDao.insert(p);
            
            // insert detail purchase order
            for(String x : details) {
                String[] detail = x.split(":");
                PurchaseDtl pd = new PurchaseDtl();
                pd.setPoCode(p.getPoCode());
                pd.setPrsNumber(detail[0]);
                pd.setProductCode(detail[1]);
                pd.setDepartmentCode(detail[2]);
                pd.setSubTotal(new BigDecimal(detail[3]));
                pd.setCreatedBy(lu.getUserId());
                pd.setCreatedDate(new Date());
                purchaseDtlDao.insert(pd);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return new ModelAndView("redirect:Purchase.htm");
        
    }
    
    public ModelAndView approval(HttpServletRequest request, HttpServletResponse response) 
        throws UserDaoException {
        
        /* DATA | get initial value */
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        /* DAO | Define needed dao here */
        UserDao userDao = DaoFactory.createUserDao();
        PurchaseDao purchaseDao = DaoFactory.createPurchaseDao();

        /* TRANSACTION | Something complex here */
        // get po data and approve it
        Purchase p = purchaseDao.findByPo(request.getParameter("po"));
        if(p != null) {
            p.setIsApproved("Y");
            p.setApprovedBy(userDao.findByPrimaryKey(lu.getUserId()).getName());
            p.setApprovedDate(new Date());
            p.setUpdatedBy(lu.getUserId());
            p.setUpdatedDate(new Date());
            purchaseDao.update(p);
        }
        
        return new ModelAndView("redirect:Purchase.htm");
        
    }
    
    public void getItems(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ProductDaoException, PrsDaoException {
        
        PurchaseDao purchaseDao = DaoFactory.createPurchaseDao();
        List<AssignCanvassing> acs = purchaseDao.findBySupplier(request.getParameter("key"));
        
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter(); pw.print("[");
        PrsDao prsDao = DaoFactory.createPrsDao();
        ProductDao productDao = DaoFactory.createProductDao();
        PrsDetailDao prsDetailDao = DaoFactory.createPrsDetailDao();
        for(AssignCanvassing ac : acs) {
            if(b)
                pw.print(",");
            
            Prs prs = prsDao.findWherePrsnumberEquals(ac.getPrsNumber()).get(0);
            Product p = productDao.findWhereProductCodeEquals(ac.getProductCode()).get(0);
            PrsDetail pd = prsDetailDao.findByPrsProduct(ac.getPrsNumber(), ac.getProductCode());
            
            pw.print("{\"prsNumber\": \"" + ac.getPrsNumber() + "\", ");
            pw.print("\"itemCode\": \"" + p.getProductCode() + "\", ");
            pw.print("\"itemName\": \"" + p.getProductName() + "\", ");
            pw.print("\"departmentCode\": \"" + prs.getDepartmentName() + "\", ");
            pw.print("\"qty\": \"" + pd.getQty() + "\", ");
            pw.print("\"unit\": \"" + pd.getUomName() + "\", ");
            pw.print("\"price\": \"" + ac.getUnitPrice() + "\", ");
            pw.print("\"amount\": \"" + pd.getQty().multiply(ac.getUnitPrice()).setScale(2) + "\"}");
            
            b = Boolean.TRUE;
        } pw.print("]");
        
    }
    
}

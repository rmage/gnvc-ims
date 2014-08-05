package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dao.CurrencyRateDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.PrsDao;
import com.app.wms.engine.db.dao.PrsDetailDao;
import com.app.wms.engine.db.dao.PurchaseDao;
import com.app.wms.engine.db.dao.PurchaseDtlDao;
import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.engine.db.dto.AssignCanvassing;
import com.app.wms.engine.db.dto.Currency;
import com.app.wms.engine.db.dto.CurrencyRate;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.Prs;
import com.app.wms.engine.db.dto.PrsDetail;
import com.app.wms.engine.db.dto.Purchase;
import com.app.wms.engine.db.dto.PurchaseDtl;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.ApprovalRangeDaoException;
import com.app.wms.engine.db.exceptions.CurrencyDaoException;
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
import java.text.NumberFormat;
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

        return new ModelAndView("non_fish/POList");

    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response)
            throws SupplierDaoException, CurrencyDaoException {

        /* DATA | get initial value */
        HashMap m = new HashMap();

        /* DAO | Define needed dao here */
        CurrencyDao currencyDao = DaoFactory.createCurrencyDao();
        SupplierDao supplierDao = DaoFactory.createSupplierDao();

        /* TRANSACTION | Something complex here */
        // get supplier list
        List<Supplier> ss = supplierDao.findWhereIsActiveEquals("Y");
        m.put("supplier", ss);

        // get currency
        List<Currency> cs = currencyDao.findAll();
        m.put("c", cs);

        return new ModelAndView("non_fish/POAdd", "model", m);

    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {

        try {
            /* DATA | get initial value */
            String[] master = request.getParameter("master").split(":", -1);
            String[] details = request.getParameterValues("detail");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            /* DAO | Define needed dao here */
            CurrencyRateDao currencyRateDao = DaoFactory.createCurrencyRateDao();
            PurchaseDao purchaseDao = DaoFactory.createPurchaseDao();
            PurchaseDtlDao purchaseDtlDao = DaoFactory.createPurchaseDtlDao();
            UserDao uDao = DaoFactory.createUserDao();

            /* TRANSACTION | Something complex here */
            // get currency rate
            //CurrencyRate cr = currencyRateDao.findByCurrency(master[6]);
            // insert master purchase order
            Purchase p = new Purchase();
            p.setPoCode(Integer.parseInt(master[0]));
            //p.setRateId(cr.getRateId());
            p.setPoDate(new SimpleDateFormat("dd/MM/yyyy").parse(master[1]));
            p.setSupplierCode(master[2]);
            p.setDiscount(Integer.parseInt(master[3]));
            p.setPph(Integer.parseInt(master[4]));
            p.setPpn(Integer.parseInt(master[5]));
            p.setCurrency(master[6]);
            p.setRemarks(master[7]);
            p.setCreatedBy(uDao.findByPrimaryKey(lu.getUserId()).getName());
            p.setCreatedDate(new Date());
            purchaseDao.insert(p);
            
            //  DEBUG | Remarks dissapear suddenly?
            System.out.println("[PO" + p.getPoCode() + "] Remarks: " + p.getRemarks());

            // insert detail purchase order
            for (String x : details) {
                String[] detail = x.split(":");
                PurchaseDtl pd = new PurchaseDtl();
                pd.setPoCode(p.getPoCode());
                pd.setPrsNumber(detail[0]);
                pd.setProductCode(detail[1]);
                pd.setDepartmentCode(detail[2]);
                pd.setSubTotal(new BigDecimal(detail[3]));
                pd.setCreatedBy(p.getCreatedBy());
                pd.setCreatedDate(p.getCreatedDate());
                purchaseDtlDao.insert(pd);
            }
        } catch (Exception e) {
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
        if (p != null) {
            String name = userDao.findByPrimaryKey(lu.getUserId()).getName();
            if (lu.getRoleCode().contains("MAN")) {
                if (p.getIsCertified().equals("N")) {
                    p.setIsCertified("Y");
                    p.setCertifiedBy(name);
                    p.setCertifiedDate(new Date());
                } else {
                    p.setIsApproved("Y");
                    p.setApprovedBy(name);
                    p.setApprovedDate(new Date());
                }
            } else if (lu.getRoleCode().contains("MD")) {
                p.setIsApproved("Y");
                p.setApprovedBy(name);
                p.setApprovedDate(new Date());
            }
            p.setUpdatedBy(name);
            p.setUpdatedDate(new Date());
            purchaseDao.update(p);
        }

        return new ModelAndView("redirect:Purchase.htm");

    }

    public void ajaxDocument(HttpServletRequest request, HttpServletResponse response)
            throws IOException, NumberFormatException, ProductDaoException {

        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        int poCode = Integer.parseInt(request.getParameter("key"));

        /* DAO | Define needed dao here */
        ProductDao productDao = DaoFactory.createProductDao();
        PurchaseDao purchaseDao = DaoFactory.createPurchaseDao();
        PrsDetailDao prsDetailDao = DaoFactory.createPrsDetailDao();
        PurchaseDtlDao purchaseDtlDao = DaoFactory.createPurchaseDtlDao();

        /* TRANSACTION | Something complex here */
        pw.print("[");
        List<PurchaseDtl> pds = purchaseDtlDao.findByPo(poCode);
        for (PurchaseDtl x : pds) {
            if (b) {
                pw.print(",");
            }

            Purchase pr = purchaseDao.findByPo(String.valueOf(x.getPoCode()));
            Product p = productDao.findWhereProductCodeEquals(x.getProductCode()).get(0);
            PrsDetail pd = prsDetailDao.findByPrsProduct(x.getPrsNumber(), x.getProductCode());
            AssignCanvassing ac = purchaseDao.findByPrsSupplierProduct(x.getPrsNumber(), pr.getSupplierCode(), x.getProductCode());

            pw.print("{\"itemCode\": \"" + p.getProductCode() + "\", ");
            pw.print("\"itemName\": \"" + p.getProductName() + "\",");
            pw.print("\"department\": \"" + x.getDepartmentCode() + "\",");
            pw.print("\"qty\": \"" + pd.getQty() + "\",");
            pw.print("\"uom\": \"" + pd.getUomName() + "\",");
            pw.print("\"price\": \"" + ac.getUnitPrice() + "\",");
            pw.print("\"amount\": \"" + x.getSubTotal() + "\"}");

            b = Boolean.TRUE;
        }
        pw.print("]");

    }

    public void getItems(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ProductDaoException, PrsDaoException {

        PurchaseDao purchaseDao = DaoFactory.createPurchaseDao();
        List<AssignCanvassing> acs = purchaseDao.findBySupplier(request.getParameter("key"));

        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        pw.print("[");
        PrsDao prsDao = DaoFactory.createPrsDao();
        ProductDao productDao = DaoFactory.createProductDao();
        PrsDetailDao prsDetailDao = DaoFactory.createPrsDetailDao();
        for (AssignCanvassing ac : acs) {
            if (b) {
                pw.print(",");
            }

            Prs prs = prsDao.findByPrsnumberEquals(ac.getPrsNumber());
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
        }
        pw.print("]");

    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) throws IOException, SupplierDaoException, ApprovalRangeDaoException, UserRoleDaoException, UserDaoException {
        String isApproved = "Y";
        Boolean b = Boolean.FALSE;
//        List<BigDecimal> bds = new ArrayList<BigDecimal>();
        PrintWriter pw = response.getWriter();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        PurchaseDao purchaseDao = DaoFactory.createPurchaseDao();
        PurchaseDtlDao purchaseDtlDao = DaoFactory.createPurchaseDtlDao();
        SupplierDao supplierDao = DaoFactory.createSupplierDao();

        pw.print("{\"maxpage\": " + purchaseDao.ajaxMaxPage(request.getParameter("where"), new BigDecimal(request.getParameter("show"))) + ",\"data\": [");
        List<Purchase> ps = purchaseDao.ajaxSearch(request.getParameter("where"), request.getParameter("order"), Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10));
        for (Purchase x : ps) {
            BigDecimal amount = BigDecimal.ZERO;
            List<PurchaseDtl> pds = purchaseDtlDao.findByPo(x.getPoCode());
            for (PurchaseDtl xx : pds) {
                amount = amount.add(xx.getSubTotal());
            }
            Supplier s = supplierDao.findWhereSupplierCodeEquals(x.getSupplierCode()).get(0);
            if (b) {
                pw.print(",");
            }
            if (x.getIsApproved().equals("N")) {
                isApproved = FyaUtility.checkPOApprovalAuth(lu.getUserId(), amount);
                x.setApprovedBy(FyaUtility.checkPOApprovalWait(amount, "name"));
            }
            System.out.println("x.getIsCertified(): " + x.getIsCertified());
            System.out.println("lu.getRoleCode(): " + lu.getRoleCode());
            System.out.println("x.getIsApproved(): " + x.getIsApproved());
            pw.print("{\"1\": \"" + x.getPoCode() + "\", ");
            pw.print("\"2\": \"" + x.getPoCode() + "\", ");
            pw.print("\"3\": \"" + x.getPoDate() + "\", ");
            pw.print("\"4\": \"" + x.getSupplierCode() + "\", ");
            pw.print("\"5\": \"" + s.getSupplierName() + "\", ");
            pw.print("\"6\": \"" + (s.getTelephone() == null ? "" : s.getTelephone()) + "\", ");
            pw.print("\"7\": \"" + NumberFormat.getNumberInstance().format(amount) + "\", ");
            pw.print("\"8\": \"" + (x.getIsCertified().equals("N") ? (lu.getRoleCode().contains("MAN") ? "Certify : <a href=\\\"?action=approval&po=" + x.getPoCode() + "\\\"><img src=\\\"resources/images/checkmark.gif\\\" title=\\\"Certify this Purchase Order\\\" /></a>" : "Waiting for Manager Purchasing certification")
                    : (x.getIsApproved().equals("N") ? (isApproved.equals("Y") ? "Waiting for " + x.getApprovedBy() + " approval" : "Approve : <a href=\\\"?action=approval&po=" + x.getPoCode() + "\\\"><img src=\\\"resources/images/checkmark.gif\\\" title=\\\"Approve this Purchase Order\\\" /></a>") : "Approved by <b>" + x.getApprovedBy() + "</b> at " + sdf.format(x.getApprovedDate()))) + "\"}");

            b = Boolean.TRUE;
        }
        pw.print("]}");
    }
}

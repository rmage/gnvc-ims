package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.AssignCanvassingDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.PrsDetailDao;
import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dto.AssignCanvassing;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.exceptions.SupplierDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.helper.StringHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class SupplierAssignmentController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response)
            throws ProductDaoException {

//        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
//        HashMap m = new HashMap();
//        
//        /* get prs and item */
//        ProductDao productDao = DaoFactory.createProductDao();
//        AssignCanvassingDao assignCanvassingDao = DaoFactory.createAssignCanvassingDao();
//        List<Product> ps = new ArrayList<Product>();
//        List<AssignCanvassing> acs = assignCanvassingDao.findByUserId(lu.getUserId());
//        for(AssignCanvassing x : acs) {
//            Product p = productDao.findWhereProductCodeEquals(x.getProductCode()).get(0);
//            ps.add(p);
//        }
//        m.put("ac", acs);
//        m.put("p", ps);
        return new ModelAndView("non_fish/SAList");

    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response)
            throws SupplierDaoException {

        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        HashMap m = new HashMap();

        /* get assignment item from manager */
        PrsDetailDao prsDetailDao = DaoFactory.createPrsDetailDao();
//        AssignCanvasserDtlDao assignCanvasserDtlDao = DaoFactory.createAssignCanvasserDtlDao();
//        List<AssignCanvasserDtl> cds = assignCanvasserDtlDao.findByUserId(lu.getUserId());
//        
//        List<PrsDetail> pds = new ArrayList<PrsDetail>();
//        for(AssignCanvasserDtl cd : cds) {
//            PrsDetail x = prsDetailDao.findByPrsProduct(cd.getPrsNumber(), cd.getProductCode());
//            pds.add(x);
//        }
        m.put("list", prsDetailDao.findUnassignedSupplier(lu.getUserId()));

        /* get supplier list */
        SupplierDao supplierDao = DaoFactory.createSupplierDao();
        List<Supplier> ss = supplierDao.findWhereIsActiveEquals("Y");
        m.put("supplier", ss);

        return new ModelAndView("non_fish/SAAdd", "model", m);

    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response)
            throws ProductDaoException {

        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        AssignCanvassingDao assignCanvassingDao = DaoFactory.createAssignCanvassingDao();
        AssignCanvassing ac = new AssignCanvassing();
        ac.setPrsNumber(request.getParameter("prsnumber"));
        ac.setProductCode(request.getParameter("productcode"));
        ac.setCreatedBy(lu.getUserId());

        int i = 1;
        while (request.getParameter(i + "supplier") != null) {
            ac.setSupplierCode(request.getParameter(i + "supplierCode"));
            ac.setCreateDate(new Date());
            assignCanvassingDao.insert(ac);

            i++;
        }

        return findByPrimaryKey(request, response);

    }

    public void ajaxDocument(HttpServletRequest request, HttpServletResponse response)
            throws ProductDaoException, SupplierDaoException, IOException {

        String prsNumber = request.getParameter("key1");
        String itemCode = request.getParameter("key2");

        /* get assigned supplier */
        ProductDao productDao = DaoFactory.createProductDao();
        SupplierDao supplierDao = DaoFactory.createSupplierDao();
        AssignCanvassingDao assignCanvassingDao = DaoFactory.createAssignCanvassingDao();
        List<AssignCanvassing> acs = assignCanvassingDao.findByPrsNumberItemCode(prsNumber, itemCode);
        String out = "[";
        for (AssignCanvassing x : acs) {
            if (!out.equals("[")) {
                out += ",";
            }

            Product p = productDao.findWhereProductCodeEquals(x.getProductCode()).get(0);
            Supplier s = supplierDao.findWhereSupplierCodeEquals(x.getSupplierCode()).get(0);

            out += "{\"prsNo\": \"" + x.getPrsNumber() + "\", "
                    + "\"assignDate\": \"" + new SimpleDateFormat("dd/MM/yyyy").format(x.getCreateDate()) + "\", "
                    + "\"itemCode\": \"" + x.getProductCode() + "\", "
                    + "\"itemName\": \"" + p.getProductName() + "\", "
                    + "\"supplierCode\": \"" + x.getSupplierCode() + "\", "
                    + "\"supplierName\": \"" + s.getSupplierName() + "\"}";
        }
        out += "]";
        response.getWriter().print(out);

    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) throws IOException, ProductDaoException, SupplierDaoException {
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ProductDao productDao = DaoFactory.createProductDao();
        AssignCanvassingDao assignCanvassingDao = DaoFactory.createAssignCanvassingDao();
        pw.print("{\"maxpage\": " + assignCanvassingDao.ajaxMaxPageSA(request.getParameter("where"), new BigDecimal(request.getParameter("show")), lu.getUserId()) + ",\"data\": [");
        List<AssignCanvassing> acs = assignCanvassingDao.ajaxSearchSA(request.getParameter("where"), request.getParameter("order"), Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show")), lu.getUserId());
        for (AssignCanvassing x : acs) {
            Product p = productDao.findWhereProductCodeEquals(x.getProductCode()).get(0);
            if (b) {
                pw.print(",");
            }
            pw.print("{\"1\": \"" + x.getId() + "\", ");
            pw.print("\"2\": \"" + x.getPrsNumber() + "\", ");
            pw.print("\"3\": \"" + (x.getUpdatedDate() == null ? "" : sdf.format(x.getUpdatedDate())) + "\", ");
            pw.print("\"4\": \"" + x.getProductCode() + "\", ");
            pw.print("\"5\": \"" + p.getProductName() + "\"}");
            b = Boolean.TRUE;
        }
        pw.print("]}");
    }

    // 2015 Update | by FYA
    public ModelAndView ajaxNSave(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createAssignCanvassingDao().ajaxNSave(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }

    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            DaoFactory.createAssignCanvassingDao().delete(Integer.parseInt(request.getParameter("key")), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:SupplierAssignment.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        try {
            SupplierDao supplierDao = DaoFactory.createSupplierDao();
            List<Supplier> ss = supplierDao.findWhereIsActiveEquals("Y");
            model.put("supplier", ss);

            int key = Integer.parseInt(request.getParameter("key"));
            AssignCanvassingDao acDao = DaoFactory.createAssignCanvassingDao();
            model.put("ass", acDao.getAssignedSupplier(key));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("default/purchase/SupplierAssignmentUpdate", "model", model);
    }
//    
    public ModelAndView ajaxNUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createAssignCanvassingDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }

}

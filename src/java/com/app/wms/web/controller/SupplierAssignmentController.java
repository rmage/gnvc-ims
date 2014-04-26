package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.AssignCanvasserDtlDao;
import com.app.wms.engine.db.dao.AssignCanvassingDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.PrsDetailDao;
import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dto.AssignCanvasserDtl;
import com.app.wms.engine.db.dto.AssignCanvassing;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.PrsDetail;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.exceptions.SupplierDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
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

public class SupplierAssignmentController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) 
        throws ProductDaoException {
        
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        HashMap m = new HashMap();
        
        /* get prs and item */
        ProductDao productDao = DaoFactory.createProductDao();
        AssignCanvassingDao assignCanvassingDao = DaoFactory.createAssignCanvassingDao();
        List<Product> ps = new ArrayList<Product>();
        List<AssignCanvassing> acs = assignCanvassingDao.findByUserId(lu.getUserId());
        for(AssignCanvassing x : acs) {
            Product p = productDao.findWhereProductCodeEquals(x.getProductCode()).get(0);
            ps.add(p);
        }
        m.put("ac", acs);
        m.put("p", ps);
        
        return new ModelAndView("non_fish/SAList", "model", m);
        
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) 
        throws SupplierDaoException {
        
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        HashMap m = new HashMap();
        
        /* get assignment item from manager */
        PrsDetailDao prsDetailDao = DaoFactory.createPrsDetailDao();
        AssignCanvasserDtlDao assignCanvasserDtlDao = DaoFactory.createAssignCanvasserDtlDao();
        List<AssignCanvasserDtl> cds = assignCanvasserDtlDao.findByUserId(lu.getUserId());
        
        List<PrsDetail> pds = new ArrayList<PrsDetail>();
        for(AssignCanvasserDtl cd : cds) {
            PrsDetail x = prsDetailDao.findByPrsProduct(cd.getPrsNumber(), cd.getProductCode());
            pds.add(x);
        }
        m.put("list", pds);
        
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
        while(request.getParameter(i + "supplier") != null) {
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
        for(AssignCanvassing x : acs) {
            if(!out.equals("["))
                out += ",";
            
            Product p = productDao.findWhereProductCodeEquals(x.getProductCode()).get(0);
            Supplier s = supplierDao.findWhereSupplierCodeEquals(x.getSupplierCode()).get(0);
            
            out += "{\"prsNo\": \"" + x.getPrsNumber()+ "\", "
                + "\"assignDate\": \"" + new SimpleDateFormat("dd/MM/yyyy").format(x.getCreateDate()) + "\", "
                + "\"itemCode\": \"" + x.getProductCode()+ "\", "
                + "\"itemName\": \"" + p.getProductName()+ "\", "
                + "\"supplierCode\": \"" + x.getSupplierCode()+ "\", "
                + "\"supplierName\": \"" + s.getSupplierName()+ "\"}";
        }
        out += "]";
        response.getWriter().print(out);
        
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) throws IOException, ProductDaoException, SupplierDaoException {
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        ProductDao productDao = DaoFactory.createProductDao();
        AssignCanvassingDao assignCanvassingDao = DaoFactory.createAssignCanvassingDao();
        pw.print("{\"maxpage\": " + assignCanvassingDao.ajaxMaxPage(request.getParameter("where"), new BigDecimal(request.getParameter("show")), lu.getUserId()) + ",\"data\": [");
        List<AssignCanvassing> acs = assignCanvassingDao.ajaxSearch(request.getParameter("where"), request.getParameter("order"), Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show")), lu.getUserId());
        for (AssignCanvassing x : acs) {
            Product p = productDao.findWhereProductCodeEquals(x.getProductCode()).get(0);
            if(b)
                pw.print(",");
            pw.print("{\"1\": \"" + "\", ");
            pw.print("\"2\": \"" + x.getPrsNumber() + "\", ");
            pw.print("\"3\": \"" + x.getUpdatedDate() + "\", ");
            pw.print("\"4\": \"" + x.getProductCode() + "\", ");
            pw.print("\"5\": \"" + p.getProductName() + "\"}");
            b = Boolean.TRUE;
        }
        pw.print("]}");
    }
}

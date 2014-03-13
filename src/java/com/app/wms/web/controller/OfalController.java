package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.BorDtlDao;
import com.app.wms.engine.db.dao.OfalDao;
import com.app.wms.engine.db.dao.OfalDtlDao;
import com.app.wms.engine.db.dao.PtsDao;
import com.app.wms.engine.db.dto.BorDtl;
import com.app.wms.engine.db.dto.Ofal;
import com.app.wms.engine.db.dto.OfalDtl;
import com.app.wms.engine.db.dto.Pts;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class OfalController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();

        /* DAO | Define needed dao here */
        OfalDao ofalDao = DaoFactory.createOfalDao();

        /* TRANSACTION | Something complex here */
        List<Ofal> os = ofalDao.findAll();
        m.put("o", os);
        
        return new ModelAndView("finish_goods/OFALList", "model", m);
        
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();

        /* DAO | Define needed dao here */

        /* TRANSACTION | Something complex here */
        
        return new ModelAndView("finish_goods/OFALAdd", "model", m);
        
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        String[] master = request.getParameter("master").split(":", -1);
        String[] detail = request.getParameter("detail").split(":", -1);
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        /* DAO | Define needed dao here */
        OfalDao ofalDao = DaoFactory.createOfalDao();
        OfalDtlDao ofalDtlDao = DaoFactory.createOfalDtlDao();

        /* TRANSACTION | Something complex here */
        // insert ofal
        Ofal o = new Ofal();
        o.setOfalLabelNw(master[0]);
        o.setOfalLabelDw(master[1]);
        o.setBorCode(master[2]);
        o.setOfalShipment(master[3]);
        o.setCreatedBy(lu.getUserId());
        o.setCreatedDate(new Date());
        o.setOfalId(ofalDao.insert(o));
        
        // insert ofal detail
        for(String x : detail) {
            OfalDtl od = new OfalDtl();
            od.setOfalId(o.getOfalId());
            od.setPtsCode(Integer.parseInt(x));
            od.setCreatedBy(lu.getUserId());
            od.setCreatedDate(new Date());
            ofalDtlDao.insert(od);
        }
        
        return new ModelAndView("redirect:Ofal.htm");
        
    }
    
    public void ajaxDocument(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        int ofalId = Integer.parseInt(request.getParameter("term"));
        
        /* DAO | Define needed dao here */
        PtsDao ptsDao = DaoFactory.createPtsDao();
        OfalDtlDao ofalDtlDao = DaoFactory.createOfalDtlDao();
        
        /* TRANSACTION | Something complex here */
        pw.print("[");
        List<OfalDtl> ods = ofalDtlDao.findByOfal(ofalId);
        for(OfalDtl x : ods) {
            if(b)
                pw.print(",");
            
            Pts p = ptsDao.findByPts(x.getPtsCode());
            pw.print("{\"ptsCode\": \"" + p.getPtsCode() + "\", ");
            pw.print("\"ptsDate\": \"" + new SimpleDateFormat("dd/MM/yyyy").format(p.getPtsDate()) + "\",");
            pw.print("\"canCode\": \"" + p.getPtsCanCode() + "\",");
            pw.print("\"qty\": \"" + p.getPtsCs() + "\",");
            pw.print("\"location\": \"" + p.getPtsLocation() + "\",");
            pw.print("\"remarks\": \"" + p.getQadRemarks() + "\"}");
            
            b = Boolean.TRUE;
        } pw.print("]");
        
    }
    
    public void getBorDetail(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        String borCode = request.getParameter("term");

        /* DAO | Define needed dao here */
        BorDtlDao borDtlDao = DaoFactory.createBorDtlDao();
        

        /* TRANSACTION | Something complex here */
        int i = 0;
        pw.print("[");
        List<BorDtl> bds = borDtlDao.findByBor(borCode);
        for(BorDtl x : bds) {
            if(b)
                pw.print(",");
            
            String cs = "";
            String[] csnw = x.getBorCanSize().split("/", -1);
            for(int j = 1; j < csnw.length; j++) {
                if(j != 1)
                    cs += "/";
                
                cs += csnw[j - 1];
            }
            
            
            pw.print("{\"idx\": \"" + i + "\", "); i++;
            pw.print("\"buyer\": \"" + x.getBorBuyer() + "\", ");
            pw.print("\"packstyle\": \"" + x.getBorPackStyle() + "\",");
            pw.print("\"cansize\": \"" + cs + "\",");
            pw.print("\"brand\": \"" + x.getBorBrand() + "\",");
            pw.print("\"po\": \"" + x.getBorPoNumber() + "\",");
            pw.print("\"nw\": \"" + csnw[csnw.length - 1] + "\",");
            pw.print("\"dw\": \"" + x.getBorODpw() + "\",");
            pw.print("\"max\": \"" + x.getBorOCcm() + "\",");
            pw.print("\"qty\": \"" + x.getBorCs() + "\",");
            pw.print("\"destination\": \"" + x.getBorDestinationPort() + "\"}");
            
            b = Boolean.TRUE;
        } pw.print("]");
        
    }
    
    public void getPts(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        String borCode = request.getParameter("term");

        /* DAO | Define needed dao here */
        PtsDao ptsDao = DaoFactory.createPtsDao();

        /* TRANSACTION | Something complex here */
        List<Pts> ps = ptsDao.findByBorNotInOfal(borCode);
        pw.print("[");
        for(Pts x : ps) {
            if(b)
                pw.print(",");
            
            pw.print("{\"ptsCode\": \"" + x.getPtsCode() + "\", ");
            pw.print("\"borCode\": \"" + x.getBorCode() + "\",");
            pw.print("\"ptsDate\": \"" + new SimpleDateFormat("mm/dd/yyyy").format(x.getPtsDate()) + "\",");
            pw.print("\"canCode\": \"" + x.getPtsCanCode() + "\",");
            pw.print("\"qty\": \"" + x.getPtsCs() + "\",");
            pw.print("\"location\": \"" + x.getPtsLocation() + "\",");
            pw.print("\"remarks\": \"" + x.getQadRemarks() + "\"}");
            
            b = Boolean.TRUE;
        } pw.print("]");
        
    }
    
}

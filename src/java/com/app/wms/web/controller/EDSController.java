package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.BorDtlDao;
import com.app.wms.engine.db.dao.EdsDao;
import com.app.wms.engine.db.dao.PtsDao;
import com.app.wms.engine.db.dto.BorDtl;
import com.app.wms.engine.db.dto.Eds;
import com.app.wms.engine.db.dto.Pts;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class EDSController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        
        /* DAO | Define needed dao here */
        EdsDao edsDao = DaoFactory.createEdsDao();
        BorDtlDao borDtlDao = DaoFactory.createBorDtlDao();
        
        /* TRANSACTION | Something complex here */
        List<Eds> es = edsDao.findAll();
        for(Eds x : es) {
            String[] i = x.getBorCode().split("::", -1);
            BorDtl bd = borDtlDao.findByBor(i[0]).get(Integer.parseInt(i[1]));
            x.setBorCode(x.getBorCode() + "::" + bd.getBorBuyer() + "::" + bd.getBorBrand() + "::" + 
                bd.getBorPoNumber() + "::" + bd.getBorDestinationPort());
        } m.put("e", es);
        
        return new ModelAndView("finish_goods/EDSList", "model", m);
        
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();

        /* DAO | Define needed dao here */

        /* TRANSACTION | Something complex here */
        
        return new ModelAndView("finish_goods/EDSAdd", "model", m);
        
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) 
        throws ParseException {
        
        /* DATA | get initial value */
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        /* DAO | Define needed dao here */
        EdsDao edsDao = DaoFactory.createEdsDao();

        /* TRANSACTION | Something complex here */
        Eds e = new Eds();
        e.setEdsCode(Integer.parseInt(request.getParameter("edsCode")));
        e.setEdsDate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("edsDate")));
        e.setEdsRemarks(request.getParameter("edsRemarks"));
        e.setBorCode(request.getParameter("borCode"));
        e.setEdsVan(request.getParameter("edsVan"));
        e.setEdsSeal(request.getParameter("edsSeal"));
        e.setEdsVessel(request.getParameter("edsVessel"));
        e.setEdsPlatNo(request.getParameter("edsPlatNo"));
        e.setEdsTimeIn(request.getParameter("edsTimeIn"));
        e.setEdsTimeOut(request.getParameter("edsTimeOut"));
        e.setEdsDriver(request.getParameter("edsDriver"));
        e.setEdsCi(request.getParameter("ciCode"));
        e.setCreatedBy(lu.getUserId());
        e.setCreatedDate(new Date());
        edsDao.insert(e);
        
        return new ModelAndView("redirect:Eds.htm");
        
    }
    
    public void getBorDetail(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        String borCode = request.getParameter("term");

        /* DAO | Define needed dao here */
        PtsDao ptsDao = DaoFactory.createPtsDao();
        BorDtlDao borDtlDao = DaoFactory.createBorDtlDao();
        

        /* TRANSACTION | Something complex here */
        int i = 0;
        pw.print("[");
        List<BorDtl> bds = borDtlDao.findByBor(borCode);
        for(BorDtl x : bds) {
            if(b)
                pw.print(",");
            
            String pts = "";
            Boolean c = Boolean.FALSE;
            List<Pts> ps = ptsDao.findByBor(x.getBorCode());
            for(Pts xx : ps) {
                if(c)
                    pts += ",";
                pts += "{\"cancode\": \"" + xx.getPtsCanCode() + "\", \"cases\": \"" + xx.getPtsCs()+ "\"}";
                c = Boolean.TRUE;
            }
            
            pw.print("{\"idx\": \"" + i + "\", "); i++;
            pw.print("\"buyer\": \"" + x.getBorBuyer() + "\", ");
            pw.print("\"packstyle\": \"" + x.getBorPackStyle() + "\",");
            pw.print("\"cansize\": \"" + x.getBorCanSize() + "\",");
            pw.print("\"brand\": \"" + x.getBorBrand() + "\",");
            pw.print("\"po\": \"" + x.getBorPoNumber() + "\",");
            pw.print("\"pts\": [" + pts + "],");
            pw.print("\"destination\": \"" + x.getBorDestinationPort() + "\"}");
            
            b = Boolean.TRUE;
        } pw.print("]");
        
    }

}

package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.DistributorDao;
import com.app.wms.engine.db.dto.Distributor;
import com.app.wms.engine.db.dto.map.LoginUser;
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

public class DistributorController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {

        /* DATA | get initial value */
        HashMap m = new HashMap();

        /* DAO | Define needed dao here */
        DistributorDao distributorDao = DaoFactory.createDistributorDao();

        /* TRANSACTION | Something complex here */
        List<Distributor> distributor = distributorDao.findAll();
        m.put("t", distributor);

        return new ModelAndView("1_setup/DistributorList", "model", m);

    }

    public ModelAndView findAll(HttpServletRequest request, HttpServletResponse response) {
        DistributorDao distributorDao = DaoFactory.createDistributorDao();
        List<Distributor> distributor = distributorDao.findAll();
        return new ModelAndView("1_setup/DistributorList", "model", distributor);
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        Map map = new HashMap();
        map.put("mode", "create");
        return new ModelAndView("1_setup/DistributorAdd", "model", map);
    }
    
//    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
//        Map map = new HashMap();
//        map.put("mode", "update");
//        return new ModelAndView("1_setup/DistributorEdit","model",map);
//    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Distributor d = new Distributor();
        d.setDistributorCode(request.getParameter("distributorCode"));
        d.setDistributorName(request.getParameter("distributorName"));
        d.setDistributorAddress(request.getParameter("distributorAddress"));
        d.setTelephone(request.getParameter("telephone"));
        d.setFax(request.getParameter("fax"));
        d.setEmail(request.getParameter("email"));
        d.setContactPerson(request.getParameter("contactPerson"));
        d.setIsActive(request.getParameter("isActive"));
        d.setIsDelete(request.getParameter("isDelete"));
        d.setCreatedBy(request.getParameter("createdBy"));
        d.setCreatedDate(new Date());

        DistributorDao distributorDao = DaoFactory.createDistributorDao();
        distributorDao.insert(d);

        return new ModelAndView("redirect:Distributor.htm");
    }
    
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
        Distributor d = new Distributor();
        d.setDistributorName(request.getParameter("distributorName"));
        d.setDistributorAddress(request.getParameter("distributorAddress"));
        d.setTelephone(request.getParameter("telephone"));
        d.setFax(request.getParameter("fax"));
        d.setEmail(request.getParameter("email"));
        d.setDistributorCode(request.getParameter("distributorCoode"));
        
        DistributorDao distributorDao = DaoFactory.createDistributorDao();
        distributorDao.edit(d);
        
        return new ModelAndView("redirect:Distributor.htm");
    }
    
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response){
        Distributor d = new Distributor();
        d.setDistributorCode(request.getParameter("distributorCode"));
        
        DistributorDao distributorDao = DaoFactory.createDistributorDao();
        distributorDao.delete(d);
        
        return new ModelAndView("redirect:Distributor.htm");
    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();

        DistributorDao distDao = DaoFactory.createDistributorDao();

        pw.print("{\"maxpage\": " + distDao.ajaxMaxPage(request.getParameter("where"), new BigDecimal(request.getParameter("show"))) + ",\"data\": [");
        List<Distributor> ps = distDao.ajaxSearch(request.getParameter("where"), request.getParameter("order"), Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10));
        for (Distributor x : ps) {
            if (b) 
                pw.print(",");
            pw.print("{\"1\": \"" + x.getId() + "\", ");
            pw.print("\"2\": \"" + x.getDistributorCode() + "\", ");
            pw.print("\"3\": \"" + x.getDistributorName() + "\", ");
            pw.print("\"4\": \"" + x.getDistributorAddress() + "\", ");
            pw.print("\"5\": \"" + x.getFax() + "\", ");
            pw.print("\"6\": \"" + x.getEmail() + "\", ");
            pw.print("\"7\": \"" + x.getTelephone() + "\"}");

            b = Boolean.TRUE;
        }
        pw.print("]}");
    }
}

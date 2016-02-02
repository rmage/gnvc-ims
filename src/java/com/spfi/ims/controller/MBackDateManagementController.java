package com.spfi.ims.controller;

import com.app.wms.engine.db.dao.AppMenuDao;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.AppMenuDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dto.BackDateProfile;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class MBackDateManagementController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws AppMenuDaoException {
        HashMap<String, Object> model = new HashMap<String, Object>();
        
        // fya} get module id
        String[] urls = {"FGPalletTransfer.htm", "FGExportDelivery.htm", "FGDelivery.htm", "FGReturnCargo.htm", "FGTransfer.htm", "FGReclassification.htm"};
        AppMenuDao appMenuDao = DaoFactory.createAppMenuDao();
        model.put("fgMenuList", appMenuDao.findByUrls(urls));
        
        return new ModelAndView("default/master/BackDateManagementList", model);
    }
    
    public void addUser(HttpServletRequest request, HttpServletResponse response) {
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        
        if (lu != null) {
            BackDateProfile bdp = new BackDateProfile();
            bdp.setMenuCode(request.getParameter("appMenuCode"));
            bdp.setUserId(request.getParameter("userId"));
            bdp.setIsActive("Y");
            bdp.setCreatedBy(DaoFactory.createUserDao().findId(lu.getUserId()).getName());
            
            DaoFactory.createBackDateProfileDao().create(bdp);
        }
    }
    
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        
        if (lu != null) {
            DaoFactory.createBackDateProfileDao().delete(Integer.parseInt(request.getParameter("id")), DaoFactory.createUserDao().findId(lu.getUserId()).getName());
        }
    }
    
    public ModelAndView getUser(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> json = new HashMap<String, Object>();
        
        json.put("users", DaoFactory.createBackDateProfileDao().findByAppMenu(request.getParameter("appMenuCode")));
        
        return new ModelAndView("jsonView", json);
    }
    
    public ModelAndView getUserByName(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> json = new HashMap<String, Object>();
        
        json.put("users", DaoFactory.createBackDateProfileDao().findUserByName(request.getParameter("term"), request.getParameter("appMenuCode")));
        
        return new ModelAndView("jsonView", json);
    }
    
}

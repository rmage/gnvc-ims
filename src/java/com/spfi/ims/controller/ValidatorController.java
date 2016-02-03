package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.BackDateProfileDao;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ValidatorController extends MultiActionController {
    
    public void findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        // silent is golden
    }
    
    public ModelAndView validateBackDate(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> json = new HashMap<String, Object>();
        
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        if (lu != null) {
            // fya} get referer from which page
            String referer = request.getHeader("Referer");
            String[] partReferers = referer.split("/");
            referer = (partReferers[partReferers.length - 1].split("\\?"))[0];
            
            // fya} check if this user eligible or not
            BackDateProfileDao bdpDao = DaoFactory.createBackDateProfileDao();
            if (bdpDao.checkIsValid(referer, lu.getUserId()) > 0) {
                json.put("validity", 1);
            } else {
                json.put("validity", 0);
            }
            
            // fya} put system date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            json.put("currentDate", sdf.format(new Date()));
        } else {
            json.put("validity", 0);
        }
        
        return new ModelAndView("jsonView", json);
    }
    
}

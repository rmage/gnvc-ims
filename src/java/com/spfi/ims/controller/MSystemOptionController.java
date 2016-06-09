package com.spfi.ims.controller;

import com.app.wms.engine.db.factory.DaoFactory;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class MSystemOptionController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        // validate only GET
        if (!request.getMethod().equals("GET")) {
            response.setStatus(403);
            return null;
        }
        
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("list", DaoFactory.createSystemOptionDao().getEditableOptions());
        
        return new ModelAndView("default/master/SystemOption", model);
    }
    
    public void postUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!request.getMethod().equals("POST")) {
            response.setStatus(403);
        }
        
        HashMap<String, Object> json = new HashMap<String, Object>();
        try {
            DaoFactory.createSystemOptionDao().postUpdateValue(Integer.parseInt(request.getParameter("id")), request.getParameter("value"));
            json.put("status", 1);
        } catch (Exception e) {
            json.put("status", -1);
            json.put("message", e.getMessage());
        } finally {
            response.getWriter().println(new Gson().toJson(json));
        }
    }
    
}

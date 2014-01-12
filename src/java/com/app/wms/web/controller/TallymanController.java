/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.spring.TallymanDao;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.hbm.bean.Tallyman;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @gnv solution
 */
@Controller
@RequestMapping( "/tallymanjson.htm")
public class TallymanController {

    @Autowired
    private TallymanDao tallymanDao;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView hello(HttpServletRequest request, HttpServletResponse response) {

        String term = request.getParameter("term") + "";

        List<Tallyman> tallymans = new ArrayList<Tallyman>();
        
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        tallymans.addAll(tallymanDao.getTallymans(lu.getCorpId(), lu.getWhCode()));
        
        Map model = new HashMap();
        model.put("page", 1);
        model.put("total ", 1);
        model.put("records ", tallymans.size());                       
        model.put("rows", tallymans);

        return new ModelAndView("jsonView", model);
    }
}

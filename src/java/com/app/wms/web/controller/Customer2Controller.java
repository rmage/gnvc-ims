/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.spring.Customer2Dao;
import com.app.wms.hbm.bean.Customer;
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
 * {"page":"1","total":2,"records":"13",
 * "rows":[{"id":"13","cell":["13","2007-10-06","Client 3","1000.00","0.00","1000.00",null]},
 * {"id":"12","cell":["12","2007-10-06","Client 2","700.00","140.00","840.00",null]},
 * {"id":"11","cell":["11","2007-10-06","Client 1","600.00","120.00","720.00",null]},
 * {"id":"10","cell":["10","2007-10-06","Client 2","100.00","20.00","120.00",null]},
 * {"id":"9","cell":["9","2007-10-06","Client 1","200.00","40.00","240.00",null]},
 * {"id":"8","cell":["8","2007-10-06","Client 3","200.00","0.00","200.00",null]},
 * {"id":"7","cell":["7","2007-10-05","Client 2","120.00","12.00","134.00",null]},
 * {"id":"6","cell":["6","2007-10-05","Client 1","50.00","10.00","60.00",""]},
 * {"id":"5","cell":["5","2007-10-05","Client 3","100.00","0.00","100.00","no tax at all"]},
 * {"id":"4","cell":["4","2007-10-04","Client 3","150.00","0.00","150.00","no tax"]}],
 * "userdata":{"amount":3220,"tax":342,"total":3564,"name":"Totals:"}}
 */
@Controller
@RequestMapping( "/customerjson.htm")
public class Customer2Controller {

    @Autowired
    private Customer2Dao customer2Dao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView hello(HttpServletRequest request, HttpServletResponse response) {

        String term = request.getParameter("term") + "";

        List<Customer> customers = new ArrayList<Customer>();
        
        customers.addAll(customer2Dao.getCustomers());
        
        Map model = new HashMap();
        model.put("page", 1);
        model.put("total ", 1);
        model.put("records ", customers.size());                       
        model.put("rows", customers);

        return new ModelAndView("jsonView", model);
    }
}

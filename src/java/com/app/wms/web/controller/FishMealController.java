package com.app.wms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FishMealController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        
        /* DAO | Define needed dao here */
        
        /* TRANSACTION | Something complex here */
        m.put("year", new SimpleDateFormat("yyyy").format(new Date()));
        
        String[][] month = { {"01", "January", "0"}, {"02", "February", "0"}, {"03", "March", "0"}, 
            {"04", "April", "0"}, {"05", "May", "0"}, {"06", "June", "0"}, {"07", "July", "0"}, 
            {"08", "August", "0"}, {"09", "September", "0"}, {"10", "October", "0"}, {"11", "November", "0"}, 
            {"12", "December", "0"} };
        for(String[] x : month) {
            if(x[0].equals(new SimpleDateFormat("MM").format(new Date()))) {
                x[2] = "1"; break;
            }
        }
        m.put("month", month);
        
        return new ModelAndView("fish_meal/FMList", "model", m);
        
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        
        /* DAO | Define needed dao here */
        
        /* TRANSACTION | Something complex here */
        m.put("year", new SimpleDateFormat("yyyy").format(new Date()));
        
        String[][] month = { {"01", "January", "0"}, {"02", "February", "0"}, {"03", "March", "0"}, 
            {"04", "April", "0"}, {"05", "May", "0"}, {"06", "June", "0"}, {"07", "July", "0"}, 
            {"08", "August", "0"}, {"09", "September", "0"}, {"10", "October", "0"}, {"11", "November", "0"}, 
            {"12", "December", "0"} };
        for(String[] x : month) {
            if(x[0].equals(new SimpleDateFormat("MM").format(new Date()))) {
                x[3] = "1"; break;
            }
        }
        m.put("month", month);
        
        return new ModelAndView("fish_meal/FMAdd", "model", m);
        
    }
    
    public void getReport(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */

        /* DAO | Define needed dao here */

        /* TRANSACTION | Something complex here */
        
    }
    
}

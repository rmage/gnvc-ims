/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author root
 */
public class HomeController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        System.err.print("handleRequest called");

        return new ModelAndView("login");
    }
    public ModelAndView findByPrimaryKey(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        System.err.print("findByPrimaryKey called");

        return new ModelAndView("login");
    }


   
}

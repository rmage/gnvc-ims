/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author programmer
 */
public class ReportController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("main");
    }

    /*
    public ModelAndView createRptAgentList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView a = new ModelAndView("rptAgentList");
        return a;
    }
	*/
    public ModelAndView previewReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("main");
    }
}

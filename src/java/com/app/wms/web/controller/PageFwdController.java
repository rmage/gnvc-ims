package com.app.wms.web.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageFwdController implements Controller
{
	/**
	 * Method 'handleRequest'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			return new ModelAndView( request.getParameter("page") );
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView( "Error" );
		}
		
	}

}

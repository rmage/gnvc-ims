/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller.common;

import com.app.wms.engine.db.dao.AppMenuDao;
import com.app.wms.engine.db.dao.AppMenuGroupDao;
import com.app.wms.engine.db.dao.CorporateDao;
import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.engine.db.dao.WhDao;
import com.app.wms.engine.db.dto.AppMenu;
import com.app.wms.engine.db.dto.AppMenuGroup;
import com.app.wms.engine.db.dto.Corporate;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author genevacons
 */
public class LoginController implements Controller {

    private Logger logger = Logger.getLogger(LoginController.class );
    private Connection conn;

    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
    	Map m = new HashMap();
    	try{
    		String action = hsr.getParameter("action");

            if (action != null && action.equals("login")) {
                logger.debug("login action detected, throw to login validation");
                return login(hsr, hsr1);
            }

            if (action != null && action.equals("logout")) {
                logger.debug("logout action detected, throw to logout action");
                return logout(hsr, hsr1);
            }

            LoginUser u = (LoginUser) hsr.getSession().getAttribute("user");
            logger.debug(" HandleRequest on LoginController called ");

            if (u != null) {
                logger.debug(" Has Login Detected, throw to app");
                return new ModelAndView("app");
            }
            logger.debug(" Not Login Detected, throw to login");
           
            return new ModelAndView("login","model",m);
    	}catch(Exception e){
    		e.printStackTrace();
    		m.put("msg", e);
    		return new ModelAndView("login","model",m);
    	}
    	
    }
    
    //Login for IMS
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map m = new HashMap();
    	try{
            String date = new Date().toString();
            request.getSession().setAttribute("date", date);
            logger.debug("login on LoginController called");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            UserDao ud = DaoFactory.createUserDao();
            LoginUser u = ud.findWhereLoginIMS(username, password);
            
            System.out.println(">>>try to LoginUser IMS="+u);
            AppMenuDao menuDao = DaoFactory.createAppMenuDao();
            AppMenuGroupDao menuGroupDao = DaoFactory.createAppMenuGroupDao();
            
            if (u != null) {
                logger.debug("got a match user , try to find menu item");
                List<AppMenuGroup> listMenuGroup = menuGroupDao.findByAuthLogin((u.getUserId()));
                List<AppMenu> listMenu = menuDao.findByAuthLogin((u.getUserId()));
                if (listMenu != null && !listMenu.isEmpty()) {
                     logger.debug("got some menu items ");
                } else {
                     logger.debug("no found menu items ");
                }

                request.getSession().setAttribute("user", u);
                request.getSession().setAttribute("menu", listMenu);
                request.getSession().setAttribute("menuGroup", listMenuGroup);

                return new ModelAndView("app");
            } else {
            	
            	Map map = new HashMap();
            	conn = openConnection();
            	
            	if(conn != null){
            		String msg = "Login Invalid, please try again ";
        	        logger.debug("no valid user found, back to login with message: " + msg);
        	        map.put("msg", msg);
                    
            	}else{
            		String msg = "Connection database refused: connect";
            		map.put("msg", msg);
            	}
    	        
                request.getSession().setAttribute("user", null);
                request.getSession().setAttribute("menu", null);
                request.getSession().setAttribute("resultList", null);
        		return new ModelAndView("login", "model", map);
            }
    		
    	}catch (Exception e){
    		e.printStackTrace();
    		m.put("msg", e);
    		return new ModelAndView("login","model",m);
    	}
   
    }

    public Connection openConnection(){
		Connection connect = null;
		try{
                    //  XXX : FYA | Database Connection Setting
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                    connect = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/inventory","sa","sa");
                    return connect;
		}
		catch(SQLException se){
			System.out.println("Connection Failed! Error @ : " +se.getMessage());
			return null;
			
		}
		catch(Exception ex){
			System.out.println("Failed to Connect Database! Error @ : " +ex.getMessage());
			return null;
		}
		
	}
    
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map m = new HashMap();
    	try{
    		logger.debug("logout on LoginController called");

            String msg = "You have been logout on: " + (new Date()).toString();
             logger.debug("back to login with message: " + msg);

            //clear session
            request.getSession().setAttribute("user", null);
            request.getSession().setAttribute("menu", null);
            request.getSession().setAttribute("resultList", null);
            //request.getSession(false);
            request.getSession().invalidate();

            // post message back
            m.put("msg", msg);
            return new ModelAndView("login", "model", m);
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		m.put("msg", e);
    		return new ModelAndView("login","model",m);
    	}

    }
    
}
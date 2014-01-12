package com.app.wms.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.app.wms.engine.db.dao.WhLocatingAreaDao;
import com.app.wms.engine.db.dto.WhLocatingArea;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.util.AppConstant;

public class WhLocatingAreaController extends MultiActionController 
{


	/**
	 * Method 'findByPrimaryKey'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
           
            HashMap m = null;
            final String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                m = this.getModelByPrimaryKey(request);
                m.put("mode", "edit");
                return new ModelAndView("1_setup/WhLocatingAreaEdit", "model", m);
            } else {

                m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/WhLocatingAreaList", "model", m);
            }

        }
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}
	
	private HashMap searchAndPaging(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{

            HashMap m = new HashMap();

            Integer page = null;
            Integer paging = null;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            if (request.getParameter("paging") != null) {
                paging = Integer.parseInt(request.getParameter("paging"));
            }
            if (page == null) {
                page = 1;
            }
            if (paging == null) {
                paging = 10;
            }
            int start = (page - 1) * paging + 1;
            int end = start + paging - 1;

            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            WhLocatingArea c = new WhLocatingArea();
            c.setCorpId(lu.getCorpId());
            c.setWhCode(lu.getWhCode());
            
            c.setLocatingArea(request.getParameter("locatingArea"));
            c.setLocatingCondition(request.getParameter("locatingCondition"));
            
            WhLocatingAreaDao dao = DaoFactory.createWhLocatingAreaDao();
            List<WhLocatingArea> listSearchPage = dao.findWhLocatingAreaPaging(c,page);
            System.out.println("listSearchPage ="+listSearchPage);
            int total = 2000; 
            m.put("locatingarea", listSearchPage);
            m.put("totalRows", total);
            m.put("page", page);
            m.put("paging", paging);

            return m;

		}catch (Exception e){
			throw e;
		}
		
	}
	
	private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
		try {
		 String locatingId = request.getParameter("locatingId");
         System.out.println("locatingId="+locatingId);
		 WhLocatingAreaDao dao = DaoFactory.createWhLocatingAreaDao();
         WhLocatingArea dto = null;

         String mode = request.getParameter("mode");
         if (mode != null && mode.equals("edit")) {
             dto = dao.findByPrimaryKey(locatingId);
         }
         if (dto == null) {
        	 dto = new WhLocatingArea();
        	 dto.setLocatingArea("");
         }
         //edit
         HashMap m = new HashMap();
         System.out.println("dto ="+dto);
         m.put("dto", dto);

         return m;
         
		} catch (Exception e) {
            throw e;
        }
	}
	
	/**
	 * Method 'findAll'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView findAll(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			
			WhLocatingAreaDao dao = DaoFactory.createWhLocatingAreaDao();
			List<WhLocatingArea> dto = dao.findAll();
			
			return new ModelAndView( "1_setup/WhLocatingAreaList", "model", dto);
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}
	/*
	public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        java.lang.String locatingId = request.getParameter("locatingId");
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        String createdBy = (String)lu.getUserId();
       
        WhLocatingAreaDao dao = DaoFactory.createWhLocatingAreaDao();

        WhLocatingArea dto = dao.findByPrimaryKey(locatingId);
        if (dto != null) {
            
        }

        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("1_setup/WhLocatingAreaList", "model", m);
    }
	*/
	/**
	 * Method 'findWhereLocatingIdEquals'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView findWhereLocatingIdEquals(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			// parse parameters
			java.lang.String locatingId = request.getParameter("locatingId");
		
			// create the DAO class
			WhLocatingAreaDao dao = DaoFactory.createWhLocatingAreaDao();
		
			// execute the finder
			List<WhLocatingArea> dto = dao.findWhereLocatingIdEquals(locatingId);
		
			return new ModelAndView( "1_setup/WhLocatingAreaList", "model", dto );
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}

	/**
	 * Method 'findWhereLocatingAreaEquals'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView findWhereLocatingAreaEquals(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			// parse parameters
			java.lang.String locatingArea = request.getParameter("locatingArea");
		
			// create the DAO class
			WhLocatingAreaDao dao = DaoFactory.createWhLocatingAreaDao();
		
			// execute the finder
			List<WhLocatingArea> dto = dao.findWhereLocatingAreaEquals(locatingArea);
		
			return new ModelAndView( "1_setup/WhLocatingAreaList", "model", dto );
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}
	
	/**
	 * Method 'create'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Map map = new HashMap();
		map = this.getModelByPrimaryKey(request);
		map.put("mode", "create");		
		
		WhLocatingAreaDao dao = DaoFactory.createWhLocatingAreaDao();
		
		return new ModelAndView( "1_setup/WhLocatingAreaAdd", "model", map);
	}

	
	/**
	 * Method 'save'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{

      boolean isCreate = true;
      String strError = "";
      Date now = new Date();
      java.lang.String mode = request.getParameter("mode");
      WhLocatingArea dto = null;
      try {
          if (mode.equalsIgnoreCase("create")) {
              isCreate = true;
          } else {
              isCreate = false;
          }

          String locatingId = request.getParameter("locatingId");
          WhLocatingAreaDao dao = DaoFactory.createWhLocatingAreaDao();
          if (isCreate) {
        	  dto = new WhLocatingArea();
          } else {
              dto = dao.findByPrimaryKey(locatingId);
          }
          
          String locatingArea = request.getParameter("locatingArea");
          if (locatingArea.trim().isEmpty()) {
              strError += "Locating Area Cannot be Empty!" + AppConstant.EOL;
          }
          
          String locatingCondition = request.getParameter("locatingCondition");
          LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
          String userId = (String)lu.getUserId();
          String corpId = (String)lu.getCorpId();
          String whCode = (String)lu.getWhCode();
          
          dto.setLocatingArea(locatingArea);
          dto.setLocatingCondition(locatingCondition);
          dto.setUserId(userId);
          dto.setCorpId(corpId);
          dto.setWhCode(whCode);
       
          if (strError.length() > 0) {
              throw new Exception(strError);
          }

          if (isCreate) {
              dao.insert(dto);
          } 
          
          else {
              
              dao.update(dto.createPk(), dto);
          }
		 
          return new ModelAndView("1_setup/WhLocatingAreaView", "dto", dto);

      } catch (Exception e) {
    	  e.printStackTrace();
          String errorMsg = e.getMessage();
          HashMap m = this.getModelByPrimaryKey(request);
          m.put("mode", mode);
          m.put("msg", errorMsg);
          System.out.println("error2="+errorMsg);
          e.printStackTrace();
          if (isCreate) {
              return new ModelAndView("1_setup/WhLocatingAreaAdd", "model", m);
          } else {
              return new ModelAndView("1_setup/WhLocatingAreaEdit", "model", m);
          }
      }
      
  }



}

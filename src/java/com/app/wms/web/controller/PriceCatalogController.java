package com.app.wms.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.db.dao.PriceCatalogDao;
import com.app.wms.engine.db.dto.PriceCatalog;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.util.AppConstant;

public class PriceCatalogController extends MultiActionController {

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
                return new ModelAndView("1_setup/PriceCatalogEdit", "model", m);
            } else {

                m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/PriceCatalogList", "model", m);
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
            PriceCatalog p = new PriceCatalog();
            p.setCatalogCode(request.getParameter("catalogCode"));
            p.setCatalogName(request.getParameter("catalogName"));
           
            PriceCatalogDao dao = DaoFactory.createPriceCatalogDao();
            List<PriceCatalog> listSearchPage = dao.findPriceCatalogPaging(p,page);
            int total = 2000; 
            m.put("pricecatalog", listSearchPage);
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
		 PriceCatalogDao dao = DaoFactory.createPriceCatalogDao();
         PriceCatalog dto = new PriceCatalog();

         String mode = request.getParameter("mode");
         if (mode != null && mode.equals("edit")) {
        	 Integer id = Integer.parseInt(request.getParameter("id"));
             dto = dao.findByPrimaryKey(id);
            
         }
         if (dto.getCatalogCode() == null) {
        	 
        	 dto.setCatalogCode("");
        	 dto.setCatalogName("");
             dto.setIsActive("Y");
             dto.setIsDelete("N");
         }
         
         if (dto.getCatalogCode() != null || dto.getCatalogName() != null) {
        	 dto.setCatalogCode(dto.getCatalogCode());
        	 dto.setCatalogName(dto.getCatalogName());
             dto.setIsActive(dto.getIsActive());
             dto.setIsDelete(dto.getIsDelete());
         }
         //edit
         HashMap m = new HashMap();
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
			
			PriceCatalogDao dao = DaoFactory.createPriceCatalogDao();
			List<PriceCatalog> dto = dao.findAll();
			
			return new ModelAndView( "1_setup/PriceCatalogList", "model", dto);
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}
	
	public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        String createdBy = (String)lu.getUserId();
       
        PriceCatalogDao dao = DaoFactory.createPriceCatalogDao();
        
        Integer id = Integer.parseInt(request.getParameter("id"));
        PriceCatalog dto = dao.findByPrimaryKey(id);
        if (dto != null) {
            dto.setIsActive(AppConstant.STATUS_FALSE);
            dto.setUpdatedBy(createdBy);
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        }

        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("1_setup/PriceCatalogList", "model", m);
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
		
		PriceCatalogDao dao = DaoFactory.createPriceCatalogDao();
		
		return new ModelAndView( "1_setup/PriceCatalogAdd", "model", map);
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
      PriceCatalog dto = null;
      try {
          if (mode.equalsIgnoreCase("create")) {
              isCreate = true;
          } else {
              isCreate = false;
          }

          PriceCatalogDao dao = DaoFactory.createPriceCatalogDao();
          if (isCreate) {
        	  dto = new PriceCatalog();
          } else {
        	  Integer id = Integer.parseInt(request.getParameter("id"));
              dto = dao.findByPrimaryKey(id);
          }
          
          String catalogCode = request.getParameter("catalogCode");
          if (catalogCode.trim().isEmpty()) {
              strError += "Catalog Code Cannot be Empty!" + AppConstant.EOL;
          }

          String catalogName = request.getParameter("catalogName");
          if (catalogName.trim().isEmpty()) {
              strError += "Catalog Name Cannot be Empty!" + AppConstant.EOL;
          }
          
          LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
          String userId = (String)lu.getUserId();

          if (isCreate) {
              dto.setCreatedBy(userId);
              dto.setCreatedDate(now);
          }
          
          dto.setCatalogCode(catalogCode);
          dto.setCatalogName(catalogName);
          dto.setIsActive("Y");
          dto.setIsDelete("N");
          dto.setUpdatedBy(userId);
          dto.setUpdatedDate(new java.util.Date());

          if (strError.length() > 0) {
              throw new Exception(strError);
          }

          if (isCreate) {
              dao.insert(dto);
          } 
          
          else {
              dto.setUpdatedBy(userId);
              dto.setUpdatedDate(new java.util.Date());
              dao.update(dto.createPk(), dto);
          }
		 
          return new ModelAndView("1_setup/PriceCatalogView", "dto", dto);

      } catch (Exception e) {
    	  e.printStackTrace();
          String errorMsg = e.getMessage();
          HashMap m = this.getModelByPrimaryKey(request);
          m.put("mode", mode);
          m.put("msg", errorMsg);

          if (isCreate) {
              return new ModelAndView("1_setup/PriceCatalogAdd", "model", m);
          } else {
              return new ModelAndView("1_setup/PriceCatalogEdit", "model", m);
          }
      }
      
  }

}

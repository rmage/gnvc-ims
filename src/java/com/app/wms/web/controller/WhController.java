package com.app.wms.web.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.app.web.engine.search.WarehouseSearch;
import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.dto.*;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.util.DroplistModel;
import com.app.wms.web.util.AppConstant;

public class WhController extends MultiActionController
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
                System.out.println("m ="+m);
                return new ModelAndView("1_setup/WarehouseEdit", "model", m);
            } else {

                m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/WarehouseList", "model", m);
            }

        }
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}

	private HashMap searchAndPaging(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
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
            /*
            WarehouseSearch c = null;
            if (request.getParameter("btnViewAll") != null) {
                request.getSession().setAttribute("WarehouseSearch", null);
            } else if (request.getParameter("btnSearch") != null) {
                System.out.println("create new search session");
                c = new WarehouseSearch();
                c.setWhCode(request.getParameter("whCode"));
                c.setName(request.getParameter("name"));
                request.getSession().setAttribute("WarehouseSearch", c);
            } else if (request.getSession().getAttribute("WarehouseSearch") != null) {
                System.out.println("use previous search session");
                c = (WarehouseSearch) request.getSession().getAttribute("WarehouseSearch");
            } else {
                // no criteria
            }
			*/
            WarehouseSearch c = new WarehouseSearch();
            c = new WarehouseSearch();
            c.setWhCode(request.getParameter("whCode"));
            c.setName(request.getParameter("name"));
            request.getSession().setAttribute("WarehouseSearch", c);
            
            
            WhDao dao = DaoFactory.createWhDao();
            List<Wh> listSearchPage = dao.findWhPaging(c,page);

            int total = 2000; 
            m.put("wh", listSearchPage);
            m.put("totalRows", total);
            m.put("page", page);
            m.put("paging", paging);

            return m;

        } catch (Exception e) {
            throw e;
        }
    }

	private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
        try {
        	LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            String whCode = request.getParameter("whCode");
            WhDao dao = DaoFactory.createWhDao();
            Wh dto = null;

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                dto = dao.findByPrimaryKey(whCode);
            }

            if (dto == null) {
                dto = new Wh();
                dto.setCorpId("");
                dto.setCode("");
                dto.setName("");
                dto.setRegion("");
                dto.setIsActive(request.getParameter("isActive"));
                dto.setIsDelete("N");
            }
            
            CorporateDao corporateDao = DaoFactory.createCorporateDao();
            List<Corporate> corporates = corporateDao.findAll();
            CityDao cdao = DaoFactory.createCityDao();
            List<City> city = cdao.findAll();
            
            HashMap m = new HashMap();
            m.put("city", city);
            m.put("corporates", corporates);
            m.put("dto", dto);

            return m;

        } catch (Exception e) {
            throw e;
        }
    }
	
	public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        java.lang.String whCode = request.getParameter("whCode");

        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        String pcreatedBy = (String)lu.getUserId();
       
        WhDao dao = DaoFactory.createWhDao();
        Wh dto = dao.findByPrimaryKey(whCode);

        if (dto != null) {
            dto.setIsActive(AppConstant.STATUS_FALSE);
            dto.setUpdatedBy(pcreatedBy);
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        }

        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("1_setup/WarehouseList", "model", m);
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
			
			WhDao dao = DaoFactory.createWhDao();
		
			List<Wh> dto = dao.findAll();
		
			return new ModelAndView( "1_setup/WarehouseList", "result", dto );
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}

	/**
	 * Method 'findWhereWhCodeEquals'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView findWhereWhCodeEquals(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			// parse parameters
			java.lang.String pwhCode = request.getParameter("whCode");
		
			// create the DAO class
			WhDao dao = DaoFactory.createWhDao();
		
			// execute the finder
			List<Wh> dto = dao.findWhereWhCodeEquals(pwhCode);
		
			return new ModelAndView( "1_setup/WarehouseList", "result", dto );
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}

	/**
	 * Method 'findWhereNameEquals'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView findWhereNameEquals(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			// parse parameters
			java.lang.String pname = request.getParameter("name");
		
			// create the DAO class
			WhDao dao = DaoFactory.createWhDao();
		
			// execute the finder
			List<Wh> dto = dao.findWhereNameEquals(pname);
		
			return new ModelAndView( "1_setup/WarehouseList", "result", dto );
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
		HashMap m = this.getModelByPrimaryKey(request);
		m = this.getModelByPrimaryKey(request);
		
		CorporateDao corporateDao = DaoFactory.createCorporateDao();
        List<Corporate> corporates = corporateDao.findAll();
        
        CityDao dao = DaoFactory.createCityDao();
        List<City> city = dao.findAll();

        m.put("city", city);
        m.put("corporates", corporates);
		m.put("mode", "create");
		return new ModelAndView( "1_setup/WarehouseAdd", "model", m);
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
      Wh dto = null;
      try {
          if (mode.equalsIgnoreCase("create")) {
              isCreate = true;
          } else {
              isCreate = false;
          }

          String whCode = request.getParameter("whCode");
          WhDao dao = DaoFactory.createWhDao();
          if (isCreate) {
              dto = new Wh();
          } else {
              dto = dao.findByPrimaryKey(whCode);
          }
          /*
          String code = request.getParameter("code");
          if (code.trim().isEmpty()) {
              strError += "Warehouse Code Cannot be Empty!" + AppConstant.EOL;
          }
         
          List<Wh> tmpWh = dao.findWhereCodeEquals(code);
	  	  if ((isCreate && tmpWh != null && tmpWh.size() > 0) || (!isCreate && tmpWh != null && tmpWh.size() > 0 && !tmpWh.get(0).getWhCode().equals(whCode))) {
	  		  strError += "Warehouse Code Already Existed!" + AppConstant.EOL;
	  	  }
          */
          java.lang.String pname = request.getParameter("name");
          if (pname.trim().isEmpty()) {
              strError += "Name Cannot be Empty!" + AppConstant.EOL;
          }
          
          java.lang.String region = request.getParameter("region");
          /*
          if (region.trim().isEmpty()) {
              strError += "Region Cannot be Empty!" + AppConstant.EOL;
          }
          */
          String corpId = request.getParameter("corpId");

          java.lang.String pisActive = request.getParameter("isActive");
          LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
          String userId = (String)lu.getUserId();

          if (isCreate) {
              dto.setCreatedBy(userId);
              dto.setCreatedDate(now);
          }

          dto.setCode("");
          dto.setName(pname);
          dto.setRegion(region);
          dto.setCorpId(corpId);
          dto.setIsActive(pisActive);
          dto.setIsDelete("N");
          dto.setUpdatedBy(userId);
          dto.setUpdatedDate(now);
          
          if (strError.length() > 0) {
              throw new Exception(strError);
          }
         
          if (isCreate) {
//        	  dto.setWhCode(code);
              dao.insert(dto);
          } else {     	 
        	  //dto.setIsActive(pisActive);
        	  dto.setUpdatedBy(userId);
        	  dto.setUpdatedDate(new java.util.Date());
              dao.update(dto.createPk(), dto);
              
          }
         // System.out.println("dto ="+dto);
          return new ModelAndView("1_setup/WarehouseView", "dto", dto);
      /*
      } catch (org.springframework.dao.DataIntegrityViolationException e) {
          String errorMsg = "Unique Key Constraint [Code, Name]!" + AppConstant.EOL;
          HashMap m = this.getModelByPrimaryKey(request);
          m.put("dto", dto);
          m.put("mode", mode);
          m.put("msg", errorMsg);
          System.out.println(errorMsg);
          if (isCreate) {
              return new ModelAndView("1_setup/WarehouseAdd", "model", m);
          } else {
              return new ModelAndView("1_setup/WarehouseEdit", "model", m);
          }
     */
      } catch (Exception e) {
    	  e.printStackTrace();
          String errorMsg = e.getMessage();
          HashMap m = this.getModelByPrimaryKey(request);
          m.put("mode", mode);
          m.put("msg", errorMsg);
          if (isCreate) {
              return new ModelAndView("1_setup/WarehouseAdd", "model", m);
          } else {
              return new ModelAndView("1_setup/WarehouseEdit", "model", m);
          }
      }

  }

}

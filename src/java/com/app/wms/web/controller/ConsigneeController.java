package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.app.wms.engine.db.dao.ConsigneeDao;
import com.app.wms.engine.db.dto.*;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.*;
import com.app.wms.web.util.AppConstant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsigneeController extends MultiActionController
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
                return new ModelAndView("1_setup/ConsigneeEdit", "model", m);
            } else {

                m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/ConsigneeList", "model", m);
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
            Consignee c = new Consignee();
            c.setCorpId(lu.getCorpId());
            c.setWhCode(lu.getWhCode());
            c.setConsigneeCode(request.getParameter("consigneeCode"));
            c.setConsigneeName(request.getParameter("consigneeName"));
            
            ConsigneeDao dao = DaoFactory.createConsigneeDao();
            List<Consignee> listSearchPage = dao.findConsigneePaging(c,page);
            System.out.println("listSearchPage ="+listSearchPage);
            int total = 2000; 
            m.put("consignee", listSearchPage);
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
		 String consigneeCode = request.getParameter("consigneeCode");
         System.out.println("consigneeCode="+consigneeCode);
		 ConsigneeDao dao = DaoFactory.createConsigneeDao();
         Consignee dto = null;

         String mode = request.getParameter("mode");
         if (mode != null && mode.equals("edit")) {
             dto = dao.findByPrimaryKey(consigneeCode);
            // dto.setIsActive("Y");
         }
         if (dto == null) {
        	 dto = new Consignee();
        	 dto.setConsigneeCode("");
        	 dto.setConsigneeName("");
        	 dto.setAddress1("");
        	 dto.setAddress2("");
        	 dto.setAddress3("");
        	 dto.setConsigneePhone("");
             dto.setIsActive("Y");
             dto.setIsDelete("N");
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
			
			ConsigneeDao dao = DaoFactory.createConsigneeDao();
			List<Consignee> dto = dao.findAll();
			
			return new ModelAndView( "1_setup/ConsigneeList", "model", dto);
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}
	
	public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        java.lang.String consigneeCode = request.getParameter("consigneeCode");
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        String createdBy = (String)lu.getUserId();
       
        ConsigneeDao dao = DaoFactory.createConsigneeDao();

        Consignee dto = dao.findByPrimaryKey(consigneeCode);
        if (dto != null) {
            dto.setIsActive(AppConstant.STATUS_FALSE);
            dto.setUpdatedBy(createdBy);
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        }

        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("1_setup/ConsigneeList", "model", m);
    }
	
	/**
	 * Method 'findWhereConsigneeCodeEquals'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView findWhereConsigneeCodeEquals(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			// parse parameters
			java.lang.String consigneeCode = request.getParameter("consigneeCode");
		
			// create the DAO class
			ConsigneeDao dao = DaoFactory.createConsigneeDao();
		
			// execute the finder
			List<Consignee> dto = dao.findWhereConsigneeCodeEquals(consigneeCode);
		
			return new ModelAndView( "1_setup/ConsigneeList", "model", dto );
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
			java.lang.String consigneeName = request.getParameter("consigneeName");
		
			// create the DAO class
			ConsigneeDao dao = DaoFactory.createConsigneeDao();
		
			// execute the finder
			List<Consignee> dto = dao.findWhereConsigneeNameEquals(consigneeName);
		
			return new ModelAndView( "1_setup/ConsigneeList", "model", dto );
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
		
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		
		return new ModelAndView( "1_setup/ConsigneeAdd", "model", map);
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
      Consignee dto = null;
      try {
          if (mode.equalsIgnoreCase("create")) {
              isCreate = true;
          } else {
              isCreate = false;
          }

          String consigneeCode = request.getParameter("consigneeCode");
          ConsigneeDao dao = DaoFactory.createConsigneeDao();
          if (isCreate) {
        	  dto = new Consignee();
          } else {
              dto = dao.findByPrimaryKey(consigneeCode);
          }
          
          String consigneeName = request.getParameter("consigneeName");
          if (consigneeName.trim().isEmpty()) {
              strError += "Consignee Name Cannot be Empty!" + AppConstant.EOL;
          }
          
          String consigneePIC = request.getParameter("consigneePIC");
          if (consigneePIC.trim().isEmpty()) {
              strError += "Consignee PIC Cannot be Empty!" + AppConstant.EOL;
          }
          
          String address1 = request.getParameter("address1");
          if (address1.trim().isEmpty()) {
              strError += "Address1 Cannot be Empty!" + AppConstant.EOL;
          }
          
          String address2 = request.getParameter("address2");
          String address3 = request.getParameter("address3");
          
          String consigneePhone = request.getParameter("consigneePhone");
          if (consigneePhone.trim().isEmpty()) {
              strError += "Consignee Phone Cannot be Empty!" + AppConstant.EOL;
          }
          
          java.lang.String pisActive = request.getParameter("isActive");
          LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
          String userId = (String)lu.getUserId();
          String corpId = (String)lu.getCorpId();
          String whCode = (String)lu.getWhCode();
          
          if (isCreate) {
              dto.setCreatedBy(userId);
              dto.setCreatedDate(now);
          }
          
          dto.setConsigneeName(consigneeName);
          dto.setAddress1(address1);
          dto.setAddress2(address2);
          dto.setAddress3(address3);
          dto.setConsigneePIC(consigneePIC);
          dto.setConsigneePhone(consigneePhone);
          dto.setIsActive(pisActive);
          dto.setIsDelete("N");
          dto.setUserId(userId);
          dto.setCorpId(corpId);
          dto.setWhCode(whCode);
          
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
		 
          return new ModelAndView("1_setup/ConsigneeView", "dto", dto);

      } catch (Exception e) {
    	  e.printStackTrace();
          String errorMsg = e.getMessage();
          HashMap m = this.getModelByPrimaryKey(request);
          m.put("mode", mode);
          m.put("msg", errorMsg);
          System.out.println("error2="+errorMsg);
          e.printStackTrace();
          if (isCreate) {
              return new ModelAndView("1_setup/ConsigneeAdd", "model", m);
          } else {
              return new ModelAndView("1_setup/ConsigneeEdit", "model", m);
          }
      }
      
  }

}

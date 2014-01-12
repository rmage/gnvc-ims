package com.app.wms.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.app.wms.engine.db.dao.DepartmentDao;
import com.app.wms.engine.db.dto.Department;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.util.AppConstant;

public class DepartmentController extends MultiActionController {


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
                return new ModelAndView("1_setup/DepartmentEdit", "model", m);
            } else {

                m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/DepartmentList", "model", m);
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

            Department d = new Department();
            d.setDepartmentCode(request.getParameter("departmentCode"));
            d.setDepartmentName(request.getParameter("departmentName"));
           
            DepartmentDao dao = DaoFactory.createDepartmentDao();
            List<Department> listSearchPage = dao.findDepartmentPaging(d,page);
            int total = 2000; 
            m.put("department", listSearchPage);
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
		 DepartmentDao dao = DaoFactory.createDepartmentDao();
         Department dto = new Department();

         String mode = request.getParameter("mode");
         if (mode != null && mode.equals("edit")) {
        	 Integer id = Integer.parseInt(request.getParameter("id"));
             dto = dao.findByPrimaryKey(id);
            
         }
         if (dto.getDepartmentCode() == null) {
        	 
        	 dto.setDepartmentCode("");
        	 dto.setDepartmentName("");
             dto.setIsActive("Y");
             dto.setIsDelete("N");
         }
         
         if (dto.getDepartmentCode() != null || dto.getDepartmentName() != null) {
        	 dto.setDepartmentCode(dto.getDepartmentCode());
        	 dto.setDepartmentName(dto.getDepartmentName());
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
			
			DepartmentDao dao = DaoFactory.createDepartmentDao();
			List<Department> dto = dao.findAll();
			
			return new ModelAndView( "1_setup/DepartmentList", "model", dto);
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}
	
	public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        String createdBy = "";
        if (lu == null) {
			HashMap m = new HashMap();
            String msg = "You haven't login or your session has been expired! Please do login again";
            m.put("msg", msg);
            return new ModelAndView("login", "model", m);
        }else{
           createdBy = (String)lu.getUserId();
        }
        
        DepartmentDao dao = DaoFactory.createDepartmentDao();
        
        Integer id = Integer.parseInt(request.getParameter("id"));
        Department dto = dao.findByPrimaryKey(id);
        if (dto != null) {
            dto.setIsActive(AppConstant.STATUS_FALSE);
            dto.setIsDelete("Y");
            dto.setUpdatedBy(createdBy);
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        }

        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("1_setup/DepartmentList", "model", m);
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
		
		DepartmentDao dao = DaoFactory.createDepartmentDao();
		
		return new ModelAndView( "1_setup/DepartmentAdd", "model", map);
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
      Department dto = null;
      try {
          if (mode.equalsIgnoreCase("create")) {
              isCreate = true;
          } else {
              isCreate = false;
          }

          DepartmentDao dao = DaoFactory.createDepartmentDao();
          if (isCreate) {
        	  dto = new Department();
          } else {
        	  Integer id = Integer.parseInt(request.getParameter("id"));
              dto = dao.findByPrimaryKey(id);
          }
          
          String departmentCode = request.getParameter("departmentCode");
          String departmentName = request.getParameter("departmentName");
          List<Department> tmp = dao.findWhereDepartmentCodeEquals(departmentCode);
          
	  	  if ((isCreate && tmp != null && tmp.size() > 0) || (!isCreate && tmp != null && tmp.size() > 0 && !tmp.get(0).getDepartmentCode().equals(departmentCode))) {
	  		  strError += "Department code already exists. Please try a different values" + AppConstant.EOL;
	  	  }
	  	  
          LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
          String userId = "";
          if (lu == null) {
  			  HashMap m = new HashMap();
              String msg = "You haven't login or your session has been expired! Please do login again";
              m.put("msg", msg);
              return new ModelAndView("login", "model", m);
          }else{
        	  userId = (String)lu.getUserId();
          }
          
          if (isCreate) {
              dto.setCreatedBy(userId);
              dto.setCreatedDate(now);
          }
          
          dto.setDepartmentCode(departmentCode);
          dto.setDepartmentName(departmentName);
          dto.setIsActive(request.getParameter("isActive"));
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
		 
          return new ModelAndView("1_setup/DepartmentView", "dto", dto);

      } catch (Exception e) {
    	  e.printStackTrace();
          String errorMsg = e.getMessage();
          HashMap m = this.getModelByPrimaryKey(request);
          m.put("mode", mode);
          m.put("msg", errorMsg);

          if (isCreate) {
              return new ModelAndView("1_setup/DepartmentAdd", "model", m);
          } else {
              return new ModelAndView("1_setup/DepartmentEdit", "model", m);
          }
      }
      
  }
	
}
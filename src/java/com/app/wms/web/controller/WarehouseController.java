package com.app.wms.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.web.engine.search.WarehouseSearch;
import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dao.ProductCategoryDao;
import com.app.wms.engine.db.dao.WarehouseDao;
import com.app.wms.engine.db.dao.WhDao;
import com.app.wms.engine.db.dto.ProductCategory;
import com.app.wms.engine.db.dto.Warehouse;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.util.AppConstant;

public class WarehouseController extends MultiActionController {

	/**
	 * Method 'findByPrimaryKey'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */

	 public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
     try {
            
            HashMap m = null;
            final String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                m = this.getModelByPrimaryKey(request);
                m.put("mode", "edit");
                return new ModelAndView("1_setup/WarehouseEdit", "model", m);
            } else {
            	WarehouseDao dao = DaoFactory.createWarehouseDao();
            	List<Warehouse> list = dao.findAll();
            	m = this.getModelByPrimaryKey(request);
            	m.put("warehouse", list);
               // m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/WarehouseList", "model", m);
            }

        }
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}

    }
	 
	 private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
     try {
           
            WarehouseDao dao = DaoFactory.createWarehouseDao();
            Warehouse dto = null;
            
            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
            	Integer id = Integer.parseInt(request.getParameter("id"));
                dto = dao.findByPrimaryKey(id);
            }

            if (dto == null) {
                dto = new Warehouse();
                dto.setWhCode("");
                dto.setWhName("");
                dto.setCategoryName("");
            }

            HashMap m = new HashMap();
            
            ProductCategoryDao daoCategory = DaoFactory.createProductCategoryDao();
            List<ProductCategory> dropListCategory = daoCategory.findAll();
            m.put("dropListCategory", dropListCategory);
            
            m.put("dto", dto);
            return m;
        } catch (Exception e) {
            throw e;
        }
    }
	
	 
	private HashMap searchAndPaging(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
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
            m.put("warehouse", listSearchPage);
            m.put("totalRows", total);
            m.put("page", page);
            m.put("paging", paging);

            return m;

        } catch (Exception e) {
            throw e;
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
		m.put("mode", "create");
		
		return new ModelAndView("1_setup/WarehouseAdd", "model", m);
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
		String mode = request.getParameter("mode");
		Warehouse dto = null;
		try{
			if (mode.equalsIgnoreCase("create")) {
	              isCreate = true;
	        } else {
	              isCreate = false;
	        }
		  	
			WarehouseDao dao = DaoFactory.createWarehouseDao();
			if(isCreate){
				dto = new Warehouse();
			}else{
				Integer id = Integer.parseInt(request.getParameter("id"));
				dto = dao.findByPrimaryKey(id);
			}
			
			String code = request.getParameter("code");
			String name = request.getParameter("name");
			String categoryName = request.getParameter("categoryName");
			
			List<Warehouse> tmp = dao.findWhereWhCodeEquals(code);
			if ((isCreate && tmp != null && tmp.size() > 0) || (!isCreate && tmp != null && tmp.size() > 0 && !tmp.get(0).getWhCode().equals(code))) {
		  		  strError += "Warehouse code already exists. Please try a different values" + AppConstant.EOL;
		  	}
			
			if(isCreate){
				dto.setWhCode(code);
				dto.setWhName(name);
				dto.setCategoryName(categoryName);
				dto.setIsActive(request.getParameter("isActive"));
			}
			
			dto.setWhCode(code);
			dto.setWhName(name);
			dto.setCategoryName(categoryName);
			dto.setIsActive(request.getParameter("isActive"));
			
			if (strError.length() > 0) {
	              throw new Exception(strError);
	        }
			
			if(isCreate){
				dao.insert(dto);
			}else{
				dao.update(dto.createPk(), dto);
			}
			
			return new ModelAndView( "1_setup/WarehouseView", "dto", dto );
		}
	    catch (Exception e){
	    	e.printStackTrace();
	    	String errorMsg = e.getMessage();
	    	HashMap m = new HashMap();
	    	m.put("mode", mode);
	    	m.put("msg", errorMsg);
	    	
	    	if(isCreate){
	    		return new ModelAndView( "1_setup/WarehouseAdd", "model", m );
	    	}else{
	    		return new ModelAndView( "1_setup/WarehouseEdit", "model", m );
	    	}
	    	
	    }

	}
	
	
	public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer id = Integer.parseInt(request.getParameter("id"));
        
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        String pcreatedBy = "";
        if (lu == null) {
			HashMap m = new HashMap();
            String msg = "You haven't login or your session has been expired! Please do login again";
            m.put("msg", msg);
            return new ModelAndView("login", "model", m);
        }else{
        	pcreatedBy = lu.getUserId();
        }

        WarehouseDao dao = DaoFactory.createWarehouseDao();
        Warehouse dto = dao.findByPrimaryKey(id);

        if (dto != null) {
        	dto.setIsActive(AppConstant.STATUS_FALSE);
            dto.setUpdatedBy(pcreatedBy);
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        }

        List<Warehouse> list = dao.findAll();

        HashMap m = new HashMap();

        m.put("warehouse", list);
        m.put("totalRows", 0); 

        return new ModelAndView("1_setup/WarehouseList", "model", m);
    }

}

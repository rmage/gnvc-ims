package com.app.wms.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.SupplierPk;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.SupplierDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.util.AppConstant;
import java.io.IOException;
import java.io.PrintWriter;

public class SupplierController  extends MultiActionController {

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
                return new ModelAndView("1_setup/SupplierEdit", "model", m);
            } else {
                m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/SupplierList", "model", m);
            }
        } catch (Throwable e) {
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

            Supplier d = new Supplier();
            d.setSupplierCode(request.getParameter("supplierCode"));
            d.setSupplierName(request.getParameter("supplierName"));
           
            SupplierDao dao = DaoFactory.createSupplierDao();
            List<Supplier> listSearchPage = dao.findSupplierPaging(d,page);
            int total = 2000; 
            m.put("supplier", listSearchPage);
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
            SupplierDao dao = DaoFactory.createSupplierDao();
            Supplier dto = new Supplier();

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                dto = dao.findByPrimaryKey(id);
            }
            if (dto.getSupplierCode() == null) {	 
                dto.setSupplierCode("");
                dto.setSupplierName("");
                dto.setIsActive("Y");
                dto.setIsDelete("N");
            }
         
            if (dto.getSupplierCode() != null || dto.getSupplierName() != null) {
                dto.setSupplierCode(dto.getSupplierCode());
                dto.setSupplierName(dto.getSupplierName());
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
			
			SupplierDao dao = DaoFactory.createSupplierDao();
			List<Supplier> dto = dao.findAll();
			
			return new ModelAndView( "1_setup/SupplierList", "model", dto);
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
       
        SupplierDao dao = DaoFactory.createSupplierDao();
        Integer id = Integer.parseInt(request.getParameter("id"));
        Supplier dto = dao.findByPrimaryKey(id);
        if (dto != null) {
            dto.setIsActive(AppConstant.STATUS_FALSE);
            dto.setIsDelete("Y");
            dto.setUpdatedBy(createdBy);
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        }

        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("1_setup/SupplierList", "model", m);
    }
	
	/**
	 * Method 'create'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map map = new HashMap();
        map = this.getModelByPrimaryKey(request);
        map.put("mode", "create");		

//        SupplierDao dao = DaoFactory.createSupplierDao();

        return new ModelAndView( "1_setup/SupplierAdd", "model", map);
    }

	
	/**
	 * Method 'save'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception {

        boolean isCreate = true;
        String strError = "";
        Date now = new Date();
        java.lang.String mode = request.getParameter("mode");
        Supplier dto = null;
        try {
            if (mode.equalsIgnoreCase("create")) {
                isCreate = true;
            } else {
                isCreate = false;
            }

            SupplierDao dao = DaoFactory.createSupplierDao();
            if (isCreate) {
                dto = new Supplier();
            } else {
                Integer id = Integer.parseInt(request.getParameter("id"));
                dto = dao.findByPrimaryKey(id);
            }

            String supplierCode = request.getParameter("supplierCode");
            int lengthSupplierCode = supplierCode.length();
            int i = 3;
            if(lengthSupplierCode < i)
                strError += "Must entry more than equals 3 character for supplier code" + AppConstant.EOL;

            String supplierName = request.getParameter("supplierName");
            String supplierAddress = request.getParameter("supplierAddress");
            List<Supplier> tmp = dao.findWhereSupplierCodeEquals(supplierCode);

            if ((isCreate && tmp != null && tmp.size() > 0) || (!isCreate && tmp != null && tmp.size() > 0 && !tmp.get(0).getSupplierCode().equals(supplierCode))) {
                strError += "Supplier code already exists. Please try a different values" + AppConstant.EOL;
            }

            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            String userId = "";
            if (lu == null) {
                HashMap m = new HashMap();
                String msg = "You haven't login or your session has been expired! Please do login again";
                m.put("msg", msg);
                return new ModelAndView("login", "model", m);
            } else{
                userId = (String)lu.getUserId();
            }
            String telephone = request.getParameter("telephone");
            String fax = request.getParameter("fax");
            String email = request.getParameter("email");
            String contactPerson = request.getParameter("contactPerson");

            if (isCreate) {
                dto.setCreatedBy(userId);
                dto.setCreatedDate(now);
            }

            dto.setSupplierCode(supplierCode);
            dto.setSupplierName(supplierName);
            dto.setSupplierAddress(supplierAddress);
            dto.setTelephone(telephone);
            dto.setFax(fax);
            dto.setEmail(email);
            dto.setContactPerson(contactPerson);
            dto.setIsActive(request.getParameter("isActive"));
            dto.setIsDelete("N");
            dto.setUpdatedBy(userId);
            dto.setUpdatedDate(new java.util.Date());

            if (strError.length() > 0) {
                throw new Exception(strError);
            }

            if (isCreate) {
                SupplierPk sp = dao.insert(dto);
                dto.setId(sp.getId());
            } else {
                dto.setUpdatedBy(userId);
                dto.setUpdatedDate(new java.util.Date());
                dao.update(dto.createPk(), dto);
            }

            return new ModelAndView("1_setup/SupplierView", "dto", dto);
        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = e.getMessage();
            HashMap m = this.getModelByPrimaryKey(request);
            m.put("mode", mode);
            m.put("msg", errorMsg);

            if (isCreate) {
                return new ModelAndView("1_setup/SupplierAdd", "model", m);
            } else {
                return new ModelAndView("1_setup/SupplierEdit", "model", m);
            }
        }
      
    }
    
    public void getUnique(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, SupplierDaoException {

        PrintWriter pw = response.getWriter();
        String uniCode = request.getParameter("term");
        SupplierDao supplierDao = DaoFactory.createSupplierDao();
        List<Supplier> sp = supplierDao.findWhereSupplierCodeEquals(uniCode);
        if (sp.isEmpty()) {
            pw.print("{\"status\": false}");
        } else {
            pw.print("{\"status\": true}");
        }
    }
}

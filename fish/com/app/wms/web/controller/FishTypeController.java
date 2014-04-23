package com.app.wms.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.db.dao.FishTypeDao;
import com.app.wms.engine.db.dto.FishType;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;

public class FishTypeController extends MultiActionController 
{

	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
		return new ModelAndView("1_setup/FishTypeList", "model", modelMap);
	}
	
	private HashMap<String, Object> searchAndPaging(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			Integer page = 1;
            Integer paging = 10;
            Integer offset = 1;
            
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
                offset = (page - 1) * paging + 1;
            }
            if (request.getParameter("paging") != null) {
                paging = Integer.parseInt(request.getParameter("paging"));
            }
            
            FishTypeDao dao = DaoFactory.createFishTypeDao();
            List<FishType> fishTypes = null;
            
            if(request.getParameter("search") != null) {
            	String typeCode = request.getParameter("typeCode");
            	fishTypes = dao.searchAndPaging(typeCode, paging, offset);
            	String querySearch = "&search=true&typeCode="+typeCode;
            	modelMap.put("querySearch", querySearch);
            	modelMap.put("queryTypeCode", typeCode);
            }
            else {
                fishTypes = dao.findAllAndPaging(paging, offset);
            }
            
            modelMap.put("fishTypes", fishTypes);
            modelMap.put("totalRows", 2000);
            modelMap.put("page", page);
            modelMap.put("paging", paging);
		}
		catch (Exception e) {
			throw e;
		}
		
		return modelMap;
	}
	
	public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response)throws Exception {
		int id = Integer.valueOf(request.getParameter("id"));
        FishTypeDao dao = DaoFactory.createFishTypeDao();
        dao.delete(id);
        
		HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
		return new ModelAndView("1_setup/FishTypeList", "model", modelMap);
	}
	
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		
		modelMap.put("mode", "create");
		return new ModelAndView("1_setup/FishTypeAdd", "model", modelMap);
	}
    
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.valueOf(request.getParameter("id"));
        FishTypeDao dao = DaoFactory.createFishTypeDao();
        FishType dto = dao.findByPrimaryKey(id);
        
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("dto", dto);
        modelMap.put("mode", "edit");
        
        return new ModelAndView("1_setup/FishTypeEdit", "model", modelMap);
    }
	
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginUser user = (LoginUser) request.getSession().getAttribute("user");
        String mode = request.getParameter("mode");
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        
        if (user == null) {
            String msg = "You haven't login or your session has been expired! Please do login again";
            modelMap.put("msg", msg);
            return new ModelAndView("login", "model", modelMap);
        }else{
        	String userId = "";
        	userId = (String)user.getUserId();
        	
        	String code = request.getParameter("code");
        	String description = request.getParameter("description");
        	Date createdDate = new Date();
        	String createdBy = userId;
        	String isActive = "Y";
        	String isDelete = "N";
        	
        	FishType dto = new FishType();
        	dto.setCode(code);
        	dto.setDescription(description);
        	dto.setIsActive(isActive);
        	dto.setIsDelete(isDelete);
        	
        	FishTypeDao dao = DaoFactory.createFishTypeDao();
            if(mode.equalsIgnoreCase("edit")) {
                int id = Integer.valueOf(request.getParameter("id"));
                dto.setUpdatedDate(new Date());
                dto.setUpdatedBy(user.getUserId());
                dao.update(id, dto);
            }
            else {
                dto.setCreatedDate(createdDate);
                dto.setCreatedBy(createdBy);
            
                int id = dao.insert(dto);
                dto.setId(id);
            }
            
        	return new ModelAndView("1_setup/FishTypeView", "dto", dto);
        }
    }
    public void getUnique (HttpServletRequest request, HttpServletResponse response)
        throws IOException{
                
        PrintWriter pw = response.getWriter();
        String uniCode = request.getParameter("term");
        System.out.println("term: "+uniCode);

        FishTypeDao fishtypeDao = DaoFactory.createFishTypeDao();

        //              pw.print("[");
        List<FishType> cr = fishtypeDao.findByCode(uniCode);
        System.out.println("data: "+cr);
          if(cr.isEmpty()) {
              pw.print("{\"status\": false}");
          }else{
              pw.print("{\"status\": true}");
          }
        //                pw.print("]");
              
    }
}

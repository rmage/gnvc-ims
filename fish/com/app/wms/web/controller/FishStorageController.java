package com.app.wms.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.db.dao.FishStorageDao;
import com.app.wms.engine.db.dto.FishStorage;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishStorageController extends MultiActionController 
{

	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
		return new ModelAndView("1_setup/FishStorageList", "model", modelMap);
	}
	
	private HashMap<String, Object> searchAndPaging(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
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
            
            FishStorageDao dao = DaoFactory.createFishStorageDao();
            List<FishStorage> fishStorageList = dao.findAll();
            modelMap.put("fishStorages", fishStorageList);
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
		return null;
	}
	
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		FishStorage dto = new FishStorage();
		
		modelMap.put("mode", "create");
		modelMap.put("dto", dto);
		
		return new ModelAndView("1_setup/FishStorageAdd", "model", modelMap);
	}
	
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
		LoginUser user = (LoginUser) request.getSession().getAttribute("user");
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
        	
        	FishStorage dto = new FishStorage();
        	dto.setCode(code);
        	dto.setDescription(description);
        	dto.setCreatedDate(createdDate);
        	dto.setCreatedBy(createdBy);
        	dto.setIsActive(isActive);
        	dto.setIsDelete(isDelete);
        	
        	FishStorageDao dao = DaoFactory.createFishStorageDao();
        	int id = dao.insert(dto);
        	
        	dto.setId(id);
        	return new ModelAndView("1_setup/FishStorageView", "dto", dto);
        }
	}
}

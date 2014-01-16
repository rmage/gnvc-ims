package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.FishStorageDao;
import com.app.wms.engine.db.dto.FishStorage;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FishStorageController extends MultiActionController 
{

	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
		return new ModelAndView("1_setup/FishStorageList", "model", modelMap);
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
            
            FishStorageDao dao = DaoFactory.createFishStorageDao();
            List<FishStorage> fishStorageList = null;
            
            if(request.getParameter("search") != null) {
            	String storageCode = request.getParameter("storageCode");
            	fishStorageList = dao.searchAndPaging(storageCode, paging, offset);
            	String querySearch = "&search=true&storageCode="+storageCode;
            	modelMap.put("querySearch", querySearch);
            	modelMap.put("queryStorageCode", storageCode);
            }
            else {
                fishStorageList = dao.findAllAndPaging(paging, offset);
            }
            
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
		int id = Integer.valueOf(request.getParameter("id"));
        FishStorageDao dao = DaoFactory.createFishStorageDao();
        dao.delete(id);
        
        HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
		return new ModelAndView("1_setup/FishStorageList", "model", modelMap);
	}
    
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.valueOf(request.getParameter("id"));
        FishStorageDao dao = DaoFactory.createFishStorageDao();
        FishStorage dto = dao.findByPrimaryKey(id);
        
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("dto", dto);
        modelMap.put("mode", "edit");
        
        return new ModelAndView("1_setup/FishStorageEdit", "model", modelMap);
    }
	
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		FishStorage dto = new FishStorage();
		
		modelMap.put("mode", "create");
		modelMap.put("dto", dto);
		
		return new ModelAndView("1_setup/FishStorageAdd", "model", modelMap);
	}
	
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        
		LoginUser user = (LoginUser) request.getSession().getAttribute("user");
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        String mode = request.getParameter("mode");
        
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
        	dto.setIsActive(isActive);
        	dto.setIsDelete(isDelete);
            
            FishStorageDao dao = DaoFactory.createFishStorageDao();
            
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
            
        	return new ModelAndView("1_setup/FishStorageView", "dto", dto);
        }
	}
}

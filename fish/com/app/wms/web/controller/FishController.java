package com.app.wms.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.db.dao.FishDao;
import com.app.wms.engine.db.dao.FishTypeDao;
import com.app.wms.engine.db.dao.FishWeightTypeDao;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishType;
import com.app.wms.engine.db.dto.FishWeightType;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishController extends MultiActionController {

	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
		return new ModelAndView("1_setup/FishList", "model", modelMap);
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

            FishDao dao = DaoFactory.createFishDao();
            List<Fish> fishList = null;
            
            if(request.getParameter("search") != null) {
            	String fishCode = request.getParameter("fishCode");
            	fishList = dao.searchAndPaging(fishCode, paging, offset);
            	String querySearch = "&search=true&fishCode="+fishCode;
            	modelMap.put("querySearch", querySearch);
            	modelMap.put("queryFishCode", fishCode);
            }
            else {
                fishList = dao.findAllAndPaging(paging, offset);
            }
            
            modelMap.put("fishes", fishList);
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
        FishDao dao = DaoFactory.createFishDao();
        dao.delete(id);
        
		HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
		return new ModelAndView("1_setup/FishList", "model", modelMap);
	}
	
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		
		FishTypeDao fishTypeDao = DaoFactory.createFishTypeDao();
		List<FishType> fishTypes = fishTypeDao.findAllActive();
		
		FishWeightTypeDao weightTypeDao = DaoFactory.createFishWeightTypeDao();
		List<FishWeightType> weightTypes = weightTypeDao.findAllActive();
		
		modelMap.put("mode", "create");
		modelMap.put("fishTypes", fishTypes);
		modelMap.put("weightTypes", weightTypes);
		return new ModelAndView("1_setup/FishAdd", "model", modelMap);
	}
	
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        
		LoginUser user = (LoginUser) request.getSession().getAttribute("user");
                
        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        if (user == null) {
            String msg = "You haven't login or your session has been expired! Please do login again";
            modelMap.put("msg", msg);
            return new ModelAndView("login", "model", modelMap);
        }else{
            String userId = (String)user.getUserId();
            String mode = request.getParameter("mode");
            
            Integer fishTypeId = Integer.valueOf(request.getParameter("fishTypeId"));
            Integer fishWeightTypeId = Integer.valueOf(request.getParameter("fishWeightTypeId"));
            String code = request.getParameter("code");

            Fish dto = new Fish();
            dto.setFishTypeId(fishTypeId);
            dto.setFishWeightTypeId(fishWeightTypeId);
            dto.setCode(code);
            dto.setIsActive("Y");
            dto.setIsDelete("N");

            FishDao dao = DaoFactory.createFishDao();
            
            if(mode.equalsIgnoreCase("edit")) {
                int id = Integer.valueOf(request.getParameter("id"));
                dto.setUpdatedDate(new Date());
                dto.setUpdatedBy(user.getUserId());
                dao.update(id, dto);
            }
            else {
                dto.setCreatedDate(new Date());
                dto.setCreatedBy(userId);
                
                int id = dao.insert(dto);
                dto.setId(id);   
            }

            try {
                FishTypeDao fishTypeDao = DaoFactory.createFishTypeDao();
                FishType fishType = fishTypeDao.findByPrimaryKey(dto.getFishTypeId());
                dto.setFishType(fishType);

                FishWeightTypeDao weightTypeDao = DaoFactory.createFishWeightTypeDao();
                FishWeightType weightType = weightTypeDao.findByPrimaryKey(dto.getFishWeightTypeId());
                dto.setFishWeightType(weightType);
            }
            catch(DaoException e) {
                e.printStackTrace();
            }   

            return new ModelAndView("1_setup/FishView", "dto", dto);
        }
    }
    
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        FishDao dao = DaoFactory.createFishDao();
        Fish dto = dao.findByPrimaryKey(id);
        
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        FishTypeDao fishTypeDao = DaoFactory.createFishTypeDao();
		List<FishType> fishTypes = fishTypeDao.findAllActive();
		
		FishWeightTypeDao weightTypeDao = DaoFactory.createFishWeightTypeDao();
		List<FishWeightType> weightTypes = weightTypeDao.findAllActive();
		
		modelMap.put("mode", "edit");
		modelMap.put("fishTypes", fishTypes);
		modelMap.put("weightTypes", weightTypes);
        modelMap.put("dto", dto);
        
        return new ModelAndView("1_setup/FishEdit", "model", modelMap);
    }
}

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
            
            FishDao dao = DaoFactory.createFishDao();
            List<Fish> fishes = dao.findAll();
            modelMap.put("fishes", fishes);
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
		
		FishTypeDao fishTypeDao = DaoFactory.createFishTypeDao();
		List<FishType> fishTypes = fishTypeDao.findAllActive();
		
		FishWeightTypeDao weightTypeDao = DaoFactory.createFishWeightTypeDao();
		List<FishWeightType> weightTypes = weightTypeDao.findAllActive();
		
		modelMap.put("mode", "create");
		modelMap.put("fishTypes", fishTypes);
		modelMap.put("weightTypes", weightTypes);
		return new ModelAndView("1_setup/FishAdd", "model", modelMap);
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
        	
        	Integer fishTypeId = Integer.valueOf(request.getParameter("fishTypeId"));
        	Integer fishWeightTypeId = Integer.valueOf(request.getParameter("fishWeightTypeId"));
        	String code = request.getParameter("code");
        	
        	Fish dto = new Fish();
        	dto.setFishTypeId(fishTypeId);
        	dto.setFishWeightTypeId(fishWeightTypeId);
        	dto.setCode(code);
        	dto.setCreatedDate(new Date());
        	dto.setCreatedBy(userId);
        	dto.setIsActive("Y");
        	dto.setIsDelete("N");
        	
        	FishDao dao = DaoFactory.createFishDao();
        	int id = dao.insert(dto);
        	dto.setId(id);
        	
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
}

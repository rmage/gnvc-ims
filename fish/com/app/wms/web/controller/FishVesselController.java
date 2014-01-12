package com.app.wms.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.db.dao.FishSupplierDao;
import com.app.wms.engine.db.dao.FishVesselDao;
import com.app.wms.engine.db.dto.FishSupplier;
import com.app.wms.engine.db.dto.FishVessel;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishVesselController extends MultiActionController {

	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
		return new ModelAndView("1_setup/FishVesselList", "model", modelMap);
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
            
            FishVesselDao dao = DaoFactory.createFishVesselDao();
            List<FishVessel> fishVessels = dao.findAll();
            modelMap.put("fishVessels", fishVessels);
            modelMap.put("totalRows", 2000);
            modelMap.put("page", page);
            modelMap.put("paging", paging);
		}
		catch (Exception e) {
			throw e;
		}
		
		return modelMap;
	}
	
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		Integer supplierId = Integer.valueOf(request.getParameter("supplierId"));
		
		FishSupplierDao dao = DaoFactory.createFishSupplierDao();
		FishSupplier dto = dao.findByPrimaryKey(supplierId);
		
		modelMap.put("mode", "create");
		modelMap.put("dto", dto);
		return new ModelAndView("1_setup/FishVesselAdd", "model", modelMap);
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
        	String userId = user.getUserId();
        	Integer supplierId = Integer.valueOf(request.getParameter("supplierId"));
        	String code = request.getParameter("code");
        	String name = request.getParameter("name");
        	String batchNo = request.getParameter("batchNo");
        	
        	FishVessel dto = new FishVessel();
        	dto.setSupplierId(supplierId);
        	dto.setCode(code);
        	dto.setName(name);
        	dto.setBatchNo(batchNo);
        	dto.setCreatedDate(new Date());
        	dto.setCreatedBy(userId);
        	dto.setIsActive("Y");
        	dto.setIsDelete("N");
        	
        	FishVesselDao dao = DaoFactory.createFishVesselDao();
        	int id = dao.insert(dto);
        	dto.setId(id);
        	
        	modelMap.put("dto", dto);
        	return new ModelAndView("1_setup/FishVesselView", modelMap);
        }
	}
}

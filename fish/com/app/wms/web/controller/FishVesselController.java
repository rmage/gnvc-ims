package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.FishBalanceDao;
import com.app.wms.engine.db.dao.FishDao;
import com.app.wms.engine.db.dao.FishStorageDao;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.db.dao.FishSupplierDao;
import com.app.wms.engine.db.dao.FishVesselDao;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishBalance;
import com.app.wms.engine.db.dto.FishStorage;
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

            FishVesselDao dao = DaoFactory.createFishVesselDao();
            List<FishVessel> vesselList = null;

            if (request.getParameter("search") != null) {
                String batchNo = request.getParameter("batchNo");
                vesselList = dao.searchAndPaging(batchNo, paging, offset);
                String querySearch = "&search=true&batchNo=" + batchNo;
                modelMap.put("querySearch", querySearch);
                modelMap.put("queryBatchNo", batchNo);
            } else {
                vesselList = dao.findAllAndPaging(paging, offset);
            }

            modelMap.put("fishVessels", vesselList);
            modelMap.put("totalRows", 2000);
            modelMap.put("page", page);
            modelMap.put("paging", paging);
        } catch (Exception e) {
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
        } else {
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
            dto.setIsActive("Y");
            dto.setIsDelete("N");

            FishVesselDao dao = DaoFactory.createFishVesselDao();
            if (mode.equalsIgnoreCase("edit")) {
                int id = Integer.valueOf(request.getParameter("id"));
                dto.setId(id);
                dto.setUpdatedDate(new Date());
                dto.setUpdatedBy(user.getUserId());
                dao.update(id, dto);
            } else {
                dto.setCreatedDate(new Date());
                dto.setCreatedBy(userId);
                int id = dao.insert(dto);
                dto.setId(id);

                // UPDATED | by FYA on November 28, 2014
                // REASON | withdrawal not limited by remaining balance, but open to the fish type
                FishStorageDao fishStorageDao = DaoFactory.createFishStorageDao();
                List<FishStorage> fses = fishStorageDao.findAllActive();

                FishDao fishDao = DaoFactory.createFishDao();
                List<Fish> fs = fishDao.findAllActive();
                
                FishBalanceDao fishBalanceDao = DaoFactory.createFishBalanceDao();
                FishBalance fishBalance = new FishBalance();
                fishBalance.setCreatedBy(userId);
                fishBalance.setCreatedDate(new Date());
                for (FishStorage fishStorage : fses) {
                    for (Fish fish : fs) {
                        fishBalance.setVesselId(dto.getId());
                        fishBalance.setStorageId(fishStorage.getId());
                        fishBalance.setFishId(fish.getId());
                        fishBalance.setBalance(Double.parseDouble("0"));
                        fishBalance.setIsActive("Y");
                        fishBalance.setIsDelete("N");
                        fishBalanceDao.insert(fishBalance);
                    }
                }
            }

            modelMap.put("dto", dto);
            return new ModelAndView("1_setup/FishVesselView", modelMap);
        }
    }

    public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.valueOf(request.getParameter("id"));
        FishVesselDao dao = DaoFactory.createFishVesselDao();
        dao.delete(id);

        HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
        return new ModelAndView("1_setup/FishVesselList", "model", modelMap);
    }

    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        FishVesselDao dao = DaoFactory.createFishVesselDao();
        FishVessel dto = dao.findByPrimaryKey(id);

        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        modelMap.put("mode", "edit");
        modelMap.put("dto", dto);

        return new ModelAndView("1_setup/FishVesselEdit", "model", modelMap);
    }
}

package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FishReclassificationDao;
import com.spfi.ims.dto.FishReclassification;
import com.spfi.ims.dto.FishReclassificationDetail;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FishReclassificationController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse
            response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/fish/ReclassificationList");
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) 
            throws DaoException {
        /* DATA | get initial value */
        HashMap<String, Object> model = new HashMap<String, Object>();
        
        /* DAO | Define needed dao here */
        FishReclassificationDao frDao = DaoFactory.createFishReclassificationDao();
        
        /* TRANSACTION | Something complex here */
        // get fish
        List<Map<String, Object>> ms = frDao.getFish();
        model.put("fs", ms);
        
        // get cold storage
        ms = frDao.getFishStorage();
        model.put("css", ms);        
        
        return new ModelAndView("default/fish/ReclassificationAdd", "model", model);
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String[] header = request.getParameter("header").split(":", -1);
            String[] details = request.getParameterValues("detail");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            /* DAO | Define needed dao here */
            FishReclassificationDao frDao = DaoFactory.createFishReclassificationDao();
            
            /* TRANSACTION | Something complex here */
            // insert header
            FishReclassification fr = new FishReclassification();
            fr.setCode(header[0]);
            fr.setDate(sdf.parse(header[1]));
            fr.setCreatedBy(lu.getUserId());
            fr.setId(frDao.insert(fr));
            
            // insert details
            FishReclassificationDetail frd = new FishReclassificationDetail();
            frd.setFrId(fr.getId());
            frd.setCreatedBy(fr.getCreatedBy());
            for(String detail : details) {
                String[] x = detail.split(":", -1);
                
                frd.setFromVesselId(Integer.parseInt(x[0]));
                frd.setFromFishId(Integer.parseInt(x[1]));
                frd.setFromStorageId(Integer.parseInt(x[2]));
                frd.setFromQty(new BigDecimal(x[3]));
                frd.setToVesselId(Integer.parseInt(x[4]));
                frd.setToFishId(Integer.parseInt(x[5]));
                frd.setToStorageId(Integer.parseInt(x[6]));
                frd.setToQty(new BigDecimal(x[7]));
                frd.setRemarks(x[8]);
                frd.setFrType(x[9]);
                frDao.insertD(frd);
            }
            
            return new ModelAndView("redirect:FishReclassification.htm");
        } catch(Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FishReclassification.htm?action=create");
        }
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        FishReclassificationDao frDao = DaoFactory.createFishReclassificationDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(frDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = frDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("fr_no")).append("\", ");
            sb.append("\"3\": \"").append(x.get("fr_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("created_by")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}");
        response.getWriter().print(sb.toString());
    }
    
    public void getBatchInfo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        StringBuilder sb = new StringBuilder();
        String batchNo = request.getParameter("term");

        /* DAO | Define needed dao here */
        FishReclassificationDao frDao = DaoFactory.createFishReclassificationDao();

        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = frDao.getBatchInfo(batchNo);
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"id\":\"").append(x.get("id")).append("\",");
            sb.append("\"batchNo\":\"").append(x.get("batch_no")).append("\",");
            sb.append("\"boat\":\"").append(x.get("boat")).append("\",");
            sb.append("\"supplier\":\"").append(x.get("supplier")).append("\"}");
            b = Boolean.TRUE;
        }
        sb.append("]");
        response.getWriter().print(sb.toString());
    }
    
}

package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FishMovingDao;
import com.spfi.ims.dto.FishMoving;
import com.spfi.ims.dto.FishMovingDetail;
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

public class FishMovingController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/fish/MovingList");
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response)
            throws DaoException {
        /* DATA | get initial value */
        HashMap<String, Object> model = new HashMap<String, Object>();

        /* DAO | Define needed dao here */
        FishMovingDao fmDao = DaoFactory.createFishMovingDao();

        /* TRANSACTION | Something complex here */
        // get cold storage
        List<Map<String, Object>> ms = fmDao.getFishStorage();
        model.put("css", ms);

        return new ModelAndView("default/fish/MovingAdd", "model", model);
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String[] header = request.getParameter("header").split(":", -1);
            String[] details = request.getParameterValues("detail");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            /* DAO | Define needed dao here */
            FishMovingDao fmDao = DaoFactory.createFishMovingDao();
            
            /* TRANSACTION | Something complex here */
            // insert header
            FishMoving fm = new FishMoving();
            fm.setCode(header[0]);
            fm.setDate(sdf.parse(header[1]));
            fm.setFromStorageId(Integer.parseInt(header[2]));
            fm.setToStorageId(Integer.parseInt(header[3]));
            fm.setRemarks(header[4]);
            fm.setCreatedBy(lu.getUserId());
            fm.setId(fmDao.insert(fm));
            
            // insert detail
            FishMovingDetail fmd = new FishMovingDetail();
            fmd.setFmId(fm.getId());
            fmd.setCreatedBy(fm.getCreatedBy());
            fmd.setUom("kg");
            for(String detail : details) {
                String[] x = detail.split(":", -1);
                fmd.setVesselId(Integer.parseInt(x[0]));
                fmd.setFishId(Integer.parseInt(x[1]));
                fmd.setTotalWeight(new BigDecimal(x[2]));
                fmDao.insertD(fmd);
            }
            
            return new ModelAndView("redirect:FishMoving.htm");
        } catch(Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FishMoving.htm?action=create");
        }
    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        FishMovingDao fmDao = DaoFactory.createFishMovingDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fmDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fmDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("fm_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("fm_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("from_cs")).append("\", ");
            sb.append("\"5\": \"").append(x.get("to_cs")).append("\", ");
            sb.append("\"6\": \"").append(x.get("remarks")).append("\", ");
            sb.append("\"7\": \"").append(x.get("created_by")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}");
        response.getWriter().print(sb.toString());
    }

    public void getBatchInCs(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        /* DATA | get initial value */
        String term = request.getParameter("storageId");
        Boolean b = Boolean.FALSE;
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        FishMovingDao fmDao = DaoFactory.createFishMovingDao();

        /* TRANSACTION | Something complex here */
        sb.append("[");
        if(!term.isEmpty()) {
            List<Map<String, Object>> xs = fmDao.getBatchInCs(Integer.parseInt(term));
            for(Map<String, Object> x : xs) {
                if (b) {
                    sb.append(",");
                }

                sb.append("{\"1\":\"").append(x.get("vessel_id")).append("\",");
                sb.append("\"2\":\"").append(x.get("batch_no")).append("\",");
                sb.append("\"3\":\"").append(x.get("supplier")).append("\"}");
                
                b = Boolean.TRUE;
            }
        }
        sb.append("]");
        response.getWriter().print(sb.toString());
    }
    
    public void getFishInCsBatch(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        /* DATA | get initial value */
        String term1 = request.getParameter("storageId");
        String term2 = request.getParameter("vesselId");
        Boolean b = Boolean.FALSE;
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        FishMovingDao fmDao = DaoFactory.createFishMovingDao();

        /* TRANSACTION | Something complex here */
        sb.append("[");
        if(!term1.isEmpty() && !term2.isEmpty()) {
            List<Map<String, Object>> xs = fmDao.getFishInCsBatch(Integer.parseInt(term1), Integer.parseInt(term2));
            for(Map<String, Object> x : xs) {
                if (b) {
                    sb.append(",");
                }

                sb.append("{\"1\":\"").append(x.get("id")).append("\",");
                sb.append("\"2\":\"").append(x.get("code")).append("\",");
                sb.append("\"3\":\"").append(x.get("balance")).append("\"}");
                
                b = Boolean.TRUE;
            }
        }
        sb.append("]");
        response.getWriter().print(sb.toString());
    }

}

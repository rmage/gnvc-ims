/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller.acc;

import com.app.wms.engine.db.dao.FishRRAccountingDao;
import com.app.wms.engine.db.dao.FishRRAccountingDetailDao;
import com.app.wms.engine.db.dto.FishRRAccounting;
import com.app.wms.engine.db.dto.FishRRAccountingDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author Faridzi
 */
public class FishRRAccountingController extends MultiActionController {

    /* DAO | Define needed dao here */
    private final FishRRAccountingDao fishRRAccountingDao = DaoFactory.createFishRRAccountingDao();

    private final FishRRAccountingDetailDao fishRRAccountingDetailDao = DaoFactory.createFishRRAccountingDetailDao();

    private List<FishRRAccounting> fishRRAccountingList = new ArrayList<FishRRAccounting>();

    private List<FishRRAccountingDetail> frrDetailList = new ArrayList<FishRRAccountingDetail>();

    private final SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");

    private final SimpleDateFormat sdfOut = new SimpleDateFormat("dd/MM/yyyy");

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        /*COPY ROW FROM FISH RR*/
        fishRRAccountingDao.copyFromRR();
        fishRRAccountingDetailDao.copyFromRRDetail();

        return new ModelAndView("accounting/FishRRAccountingList", "model", modelMap);
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        System.out.println("UPDATE");

        Integer key = Integer.parseInt(request.getParameter("key"));
        FishRRAccounting frrAcc = fishRRAccountingList.get(key - 1);

        frrDetailList = fishRRAccountingDetailDao.findByRRId(frrAcc.getRrId());

        Date dateTemp = new Date();
        try {
            dateTemp = sdfIn.parse(frrAcc.getFishRr().getRrDate().toString());
        } catch (ParseException ex) {
            Logger.getLogger(FishRRAccountingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*PUT RR DETAIL*/
        modelMap.put("rrNo", frrAcc.getFishRr().getRrNo());
        modelMap.put("rrDate", sdfOut.format(dateTemp));
        modelMap.put("wsNo", frrAcc.getFishRr().getWsNo());
        modelMap.put("batchNo", frrAcc.getFishRr().getVessel().getBatchNo());
        modelMap.put("supplierName", frrAcc.getFishRr().getVessel().getSupplier().getName());
        /*LIST DETAIL*/
        modelMap.put("frrDetailList", frrDetailList);

        return new ModelAndView("accounting/FishRRAccountingAdd", "model", modelMap);
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        LoginUser user = (LoginUser) request.getSession().getAttribute("user");
        Date now = new Date();
        int idx = 0;
        Integer frrId = frrDetailList.get(idx).getRrId();
        for (FishRRAccountingDetail frrd : frrDetailList) {

            /*GET AMOUNT FROM EDITTING*/
            String idUC = "amount-" + idx;
            BigDecimal amount = new BigDecimal(request.getParameter(idUC));
            frrd.setAmount(amount);

            /*ACTUAL UPDATE*/
            fishRRAccountingDetailDao.update(frrd);
            idx++;
        }

        /*SET AUDITABLE*/
        FishRRAccounting frr = new FishRRAccounting();
        frr.setUpdatedBy(user.getUsername());
        frr.setUpdatedDate(now);
        frr.setRrId(frrId);

        fishRRAccountingDao.update(frr);

        return findByPrimaryKey(request, response);
    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();

        pw.print("{\"maxpage\": " + fishRRAccountingDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where")) + ",\"data\": [");
        fishRRAccountingList = fishRRAccountingDao.ajaxSearch(request.getParameter("where"), request.getParameter("order"), Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10));
        for (FishRRAccounting fRRAcc : fishRRAccountingList) {
            if (b) {
                pw.print(",");
            }
            Date dateTemp = new Date();
            Date updateTemp = new Date();
            try {
                dateTemp = sdfIn.parse(fRRAcc.getFishRr().getRrDate().toString());
                if (fRRAcc.getUpdatedDate() != null) {
                    updateTemp = sdfIn.parse(fRRAcc.getUpdatedDate().toString());
                }
            } catch (ParseException ex) {
                Logger.getLogger(FishRRAccountingController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            pw.print("{\"1\": \"" + fRRAcc.getId() + "\", ");
            pw.print("\"2\": \"" + fRRAcc.getFishRr().getRrNo() + "\", ");
            pw.print("\"3\": \"" + sdfOut.format(dateTemp) + "\", ");
            pw.print("\"4\": \"" + fRRAcc.getFishRr().getWsNo() + "\", ");
            pw.print("\"5\": \"" + fRRAcc.getFishRr().getVessel().getSupplier().getName() + "\", ");
            pw.print("\"6\": \"" + fRRAcc.getFishRr().getVessel().getBatchNo() + "\", ");
            pw.print("\"7\": \"" + fRRAcc.getUpdatedBy() + "\", ");
            if (fRRAcc.getUpdatedDate() != null) {
                pw.print("\"8\": \"" + sdfOut.format(updateTemp) + "\"}");
            } else {
                pw.print("\"8\": \"" + fRRAcc.getUpdatedDate() + "\"}");
            }

            b = Boolean.TRUE;
        }
        pw.print("]}");
    }

}

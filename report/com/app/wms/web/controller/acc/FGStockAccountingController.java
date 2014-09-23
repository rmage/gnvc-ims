/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller.acc;

import com.app.wms.engine.db.dao.CurrencyRateDao;
import com.app.wms.engine.db.dto.CurrencyRate;
import com.app.wms.engine.db.dto.FGPackStyle;
import com.app.wms.engine.db.dto.FGStockCardAccounting;
import com.app.wms.engine.db.dto.FGUnitCost;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGPackStyleDao;
import com.spfi.ims.dao.FGStockCardDao;
import com.spfi.ims.dao.FgUnitCostDao;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author Faridzi
 */
public class FGStockAccountingController extends MultiActionController {

    private final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    /* DAO | Define needed dao here */
    private final FGPackStyleDao fgPackStyleDao = DaoFactory.createFGPackStyleDao();

    private final FGStockCardDao fgStockCardDao = DaoFactory.createFGStockCardDao();

    private final FgUnitCostDao fgUnitCostDao = DaoFactory.createFgUnitCostDao();

    private final CurrencyRateDao currencyRateDao = DaoFactory.createCurrencyRateDao();

    /*INITIALIZE VAR*/
    private List<FGPackStyle> fgPackStyles = new ArrayList<FGPackStyle>();

    private List<FGStockCardAccounting> fgStockCardAccountingList = new ArrayList<FGStockCardAccounting>();

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        fgPackStyles = fgPackStyleDao.findAllActive();

        /*PUT THOSE TO MODEL*/
        modelMap.put("fgPackStyles", fgPackStyles);
        return new ModelAndView("accounting/FGStockAccounting", "model", modelMap);
    }

    public ModelAndView generate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        Date asOf = new Date();

        /*GET PARAMETER*/
        String asOfDate = request.getParameter("asOfDate");
        String idxString = request.getParameter("dropdownPackStyle");

        Integer idx = Integer.parseInt(idxString);

        FGPackStyle fgPs = fgPackStyles.get(idx);

        asOf = df.parse(asOfDate);

        fgStockCardAccountingList = fgStockCardDao.findByDateAndPackId(fgPs.getId(), asOf);
        int i = 0;
        BigDecimal amountFixIDR = BigDecimal.ZERO;
        BigDecimal amountVarIDR = BigDecimal.ZERO;
        BigDecimal amountTotalIDR = BigDecimal.ZERO;

        /*VAR TOTAL*/
        Double totalQty = 0d;
        BigDecimal totalFixUSD = BigDecimal.ZERO;
        BigDecimal totalVarUSD = BigDecimal.ZERO;
        BigDecimal totalTotalUSD = BigDecimal.ZERO;
        BigDecimal totalFixIDR = BigDecimal.ZERO;
        BigDecimal totalVarIDR = BigDecimal.ZERO;
        BigDecimal totalTotalIDR = BigDecimal.ZERO;

        /*GET CURRENCY RATE FROM USD TO IDR */
        CurrencyRate cr = new CurrencyRate();
        cr = currencyRateDao.findLatestByCurrencyCodeFromToAndDate("USD", "IDR", asOf);

        for (FGStockCardAccounting fgAcc : fgStockCardAccountingList) {
            FGUnitCost fgUc = new FGUnitCost();
            fgUc = fgUnitCostDao.findLatest(fgAcc.getScDate(), fgAcc.getItemId());
            /*CALCULATE TOTAL*/
            Double qty = fgAcc.getEndQuantity();
            amountFixIDR = fgUc.getAmountFixCost().multiply(BigDecimal.valueOf(qty));
            amountVarIDR = fgUc.getAmountVarCost().multiply(BigDecimal.valueOf(qty));
            amountTotalIDR = fgUc.getAmountTotal().multiply(BigDecimal.valueOf(qty));

            BigDecimal rateValue = cr.getRateValue();
            amountFixIDR = amountFixIDR.multiply(rateValue);
            amountVarIDR = amountVarIDR.multiply(rateValue);
            amountTotalIDR = amountTotalIDR.multiply(rateValue);

            /*SET TO LIST*/
            fgStockCardAccountingList.get(i).setFixCost(fgUc.getAmountFixCost());
            fgStockCardAccountingList.get(i).setVarCost(fgUc.getAmountVarCost());
            fgStockCardAccountingList.get(i).setTotalCost(fgUc.getAmountTotal());
            fgStockCardAccountingList.get(i).setAmountFixCost(amountFixIDR);
            fgStockCardAccountingList.get(i).setAmountVarCost(amountVarIDR);
            fgStockCardAccountingList.get(i).setAmountTotalCost(amountTotalIDR);

            i++;
            /*TOTALING*/
            totalQty += fgAcc.getEndQuantity();
            totalFixUSD = totalFixUSD.add(fgUc.getAmountFixCost());
            totalVarUSD = totalVarUSD.add(fgUc.getAmountVarCost());
            totalTotalUSD = totalTotalUSD.add(fgUc.getAmountTotal());
            totalFixIDR = totalFixIDR.add(amountFixIDR);
            totalVarIDR = totalVarIDR.add(amountVarIDR);
            totalTotalIDR = totalTotalIDR.add(amountTotalIDR);
        }

        System.out.println("GENERATE " + asOfDate + " " + idxString);

        modelMap.put("fgPs", fgPs);
        modelMap.put("asOfDate", asOfDate);
        modelMap.put("cr", cr);
        /*TOTALING TO MAP*/
        modelMap.put("totalQty", totalQty);
        modelMap.put("totalFixUSD", totalFixUSD);
        modelMap.put("totalVarUSD", totalVarUSD);
        modelMap.put("totalTotalUSD", totalTotalUSD);
        modelMap.put("totalFixIDR", totalFixIDR);
        modelMap.put("totalVarIDR", totalVarIDR);
        modelMap.put("totalTotalIDR", totalTotalIDR);
        
        modelMap.put("fgStockCardAccountingList", fgStockCardAccountingList);

        return new ModelAndView("accounting/FGStockAccountingGenerate", "model", modelMap);
    }

}

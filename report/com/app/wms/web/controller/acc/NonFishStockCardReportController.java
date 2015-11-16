/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller.acc;

import com.app.wms.engine.db.dao.CategoryItemCurrencyTypeDao;
import com.app.wms.engine.db.dao.DrDao;
import com.app.wms.engine.db.dao.NonFishStockCardSummaryDao;
import com.app.wms.engine.db.dao.ProductCategoryDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.ProductPriceDao;
import com.app.wms.engine.db.dao.ReceiveReportDao;
import com.app.wms.engine.db.dao.StockInventoryDao;
import com.app.wms.engine.db.dao.TsDao;
import com.app.wms.engine.db.dto.CategoryItemCurrencyType;
import com.app.wms.engine.db.dto.Dr;
import com.app.wms.engine.db.dto.NonFishStockCardAccounting;
import com.app.wms.engine.db.dto.NonFishStockCardSummary;
import com.app.wms.engine.db.dto.ProductCategory;
import com.app.wms.engine.db.dto.ProductPrice;
import com.app.wms.engine.db.dto.ReceiveReport;
import com.app.wms.engine.db.dto.StockInventory;
import com.app.wms.engine.db.dto.Ts;
import com.app.wms.engine.db.exceptions.ProductCategoryDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
public class NonFishStockCardReportController extends MultiActionController {

    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /* DAO | Define needed dao here */
    private final ProductDao productDao = DaoFactory.createProductDao();

    private final ReceiveReportDao receiveReportDao = DaoFactory.createReceiveReportDao();

    private final TsDao tsDao = DaoFactory.createTsDao();

    private final DrDao drDao = DaoFactory.createDrDao();

    private final StockInventoryDao stockInventoryDao = DaoFactory.createStockInventoryDao();

    private final ProductCategoryDao productCategoryDao = DaoFactory.createProductCategoryDao();

    private final NonFishStockCardSummaryDao nonFishStockCardSummaryDao = DaoFactory.createNonFishStockCardSummaryDao();

    private final ProductPriceDao productPriceDao = DaoFactory.createProductPriceDao();

    private final CategoryItemCurrencyTypeDao categoryItemCurrencyTypeDao = DaoFactory.createCategoryItemCurrencyTypeDao();

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        List<ProductCategory> productCategoryList = null;
        try {
            productCategoryList = productCategoryDao.findAll();
        } catch (ProductCategoryDaoException ex) {
            Logger.getLogger(NonFishStockCardReportController.class.getName()).log(Level.SEVERE, null, ex);
        }

        modelMap.put("prodCategory", productCategoryList);

        return new ModelAndView("accounting/NonFishStockCardReport", "model", modelMap);
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        List<String> productCodeList = new ArrayList();
        List<BeginningBalance> begBalanceList = new ArrayList<BeginningBalance>();

        /*BEGIN*/
        long start = System.currentTimeMillis();

        /*INITIALIZE*/
        List<NonFishStockCardSummary> nonFishStockCardReportList = new ArrayList<NonFishStockCardSummary>();
        CategoryItemCurrencyType cri = new CategoryItemCurrencyType();
        Calendar aCalendar = Calendar.getInstance();
        Date now = null;
        String lastDateOfThisMonthString = "";

        /*GET PARAMETER*/
        String productCat = request.getParameter("productcat");
        String dateAsOf = request.getParameter("asOf");

        /*GET CRI*/
        cri = categoryItemCurrencyTypeDao.findCurrencyTypeByCategoryCode(productCat);

        try {
            now = df.parse(dateAsOf);
        } catch (ParseException ex) {
            Logger.getLogger(NonFishStockCardSummaryDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*GET PRODUCT CATEGORY IN LIST THAT HAVE RR TS OR DR IN THIS CURRENT MONTH AS OF*/
        /*RR*/
        productCodeList = receiveReportDao.findProductCodeWithRR(productCat, now);
        /*TS*/
        productCodeList.addAll(tsDao.findProductCodeWithRR(productCat, now));
        /*DR*/
        productCodeList.addAll(drDao.findProductCodeWithRR(productCat, now));

        /*STOCK INVENTORY BALANCE*/
        productCodeList.addAll(stockInventoryDao.findByProductCategoryAndNotEmpty(productCat));

        HashSet hs = new HashSet();
        hs.addAll(productCodeList);
        productCodeList.clear();
        productCodeList.addAll(hs);

        /*THIS LIST PRODUCT SHOWS THAT THERE IS TRANSACTION WITHIN THIS MONTH*/
        for (int i = 0; i < productCodeList.size(); i++) {

            /* CALCULATE EVERYTHING HERE */
            begBalanceList.add(calculateLastMonthQtyAndBalance(productCodeList.get(i), dateAsOf));
        }

//        for (int i = 0; i < productCodeList.size(); i++) {
//            System.out.println("CODE " + productCodeList.get(i));
//            System.out.println("BAL " + begBalanceList.get(i).balance);
//            System.out.println("QTY " + begBalanceList.get(i).qty);
//            System.out.println("UC " + begBalanceList.get(i).unitCost);
//            System.out.println("--------------------------------");
//        }

        /*String csv = productCodeList.toString().replace("[", "").replace("]", "").replace(", ", ",");*/
        /*GET LAST DATE*/
        /*SET DATE REPORT TO PREVIOUS MONTH LAST DAY*/
        aCalendar.setTime(now);

        /*SET LAST DAY OF MONTH*/
        aCalendar.set(Calendar.DATE, aCalendar.getActualMaximum(Calendar.DATE));

        Date lastDateOfThisMonth = aCalendar.getTime();
        lastDateOfThisMonthString = df.format(lastDateOfThisMonth);

        /*VAR FOR TOTALING*/
        Double totalQuantity = 0d;
        BigDecimal totalAmountToDate = BigDecimal.ZERO;
        BigDecimal totalTransactionAmount = BigDecimal.ZERO;

        nonFishStockCardReportList = nonFishStockCardSummaryDao.findByItemCategoryandDate(productCat, lastDateOfThisMonthString);

        for (NonFishStockCardSummary nfs : nonFishStockCardReportList) {
            totalQuantity += nfs.getQuantity();
            totalAmountToDate = totalAmountToDate.add(nfs.getAmountToDate());
            totalTransactionAmount = totalTransactionAmount.add(nfs.getTransactionAmount());
        }

        modelMap.put("nonFishStockCardReportList", nonFishStockCardReportList);
        modelMap.put("productCat", productCat);
        modelMap.put("dateAsOf", dateAsOf);
        modelMap.put("cri", cri);
        /*PUT TOTALING TO MODEL*/
        modelMap.put("totalQuantity", totalQuantity);
        modelMap.put("totalAmountToDate", totalAmountToDate);
        modelMap.put("totalTransactionAmount", totalTransactionAmount);

        /*END*/
        long end = System.currentTimeMillis();
        long diff = end - start;
        System.out.println("Difference is : " + diff);

        return new ModelAndView("accounting/NonFishStockCardReportGenerate", "model", modelMap);
    }

    private BeginningBalance calculateLastMonthQtyAndBalance(String productCode, String lastMonth) {
        List<ReceiveReport> receiveReportList;
        List<Ts> tsList;
        List<Dr> drList;
        StockInventory stockInventory = new StockInventory();
        ProductPrice productPrice = new ProductPrice();
        BeginningBalance result = new BeginningBalance();

        List<NonFishStockCardAccounting> nFStockCardList = new ArrayList<NonFishStockCardAccounting>();
        NonFishStockCardAccounting nfTemp;

        /*GET INITIALIZE QUANTITY*/
        stockInventory = stockInventoryDao.findByProductCode(productCode);
        System.out.println("CODE " + stockInventory.getProductCode());
        System.out.println("START BAL " + stockInventory.getStartBalance());

        productPrice = productPriceDao.findByProduct(productCode);

        /*GET RR LIST*/
        receiveReportList = receiveReportDao.findByProductCodeAndBeforeThan(productCode, lastMonth);
        for (ReceiveReport receiveReport : receiveReportList) {
            BigDecimal amountIDR;
            BigDecimal amountIN;
            /*DOCUMENT*/
            nfTemp = new NonFishStockCardAccounting();
            nfTemp.setStockCardDate(receiveReport.getRrDate());
            nfTemp.setCode("RR");
            nfTemp.setNumber(receiveReport.getRrCode());
            /*UNIT COST*/
            nfTemp.setCurrencyCode(receiveReport.getPo().getCurrency());
            nfTemp.setAmount(BigDecimal.valueOf(receiveReport.getUnitCost()));
            nfTemp.setRateValue(receiveReport.getCurrencyRate().getRateValue());
            amountIDR = nfTemp.getRateValue().multiply(nfTemp.getAmount());
            nfTemp.setAmountIDR(amountIDR);
            /*IN*/
            nfTemp.setQuantityIN(receiveReport.getQty());
            nfTemp.setUnitCostIN(amountIDR);
            amountIN = amountIDR.multiply(BigDecimal.valueOf(nfTemp.getQuantityIN()));
            nfTemp.setAmountIN(amountIN);
            /*OUT*/
            /*THIS IS RR , SO OUT VALUE IS EMPTY*/

            /*ADD NF TO LIST*/
            nFStockCardList.add(nfTemp);
        }

        /*GET TS LIST*/
        tsList = tsDao.findByProductCodeAndBeforeThan(productCode, lastMonth);

        for (Ts ts : tsList) {
            BigDecimal amountIDR;
            BigDecimal amountOUT;
            /*DOCUMENT*/
            nfTemp = new NonFishStockCardAccounting();
            nfTemp.setStockCardDate(ts.getTsDate());
            nfTemp.setCode("TS");
            nfTemp.setNumber(ts.getTsCode());
            /*UNIT COST*/
            /*UNIT COST HERE WILL BE CALCULATE WITH AVERAGE COST*/

            /*IN*/
            /*OUT*/
            nfTemp.setQuantityOUT(ts.getQty());

            /*ADD NF TO LIST*/
            nFStockCardList.add(nfTemp);
        }

        /*GET DR LIST*/
        drList = drDao.findByProductCodeAndBeforeThan(productCode, lastMonth);

        for (Dr dr : drList) {
            BigDecimal amountIDR;
            BigDecimal amountOUT;
            /*DOCUMENT*/
            nfTemp = new NonFishStockCardAccounting();
            nfTemp.setStockCardDate(dr.getDrDate());
            nfTemp.setCode("DR");
            nfTemp.setNumber(dr.getDrCode() + "");
            /*UNIT COST*/
            /*UNIT COST HERE WILL BE CALCULATE WITH AVERAGE COST*/

            /*IN*/
            /*OUT*/
            nfTemp.setQuantityOUT(dr.getQty());

            /*ADD NF TO LIST*/
            nFStockCardList.add(nfTemp);
        }

        /*SORT NFSTOCK CARD LIST BY DATE*/
        Collections.sort(nFStockCardList, new Comparator<NonFishStockCardAccounting>() {
            public int compare(NonFishStockCardAccounting o1, NonFishStockCardAccounting o2) {
                if (o1.getStockCardDate() == null || o2.getStockCardDate() == null) {
                    return 0;
                }
                return o1.getStockCardDate().compareTo(o2.getStockCardDate());
            }
        });

        /*COUNT AVERAGE COST HERE*/
        BigDecimal initPrice = productPrice.getUnitPrice();
        Double tempTotalQty = stockInventory.getStartBalance().doubleValue();
        Double tempAmount = 0d;
        BigDecimal tempTotalINIDR = initPrice.multiply(BigDecimal.valueOf(tempTotalQty));
        int size = nFStockCardList.size();
        boolean isOut = false;

        /*INITIALIZE VAR FOR TOTALING*/
        Double totalQTY = stockInventory.getStartBalance().doubleValue();
        Double totalQTYEnd = 0d;
        BigDecimal totalAmountEND = tempTotalINIDR;
        BigDecimal totalAmountIDR = BigDecimal.ZERO;
        BigDecimal totalINIDR = BigDecimal.ZERO;
        BigDecimal totalOUTIDR = BigDecimal.ZERO;
        BigDecimal balanceIDR = BigDecimal.ZERO;
        Double amountOut = 0d;

        /*COUNT AVERAGE COST*/
        for (int i = 0; i < size; i++) {
            NonFishStockCardAccounting nf = nFStockCardList.get(i);
            if (nf.getCode().equalsIgnoreCase("RR")) {
                if (isOut) {
                    tempTotalQty = 0d;
                    tempTotalINIDR = BigDecimal.ZERO;
                    isOut = false;
                    amountOut = nf.getAmountIN().doubleValue();
                }
                tempTotalQty += nf.getQuantityIN();
                tempTotalINIDR = tempTotalINIDR.add(nf.getAmountIN());
                amountOut = tempTotalINIDR.doubleValue() / tempTotalQty;

                totalQTY += nf.getQuantityIN();
            }

            if (nf.getCode().equalsIgnoreCase("TS") || nf.getCode().equalsIgnoreCase("DR")) {
                Double qtyOut = nf.getQuantityOUT();
                if (tempTotalQty > 0) {
                    amountOut = tempTotalINIDR.doubleValue() / tempTotalQty;
                }
                nFStockCardList.get(i).setUnitCostOUT(BigDecimal.valueOf(amountOut));
                nFStockCardList.get(i).setAmountOUT(BigDecimal.valueOf(amountOut * qtyOut));

                /*SET ENDING BALANCE UNIT COST*/
                nFStockCardList.get(i).setUnitCostEND(BigDecimal.valueOf(amountOut));
                isOut = true;

                totalQTY -= qtyOut;
            }

            /*SET ENDING BALANCE*/
            totalQTYEnd += nf.getQuantityIN();
            totalQTYEnd += nf.getQuantityOUT();

            totalAmountEND = totalAmountEND.add(nf.getAmountIN());
            totalAmountEND = totalAmountEND.subtract(nf.getAmountOUT());

            nFStockCardList.get(i).setQuantityEND(totalQTYEnd);
            nFStockCardList.get(i).setAmountEND(totalAmountEND);

            nf = nFStockCardList.get(i);

            /*FOR TOTALING*/
            totalAmountIDR = totalAmountIDR.add(nf.getAmountIDR());

        }

        balanceIDR = totalAmountEND;

        result.balance = balanceIDR;
        result.qty = totalQTY;
        /*THIS IS FOR NEXT AVERAGE COST*/
        result.unitCost = amountOut;

        return result;
    }

    private class BeginningBalance {

        Double qty;
        BigDecimal balance;
        Double unitCost;

        public BeginningBalance() {
            this.qty = 0d;
            this.balance = BigDecimal.ZERO;
            this.unitCost = 0d;
        }
    }

}

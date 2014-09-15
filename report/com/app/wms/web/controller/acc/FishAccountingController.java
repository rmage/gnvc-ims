package com.app.wms.web.controller.acc;

import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dao.CurrencyRateDao;
import com.app.wms.engine.db.dao.FishDao;
import com.app.wms.engine.db.dao.FishSupplierDao;
import com.app.wms.engine.db.dao.FishTransactionDao;
import com.app.wms.engine.db.dao.FishWsDao;
import com.app.wms.engine.db.dto.CurrencyRate;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishStockCardAccounting;
import com.app.wms.engine.db.dto.FishSupplier;
import com.app.wms.engine.db.dto.FishTransaction;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FishAccountingController extends MultiActionController {

    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /* DAO | Define needed dao here */
    private final FishDao fishDao = DaoFactory.createFishDao();

    private final FishWsDao fishWsDao = DaoFactory.createFishWsDao();

    private final CurrencyDao currencyDao = DaoFactory.createCurrencyDao();

    private final CurrencyRateDao currencyRateDao = DaoFactory.createCurrencyRateDao();

    private final FishSupplierDao fishSupplierDao = DaoFactory.createFishSupplierDao();

    private final FishTransactionDao fishTransactionDao = DaoFactory.createFishTransactionDao();

    private List<Fish> fishes = new ArrayList<Fish>();

    /* GNVS | New Fish Created Fish Inventory */
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        List<FishSupplier> suppliers = new ArrayList<FishSupplier>();
        try {
            /*QUERY ALL FISH SUPPLIER REGISTERED*/
            suppliers = fishSupplierDao.findAllActive();
        } catch (DaoException ex) {
            Logger.getLogger(FishAccountingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        modelMap.put("suppliers", suppliers);
        return new ModelAndView("accounting/FishStockAccounting", "model", modelMap);
    }

    public ModelAndView generate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        /*INITIALIZE VAR*/
        Calendar aCalendar = Calendar.getInstance();
        Date now = new Date();
        List<FishStockCardAccounting> fishStockCardAccountingList = new ArrayList<FishStockCardAccounting>();
        BeginningBalance tempBegBalance = new BeginningBalance();
        BeginningBalance tempIN = new BeginningBalance();
        BeginningBalance tempOUT = new BeginningBalance();
        BeginningBalance tempEND = new BeginningBalance();

        FishSupplier fs = new FishSupplier();

        /*INITIALIZE FOR TOTALING*/
        Double begTotalQty = 0d;
        BigDecimal begTotalAmount = BigDecimal.ZERO;
        Double inTotalQty = 0d;
        BigDecimal inTotalAmount = BigDecimal.ZERO;
        Double outTotalQty = 0d;
        BigDecimal outTotalAmount = BigDecimal.ZERO;
        Double endTotalQty = 0d;
        BigDecimal endTotalAmount = BigDecimal.ZERO;

        /*GET FORM SUBMITTED VALUE*/
        String asOfDate = request.getParameter("asOfDate");
        String supplierIdString = request.getParameter("dropdownSupplierCode");

        Integer supplierId = Integer.parseInt(supplierIdString);

        fs = fishSupplierDao.findByPrimaryKey(supplierId);

        try {
            now = df.parse(asOfDate);
        } catch (ParseException ex) {
            Logger.getLogger(FishAccountingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        aCalendar.setTime(now);

        /*SET FIRST DAY OF AS OF MONTH*/
        aCalendar.set(Calendar.DATE, aCalendar.getActualMinimum(Calendar.DATE));

        Date firstDateOfThisMonth = aCalendar.getTime();

        /*GET ALL FISH*/
        fishes = fishDao.findAllActiveAndNotDelete();

        /*LET THE FUN BEGIN*/

        /*CALCULATE BEGINNING BALANCE FOR EVERY FISH HERE*/
        for (Fish f : fishes) {
            FishStockCardAccounting fsc = new FishStockCardAccounting();
            /*FISH PROPERTIES*/
            fsc.setFishId(f.getId());
            fsc.setFish(f);

            /*BEGINNING BALANCE CERTAIN FISH*/
            tempBegBalance = calculateBeginning(supplierId, f.getId(), firstDateOfThisMonth);
            fsc.setBegQuantity(tempBegBalance.begQuantity);
            fsc.setBegUnitCost(tempBegBalance.begUnitCost);
            fsc.setBegAmount(tempBegBalance.begAmount);

            /*TRANSACTION IN THIS MONTH*/
            tempIN = calculateFishIN(supplierId, f.getId(), now);
            fsc.setReceiveQuantity(tempIN.begQuantity);
            fsc.setReceiveUnitCost(tempIN.begUnitCost);
            fsc.setReceiveAmount(tempIN.begAmount);

            /*TRANSACTION OUT THIS MONTH*/
            tempOUT = calculateFishOUT(supplierId, f.getId(), now);
            /*CALCULATE UNIT COST AND AMOUNT*/
            Double tempQuantity = tempBegBalance.begQuantity + tempIN.begQuantity;
            if (tempQuantity != 0d) {
                tempOUT.begUnitCost = tempBegBalance.begAmount.add(tempIN.begAmount);
                tempOUT.begUnitCost = tempOUT.begUnitCost.divide(BigDecimal.valueOf(tempQuantity), 2, RoundingMode.HALF_UP);
            } else {
                tempOUT.begUnitCost = BigDecimal.ZERO;
            }
            tempOUT.begAmount = tempOUT.begUnitCost.multiply(BigDecimal.valueOf(tempOUT.begQuantity));
            fsc.setWithdrawalQuantity(tempOUT.begQuantity);
            fsc.setWithdrawalUnitCost(tempOUT.begUnitCost);
            fsc.setWithdrawalAmount(tempOUT.begAmount);

            /* ENDING BALANCE*/
            /*SET UNIT COST*/
            tempEND.begQuantity = tempBegBalance.begQuantity + tempIN.begQuantity - tempOUT.begQuantity;
            tempEND.begUnitCost = tempOUT.begUnitCost;
            tempEND.begAmount = tempOUT.begUnitCost.multiply(BigDecimal.valueOf(tempEND.begQuantity));
            fsc.setEndQuantity(tempEND.begQuantity);
            fsc.setEndUnitCost(tempEND.begUnitCost);
            fsc.setEndAmount(tempEND.begAmount);

            /*ADD TO LIST*/
            fishStockCardAccountingList.add(fsc);
        }

        /*TOTALING CALCULATION HERE*/
        for (FishStockCardAccounting fsc : fishStockCardAccountingList) {
            /*BEGINNING*/
            begTotalQty += fsc.getBegQuantity();
            begTotalAmount = begTotalAmount.add(fsc.getBegAmount());
            /*IN*/
            inTotalQty += fsc.getReceiveQuantity();
            inTotalAmount = inTotalAmount.add(fsc.getReceiveAmount());
            /*OUT*/
            outTotalQty += fsc.getWithdrawalQuantity();
            outTotalAmount = outTotalAmount.add(fsc.getWithdrawalAmount());
            /*ENDING*/
            endTotalQty += fsc.getEndQuantity();
            endTotalAmount = endTotalAmount.add(fsc.getEndAmount());
        }

        /*PUT TOTALING TO MODEL*/
        modelMap.put("begTotalQty", begTotalQty);
        modelMap.put("begTotalAmount", begTotalAmount);
        modelMap.put("inTotalQty", inTotalQty);
        modelMap.put("inTotalAmount", inTotalAmount);
        modelMap.put("outTotalQty", outTotalQty);
        modelMap.put("outTotalAmount", outTotalAmount);
        modelMap.put("endTotalQty", endTotalQty);
        modelMap.put("endTotalAmount", endTotalAmount);

        modelMap.put("fishSupplier", fs);
        modelMap.put("asOfDate", asOfDate);
        modelMap.put("fishStockCardAccountingList", fishStockCardAccountingList);

        return new ModelAndView("accounting/FishStockAccountingGenerate", "model", modelMap);
    }

    public boolean isLogin(HttpServletRequest request) {
        return request.getSession().getAttribute("user") != null;
    }

    public int countDayOfMonth(int month) {
        int result;

        result = 30 + ((month > 7 ? 0 : 1) + month) % 2;

        return result;
    }

    private BeginningBalance calculateBeginning(Integer fishSupplierId, Integer fishId, Date date) {
        BeginningBalance begBalance = new BeginningBalance();
        CurrencyRate cr = new CurrencyRate();

        List<FishTransaction> fishTransactionList = new ArrayList<FishTransaction>();
        List<FishTransaction> tempFishTransactionList = new ArrayList<FishTransaction>();

        Double tempQtyIN = 0d;
        Double tempQtyOUT = 0d;

        BigDecimal tempUnitCost = BigDecimal.ZERO;
        BigDecimal averageUnitCost = BigDecimal.ZERO;

        /*GET ALL FISH IN*/
        fishTransactionList = fishTransactionDao.findByTransactionIn(fishSupplierId, fishId, date);
        int size = fishTransactionList.size();
        for (int i = 0; i < size; i++) {
            /*SUMMARY ALL QUANTITY IN*/
            tempQtyIN += fishTransactionList.get(i).getQuantity();
            /*GET CURRENCY RATE BASED ON DATE AND CURRENCY CODE*/
            cr = currencyRateDao.findLatestByCurrencyCodeFromToAndDate("USD", fishTransactionList.get(i).getCurrencyCode(), date);
            tempUnitCost = tempUnitCost.add(fishTransactionList.get(i).getUnitCost().divide(cr.getRateValue(), 2, RoundingMode.HALF_UP));
        }

        if (size > 0) {
            averageUnitCost = tempUnitCost.divide(BigDecimal.valueOf(Long.valueOf(size)));
        } else {
            averageUnitCost = BigDecimal.ZERO;
        }

        /*GET ALL FISH OUT*/
        tempFishTransactionList = fishTransactionDao.findByTransactionOut(fishSupplierId, fishId, date);
        size = tempFishTransactionList.size();
        for (int i = 0; i < size; i++) {
            /*SUMMARY ALL QUANTITY IN*/
            tempQtyOUT += tempFishTransactionList.get(i).getQuantity();
        }

        /*GET RATE VALUE*/
        /*CONTRACT UNIT CURRENCY TO USD*/
        /*SUMMARY*/
        /*GET ENDING BALANCE*/
        begBalance.begQuantity = tempQtyIN - tempQtyOUT;
        /*GET UNIT COST*/
        begBalance.begUnitCost = averageUnitCost;
        /*GET BALANCE AMOUNT*/
        begBalance.begAmount = averageUnitCost.multiply(BigDecimal.valueOf(begBalance.begQuantity));

        return begBalance;
    }

    private BeginningBalance calculateFishIN(Integer fishSupplierId, Integer fishId, Date date) {
        BeginningBalance balance = new BeginningBalance();
        CurrencyRate cr = new CurrencyRate();
        Double tempQtyIN = 0d;

        BigDecimal tempUnitCost = BigDecimal.ZERO;
        BigDecimal averageUnitCost = BigDecimal.ZERO;

        List<FishTransaction> fishTransactionList = new ArrayList<FishTransaction>();
        /*GET ALL FISH IN TRANSACTION WITHIN DATE GIVEN*/
        fishTransactionList = fishTransactionDao.findByTransactionInThisMonth(fishSupplierId, fishId, date);
        int size = fishTransactionList.size();
        for (int i = 0; i < size; i++) {
            /*SUMMARY ALL QUANTITY IN*/
            tempQtyIN += fishTransactionList.get(i).getQuantity();
            /*GET CURRENCY RATE BASED ON DATE AND CURRENCY CODE*/
            cr = currencyRateDao.findLatestByCurrencyCodeFromToAndDate("USD", fishTransactionList.get(i).getCurrencyCode(), date);
            tempUnitCost = tempUnitCost.add(fishTransactionList.get(i).getUnitCost().divide(cr.getRateValue(), 2, RoundingMode.HALF_UP));
        }

        if (size > 0) {
            averageUnitCost = tempUnitCost.divide(BigDecimal.valueOf(Long.valueOf(size)), 2, RoundingMode.HALF_UP);
        } else {
            averageUnitCost = BigDecimal.ZERO;
        }

        balance.begQuantity = tempQtyIN;
        /*GET UNIT COST*/
        balance.begUnitCost = averageUnitCost;
        /*GET BALANCE AMOUNT*/
        balance.begAmount = averageUnitCost.multiply(BigDecimal.valueOf(tempQtyIN));

        return balance;
    }

    private BeginningBalance calculateFishOUT(Integer fishSupplierId, Integer fishId, Date date) {
        BeginningBalance balance = new BeginningBalance();
        Double tempQtyOUT = 0d;

        BigDecimal tempUnitCost = BigDecimal.ZERO;
        BigDecimal averageUnitCost = BigDecimal.ZERO;

        List<FishTransaction> fishTransactionList = new ArrayList<FishTransaction>();
        /*GET ALL FISH IN TRANSACTION WITHIN DATE GIVEN*/
        fishTransactionList = fishTransactionDao.findByTransactionOutThisMonth(fishSupplierId, fishId, date);
        int size = fishTransactionList.size();
        for (int i = 0; i < size; i++) {
            /*SUMMARY ALL QUANTITY OUT*/
            tempQtyOUT += fishTransactionList.get(i).getQuantity();
        }

        balance.begQuantity = tempQtyOUT;
        /*GET UNIT COST*/
        balance.begUnitCost = BigDecimal.ZERO;
        /*GET BALANCE AMOUNT*/
        balance.begAmount = BigDecimal.ZERO;

        return balance;
    }

    private class BeginningBalance {

        private Integer fishId;
        private Double begQuantity;
        private BigDecimal begUnitCost;
        private BigDecimal begAmount;

        public BeginningBalance() {
            this.fishId = 0;
            this.begQuantity = 0d;
            this.begUnitCost = BigDecimal.ZERO;
            this.begAmount = BigDecimal.ZERO;
        }

    }

}

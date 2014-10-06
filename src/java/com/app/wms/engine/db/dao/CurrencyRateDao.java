package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.CurrencyRate;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface CurrencyRateDao {

    public int insert(CurrencyRate cr);

    public CurrencyRate findByCurrency(String currencyCode);

    public CurrencyRate findByPurchase(int poCode);

    public CurrencyRate findLatestCurrency(Date rateDate, String currencyCode);

    public List<CurrencyRate> findAll();

    public CurrencyRate findLatestRate(String currencyCode);

    public CurrencyRate findLatestByCurrencyCodeAndDate(String currencyCode, Date date);

    public CurrencyRate findLatestByCurrencyCodeFromToAndDate(String currencyCodeFrom, String currencyCodeTo, Date date);
    
    public CurrencyRate findLatestByCurrencyCodeFromToDateAndType(String currencyCodeFrom, String currencyCodeTo, Date date, String currencyType);

    public List<CurrencyRate> findByCurrencyCodeAndDate(String currencyCode, Date newerDate);

    public List<CurrencyRate> findByCurrencyCodeAndDate(String currencyCode, Date newerDate, int page, int pageSize);

    public int ajaxMaxPage(String where, BigDecimal show);

    public List<CurrencyRate> ajaxSearch(String condition, String where, String order, int page, int show);

}

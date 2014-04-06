package com.app.wms.engine.db.dao;

import java.math.BigDecimal;
import java.util.Date;

public interface StockBalanceDao {

    public void insertOrUpdate(String productCode, Date date, BigDecimal begin, BigDecimal qty, int mode);

}

package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.Sws;
import com.app.wms.engine.db.dto.Ts;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface TsDao {

    public void insert(Ts t);

    public void updateStockInventory(String productCode, int qty);

    public List<Sws> findWhereNotInTs();

    public List<Ts> findAll(String module);

    public int ajaxMaxPage(BigDecimal show, String where);

    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);

    public List<Map<String, Object>> findSwsDtlForTs(String swsCode);

}

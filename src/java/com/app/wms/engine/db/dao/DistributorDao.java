package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.Distributor;
import java.math.BigDecimal;
import java.util.List;

public interface DistributorDao {
    public void insert(Distributor d);
    public void edit(Distributor d);
    public void delete(Distributor d);
    public List<Distributor> findAll();
    public int ajaxMaxPage(String where, BigDecimal show);
    public List<Distributor> ajaxSearch(String where, String order, int page, int show);
    public Distributor findId(int id);
}

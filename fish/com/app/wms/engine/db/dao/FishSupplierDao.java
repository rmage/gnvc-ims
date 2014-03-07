package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.wms.engine.db.dto.FishSupplier;
import com.app.wms.engine.db.exceptions.DaoException;

public interface FishSupplierDao {

	public int insert(FishSupplier dto);
	public void update(int id, FishSupplier dto) throws DaoException;
	public void delete(int id) throws DaoException;
	public FishSupplier findByPrimaryKey(int id) throws DaoException;
	public List<FishSupplier> findAll() throws DaoException;
	public List<FishSupplier> findAllActive() throws DaoException;
	public List<FishSupplier> findBySupplierName(String supplierName) throws DaoException;
    public List<FishSupplier> findAllAndPaging(int limit, int offset);
    public List<FishSupplier> searchAndPaging(String supplierName, int limit, int offset);
     public List<FishSupplier> findByCode(String code);
}

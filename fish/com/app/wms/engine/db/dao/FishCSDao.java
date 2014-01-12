package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.wms.engine.db.dto.FishStorage;
import com.app.wms.engine.db.exceptions.DaoException;

public interface FishCSDao {

	public int insert(FishStorage dto);
	public void update(int id, FishStorage dto) throws DaoException;
	public void delete(int id) throws DaoException;
	public FishStorage findByPrimaryKey(int id) throws DaoException;
	public List<FishStorage> findAll() throws DaoException;
	public FishStorage findByFishCode(String csCode) throws DaoException;
	public List<FishStorage> findAllActive() throws DaoException;
}

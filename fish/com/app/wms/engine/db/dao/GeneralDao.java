package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.wms.engine.db.exceptions.DaoException;

public interface GeneralDao<T> {
	public int insert(T dto);
	public void update(int id, T dto) throws DaoException;
	public void delete(int id) throws DaoException;
	public T findByPrimaryKey(int id) throws DaoException;
	public List<T> findAll() throws DaoException;
	public List<T> findAllActive() throws DaoException;
}

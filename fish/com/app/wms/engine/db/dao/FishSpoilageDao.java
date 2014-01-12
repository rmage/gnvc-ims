package com.app.wms.engine.db.dao;

import java.util.Date;
import java.util.List;

import com.app.wms.engine.db.dto.FishSpoilage;
import com.app.wms.engine.db.exceptions.DaoException;

public interface FishSpoilageDao {
	
	public int insert(FishSpoilage dto) throws DaoException;
	public void update(int id, FishSpoilage dto) throws DaoException;
	public void delete(int id) throws DaoException;
	public FishSpoilage findByPrimaryKey(int id) throws DaoException;
	public List<FishSpoilage> findAll() throws DaoException;
	public List<FishSpoilage> findByDateRange(Date dateFrom, Date dateTo) throws DaoException;
	public List<FishSpoilage> findByBatchNumber(String batchNumber) throws DaoException;
	public List<FishSpoilage> findAllDistinct();
	public List<FishSpoilage> findAllDistinctAndPaging(int limit, int offset);
	public List<FishSpoilage> findAllForReport(int vesselId, Date dateShift, String timeShift);
}

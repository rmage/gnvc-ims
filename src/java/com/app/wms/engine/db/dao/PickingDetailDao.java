package com.app.wms.engine.db.dao;

import java.util.List;
import com.app.web.engine.search.PickingDetailSearch;
import com.app.wms.engine.db.dto.Picking;
import com.app.wms.engine.db.dto.PickingDetail;
import com.app.wms.engine.db.dto.PickingDetailPk;
import com.app.wms.engine.db.exceptions.PickingDetailDaoException;

public interface PickingDetailDao 
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return PickingDetailPk
	 */
	public PickingDetailPk insert(PickingDetail dto);

	/** 
	 * Updates a single row in the PickingDetail table.
	 */
	public void update(PickingDetail dto) throws PickingDetailDaoException;

	/** 
	 * Deletes a single row in the PickingDetail table.
	 */
	public void delete(PickingDetailPk pk) throws PickingDetailDaoException;

	/** 
	 * Returns all rows from the PickingDetail table that match the criteria 'PickingId = :PickingId'.
	 */
	public PickingDetail findByPrimaryKey(String PickingId) throws PickingDetailDaoException;

	/** 
	 * Returns all rows from the PickingDetail table that match the criteria ''.
	 */
	public List<PickingDetail> findAll() throws PickingDetailDaoException;

	/** 
	 * Returns all rows from the PickingDetail table that match the criteria 'PickingId = :PickingId'.
	 */
	public List<PickingDetail> findWherePickingIdEquals(String PickingId) throws PickingDetailDaoException;

	/** 
	 * Returns all rows from the PickingDetail table that match the criteria 'NAME = :name'.
	 */
	public List<PickingDetail> findWhereNameEquals(String name) throws PickingDetailDaoException;

	/** 
	 * Returns the rows from the PickingDetail table that matches the specified primary-key value.
	 */
	public PickingDetail findByPrimaryKey(PickingDetailPk pk) throws PickingDetailDaoException;

	public List<PickingDetail> findByCriteriaLimit(PickingDetailSearch criteria, int start, int end) throws PickingDetailDaoException;

	public List<PickingDetail> findPickingDetailList(String salesOrderNo) throws PickingDetailDaoException;

	public List<PickingDetail> findDetail(PickingDetail pid) throws PickingDetailDaoException;
}

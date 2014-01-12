package com.app.wms.engine.db.dao;

import java.util.Collection;
import java.util.List;

import com.app.web.engine.search.PickingSearch;
import com.app.wms.engine.db.dto.Picking;
import com.app.wms.engine.db.dto.PickingDetail;
import com.app.wms.engine.db.dto.PickingPk;
import com.app.wms.engine.db.dto.SalesOrder;
import com.app.wms.engine.db.exceptions.PickingDaoException;
import com.app.wms.engine.db.exceptions.PickingDetailDaoException;

public interface PickingDao 
{

	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return PickingPk
	 */
	public PickingPk insert(Picking dto);

	/** 
	 * Updates a single row in the Picking table.
	 */
	public void update(PickingPk pk, Picking dto) throws PickingDaoException;

	/** 
	 * Deletes a single row in the Picking table.
	 */
	public void delete(PickingPk pk) throws PickingDaoException;

	/** 
	 * Returns all rows from the Picking table that match the criteria 'Picking_CODE = :PickingCode'.
	 */
	public Picking findByPrimaryKey(String PickingCode) throws PickingDaoException;

	/** 
	 * Returns all rows from the Picking table that match the criteria ''.
	 */
	public List<Picking> findAll() throws PickingDaoException;
	
	public List<Picking> findPickingPaging(Picking pi, int page) throws PickingDaoException;


	/** 
	 * Returns all rows from the Picking table that match the criteria 'Picking_CODE = :PickingCode'.
	 */
	public List<Picking> findWherePickingCodeEquals(String PickingCode) throws PickingDaoException;

	/** 
	 * Returns all rows from the Picking table that match the criteria 'NAME = :name'.
	 */
	public List<Picking> findWhereNameEquals(String name) throws PickingDaoException;
	
	public List<Picking> findSalesOrderPickingList(Picking p) throws PickingDaoException;

	/** 
	 * Returns the rows from the Picking table that matches the specified primary-key value.
	 */
	public Picking findByPrimaryKey(PickingPk pk) throws PickingDaoException;

	public List<Picking> findByCriteriaLimit(PickingSearch criteria, int start, int end) throws PickingDaoException;

	public List<PickingDetail> findProductLocation(PickingDetail pickingDetail)throws PickingDetailDaoException;
	
	public List<PickingDetail> findSearchProductLocation(PickingDetail pickingDetail)throws PickingDetailDaoException;
	
	//public List<PickingDetail> findProductQuantity(PickingDetail pickingDetail)throws PickingDetailDaoException;

	public List<PickingDetail> findProductPickingQuantity(PickingDetail pickingDetail)throws PickingDetailDaoException;

	public List<Picking> findProductDelivery(Picking pic) throws PickingDaoException;


}

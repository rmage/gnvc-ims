package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.web.engine.search.SalesOrderDetailSearch;
import com.app.web.engine.search.SalesOrderSearch;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.dto.SalesOrder;
import com.app.wms.engine.db.dto.SalesOrderDetail;
import com.app.wms.engine.db.dto.SalesOrderDetailPk;
import com.app.wms.engine.db.dto.SalesOrderPk;
import com.app.wms.engine.db.exceptions.PutawayDetailDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDetailDaoException;

public interface SalesOrderDetailDao 
{

	/**
	 * Method 'insert'
	 * 
	 * @return SalesOrderDetailPk
	 */
	public SalesOrderDetailPk insert(SalesOrderDetail dso);

	/** 
	 * Updates a single row in the SalesOrderDetail table.
	 */
	public void update(SalesOrderDetailPk pk, SalesOrderDetail dto) throws SalesOrderDetailDaoException;

	/** 
	 * Deletes a single row in the SalesOrderDetail table.
	 */
	public void delete(SalesOrderDetailPk pk) throws SalesOrderDetailDaoException;

	/** 
	 * Returns all rows from the SalesOrderDetail table that match the criteria 'SalesOrderDetail_CODE = :SalesOrderDetailCode'.
	 */
	public SalesOrderDetail findByPrimaryKey(String SalesOrderNumber) throws SalesOrderDetailDaoException;

	/** 
	 * Returns all rows from the SalesOrderDetail table that match the criteria ''.
	 */
	public List<SalesOrderDetail> findAll() throws SalesOrderDetailDaoException;
	
	public List<SalesOrderDetail> findDetail(SalesOrderDetail sod) throws SalesOrderDetailDaoException;

	/** 
	 * Returns all rows from the SalesOrderDetail table that match the criteria 'NAME = :name'.
	 */
	public List<SalesOrderDetail> findWhereNameEquals(String name) throws SalesOrderDetailDaoException;

	/** 
	 * Returns the rows from the SalesOrderDetail table that matches the specified primary-key value.
	 */
	public SalesOrderDetail findByPrimaryKey(SalesOrderDetailPk pk) throws SalesOrderDetailDaoException;

	public List<PutawayDetail> findWhereProductCodeBalanceEquals(PutawayDetail pud) throws PutawayDetailDaoException;

}

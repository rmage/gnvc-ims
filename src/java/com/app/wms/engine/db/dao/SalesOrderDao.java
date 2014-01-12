package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.web.engine.search.SalesOrderSearch;
import com.app.web.engine.search.WarehouseSearch;
import com.app.wms.engine.db.dto.PickingDetail;
import com.app.wms.engine.db.dto.SalesOrder;
import com.app.wms.engine.db.dto.SalesOrderDetail;
import com.app.wms.engine.db.dto.SalesOrderPk;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.exceptions.PickingDetailDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDetailDaoException;
import com.app.wms.engine.db.exceptions.WhDaoException;

public interface SalesOrderDao 
{


	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return SalesOrderPk
	 */
	public SalesOrderPk insert(SalesOrder dto);

	/** 
	 * Updates a single row in the SalesOrder table.
	 */
	public void update(SalesOrderPk pk, SalesOrder dto) throws SalesOrderDaoException;

	/** 
	 * Deletes a single row in the SalesOrder table.
	 */
	public void delete(SalesOrderPk pk) throws SalesOrderDaoException;

	/** 
	 * Returns all rows from the SalesOrder table that match the criteria 'SalesOrder_CODE = :SalesOrderCode'.
	 */
	public SalesOrder findByPrimaryKey(String SalesOrderNumber) throws SalesOrderDaoException;

	/** 
	 * Returns all rows from the SalesOrder table that match the criteria ''.
	 */
	public List<SalesOrder> findAll() throws SalesOrderDaoException;
	
	public List<SalesOrder> findSalesOrderDeliveryProduct(SalesOrder so) throws SalesOrderDaoException;
	
	public List<SalesOrder> findSalesOrder() throws SalesOrderDaoException;
	
	public List<SalesOrder> findSalesOrderPaging(SalesOrder so, int page) throws SalesOrderDaoException;
	
	public List<SalesOrder> findSalesOrderPickList() throws SalesOrderDaoException;

	/** 
	 * Returns the rows from the SalesOrder table that matches the specified primary-key value.
	 */
	public SalesOrder findByPrimaryKey(SalesOrderPk pk) throws SalesOrderDaoException;
	
	public List<SalesOrder> findByCriteriaLimit(SalesOrderSearch criteria, int start, int end) throws SalesOrderDaoException;


	public List<PickingDetail> findProductLocation(PickingDetail pickingDetail)throws PickingDetailDaoException;
	
	public List<PickingDetail> findSearchProductLocation(PickingDetail pickingDetail)throws PickingDetailDaoException;
	
	public List<PickingDetail> findProductQuantity(PickingDetail pickingDetail)throws PickingDetailDaoException;
	
	public List<SalesOrder> findSalesOrder(SalesOrder so) throws SalesOrderDaoException;

	public List<SalesOrder> findSO(SalesOrder so) throws SalesOrderDaoException;
	
	List<SalesOrder> findSONumberOnDeliveryOrder(SalesOrder so) throws SalesOrderDaoException;

}

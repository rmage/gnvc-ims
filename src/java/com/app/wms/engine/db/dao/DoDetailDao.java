package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.DoDetailDao;
import com.app.wms.engine.db.dto.DoDetail;
import com.app.wms.engine.db.exceptions.DoDetailDaoException;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface DoDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(DoDetail dto);

	/** 
	 * Returns all rows from the do_detail table that match the criteria ''.
	 */
	public List<DoDetail> findAll() throws DoDetailDaoException;

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'delivery_no = :deliveryNo'.
	 */
	public List<DoDetail> findWhereDeliveryNoEquals(String deliveryNo) throws DoDetailDaoException;

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'so_number = :soNumber'.
	 */
	public List<DoDetail> findWhereSoNumberEquals(String soNumber) throws DoDetailDaoException;

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'picking_id = :pickingId'.
	 */
	public List<DoDetail> findWherePickingIdEquals(String pickingId) throws DoDetailDaoException;

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'product_id = :productId'.
	 */
	public List<DoDetail> findWhereProductIdEquals(String productId) throws DoDetailDaoException;

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'product_code = :productCode'.
	 */
	public List<DoDetail> findWhereProductCodeEquals(String productCode) throws DoDetailDaoException;

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'product_name = :productName'.
	 */
	public List<DoDetail> findWhereProductNameEquals(String productName) throws DoDetailDaoException;

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'qty_delivery = :qtyDelivery'.
	 */
	public List<DoDetail> findWhereQtyDeliveryEquals(float qtyDelivery) throws DoDetailDaoException;

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'unit_code = :unitCode'.
	 */
	public List<DoDetail> findWhereUnitCodeEquals(String unitCode) throws DoDetailDaoException;

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'transporter_type = :transporterType'.
	 */
	public List<DoDetail> findWhereTransporterTypeEquals(String transporterType) throws DoDetailDaoException;

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'transporter_name = :transporterName'.
	 */
	public List<DoDetail> findWhereTransporterNameEquals(String transporterName) throws DoDetailDaoException;

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'user_id = :userId'.
	 */
	public List<DoDetail> findWhereUserIdEquals(String userId) throws DoDetailDaoException;

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'corp_id = :corpId'.
	 */
	public List<DoDetail> findWhereCorpIdEquals(String corpId) throws DoDetailDaoException;

	/** 
	 * Returns all rows from the do_detail table that match the criteria 'wh_code = :whCode'.
	 */
	public List<DoDetail> findWhereWhCodeEquals(String whCode) throws DoDetailDaoException;
	
	public DoDetail findByPrimaryKey (String DeliveryOrderNumber) throws DoDetailDaoException;

	public List<DoDetail> findDetail (DoDetail dod) throws DoDetailDaoException;

}

package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.ReplenishDetailDao;
import com.app.wms.engine.db.dto.ReplenishDetail;
import com.app.wms.engine.db.exceptions.ReplenishDetailDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface ReplenishDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(ReplenishDetail dto);

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria ''.
	 */
	public List<ReplenishDetail> findAll() throws ReplenishDetailDaoException;

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'replenish_no = :replenishNo'.
	 */
	public List<ReplenishDetail> findWhereReplenishNoEquals(String replenishNo) throws ReplenishDetailDaoException;

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'from_location = :fromLocation'.
	 */
	public List<ReplenishDetail> findWhereFromLocationEquals(String fromLocation) throws ReplenishDetailDaoException;

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'to_location = :toLocation'.
	 */
	public List<ReplenishDetail> findWhereToLocationEquals(String toLocation) throws ReplenishDetailDaoException;

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'product_id = :productId'.
	 */
	public List<ReplenishDetail> findWhereProductIdEquals(String productId) throws ReplenishDetailDaoException;

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'product_code = :productCode'.
	 */
	public List<ReplenishDetail> findWhereProductCodeEquals(String productCode) throws ReplenishDetailDaoException;

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'product_name = :productName'.
	 */
	public List<ReplenishDetail> findWhereProductNameEquals(String productName) throws ReplenishDetailDaoException;

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'received_date = :receivedDate'.
	 */
	public List<ReplenishDetail> findWhereReceivedDateEquals(Date receivedDate) throws ReplenishDetailDaoException;

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'expired_date = :expiredDate'.
	 */
	public List<ReplenishDetail> findWhereExpiredDateEquals(Date expiredDate) throws ReplenishDetailDaoException;

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'unit_code = :unitCode'.
	 */
	public List<ReplenishDetail> findWhereUnitCodeEquals(String unitCode) throws ReplenishDetailDaoException;

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'qty_replenish = :qtyReplenish'.
	 */
	public List<ReplenishDetail> findWhereQtyReplenishEquals(int qtyReplenish) throws ReplenishDetailDaoException;

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'balance = :balance'.
	 */
	public List<ReplenishDetail> findWhereBalanceEquals(int balance) throws ReplenishDetailDaoException;

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'confirmed_by = :confirmedBy'.
	 */
	public List<ReplenishDetail> findWhereConfirmedByEquals(String confirmedBy) throws ReplenishDetailDaoException;

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'remarks = :remarks'.
	 */
	public List<ReplenishDetail> findWhereRemarksEquals(String remarks) throws ReplenishDetailDaoException;

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'user_id = :userId'.
	 */
	public List<ReplenishDetail> findWhereUserIdEquals(String userId) throws ReplenishDetailDaoException;

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'corp_id = :corpId'.
	 */
	public List<ReplenishDetail> findWhereCorpIdEquals(String corpId) throws ReplenishDetailDaoException;

	/** 
	 * Returns all rows from the replenish_detail table that match the criteria 'wh_code = :whCode'.
	 */
	public List<ReplenishDetail> findWhereWhCodeEquals(String whCode) throws ReplenishDetailDaoException;
        
        /* added by FYA : 23 May 2013 */
        public void confirmDocument(String replenishNo, String userId) throws ReplenishDetailDaoException;

}

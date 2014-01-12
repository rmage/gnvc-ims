package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.PutawayDetailDao;
import com.app.wms.engine.db.dto.PickingDetail;
import com.app.wms.engine.db.dto.PickingDetailPk;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.exceptions.PickingDetailDaoException;
import com.app.wms.engine.db.exceptions.PutawayDetailDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface PutawayDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(PutawayDetail dto);
	
	/** 
	 * Updates a single row in the PickingDetail table.
	 */
	public void update(PutawayDetail dto) throws PickingDetailDaoException;
	
	public void updateBalance(PutawayDetail dto) throws PickingDetailDaoException;
	
	public void updateBalance1(PutawayDetail dto) throws PickingDetailDaoException;
	
	public void updateQtyPut(PutawayDetail dto) throws PickingDetailDaoException;

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria ''.
	 */
	public List<PutawayDetail> findAll() throws PutawayDetailDaoException;

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'putaway_id = :putawayId'.
	 */
	public List<PutawayDetail> findWherePutawayIdEquals(String putawayId) throws PutawayDetailDaoException;

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'product_id = :productId'.
	 */
	public List<PutawayDetail> findWhereProductIdEquals(String productId) throws PutawayDetailDaoException;

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'received_date = :receivedDate'.
	 */
	public List<PutawayDetail> findWhereReceivedDateEquals(Date receivedDate) throws PutawayDetailDaoException;

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'expired_date = :expiredDate'.
	 */
	public List<PutawayDetail> findWhereExpiredDateEquals(Date expiredDate) throws PutawayDetailDaoException;

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'unit_code = :unitCode'.
	 */
	public List<PutawayDetail> findWhereUnitCodeEquals(String unitCode) throws PutawayDetailDaoException;

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'qty_order = :qtyOrder'.
	 */
	public List<PutawayDetail> findWhereQtyOrderEquals(int qtyOrder) throws PutawayDetailDaoException;

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'qty_put = :qtyPut'.
	 */
	public List<PutawayDetail> findWhereQtyPutEquals(int qtyPut) throws PutawayDetailDaoException;

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'balance = :balance'.
	 */
	public List<PutawayDetail> findWhereBalanceEquals(int balance) throws PutawayDetailDaoException;

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'user_id = :userId'.
	 */
	public List<PutawayDetail> findWhereUserIdEquals(String userId) throws PutawayDetailDaoException;

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'corp_id = :corpId'.
	 */
	public List<PutawayDetail> findWhereCorpIdEquals(String corpId) throws PutawayDetailDaoException;

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'location_code = :locationCode'.
	 */
	public List<PutawayDetail> findWhereLocationCodeEquals(String locationCode) throws PutawayDetailDaoException;

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'wh_code = :whCode'.
	 */
	public List<PutawayDetail> findWhereWhCodeEquals(String whCode) throws PutawayDetailDaoException;

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'id = :id'.
	 */
	public List<PutawayDetail> findWhereIdEquals(int id) throws PutawayDetailDaoException;

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'lotid = :lotid'.
	 */
	public List<PutawayDetail> findWhereLotidEquals(String lotid) throws PutawayDetailDaoException;

	/** 
	 * Returns all rows from the putaway_detail table that match the criteria 'product_code = :productCode'.
	 */
	public List<PutawayDetail> findWhereProductCodeEquals(String productCode) throws PutawayDetailDaoException;

	public List<PutawayDetail> findWhereProductCodeEquals(PutawayDetail pud) throws PutawayDetailDaoException;
	
	public List<PutawayDetail> findWhereProductCodeEquals2(PutawayDetail pud) throws PutawayDetailDaoException;

	public List<PutawayDetail> findWhereProductLocation(PutawayDetail pud) throws PutawayDetailDaoException;

	public List<PutawayDetail> findWhereProductModel(PutawayDetail pud) throws PutawayDetailDaoException;
	
	public List<PutawayDetail> findBalanceLocation(PutawayDetail pud) throws PutawayDetailDaoException;
	
	public List<PutawayDetail> findMaxId(PutawayDetail pud) throws PutawayDetailDaoException;
	
	public List<PutawayDetail> findMaxBalance(PutawayDetail pud) throws PutawayDetailDaoException;
	
	public List<PutawayDetail> findMaxBalance1(PutawayDetail pud) throws PutawayDetailDaoException;

	/* added by FYA : 14 May 2013 */
    public List<PutawayDetail> findForReplenishFrom(String whCode, String sidx, String sord) throws PutawayDetailDaoException;
    
    /* added by FYA : 22 May 2013 */
    public List<PutawayDetail> findForReplenishTo(String whCode, String not, String product, String sidx, String sord) throws PutawayDetailDaoException;
    
    /* added by FYA : 10 June 2013 */
    public List<PutawayDetail> findForReplenishToX(String whCode, String corpId, String product, String sidx, String sord) throws PutawayDetailDaoException;
    
    /* added by FYA : 23 May 2013 */
    public PutawayDetail findExactLocationCode(String locationCode, String whCode, String corpId) throws PutawayDetailDaoException;
    
    /* added by FYA : 14 June 2013 */
    public PutawayDetail findExactLocationCodeC(String locationCode, String userId, String corpId) throws PutawayDetailDaoException;
}

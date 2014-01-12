package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.KittingDetailDao;
import com.app.wms.engine.db.dto.Kitting;
import com.app.wms.engine.db.dto.KittingDetail;
import com.app.wms.engine.db.dto.KittingDetailPk;
import com.app.wms.engine.db.exceptions.KittingDetailDaoException;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface KittingDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param KittingDetailPk
	 */
	public KittingDetailPk insert(KittingDetail dto);
	
	/** 
	 * Updates a single row in the KittingDetail table.
	 */
	public void update(KittingDetailPk pk, KittingDetail dto) throws KittingDetailDaoException;

	/** 
	 * Deletes a single row in the KittingDetail table.
	 */
	public void delete(KittingDetailPk pk) throws KittingDetailDaoException;

	/** 
	 * Returns all rows from the KittingDetail table that match the criteria 'KittingDetailNo = :KittingDetailNo'.
	 */
	public KittingDetail findByPrimaryKey(String KittingNo) throws KittingDetailDaoException;


	/** 
	 * Returns all rows from the kitting_detail table that match the criteria ''.
	 */
	public List<KittingDetail> findAll() throws KittingDetailDaoException;

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'kitting_no = :kittingNo'.
	 */
	public List<KittingDetail> findWhereKittingNoEquals(String kittingNo) throws KittingDetailDaoException;

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'product_id = :productId'.
	 */
	public List<KittingDetail> findWhereProductIdEquals(String productId) throws KittingDetailDaoException;

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'product_code = :productCode'.
	 */
	public List<KittingDetail> findWhereProductCodeEquals(String productCode) throws KittingDetailDaoException;

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'product_name = :productName'.
	 */
	public List<KittingDetail> findWhereProductNameEquals(String productName) throws KittingDetailDaoException;

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'unit_code = :unitCode'.
	 */
	public List<KittingDetail> findWhereUnitCodeEquals(String unitCode) throws KittingDetailDaoException;

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'qty_kitting = :qtyKitting'.
	 */
	public List<KittingDetail> findWhereQtyKittingEquals(int qtyKitting) throws KittingDetailDaoException;

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'user_id = :userId'.
	 */
	public List<KittingDetail> findWhereUserIdEquals(String userId) throws KittingDetailDaoException;

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'corp_id = :corpId'.
	 */
	public List<KittingDetail> findWhereCorpIdEquals(String corpId) throws KittingDetailDaoException;

	/** 
	 * Returns all rows from the kitting_detail table that match the criteria 'wh_code = :whCode'.
	 */
	public List<KittingDetail> findWhereWhCodeEquals(String whCode) throws KittingDetailDaoException;
	
	/** 
	 * Returns the rows from the KittingDetail table that matches the specified primary-key value.
	 */
	public KittingDetail findByPrimaryKey(KittingDetailPk pk) throws KittingDetailDaoException;

	public List<KittingDetail> findDetail(KittingDetail kd) throws KittingDetailDaoException;

	public List<KittingDetail> findKittingDetailList(String salesOrderNo) throws KittingDetailDaoException;
}

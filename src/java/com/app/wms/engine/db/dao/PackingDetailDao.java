package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.PackingDetailDao;
import com.app.wms.engine.db.dto.PackingDetail;
import com.app.wms.engine.db.dto.PackingDetailPk;
import com.app.wms.engine.db.exceptions.PackingDetailDaoException;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface PackingDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param PackingDetailPk
	 */
	public PackingDetailPk insert(PackingDetail dto);
	
	/** 
	 * Updates a single row in the packing_detail table.
	 */
	public void update(PackingDetailPk pk, PackingDetail dto) throws PackingDetailDaoException;

	/** 
	 * Deletes a single row in the packing_detail table.
	 */
	public void delete(PackingDetailPk pk) throws PackingDetailDaoException;

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'packing_no = :packing_no'.
	 */
	public PackingDetail findByPrimaryKey(String packingNo) throws PackingDetailDaoException;

	/** 
	 * Returns the rows from the packing_detail table that matches the specified primary-key value.
	 */
	public PackingDetail findByPrimaryKey(PackingDetailPk pk) throws PackingDetailDaoException;
	
	/** 
	 * Returns all rows from the packing_detail table that match the criteria ''.
	 */
	public List<PackingDetail> findAll() throws PackingDetailDaoException;

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'packing_no = :packingNo'.
	 */
	public List<PackingDetail> findWherePackingNoEquals(String packingNo) throws PackingDetailDaoException;

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'so_number = :soNumber'.
	 */
	public List<PackingDetail> findWhereSoNumberEquals(String soNumber) throws PackingDetailDaoException;

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'picking_id = :pickingId'.
	 */
	public List<PackingDetail> findWherePickingIdEquals(String pickingId) throws PackingDetailDaoException;

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'kitting_no = :kittingNo'.
	 */
	public List<PackingDetail> findWhereKittingNoEquals(String kittingNo) throws PackingDetailDaoException;

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'product_id = :productId'.
	 */
	public List<PackingDetail> findWhereProductIdEquals(String productId) throws PackingDetailDaoException;

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'product_code = :productCode'.
	 */
	public List<PackingDetail> findWhereProductCodeEquals(String productCode) throws PackingDetailDaoException;

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'product_name = :productName'.
	 */
	public List<PackingDetail> findWhereProductNameEquals(String productName) throws PackingDetailDaoException;

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'unitcode = :unitcode'.
	 */
	public List<PackingDetail> findWhereUnitcodeEquals(String unitcode) throws PackingDetailDaoException;

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'quantity = :quantity'.
	 */
	public List<PackingDetail> findWhereQuantityEquals(int quantity) throws PackingDetailDaoException;

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'user_id = :userId'.
	 */
	public List<PackingDetail> findWhereUserIdEquals(String userId) throws PackingDetailDaoException;

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'corp_id = :corpId'.
	 */
	public List<PackingDetail> findWhereCorpIdEquals(String corpId) throws PackingDetailDaoException;

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'wh_code = :whCode'.
	 */
	public List<PackingDetail> findWhereWhCodeEquals(String whCode) throws PackingDetailDaoException;

	public List<PackingDetail> findDetail(PackingDetail pd) throws PackingDetailDaoException;

}

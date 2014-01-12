package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.WhLocationDetailDao;
import com.app.wms.engine.db.dto.WhLocationDetail;
import com.app.wms.engine.db.dto.WhLocationDetailPk;
import com.app.wms.engine.db.exceptions.WhLocationDetailDaoException;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface WhLocationDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return WhLocationDetailPk
	 */
	public WhLocationDetailPk insert(WhLocationDetail dto);

	/** 
	 * Updates a single row in the wh_location_detail table.
	 */
	public void update(WhLocationDetailPk pk, WhLocationDetail dto) throws WhLocationDetailDaoException;

	/** 
	 * Deletes a single row in the wh_location_detail table.
	 */
	public void delete(WhLocationDetailPk pk) throws WhLocationDetailDaoException;

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'id = :id'.
	 */
	public WhLocationDetail findByPrimaryKey(int id) throws WhLocationDetailDaoException;

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria ''.
	 */
	public List<WhLocationDetail> findAll() throws WhLocationDetailDaoException;

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'id = :id'.
	 */
	public List<WhLocationDetail> findWhereIdEquals(int id) throws WhLocationDetailDaoException;

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'location_id = :locationId'.
	 */
	public List<WhLocationDetail> findWhereLocationIdEquals(String locationId) throws WhLocationDetailDaoException;

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'product_id = :productId'.
	 */
	public List<WhLocationDetail> findWhereProductIdEquals(String productId) throws WhLocationDetailDaoException;

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'product_code = :productCode'.
	 */
	public List<WhLocationDetail> findWhereProductCodeEquals(String productCode) throws WhLocationDetailDaoException;

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'product_name = :productName'.
	 */
	public List<WhLocationDetail> findWhereProductNameEquals(String productName) throws WhLocationDetailDaoException;

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'unit_code = :unitCode'.
	 */
	public List<WhLocationDetail> findWhereUnitCodeEquals(String unitCode) throws WhLocationDetailDaoException;

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'product_category = :productCategory'.
	 */
	public List<WhLocationDetail> findWhereProductCategoryEquals(String productCategory) throws WhLocationDetailDaoException;

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'user_id = :userId'.
	 */
	public List<WhLocationDetail> findWhereUserIdEquals(String userId) throws WhLocationDetailDaoException;

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'corp_id = :corpId'.
	 */
	public List<WhLocationDetail> findWhereCorpIdEquals(String corpId) throws WhLocationDetailDaoException;

	/** 
	 * Returns all rows from the wh_location_detail table that match the criteria 'wh_code = :whCode'.
	 */
	public List<WhLocationDetail> findWhereWhCodeEquals(String whCode) throws WhLocationDetailDaoException;

	/** 
	 * Returns the rows from the wh_location_detail table that matches the specified primary-key value.
	 */
	public WhLocationDetail findByPrimaryKey(WhLocationDetailPk pk) throws WhLocationDetailDaoException;

}

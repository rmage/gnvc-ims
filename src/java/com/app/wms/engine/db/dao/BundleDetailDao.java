package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.BundleDetailDao;
import com.app.wms.engine.db.dto.BundleDetail;
import com.app.wms.engine.db.exceptions.BundleDetailDaoException;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface BundleDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(BundleDetail dto);

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria ''.
	 */
	public List<BundleDetail> findAll() throws BundleDetailDaoException;

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'bundle_code = :bundleCode'.
	 */
	public List<BundleDetail> findWhereBundleCodeEquals(String bundleCode) throws BundleDetailDaoException;

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'product_id = :productId'.
	 */
	public List<BundleDetail> findWhereProductIdEquals(String productId) throws BundleDetailDaoException;

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'product_code = :productCode'.
	 */
	public List<BundleDetail> findWhereProductCodeEquals(String productCode) throws BundleDetailDaoException;

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'product_name = :productName'.
	 */
	public List<BundleDetail> findWhereProductNameEquals(String productName) throws BundleDetailDaoException;

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'unit_code = :unitCode'.
	 */
	public List<BundleDetail> findWhereUnitCodeEquals(String unitCode) throws BundleDetailDaoException;

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'qty_bundle = :qtyBundle'.
	 */
	public List<BundleDetail> findWhereQtyBundleEquals(int qtyBundle) throws BundleDetailDaoException;

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'user_id = :userId'.
	 */
	public List<BundleDetail> findWhereUserIdEquals(String userId) throws BundleDetailDaoException;

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'corp_id = :corpId'.
	 */
	public List<BundleDetail> findWhereCorpIdEquals(String corpId) throws BundleDetailDaoException;

	/** 
	 * Returns all rows from the bundle_detail table that match the criteria 'wh_code = :whCode'.
	 */
	public List<BundleDetail> findWhereWhCodeEquals(String whCode) throws BundleDetailDaoException;
	
	public List<BundleDetail> findWhereBundleCode(BundleDetail bud) throws BundleDetailDaoException;
	
	public BundleDetail findByPrimaryKey (String bundleCode) throws BundleDetailDaoException; 
	
	public List <BundleDetail> findDetail (BundleDetail bud) throws BundleDetailDaoException;


}

package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.BundleDao;
import com.app.wms.engine.db.dto.Bundle;
import com.app.wms.engine.db.dto.BundlePk;
import com.app.wms.engine.db.exceptions.BundleDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface BundleDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return BundlePk
	 */
	public BundlePk insert(Bundle dto);

	/** 
	 * Updates a single row in the bundle table.
	 */
	public void update(BundlePk pk, Bundle dto) throws BundleDaoException;

	/** 
	 * Deletes a single row in the bundle table.
	 */
	public void delete(BundlePk pk) throws BundleDaoException;

	/** 
	 * Returns all rows from the bundle table that match the criteria 'bundle_code = :bundleCode'.
	 */
	public Bundle findByPrimaryKey(String bundleCode) throws BundleDaoException;

	/** 
	 * Returns all rows from the bundle table that match the criteria ''.
	 */
	public List<Bundle> findAll() throws BundleDaoException;

	/** 
	 * Returns all rows from the bundle table that match the criteria 'bundle_code = :bundleCode'.
	 */
	public List<Bundle> findWhereBundleCodeEquals(String bundleCode) throws BundleDaoException;

	/** 
	 * Returns all rows from the bundle table that match the criteria 'bundle_name = :bundleName'.
	 */
	public List<Bundle> findWhereBundleNameEquals(String bundleName) throws BundleDaoException;

	/** 
	 * Returns all rows from the bundle table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Bundle> findWhereCreatedByEquals(String createdBy) throws BundleDaoException;

	/** 
	 * Returns all rows from the bundle table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Bundle> findWhereCreatedDateEquals(Date createdDate) throws BundleDaoException;

	/** 
	 * Returns all rows from the bundle table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Bundle> findWhereUpdatedByEquals(String updatedBy) throws BundleDaoException;

	/** 
	 * Returns all rows from the bundle table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Bundle> findWhereUpdatedDateEquals(Date updatedDate) throws BundleDaoException;

	/** 
	 * Returns the rows from the bundle table that matches the specified primary-key value.
	 */
	public Bundle findByPrimaryKey(BundlePk pk) throws BundleDaoException;
	
	public List<Bundle> findBundlingPaging(Bundle b, int page) throws BundleDaoException;
	
	public List<Bundle> findBundling(Bundle b) throws BundleDaoException;

}

package com.app.wms.engine.db.dao;

import com.app.web.engine.search.WarehouseLocationSearch;
import com.app.wms.engine.db.dao.WhLocationDao;
import com.app.wms.engine.db.dto.WhLocation;
import com.app.wms.engine.db.dto.WhLocationPk;
import com.app.wms.engine.db.exceptions.WhLocationDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface WhLocationDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return WhLocationPk
	 */
	public WhLocationPk insert(WhLocation dto);

	/** 
	 * Updates a single row in the wh_location table.
	 */
	public void update(WhLocationPk pk, WhLocation dto) throws WhLocationDaoException;

	/** 
	 * Deletes a single row in the wh_location table.
	 */
	public void delete(WhLocationPk pk) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'id = :id'.
	 */
	public WhLocation findByPrimaryKey(String locationId) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria ''.
	 */
	public List<WhLocation> findAll() throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'id = :id'.
	 */
	public List<WhLocation> findWhereIdEquals(int id) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'location_id = :locationId'.
	 */
	public List<WhLocation> findWhereLocationIdEquals(String locationId) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'location_id = :locationId'.
	 */
	public List<WhLocation> findWhereLocationIdEquals(WhLocation location, String locationId) throws WhLocationDaoException;

	
	/** 
	 * Returns all rows from the wh_location table that match the criteria 'location_code = :locationCode'.
	 */
	public List<WhLocation> findWhereLocationCodeEquals(String locationCode) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'location_name = :locationName'.
	 */
	public List<WhLocation> findWhereLocationNameEquals(String locationName) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'location_type = :locationType'.
	 */
	public List<WhLocation> findWhereLocationTypeEquals(String locationType) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'min_product = :minProduct'.
	 */
	public List<WhLocation> findWhereMinProductEquals(int minProduct) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'max_product = :maxProduct'.
	 */
	public List<WhLocation> findWhereMaxProductEquals(int maxProduct) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'locating_area = :locatingArea'.
	 */
	public List<WhLocation> findWhereLocatingAreaEquals(String locatingArea) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'locating_zone = :locatingZone'.
	 */
	public List<WhLocation> findWhereLocatingZoneEquals(String locatingZone) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'location_bay = :locationBay'.
	 */
	public List<WhLocation> findWhereLocationBayEquals(String locationBay) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'location_level = :locationLevel'.
	 */
	public List<WhLocation> findWhereLocationLevelEquals(String locationLevel) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'location_position = :locationPosition'.
	 */
	public List<WhLocation> findWhereLocationPositionEquals(String locationPosition) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'allocation_zone = :allocationZone'.
	 */
	public List<WhLocation> findWhereAllocationZoneEquals(String allocationZone) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'work_zone = :workZone'.
	 */
	public List<WhLocation> findWhereWorkZoneEquals(String workZone) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'is_active = :isActive'.
	 */
	public List<WhLocation> findWhereIsActiveEquals(String isActive) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<WhLocation> findWhereIsDeleteEquals(String isDelete) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'user_id = :userId'.
	 */
	public List<WhLocation> findWhereUserIdEquals(String userId) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'corp_id = :corpId'.
	 */
	public List<WhLocation> findWhereCorpIdEquals(String corpId) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'wh_code = :whCode'.
	 */
	public List<WhLocation> findWhereWhCodeEquals(String whCode) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'created_by = :createdBy'.
	 */
	public List<WhLocation> findWhereCreatedByEquals(String createdBy) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'created_date = :createdDate'.
	 */
	public List<WhLocation> findWhereCreatedDateEquals(Date createdDate) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<WhLocation> findWhereUpdatedByEquals(String updatedBy) throws WhLocationDaoException;

	/** 
	 * Returns all rows from the wh_location table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<WhLocation> findWhereUpdatedDateEquals(Date updatedDate) throws WhLocationDaoException;

	/** 
	 * Returns the rows from the wh_location table that matches the specified primary-key value.
	 */
	public WhLocation findByPrimaryKey(WhLocationPk pk) throws WhLocationDaoException;

	public List<WhLocation> findByCriteriaLimit(WarehouseLocationSearch c, int start, int end) throws WhLocationDaoException;

	public List<WhLocation> findByLocationPaging(WhLocation w, int page) throws WhLocationDaoException;

}

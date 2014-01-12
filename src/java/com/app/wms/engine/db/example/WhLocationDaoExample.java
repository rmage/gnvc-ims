package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.WhLocationDao;
import com.app.wms.engine.db.dto.WhLocation;
import com.app.wms.engine.db.exceptions.WhLocationDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class WhLocationDaoExample
{
	/**
	 * Method 'main'
	 * 
	 * @param arg
	 * @throws Exception
	 */
	public static void main(String[] arg) throws Exception
	{
		// Uncomment one of the lines below to test the generated code
		
		// findAll();
		// findWhereIdEquals(0);
		// findWhereLocationIdEquals("");
		// findWhereLocationCodeEquals("");
		// findWhereLocationNameEquals("");
		// findWhereLocationTypeEquals("");
		// findWhereMinProductEquals(0);
		// findWhereMaxProductEquals(0);
		// findWhereLocatingAreaEquals("");
		// findWhereLocatingZoneEquals("");
		// findWhereLocationBayEquals("");
		// findWhereLocationLevelEquals("");
		// findWhereLocationPositionEquals("");
		// findWhereAllocationZoneEquals("");
		// findWhereWorkZoneEquals("");
		// findWhereIsActiveEquals("");
		// findWhereIsDeleteEquals("");
		// findWhereUserIdEquals("");
		// findWhereCorpIdEquals("");
		// findWhereWhCodeEquals("");
		// findWhereCreatedByEquals("");
		// findWhereCreatedDateEquals(null);
		// findWhereUpdatedByEquals("");
		// findWhereUpdatedDateEquals(null);
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findAll();
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIdEquals'
	 * 
	 * @param id
	 * @throws Exception
	 */
	public static void findWhereIdEquals(int id) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereIdEquals(id);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereLocationIdEquals'
	 * 
	 * @param locationId
	 * @throws Exception
	 */
	public static void findWhereLocationIdEquals(String locationId) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereLocationIdEquals(locationId);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereLocationCodeEquals'
	 * 
	 * @param locationCode
	 * @throws Exception
	 */
	public static void findWhereLocationCodeEquals(String locationCode) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereLocationCodeEquals(locationCode);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereLocationNameEquals'
	 * 
	 * @param locationName
	 * @throws Exception
	 */
	public static void findWhereLocationNameEquals(String locationName) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereLocationNameEquals(locationName);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereLocationTypeEquals'
	 * 
	 * @param locationType
	 * @throws Exception
	 */
	public static void findWhereLocationTypeEquals(String locationType) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereLocationTypeEquals(locationType);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereMinProductEquals'
	 * 
	 * @param minProduct
	 * @throws Exception
	 */
	public static void findWhereMinProductEquals(int minProduct) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereMinProductEquals(minProduct);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereMaxProductEquals'
	 * 
	 * @param maxProduct
	 * @throws Exception
	 */
	public static void findWhereMaxProductEquals(int maxProduct) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereMaxProductEquals(maxProduct);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereLocatingAreaEquals'
	 * 
	 * @param locatingArea
	 * @throws Exception
	 */
	public static void findWhereLocatingAreaEquals(String locatingArea) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereLocatingAreaEquals(locatingArea);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereLocatingZoneEquals'
	 * 
	 * @param locatingZone
	 * @throws Exception
	 */
	public static void findWhereLocatingZoneEquals(String locatingZone) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereLocatingZoneEquals(locatingZone);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereLocationBayEquals'
	 * 
	 * @param locationBay
	 * @throws Exception
	 */
	public static void findWhereLocationBayEquals(String locationBay) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereLocationBayEquals(locationBay);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereLocationLevelEquals'
	 * 
	 * @param locationLevel
	 * @throws Exception
	 */
	public static void findWhereLocationLevelEquals(String locationLevel) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereLocationLevelEquals(locationLevel);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereLocationPositionEquals'
	 * 
	 * @param locationPosition
	 * @throws Exception
	 */
	public static void findWhereLocationPositionEquals(String locationPosition) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereLocationPositionEquals(locationPosition);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereAllocationZoneEquals'
	 * 
	 * @param allocationZone
	 * @throws Exception
	 */
	public static void findWhereAllocationZoneEquals(String allocationZone) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereAllocationZoneEquals(allocationZone);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereWorkZoneEquals'
	 * 
	 * @param workZone
	 * @throws Exception
	 */
	public static void findWhereWorkZoneEquals(String workZone) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereWorkZoneEquals(workZone);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIsActiveEquals'
	 * 
	 * @param isActive
	 * @throws Exception
	 */
	public static void findWhereIsActiveEquals(String isActive) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereIsActiveEquals(isActive);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIsDeleteEquals'
	 * 
	 * @param isDelete
	 * @throws Exception
	 */
	public static void findWhereIsDeleteEquals(String isDelete) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUserIdEquals'
	 * 
	 * @param userId
	 * @throws Exception
	 */
	public static void findWhereUserIdEquals(String userId) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereUserIdEquals(userId);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCorpIdEquals'
	 * 
	 * @param corpId
	 * @throws Exception
	 */
	public static void findWhereCorpIdEquals(String corpId) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereCorpIdEquals(corpId);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereWhCodeEquals'
	 * 
	 * @param whCode
	 * @throws Exception
	 */
	public static void findWhereWhCodeEquals(String whCode) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereWhCodeEquals(whCode);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCreatedByEquals'
	 * 
	 * @param createdBy
	 * @throws Exception
	 */
	public static void findWhereCreatedByEquals(String createdBy) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereCreatedByEquals(createdBy);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCreatedDateEquals'
	 * 
	 * @param createdDate
	 * @throws Exception
	 */
	public static void findWhereCreatedDateEquals(Date createdDate) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUpdatedByEquals'
	 * 
	 * @param updatedBy
	 * @throws Exception
	 */
	public static void findWhereUpdatedByEquals(String updatedBy) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUpdatedDateEquals'
	 * 
	 * @param updatedDate
	 * @throws Exception
	 */
	public static void findWhereUpdatedDateEquals(Date updatedDate) throws Exception
	{
		WhLocationDao dao = DaoFactory.createWhLocationDao();
		List<WhLocation> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (WhLocation dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(WhLocation dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getLocationId() );
		buf.append( ", " );
		buf.append( dto.getLocationCode() );
		buf.append( ", " );
		buf.append( dto.getLocationName() );
		buf.append( ", " );
		buf.append( dto.getLocationType() );
		buf.append( ", " );
		buf.append( dto.getMinProduct() );
		buf.append( ", " );
		buf.append( dto.getMaxProduct() );
		buf.append( ", " );
		buf.append( dto.getLocatingArea() );
		buf.append( ", " );
		buf.append( dto.getLocatingZone() );
		buf.append( ", " );
		buf.append( dto.getLocationBay() );
		buf.append( ", " );
		buf.append( dto.getLocationLevel() );
		buf.append( ", " );
		buf.append( dto.getLocationPosition() );
		buf.append( ", " );
		buf.append( dto.getAllocationZone() );
		buf.append( ", " );
		buf.append( dto.getWorkZone() );
		buf.append( ", " );
		buf.append( dto.getIsActive() );
		buf.append( ", " );
		buf.append( dto.getIsDelete() );
		buf.append( ", " );
		buf.append( dto.getUserId() );
		buf.append( ", " );
		buf.append( dto.getCorpId() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		buf.append( ", " );
		buf.append( dto.getCreatedBy() );
		buf.append( ", " );
		buf.append( dto.getCreatedDate() );
		buf.append( ", " );
		buf.append( dto.getUpdatedBy() );
		buf.append( ", " );
		buf.append( dto.getUpdatedDate() );
		System.out.println( buf.toString() );
	}

}

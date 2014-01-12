package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.WhDao;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.exceptions.WhDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class WhDaoExample
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
		// findWhereWhCodeEquals("");
		// findWhereCodeEquals("");
		// findWhereNameEquals("");
		// findWhereRegionEquals("");
		// findWhereIsActiveEquals("");
		// findWhereIsDeleteEquals("");
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
		WhDao dao = DaoFactory.createWhDao();
		List<Wh> _result = dao.findAll();
		for (Wh dto : _result) {
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
		WhDao dao = DaoFactory.createWhDao();
		List<Wh> _result = dao.findWhereIdEquals(id);
		for (Wh dto : _result) {
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
		WhDao dao = DaoFactory.createWhDao();
		List<Wh> _result = dao.findWhereWhCodeEquals(whCode);
		for (Wh dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCodeEquals'
	 * 
	 * @param code
	 * @throws Exception
	 */
	public static void findWhereCodeEquals(String code) throws Exception
	{
		WhDao dao = DaoFactory.createWhDao();
		List<Wh> _result = dao.findWhereCodeEquals(code);
		for (Wh dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereNameEquals'
	 * 
	 * @param name
	 * @throws Exception
	 */
	public static void findWhereNameEquals(String name) throws Exception
	{
		WhDao dao = DaoFactory.createWhDao();
		List<Wh> _result = dao.findWhereNameEquals(name);
		for (Wh dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereRegionEquals'
	 * 
	 * @param region
	 * @throws Exception
	 */
	public static void findWhereRegionEquals(String region) throws Exception
	{
		WhDao dao = DaoFactory.createWhDao();
		List<Wh> _result = dao.findWhereRegionEquals(region);
		for (Wh dto : _result) {
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
		WhDao dao = DaoFactory.createWhDao();
		List<Wh> _result = dao.findWhereIsActiveEquals(isActive);
		for (Wh dto : _result) {
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
		WhDao dao = DaoFactory.createWhDao();
		List<Wh> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (Wh dto : _result) {
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
		WhDao dao = DaoFactory.createWhDao();
		List<Wh> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Wh dto : _result) {
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
		WhDao dao = DaoFactory.createWhDao();
		List<Wh> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Wh dto : _result) {
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
		WhDao dao = DaoFactory.createWhDao();
		List<Wh> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Wh dto : _result) {
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
		WhDao dao = DaoFactory.createWhDao();
		List<Wh> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Wh dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Wh dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		buf.append( ", " );
		buf.append( dto.getCode() );
		buf.append( ", " );
		buf.append( dto.getName() );
		buf.append( ", " );
		buf.append( dto.getRegion() );
		buf.append( ", " );
		buf.append( dto.getIsActive() );
		buf.append( ", " );
		buf.append( dto.getIsDelete() );
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

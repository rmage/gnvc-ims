package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.WarehouseDao;
import com.app.wms.engine.db.dto.Warehouse;
import com.app.wms.engine.db.exceptions.WarehouseDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class WarehouseDaoExample
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
		// findWhereWhNameEquals("");
		// findWhereCategoryNameEquals("");
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
		WarehouseDao dao = DaoFactory.createWarehouseDao();
		List<Warehouse> _result = dao.findAll();
		for (Warehouse dto : _result) {
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
		WarehouseDao dao = DaoFactory.createWarehouseDao();
		List<Warehouse> _result = dao.findWhereIdEquals(id);
		for (Warehouse dto : _result) {
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
		WarehouseDao dao = DaoFactory.createWarehouseDao();
		List<Warehouse> _result = dao.findWhereWhCodeEquals(whCode);
		for (Warehouse dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereWhNameEquals'
	 * 
	 * @param whName
	 * @throws Exception
	 */
	public static void findWhereWhNameEquals(String whName) throws Exception
	{
		WarehouseDao dao = DaoFactory.createWarehouseDao();
		List<Warehouse> _result = dao.findWhereWhNameEquals(whName);
		for (Warehouse dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCategoryNameEquals'
	 * 
	 * @param categoryName
	 * @throws Exception
	 */
	public static void findWhereCategoryNameEquals(String categoryName) throws Exception
	{
		WarehouseDao dao = DaoFactory.createWarehouseDao();
		List<Warehouse> _result = dao.findWhereCategoryNameEquals(categoryName);
		for (Warehouse dto : _result) {
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
		WarehouseDao dao = DaoFactory.createWarehouseDao();
		List<Warehouse> _result = dao.findWhereIsActiveEquals(isActive);
		for (Warehouse dto : _result) {
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
		WarehouseDao dao = DaoFactory.createWarehouseDao();
		List<Warehouse> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (Warehouse dto : _result) {
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
		WarehouseDao dao = DaoFactory.createWarehouseDao();
		List<Warehouse> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Warehouse dto : _result) {
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
		WarehouseDao dao = DaoFactory.createWarehouseDao();
		List<Warehouse> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Warehouse dto : _result) {
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
		WarehouseDao dao = DaoFactory.createWarehouseDao();
		List<Warehouse> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Warehouse dto : _result) {
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
		WarehouseDao dao = DaoFactory.createWarehouseDao();
		List<Warehouse> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Warehouse dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Warehouse dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		buf.append( ", " );
		buf.append( dto.getWhName() );
		buf.append( ", " );
		buf.append( dto.getCategoryName() );
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

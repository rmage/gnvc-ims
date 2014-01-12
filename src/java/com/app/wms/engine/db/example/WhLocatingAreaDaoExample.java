package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.WhLocatingAreaDao;
import com.app.wms.engine.db.dto.WhLocatingArea;
import com.app.wms.engine.db.exceptions.WhLocatingAreaDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class WhLocatingAreaDaoExample
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
		// findWhereLocatingIdEquals("");
		// findWhereLocatingAreaEquals("");
		// findWhereLocatingConditionEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		WhLocatingAreaDao dao = DaoFactory.createWhLocatingAreaDao();
		List<WhLocatingArea> _result = dao.findAll();
		for (WhLocatingArea dto : _result) {
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
		WhLocatingAreaDao dao = DaoFactory.createWhLocatingAreaDao();
		List<WhLocatingArea> _result = dao.findWhereIdEquals(id);
		for (WhLocatingArea dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereLocatingIdEquals'
	 * 
	 * @param locatingId
	 * @throws Exception
	 */
	public static void findWhereLocatingIdEquals(String locatingId) throws Exception
	{
		WhLocatingAreaDao dao = DaoFactory.createWhLocatingAreaDao();
		List<WhLocatingArea> _result = dao.findWhereLocatingIdEquals(locatingId);
		for (WhLocatingArea dto : _result) {
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
		WhLocatingAreaDao dao = DaoFactory.createWhLocatingAreaDao();
		List<WhLocatingArea> _result = dao.findWhereLocatingAreaEquals(locatingArea);
		for (WhLocatingArea dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereLocatingConditionEquals'
	 * 
	 * @param locatingCondition
	 * @throws Exception
	 */
	public static void findWhereLocatingConditionEquals(String locatingCondition) throws Exception
	{
		WhLocatingAreaDao dao = DaoFactory.createWhLocatingAreaDao();
		List<WhLocatingArea> _result = dao.findWhereLocatingConditionEquals(locatingCondition);
		for (WhLocatingArea dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(WhLocatingArea dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getLocatingId() );
		buf.append( ", " );
		buf.append( dto.getLocatingArea() );
		buf.append( ", " );
		buf.append( dto.getLocatingCondition() );
		System.out.println( buf.toString() );
	}

}

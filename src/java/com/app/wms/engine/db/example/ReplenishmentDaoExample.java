package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.ReplenishmentDao;
import com.app.wms.engine.db.dto.Replenishment;
import com.app.wms.engine.db.exceptions.ReplenishmentDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class ReplenishmentDaoExample
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
		// findWhereReplenishNoEquals("");
		// findWhereReplenishDateEquals(null);
		// findWhereStatusAppEquals("");
		// findWhereAppDateEquals(null);
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
		ReplenishmentDao dao = DaoFactory.createReplenishmentDao();
		List<Replenishment> _result = dao.findAll();
		for (Replenishment dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereReplenishNoEquals'
	 * 
	 * @param replenishNo
	 * @throws Exception
	 */
	public static void findWhereReplenishNoEquals(String replenishNo) throws Exception
	{
		ReplenishmentDao dao = DaoFactory.createReplenishmentDao();
		List<Replenishment> _result = dao.findWhereReplenishNoEquals(replenishNo);
		for (Replenishment dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereReplenishDateEquals'
	 * 
	 * @param replenishDate
	 * @throws Exception
	 */
	public static void findWhereReplenishDateEquals(Date replenishDate) throws Exception
	{
		ReplenishmentDao dao = DaoFactory.createReplenishmentDao();
		List<Replenishment> _result = dao.findWhereReplenishDateEquals(replenishDate);
		for (Replenishment dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereStatusAppEquals'
	 * 
	 * @param statusApp
	 * @throws Exception
	 */
	public static void findWhereStatusAppEquals(String statusApp) throws Exception
	{
		ReplenishmentDao dao = DaoFactory.createReplenishmentDao();
		List<Replenishment> _result = dao.findWhereStatusAppEquals(statusApp);
		for (Replenishment dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereAppDateEquals'
	 * 
	 * @param appDate
	 * @throws Exception
	 */
	public static void findWhereAppDateEquals(Date appDate) throws Exception
	{
		ReplenishmentDao dao = DaoFactory.createReplenishmentDao();
		List<Replenishment> _result = dao.findWhereAppDateEquals(appDate);
		for (Replenishment dto : _result) {
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
		ReplenishmentDao dao = DaoFactory.createReplenishmentDao();
		List<Replenishment> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Replenishment dto : _result) {
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
		ReplenishmentDao dao = DaoFactory.createReplenishmentDao();
		List<Replenishment> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Replenishment dto : _result) {
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
		ReplenishmentDao dao = DaoFactory.createReplenishmentDao();
		List<Replenishment> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Replenishment dto : _result) {
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
		ReplenishmentDao dao = DaoFactory.createReplenishmentDao();
		List<Replenishment> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Replenishment dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Replenishment dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getReplenishNo() );
		buf.append( ", " );
		buf.append( dto.getReplenishDate() );
		buf.append( ", " );
		buf.append( dto.getStatusApp() );
		buf.append( ", " );
		buf.append( dto.getAppDate() );
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

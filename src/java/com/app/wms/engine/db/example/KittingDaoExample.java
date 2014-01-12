package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.KittingDao;
import com.app.wms.engine.db.dto.Kitting;
import com.app.wms.engine.db.exceptions.KittingDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class KittingDaoExample
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
		// findWhereKittingNoEquals("");
		// findWhereKittingDateEquals(null);
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
		KittingDao dao = DaoFactory.createKittingDao();
		List<Kitting> _result = dao.findAll();
		for (Kitting dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereKittingNoEquals'
	 * 
	 * @param kittingNo
	 * @throws Exception
	 */
	public static void findWhereKittingNoEquals(String kittingNo) throws Exception
	{
		KittingDao dao = DaoFactory.createKittingDao();
		List<Kitting> _result = dao.findWhereKittingNoEquals(kittingNo);
		for (Kitting dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereKittingDateEquals'
	 * 
	 * @param kittingDate
	 * @throws Exception
	 */
	public static void findWhereKittingDateEquals(Date kittingDate) throws Exception
	{
		KittingDao dao = DaoFactory.createKittingDao();
		List<Kitting> _result = dao.findWhereKittingDateEquals(kittingDate);
		for (Kitting dto : _result) {
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
		KittingDao dao = DaoFactory.createKittingDao();
		List<Kitting> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Kitting dto : _result) {
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
		KittingDao dao = DaoFactory.createKittingDao();
		List<Kitting> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Kitting dto : _result) {
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
		KittingDao dao = DaoFactory.createKittingDao();
		List<Kitting> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Kitting dto : _result) {
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
		KittingDao dao = DaoFactory.createKittingDao();
		List<Kitting> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Kitting dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Kitting dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getKittingNo() );
		buf.append( ", " );
		buf.append( dto.getKittingDate() );
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

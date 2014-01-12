package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.PackingDao;
import com.app.wms.engine.db.dto.Packing;
import com.app.wms.engine.db.exceptions.PackingDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class PackingDaoExample
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
		// findWherePackingNoEquals("");
		// findWherePackingDateEquals(null);
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
		PackingDao dao = DaoFactory.createPackingDao();
		List<Packing> _result = dao.findAll();
		for (Packing dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePackingNoEquals'
	 * 
	 * @param packingNo
	 * @throws Exception
	 */
	public static void findWherePackingNoEquals(String packingNo) throws Exception
	{
		PackingDao dao = DaoFactory.createPackingDao();
		List<Packing> _result = dao.findWherePackingNoEquals(packingNo);
		for (Packing dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePackingDateEquals'
	 * 
	 * @param packingDate
	 * @throws Exception
	 */
	public static void findWherePackingDateEquals(Date packingDate) throws Exception
	{
		PackingDao dao = DaoFactory.createPackingDao();
		List<Packing> _result = dao.findWherePackingDateEquals(packingDate);
		for (Packing dto : _result) {
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
		PackingDao dao = DaoFactory.createPackingDao();
		List<Packing> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Packing dto : _result) {
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
		PackingDao dao = DaoFactory.createPackingDao();
		List<Packing> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Packing dto : _result) {
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
		PackingDao dao = DaoFactory.createPackingDao();
		List<Packing> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Packing dto : _result) {
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
		PackingDao dao = DaoFactory.createPackingDao();
		List<Packing> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Packing dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Packing dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getPackingNo() );
		buf.append( ", " );
		buf.append( dto.getPackingDate() );
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

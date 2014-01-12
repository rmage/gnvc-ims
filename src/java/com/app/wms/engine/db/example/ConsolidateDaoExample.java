package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.ConsolidateDao;
import com.app.wms.engine.db.dto.Consolidate;
import com.app.wms.engine.db.exceptions.ConsolidateDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class ConsolidateDaoExample
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
		// findWhereConsolidateNoEquals("");
		// findWhereConsolidateDateEquals(null);
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
		ConsolidateDao dao = DaoFactory.createConsolidateDao();
		List<Consolidate> _result = dao.findAll();
		for (Consolidate dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereConsolidateNoEquals'
	 * 
	 * @param consolidateNo
	 * @throws Exception
	 */
	public static void findWhereConsolidateNoEquals(String consolidateNo) throws Exception
	{
		ConsolidateDao dao = DaoFactory.createConsolidateDao();
		List<Consolidate> _result = dao.findWhereConsolidateNoEquals(consolidateNo);
		for (Consolidate dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereConsolidateDateEquals'
	 * 
	 * @param consolidateDate
	 * @throws Exception
	 */
	public static void findWhereConsolidateDateEquals(Date consolidateDate) throws Exception
	{
		ConsolidateDao dao = DaoFactory.createConsolidateDao();
		List<Consolidate> _result = dao.findWhereConsolidateDateEquals(consolidateDate);
		for (Consolidate dto : _result) {
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
		ConsolidateDao dao = DaoFactory.createConsolidateDao();
		List<Consolidate> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Consolidate dto : _result) {
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
		ConsolidateDao dao = DaoFactory.createConsolidateDao();
		List<Consolidate> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Consolidate dto : _result) {
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
		ConsolidateDao dao = DaoFactory.createConsolidateDao();
		List<Consolidate> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Consolidate dto : _result) {
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
		ConsolidateDao dao = DaoFactory.createConsolidateDao();
		List<Consolidate> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Consolidate dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Consolidate dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getConsolidateNo() );
		buf.append( ", " );
		buf.append( dto.getConsolidateDate() );
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

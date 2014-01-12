package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.CorpWhDao;
import com.app.wms.engine.db.dto.CorpWh;
import com.app.wms.engine.db.exceptions.CorpWhDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class CorpWhDaoExample
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
		// findWhereCorpWhIdEquals("");
		// findWhereCorpIdEquals("");
		// findWhereWhCodeEquals("");
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
		CorpWhDao dao = DaoFactory.createCorpWhDao();
		List<CorpWh> _result = dao.findAll();
		for (CorpWh dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCorpWhIdEquals'
	 * 
	 * @param corpWhId
	 * @throws Exception
	 */
	public static void findWhereCorpWhIdEquals(String corpWhId) throws Exception
	{
		CorpWhDao dao = DaoFactory.createCorpWhDao();
		List<CorpWh> _result = dao.findWhereCorpWhIdEquals(corpWhId);
		for (CorpWh dto : _result) {
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
		CorpWhDao dao = DaoFactory.createCorpWhDao();
		List<CorpWh> _result = dao.findWhereCorpIdEquals(corpId);
		for (CorpWh dto : _result) {
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
		CorpWhDao dao = DaoFactory.createCorpWhDao();
		List<CorpWh> _result = dao.findWhereWhCodeEquals(whCode);
		for (CorpWh dto : _result) {
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
		CorpWhDao dao = DaoFactory.createCorpWhDao();
		List<CorpWh> _result = dao.findWhereIsActiveEquals(isActive);
		for (CorpWh dto : _result) {
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
		CorpWhDao dao = DaoFactory.createCorpWhDao();
		List<CorpWh> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (CorpWh dto : _result) {
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
		CorpWhDao dao = DaoFactory.createCorpWhDao();
		List<CorpWh> _result = dao.findWhereCreatedByEquals(createdBy);
		for (CorpWh dto : _result) {
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
		CorpWhDao dao = DaoFactory.createCorpWhDao();
		List<CorpWh> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (CorpWh dto : _result) {
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
		CorpWhDao dao = DaoFactory.createCorpWhDao();
		List<CorpWh> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (CorpWh dto : _result) {
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
		CorpWhDao dao = DaoFactory.createCorpWhDao();
		List<CorpWh> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (CorpWh dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(CorpWh dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getCorpWhId() );
		buf.append( ", " );
		buf.append( dto.getCorpId() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
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

package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.CorpUserDao;
import com.app.wms.engine.db.dto.CorpUser;
import com.app.wms.engine.db.exceptions.CorpUserDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class CorpUserDaoExample
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
		// findWhereCorpuserIdEquals("");
		// findWhereCorpIdEquals("");
		// findWhereUserIdEquals("");
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
		CorpUserDao dao = DaoFactory.createCorpUserDao();
		List<CorpUser> _result = dao.findAll();
		for (CorpUser dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCorpuserIdEquals'
	 * 
	 * @param corpuserId
	 * @throws Exception
	 */
	public static void findWhereCorpuserIdEquals(String corpuserId) throws Exception
	{
		CorpUserDao dao = DaoFactory.createCorpUserDao();
		List<CorpUser> _result = dao.findWhereCorpuserIdEquals(corpuserId);
		for (CorpUser dto : _result) {
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
		CorpUserDao dao = DaoFactory.createCorpUserDao();
		List<CorpUser> _result = dao.findWhereCorpIdEquals(corpId);
		for (CorpUser dto : _result) {
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
		CorpUserDao dao = DaoFactory.createCorpUserDao();
		List<CorpUser> _result = dao.findWhereUserIdEquals(userId);
		for (CorpUser dto : _result) {
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
		CorpUserDao dao = DaoFactory.createCorpUserDao();
		List<CorpUser> _result = dao.findWhereIsActiveEquals(isActive);
		for (CorpUser dto : _result) {
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
		CorpUserDao dao = DaoFactory.createCorpUserDao();
		List<CorpUser> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (CorpUser dto : _result) {
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
		CorpUserDao dao = DaoFactory.createCorpUserDao();
		List<CorpUser> _result = dao.findWhereCreatedByEquals(createdBy);
		for (CorpUser dto : _result) {
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
		CorpUserDao dao = DaoFactory.createCorpUserDao();
		List<CorpUser> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (CorpUser dto : _result) {
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
		CorpUserDao dao = DaoFactory.createCorpUserDao();
		List<CorpUser> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (CorpUser dto : _result) {
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
		CorpUserDao dao = DaoFactory.createCorpUserDao();
		List<CorpUser> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (CorpUser dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(CorpUser dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getCorpuserId() );
		buf.append( ", " );
		buf.append( dto.getCorpId() );
		buf.append( ", " );
		buf.append( dto.getUserId() );
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

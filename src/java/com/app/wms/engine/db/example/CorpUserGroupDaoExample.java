package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.CorpUserGroupDao;
import com.app.wms.engine.db.dto.CorpUserGroup;
import com.app.wms.engine.db.exceptions.CorpUserGroupDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class CorpUserGroupDaoExample
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
		// findWhereCorpUsergroupIdEquals("");
		// findWhereCorpIdEquals("");
		// findWhereWhCodeEquals("");
		// findWhereRoleCodeEquals("");
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
		CorpUserGroupDao dao = DaoFactory.createCorpUserGroupDao();
		List<CorpUserGroup> _result = dao.findAll();
		for (CorpUserGroup dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCorpUsergroupIdEquals'
	 * 
	 * @param corpUsergroupId
	 * @throws Exception
	 */
	public static void findWhereCorpUsergroupIdEquals(String corpUsergroupId) throws Exception
	{
		CorpUserGroupDao dao = DaoFactory.createCorpUserGroupDao();
		List<CorpUserGroup> _result = dao.findWhereCorpUsergroupIdEquals(corpUsergroupId);
		for (CorpUserGroup dto : _result) {
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
		CorpUserGroupDao dao = DaoFactory.createCorpUserGroupDao();
		List<CorpUserGroup> _result = dao.findWhereCorpIdEquals(corpId);
		for (CorpUserGroup dto : _result) {
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
		CorpUserGroupDao dao = DaoFactory.createCorpUserGroupDao();
		List<CorpUserGroup> _result = dao.findWhereWhCodeEquals(whCode);
		for (CorpUserGroup dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereRoleCodeEquals'
	 * 
	 * @param roleCode
	 * @throws Exception
	 */
	public static void findWhereRoleCodeEquals(String roleCode) throws Exception
	{
		CorpUserGroupDao dao = DaoFactory.createCorpUserGroupDao();
		List<CorpUserGroup> _result = dao.findWhereRoleCodeEquals(roleCode);
		for (CorpUserGroup dto : _result) {
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
		CorpUserGroupDao dao = DaoFactory.createCorpUserGroupDao();
		List<CorpUserGroup> _result = dao.findWhereIsActiveEquals(isActive);
		for (CorpUserGroup dto : _result) {
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
		CorpUserGroupDao dao = DaoFactory.createCorpUserGroupDao();
		List<CorpUserGroup> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (CorpUserGroup dto : _result) {
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
		CorpUserGroupDao dao = DaoFactory.createCorpUserGroupDao();
		List<CorpUserGroup> _result = dao.findWhereCreatedByEquals(createdBy);
		for (CorpUserGroup dto : _result) {
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
		CorpUserGroupDao dao = DaoFactory.createCorpUserGroupDao();
		List<CorpUserGroup> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (CorpUserGroup dto : _result) {
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
		CorpUserGroupDao dao = DaoFactory.createCorpUserGroupDao();
		List<CorpUserGroup> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (CorpUserGroup dto : _result) {
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
		CorpUserGroupDao dao = DaoFactory.createCorpUserGroupDao();
		List<CorpUserGroup> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (CorpUserGroup dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(CorpUserGroup dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getCorpUsergroupId() );
		buf.append( ", " );
		buf.append( dto.getCorpId() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		buf.append( ", " );
		buf.append( dto.getRoleCode() );
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

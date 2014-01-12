package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.ApprovalRangeDao;
import com.app.wms.engine.db.dto.ApprovalRange;
import com.app.wms.engine.db.exceptions.ApprovalRangeDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class ApprovalRangeDaoExample
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
		// findWhereUsernameEquals("");
		// findWhereRoleCodeEquals("");
		// findWhereValAmountEquals(0);
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
		ApprovalRangeDao dao = DaoFactory.createApprovalRangeDao();
		List<ApprovalRange> _result = dao.findAll();
		for (ApprovalRange dto : _result) {
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
		ApprovalRangeDao dao = DaoFactory.createApprovalRangeDao();
		List<ApprovalRange> _result = dao.findWhereIdEquals(id);
		for (ApprovalRange dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUsernameEquals'
	 * 
	 * @param username
	 * @throws Exception
	 */
	public static void findWhereUsernameEquals(String username) throws Exception
	{
		ApprovalRangeDao dao = DaoFactory.createApprovalRangeDao();
		List<ApprovalRange> _result = dao.findWhereUsernameEquals(username);
		for (ApprovalRange dto : _result) {
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
		ApprovalRangeDao dao = DaoFactory.createApprovalRangeDao();
		List<ApprovalRange> _result = dao.findWhereRoleCodeEquals(roleCode);
		for (ApprovalRange dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereValAmountEquals'
	 * 
	 * @param valAmount
	 * @throws Exception
	 */
	public static void findWhereValAmountEquals(float valAmount) throws Exception
	{
		ApprovalRangeDao dao = DaoFactory.createApprovalRangeDao();
		List<ApprovalRange> _result = dao.findWhereValAmountEquals(valAmount);
		for (ApprovalRange dto : _result) {
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
		ApprovalRangeDao dao = DaoFactory.createApprovalRangeDao();
		List<ApprovalRange> _result = dao.findWhereIsActiveEquals(isActive);
		for (ApprovalRange dto : _result) {
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
		ApprovalRangeDao dao = DaoFactory.createApprovalRangeDao();
		List<ApprovalRange> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (ApprovalRange dto : _result) {
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
		ApprovalRangeDao dao = DaoFactory.createApprovalRangeDao();
		List<ApprovalRange> _result = dao.findWhereCreatedByEquals(createdBy);
		for (ApprovalRange dto : _result) {
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
		ApprovalRangeDao dao = DaoFactory.createApprovalRangeDao();
		List<ApprovalRange> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (ApprovalRange dto : _result) {
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
		ApprovalRangeDao dao = DaoFactory.createApprovalRangeDao();
		List<ApprovalRange> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (ApprovalRange dto : _result) {
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
		ApprovalRangeDao dao = DaoFactory.createApprovalRangeDao();
		List<ApprovalRange> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (ApprovalRange dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(ApprovalRange dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getUsername() );
		buf.append( ", " );
		buf.append( dto.getRoleCode() );
		buf.append( ", " );
		buf.append( dto.getFromAmount() );
		buf.append( ", " );
		buf.append( dto.getToAmount() );
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

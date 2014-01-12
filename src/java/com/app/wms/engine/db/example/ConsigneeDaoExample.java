package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.ConsigneeDao;
import com.app.wms.engine.db.dto.Consignee;
import com.app.wms.engine.db.exceptions.ConsigneeDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class ConsigneeDaoExample
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
		// findWhereConsigneeCodeEquals("");
		// findWhereConsigneeNameEquals("");
		// findWhereAddress1Equals("");
		// findWhereAddress2Equals("");
		// findWhereAddress3Equals("");
		// findWhereConsigneePhoneEquals("");
		// findWhereUserIdEquals("");
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
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> _result = dao.findAll();
		for (Consignee dto : _result) {
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
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> _result = dao.findWhereIdEquals(id);
		for (Consignee dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereConsigneeCodeEquals'
	 * 
	 * @param consigneeCode
	 * @throws Exception
	 */
	public static void findWhereConsigneeCodeEquals(String consigneeCode) throws Exception
	{
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> _result = dao.findWhereConsigneeCodeEquals(consigneeCode);
		for (Consignee dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereConsigneeNameEquals'
	 * 
	 * @param consigneeName
	 * @throws Exception
	 */
	public static void findWhereConsigneeNameEquals(String consigneeName) throws Exception
	{
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> _result = dao.findWhereConsigneeNameEquals(consigneeName);
		for (Consignee dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereAddress1Equals'
	 * 
	 * @param address1
	 * @throws Exception
	 */
	public static void findWhereAddress1Equals(String address1) throws Exception
	{
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> _result = dao.findWhereAddress1Equals(address1);
		for (Consignee dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereAddress2Equals'
	 * 
	 * @param address2
	 * @throws Exception
	 */
	public static void findWhereAddress2Equals(String address2) throws Exception
	{
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> _result = dao.findWhereAddress2Equals(address2);
		for (Consignee dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereAddress3Equals'
	 * 
	 * @param address3
	 * @throws Exception
	 */
	public static void findWhereAddress3Equals(String address3) throws Exception
	{
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> _result = dao.findWhereAddress3Equals(address3);
		for (Consignee dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereConsigneePhoneEquals'
	 * 
	 * @param consigneePhone
	 * @throws Exception
	 */
	public static void findWhereConsigneePhoneEquals(String consigneePhone) throws Exception
	{
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> _result = dao.findWhereConsigneePhoneEquals(consigneePhone);
		for (Consignee dto : _result) {
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
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> _result = dao.findWhereUserIdEquals(userId);
		for (Consignee dto : _result) {
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
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> _result = dao.findWhereCorpIdEquals(corpId);
		for (Consignee dto : _result) {
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
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> _result = dao.findWhereWhCodeEquals(whCode);
		for (Consignee dto : _result) {
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
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> _result = dao.findWhereIsActiveEquals(isActive);
		for (Consignee dto : _result) {
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
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (Consignee dto : _result) {
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
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Consignee dto : _result) {
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
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Consignee dto : _result) {
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
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Consignee dto : _result) {
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
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Consignee dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Consignee dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getConsigneeCode() );
		buf.append( ", " );
		buf.append( dto.getConsigneeName() );
		buf.append( ", " );
		buf.append( dto.getAddress1() );
		buf.append( ", " );
		buf.append( dto.getAddress2() );
		buf.append( ", " );
		buf.append( dto.getAddress3() );
		buf.append( ", " );
		buf.append( dto.getConsigneePhone() );
		buf.append( ", " );
		buf.append( dto.getUserId() );
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

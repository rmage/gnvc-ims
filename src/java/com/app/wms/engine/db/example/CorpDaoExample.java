package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.CorpDao;
import com.app.wms.engine.db.dto.Corp;
import com.app.wms.engine.db.exceptions.CorpDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class CorpDaoExample
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
		// findWhereCorpIdEquals("");
		// findWhereCorpNameEquals("");
		// findWhereWhCodeEquals("");
		// findWhereAddress1Equals("");
		// findWhereAddress2Equals("");
		// findWhereAddress3Equals("");
		// findWhereEmailEquals("");
		// findWhereCityCodeEquals("");
		// findWhereZipcodeEquals("");
		// findWherePhone1Equals("");
		// findWherePhone2Equals("");
		// findWhereFaxEquals("");
		// findWhereProvinceCodeEquals("");
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
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findAll();
		for (Corp dto : _result) {
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
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWhereCorpIdEquals(corpId);
		for (Corp dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCorpNameEquals'
	 * 
	 * @param corpName
	 * @throws Exception
	 */
	public static void findWhereCorpNameEquals(String corpName) throws Exception
	{
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWhereCorpNameEquals(corpName);
		for (Corp dto : _result) {
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
		CorpDao dao = DaoFactory.createCorpDao();
	//	List<Corp> _result = dao.findWhereWhCodeEquals(whCode);
//		for (Corp dto : _result) {
//			display(dto);
//		}
		
	}

	/**
	 * Method 'findWhereAddress1Equals'
	 * 
	 * @param address1
	 * @throws Exception
	 */
	public static void findWhereAddress1Equals(String address1) throws Exception
	{
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWhereAddress1Equals(address1);
		for (Corp dto : _result) {
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
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWhereAddress2Equals(address2);
		for (Corp dto : _result) {
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
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWhereAddress3Equals(address3);
		for (Corp dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereEmailEquals'
	 * 
	 * @param email
	 * @throws Exception
	 */
	public static void findWhereEmailEquals(String email) throws Exception
	{
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWhereEmailEquals(email);
		for (Corp dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCityCodeEquals'
	 * 
	 * @param cityCode
	 * @throws Exception
	 */
	public static void findWhereCityCodeEquals(String cityCode) throws Exception
	{
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWhereCityCodeEquals(cityCode);
		for (Corp dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereZipcodeEquals'
	 * 
	 * @param zipcode
	 * @throws Exception
	 */
	public static void findWhereZipcodeEquals(String zipcode) throws Exception
	{
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWhereZipcodeEquals(zipcode);
		for (Corp dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePhone1Equals'
	 * 
	 * @param phone1
	 * @throws Exception
	 */
	public static void findWherePhone1Equals(String phone1) throws Exception
	{
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWherePhone1Equals(phone1);
		for (Corp dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePhone2Equals'
	 * 
	 * @param phone2
	 * @throws Exception
	 */
	public static void findWherePhone2Equals(String phone2) throws Exception
	{
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWherePhone2Equals(phone2);
		for (Corp dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereFaxEquals'
	 * 
	 * @param fax
	 * @throws Exception
	 */
	public static void findWhereFaxEquals(String fax) throws Exception
	{
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWhereFaxEquals(fax);
		for (Corp dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProvinceCodeEquals'
	 * 
	 * @param provinceCode
	 * @throws Exception
	 */
	public static void findWhereProvinceCodeEquals(String provinceCode) throws Exception
	{
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWhereProvinceCodeEquals(provinceCode);
		for (Corp dto : _result) {
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
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWhereIsActiveEquals(isActive);
		for (Corp dto : _result) {
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
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (Corp dto : _result) {
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
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Corp dto : _result) {
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
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Corp dto : _result) {
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
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Corp dto : _result) {
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
		CorpDao dao = DaoFactory.createCorpDao();
		List<Corp> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Corp dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Corp dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getCorpId() );
		buf.append( ", " );
		buf.append( dto.getCorpName() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		buf.append( ", " );
		buf.append( dto.getAddress1() );
		buf.append( ", " );
		buf.append( dto.getAddress2() );
		buf.append( ", " );
		buf.append( dto.getAddress3() );
		buf.append( ", " );
		buf.append( dto.getEmail() );
		buf.append( ", " );
		buf.append( dto.getCityCode() );
		buf.append( ", " );
		buf.append( dto.getZipcode() );
		buf.append( ", " );
		buf.append( dto.getPhone1() );
		buf.append( ", " );
		buf.append( dto.getPhone2() );
		buf.append( ", " );
		buf.append( dto.getFax() );
		buf.append( ", " );
		buf.append( dto.getProvinceCode() );
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

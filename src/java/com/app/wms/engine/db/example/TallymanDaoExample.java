package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.TallymanDao;
import com.app.wms.engine.db.dto.Tallyman;
import com.app.wms.engine.db.exceptions.TallymanDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class TallymanDaoExample
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
		// findWhereTallyIdEquals("");
		// findWhereCodeEquals("");
		// findWhereNameEquals("");
		// findWhereTallynameEquals("");
		// findWhereJobfunctionEquals("");
		// findWhereCorpIdEquals("");
		// findWhereWhCodeEquals("");
		// findWhereIsActiveEquals("");
		// findWhereIsDeleteEquals("");
		// findWhereCreatedByEquals(0);
		// findWhereCreatedDateEquals(null);
		// findWhereUpdatedByEquals(0);
		// findWhereUpdatedDateEquals(null);
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		TallymanDao dao = DaoFactory.createTallymanDao();
		List<Tallyman> _result = dao.findAll();
		for (Tallyman dto : _result) {
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
		TallymanDao dao = DaoFactory.createTallymanDao();
		List<Tallyman> _result = dao.findWhereIdEquals(id);
		for (Tallyman dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereTallyIdEquals'
	 * 
	 * @param tallyId
	 * @throws Exception
	 */
	public static void findWhereTallyIdEquals(String tallyId) throws Exception
	{
		TallymanDao dao = DaoFactory.createTallymanDao();
		List<Tallyman> _result = dao.findWhereTallyIdEquals(tallyId);
		for (Tallyman dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCodeEquals'
	 * 
	 * @param code
	 * @throws Exception
	 */
	public static void findWhereCodeEquals(String code) throws Exception
	{
		TallymanDao dao = DaoFactory.createTallymanDao();
		List<Tallyman> _result = dao.findWhereCodeEquals(code);
		for (Tallyman dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereNameEquals'
	 * 
	 * @param name
	 * @throws Exception
	 */
	public static void findWhereNameEquals(String name) throws Exception
	{
		TallymanDao dao = DaoFactory.createTallymanDao();
		List<Tallyman> _result = dao.findWhereNameEquals(name);
		for (Tallyman dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereTallynameEquals'
	 * 
	 * @param tallyname
	 * @throws Exception
	 */
	public static void findWhereTallynameEquals(String tallyname) throws Exception
	{
		TallymanDao dao = DaoFactory.createTallymanDao();
		List<Tallyman> _result = dao.findWhereTallynameEquals(tallyname);
		for (Tallyman dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereJobfunctionEquals'
	 * 
	 * @param jobfunction
	 * @throws Exception
	 */
	public static void findWhereJobfunctionEquals(String jobfunction) throws Exception
	{
		TallymanDao dao = DaoFactory.createTallymanDao();
		List<Tallyman> _result = dao.findWhereJobfunctionEquals(jobfunction);
		for (Tallyman dto : _result) {
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
		TallymanDao dao = DaoFactory.createTallymanDao();
		List<Tallyman> _result = dao.findWhereCorpIdEquals(corpId);
		for (Tallyman dto : _result) {
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
		TallymanDao dao = DaoFactory.createTallymanDao();
		List<Tallyman> _result = dao.findWhereWhCodeEquals(whCode);
		for (Tallyman dto : _result) {
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
		TallymanDao dao = DaoFactory.createTallymanDao();
		List<Tallyman> _result = dao.findWhereIsActiveEquals(isActive);
		for (Tallyman dto : _result) {
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
		TallymanDao dao = DaoFactory.createTallymanDao();
		List<Tallyman> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (Tallyman dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCreatedByEquals'
	 * 
	 * @param createdBy
	 * @throws Exception
	 */
	public static void findWhereCreatedByEquals(int createdBy) throws Exception
	{
		TallymanDao dao = DaoFactory.createTallymanDao();
		List<Tallyman> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Tallyman dto : _result) {
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
		TallymanDao dao = DaoFactory.createTallymanDao();
		List<Tallyman> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Tallyman dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUpdatedByEquals'
	 * 
	 * @param updatedBy
	 * @throws Exception
	 */
	public static void findWhereUpdatedByEquals(int updatedBy) throws Exception
	{
		TallymanDao dao = DaoFactory.createTallymanDao();
		List<Tallyman> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Tallyman dto : _result) {
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
		TallymanDao dao = DaoFactory.createTallymanDao();
		List<Tallyman> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Tallyman dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Tallyman dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getTallyId() );
		buf.append( ", " );
		buf.append( dto.getCode() );
		buf.append( ", " );
		buf.append( dto.getName() );
		buf.append( ", " );
		buf.append( dto.getTallyname() );
		buf.append( ", " );
		buf.append( dto.getJobfunction() );
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

package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.PriceCatalogDao;
import com.app.wms.engine.db.dto.PriceCatalog;
import com.app.wms.engine.db.exceptions.PriceCatalogDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class PriceCatalogDaoExample
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
		// findWhereCatalogCodeEquals("");
		// findWhereCatalogNameEquals("");
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
		PriceCatalogDao dao = DaoFactory.createPriceCatalogDao();
		List<PriceCatalog> _result = dao.findAll();
		for (PriceCatalog dto : _result) {
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
		PriceCatalogDao dao = DaoFactory.createPriceCatalogDao();
		List<PriceCatalog> _result = dao.findWhereIdEquals(id);
		for (PriceCatalog dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCatalogCodeEquals'
	 * 
	 * @param catalogCode
	 * @throws Exception
	 */
	public static void findWhereCatalogCodeEquals(String catalogCode) throws Exception
	{
		PriceCatalogDao dao = DaoFactory.createPriceCatalogDao();
		List<PriceCatalog> _result = dao.findWhereCatalogCodeEquals(catalogCode);
		for (PriceCatalog dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCatalogNameEquals'
	 * 
	 * @param catalogName
	 * @throws Exception
	 */
	public static void findWhereCatalogNameEquals(String catalogName) throws Exception
	{
		PriceCatalogDao dao = DaoFactory.createPriceCatalogDao();
		List<PriceCatalog> _result = dao.findWhereCatalogNameEquals(catalogName);
		for (PriceCatalog dto : _result) {
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
		PriceCatalogDao dao = DaoFactory.createPriceCatalogDao();
		List<PriceCatalog> _result = dao.findWhereIsActiveEquals(isActive);
		for (PriceCatalog dto : _result) {
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
		PriceCatalogDao dao = DaoFactory.createPriceCatalogDao();
		List<PriceCatalog> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (PriceCatalog dto : _result) {
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
		PriceCatalogDao dao = DaoFactory.createPriceCatalogDao();
		List<PriceCatalog> _result = dao.findWhereCreatedByEquals(createdBy);
		for (PriceCatalog dto : _result) {
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
		PriceCatalogDao dao = DaoFactory.createPriceCatalogDao();
		List<PriceCatalog> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (PriceCatalog dto : _result) {
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
		PriceCatalogDao dao = DaoFactory.createPriceCatalogDao();
		List<PriceCatalog> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (PriceCatalog dto : _result) {
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
		PriceCatalogDao dao = DaoFactory.createPriceCatalogDao();
		List<PriceCatalog> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (PriceCatalog dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(PriceCatalog dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getCatalogCode() );
		buf.append( ", " );
		buf.append( dto.getCatalogName() );
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

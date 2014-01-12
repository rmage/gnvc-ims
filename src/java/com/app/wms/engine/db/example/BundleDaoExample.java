package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.BundleDao;
import com.app.wms.engine.db.dto.Bundle;
import com.app.wms.engine.db.exceptions.BundleDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class BundleDaoExample
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
		// findWhereBundleCodeEquals("");
		// findWhereBundleNameEquals("");
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
		BundleDao dao = DaoFactory.createBundleDao();
		List<Bundle> _result = dao.findAll();
		for (Bundle dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBundleCodeEquals'
	 * 
	 * @param bundleCode
	 * @throws Exception
	 */
	public static void findWhereBundleCodeEquals(String bundleCode) throws Exception
	{
		BundleDao dao = DaoFactory.createBundleDao();
		List<Bundle> _result = dao.findWhereBundleCodeEquals(bundleCode);
		for (Bundle dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBundleNameEquals'
	 * 
	 * @param bundleName
	 * @throws Exception
	 */
	public static void findWhereBundleNameEquals(String bundleName) throws Exception
	{
		BundleDao dao = DaoFactory.createBundleDao();
		List<Bundle> _result = dao.findWhereBundleNameEquals(bundleName);
		for (Bundle dto : _result) {
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
		BundleDao dao = DaoFactory.createBundleDao();
		List<Bundle> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Bundle dto : _result) {
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
		BundleDao dao = DaoFactory.createBundleDao();
		List<Bundle> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Bundle dto : _result) {
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
		BundleDao dao = DaoFactory.createBundleDao();
		List<Bundle> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Bundle dto : _result) {
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
		BundleDao dao = DaoFactory.createBundleDao();
		List<Bundle> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Bundle dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Bundle dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getBundleCode() );
		buf.append( ", " );
		buf.append( dto.getBundleName() );
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

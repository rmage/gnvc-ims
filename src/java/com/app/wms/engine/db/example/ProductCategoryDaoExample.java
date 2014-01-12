package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.ProductCategoryDao;
import com.app.wms.engine.db.dto.ProductCategory;
import com.app.wms.engine.db.exceptions.ProductCategoryDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class ProductCategoryDaoExample
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
		// findWhereCategoryCodeEquals("");
		// findWhereCategoryNameEquals("");
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
		ProductCategoryDao dao = DaoFactory.createProductCategoryDao();
		List<ProductCategory> _result = dao.findAll();
		for (ProductCategory dto : _result) {
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
		ProductCategoryDao dao = DaoFactory.createProductCategoryDao();
		List<ProductCategory> _result = dao.findWhereIdEquals(id);
		for (ProductCategory dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCategoryCodeEquals'
	 * 
	 * @param categoryCode
	 * @throws Exception
	 */
	public static void findWhereCategoryCodeEquals(String categoryCode) throws Exception
	{
		ProductCategoryDao dao = DaoFactory.createProductCategoryDao();
		List<ProductCategory> _result = dao.findWhereCategoryCodeEquals(categoryCode);
		for (ProductCategory dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCategoryNameEquals'
	 * 
	 * @param categoryName
	 * @throws Exception
	 */
	public static void findWhereCategoryNameEquals(String categoryName) throws Exception
	{
		ProductCategoryDao dao = DaoFactory.createProductCategoryDao();
		List<ProductCategory> _result = dao.findWhereCategoryNameEquals(categoryName);
		for (ProductCategory dto : _result) {
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
		ProductCategoryDao dao = DaoFactory.createProductCategoryDao();
		List<ProductCategory> _result = dao.findWhereIsActiveEquals(isActive);
		for (ProductCategory dto : _result) {
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
		ProductCategoryDao dao = DaoFactory.createProductCategoryDao();
		List<ProductCategory> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (ProductCategory dto : _result) {
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
		ProductCategoryDao dao = DaoFactory.createProductCategoryDao();
		List<ProductCategory> _result = dao.findWhereCreatedByEquals(createdBy);
		for (ProductCategory dto : _result) {
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
		ProductCategoryDao dao = DaoFactory.createProductCategoryDao();
		List<ProductCategory> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (ProductCategory dto : _result) {
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
		ProductCategoryDao dao = DaoFactory.createProductCategoryDao();
		List<ProductCategory> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (ProductCategory dto : _result) {
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
		ProductCategoryDao dao = DaoFactory.createProductCategoryDao();
		List<ProductCategory> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (ProductCategory dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(ProductCategory dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getCategoryCode() );
		buf.append( ", " );
		buf.append( dto.getCategoryName() );
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

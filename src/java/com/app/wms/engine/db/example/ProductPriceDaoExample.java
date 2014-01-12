package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.ProductPriceDao;
import com.app.wms.engine.db.dto.ProductPrice;
import com.app.wms.engine.db.exceptions.ProductPriceDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class ProductPriceDaoExample
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
		// findWhereProductCodeEquals("");
		// findWhereCatalogCodeEquals("");
		// findWhereBasePriceEquals(0);
		// findWhereDiscountPercentEquals(0);
		// findWhereDiscountPriceEquals(0);
		// findWhereValuePriceEquals(0);
		// findWhereBaseVatEquals(0);
		// findWherePercentVatEquals(0);
		// findWhereValueVatEquals(0);
		// findWhereCustomerPriceEquals(0);
		// findWhereVendorPriceEquals(0);
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
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findAll();
		for (ProductPrice dto : _result) {
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
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWhereIdEquals(id);
		for (ProductPrice dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductCodeEquals'
	 * 
	 * @param productCode
	 * @throws Exception
	 */
	public static void findWhereProductCodeEquals(String productCode) throws Exception
	{
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWhereProductCodeEquals(productCode);
		for (ProductPrice dto : _result) {
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
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWhereCatalogCodeEquals(catalogCode);
		for (ProductPrice dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBasePriceEquals'
	 * 
	 * @param basePrice
	 * @throws Exception
	 */
	public static void findWhereBasePriceEquals(float basePrice) throws Exception
	{
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWhereBasePriceEquals(basePrice);
		for (ProductPrice dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDiscountPercentEquals'
	 * 
	 * @param discountPercent
	 * @throws Exception
	 */
	public static void findWhereDiscountPercentEquals(float discountPercent) throws Exception
	{
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWhereDiscountPercentEquals(discountPercent);
		for (ProductPrice dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDiscountPriceEquals'
	 * 
	 * @param discountPrice
	 * @throws Exception
	 */
	public static void findWhereDiscountPriceEquals(float discountPrice) throws Exception
	{
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWhereDiscountPriceEquals(discountPrice);
		for (ProductPrice dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereValuePriceEquals'
	 * 
	 * @param valuePrice
	 * @throws Exception
	 */
	public static void findWhereValuePriceEquals(float valuePrice) throws Exception
	{
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWhereValuePriceEquals(valuePrice);
		for (ProductPrice dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBaseVatEquals'
	 * 
	 * @param baseVat
	 * @throws Exception
	 */
	public static void findWhereBaseVatEquals(float baseVat) throws Exception
	{
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWhereBaseVatEquals(baseVat);
		for (ProductPrice dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePercentVatEquals'
	 * 
	 * @param percentVat
	 * @throws Exception
	 */
	public static void findWherePercentVatEquals(float percentVat) throws Exception
	{
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWherePercentVatEquals(percentVat);
		for (ProductPrice dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereValueVatEquals'
	 * 
	 * @param valueVat
	 * @throws Exception
	 */
	public static void findWhereValueVatEquals(float valueVat) throws Exception
	{
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWhereValueVatEquals(valueVat);
		for (ProductPrice dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCustomerPriceEquals'
	 * 
	 * @param customerPrice
	 * @throws Exception
	 */
	public static void findWhereCustomerPriceEquals(float customerPrice) throws Exception
	{
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWhereCustomerPriceEquals(customerPrice);
		for (ProductPrice dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereVendorPriceEquals'
	 * 
	 * @param vendorPrice
	 * @throws Exception
	 */
	public static void findWhereVendorPriceEquals(float vendorPrice) throws Exception
	{
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWhereVendorPriceEquals(vendorPrice);
		for (ProductPrice dto : _result) {
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
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWhereIsActiveEquals(isActive);
		for (ProductPrice dto : _result) {
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
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (ProductPrice dto : _result) {
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
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWhereCreatedByEquals(createdBy);
		for (ProductPrice dto : _result) {
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
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (ProductPrice dto : _result) {
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
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (ProductPrice dto : _result) {
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
		ProductPriceDao dao = DaoFactory.createProductPriceDao();
		List<ProductPrice> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (ProductPrice dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(ProductPrice dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getProductCode() );
		buf.append( ", " );
		buf.append( dto.getCatalogCode() );
		buf.append( ", " );
		buf.append( dto.getBasePrice() );
		buf.append( ", " );
		buf.append( dto.getDiscountPercent() );
		buf.append( ", " );
		buf.append( dto.getDiscountPrice() );
		buf.append( ", " );
		buf.append( dto.getValuePrice() );
		buf.append( ", " );
		buf.append( dto.getBaseVat() );
		buf.append( ", " );
		buf.append( dto.getPercentVat() );
		buf.append( ", " );
		buf.append( dto.getValueVat() );
		buf.append( ", " );
		buf.append( dto.getCustomerPrice() );
		buf.append( ", " );
		buf.append( dto.getVendorPrice() );
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

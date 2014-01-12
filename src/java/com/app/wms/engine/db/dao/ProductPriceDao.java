package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.ProductPriceDao;
import com.app.wms.engine.db.dto.ProductPrice;
import com.app.wms.engine.db.dto.ProductPricePk;
import com.app.wms.engine.db.exceptions.ProductPriceDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface ProductPriceDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return ProductPricePk
	 */
	public ProductPricePk insert(ProductPrice dto);

	/** 
	 * Updates a single row in the product_price table.
	 */
	public void update(ProductPricePk pk, ProductPrice dto) throws ProductPriceDaoException;

	/** 
	 * Deletes a single row in the product_price table.
	 */
	public void delete(ProductPricePk pk) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'id = :id'.
	 */
	public ProductPrice findByPrimaryKey(int id) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria ''.
	 */
	public List<ProductPrice> findAll() throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'id = :id'.
	 */
	public List<ProductPrice> findWhereIdEquals(int id) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'product_code = :productCode'.
	 */
	public List<ProductPrice> findWhereProductCodeEquals(String productCode) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'catalog_code = :catalogCode'.
	 */
	public List<ProductPrice> findWhereCatalogCodeEquals(String catalogCode) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'base_price = :basePrice'.
	 */
	public List<ProductPrice> findWhereBasePriceEquals(float basePrice) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'discount_percent = :discountPercent'.
	 */
	public List<ProductPrice> findWhereDiscountPercentEquals(float discountPercent) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'discount_price = :discountPrice'.
	 */
	public List<ProductPrice> findWhereDiscountPriceEquals(float discountPrice) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'value_price = :valuePrice'.
	 */
	public List<ProductPrice> findWhereValuePriceEquals(float valuePrice) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'base_vat = :baseVat'.
	 */
	public List<ProductPrice> findWhereBaseVatEquals(float baseVat) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'percent_vat = :percentVat'.
	 */
	public List<ProductPrice> findWherePercentVatEquals(float percentVat) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'value_vat = :valueVat'.
	 */
	public List<ProductPrice> findWhereValueVatEquals(float valueVat) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'customer_price = :customerPrice'.
	 */
	public List<ProductPrice> findWhereCustomerPriceEquals(float customerPrice) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'vendor_price = :vendorPrice'.
	 */
	public List<ProductPrice> findWhereVendorPriceEquals(float vendorPrice) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'is_active = :isActive'.
	 */
	public List<ProductPrice> findWhereIsActiveEquals(String isActive) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<ProductPrice> findWhereIsDeleteEquals(String isDelete) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'created_by = :createdBy'.
	 */
	public List<ProductPrice> findWhereCreatedByEquals(String createdBy) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'created_date = :createdDate'.
	 */
	public List<ProductPrice> findWhereCreatedDateEquals(Date createdDate) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<ProductPrice> findWhereUpdatedByEquals(String updatedBy) throws ProductPriceDaoException;

	/** 
	 * Returns all rows from the product_price table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<ProductPrice> findWhereUpdatedDateEquals(Date updatedDate) throws ProductPriceDaoException;

	/** 
	 * Returns the rows from the product_price table that matches the specified primary-key value.
	 */
	public ProductPrice findByPrimaryKey(ProductPricePk pk) throws ProductPriceDaoException;

}

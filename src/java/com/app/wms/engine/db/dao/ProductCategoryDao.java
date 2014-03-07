package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.ProductCategoryDao;
import com.app.wms.engine.db.dto.ProductCategory;
import com.app.wms.engine.db.dto.ProductCategoryPk;
import com.app.wms.engine.db.exceptions.ProductCategoryDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface ProductCategoryDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return ProductCategoryPk
	 */
	public ProductCategoryPk insert(ProductCategory dto);

	/** 
	 * Updates a single row in the product_category table.
	 */
	public void update(ProductCategoryPk pk, ProductCategory dto) throws ProductCategoryDaoException;

	/** 
	 * Deletes a single row in the product_category table.
	 */
	public void delete(ProductCategoryPk pk) throws ProductCategoryDaoException;

	/** 
	 * Returns all rows from the product_category table that match the criteria 'id = :id'.
	 */
	public ProductCategory findByPrimaryKey(int id) throws ProductCategoryDaoException;

	/** 
	 * Returns all rows from the product_category table that match the criteria ''.
	 */
	public List<ProductCategory> findAll() throws ProductCategoryDaoException;

	/** 
	 * Returns all rows from the product_category table that match the criteria 'id = :id'.
	 */
	public List<ProductCategory> findWhereIdEquals(int id) throws ProductCategoryDaoException;

	/** 
	 * Returns all rows from the product_category table that match the criteria 'category_code = :categoryCode'.
	 */
	public List<ProductCategory> findWhereCategoryCodeEquals(String categoryCode) throws ProductCategoryDaoException;

	/** 
	 * Returns all rows from the product_category table that match the criteria 'category_name = :categoryName'.
	 */
	public List<ProductCategory> findWhereCategoryNameEquals(String categoryName) throws ProductCategoryDaoException;

	/** 
	 * Returns all rows from the product_category table that match the criteria 'is_active = :isActive'.
	 */
	public List<ProductCategory> findWhereIsActiveEquals(String isActive) throws ProductCategoryDaoException;

	/** 
	 * Returns all rows from the product_category table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<ProductCategory> findWhereIsDeleteEquals(String isDelete) throws ProductCategoryDaoException;

	/** 
	 * Returns all rows from the product_category table that match the criteria 'created_by = :createdBy'.
	 */
	public List<ProductCategory> findWhereCreatedByEquals(String createdBy) throws ProductCategoryDaoException;

	/** 
	 * Returns all rows from the product_category table that match the criteria 'created_date = :createdDate'.
	 */
	public List<ProductCategory> findWhereCreatedDateEquals(Date createdDate) throws ProductCategoryDaoException;

	/** 
	 * Returns all rows from the product_category table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<ProductCategory> findWhereUpdatedByEquals(String updatedBy) throws ProductCategoryDaoException;

	/** 
	 * Returns all rows from the product_category table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<ProductCategory> findWhereUpdatedDateEquals(Date updatedDate) throws ProductCategoryDaoException;

	/** 
	 * Returns the rows from the product_category table that matches the specified primary-key value.
	 */
	public ProductCategory findByPrimaryKey(ProductCategoryPk pk) throws ProductCategoryDaoException;
	
	public List<ProductCategory> findProductCategoryPaging(ProductCategory p, int page) throws ProductCategoryDaoException;
        
        public List<ProductCategory> findByCode(String category_code);

}

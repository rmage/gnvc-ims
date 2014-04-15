package com.app.wms.engine.db.dao;

import com.app.web.engine.search.ProductSearch;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.ProductPk;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface ProductDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return ProductPk
     */
    public ProductPk insert(Product dto);

    /**
     * Updates a single row in the product table.
     */
    public void update(ProductPk pk, Product dto) throws ProductDaoException;

    /**
     * Deletes a single row in the product table.
     */
    public void delete(ProductPk pk) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria 'id =
     * :id'.
     */
    public Product findByPrimaryKey(int id) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria ''.
     */
    public List<Product> findAll() throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria 'id =
     * :id'.
     */
    public List<Product> findWhereIdEquals(int id) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'product_id = :productId'.
     */
    public List<Product> findWhereProductIdEquals(String productId) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria 'bar_code
     * = :barCode'.
     */
    public List<Product> findWhereBarCodeEquals(String barCode) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'product_code = :productCode'.
     */
    public List<Product> findWhereProductCodeEquals(String productCode) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'product_name = :productName'.
     */
    public List<Product> findWhereProductNameEquals(String productName) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'product_alias = :productAlias'.
     */
    public List<Product> findWhereProductAliasEquals(String productAlias) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'product_category = :productCategory'.
     */
    public List<Product> findWhereProductCategoryEquals(String productCategory) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'brand_name = :brandName'.
     */
    public List<Product> findWhereBrandNameEquals(String brandName) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'product_type = :productType'.
     */
    public List<Product> findWhereProductTypeEquals(String productType) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'product_color = :productColor'.
     */
    public List<Product> findWhereProductColorEquals(String productColor) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'product_description = :productDescription'.
     */
    public List<Product> findWhereProductDescriptionEquals(String productDescription) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'volume_weight = :volumeWeight'.
     */
    public List<Product> findWhereVolumeWeightEquals(double volumeWeight) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'unit_weight = :unitWeight'.
     */
    public List<Product> findWhereUnitWeightEquals(String unitWeight) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'volume_matrix = :volumeMatrix'.
     */
    public List<Product> findWhereVolumeMatrixEquals(double volumeMatrix) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'unit_matrix = :unitMatrix'.
     */
    public List<Product> findWhereUnitMatrixEquals(String unitMatrix) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'unit_length = :unitLength'.
     */
    public List<Product> findWhereUnitLengthEquals(double unitLength) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'unit_width = :unitWidth'.
     */
    public List<Product> findWhereUnitWidthEquals(double unitWidth) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'unit_height = :unitHeight'.
     */
    public List<Product> findWhereUnitHeightEquals(double unitHeight) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'unit_piece = :unitPiece'.
     */
    public List<Product> findWhereUnitPieceEquals(int unitPiece) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria 'unit_box
     * = :unitBox'.
     */
    public List<Product> findWhereUnitBoxEquals(int unitBox) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'unit_cartoon = :unitCartoon'.
     */
    public List<Product> findWhereUnitCartoonEquals(int unitCartoon) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'unit_pallete = :unitPallete'.
     */
    public List<Product> findWhereUnitPalleteEquals(int unitPallete) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria 'user_id
     * = :userId'.
     */
    public List<Product> findWhereUserIdEquals(String userId) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria 'corp_id
     * = :corpId'.
     */
    public List<Product> findWhereCorpIdEquals(String corpId) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria 'wh_code
     * = :whCode'.
     */
    public List<Product> findWhereWhCodeEquals(String whCode) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'is_active = :isActive'.
     */
    public List<Product> findWhereIsActiveEquals(String isActive) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'is_delete = :isDelete'.
     */
    public List<Product> findWhereIsDeleteEquals(String isDelete) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'created_by = :createdBy'.
     */
    public List<Product> findWhereCreatedByEquals(String createdBy) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'created_date = :createdDate'.
     */
    public List<Product> findWhereCreatedDateEquals(Date createdDate) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'updated_by = :updatedBy'.
     */
    public List<Product> findWhereUpdatedByEquals(String updatedBy) throws ProductDaoException;

    /**
     * Returns all rows from the product table that match the criteria
     * 'updated_date = :updatedDate'.
     */
    public List<Product> findWhereUpdatedDateEquals(Date updatedDate) throws ProductDaoException;

    /**
     * Returns the rows from the product table that matches the specified
     * primary-key value.
     */
    public Product findByPrimaryKey(ProductPk pk) throws ProductDaoException;

    public List<Product> findByCriteriaLimit(ProductSearch c, int start, int end) throws ProductDaoException;

    public List<Product> findProductPaging(Product p, int page) throws ProductDaoException;

    public Product findByPrimaryKey(String productId) throws ProductDaoException;

    public List<Product> findWhereProductNameEquals(String productName, int limit);

    public List<Product> findWhereBrandNameEquals(String brandName, int limit);

    //Modified 9 April 2014
    public int ajaxMaxPage(String where, BigDecimal show);
    public List<Product> ajaxSearch(String where, String order, int page, int show);
    public Product findId(String id);
}

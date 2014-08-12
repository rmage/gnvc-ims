package com.app.wms.engine.db.dao.spring;

import com.app.web.engine.search.ProductSearch;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.ProductPk;
import com.app.wms.engine.db.dto.map.ProductListMap;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class ProductDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Product>, ProductDao {

    protected SimpleJdbcTemplate jdbcTemplate;

    protected DataSource dataSource;

    /**
     * Method 'setDataSource'
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return ProductPk
     */
    public ProductPk insert(Product dto) {
        SqlUpdate su = new SqlUpdate(dataSource, "INSERT INTO " + getTableName() + " ( bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        //su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.compile();
        su.update(new Object[]{dto.getBarCode(), dto.getProductCode(), dto.getProductName(), dto.getProductAlias(), dto.getProductCategory(), dto.getBrandName(), dto.getProductType(), dto.getProductColor(), dto.getProductDescription(), dto.getVolumeWeight(), dto.getUnitWeight(), dto.getVolumeMatrix(), dto.getUnitMatrix(), dto.getUnitLength(), dto.getUnitWidth(), dto.getUnitHeight(), dto.getUnitPiece(), dto.getUnitBox(), dto.getUnitCartoon(), dto.getUnitPallete(), dto.getUserId(), dto.getCorpId(), dto.getWhCode(), dto.getIsActive(), dto.getIsDelete(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate(), dto.getUom(), dto.getSupplier(), dto.getBuyer(), dto.getPackstyle(), dto.getPacksize(), dto.getLid(), dto.getNwdwpw()});
        return dto.createPk();
    }

    /**
     * Updates a single row in the product table.
     */
    public void update(ProductPk pk, Product dto) throws ProductDaoException {
//        SqlUpdate su = new SqlUpdate(dataSource, "UPDATE " + getTableName()
//                + " SET bar_code = ?, product_code = ?, product_name = ?, product_alias = ?, product_category = ?, "
//                + " brand_name = ?, product_type = ?, product_color = ?, product_description = ?, volume_weight = ?, "
//                + " unit_weight = ?, volume_matrix = ?, unit_matrix = ?, unit_length = ?, unit_width = ?, "
//                + " unit_height = ?, unit_piece = ?, unit_box = ?, unit_cartoon = ?, unit_pallete = ?, "
//                + " user_id = ?, corp_id = ?, wh_code = ?, is_active = ?, is_delete = ?, "
//                + " created_by = ?, created_date = ?, updated_by = ?, updated_date = ?, uom_name = ?, "
//                + " supplier_name = ?, buyer = ?, packstyle = ?, packsize = ?, lid = ?, nwdwpw = ?  WHERE id = ?");

        SqlUpdate su = new SqlUpdate(dataSource, "UPDATE " + getTableName()
                + " SET product_code=?, product_name=?, product_alias=?, product_category=?,"
                + " brand_name=?, is_active=?, is_delete=?, updated_by=?, updated_date=?,"
                + " uom_name=?, packstyle=?, packsize=?, lid=?, nwdwpw=?, product_description = ?  WHERE product_id=?");

        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
//        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
//        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
//        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
//        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
//        su.declareParameter(new SqlParameter(java.sql.Types.INTEGER));
//        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
//        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
//        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
//        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
//        su.declareParameter(new SqlParameter(java.sql.Types.CHAR));
//        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
//        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
//        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
//        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
//        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
//        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
//        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
//        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
//        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
//        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));
//        su.declareParameter(new SqlParameter(java.sql.Types.TIMESTAMP));
//        su.declareParameter(new SqlParameter(java.sql.Types.VARCHAR));

        su.compile();
//        su.update(new Object[]{dto.getBarCode(), dto.getProductCode(), dto.getProductName(), dto.getProductAlias(), dto.getProductCategory(), dto.getBrandName(), dto.getProductType(), dto.getProductColor(), dto.getProductDescription(), dto.getVolumeWeight(), dto.getUnitWeight(), dto.getVolumeMatrix(), dto.getUnitMatrix(), dto.getUnitLength(), dto.getUnitWidth(), dto.getUnitHeight(), dto.getUnitPiece(), dto.getUnitBox(), dto.getUnitCartoon(), dto.getUnitPallete(), dto.getUserId(), dto.getCorpId(), dto.getWhCode(), dto.getIsActive(), dto.getIsDelete(), dto.getCreatedBy(), dto.getCreatedDate(), dto.getUpdatedBy(), dto.getUpdatedDate(), dto.getUom(), dto.getSupplier(), dto.getBuyer(), dto.getPackstyle(), dto.getPacksize(), dto.getLid(), dto.getNwdwpw(), pk.getProductId()});
        su.update(new Object[]{dto.getProductCode(), dto.getProductName(), dto.getProductAlias(), dto.getProductCategory(), dto.getBrandName(), dto.getIsActive(), dto.getIsDelete(), dto.getUpdatedBy(), dto.getUpdatedDate(), dto.getUom(), dto.getPackstyle(), dto.getPacksize(), dto.getLid(), dto.getNwdwpw(), dto.getProductDescription(),
            pk.getProductId()});
    }

    /**
     * Deletes a single row in the product table.
     */
    @Transactional
    public void delete(ProductPk pk) throws ProductDaoException {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE product_id = ?", pk.getProductId());
    }

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return Product
     */
    public Product mapRow(ResultSet rs, int row) throws SQLException {
        Product dto = new Product();
//		dto.setId( rs.getInt( 1 ) );
        dto.setProductId(rs.getString(1));
        dto.setBarCode(rs.getString(2));
        dto.setProductCode(rs.getString(3));
        dto.setProductName(rs.getString(4));
        dto.setProductAlias(rs.getString(5));
        dto.setProductCategory(rs.getString(6));
        dto.setBrandName(rs.getString(7));
        dto.setProductType(rs.getString(8));
        dto.setProductColor(rs.getString(9));
        dto.setProductDescription(rs.getString(10));
        dto.setVolumeWeight(rs.getString(11));

        dto.setUnitWeight(rs.getString(12));
        dto.setVolumeMatrix(rs.getString(13));

        dto.setUnitMatrix(rs.getString(14));
        dto.setUnitLength(rs.getString(15));

        dto.setUnitWidth(rs.getString(16));

        dto.setUnitHeight(rs.getString(17));

        dto.setUnitPiece(rs.getInt(18));
        if (rs.wasNull()) {
            dto.setUnitPieceNull(true);
        }

        dto.setUnitBox(rs.getInt(19));
        if (rs.wasNull()) {
            dto.setUnitBoxNull(true);
        }

        dto.setUnitCartoon(rs.getInt(20));
        if (rs.wasNull()) {
            dto.setUnitCartoonNull(true);
        }

        dto.setUnitPallete(rs.getInt(21));
        if (rs.wasNull()) {
            dto.setUnitPalleteNull(true);
        }

        dto.setUserId(rs.getString(22));
        dto.setCorpId(rs.getString(23));
        dto.setWhCode(rs.getString(24));
        dto.setIsActive(rs.getString(25));
        dto.setIsDelete(rs.getString(26));
        dto.setCreatedBy(rs.getString(27));
        dto.setCreatedDate(rs.getTimestamp(28));
        dto.setUpdatedBy(rs.getString(29));
        dto.setUpdatedDate(rs.getTimestamp(30));
        dto.setUom(rs.getString(31));
        dto.setSupplier(rs.getString(32));
        dto.setBuyer(rs.getString(33));
        dto.setPackstyle(rs.getString(34));
        dto.setPacksize(rs.getString(35));
        dto.setLid(rs.getString(36));
        dto.setNwdwpw(rs.getString(37));
        return dto;
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public String getTableName() {
        return "product";
    }

    /**
     * Returns all rows from the product table that match the criteria 'id =
     * :id'.
     */
    @Transactional
    public Product findByPrimaryKey(int id) throws ProductDaoException {
        try {
            List<Product> list = jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw FROM " + getTableName() + " WHERE id = ?", this, id);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria 'id =
     * :id'.
     */
    @Transactional
    public Product findByPrimaryKey(String productId) throws ProductDaoException {
        try {
            List<Product> list = jdbcTemplate.query(" select product_id, bar_code, product_code, product_name, product_alias, product_category, "
                    + "brand_name, product_type, product_color, product_description, "
                    + "volume_weight, unit_weight, volume_matrix, unit_matrix, "
                    + "unit_length, unit_width, unit_height, unit_piece, unit_box, "
                    + "unit_cartoon, unit_pallete, user_id, corp_id, wh_code, "
                    + "is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw "
                    + " FROM " + getTableName() + " WHERE product_id = ?", this, productId);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria ''.
     */
    @Transactional
    public List<Product> findAll() throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw FROM " + getTableName() + " ORDER BY id", this);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria 'id =
     * :id'.
     */
    @Transactional
    public List<Product> findWhereIdEquals(int id) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw FROM " + getTableName() + " WHERE id = ? ORDER BY id", this, id);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'product_id = :productId'.
     */
    @Transactional
    public List<Product> findWhereProductIdEquals(String productId) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE product_id = ? ORDER BY product_id", this, productId);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria 'bar_code
     * = :barCode'.
     */
    @Transactional
    public List<Product> findWhereBarCodeEquals(String barCode) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE bar_code = ? ORDER BY bar_code", this, barCode);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'product_code = :productCode'.
     */
    @Transactional
    public List<Product> findWhereProductCodeEquals(String productCode) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE product_code = ? ORDER BY product_name", this, productCode);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'product_name = :productName'.
     */
    @Transactional
    public List<Product> findWhereProductNameEquals(String productName) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE product_name = ? ORDER BY product_name", this, productName);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'product_alias = :productAlias'.
     */
    @Transactional
    public List<Product> findWhereProductAliasEquals(String productAlias) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE product_alias = ? ORDER BY product_alias", this, productAlias);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'product_category = :productCategory'.
     */
    @Transactional
    public List<Product> findWhereProductCategoryEquals(String productCategory) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, product_code, product_name, is_active, product_category, updated_by, updated_date FROM " + getTableName() + " WHERE product_category = ? ORDER BY product_name", new ProductListMap(), productCategory);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'brand_name = :brandName'.
     */
    @Transactional
    public List<Product> findWhereBrandNameEquals(String brandName) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE brand_name = ? ORDER BY brand_name", this, brandName);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'product_type = :productType'.
     */
    @Transactional
    public List<Product> findWhereProductTypeEquals(String productType) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE product_type = ? ORDER BY product_type", this, productType);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'product_color = :productColor'.
     */
    @Transactional
    public List<Product> findWhereProductColorEquals(String productColor) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE product_color = ? ORDER BY product_color", this, productColor);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'product_description = :productDescription'.
     */
    @Transactional
    public List<Product> findWhereProductDescriptionEquals(String productDescription) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE product_description = ? ORDER BY product_description", this, productDescription);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'volume_weight = :volumeWeight'.
     */
    @Transactional
    public List<Product> findWhereVolumeWeightEquals(double volumeWeight) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE volume_weight = ? ORDER BY volume_weight", this, volumeWeight);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'unit_weight = :unitWeight'.
     */
    @Transactional
    public List<Product> findWhereUnitWeightEquals(String unitWeight) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE unit_weight = ? ORDER BY unit_weight", this, unitWeight);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'volume_matrix = :volumeMatrix'.
     */
    @Transactional
    public List<Product> findWhereVolumeMatrixEquals(double volumeMatrix) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE volume_matrix = ? ORDER BY volume_matrix", this, volumeMatrix);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'unit_matrix = :unitMatrix'.
     */
    @Transactional
    public List<Product> findWhereUnitMatrixEquals(String unitMatrix) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE unit_matrix = ? ORDER BY unit_matrix", this, unitMatrix);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'unit_length = :unitLength'.
     */
    @Transactional
    public List<Product> findWhereUnitLengthEquals(double unitLength) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE unit_length = ? ORDER BY unit_length", this, unitLength);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'unit_width = :unitWidth'.
     */
    @Transactional
    public List<Product> findWhereUnitWidthEquals(double unitWidth) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE unit_width = ? ORDER BY unit_width", this, unitWidth);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'unit_height = :unitHeight'.
     */
    @Transactional
    public List<Product> findWhereUnitHeightEquals(double unitHeight) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE unit_height = ? ORDER BY unit_height", this, unitHeight);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'unit_piece = :unitPiece'.
     */
    @Transactional
    public List<Product> findWhereUnitPieceEquals(int unitPiece) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE unit_piece = ? ORDER BY unit_piece", this, unitPiece);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria 'unit_box
     * = :unitBox'.
     */
    @Transactional
    public List<Product> findWhereUnitBoxEquals(int unitBox) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE unit_box = ? ORDER BY unit_box", this, unitBox);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'unit_cartoon = :unitCartoon'.
     */
    @Transactional
    public List<Product> findWhereUnitCartoonEquals(int unitCartoon) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE unit_cartoon = ? ORDER BY unit_cartoon", this, unitCartoon);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'unit_pallete = :unitPallete'.
     */
    @Transactional
    public List<Product> findWhereUnitPalleteEquals(int unitPallete) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE unit_pallete = ? ORDER BY unit_pallete", this, unitPallete);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria 'user_id
     * = :userId'.
     */
    @Transactional
    public List<Product> findWhereUserIdEquals(String userId) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE user_id = ? ORDER BY user_id", this, userId);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria 'corp_id
     * = :corpId'.
     */
    @Transactional
    public List<Product> findWhereCorpIdEquals(String corpId) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE corp_id = ? ORDER BY corp_id", this, corpId);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria 'wh_code
     * = :whCode'.
     */
    @Transactional
    public List<Product> findWhereWhCodeEquals(String whCode) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this, whCode);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'is_active = :isActive'.
     */
    @Transactional
    public List<Product> findWhereIsActiveEquals(String isActive) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this, isActive);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'is_delete = :isDelete'.
     */
    @Transactional
    public List<Product> findWhereIsDeleteEquals(String isDelete) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this, isDelete);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'created_by = :createdBy'.
     */
    @Transactional
    public List<Product> findWhereCreatedByEquals(String createdBy) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this, createdBy);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'created_date = :createdDate'.
     */
    @Transactional
    public List<Product> findWhereCreatedDateEquals(Date createdDate) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this, createdDate);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'updated_by = :updatedBy'.
     */
    @Transactional
    public List<Product> findWhereUpdatedByEquals(String updatedBy) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this, updatedBy);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns all rows from the product table that match the criteria
     * 'updated_date = :updatedDate'.
     */
    @Transactional
    public List<Product> findWhereUpdatedDateEquals(Date updatedDate) throws ProductDaoException {
        try {
            return jdbcTemplate.query("SELECT id, product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this, updatedDate);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    /**
     * Returns the rows from the product table that matches the specified
     * primary-key value.
     */
    public Product findByPrimaryKey(ProductPk pk) throws ProductDaoException {
        return findByPrimaryKey(pk.getProductId());
    }

    @Override
    public List<Product> findByCriteriaLimit(ProductSearch c, int start, int end) throws ProductDaoException {

        if (c == null) {
            c = new ProductSearch();
        }
        try {

            c.setTableAlias(this.getTableName());

            HashMap ret = c.getCriteria();
            HashMap param = (HashMap) ret.get("parameter");
            String search = (String) ret.get("search");

            System.out.println("ret =" + ret);

            param.put("pagestart", start);
            param.put("pageend", end);

            String strQuery = "SELECT product_id, bar_code, product_code, product_name, product_alias, product_category, "
                    + "brand_name, product_type, product_color, product_description, unit_weight, unit_length, "
                    + "unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, "
                    + "corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date "
                    + "FROM product";

            return jdbcTemplate.query(strQuery, this, param);
        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }
    }

    /**
     * Returns all rows from the findProductPaging table that match the criteria
     * ''.
     */
    @Transactional
    public List<Product> findProductPaging(Product p, int page) throws ProductDaoException {
        try {
            String corpId = p.getCorpId();
            String whCode = p.getWhCode();

            String productCode = p.getProductCode();
            String productName = p.getProductName();

            int i = page;
            Map map = new HashMap();
            map.put("corpId", corpId);
            map.put("whCode", whCode);
            map.put("i", i);

            StringBuffer sb = new StringBuffer();

            if (p == null) {
                p = new Product();
            }

            if (productCode == null || productName == null) {
                productCode = "%";
                productName = "%";
            }

            sb.append("declare @Page int, @PageSize int "
                    + "set @Page = '" + i + "'; "
                    + "set @PageSize = 10; "
                    + "with PagedResult "
                    + "as (select ROW_NUMBER() over (order by product_code desc) as id, product_id, product_code, product_name, is_active, product_category from product"
                    + " where product_code like '%" + productCode + "%' and product_name like '%" + productName + "%' AND is_active = 'Y' ) "
                    + "select * from PagedResult where id between "
                    + "case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
                    + "else @Page end and @PageSize * @Page ");

            return jdbcTemplate.query(sb.toString(), new ProductListMap(), map);

        } catch (Exception e) {
            throw new ProductDaoException("Query failed", e);
        }

    }

    public List<Product> findWhereProductNameEquals(String productName, int limit) {
        HashMap hm = new HashMap();
        hm.put("productName", "%" + productName + "%");
        hm.put("limit", limit);
        return jdbcTemplate.query("SELECT TOP(:limit) product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName()
                + " WHERE product_name LIKE :productName AND is_active = 'Y' ORDER BY product_name", this, hm);
    }
    
    public List<Product> findWhereProductCodeEquals(String productCode, int limit) {
        HashMap hm = new HashMap();
        hm.put("productCode", "%" + productCode + "%");
        hm.put("limit", limit);
        return jdbcTemplate.query("SELECT TOP(:limit) product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName()
                + " WHERE product_code LIKE :productCode AND is_active = 'Y' ORDER BY product_code", this, hm);
    }
    
    public List<Product> findWhereProductNameEquals(String productName, String type, int limit) {
        HashMap hm = new HashMap();
        hm.put("productName", "%" + productName + "%");
        hm.put("productCategory", "FG");
        hm.put("limit", limit);
        return jdbcTemplate.query("SELECT TOP(:limit) product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName()
                + " WHERE product_name LIKE :productName AND product_category " + (type.equals("NF") ? "NOT " : "") + " IN (:productCategory) AND is_active = 'Y' ORDER BY product_name", this, hm);
    }

    public List<Product> findWhereBrandNameEquals(String brandName, int limit) {
        HashMap hm = new HashMap();
        hm.put("brandName", "%" + brandName + "%");
        hm.put("limit", limit);
        return jdbcTemplate.query("SELECT TOP(:limit) product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw  FROM " + getTableName()
                + " WHERE brand_name LIKE :brandName AND is_active = 'Y' ORDER BY product_name", this, hm);
    }

    //Modified 9 April 2014
    public int ajaxMaxPage(String where, BigDecimal show) {
        return jdbcTemplate.queryForInt("SELECT CEILING(COUNT(id)/?) maxpage FROM " + getTableName() + " " + (where.isEmpty() ? "WHERE is_active='Y'" : where + " AND is_active='Y'"), show);
    }

    public List<Product> ajaxSearch(String where, String order, int page, int show) {
        return jdbcTemplate.query("DECLARE @page INT, @show INT "
                + "SELECT @page=?, @show=? "
                + "SELECT * FROM ("
                + "SELECT id, product_id, product_code, product_name, is_active, product_category, ROW_NUMBER() OVER (" + (order.isEmpty() ? "ORDER BY id" : order) + ") row FROM " + getTableName() + " " + (where.isEmpty() ? "WHERE is_active='Y'" : where + " AND is_active='Y'")
                + ") list WHERE row BETWEEN (((@page - 1) * @show) + 1) AND (@page * @show)", new ProductListMap(), page, show);
    }

    public Product findId(String id) {
        List<Product> products = jdbcTemplate.query("SELECT product_id, bar_code, product_code, product_name, product_alias, product_category, brand_name, product_type, product_color, product_description, volume_weight, unit_weight, volume_matrix, unit_matrix, unit_length, unit_width, unit_height, unit_piece, unit_box, unit_cartoon, unit_pallete, user_id, corp_id, wh_code, is_active, is_delete, created_by, created_date, updated_by, updated_date, uom_name, supplier_name, buyer, packstyle, packsize, lid, nwdwpw FROM " + getTableName() + " WHERE product_id=?", this, id);
        return products.isEmpty() ? null : products.get(0);
    }
    
    public List<Map<String, Object>> getLastXInPo(String productCode, int x) {
        try {
            return jdbcTemplate.queryForList("EXEC M_PRODUCT_GET_LAST_X_IN_PURCHASE_ORDER ?, ?", productCode, x);
        } catch(DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
    
}

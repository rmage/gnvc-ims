package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.ProductCategoryDao;
import com.app.wms.engine.db.dto.ProductCategory;
import com.app.wms.engine.db.dto.ProductCategoryPk;
import com.app.wms.engine.db.dto.map.ProductCategoryListMap;
import com.app.wms.engine.db.exceptions.ProductCategoryDaoException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class ProductCategoryDaoImpl extends AbstractDAO implements ParameterizedRowMapper<ProductCategory>, ProductCategoryDao
{
	protected SimpleJdbcTemplate jdbcTemplate;

	protected DataSource dataSource;

	/**
	 * Method 'setDataSource'
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return ProductCategoryPk
	 */
	public ProductCategoryPk insert(ProductCategory dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( category_code, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getCategoryCode(),dto.getCategoryName(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		ProductCategoryPk pk = new ProductCategoryPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the product_category table.
	 */
	public void update(ProductCategoryPk pk, ProductCategory dto) throws ProductCategoryDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET category_code = ?, category_name = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getCategoryCode(),dto.getCategoryName(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the product_category table.
	 */
	@Transactional
	public void delete(ProductCategoryPk pk) throws ProductCategoryDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return ProductCategory
	 */
	public ProductCategory mapRow(ResultSet rs, int row) throws SQLException
	{
		ProductCategory dto = new ProductCategory();
		dto.setId( rs.getInt( 1 ) );
		dto.setCategoryCode( rs.getString( 2 ) );
		dto.setCategoryName( rs.getString( 3 ) );
		dto.setIsActive( rs.getString( 4 ) );
		dto.setIsDelete( rs.getString( 5 ) );
		dto.setCreatedBy( rs.getString( 6 ) );
		dto.setCreatedDate( rs.getTimestamp(7 ) );
		dto.setUpdatedBy( rs.getString( 8 ) );
		dto.setUpdatedDate( rs.getTimestamp(9 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..product_category";
	}

	/** 
	 * Returns all rows from the product_category table that match the criteria 'id = :id'.
	 */
	@Transactional
	public ProductCategory findByPrimaryKey(int id) throws ProductCategoryDaoException
	{
		try {
			List<ProductCategory> list = jdbcTemplate.query("SELECT id, category_code, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new ProductCategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_category table that match the criteria ''.
	 */
	@Transactional
	public List<ProductCategory> findAll() throws ProductCategoryDaoException {
            try {
                return jdbcTemplate.query("SELECT id, category_code, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = 'Y' ORDER BY id", this);
            } catch (Exception e) {
                throw new ProductCategoryDaoException("Query failed", e);
            }
	}

	/** 
	 * Returns all rows from the product_category table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<ProductCategory> findWhereIdEquals(int id) throws ProductCategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, category_code, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new ProductCategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_category table that match the criteria 'category_code = :categoryCode'.
	 */
	@Transactional
	public List<ProductCategory> findWhereCategoryCodeEquals(String categoryCode) throws ProductCategoryDaoException {
            try {
                return jdbcTemplate.query("SELECT id, category_code, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE category_code = ? AND is_active = 'Y' ORDER BY category_code", this,categoryCode);
            } catch (Exception e) {
                throw new ProductCategoryDaoException("Query failed", e);
            }
	}

	/** 
	 * Returns all rows from the product_category table that match the criteria 'category_name = :categoryName'.
	 */
	@Transactional
	public List<ProductCategory> findWhereCategoryNameEquals(String categoryName) throws ProductCategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, category_code, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE category_name = ? ORDER BY category_name", this,categoryName);
		}
		catch (Exception e) {
			throw new ProductCategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_category table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<ProductCategory> findWhereIsActiveEquals(String isActive) throws ProductCategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, category_code, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new ProductCategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_category table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<ProductCategory> findWhereIsDeleteEquals(String isDelete) throws ProductCategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, category_code, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new ProductCategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_category table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<ProductCategory> findWhereCreatedByEquals(String createdBy) throws ProductCategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, category_code, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new ProductCategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_category table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<ProductCategory> findWhereCreatedDateEquals(Date createdDate) throws ProductCategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, category_code, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new ProductCategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_category table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<ProductCategory> findWhereUpdatedByEquals(String updatedBy) throws ProductCategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, category_code, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new ProductCategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the product_category table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<ProductCategory> findWhereUpdatedDateEquals(Date updatedDate) throws ProductCategoryDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, category_code, category_name, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new ProductCategoryDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the product_category table that matches the specified primary-key value.
	 */
	public ProductCategory findByPrimaryKey(ProductCategoryPk pk) throws ProductCategoryDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

	public List<ProductCategory> findProductCategoryPaging(ProductCategory p, int page) throws ProductCategoryDaoException {
            try {
        	int i = page;
        	Map map = new HashMap();
        	map.put("i", i);
        	
        	if(p == null){
        		p = new ProductCategory();
        	}
        
        	StringBuffer sb = new StringBuffer();
        	String categoryCode = p.getCategoryCode();
        	String categoryName = p.getCategoryName();
        	
        	if(categoryCode == null || categoryName == null){
                    categoryCode ="%";
                    categoryName ="%";
        	}
                
                sb.append("declare @Page int, @PageSize int "
                    +"set @Page = '"+i+"'; "
                    +"set @PageSize = 10; "
                    +"with PagedResult "
                    +"as (select id, category_code, category_name, is_active, ROW_NUMBER() over (order by id asc) as ids from product_category" +
                                    " where category_code like '%"+categoryCode+"%' and category_name like '%"+categoryName+"%' and is_active = 'Y') "
                        +"select * from PagedResult where ids between "
                    +"case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 "
                         +"else @Page end and @PageSize * @Page ");
        	
        	return jdbcTemplate.query(sb.toString(),new ProductCategoryListMap(),map);	
        
        } catch (Exception e) {
            throw new ProductCategoryDaoException("Query failed", e);
        }
    }
        public List<ProductCategory> findByCode(String category_code){
            return jdbcTemplate.query("SELECT * FROM product_category WHERE category_code = ?", this, category_code);
        }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.CategoryItemCurrencyTypeDao;
import com.app.wms.engine.db.dto.CategoryItemCurrencyType;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/**
 *
 * @author Faridzi
 */
public class CategoryItemCurrencyTypeDaoImpl extends AbstractDAO implements ParameterizedRowMapper<CategoryItemCurrencyType>, CategoryItemCurrencyTypeDao {

    private SimpleJdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getTableName() {
        return "category_item_currency_type";
    }

    public CategoryItemCurrencyType mapRow(ResultSet rs, int i) throws SQLException {
        CategoryItemCurrencyType categoryItemCurrencyType = new CategoryItemCurrencyType();
        categoryItemCurrencyType.setNumber(rs.getInt("number"));
        categoryItemCurrencyType.setId(rs.getInt("id"));
        categoryItemCurrencyType.setCategoryCode(rs.getString("category_code"));
        categoryItemCurrencyType.setProductId(rs.getInt("product_id"));
        categoryItemCurrencyType.setCurrencyType(rs.getString("currency_type"));
        categoryItemCurrencyType.setCreatedBy(rs.getString("created_by"));
        categoryItemCurrencyType.setCreatedDate(rs.getDate("created_date"));
        categoryItemCurrencyType.setUpdatedBy(rs.getString("updated_by"));
        categoryItemCurrencyType.setUpdatedDate(rs.getDate("updated_date"));
        return categoryItemCurrencyType;
    }

    public int insert(CategoryItemCurrencyType ciCurrType) {
        return jdbcTemplate.update("INSERT INTO " + getTableName() + " VALUES(?, ?, ?, ?, ?, ?, ?)",
                ciCurrType.getCategoryCode(), ciCurrType.getProductId(), ciCurrType.getCurrencyType(), ciCurrType.getCreatedBy(), ciCurrType.getCreatedDate(), ciCurrType.getUpdatedBy(), ciCurrType.getUpdatedDate());
    }

    public int update(CategoryItemCurrencyType ciCurrType) {
        return jdbcTemplate.update("UPDATE " + getTableName() + " SET updated_by = ? , updated_date = ? , currency_type = ?  WHERE id = ?", ciCurrType.getUpdatedBy(), ciCurrType.getUpdatedDate(), ciCurrType.getCurrencyType(), ciCurrType.getId());
    }

    public int ajaxMaxPage(String where, BigDecimal show) {
        if (where == null || where.equalsIgnoreCase("")) {
            where = "WHERE 1 = 1 ";
        }
        return jdbcTemplate.queryForInt("SELECT CEILING(COUNT(id)/?) maxpage FROM " + getTableName() + " " + where, show);
    }

    public List<CategoryItemCurrencyType> ajaxSearch(String condition, String where, String order, int page, int show) {
        return jdbcTemplate.query("declare @Page int, @PageSize int set @Page = ?; set @PageSize = ?; "
                + "with PagedResult as (select ROW_NUMBER() over (order by id asc) as number, id, "
                + "category_code, product_id, currency_type , created_by, created_date, updated_by , updated_date from " + getTableName() + (where.isEmpty() ? " WHERE 1=1 " : " " + where) + " ) "
                + "select * from PagedResult where number between case when @Page > 1 then (@PageSize * @Page) - @PageSize + 1 else @Page end and @PageSize * @Page", this, page, show);
    }

    public CategoryItemCurrencyType findCurrencyTypeByCategoryCode(String categoryCode) {
        List<CategoryItemCurrencyType> cris = jdbcTemplate.query("select id as number , * from " + getTableName() + " where category_code = ? ", this, categoryCode);
        return cris.isEmpty() ? null : cris.get(0);
    }

}

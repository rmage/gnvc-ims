package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.CityDao;
import com.app.wms.engine.db.dto.City;
import com.app.wms.engine.db.dto.CityPk;
import com.app.wms.engine.db.exceptions.CityDaoException;
import com.app.web.engine.search.CitySearch;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface CityDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return CityPk
     */
    public CityPk insert(City dto);

    /**
     * Updates a single row in the CITY table.
     */
    public void update(CityPk pk, City dto) throws CityDaoException;

    /**
     * Deletes a single row in the CITY table.
     */
    public void delete(CityPk pk) throws CityDaoException;

    /**
     * Returns all rows from the CITY table that match the criteria 'CITY_CODE = :cityCode'.
     */
    public City findByPrimaryKey(String cityCode) throws CityDaoException;

    /**
     * Returns all rows from the CITY table that match the criteria ''.
     */
    public List<City> findAll() throws CityDaoException;

    /**
     * Returns all rows from the CITY table that match the criteria 'CITY_CODE = :cityCode'.
     */
    public List<City> findWhereCityCodeEquals(String cityCode) throws CityDaoException;

    /**
     * Returns all rows from the CITY table that match the criteria 'NAME = :name'.
     */
    public List<City> findWhereNameEquals(String name) throws CityDaoException;

    /**
     * Returns all rows from the CITY table that match the criteria 'CREATED_BY = :createdBy'.
     */
    public List<City> findWhereCreatedByEquals(BigDecimal createdBy) throws CityDaoException;

    /**
     * Returns all rows from the CITY table that match the criteria 'CREATED_DATE = :createdDate'.
     */
    public List<City> findWhereCreatedDateEquals(Date createdDate) throws CityDaoException;

    /**
     * Returns all rows from the CITY table that match the criteria 'UPDATED_BY = :updatedBy'.
     */
    public List<City> findWhereUpdatedByEquals(BigDecimal updatedBy) throws CityDaoException;

    /**
     * Returns all rows from the CITY table that match the criteria 'UPDATED_DATE = :updatedDate'.
     */
    public List<City> findWhereUpdatedDateEquals(Date updatedDate) throws CityDaoException;

    /**
     * Returns the rows from the CITY table that matches the specified primary-key value.
     */
    public City findByPrimaryKey(CityPk pk) throws CityDaoException;

    public List<City> findLimit(int page, int paging) throws CityDaoException;

    public List<City> findByCriteriaLimit(CitySearch criteria, int start, int end) throws CityDaoException;

    public int countTotalRows() throws CityDaoException;
}

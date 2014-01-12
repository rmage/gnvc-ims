package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.RegionDao;
import com.app.wms.engine.db.dto.Region;
import com.app.wms.engine.db.dto.RegionPk;
import com.app.wms.engine.db.exceptions.RegionDaoException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface RegionDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return RegionPk
     */
    public RegionPk insert(Region dto);

    /**
     * Updates a single row in the CITY table.
     */
    public void update(RegionPk pk, Region dto) throws RegionDaoException;

    /**
     * Deletes a single row in the CITY table.
     */
    public void delete(RegionPk pk) throws RegionDaoException;

    /**
     * Returns all rows from the CITY table that match the criteria 'CITY_CODE = :regionCode'.
     */
    public Region findByPrimaryKey(String regionCode) throws RegionDaoException;

    /**
     * Returns all rows from the CITY table that match the criteria ''.
     */
    public List<Region> findAll() throws RegionDaoException;

    /**
     * Returns all rows from the CITY table that match the criteria 'CITY_CODE = :regionCode'.
     */
    public List<Region> findWhereRegionCodeEquals(String regionCode) throws RegionDaoException;

    /**
     * Returns all rows from the CITY table that match the criteria 'NAME = :name'.
     */
    public List<Region> findWhereNameEquals(String name) throws RegionDaoException;

    /**
     * Returns all rows from the CITY table that match the criteria 'CREATED_BY = :createdBy'.
     */
    public List<Region> findWhereCreatedByEquals(BigDecimal createdBy) throws RegionDaoException;

    /**
     * Returns all rows from the CITY table that match the criteria 'CREATED_DATE = :createdDate'.
     */
    public List<Region> findWhereCreatedDateEquals(Date createdDate) throws RegionDaoException;

    /**
     * Returns all rows from the CITY table that match the criteria 'UPDATED_BY = :updatedBy'.
     */
    public List<Region> findWhereUpdatedByEquals(BigDecimal updatedBy) throws RegionDaoException;

    /**
     * Returns all rows from the CITY table that match the criteria 'UPDATED_DATE = :updatedDate'.
     */
    public List<Region> findWhereUpdatedDateEquals(Date updatedDate) throws RegionDaoException;

    /**
     * Returns the rows from the CITY table that matches the specified primary-key value.
     */
    public Region findByPrimaryKey(RegionPk pk) throws RegionDaoException;

    public List<Region> findLimit(int page, int paging) throws RegionDaoException;

    public int countTotalRows() throws RegionDaoException;

    public void assignBg(String regionCode, List<String> bgCode) throws RegionDaoException;

    public void removeBg(List<String> bgCode) throws RegionDaoException;
}

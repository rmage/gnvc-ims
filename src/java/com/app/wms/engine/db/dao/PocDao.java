package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.PocDao;
import com.app.wms.engine.db.dto.Poc;
import com.app.wms.engine.db.dto.PocPk;
import com.app.wms.engine.db.exceptions.PocDaoException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface PocDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return PocPk
     */
    public PocPk insert(Poc dto);

    /**
     * Updates a single row in the POC table.
     */
    public void update(PocPk pk, Poc dto) throws PocDaoException;

    /**
     * Deletes a single row in the POC table.
     */
    public void delete(PocPk pk) throws PocDaoException;

    /**
     * Returns all rows from the POC table that match the criteria 'POC_CODE = :pocCode'.
     */
    public Poc findByPrimaryKey(String pocCode) throws PocDaoException;

    /**
     * Returns all rows from the POC table that match the criteria ''.
     */
    public List<Poc> findAll() throws PocDaoException;

    /**
     * Returns all rows from the POC table that match the criteria 'POC_CODE = :pocCode'.
     */
    public List<Poc> findWherePocCodeEquals(String pocCode) throws PocDaoException;

    /**
     * Returns all rows from the POC table that match the criteria 'NAME = :name'.
     */
    public List<Poc> findWhereNameEquals(String name) throws PocDaoException;

    /**
     * Returns all rows from the POC table that match the criteria 'CREATED_BY = :createdBy'.
     */
    public List<Poc> findWhereCreatedByEquals(BigDecimal createdBy) throws PocDaoException;

    /**
     * Returns all rows from the POC table that match the criteria 'CREATED_DATE = :createdDate'.
     */
    public List<Poc> findWhereCreatedDateEquals(Date createdDate) throws PocDaoException;

    /**
     * Returns all rows from the POC table that match the criteria 'UPDATED_BY = :updatedBy'.
     */
    public List<Poc> findWhereUpdatedByEquals(BigDecimal updatedBy) throws PocDaoException;

    /**
     * Returns all rows from the POC table that match the criteria 'UPDATED_DATE = :updatedDate'.
     */
    public List<Poc> findWhereUpdatedDateEquals(Date updatedDate) throws PocDaoException;

    /**
     * Returns the rows from the POC table that matches the specified primary-key value.
     */
    public Poc findByPrimaryKey(PocPk pk) throws PocDaoException;

    public List<Poc> findLimit(int page, int paging) throws PocDaoException;

    public List<Poc> findUnassignedToBg(String bgCode) throws PocDaoException;

    public List<Poc> findAssignedToBg(String bgCode) throws PocDaoException;

}

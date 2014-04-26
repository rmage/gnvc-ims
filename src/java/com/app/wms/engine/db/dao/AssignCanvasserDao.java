package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.AssignCanvasserDao;
import com.app.wms.engine.db.dto.AssignCanvasser;
import com.app.wms.engine.db.dto.AssignCanvasserPk;
import com.app.wms.engine.db.exceptions.AssignCanvasserDaoException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface AssignCanvasserDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return AssignCanvasserPk
     */
    public AssignCanvasserPk insert(AssignCanvasser dto);

    /**
     * Updates a single row in the canvasserassignment table.
     */
    public void update(AssignCanvasserPk pk, AssignCanvasser dto) throws AssignCanvasserDaoException;

    /**
     * Deletes a single row in the canvasserassignment table.
     */
    public void delete(AssignCanvasserPk pk) throws AssignCanvasserDaoException;

    /**
     * Returns all rows from the canvasserassignment table that match the
     * criteria 'id = :id'.
     */
    public AssignCanvasser findByPrimaryKey(int id) throws AssignCanvasserDaoException;

    /**
     * Returns all rows from the canvasserassignment table that match the
     * criteria ''.
     */
    public List<AssignCanvasser> findAll() throws AssignCanvasserDaoException;

    /**
     * Returns all rows from the canvasserassignment table that match the
     * criteria 'id = :id'.
     */
    public List<AssignCanvasser> findWhereIdEquals(int id) throws AssignCanvasserDaoException;

    /**
     * Returns all rows from the canvasserassignment table that match the
     * criteria 'prsnumber = :prsnumber'.
     */
    public List<AssignCanvasser> findWherePrsnumberEquals(String prsnumber) throws AssignCanvasserDaoException;

    /**
     * Returns all rows from the canvasserassignment table that match the
     * criteria 'canvassername = :canvassername'.
     */
    public List<AssignCanvasser> findWhereCanvassernameEquals(String canvassername) throws AssignCanvasserDaoException;

    /**
     * Returns all rows from the canvasserassignment table that match the
     * criteria 'created_by = :createdBy'.
     */
    public List<AssignCanvasser> findWhereCreatedByEquals(String createdBy) throws AssignCanvasserDaoException;

    /**
     * Returns all rows from the canvasserassignment table that match the
     * criteria 'created_date = :createdDate'.
     */
    public List<AssignCanvasser> findWhereCreatedDateEquals(Date createdDate) throws AssignCanvasserDaoException;

    /**
     * Returns all rows from the canvasserassignment table that match the
     * criteria 'updated_by = :updatedBy'.
     */
    public List<AssignCanvasser> findWhereUpdatedByEquals(String updatedBy) throws AssignCanvasserDaoException;

    /**
     * Returns all rows from the canvasserassignment table that match the
     * criteria 'updated_date = :updatedDate'.
     */
    public List<AssignCanvasser> findWhereUpdatedDateEquals(Date updatedDate) throws AssignCanvasserDaoException;

    /**
     * Returns the rows from the canvasserassignment table that matches the
     * specified primary-key value.
     */
    public AssignCanvasser findByPrimaryKey(AssignCanvasserPk pk) throws AssignCanvasserDaoException;

    public List<AssignCanvasser> findCanvasserAssignPaging(AssignCanvasser c, int page) throws AssignCanvasserDaoException;

    //Modified 25 April 2014
    public int ajaxMaxPage(String where, BigDecimal show);
    public List<AssignCanvasser> ajaxSearch(String where, String order, int page, int show);
}

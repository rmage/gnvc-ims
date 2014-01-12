package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.CanvasserassignmentDao;
import com.app.wms.engine.db.dto.Canvasserassignment;
import com.app.wms.engine.db.dto.CanvasserassignmentPk;
import com.app.wms.engine.db.exceptions.CanvasserassignmentDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface CanvasserassignmentDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return CanvasserassignmentPk
	 */
	public CanvasserassignmentPk insert(Canvasserassignment dto);

	/** 
	 * Updates a single row in the canvasserassignment table.
	 */
	public void update(CanvasserassignmentPk pk, Canvasserassignment dto) throws CanvasserassignmentDaoException;

	/** 
	 * Deletes a single row in the canvasserassignment table.
	 */
	public void delete(CanvasserassignmentPk pk) throws CanvasserassignmentDaoException;

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'id = :id'.
	 */
	public Canvasserassignment findByPrimaryKey(int id) throws CanvasserassignmentDaoException;

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria ''.
	 */
	public List<Canvasserassignment> findAll() throws CanvasserassignmentDaoException;

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'id = :id'.
	 */
	public List<Canvasserassignment> findWhereIdEquals(int id) throws CanvasserassignmentDaoException;

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'prsnumber = :prsnumber'.
	 */
	public List<Canvasserassignment> findWherePrsnumberEquals(String prsnumber) throws CanvasserassignmentDaoException;

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'canvassername = :canvassername'.
	 */
	public List<Canvasserassignment> findWhereCanvassernameEquals(String canvassername) throws CanvasserassignmentDaoException;

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Canvasserassignment> findWhereCreatedByEquals(String createdBy) throws CanvasserassignmentDaoException;

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Canvasserassignment> findWhereCreatedDateEquals(Date createdDate) throws CanvasserassignmentDaoException;

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Canvasserassignment> findWhereUpdatedByEquals(String updatedBy) throws CanvasserassignmentDaoException;

	/** 
	 * Returns all rows from the canvasserassignment table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Canvasserassignment> findWhereUpdatedDateEquals(Date updatedDate) throws CanvasserassignmentDaoException;

	/** 
	 * Returns the rows from the canvasserassignment table that matches the specified primary-key value.
	 */
	public Canvasserassignment findByPrimaryKey(CanvasserassignmentPk pk) throws CanvasserassignmentDaoException;

	public List<Canvasserassignment> findCanvasserAssignPaging (Canvasserassignment c, int page) throws CanvasserassignmentDaoException;
}

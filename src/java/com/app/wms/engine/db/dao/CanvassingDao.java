package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.CanvassingDao;
import com.app.wms.engine.db.dto.Canvassing;
import com.app.wms.engine.db.dto.CanvassingPk;
import com.app.wms.engine.db.exceptions.CanvassingDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface CanvassingDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return CanvassingPk
	 */
	public CanvassingPk insert(Canvassing dto);

	/** 
	 * Updates a single row in the canvassing table.
	 */
	public void update(CanvassingPk pk, Canvassing dto) throws CanvassingDaoException;

	/** 
	 * Deletes a single row in the canvassing table.
	 */
	public void delete(CanvassingPk pk) throws CanvassingDaoException;

	/** 
	 * Returns all rows from the canvassing table that match the criteria 'id = :id'.
	 */
	public Canvassing findByPrimaryKey(int id) throws CanvassingDaoException;

	/** 
	 * Returns all rows from the canvassing table that match the criteria ''.
	 */
	public List<Canvassing> findAll() throws CanvassingDaoException;

	/** 
	 * Returns all rows from the canvassing table that match the criteria 'id = :id'.
	 */
	public List<Canvassing> findWhereIdEquals(int id) throws CanvassingDaoException;

	/** 
	 * Returns all rows from the canvassing table that match the criteria 'supplier_code = :supplierCode'.
	 */
//	public List<Canvassing> findWhereSupplierCodeEquals(String supplierCode) throws CanvassingDaoException;

	/** 
	 * Returns all rows from the canvassing table that match the criteria 'prsnumber = :prsnumber'.
	 */
	public List<Canvassing> findWherePrsnumberEquals(String prsnumber) throws CanvassingDaoException;

	/** 
	 * Returns all rows from the canvassing table that match the criteria 'canvassername = :canvassername'.
	 */
	public List<Canvassing> findWhereCanvassernameEquals(String canvassername) throws CanvassingDaoException;

	/** 
	 * Returns all rows from the canvassing table that match the criteria 'canvassingdate = :canvassingdate'.
	 */
	public List<Canvassing> findWhereCanvassingdateEquals(Date canvassingdate) throws CanvassingDaoException;

	/** 
	 * Returns all rows from the canvassing table that match the criteria 'remarks = :remarks'.
	 */
	public List<Canvassing> findWhereRemarksEquals(String remarks) throws CanvassingDaoException;

	/** 
	 * Returns all rows from the canvassing table that match the criteria 'createdby = :createdby'.
	 */
	public List<Canvassing> findWhereCreatedbyEquals(String createdby) throws CanvassingDaoException;

	/** 
	 * Returns the rows from the canvassing table that matches the specified primary-key value.
	 */
	public Canvassing findByPrimaryKey(CanvassingPk pk) throws CanvassingDaoException;
	
	public Canvassing findWherePrsnumber(String prsnumber) throws CanvassingDaoException;
	
	public void updateDto(Canvassing dto) throws CanvassingDaoException;

}

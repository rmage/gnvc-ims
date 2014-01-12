package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.UomDao;
import com.app.wms.engine.db.dto.Uom;
import com.app.wms.engine.db.dto.UomPk;
import com.app.wms.engine.db.exceptions.UomDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface UomDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return UomPk
	 */
	public UomPk insert(Uom dto);

	/** 
	 * Updates a single row in the uom table.
	 */
	public void update(UomPk pk, Uom dto) throws UomDaoException;

	/** 
	 * Deletes a single row in the uom table.
	 */
	public void delete(UomPk pk) throws UomDaoException;

	/** 
	 * Returns all rows from the uom table that match the criteria 'id = :id'.
	 */
	public Uom findByPrimaryKey(int id) throws UomDaoException;

	/** 
	 * Returns all rows from the uom table that match the criteria ''.
	 */
	public List<Uom> findAll() throws UomDaoException;

	/** 
	 * Returns all rows from the uom table that match the criteria 'id = :id'.
	 */
	public List<Uom> findWhereIdEquals(int id) throws UomDaoException;

	/** 
	 * Returns all rows from the uom table that match the criteria 'uom_code = :uomCode'.
	 */
	public List<Uom> findWhereUomCodeEquals(String uomCode) throws UomDaoException;

	/** 
	 * Returns all rows from the uom table that match the criteria 'uom_name = :uomName'.
	 */
	public List<Uom> findWhereUomNameEquals(String uomName) throws UomDaoException;

	/** 
	 * Returns all rows from the uom table that match the criteria 'remarks = :remarks'.
	 */
	public List<Uom> findWhereRemarksEquals(String remarks) throws UomDaoException;

	/** 
	 * Returns all rows from the uom table that match the criteria 'is_active = :isActive'.
	 */
	public List<Uom> findWhereIsActiveEquals(String isActive) throws UomDaoException;

	/** 
	 * Returns all rows from the uom table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<Uom> findWhereIsDeleteEquals(String isDelete) throws UomDaoException;

	/** 
	 * Returns all rows from the uom table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Uom> findWhereCreatedByEquals(String createdBy) throws UomDaoException;

	/** 
	 * Returns all rows from the uom table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Uom> findWhereCreatedDateEquals(Date createdDate) throws UomDaoException;

	/** 
	 * Returns all rows from the uom table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Uom> findWhereUpdatedByEquals(String updatedBy) throws UomDaoException;

	/** 
	 * Returns all rows from the uom table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Uom> findWhereUpdatedDateEquals(Date updatedDate) throws UomDaoException;

	/** 
	 * Returns the rows from the uom table that matches the specified primary-key value.
	 */
	public Uom findByPrimaryKey(UomPk pk) throws UomDaoException;

}

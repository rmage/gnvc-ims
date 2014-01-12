package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.SwsDao;
import com.app.wms.engine.db.dto.Sws;
import com.app.wms.engine.db.dto.SwsPk;
import com.app.wms.engine.db.exceptions.SwsDaoException;
import java.util.Date;
import java.util.List;

public interface SwsDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return SwsPk
	 */
	public SwsPk insert(Sws dto);

	/** 
	 * Updates a single row in the sws table.
	 */
	public void update(SwsPk pk, Sws dto) throws SwsDaoException;

	/** 
	 * Deletes a single row in the sws table.
	 */
	public void delete(SwsPk pk) throws SwsDaoException;

	/** 
	 * Returns all rows from the sws table that match the criteria 'id = :id'.
	 */
	public Sws findByPrimaryKey(int id) throws SwsDaoException;

	/** 
	 * Returns all rows from the sws table that match the criteria ''.
	 */
	public List<Sws> findAll() throws SwsDaoException;

	/** 
	 * Returns all rows from the sws table that match the criteria 'id = :id'.
	 */
	public List<Sws> findWhereIdEquals(int id) throws SwsDaoException;

	/** 
	 * Returns all rows from the sws table that match the criteria 'swsnumber = :swsnumber'.
	 */
	public List<Sws> findWhereSwsnumberEquals(String swsnumber) throws SwsDaoException;

	/** 
	 * Returns all rows from the sws table that match the criteria 'swsdate = :swsdate'.
	 */
	public List<Sws> findWhereSwsdateEquals(Date swsdate) throws SwsDaoException;

	/** 
	 * Returns all rows from the sws table that match the criteria 'swsreferensi = :swsreferensi'.
	 */
	public List<Sws> findWhereSwsreferensiEquals(String swsreferensi) throws SwsDaoException;

	/** 
	 * Returns all rows from the sws table that match the criteria 'remarks = :remarks'.
	 */
	public List<Sws> findWhereRemarksEquals(String remarks) throws SwsDaoException;

	/** 
	 * Returns all rows from the sws table that match the criteria 'createdby = :createdby'.
	 */
	public List<Sws> findWhereCreatedbyEquals(String createdby) throws SwsDaoException;

	/** 
	 * Returns all rows from the sws table that match the criteria 'requestedBy = :requestedBy'.
	 */
	public List<Sws> findWhereRequestedByEquals(String requestedBy) throws SwsDaoException;

	/** 
	 * Returns all rows from the sws table that match the criteria 'approvedBy = :approvedBy'.
	 */
	public List<Sws> findWhereApprovedByEquals(String approvedBy) throws SwsDaoException;

	/** 
	 * Returns all rows from the sws table that match the criteria 'wh_code = :whCode'.
	 */
	public List<Sws> findWhereWhCodeEquals(String whCode) throws SwsDaoException;

	/** 
	 * Returns all rows from the sws table that match the criteria 'department_name = :departmentName'.
	 */
	public List<Sws> findWhereDepartmentNameEquals(String departmentName) throws SwsDaoException;

	/** 
	 * Returns the rows from the sws table that matches the specified primary-key value.
	 */
	public Sws findByPrimaryKey(SwsPk pk) throws SwsDaoException;
	
	public List<Sws> findSwsPaging(Sws s, int page) throws SwsDaoException;
	
	public List<Sws> findWhereSwsNumberNotInTS() throws SwsDaoException;
	
	public void update(Sws dto) throws SwsDaoException;

}

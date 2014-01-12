package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.TsDao;
import com.app.wms.engine.db.dto.Ts;
import com.app.wms.engine.db.dto.TsPk;
import com.app.wms.engine.db.exceptions.TsDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface TsDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return TsPk
	 */
	public TsPk insert(Ts dto);

	/** 
	 * Updates a single row in the ts table.
	 */
	public void update(TsPk pk, Ts dto) throws TsDaoException;

	/** 
	 * Deletes a single row in the ts table.
	 */
	public void delete(TsPk pk) throws TsDaoException;

	/** 
	 * Returns all rows from the ts table that match the criteria 'id = :id'.
	 */
	public Ts findByPrimaryKey(int id) throws TsDaoException;

	/** 
	 * Returns all rows from the ts table that match the criteria ''.
	 */
	public List<Ts> findAll() throws TsDaoException;

	/** 
	 * Returns all rows from the ts table that match the criteria 'id = :id'.
	 */
	public List<Ts> findWhereIdEquals(int id) throws TsDaoException;

	/** 
	 * Returns all rows from the ts table that match the criteria 'tsnumber = :tsnumber'.
	 */
	public List<Ts> findWhereTsnumberEquals(String tsnumber) throws TsDaoException;

	/** 
	 * Returns all rows from the ts table that match the criteria 'tsdate = :tsdate'.
	 */
	public List<Ts> findWhereTsdateEquals(Date tsdate) throws TsDaoException;

	/** 
	 * Returns all rows from the ts table that match the criteria 'swsnumber = :swsnumber'.
	 */
	public List<Ts> findWhereSwsnumberEquals(String swsnumber) throws TsDaoException;

	/** 
	 * Returns all rows from the ts table that match the criteria 'department_code = :departmentCode'.
	 */
	public List<Ts> findWhereDepartmentCodeEquals(String departmentCode) throws TsDaoException;

	/** 
	 * Returns all rows from the ts table that match the criteria 'createdby = :createdby'.
	 */
	public List<Ts> findWhereCreatedbyEquals(String createdby) throws TsDaoException;

	/** 
	 * Returns all rows from the ts table that match the criteria 'issuedby = :issuedby'.
	 */
	public List<Ts> findWhereIssuedbyEquals(String issuedby) throws TsDaoException;

	/** 
	 * Returns all rows from the ts table that match the criteria 'notedby = :notedby'.
	 */
	public List<Ts> findWhereNotedbyEquals(String notedby) throws TsDaoException;

	/** 
	 * Returns all rows from the ts table that match the criteria 'receivedby = :receivedby'.
	 */
	public List<Ts> findWhereReceivedbyEquals(String receivedby) throws TsDaoException;

	/** 
	 * Returns all rows from the ts table that match the criteria 'approvedby = :approvedby'.
	 */
	public List<Ts> findWhereApprovedbyEquals(String approvedby) throws TsDaoException;

	/** 
	 * Returns all rows from the ts table that match the criteria 'remarks = :remarks'.
	 */
	public List<Ts> findWhereRemarksEquals(String remarks) throws TsDaoException;

	/** 
	 * Returns the rows from the ts table that matches the specified primary-key value.
	 */
	public Ts findByPrimaryKey(TsPk pk) throws TsDaoException;
	
	public List<Ts> findTsPaging(Ts t, Integer page) throws TsDaoException;

}

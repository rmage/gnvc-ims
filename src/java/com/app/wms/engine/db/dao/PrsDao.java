package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.PrsDao;
import com.app.wms.engine.db.dto.Prs;
import com.app.wms.engine.db.dto.PrsPk;
import com.app.wms.engine.db.exceptions.PrsDaoException;
import java.util.Date;
import java.util.List;

public interface PrsDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return PrsPk
	 */
	public PrsPk insert(Prs dto);

	/** 
	 * Updates a single row in the prs table.
	 */
	public void update(PrsPk pk, Prs dto) throws PrsDaoException;

	/** 
	 * Deletes a single row in the prs table.
	 */
	public void delete(PrsPk pk) throws PrsDaoException;

	/** 
	 * Returns all rows from the prs table that match the criteria 'id = :id'.
	 */
	public Prs findByPrimaryKey(int id) throws PrsDaoException;

	/** 
	 * Returns all rows from the prs table that match the criteria ''.
	 */
	public List<Prs> findAll() throws PrsDaoException;

	/** 
	 * Returns all rows from the prs table that match the criteria 'id = :id'.
	 */
	public List<Prs> findWhereIdEquals(int id) throws PrsDaoException;

	/** 
	 * Returns all rows from the prs table that match the criteria 'prsnumber = :prsnumber'.
	 */
	public List<Prs> findWherePrsnumberEquals(String prsnumber) throws PrsDaoException;

	/** 
	 * Returns all rows from the prs table that match the criteria 'prsdate = :prsdate'.
	 */
	public List<Prs> findWherePrsdateEquals(Date prsdate) throws PrsDaoException;

	/** 
	 * Returns all rows from the prs table that match the criteria 'requestdate = :requestdate'.
	 */
	public List<Prs> findWhereRequestdateEquals(Date requestdate) throws PrsDaoException;

	/** 
	 * Returns all rows from the prs table that match the criteria 'deliverydate = :deliverydate'.
	 */
	public List<Prs> findWhereDeliverydateEquals(Date deliverydate) throws PrsDaoException;

	/** 
	 * Returns all rows from the prs table that match the criteria 'poreferensi = :poreferensi'.
	 */
	public List<Prs> findWherePoreferensiEquals(String poreferensi) throws PrsDaoException;

	/** 
	 * Returns all rows from the prs table that match the criteria 'remarks = :remarks'.
	 */
	public List<Prs> findWhereRemarksEquals(String remarks) throws PrsDaoException;

	/** 
	 * Returns all rows from the prs table that match the criteria 'createdby = :createdby'.
	 */
	public List<Prs> findWhereCreatedbyEquals(String createdby) throws PrsDaoException;

	/** 
	 * Returns all rows from the prs table that match the criteria 'department_name = :departmentName'.
	 */
	public List<Prs> findWhereDepartmentNameEquals(String departmentName) throws PrsDaoException;

	/** 
	 * Returns the rows from the prs table that matches the specified primary-key value.
	 */
	public Prs findByPrimaryKey(PrsPk pk) throws PrsDaoException;
	
	public List<Prs> findPrsPaging(Prs p, int page) throws PrsDaoException;
	
	public List<Prs> findWherePrsNumberCanvasserNotInPO () throws PrsDaoException;

	public List<Prs> findWherePrsNumberNotInPO() throws PrsDaoException;
	
	public void update(Prs dto) throws PrsDaoException;
}

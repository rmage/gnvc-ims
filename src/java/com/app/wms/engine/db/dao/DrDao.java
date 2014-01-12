package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.DrDao;
import com.app.wms.engine.db.dto.Delivery;
import com.app.wms.engine.db.dto.Dr;
import com.app.wms.engine.db.dto.DrPk;
import com.app.wms.engine.db.exceptions.DrDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface DrDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return DrPk
	 */
	public DrPk insert(Dr dto);

	/** 
	 * Updates a single row in the dr table.
	 */
	public void update(DrPk pk, Dr dto) throws DrDaoException;

	/** 
	 * Deletes a single row in the dr table.
	 */
	public void delete(DrPk pk) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'id = :id'.
	 */
	public Dr findByPrimaryKey(int id) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria ''.
	 */
	public List<Dr> findAll() throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'id = :id'.
	 */
	public List<Dr> findWhereIdEquals(int id) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'drnumber = :drnumber'.
	 */
	public List<Dr> findWhereDrnumberEquals(String drnumber) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'ornumber = :ornumber'.
	 */
	public List<Dr> findWhereOrnumberEquals(String ornumber) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'dmnumber = :dmnumber'.
	 */
	public List<Dr> findWhereDmnumberEquals(String dmnumber) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'drdate = :drdate'.
	 */
	public List<Dr> findWhereDrdateEquals(Date drdate) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'createdby = :createdby'.
	 */
	public List<Dr> findWhereCreatedbyEquals(String createdby) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'lotid = :lotid'.
	 */
	public List<Dr> findWhereLotidEquals(String lotid) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'wh_code = :whCode'.
	 */
	public List<Dr> findWhereWhCodeEquals(String whCode) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'wsNo = :wsNo'.
	 */
	public List<Dr> findWhereWsNoEquals(String wsNo) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'unitCost = :unitCost'.
	 */
	public List<Dr> findWhereUnitCostEquals(int unitCost) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'amount = :amount'.
	 */
	public List<Dr> findWhereAmountEquals(int amount) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'from = :from'.
	 */
	public List<Dr> findWhereFromEquals(String from) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'issuedBy = :issuedBy'.
	 */
	public List<Dr> findWhereIssuedByEquals(String issuedBy) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'deliveredBy = :deliveredBy'.
	 */
	public List<Dr> findWhereDeliveredByEquals(String deliveredBy) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'accEntry = :accEntry'.
	 */
	public List<Dr> findWhereAccEntryEquals(String accEntry) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'approvedBy = :approvedBy'.
	 */
	public List<Dr> findWhereApprovedByEquals(String approvedBy) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'remarks = :remarks'.
	 */
	public List<Dr> findWhereRemarksEquals(String remarks) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'receivedBy = :receivedBy'.
	 */
	public List<Dr> findWhereReceivedByEquals(String receivedBy) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'department_name = :departmentName'.
	 */
	public List<Dr> findWhereDepartmentNameEquals(String departmentName) throws DrDaoException;

	/** 
	 * Returns all rows from the dr table that match the criteria 'supplierName = :supplierName'.
	 */
	public List<Dr> findWhereSupplierNameEquals(String supplierName) throws DrDaoException;

	/** 
	 * Returns the rows from the dr table that matches the specified primary-key value.
	 */
	public Dr findByPrimaryKey(DrPk pk) throws DrDaoException;
	
	public List<Dr> findDeliveryPaging(Dr d, int page) throws DrDaoException;

}

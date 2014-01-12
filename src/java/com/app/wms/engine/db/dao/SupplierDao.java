package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.SupplierPk;
import com.app.wms.engine.db.exceptions.SupplierDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface SupplierDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return SupplierPk
	 */
	public SupplierPk insert(Supplier dto);

	/** 
	 * Updates a single row in the supplier table.
	 */
	public void update(SupplierPk pk, Supplier dto) throws SupplierDaoException;

	/** 
	 * Deletes a single row in the supplier table.
	 */
	public void delete(SupplierPk pk) throws SupplierDaoException;

	/** 
	 * Returns all rows from the supplier table that match the criteria 'id = :id'.
	 */
	public Supplier findByPrimaryKey(int id) throws SupplierDaoException;

	/** 
	 * Returns all rows from the supplier table that match the criteria ''.
	 */
	public List<Supplier> findAll() throws SupplierDaoException;

	/** 
	 * Returns all rows from the supplier table that match the criteria 'id = :id'.
	 */
	public List<Supplier> findWhereIdEquals(int id) throws SupplierDaoException;

	/** 
	 * Returns all rows from the supplier table that match the criteria 'supplier_code = :supplierCode'.
	 */
	public List<Supplier> findWhereSupplierCodeEquals(String supplierCode) throws SupplierDaoException;

	/** 
	 * Returns all rows from the supplier table that match the criteria 'supplier_name = :supplierName'.
	 */
	public List<Supplier> findWhereSupplierNameEquals(String supplierName) throws SupplierDaoException;

	/** 
	 * Returns all rows from the supplier table that match the criteria 'supplier_address = :supplierAddress'.
	 */
	public List<Supplier> findWhereSupplierAddressEquals(String supplierAddress) throws SupplierDaoException;

	/** 
	 * Returns all rows from the supplier table that match the criteria 'telephone = :telephone'.
	 */
	public List<Supplier> findWhereTelephoneEquals(String telephone) throws SupplierDaoException;

	/** 
	 * Returns all rows from the supplier table that match the criteria 'fax = :fax'.
	 */
	public List<Supplier> findWhereFaxEquals(String fax) throws SupplierDaoException;

	/** 
	 * Returns all rows from the supplier table that match the criteria 'email = :email'.
	 */
	public List<Supplier> findWhereEmailEquals(String email) throws SupplierDaoException;

	/** 
	 * Returns all rows from the supplier table that match the criteria 'contact_person = :contactPerson'.
	 */
	public List<Supplier> findWhereContactPersonEquals(String contactPerson) throws SupplierDaoException;

	/** 
	 * Returns all rows from the supplier table that match the criteria 'is_active = :isActive'.
	 */
	public List<Supplier> findWhereIsActiveEquals(String isActive) throws SupplierDaoException;

	/** 
	 * Returns all rows from the supplier table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<Supplier> findWhereIsDeleteEquals(String isDelete) throws SupplierDaoException;

	/** 
	 * Returns all rows from the supplier table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Supplier> findWhereCreatedByEquals(String createdBy) throws SupplierDaoException;

	/** 
	 * Returns all rows from the supplier table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Supplier> findWhereCreatedDateEquals(Date createdDate) throws SupplierDaoException;

	/** 
	 * Returns all rows from the supplier table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Supplier> findWhereUpdatedByEquals(String updatedBy) throws SupplierDaoException;

	/** 
	 * Returns all rows from the supplier table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Supplier> findWhereUpdatedDateEquals(Date updatedDate) throws SupplierDaoException;

	/** 
	 * Returns the rows from the supplier table that matches the specified primary-key value.
	 */
	public Supplier findByPrimaryKey(SupplierPk pk) throws SupplierDaoException;
	
	public List<Supplier> findSupplierPaging(Supplier s,int page) throws SupplierDaoException;

}

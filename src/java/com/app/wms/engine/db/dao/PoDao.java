package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.PoDao;
import com.app.wms.engine.db.dto.Po;
import com.app.wms.engine.db.dto.PoPk;
import com.app.wms.engine.db.exceptions.PoDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface PoDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return PoPk
	 */
	public PoPk insert(Po dto);

	/** 
	 * Updates a single row in the po table.
	 */
	public void update(PoPk pk, Po dto) throws PoDaoException;

	/** 
	 * Deletes a single row in the po table.
	 */
	public void delete(PoPk pk) throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria 'id = :id'.
	 */
	public Po findByPrimaryKey(long id) throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria ''.
	 */
	public List<Po> findAll() throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria 'id = :id'.
	 */
	public List<Po> findWhereIdEquals(long id) throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria 'ponumber = :ponumber'.
	 */
	public List<Po> findWherePonumberEquals(String ponumber) throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria 'podate = :podate'.
	 */
	public List<Po> findWherePodateEquals(Date podate) throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria 'prsnumber = :prsnumber'.
	 */
	public List<Po> findWherePrsnumberEquals(String prsnumber) throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria 'prsdate = :prsdate'.
	 */
	public List<Po> findWherePrsdateEquals(Date prsdate) throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria 'deliverydate = :deliverydate'.
	 */
	public List<Po> findWhereDeliverydateEquals(Date deliverydate) throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria 'poreferensi = :poreferensi'.
	 */
	public List<Po> findWherePoreferensiEquals(String poreferensi) throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria 'createdby = :createdby'.
	 */
	public List<Po> findWhereCreatedbyEquals(String createdby) throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria 'corpid = :corpid'.
	 */
	public List<Po> findWhereCorpidEquals(String corpid) throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria 'wh_code = :whCode'.
	 */
	public List<Po> findWhereWhCodeEquals(String whCode) throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria 'department_name = :departmentName'.
	 */
	public List<Po> findWhereDepartmentNameEquals(String departmentName) throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria 'supplier_name = :supplierName'.
	 */
	public List<Po> findWhereSupplierNameEquals(String supplierName) throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria 'currency = :currency'.
	 */
	public List<Po> findWhereCurrencyEquals(String currency) throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria 'prsremarks = :prsremarks'.
	 */
	public List<Po> findWherePrsremarksEquals(String prsremarks) throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria 'role_code = :roleCode'.
	 */
	public List<Po> findWhereRoleCodeEquals(String roleCode) throws PoDaoException;

	/** 
	 * Returns all rows from the po table that match the criteria 'status = :status'.
	 */
	public List<Po> findWhereStatusEquals(String status) throws PoDaoException;

	/** 
	 * Returns the rows from the po table that matches the specified primary-key value.
	 */
	public Po findByPrimaryKey(PoPk pk) throws PoDaoException;
	
	public List<Po> findWherePoNumberNotInGR() throws PoDaoException;

}

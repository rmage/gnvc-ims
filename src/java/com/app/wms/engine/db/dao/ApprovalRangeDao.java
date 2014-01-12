package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.ApprovalRangeDao;
import com.app.wms.engine.db.dto.ApprovalRange;
import com.app.wms.engine.db.dto.ApprovalRangePk;
import com.app.wms.engine.db.exceptions.ApprovalRangeDaoException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface ApprovalRangeDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return ApprovalRangePk
	 */
	public ApprovalRangePk insert(ApprovalRange dto);

	/** 
	 * Updates a single row in the approval_range table.
	 */
	public void update(ApprovalRangePk pk, ApprovalRange dto) throws ApprovalRangeDaoException;

	/** 
	 * Deletes a single row in the approval_range table.
	 */
	public void delete(ApprovalRangePk pk) throws ApprovalRangeDaoException;

	/** 
	 * Returns all rows from the approval_range table that match the criteria 'id = :id'.
	 */
	public ApprovalRange findByPrimaryKey(int id) throws ApprovalRangeDaoException;

	/** 
	 * Returns all rows from the approval_range table that match the criteria ''.
	 */
	public List<ApprovalRange> findAll() throws ApprovalRangeDaoException;

	/** 
	 * Returns all rows from the approval_range table that match the criteria 'id = :id'.
	 */
	public List<ApprovalRange> findWhereIdEquals(int id) throws ApprovalRangeDaoException;

	/** 
	 * Returns all rows from the approval_range table that match the criteria 'username = :username'.
	 */
	public List<ApprovalRange> findWhereUsernameEquals(String username) throws ApprovalRangeDaoException;

	/** 
	 * Returns all rows from the approval_range table that match the criteria 'role_code = :roleCode'.
	 */
	public List<ApprovalRange> findWhereRoleCodeEquals(String roleCode) throws ApprovalRangeDaoException;

	/** 
	 * Returns all rows from the approval_range table that match the criteria 'val_amount = :valAmount'.
	 */
	public List<ApprovalRange> findWhereValAmountEquals(float valAmount) throws ApprovalRangeDaoException;

	/** 
	 * Returns all rows from the approval_range table that match the criteria 'is_active = :isActive'.
	 */
	public List<ApprovalRange> findWhereIsActiveEquals(String isActive) throws ApprovalRangeDaoException;

	/** 
	 * Returns all rows from the approval_range table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<ApprovalRange> findWhereIsDeleteEquals(String isDelete) throws ApprovalRangeDaoException;

	/** 
	 * Returns all rows from the approval_range table that match the criteria 'created_by = :createdBy'.
	 */
	public List<ApprovalRange> findWhereCreatedByEquals(String createdBy) throws ApprovalRangeDaoException;

	/** 
	 * Returns all rows from the approval_range table that match the criteria 'created_date = :createdDate'.
	 */
	public List<ApprovalRange> findWhereCreatedDateEquals(Date createdDate) throws ApprovalRangeDaoException;

	/** 
	 * Returns all rows from the approval_range table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<ApprovalRange> findWhereUpdatedByEquals(String updatedBy) throws ApprovalRangeDaoException;

	/** 
	 * Returns all rows from the approval_range table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<ApprovalRange> findWhereUpdatedDateEquals(Date updatedDate) throws ApprovalRangeDaoException;

	/** 
	 * Returns the rows from the approval_range table that matches the specified primary-key value.
	 */
	public ApprovalRange findByPrimaryKey(ApprovalRangePk pk) throws ApprovalRangeDaoException;
	
	public List<ApprovalRange> findApprovalRangePaging(ApprovalRange a, int page) throws ApprovalRangeDaoException;
	
	public List<ApprovalRange> findApprovalRangeTotal(BigDecimal total) throws ApprovalRangeDaoException;
	
	public List<ApprovalRange> findApprovalRangeRoleCode(String departmentCode, BigDecimal total, BigDecimal from, BigDecimal to) throws ApprovalRangeDaoException;

}

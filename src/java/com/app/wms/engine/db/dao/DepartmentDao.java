package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.DepartmentDao;
import com.app.wms.engine.db.dto.Department;
import com.app.wms.engine.db.dto.DepartmentPk;
import com.app.wms.engine.db.exceptions.DepartmentDaoException;

import java.util.Date;
import java.util.List;

public interface DepartmentDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return DepartmentPk
	 */
	public DepartmentPk insert(Department dto);

	/** 
	 * Updates a single row in the department table.
	 */
	public void update(DepartmentPk pk, Department dto) throws DepartmentDaoException;

	/** 
	 * Deletes a single row in the department table.
	 */
	public void delete(DepartmentPk pk) throws DepartmentDaoException;

	/** 
	 * Returns all rows from the department table that match the criteria 'id = :id'.
	 */
	public Department findByPrimaryKey(int id) throws DepartmentDaoException;

	/** 
	 * Returns all rows from the department table that match the criteria ''.
	 */
	public List<Department> findAll() throws DepartmentDaoException;

	/** 
	 * Returns all rows from the department table that match the criteria 'id = :id'.
	 */
	public List<Department> findWhereIdEquals(int id) throws DepartmentDaoException;

	/** 
	 * Returns all rows from the department table that match the criteria 'department_code = :departmentCode'.
	 */
	public List<Department> findWhereDepartmentCodeEquals(String departmentCode) throws DepartmentDaoException;

	/** 
	 * Returns all rows from the department table that match the criteria 'department_name = :departmentName'.
	 */
	public List<Department> findWhereDepartmentNameEquals(String departmentName) throws DepartmentDaoException;

	/** 
	 * Returns all rows from the department table that match the criteria 'is_active = :isActive'.
	 */
	public List<Department> findWhereIsActiveEquals(String isActive) throws DepartmentDaoException;

	/** 
	 * Returns all rows from the department table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<Department> findWhereIsDeleteEquals(String isDelete) throws DepartmentDaoException;

	/** 
	 * Returns all rows from the department table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Department> findWhereCreatedByEquals(String createdBy) throws DepartmentDaoException;

	/** 
	 * Returns all rows from the department table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Department> findWhereCreatedDateEquals(Date createdDate) throws DepartmentDaoException;

	/** 
	 * Returns all rows from the department table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Department> findWhereUpdatedByEquals(String updatedBy) throws DepartmentDaoException;

	/** 
	 * Returns all rows from the department table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Department> findWhereUpdatedDateEquals(Date updatedDate) throws DepartmentDaoException;

	/** 
	 * Returns the rows from the department table that matches the specified primary-key value.
	 */
	public Department findByPrimaryKey(DepartmentPk pk) throws DepartmentDaoException;
	
	public List<Department> findDepartmentPaging(Department d,int page) throws DepartmentDaoException;

}

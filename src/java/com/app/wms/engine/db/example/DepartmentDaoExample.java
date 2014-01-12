package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.DepartmentDao;
import com.app.wms.engine.db.dto.Department;
import com.app.wms.engine.db.exceptions.DepartmentDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class DepartmentDaoExample
{
	/**
	 * Method 'main'
	 * 
	 * @param arg
	 * @throws Exception
	 */
	public static void main(String[] arg) throws Exception
	{
		// Uncomment one of the lines below to test the generated code
		
		// findAll();
		// findWhereIdEquals(0);
		// findWhereDepartmentCodeEquals("");
		// findWhereDepartmentNameEquals("");
		// findWhereIsActiveEquals("");
		// findWhereIsDeleteEquals("");
		// findWhereCreatedByEquals("");
		// findWhereCreatedDateEquals(null);
		// findWhereUpdatedByEquals("");
		// findWhereUpdatedDateEquals(null);
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		DepartmentDao dao = DaoFactory.createDepartmentDao();
		List<Department> _result = dao.findAll();
		for (Department dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIdEquals'
	 * 
	 * @param id
	 * @throws Exception
	 */
	public static void findWhereIdEquals(int id) throws Exception
	{
		DepartmentDao dao = DaoFactory.createDepartmentDao();
		List<Department> _result = dao.findWhereIdEquals(id);
		for (Department dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDepartmentCodeEquals'
	 * 
	 * @param departmentCode
	 * @throws Exception
	 */
	public static void findWhereDepartmentCodeEquals(String departmentCode) throws Exception
	{
		DepartmentDao dao = DaoFactory.createDepartmentDao();
		List<Department> _result = dao.findWhereDepartmentCodeEquals(departmentCode);
		for (Department dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDepartmentNameEquals'
	 * 
	 * @param departmentName
	 * @throws Exception
	 */
	public static void findWhereDepartmentNameEquals(String departmentName) throws Exception
	{
		DepartmentDao dao = DaoFactory.createDepartmentDao();
		List<Department> _result = dao.findWhereDepartmentNameEquals(departmentName);
		for (Department dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIsActiveEquals'
	 * 
	 * @param isActive
	 * @throws Exception
	 */
	public static void findWhereIsActiveEquals(String isActive) throws Exception
	{
		DepartmentDao dao = DaoFactory.createDepartmentDao();
		List<Department> _result = dao.findWhereIsActiveEquals(isActive);
		for (Department dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIsDeleteEquals'
	 * 
	 * @param isDelete
	 * @throws Exception
	 */
	public static void findWhereIsDeleteEquals(String isDelete) throws Exception
	{
		DepartmentDao dao = DaoFactory.createDepartmentDao();
		List<Department> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (Department dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCreatedByEquals'
	 * 
	 * @param createdBy
	 * @throws Exception
	 */
	public static void findWhereCreatedByEquals(String createdBy) throws Exception
	{
		DepartmentDao dao = DaoFactory.createDepartmentDao();
		List<Department> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Department dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCreatedDateEquals'
	 * 
	 * @param createdDate
	 * @throws Exception
	 */
	public static void findWhereCreatedDateEquals(Date createdDate) throws Exception
	{
		DepartmentDao dao = DaoFactory.createDepartmentDao();
		List<Department> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Department dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUpdatedByEquals'
	 * 
	 * @param updatedBy
	 * @throws Exception
	 */
	public static void findWhereUpdatedByEquals(String updatedBy) throws Exception
	{
		DepartmentDao dao = DaoFactory.createDepartmentDao();
		List<Department> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Department dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUpdatedDateEquals'
	 * 
	 * @param updatedDate
	 * @throws Exception
	 */
	public static void findWhereUpdatedDateEquals(Date updatedDate) throws Exception
	{
		DepartmentDao dao = DaoFactory.createDepartmentDao();
		List<Department> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Department dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Department dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getDepartmentCode() );
		buf.append( ", " );
		buf.append( dto.getDepartmentName() );
		buf.append( ", " );
		buf.append( dto.getIsActive() );
		buf.append( ", " );
		buf.append( dto.getIsDelete() );
		buf.append( ", " );
		buf.append( dto.getCreatedBy() );
		buf.append( ", " );
		buf.append( dto.getCreatedDate() );
		buf.append( ", " );
		buf.append( dto.getUpdatedBy() );
		buf.append( ", " );
		buf.append( dto.getUpdatedDate() );
		System.out.println( buf.toString() );
	}

}

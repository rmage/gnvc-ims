package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;

import com.app.wms.engine.db.dao.AppMenuRoleDao;
import com.app.wms.engine.db.dto.AppMenuRole;
import com.app.wms.engine.db.exceptions.AppMenuRoleDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class AppMenuRoleDaoExample
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
		// findByAppMenu("");
		// findByUserRole("");
		// findWhereRoleCodeEquals("");
		// findWhereMenuCodeEquals("");
		// findWhereIsViewEquals("");
		// findWhereIsCreateEquals("");
		// findWhereIsEditEquals("");
		// findWhereIsDeleteEquals("");
		// findWhereCreatedByEquals(null);
		// findWhereCreatedDateEquals(null);
		// findWhereUpdatedByEquals(null);
		// findWhereUpdatedDateEquals(null);
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		AppMenuRoleDao dao = DaoFactory.createAppMenuRoleDao();
		List<AppMenuRole> _result = dao.findAll();
		for (AppMenuRole dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findByAppMenu'
	 * 
	 * @param menuCode
	 * @throws Exception
	 */
	public static void findByAppMenu(String menuCode) throws Exception
	{
		AppMenuRoleDao dao = DaoFactory.createAppMenuRoleDao();
		List<AppMenuRole> _result = dao.findByAppMenu(menuCode);
		for (AppMenuRole dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findByUserRole'
	 * 
	 * @param roleCode
	 * @throws Exception
	 */
	public static void findByUserRole(String roleCode) throws Exception
	{
		AppMenuRoleDao dao = DaoFactory.createAppMenuRoleDao();
		List<AppMenuRole> _result = dao.findByUserRole(roleCode);
		for (AppMenuRole dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereRoleCodeEquals'
	 * 
	 * @param roleCode
	 * @throws Exception
	 */
	public static void findWhereRoleCodeEquals(String roleCode) throws Exception
	{
		AppMenuRoleDao dao = DaoFactory.createAppMenuRoleDao();
		List<AppMenuRole> _result = dao.findWhereRoleCodeEquals(roleCode);
		for (AppMenuRole dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereMenuCodeEquals'
	 * 
	 * @param menuCode
	 * @throws Exception
	 */
	public static void findWhereMenuCodeEquals(String menuCode) throws Exception
	{
		AppMenuRoleDao dao = DaoFactory.createAppMenuRoleDao();
		List<AppMenuRole> _result = dao.findWhereMenuCodeEquals(menuCode);
		for (AppMenuRole dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIsViewEquals'
	 * 
	 * @param isView
	 * @throws Exception
	 */
	public static void findWhereIsViewEquals(String isView) throws Exception
	{
		AppMenuRoleDao dao = DaoFactory.createAppMenuRoleDao();
		List<AppMenuRole> _result = dao.findWhereIsViewEquals(isView);
		for (AppMenuRole dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIsCreateEquals'
	 * 
	 * @param isCreate
	 * @throws Exception
	 */
	public static void findWhereIsCreateEquals(String isCreate) throws Exception
	{
		AppMenuRoleDao dao = DaoFactory.createAppMenuRoleDao();
		List<AppMenuRole> _result = dao.findWhereIsCreateEquals(isCreate);
		for (AppMenuRole dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIsEditEquals'
	 * 
	 * @param isEdit
	 * @throws Exception
	 */
	public static void findWhereIsEditEquals(String isEdit) throws Exception
	{
		AppMenuRoleDao dao = DaoFactory.createAppMenuRoleDao();
		List<AppMenuRole> _result = dao.findWhereIsEditEquals(isEdit);
		for (AppMenuRole dto : _result) {
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
		AppMenuRoleDao dao = DaoFactory.createAppMenuRoleDao();
		List<AppMenuRole> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (AppMenuRole dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCreatedByEquals'
	 * 
	 * @param createdBy
	 * @throws Exception
	 */
	public static void findWhereCreatedByEquals(BigDecimal createdBy) throws Exception
	{
		AppMenuRoleDao dao = DaoFactory.createAppMenuRoleDao();
		List<AppMenuRole> _result = dao.findWhereCreatedByEquals(createdBy);
		for (AppMenuRole dto : _result) {
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
		AppMenuRoleDao dao = DaoFactory.createAppMenuRoleDao();
		List<AppMenuRole> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (AppMenuRole dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUpdatedByEquals'
	 * 
	 * @param updatedBy
	 * @throws Exception
	 */
	public static void findWhereUpdatedByEquals(BigDecimal updatedBy) throws Exception
	{
		AppMenuRoleDao dao = DaoFactory.createAppMenuRoleDao();
		List<AppMenuRole> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (AppMenuRole dto : _result) {
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
		AppMenuRoleDao dao = DaoFactory.createAppMenuRoleDao();
		List<AppMenuRole> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (AppMenuRole dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(AppMenuRole dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getRoleCode() );
		buf.append( ", " );
		buf.append( dto.getMenuCode() );
		buf.append( ", " );
		buf.append( dto.getIsView() );
		buf.append( ", " );
		buf.append( dto.getIsCreate() );
		buf.append( ", " );
		buf.append( dto.getIsEdit() );
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

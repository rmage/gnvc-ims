package com.app.wms.engine.db.example;

import java.util.List;

import com.app.wms.engine.db.dao.AppMenuDao;
import com.app.wms.engine.db.dto.AppMenu;
import com.app.wms.engine.db.factory.DaoFactory;

public class AppMenuDaoExample
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
		// findWhereMenuCodeEquals("");
		// findWhereNameEquals("");
		// findWhereUrlEquals("");
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
		AppMenuDao dao = DaoFactory.createAppMenuDao();
		List<AppMenu> _result = dao.findAll();
		for (AppMenu dto : _result) {
			display(dto);
		}
		
	}

//    /**
//     * Method 'findWhereMenuCodeEquals'
//     *
//     * @param menuCode
//     * @throws Exception
//     */
//    public static void findWhereMenuCodeEquals(String menuCode) throws Exception
//    {
//            AppMenuDao dao = DaoFactory.createAppMenuDao();
//            List<AppMenu> _result = dao.findWhereMenuCodeEquals(menuCode);
//            for (AppMenu dto : _result) {
//                    display(dto);
//            }
//
//    }
//
//    /**
//     * Method 'findWhereNameEquals'
//     *
//     * @param name
//     * @throws Exception
//     */
//    public static void findWhereNameEquals(String name) throws Exception
//    {
//            AppMenuDao dao = DaoFactory.createAppMenuDao();
//            List<AppMenu> _result = dao.findWhereNameEquals(name);
//            for (AppMenu dto : _result) {
//                    display(dto);
//            }
//
//    }

	/**
//	 * Method 'findWhereUrlEquals'
//	 *
//	 * @param url
//	 * @throws Exception
//	 */
//	public static void findWhereUrlEquals(String url) throws Exception
//	{
//		AppMenuDao dao = DaoFactory.createAppMenuDao();
//		List<AppMenu> _result = dao.findWhereUrlEquals(url);
//		for (AppMenu dto : _result) {
//			display(dto);
//		}
//
//	}
//
//	/**
//	 * Method 'findWhereCreatedByEquals'
//	 *
//	 * @param createdBy
//	 * @throws Exception
//	 */
//	public static void findWhereCreatedByEquals(BigDecimal createdBy) throws Exception
//	{
//		AppMenuDao dao = DaoFactory.createAppMenuDao();
//		List<AppMenu> _result = dao.findWhereCreatedByEquals(createdBy);
//		for (AppMenu dto : _result) {
//			display(dto);
//		}
//
//	}
//
//	/**
//	 * Method 'findWhereCreatedDateEquals'
//	 *
//	 * @param createdDate
//	 * @throws Exception
//	 */
//	public static void findWhereCreatedDateEquals(Date createdDate) throws Exception
//	{
//		AppMenuDao dao = DaoFactory.createAppMenuDao();
//		List<AppMenu> _result = dao.findWhereCreatedDateEquals(createdDate);
//		for (AppMenu dto : _result) {
//			display(dto);
//		}
//
//	}
//
//	/**
//	 * Method 'findWhereUpdatedByEquals'
//	 *
//	 * @param updatedBy
//	 * @throws Exception
//	 */
//	public static void findWhereUpdatedByEquals(BigDecimal updatedBy) throws Exception
//	{
//		AppMenuDao dao = DaoFactory.createAppMenuDao();
//		List<AppMenu> _result = dao.findWhereUpdatedByEquals(updatedBy);
//		for (AppMenu dto : _result) {
//			display(dto);
//		}
//
//	}
//
//	/**
//	 * Method 'findWhereUpdatedDateEquals'
//	 *
//	 * @param updatedDate
//	 * @throws Exception
//	 */
//	public static void findWhereUpdatedDateEquals(Date updatedDate) throws Exception
//	{
//		AppMenuDao dao = DaoFactory.createAppMenuDao();
//		List<AppMenu> _result = dao.findWhereUpdatedDateEquals(updatedDate);
//		for (AppMenu dto : _result) {
//			display(dto);
//		}
//
//	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(AppMenu dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getMenuCode() );
		buf.append( ", " );
		buf.append( dto.getName() );
		buf.append( ", " );
		buf.append( dto.getUrl() );
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

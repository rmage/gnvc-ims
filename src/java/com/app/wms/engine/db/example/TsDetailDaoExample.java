package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.TsDetailDao;
import com.app.wms.engine.db.dto.TsDetail;
import com.app.wms.engine.db.exceptions.TsDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class TsDetailDaoExample
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
		// findWhereTsnumberEquals("");
		// findWhereProductcodeEquals("");
		// findWhereQtyrealEquals(0);
		// findWhereStatusEquals("");
		// findWhereExpirydateEquals(null);
		// findWhereRemarksEquals("");
		// findWhereQtygoodEquals(0);
		// findWhereQtydmgEquals(0);
		// findWhereProducttypeEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		TsDetailDao dao = DaoFactory.createTsDetailDao();
		List<TsDetail> _result = dao.findAll();
		for (TsDetail dto : _result) {
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
		TsDetailDao dao = DaoFactory.createTsDetailDao();
		List<TsDetail> _result = dao.findWhereIdEquals(id);
		for (TsDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereTsnumberEquals'
	 * 
	 * @param tsnumber
	 * @throws Exception
	 */
	public static void findWhereTsnumberEquals(String tsnumber) throws Exception
	{
		TsDetailDao dao = DaoFactory.createTsDetailDao();
		List<TsDetail> _result = dao.findWhereTsnumberEquals(tsnumber);
		for (TsDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductcodeEquals'
	 * 
	 * @param productcode
	 * @throws Exception
	 */
	public static void findWhereProductcodeEquals(String productcode) throws Exception
	{
		TsDetailDao dao = DaoFactory.createTsDetailDao();
		List<TsDetail> _result = dao.findWhereProductcodeEquals(productcode);
		for (TsDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(TsDetail dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getTsnumber() );
		buf.append( ", " );
		buf.append( dto.getProductcode() );
		buf.append( ", " );
		System.out.println( buf.toString() );
	}

}

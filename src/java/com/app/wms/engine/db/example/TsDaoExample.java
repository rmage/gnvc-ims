package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.TsDao;
import com.app.wms.engine.db.dto.Ts;
import com.app.wms.engine.db.exceptions.TsDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class TsDaoExample
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
		// findWhereWsnumberEquals("");
		// findWhereBornumberEquals("");
		// findWhereTsdateEquals(null);
		// findWhereCreatedbyEquals("");
		// findWhereLotidEquals("");
		// findWhereWhCodeEquals("");
		// findWhereWsNoEquals("");
		// findWhereUnitCostEquals(0);
		// findWhereAmountEquals(0);
		// findWhereFromEquals("");
		// findWhereIssuedByEquals("");
		// findWhereNotedByEquals("");
		// findWhereAccEntryEquals("");
		// findWhereApprovedByEquals("");
		// findWhereRemarksEquals("");
		// findWhereReceivedByEquals("");
		// findWhereDepartmentNameEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		TsDao dao = DaoFactory.createTsDao();
		List<Ts> _result = dao.findAll();
		for (Ts dto : _result) {
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
		TsDao dao = DaoFactory.createTsDao();
		List<Ts> _result = dao.findWhereIdEquals(id);
		for (Ts dto : _result) {
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
		TsDao dao = DaoFactory.createTsDao();
		List<Ts> _result = dao.findWhereTsnumberEquals(tsnumber);
		for (Ts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereWsnumberEquals'
	 * 
	 * @param wsnumber
	 * @throws Exception
	 */
	public static void findWhereWsnumberEquals(String wsnumber) throws Exception
	{
		TsDao dao = DaoFactory.createTsDao();
		List<Ts> _result = dao.findWhereSwsnumberEquals(wsnumber);
		for (Ts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereTsdateEquals'
	 * 
	 * @param tsdate
	 * @throws Exception
	 */
	public static void findWhereTsdateEquals(Date tsdate) throws Exception
	{
		TsDao dao = DaoFactory.createTsDao();
		List<Ts> _result = dao.findWhereTsdateEquals(tsdate);
		for (Ts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCreatedbyEquals'
	 * 
	 * @param createdby
	 * @throws Exception
	 */
	public static void findWhereCreatedbyEquals(String createdby) throws Exception
	{
		TsDao dao = DaoFactory.createTsDao();
		List<Ts> _result = dao.findWhereCreatedbyEquals(createdby);
		for (Ts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereRemarksEquals'
	 * 
	 * @param remarks
	 * @throws Exception
	 */
	public static void findWhereRemarksEquals(String remarks) throws Exception
	{
		TsDao dao = DaoFactory.createTsDao();
		List<Ts> _result = dao.findWhereRemarksEquals(remarks);
		for (Ts dto : _result) {
			display(dto);
		}
		
	}



	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Ts dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getTsnumber() );
		buf.append( ", " );
		buf.append( dto.getSwsnumber() );
		buf.append( ", " );
		buf.append( dto.getTsdate() );
		buf.append( ", " );
		buf.append( dto.getCreatedby() );
		buf.append( ", " );
		buf.append( dto.getRemarks() );
		buf.append( ", " );
		System.out.println( buf.toString() );
	}

}

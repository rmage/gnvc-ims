package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.UomDao;
import com.app.wms.engine.db.dto.Uom;
import com.app.wms.engine.db.exceptions.UomDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class UomDaoExample
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
		// findWhereUomCodeEquals("");
		// findWhereUomNameEquals("");
		// findWhereRemarksEquals("");
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
		UomDao dao = DaoFactory.createUomDao();
		List<Uom> _result = dao.findAll();
		for (Uom dto : _result) {
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
		UomDao dao = DaoFactory.createUomDao();
		List<Uom> _result = dao.findWhereIdEquals(id);
		for (Uom dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUomCodeEquals'
	 * 
	 * @param uomCode
	 * @throws Exception
	 */
	public static void findWhereUomCodeEquals(String uomCode) throws Exception
	{
		UomDao dao = DaoFactory.createUomDao();
		List<Uom> _result = dao.findWhereUomCodeEquals(uomCode);
		for (Uom dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUomNameEquals'
	 * 
	 * @param uomName
	 * @throws Exception
	 */
	public static void findWhereUomNameEquals(String uomName) throws Exception
	{
		UomDao dao = DaoFactory.createUomDao();
		List<Uom> _result = dao.findWhereUomNameEquals(uomName);
		for (Uom dto : _result) {
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
		UomDao dao = DaoFactory.createUomDao();
		List<Uom> _result = dao.findWhereRemarksEquals(remarks);
		for (Uom dto : _result) {
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
		UomDao dao = DaoFactory.createUomDao();
		List<Uom> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Uom dto : _result) {
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
		UomDao dao = DaoFactory.createUomDao();
		List<Uom> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Uom dto : _result) {
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
		UomDao dao = DaoFactory.createUomDao();
		List<Uom> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Uom dto : _result) {
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
		UomDao dao = DaoFactory.createUomDao();
		List<Uom> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Uom dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Uom dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getUomCode() );
		buf.append( ", " );
		buf.append( dto.getUomName() );
		buf.append( ", " );
		buf.append( dto.getRemarks() );
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

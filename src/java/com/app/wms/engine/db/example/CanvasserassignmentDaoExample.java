package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.CanvasserassignmentDao;
import com.app.wms.engine.db.dto.Canvasserassignment;
import com.app.wms.engine.db.exceptions.CanvasserassignmentDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class CanvasserassignmentDaoExample
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
		// findWherePrsnumberEquals("");
		// findWhereCanvassernameEquals("");
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
		CanvasserassignmentDao dao = DaoFactory.createCanvasserassignmentDao();
		List<Canvasserassignment> _result = dao.findAll();
		for (Canvasserassignment dto : _result) {
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
		CanvasserassignmentDao dao = DaoFactory.createCanvasserassignmentDao();
		List<Canvasserassignment> _result = dao.findWhereIdEquals(id);
		for (Canvasserassignment dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePrsnumberEquals'
	 * 
	 * @param prsnumber
	 * @throws Exception
	 */
	public static void findWherePrsnumberEquals(String prsnumber) throws Exception
	{
		CanvasserassignmentDao dao = DaoFactory.createCanvasserassignmentDao();
		List<Canvasserassignment> _result = dao.findWherePrsnumberEquals(prsnumber);
		for (Canvasserassignment dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCanvassernameEquals'
	 * 
	 * @param canvassername
	 * @throws Exception
	 */
	public static void findWhereCanvassernameEquals(String canvassername) throws Exception
	{
		CanvasserassignmentDao dao = DaoFactory.createCanvasserassignmentDao();
		List<Canvasserassignment> _result = dao.findWhereCanvassernameEquals(canvassername);
		for (Canvasserassignment dto : _result) {
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
		CanvasserassignmentDao dao = DaoFactory.createCanvasserassignmentDao();
		List<Canvasserassignment> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Canvasserassignment dto : _result) {
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
		CanvasserassignmentDao dao = DaoFactory.createCanvasserassignmentDao();
		List<Canvasserassignment> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Canvasserassignment dto : _result) {
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
		CanvasserassignmentDao dao = DaoFactory.createCanvasserassignmentDao();
		List<Canvasserassignment> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Canvasserassignment dto : _result) {
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
		CanvasserassignmentDao dao = DaoFactory.createCanvasserassignmentDao();
		List<Canvasserassignment> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Canvasserassignment dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Canvasserassignment dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getPrsnumber() );
		buf.append( ", " );
		buf.append( dto.getCanvassername() );
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

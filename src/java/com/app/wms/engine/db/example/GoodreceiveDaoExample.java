package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.GoodreceiveDao;
import com.app.wms.engine.db.dto.Goodreceive;
import com.app.wms.engine.db.exceptions.GoodreceiveDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class GoodreceiveDaoExample
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
		// findWhereGrnumberEquals("");
		// findWherePonumberEquals("");
		// findWhereReceiveddateEquals(null);
		// findWhereCreatedbyEquals("");
		// findWhereCorpidEquals("");
		// findWhereLotidEquals("");
		// findWhereWhCodeEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		GoodreceiveDao dao = DaoFactory.createGoodreceiveDao();
		List<Goodreceive> _result = dao.findAll();
		for (Goodreceive dto : _result) {
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
		GoodreceiveDao dao = DaoFactory.createGoodreceiveDao();
		List<Goodreceive> _result = dao.findWhereIdEquals(id);
		for (Goodreceive dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereGrnumberEquals'
	 * 
	 * @param grnumber
	 * @throws Exception
	 */
	public static void findWhereGrnumberEquals(String grnumber) throws Exception
	{
		GoodreceiveDao dao = DaoFactory.createGoodreceiveDao();
		List<Goodreceive> _result = dao.findWhereGrnumberEquals(grnumber);
		for (Goodreceive dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePonumberEquals'
	 * 
	 * @param ponumber
	 * @throws Exception
	 */
	public static void findWherePonumberEquals(String ponumber) throws Exception
	{
		GoodreceiveDao dao = DaoFactory.createGoodreceiveDao();
		List<Goodreceive> _result = dao.findWherePonumberEquals(ponumber);
		for (Goodreceive dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereReceiveddateEquals'
	 * 
	 * @param receiveddate
	 * @throws Exception
	 */
	public static void findWhereReceiveddateEquals(Date receiveddate) throws Exception
	{
		GoodreceiveDao dao = DaoFactory.createGoodreceiveDao();
		List<Goodreceive> _result = dao.findWhereReceiveddateEquals(receiveddate);
		for (Goodreceive dto : _result) {
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
		GoodreceiveDao dao = DaoFactory.createGoodreceiveDao();
		List<Goodreceive> _result = dao.findWhereCreatedbyEquals(createdby);
		for (Goodreceive dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCorpidEquals'
	 * 
	 * @param corpid
	 * @throws Exception
	 */
	public static void findWhereCorpidEquals(String corpid) throws Exception
	{
		GoodreceiveDao dao = DaoFactory.createGoodreceiveDao();
		List<Goodreceive> _result = dao.findWhereCorpidEquals(corpid);
		for (Goodreceive dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereLotidEquals'
	 * 
	 * @param lotid
	 * @throws Exception
	 */
	public static void findWhereLotidEquals(String lotid) throws Exception
	{
		GoodreceiveDao dao = DaoFactory.createGoodreceiveDao();
		List<Goodreceive> _result = dao.findWhereLotidEquals(lotid);
		for (Goodreceive dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereWhCodeEquals'
	 * 
	 * @param whCode
	 * @throws Exception
	 */
	public static void findWhereWhCodeEquals(String whCode) throws Exception
	{
		GoodreceiveDao dao = DaoFactory.createGoodreceiveDao();
		List<Goodreceive> _result = dao.findWhereWhCodeEquals(whCode);
		for (Goodreceive dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Goodreceive dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getGrnumber() );
		buf.append( ", " );
		buf.append( dto.getPonumber() );
		buf.append( ", " );
		buf.append( dto.getReceiveddate() );
		buf.append( ", " );
		buf.append( dto.getCreatedby() );
		buf.append( ", " );
		buf.append( dto.getCorpid() );
		buf.append( ", " );
		buf.append( dto.getLotid() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		System.out.println( buf.toString() );
	}

}

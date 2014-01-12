package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.PutawayDetailDao;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.exceptions.PutawayDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class PutawayDetailDaoExample
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
		// findWherePutawayIdEquals("");
		// findWhereProductIdEquals("");
		// findWhereReceivedDateEquals(null);
		// findWhereExpiredDateEquals(null);
		// findWhereUnitCodeEquals("");
		// findWhereQtyOrderEquals(0);
		// findWhereQtyPutEquals(0);
		// findWhereBalanceEquals(0);
		// findWhereUserIdEquals("");
		// findWhereCorpIdEquals("");
		// findWhereLocationCodeEquals("");
		// findWhereWhCodeEquals("");
		// findWhereIdEquals(0);
		// findWhereLotidEquals("");
		// findWhereProductCodeEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
		List<PutawayDetail> _result = dao.findAll();
		for (PutawayDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePutawayIdEquals'
	 * 
	 * @param putawayId
	 * @throws Exception
	 */
	public static void findWherePutawayIdEquals(String putawayId) throws Exception
	{
		PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
		List<PutawayDetail> _result = dao.findWherePutawayIdEquals(putawayId);
		for (PutawayDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductIdEquals'
	 * 
	 * @param productId
	 * @throws Exception
	 */
	public static void findWhereProductIdEquals(String productId) throws Exception
	{
		PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
		List<PutawayDetail> _result = dao.findWhereProductIdEquals(productId);
		for (PutawayDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereReceivedDateEquals'
	 * 
	 * @param receivedDate
	 * @throws Exception
	 */
	public static void findWhereReceivedDateEquals(Date receivedDate) throws Exception
	{
		PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
		List<PutawayDetail> _result = dao.findWhereReceivedDateEquals(receivedDate);
		for (PutawayDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereExpiredDateEquals'
	 * 
	 * @param expiredDate
	 * @throws Exception
	 */
	public static void findWhereExpiredDateEquals(Date expiredDate) throws Exception
	{
		PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
		List<PutawayDetail> _result = dao.findWhereExpiredDateEquals(expiredDate);
		for (PutawayDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUnitCodeEquals'
	 * 
	 * @param unitCode
	 * @throws Exception
	 */
	public static void findWhereUnitCodeEquals(String unitCode) throws Exception
	{
		PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
		List<PutawayDetail> _result = dao.findWhereUnitCodeEquals(unitCode);
		for (PutawayDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQtyOrderEquals'
	 * 
	 * @param qtyOrder
	 * @throws Exception
	 */
	public static void findWhereQtyOrderEquals(int qtyOrder) throws Exception
	{
		PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
		List<PutawayDetail> _result = dao.findWhereQtyOrderEquals(qtyOrder);
		for (PutawayDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQtyPutEquals'
	 * 
	 * @param qtyPut
	 * @throws Exception
	 */
	public static void findWhereQtyPutEquals(int qtyPut) throws Exception
	{
		PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
		List<PutawayDetail> _result = dao.findWhereQtyPutEquals(qtyPut);
		for (PutawayDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBalanceEquals'
	 * 
	 * @param balance
	 * @throws Exception
	 */
	public static void findWhereBalanceEquals(int balance) throws Exception
	{
		PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
		List<PutawayDetail> _result = dao.findWhereBalanceEquals(balance);
		for (PutawayDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUserIdEquals'
	 * 
	 * @param userId
	 * @throws Exception
	 */
	public static void findWhereUserIdEquals(String userId) throws Exception
	{
		PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
		List<PutawayDetail> _result = dao.findWhereUserIdEquals(userId);
		for (PutawayDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCorpIdEquals'
	 * 
	 * @param corpId
	 * @throws Exception
	 */
	public static void findWhereCorpIdEquals(String corpId) throws Exception
	{
		PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
		List<PutawayDetail> _result = dao.findWhereCorpIdEquals(corpId);
		for (PutawayDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereLocationCodeEquals'
	 * 
	 * @param locationCode
	 * @throws Exception
	 */
	public static void findWhereLocationCodeEquals(String locationCode) throws Exception
	{
		PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
		List<PutawayDetail> _result = dao.findWhereLocationCodeEquals(locationCode);
		for (PutawayDetail dto : _result) {
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
		PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
		List<PutawayDetail> _result = dao.findWhereWhCodeEquals(whCode);
		for (PutawayDetail dto : _result) {
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
		PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
		List<PutawayDetail> _result = dao.findWhereIdEquals(id);
		for (PutawayDetail dto : _result) {
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
		PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
		List<PutawayDetail> _result = dao.findWhereLotidEquals(lotid);
		for (PutawayDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductCodeEquals'
	 * 
	 * @param productCode
	 * @throws Exception
	 */
	public static void findWhereProductCodeEquals(String productCode) throws Exception
	{
		PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
		List<PutawayDetail> _result = dao.findWhereProductCodeEquals(productCode);
		for (PutawayDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(PutawayDetail dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getPutawayId() );
		buf.append( ", " );
		buf.append( dto.getProductId() );
		buf.append( ", " );
		buf.append( dto.getReceivedDate() );
		buf.append( ", " );
		buf.append( dto.getExpiredDate() );
		buf.append( ", " );
		buf.append( dto.getUnitCode() );
		buf.append( ", " );
		buf.append( dto.getQtyOrder() );
		buf.append( ", " );
		buf.append( dto.getQtyPut() );
		buf.append( ", " );
		buf.append( dto.getBalance() );
		buf.append( ", " );
		buf.append( dto.getUserId() );
		buf.append( ", " );
		buf.append( dto.getCorpId() );
		buf.append( ", " );
		buf.append( dto.getLocationCode() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		buf.append( ", " );
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getLotid() );
		buf.append( ", " );
		buf.append( dto.getProductCode() );
		System.out.println( buf.toString() );
	}

}

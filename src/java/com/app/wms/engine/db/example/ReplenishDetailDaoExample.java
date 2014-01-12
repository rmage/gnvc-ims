package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.ReplenishDetailDao;
import com.app.wms.engine.db.dto.ReplenishDetail;
import com.app.wms.engine.db.exceptions.ReplenishDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class ReplenishDetailDaoExample
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
		// findWhereReplenishNoEquals("");
		// findWhereFromLocationEquals("");
		// findWhereToLocationEquals("");
		// findWhereProductIdEquals("");
		// findWhereProductCodeEquals("");
		// findWhereProductNameEquals("");
		// findWhereReceivedDateEquals(null);
		// findWhereExpiredDateEquals(null);
		// findWhereUnitCodeEquals("");
		// findWhereQtyReplenishEquals(0);
		// findWhereBalanceEquals(0);
		// findWhereConfirmedByEquals("");
		// findWhereRemarksEquals("");
		// findWhereUserIdEquals("");
		// findWhereCorpIdEquals("");
		// findWhereWhCodeEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		ReplenishDetailDao dao = DaoFactory.createReplenishDetailDao();
		List<ReplenishDetail> _result = dao.findAll();
		for (ReplenishDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereReplenishNoEquals'
	 * 
	 * @param replenishNo
	 * @throws Exception
	 */
	public static void findWhereReplenishNoEquals(String replenishNo) throws Exception
	{
		ReplenishDetailDao dao = DaoFactory.createReplenishDetailDao();
		List<ReplenishDetail> _result = dao.findWhereReplenishNoEquals(replenishNo);
		for (ReplenishDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereFromLocationEquals'
	 * 
	 * @param fromLocation
	 * @throws Exception
	 */
	public static void findWhereFromLocationEquals(String fromLocation) throws Exception
	{
		ReplenishDetailDao dao = DaoFactory.createReplenishDetailDao();
		List<ReplenishDetail> _result = dao.findWhereFromLocationEquals(fromLocation);
		for (ReplenishDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereToLocationEquals'
	 * 
	 * @param toLocation
	 * @throws Exception
	 */
	public static void findWhereToLocationEquals(String toLocation) throws Exception
	{
		ReplenishDetailDao dao = DaoFactory.createReplenishDetailDao();
		List<ReplenishDetail> _result = dao.findWhereToLocationEquals(toLocation);
		for (ReplenishDetail dto : _result) {
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
		ReplenishDetailDao dao = DaoFactory.createReplenishDetailDao();
		List<ReplenishDetail> _result = dao.findWhereProductIdEquals(productId);
		for (ReplenishDetail dto : _result) {
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
		ReplenishDetailDao dao = DaoFactory.createReplenishDetailDao();
		List<ReplenishDetail> _result = dao.findWhereProductCodeEquals(productCode);
		for (ReplenishDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductNameEquals'
	 * 
	 * @param productName
	 * @throws Exception
	 */
	public static void findWhereProductNameEquals(String productName) throws Exception
	{
		ReplenishDetailDao dao = DaoFactory.createReplenishDetailDao();
		List<ReplenishDetail> _result = dao.findWhereProductNameEquals(productName);
		for (ReplenishDetail dto : _result) {
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
		ReplenishDetailDao dao = DaoFactory.createReplenishDetailDao();
		List<ReplenishDetail> _result = dao.findWhereReceivedDateEquals(receivedDate);
		for (ReplenishDetail dto : _result) {
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
		ReplenishDetailDao dao = DaoFactory.createReplenishDetailDao();
		List<ReplenishDetail> _result = dao.findWhereExpiredDateEquals(expiredDate);
		for (ReplenishDetail dto : _result) {
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
		ReplenishDetailDao dao = DaoFactory.createReplenishDetailDao();
		List<ReplenishDetail> _result = dao.findWhereUnitCodeEquals(unitCode);
		for (ReplenishDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQtyReplenishEquals'
	 * 
	 * @param qtyReplenish
	 * @throws Exception
	 */
	public static void findWhereQtyReplenishEquals(int qtyReplenish) throws Exception
	{
		ReplenishDetailDao dao = DaoFactory.createReplenishDetailDao();
		List<ReplenishDetail> _result = dao.findWhereQtyReplenishEquals(qtyReplenish);
		for (ReplenishDetail dto : _result) {
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
		ReplenishDetailDao dao = DaoFactory.createReplenishDetailDao();
		List<ReplenishDetail> _result = dao.findWhereBalanceEquals(balance);
		for (ReplenishDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereConfirmedByEquals'
	 * 
	 * @param confirmedBy
	 * @throws Exception
	 */
	public static void findWhereConfirmedByEquals(String confirmedBy) throws Exception
	{
		ReplenishDetailDao dao = DaoFactory.createReplenishDetailDao();
		List<ReplenishDetail> _result = dao.findWhereConfirmedByEquals(confirmedBy);
		for (ReplenishDetail dto : _result) {
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
		ReplenishDetailDao dao = DaoFactory.createReplenishDetailDao();
		List<ReplenishDetail> _result = dao.findWhereRemarksEquals(remarks);
		for (ReplenishDetail dto : _result) {
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
		ReplenishDetailDao dao = DaoFactory.createReplenishDetailDao();
		List<ReplenishDetail> _result = dao.findWhereUserIdEquals(userId);
		for (ReplenishDetail dto : _result) {
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
		ReplenishDetailDao dao = DaoFactory.createReplenishDetailDao();
		List<ReplenishDetail> _result = dao.findWhereCorpIdEquals(corpId);
		for (ReplenishDetail dto : _result) {
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
		ReplenishDetailDao dao = DaoFactory.createReplenishDetailDao();
		List<ReplenishDetail> _result = dao.findWhereWhCodeEquals(whCode);
		for (ReplenishDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(ReplenishDetail dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getReplenishNo() );
		buf.append( ", " );
		buf.append( dto.getFromLocation() );
		buf.append( ", " );
		buf.append( dto.getToLocation() );
		buf.append( ", " );
		buf.append( dto.getProductId() );
		buf.append( ", " );
		buf.append( dto.getProductCode() );
		buf.append( ", " );
		buf.append( dto.getProductName() );
		buf.append( ", " );
		buf.append( dto.getReceivedDate() );
		buf.append( ", " );
		buf.append( dto.getExpiredDate() );
		buf.append( ", " );
		buf.append( dto.getUnitCode() );
		buf.append( ", " );
		buf.append( dto.getQtyReplenish() );
		buf.append( ", " );
		buf.append( dto.getBalance() );
		buf.append( ", " );
		buf.append( dto.getConfirmedBy() );
		buf.append( ", " );
		buf.append( dto.getRemarks() );
		buf.append( ", " );
		buf.append( dto.getUserId() );
		buf.append( ", " );
		buf.append( dto.getCorpId() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		System.out.println( buf.toString() );
	}

}

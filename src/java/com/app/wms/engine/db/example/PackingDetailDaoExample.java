package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.PackingDetailDao;
import com.app.wms.engine.db.dto.PackingDetail;
import com.app.wms.engine.db.exceptions.PackingDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class PackingDetailDaoExample
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
		// findWherePackingNoEquals("");
		// findWhereSoNumberEquals("");
		// findWherePickingIdEquals("");
		// findWhereKittingNoEquals("");
		// findWhereProductIdEquals("");
		// findWhereProductCodeEquals("");
		// findWhereProductNameEquals("");
		// findWhereUnitcodeEquals("");
		// findWhereQuantityEquals(0);
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
		PackingDetailDao dao = DaoFactory.createPackingDetailDao();
		List<PackingDetail> _result = dao.findAll();
		for (PackingDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePackingNoEquals'
	 * 
	 * @param packingNo
	 * @throws Exception
	 */
	public static void findWherePackingNoEquals(String packingNo) throws Exception
	{
		PackingDetailDao dao = DaoFactory.createPackingDetailDao();
		List<PackingDetail> _result = dao.findWherePackingNoEquals(packingNo);
		for (PackingDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereSoNumberEquals'
	 * 
	 * @param soNumber
	 * @throws Exception
	 */
	public static void findWhereSoNumberEquals(String soNumber) throws Exception
	{
		PackingDetailDao dao = DaoFactory.createPackingDetailDao();
		List<PackingDetail> _result = dao.findWhereSoNumberEquals(soNumber);
		for (PackingDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePickingIdEquals'
	 * 
	 * @param pickingId
	 * @throws Exception
	 */
	public static void findWherePickingIdEquals(String pickingId) throws Exception
	{
		PackingDetailDao dao = DaoFactory.createPackingDetailDao();
		List<PackingDetail> _result = dao.findWherePickingIdEquals(pickingId);
		for (PackingDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereKittingNoEquals'
	 * 
	 * @param kittingNo
	 * @throws Exception
	 */
	public static void findWhereKittingNoEquals(String kittingNo) throws Exception
	{
		PackingDetailDao dao = DaoFactory.createPackingDetailDao();
		List<PackingDetail> _result = dao.findWhereKittingNoEquals(kittingNo);
		for (PackingDetail dto : _result) {
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
		PackingDetailDao dao = DaoFactory.createPackingDetailDao();
		List<PackingDetail> _result = dao.findWhereProductIdEquals(productId);
		for (PackingDetail dto : _result) {
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
		PackingDetailDao dao = DaoFactory.createPackingDetailDao();
		List<PackingDetail> _result = dao.findWhereProductCodeEquals(productCode);
		for (PackingDetail dto : _result) {
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
		PackingDetailDao dao = DaoFactory.createPackingDetailDao();
		List<PackingDetail> _result = dao.findWhereProductNameEquals(productName);
		for (PackingDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUnitcodeEquals'
	 * 
	 * @param unitcode
	 * @throws Exception
	 */
	public static void findWhereUnitcodeEquals(String unitcode) throws Exception
	{
		PackingDetailDao dao = DaoFactory.createPackingDetailDao();
		List<PackingDetail> _result = dao.findWhereUnitcodeEquals(unitcode);
		for (PackingDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQuantityEquals'
	 * 
	 * @param quantity
	 * @throws Exception
	 */
	public static void findWhereQuantityEquals(int quantity) throws Exception
	{
		PackingDetailDao dao = DaoFactory.createPackingDetailDao();
		List<PackingDetail> _result = dao.findWhereQuantityEquals(quantity);
		for (PackingDetail dto : _result) {
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
		PackingDetailDao dao = DaoFactory.createPackingDetailDao();
		List<PackingDetail> _result = dao.findWhereUserIdEquals(userId);
		for (PackingDetail dto : _result) {
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
		PackingDetailDao dao = DaoFactory.createPackingDetailDao();
		List<PackingDetail> _result = dao.findWhereCorpIdEquals(corpId);
		for (PackingDetail dto : _result) {
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
		PackingDetailDao dao = DaoFactory.createPackingDetailDao();
		List<PackingDetail> _result = dao.findWhereWhCodeEquals(whCode);
		for (PackingDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(PackingDetail dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getPackingNo() );
		buf.append( ", " );
		buf.append( dto.getSoNumber() );
		buf.append( ", " );
		buf.append( dto.getPickingId() );
		buf.append( ", " );
		buf.append( dto.getKittingNo() );
		buf.append( ", " );
		buf.append( dto.getProductId() );
		buf.append( ", " );
		buf.append( dto.getProductCode() );
		buf.append( ", " );
		buf.append( dto.getProductName() );
		buf.append( ", " );
		buf.append( dto.getUnitcode() );
		buf.append( ", " );
		buf.append( dto.getQuantity() );
		buf.append( ", " );
		buf.append( dto.getUserId() );
		buf.append( ", " );
		buf.append( dto.getCorpId() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		System.out.println( buf.toString() );
	}

}

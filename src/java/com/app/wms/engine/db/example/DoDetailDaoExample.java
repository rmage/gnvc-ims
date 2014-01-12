package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.DoDetailDao;
import com.app.wms.engine.db.dto.DoDetail;
import com.app.wms.engine.db.exceptions.DoDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class DoDetailDaoExample
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
		// findWhereDeliveryNoEquals("");
		// findWhereSoNumberEquals("");
		// findWherePickingIdEquals("");
		// findWhereProductIdEquals("");
		// findWhereProductCodeEquals("");
		// findWhereProductNameEquals("");
		// findWhereQtyDeliveryEquals(0);
		// findWhereUnitCodeEquals("");
		// findWhereTransporterTypeEquals("");
		// findWhereTransporterNameEquals("");
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
		DoDetailDao dao = DaoFactory.createDoDetailDao();
		List<DoDetail> _result = dao.findAll();
		for (DoDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDeliveryNoEquals'
	 * 
	 * @param deliveryNo
	 * @throws Exception
	 */
	public static void findWhereDeliveryNoEquals(String deliveryNo) throws Exception
	{
		DoDetailDao dao = DaoFactory.createDoDetailDao();
		List<DoDetail> _result = dao.findWhereDeliveryNoEquals(deliveryNo);
		for (DoDetail dto : _result) {
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
		DoDetailDao dao = DaoFactory.createDoDetailDao();
		List<DoDetail> _result = dao.findWhereSoNumberEquals(soNumber);
		for (DoDetail dto : _result) {
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
		DoDetailDao dao = DaoFactory.createDoDetailDao();
		List<DoDetail> _result = dao.findWherePickingIdEquals(pickingId);
		for (DoDetail dto : _result) {
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
		DoDetailDao dao = DaoFactory.createDoDetailDao();
		List<DoDetail> _result = dao.findWhereProductIdEquals(productId);
		for (DoDetail dto : _result) {
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
		DoDetailDao dao = DaoFactory.createDoDetailDao();
		List<DoDetail> _result = dao.findWhereProductCodeEquals(productCode);
		for (DoDetail dto : _result) {
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
		DoDetailDao dao = DaoFactory.createDoDetailDao();
		List<DoDetail> _result = dao.findWhereProductNameEquals(productName);
		for (DoDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQtyDeliveryEquals'
	 * 
	 * @param qtyDelivery
	 * @throws Exception
	 */
	public static void findWhereQtyDeliveryEquals(int qtyDelivery) throws Exception
	{
		DoDetailDao dao = DaoFactory.createDoDetailDao();
		List<DoDetail> _result = dao.findWhereQtyDeliveryEquals(qtyDelivery);
		for (DoDetail dto : _result) {
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
		DoDetailDao dao = DaoFactory.createDoDetailDao();
		List<DoDetail> _result = dao.findWhereUnitCodeEquals(unitCode);
		for (DoDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereTransporterTypeEquals'
	 * 
	 * @param transporterType
	 * @throws Exception
	 */
	public static void findWhereTransporterTypeEquals(String transporterType) throws Exception
	{
		DoDetailDao dao = DaoFactory.createDoDetailDao();
		List<DoDetail> _result = dao.findWhereTransporterTypeEquals(transporterType);
		for (DoDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereTransporterNameEquals'
	 * 
	 * @param transporterName
	 * @throws Exception
	 */
	public static void findWhereTransporterNameEquals(String transporterName) throws Exception
	{
		DoDetailDao dao = DaoFactory.createDoDetailDao();
		List<DoDetail> _result = dao.findWhereTransporterNameEquals(transporterName);
		for (DoDetail dto : _result) {
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
		DoDetailDao dao = DaoFactory.createDoDetailDao();
		List<DoDetail> _result = dao.findWhereUserIdEquals(userId);
		for (DoDetail dto : _result) {
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
		DoDetailDao dao = DaoFactory.createDoDetailDao();
		List<DoDetail> _result = dao.findWhereCorpIdEquals(corpId);
		for (DoDetail dto : _result) {
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
		DoDetailDao dao = DaoFactory.createDoDetailDao();
		List<DoDetail> _result = dao.findWhereWhCodeEquals(whCode);
		for (DoDetail dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(DoDetail dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getDeliveryNo() );
		buf.append( ", " );
		buf.append( dto.getSoNumber() );
		buf.append( ", " );
		buf.append( dto.getPickingId() );
		buf.append( ", " );
		buf.append( dto.getProductId() );
		buf.append( ", " );
		buf.append( dto.getProductCode() );
		buf.append( ", " );
		buf.append( dto.getProductName() );
		buf.append( ", " );
		buf.append( dto.getQtyDelivery() );
		buf.append( ", " );
		buf.append( dto.getUnitCode() );
		buf.append( ", " );
		buf.append( dto.getTransporterType() );
		buf.append( ", " );
		buf.append( dto.getTransporterName() );
		buf.append( ", " );
		buf.append( dto.getUserId() );
		buf.append( ", " );
		buf.append( dto.getCorpId() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		System.out.println( buf.toString() );
	}

}

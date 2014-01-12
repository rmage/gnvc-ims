package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.EdsDao;
import com.app.wms.engine.db.dto.Eds;
import com.app.wms.engine.db.exceptions.EdsDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class EdsDaoExample
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
		// findWhereEdsnumberEquals("");
		// findWhereCinumberEquals("");
		// findWhereBuyerNameEquals("");
		// findWhereBrandEquals("");
		// findWhereReferensiEquals("");
		// findWhereDestinationEquals("");
		// findWhereVanEquals("");
		// findWhereSealShipEquals("");
		// findWhereVesselEquals("");
		// findWherePlatNoEquals("");
		// findWhereTimeInEquals(null);
		// findWhereTimeOutEquals(null);
		// findWhereDriverNameEquals("");
		// findWhereIssuedByEquals("");
		// findWhereCheckedByEquals("");
		// findWhereApprovedByEquals("");
		// findWhereRemarksEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findAll();
		for (Eds dto : _result) {
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
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWhereIdEquals(id);
		for (Eds dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereEdsnumberEquals'
	 * 
	 * @param edsnumber
	 * @throws Exception
	 */
	public static void findWhereEdsnumberEquals(String edsnumber) throws Exception
	{
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWhereEdsnumberEquals(edsnumber);
		for (Eds dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCinumberEquals'
	 * 
	 * @param cinumber
	 * @throws Exception
	 */
	public static void findWhereCinumberEquals(String cinumber) throws Exception
	{
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWhereCinumberEquals(cinumber);
		for (Eds dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBuyerNameEquals'
	 * 
	 * @param buyerName
	 * @throws Exception
	 */
	public static void findWhereBuyerNameEquals(String buyerName) throws Exception
	{
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWhereBuyerNameEquals(buyerName);
		for (Eds dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBrandEquals'
	 * 
	 * @param brand
	 * @throws Exception
	 */
	public static void findWhereBrandEquals(String brand) throws Exception
	{
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWhereBrandEquals(brand);
		for (Eds dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereReferensiEquals'
	 * 
	 * @param referensi
	 * @throws Exception
	 */
	public static void findWhereReferensiEquals(String referensi) throws Exception
	{
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWhereReferensiEquals(referensi);
		for (Eds dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDestinationEquals'
	 * 
	 * @param destination
	 * @throws Exception
	 */
	public static void findWhereDestinationEquals(String destination) throws Exception
	{
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWhereDestinationEquals(destination);
		for (Eds dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereVanEquals'
	 * 
	 * @param van
	 * @throws Exception
	 */
	public static void findWhereVanEquals(String van) throws Exception
	{
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWhereVanEquals(van);
		for (Eds dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereSealShipEquals'
	 * 
	 * @param sealShip
	 * @throws Exception
	 */
	public static void findWhereSealShipEquals(String sealShip) throws Exception
	{
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWhereSealShipEquals(sealShip);
		for (Eds dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereVesselEquals'
	 * 
	 * @param vessel
	 * @throws Exception
	 */
	public static void findWhereVesselEquals(String vessel) throws Exception
	{
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWhereVesselEquals(vessel);
		for (Eds dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePlatNoEquals'
	 * 
	 * @param platNo
	 * @throws Exception
	 */
	public static void findWherePlatNoEquals(String platNo) throws Exception
	{
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWherePlatNoEquals(platNo);
		for (Eds dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereTimeInEquals'
	 * 
	 * @param timeIn
	 * @throws Exception
	 */
	public static void findWhereTimeInEquals(Date timeIn) throws Exception
	{
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWhereTimeInEquals(timeIn);
		for (Eds dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereTimeOutEquals'
	 * 
	 * @param timeOut
	 * @throws Exception
	 */
	public static void findWhereTimeOutEquals(Date timeOut) throws Exception
	{
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWhereTimeOutEquals(timeOut);
		for (Eds dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDriverNameEquals'
	 * 
	 * @param driverName
	 * @throws Exception
	 */
	public static void findWhereDriverNameEquals(String driverName) throws Exception
	{
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWhereDriverNameEquals(driverName);
		for (Eds dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIssuedByEquals'
	 * 
	 * @param issuedBy
	 * @throws Exception
	 */
	public static void findWhereIssuedByEquals(String issuedBy) throws Exception
	{
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWhereIssuedByEquals(issuedBy);
		for (Eds dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCheckedByEquals'
	 * 
	 * @param checkedBy
	 * @throws Exception
	 */
	public static void findWhereCheckedByEquals(String checkedBy) throws Exception
	{
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWhereCheckedByEquals(checkedBy);
		for (Eds dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereApprovedByEquals'
	 * 
	 * @param approvedBy
	 * @throws Exception
	 */
	public static void findWhereApprovedByEquals(String approvedBy) throws Exception
	{
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWhereApprovedByEquals(approvedBy);
		for (Eds dto : _result) {
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
		EdsDao dao = DaoFactory.createEdsDao();
		List<Eds> _result = dao.findWhereRemarksEquals(remarks);
		for (Eds dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Eds dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getEdsnumber() );
		buf.append( ", " );
		buf.append( dto.getCinumber() );
		buf.append( ", " );
		buf.append( dto.getBuyerName() );
		buf.append( ", " );
		buf.append( dto.getBrand() );
		buf.append( ", " );
		buf.append( dto.getReferensi() );
		buf.append( ", " );
		buf.append( dto.getDestination() );
		buf.append( ", " );
		buf.append( dto.getVan() );
		buf.append( ", " );
		buf.append( dto.getSealShip() );
		buf.append( ", " );
		buf.append( dto.getVessel() );
		buf.append( ", " );
		buf.append( dto.getPlatNo() );
		buf.append( ", " );
		buf.append( dto.getTimeIn() );
		buf.append( ", " );
		buf.append( dto.getTimeOut() );
		buf.append( ", " );
		buf.append( dto.getDriverName() );
		buf.append( ", " );
		buf.append( dto.getIssuedBy() );
		buf.append( ", " );
		buf.append( dto.getCheckedBy() );
		buf.append( ", " );
		buf.append( dto.getApprovedBy() );
		buf.append( ", " );
		buf.append( dto.getRemarks() );
		System.out.println( buf.toString() );
	}

}

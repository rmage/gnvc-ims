package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.DrDao;
import com.app.wms.engine.db.dto.Dr;
import com.app.wms.engine.db.exceptions.DrDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class DrDaoExample
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
		// findWhereDrnumberEquals("");
		// findWhereOrnumberEquals("");
		// findWhereDmnumberEquals("");
		// findWhereDrdateEquals(null);
		// findWhereCreatedbyEquals("");
		// findWhereLotidEquals("");
		// findWhereWhCodeEquals("");
		// findWhereWsNoEquals("");
		// findWhereUnitCostEquals(0);
		// findWhereAmountEquals(0);
		// findWhereFromEquals("");
		// findWhereIssuedByEquals("");
		// findWhereDeliveredByEquals("");
		// findWhereAccEntryEquals("");
		// findWhereApprovedByEquals("");
		// findWhereRemarksEquals("");
		// findWhereReceivedByEquals("");
		// findWhereDepartmentNameEquals("");
		// findWhereSupplierNameEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findAll();
		for (Dr dto : _result) {
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
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereIdEquals(id);
		for (Dr dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDrnumberEquals'
	 * 
	 * @param drnumber
	 * @throws Exception
	 */
	public static void findWhereDrnumberEquals(String drnumber) throws Exception
	{
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereDrnumberEquals(drnumber);
		for (Dr dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereOrnumberEquals'
	 * 
	 * @param ornumber
	 * @throws Exception
	 */
	public static void findWhereOrnumberEquals(String ornumber) throws Exception
	{
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereOrnumberEquals(ornumber);
		for (Dr dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDmnumberEquals'
	 * 
	 * @param dmnumber
	 * @throws Exception
	 */
	public static void findWhereDmnumberEquals(String dmnumber) throws Exception
	{
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereDmnumberEquals(dmnumber);
		for (Dr dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDrdateEquals'
	 * 
	 * @param drdate
	 * @throws Exception
	 */
	public static void findWhereDrdateEquals(Date drdate) throws Exception
	{
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereDrdateEquals(drdate);
		for (Dr dto : _result) {
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
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereCreatedbyEquals(createdby);
		for (Dr dto : _result) {
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
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereLotidEquals(lotid);
		for (Dr dto : _result) {
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
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereWhCodeEquals(whCode);
		for (Dr dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereWsNoEquals'
	 * 
	 * @param wsNo
	 * @throws Exception
	 */
	public static void findWhereWsNoEquals(String wsNo) throws Exception
	{
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereWsNoEquals(wsNo);
		for (Dr dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUnitCostEquals'
	 * 
	 * @param unitCost
	 * @throws Exception
	 */
	public static void findWhereUnitCostEquals(int unitCost) throws Exception
	{
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereUnitCostEquals(unitCost);
		for (Dr dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereAmountEquals'
	 * 
	 * @param amount
	 * @throws Exception
	 */
	public static void findWhereAmountEquals(int amount) throws Exception
	{
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereAmountEquals(amount);
		for (Dr dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereFromEquals'
	 * 
	 * @param from
	 * @throws Exception
	 */
	public static void findWhereFromEquals(String from) throws Exception
	{
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereFromEquals(from);
		for (Dr dto : _result) {
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
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereIssuedByEquals(issuedBy);
		for (Dr dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDeliveredByEquals'
	 * 
	 * @param deliveredBy
	 * @throws Exception
	 */
	public static void findWhereDeliveredByEquals(String deliveredBy) throws Exception
	{
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereDeliveredByEquals(deliveredBy);
		for (Dr dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereAccEntryEquals'
	 * 
	 * @param accEntry
	 * @throws Exception
	 */
	public static void findWhereAccEntryEquals(String accEntry) throws Exception
	{
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereAccEntryEquals(accEntry);
		for (Dr dto : _result) {
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
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereApprovedByEquals(approvedBy);
		for (Dr dto : _result) {
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
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereRemarksEquals(remarks);
		for (Dr dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereReceivedByEquals'
	 * 
	 * @param receivedBy
	 * @throws Exception
	 */
	public static void findWhereReceivedByEquals(String receivedBy) throws Exception
	{
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereReceivedByEquals(receivedBy);
		for (Dr dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDepartmentNameEquals'
	 * 
	 * @param departmentName
	 * @throws Exception
	 */
	public static void findWhereDepartmentNameEquals(String departmentName) throws Exception
	{
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereDepartmentNameEquals(departmentName);
		for (Dr dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereSupplierNameEquals'
	 * 
	 * @param supplierName
	 * @throws Exception
	 */
	public static void findWhereSupplierNameEquals(String supplierName) throws Exception
	{
		DrDao dao = DaoFactory.createDrDao();
		List<Dr> _result = dao.findWhereSupplierNameEquals(supplierName);
		for (Dr dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Dr dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getDrnumber() );
		buf.append( ", " );
		buf.append( dto.getOrnumber() );
		buf.append( ", " );
		buf.append( dto.getDmnumber() );
		buf.append( ", " );
		buf.append( dto.getDrdate() );
		buf.append( ", " );
		buf.append( dto.getCreatedby() );
		buf.append( ", " );
		buf.append( dto.getLotid() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		buf.append( ", " );
		buf.append( dto.getWsNo() );
		buf.append( ", " );
		buf.append( dto.getUnitCost() );
		buf.append( ", " );
		buf.append( dto.getAmount() );
		buf.append( ", " );
		buf.append( dto.getFrom() );
		buf.append( ", " );
		buf.append( dto.getIssuedBy() );
		buf.append( ", " );
		buf.append( dto.getDeliveredBy() );
		buf.append( ", " );
		buf.append( dto.getAccEntry() );
		buf.append( ", " );
		buf.append( dto.getApprovedBy() );
		buf.append( ", " );
		buf.append( dto.getRemarks() );
		buf.append( ", " );
		buf.append( dto.getReceivedBy() );
		buf.append( ", " );
		buf.append( dto.getDepartmentName() );
		buf.append( ", " );
		buf.append( dto.getSupplierName() );
		System.out.println( buf.toString() );
	}

}

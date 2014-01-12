package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.PrsDao;
import com.app.wms.engine.db.dto.Prs;
import com.app.wms.engine.db.exceptions.PrsDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class PrsDaoExample
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
		// findWherePrsdateEquals(null);
		// findWhereRequestdateEquals(null);
		// findWhereDeliverydateEquals(null);
		// findWherePoreferensiEquals("");
		// findWhereRemarksEquals("");
		// findWhereCreatedbyEquals("");
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
		PrsDao dao = DaoFactory.createPrsDao();
		List<Prs> _result = dao.findAll();
		for (Prs dto : _result) {
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
		PrsDao dao = DaoFactory.createPrsDao();
		List<Prs> _result = dao.findWhereIdEquals(id);
		for (Prs dto : _result) {
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
		PrsDao dao = DaoFactory.createPrsDao();
		List<Prs> _result = dao.findWherePrsnumberEquals(prsnumber);
		for (Prs dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePrsdateEquals'
	 * 
	 * @param prsdate
	 * @throws Exception
	 */
	public static void findWherePrsdateEquals(Date prsdate) throws Exception
	{
		PrsDao dao = DaoFactory.createPrsDao();
		List<Prs> _result = dao.findWherePrsdateEquals(prsdate);
		for (Prs dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereRequestdateEquals'
	 * 
	 * @param requestdate
	 * @throws Exception
	 */
	public static void findWhereRequestdateEquals(Date requestdate) throws Exception
	{
		PrsDao dao = DaoFactory.createPrsDao();
		List<Prs> _result = dao.findWhereRequestdateEquals(requestdate);
		for (Prs dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDeliverydateEquals'
	 * 
	 * @param deliverydate
	 * @throws Exception
	 */
	public static void findWhereDeliverydateEquals(Date deliverydate) throws Exception
	{
		PrsDao dao = DaoFactory.createPrsDao();
		List<Prs> _result = dao.findWhereDeliverydateEquals(deliverydate);
		for (Prs dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePoreferensiEquals'
	 * 
	 * @param poreferensi
	 * @throws Exception
	 */
	public static void findWherePoreferensiEquals(String poreferensi) throws Exception
	{
		PrsDao dao = DaoFactory.createPrsDao();
		List<Prs> _result = dao.findWherePoreferensiEquals(poreferensi);
		for (Prs dto : _result) {
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
		PrsDao dao = DaoFactory.createPrsDao();
		List<Prs> _result = dao.findWhereRemarksEquals(remarks);
		for (Prs dto : _result) {
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
		PrsDao dao = DaoFactory.createPrsDao();
		List<Prs> _result = dao.findWhereCreatedbyEquals(createdby);
		for (Prs dto : _result) {
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
		PrsDao dao = DaoFactory.createPrsDao();
		List<Prs> _result = dao.findWhereDepartmentNameEquals(departmentName);
		for (Prs dto : _result) {
			display(dto);
		}
		
	}


	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Prs dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getPrsnumber() );
		buf.append( ", " );
		buf.append( dto.getPrsdate() );
		buf.append( ", " );
		buf.append( dto.getRequestdate() );
		buf.append( ", " );
		buf.append( dto.getDeliverydate() );
		buf.append( ", " );
		buf.append( dto.getPoreferensi() );
		buf.append( ", " );
		buf.append( dto.getRemarks() );
		buf.append( ", " );
		buf.append( dto.getCreatedby() );
		buf.append( ", " );
		buf.append( dto.getDepartmentName() );
		System.out.println( buf.toString() );
	}

}

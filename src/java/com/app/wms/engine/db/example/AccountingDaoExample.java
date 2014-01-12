package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.AccountingDao;
import com.app.wms.engine.db.dto.Accounting;
import com.app.wms.engine.db.exceptions.AccountingDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class AccountingDaoExample
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
		// findWhereDocumentNoEquals("");
		// findWhereDocumentDateEquals(null);
		// findWhereProductCodeEquals("");
		// findWhereProductNameEquals("");
		// findWhereQtyInEquals(0);
		// findWhereQtyOutEquals(0);
		// findWhereBalanceEquals(0);
		// findWhereUnitpriceEquals(0);
		// findWhereAmountEquals(0);
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
		AccountingDao dao = DaoFactory.createAccountingDao();
		List<Accounting> _result = dao.findAll();
		for (Accounting dto : _result) {
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
		AccountingDao dao = DaoFactory.createAccountingDao();
		List<Accounting> _result = dao.findWhereIdEquals(id);
		for (Accounting dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDocumentNoEquals'
	 * 
	 * @param documentNo
	 * @throws Exception
	 */
	public static void findWhereDocumentNoEquals(String documentNo) throws Exception
	{
		AccountingDao dao = DaoFactory.createAccountingDao();
		List<Accounting> _result = dao.findWhereDocumentNoEquals(documentNo);
		for (Accounting dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDocumentDateEquals'
	 * 
	 * @param documentDate
	 * @throws Exception
	 */
	public static void findWhereDocumentDateEquals(Date documentDate) throws Exception
	{
		AccountingDao dao = DaoFactory.createAccountingDao();
		List<Accounting> _result = dao.findWhereDocumentDateEquals(documentDate);
		for (Accounting dto : _result) {
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
		AccountingDao dao = DaoFactory.createAccountingDao();
		List<Accounting> _result = dao.findWhereProductCodeEquals(productCode);
		for (Accounting dto : _result) {
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
		AccountingDao dao = DaoFactory.createAccountingDao();
		List<Accounting> _result = dao.findWhereProductNameEquals(productName);
		for (Accounting dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQtyInEquals'
	 * 
	 * @param qtyIn
	 * @throws Exception
	 */
	public static void findWhereQtyInEquals(float qtyIn) throws Exception
	{
		AccountingDao dao = DaoFactory.createAccountingDao();
		List<Accounting> _result = dao.findWhereQtyInEquals(qtyIn);
		for (Accounting dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQtyOutEquals'
	 * 
	 * @param qtyOut
	 * @throws Exception
	 */
	public static void findWhereQtyOutEquals(float qtyOut) throws Exception
	{
		AccountingDao dao = DaoFactory.createAccountingDao();
		List<Accounting> _result = dao.findWhereQtyOutEquals(qtyOut);
		for (Accounting dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBalanceEquals'
	 * 
	 * @param balance
	 * @throws Exception
	 */
	public static void findWhereBalanceEquals(float balance) throws Exception
	{
		AccountingDao dao = DaoFactory.createAccountingDao();
		List<Accounting> _result = dao.findWhereBalanceEquals(balance);
		for (Accounting dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUnitpriceEquals'
	 * 
	 * @param unitprice
	 * @throws Exception
	 */
	public static void findWhereUnitpriceEquals(float unitprice) throws Exception
	{
		AccountingDao dao = DaoFactory.createAccountingDao();
		List<Accounting> _result = dao.findWhereUnitpriceEquals(unitprice);
		for (Accounting dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereAmountEquals'
	 * 
	 * @param amount
	 * @throws Exception
	 */
	public static void findWhereAmountEquals(float amount) throws Exception
	{
		AccountingDao dao = DaoFactory.createAccountingDao();
		List<Accounting> _result = dao.findWhereAmountEquals(amount);
		for (Accounting dto : _result) {
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
		AccountingDao dao = DaoFactory.createAccountingDao();
		List<Accounting> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Accounting dto : _result) {
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
		AccountingDao dao = DaoFactory.createAccountingDao();
		List<Accounting> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Accounting dto : _result) {
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
		AccountingDao dao = DaoFactory.createAccountingDao();
		List<Accounting> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Accounting dto : _result) {
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
		AccountingDao dao = DaoFactory.createAccountingDao();
		List<Accounting> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Accounting dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Accounting dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getDocumentNo() );
		buf.append( ", " );
		buf.append( dto.getDocumentDate() );
		buf.append( ", " );
		buf.append( dto.getProductCode() );
		buf.append( ", " );
		buf.append( dto.getProductName() );
		buf.append( ", " );
		buf.append( dto.getQtyIn() );
		buf.append( ", " );
		buf.append( dto.getQtyOut() );
		buf.append( ", " );
		buf.append( dto.getBalance() );
		buf.append( ", " );
		buf.append( dto.getUnitprice() );
		buf.append( ", " );
		buf.append( dto.getAmount() );
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

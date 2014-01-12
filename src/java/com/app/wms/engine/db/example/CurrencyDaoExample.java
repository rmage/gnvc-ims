package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dto.Currency;
import com.app.wms.engine.db.exceptions.CurrencyDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class CurrencyDaoExample
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
		// findWhereCurrencyCodeEquals("");
		// findWhereCurrencyNameEquals("");
		// findWhereIsActiveEquals("");
		// findWhereIsDeleteEquals("");
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
		CurrencyDao dao = DaoFactory.createCurrencyDao();
		List<Currency> _result = dao.findAll();
		for (Currency dto : _result) {
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
		CurrencyDao dao = DaoFactory.createCurrencyDao();
		List<Currency> _result = dao.findWhereIdEquals(id);
		for (Currency dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCurrencyCodeEquals'
	 * 
	 * @param currencyCode
	 * @throws Exception
	 */
	public static void findWhereCurrencyCodeEquals(String currencyCode) throws Exception
	{
		CurrencyDao dao = DaoFactory.createCurrencyDao();
		List<Currency> _result = dao.findWhereCurrencyCodeEquals(currencyCode);
		for (Currency dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCurrencyNameEquals'
	 * 
	 * @param currencyName
	 * @throws Exception
	 */
	public static void findWhereCurrencyNameEquals(String currencyName) throws Exception
	{
		CurrencyDao dao = DaoFactory.createCurrencyDao();
		List<Currency> _result = dao.findWhereCurrencyNameEquals(currencyName);
		for (Currency dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIsActiveEquals'
	 * 
	 * @param isActive
	 * @throws Exception
	 */
	public static void findWhereIsActiveEquals(String isActive) throws Exception
	{
		CurrencyDao dao = DaoFactory.createCurrencyDao();
		List<Currency> _result = dao.findWhereIsActiveEquals(isActive);
		for (Currency dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIsDeleteEquals'
	 * 
	 * @param isDelete
	 * @throws Exception
	 */
	public static void findWhereIsDeleteEquals(String isDelete) throws Exception
	{
		CurrencyDao dao = DaoFactory.createCurrencyDao();
		List<Currency> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (Currency dto : _result) {
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
		CurrencyDao dao = DaoFactory.createCurrencyDao();
		List<Currency> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Currency dto : _result) {
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
		CurrencyDao dao = DaoFactory.createCurrencyDao();
		List<Currency> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Currency dto : _result) {
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
		CurrencyDao dao = DaoFactory.createCurrencyDao();
		List<Currency> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Currency dto : _result) {
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
		CurrencyDao dao = DaoFactory.createCurrencyDao();
		List<Currency> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Currency dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Currency dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getCurrencyCode() );
		buf.append( ", " );
		buf.append( dto.getCurrencyName() );
		buf.append( ", " );
		buf.append( dto.getIsActive() );
		buf.append( ", " );
		buf.append( dto.getIsDelete() );
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

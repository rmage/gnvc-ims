package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;

import com.app.wms.engine.db.dao.CityDao;
import com.app.wms.engine.db.dto.City;
import com.app.wms.engine.db.exceptions.CityDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class CityDaoExample
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
		// findWhereCityCodeEquals("");
		// findWhereNameEquals("");
		// findWhereCreatedByEquals(null);
		// findWhereCreatedDateEquals(null);
		// findWhereUpdatedByEquals(null);
		// findWhereUpdatedDateEquals(null);
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		CityDao dao = DaoFactory.createCityDao();
		List<City> _result = dao.findAll();
		for (City dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCityCodeEquals'
	 * 
	 * @param cityCode
	 * @throws Exception
	 */
	public static void findWhereCityCodeEquals(String cityCode) throws Exception
	{
		CityDao dao = DaoFactory.createCityDao();
		List<City> _result = dao.findWhereCityCodeEquals(cityCode);
		for (City dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereNameEquals'
	 * 
	 * @param name
	 * @throws Exception
	 */
	public static void findWhereNameEquals(String name) throws Exception
	{
		CityDao dao = DaoFactory.createCityDao();
		List<City> _result = dao.findWhereNameEquals(name);
		for (City dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCreatedByEquals'
	 * 
	 * @param createdBy
	 * @throws Exception
	 */
	public static void findWhereCreatedByEquals(BigDecimal createdBy) throws Exception
	{
		CityDao dao = DaoFactory.createCityDao();
		List<City> _result = dao.findWhereCreatedByEquals(createdBy);
		for (City dto : _result) {
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
		CityDao dao = DaoFactory.createCityDao();
		List<City> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (City dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUpdatedByEquals'
	 * 
	 * @param updatedBy
	 * @throws Exception
	 */
	public static void findWhereUpdatedByEquals(BigDecimal updatedBy) throws Exception
	{
		CityDao dao = DaoFactory.createCityDao();
		List<City> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (City dto : _result) {
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
		CityDao dao = DaoFactory.createCityDao();
		List<City> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (City dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(City dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getCityCode() );
		buf.append( ", " );
		buf.append( dto.getName() );
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

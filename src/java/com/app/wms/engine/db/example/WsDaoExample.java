package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.WsDao;
import com.app.wms.engine.db.dto.Ws;
import com.app.wms.engine.db.exceptions.WsDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class WsDaoExample
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
		// findWhereWsCodeEquals("");
		// findWhereWsDateEquals("");
		// findWhereSupplierNameEquals("");
		// findWhereFishSpeciesEquals("");
		// findWhereFishSizeEquals("");
		// findWhereFishWeightEquals("");
		// findWhereQuantityEquals(0);
		// findWhereRemarksEquals("");
		// findWhereIssuedByEquals("");
		// findWhereCheckedByEquals("");
		// findWhereReceivedByEquals("");
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
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findAll();
		for (Ws dto : _result) {
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
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereIdEquals(id);
		for (Ws dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereWsCodeEquals'
	 * 
	 * @param wsCode
	 * @throws Exception
	 */
	public static void findWhereWsCodeEquals(String wsCode) throws Exception
	{
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereWsCodeEquals(wsCode);
		for (Ws dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereWsDateEquals'
	 * 
	 * @param wsDate
	 * @throws Exception
	 */
	public static void findWhereWsDateEquals(String wsDate) throws Exception
	{
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereWsDateEquals(wsDate);
		for (Ws dto : _result) {
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
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereSupplierNameEquals(supplierName);
		for (Ws dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereFishSpeciesEquals'
	 * 
	 * @param fishSpecies
	 * @throws Exception
	 */
	public static void findWhereFishSpeciesEquals(String fishSpecies) throws Exception
	{
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereFishSpeciesEquals(fishSpecies);
		for (Ws dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereFishSizeEquals'
	 * 
	 * @param fishSize
	 * @throws Exception
	 */
	public static void findWhereFishSizeEquals(String fishSize) throws Exception
	{
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereFishSizeEquals(fishSize);
		for (Ws dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereFishWeightEquals'
	 * 
	 * @param fishWeight
	 * @throws Exception
	 */
	public static void findWhereFishWeightEquals(String fishWeight) throws Exception
	{
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereFishWeightEquals(fishWeight);
		for (Ws dto : _result) {
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
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereQuantityEquals(quantity);
		for (Ws dto : _result) {
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
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereRemarksEquals(remarks);
		for (Ws dto : _result) {
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
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereIssuedByEquals(issuedBy);
		for (Ws dto : _result) {
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
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereCheckedByEquals(checkedBy);
		for (Ws dto : _result) {
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
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereReceivedByEquals(receivedBy);
		for (Ws dto : _result) {
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
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereIsActiveEquals(isActive);
		for (Ws dto : _result) {
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
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (Ws dto : _result) {
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
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Ws dto : _result) {
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
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Ws dto : _result) {
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
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Ws dto : _result) {
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
		WsDao dao = DaoFactory.createWsDao();
		List<Ws> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Ws dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Ws dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getWsCode() );
		buf.append( ", " );
		buf.append( dto.getWsDate() );
		buf.append( ", " );
		buf.append( dto.getSupplierName() );
		buf.append( ", " );
		buf.append( dto.getFishSpecies() );
		buf.append( ", " );
		buf.append( dto.getFishSize() );
		buf.append( ", " );
		buf.append( dto.getFishWeight() );
		buf.append( ", " );
		buf.append( dto.getQuantity() );
		buf.append( ", " );
		buf.append( dto.getRemarks() );
		buf.append( ", " );
		buf.append( dto.getIssuedBy() );
		buf.append( ", " );
		buf.append( dto.getCheckedBy() );
		buf.append( ", " );
		buf.append( dto.getReceivedBy() );
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

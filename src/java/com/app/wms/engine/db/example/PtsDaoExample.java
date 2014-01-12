package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.PtsDao;
import com.app.wms.engine.db.dto.Pts;
import com.app.wms.engine.db.exceptions.PtsDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class PtsDaoExample
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
		// findWherePtsCodeEquals("");
		// findWherePtsDateEquals("");
		// findWherePackStyleSizeEquals("");
		// findWhereCanCodeEquals("");
		// findWhereForBrandEquals("");
		// findWhereReffEquals("");
		// findWhereNsDsEquals("");
		// findWhereProductBatchEquals("");
		// findWhereBasketEquals("");
		// findWhereQuantityEquals(0);
		// findWhereFlkPercentEquals(0);
		// findWhereNwEquals("");
		// findWhereDwEquals("");
		// findWherePwEquals("");
		// findWhereReleaseToEquals("");
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
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findAll();
		for (Pts dto : _result) {
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
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereIdEquals(id);
		for (Pts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePtsCodeEquals'
	 * 
	 * @param ptsCode
	 * @throws Exception
	 */
	public static void findWherePtsCodeEquals(String ptsCode) throws Exception
	{
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWherePtsCodeEquals(ptsCode);
		for (Pts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePtsDateEquals'
	 * 
	 * @param ptsDate
	 * @throws Exception
	 */
	public static void findWherePtsDateEquals(String ptsDate) throws Exception
	{
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWherePtsDateEquals(ptsDate);
		for (Pts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePackStyleSizeEquals'
	 * 
	 * @param packStyleSize
	 * @throws Exception
	 */
	public static void findWherePackStyleSizeEquals(String packStyleSize) throws Exception
	{
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWherePackStyleSizeEquals(packStyleSize);
		for (Pts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCanCodeEquals'
	 * 
	 * @param canCode
	 * @throws Exception
	 */
	public static void findWhereCanCodeEquals(String canCode) throws Exception
	{
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereCanCodeEquals(canCode);
		for (Pts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereForBrandEquals'
	 * 
	 * @param forBrand
	 * @throws Exception
	 */
	public static void findWhereForBrandEquals(String forBrand) throws Exception
	{
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereForBrandEquals(forBrand);
		for (Pts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereReffEquals'
	 * 
	 * @param reff
	 * @throws Exception
	 */
	public static void findWhereReffEquals(String reff) throws Exception
	{
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereReffEquals(reff);
		for (Pts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereNsDsEquals'
	 * 
	 * @param nsDs
	 * @throws Exception
	 */
	public static void findWhereNsDsEquals(String nsDs) throws Exception
	{
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereNsDsEquals(nsDs);
		for (Pts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductBatchEquals'
	 * 
	 * @param productBatch
	 * @throws Exception
	 */
	public static void findWhereProductBatchEquals(String productBatch) throws Exception
	{
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereProductBatchEquals(productBatch);
		for (Pts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBasketEquals'
	 * 
	 * @param basket
	 * @throws Exception
	 */
	public static void findWhereBasketEquals(String basket) throws Exception
	{
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereBasketEquals(basket);
		for (Pts dto : _result) {
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
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereQuantityEquals(quantity);
		for (Pts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereFlkPercentEquals'
	 * 
	 * @param flkPercent
	 * @throws Exception
	 */
	public static void findWhereFlkPercentEquals(int flkPercent) throws Exception
	{
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereFlkPercentEquals(flkPercent);
		for (Pts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereNwEquals'
	 * 
	 * @param nw
	 * @throws Exception
	 */
	public static void findWhereNwEquals(String nw) throws Exception
	{
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereNwEquals(nw);
		for (Pts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDwEquals'
	 * 
	 * @param dw
	 * @throws Exception
	 */
	public static void findWhereDwEquals(String dw) throws Exception
	{
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereDwEquals(dw);
		for (Pts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePwEquals'
	 * 
	 * @param pw
	 * @throws Exception
	 */
	public static void findWherePwEquals(String pw) throws Exception
	{
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWherePwEquals(pw);
		for (Pts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereReleaseToEquals'
	 * 
	 * @param releaseTo
	 * @throws Exception
	 */
	public static void findWhereReleaseToEquals(String releaseTo) throws Exception
	{
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereReleaseToEquals(releaseTo);
		for (Pts dto : _result) {
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
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereRemarksEquals(remarks);
		for (Pts dto : _result) {
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
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereIssuedByEquals(issuedBy);
		for (Pts dto : _result) {
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
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereCheckedByEquals(checkedBy);
		for (Pts dto : _result) {
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
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereReceivedByEquals(receivedBy);
		for (Pts dto : _result) {
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
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereIsActiveEquals(isActive);
		for (Pts dto : _result) {
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
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (Pts dto : _result) {
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
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Pts dto : _result) {
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
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Pts dto : _result) {
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
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Pts dto : _result) {
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
		PtsDao dao = DaoFactory.createPtsDao();
		List<Pts> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Pts dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Pts dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getPtsCode() );
		buf.append( ", " );
		buf.append( dto.getPtsDate() );
		buf.append( ", " );
		buf.append( dto.getPackStyleSize() );
		buf.append( ", " );
		buf.append( dto.getCanCode() );
		buf.append( ", " );
		buf.append( dto.getForBrand() );
		buf.append( ", " );
		buf.append( dto.getReff() );
		buf.append( ", " );
		buf.append( dto.getNsDs() );
		buf.append( ", " );
		buf.append( dto.getProductBatch() );
		buf.append( ", " );
		buf.append( dto.getBasket() );
		buf.append( ", " );
		buf.append( dto.getQuantity() );
		buf.append( ", " );
		buf.append( dto.getFlkPercent() );
		buf.append( ", " );
		buf.append( dto.getNw() );
		buf.append( ", " );
		buf.append( dto.getDw() );
		buf.append( ", " );
		buf.append( dto.getPw() );
		buf.append( ", " );
		buf.append( dto.getReleaseTo() );
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

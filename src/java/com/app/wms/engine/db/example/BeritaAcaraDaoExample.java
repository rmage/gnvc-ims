package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.BeritaAcaraDao;
import com.app.wms.engine.db.dto.BeritaAcara;
import com.app.wms.engine.db.exceptions.BeritaAcaraDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class BeritaAcaraDaoExample
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
		// findWhereBeritaNoEquals("");
		// findWhereBeritaDateEquals(null);
		// findWherePonumberEquals("");
		// findWhereTotalEquals(0);
		// findWhereContractorNameEquals("");
		// findWhereResultWorkEquals(0);
		// findWherePertialCompletionEquals(0);
		// findWhereRetentionEquals(0);
		// findWhereDescriptionEquals("");
		// findWhereQtyEquals(0);
		// findWhereUomNameEquals("");
		// findWhereProgressEquals(0);
		// findWhereTotalProgressEquals(0);
		// findWhereEndUserEquals("");
		// findWherePlantEngineerEquals("");
		// findWhereOperationEngineerEquals("");
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
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findAll();
		for (BeritaAcara dto : _result) {
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
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereIdEquals(id);
		for (BeritaAcara dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBeritaNoEquals'
	 * 
	 * @param beritaNo
	 * @throws Exception
	 */
	public static void findWhereBeritaNoEquals(String beritaNo) throws Exception
	{
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereBeritaNoEquals(beritaNo);
		for (BeritaAcara dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBeritaDateEquals'
	 * 
	 * @param beritaDate
	 * @throws Exception
	 */
	public static void findWhereBeritaDateEquals(Date beritaDate) throws Exception
	{
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereBeritaDateEquals(beritaDate);
		for (BeritaAcara dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePonumberEquals'
	 * 
	 * @param ponumber
	 * @throws Exception
	 */
	public static void findWherePonumberEquals(String ponumber) throws Exception
	{
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWherePonumberEquals(ponumber);
		for (BeritaAcara dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereTotalEquals'
	 * 
	 * @param total
	 * @throws Exception
	 */
	public static void findWhereTotalEquals(float total) throws Exception
	{
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereTotalEquals(total);
		for (BeritaAcara dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereContractorNameEquals'
	 * 
	 * @param contractorName
	 * @throws Exception
	 */
	public static void findWhereContractorNameEquals(String contractorName) throws Exception
	{
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereContractorNameEquals(contractorName);
		for (BeritaAcara dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereResultWorkEquals'
	 * 
	 * @param resultWork
	 * @throws Exception
	 */
	public static void findWhereResultWorkEquals(float resultWork) throws Exception
	{
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereResultWorkEquals(resultWork);
		for (BeritaAcara dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePertialCompletionEquals'
	 * 
	 * @param pertialCompletion
	 * @throws Exception
	 */
	public static void findWherePertialCompletionEquals(float pertialCompletion) throws Exception
	{
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWherePertialCompletionEquals(pertialCompletion);
		for (BeritaAcara dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereRetentionEquals'
	 * 
	 * @param retention
	 * @throws Exception
	 */
	public static void findWhereRetentionEquals(float retention) throws Exception
	{
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereRetentionEquals(retention);
		for (BeritaAcara dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereDescriptionEquals'
	 * 
	 * @param description
	 * @throws Exception
	 */
	public static void findWhereDescriptionEquals(String description) throws Exception
	{
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereDescriptionEquals(description);
		for (BeritaAcara dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereQtyEquals'
	 * 
	 * @param qty
	 * @throws Exception
	 */
	public static void findWhereQtyEquals(float qty) throws Exception
	{
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereQtyEquals(qty);
		for (BeritaAcara dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUomNameEquals'
	 * 
	 * @param uomName
	 * @throws Exception
	 */
	public static void findWhereUomNameEquals(String uomName) throws Exception
	{
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereUomNameEquals(uomName);
		for (BeritaAcara dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProgressEquals'
	 * 
	 * @param progress
	 * @throws Exception
	 */
	public static void findWhereProgressEquals(float progress) throws Exception
	{
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereProgressEquals(progress);
		for (BeritaAcara dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereTotalProgressEquals'
	 * 
	 * @param totalProgress
	 * @throws Exception
	 */
	public static void findWhereTotalProgressEquals(float totalProgress) throws Exception
	{
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereTotalProgressEquals(totalProgress);
		for (BeritaAcara dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereEndUserEquals'
	 * 
	 * @param endUser
	 * @throws Exception
	 */
	public static void findWhereEndUserEquals(String endUser) throws Exception
	{
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereEndUserEquals(endUser);
		for (BeritaAcara dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWherePlantEngineerEquals'
	 * 
	 * @param plantEngineer
	 * @throws Exception
	 */
	public static void findWherePlantEngineerEquals(String plantEngineer) throws Exception
	{
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWherePlantEngineerEquals(plantEngineer);
		for (BeritaAcara dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereOperationEngineerEquals'
	 * 
	 * @param operationEngineer
	 * @throws Exception
	 */
	public static void findWhereOperationEngineerEquals(String operationEngineer) throws Exception
	{
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereOperationEngineerEquals(operationEngineer);
		for (BeritaAcara dto : _result) {
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
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereIsActiveEquals(isActive);
		for (BeritaAcara dto : _result) {
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
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (BeritaAcara dto : _result) {
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
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereCreatedByEquals(createdBy);
		for (BeritaAcara dto : _result) {
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
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (BeritaAcara dto : _result) {
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
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (BeritaAcara dto : _result) {
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
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		List<BeritaAcara> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (BeritaAcara dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(BeritaAcara dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getBeritaNo() );
		buf.append( ", " );
		buf.append( dto.getBeritaDate() );
		buf.append( ", " );
		buf.append( dto.getPonumber() );
		buf.append( ", " );
		buf.append( dto.getTotal() );
		buf.append( ", " );
		buf.append( dto.getContractorName() );
		buf.append( ", " );
		buf.append( dto.getResultWork() );
		buf.append( ", " );
		buf.append( dto.getPertialCompletion() );
		buf.append( ", " );
		buf.append( dto.getRetention() );
		buf.append( ", " );
		buf.append( dto.getDescription() );
		buf.append( ", " );
		buf.append( dto.getQty() );
		buf.append( ", " );
		buf.append( dto.getUomName() );
		buf.append( ", " );
		buf.append( dto.getProgress() );
		buf.append( ", " );
		buf.append( dto.getTotalProgress() );
		buf.append( ", " );
		buf.append( dto.getEndUser() );
		buf.append( ", " );
		buf.append( dto.getPlantEngineer() );
		buf.append( ", " );
		buf.append( dto.getOperationEngineer() );
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

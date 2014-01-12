package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.PtsDao;
import com.app.wms.engine.db.dto.Pts;
import com.app.wms.engine.db.dto.PtsPk;
import com.app.wms.engine.db.exceptions.PtsDaoException;

import java.util.Date;
import java.util.List;

public interface PtsDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return PtsPk
	 */
	public PtsPk insert(Pts dto);

	/** 
	 * Updates a single row in the pts table.
	 */
	public void update(PtsPk pk, Pts dto) throws PtsDaoException;

	/** 
	 * Deletes a single row in the pts table.
	 */
	public void delete(PtsPk pk) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'id = :id'.
	 */
	public Pts findByPrimaryKey(int id) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria ''.
	 */
	public List<Pts> findAll() throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'id = :id'.
	 */
	public List<Pts> findWhereIdEquals(int id) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'pts_code = :ptsCode'.
	 */
	public List<Pts> findWherePtsCodeEquals(String ptsCode) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'pts_date = :ptsDate'.
	 */
	public List<Pts> findWherePtsDateEquals(String ptsDate) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'pack_style_size = :packStyleSize'.
	 */
	public List<Pts> findWherePackStyleSizeEquals(String packStyleSize) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'can_code = :canCode'.
	 */
	public List<Pts> findWhereCanCodeEquals(String canCode) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'for_brand = :forBrand'.
	 */
	public List<Pts> findWhereForBrandEquals(String forBrand) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'reff = :reff'.
	 */
	public List<Pts> findWhereReffEquals(String reff) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'ns_ds = :nsDs'.
	 */
	public List<Pts> findWhereNsDsEquals(String nsDs) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'product_batch = :productBatch'.
	 */
	public List<Pts> findWhereProductBatchEquals(String productBatch) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'basket = :basket'.
	 */
	public List<Pts> findWhereBasketEquals(String basket) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'quantity = :quantity'.
	 */
	public List<Pts> findWhereQuantityEquals(int quantity) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'flk_percent = :flkPercent'.
	 */
	public List<Pts> findWhereFlkPercentEquals(int flkPercent) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'nw = :nw'.
	 */
	public List<Pts> findWhereNwEquals(String nw) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'dw = :dw'.
	 */
	public List<Pts> findWhereDwEquals(String dw) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'pw = :pw'.
	 */
	public List<Pts> findWherePwEquals(String pw) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'release_to = :releaseTo'.
	 */
	public List<Pts> findWhereReleaseToEquals(String releaseTo) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'remarks = :remarks'.
	 */
	public List<Pts> findWhereRemarksEquals(String remarks) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'issued_by = :issuedBy'.
	 */
	public List<Pts> findWhereIssuedByEquals(String issuedBy) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'checked_by = :checkedBy'.
	 */
	public List<Pts> findWhereCheckedByEquals(String checkedBy) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'received_by = :receivedBy'.
	 */
	public List<Pts> findWhereReceivedByEquals(String receivedBy) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'is_active = :isActive'.
	 */
	public List<Pts> findWhereIsActiveEquals(String isActive) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<Pts> findWhereIsDeleteEquals(String isDelete) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Pts> findWhereCreatedByEquals(String createdBy) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Pts> findWhereCreatedDateEquals(Date createdDate) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Pts> findWhereUpdatedByEquals(String updatedBy) throws PtsDaoException;

	/** 
	 * Returns all rows from the pts table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Pts> findWhereUpdatedDateEquals(Date updatedDate) throws PtsDaoException;

	/** 
	 * Returns the rows from the pts table that matches the specified primary-key value.
	 */
	public Pts findByPrimaryKey(PtsPk pk) throws PtsDaoException;
	
	public List<Pts> findPtsPaging(Pts p,int page) throws PtsDaoException;

}

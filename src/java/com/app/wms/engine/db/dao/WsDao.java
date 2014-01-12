package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.WsDao;
import com.app.wms.engine.db.dto.Pts;
import com.app.wms.engine.db.dto.Ws;
import com.app.wms.engine.db.dto.WsPk;
import com.app.wms.engine.db.exceptions.PtsDaoException;
import com.app.wms.engine.db.exceptions.WsDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface WsDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return WsPk
	 */
	public WsPk insert(Ws dto);

	/** 
	 * Updates a single row in the ws table.
	 */
	public void update(WsPk pk, Ws dto) throws WsDaoException;

	/** 
	 * Deletes a single row in the ws table.
	 */
	public void delete(WsPk pk) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'id = :id'.
	 */
	public Ws findByPrimaryKey(int id) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria ''.
	 */
	public List<Ws> findAll() throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'id = :id'.
	 */
	public List<Ws> findWhereIdEquals(int id) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'ws_code = :wsCode'.
	 */
	public List<Ws> findWhereWsCodeEquals(String wsCode) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'ws_date = :wsDate'.
	 */
	public List<Ws> findWhereWsDateEquals(String wsDate) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'supplier_name = :supplierName'.
	 */
	public List<Ws> findWhereSupplierNameEquals(String supplierName) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'fish_species = :fishSpecies'.
	 */
	public List<Ws> findWhereFishSpeciesEquals(String fishSpecies) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'fish_size = :fishSize'.
	 */
	public List<Ws> findWhereFishSizeEquals(String fishSize) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'fish_weight = :fishWeight'.
	 */
	public List<Ws> findWhereFishWeightEquals(String fishWeight) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'quantity = :quantity'.
	 */
	public List<Ws> findWhereQuantityEquals(int quantity) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'remarks = :remarks'.
	 */
	public List<Ws> findWhereRemarksEquals(String remarks) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'issued_by = :issuedBy'.
	 */
	public List<Ws> findWhereIssuedByEquals(String issuedBy) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'checked_by = :checkedBy'.
	 */
	public List<Ws> findWhereCheckedByEquals(String checkedBy) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'received_by = :receivedBy'.
	 */
	public List<Ws> findWhereReceivedByEquals(String receivedBy) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'is_active = :isActive'.
	 */
	public List<Ws> findWhereIsActiveEquals(String isActive) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<Ws> findWhereIsDeleteEquals(String isDelete) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Ws> findWhereCreatedByEquals(String createdBy) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Ws> findWhereCreatedDateEquals(Date createdDate) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Ws> findWhereUpdatedByEquals(String updatedBy) throws WsDaoException;

	/** 
	 * Returns all rows from the ws table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Ws> findWhereUpdatedDateEquals(Date updatedDate) throws WsDaoException;

	/** 
	 * Returns the rows from the ws table that matches the specified primary-key value.
	 */
	public Ws findByPrimaryKey(WsPk pk) throws WsDaoException;
	
	public List<Ws> findWsPaging(Ws p,int page) throws WsDaoException;

}

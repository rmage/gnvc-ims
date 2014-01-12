package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.EdsDao;
import com.app.wms.engine.db.dto.Eds;
import com.app.wms.engine.db.dto.EdsPk;
import com.app.wms.engine.db.exceptions.EdsDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface EdsDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return EdsPk
	 */
	public EdsPk insert(Eds dto);

	/** 
	 * Updates a single row in the eds table.
	 */
	public void update(EdsPk pk, Eds dto) throws EdsDaoException;

	/** 
	 * Deletes a single row in the eds table.
	 */
	public void delete(EdsPk pk) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'id = :id'.
	 */
	public Eds findByPrimaryKey(int id) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria ''.
	 */
	public List<Eds> findAll() throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'id = :id'.
	 */
	public List<Eds> findWhereIdEquals(int id) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'edsnumber = :edsnumber'.
	 */
	public List<Eds> findWhereEdsnumberEquals(String edsnumber) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'edsdate = :edsdate'.
	 */
	public List<Eds> findWhereEdsdateEquals(Date edsdate) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'cinumber = :cinumber'.
	 */
	public List<Eds> findWhereCinumberEquals(String cinumber) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'buyerName = :buyerName'.
	 */
	public List<Eds> findWhereBuyerNameEquals(String buyerName) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'brand = :brand'.
	 */
	public List<Eds> findWhereBrandEquals(String brand) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'referensi = :referensi'.
	 */
	public List<Eds> findWhereReferensiEquals(String referensi) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'destination = :destination'.
	 */
	public List<Eds> findWhereDestinationEquals(String destination) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'van = :van'.
	 */
	public List<Eds> findWhereVanEquals(String van) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'sealShip = :sealShip'.
	 */
	public List<Eds> findWhereSealShipEquals(String sealShip) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'vessel = :vessel'.
	 */
	public List<Eds> findWhereVesselEquals(String vessel) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'platNo = :platNo'.
	 */
	public List<Eds> findWherePlatNoEquals(String platNo) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'time_in = :timeIn'.
	 */
	public List<Eds> findWhereTimeInEquals(Date timeIn) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'time_out = :timeOut'.
	 */
	public List<Eds> findWhereTimeOutEquals(Date timeOut) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'driverName = :driverName'.
	 */
	public List<Eds> findWhereDriverNameEquals(String driverName) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'issuedBy = :issuedBy'.
	 */
	public List<Eds> findWhereIssuedByEquals(String issuedBy) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'checkedBy = :checkedBy'.
	 */
	public List<Eds> findWhereCheckedByEquals(String checkedBy) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'approvedBy = :approvedBy'.
	 */
	public List<Eds> findWhereApprovedByEquals(String approvedBy) throws EdsDaoException;

	/** 
	 * Returns all rows from the eds table that match the criteria 'remarks = :remarks'.
	 */
	public List<Eds> findWhereRemarksEquals(String remarks) throws EdsDaoException;

	/** 
	 * Returns the rows from the eds table that matches the specified primary-key value.
	 */
	public Eds findByPrimaryKey(EdsPk pk) throws EdsDaoException;

	public List<Eds> findEdsPaging(Eds t, Integer page)throws EdsDaoException;

}

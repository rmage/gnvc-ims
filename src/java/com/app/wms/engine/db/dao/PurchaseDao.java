package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.web.engine.search.WarehouseSearch;
import com.app.wms.engine.db.dto.Purchase;
import com.app.wms.engine.db.dto.PurchasePk;
import com.app.wms.engine.db.exceptions.PurchaseDaoException;

public interface PurchaseDao 
{

	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return PurchasePk
	 */
	public PurchasePk insert(Purchase dto);

	/** 
	 * Updates a single row in the Purchase table.
	 */
	public void update(PurchasePk pk, Purchase dto) throws PurchaseDaoException;

	/** 
	 * Deletes a single row in the Purchase table.
	 */
	public void delete(PurchasePk pk) throws PurchaseDaoException;

	/** 
	 * Returns all rows from the Purchase table that match the criteria 'Purchase_CODE = :PurchaseCode'.
	 */
	public Purchase findByPrimaryKey(String PurchaseCode) throws PurchaseDaoException;

	/** 
	 * Returns all rows from the Purchase table that match the criteria ''.
	 */
	public List<Purchase> findAll() throws PurchaseDaoException;

	/** 
	 * Returns all rows from the Purchase table that match the criteria 'Purchase_CODE = :PurchaseCode'.
	 */
	public List<Purchase> findPurchaseerePurchaseCodeEquals(String PurchaseCode) throws PurchaseDaoException;

	/** 
	 * Returns all rows from the Purchase table that match the criteria 'NAME = :name'.
	 */
	public List<Purchase> findPurchaseereNameEquals(String name) throws PurchaseDaoException;

	/** 
	 * Returns the rows from the Purchase table that matches the specified primary-key value.
	 */
	public Purchase findByPrimaryKey(PurchasePk pk) throws PurchaseDaoException;

	public List<Purchase> findByCriteriaLimit(WarehouseSearch criteria, int start, int end) throws PurchaseDaoException;



}

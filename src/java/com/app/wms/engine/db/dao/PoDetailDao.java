package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.PoDetailDao;
import com.app.wms.engine.db.dto.PoDetail;
import com.app.wms.engine.db.dto.PoDetailPk;
import com.app.wms.engine.db.exceptions.PoDetailDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface PoDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return PoDetailPk
	 */
	public PoDetailPk insert(PoDetail dto);

	/** 
	 * Updates a single row in the po_detail table.
	 */
	public void update(PoDetailPk pk, PoDetail dto) throws PoDetailDaoException;

	/** 
	 * Deletes a single row in the po_detail table.
	 */
	public void delete(PoDetailPk pk) throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'id = :id'.
	 */
	public PoDetail findByPrimaryKey(int id) throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria ''.
	 */
	public List<PoDetail> findAll() throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'id = :id'.
	 */
	public List<PoDetail> findWhereIdEquals(int id) throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'ponumber = :ponumber'.
	 */
	public List<PoDetail> findWherePonumberEquals(String ponumber) throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'productcode = :productcode'.
	 */
	public List<PoDetail> findWhereProductcodeEquals(String productcode) throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'qty = :qty'.
	 */
	public List<PoDetail> findWhereQtyEquals(float qty) throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'producttype = :producttype'.
	 */
	public List<PoDetail> findWhereProducttypeEquals(String producttype) throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'unitprice = :unitprice'.
	 */
	public List<PoDetail> findWhereUnitpriceEquals(float unitprice) throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'amount = :amount'.
	 */
	public List<PoDetail> findWhereAmountEquals(float amount) throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'ppn = :ppn'.
	 */
	public List<PoDetail> findWherePpnEquals(float ppn) throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'poremarks = :poremarks'.
	 */
	public List<PoDetail> findWherePoremarksEquals(String poremarks) throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'currencyCode = :currencyCode'.
	 */
	public List<PoDetail> findWhereCurrencyCodeEquals(String currencyCode) throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'warranty = :warranty'.
	 */
	public List<PoDetail> findWhereWarrantyEquals(Date warranty) throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'termpayment = :termpayment'.
	 */
	public List<PoDetail> findWhereTermpaymentEquals(String termpayment) throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'termdelivery = :termdelivery'.
	 */
	public List<PoDetail> findWhereTermdeliveryEquals(String termdelivery) throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'discount = :discount'.
	 */
	public List<PoDetail> findWhereDiscountEquals(float discount) throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'pph = :pph'.
	 */
	public List<PoDetail> findWherePphEquals(float pph) throws PoDetailDaoException;

	/** 
	 * Returns all rows from the po_detail table that match the criteria 'total = :total'.
	 */
	public List<PoDetail> findWhereTotalEquals(float total) throws PoDetailDaoException;

	/** 
	 * Returns the rows from the po_detail table that matches the specified primary-key value.
	 */
	public PoDetail findByPrimaryKey(PoDetailPk pk) throws PoDetailDaoException;

	public List<PoDetail> findPoDetailPaging(PoDetail pd, Integer page)throws PoDetailDaoException;
	
	public void update(PoDetail dto) throws PoDetailDaoException;
	
}

package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.CanvassingDetailDao;
import com.app.wms.engine.db.dto.CanvassingDetail;
import com.app.wms.engine.db.dto.CanvassingDetailPk;
import com.app.wms.engine.db.exceptions.CanvassingDetailDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface CanvassingDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return CanvassingDetailPk
	 */
	public CanvassingDetailPk insert(CanvassingDetail dto);

	/** 
	 * Updates a single row in the canvassing_detail table.
	 */
	public void update(CanvassingDetailPk pk, CanvassingDetail dto) throws CanvassingDetailDaoException;

	/** 
	 * Deletes a single row in the canvassing_detail table.
	 */
	public void delete(CanvassingDetailPk pk) throws CanvassingDetailDaoException;

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'id = :id'.
	 */
	public CanvassingDetail findByPrimaryKey(int id) throws CanvassingDetailDaoException;

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria ''.
	 */
	public List<CanvassingDetail> findAll() throws CanvassingDetailDaoException;

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'id = :id'.
	 */
	public List<CanvassingDetail> findWhereIdEquals(int id) throws CanvassingDetailDaoException;

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'prsnumber = :prsnumber'.
	 */
	public List<CanvassingDetail> findWherePrsnumberEquals(String prsnumber) throws CanvassingDetailDaoException;

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'supplier_code = :supplierCode'.
	 */
	public List<CanvassingDetail> findWhereSupplierCodeEquals(String supplierCode) throws CanvassingDetailDaoException;

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'productcode = :productcode'.
	 */
	public List<CanvassingDetail> findWhereProductcodeEquals(String productcode) throws CanvassingDetailDaoException;

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'productname = :productname'.
	 */
	public List<CanvassingDetail> findWhereProductnameEquals(String productname) throws CanvassingDetailDaoException;

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'priceunit = :priceunit'.
	 */
	public List<CanvassingDetail> findWherePriceunitEquals(float priceunit) throws CanvassingDetailDaoException;

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'warranty = :warranty'.
	 */
	public List<CanvassingDetail> findWhereWarrantyEquals(Date warranty) throws CanvassingDetailDaoException;

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'termpayment = :termpayment'.
	 */
	public List<CanvassingDetail> findWhereTermpaymentEquals(String termpayment) throws CanvassingDetailDaoException;

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'termdelivery = :termdelivery'.
	 */
	public List<CanvassingDetail> findWhereTermdeliveryEquals(String termdelivery) throws CanvassingDetailDaoException;

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'discount = :discount'.
	 */
	public List<CanvassingDetail> findWhereDiscountEquals(float discount) throws CanvassingDetailDaoException;

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'pph = :pph'.
	 */
	public List<CanvassingDetail> findWherePphEquals(float pph) throws CanvassingDetailDaoException;

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'ppn = :ppn'.
	 */
	public List<CanvassingDetail> findWherePpnEquals(float ppn) throws CanvassingDetailDaoException;

	/** 
	 * Returns all rows from the canvassing_detail table that match the criteria 'is_selected = :isSelected'.
	 */
	public List<CanvassingDetail> findWhereIsSelectedEquals(String isSelected) throws CanvassingDetailDaoException;

	/** 
	 * Returns the rows from the canvassing_detail table that matches the specified primary-key value.
	 */
	public CanvassingDetail findByPrimaryKey(CanvassingDetailPk pk) throws CanvassingDetailDaoException;
	
    public List<CanvassingDetail> findAllList() throws CanvassingDetailDaoException;
	
	public CanvassingDetail findWherePrsnumber(String prsnumber) throws CanvassingDetailDaoException;
	
	public List<CanvassingDetail> findWhereCanvassingDetail(int id, String prsnumber, String productcode) throws CanvassingDetailDaoException;

	public void updateDto(CanvassingDetail dto) throws CanvassingDetailDaoException;

}

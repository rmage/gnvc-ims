package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.PrsDetailDao;
import com.app.wms.engine.db.dto.PrsDetail;
import com.app.wms.engine.db.dto.PrsDetailPk;
import com.app.wms.engine.db.exceptions.PrsDetailDaoException;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface PrsDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return PrsDetailPk
	 */
	public PrsDetailPk insert(PrsDetail dto);

	/** 
	 * Updates a single row in the prs_detail table.
	 */
	public void update(PrsDetailPk pk, PrsDetail dto) throws PrsDetailDaoException;

	/** 
	 * Deletes a single row in the prs_detail table.
	 */
	public void delete(PrsDetailPk pk) throws PrsDetailDaoException;

	/** 
	 * Returns all rows from the prs_detail table that match the criteria 'id = :id'.
	 */
	public PrsDetail findByPrimaryKey(int id) throws PrsDetailDaoException;

	/** 
	 * Returns all rows from the prs_detail table that match the criteria ''.
	 */
	public List<PrsDetail> findAll() throws PrsDetailDaoException;

	/** 
	 * Returns all rows from the prs_detail table that match the criteria 'id = :id'.
	 */
	public List<PrsDetail> findWhereIdEquals(int id) throws PrsDetailDaoException;

	/** 
	 * Returns all rows from the prs_detail table that match the criteria 'prsnumber = :prsnumber'.
	 */
	public List<PrsDetail> findWherePrsnumberEquals(String prsnumber) throws PrsDetailDaoException;

	/** 
	 * Returns all rows from the prs_detail table that match the criteria 'productcode = :productcode'.
	 */
	public List<PrsDetail> findWhereProductcodeEquals(String productcode) throws PrsDetailDaoException;

	/** 
	 * Returns all rows from the prs_detail table that match the criteria 'productname = :productname'.
	 */
	public List<PrsDetail> findWhereProductnameEquals(String productname) throws PrsDetailDaoException;

	/** 
	 * Returns all rows from the prs_detail table that match the criteria 'qty = :qty'.
	 */
	public List<PrsDetail> findWhereQtyEquals(int qty) throws PrsDetailDaoException;

	/** 
	 * Returns all rows from the prs_detail table that match the criteria 'uom_name = :uomName'.
	 */
	public List<PrsDetail> findWhereUomNameEquals(String uomName) throws PrsDetailDaoException;

	/** 
	 * Returns the rows from the prs_detail table that matches the specified primary-key value.
	 */
	public PrsDetail findByPrimaryKey(PrsDetailPk pk) throws PrsDetailDaoException;
	
	public List<PrsDetail> findWherePrsnumberEqualsDetail(String prsnumber) throws PrsDetailDaoException;
	
	public List<PrsDetail> findWherePrsnumberCanvassingSelected(String prsnumber, String suppliercode) throws PrsDetailDaoException;
        
        /* FYA : 07 January 2014 */
        public PrsDetail findByPrsProduct(String prsNumber, String productCode);
        
        // 2015 Update | by FYA
        public List<PrsDetail> findUnassignedSupplier(String userId);

}

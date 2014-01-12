package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.ConsolidateDetailDao;
import com.app.wms.engine.db.dto.ConsolidateDetail;
import com.app.wms.engine.db.dto.SalesOrderDetail;
import com.app.wms.engine.db.exceptions.ConsolidateDetailDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDetailDaoException;

import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface ConsolidateDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(ConsolidateDetail dto);

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria ''.
	 */
	public List<ConsolidateDetail> findAll() throws ConsolidateDetailDaoException;

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'consolidate_no = :consolidateNo'.
	 */
	public List<ConsolidateDetail> findWhereConsolidateNoEquals(String consolidateNo) throws ConsolidateDetailDaoException;

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'packing_no = :packingNo'.
	 */
	public List<ConsolidateDetail> findWherePackingNoEquals(String packingNo) throws ConsolidateDetailDaoException;

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'product_id = :productId'.
	 */
	public List<ConsolidateDetail> findWhereProductIdEquals(String productId) throws ConsolidateDetailDaoException;

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'product_code = :productCode'.
	 */
	public List<ConsolidateDetail> findWhereProductCodeEquals(String productCode) throws ConsolidateDetailDaoException;

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'product_name = :productName'.
	 */
	public List<ConsolidateDetail> findWhereProductNameEquals(String productName) throws ConsolidateDetailDaoException;

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'unit_code = :unitCode'.
	 */
	public List<ConsolidateDetail> findWhereUnitCodeEquals(String unitCode) throws ConsolidateDetailDaoException;

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'qty_consolidate = :qtyConsolidate'.
	 */
	public List<ConsolidateDetail> findWhereQtyConsolidateEquals(int qtyConsolidate) throws ConsolidateDetailDaoException;

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'user_id = :userId'.
	 */
	public List<ConsolidateDetail> findWhereUserIdEquals(String userId) throws ConsolidateDetailDaoException;

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'corp_id = :corpId'.
	 */
	public List<ConsolidateDetail> findWhereCorpIdEquals(String corpId) throws ConsolidateDetailDaoException;

	/** 
	 * Returns all rows from the consolidate_detail table that match the criteria 'wh_code = :whCode'.
	 */
	public List<ConsolidateDetail> findWhereWhCodeEquals(String whCode) throws ConsolidateDetailDaoException;

	public ConsolidateDetail findByPrimaryKey(String ConsolidateNumber) throws ConsolidateDetailDaoException;
	
	public List<ConsolidateDetail> findDetail(ConsolidateDetail cod) throws ConsolidateDetailDaoException;

}

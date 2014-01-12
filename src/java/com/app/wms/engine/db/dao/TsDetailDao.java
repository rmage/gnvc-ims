package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.TsDetailDao;
import com.app.wms.engine.db.dto.TsDetail;
import com.app.wms.engine.db.dto.TsDetailPk;
import com.app.wms.engine.db.exceptions.TsDetailDaoException;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface TsDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return TsDetailPk
	 */
	public TsDetailPk insert(TsDetail dto);

	/** 
	 * Updates a single row in the ts_detail table.
	 */
	public void update(TsDetailPk pk, TsDetail dto) throws TsDetailDaoException;

	/** 
	 * Deletes a single row in the ts_detail table.
	 */
	public void delete(TsDetailPk pk) throws TsDetailDaoException;

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'id = :id'.
	 */
	public TsDetail findByPrimaryKey(int id) throws TsDetailDaoException;

	/** 
	 * Returns all rows from the ts_detail table that match the criteria ''.
	 */
	public List<TsDetail> findAll() throws TsDetailDaoException;

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'id = :id'.
	 */
	public List<TsDetail> findWhereIdEquals(int id) throws TsDetailDaoException;

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'tsnumber = :tsnumber'.
	 */
	public List<TsDetail> findWhereTsnumberEquals(String tsnumber) throws TsDetailDaoException;

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'productcode = :productcode'.
	 */
	public List<TsDetail> findWhereProductcodeEquals(String productcode) throws TsDetailDaoException;

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'product_name = :productName'.
	 */
	public List<TsDetail> findWhereProductNameEquals(String productName) throws TsDetailDaoException;

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'product_category = :productCategory'.
	 */
	public List<TsDetail> findWhereProductCategoryEquals(String productCategory) throws TsDetailDaoException;

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'uom_name = :uomName'.
	 */
	public List<TsDetail> findWhereUomNameEquals(String uomName) throws TsDetailDaoException;

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'wh_code = :whCode'.
	 */
	public List<TsDetail> findWhereWhCodeEquals(String whCode) throws TsDetailDaoException;

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'balance = :balance'.
	 */
	public List<TsDetail> findWhereBalanceEquals(float balance) throws TsDetailDaoException;

	/** 
	 * Returns all rows from the ts_detail table that match the criteria 'qtyrequest = :qtyrequest'.
	 */
	public List<TsDetail> findWhereQtyrequestEquals(float qtyrequest) throws TsDetailDaoException;

	/** 
	 * Returns the rows from the ts_detail table that matches the specified primary-key value.
	 */
	public TsDetail findByPrimaryKey(TsDetailPk pk) throws TsDetailDaoException;

}

package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.BorDetailDao;
import com.app.wms.engine.db.dto.BorDetail;
import com.app.wms.engine.db.dto.BorDetailPk;
import com.app.wms.engine.db.exceptions.BorDetailDaoException;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface BorDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return BorDetailPk
	 */
	public BorDetailPk insert(BorDetail dto);

	/** 
	 * Updates a single row in the bor_detail table.
	 */
	public void update(BorDetailPk pk, BorDetail dto) throws BorDetailDaoException;

	/** 
	 * Deletes a single row in the bor_detail table.
	 */
	public void delete(BorDetailPk pk) throws BorDetailDaoException;

	/** 
	 * Returns all rows from the bor_detail table that match the criteria 'id = :id'.
	 */
	public BorDetail findByPrimaryKey(int id) throws BorDetailDaoException;

	/** 
	 * Returns all rows from the bor_detail table that match the criteria ''.
	 */
	public List<BorDetail> findAll() throws BorDetailDaoException;

	/** 
	 * Returns all rows from the bor_detail table that match the criteria 'id = :id'.
	 */
	public List<BorDetail> findWhereIdEquals(int id) throws BorDetailDaoException;

	/** 
	 * Returns all rows from the bor_detail table that match the criteria 'bornumber = :bornumber'.
	 */
	public List<BorDetail> findWhereBornumberEquals(String bornumber) throws BorDetailDaoException;

	/** 
	 * Returns all rows from the bor_detail table that match the criteria 'productcode = :productcode'.
	 */
	public List<BorDetail> findWhereProductcodeEquals(String productcode) throws BorDetailDaoException;

	/** 
	 * Returns all rows from the bor_detail table that match the criteria 'qty = :qty'.
	 */
	public List<BorDetail> findWhereQtyEquals(float qty) throws BorDetailDaoException;

	/** 
	 * Returns all rows from the bor_detail table that match the criteria 'producttype = :producttype'.
	 */
	public List<BorDetail> findWhereProducttypeEquals(String producttype) throws BorDetailDaoException;

	/** 
	 * Returns the rows from the bor_detail table that matches the specified primary-key value.
	 */
	public BorDetail findByPrimaryKey(BorDetailPk pk) throws BorDetailDaoException;

}

package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.SwsDetailDao;
import com.app.wms.engine.db.dto.SwsDetail;
import com.app.wms.engine.db.dto.SwsDetailPk;
import com.app.wms.engine.db.exceptions.SwsDetailDaoException;

import java.util.Collection;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface SwsDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return SwsDetailPk
	 */
	public SwsDetailPk insert(SwsDetail dto);

	/** 
	 * Updates a single row in the sws_detail table.
	 */
	public void update(SwsDetailPk pk, SwsDetail dto) throws SwsDetailDaoException;

	/** 
	 * Deletes a single row in the sws_detail table.
	 */
	public void delete(SwsDetailPk pk) throws SwsDetailDaoException;

	/** 
	 * Returns all rows from the sws_detail table that match the criteria 'id = :id'.
	 */
	public SwsDetail findByPrimaryKey(int id) throws SwsDetailDaoException;

	/** 
	 * Returns all rows from the sws_detail table that match the criteria ''.
	 */
	public List<SwsDetail> findAll() throws SwsDetailDaoException;

	/** 
	 * Returns all rows from the sws_detail table that match the criteria 'id = :id'.
	 */
	public List<SwsDetail> findWhereIdEquals(int id) throws SwsDetailDaoException;

	/** 
	 * Returns all rows from the sws_detail table that match the criteria 'swsnumber = :swsnumber'.
	 */
	public List<SwsDetail> findWhereSwsnumberEquals(String swsnumber) throws SwsDetailDaoException;

	/** 
	 * Returns all rows from the sws_detail table that match the criteria 'productcode = :productcode'.
	 */
	public List<SwsDetail> findWhereProductcodeEquals(String productcode) throws SwsDetailDaoException;

	/** 
	 * Returns all rows from the sws_detail table that match the criteria 'qty = :qty'.
	 */
	public List<SwsDetail> findWhereQtyEquals(int qty) throws SwsDetailDaoException;

	/** 
	 * Returns all rows from the sws_detail table that match the criteria 'producttype = :producttype'.
	 */
	public List<SwsDetail> findWhereProducttypeEquals(String producttype) throws SwsDetailDaoException;

	/** 
	 * Returns the rows from the sws_detail table that matches the specified primary-key value.
	 */
	public SwsDetail findByPrimaryKey(SwsDetailPk pk) throws SwsDetailDaoException;

	public List<SwsDetail> findWhereSwsnumberDetail(String param) throws SwsDetailDaoException;

}

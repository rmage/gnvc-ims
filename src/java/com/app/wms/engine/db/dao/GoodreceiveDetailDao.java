package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.GoodreceiveDetailDao;
import com.app.wms.engine.db.dto.GoodreceiveDetail;
import com.app.wms.engine.db.dto.GoodreceiveDetailPk;
import com.app.wms.engine.db.exceptions.GoodreceiveDetailDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface GoodreceiveDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return GoodreceiveDetailPk
	 */
	public GoodreceiveDetailPk insert(GoodreceiveDetail dto);

	/** 
	 * Updates a single row in the goodreceive_detail table.
	 */
	public void update(GoodreceiveDetailPk pk, GoodreceiveDetail dto) throws GoodreceiveDetailDaoException;

	/** 
	 * Deletes a single row in the goodreceive_detail table.
	 */
	public void delete(GoodreceiveDetailPk pk) throws GoodreceiveDetailDaoException;

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'id = :id'.
	 */
	public GoodreceiveDetail findByPrimaryKey(int id) throws GoodreceiveDetailDaoException;

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria ''.
	 */
	public List<GoodreceiveDetail> findAll() throws GoodreceiveDetailDaoException;

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'id = :id'.
	 */
	public List<GoodreceiveDetail> findWhereIdEquals(int id) throws GoodreceiveDetailDaoException;

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'grnumber = :grnumber'.
	 */
	public List<GoodreceiveDetail> findWhereGrnumberEquals(String grnumber) throws GoodreceiveDetailDaoException;

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'productcode = :productcode'.
	 */
	public List<GoodreceiveDetail> findWhereProductcodeEquals(String productcode) throws GoodreceiveDetailDaoException;

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'qtyreal = :qtyreal'.
	 */
	public List<GoodreceiveDetail> findWhereQtyrealEquals(float qtyreal) throws GoodreceiveDetailDaoException;

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'status = :status'.
	 */
	public List<GoodreceiveDetail> findWhereStatusEquals(String status) throws GoodreceiveDetailDaoException;

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'expirydate = :expirydate'.
	 */
	public List<GoodreceiveDetail> findWhereExpirydateEquals(Date expirydate) throws GoodreceiveDetailDaoException;

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'remark = :remark'.
	 */
	public List<GoodreceiveDetail> findWhereRemarkEquals(String remark) throws GoodreceiveDetailDaoException;

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'lotid = :lotid'.
	 */
	public List<GoodreceiveDetail> findWhereLotidEquals(int lotid) throws GoodreceiveDetailDaoException;

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'qtygood = :qtygood'.
	 */
	public List<GoodreceiveDetail> findWhereQtygoodEquals(float qtygood) throws GoodreceiveDetailDaoException;

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'qtydmg = :qtydmg'.
	 */
	public List<GoodreceiveDetail> findWhereQtydmgEquals(float qtydmg) throws GoodreceiveDetailDaoException;

	/** 
	 * Returns all rows from the goodreceive_detail table that match the criteria 'producttype = :producttype'.
	 */
	public List<GoodreceiveDetail> findWhereProducttypeEquals(String producttype) throws GoodreceiveDetailDaoException;

	/** 
	 * Returns the rows from the goodreceive_detail table that matches the specified primary-key value.
	 */
	public GoodreceiveDetail findByPrimaryKey(GoodreceiveDetailPk pk) throws GoodreceiveDetailDaoException;

}

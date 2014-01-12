package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.DrDetailDao;
import com.app.wms.engine.db.dto.DrDetail;
import com.app.wms.engine.db.dto.DrDetailPk;
import com.app.wms.engine.db.exceptions.DrDetailDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface DrDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return DrDetailPk
	 */
	public DrDetailPk insert(DrDetail dto);

	/** 
	 * Updates a single row in the dr_detail table.
	 */
	public void update(DrDetailPk pk, DrDetail dto) throws DrDetailDaoException;

	/** 
	 * Deletes a single row in the dr_detail table.
	 */
	public void delete(DrDetailPk pk) throws DrDetailDaoException;

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'id = :id'.
	 */
	public DrDetail findByPrimaryKey(int id) throws DrDetailDaoException;

	/** 
	 * Returns all rows from the dr_detail table that match the criteria ''.
	 */
	public List<DrDetail> findAll() throws DrDetailDaoException;

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'id = :id'.
	 */
	public List<DrDetail> findWhereIdEquals(int id) throws DrDetailDaoException;

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'drnumber = :drnumber'.
	 */
	public List<DrDetail> findWhereDrnumberEquals(String drnumber) throws DrDetailDaoException;

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'productcode = :productcode'.
	 */
	public List<DrDetail> findWhereProductcodeEquals(String productcode) throws DrDetailDaoException;

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'qtyreal = :qtyreal'.
	 */
	public List<DrDetail> findWhereQtyrealEquals(float qtyreal) throws DrDetailDaoException;

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'status = :status'.
	 */
	public List<DrDetail> findWhereStatusEquals(String status) throws DrDetailDaoException;

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'expirydate = :expirydate'.
	 */
	public List<DrDetail> findWhereExpirydateEquals(Date expirydate) throws DrDetailDaoException;

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'remarks = :remarks'.
	 */
	public List<DrDetail> findWhereRemarksEquals(String remarks) throws DrDetailDaoException;

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'qtygood = :qtygood'.
	 */
	public List<DrDetail> findWhereQtygoodEquals(float qtygood) throws DrDetailDaoException;

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'qtydmg = :qtydmg'.
	 */
	public List<DrDetail> findWhereQtydmgEquals(float qtydmg) throws DrDetailDaoException;

	/** 
	 * Returns all rows from the dr_detail table that match the criteria 'producttype = :producttype'.
	 */
	public List<DrDetail> findWhereProducttypeEquals(String producttype) throws DrDetailDaoException;

	/** 
	 * Returns the rows from the dr_detail table that matches the specified primary-key value.
	 */
	public DrDetail findByPrimaryKey(DrDetailPk pk) throws DrDetailDaoException;

}

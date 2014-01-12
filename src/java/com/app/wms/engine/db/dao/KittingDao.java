package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.KittingDao;
import com.app.wms.engine.db.dto.Kitting;
import com.app.wms.engine.db.dto.KittingDetail;
import com.app.wms.engine.db.dto.KittingPk;
import com.app.wms.engine.db.exceptions.KittingDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface KittingDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return KittingPk
	 */
	public KittingPk insert(Kitting dto);

	/** 
	 * Updates a single row in the kitting table.
	 */
	public void update(KittingPk pk, Kitting dto) throws KittingDaoException;

	/** 
	 * Deletes a single row in the kitting table.
	 */
	public void delete(KittingPk pk) throws KittingDaoException;

	/** 
	 * Returns all rows from the kitting table that match the criteria 'kitting_no = :kittingNo'.
	 */
	public Kitting findByPrimaryKey(String kittingNo) throws KittingDaoException;

	/** 
	 * Returns all rows from the kitting table that match the criteria ''.
	 */
	public List<Kitting> findAll() throws KittingDaoException;

	/** 
	 * Returns all rows from the kitting table that match the criteria 'kitting_no = :kittingNo'.
	 */
	public List<Kitting> findWhereKittingNoEquals(String kittingNo) throws KittingDaoException;

	/** 
	 * Returns all rows from the kitting table that match the criteria 'kitting_date = :kittingDate'.
	 */
	public List<Kitting> findWhereKittingDateEquals(Date kittingDate) throws KittingDaoException;

	/** 
	 * Returns all rows from the kitting table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Kitting> findWhereCreatedByEquals(String createdBy) throws KittingDaoException;

	/** 
	 * Returns all rows from the kitting table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Kitting> findWhereCreatedDateEquals(Date createdDate) throws KittingDaoException;

	/** 
	 * Returns all rows from the kitting table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Kitting> findWhereUpdatedByEquals(String updatedBy) throws KittingDaoException;

	/** 
	 * Returns all rows from the kitting table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Kitting> findWhereUpdatedDateEquals(Date updatedDate) throws KittingDaoException;

	/** 
	 * Returns the rows from the kitting table that matches the specified primary-key value.
	 */
	public Kitting findByPrimaryKey(KittingPk pk) throws KittingDaoException;

	public List<KittingDetail> findProductLocation(KittingDetail kittingDetail) throws KittingDaoException;

	public List<Kitting> findKittingPaging(Kitting k, int page)throws KittingDaoException;

}

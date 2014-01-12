package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.PackingDao;
import com.app.wms.engine.db.dto.Packing;
import com.app.wms.engine.db.dto.PackingPk;
import com.app.wms.engine.db.exceptions.PackingDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface PackingDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return PackingPk
	 */
	public PackingPk insert(Packing dto);

	/** 
	 * Updates a single row in the packing table.
	 */
	public void update(PackingPk pk, Packing dto) throws PackingDaoException;

	/** 
	 * Deletes a single row in the packing table.
	 */
	public void delete(PackingPk pk) throws PackingDaoException;

	/** 
	 * Returns all rows from the packing table that match the criteria 'packing_no = :packingNo'.
	 */
	public Packing findByPrimaryKey(String packingNo) throws PackingDaoException;

	/** 
	 * Returns all rows from the packing table that match the criteria ''.
	 */
	public List<Packing> findAll() throws PackingDaoException;

	/** 
	 * Returns all rows from the packing table that match the criteria 'packing_no = :packingNo'.
	 */
	public List<Packing> findWherePackingNoEquals(String packingNo) throws PackingDaoException;

	/** 
	 * Returns all rows from the packing table that match the criteria 'packing_date = :packingDate'.
	 */
	public List<Packing> findWherePackingDateEquals(Date packingDate) throws PackingDaoException;

	/** 
	 * Returns all rows from the packing table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Packing> findWhereCreatedByEquals(String createdBy) throws PackingDaoException;

	/** 
	 * Returns all rows from the packing table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Packing> findWhereCreatedDateEquals(Date createdDate) throws PackingDaoException;

	/** 
	 * Returns all rows from the packing table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Packing> findWhereUpdatedByEquals(String updatedBy) throws PackingDaoException;

	/** 
	 * Returns all rows from the packing table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Packing> findWhereUpdatedDateEquals(Date updatedDate) throws PackingDaoException;

	/** 
	 * Returns the rows from the packing table that matches the specified primary-key value.
	 */
	public Packing findByPrimaryKey(PackingPk pk) throws PackingDaoException;

	public List<Packing> findPacking(Packing packing) throws PackingDaoException;

	public List<Packing> findPackingPaging(Packing p, int page)throws PackingDaoException;

}

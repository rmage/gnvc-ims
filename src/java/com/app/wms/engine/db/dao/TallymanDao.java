package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.TallymanDao;
import com.app.wms.engine.db.dto.Tallyman;
import com.app.wms.engine.db.dto.TallymanPk;
import com.app.wms.engine.db.exceptions.TallymanDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface TallymanDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return TallymanPk
	 */
	public TallymanPk insert(Tallyman dto);

	/** 
	 * Updates a single row in the tallyman table.
	 */
	public void update(TallymanPk pk, Tallyman dto) throws TallymanDaoException;

	/** 
	 * Deletes a single row in the tallyman table.
	 */
	public void delete(TallymanPk pk) throws TallymanDaoException;

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'id = :id'.
	 */
	public Tallyman findByPrimaryKey(int id) throws TallymanDaoException;

	/** 
	 * Returns all rows from the tallyman table that match the criteria ''.
	 */
	public List<Tallyman> findAll() throws TallymanDaoException;

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'id = :id'.
	 */
	public List<Tallyman> findWhereIdEquals(int id) throws TallymanDaoException;

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'tally_id = :tallyId'.
	 */
	public List<Tallyman> findWhereTallyIdEquals(String tallyId) throws TallymanDaoException;

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'code = :code'.
	 */
	public List<Tallyman> findWhereCodeEquals(String code) throws TallymanDaoException;

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'name = :name'.
	 */
	public List<Tallyman> findWhereNameEquals(String name) throws TallymanDaoException;

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'tallyname = :tallyname'.
	 */
	public List<Tallyman> findWhereTallynameEquals(String tallyname) throws TallymanDaoException;

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'jobfunction = :jobfunction'.
	 */
	public List<Tallyman> findWhereJobfunctionEquals(String jobfunction) throws TallymanDaoException;

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'corp_id = :corpId'.
	 */
	public List<Tallyman> findWhereCorpIdEquals(String corpId) throws TallymanDaoException;

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'wh_code = :whCode'.
	 */
	public List<Tallyman> findWhereWhCodeEquals(String whCode) throws TallymanDaoException;

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'is_active = :isActive'.
	 */
	public List<Tallyman> findWhereIsActiveEquals(String isActive) throws TallymanDaoException;

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<Tallyman> findWhereIsDeleteEquals(String isDelete) throws TallymanDaoException;

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Tallyman> findWhereCreatedByEquals(int createdBy) throws TallymanDaoException;

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Tallyman> findWhereCreatedDateEquals(Date createdDate) throws TallymanDaoException;

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Tallyman> findWhereUpdatedByEquals(int updatedBy) throws TallymanDaoException;

	/** 
	 * Returns all rows from the tallyman table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Tallyman> findWhereUpdatedDateEquals(Date updatedDate) throws TallymanDaoException;

	/** 
	 * Returns the rows from the tallyman table that matches the specified primary-key value.
	 */
	public Tallyman findByPrimaryKey(TallymanPk pk) throws TallymanDaoException;

}

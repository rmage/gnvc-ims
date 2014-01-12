package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.GoodreceiveDao;
import com.app.wms.engine.db.dto.Goodreceive;
import com.app.wms.engine.db.dto.GoodreceivePk;
import com.app.wms.engine.db.exceptions.GoodreceiveDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface GoodreceiveDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return GoodreceivePk
	 */
	public GoodreceivePk insert(Goodreceive dto);

	/** 
	 * Updates a single row in the goodreceive table.
	 */
	public void update(GoodreceivePk pk, Goodreceive dto) throws GoodreceiveDaoException;

	/** 
	 * Deletes a single row in the goodreceive table.
	 */
	public void delete(GoodreceivePk pk) throws GoodreceiveDaoException;

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'id = :id'.
	 */
	public Goodreceive findByPrimaryKey(int id) throws GoodreceiveDaoException;

	/** 
	 * Returns all rows from the goodreceive table that match the criteria ''.
	 */
	public List<Goodreceive> findAll() throws GoodreceiveDaoException;

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'id = :id'.
	 */
	public List<Goodreceive> findWhereIdEquals(int id) throws GoodreceiveDaoException;

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'grnumber = :grnumber'.
	 */
	public List<Goodreceive> findWhereGrnumberEquals(String grnumber) throws GoodreceiveDaoException;

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'ponumber = :ponumber'.
	 */
	public List<Goodreceive> findWherePonumberEquals(String ponumber) throws GoodreceiveDaoException;

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'receiveddate = :receiveddate'.
	 */
	public List<Goodreceive> findWhereReceiveddateEquals(Date receiveddate) throws GoodreceiveDaoException;

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'createdby = :createdby'.
	 */
	public List<Goodreceive> findWhereCreatedbyEquals(String createdby) throws GoodreceiveDaoException;

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'corpid = :corpid'.
	 */
	public List<Goodreceive> findWhereCorpidEquals(String corpid) throws GoodreceiveDaoException;

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'lotid = :lotid'.
	 */
	public List<Goodreceive> findWhereLotidEquals(String lotid) throws GoodreceiveDaoException;

	/** 
	 * Returns all rows from the goodreceive table that match the criteria 'wh_code = :whCode'.
	 */
	public List<Goodreceive> findWhereWhCodeEquals(String whCode) throws GoodreceiveDaoException;

	/** 
	 * Returns the rows from the goodreceive table that matches the specified primary-key value.
	 */
	public Goodreceive findByPrimaryKey(GoodreceivePk pk) throws GoodreceiveDaoException;

}

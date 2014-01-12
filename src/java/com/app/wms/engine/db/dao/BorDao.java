package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.BorDao;
import com.app.wms.engine.db.dto.Bor;
import com.app.wms.engine.db.dto.BorPk;
import com.app.wms.engine.db.exceptions.BorDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface BorDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return BorPk
	 */
	public BorPk insert(Bor dto);

	/** 
	 * Updates a single row in the bor table.
	 */
	public void update(BorPk pk, Bor dto) throws BorDaoException;

	/** 
	 * Deletes a single row in the bor table.
	 */
	public void delete(BorPk pk) throws BorDaoException;

	/** 
	 * Returns all rows from the bor table that match the criteria 'id = :id'.
	 */
	public Bor findByPrimaryKey(int id) throws BorDaoException;

	/** 
	 * Returns all rows from the bor table that match the criteria ''.
	 */
	public List<Bor> findAll() throws BorDaoException;

	/** 
	 * Returns all rows from the bor table that match the criteria 'id = :id'.
	 */
	public List<Bor> findWhereIdEquals(int id) throws BorDaoException;

	/** 
	 * Returns all rows from the bor table that match the criteria 'bornumber = :bornumber'.
	 */
	public List<Bor> findWhereBornumberEquals(String bornumber) throws BorDaoException;

	/** 
	 * Returns all rows from the bor table that match the criteria 'bordate = :bordate'.
	 */
	public List<Bor> findWhereBordateEquals(Date bordate) throws BorDaoException;

	/** 
	 * Returns all rows from the bor table that match the criteria 'borreferensi = :borreferensi'.
	 */
	public List<Bor> findWhereBorreferensiEquals(String borreferensi) throws BorDaoException;

	/** 
	 * Returns all rows from the bor table that match the criteria 'createdby = :createdby'.
	 */
	public List<Bor> findWhereCreatedbyEquals(String createdby) throws BorDaoException;

	/** 
	 * Returns all rows from the bor table that match the criteria 'buyer_name = :buyerName'.
	 */
	public List<Bor> findWhereBuyerNameEquals(String buyerName) throws BorDaoException;

	/** 
	 * Returns the rows from the bor table that matches the specified primary-key value.
	 */
	public Bor findByPrimaryKey(BorPk pk) throws BorDaoException;
	
	public List<Bor> findBorPaging(Bor b, int page) throws BorDaoException;

}

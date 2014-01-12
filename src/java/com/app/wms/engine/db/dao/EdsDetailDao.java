package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.EdsDetailDao;
import com.app.wms.engine.db.dto.EdsDetail;
import com.app.wms.engine.db.dto.EdsDetailPk;
import com.app.wms.engine.db.exceptions.EdsDetailDaoException;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface EdsDetailDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return EdsDetailPk
	 */
	public EdsDetailPk insert(EdsDetail dto);

	/** 
	 * Updates a single row in the eds_detail table.
	 */
	public void update(EdsDetailPk pk, EdsDetail dto) throws EdsDetailDaoException;

	/** 
	 * Deletes a single row in the eds_detail table.
	 */
	public void delete(EdsDetailPk pk) throws EdsDetailDaoException;

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'id = :id'.
	 */
	public EdsDetail findByPrimaryKey(int id) throws EdsDetailDaoException;

	/** 
	 * Returns all rows from the eds_detail table that match the criteria ''.
	 */
	public List<EdsDetail> findAll() throws EdsDetailDaoException;

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'id = :id'.
	 */
	public List<EdsDetail> findWhereIdEquals(int id) throws EdsDetailDaoException;

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'edsnumber = :edsnumber'.
	 */
	public List<EdsDetail> findWhereEdsnumberEquals(String edsnumber) throws EdsDetailDaoException;

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'packStyle = :packStyle'.
	 */
	public List<EdsDetail> findWherePackStyleEquals(String packStyle) throws EdsDetailDaoException;

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'canSize = :canSize'.
	 */
	public List<EdsDetail> findWhereCanSizeEquals(String canSize) throws EdsDetailDaoException;

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'productcode = :productcode'.
	 */
	public List<EdsDetail> findWhereProductcodeEquals(String productcode) throws EdsDetailDaoException;

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'qtyCases = :qtyCases'.
	 */
	public List<EdsDetail> findWhereQtyCasesEquals(float qtyCases) throws EdsDetailDaoException;

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'remarks = :remarks'.
	 */
	public List<EdsDetail> findWhereRemarksEquals(String remarks) throws EdsDetailDaoException;

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'unitPrice = :unitPrice'.
	 */
	public List<EdsDetail> findWhereUnitPriceEquals(int unitPrice) throws EdsDetailDaoException;

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'amount = :amount'.
	 */
	public List<EdsDetail> findWhereAmountEquals(int amount) throws EdsDetailDaoException;

	/** 
	 * Returns all rows from the eds_detail table that match the criteria 'producttype = :producttype'.
	 */
	public List<EdsDetail> findWhereProducttypeEquals(String producttype) throws EdsDetailDaoException;

	/** 
	 * Returns the rows from the eds_detail table that matches the specified primary-key value.
	 */
	public EdsDetail findByPrimaryKey(EdsDetailPk pk) throws EdsDetailDaoException;

}

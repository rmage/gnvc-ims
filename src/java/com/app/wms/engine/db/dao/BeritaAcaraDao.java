package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.BeritaAcaraDao;
import com.app.wms.engine.db.dto.BeritaAcara;
import com.app.wms.engine.db.dto.BeritaAcaraPk;
import com.app.wms.engine.db.exceptions.BeritaAcaraDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface BeritaAcaraDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return BeritaAcaraPk
	 */
	public BeritaAcaraPk insert(BeritaAcara dto);

	/** 
	 * Updates a single row in the berita_acara table.
	 */
	public void update(BeritaAcaraPk pk, BeritaAcara dto) throws BeritaAcaraDaoException;

	/** 
	 * Deletes a single row in the berita_acara table.
	 */
	public void delete(BeritaAcaraPk pk) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'id = :id'.
	 */
	public BeritaAcara findByPrimaryKey(int id) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria ''.
	 */
	public List<BeritaAcara> findAll() throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'id = :id'.
	 */
	public List<BeritaAcara> findWhereIdEquals(int id) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'berita_no = :beritaNo'.
	 */
	public List<BeritaAcara> findWhereBeritaNoEquals(String beritaNo) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'berita_date = :beritaDate'.
	 */
	public List<BeritaAcara> findWhereBeritaDateEquals(Date beritaDate) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'ponumber = :ponumber'.
	 */
	public List<BeritaAcara> findWherePonumberEquals(String ponumber) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'podate = :podate'.
	 */
	public List<BeritaAcara> findWherePodateEquals(Date podate) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'productcode = :productcode'.
	 */
	public List<BeritaAcara> findWhereProductcodeEquals(String productcode) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'qty = :qty'.
	 */
	public List<BeritaAcara> findWhereQtyEquals(float qty) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'uom_name = :uomName'.
	 */
	public List<BeritaAcara> findWhereUomNameEquals(String uomName) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'total = :total'.
	 */
	public List<BeritaAcara> findWhereTotalEquals(float total) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'contractor_name = :contractorName'.
	 */
	public List<BeritaAcara> findWhereContractorNameEquals(String contractorName) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'result_work = :resultWork'.
	 */
	public List<BeritaAcara> findWhereResultWorkEquals(float resultWork) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'pertial_completion = :pertialCompletion'.
	 */
	public List<BeritaAcara> findWherePertialCompletionEquals(float pertialCompletion) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'retention = :retention'.
	 */
	public List<BeritaAcara> findWhereRetentionEquals(float retention) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'description = :description'.
	 */
	public List<BeritaAcara> findWhereDescriptionEquals(String description) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'progress = :progress'.
	 */
	public List<BeritaAcara> findWhereProgressEquals(float progress) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'total_progress = :totalProgress'.
	 */
	public List<BeritaAcara> findWhereTotalProgressEquals(float totalProgress) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'end_user = :endUser'.
	 */
	public List<BeritaAcara> findWhereEndUserEquals(String endUser) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'plant_engineer = :plantEngineer'.
	 */
	public List<BeritaAcara> findWherePlantEngineerEquals(String plantEngineer) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'operation_engineer = :operationEngineer'.
	 */
	public List<BeritaAcara> findWhereOperationEngineerEquals(String operationEngineer) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'is_active = :isActive'.
	 */
	public List<BeritaAcara> findWhereIsActiveEquals(String isActive) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<BeritaAcara> findWhereIsDeleteEquals(String isDelete) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'created_by = :createdBy'.
	 */
	public List<BeritaAcara> findWhereCreatedByEquals(String createdBy) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'created_date = :createdDate'.
	 */
	public List<BeritaAcara> findWhereCreatedDateEquals(Date createdDate) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<BeritaAcara> findWhereUpdatedByEquals(String updatedBy) throws BeritaAcaraDaoException;

	/** 
	 * Returns all rows from the berita_acara table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<BeritaAcara> findWhereUpdatedDateEquals(Date updatedDate) throws BeritaAcaraDaoException;

	/** 
	 * Returns the rows from the berita_acara table that matches the specified primary-key value.
	 */
	public BeritaAcara findByPrimaryKey(BeritaAcaraPk pk) throws BeritaAcaraDaoException;

	public List<BeritaAcara> findBeritaAcaraPaging(BeritaAcara b, int page) throws BeritaAcaraDaoException;;

}

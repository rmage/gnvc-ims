package com.app.wms.engine.db.dao;
//
import com.app.wms.engine.db.dao.ConsigneeDao;
import com.app.wms.engine.db.dto.Consignee;
import com.app.wms.engine.db.dto.ConsigneePk;
import com.app.wms.engine.db.exceptions.ConsigneeDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface ConsigneeDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return ConsigneePk
	 */
	public ConsigneePk insert(Consignee dto);

	/** 
	 * Updates a single row in the consignee table.
	 */
	public void update(ConsigneePk pk, Consignee dto) throws ConsigneeDaoException;

	/** 
	 * Deletes a single row in the consignee table.
	 */
	public void delete(ConsigneePk pk) throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria 'id = :id'.
	 */
	public Consignee findByPrimaryKey(int id) throws ConsigneeDaoException;
	
	public Consignee findByPrimaryKey(String consigneeCode) throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria ''.
	 */
	public List<Consignee> findAll() throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria 'id = :id'.
	 */
	public List<Consignee> findWhereIdEquals(int id) throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria 'consignee_code = :consigneeCode'.
	 */
	public List<Consignee> findWhereConsigneeCodeEquals(String consigneeCode) throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria 'consignee_name = :consigneeName'.
	 */
	public List<Consignee> findWhereConsigneeNameEquals(String consigneeName) throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria 'address1 = :address1'.
	 */
	public List<Consignee> findWhereAddress1Equals(String address1) throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria 'address2 = :address2'.
	 */
	public List<Consignee> findWhereAddress2Equals(String address2) throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria 'address3 = :address3'.
	 */
	public List<Consignee> findWhereAddress3Equals(String address3) throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria 'consignee_phone = :consigneePhone'.
	 */
	public List<Consignee> findWhereConsigneePhoneEquals(String consigneePhone) throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria 'user_id = :userId'.
	 */
	public List<Consignee> findWhereUserIdEquals(String userId) throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria 'corp_id = :corpId'.
	 */
	public List<Consignee> findWhereCorpIdEquals(String corpId) throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria 'wh_code = :whCode'.
	 */
	public List<Consignee> findWhereWhCodeEquals(String whCode) throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria 'is_active = :isActive'.
	 */
	public List<Consignee> findWhereIsActiveEquals(String isActive) throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<Consignee> findWhereIsDeleteEquals(String isDelete) throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Consignee> findWhereCreatedByEquals(String createdBy) throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Consignee> findWhereCreatedDateEquals(Date createdDate) throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Consignee> findWhereUpdatedByEquals(String updatedBy) throws ConsigneeDaoException;

	/** 
	 * Returns all rows from the consignee table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Consignee> findWhereUpdatedDateEquals(Date updatedDate) throws ConsigneeDaoException;

	/** 
	 * Returns the rows from the consignee table that matches the specified primary-key value.
	 */
	public Consignee findByPrimaryKey(ConsigneePk pk) throws ConsigneeDaoException;
	
	public List<Consignee> findConsigneePaging(Consignee c, Integer page) throws ConsigneeDaoException;
	
	public List<Consignee> findWhereConsignee(Consignee c) throws ConsigneeDaoException;

}

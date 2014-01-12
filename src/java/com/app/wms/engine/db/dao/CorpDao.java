package com.app.wms.engine.db.dao;

import com.app.web.engine.search.CorporateSearch;
import com.app.wms.engine.db.dao.CorpDao;
import com.app.wms.engine.db.dto.Corp;
import com.app.wms.engine.db.dto.CorpPk;
import com.app.wms.engine.db.exceptions.CorpDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface CorpDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return CorpPk
	 */
	public CorpPk insert(Corp dto);

	/** 
	 * Updates a single row in the corp table.
	 */
	public void update(CorpPk pk, Corp dto) throws CorpDaoException;

	/** 
	 * Deletes a single row in the corp table.
	 */
	public void delete(CorpPk pk) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'corp_id = :corpId'.
	 */
	public Corp findByPrimaryKey(String corpId) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria ''.
	 */
	public List<Corp> findAll() throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'corp_id = :corpId'.
	 */
	public List<Corp> findWhereCorpIdEquals(String corpId) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'corp_name = :corpName'.
	 */
	public List<Corp> findWhereCorpNameEquals(String corpName) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'wh_code = :whCode'.
	 */
//	public List<Corp> findWhereWhCodeEquals(String whCode) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'address1 = :address1'.
	 */
	public List<Corp> findWhereAddress1Equals(String address1) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'address2 = :address2'.
	 */
	public List<Corp> findWhereAddress2Equals(String address2) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'address3 = :address3'.
	 */
	public List<Corp> findWhereAddress3Equals(String address3) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'email = :email'.
	 */
	public List<Corp> findWhereEmailEquals(String email) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'city_code = :cityCode'.
	 */
	public List<Corp> findWhereCityCodeEquals(String cityCode) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'zipcode = :zipcode'.
	 */
	public List<Corp> findWhereZipcodeEquals(String zipcode) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'phone1 = :phone1'.
	 */
	public List<Corp> findWherePhone1Equals(String phone1) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'phone2 = :phone2'.
	 */
	public List<Corp> findWherePhone2Equals(String phone2) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'fax = :fax'.
	 */
	public List<Corp> findWhereFaxEquals(String fax) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'province_code = :provinceCode'.
	 */
	public List<Corp> findWhereProvinceCodeEquals(String provinceCode) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'is_active = :isActive'.
	 */
	public List<Corp> findWhereIsActiveEquals(String isActive) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'is_delete = :isDelete'.
	 */
	public List<Corp> findWhereIsDeleteEquals(String isDelete) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'created_by = :createdBy'.
	 */
	public List<Corp> findWhereCreatedByEquals(String createdBy) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'created_date = :createdDate'.
	 */
	public List<Corp> findWhereCreatedDateEquals(Date createdDate) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'updated_by = :updatedBy'.
	 */
	public List<Corp> findWhereUpdatedByEquals(String updatedBy) throws CorpDaoException;

	/** 
	 * Returns all rows from the corp table that match the criteria 'updated_date = :updatedDate'.
	 */
	public List<Corp> findWhereUpdatedDateEquals(Date updatedDate) throws CorpDaoException;

	/** 
	 * Returns the rows from the corp table that matches the specified primary-key value.
	 */
	public Corp findByPrimaryKey(CorpPk pk) throws CorpDaoException;

	public List<Corp> findByCriteriaLimit(CorporateSearch c, int start, int end) throws CorpDaoException;

	public List<Corp> findCorporatePaging(CorporateSearch c, int page) throws CorpDaoException;
}

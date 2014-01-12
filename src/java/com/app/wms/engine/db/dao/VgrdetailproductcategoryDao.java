package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.VgrdetailproductcategoryDao;
import com.app.wms.engine.db.dto.Vgrdetailproductcategory;
import com.app.wms.engine.db.exceptions.VgrdetailproductcategoryDaoException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public interface VgrdetailproductcategoryDao
{
	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	public void insert(Vgrdetailproductcategory dto);

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria ''.
	 */
	public List<Vgrdetailproductcategory> findAll() throws VgrdetailproductcategoryDaoException;

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'id = :id'.
	 */
	public List<Vgrdetailproductcategory> findWhereIdEquals(int id) throws VgrdetailproductcategoryDaoException;

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'grnumber = :grnumber'.
	 */
	public List<Vgrdetailproductcategory> findWhereGrnumberEquals(String grnumber) throws VgrdetailproductcategoryDaoException;

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'productcode = :productcode'.
	 */
	public List<Vgrdetailproductcategory> findWhereProductcodeEquals(String productcode) throws VgrdetailproductcategoryDaoException;

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'qtyreal = :qtyreal'.
	 */
	public List<Vgrdetailproductcategory> findWhereQtyrealEquals(int qtyreal) throws VgrdetailproductcategoryDaoException;

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'status = :status'.
	 */
	public List<Vgrdetailproductcategory> findWhereStatusEquals(String status) throws VgrdetailproductcategoryDaoException;

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'expirydate = :expirydate'.
	 */
	public List<Vgrdetailproductcategory> findWhereExpirydateEquals(Date expirydate) throws VgrdetailproductcategoryDaoException;

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'remark = :remark'.
	 */
	public List<Vgrdetailproductcategory> findWhereRemarkEquals(String remark) throws VgrdetailproductcategoryDaoException;

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'lotid = :lotid'.
	 */
	public List<Vgrdetailproductcategory> findWhereLotidEquals(int lotid) throws VgrdetailproductcategoryDaoException;

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'product_name = :productName'.
	 */
	public List<Vgrdetailproductcategory> findWhereProductNameEquals(String productName) throws VgrdetailproductcategoryDaoException;

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'category_name = :categoryName'.
	 */
	public List<Vgrdetailproductcategory> findWhereCategoryNameEquals(String categoryName) throws VgrdetailproductcategoryDaoException;

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'qtygood = :qtygood'.
	 */
	public List<Vgrdetailproductcategory> findWhereQtygoodEquals(int qtygood) throws VgrdetailproductcategoryDaoException;

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'qtydmg = :qtydmg'.
	 */
	public List<Vgrdetailproductcategory> findWhereQtydmgEquals(int qtydmg) throws VgrdetailproductcategoryDaoException;

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'producttype = :producttype'.
	 */
	public List<Vgrdetailproductcategory> findWhereProducttypeEquals(String producttype) throws VgrdetailproductcategoryDaoException;

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'wh_code = :whCode'.
	 */
	public List<Vgrdetailproductcategory> findWhereWhCodeEquals(String whCode) throws VgrdetailproductcategoryDaoException;

	/** 
	 * Returns all rows from the vgrdetailproductcategory table that match the criteria 'corp_id = :corpId'.
	 */
	public List<Vgrdetailproductcategory> findWhereCorpIdEquals(String corpId) throws VgrdetailproductcategoryDaoException;
	
	public List<Vgrdetailproductcategory> getGRDetail(String grnumber) throws VgrdetailproductcategoryDaoException;

}

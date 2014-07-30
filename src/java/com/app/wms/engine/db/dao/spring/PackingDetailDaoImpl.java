package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.PackingDetailDao;
import com.app.wms.engine.db.dto.PackingDetail;
import com.app.wms.engine.db.dto.PackingDetailPk;
import com.app.wms.engine.db.exceptions.KittingDetailDaoException;
import com.app.wms.engine.db.exceptions.PackingDetailDaoException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class PackingDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<PackingDetail>, PackingDetailDao
{
	protected SimpleJdbcTemplate jdbcTemplate;

	protected DataSource dataSource;

	/**
	 * Method 'setDataSource'
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 * @return PackingDetailPk
	 */
	public PackingDetailPk insert(PackingDetail dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( packing_no, so_number, picking_id, kitting_no, product_id, product_code, product_name, unitcode, quantity, user_id, corp_id, wh_code ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getPackingNo(),dto.getSoNumber(),dto.getPickingId(),dto.getKittingNo(),dto.getProductId(),dto.getProductCode(),dto.getProductName(),dto.getUnitcode(),dto.getQuantity(),dto.getUserId(),dto.getCorpId(),dto.getWhCode()} );
	
		return dto.createPk();
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return PackingDetail
	 */
	public PackingDetail mapRow(ResultSet rs, int row) throws SQLException
	{
		PackingDetail dto = new PackingDetail();
		dto.setPackingNo( rs.getString( 1 ) );
		dto.setSoNumber( rs.getString( 2 ) );
		dto.setPickingId( rs.getString( 3 ) );
		dto.setKittingNo( rs.getString( 4 ) );
		dto.setProductId( rs.getString( 5 ) );
		dto.setProductCode( rs.getString( 6 ) );
		dto.setProductName( rs.getString( 7 ) );
		dto.setUnitcode( rs.getString( 8 ) );
		dto.setQuantity( rs.getInt( 9 ) );
		if (rs.wasNull()) {
			dto.setQuantityNull( true );
		}
		
		dto.setUserId( rs.getString( 10 ) );
		dto.setCorpId( rs.getString( 11 ) );
		dto.setWhCode( rs.getString( 12 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "packing_detail";
	}

	/** 
	 * Returns all rows from the packing_detail table that match the criteria ''.
	 */
	@Transactional
	public List<PackingDetail> findAll() throws PackingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, so_number, picking_id, kitting_no, product_id, product_code, product_name, unitcode, quantity, user_id, corp_id, wh_code FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new PackingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'packing_no = :packingNo'.
	 */
	@Transactional
	public List<PackingDetail> findWherePackingNoEquals(String packingNo) throws PackingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, so_number, picking_id, kitting_no, product_id, product_code, product_name, unitcode, quantity, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE packing_no = ? ORDER BY packing_no", this,packingNo);
		}
		catch (Exception e) {
			throw new PackingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'so_number = :soNumber'.
	 */
	@Transactional
	public List<PackingDetail> findWhereSoNumberEquals(String soNumber) throws PackingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, so_number, picking_id, kitting_no, product_id, product_code, product_name, unitcode, quantity, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE so_number = ? ORDER BY so_number", this,soNumber);
		}
		catch (Exception e) {
			throw new PackingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'picking_id = :pickingId'.
	 */
	@Transactional
	public List<PackingDetail> findWherePickingIdEquals(String pickingId) throws PackingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, so_number, picking_id, kitting_no, product_id, product_code, product_name, unitcode, quantity, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE picking_id = ? ORDER BY picking_id", this,pickingId);
		}
		catch (Exception e) {
			throw new PackingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'kitting_no = :kittingNo'.
	 */
	@Transactional
	public List<PackingDetail> findWhereKittingNoEquals(String kittingNo) throws PackingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, so_number, picking_id, kitting_no, product_id, product_code, product_name, unitcode, quantity, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE kitting_no = ? ORDER BY kitting_no", this,kittingNo);
		}
		catch (Exception e) {
			throw new PackingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'product_id = :productId'.
	 */
	@Transactional
	public List<PackingDetail> findWhereProductIdEquals(String productId) throws PackingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, so_number, picking_id, kitting_no, product_id, product_code, product_name, unitcode, quantity, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_id = ? ORDER BY product_id", this,productId);
		}
		catch (Exception e) {
			throw new PackingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'product_code = :productCode'.
	 */
	@Transactional
	public List<PackingDetail> findWhereProductCodeEquals(String productCode) throws PackingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, so_number, picking_id, kitting_no, product_id, product_code, product_name, unitcode, quantity, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_code = ? ORDER BY product_code", this,productCode);
		}
		catch (Exception e) {
			throw new PackingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'product_name = :productName'.
	 */
	@Transactional
	public List<PackingDetail> findWhereProductNameEquals(String productName) throws PackingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, so_number, picking_id, kitting_no, product_id, product_code, product_name, unitcode, quantity, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE product_name = ? ORDER BY product_name", this,productName);
		}
		catch (Exception e) {
			throw new PackingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'unitcode = :unitcode'.
	 */
	@Transactional
	public List<PackingDetail> findWhereUnitcodeEquals(String unitcode) throws PackingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, so_number, picking_id, kitting_no, product_id, product_code, product_name, unitcode, quantity, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE unitcode = ? ORDER BY unitcode", this,unitcode);
		}
		catch (Exception e) {
			throw new PackingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'quantity = :quantity'.
	 */
	@Transactional
	public List<PackingDetail> findWhereQuantityEquals(int quantity) throws PackingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, so_number, picking_id, kitting_no, product_id, product_code, product_name, unitcode, quantity, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE quantity = ? ORDER BY quantity", this,quantity);
		}
		catch (Exception e) {
			throw new PackingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'user_id = :userId'.
	 */
	@Transactional
	public List<PackingDetail> findWhereUserIdEquals(String userId) throws PackingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, so_number, picking_id, kitting_no, product_id, product_code, product_name, unitcode, quantity, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE user_id = ? ORDER BY user_id", this,userId);
		}
		catch (Exception e) {
			throw new PackingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'corp_id = :corpId'.
	 */
	@Transactional
	public List<PackingDetail> findWhereCorpIdEquals(String corpId) throws PackingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, so_number, picking_id, kitting_no, product_id, product_code, product_name, unitcode, quantity, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE corp_id = ? ORDER BY corp_id", this,corpId);
		}
		catch (Exception e) {
			throw new PackingDetailDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the packing_detail table that match the criteria 'wh_code = :whCode'.
	 */
	@Transactional
	public List<PackingDetail> findWhereWhCodeEquals(String whCode) throws PackingDetailDaoException
	{
		try {
			return jdbcTemplate.query("SELECT packing_no, so_number, picking_id, kitting_no, product_id, product_code, product_name, unitcode, quantity, user_id, corp_id, wh_code FROM " + getTableName() + " WHERE wh_code = ? ORDER BY wh_code", this,whCode);
		}
		catch (Exception e) {
			throw new PackingDetailDaoException("Query failed", e);
		}
		
	}

	/**
    * Updates a single row in the packing_detail table.
    */
	@Transactional
	public void update(PackingDetailPk pk, PackingDetail dto) throws PackingDetailDaoException {
		jdbcTemplate.update("update " + getTableName() + " set packing_no = ?, so_number = ?, picking_id = ?, kitting_no =?, product_id = ?, product_code = ?, product_name = ?, unitcode = ?, quantity = ?, user_id = ?, corp_id = ?, wh_code = ? where packing_no = ?", dto.getPackingNo(), dto.getSoNumber(), dto.getPickingId(), dto.getKittingNo(), dto.getProductId(), dto.getProductCode(), dto.getProductName(), dto.getUnitcode(), dto.getQuantity(), dto.getUserId(), dto.getCorpId(), dto.getWhCode(), pk.getPackingNo());
	}

	/**
    * Deletes a single row in the packing_detail table.
    */
	@Transactional
	public void delete(PackingDetailPk pk) throws PackingDetailDaoException {
		jdbcTemplate.update("delete from " + getTableName() + " where packing_no = ?", pk.getPackingNo());
	}

	/**
	* Returns all rows from the packing_detail table that match the criteria 'packing_no = :packing_no'.
	*/
	@Transactional
	public PackingDetail findByPrimaryKey(String packingNo)	throws PackingDetailDaoException {
		try {
            List<PackingDetail> list = jdbcTemplate.query("select packing_no, so_number, picking_id, kitting_no, product_id, product_code, product_name, unitcode, quantity, user_id, corp_id, wh_code from " + getTableName() + " where packing_no = ?", this, packingNo);
            return list.size() == 0 ? null : list.get(0);
        } catch (Exception e) {
            throw new PackingDetailDaoException("Query failed", e);
        }
	}

	/**
     * Returns the rows from the packing_detail table that matches the specified primary-key value.
     */
	@Transactional
	public PackingDetail findByPrimaryKey(PackingDetailPk pk) throws PackingDetailDaoException {
		return findByPrimaryKey(pk.getPackingNo());
	}

	@Override
	public List<PackingDetail> findDetail(PackingDetail pd)	throws PackingDetailDaoException {
		try {
	       	 String packingNo = pd.getPackingNo();
	       	 StringBuffer sb = new StringBuffer();
	       	 
		    	 sb.append("select distinct" 
			 		  +" packing_no, so_number, picking_id, kitting_no, product_id, product_code," 
			 		  +" product_name, unitcode, quantity, user_id, corp_id, wh_code from "
			 		  +getTableName() 
			 		  +" where packing_no like '"+packingNo+"' ");

	       	 return jdbcTemplate.query(sb.toString(), this);
	       } catch (Exception e) {
	           throw new PackingDetailDaoException("Query failed", e);
	       }
	}

}

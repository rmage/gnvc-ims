package com.app.wms.engine.db.dao.spring;

import com.app.web.engine.search.ReplenishSearch;
import com.app.wms.engine.db.dao.ReplenishmentDao;
import com.app.wms.engine.db.dto.Replenishment;
import com.app.wms.engine.db.dto.ReplenishmentPk;
import com.app.wms.engine.db.exceptions.ReplenishmentDaoException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class ReplenishmentDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Replenishment>, ReplenishmentDao
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
	 * @return ReplenishmentPk
	 */
	public ReplenishmentPk insert(Replenishment dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( replenish_no, replenish_date, status_app, app_date, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getReplenishNo(),dto.getReplenishDate(),dto.getStatusApp(),dto.getAppDate(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		return dto.createPk();
	}

	/** 
	 * Updates a single row in the replenishment table.
	 */
	public void update(ReplenishmentPk pk, Replenishment dto) throws ReplenishmentDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET replenish_no = ?, replenish_date = ?, status_app = ?, app_date = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE replenish_no = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.compile();
		su.update( new Object[] { dto.getReplenishNo(),dto.getReplenishDate(),dto.getStatusApp(),dto.getAppDate(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getReplenishNo() } );
	}

	/** 
	 * Deletes a single row in the replenishment table.
	 */
	@Transactional
	public void delete(ReplenishmentPk pk) throws ReplenishmentDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE replenish_no = ?",pk.getReplenishNo());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Replenishment
	 */
	public Replenishment mapRow(ResultSet rs, int row) throws SQLException
	{
		Replenishment dto = new Replenishment();
		dto.setReplenishNo( rs.getString( 1 ) );
		dto.setReplenishDate( rs.getTimestamp(2 ) );
		dto.setStatusApp( rs.getString( 3 ) );
		dto.setAppDate( rs.getTimestamp(4 ) );
		dto.setCreatedBy( rs.getString( 5 ) );
		dto.setCreatedDate( rs.getTimestamp(6 ) );
		dto.setUpdatedBy( rs.getString( 7 ) );
		dto.setUpdatedDate( rs.getTimestamp(8 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..replenishment";
	}

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'replenish_no = :replenishNo'.
	 */
	@Transactional
	public Replenishment findByPrimaryKey(String replenishNo) throws ReplenishmentDaoException
	{
		try {
			List<Replenishment> list = jdbcTemplate.query("SELECT replenish_no, replenish_date, status_app, app_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE replenish_no = ?", this,replenishNo);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new ReplenishmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenishment table that match the criteria ''.
	 */
	@Transactional
	public List<Replenishment> findAll() throws ReplenishmentDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, replenish_date, status_app, app_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " ORDER BY replenish_no", this);
		}
		catch (Exception e) {
			throw new ReplenishmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'replenish_no = :replenishNo'.
	 */
	@Transactional
	public List<Replenishment> findWhereReplenishNoEquals(String replenishNo) throws ReplenishmentDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, replenish_date, status_app, app_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE replenish_no = ? ORDER BY replenish_no", this,replenishNo);
		}
		catch (Exception e) {
			throw new ReplenishmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'replenish_date = :replenishDate'.
	 */
	@Transactional
	public List<Replenishment> findWhereReplenishDateEquals(Date replenishDate) throws ReplenishmentDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, replenish_date, status_app, app_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE replenish_date = ? ORDER BY replenish_date", this,replenishDate);
		}
		catch (Exception e) {
			throw new ReplenishmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'status_app = :statusApp'.
	 */
	@Transactional
	public List<Replenishment> findWhereStatusAppEquals(String statusApp) throws ReplenishmentDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, replenish_date, status_app, app_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE status_app = ? ORDER BY status_app", this,statusApp);
		}
		catch (Exception e) {
			throw new ReplenishmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'app_date = :appDate'.
	 */
	@Transactional
	public List<Replenishment> findWhereAppDateEquals(Date appDate) throws ReplenishmentDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, replenish_date, status_app, app_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE app_date = ? ORDER BY app_date", this,appDate);
		}
		catch (Exception e) {
			throw new ReplenishmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<Replenishment> findWhereCreatedByEquals(String createdBy) throws ReplenishmentDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, replenish_date, status_app, app_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new ReplenishmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<Replenishment> findWhereCreatedDateEquals(Date createdDate) throws ReplenishmentDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, replenish_date, status_app, app_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new ReplenishmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<Replenishment> findWhereUpdatedByEquals(String updatedBy) throws ReplenishmentDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, replenish_date, status_app, app_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new ReplenishmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the replenishment table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<Replenishment> findWhereUpdatedDateEquals(Date updatedDate) throws ReplenishmentDaoException
	{
		try {
			return jdbcTemplate.query("SELECT replenish_no, replenish_date, status_app, app_date, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new ReplenishmentDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the replenishment table that matches the specified primary-key value.
	 */
	public Replenishment findByPrimaryKey(ReplenishmentPk pk) throws ReplenishmentDaoException
	{
		return findByPrimaryKey( pk.getReplenishNo() );
	}

        /* added by FYA : 23 May 2013 */
        public void statusDocument(String replenishNo, String status) throws ReplenishmentDaoException {
            try {
                String c = "";
                if(status.equals("0"))
                    c = " + '-' + status_app";
                jdbcTemplate.update("UPDATE replenishment SET status_app = ?" + c + " WHERE replenish_no = ?", status, replenishNo);
            } catch(Exception e) {
                throw new ReplenishmentDaoException("Query failed : " + e.getMessage(), e);
            }
        }
        
        /* added by FYA : 24 May 2013 */
        public List<Replenishment> findByCriteriaLimit(ReplenishSearch c, int start, int end) throws ReplenishmentDaoException {
            try {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String cSql = "AND (1=1)";
                
                if(c != null) {
                    if(c.getReplenishNo() != null)
                        cSql += " AND replenish_no = '" + c.getReplenishNo() + "'";

                    if(c.getReplenishDate()!= null)
                        cSql += " AND created_date BETWEEN convert(datetime, '" + df.format(c.getReplenishDate()) + " 00:00:00.000', 121) "
                            + "AND convert(datetime, '" + df.format(c.getReplenishDate()) + " 23:59:59.999', 121)";
                }
                
                return jdbcTemplate.query("SELECT * FROM (SELECT replenish_no, replenish_date, status_app, app_date, created_by, "
                    + "created_date, updated_by, updated_date, ROW_NUMBER() OVER(ORDER BY replenish_no desc) as rowID FROM replenishment) AS sqR "
                    + "WHERE rowID BETWEEN ? AND ? " + cSql + " ORDER BY replenish_no desc", this, start, end);
            }
            catch (Exception e) {
                throw new ReplenishmentDaoException("Query failed : " + e.getMessage(), e);
            }
        }
}

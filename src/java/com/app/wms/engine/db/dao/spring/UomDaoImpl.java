package com.app.wms.engine.db.dao.spring;

import com.app.wms.engine.db.dao.UomDao;
import com.app.wms.engine.db.dto.Uom;
import com.app.wms.engine.db.dto.UomPk;
import com.app.wms.engine.db.exceptions.UomDaoException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UomDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Uom>, UomDao
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
	 * @return UomPk
	 */
	public UomPk insert(Uom dto)
	{
		SqlUpdate su = new SqlUpdate( dataSource, "INSERT INTO " + getTableName() + " ( uom_code, uom_name, remarks, is_active, is_delete, created_by, created_date, updated_by, updated_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.compile();
		su.update( new Object[] { dto.getUomCode(),dto.getUomName(),dto.getRemarks(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate()} );
		UomPk pk = new UomPk();
		pk.setId( jdbcTemplate.queryForInt("select @@IDENTITY") );
		return pk;
	}

	/** 
	 * Updates a single row in the uom table.
	 */
	public void update(UomPk pk, Uom dto) throws UomDaoException
	{
		SqlUpdate su = new SqlUpdate( dataSource, "UPDATE " + getTableName() + " SET uom_code = ?, uom_name = ?, remarks = ?, is_active = ?, is_delete = ?, created_by = ?, created_date = ?, updated_by = ?, updated_date = ? WHERE id = ?");
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.CHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.VARCHAR) );
		su.declareParameter( new SqlParameter( java.sql.Types.TIMESTAMP) );
		su.declareParameter( new SqlParameter( java.sql.Types.INTEGER) );
		su.compile();
		su.update( new Object[] { dto.getUomCode(),dto.getUomName(),dto.getRemarks(),dto.getIsActive(),dto.getIsDelete(),dto.getCreatedBy(),dto.getCreatedDate(),dto.getUpdatedBy(),dto.getUpdatedDate(),pk.getId() } );
	}

	/** 
	 * Deletes a single row in the uom table.
	 */
	@Transactional
	public void delete(UomPk pk) throws UomDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?",pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Uom
	 */
	public Uom mapRow(ResultSet rs, int row) throws SQLException
	{
		Uom dto = new Uom();
		dto.setId( rs.getInt( 1 ) );
		dto.setUomCode( rs.getString( 2 ) );
		dto.setUomName( rs.getString( 3 ) );
		dto.setRemarks( rs.getString( 4 ) );
		dto.setIsActive( rs.getString( 5 ) );
		dto.setIsDelete( rs.getString( 6 ) );
		dto.setCreatedBy( rs.getString( 7 ) );
		dto.setCreatedDate( rs.getTimestamp(8 ) );
		dto.setUpdatedBy( rs.getString( 9 ) );
		dto.setUpdatedDate( rs.getTimestamp(10 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "inventory..uom";
	}

	/** 
	 * Returns all rows from the uom table that match the criteria 'id = :id'.
	 */
	@Transactional
	public Uom findByPrimaryKey(int id) throws UomDaoException
	{
		try {
			List<Uom> list = jdbcTemplate.query("SELECT id, uom_code, uom_name, remarks, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ?", this,id);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new UomDaoException("Query failed", e);
		}
		
	}

    /** 
     * Returns all rows from the uom table that match the criteria ''.
     */
    @Transactional
    public List<Uom> findAll() throws UomDaoException {
        try {
            return jdbcTemplate.query("SELECT id, uom_code, uom_name, remarks, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = 'Y' ORDER BY id", this);
        } catch (Exception e) {
            throw new UomDaoException("Query failed", e);
        }
    }

	/** 
	 * Returns all rows from the uom table that match the criteria 'id = :id'.
	 */
	@Transactional
	public List<Uom> findWhereIdEquals(int id) throws UomDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, uom_code, uom_name, remarks, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE id = ? ORDER BY id", this,id);
		}
		catch (Exception e) {
			throw new UomDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the uom table that match the criteria 'uom_code = :uomCode'.
	 */
	@Transactional
	public List<Uom> findWhereUomCodeEquals(String uomCode) throws UomDaoException {
            try {
                return jdbcTemplate.query("SELECT id, uom_code, uom_name, remarks, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE uom_code = ? AND is_active = 'Y' ORDER BY uom_code", this,uomCode);
            } catch (Exception e) {
                throw new UomDaoException("Query failed", e);
            }
	}

	/** 
	 * Returns all rows from the uom table that match the criteria 'uom_name = :uomName'.
	 */
	@Transactional
	public List<Uom> findWhereUomNameEquals(String uomName) throws UomDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, uom_code, uom_name, remarks, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE uom_name = ? ORDER BY uom_name", this,uomName);
		}
		catch (Exception e) {
			throw new UomDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the uom table that match the criteria 'remarks = :remarks'.
	 */
	@Transactional
	public List<Uom> findWhereRemarksEquals(String remarks) throws UomDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, uom_code, uom_name, remarks, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE remarks = ? ORDER BY remarks", this,remarks);
		}
		catch (Exception e) {
			throw new UomDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the uom table that match the criteria 'is_active = :isActive'.
	 */
	@Transactional
	public List<Uom> findWhereIsActiveEquals(String isActive) throws UomDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, uom_code, uom_name, remarks, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_active = ? ORDER BY is_active", this,isActive);
		}
		catch (Exception e) {
			throw new UomDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the uom table that match the criteria 'is_delete = :isDelete'.
	 */
	@Transactional
	public List<Uom> findWhereIsDeleteEquals(String isDelete) throws UomDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, uom_code, uom_name, remarks, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE is_delete = ? ORDER BY is_delete", this,isDelete);
		}
		catch (Exception e) {
			throw new UomDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the uom table that match the criteria 'created_by = :createdBy'.
	 */
	@Transactional
	public List<Uom> findWhereCreatedByEquals(String createdBy) throws UomDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, uom_code, uom_name, remarks, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_by = ? ORDER BY created_by", this,createdBy);
		}
		catch (Exception e) {
			throw new UomDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the uom table that match the criteria 'created_date = :createdDate'.
	 */
	@Transactional
	public List<Uom> findWhereCreatedDateEquals(Date createdDate) throws UomDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, uom_code, uom_name, remarks, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE created_date = ? ORDER BY created_date", this,createdDate);
		}
		catch (Exception e) {
			throw new UomDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the uom table that match the criteria 'updated_by = :updatedBy'.
	 */
	@Transactional
	public List<Uom> findWhereUpdatedByEquals(String updatedBy) throws UomDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, uom_code, uom_name, remarks, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_by = ? ORDER BY updated_by", this,updatedBy);
		}
		catch (Exception e) {
			throw new UomDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the uom table that match the criteria 'updated_date = :updatedDate'.
	 */
	@Transactional
	public List<Uom> findWhereUpdatedDateEquals(Date updatedDate) throws UomDaoException
	{
		try {
			return jdbcTemplate.query("SELECT id, uom_code, uom_name, remarks, is_active, is_delete, created_by, created_date, updated_by, updated_date FROM " + getTableName() + " WHERE updated_date = ? ORDER BY updated_date", this,updatedDate);
		}
		catch (Exception e) {
			throw new UomDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the uom table that matches the specified primary-key value.
	 */
	public Uom findByPrimaryKey(UomPk pk) throws UomDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

}
